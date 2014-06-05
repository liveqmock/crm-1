package com.deppon.crm.module.custview.shared.domain;

/**
 * 
 * <p>
 * Description:360新增查询视图条件的封装<br />
 * </p>
 * @title SearchFastCondition.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author zouming
 * @version 0.1 2012-10-26
 */
public class SearchFastCondition {
	/*
	 * 客户编码
	 */
	private String custNumber;
	/*
	 * 联系人编码
	 */
	private String linkManNumber;
	/*
	 * 手机号码
	 */
	private String mobile;
	/*
	 * 税务登记号
	 */
	private String taxregNumber;
	/*
	 * 证件号码
	 */
	private String idCard;
	/**
	 * @return the custNumber
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber the custNumber to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @return the linkManNumber
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getLinkManNumber() {
		return linkManNumber;
	}
	/**
	 * @param linkManNumber the linkManNumber to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setLinkManNumber(String linkManNumber) {
		this.linkManNumber = linkManNumber;
	}
	/**
	 * @return the mobile
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the taxregNumber
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getTaxregNumber() {
		return taxregNumber;
	}
	/**
	 * @param taxregNumber the taxregNumber to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setTaxregNumber(String taxregNumber) {
		this.taxregNumber = taxregNumber;
	}
	/**
	 * @return the idCard
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * @param idCard the idCard to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
