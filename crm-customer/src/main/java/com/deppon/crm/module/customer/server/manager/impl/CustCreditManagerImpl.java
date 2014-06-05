package com.deppon.crm.module.customer.server.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.common.server.manager.IMessageManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.app.VelocityEngine;
import org.jfree.util.Log;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.adapter.mail.MailException;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.service.ICustCreditService;
import com.deppon.crm.module.customer.server.utils.CustCreditUtil;
import com.deppon.crm.module.customer.server.utils.ZipUtil;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.CustomerExceptionType;
import com.deppon.crm.module.frameworkimpl.server.cache.ServerParameterCache;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.sysmail.server.service.ISysMailSendService;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description: 客户信用manager<br/>
 * </p>
 * @title CustCreditManagerImpl
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author andy
 * @version 1.0
 * @date 2014-03-07
 *
 */
public class CustCreditManagerImpl implements ICustCreditManager {
	private static Logger logger = Logger.getLogger(CustCreditManagerImpl.class);

	private ICustCreditService custCreditService;
	private IDepartmentService departmentService;
	private IEmployeeService employeeService;
	private IFileManager fileManager;
	private VelocityEngine velocityEngine;
	private MailSenderService mailSenderService;
	private ISysMailSendService sysMailSendService;
	private IMessageManager messageManager;
	private IUserService userService;
	
	// 查询近 3个月发货金额
	private static final int MONTH = 3;
	// 批量处理（1000/次）
	private static final int BATCH_COUNT = 1000;
	// 月结
	private static final String MONTHLY_CUST = "MONTH_END";
	// 附件 excel 大小限制(10M)
	private static final int EXCEL_SIZE = 10485760;
	// 应收账款管理小组
	private static final String MANAGEMENT_OF_ACCOUNTS_RECEIVABLE_DEPTCODE = "W01030601";
	// CRM管理组
	private static final String CRM_DEPTCODE = "W01000201083108";
	// 客户管理小组负责人
	private static final String CUSTOMER_MANAGEMENT_TEAM = "W01000301070309";
	// CRM推进小组负责人
	private static final String CRM_PROMOTE_TEAM = "W0100020108310801";
	// 邮件内容模板
	private static final String CUSTCREDIT_TEMPLATE_NAME = "mail_custCredit_template.vm";
	private static final String CHARSET = "UTF-8";
	// 全国发送
	private static final String WHOLECOUNTRY = "全国";
	// 经营本部
	private static final String SOUTHCHINAOPERATE   = "W0133";  //华南经营本部
	private static final String CENTRALCHINAOPERATE = "W0132";  //华中经营本部
	private static final String NORTHCHINAOPERATE   = "W0131";  //华北经营本部
	// 经营本部营销推进组
	private static final String SOUTH_MARKETING_GROUP   = "W01330103"; // 华南营销推进组
	private static final String CENTEAL_MARKETING_GROUP = "W01320103"; // 华中营销推进组
	private static final String NORTH_MARKETING_GROUP   = "W01310704"; // 华北营销推进组
	// 经营本部营运标准推进组
	private static final String SOUTH_OPERATIONALSTANDARDSWORKING   = "W01330101";   //华南营运标准推进组
	private static final String CENTEAL_OPERATIONALSTANDARDSWORKING = "W01320101";   //华中营运标准推进组
	private static final String NORTH_OPERATIONALSTANDARDSWORKING   = "W01310701";   //华北营运标准推进组
	// 经营本部营运办公室 
	private static final String SOUTH_BUSINESS_OFFICE   = "W013301";  //华南营运办公室 
	private static final String CENTEAL_BUSINESS_OFFICE = "W013201";  //华中营运办公室 
	private static final String NORTH_BUSINESS_OFFICE   = "W013107";  //华北营运办公室 
	// 点部
	private static final String MINISTRY = "点部";
	
	
	// 事业部营销管理组
	//private static final String MANAGEMENT_GROUP_MARKETING_DEPARTMENT = "事业部营销管理组";
	// 大区统计组
	//private static final String DISTRICT_STATISTICS_GROUP = "大区统计组";
	// 分部统计组
	//private static final String DIVISION_STATISTICS_GROUP = "分部统计组";
	// 事业部办公室
	//private static final String DEPARTMENT_OFFICE = "事业部办公室";
	// 大区办公室
	//private static final String BIGAREA_DEPARTMENT_OFFICE = "大区办公室";
	// 快递分部
	//private static final String EXPRESS_BRANCH_DEPTNAME = "快递分部";
	// 快递大区
	//private static final String EXPRESS_BIG_AREA_DEPTNAME = "快递大区";
	// 快递大区办公室
	//private static final String EXPRESS_BIGAREA_DEPARTMENT_OFFICE = "快递大区办公室";
	// 快递大区统计组
	//private static final String EXPRESS_DISTRICT_STATISTICS_GROUP = "快递大区统计组";
	// 快递点部
	//private static final String EXPRESS_THE_MINISTRY = "快递点部";
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String CUSTCREDIT_SOURCE_TYPE = "CUST_CREDIT";
	private static final String DIM = "^,^";
	
	
	public ICustCreditService getCustCreditService() {
		return custCreditService;
	}

