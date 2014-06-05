package com.deppon.crm.module.backfreight.shared.exception;


/**
 * Description:退运费异常类型
 * BackFreightExeceptionType.java Create on 2012-10-29 上午10:15:50 
 * @author 华龙
 * @version 1.0
 */
public enum BackFreightExceptionType {
	// 退运费异常(test，未在i18n添加)
	BACKFRIGHT_EXECEPTION(
			"i18n.backfreight.backexception"),
	WAYBILL_NOT_SIGNIN_EXCEPTION("i18n.backfreight.waybillnotsigninexception"),//该单号尚未签收
	WAYBILL_NOT_EXIST_EXCEPTION("i18n.backfreight.waybillnotexistexception"),// 你好，你输入的运单号不存在，请从新输入
	WAYBILL_OUT_OF_APPLYDATE_EXCEPTION("i18n.backfreight.waybilloutofappdateexception"),//该运单已超出退运费申请期限，不允许申请
	WAYBILL_ERROR_TRANTYPE_NULL_EXECEPTION("i18n.backfreight.waybillerrortrantypenullexception"),//退运费只适用于精准城运或精准空运
	WAYBILL_ERROR_TRANTYPE_EXECEPTION("i18n.backfreight.waybillerrortrantypeexception"),//退运费只适用于精准城运或精准空运
	WAYBILL_ERROR_APPLY_DEPT_EXECEPTION("i18n.backfreight.waybillerrorapplydeptexception"),//此运单号的申请部门不是您所在的部门，您无权申请
	WAYBILL_APPLED_BACKFREIGHT_EXCEPTION("i18n.backfreight.appledbackfreightexception"),//该运单已申请退运费，请勿重复申请
	WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION("i18n.backfreight.appledBACKFREIGHTexception"),//该运单已申请过服务补救，请勿重复申请
	WAYBILL_APPLED_RECOMPENSE_EXCPTION("i18n.backfreight.appledrecompenseexception"),//该运单已申请过理赔，不允许退运费
	WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION("i18n.backfreight.appleddelayaccidentexception"),//该运费已上报过时效延误差错，不允许退运费
	WAYBILL_APPLED_BADDEBT_EXCEPTION("i18n.backfreight.appleddbaddebtexception"),//该运费已上报坏账，不允许退运费
	BACK_FREIGHT_APPLYAMOUNT_NULL_EXCEPTION("i18n.backfreight.applyamountnullexception"),//申请金额不能为空，请输入金额
	BACK_FREIGHT_APPLYAMOUNT_ZERO_EXCEPTION("i18n.backfreight.applyamountzeroexception"),//申请金额必须为大于0的正整数，请重新输入
	BACK_FREIGHT_APPLYAMOUNT_MORE_EXCEPTION("i18n.backfreight.inputamountmoreexception"),//申请金额不能大于运费，请重新输入！
	BACK_FREIGHT_APPLYAMOUNT_THREEMORE_EXCEPTION("i18n.backfreight.inputamountthreemoreexception"),//申请金额不能大于3倍运单运费，请重新输入！
	BACK_FREIGHT_ATTCHMENT_MORE_EXCEPTION("i18n.backfreight.attachmentmoreexception"),//你好，最多只能添加十个附件
	BACK_FREIGHT_DATE_OUT_90_EXCEPTION("i18n.backfreight.dateout90exception"),//查询范围超过九十天
	BACK_FREIGHT_WAYBILL_TOMORE_EXCEPTION("i18n.backfreight.waybilltomoreexception"),//输入运单号超过十个
	BACK_FREIGHT_NULL_SERACH_CONDITION_EXCEPTION("i18n.backfreight.nullsearchconditionexception"),//搜索条件不能全为空
	BACK_FREIGHT_OA_RETURN_EXCEPTION("i18n.backfreight.oareturnexception"),//OA回调异常
	BACK_FREIGHT_NOT_EXIST_EXCEPTION("i18n.backfreight.existexception"),//该退运费不存在
	BACK_FREIGHT_APPLY_ERROR_EXCEPTION("i18n.backfreight.applyerrorexception"),//申请状态不为已提交
	BACK_FREIGHT_PAYFAIl_EXCEPTION("退运费付款申请失败");//申请状态不为已提交

