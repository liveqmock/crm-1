package com.deppon.crm.module.servicerecovery.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * Description:服务补救实体类 ServiceRecovery.java Create on 2012-10-29 上午9:53:56
 * 
 * @author 华龙
 * @version 1.0
 */
public class ServiceRecovery extends BaseEntity {
	/** 运单号 */
	private String waybillNumber;
	/** 客户类型 */
	private String customerType;
	/** 客户编号 */
	private String customerNum;
	/** 客户姓名 */
	private String customerName;
	/** 客户等级 */
	private String customerLevel;
	/** 联系人姓名 */
	private String contactName;
	/** 运单总额 */
	private Double waybillAmount;
	/** 减免总额 */
	private Double reductionAmount;
	/** 出库时间（签收时间） */
	private Date outboundTime;
	/** 所属财政部门 */
	private String financeDept;
	/** 所属财政部门名 */
	private String financeDeptName;
	/** 操作人 */
	private String operator;
	/** 操作人 姓名 */
	private String operatorName;
	/** 减免类型 */
	private String reductionType;
	/** 总件数 */
	private Integer totalPackage;
	/** 破损件数 */
	private Integer damagePackage;
	/** 补救原因 */
	private String recoveryReason;
	/** 申请人 */
	private String applicant;
	/** 申请人 姓名 */
	private String applicantName;
	/** 申请部门 */
	private String applyDept;
	/** 申请部门名称 */
	private String applyDeptName;
	/** 申请时间 */
	private Date applyTime;
	/** 申请状态 */
	private String applyStatus;
	/** OA工作流编号 */
	private String oaWorkflowNum;
	/** 审批时间 */
	private Date verifyTime;
	/** 审批人 */
	private String verifier;
	/** 审批人名称 */
	private String verifierName;
	/** 所属子公司 */
	private String subsidiary;
	/**申请人职务*/
	private String position;
	private String empCode;
	private String financeDeptCode;
	private String operatorCode;
	/**运输方式*/
	private String tranType;
	private String party;//收发货方,快递才用到
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	//工作流号 ICRM+年月日+7位数字
	private String workflowNo;
	
	//加密后的老工作流号
	private String workflowNumEnc;

	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getWaybillAmount() {
		return waybillAmount;
	}

	public void setWaybillAmount(Double waybillAmount) {
		this.waybillAmount = waybillAmount;
	}

	public Date getOutboundTime() {
		return outboundTime;
	}

	public void setOutboundTime(Date outboundTime) {
		this.outboundTime = outboundTime;
	}

	public String getFinanceDept() {
		return financeDept;
	}

	public void setFinanceDept(String financeDept) {
		this.financeDept = financeDept;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getReductionType() {
		return reductionType;
	}

	public void setReductionType(String reductionType) {
		this.reductionType = reductionType;
	}

	public Integer getTotalPackage() {
		return totalPackage;
	}

	public void setTotalPackage(Integer totalPackage) {
		this.totalPackage = totalPackage;
	}

	public Integer getDamagePackage() {
		return damagePackage;
	}

	public void setDamagePackage(Integer damagePackage) {
		this.damagePackage = damagePackage;
	}

	public String getRecoveryReason() {
		return recoveryReason;
	}

	public void setRecoveryReason(String recoveryReason) {
		this.recoveryReason = recoveryReason;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getOaWorkflowNum() {
		return oaWorkflowNum;
	}

	public void setOaWorkflowNum(String oaWorkflowNum) {
		this.oaWorkflowNum = oaWorkflowNum;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public Double getReductionAmount() {
		return reductionAmount;
	}

	public void setReductionAmount(Double reductionAmount) {
		this.reductionAmount = reductionAmount;
	}

	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	public String getVerifierName() {
		return verifierName;
	}

	public void setVerifierName(String verifierName) {
		this.verifierName = verifierName;
	}

	public String getFinanceDeptName() {
		return financeDeptName;
	}

	public void setFinanceDeptName(String financeDeptName) {
		this.financeDeptName = financeDeptName;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFinanceDeptCode() {
		return financeDeptCode;
	}

	public void setFinanceDeptCode(String financeDeptCode) {
		this.financeDeptCode = financeDeptCode;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getWorkflowNumEnc() {
		return workflowNumEnc;
	}
	public void setWorkflowNumEnc(String workflowNumEnc) {
		this.workflowNumEnc = workflowNumEnc;
	}

	
}
