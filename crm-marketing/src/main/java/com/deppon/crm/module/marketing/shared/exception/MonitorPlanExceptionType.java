/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.shared.exception;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ReturnVisitExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */

public enum MonitorPlanExceptionType {
	// 监控信息--异常信息枚举
	searchConditionIsEmpty("i18n.MonitorPlan.searchConditionIsEmpty"),
	// 查询时间差不能超过3个月
	beginEndTimeOffset("i18n.MonitorPlan.beginEndTimeOffset"),
	// 待查询的详情部门不能为空
	searchDetailConditionIsEmpty("i18n.MonitorPlan.searchDetailConditionIsEmpty");
		
	private String errCode;
	/**
	 * @return errCode : return the property errCode.
	 */
	public String getErrCode() {
		return errCode;
	}
	/**
	 * @param errCode : set the property errCode.
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * 
	 * constructer
	 * @param errorCode
	 */
	private MonitorPlanExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
