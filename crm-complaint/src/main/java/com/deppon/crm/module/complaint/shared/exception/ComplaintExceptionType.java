package com.deppon.crm.module.complaint.shared.exception;

/**
 * <p>
 * Description:工单异常枚举<br />
 * </p>
 * @title ComplaintException.java
 * @package com.deppon.crm.module.complaint.shared.exception 
 * @author
 * @version 0.1 
 */
public enum ComplaintExceptionType{
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
	 * 上报类型不相等
	 */
	REPORTTYPE_NOTEQUERL("i18n.complaint.ReportTypeNotEquel"),
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
	//工单处理短信发送失败
	COMPLAINT_MESSAGE_fail("i18n.complaint.sendMessage.fail"),
	//请接入与列表中业务模式相同的工单
	COMPLAINT_INSERT_EXPRESS_OR_NOEXPRESS("i18n.complaint.insert.error"),
	//请选择业务模式
	COMPLAINT_ERROR_CHOSE_BUSINESS_TYPE("i18n.complaint.error.choseBusinessType"),
	//重复工单编码 不存在
	REBINDNO_ISNULL("i18n.complaint.error.rebindno_isNull"),
	//基础资料添加失败
	BASEINFO_INSERT_EXCEPTION("i18n.baseInfo.insertfail"),
	//基础资料修改失败
	BASEINFO_UPDATE_EXCEPTION("i18n.baseInfo.updatefail"),
	//基础资料前台过来对象为空
	BASEINFO_IS_NULL("i18n.baseInfo.isnull"),
	//基础资料删除失败
	BASEINFO_DELETE_EXCEPTION("i18n.baseInfo.deletefail"),
	//基础资料查询失败
	BASEINFO_SEARCH_EXCEPTION("i18n.baseInfo.searchfail");
	private String errorCode;

	private ComplaintExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
