package com.deppon.crm.module.scheduler.server.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.scheduler.server.manager.UserUtil;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 定时器声明-散客失效定时器
 * </p>
 * @title LoseScatterCustomerJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-2-20
 */
public class LoseScatterCustomerJob extends GridJob{
	IFileManager fileManager;
	IMessageManager messageManager;
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		fileManager = getBean("fileManager", IFileManager.class);
		messageManager = getBean("messageManager", IMessageManager.class);
		Log logger = LogFactory.getLog(LoseScatterCustomerJob.class);
		loseScatterCustomer(new Date());
		logger.info(new Date() + " loseScatterCustomerJob");
	}
	/**
	 * 
	 * <p>
	 * 散客失效<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-5-26
	 * @param date
	 * void
	 */
	public void loseScatterCustomer(Date date){
		IMemberManager memberManager = getBean("scatterCustomerManager",
				IMemberManager.class);
		IAlterMemberManager alterMemberManager = getBean("scatterCustomerManager",
				IAlterMemberManager.class);
		UserUtil.setUserToAdmin();
		List<String> scDeptIdList = memberManager.getNotDevpPlanAndSysmakelsLoseDeptList(date,Constant.CUST_GROUP_SC_CUSTOMER);
		for (String scDept : scDeptIdList) {
			
			List<Member> scList = memberManager.getNotDevpPlanAndSysmakelsLoseList(date, scDept,Constant.CUST_GROUP_SC_CUSTOMER);
			String[] ids = new String[scList.size()];
			for (int i=0;i<scList.size();i++) {
				ids[i] = scList.get(i).getId();
				alterMemberManager.deleteMember(scList.get(i));
			}
			
			//上传一个文件
			FileInfo fileInfo = createPromptFile(scList,"本部门今年失效散客.xls","scheduler");
		
			//生成一个代办
			createMessage(fileInfo,scDept);
		}
	}
	/**
	 * 
	 * <p>
	 * 生成提示文件<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-5-26
	 * @param pcList 文件生成数据--- 此方法提供给  潜散客
	 * @param fileName 文件名称
	 * @param fileType 文件类型
	 * @return
	 * FileInfo
	 */
	private FileInfo createPromptFile(List<? extends Member> pcList,
			String fileName, String fileType) {
		File file = null;
		try {
			file = File.createTempFile("temp", ".xls");
		} catch (IOException e) {
			throw new RuntimeException("人品出问题啦");
		}
		getExcelFile(pcList,file);
		//上传文件
		FileInfo fileInfo = fileManager.fileUpload(file, fileName,fileType, "customer"
				);
		
		file.deleteOnExit();
		return fileInfo;
	}
	/**
	 * 
	 * <p>
	 * 创建一个下载文件的代办<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-5-26
	 * @param fileInfo
	 * void
	 */
	private void createMessage(FileInfo fileInfo,String pcDeptId) {
		Message todoMessage = new Message();
		todoMessage.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
		todoMessage.setTaskcount(1);
		
		todoMessage.setDeptId(Integer.parseInt(pcDeptId));
		todoMessage.setIshaveinfo("<a href ='../common/downLoad.action?fileName="+fileInfo.getFileName()+"&inputPath="+fileInfo.getSavePath()+"'>"+fileInfo.getFileName()+"</a>");
		messageManager.addMessage(todoMessage);
	}
	/**
	 * 
	 * <p>
	 * Description:潜客<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-5-26
	 * @param dataList
	 * @return
	 * InputStream
	 */
	public FileOutputStream getExcelFile(List<? extends Member> dataList,File file) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");

		// 创建表头
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("客户名称");
		row.createCell(1).setCellValue("行业");
		row.createCell(2).setCellValue("客户来源");
		row.createCell(3).setCellValue("联系人姓名");
		row.createCell(4).setCellValue("联系人手机");
		row.createCell(5).setCellValue("联系人电话");
		row.createCell(6).setCellValue("最近合作物流公司");
		row.createCell(7).setCellValue("合作意向");
		row.createCell(8).setCellValue("货量潜力");
		row.createCell(9).setCellValue("客户需求");

		// 创建数据
		int i = 1;
		for (Member pc : dataList) {
			row = sheet.createRow(i++);
			
			row.createCell(0).setCellValue(pc.getCustName());
			row.createCell(1).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.TRADE,pc.getTradeId()));
			row.createCell(2).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CUST_SOURCE,pc.getPotenSource()));
			row.createCell(3).setCellValue(pc.getContactList().get(0).getName());
			row.createCell(4).setCellValue(pc.getContactList().get(0).getTelPhone());
			row.createCell(5).setCellValue(pc.getContactList().get(0).getMobilePhone());
			row.createCell(6).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.COOPERATION_LOGISTICS,pc.getMemberExtend().getRecentCoop()));
			row.createCell(7).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.COOPERATION_INTENTION,pc.getMemberExtend().getCoopIntention()));
			row.createCell(8).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CARGO_POTENTIAL, pc.getMemberExtend().getVolumePotential()));
			row.createCell(9).setCellValue(pc.getMemberExtend().getCustNeed());
		}
		FileOutputStream fileOut = null;
		try {
			// 创建excel文件
			fileOut= new FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileOut;
	}
}
