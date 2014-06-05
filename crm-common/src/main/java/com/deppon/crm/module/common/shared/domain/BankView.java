package com.deppon.crm.module.common.shared.domain;

public class BankView {
	//所在省
	private String province;
	//所在省ID
	private String provinceId;
	//所在城市
	private String city;
	//所在城市id
	private String cityId;
	//开户银行
	private String accountBank;
	//开户支行
	private String accountBranch;
	//开户银行ID
	private String accountBankId;
	//开户支行ID
	private String accountBranchId;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAccountBankId() {
		return accountBankId;
	}
	public void setAccountBankId(String accountBankId) {
		this.accountBankId = accountBankId;
	}
	public String getAccountBranchId() {
		return accountBranchId;
	}
	public void setAccountBranchId(String accountBranchId) {
		this.accountBranchId = accountBranchId;
	}
}
