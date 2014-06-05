package com.deppon.crm.module.report.shared.domain;

import java.util.Date;
/**
 * 大客户走货报表
 * @author yw
 *
 */
public class KeycustDelivery {
	//主键
	private String id;
	//客户编码
	private String customer;
	//统计时间 报表生成数据前一月第一天
	private Date analysebyDate;
	//发货重量
	private Double weight;
	//票数
	private Integer deliveryCount;
	//金额
	private Double amount;
	//重量占比
	private Double weightRate;
	//时效 同一客户每条线路不同运输方式的平均时效
	private Double time;
	//出发城市编码
	private String deliveryCityCode;
	//到达城市编码
	private String receiverCityCode;
	//运输方式编码
	private String transport;
	//运输方式名称
	private String transportName;
	//创建时间
	private Date createTime;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the analysebyDate
	 */
	public Date getAnalysebyDate() {
		return analysebyDate;
	}
	/**
	 * @param analysebyDate the analysebyDate to set
	 */
	public void setAnalysebyDate(Date analysebyDate) {
		this.analysebyDate = analysebyDate;
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
	 * @return the deliveryCount
	 */
	public Integer getDeliveryCount() {
		return deliveryCount;
	}
	/**
	 * @param deliveryCount the deliveryCount to set
	 */
	public void setDeliveryCount(Integer deliveryCount) {
		this.deliveryCount = deliveryCount;
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
	 * @return the time
	 */
	public Double getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Double time) {
		this.time = time;
	}
	/**
	 * @return the deliveryCityCode
	 */
	public String getDeliveryCityCode() {
		return deliveryCityCode;
	}
	/**
	 * @param deliveryCityCode the deliveryCityCode to set
	 */
	public void setDeliveryCityCode(String deliveryCityCode) {
		this.deliveryCityCode = deliveryCityCode;
	}
	/**
	 * @return the receiverCityCode
	 */
	public String getReceiverCityCode() {
		return receiverCityCode;
	}
	/**
	 * @param receiverCityCode the receiverCityCode to set
	 */
	public void setReceiverCityCode(String receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}
	/**
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}
	/**
	 * @param transport the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}
	/**
	 * @return the transportName
	 */
	public String getTransportName() {
		return transportName;
	}
	/**
	 * @param transportName the transportName to set
	 */
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
