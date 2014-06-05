package com.deppon.crm.module.recompense.shared.exception;

/**
 * @description 理赔异常类型
 * @author 黄展明
 * @version 0.1 2012-3-30
 * @date 2012-3-30
 */

public enum RecompenseExceptionType {
	// 理赔上报数据不全
	RECOMPENSE_REPORT_REQUIRE_ERROR(
			"i18n.recompense.validate.report.requireerror"),
	// 理赔冲账列表为空
	RECOMPENSE_BALANCE_LIST_NULL("i18n.recompense.validate.balance.listnull"),
	// 理赔冲账记录数据不正确
	RECOMPENSE_BALANCE_ITEM_UNASSGIN(
			"i18n.recompense.validate.balance.itemunassign"),
	// 冲账总金额与实际的不符
	RECOMPENSE_BALANCE_TOTAL_UNASSGIN(
			"i18n.recompense.validate.balance.totalunassign"),
	// 付款单为空
	RECOMPENSE_PAYMENT_NULL_ERROR("i18n.recompense.validate.payment.nullerror"),
	// 付款金额不正确
	RECOMPENSE_PAYMENT_AMOUNT_ERROR(
			"i18n.recompense.validate.payment.amounterror"),
	// 付款必填项为没填全
	RECOMPENSE_PAYMENT_REQUIRE_ERROR(
			"i18n.recompense.validate.payment.reguireerror"),
	// 理赔单状态不对
	RECOMPENSE_STATUS_NOTMATCH_ERROR("i18n.recompense.status.notmatcherror"),
	// 理赔单重复
	RECOMPENSE_RECORD_EXIST_ERROR("i18n.recompense.record.existerror"),
	// 理赔单未开单差错不存在
	RECOMPENSE_UNBILLED_MISTAKE_NOEXIST_ERROR(
			"i18n.recompense.unbilled.mistake.notexisterror"),
	// 运单不存在
	RECOMPENSE_NOEXIST_WAYBILL_ERROR("i18n.recompense.notexist.waybill.error"),
	// 理赔单异常签收和丢货差错不存在
	RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR(
			"i18n.recompense.other.mistake.notexisterror"),
	// 理赔单异常签收不存在
	RECOMPENSE_ABNORMAL_MISTAKE_NOEXIST_ERROR(
			"i18n.recompense.abnormal.mistake.notexisterror"),
	// 理赔单丢货差错不存在
	RECOMPENSE_GOODSLOST_MISTAKE_NOEXIST_ERROR(
			"i18n.recompense.goodslost.mistake.notexisterror"),

	// 用户未登录
	RECOMPENSE_USER_NOTLOGIN("i18n.recompense.err.userNotLogin"),

	// 理赔调用外部接口异常
	RECOMPENSE_INTERFACE("i18n.recompense.err.interface"),
	// 在线理赔申请不是待受理状态
	RECOMPENSE_STATUS_WAIT_ACCEPT("i18n.recompense.err.waitaccept"),
	// 无工作流
	RECOMPENSE_OA_WORKFLOW_NULL("i18n.recompense.oaworkflow.null"),
	// 无理赔单
	RECOMPENSE_APPLICATION_NULL("i18n.recompense.application.null"),
	// 在线理赔不能操作
	ONLINEAPPLY_NOTOPERATION("i18n.onlineapply.notoperation"), ONLINEAPPLY_ACCEPT_DEPT_ERROR(
			"i18n.onlineapply.aceept.depterror"),
	// 理赔状态不为审批完成或付款失败
	PAYMENT_STATUS_ERROR("i18n.payment.statuserror"),
	// 请确认费用报销系统中已添加收银员账号信息。如确认已添加，请重新选择客户领款方式为现金。
	PAYMENT_NULL_CASHIER_ACCOUNT("i18n.payment.nullcashieraccount"),
	// 理赔类型为未开单理赔时，客户领款方式仅可选择汇款或现金。
	UNBILLED_PAYMENTMODE_ERROR("i18n.payment.unbillpaymentmodeerror"),
	// 对于无唯一识别客户编码的理赔，客户领款方式仅可选择汇款或现金。
	CUSTOMER_NULL_PAYMENTMODE_ERROR("i18n.payment.unbillpaymentmodeerror"), NOT_INSERT_CUSTOMER_ACCOUNT(
			"i18n.payment.notinsertcustomeraccount"), 
			NOT_NULL_BANK_PROVINCE_CITY(
			"i18n.payment.notnullbankprovincecity"),//总行省市都不能为空
			
			//理赔付款失败
			SUBMITPAYMENTFAIL("i18n.payment.submitpaymentfail"), //理赔付款申请失败
			NOTFULLBANKINFO("i18n.payment.notfullbankinfo"),  //收银员帐号信息缺失
			ERRORCUSTOMBANKINFO("i18n.payment.errorcustomerbankinfo"), //客户省市银行支行帐号信息有误！
			ONLINEAPPLY_MONITORING_ACCEPTTIME_ERROR("i18n.onlineapply.acceptTime.error"),//受理时间有问题
			ONLINEAPPLY_ACCOUNT_NOT_ALL_NUM("银行账号应该全为数字"),
			ONLINEAPPLY_AMOUNT_TOO_BIG("在线理赔金额不能大于1000");
	private String errorCode;

