package com.deppon.crm.module.marketing.shared.domain;
/**   
 * <p>
 * Description:客户分组-客户列表详情-每日金额实体>
 * </p>
 * @title CustGroupDayAmount.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhouYuan
 * @version 2013.11.26
 */
public class CustGroupDayAmount {
	//日期天数
	private String dayValue;
	//金额
	private String amount;
	public String getDayValue() {
		return dayValue;
	}
	public void setDayValue(String dayValue) {
		this.dayValue = dayValue;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}	
}