	public void setCustCreditService(ICustCreditService custCreditService) {
		this.custCreditService = custCreditService;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IFileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
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

	public ISysMailSendService getSysMailSendService() {
		return sysMailSendService;
	}

	public void setSysMailSendService(ISysMailSendService sysMailSendService) {
		this.sysMailSendService = sysMailSendService;
	}

	public IMessageManager getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * <p>
	 * 定时恢复散客、固客的临欠额度<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return void
	 * 
	 */
	@Override
	public void getDeliverMoneyByCondtion() {
		// 添加最近三个月
		Date beginDate = CustCreditUtil.getBeginDate(MONTH, 1);
		Date endDate = CustCreditUtil.getEndDate();
		custCreditService.getDeliverMoneyByCondtion(beginDate, endDate);
	}

	/**
	 * 
	 * <p>
	 * 根据客户id，查询客户信用信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custId
	 * @return CustCredit
	 * 
	 */
	@Override
	public CustCredit getCustCreditByCustId(String custId) {
		CustCredit custCredit = custCreditService.getCustCreditByCustId(custId);
		if(custCredit == null) {
			custCredit = new CustCredit();
		}
		return custCredit;
	}

	/**
	 * 
	 * <p>
	 * 更新信用较差客户配置信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param config
	 * @return void
	 * 
	 */
	@Override
	public void updateCustCreditConfig(CustCreditConfig config) {
		User user = (User) UserContext.getCurrentUser();
		String userId = user.getEmpCode().getId();
		config.setModifyUser(userId);
		custCreditService.updateCustCreditConfig(config);
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户配置信息<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return CustCreditConfig
	 * 
	 */
	@Override
	public CustCreditConfig getCustCreditConfig() {
		return custCreditService.getCustCreditConfig();
	}

	/**
	 * 
	 * <p>
	 * 查询客户未还款信息
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<CustCredit>
	 * 
	 */
	@Override
	public List<CustCredit> getCustBadCreditList() {
		return custCreditService.getCustBadCreditList();
	}

	/**
	 * 
	 * <p>
	 * 标记信用较差客户
	 * 
	 * 1.临欠超过 N 天未还临欠客户
	 * 2.按照月结合同规定时间后 N 天未还款的月结客户
	 * 3.逾期 N 天未还款的客户
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return void
	 * @throws ParseException 
	 * 
	 */
	@Override
	public void updateCustBadCredit() throws ParseException {
		/*CustCreditConfig config = this.getCustCreditConfig();
		// 临时欠款超过 N  天未还款临时欠款客户
		int maxOverdraftDay = config.getMaxOverdraftDay();
		// 按照月结合同规定时间后 N   天未还款的月结客户
		int monthEndOvertake = config.getMonthEndOvertake();
		// 逾期 N 天未还款的客户
		int overdueMonth = config.getOverdueMonth();
		
		Date currDate = CustCreditUtil.getNowDate();
		List<CustCredit> custBadCreditList = new ArrayList<CustCredit>();
		List<CustCredit> creditList = custCreditService.getCustBadCreditExcelList();
		for (CustCredit custCredit : creditList) {
			if(custCredit != null) {
				// 最早一笔未还临欠开单时间
				Date temporarydebitDate = custCredit.getTemporarydebitDate();
				
				// 1.临欠超过 N 天未还临欠客户
				int day = CustCreditUtil.daysBetween(temporarydebitDate, currDate);
				if(day > maxOverdraftDay) {
					custBadCreditList.add(custCredit);
					continue ;
				}
				
				// 最早一笔未还月结开单时间
				Date nomonthlyDate = custCredit.getNomonthlyDate();
				// 合同结款日期 
				int resultDate = custCredit.getRepaymentDate();
				Date date = CustCreditUtil.getAddMonthDate(nomonthlyDate, 1, resultDate);
				// 2.按照月结合同规定时间后 N 天未还款的月结客户
				day = CustCreditUtil.daysBetween(date, currDate);
				if(day > monthEndOvertake) {
					custBadCreditList.add(custCredit);
					continue ;
				}
				
				// 3.逾期 N 天未还款的客户
				day = CustCreditUtil.daysBetween(temporarydebitDate, currDate);
				int nomonthDay = CustCreditUtil.daysBetween(nomonthlyDate, currDate);
				if(day > overdueMonth || nomonthDay > overdueMonth) {
					custBadCreditList.add(custCredit);
					continue ;
				}
			}
		}
		List<List<CustCredit>> badList = CustCreditUtil.batchList(custBadCreditList);
		for (List<CustCredit> list : badList) {
			custCreditService.updateCustBadCredit(list);
		}*/
		
		//标记信用较差客户（存储过程）
		custCreditService.updateCustBadCredit();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户名单
	 * 
	 * 1.临时欠款超过 N  天未还款临时欠款客户
	 * 2.按照月结合同规定时间后 N   天未还款的月结客户
	 * 3.逾期  N  天未还款的客户
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return List<CustCredit>
	 * @throws ParseException 
	 * 
	 */
	@Override
	public List<CustCredit> getCustBadCreditExcelList() throws ParseException {
		CustCreditConfig config = this.getCustCreditConfig();
		// 临时欠款超过 N  天未还临时欠款客户
		int maxOverdraftDay = config.getMaxOverdraftDay();
		// 按照月结合同规定时间后 N   天未还款的月结客户
		int monthEndOvertake = config.getMonthEndOvertake();
		// 逾期 N 天未还款的客户
		int overdueMonth = config.getOverdueMonth();
		
		// 最长一笔临欠达到 N 天（含）的时欠款客户
		int earliestDay = config.getEarliestDay();
		// 距离规定结款日期前   N   天未还款的月结客户
		int beforePaymentdateDay = config.getBeforePaymentdateDay();
		
		Date currDate = CustCreditUtil.getNowDate();
		List<CustCredit> custBadCreditList = new ArrayList<CustCredit>();
		List<CustCredit> creditList = custCreditService.getCustBadCreditExcelList();
		for (CustCredit custCredit : creditList) {
			if(custCredit != null && !StringUtils.isEmpty(custCredit.getResponsibilityDeptId())) {
				StringBuffer norepaymentTypeSB = new StringBuffer();
				StringBuffer norepaymentTypeNameSB = new StringBuffer();
				List<Department> deptList = departmentService.getAllParentDeptByDeptId(custCredit.getResponsibilityDeptId());
				if(deptList.size() > 5) {
					// 营业部---小区---大区---事业部---经营本部
					custCredit.setAreaDeptId(deptList.get(1).getId());
					custCredit.setAreaDeptName(deptList.get(1).getDeptName());
					
					custCredit.setBigAreaDeptId(deptList.get(2).getId());
					custCredit.setBigAreaDeptName(deptList.get(2).getDeptName());
					
					custCredit.setBusDeptId(deptList.get(3).getId());
					custCredit.setBusDeptName(deptList.get(3).getDeptName());
					
					custCredit.setManagementDeptId(deptList.get(4).getId());
					custCredit.setManagementDeptName(deptList.get(4).getDeptName());
				}
				
				// 零担结款方式
				String payway = custCredit.getPayway();
				//　快递结款方式
				String exPayWay = custCredit.getExPayWay();
				// 是否月结
				if((!StringUtils.isEmpty(payway) && payway.equalsIgnoreCase(MONTHLY_CUST)) || 
						(!StringUtils.isEmpty(exPayWay) && exPayWay.equalsIgnoreCase(MONTHLY_CUST)) ) {
					custCredit.setIsMonthlyCust((byte) 1);
				}else {
					custCredit.setIsMonthlyCust((byte) 0);
				}
				// 临欠信用额度
				double procreditAmount = custCredit.getOverdraftCreditAmount();
				// 月结信用额度
				double arrearAmount = custCredit.getMonthCreditAmount();
				// 临欠当期应收金额
				double overdrftRecivableAmount = custCredit.getOverdrftRecivableAmount();
				// 月结应收总额
				double monReceivableAmount = custCredit.getMonReceivableAmount();
				// 临欠信用额度余额 = 临时欠款信用额度 - 临时欠款当期应收金额
				double overdraftCreditBalanceAmount = procreditAmount - overdrftRecivableAmount;
				// 月结信用额度余额     = 月结信用额度 - 月结应收总额
				double monthCreditBalanceAmount = arrearAmount - monReceivableAmount;
				
				custCredit.setMonReceivableAmount(monReceivableAmount);
				custCredit.setOverdraftCreditBalanceAmount(overdraftCreditBalanceAmount);
				custCredit.setMonthCreditBalanceAmount(monthCreditBalanceAmount);
				
				// 临时欠款信用额度使用率 = 临欠当期应收金额/临欠信用额度*100%
				String overdraftCreditUserate = "0";
				if(procreditAmount != 0) {
					overdraftCreditUserate = CustCreditUtil.getMultiply(CustCreditUtil.getDivide(overdrftRecivableAmount, procreditAmount, 2), 100) + "";
				}
				// 月结信用额度使用率 = 月结应收总额/月结信用额度*100%
				String monthCreditUserate = "0";
				if(arrearAmount != 0) {
					monthCreditUserate = CustCreditUtil.getMultiply(CustCreditUtil.getDivide(monReceivableAmount, arrearAmount, 2), 100) + "";
				}
				custCredit.setOverdraftCreditUserate(overdraftCreditUserate);
				custCredit.setMonthCreditUserate(monthCreditUserate);
				
				boolean isBad = false;
				// 最早一笔未还临欠开单时间
				Date temporarydebitDate = custCredit.getTemporarydebitDate();
				
				// 1.临欠超过 N 天未还临欠客户
				int day = CustCreditUtil.daysBetween(temporarydebitDate, currDate);
				if(day > maxOverdraftDay) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.MAXOVERDRAFTDAY_CONTENT, 
							new Object[] {maxOverdraftDay});
					custCredit.setNorepaymentType(norepaymentTypeSB.append("1").append(DIM).toString());
					custCredit.setNorepaymentTypeName(norepaymentTypeNameSB.append(norepaymentType).append(DIM).toString());
					isBad = true;
				}
				
				// 最早一笔未还月结开单时间
				Date nomonthlyDate = custCredit.getNomonthlyDate();
				// 合同结款日期 
				int resultDate = custCredit.getRepaymentDate();
				Date date = CustCreditUtil.getAddMonthDate(nomonthlyDate, 1, resultDate);
				// 2.按照月结合同规定时间后 N 天未还款的月结客户
				day = CustCreditUtil.daysBetween(date, currDate);
				if(day > monthEndOvertake) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.MONTHENDOVERTAKE_CONTENT, 
							new Object[] {monthEndOvertake});
					custCredit.setNorepaymentType(norepaymentTypeSB.append("2").append(DIM).toString());
					custCredit.setNorepaymentTypeName(norepaymentTypeNameSB.append(norepaymentType).append(DIM).toString());
					isBad = true;
				}
				
				// 3.逾期 N 天未还款的客户
				day = CustCreditUtil.daysBetween(temporarydebitDate, currDate);
				int nomonthDay = CustCreditUtil.daysBetween(nomonthlyDate, currDate);
				if(day > overdueMonth || nomonthDay > overdueMonth) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.OVERDUEMONTH_CONTENT, 
							new Object[] {overdueMonth});
					custCredit.setNorepaymentType(norepaymentTypeSB.append("3").append(DIM).toString());
					custCredit.setNorepaymentTypeName(norepaymentTypeNameSB.append(norepaymentType).append(DIM).toString());
					isBad = true;
				}
				
