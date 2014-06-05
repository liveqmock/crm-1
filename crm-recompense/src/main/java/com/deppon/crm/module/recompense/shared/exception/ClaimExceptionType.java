package com.deppon.crm.module.recompense.shared.exception;

/**
 * 
 * <p>
 * Description:理赔异常类型<br />
 * </p>
 * 
 * @title ClaimExceptionType.java
 * @package com.deppon.crm.module.recompense.shared.exception
 * @author roy
 * @version 0.1 2013-3-1
 */

public enum ClaimExceptionType {
	// 运单号为空
	NULL_VOUCHERNO("i18n.claim.nullvoucherno"),
	// 用户未登录
	RECOMPENSE_USER_NOTLOGIN("i18n.claim.recompenseusernotlogin"),
	// 您好,请输入8位到10位运单号
	ERROR_WAYBILLNUMBER_BIT("i18n.claim.errorwaybillnumber"),
	// 该凭证号已上报索赔
	NOT_NULL_CLAIM("i18n.claim.notnullcliam"),
	// 该凭证号已经上报过理赔
	RECOMPENSE_RECORD_EXIST_ERROR("i18n.claim.recompenserecordexisterror"),
	// 该凭证号已上报索赔
	CLAIM_RECORD_EXIST_ERROR("i18n.claim.claimrecordexisterror"),
	// 该凭证号已在线理赔
	ONLINEAPPLY_RECORD_EXIST_ERROR("i18n.claim.onlineapplyrecordexisterror"),
	// 请到该运单的对应部门索赔
	CLAIMDEPT_ERROR("i18n.claim.claimdepterror"),
	// 该运单为偏线或者空运，录入部门只能是发货部门
	SEPCIAL_CLAIMDEPT_ERROR("i18n.claim.sepecialclaimdepterror"),
	// 查询条件不能全部为空
	NULL_SEARCH_CONDITION("i18n.claim.nullsearchcondition"),
	// 查询条件不能全部为空
	TOO_LONG_SEARCH_TIME("i18n.claim.toolongsearchtime"),
	// 跟进消息不能为空
	NULL_FOLLOW_MESSAGE("i18n.claim.nullfollowmessage"),
	// 您好，发送原因不能为空
	NULL_SEND_REASON("i18n.claim.nullsendreason"),
	// 对方部门为空，不能发送
	NULL_ARRIVEDDEPT_REASON("i18n.claim.nullarrivedeptreason"), ERROR_PROCESS_DEPT(
			"i18n.claim.errorprocessdept"), ERROR_CANNEL_REMIT_DEPT(
			"i18n.claim.errocannelremitdept"), NULLCURENEXPRESSBIGAREA(
			"索赔录入只能录入本大区快递运单"), CURRENTNOTCUSTOMSERVICE("试点城市快递理赔只能由本大区快递客服专员发起！"),
			//收货部门不能为快递点部和快递统计组
			EXPRESS_DEPT_IS_ERROR("i18n.claim.expressDeptError"), 
			NO_RECEIVE_DEPT("收货部门不存在"),
			NOTEXPRESSOFFICE("该大区无快递统计组"), 
			CURRENTNOTBUSSINESS("索赔录入只能录入本大区快递运单"),
			RECEIVEDEPT_ERROR("收货部门信息错误"),
			EXPRESS_DEPT_ERROR("无权限录入该运单索赔");
	
	private String errorCode;

	private ClaimExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(ClaimExceptionType type) {
		switch (type) {
		case NULL_VOUCHERNO:
			return NULL_VOUCHERNO.getErrorCode();
		case ERROR_WAYBILLNUMBER_BIT:
			return ERROR_WAYBILLNUMBER_BIT.getErrorCode();
		case NOT_NULL_CLAIM:
			return NOT_NULL_CLAIM.getErrorCode();
		case RECOMPENSE_RECORD_EXIST_ERROR:
			return RECOMPENSE_RECORD_EXIST_ERROR.getErrorCode();
		case CLAIM_RECORD_EXIST_ERROR:
			return CLAIM_RECORD_EXIST_ERROR.getErrorCode();
		case ONLINEAPPLY_RECORD_EXIST_ERROR:
			return ONLINEAPPLY_RECORD_EXIST_ERROR.getErrorCode();
		case CLAIMDEPT_ERROR:
			return CLAIMDEPT_ERROR.getErrorCode();
		case SEPCIAL_CLAIMDEPT_ERROR:
			return SEPCIAL_CLAIMDEPT_ERROR.getErrorCode();
		case NULL_SEARCH_CONDITION:
			return NULL_SEARCH_CONDITION.getErrorCode();
		case TOO_LONG_SEARCH_TIME:
			return TOO_LONG_SEARCH_TIME.getErrorCode();
		case NULL_FOLLOW_MESSAGE:
			return NULL_FOLLOW_MESSAGE.getErrorCode();
		case NULL_SEND_REASON:
			return NULL_SEND_REASON.getErrorCode();
		case NULL_ARRIVEDDEPT_REASON:
			return NULL_ARRIVEDDEPT_REASON.getErrorCode();
		case ERROR_PROCESS_DEPT:
			return ERROR_PROCESS_DEPT.getErrorCode();
		case NULLCURENEXPRESSBIGAREA:
			return NULLCURENEXPRESSBIGAREA.getErrorCode();
		case CURRENTNOTCUSTOMSERVICE:
			return CURRENTNOTCUSTOMSERVICE.getErrorCode();
		case EXPRESS_DEPT_IS_ERROR:
			return EXPRESS_DEPT_IS_ERROR.getErrorCode();
		case NOTEXPRESSOFFICE:
			return NOTEXPRESSOFFICE.getErrorCode();
		case CURRENTNOTBUSSINESS:
			return CURRENTNOTBUSSINESS.getErrorCode();
		case NO_RECEIVE_DEPT:
			return NO_RECEIVE_DEPT.getErrorCode();
		case RECEIVEDEPT_ERROR:
			return NO_RECEIVE_DEPT.getErrorCode();
		case EXPRESS_DEPT_ERROR:
			return EXPRESS_DEPT_ERROR.getErrorCode();
		default:
			return RECOMPENSE_USER_NOTLOGIN.getErrorCode();
		}
	}
}