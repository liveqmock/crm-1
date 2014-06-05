package com.deppon.crm.module.common.shared.exception;

public enum SpecialDayExceptionType {
	SPECIALDAY_IS_EXIST("i18n.common.specialday.isexist"),
	LEGAL_HOLIDAYS_IS_EXIST(
			"i18n.common.specialday.legalisexist");
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	private SpecialDayExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(SpecialDayExceptionType type) {
		switch (type) {
		case SPECIALDAY_IS_EXIST:
			return SPECIALDAY_IS_EXIST.getErrorCode();
		case LEGAL_HOLIDAYS_IS_EXIST:
			return LEGAL_HOLIDAYS_IS_EXIST.getErrorCode();
		default:
			return null;
		}
	}
}
