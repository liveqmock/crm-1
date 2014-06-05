package com.deppon.crm.module.duty.shared.exception;


public enum DutyExceptionType{
	/**
	 * 工单号不相等
	 */
	BILL_NOTEQUERL("i18n.complaint.billNumberNotEqeal"),
	/**
	 * 工单不存在
	 */
	COMPLAINT_NOT_EXIST("i18n.complaint.billNotExist"),
	/**
	 * 任务部门处理结果不存在
	 */
	RESULT_NOT_EXIST("Task does not exist !"),
	
	/**
	 * 任务部门已做过延时申请
	 */
	RESULT_DUPLICATE_DELAY("i18n.complaint.duplicateDelay"),
	
	/**
	 * 不可反馈登记，不在可执行状态
	 */
	TASK_CAN_NOT_BE_FEEDBACKABLE("Task can not be operated,in a wrong process status !"),
	
	/**
	 * 不可退回处理人，不在可执行状态
	 */
	TASK_CAN_NOT_BE_RETURNED("Task can not be returned,in a wrong process status !"),
	
	/**
	 * 不可申请延时，不在可执行状态
	 */
	TASK_CAN_NOT_APPLY_DELAY("Task can not apply delay,in a wrong process status !"),
	
	/**
	 * 上报类型不能为空
	 */
	REPORTTYPE_CANNOTNULL("i18n.complaint.ReportTypeCanNotNull"),
	/**
	 * 查询责任反馈记录时，责任ID为空
	 */
	DUTY_FEEDBACK_DUTYID_CAN_NOT_NULL("i18n.Duty.DutyFeedbackRecord.DutyIdIsNull"),
	/**
	 * 查询责任反馈记录时，责任划分结果ID为空
	 */
	DUTY_FEEDBACK_DETAILID_CAN_NOT_NULL("i18n.Duty.DutyApproval.DetailIdIsNull"),
	
	/**
	 * 查询责任反馈记录时，责任反馈ID为空
	 */
	DUTY_FEEDBACK_FEEDBACKID_CAN_NOT_NULL("i18n.Duty.DutyApproval.FeedbackIdIsNull"),
	/**
	 * 当责任状态不是责任待审批时，不允许统计员进行审批
	 */
	DUTY_APPROVAL_STATISTICS_CAN_NOT_DO("i18n.Duty.DutyApproval.StatusCanNotApproval"),
	/**
	 * 统计员审批出现异常，无法判断审批类型是同意还是退回
	 */
	DUTY_APPROVAL_STATISTICS_CAN_NOT_APPROVAL("i18n.Duty.DutyApproval.StatCanNotApproval"),
	/**
	 * 操作不合法，只有大区统计员才可进行此操作
	 */
	DUTY_APPROVAL_ROLE_NOT_STATISTICS("i18n.Duty.DutyApproval.RoleIsNotStatistics"),
	/**
	 * 操作不合法，当前登录用户无权审批
	 */
	DUTY_APPROVAL_ROLE_CAN_NOT_DO("i18n.Duty.DutyApproval.CanNotApproval"),
	/**
	 * 操作不合法，只有质检员才可进行此操作
	 */
	DUTY_APPROVAL_ROLE_NOT_CALLER("i18n.Duty.DutyApproval.RoleIsNotCaller"),
	/**
	 * 上报类型不相等
	 */
	REPORTTYPE_NOTEQUERL("i18n.complaint.ReportTypeNotEquel"),
	/**
	 * 审批意见不能为空
	 */
	APPROVAL_OPINION_IS_NULL("i18n.Duty.DutyApproval.approvalOpinionIsNull"),
	/**
	 * 审批依据不能为空
	 */
	APPROVAL_ACCORDING_IS_NULL("i18n.Duty.DutyApproval.approvalAccordingIsNull"),
	//当前式单状态不能完成处理
	currentComplaintCannotBeFinishProccess("i18n.complaint.CurrentComplaintCannotBeFinishProccess"),
	currentComplaintCannotBeSubmitApproval("i18n.complaint.CurrentComplaintCannotBeSubmitApproval"),
	cannotMoreTimeApproval("i18n.complaint.connotMoreTimeApproval"),
	connotReturnSubmitor("i18n.complaint.connotReturnSubmitor"),
	//反馈状态为空或值不正确
	feedBackIsNull("i18n.complaint.feedBackIsNull"),
	//两个工单不能绑定
	connotComplainBinding("i18n.complaint.canotBinding"),
	//反馈已解决工单不能退回
	feedbackResolvedComplainConnotReturn("i18n.complaint.feedbackResolved_Complain_Connot_Return"),
	
	/**
	 * 工单接入超时
	 */
	COMPLAINT_ACCESS_EXPIRED("i18n.complaint.access.expired"),
	/**
	 * 工单状态：反馈已解决
	 */
	CANT_OVERTIME_FEEDBACK_RESOLVED("i18n.complaint.overTime.resolved"),
	
	/**
	 * 基础资料-业务项名称为空
	 */
	basicInfoBusItemCanNotNull("i18n.basicinfo.busitem.canotBeNull"),
	basicInfoReprotTypeCanNotNull("i18n.basicinfo.reporttype.illegal"),
	
