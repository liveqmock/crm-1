package com.deppon.crm.module.marketing.shared.domain;

import org.apache.commons.lang.StringUtils;

public class BusinessOpportunityCustomer {

	// 客户Id
	private String custId;
	// 客户名称
	private String custName;
	// 客户编码
	private String custNumber;
	// 客户一级行业
	private String custFirstType;
	// 客户二级行业
	private String custSecondType;
	// 联系人Id
	private String mainLinkmanId;
	// 联系人姓名
	private String mainLinkmanName;
	// 联系人电话
	private String mainLinkmanPhone;
	// 联系人手机
	private String mainLinkmanMobile;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = StringUtils.trim(custId);
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = StringUtils.trim(custName);
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = StringUtils.trim(custNumber);
	}

	public String getCustFirstType() {
		return custFirstType;
	}

	public void setCustFirstType(String custFirstType) {
		this.custFirstType = StringUtils.trim(custFirstType);
	}

	public String getCustSecondType() {
		return custSecondType;
	}

	public void setCustSecondType(String custSecondType) {
		this.custSecondType = StringUtils.trim(custSecondType);
	}

	public String getMainLinkmanId() {
		return mainLinkmanId;
	}

	public void setMainLinkmanId(String mainLinkmanId) {
		this.mainLinkmanId = StringUtils.trim(mainLinkmanId);
	}

	public String getMainLinkmanName() {
		return mainLinkmanName;
	}

	public void setMainLinkmanName(String mainLinkmanName) {
		this.mainLinkmanName = StringUtils.trim(mainLinkmanName);
	}

	public String getMainLinkmanPhone() {
		return mainLinkmanPhone;
	}

	public void setMainLinkmanPhone(String mainLinkmanPhone) {
		this.mainLinkmanPhone = StringUtils.trim(mainLinkmanPhone);
	}

	public String getMainLinkmanMobile() {
		return mainLinkmanMobile;
	}

	public void setMainLinkmanMobile(String mainLinkmanMobile) {
		this.mainLinkmanMobile = StringUtils.trim(mainLinkmanMobile);
	}

}
