package com.deppon.crm.module.client.order.domain;

import java.util.Date;

/**
 * 此日志表记录用触发器生成
 * @author davidcun @2012-4-16
 */
public class OrderStatusLog {
	
	private int id;
	//订单号(渠道单号)
	private String orderNumber;
	//用户标识：下单人
	private String userId;
	//订单状态
	private String orderStatus;
	//订单来源
	private String orderSource;
	//日志状态：待处理 "ready"; 已处理"processed"
	private String logStatus;

	//修改时间
	private Date updateTime;
	//备注，可能是一些操作失败的原因
	private String remark;
	
	private int sendCount;
	
	private long TransactionNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getTransactionNumber() {
		return TransactionNumber;
	}
	public void setTransactionNumber(long transactionNumber) {
		TransactionNumber = transactionNumber;
	}
	@Override
	public String toString() {
		return "OrderStatusLog [id=" + id + ", orderNumber=" + orderNumber
				+ ", userId=" + userId + ", orderStatus=" + orderStatus
				+ ", orderSource=" + orderSource + ", logStatus=" + logStatus
				+ ", updateTime=" + updateTime + ", remark=" + remark
				+ ", TransactionNumber=" + TransactionNumber + "]";
	}
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
}