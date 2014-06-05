/**   
 * <p>
 * Description:营销活动
 * </p>
 * @title MarketingActivity.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author 106143
 * @version 0.1 2014-4-19上午10:49:27
 */
package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**   
 * <p>
 * Description:营销活动
 * </p>
 * @title MarketingActivity.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author 106143
 * @version 0.1 2014-4-19上午10:49:27
 */

public class MarketingActivity {
	//运单号
	private String waybillNo;
	//活动编码
	private String activityCode;
	//活动名称
	private String activityName;
	//活动类型
	private String activityType;
	//开单时间
	private Date billingDate;
	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate; 
	//优惠方式
	private String preferMethod;
	
	/**
	 *@return  waybillNo;
	 */
	public String getWaybillNo() {
		return waybillNo;
	}
	/**
	 * @param waybillNo : set the property waybillNo.
	 */
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	/**
	 *@return  activityCode;
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * @param activityCode : set the property activityCode.
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	/**
	 *@return  activityName;
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName : set the property activityName.
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 *@return  activityType;
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * @param activityType : set the property activityType.
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 *@return  billingDate;
	 */
	public Date getBillingDate() {
		return billingDate;
	}
	/**
	 * @param billingDate : set the property billingDate.
	 */
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	/**
	 *@return  startDate;
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate : set the property startDate.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 *@return  endDate;
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate : set the property endDate.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 *@return  preferMethod;
	 */
	public String getPreferMethod() {
		return preferMethod;
	}
	/**
	 * @param preferMethod : set the property preferMethod.
	 */
	public void setPreferMethod(String preferMethod) {
		this.preferMethod = preferMethod;
	}
}
