package com.deppon.crm.module.client.workflow.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @作者：罗典
 * @时间：2012-9-13
 * @描述：积分兑换申请信息
 * */
@XStreamAlias("billHead")
public class BillHead {
	// 需求申请单编号
	private String appnum = "";
	// 申请人
	private String applicant;
	// 申请部门
	private String applyOrgUnit;
	// 申请时间
	private String applyDate;
	// 地址
	private String address;
	// 电话-
	private String phone;

	public String getAppnum() {
		return appnum;
	}

	public void setAppnum(String appnum) {
		this.appnum = appnum;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplyOrgUnit() {
		return applyOrgUnit;
	}

	public void setApplyOrgUnit(String applyOrgUnit) {
		this.applyOrgUnit = applyOrgUnit;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}