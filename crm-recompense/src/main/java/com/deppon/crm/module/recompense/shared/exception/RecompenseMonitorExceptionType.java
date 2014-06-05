package com.deppon.crm.module.recompense.shared.exception;

/**
 * @description 理赔监控异常类型
 * @author andy
 * @version 0.1 
 * @date 2013-7-5
 */

public enum RecompenseMonitorExceptionType {
	// 用户未登录
	RECOMPENSE_USER_NOTLOGIN("i18n.recompense.err.userNotLogin"),
	// 请选择短信通知对象
	RECOMPENSE_MESSAGERECEIVER_NULL("i18n.recompense.validate.MessageReceiver.CanNotNull"),
	// 当前状态没有短信模板
	RECOMPENSE_SMSTEMPLATE_REQUIRE_ERROR("i18n.recompense.validate.SMSTemplate.requireerror"),
	// 短信发送失败
	RECOMPENSE_SENDMESSAGE_ERROR("i18n.recompense.sendMessage.fail");

	private String errorCode;

	private RecompenseMonitorExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(RecompenseMonitorExceptionType type) {
		switch (type) {
			case RECOMPENSE_MESSAGERECEIVER_NULL:
				return RECOMPENSE_MESSAGERECEIVER_NULL.getErrorCode();
			case RECOMPENSE_SMSTEMPLATE_REQUIRE_ERROR:
				return RECOMPENSE_SMSTEMPLATE_REQUIRE_ERROR.getErrorCode();
			case RECOMPENSE_SENDMESSAGE_ERROR:
				return RECOMPENSE_SENDMESSAGE_ERROR.getErrorCode();
			default:
				return RECOMPENSE_USER_NOTLOGIN.getErrorCode();
		}
	}
}