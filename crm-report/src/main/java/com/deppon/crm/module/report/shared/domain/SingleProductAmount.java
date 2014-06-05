package com.deppon.crm.module.report.shared.domain;

import java.util.Date;

/**
 * 单月产品货量
 */
public class SingleProductAmount {
	private int month;// 月份
	private Double deliverycount;// 单月票数
	private Double weight;// 单月重量
	private Double amount;// 单月金额
	private Double weightRate;// 单月重量占比
	private String grouping;// 产品名称
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the deliverycount
	 */
	public Double getDeliverycount() {
		return deliverycount;
	}
	/**
	 * @param deliverycount the deliverycount to set
	 */
	public void setDeliverycount(Double deliverycount) {
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
	/**
	 * @return the weightRate
	 */
	public Double getWeightRate() {
		return weightRate;
	}
	/**
	 * @param weightRate the weightRate to set
	 */
	public void setWeightRate(Double weightRate) {
		this.weightRate = weightRate;
	}
	/**
	 * @return the grouping
	 */
	public String getGrouping() {
		return grouping;
	}
	/**
	 * @param grouping the grouping to set
	 */
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	
}
