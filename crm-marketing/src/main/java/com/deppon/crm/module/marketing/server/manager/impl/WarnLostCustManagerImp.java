package com.deppon.crm.module.marketing.server.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.app.VelocityEngine;
import org.jfree.util.Log;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.server.service.IWarnLostCustSerivce;
import com.deppon.crm.module.marketing.server.utils.DateUtils;
import com.deppon.crm.module.marketing.server.utils.ExportExcel;
import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.WarnLostCust;
import com.deppon.crm.module.marketing.shared.domain.WarnLostMailAccount;
import com.deppon.crm.module.marketing.shared.domain.WarnStatusUpdateInfo;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;

/**
 * <p>
 * Description:流失预警客户<br />
 * </p>
 * 
 * @title IWarnLostCustDao.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author zzw
 * @version 2014-3-18
 */
public class WarnLostCustManagerImp implements IWarnLostCustManager {
	private static Logger logger = Logger.getLogger(WarnLostCustManagerImp.class);
	private static Logger log = Logger.getLogger("lavasoft");
	private IWarnLostCustSerivce warnLostCustSerivce;
	private VelocityEngine velocityEngine;
	private  MailSenderService mailSenderService;
	private IFileManager fileManager;
	static final String SUBJECT = "流失预警名单";
	public  static Vector<MailInfo> mailList=new Vector<MailInfo>();
/*	private static ThreadLocal<MailInfo> threadLocal=new ThreadLocal<MailInfo>(){
		 protected MailInfo initialValue() {
		        return null;
		    }
	};*/
	public  Map<String,MailInfo> mailMap=new ConcurrentHashMap<String,MailInfo>();
	private static final String TEMPLATE_NAME = "mail_template.vm";
	private static final String CHARSET = "UTF-8";
	private static final String PRIX = ".xls";
	private static final long MAXFILESIZE = 10 * 1024 * 1024;
	private static final String PRELOST = "PRELOST";
	private static final String NORMAL = "NORMAL";
	private static final String LOST = "LOST";
	private static final int NOTSEASON = 0;
	// 邮件内容类型
	private static final String CONTENDTYPE = "contendType";
	// 附件发送
	private static final String CONTENDTYPEWITHFILE = "sendWithFile";
	// 无附件发送
	private static final String CONTENDTYPEWITHOUTFILE = "sendWithoutFile";
	// 等级分布
	private static final String[] LEVELS = new String[] { WarnLostCust.AREA,
			WarnLostCust.BIGAREA, WarnLostCust.EXPRESSBIGAREA,
			WarnLostCust.CAREEA };
	private static final String[] POSITIONS = new String[] { "营业区区域经理",
			"大区总经理", "快递大区总经理", "事业部总裁" };
	// 等级对应的筛选条件
	private static final String[][] PARAS = new String[][] {
			{ WarnLostCust.GOLD, WarnLostCust.PLATINUM, WarnLostCust.DIAMOND },
			{ WarnLostCust.PLATINUM, WarnLostCust.DIAMOND },
			{ WarnLostCust.PLATINUM, WarnLostCust.DIAMOND,WarnLostCust.GOLD,WarnLostCust.NORMAL },
			{ WarnLostCust.DIAMOND } };

	public IWarnLostCustSerivce getWarnLostCustSerivce() {
		return warnLostCustSerivce;
	}

	public void setWarnLostCustSerivce(IWarnLostCustSerivce warnLostCustSerivce) {
		this.warnLostCustSerivce = warnLostCustSerivce;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public MailSenderService getMailSenderService() {
		return mailSenderService;
	}

	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}

	public IFileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * <p>
	 * Description:生成流失预警客户名单<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-11
	 * @return
	 */
	public void createWarnList() {
		warnLostCustSerivce.createWarnList();
		try {
			// 生成流失预警名单后，速度发送邮件
			sendMial();
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			System.out.println("发送失败");
			throw new BusinessException("邮件发送失败");
		}
	}

