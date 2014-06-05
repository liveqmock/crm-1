package com.deppon.crm.module.backfreight.shared.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * Description:退运费实体 BackFreight.java Create on 2012-10-29 上午10:22:15
 * 
 * @author 华龙
 * @version 1.0
 */
public class BackFreight extends BaseEntity {
	/* 运单号 */
	private String waybillNumber;
	/* 运输性质 */
	private String tranType;
	/* 客户类型 */
	private String customerType;
	/* 客户编号 */
	private String customerNum;
	/* 客户姓名 */
	private String customerName;
	/* 客户等级 */
	private String customerLevel;
	/* 联系人姓名 */
	private String contactName;
	/* 运单总额 */
	private Double waybillAmount;
	/* 出库时间（签收时间） */
	private Date outboundTime;
	/* 申请总额 */
	private Integer applyAmount;
	/* 申请原因 */
	private String applyReason;
	/* 申请人 */
	private String applicant;
	/* 申请人名字 */
	private String applicantName;
	/* 申请部门 */
	private String applyDept;
	/* 申请部门 名字*/
	private String applyDeptName;
	/* 申请时间 */
	private Date applyTime;
	/* 申请状态 */
	private String applyStatus;
	/* OA工作流编号 */
	private String oaWorkflowNum;
	/* 审批时间 */
	private Date verifyTime;
	/* 审批人 */
	private String verifier;
	/* 审批人名字 */
	private String verifierName;
	/* 支付方式 */
	private String paymentType;
	/* 子公司 */
	private String subsidiary;
	/* 财务部 */
	private String financeDept;
	/* 所属财政部门名 */
	private String financeDeptName;
	/* 配载部门 */
	private String stowageDept;
	/* 配置部门名 */
	private String stowageDeptName;
	/*申请人职务*/
	private String position;
	private String empCode;
	private String financeDeptCode;
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();

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

	public double getWaybillAmount() {
		return waybillAmount;
	}

	public void setWaybillAmount(double waybillAmount) {
		this.waybillAmount = waybillAmount;
	}

	public Date getOutboundTime() {
		return outboundTime;
	}

	public void setOutboundTime(Date outboundTime) {
		this.outboundTime = outboundTime;
	}

	public Integer getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Integer applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSubsidiary() {
		return subsidiary;
	}

	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	public String getFinanceDept() {
		return financeDept;
	}

	public void setFinanceDept(String financeDept) {
		this.financeDept = financeDept;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	public String getFinanceDeptName() {
		return financeDeptName;
	}

	public void setFinanceDeptName(String financeDeptName) {
		this.financeDeptName = financeDeptName;
	}

	public String getStowageDept() {
		return stowageDept;
	}

	public void setStowageDept(String stowageDept) {
		this.stowageDept = stowageDept;
	}

	public String getStowageDeptName() {
		return stowageDeptName;
	}

	public void setStowageDeptName(String stowageDeptName) {
		this.stowageDeptName = stowageDeptName;
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

	public void setWaybillAmount(Double waybillAmount) {
		this.waybillAmount = waybillAmount;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getFinanceDeptCode() {
		return financeDeptCode;
	}

	public void setFinanceDeptCode(String financeDeptCode) {
		this.financeDeptCode = financeDeptCode;
	}
}
