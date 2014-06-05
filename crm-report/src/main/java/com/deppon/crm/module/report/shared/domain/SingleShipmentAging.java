package com.deppon.crm.module.report.shared.domain;

/**
 * 单月走货时效
 */
public class SingleShipmentAging {
	private String grouping;// 分组
	private String transport;// 运输方式
	private Integer month;// 月份
	private Double time;// 时效
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
}
