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
 * Description:客户分组-客户列表-按日分组发货金额,从1号到当天/>
 * </p>
 * @title CustomerGroupMonth.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 12, 2012
 */

public class CustomerGroupDay {
	// 客户编码
	private String customerNumber;
	// 当天
	private String day;
	// 当天总金额
	private String amount;
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
	 * @return day : return the property day.
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day : set the property day.
	 */
	public void setDay(String day) {
		this.day = day;
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
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//返回固定格式
		return "CustomerGroupDay [customerNumber=" + customerNumber + ", day="
				+ day + ", amount=" + amount + "]";
	}
}