	basicBusScopeVOBusItemCanNotNull("i18n.basicInfo.basicbasicbusscopevo.busitem.canotBeNull"),
	//新增业务项时业务项列表不能为空
	basicBusItemsCanotNull("i18n.basicInfo.basicitems.canotNull"),
	//新增的业务项不能为空
	newBasicBusItemCanotNull("i18n.basicInfo.newBasicBusItem.cannotNull"),
	//新增业务项名称不能为空
	newBasicBusItemNameCanotNull("i18n.basicInfo.newBasicBusItem.cannotNull"),
	//新增业务项类型不合法
	newBasicBusItemTypeIllegal("i18n.basicInfo.newBasicBusItemType.illegal"),
	//业务范围名称不能为空
	basicBusScopeVOBusScopeNameCanNotNull("i18n.basicInfo.basicBusScopeName.cannotNull"),
	//删除业务项时业务项ID列表不能为空
	newBasicBusItemidsIsNull("i18n.basicInfo.BasicBusItemids.cannotNull"),
	//业务类型不能为空
	newBasicBusTypeIsNull("i18n.basicInfo.BasicBusType.busType.cannotNull"),
	//业务类型名称不能为空
	newBasicBusTypeNameIsNull("i18n.basicInfo.BasicBusType.busTypeName.cannotNull"),
	//处理语言不能为空
	newBasicBusTypeDealLanIsNull("i18n.basicInfo.BasicBusType.busTypeDealLan.cannotNull"),
	//反馈时限不合法
	newBasicBusTypeFeedbackIsIllegal("i18n.basicInfo.BasicBusType.busTypeFeedback.illegal"),
	//处理时限不合法
	newBasicBusTypeProcIsIllegal("i18n.basicInfo.BasicBusType.busTypeProc.illegal"),
	//业务项ID不能为空
	basicInfoBusItemIdCanNotNull("i18n.basicInfo.BasicBusItemId.cannotNull"), 
	// 工单责任查询条件为空
	queryDutyListConditionIsNull("i18n.Duty.QueryDutyListCondition.IsNull"), 
	// 上报时间范围大于31天
	queryDutyListReportTimeIsLong("i18n.Duty.queryDutyListReportTime.IsLong"), 
	// 反馈时间范围大于31天
	queryDutyListFeedbackTimeIsLong("i18n.Duty.queryDutyListFeedbackTime.IsLong"),
	// 责任划分时间范围大于31天
	queryDutyListAppDutyTimeIsLong("i18n.Duty.queryDutyListAppDutyTime.IsLong"), 
	// 请选择查询时间段
	queryDutyListArrayConditionIsNull("i18n.Duty.queryDutyListConditionIsNull.IsNull"), 
	// 请选择开始和结束时间
	queryDutyListTimeIsLose("i18n.Duty.queryDutyListTime.IsLose"), 
	// 没有满足查询条件的结果
	queryDutyListResultIsNull("i18n.Duty.queryDutyListResult.IsNull"),
	//没有未被接入的工单
	searchDutyReceiveIsNull("i18n.Duty.searchDutyReceiveIsNull.IsNull"),
	//接入工单数超过最大允许接入数
	searchMaxDutyReceiveBeyondMaxReceive("i18n.Duty.searchMaxDutyReceive.beyondMaxReceive"),
	//该用户没有权限
	searchDutyReceiveReportTypeIsIllegal("i18n.Duty.searchDutyReceiveReportType.isIllegal"),
	//责任id为空
	dutyIdIsNull("i18n.Duty.searchDutyResults.dutyIdIsNull"),
	//工单编号为空
	complaintidIsNull("i18n.Duty.searchDutyResults.complaintidIsNull"),
	//有相同的划分结果
	haveSameDutyResult("i18n.Duty.searchDutyResults.haveSameDutyResult"),
	//没有可以认领的划分结果
	dutyClaimNoOne("i18n.Duty.dutyClaim.dutyClaimNoOne"),
	//没有可以反馈的划分结果
	dutyFeedBackNoOne("i18n.Duty.dutyClaim.dutyFeedBackNoOne"),
	//已超期
	hadFeedBackExtended("i18n.Duty.hadFeedBackExtended"),
	//该部门或个人无权反馈，请核实
	duty_not_FeedBack("i18n.Duty.duty_not_FeedBack"),
	//查询的责任列表为空
	searchDutyListIsNull("i18n.Duty.dutyList.isNull"),
	//反馈已被处理
	feedbackAlreadyProcessed("i18n.Duty.dutyList.feedbackAlreadyProcessed"),
	//添加特殊部门为空
	DUTYDEPTISNULL("i18n.dutydept.deptisnull"),
	//该责任部门已经存在
	DUTYDEPTISHAVE("i18n.dutydept.deptishave"),
	//所选部门或个人非本事业部，请重新选择。
	isNotSameBusinessDepartment("i18n.duty.result.isNotSameBusinessDepartment"),
	//工单责任部门集合是否为空
	DUTYDEPTLISTISNULL("i18n.dutydept.dutydeptisnull"),
	//责任已提交
	DUTY_SUBMIT("i18n.duty.exception.submit"),
	//确认无责
	DUTY_SURVEYRESULT_NORESPONSIBILITY("i18n.duty.exception.surveyresult.noresponsibility");
	
	private String errorCode;

	private DutyExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