				// 4.最长一笔临欠达到 N 天（含）的时欠款客户
				day = CustCreditUtil.daysBetween(temporarydebitDate, currDate);
				if(day >= earliestDay) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.EARLIESTDAY_CONTENT, 
							new Object[] {earliestDay});
					custCredit.setNorepaymentType(norepaymentTypeSB.append("4").append(DIM).toString());
					custCredit.setNorepaymentTypeName(norepaymentTypeNameSB.append(norepaymentType).append(DIM).toString());
					isBad = true;
				}
				
				// 5.距离规定结款日期前   N   天未还款的月结客户
				day = CustCreditUtil.daysBetween(date, currDate);
				if(day >= beforePaymentdateDay*-1) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.BEFOREPAYMENTDATEDAY_CONTENT, 
							new Object[] {beforePaymentdateDay});
					custCredit.setNorepaymentType(norepaymentTypeSB.append("5").append(DIM).toString());
					custCredit.setNorepaymentTypeName(norepaymentTypeNameSB.append(norepaymentType).append(DIM).toString());
					isBad = true;
				}
				if(isBad) {
					custBadCreditList.add(custCredit);
				}
			}
		}
		return custBadCreditList;
	}
	
	private FileInfo createPromptFile(CustCreditConfig config, List<CustCredit> custBadCreditList,
			String deptId, String fileName, String fileType) throws IOException{
		File file = null;
		FileInfo fileInfo = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(PropertiesUtil.getInstance()
					.getProperty("excelExportTemplatePath").trim().toString());
			stringBuffer.append("/");
			stringBuffer.append(PropertiesUtil.getInstance().getProperty("CustCreditReportTemplate"));
			file = new File(stringBuffer.toString());
			if (!file.exists()) {
				logger.error(stringBuffer.toString() + "：对应文件不存在！");
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
			fileInfo = fileManager.fileUpload(file, fileName, fileType, fileType);
			fileManager.saveFileInfo(fileInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("人品出问题啦---信用较差客户excel生成失败");
		}
		logger.error("-------------------2、信用较差客户excel数据写入：begin");
		this.getExcelFile(config, custBadCreditList, new File(fileInfo.getSavePath()));
		logger.error("-------------------7、信用较差客户excel数据写入：end");
		/*User user = (User) UserContext.getCurrentUser();
		fileInfo.setCreateUser(user.getEmpCode().getId());
		fileInfo.setCreateDept(user.getEmpCode().getDeptId().getId());*/
		fileInfo.setCreateDate(new Date());
		fileInfo.setSourceId(deptId);
		fileInfo.setFileType("application/excel");
		fileInfo.setSourceType(CUSTCREDIT_SOURCE_TYPE);
		
		//file.deleteOnExit();  不删除模板
		return fileInfo;
	}

	/**
	 * 
	 * <p>
	 * 插入信用较差客户名单
	 * 
	 * 1.临时欠款超过 N  天未还款临时欠款客户
	 * 2.按照月结合同规定时间后 N   天未还款的月结客户
	 * 3.逾期  N  天未还款的客户
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param 
	 * @return void
	 * @throws ParseException 
	 * 
	 */
	@Override
	public void insertCustBadCreditReport() throws ParseException {
		/*// 获取信用较差客户名单
		List<CustCredit> custBadCreditList = this.getCustBadCreditExcelList();
		
		for(int i = 0; i < custBadCreditList.size(); i++) {
			CustCredit custCredit = custBadCreditList.get(i);
			boolean isCommit = false;
			if (i % BATCH_COUNT == 0 || i == custBadCreditList.size() - 1) {
				isCommit = true;
			}
			custCreditService.insertCustBadCreditReport(custCredit, isCommit);
		}
		// 更新信用预警信息（信用预警次数、最后一次信用预警时间）
		this.updateEarlyWarnInfo(custBadCreditList);*/
		
		// 改存储过程实现
		custCreditService.insertCustBadCreditReport();
	}

	/**
	 * 
	 * <p>
	 * 更新信用预警信息（信用预警次数、最后一次信用预警时间）
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param custBadCreditList
	 * @return boolean
	 * 
	 */
	@Override
	public boolean updateEarlyWarnInfo(List<CustCredit> custBadCreditList) {
		for(int i = 0; i < custBadCreditList.size(); i++) {
			CustCredit custCredit = custBadCreditList.get(i);
			boolean isCommit = false;
			if (i % BATCH_COUNT == 0 || i == custBadCreditList.size() - 1) {
				isCommit = true;
			}
			custCreditService.updateEarlyWarnInfo(custCredit.getCustId(), isCommit);
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 生成信用较差客户名单excel
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param config 
	 * @param custBadCreditList
	 * @param file
	 * @return FileInputStream
	 * @throws IOException 
	 * 
	 */
	@Override
	public FileInputStream getExcelFile(CustCreditConfig config, List<CustCredit> custBadCreditList, File file) throws IOException {
		logger.error("-------------------3、信用较差客户excel路径："+file.getPath());
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
		FileInputStream is = new FileInputStream(file);
		if(excelVersion == 2003){
			logger.error("-------------------2003");
			wb = new HSSFWorkbook(is);
		}else if(excelVersion == 2007){
			logger.error("-------------------2007");
			wb = new XSSFWorkbook(is);
		}
		logger.error("-------------------4、信用较差excel开始导入");
		// 一、导出数据
		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		
		/*HSSFSheet sheet = workbook.createSheet("信用较差客户名单");

		// 创建表头
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("经营本部名称");
		row.createCell(1).setCellValue("事业部名称");
		row.createCell(2).setCellValue("零担大区");
		row.createCell(3).setCellValue("所属营业区");
		row.createCell(4).setCellValue("归属部门");
		row.createCell(5).setCellValue("责任部门");
		row.createCell(6).setCellValue("客户名称");
		row.createCell(7).setCellValue("客户编码");
		row.createCell(8).setCellValue("是否月结");
		//row.createCell(9).setCellValue("未还款类型");
		row.createCell(9).setCellValue("月结合同规定还款时间");
		row.createCell(10).setCellValue("临时欠款信用额度");
		row.createCell(11).setCellValue("月结信用额度");
		row.createCell(12).setCellValue("临时欠款信用额度余额");
		row.createCell(13).setCellValue("月结信用额度余额");
		row.createCell(14).setCellValue("临时欠款当期应收金额");
		row.createCell(15).setCellValue("月结当期应收金额");
		row.createCell(16).setCellValue("临时欠款应收总额");
		row.createCell(17).setCellValue("月结应收总额");
		row.createCell(18).setCellValue("部门临欠额度");
		row.createCell(19).setCellValue("临时欠款信用额度使用率");
		row.createCell(20).setCellValue("月结信用额度使用率");
		row.createCell(21).setCellValue("部门应收总额");
		row.createCell(22).setCellValue("未还类型一");
		row.createCell(23).setCellValue("未还类型二");
		row.createCell(24).setCellValue("未还类型三");
		row.createCell(25).setCellValue("未还类型四");
		row.createCell(26).setCellValue("未还类型五");*/
		
		// 创建数据
		int i = 1;
		for(CustCredit custCredit : custBadCreditList) {
			row = sheet.createRow(i++);
			row.createCell(0).setCellValue(custCredit.getManagementDeptName());
			row.createCell(1).setCellValue(custCredit.getBusDeptName());
			row.createCell(2).setCellValue(custCredit.getBigAreaDeptName());
			row.createCell(3).setCellValue(custCredit.getAreaDeptName());
			row.createCell(4).setCellValue(custCredit.getAttributionDeptName());
			row.createCell(5).setCellValue(custCredit.getResponsibilityDeptName());
			row.createCell(6).setCellValue(custCredit.getCustName());
			row.createCell(7).setCellValue(custCredit.getCustNumber());
			row.createCell(8).setCellValue("1".equals(custCredit.getIsMonthlyCust()) ? "月结" : "无" );
			//row.createCell(9).setCellValue(custCredit.getNorepaymentTypeName());
			row.createCell(9).setCellValue(custCredit.getRepaymentDate());
			row.createCell(10).setCellValue(custCredit.getOverdraftCreditAmount());
			row.createCell(11).setCellValue(custCredit.getMonthCreditAmount());
			row.createCell(12).setCellValue(custCredit.getOverdraftCreditBalanceAmount());
			row.createCell(13).setCellValue(custCredit.getMonthCreditBalanceAmount());
			row.createCell(14).setCellValue(custCredit.getOverdrftRecivableAmount());
			row.createCell(15).setCellValue(custCredit.getLttRecivableAmount());
			row.createCell(16).setCellValue(custCredit.getTdreceivableAmount());
			row.createCell(17).setCellValue(custCredit.getMonReceivableAmount());
			row.createCell(18).setCellValue(custCredit.getDeptCredit());
			row.createCell(19).setCellValue(custCredit.getOverdraftCreditUserate());
			row.createCell(20).setCellValue(custCredit.getMonthCreditUserate());
			row.createCell(21).setCellValue(custCredit.getReceivableAmount());
			String norepaymentType = custCredit.getNorepaymentType();
			List<String> list = CustCreditUtil.TokenizerString(norepaymentType, DIM);
			for(int j = 0; j < list.size(); j++) {
				int type = Integer.parseInt((String)list.get(j));
				switch (type) {
					case 1:
						row.createCell(22).setCellValue("√");
						break;
					case 2:
						row.createCell(23).setCellValue("√");
						break;
					case 3:
						row.createCell(24).setCellValue("√");
						break;
					case 4:
						row.createCell(25).setCellValue("√");
						break;
					case 5:
						row.createCell(26).setCellValue("√");
						break;
					default:
						break;
				}
			}
		}
		logger.error("-------------------5、信用较差excel配置替换");
		// 二、替换信用较差客户配置信息
		sheet = wb.getSheetAt(1);
		row = sheet.getRow(2);
		Cell cell = row.getCell(2);
		cell.setCellValue(cell.getStringCellValue().replace("#{maxOverdraftDay}", config.getMaxOverdraftDay()+""));
		
		row = sheet.getRow(3);
		cell = row.getCell(2);
		cell.setCellValue(cell.getStringCellValue().replace("#{monthEndOvertake}", config.getMonthEndOvertake()+""));
		
		row = sheet.getRow(4);
		cell = row.getCell(2);
		cell.setCellValue(cell.getStringCellValue().replace("#{overdueMonth}", config.getOverdueMonth()+""));
		
		row = sheet.getRow(5);
		cell = row.getCell(2);
		cell.setCellValue(cell.getStringCellValue().replace("#{earliestDay}", config.getEarliestDay()+""));
		
		row = sheet.getRow(6);
		cell = row.getCell(2);
		cell.setCellValue(cell.getStringCellValue().replace("#{beforePaymentdateDay}", config.getBeforePaymentdateDay()+""));
		
		FileOutputStream fileOut = null;
		FileInputStream fileInput = null;
		try {
			// 创建excel文件
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			fileInput = new FileInputStream(file);
			logger.error("-------------------6、信用较差excel整理成功");
		} catch (IOException e) {
			Log.info("文件操作异常", e);
			throw e;
		}
		return fileInput;
	}

	/**
	 * 
	 * <p>
	 * 信用较差客户邮件发送
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-7
	 * @param
	 * @return boolean 
	 * @throws ParseException, IOException, MessagingException
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean sendCustCreditMail() throws ParseException, IOException, MessagingException {
		try {
			/**
			 * <p>
			 * Description: 邮件发送前，先统计信用较差客户<br/>
			 * 
			 * 1、临时欠款超过 N  天未还款临时欠款客户
			 * 2、按照月结合同规定时间后 N   天未还款的月结客户
			 * 3、逾期  N  天未还款的客户
			 * 4、最长一笔临欠达到 N  天（含）的临时欠款客户
			 * 5、距离规定结款日期前   N   天未还款的月结客户
			 * 
			 */
			this.insertCustBadCreditReport();
		} catch (ParseException e) {
			throw e;
		}
		
		String busDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("MANAGEMENT_GROUP_MARKETING_DEPARTMENT");
		String bigAreaDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("DISTRICT_STATISTICS_GROUP");
		String branchDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("DIVISION_STATISTICS_GROUP");
		String officeDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("DEPARTMENT_OFFICE");
		String bigAreaOfficeDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("BIGAREA_DEPARTMENT_OFFICE");
		String expressBranchDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BRANCH_DEPTNAME");
		String expressBigAreaDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BIG_AREA_DEPTNAME");
		String expressBigareaDepartmentOffice = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BIGAREA_DEPARTMENT_OFFICE");
		String expressDistrictStatisticsGroup = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_DISTRICT_STATISTICS_GROUP");
		String expressTheMinistry = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_THE_MINISTRY");
		
		String beginDate = "";
		String endDate = "";
		// 生成信用较差客户名单excel
		Date excelDay = null;
		int day = CustCreditUtil.getDay();
		if(day >= 16) {
			excelDay = CustCreditUtil.getHalfMonthDate(15);
			// 查询本月1号至15号的信用较差客户
			beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(1));
			endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(15));
		}else{
			excelDay = CustCreditUtil.getEndDate();
			// 查询上个月16号至上个月最后一天的信用较差客户
			beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getBeginDate(1, 16));
			endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getEndDate());
		}
		
		CustCredit custCredit = new CustCredit();
		custCredit.setExcelDay(excelDay);
		custCredit.setBeginDate(beginDate);
		custCredit.setEndDate(endDate);
		
		
		try {
			// 1、全国数据发送至--->应收账款管理小组、CRM管理组负责人、客户管理小组负责人、CRM推进小组负责人
			Department department = departmentService.getDeptByCode(MANAGEMENT_OF_ACCOUNTS_RECEIVABLE_DEPTCODE);
			sendMail(custCredit, department.getId(), WHOLECOUNTRY, MANAGEMENT_OF_ACCOUNTS_RECEIVABLE_DEPTCODE, "", null);
			// CRM管理组负责人
			department = departmentService.getDeptByCode(CRM_DEPTCODE);
			sendMail(custCredit, department.getId(), WHOLECOUNTRY, CRM_DEPTCODE, department.getPrincipal(), null);
			// 客户管理小组负责人
			department = departmentService.getDeptByCode(CUSTOMER_MANAGEMENT_TEAM);
			sendMail(custCredit, department.getId(), WHOLECOUNTRY, CUSTOMER_MANAGEMENT_TEAM, department.getPrincipal(), null);
			// CRM推进小组负责人
			department = departmentService.getDeptByCode(CRM_PROMOTE_TEAM);
			sendMail(custCredit, department.getId(), WHOLECOUNTRY, CRM_PROMOTE_TEAM, department.getPrincipal(), null);
			
			// 2、XX经营本部数据对应推送XX经营本部营销推进组，XX经营本部营运标准推进组，XX经营本部营运办公室主任
			List<Map<String, String>> managementDeptList = custCreditService.getManagementDeptList();
			for(int i = 0; i < managementDeptList.size(); i++) {
				Map<String, String> map = managementDeptList.get(i);
				Object obj = map.get("FMANAGEMENTDEPTID");
				String managementDeptId = obj.toString();
				String deptName = map.get("FDEPTNAME");
				String deptCode = map.get("FDEPTCODE");
				
				custCredit.setManagementDeptId(managementDeptId);
				// 华南经营本部
				if(SOUTHCHINAOPERATE.equalsIgnoreCase(deptCode)) {
					department = departmentService.getDeptByCode(SOUTH_MARKETING_GROUP);
					sendMail(custCredit, department.getId(), deptName, SOUTH_MARKETING_GROUP, "", null);
					department = departmentService.getDeptByCode(SOUTH_OPERATIONALSTANDARDSWORKING);
					sendMail(custCredit, department.getId(), deptName, SOUTH_OPERATIONALSTANDARDSWORKING, "", null);
					
					department = departmentService.getDeptByCode(SOUTH_BUSINESS_OFFICE);
					sendMail(custCredit, department.getId(), deptName, department.getDeptCode(), department.getPrincipal(), null);
				}
				// 华中经营本部
				else if(CENTRALCHINAOPERATE.equalsIgnoreCase(deptCode)) {
					department = departmentService.getDeptByCode(CENTEAL_MARKETING_GROUP);
					sendMail(custCredit, department.getId(), deptName, CENTEAL_MARKETING_GROUP, "", null);
					department = departmentService.getDeptByCode(CENTEAL_OPERATIONALSTANDARDSWORKING);
					sendMail(custCredit, department.getId(), deptName, CENTEAL_OPERATIONALSTANDARDSWORKING, "", null);
					
					department = departmentService.getDeptByCode(CENTEAL_BUSINESS_OFFICE);
					sendMail(custCredit, department.getId(), deptName, department.getDeptCode(), department.getPrincipal(), null);
				}
				//　华北经营本部
				else if(NORTHCHINAOPERATE.equalsIgnoreCase(deptCode)) {
					department = departmentService.getDeptByCode(NORTH_MARKETING_GROUP);
					sendMail(custCredit, department.getId(), deptName, NORTH_MARKETING_GROUP, "", null);
					department = departmentService.getDeptByCode(NORTH_OPERATIONALSTANDARDSWORKING);
					sendMail(custCredit, department.getId(), deptName, NORTH_OPERATIONALSTANDARDSWORKING, "", null);
					
					department = departmentService.getDeptByCode(NORTH_BUSINESS_OFFICE);
					sendMail(custCredit, department.getId(), deptName, department.getDeptCode(), department.getPrincipal(), null);
				}
			}
			
			// 3、XX事业部数据对应推送XX事业部营销管理组,XX事业部总裁,XX事业部办公室主任
			List<Map<String, String>> busDeptList = custCreditService.getBusDeptList();
			for(int i = 0; i < busDeptList.size(); i++) {
				Map<String, String> map = busDeptList.get(i);
				Object obj = map.get("FBUSDEPTID");
				String busDeptId = obj.toString();
				String busdeptname = map.get("FBUSDEPTNAME");
				
				custCredit.setManagementDeptId("");
				custCredit.setBusDeptId(busDeptId);
				
				// 通过事业部id查找对应的事业部营销管理组
				List<Department> deptList = departmentService.queryAllChildDeptByDeptId(busDeptId);
				if(deptList.size() > 0) {
					// XX事业部总裁
					Department dept = deptList.get(0);
					sendMail(custCredit, dept.getId(), busdeptname, dept.getDeptCode(), dept.getPrincipal(), null);
				}
				for(Department dept : deptList) {
					if(dept != null && dept.getDeptName().endsWith(busDeptName)) {
						sendMail(custCredit, dept.getId(), busdeptname, dept.getDeptCode(), "", null);
					}
					// XX事业部办公室主任
					if(dept != null && dept.getDeptName().endsWith(officeDeptName)) {
						sendMail(custCredit, dept.getId(), busdeptname, dept.getDeptCode(), dept.getPrincipal(), null);
					}
				}
			}
			
			// 4、XX大区(零担、快递)数据对应推送XX大区统计组（零担、快递）,XX大区大区总经理,XX大区办公室主任
			List<Map<String, String>> bigAreaDeptList = custCreditService.getBigAreaDeptList();
			for(int i = 0; i < bigAreaDeptList.size(); i++) {
				Map<String, String> map = bigAreaDeptList.get(i);
				Object obj = map.get("FBIGAREADEPTID");
				String bigAreaDeptId = obj.toString();
				String bigAreaName = map.get("FBIGAREADEPTNAME");
				
				custCredit.setManagementDeptId("");
				custCredit.setBusDeptId("");
				custCredit.setBigAreaDeptId(bigAreaDeptId);
				
				// 通过大区id查找对应的大区统计组
				List<Department> deptList = departmentService.queryAllChildDeptByDeptId(bigAreaDeptId);
				if(deptList.size() > 0) {
					// XX大区总经理
					Department dept = deptList.get(0);
					sendMail(custCredit, dept.getId(), bigAreaName, dept.getDeptCode(), dept.getPrincipal(), null);
				}
				for(Department dept : deptList) {
					// XX大区统计组
					if(dept != null && dept.getDeptName().endsWith(bigAreaDeptName)) {
						sendMail(custCredit, dept.getId(), bigAreaName, dept.getDeptCode(), "", null);
					}
					
					// XX大区办公室主任
					if(dept != null && dept.getDeptName().endsWith(bigAreaOfficeDeptName)) {
						sendMail(custCredit, dept.getId(), bigAreaName, dept.getDeptCode(), dept.getPrincipal(), null);
					}
				}
			}
			
			// 5、XX营业区数据对应推送XX营业区区域经理
			List<Map<String, String>> areaDeptList = custCreditService.getAreaDeptList();
			for(int i = 0; i < areaDeptList.size(); i++) {
				Map<String, String> map = areaDeptList.get(i);
				Object obj = map.get("FAREADEPTID");
				String areaDeptId = obj.toString();
				String areaDeptName = map.get("FAREADEPTNAME");
				
				custCredit.setManagementDeptId("");
				custCredit.setBusDeptId("");
				custCredit.setBigAreaDeptId("");
				custCredit.setAreaDeptId(areaDeptId);
				
				Department dept = departmentService.getDepartmentById(areaDeptId);
				sendMail(custCredit, dept.getId(), areaDeptName, dept.getDeptCode(), dept.getPrincipal(), null);
			}
			
			// 6、XX营业部数据推送对应XX营业部负责人
			Map<String, String> attributionDeptIdMap = new HashMap<String, String>();
			List<Map<String, String>> responsibilityDeptList = custCreditService.getResponsibilityDeptList();
			for(int i = 0; i < responsibilityDeptList.size(); i++) {
				Map<String, String> map = responsibilityDeptList.get(i);
				Object obj = map.get("FRESPONSIBILITYDEPTID");   // 责任部门
				String responsibilityDeptId = obj.toString();
				String responsibilityname = map.get("FRESPONSIBILITYNAME");
				
				obj = map.get("FATTRIBUTIONDEPTID");         // 归属部门
				String attributionDeptId = obj.toString();
				String attributiondeptname = map.get("FATTRIBUTIONDEPTNAME");
				
				custCredit.setManagementDeptId("");
				custCredit.setBusDeptId("");
				custCredit.setBigAreaDeptId("");
				custCredit.setAreaDeptId("");
				custCredit.setResponsibilityDeptId(responsibilityDeptId);
				custCredit.setAttributionDeptId("");
				
				
				Department dept = departmentService.getDepartmentById(responsibilityDeptId);
				sendMail(custCredit, dept.getId(), responsibilityname, dept.getDeptCode(), dept.getPrincipal(), null);
				
				if(!StringUtils.isEmpty(attributionDeptId)) {
					if(!attributionDeptIdMap.containsKey(attributionDeptId)) {
						custCredit.setResponsibilityDeptId("");
						custCredit.setAttributionDeptId(attributionDeptId);
						
						dept = departmentService.getDepartmentById(attributionDeptId);
						sendMail(custCredit, dept.getId(), attributiondeptname, dept.getDeptCode(), dept.getPrincipal(), null);
						
						attributionDeptIdMap.put(attributionDeptId, attributionDeptId);
					}
				}
			}
			
			
			// XX快递分部数据对应推送XX快递分部高级经理、XX分部统计组
			// 获取所有的快递分部
			List<Department> deptList = departmentService.getDepartmentListByLikeDeptName(expressBranchDeptName);
			for(Department dept : deptList) {
				String deptName = dept.getDeptName();
				Map<String, String> map = new HashMap<String, String>();
				map.put("deptId", dept.getId());
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				List<CustCredit> custBadCreditList = 
						custCreditService.getCustCreditListByBranch(map);
				// XX快递分部高级经理
				sendMail(custCredit, dept.getId(), deptName, dept.getDeptCode(), dept.getPrincipal(), custBadCreditList);
				// 通过快递分部查找对应的XX分部统计组
				List<Department> list = departmentService.queryAllChildDeptByDeptId(dept.getId());
				for(Department depart : list) {
					// 分部统计组
					if(depart != null && depart.getDeptName().endsWith(branchDeptName)) {
						sendMail(custCredit, dept.getId(), deptName, dept.getDeptCode(), "", custBadCreditList);
					}
				}
			}
			
			// XX快递大区数据对应推送XX快递大区总经理、XX快递大区办公室主任、XX快递大区统计组
			// 获取所有的快递大区
			List<Department> expressBigAreaList = departmentService.getDepartmentListByLikeDeptName(expressBigAreaDeptName);
			for(Department dept : expressBigAreaList) {
				String deptName = dept.getDeptName();
				Map<String, String> map = new HashMap<String, String>();
				map.put("deptId", dept.getId());
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				List<CustCredit> custBadCreditList = 
						custCreditService.getCustCreditListByBranch(map);
				// XX快递大区总经理
				sendMail(custCredit, dept.getId(), deptName, dept.getDeptCode(), dept.getPrincipal(), custBadCreditList);
				// 通过快递大区查找对应的XX快递大区办公室
				List<Department> list = departmentService.queryAllChildDeptByDeptId(dept.getId());
				for(Department depart : list) {
					// 快递大区办公室
					if(depart != null && depart.getDeptName().endsWith(expressBigareaDepartmentOffice)) {
						sendMail(custCredit, dept.getId(), deptName, dept.getDeptCode(), depart.getPrincipal(), custBadCreditList);
					}
					// 快递大区统计组
					if(depart != null && depart.getDeptName().endsWith(expressDistrictStatisticsGroup)) {
						sendMail(custCredit, dept.getId(), deptName, dept.getDeptCode(), "", custBadCreditList);
					}
				}
			}
			
			// XX快递点部数据推送对应XX快递点部经理
			// 获取所有的快递点部
			List<Department> expressTheMinistryList = departmentService.getDepartmentListByLikeDeptName(expressTheMinistry);
			for(Department dept : expressTheMinistryList) {
				String deptName = dept.getDeptName();
				Map<String, String> map = new HashMap<String, String>();
				map.put("deptId", dept.getId());
				map.put("beginDate", beginDate);
				map.put("endDate", endDate);
				List<CustCredit> custBadCreditList = 
						custCreditService.getCustCreditListByTheMinistry(map);
				// XX快递点部经理
				sendMail(custCredit, dept.getId(), deptName, dept.getDeptCode(), dept.getPrincipal(), custBadCreditList);
			}
		} catch (IOException e) {
			Log.info("文件操作异常", e);
			throw e;
		}
		return true;
	}
	
	/**
	 * <p>
	 * 邮件发送
	 * 
	 * excel文件名：信用较差客户明细表-yyyy-MM-dd-部门Id.xls
	 * zip文件名       ：信用较差客户明细表-yyyy-MM-dd.zip  
	 * </p>
	 * @param custCredit
	 * @param deptId
	 * @param deptName
	 * @param deptCode
	 * @param principal
	 * @throws IOException
	 * @throws IOException, MailException, MessagingException
	 */
	public void sendMail(CustCredit custCredit, 
			String deptId, 
			String deptName, 
			String deptCode, 
			String principal, 
			List<CustCredit> custBadCreditList) 
			throws IOException, MailException, MessagingException {
		if(custBadCreditList == null) {
			custBadCreditList = custCreditService.getCustCreditListByCondition(custCredit);
		}
		if(custBadCreditList == null || custBadCreditList.size() < 1) {
			return ;
		}
		CustCreditConfig config = this.getCustCreditConfig();
		logger.error("-------------------1、信用较差客户excel数据条数："+custBadCreditList.size());
		FileInfo fileInfo = createPromptFile(config, custBadCreditList, deptId, "信用较差客户明细表-"+CustCreditUtil.dateFormat(DATE_FORMAT, custCredit.getExcelDay())+"-"+deptId+".xls", "customer");
		// 压缩excel
		// ZipUtil.zip("", "", "D:\\工单统计员角色删除历史数据备份.xlsx");	
		
		logger.error("-------------------8、信用较差客户excel数据写入完毕");
		
		StringBuffer sb = new StringBuffer();
		sb.append(fileInfo.getSavePath().substring(0, fileInfo.getSavePath().lastIndexOf("/")+1));
		sb.append(fileInfo.getFileName().substring(0, fileInfo.getFileName().lastIndexOf("-")));
		sb.append(".zip");
		
		logger.error("-------------------9、信用较差客户excel路径："+fileInfo.getSavePath());
		String zipPathName = ZipUtil.zip(sb.toString(), "", fileInfo.getSavePath());
		logger.error("-------------------10、信用较差客户excel压缩完毕");
		File file = new File(zipPathName);
		logger.error("-------------------11、信用较差客户excel压缩zip文件地址："+file.getPath());
		FileInputStream fis = new FileInputStream(file);
		int fileSize = fis.available();
		
		// 邮件发送
		MailInfo info = new MailInfo();
		info.setSubject("【信用较差客户明细表-"+CustCreditUtil.dateFormat("yyyyMMdd", custCredit.getExcelDay())+"】");
		Map<String, String> model = new HashMap<String, String>();
		model.put("title", 
				CustCreditUtil.dateFormat("yyyy", custCredit.getExcelDay()) + "-" + 
				CustCreditUtil.dateFormat("MM", custCredit.getExcelDay())   + "-" + 
				CustCreditUtil.dateFormat("dd", custCredit.getExcelDay())   + ".zip");
		model.put("year", CustCreditUtil.dateFormat("yyyy", custCredit.getExcelDay()));
		model.put("month", CustCreditUtil.dateFormat("MM", custCredit.getExcelDay()));
		model.put("day", CustCreditUtil.dateFormat("dd", custCredit.getExcelDay()));
		model.put("deptName", deptName);
		model.put("maxOverdraftDay", config.getMaxOverdraftDay()+"");
		model.put("monthEndOvertake", config.getMonthEndOvertake()+"");
		model.put("overdueMonth", config.getOverdueMonth()+"");
		model.put("earliestDay", config.getEarliestDay()+"");
		model.put("beforePaymentdateDay", config.getBeforePaymentdateDay()+"");
		
		if(fileSize <= EXCEL_SIZE) {
			model.put("sendType", "attachments");
			
			// 附件发送
			File[] files = { file };
			info.setAttachments(files);
		} else {
			// 不发送附件
			model.put("sendType", "NoAttachments");
		}
		String content = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, CUSTCREDIT_TEMPLATE_NAME, CHARSET, model);
		logger.error("-------------------12、邮件内容：" + content);
		info.setContent(content);
		//sysMailSendManager.sendEmail(info, groupCode);
		info.setFrom(mailSenderService.getUserName());
		List<String> toSend = new ArrayList<String>();
		if(StringUtils.isEmpty(principal)) {  
			// 收件人为部门编号是deptCode的所有人
			List<Employee> list = employeeService.getAllEmployeeByDeptCode(deptCode);
			for(Employee emp : list) {
				toSend.add(emp.getOfferEmail());
			}
			if(list.size() < 1) {
				return ;
			}
			info.setTo(toSend.toArray(new String[list.size()]));
		}else{
			// 收件人为单人
			Employee emp = employeeService.getEmpByCode(principal);
			if(StringUtils.isEmpty(emp.getOfferEmail())) {
				return ;
			}
			toSend.add(emp.getOfferEmail());
			info.setTo(toSend.toArray(new String[1]));
		}
		try {
			mailSenderService.sendExtranetMail(info);
			//保存发送记录
			sysMailSendService.saveSendLog(toSend);
		} catch (MessagingException e) {
			logger.info("邮件发送失败", e);
			throw e;
		}
		
		// 删除 zip 文件
		//file.delete();
		// 删除 excel 文件
		//new File(fileInfo.getSavePath()).delete();
	}

	/**
	 * 
	 * <p>
	 * 获取信用较差客户待办数据
	 * 
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
	 * 7.信用额度使用率达到  N %的月结客户
	 * 8.营业部临时欠款总额使用率达到  N %
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-24
	 * @param 
	 * @return List<CustCredit>
	 * @throws ParseException
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditToDo() throws ParseException {
		CustCreditConfig config = this.getCustCreditConfig();
		
		// 最长一笔临欠达到 N 天（含）的时欠款客户
		int earliestDay = config.getEarliestDay();
		// 距离规定结款日期前   N   天未还款的月结客户
		int beforePaymentdateDay = config.getBeforePaymentdateDay();
		// 最长还款周期（月结天数）到期前  N  天未还款的月结客户
		int beforeMonthPaymentDay = config.getBeforeMonthPaymentDay();
		// 信用额度使用率达到  N %的月结客户
		double usedcreditrate = config.getUsedcreditrate();
		// 营业部临时欠款总额使用率达到  N %
		double deptUsedrate = config.getDeptUsedrate();
		
		Date currDate = CustCreditUtil.getNowDate();
		List<CustCredit> custBadCreditList = new ArrayList<CustCredit>();
		List<CustCredit> creditList = custCreditService.getCustBadCreditExcelList();
		int day = 0;
		for (CustCredit custCredit : creditList) {
			if(custCredit != null && !StringUtils.isEmpty(custCredit.getResponsibilityDeptId())) {
				// 最早一笔未还临欠开单时间
				Date temporarydebitDate = custCredit.getTemporarydebitDate();
				// 最早一笔未还月结开单时间
				Date nomonthlyDate = custCredit.getNomonthlyDate();
				// 合同结款日期 
				int resultDate = custCredit.getRepaymentDate();
				Date date = CustCreditUtil.getAddMonthDate(nomonthlyDate, 1, resultDate);
				
				// 4.最长一笔临欠达到 N 天（含）的时欠款客户
				day = CustCreditUtil.daysBetween(temporarydebitDate, currDate);
				if(day >= earliestDay) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.EARLIESTDAY_CONTENT, 
							new Object[] {earliestDay});
					custCredit.setNorepaymentType("4");
					custCredit.setNorepaymentTypeName(norepaymentType);
					custBadCreditList.add(custCredit);
				}
				
				// 5.距离规定结款日期前   N   天未还款的月结客户
				day = CustCreditUtil.daysBetween(date, currDate);
				if(day >= beforePaymentdateDay*-1) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.BEFOREPAYMENTDATEDAY_CONTENT, 
							new Object[] {beforePaymentdateDay});
					custCredit.setNorepaymentType("5");
					custCredit.setNorepaymentTypeName(norepaymentType);
					custBadCreditList.add(custCredit);
				}
				
				// 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
				// 月结天数
				int debtdays = custCredit.getDebtdays();
				day = CustCreditUtil.daysBetween(nomonthlyDate, currDate);
				if(day >= (debtdays-beforeMonthPaymentDay)) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.BEFOREMONTHPAYMENTDAY_CONTENT, 
							new Object[] {beforePaymentdateDay});
					custCredit.setNorepaymentType("6");
					custCredit.setNorepaymentTypeName(norepaymentType);
					custBadCreditList.add(custCredit);
				}
				
				// 7.信用额度使用率达到  N %的月结客户
				// 月结应收总额
				double monReceivableAmount = custCredit.getMonReceivableAmount();
				// 月结信用额度
				double monthCreditAmount = custCredit.getMonthCreditAmount();
				// 月结信用额度使用率
				double monthCreditUserate = 0;
				if(monthCreditAmount != 0) {
					monthCreditUserate = CustCreditUtil.getMultiply(CustCreditUtil.getDivide(monReceivableAmount, monthCreditAmount, 2), 100);
				}
				if(monthCreditUserate >= usedcreditrate) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.USEDCREDITRATE_CONTENT, 
							new Object[] {beforePaymentdateDay});
					custCredit.setNorepaymentType("7");
					custCredit.setNorepaymentTypeName(norepaymentType);
					custBadCreditList.add(custCredit);
				}
				
				// 8.营业部临时欠款总额使用率达到  N %
				// 部门当期临欠应收总额
				double deptCurdebtReceivableAmount = custCredit.getDeptCurdebtReceivableAmount();
				// 部门临欠额度
				double deptCredit = custCredit.getDeptCredit();
				double deptAmount = 0;
				if(deptCredit != 0) {
					deptAmount = CustCreditUtil.getMultiply(CustCreditUtil.getDivide(deptCurdebtReceivableAmount, deptCredit, 2), 100);
				}
				if(deptAmount >= deptUsedrate) {
					String norepaymentType = MessageFormat.format(CustCreditUtil.DEPTUSEDRATE_CONTENT, 
							new Object[] {beforePaymentdateDay});
					custCredit.setNorepaymentType("8");
					custCredit.setNorepaymentTypeName(norepaymentType);
					custBadCreditList.add(custCredit);
				}
			}
		}
		return custBadCreditList;
	}
	
	/**
	 * 
	 * <p>
	 * 生成信用较差客户待办信息之前先删除信用较差客户待办
	 * 
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
	 * 7.信用额度使用率达到  N %的月结客户
	 * 8.营业部临时欠款总额使用率达到  N %
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-24
	 * @param 
	 * @return boolean
	 * 
	 */
	@Override
	public boolean deleteCustCreditTodo() {
		messageManager.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTCREDIT_MESSAGE);
		messageManager.deleteMessageByType(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_DEPTCREDIT_MESSAGE);
		return true;
	}

	/**
	 * 
	 * <p>
	 * 生成信用较差客户待办信息
	 * 
	 * 4.最长一笔临欠达到 N  天（含）的时欠款客户
	 * 5.距离规定结款日期前   N   天未还款的月结客户
	 * 6.最长还款周期（月结天数）到期前  N  天未还款的月结客户
	 * 7.信用额度使用率达到  N %的月结客户
	 * 8.营业部临时欠款总额使用率达到  N %
	 * 
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-24
	 * @param 
	 * @return void
	 * @throws ParseException
	 * 
	 */
	@Override
	public void generationCustCreditToDo() throws ParseException {
		/*CustCreditConfig config = this.getCustCreditConfig();
		
		// 最长一笔临欠达到 N 天（含）的时欠款客户
		int earliestDay = config.getEarliestDay();
		// 信用额度使用率达到  N %的月结客户
		double usedcreditrate = config.getUsedcreditrate();
		// 营业部临时欠款总额使用率达到  N %
		double deptUsedrate = config.getDeptUsedrate();
		
		List<CustCredit> custCreditList = this.getCustCreditToDo();
		List<Message> messageList = new ArrayList<Message>();
		Message message = null;
		for(CustCredit custCredit : custCreditList) {
			String taskType = "";
			String content = "";
			String custNumber = custCredit.getCustNumber();
			String custName = custCredit.getCustName();
			String norepaymentType = custCredit.getNorepaymentType();
			String responsibilityDeptId = custCredit.getResponsibilityDeptId();
			List<String> deptIds = new ArrayList<String>();
			deptIds.add(responsibilityDeptId);
			List<User> userList = userService.queryUsersByDeptIds(deptIds);
			for(User user : userList) {
				switch (Integer.parseInt(norepaymentType)) {
					case 4:
						taskType = com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTCREDIT_MESSAGE;
						content = MessageFormat.format(CustCreditUtil.EARLIESTDAY_DOTO_CONTENT, 
								new Object[] {custNumber, custName, earliestDay});
						break;
					case 5:
						taskType = com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTCREDIT_MESSAGE;
						content = MessageFormat.format(CustCreditUtil.BEFOREPAYMENTDATEDAY_DOTO_CONTENT, 
								new Object[] {custNumber, custName});
						break;
					case 6:
						taskType = com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTCREDIT_MESSAGE;
						content = MessageFormat.format(CustCreditUtil.BEFOREMONTHPAYMENTDAY_DOTO_CONTENT, 
								new Object[] {custNumber, custName});
						break;
					case 7:
						taskType = com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTCREDIT_MESSAGE;
						content = MessageFormat.format(CustCreditUtil.USEDCREDITRATE_DOTO_CONTENT, 
								new Object[] {custNumber, custName, usedcreditrate});
						break;
					case 8:
						taskType = com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_DEPTCREDIT_MESSAGE;
						content = MessageFormat.format(CustCreditUtil.DEPTUSEDRATE_DOTO_CONTENT, 
								new Object[] {deptUsedrate});
						break;
					default:
						break;
				}
				message = new Message();
				message.setTasktype(taskType);
				message.setTaskcount(1);
				message.setIshaveinfo(content);
				message.setUserid(Integer.parseInt(user.getId()));
				message.setCreateDate(new Date());
				messageList.add(message);
			}
		}
		//先删再增
		this.deleteCustCreditTodo();
		messageManager.addMessageList(messageList);*/
		// 生成信用较差客户待办信息(存储过程实现)
		custCreditService.generationCustCreditToDo();
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询权限校验<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @return int
	 * 
	 */
	@Override
	public int custCreditAuthorization(String deptId) {
		if(!StringUtils.isEmpty(deptId)) {
			User user = (User) UserContext.getCurrentUser();
			//当前登录人部门信息
			String currDeptId = user.getEmpCode().getDeptId().getId();
			String currDeptCode = user.getEmpCode().getDeptId().getDeptCode();
			String currDeptName = user.getEmpCode().getDeptId().getDeptName();
			//当前登录人不为"应收账款管理小组"、"CRM管理组",加权限限制
			if(!StringUtils.isEmpty(currDeptId) && !StringUtils.isEmpty(currDeptCode) && 
					!currDeptCode.equals(MANAGEMENT_OF_ACCOUNTS_RECEIVABLE_DEPTCODE) && 
					!currDeptCode.equals(CRM_DEPTCODE) && 
					!currDeptCode.equals(CUSTOMER_MANAGEMENT_TEAM) && 
					!currDeptCode.equals(CRM_PROMOTE_TEAM)) {
				boolean isView = false;
				boolean isBreak = false;
				// 1、递归当前登录人所在部门，看下级部门中是否存在查询的部门（存在则有权查询）
				List<Department> deptList = departmentService.queryAllChildDeptByDeptId(currDeptId);
				for(Department dept : deptList) {
					if(dept.getId().equals(deptId)) {
						isView = true;
						break;
					}
				}
				List<Department> parentDeptList = departmentService.getAllParentDeptByDeptId(currDeptId);
				if(!isView) {
					// 2、XX经营本部营运办公室主任、XX经营本部营销推进组，XX经营本部营运标准推进组   只能查询XX经营本部下的数据
					if(currDeptName.endsWith("营运办公室") || 
							currDeptName.endsWith("营运标准推进组") || 
							currDeptName.endsWith("营销推进组")) {
						for(Department depart : parentDeptList) {
							if(isBreak) {
								break;
							}
							if(depart != null && depart.getDeptLevel() == 3 && depart.getDeptName().endsWith("经营本部")) {
								deptList = departmentService.queryAllChildDeptByDeptId(depart.getId());
								for(Department dept : deptList) {
									if(dept.getId().equals(deptId)) {
										isView = true;
										isBreak = true;
										break;
									}
								}
							}
						}
					}
				}
				if(!isView) {
					// 3、XX事业部数据对应权限为XX事业部总裁、XX事业部办公室主任、XX事业部营销管理组
					isBreak = false;
					if(currDeptName.endsWith("事业部办公室") || 
							currDeptName.endsWith("事业部营销管理组") || 
							currDeptName.endsWith("事业部")) {
						for(Department depart : parentDeptList) {
							if(isBreak) {
								break;
							}
							if(depart != null && depart.getDeptLevel() == 4 && depart.getDeptName().endsWith("事业部")) {
								deptList = departmentService.queryAllChildDeptByDeptId(depart.getId());
								for(Department dept : deptList) {
									if(dept.getId().equals(deptId)) {
										isView = true;
										isBreak = true;
										break;
									}
								}
							}
						}
					}
				}
				if(!isView) {
					// 4、XX大区(零担、快递)数据对应权限为XX大区总（零担、快递）、XX大区办公室主任、XX大区统计组（零担、快递）
					isBreak = false;
					if(currDeptName.endsWith("大区") || 
							currDeptName.endsWith("大区办公室") || 
							currDeptName.endsWith("大区统计组")) {
						for(Department depart : parentDeptList) {
							if(isBreak) {
								break;
							}
							if(depart != null && depart.getDeptLevel() == 5 && depart.getDeptName().endsWith("大区")) {
								deptList = departmentService.queryAllChildDeptByDeptId(depart.getId());
								for(Department dept : deptList) {
									if(dept.getId().equals(deptId)) {
										isView = true;
										isBreak = true;
										break;
									}
								}
							}
						}
					}
				}
				if(!isView) {
					// 5、XX营业区数据对应权限为XX营业区区域经理
					isBreak = false;
					if(currDeptName.endsWith("营业区")) {
						deptList = departmentService.queryAllChildDeptByDeptId(currDeptId);
						for(Department dept : deptList) {
							if(dept.getId().equals(deptId)) {
								isView = true;
								break;
							}
						}
					}
				}
				if(!isView) {
					// 6、XX快递点部数据对应权限为XX快递点部经理
					if(currDeptName.endsWith("点部")) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("currDeptCode", currDeptCode);
						map.put("deptId", deptId);
						int count = custCreditService.checkIsViewByexpressMinistry(map);
						if(count > 0) {
							isView = true;
						}
					}
				}
				if(!isView) {
					// 7、XX快递分部数据对应权限为XX分部高级经理、XX分部统计组
					if(currDeptName.endsWith("快递分部") || 
							currDeptName.endsWith("分部统计组")) {
						for(Department depart : parentDeptList) {
							if(depart != null && depart.getDeptName().endsWith("快递分部")) {
								Map<String, String> map = new HashMap<String, String>();
								map.put("currDeptCode", currDeptCode);
								map.put("deptId", deptId);
								int count = custCreditService.checkIsViewByexpressBranch(map);
								if(count > 0) {
									isView = true;
									break;
								}
							}
						}
					}
				}
				if(currDeptId.equals(deptId)) {
					isView = true;
				}
				
				if(!isView) {
					CustomerException re = new CustomerException(
							CustomerExceptionType.CustCreditWithoutPermission);
					
					throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
							new Object[] {}) {

								/**
								 * 
								 */
								private static final long serialVersionUID = -4999283627991723505L;
					};
				}
			}
		}
		return 1;
	}

	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @param start
	 * @param limit
	 * @return List<CustCredit>
	 * @throws ParseException
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CustCredit> getCustCreditListByConditions(String deptId,
			String date, int start,int limit) throws ParseException {
		this.custCreditAuthorization(deptId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptId", deptId);
		String beginDate = "";
		String endDate = "";
		if(date != null && date.length() > 0) {
			int day = CustCreditUtil.getDayForStr(date, DATE_FORMAT);
			if(day >= 16) {
				// 查询本月1号至15号的信用较差客户
				beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(date, DATE_FORMAT, 1));
				endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(date, DATE_FORMAT, 15));
			}else{
				// 查询上个月16号至上个月最后一天的信用较差客户
				beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, 
						CustCreditUtil.getBeginDate(date, DATE_FORMAT, 1, 16));
				endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getEndDate(date, DATE_FORMAT));
			}
		}
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		Department dept = departmentService.getDepartmentById(deptId);
		//快递分部
		String expressBranchDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BRANCH_DEPTNAME");
		//快递大区
		String expressBigAreaDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BIG_AREA_DEPTNAME");
		// 1、查询部门为 “快递分部”或者“快递大区”
		if(dept != null && (dept.getDeptName().endsWith(expressBranchDeptName) || dept.getDeptName().endsWith(expressBigAreaDeptName))) {
			return custCreditService.getCustCreditListByOtherConditions(map, start, limit);
		}
		// 2、查询部门为“点部”
		if(dept != null && dept.getDeptName().endsWith(MINISTRY)) {
			return custCreditService.getCustCreditListByMinistryConditions(map, start, limit);
		}
		return custCreditService.getCustCreditListByConditions(map, start, limit);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @return int
	 * @throws ParseException
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int getCustCreditListByConditions(String deptId,
			String date) throws ParseException {
		this.custCreditAuthorization(deptId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptId", deptId);
		String beginDate = "";
		String endDate = "";
		if(date != null && date.length() > 0) {
			int day = CustCreditUtil.getDayForStr(date, DATE_FORMAT);
			if(day >= 16) {
				// 查询本月1号至15号的信用较差客户
				beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(date, DATE_FORMAT, 1));
				endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(date, DATE_FORMAT, 15));
			}else{
				// 查询上个月16号至上个月最后一天的信用较差客户
				beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, 
						CustCreditUtil.getBeginDate(date, DATE_FORMAT, 1, 16));
				endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getEndDate(date, DATE_FORMAT));
			}
		}
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		Department dept = departmentService.getDepartmentById(deptId);
		//快递分部
		String expressBranchDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BRANCH_DEPTNAME");
		//快递大区
		String expressBigAreaDeptName = (String)CacheManager.getInstance().getCache(ServerParameterCache.UUID).get("EXPRESS_BIG_AREA_DEPTNAME");
		// 1、查询部门为 “快递分部”或者“快递大区”
		if(dept != null && (dept.getDeptName().endsWith(expressBranchDeptName) || dept.getDeptName().endsWith(expressBigAreaDeptName))) {
			return custCreditService.getCustCreditCountByOtherConditions(map);
		}
		// 2、查询部门为“点部”
		if(dept != null && dept.getDeptName().endsWith(MINISTRY)) {
			return custCreditService.getCustCreditCountByMinistryConditions(map);
		}
		return custCreditService.getCustCreditCountByConditions(map);
	}

	/**
	 * 
	 * <p>
	 * 信用较差客户报表导出<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @return FileInputStream
	 * @throws ParseException, IOException
	 * 
	 */
	@Override
	public FileInputStream getExcel(String deptId, String date) throws ParseException, IOException {
		this.custCreditAuthorization(deptId);
		String beginDate = "";
		String endDate = "";
		int day = CustCreditUtil.getDayForStr(date, DATE_FORMAT);
		if(day >= 16) {
			// 查询本月1号至15号的信用较差客户
			beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(date, DATE_FORMAT, 1));
			endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getHalfMonthDate(date, DATE_FORMAT, 15));
		}else{
			// 查询上个月16号至上个月最后一天的信用较差客户
			beginDate = CustCreditUtil.dateFormat(DATE_FORMAT, 
					CustCreditUtil.getBeginDate(date, DATE_FORMAT, 1, 16));
			endDate = CustCreditUtil.dateFormat(DATE_FORMAT, CustCreditUtil.getEndDate(date, DATE_FORMAT));
		}
		// 文件名，如：信用较差客户明细表-2014-03-25-10341
		String fileName = "信用较差客户明细表-" + endDate + "-" + deptId + ".xls";
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(fileName);
		List<FileInfo> fileInfoList = fileManager.searchFileInfoByCondition(fileInfo, 0, 1);
		FileInputStream fileInput = null;
		if(fileInfoList.size() < 1) {
			File file = null;
			try {
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(PropertiesUtil.getInstance()
						.getProperty("excelExportTemplatePath").trim().toString());
				stringBuffer.append("/");
				stringBuffer.append(PropertiesUtil.getInstance().getProperty("CustCreditReportTemplate"));
				file = new File(stringBuffer.toString());
				if (!file.exists()) {
					logger.error(stringBuffer.toString() + "：对应文件不存在！");
					FileException fe = new FileException(
							FileExceptionType.FILE_EXCEPTION_FILENOTEXISTS);
					throw new GeneralException(fe.getErrorCode(),
							fe.getMessage(), fe, new Object[] {}) {

								/**
								 * 
								 */
								private static final long serialVersionUID = -2260528795563317896L;
					};
				}
				
				//上传文件
				fileInfo = fileManager.fileUpload(file, fileName, "customer", "customer");
				fileManager.saveFileInfo(fileInfo);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("人品出问题啦---信用较差客户excel生成失败");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("deptId", deptId);
			map.put("beginDate", beginDate);
			map.put("endDate", endDate);
			List<CustCredit> custBadCreditList = custCreditService.getCustCreditListByConditions(map, 0, -1);
			CustCreditConfig config = this.getCustCreditConfig();
			fileInput = this.getExcelFile(config, custBadCreditList, new File(fileInfo.getSavePath()));
		}
		for(FileInfo info : fileInfoList) {
			File file = new File(info.getSavePath());
			try {
				fileInput = new FileInputStream(file);
			} catch (IOException e) {
				Log.info("不知道啥异常", e);
				throw e;
			}
				
		}
		return fileInput;
	}
}
