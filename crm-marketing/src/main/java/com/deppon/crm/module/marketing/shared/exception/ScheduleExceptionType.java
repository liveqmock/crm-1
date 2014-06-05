/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DevelopExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception 
 * @author Administrator
 * @version 0.1 2012-3-23
 */
package com.deppon.crm.module.marketing.shared.exception;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title DevelopExceptionType.java
 * @package com.deppon.crm.module.marketing.shared.exception
 * @author Administrator
 * @version 0.1 2012-3-23
 */

public enum ScheduleExceptionType {
	//超过日期范围
	scheduleDateNotBetweenPlanDate("i18n.developSchedule.scheduleDateNotBetweenPlanDate"),
	//客户为空
	customerIsNull("i18n.developSchedule.customerIsNull"),
	//日程不能为空
	scheduleIdConntBeNull("i18n.developSchedule.scheduleIdConntBeNull"),
	//不能更改非本人的日程
	cannotModifyNotSelfSchedule("i18n.developSchedule.cannotModifyNotSelfSchedule"),
	//开发计划已经完成或过期，不能修改日程
	cannotModifyFinOrOverSchedule("i18n.developSchedule.cannotModifyFinOrOverSchedule"),
	//不能修改已经完成的日程
	cannotModifyFinishedSchedule("i18n.developSchedule.cannotModifyFinishedSchedule"),
	//查询条件不能为空
	queryConditionCannotBeNull("i18n.developSchedule.queryConditionCannotBeNull"),
	//日期范围超过三个月
	dateRangeTooLarge("i18n.developSchedule.dateRangeTooLarge"),
	//商机日程查询不能超过六个月
	dateRangeTooLarge6("i18n.developSchedule.dateRangeTooLarge6"),
	//日程状态已完成，不可删除
	cannotDeleteFinishSchedule("i18n.developSchedule.cannotDeleteFinishSchedule"),
	//不能删除已完成计划日程
	cannotDeleteScheduleForFinishPlan("i18n.developSchedule.cannotDeleteScheduleForFinishPlan"),
	//未选择日程时间
	doNotSetScheduleTime("i18n.developSchedule.doNotSetScheduleTime"),
	// 待删除日程列表为空
	scheduleIdsCanntBeNull("i18n.developSchedule.scheduleIdsCanntBeNull"),
	//日程时间不能大于当前
	scheduleCannotLTCurrent("i18n.developSchedule.scheduleCannotLTCurrent"),
	//日程不能为空
	scheduleCannotBeNull("i18n.developSchedule.scheduleCannotBeNull"),
	//请选择部门
	mustSelectDept("i18n.developSchedule.mustSelectDept"),
	//查询时间是否都选择了或者都没选择
	queryAllSelectOrNot("i18n.developSchedule.queryAllSelectOrNot"),
	//查询开始时间不能小于创建开始时间
	qbtCannotLessThanbct("i18n.developSchedule.qbtCannotLessThanbct"),
	//查询结束时间不能大于创建开始时间
	qotCannotLargeThanoct("i18n.developSchedule.qotCannotLargeThanoct"), 
	//是重复客户，不能制定日程
	isRepeatedCust("i18n.developSchedule.isRepeatedCust"), 
	//存在商机，不能制定日程
	isExistBO("i18n.developSchedule.isExistBO");
	//异常代码
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
	private ScheduleExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

}
