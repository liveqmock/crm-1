package com.deppon.crm.module.report.shared.domain;
/**
 *单个走货质量
 */
public class ShipmentQuality {
	private String grouping;//分组
	private Integer firstQuality;//第1个月质量
	private Integer secondQuality;//第2个月质量
	private Integer thirdQuality;//第3个月质量
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
	 * @return the firstQuality
	 */
	public Integer getFirstQuality() {
		return firstQuality;
	}
	/**
	 * @param firstQuality the firstQuality to set
	 */
	public void setFirstQuality(Integer firstQuality) {
		this.firstQuality = firstQuality;
	}
	/**
	 * @return the secondQuality
	 */
	public Integer getSecondQuality() {
		return secondQuality;
	}
	/**
	 * @param secondQuality the secondQuality to set
	 */
	public void setSecondQuality(Integer secondQuality) {
		this.secondQuality = secondQuality;
	}
	/**
	 * @return the thirdQuality
	 */
	public Integer getThirdQuality() {
		return thirdQuality;
	}
	/**
	 * @param thirdQuality the thirdQuality to set
	 */
	public void setThirdQuality(Integer thirdQuality) {
		this.thirdQuality = thirdQuality;
	}
	
	
}
