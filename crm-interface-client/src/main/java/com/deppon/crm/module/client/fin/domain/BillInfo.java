package com.deppon.crm.module.client.fin.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BillInfo {

	// 金额
	private BigDecimal recompenseAmount;
	// 账户性质
	private String accountNature;
	// 开户账号
	private String bankAccount;
	// 开户银行编码
	private String bankCode;
	// 开户支行编码
	private String bankBranchCode;
	// 开户行省份编码
	private String bankProvinceCode;
	// 开户行城市编码
	private String bankCityCode;
	// 联系方式
	private String mobilePhone;
	// 申请人工号
	private String applyUser;
	//费用明细
	private List<CostDetail> costDetails;
	// 凭证号
	private String billNum;
	//开户人姓名
	private String userName;
	//客户领款方式
	private String drawMoneyType;
	
	public BigDecimal getRecompenseAmount() {
		return recompenseAmount;
	}
	public void setRecompenseAmount(BigDecimal recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}
	public String getAccountNature() {
		return accountNature;
	}
	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getBankProvinceCode() {
		return bankProvinceCode;
	}
	public void setBankProvinceCode(String bankProvinceCode) {
		this.bankProvinceCode = bankProvinceCode;
	}
	public String getBankCityCode() {
		return bankCityCode;
	}
	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public List<CostDetail> getCostDetails() {
		if (costDetails == null) {
			costDetails = new ArrayList<CostDetail>();
        }
		return costDetails;
	}
	public void setCostDetails(List<CostDetail> costDetails) {
		this.costDetails = costDetails;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDrawMoneyType() {
		return drawMoneyType;
	}
	public void setDrawMoneyType(String drawMoneyType) {
		this.drawMoneyType = drawMoneyType;
	}
	
}
