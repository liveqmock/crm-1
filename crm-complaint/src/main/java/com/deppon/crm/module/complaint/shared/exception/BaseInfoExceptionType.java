package com.deppon.crm.module.complaint.shared.exception;

public enum BaseInfoExceptionType {
	
	
	BASEINFO_NULL_EXCEPTION("i18n.baseinfo.baseinfoisnull"),
	
	
	;
	private String errorCode;

	private BaseInfoExceptionType(String errorCode){
		this.errorCode=errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
