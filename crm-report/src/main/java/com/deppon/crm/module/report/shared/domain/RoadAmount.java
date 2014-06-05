package com.deppon.crm.module.report.shared.domain;
/**
 * 近三个月线路货量
 * @author 杨伟
 *
 */
public class RoadAmount {
	private String grouping;//分组那一列
	private SingleRoadAmount firstMonthRoadAmount;//第1个月数据
	private SingleRoadAmount secondMonthRoadAmount;//第2个月数据
	private SingleRoadAmount thirdMonthRoadAmount;//第3个月数据
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
	 * @return the firstMonthRoadAmount
	 */
	public SingleRoadAmount getFirstMonthRoadAmount() {
		return firstMonthRoadAmount;
	}
	/**
	 * @param firstMonthRoadAmount the firstMonthRoadAmount to set
	 */
	public void setFirstMonthRoadAmount(SingleRoadAmount firstMonthRoadAmount) {
		this.firstMonthRoadAmount = firstMonthRoadAmount;
	}
	/**
	 * @return the secondMonthRoadAmount
	 */
	public SingleRoadAmount getSecondMonthRoadAmount() {
		return secondMonthRoadAmount;
	}
	/**
	 * @param secondMonthRoadAmount the secondMonthRoadAmount to set
	 */
	public void setSecondMonthRoadAmount(SingleRoadAmount secondMonthRoadAmount) {
		this.secondMonthRoadAmount = secondMonthRoadAmount;
	}
	/**
	 * @return the thirdMonthRoadAmount
	 */
	public SingleRoadAmount getThirdMonthRoadAmount() {
		return thirdMonthRoadAmount;
	}
	/**
	 * @param thirdMonthRoadAmount the thirdMonthRoadAmount to set
	 */
	public void setThirdMonthRoadAmount(SingleRoadAmount thirdMonthRoadAmount) {
		this.thirdMonthRoadAmount = thirdMonthRoadAmount;
	}
}