	/**
	 * <p>
	 * Description:根据部门获取流失客户(根据部门的等级)<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-11 para:deptStandardCode部门标杆编码， level 部门等级：54321
	 *          按照营业部至本部的组织等级， para：其他的参数，客户等级,客户类别等 暂时定为客户等级参数 String[]
	 *          beginTime 查询开始时间<createTime endTime 查询结束时间>createTime
	 * @return List<WarnLostCust>
	 */
	public List<WarnLostCust> searchWarnLostCust(String deptStandardCode,
			String level, String[] para, Date beginTime, Date endTime) {
		deptStandardCode = deptStandardCode.trim();
		if (!level.equals(WarnLostCust.EXPRESSBIGAREA)) {
			if (StringUtils.isEmpty(deptStandardCode)) {
				return null;
			}
		}
		if (StringUtils.isEmpty(level)) {
			level = WarnLostCust.AREA;
		}
		if (para == null || para.length < 1) {
			para = new String[] { WarnLostCust.GOLD, WarnLostCust.DIAMOND,
					WarnLostCust.PLATINUM };
		}
		return warnLostCustSerivce.searchWarnLostCust(deptStandardCode, level,
				para, beginTime, endTime);
	}

	/**
	 * <p>
	 * Description:获取符合条件的部门集合<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-11 para: level 部门等级：54321 按照营业部至本部的组织等级，
	 *          para：其他的参数，客户等级,客户类别等 暂时定为客户等级参数 String[]
	 * @return List<WarnLostCust>
	 */
	public List<WarnLostMailAccount> getDetpGroupFromWarnLost(String level,
			String[] para, String position) {

		if (StringUtils.isEmpty(level)) {
			level = WarnLostCust.AREA;
		}
		if (para == null || para.length < 1) {
			para = new String[] { WarnLostCust.GOLD, WarnLostCust.DIAMOND,
					WarnLostCust.PLATINUM };
		}
		return warnLostCustSerivce.getDetpGroupFromWarnLost(level, para,
				position);
	}

	public MailInfo getMailInfo(WarnLostMailAccount MailAccount) {
		if (MailAccount == null) {
			return null;
		}
		MailInfo mailInfo = new MailInfo();
		// 主题
		String date = new SimpleDateFormat("yyyy-mm-dd").format(new Date());
		mailInfo.setSubject(date + " " + SUBJECT);
		// 设置主题
		mailInfo.setSubject(SUBJECT);
		// 设置发件人
		mailInfo.setFrom(mailSenderService.getUserName());
		// 设置发送地址
		mailInfo.setTo(new String[] { MailAccount.getEmailAddress()});
		// mailInfo.setTo(new String[] {"zhangzhenwei004@deppon.com"});
		return mailInfo;
	}

