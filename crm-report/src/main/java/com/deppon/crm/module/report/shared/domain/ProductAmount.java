package com.deppon.crm.module.report.shared.domain;
/**
 * descript:近三个月产品货量
 * @author 杨伟
 *
 */
public class ProductAmount {
	private String grouping;//分组那一列
	private SingleProductAmount firstMonthProductAmount;//第1个月数据
	private SingleProductAmount secondMonthProductAmount;//第2个月数据
	private SingleProductAmount thirdMonthProductAmount;//第3个月数据
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
	 * @return the firstMonthProductAmount
	 */
	public SingleProductAmount getFirstMonthProductAmount() {
		return firstMonthProductAmount;
	}
	/**
	 * @param firstMonthProductAmount the firstMonthProductAmount to set
	 */
	public void setFirstMonthProductAmount(
			SingleProductAmount firstMonthProductAmount) {
		this.firstMonthProductAmount = firstMonthProductAmount;
	}
	/**
	 * @return the secondMonthProductAmount
	 */
	public SingleProductAmount getSecondMonthProductAmount() {
		return secondMonthProductAmount;
	}
	/**
	 * @param secondMonthProductAmount the secondMonthProductAmount to set
	 */
	public void setSecondMonthProductAmount(
			SingleProductAmount secondMonthProductAmount) {
		this.secondMonthProductAmount = secondMonthProductAmount;
	}
	/**
	 * @return the thirdMonthProductAmount
	 */
	public SingleProductAmount getThirdMonthProductAmount() {
		return thirdMonthProductAmount;
	}
	/**
	 * @param thirdMonthProductAmount the thirdMonthProductAmount to set
	 */
	public void setThirdMonthProductAmount(
			SingleProductAmount thirdMonthProductAmount) {
		this.thirdMonthProductAmount = thirdMonthProductAmount;
	}
	
}