	private RecompenseExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(RecompenseExceptionType type) {
		switch (type) {
		case RECOMPENSE_REPORT_REQUIRE_ERROR:
			return RECOMPENSE_REPORT_REQUIRE_ERROR.getErrorCode();
		case RECOMPENSE_BALANCE_LIST_NULL:
			return RECOMPENSE_BALANCE_LIST_NULL.getErrorCode();
		case RECOMPENSE_BALANCE_ITEM_UNASSGIN:
			return RECOMPENSE_BALANCE_ITEM_UNASSGIN.getErrorCode();
		case RECOMPENSE_BALANCE_TOTAL_UNASSGIN:
			return RECOMPENSE_BALANCE_TOTAL_UNASSGIN.getErrorCode();
		case RECOMPENSE_PAYMENT_NULL_ERROR:
			return RECOMPENSE_PAYMENT_NULL_ERROR.getErrorCode();
		case RECOMPENSE_PAYMENT_AMOUNT_ERROR:
			return RECOMPENSE_PAYMENT_AMOUNT_ERROR.getErrorCode();
		case RECOMPENSE_PAYMENT_REQUIRE_ERROR:
			return RECOMPENSE_PAYMENT_REQUIRE_ERROR.getErrorCode();
		case RECOMPENSE_STATUS_NOTMATCH_ERROR:
			return RECOMPENSE_STATUS_NOTMATCH_ERROR.getErrorCode();
		case RECOMPENSE_RECORD_EXIST_ERROR:
			return RECOMPENSE_RECORD_EXIST_ERROR.getErrorCode();
		case RECOMPENSE_UNBILLED_MISTAKE_NOEXIST_ERROR:
			return RECOMPENSE_UNBILLED_MISTAKE_NOEXIST_ERROR.getErrorCode();
		case RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR:
			return RECOMPENSE_OTHER_MISTAKE_NOEXIST_ERROR.getErrorCode();
		case RECOMPENSE_INTERFACE:
			return RECOMPENSE_INTERFACE.getErrorCode();
		case ONLINEAPPLY_NOTOPERATION:
			return ONLINEAPPLY_NOTOPERATION.getErrorCode();
		case RECOMPENSE_OA_WORKFLOW_NULL:
			return RECOMPENSE_OA_WORKFLOW_NULL.getErrorCode();
		case RECOMPENSE_APPLICATION_NULL:
			return RECOMPENSE_APPLICATION_NULL.getErrorCode();
		case RECOMPENSE_STATUS_WAIT_ACCEPT:
			return RECOMPENSE_STATUS_WAIT_ACCEPT.getErrorCode();
		case RECOMPENSE_NOEXIST_WAYBILL_ERROR:
			return RECOMPENSE_NOEXIST_WAYBILL_ERROR.getErrorCode();
		case RECOMPENSE_ABNORMAL_MISTAKE_NOEXIST_ERROR:
			return RECOMPENSE_ABNORMAL_MISTAKE_NOEXIST_ERROR.getErrorCode();
		case PAYMENT_STATUS_ERROR:
			return PAYMENT_STATUS_ERROR.getErrorCode();
		case PAYMENT_NULL_CASHIER_ACCOUNT:
			return PAYMENT_NULL_CASHIER_ACCOUNT.getErrorCode();
		case UNBILLED_PAYMENTMODE_ERROR:
			return UNBILLED_PAYMENTMODE_ERROR.getErrorCode();
		case CUSTOMER_NULL_PAYMENTMODE_ERROR:
			return CUSTOMER_NULL_PAYMENTMODE_ERROR.getErrorCode();
		case NOT_INSERT_CUSTOMER_ACCOUNT:
			return NOT_INSERT_CUSTOMER_ACCOUNT.getErrorCode();
		case NOT_NULL_BANK_PROVINCE_CITY:
			return NOT_NULL_BANK_PROVINCE_CITY.getErrorCode();
		case ONLINEAPPLY_ACCEPT_DEPT_ERROR:
			return ONLINEAPPLY_ACCEPT_DEPT_ERROR.getErrorCode();
		case RECOMPENSE_GOODSLOST_MISTAKE_NOEXIST_ERROR:
			return RECOMPENSE_GOODSLOST_MISTAKE_NOEXIST_ERROR.getErrorCode();
		case SUBMITPAYMENTFAIL:
			return SUBMITPAYMENTFAIL.getErrorCode();
		case NOTFULLBANKINFO:
			return NOTFULLBANKINFO.getErrorCode();
		case ERRORCUSTOMBANKINFO:
			return ERRORCUSTOMBANKINFO.getErrorCode();
		case ONLINEAPPLY_MONITORING_ACCEPTTIME_ERROR:
			return ONLINEAPPLY_MONITORING_ACCEPTTIME_ERROR.getErrorCode();
		case ONLINEAPPLY_ACCOUNT_NOT_ALL_NUM:
			return ERRORCUSTOMBANKINFO.getErrorCode();
		case ONLINEAPPLY_AMOUNT_TOO_BIG:
			return ONLINEAPPLY_MONITORING_ACCEPTTIME_ERROR.getErrorCode();
		default:
			return RECOMPENSE_USER_NOTLOGIN.getErrorCode();
		}
	}
}