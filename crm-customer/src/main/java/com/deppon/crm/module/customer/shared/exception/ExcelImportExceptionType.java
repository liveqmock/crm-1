package com.deppon.crm.module.customer.shared.exception;


public enum ExcelImportExceptionType {
	//头长度出错
	HeadSizeError("i18n.import.HeadSizeError"),
	HeadValueError("i18n.import.HeadValueError"),
	TypeValueError("i18n.import.TypeValueError"),
	
	;
	
	private String errCode;

	private ExcelImportExceptionType(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * <p>
	 * Description:errCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * <p>
	 * Description:errCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
