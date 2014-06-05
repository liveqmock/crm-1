package com.deppon.crm.module.client.fin.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：罗典
 * @描述：未开单理赔实体信息
 * @时间：2013-01-15
 * */
public class NoBillingRecompenseInfo {
	// 单号/差错编号
	private String billNum;
	// 金额
	private String recompenseAmount;
	// 开户银行
	private String bankCode;
	// 收款方名称
	private String receiverName;
	// 开户账号
	private String bankAccount;
	// 开户支行编码
	private String bankBranchCode;
	// 开户行省份编码
	private String bankProvinceCode;
	// 开户行城市编码
	private String bankCityCode;
	// 手机号码
	private String mobilePhone;
	// 客户领款方式  现金10  汇款20
	private String drawMoneyType;
	// 理赔类型  在线理赔 30  其它不限制
	private String recompenseType;
	// 申请人工号
	private String applyUser;
	// 账户性质
	private String accountNature;
	// 费用承担部门（单号）
	private List<ResponsibilityDeptInfo> responsibilityDeptInfos;

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getRecompenseAmount() {
		return recompenseAmount;
	}

	public void setRecompenseAmount(String recompenseAmount) {
		this.recompenseAmount = recompenseAmount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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

	public String getDrawMoneyType() {
		return drawMoneyType;
	}

	public void setDrawMoneyType(String drawMoneyType) {
		this.drawMoneyType = drawMoneyType;
	}

	public String getRecompenseType() {
		return recompenseType;
	}

	public void setRecompenseType(String recompenseType) {
		this.recompenseType = recompenseType;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getAccountNature() {
		return accountNature;
	}

	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}

	public List<ResponsibilityDeptInfo> getResponsibilityDeptInfos() {
		if(responsibilityDeptInfos == null){
			responsibilityDeptInfos = new ArrayList<ResponsibilityDeptInfo>();
		}
		return responsibilityDeptInfos;
	}

	public void setResponsibilityDeptInfos(
			List<ResponsibilityDeptInfo> responsibilityDeptInfos) {
		this.responsibilityDeptInfos = responsibilityDeptInfos;
	}

}
