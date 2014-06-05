package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class BusinessOpportunityCondition {

	// 商机名称
	private String boName;
	// 商机编码
	private String boNumber;
	// 客户Id
	private String customerId;
	// 客户名称
	private String customerName;
	// 客户编号
	private String customerNum;
	// 联系人姓名
	private String contactName;
	// 联系人电话
	private String contactPhone;
	// 联系人手机
	private String contactMobile;
	// 商机状态
	private String boStatus;
	// 商机阶段
	private String boStep;
	// 开始时间
	private Date beginTime;
	// 结束时间
	private Date endTime;
	// 部门类型
	private int deptType;
	// 所属部门id
	private String deptId;
	// 开始条数
	private int start;
	// 每页条数
	private int limit;

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = StringUtils.trim(boName);
	}

	public String getBoNumber() {
		return boNumber;
	}

	public void setBoNumber(String boNumber) {
		this.boNumber = StringUtils.trim(boNumber);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = StringUtils.trim(customerName);
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = StringUtils.trim(customerNum);
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = StringUtils.trim(contactName);
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = StringUtils.trim(contactPhone);
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = StringUtils.trim(contactMobile);
	}

	public String getBoStatus() {
		return boStatus;
	}

	public void setBoStatus(String boStatus) {
		this.boStatus = StringUtils.trim(boStatus);
	}

	public String getBoStep() {
		return boStep;
	}

	public void setBoStep(String boStep) {
		this.boStep = StringUtils.trim(boStep);
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = StringUtils.trim(customerId);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getDeptType() {
		return deptType;
	}

	public void setDeptType(int deptType) {
		this.deptType = deptType;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = StringUtils.trim(deptId);
	}

}
