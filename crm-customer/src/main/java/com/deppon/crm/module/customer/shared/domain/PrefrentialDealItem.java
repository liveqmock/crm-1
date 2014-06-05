/**   
 * @title PrefrentialDeal.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description 方案条目实体
 * @author 潘光均
 * @update 2013-3-8 下午4:35:48
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class PrefrentialDealItem.
 * 
 * @description 方案条目实体
 * @author 潘光均
 * @version 0.1 2013-3-8
 * @date 2013-3-8
 */

public class PrefrentialDealItem extends BaseEntity {

	/** The Constant serialVersionUID. @fields serialVersionUID */

	private static final long serialVersionUID = 7900554678018630791L;
	// 等级
	/** The degree. */
	private String degree;
	// 最小金额
	/** The min amount. */
	private double minAmount;
	// 最大金额
	/** The max amount. */
	private double maxAmount;
	// 折扣
	/** The rate. */
	private double rate;
	// 描述
	/** The description. */
	private String itemDesc;
	//优惠方案id
	/** The deal id. */
	private String dealId;
	
	/**
	 * Gets the deal id.
	 *
	 * @return the deal id
	 */
	public String getDealId() {
		return dealId;
	}

	/**
	 * Sets the deal id.
	 *
	 * @param dealId the new deal id
	 */
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	/**
	 * Gets the degree.
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * Sets the degree.
	 * 
	 * @param degree
	 *            the new degree
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * Gets the min amount.
	 * 
	 * @return the min amount
	 */
	public double getMinAmount() {
		return minAmount;
	}

	/**
	 * Sets the min amount.
	 * 
	 * @param minAmount
	 *            the new min amount
	 */
	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * Gets the max amount.
	 * 
	 * @return the max amount
	 */
	public double getMaxAmount() {
		return maxAmount;
	}

	/**
	 * Sets the max amount.
	 * 
	 * @param maxAmount
	 *            the new max amount
	 */
	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * Gets the rate.
	 * 
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 * 
	 * @param rate
	 *            the new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 *@user pgj
	 *2013-9-21下午3:20:13
	 * @return itemDesc : return the property itemDesc.
	 */
	public String getItemDesc() {
		return itemDesc;
	}

	/**
	 * @param itemDesc : set the property itemDesc.
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

}
