package com.deppon.crm.module.scheduler.shared.domain;

public class CompareAccount {
	// 银行账号
	private String bankAccount;
	// 开户行ID
	private String bankId;
	// 支行名称Id
	private String subBanknameId;
	// CRMID
	private String crmId;
	// 客户ID
	private String custId;
	// 最近跟新时间
	private String lastUpdateTime;

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getSubBanknameId() {
		return subBanknameId;
	}

	public void setSubBanknameId(String subBanknameId) {
		this.subBanknameId = subBanknameId;
	}

	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

}
