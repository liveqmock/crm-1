package com.deppon.crm.module.marketing.shared.domain.activity;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:市场推广活动异常处理<br />
 * </p>
 * @title MarketActivity.java
 * @package com.deppon.crm.module.marketing.shared.domain.activity.ActivityExceptionHandle 
 * @author ZhouYuan
 * @version 0.1 2014-04-06
 */
public class ActivityExceptionHandle extends BaseEntity{
	//市场推广活动id
	private String activityId;
	//异常类型
	private String exceptionType;
	//是否已处理
	private String handle;
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	
}
