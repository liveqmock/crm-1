package com.deppon.crm.module.servicerecovery.shared.exception;

import com.deppon.crm.module.recompense.shared.exception.RecompenseExceptionType;

/**
 * Description:服务补救异常类型 ServiceRecoveryExceptionType.java Create on 2012-10-29
 * 上午10:15:50
 * 
 * @author 华龙
 * @version 1.0
 */

public enum ServiceRecoveryExceptionType {
	// 服务补救异常(test，未在i18n添加)
	SERVICE_RECOVERY_EXCEPTION("i18n.servicerecovery.servicerecoveryexception"), WAYBILL_NOT_SIGNIN_EXCEPTION(
			"i18n.servicerecovery.waybillnotsigninexception"), // 该单号尚未签收
	WAYBILL_NOT_EXIST_EXCEPTION("i18n.servicerecovery.waybillnotexistexception"), // 你好，你输入的运单号不存在，请从新输入
	WAYBILL_OUT_OF_APPLYDATE_EXCEPTION(
			"i18n.servicerecovery.waybilloutofappdateexception"), // 该运单已超出服务补救申请期限，不允许申请
	WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION(
			"i18n.servicerecovery.appledservicerecoveryexception"), // 该运单已申请过服务补救，请勿重复申请
	WAYBILL_APPLED_RECOMPENSE_EXCPTION(
			"i18n.servicerecovery.appledrecompenseexception"), // 该运单已申请过理赔，不允许服务补救
	WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION(
			"i18n.servicerecovery.appleddelayaccidentexception"), // 该运费已上报过时效延误差错，不允许服务补救
	WAYBILL_APPLED_BACKFREIGHT_EXCEPTION(
			"i18n.servicerecovery.appledbackfreightexception"), // 该运单已申请退运费，请勿重复申请

	SERVICE_RECOVERY_NULL_CUSTOMER_TYPE_EXCEPTION(
			"i18n.servicerecovery.nullcustomertypeexception"), // 你好，客户类型不能为空，请选择客户类型
	SERVICE_RECOVERY_INPUTAMOUNT_MORE_EXCEPTION(
			"i18n.servicerecovery.inputamountmoreexception"), // 输入金额必须小于开单金额
	SERVICE_RECOVERY_INPUTAMOUNT_MORE_100_EXCEPTION(
			"i18n.servicerecovery.inputamountmore100exception"), // 输入金额必须小于等于100元
	SERVICE_RECOVERY_INPUTAMOUNT_IS_NULL_EXCEPTION(
			"i18n.servicerecovery.inputamountisnullexception"), // 您好，减免金额不能为空，请输入金额！
	SERVICE_RECOVERY_DAMAGEPACKAGE_IS_NULL_EXCEPTION(
			"i18n.servicerecovery.damagepackageisnullexception"), // 受损件数不能为空，请输入受损件数
	SERVICE_RECOVERY_DAMAGEPACKAGE_MORE_EXCEPTION(
			"i18n.servicerecovery.damagepackagemoreexception"), // 受损件数不能超过总件数
	SERVICE_RECOVERY_ATTCHMENT_MORE_EXCEPTION(
			"i18n.servicerecovery.attachmentmoreexception"), // 你好，最多只能添加十个附件
	SERVICE_RECOVERY_DAMAGE_ATTCHMENT_NOT_NULL_EXCEPTION(
			"i18n.servicerecovery.damageattchmentnotnullexception"), // 减免类别为货物受损时，最少需要上传一个附件，请选择你需要上传的附件
	SERVICE_RECOVERY_DATE_OUT_90_EXCEPTION(
			"i18n.servicerecovery.dateout90exception"), // 查询范围超过九十天
	SERVICE_RECOVERY_WAYBILL_TOMORE_EXCEPTION(
			"i18n.servicerecovery.waybilltomoreexception"), // 输入运单号超过十个
	SERVICE_RECOVERY_NULL_SERACH_CONDITION_EXCEPTION(
			"i18n.servicerecovery.nullsearchconditionexception"), // 搜索条件不能全为空
	SERVICERECOVERY_OA_RETURN_EXCEPTION(
			"i18n.servicerecovery.oareturnexception"), // OA回调异常
	SERVICE_RECOVERY__NOT_EXIST_EXCEPTION(
			"i18n.servicerecovery.notexistexception"), // 该服务补救不存在
	SERVICE_RECOVERY_APPLY_ERROR_EXCEPTION(
			"i18n.servicerecovery.applyerrortexception"), // 申请状态不为已提交
	SERVICE_RECOVERY_APPLYPAYMENT_ERROR_EXCEPTION("i18n.servicerecovery.applyfail"),//服务补救申请付款失败
	WAYBILL_NOT_NORMAL_SIGNIN_EXCEPTION("i18n.servicerecovery.notnormalsign"), //此运单为异常签收，不允许申请服务补救
	WAYBILL_DEPT_ERROR_EXCEPTION("i18n.servicerecovery.depterror"),// 您好，本部门非运单收货或到达部门，没有权限申请服务补救
	SERVICE_RECOVERY_APPLY_EXPRESS_AUTH_ERROR("i18n.servicerecovery.express.auth.error");//抱歉，当前用户无上报权限
	private String errorCode;

