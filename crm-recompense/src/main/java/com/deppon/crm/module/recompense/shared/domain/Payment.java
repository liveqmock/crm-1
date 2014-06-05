package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;
import com.deppon.foss.framework.entity.BaseEntity;

public class Payment extends BaseEntity {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 7533067468601558238L;
	// 创建人Id
	private String createUserId;
	// 创建时间
	private Date createTime;
	// 理赔Id
	private String recompenseId;
	// 领款方式
	private String paymentMode;
	// 账号性质
	private String accountProp;
	// 开户名
	private String openName;
	// 账号
	private String account;
	private BankProvince bankProvice;
	private String bankProviceName;
	private BankCity bankCity;
	private String bankCityName;
	private AccountBank bank;
	private String bankName;
	private AccountBranch branch;
	private String branchName;
	// 手机
	private String mobile;
	// 付款状态
	private String paymentStatus;
	// 申请时间
	private Date applyTime;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRecompenseId() {
		return recompenseId;
	}

	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getAccountProp() {
		return accountProp;
	}

	public void setAccountProp(String accountProp) {
		this.accountProp = accountProp;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public BankProvince getBankProvice() {
		return bankProvice;
	}

	public void setBankProvice(BankProvince bankProvice) {
		this.bankProvice = bankProvice;
	}

	public BankCity getBankCity() {
		return bankCity;
	}

	public void setBankCity(BankCity bankCity) {
		this.bankCity = bankCity;
	}

	public AccountBank getBank() {
		return bank;
	}

	public void setBank(AccountBank bank) {
		this.bank = bank;
	}

	public AccountBranch getBranch() {
		return branch;
	}

	public void setBranch(AccountBranch branch) {
		this.branch = branch;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankProviceName() {
		return bankProviceName;
	}

	public void setBankProviceName(String bankProviceName) {
		this.bankProviceName = bankProviceName;
	}

	public String getBankCityName() {
		return bankCityName;
	}

	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
