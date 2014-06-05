/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupMonth.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 12, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description:客户分组-客户列表-按月分组发货金额,当月,上月和上两月/>
 * </p>
 * @title CustomerGroupMonth.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 12, 2012
 */

public class CustomerGroupMonth {
	// 客户编码
	private String customerNumber;
	// 年份
	private String year;
	// 月份
	private String month;
	// 当月总金额
	private String amount;
	// 当月发货次数
	private String times;
	/**
	 * @return customerNumber : return the property customerNumber.
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * @param customerNumber : set the property customerNumber.
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	/**
	 * @return year : return the property year.
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year : set the property year.
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return month : return the property month.
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month : set the property month.
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return amount : return the property amount.
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount : set the property amount.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return times : return the property times.
	 */
	public String getTimes() {
		return times;
	}
	/**
	 * @param times : set the property times.
	 */
	public void setTimes(String times) {
		this.times = times;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//返回固定格式
		return "CustomerGroupMonth [customerNumber=" + customerNumber
				+ ", year=" + year + ", month=" + month + ", amount=" + amount
				+ ", times=" + times + "]";
	}
	
	
	
}
