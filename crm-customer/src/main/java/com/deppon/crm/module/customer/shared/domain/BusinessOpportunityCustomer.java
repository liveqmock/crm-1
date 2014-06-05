package com.deppon.crm.module.customer.shared.domain;

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
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustFirstType() {
		return custFirstType;
	}

	public void setCustFirstType(String custFirstType) {
		this.custFirstType = custFirstType;
	}

	public String getCustSecondType() {
		return custSecondType;
	}

	public void setCustSecondType(String custSecondType) {
		this.custSecondType = custSecondType;
	}

	public String getMainLinkmanId() {
		return mainLinkmanId;
	}

	public void setMainLinkmanId(String mainLinkmanId) {
		this.mainLinkmanId = mainLinkmanId;
	}

	public String getMainLinkmanName() {
		return mainLinkmanName;
	}

	public void setMainLinkmanName(String mainLinkmanName) {
		this.mainLinkmanName = mainLinkmanName;
	}

	public String getMainLinkmanPhone() {
		return mainLinkmanPhone;
	}

	public void setMainLinkmanPhone(String mainLinkmanPhone) {
		this.mainLinkmanPhone = mainLinkmanPhone;
	}

	public String getMainLinkmanMobile() {
		return mainLinkmanMobile;
	}

	public void setMainLinkmanMobile(String mainLinkmanMobile) {
		this.mainLinkmanMobile = mainLinkmanMobile;
	}

}
