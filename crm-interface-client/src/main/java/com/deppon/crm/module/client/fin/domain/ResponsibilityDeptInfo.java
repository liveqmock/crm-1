package com.deppon.crm.module.client.fin.domain;


/**
 * @作者：罗典
 * @描述：责任部门信息
 * @时间：2012-11-22
 * */
public class ResponsibilityDeptInfo {

	// 责任部门标杆编码(这笔费用的承担部门)
	protected String responsibilityDeptCode;
	// 承担金额
	protected String responsibilityMoney;

	public String getResponsibilityDeptCode() {
		return responsibilityDeptCode;
	}

	public void setResponsibilityDeptCode(String responsibilityDeptCode) {
		this.responsibilityDeptCode = responsibilityDeptCode;
	}

	public String getResponsibilityMoney() {
		return responsibilityMoney;
	}

	public void setResponsibilityMoney(String responsibilityMoney) {
		this.responsibilityMoney = responsibilityMoney;
	}

}
