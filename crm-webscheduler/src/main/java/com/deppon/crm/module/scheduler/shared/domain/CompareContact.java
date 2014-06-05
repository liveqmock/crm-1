package com.deppon.crm.module.scheduler.shared.domain;

import java.util.Date;

public class CompareContact {
	// 联系人编码
	private String number;
	// 状态 正常：0； 审批中：1 ；失效 2；
	private String status;
	// 手机号码
	private String mobilePhone;
	// 客户ID
	private String custId;
	// 最近更新时间
	private Date lastUpdateTime;
	// crmId
	private String crmId;
	

	public String getCrmId() {
		return crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