	/**
	 * <p>
	 * Description:邮件发送方法，通过多线程完成<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-4-10
	 * 
	 * @return 根据List<WarnLostMailAccount> 获得流失客户所属部门的 各个需要层级（小区 大区 快递
	 *         事业部）相应的联系人，然后将层级下的客户筛选出来， 进行邮件发送
	 */
	public int sendMial() throws Exception {
		 String  level;
		 String[] para;
		 String position;
		 MailInfo mailInfo = null;
		 List<WarnLostMailAccount> accouts = null;
		 logger.error("------------1、流失预警邮件发送开始："+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		for (int i = 0; i < LEVELS.length; i++) {
			level = LEVELS[i];
			para = PARAS[i];
			position = POSITIONS[i];
			accouts = getDetpGroupFromWarnLost(level,
					para, position);
			//邮件发送
			
			for (WarnLostMailAccount accout : accouts) {
				mailInfo=getMailInfo(level, para, accout);
				mailSenderService.sendExtranetMail(mailInfo);
				mailInfo = null;
			}
		}
		logger.error("------------2、流失预警邮件发送成功："+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()));
		return 1;
	}
	//根据名单生成 相应的邮件文件
	public  MailInfo getMailInfo(String level, String[] para,
			WarnLostMailAccount MailAccount){
			MailInfo mailInfo = null;
			String deptStandardCode = "";
			File atta = null;
			Map<String, String> template = new HashMap<String, String>();
			List<WarnLostCust> warnLostCusts = null;
			FileInputStream fileInputStream = null;
			FileInfo fileInfo = null;
			int fileSize = 0;
			// 获取邮件收件人信息
			mailInfo = getMailInfo(MailAccount);
			//mailInfo.setTo(new String[] { "zhangzhenwei004@deppon.com" });
			// 获取邮件收人的部门标杆编码
			deptStandardCode = MailAccount.getDeptStandardCode();
			// 获取此部门下的所有的符合条件的客户
			warnLostCusts = searchWarnLostCust(deptStandardCode, level, para,
					null, null);
			// 是否为空
			if (warnLostCusts == null || warnLostCusts.size() <= 0) {
				return null;
			}
			try {
				// 生成相应的邮件附件
				/*atta =  ExportExcel.resultSetToExcel(WarnLostCust.HEAD,
						WarnLostCust.KEYS, warnLostCusts, generateFileName()
								+ PRIX, "SHEET1");*/
				
				
				// 生成邮件附件（使用模板）
				fileInfo = createPromptFile(warnLostCusts);
				atta = new File(fileInfo.getSavePath());
				FileInputStream fis = new FileInputStream(atta);
				fileSize = fis.available();
			} catch (Exception e1) {
				log.info(e1);
				e1.printStackTrace();
			}
			// 如果没有附件,不发送邮件
			// 邮件过大，不发送邮件
			
			if (fileSize >= MAXFILESIZE) {
				template.put(CONTENDTYPE, CONTENDTYPEWITHOUTFILE);
			} else {
				template.put(CONTENDTYPE, CONTENDTYPEWITHFILE);
				if (atta != null) {
					File[] files = { atta };
					mailInfo.setAttachments(files);
				}
		    }
			template.put("warnTime", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
			String content = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, TEMPLATE_NAME, CHARSET, template);
			// 设置文字内容
			mailInfo.setContent(content);
			
			
			//mailMap.put(MailAccount.getEmpCode(), mailInfo);
			return mailInfo;
	}
	
