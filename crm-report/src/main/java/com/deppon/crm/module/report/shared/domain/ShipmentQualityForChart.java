package com.deppon.crm.module.report.shared.domain;
/**
 * Description 大客户走货质量 图表数据模式
 * @author yw
 *
 */
public class ShipmentQualityForChart {
	private Integer month;//月份
	private Integer abSignNormalCount;//异常签收次数
	private Integer recompenseCount;//理赔次数
	public ShipmentQualityForChart(){
		this.abSignNormalCount=0;
		this.recompenseCount=0;
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
	 * @return the abSignNormalCount
	 */
	public Integer getAbSignNormalCount() {
		return abSignNormalCount;
	}
	/**
	 * @param abSignNormalCount the abSignNormalCount to set
	 */
	public void setAbSignNormalCount(Integer abSignNormalCount) {
		this.abSignNormalCount = abSignNormalCount;
	}
	/**
	 * @return the recompenseCount
	 */
	public Integer getRecompenseCount() {
		return recompenseCount;
	}
	/**
	 * @param recompenseCount the recompenseCount to set
	 */
	public void setRecompenseCount(Integer recompenseCount) {
		this.recompenseCount = recompenseCount;
	}
}
