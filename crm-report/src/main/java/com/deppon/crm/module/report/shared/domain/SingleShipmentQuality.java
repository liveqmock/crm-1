package com.deppon.crm.module.report.shared.domain;
/**
 * Description 大客户单月走货质量
 * @author yw
 *
 */
public class SingleShipmentQuality {
	private String grouping;//分组
	private Integer month;//月份
	private Integer count;//票数
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
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}