	private String errorCode;

	private BackFreightExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public static String getValue(BackFreightExceptionType type) {
		
		switch (type) {
		case WAYBILL_NOT_SIGNIN_EXCEPTION:
			return WAYBILL_NOT_SIGNIN_EXCEPTION.getErrorCode();
		case WAYBILL_NOT_EXIST_EXCEPTION:
			return WAYBILL_NOT_EXIST_EXCEPTION.getErrorCode();
		case WAYBILL_OUT_OF_APPLYDATE_EXCEPTION:
			return WAYBILL_OUT_OF_APPLYDATE_EXCEPTION.getErrorCode();
		case WAYBILL_ERROR_TRANTYPE_EXECEPTION:
			return WAYBILL_ERROR_TRANTYPE_EXECEPTION.getErrorCode();
		case WAYBILL_ERROR_APPLY_DEPT_EXECEPTION:
			return WAYBILL_ERROR_APPLY_DEPT_EXECEPTION.getErrorCode();
		case WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION:
			return WAYBILL_APPLED_SERVICE_RECOVERY_EXCEPTION.getErrorCode();
		case WAYBILL_APPLED_RECOMPENSE_EXCPTION:
			return WAYBILL_APPLED_RECOMPENSE_EXCPTION.getErrorCode();
		case WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION:
			return WAYBILL_APPLED_DELAYACCIDENT_EXCEPTION.getErrorCode();
		case WAYBILL_APPLED_BACKFREIGHT_EXCEPTION:
			return WAYBILL_APPLED_BACKFREIGHT_EXCEPTION.getErrorCode();
		case WAYBILL_APPLED_BADDEBT_EXCEPTION:
			return WAYBILL_APPLED_BADDEBT_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_APPLYAMOUNT_NULL_EXCEPTION:
			return BACK_FREIGHT_APPLYAMOUNT_NULL_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_APPLYAMOUNT_ZERO_EXCEPTION:
			return BACK_FREIGHT_APPLYAMOUNT_ZERO_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_APPLYAMOUNT_MORE_EXCEPTION:
			return BACK_FREIGHT_APPLYAMOUNT_MORE_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_APPLYAMOUNT_THREEMORE_EXCEPTION:
			return BACK_FREIGHT_APPLYAMOUNT_THREEMORE_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_ATTCHMENT_MORE_EXCEPTION:
			return BACK_FREIGHT_ATTCHMENT_MORE_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_DATE_OUT_90_EXCEPTION:
			return BACK_FREIGHT_DATE_OUT_90_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_WAYBILL_TOMORE_EXCEPTION:
			return BACK_FREIGHT_WAYBILL_TOMORE_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_NULL_SERACH_CONDITION_EXCEPTION:
			return BACK_FREIGHT_NULL_SERACH_CONDITION_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_OA_RETURN_EXCEPTION:
			return BACK_FREIGHT_OA_RETURN_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_NOT_EXIST_EXCEPTION:
			return BACK_FREIGHT_NOT_EXIST_EXCEPTION.getErrorCode();
		case BACK_FREIGHT_APPLY_ERROR_EXCEPTION:
			return BACK_FREIGHT_APPLY_ERROR_EXCEPTION.getErrorCode();
		case WAYBILL_ERROR_TRANTYPE_NULL_EXECEPTION:
			return WAYBILL_ERROR_TRANTYPE_NULL_EXECEPTION.getErrorCode();
		case BACK_FREIGHT_PAYFAIl_EXCEPTION:
			return BACK_FREIGHT_PAYFAIl_EXCEPTION.getErrorCode();
			
		default:
			return null;
		}
	}

	
}