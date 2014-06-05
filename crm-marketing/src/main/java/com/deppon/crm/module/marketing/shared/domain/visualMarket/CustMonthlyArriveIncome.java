/**
 * <p>
 * Description:<br />
 * </p>
 * @title CustMonthlyArriveIncome.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
package com.deppon.crm.module.marketing.shared.domain.visualMarket;

/**
 * <p>
 * Description:固定客户月出发收入（计算口径同360视图中数据统计）<br />
 * </p>
 * @title CustMonthlyArriveIncome.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
public class CustMonthlyArriveIncome {
	//客户id
	private String custId;
	//年份
	private int year;
	//月份
	private int month;
	//到货金额
	private double arriveIncome;
	//出发金额
	private double sendIncome;
	/**
	 * @param custId the custId to get
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @param year the year to get
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @param month the month to get
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @param arriveIncome the arriveIncome to get
	 */
	public double getArriveIncome() {
		return arriveIncome;
	}
	/**
	 * @param arriveIncome the arriveIncome to set
	 */
	public void setArriveIncome(double arriveIncome) {
		this.arriveIncome = arriveIncome;
	}
	public double getSendIncome() {
		return sendIncome;
	}
	public void setSendIncome(double sendIncome) {
		this.sendIncome = sendIncome;
	}
	
}
