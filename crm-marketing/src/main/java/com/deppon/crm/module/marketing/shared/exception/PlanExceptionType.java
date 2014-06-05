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

public enum PlanExceptionType {
	//已存在主题异常
	existsPlanTheme("i18n.developPlan.existsPlanTheme"),
	//计划创建失败异常
	createPlanFail("i18n.developPlan.createPlanFail"),
	//保存客户计划异常
	createPlanCustFail("i18n.developPlan.savePlanCustFail"),
	//创建日程异常
	createScheduleFail("i18n.developPlan.createScheduleFail"),
	//客户list为空异常
	customerListCannotBeNull("i18n.developPlan.customerListCannotBeNull"),
	// 日程已制定，无法删除相关计划
	errorPlaneState("i18n.developPlan.errorPlaneState"),
	//日程状态不全是已指派
	notAllOfStatusIsAssigned("i18n.developPlan.notAllOfStatusIsAssigned"),
	//登陆人和计划制定人不一致
	isNotSelfDevelopPlan("i18n.developPlan.isNotSelfDevelopPlan"),
	// 不可修改固定计划，仅能调整执行人
	canModifyFixedPlan("i18n.developPlan.canModifyFixedPlan"),
	//开发计划不能为空
	planCannotBeNull("i18n.developPlan.planCannotBeNull"),
	// 日程时间不能为空
	scheduleCannotBeNull("i18n.developPlan.scheduleCannotBeNull"),
	
	//id不能为空
	planIdCannotBeNull("i18n.developPlan.planCannotBeNull"),
	//开始日期不能等于结束日期
	startDateBGEndDate("i18n.developPlan.startDateBGEndDate"),
	//开始日期必须晚于今天
	startCnnotMoreThanCurrent("i18n.developPlan.startCnnotMoreThanCurrent"),
	//查询条件不能全为空
	queryConditionCannotBeNull("i18n.developPlan.queryConditionCannotBeNull"),
	//请选择记录
	doNotSelectItem("i18n.plan.doNotSelectItem"),
	// 客户查询时间不能大于1年
	dateRangeTooLarge("i18n.developPlan.dateRangeTooLarge"),
	// 计划时限范围不能超过x月
	planTimeTooLarge("i18n.developPlan.planTimeTooLarge"),	
	// 无法操作已完成/已过期的相关计划。
	canNotUpdDelPlan("i18n.developPlan.canNotUpdDelPlan"),
	// 无法操作已完成/已过期的相关计划。
	canNotUpdExecutingPlan("i18n.developPlan.canNotUpdExecutingPlan"),
	//无法修改已过期
	canNotUpdOverduePlan("i18n.developPlan.canNotUpdOverduePlan"),
	// 执行部门不能为空
	execDeptIsNull("i18n.developPlan.execDeptIsNull"),
	//固定计划未修改执行人
	notModifyExePersonWithFixPlan("i18n.developPlan.notModifyExePersonWithFixPlan"),
	//只有部门经理才能更改固定计划执行人
	onlyDeptManagerUpdateFixPlan("i18n.developPlan.onlyDeptManagerUpdateFixPlan"),
	// 固定计划——联系人已被修改
	canNotChangeContact("i18n.developPlan.canNotChangeContact"),
	// 计划时限范围不能超过3月
	planTimeTooLargeThreeMonth("i18n.developSchedule.dateRangeTooLarge"),
	//您所查看营业部数量超过800个，必须选择一个营业部才能使用查询功能!
	deptNumLimit("i18n.developPlan.deptLimit"),
	//不能删除非本人制定的日程
	cannotDeleteOtherPeoplePlan("i18n.developPlan.cannotDeleteOtherPeoplePlan"),
	doNotExistPlan("i18n.developPlan.doNotExistPlan")
	;
	
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
	private PlanExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
}
