package com.deppon.crm.module.client.workflow.domain;

/**
 * 收银员账户信息
 * @author davidcun @2012-4-12
 */
public class AccountInfo {

	//工号
	private String jobNumber;
	//开户银行
	private String bank;
	//银行账号
	private String accountNumber;
	//开户名
	private String accountName;
	//开户支行
	private String subbranch;
	//省
	private String province;
	//市
	private String city;
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}
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
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	
}
