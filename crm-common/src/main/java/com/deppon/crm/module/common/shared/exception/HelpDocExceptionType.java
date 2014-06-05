package com.deppon.crm.module.common.shared.exception;

public enum HelpDocExceptionType {

	/**
	 * 帮助后台异常
	 */
	HLEPDOC_EXCEPTION("i18n.helpdoc.exception"),

	/**
	 * 帮助弹窗编码存在
	 */
	WINDOWNUM_EXISTED_EXCEPTION("i18n.helpdoc.windownumExistedException");

	private String errorCode;

	private HelpDocExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static String getValue(HelpDocExceptionType type) {
		switch (type) {

		case WINDOWNUM_EXISTED_EXCEPTION:
			return WINDOWNUM_EXISTED_EXCEPTION.getErrorCode();

		default:
			return HLEPDOC_EXCEPTION.getErrorCode();
		}
	}

}