	private ServiceRecoveryExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(ServiceRecoveryExceptionType type) {

		switch (type) {
		case WAYBILL_NOT_SIGNIN_EXCEPTION:
			return WAYBILL_NOT_SIGNIN_EXCEPTION.getErrorCode();
		case WAYBILL_NOT_EXIST_EXCEPTION:
			return WAYBILL_NOT_EXIST_EXCEPTION.getErrorCode();
		case WAYBILL_OUT_OF_APPLYDATE_EXCEPTION:
			return WAYBILL_OUT_OF_APPLYDATE_EXCEPTION.getErrorCode();
		case WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION:
			return WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION.getErrorCode();
		case WAYBILL_APPLED_RECOMPENSE_EXCPTION:
			return WAYBILL_APPLED_RECOMPENSE_EXCPTION.getErrorCode();
		case WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION:
			return WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION.getErrorCode();
		case WAYBILL_APPLED_BACKFREIGHT_EXCEPTION:
			return WAYBILL_APPLED_BACKFREIGHT_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_NULL_CUSTOMER_TYPE_EXCEPTION:
			return SERVICE_RECOVERY_NULL_CUSTOMER_TYPE_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_INPUTAMOUNT_MORE_EXCEPTION:
			return SERVICE_RECOVERY_INPUTAMOUNT_MORE_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_INPUTAMOUNT_MORE_100_EXCEPTION:
			return SERVICE_RECOVERY_INPUTAMOUNT_MORE_100_EXCEPTION
					.getErrorCode();
		case SERVICE_RECOVERY_INPUTAMOUNT_IS_NULL_EXCEPTION:
			return SERVICE_RECOVERY_INPUTAMOUNT_IS_NULL_EXCEPTION
					.getErrorCode();
		case SERVICE_RECOVERY_DAMAGEPACKAGE_IS_NULL_EXCEPTION:
			return SERVICE_RECOVERY_DAMAGEPACKAGE_IS_NULL_EXCEPTION
					.getErrorCode();
		case SERVICE_RECOVERY_DAMAGEPACKAGE_MORE_EXCEPTION:
			return SERVICE_RECOVERY_DAMAGEPACKAGE_MORE_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_ATTCHMENT_MORE_EXCEPTION:
			return SERVICE_RECOVERY_ATTCHMENT_MORE_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_DAMAGE_ATTCHMENT_NOT_NULL_EXCEPTION:
			return SERVICE_RECOVERY_DAMAGE_ATTCHMENT_NOT_NULL_EXCEPTION
					.getErrorCode();
		case SERVICE_RECOVERY_DATE_OUT_90_EXCEPTION:
			return SERVICE_RECOVERY_DATE_OUT_90_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_WAYBILL_TOMORE_EXCEPTION:
			return SERVICE_RECOVERY_WAYBILL_TOMORE_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_NULL_SERACH_CONDITION_EXCEPTION:
			return SERVICE_RECOVERY_NULL_SERACH_CONDITION_EXCEPTION
					.getErrorCode();
		case SERVICERECOVERY_OA_RETURN_EXCEPTION:
			return SERVICERECOVERY_OA_RETURN_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY__NOT_EXIST_EXCEPTION:
			return SERVICE_RECOVERY__NOT_EXIST_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_APPLY_ERROR_EXCEPTION:
			return SERVICE_RECOVERY_APPLY_ERROR_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_APPLYPAYMENT_ERROR_EXCEPTION:
			return SERVICE_RECOVERY_APPLYPAYMENT_ERROR_EXCEPTION.getErrorCode();
		case WAYBILL_NOT_NORMAL_SIGNIN_EXCEPTION:
			return WAYBILL_NOT_NORMAL_SIGNIN_EXCEPTION.getErrorCode();
		case WAYBILL_DEPT_ERROR_EXCEPTION:
			return WAYBILL_DEPT_ERROR_EXCEPTION.getErrorCode();
		case SERVICE_RECOVERY_APPLY_EXPRESS_AUTH_ERROR:
			return SERVICE_RECOVERY_APPLY_EXPRESS_AUTH_ERROR.getErrorCode();
		default:
			return null;
		}
	}

}