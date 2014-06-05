package com.deppon.crm.module.scheduler.shared.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：罗典
 * @时间：2012-8-24
 * @描述：客户对比数据实体
 * */
public class CompareMember {
	// 客户编码
	private String custNumber;
	// 临欠额度
	private Double procRedit;
	// 客户状态 正常：0； 审批中：1 ；无效 ：2；
	private String custStatus;
	// CRM客户ID
	private String crmId;
	// 账号信息
	private List<CompareAccount> accountList = new ArrayList<CompareAccount>();
	// 联系人信息
	private List<CompareContact> contactList = new ArrayList<CompareContact>();
	// 合同信息
	private List<CompareContract> contractList = new ArrayList<CompareContract>();

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public Double getProcRedit() {
		return procRedit;
	}

	public void setProcRedit(Double procRedit) {
		this.procRedit = procRedit;
	}

	public String getCustStatus() {
		return custStatus;
	}

	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public List<CompareAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<CompareAccount> accountList) {
		this.accountList = accountList;
	}

	public List<CompareContact> getContactList() {
		return contactList;
	}

	public void setContactList(List<CompareContact> contactList) {
		this.contactList = contactList;
	}

	public List<CompareContract> getContractList() {
		return contractList;
	}

	public void setContractList(List<CompareContract> contractList) {
		this.contractList = contractList;
	}

}
