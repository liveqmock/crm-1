package com.deppon.crm.module.common.shared.domain;


/**
 * Description:服务补救异常类型 ServiceRecoveryExceptionType.java Create on 2012-10-29
 * 上午10:15:50
 * 
 * @author 华龙
 * @version 1.0
 */

public enum SpecialDayExceptionType {

	SPECIALDAY_NOT_SPECIALDAY_EXECEPTION(
			"i18n.servicerecovery.notspecialdayexeception");
	private String errorCode;

	private SpecialDayExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(SpecialDayExceptionType type) {

		switch (type) {
		case SPECIALDAY_NOT_SPECIALDAY_EXECEPTION:
			return SPECIALDAY_NOT_SPECIALDAY_EXECEPTION.getErrorCode();
		default:
			return null;
		}

	}
}