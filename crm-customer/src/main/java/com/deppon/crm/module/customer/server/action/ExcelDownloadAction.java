package com.deppon.crm.module.customer.server.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title OutAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author Administrator
 * @version 0.1 2012-5-23
 */
public class ExcelDownloadAction extends AbstractAction {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -1883072471033134830L;
	private IContractManager contractManager;// 合同管理manage
	// 查询条件
	private ContractCondition contractCondition = new ContractCondition();
	// 部门Id
	private String deptId = "";
	// 客户编码
	private String custNumber = "";
	// 客户全称
	private String custCompany = "";
	// 合同编号
	private String contractNum = "";
	// 协议联系人
	private String contactName = "";
	// 创建起始时间
	private long searchStartTime;
	// 创建结束时间
	private long searchEndTime;
	// 查询结果
	private List<Contract> contractList = new ArrayList<Contract>();

	private String aa;

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}
    /**
     * 
     * <p>
     * Description:生成excel文件<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @return
     * InputStream
     */
	public InputStream getExcelFile() {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");

		// 创建表头
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("合同编号");
		row.createCell(1).setCellValue("归属部门");
		row.createCell(2).setCellValue("客户全称");
		row.createCell(3).setCellValue("优惠类型");
		row.createCell(4).setCellValue("结款方式");
		row.createCell(5).setCellValue("欠款额度");
		row.createCell(6).setCellValue("生效日期");
		row.createCell(7).setCellValue("失效日期");
		row.createCell(8).setCellValue("协议联系人");
		row.createCell(9).setCellValue("联系人手机");
		row.createCell(10).setCellValue("联系人电话");
		row.createCell(11).setCellValue("合同状态");
		row.createCell(12).setCellValue("创建人");
		row.createCell(13).setCellValue("创建时间");
		row.createCell(14).setCellValue("最后修改人");
		row.createCell(15).setCellValue("最后修改时间");

		// 创建数据
		int i = 1;
		for (Contract contract : contractList) {
			row = sheet.createRow(i++);
			row.createCell(0).setCellValue(contract.getContractNum());
			row.createCell(1).setCellValue(contract.getDept().getDeptName());
			row.createCell(2).setCellValue(contract.getCustCompany());
			row.createCell(3).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.PRIVILEGE_TYPE,
					contract.getPreferentialType()));
			row.createCell(4).setCellValue(DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.RECKON_WAY,
					contract.getPayWay()));
			row.createCell(5).setCellValue(contract.getArrearaMount());
			row.createCell(6).setCellValue(contract.getContractBeginDate());
			row.createCell(7).setCellValue(contract.getContractendDate());
			row.createCell(8).setCellValue(contract.getLinkManName());
			row.createCell(9).setCellValue(contract.getLinkManMobile());
			row.createCell(10).setCellValue(contract.getLinkManPhone());
			if("1".equals(contract.getContractStatus())){
				row.createCell(11).setCellValue("生效");
			}else{
				row.createCell(11).setCellValue("失效");
			}
			
			row.createCell(12).setCellValue(contract.getCreateUser());
			row.createCell(13).setCellValue(contract.getCreateDate());
			row.createCell(14).setCellValue(contract.getModifyUser());
			row.createCell(15).setCellValue(contract.getModifyUser());
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			workbook.write(baos);
		} catch (IOException e) {
			
		}
		byte[] ba = baos.toByteArray();
		return new ByteArrayInputStream(ba);

	}

   /**
     * 
     * <p>
     * Description:封装下载文件<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @return
     * InputStream
     */
	public String downLoad() {
		contractCondition.setContactName(contactName);
		contractCondition.setContractNum(contractNum);
		contractCondition.setCustCompany(custCompany);
		contractCondition.setCustNumber(custNumber);
		contractCondition.setDeptId(deptId);
		contractCondition.setSearchEndTime(new Date(searchEndTime));
		contractCondition.setSearchStartTime(new Date(searchStartTime));
		if (contractCondition.getSearchEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(contractCondition.getSearchEndTime());
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE,59); 
			cal.set(Calendar.SECOND,59);
			contractCondition.setSearchEndTime(cal.getTime());
		}
		contractList = contractManager.searchContract(contractCondition, 0,
				100000);
		return SUCCESS;
	}

	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}

	public ContractCondition getContractCondition() {
		return contractCondition;
	}

	public void setContractCondition(ContractCondition contractCondition) {
		this.contractCondition = contractCondition;
	}

	public List<Contract> getContractList() {
		return contractList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustCompany() {
		return custCompany;
	}

	public void setCustCompany(String custCompany) {
		this.custCompany = custCompany;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public long getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(long searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public long getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(long searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

}
