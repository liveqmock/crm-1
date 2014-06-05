package com.deppon.crm.module.client.fin.domain;

/**
 * @作者：罗典
 * @描述：收银员账号信息
 * @时间：2013-01-18
 * */
public class CashierAccountInfo {
	// 银行省份编码
	private String bankProvinceCode;
	// 银行城市编码
	private String bankCityCode;
	// 银行支行编码
	private String bankBranchCode;
	// 开户银行编码
	private String bankCode;
	// 开户名
	private String openBankUserName;
	// 账号，
	private String account;
	// 账号性质   2:收银员卡账户    暂时只可能传这一种值过来
	private String accountNature;
	// 手机号
	private String mobile;

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

	public String getBankBranchCode() {
		return bankBranchCode;
	}

	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getOpenBankUserName() {
		return openBankUserName;
	}

	public void setOpenBankUserName(String openBankUserName) {
		this.openBankUserName = openBankUserName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountNature() {
		return accountNature;
	}

	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
