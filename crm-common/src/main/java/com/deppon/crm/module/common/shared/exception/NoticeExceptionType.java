package com.deppon.crm.module.common.shared.exception;

public enum NoticeExceptionType {

	NOTICE_EXCEPTION("i18n.notice.exception"),
	NOTICE_NULLDATA_EXCEPTION("i18n.notice.nulldataexception");

	private String errorCode;

	private NoticeExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
