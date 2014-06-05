package com.deppon.crm.module.client.workflow.domain;

import java.util.Date;
import java.util.List;

/**
 * @作者：罗典
 * @描述：服务补救工作流申请实体
 * @时间：2012-11-19
 * */
public class BackFreightInfo {
	// 申请人工号
	private String applyPersonCode;
	// 申请人姓名
	private String applyPersonName;
	// 申请人职务
	private String proposerPosition;
	// 所属部门
	private String applyDept;
	// 运单号
	private String waybillNo;
	// 运输性质
	private String transportType;
	// 空运总调
	private String airliftJustify;
	// 当地财务部
	private String localFinancial;
	// 申请金额
	private Long applyAmount;
	// 申请事由
	private String applyReason;
	// 配载部门
	private String stowageDept;
	// 纯运费
	private Double pureCarriage;
	// 客户信息
	private String customerInfor;
	// 付款方式
	private String paymentType;
	// 签收时间
	private Date signinTime;
	// 附件
	private List<ContractNounInfo> file;
	public String getApplyPersonCode() {
		return applyPersonCode;
	}

	public void setApplyPersonCode(String applyPersonCode) {
		this.applyPersonCode = applyPersonCode;
	}

	public List<ContractNounInfo> getFile() {
		return file;
	}

	public void setFile(List<ContractNounInfo> file) {
		this.file = file;
	}

	public String getApplyPersonName() {
		return applyPersonName;
	}

	public void setApplyPersonName(String applyPersonName) {
		this.applyPersonName = applyPersonName;
	}

	public String getProposerPosition() {
		return proposerPosition;
	}

	public void setProposerPosition(String proposerPosition) {
		this.proposerPosition = proposerPosition;
	}

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getAirliftJustify() {
		return airliftJustify;
	}

	public void setAirliftJustify(String airliftJustify) {
		this.airliftJustify = airliftJustify;
	}

	public String getLocalFinancial() {
		return localFinancial;
	}

	public void setLocalFinancial(String localFinancial) {
		this.localFinancial = localFinancial;
	}

	public Long getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Long applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getStowageDept() {
		return stowageDept;
	}

	public void setStowageDept(String stowageDept) {
		this.stowageDept = stowageDept;
	}

	public Double getPureCarriage() {
		return pureCarriage;
	}

	public void setPureCarriage(Double pureCarriage) {
		this.pureCarriage = pureCarriage;
	}

	public String getCustomerInfor() {
		return customerInfor;
	}

	public void setCustomerInfor(String customerInfor) {
		this.customerInfor = customerInfor;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getSigninTime() {
		return signinTime;
	}

	public void setSigninTime(Date signinTime) {
		this.signinTime = signinTime;
	}

}
