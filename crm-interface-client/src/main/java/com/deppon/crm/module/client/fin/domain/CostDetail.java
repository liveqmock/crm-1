package com.deppon.crm.module.client.fin.domain;

import java.math.BigDecimal;
import java.util.Date;


public class CostDetail {

	//费用发生日期
    protected Date costDate;
    //报账金额
    protected BigDecimal reimbursementMoneyDetail;
    // 差错编号
    protected String voucherNumber;
    //费用承担部门
    protected String responsibilityDeptInfo;
    //理赔类型
    protected String recompenseType;
	public Date getCostDate() {
		return costDate;
	}
	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}
	public BigDecimal getReimbursementMoneyDetail() {
		return reimbursementMoneyDetail;
	}
	public void setReimbursementMoneyDetail(BigDecimal reimbursementMoneyDetail) {
		this.reimbursementMoneyDetail = reimbursementMoneyDetail;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getResponsibilityDeptInfo() {
		return responsibilityDeptInfo;
	}
	public void setResponsibilityDeptInfo(String responsibilityDeptInfo) {
		this.responsibilityDeptInfo = responsibilityDeptInfo;
	}
	public String getRecompenseType() {
		return recompenseType;
	}
	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}
    
}
