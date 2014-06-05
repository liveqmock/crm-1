/**   
 * @title CustMonthSum.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description 客户发货月汇总
 * @author 潘光均
 * @update 2013-3-12 上午9:30:41
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

/**
 * The Class CustMonthSum.
 *
 * @description 客户发货月汇总(出空运和整车)
 * @author 潘光均
 * @version 0.1 2013-3-12
 * @date 2013-3-12
 */

public class CustMonthSum {
	//id
	/** The id. */
	private String id;
	//客户id
	/** The cust id. */
	private String custId;
	//月份
	/** The month. */
	private Date month;
	//金额
	/** The amount. */
	private Double amount;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the cust id.
	 *
	 * @return the cust id
	 */
	public String getCustId() {
		return custId;
	}
	
	/**
	 * Sets the cust id.
	 *
	 * @param custId the new cust id
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public Date getMonth() {
		return month;
	}
	
	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(Date month) {
		this.month = month;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
