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

public enum ReturnVisitExceptionType {
	// 回访信息--异常信息枚举
	createReturnVisitFail("i18n.developPlan.createReturnVisitFail"),
	//跟踪数据错误
	TrackDateError("i18n.developPlan.TrackDateError"),
	//初始化页面失败
	initCreatePageFail("i18n.developPlan.initCreatePageFail"),
	// 未选中回访类型
	notTraceWay("i18n.developPlan.notTraceWay"),
	//回访录入查询-主题不能为空
	themeCannotBeNull("i18n.returnVisit.themeCannotBeNull"),
	//回访录入查询-查询条件不能全为空
	queryConditionIsNull("i18n.returnVisit.queryConditionIsNull"),
	//回访录入查询-结束时间不能早于开始时间，查询时间差不能超过3个月
	beginEndTimeOffset("i18n.returnVisit.beginEndTimeOffset"),
	// 回访录入验证：已回访过的日程无需再回访。
	dontRepeatRV("i18n.returnVisit.dontRepeatRV"),
	// 回访数据异常，无法正常执行。
	returnVisitDataError("i18n.returnVisit.returnVisitDataError"),
	// 日程过期，不允许回访。
	returnVisitSceStatusError("i18n.returnVisit.returnVisitSceStatusError"),
	// 未设置日程-无法回访
	scheduleIsNull("i18n.returnVisit.scheduleIsNull"),
	// 无权回访，确认是否为日程执行人
	noPermissions("i18n.returnVisit.noPermissions"),
	//必须选择部门
	mustSelectDept("i18n.returnVisit.mustSelectDept"),
	//疑似重复客户
	RepeatCust("i18n.returnVisit.RepeatCust"),
	//存在未关闭商机
	existUnClosedBO("i18n.returnVisit.existUnClosedBO")
	;
	/**
	 * 异常代码
	 */
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
	private ReturnVisitExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
