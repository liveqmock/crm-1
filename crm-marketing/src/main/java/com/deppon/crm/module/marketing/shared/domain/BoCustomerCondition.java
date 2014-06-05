package com.deppon.crm.module.marketing.shared.domain;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class BoCustomerCondition {
	// 会员所属部门ID
	private String deptId;
	// 客户编码
	private String custNumber;
	// 客户名称（企业或个人）
	private String custName;
	// 联系人姓名
	private String linkmanName;
	// 手机号码(联系人手机号)
	private String mobile;
	// 固定电话(联系人固定电话)
	private String phone;
	// 客户来源
	private String custResource;
	// 合作意向
	private String purpose;
	// 开发阶段
	private String developStage;
	// 一级行业
	private String firstTrade;
	// 二级行业
	private String secondTrade;
	// 业务类别
	private String custCategory;
	// 数据权限部门
	private List<String> deptIds;
	// 部门类型
	private int deptType;
	// 父部门id
	private String parentId;
	// 起始值
	private int start;
	// 长度 值为-1时不分页查询
	private int limit = -1;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = StringUtils.trim(deptId);
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = StringUtils.trim(custNumber);
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = StringUtils.trim(custName);
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = StringUtils.trim(linkmanName);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = StringUtils.trim(mobile);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = StringUtils.trim(phone);
	}

	public String getCustResource() {
		return custResource;
	}

	public void setCustResource(String custResource) {
		this.custResource = StringUtils.trim(custResource);
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = StringUtils.trim(purpose);
	}

	public String getDevelopStage() {
		return developStage;
	}

	public void setDevelopStage(String developStage) {
		this.developStage = StringUtils.trim(developStage);
	}

	public String getFirstTrade() {
		return firstTrade;
	}

	public void setFirstTrade(String firstTrade) {
		this.firstTrade = StringUtils.trim(firstTrade);
	}

	public String getSecondTrade() {
		return secondTrade;
	}

	public void setSecondTrade(String secondTrade) {
		this.secondTrade = StringUtils.trim(secondTrade);
	}

	public String getCustCategory() {
		return custCategory;
	}

	public void setCustCategory(String custCategory) {
		this.custCategory = StringUtils.trim(custCategory);
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = StringUtils.trim(parentId);
	}

}