	private FileInfo createPromptFile(List<WarnLostCust> warnLostCusts) {
		File file = null;
		FileInfo fileInfo = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(PropertiesUtil.getInstance()
					.getProperty("excelExportTemplatePath").trim().toString());
			stringBuffer.append("/");
			stringBuffer.append(PropertiesUtil.getInstance().getProperty("WarnLostCustTemplate"));
			file = new File(stringBuffer.toString());
			if (!file.exists()) {
				logger.error(stringBuffer.toString() + "：流失预警邮件附件模板文件不存在！");
				FileException fe = new FileException(
						FileExceptionType.FILE_EXCEPTION_FILENOTEXISTS);
				throw new GeneralException(fe.getErrorCode(),
						fe.getMessage(), fe, new Object[] {}) {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
				};
			}
			
			//上传文件
			fileInfo = fileManager.fileUpload(file, "流失预警.xls", "marketing", "marketing");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("流失预警邮件excel生成失败");
		}
		this.getExcelFile(warnLostCusts, new File(fileInfo.getSavePath()));
		fileInfo.setCreateDate(new Date());
		fileInfo.setFileType("application/excel");
		fileInfo.setSourceType("WARNLOST_CUST");
		return fileInfo;
	}
	
	public FileInputStream getExcelFile(List<WarnLostCust> warnLostCusts, File file) {
		if(file == null || file.length() <= 0) {
			return null;
		}
		String fileName = file.getName();
		int excelVersion = 2003;
		if(fileName.endsWith(".xls")) {
			excelVersion = 2003;
		}else if(fileName.endsWith(".xlsx")) {
			excelVersion = 2007;
		}else{
			return null;
		}
		
		Workbook wb = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			if(excelVersion == 2003){
				wb = new HSSFWorkbook(is);
			}else if(excelVersion == 2007){
				wb = new XSSFWorkbook(is);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 一、导出数据
		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		// 创建数据
		int i = 1;
		for(WarnLostCust warnLostCust : warnLostCusts) {
			row = sheet.createRow(i++);
			row.createCell(0).setCellValue(warnLostCust.getCadreStandardName());
			row.createCell(1).setCellValue(warnLostCust.getCareerDeptStandardName());
			row.createCell(2).setCellValue(warnLostCust.getBigAreaStandardName());
			row.createCell(3).setCellValue(warnLostCust.getAreaStandardName());
			row.createCell(4).setCellValue(warnLostCust.getDeptStandardName());
			
			row.createCell(5).setCellValue(warnLostCust.getCustNumber());
			row.createCell(6).setCellValue(warnLostCust.getCustLevelDesc());
			row.createCell(7).setCellValue(warnLostCust.getCustName());
			row.createCell(8).setCellValue(warnLostCust.getCustCategoryDesc());
			row.createCell(9).setCellValue(warnLostCust.getWarnStatusDesc());
			
			row.createCell(10).setCellValue(dateNullToString(warnLostCust.getPreDeliverytBiegnTime()));
			row.createCell(11).setCellValue(dateNullToString(warnLostCust.getPreDeliverytendTime()));
			row.createCell(12).setCellValue(warnLostCust.getIsSeasonDesc());
			row.createCell(13).setCellValue(warnLostCust.getSeasonTime());
			row.createCell(14).setCellValue(dateNullToString(warnLostCust.getCreateTime()));
			
			row.createCell(15).setCellValue(warnLostCust.getIntervalNendTime());
			row.createCell(16).setCellValue(dateNullToString(warnLostCust.getLastsendTime()));
			row.createCell(17).setCellValue(warnLostCust.getLastsendMoney());
			row.createCell(18).setCellValue(warnLostCust.getLostCause());
			row.createCell(19).setCellValue(warnLostCust.getLostCustRemark());
			
			row.createCell(20).setCellValue(dateNullToString(warnLostCust.getWarnStatusChangeTime()));
			row.createCell(21).setCellValue(warnLostCust.getWarnStatusChangeUser());
			row.createCell(22).setCellValue(warnLostCust.getSuminRecent90day());
		}
		FileOutputStream fileOut = null;
		FileInputStream fileInput = null;
		try {
			// 创建excel文件
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			fileInput = new FileInputStream(file);
		} catch (IOException e) {
			Log.info("文件操作异常", e);
			try {
				throw e;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return fileInput;
	}
	
	private static String dateNullToString(Date date) {
		if(date == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
			
	
	private String generateFileName() {
		return UUID.randomUUID().toString();
	}
	/**
	 * <p>
	 * Description:内部类，根据层级关系，筛选出来对应的客户列表<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-11 para: level 部门等级： 按照营业部至本部的组织等级，
	 *          para：其他的参数，客户等级,客户类别等 暂时定为客户等级参数 String[]
	 * 
	 * @return List<WarnLostCust>
	 */
	class  SendMailThread implements Runnable{
		private String level;
		private String[] para;
		private WarnLostMailAccount MailAccount;
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String[] getPara() {
			return para;
		}
		public void setPara(String[] para) {
			this.para = para;
		}
		public SendMailThread() {
		}
		public SendMailThread(String level, String[] para,
				WarnLostMailAccount MailAccount ) {
			this.level = level;
			this.para = para;
			this.MailAccount = MailAccount;
		}
		public void run() {
			  /* MailInfo mailInfo=threadLocal.get();
			   if(mailInfo==null){*/
			   getMailInfo(level, para, MailAccount);
			  // }
			    //MailInfo mailInfo=getMailInfo(level, para, MailAccount);
				/*try {
					mailSenderService.sendExtranetMail(mailInfo);
				} catch (MessagingException e) {
					e.printStackTrace();
				}*/
				//mailMap.put(MailAccount.getEmpCode(), mailInfo);
			    //mailList.add(mailInfo);
	    }
		/*@Override
		public MailInfo call() throws Exception {
			MailInfo maile=getMailInfo(level, para, MailAccount);
			synchronized (mailMap){
				mailMap.put(MailAccount.getEmpCode(), maile);
			}
			return null;
		} */
	}

	/**
     * <p>
     * Description:查看客户预警信息<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * ，
     *      custNumber 某客户编码，用去取此用户下的权限部门（目前仅限于快递大区）
     * 		custName   客户名称，
     *       beginTime　开始时间
     *       endTime 结束时间
     *       deptid  部门ID
     * 		
     * @return  WarnLostCust
     */
	public List<WarnLostCust> searchWarnLostCust(String deptid, String custNumber,String custName, Date beginTime,Date endTime)
    {
		if(StringUtils.isEmpty(custNumber)&&StringUtils.isEmpty(custName)&&StringUtils.isEmpty(deptid)){
			return null;
		}
		if(!StringUtils.isEmpty(custNumber)){
			custNumber=custNumber.trim();
		}
		if(!StringUtils.isEmpty(custName)){
			custName=custName.trim();
		}
		return warnLostCustSerivce.searchWarnLostCust(deptid, custNumber, custName, beginTime, endTime);
	}
	/**
	 * <p>
	 * Description:手动更新客户预警客户信息<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-11 para: WarnStatusUpdateInfo 更新的详细信息 return void
	 * @return List<WarnLostCust>
	 */
	public void updateWarnInfo(WarnStatusUpdateInfo info) {
		if (info == null || StringUtils.isEmpty(info.getCustNumber())) {
			return;
		}
		Date lastSendTime = info.getLastSendTime();
		Date warnTime = info.getWarnTime();
		int isseason = info.getIsseason();
		String warnStatus = info.getWarnStatus();
		
		// 如果信息中的客户预警状态为预流失和季节性状态为否（0） 则表示用户未修改客户预警信息，
		// 需要判断客户状态的修改
		if (isseason == NOTSEASON && PRELOST.equals(warnStatus)) {
			// 如果客户在预流失后有发货则修改为正常
			if(lastSendTime!=null){
				info= dealByLastSendTime( info, warnTime , lastSendTime)	;
			}else{
				// 再查询一次客户最近一次发货 
				WarnStatusUpdateInfo newInfo = this.searchWarnLostCustInfo(0, info.getCustNumber());
				if(newInfo!=null){
					lastSendTime=newInfo.getLastSendTime();
				}
				if(lastSendTime!=null){
					info=dealByLastSendTime( info, warnTime , lastSendTime)	;
				}
			}
		}
		warnLostCustSerivce.updateWarnInfo(info);
	}
	private WarnStatusUpdateInfo dealByLastSendTime(WarnStatusUpdateInfo info,Date warnTime ,Date lastSendTime){
		if(lastSendTime==null){
			return info;
		}else{
			if (lastSendTime.after(warnTime)) {
				
				info.setWarnStatus(NORMAL);
				info.setPreSendBeginTime(null);
				info.setPreSendEndTime(null);
				info.setLostCause(null);
				info.setLostCauseRemark(null);
			} else {
				// 如果客户在预流失后无发货切最近一次发货时间与当前时间相差大于60天
				// 则定义为流失状态
				int dateM=0;
				Date now = new Date();
				try {
					dateM = DateUtils.daysBetween(now, lastSendTime);
				} catch (ParseException e) {
					log.info(e.getMessage());
				}
				if (dateM > 60) {
					info.setPreSendBeginTime(null);
					info.setPreSendEndTime(null);
					info.setLostCause(null);
					info.setLostCauseRemark(null);
					info.setWarnStatus(LOST);
				}
			}
		}
		return info;

	}
	/**
	 * <p>
	 * Description:查看某客户的预警信息<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-11 para:deptStandardCode部门标杆编码， empCode
	 *          某用户编码，用去取此用户下的权限部门（目前仅限于快递大区） custName 客户名称，
	 * 
	 * @return WarnLostCust
	 */
	public WarnStatusUpdateInfo searchWarnLostCustInfo(int fcustId,
			String custNum) {
		if (StringUtils.isEmpty(custNum) && fcustId <= 0) {
			return null;
		}
		return warnLostCustSerivce.searchWarnLostCustInfo(fcustId, custNum);
	}

	/**
	 * <p>
	 * Description:自动更新客户的预警信息以及预发货时限<br />
	 * </p>
	 * 
	 * @author 张振伟
	 * @version 0.1 2014-3-27
	 * 
	 * @return
	 */
	public void updateCustWarnStatusAndPreSendTime() {
		warnLostCustSerivce.updateCustWarnStatusAndPreSendTime();
	}

	@Override
	public List<WarnLostCust> searchWarnLostCust(String deptid,
			String custNumber, String custName, Date beginTime, Date endTime,
			int StartRecord, int limitRecord) {
		if(StringUtils.isEmpty(custNumber)&&StringUtils.isEmpty(custName)&&StringUtils.isEmpty(deptid)){
			return null;
		}
		if(!StringUtils.isEmpty(custNumber)){
			custNumber=custNumber.trim();
		}
		if(!StringUtils.isEmpty(custName)){
			custName=custName.trim();
		}
		if(StartRecord<0||limitRecord<=0){
			return searchWarnLostCust(deptid, custNumber, custName, beginTime, endTime);
		}
		return warnLostCustSerivce.searchWarnLostCust(deptid, custNumber, custName, beginTime, endTime, StartRecord, limitRecord);
	}
	//返回流文件了，供文件导出
	@Override
	public FileInputStream searchWarnLostCust(String deptid, Date beginTime,
			Date endTime) throws Exception {
		List<WarnLostCust> warnLostCusts=warnLostCustSerivce.searchWarnLostCust(deptid, null, null, beginTime, endTime, 0, 0);
		File f=ExportExcel.resultSetToExcel(WarnLostCust.HEAD,
				WarnLostCust.KEYS, warnLostCusts, generateFileName()
				+ PRIX, "SHEET1");
		FileInputStream fi=new FileInputStream(f);
		return fi;
	}
	/**
     * <p>
     * Description:查看客户预警的总数<br />
     * </p>
     * @author 张振伟
     * @version 0.1 2014-3-11
     * ，
     *      custNumber 某客户编码，用去取此用户下的权限部门（目前仅限于快递大区）
     * 		custName   客户名称，
     *       beginTime　开始时间
     *       endTime 结束时间
     *       deptid  部门ID
     * @return  int 查询总数
     */
	public int searchWarnLostCustCount(String deptid, String custNumber,String custName, Date beginTime,Date endTime){
		if(StringUtils.isEmpty(deptid)&&StringUtils.isEmpty(custNumber)&&StringUtils.isEmpty(custName)){
			return 0;
		}
		return warnLostCustSerivce.searchWarnLostCustCount(deptid, custNumber, custName, beginTime, endTime);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager#searchWarnLostCustFor360View(java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WarnLostInfoFor360> searchWarnLostCustFor360View(String deptid,
			String custNumber, String custName, Date beginTime, Date endTime) {
		List<WarnLostInfoFor360> list = new ArrayList<WarnLostInfoFor360>();
		if(StringUtils.isEmpty(custNumber)&&StringUtils.isEmpty(custName)&&StringUtils.isEmpty(deptid)){
			return list;
		}
		if(StringUtils.isNotEmpty(custNumber)){
			custNumber=custNumber.trim();
		}
		if(StringUtils.isNotEmpty(custName)){
			custName=custName.trim();
		}
		list = warnLostCustSerivce.searchWarnLostCustFor360View(deptid,custNumber,custName,beginTime,endTime);
		return list;
	}
}
