package com.deppon.crm.module.interfaces.order.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.deppon.crm.module.client.common.domain.QueryCondition;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderQueryCondition extends QueryCondition {

	/**
	 * 用户名、查询起始日期、查询结束日期、支付方式、订单物流状态、订单号、运单号、收货联系人、货物名称；
	 */
	//用户名
	@XmlElement
	private String customerCode;
	//支付方式
	@XmlElement
	private String paymentType;
	//订单状态
	@XmlElement
	private List<String> orderStatus;
	//订单号
	@XmlElement
	private String orderNumber;
	//运单号
	@XmlElement
	private String waybillNumber;
	//收货人用户名
	@XmlElement
	private String onlineName;
	//收货联系人姓名（尾模糊匹配）
	@XmlElement
	private String receiveContact;
	//货物名称(全模糊匹配)
	@XmlElement
	private String goodsName;
	//起始条数
	@XmlElement
	private int startLine;
	//结束条数
	@XmlElement
	private int endLine;
	@XmlElement
	private int count;

	public String getOnlineName() {
		return onlineName;
	}

	public void setOnlineName(String onlineName) {
		this.onlineName = onlineName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public List<String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<String> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getReceiveContact() {
		return receiveContact;
	}

	public void setReceiveContact(String receiveContact) {
		this.receiveContact = receiveContact;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
