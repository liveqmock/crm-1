package com.deppon.crm.module.report.shared.domain;

import java.util.Date;

/**
 * descript:单月走货量
 * @author 杨伟
 *
 */
public class ShipmentAmount {
	private Integer month;//月份
	private Integer deliverycount;//票数
	private Double weight;//重量
	private Double amount;//金额
	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * @return the deliverycount
	 */
	public Integer getDeliverycount() {
		return deliverycount;
	}
	/**
	 * @param deliverycount the deliverycount to set
	 */
	public void setDeliverycount(Integer deliverycount) {
		this.deliverycount = deliverycount;
	}
	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
