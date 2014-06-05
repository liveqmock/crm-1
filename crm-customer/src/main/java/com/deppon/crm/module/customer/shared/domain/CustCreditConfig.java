package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @作者：andy
 * @时间：2014-3-7
 * @功能：信用较差客户配置信息
 * @修改：
 * @功能：信用较差客户配置实体类
 * @时间：2014-3-7
 * 
 */
public class CustCreditConfig extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9178123871749914880L;

	// 临欠最大欠款天数
	private int maxOverdraftDay;
	
	// 月结规定时间超过天数
	private int monthEndOvertake;
	
	// 逾期未还款月份
	private int overdueMonth;
	
	// 最早临欠单欠款天数
	private int earliestDay;
	
	// 距离规定结款日期前
	private int beforePaymentdateDay;
	
	// 最长还款周期（月结天数）到期前  几  天
	private int beforeMonthPaymentDay;
	
	// 信用额度使用大于0%
	private double usedcreditrate;
	
	// 营业部临时欠款额度使用%
	private double deptUsedrate;

	public int getMaxOverdraftDay() {
		return maxOverdraftDay;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return monthEndOvertake : return the property monthEndOvertake.
	 */
	public int getMonthEndOvertake() {
		return monthEndOvertake;
	}

	/**
	 * @param monthEndOvertake : set the property monthEndOvertake.
	 */
	public void setMonthEndOvertake(int monthEndOvertake) {
		this.monthEndOvertake = monthEndOvertake;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return overdueMonth : return the property overdueMonth.
	 */
	public int getOverdueMonth() {
		return overdueMonth;
	}

	/**
	 * @param overdueMonth : set the property overdueMonth.
	 */
	public void setOverdueMonth(int overdueMonth) {
		this.overdueMonth = overdueMonth;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return earliestDay : return the property earliestDay.
	 */
	public int getEarliestDay() {
		return earliestDay;
	}

	/**
	 * @param earliestDay : set the property earliestDay.
	 */
	public void setEarliestDay(int earliestDay) {
		this.earliestDay = earliestDay;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return beforePaymentdateDay : return the property beforePaymentdateDay.
	 */
	public int getBeforePaymentdateDay() {
		return beforePaymentdateDay;
	}

	/**
	 * @param beforePaymentdateDay : set the property beforePaymentdateDay.
	 */
	public void setBeforePaymentdateDay(int beforePaymentdateDay) {
		this.beforePaymentdateDay = beforePaymentdateDay;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return beforeMonthPaymentDay : return the property beforeMonthPaymentDay.
	 */
	public int getBeforeMonthPaymentDay() {
		return beforeMonthPaymentDay;
	}

	/**
	 * @param beforeMonthPaymentDay : set the property beforeMonthPaymentDay.
	 */
	public void setBeforeMonthPaymentDay(int beforeMonthPaymentDay) {
		this.beforeMonthPaymentDay = beforeMonthPaymentDay;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return usedcreditrate : return the property usedcreditrate.
	 */
	public double getUsedcreditrate() {
		return usedcreditrate;
	}

	/**
	 * @param usedcreditrate : set the property usedcreditrate.
	 */
	public void setUsedcreditrate(double usedcreditrate) {
		this.usedcreditrate = usedcreditrate;
	}

	/**
	 *@user pgj
	 *2014-4-11上午8:12:12
	 * @return deptUsedrate : return the property deptUsedrate.
	 */
	public double getDeptUsedrate() {
		return deptUsedrate;
	}

	/**
	 * @param deptUsedrate : set the property deptUsedrate.
	 */
	public void setDeptUsedrate(double deptUsedrate) {
		this.deptUsedrate = deptUsedrate;
	}

	/**
	 * @param maxOverdraftDay : set the property maxOverdraftDay.
	 */
	public void setMaxOverdraftDay(int maxOverdraftDay) {
		this.maxOverdraftDay = maxOverdraftDay;
	}

	}


