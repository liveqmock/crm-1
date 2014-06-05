package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class ExcelImportException extends GeneralException{

	public ExcelImportException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelImportException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}
	
	public ExcelImportException(String errCode,Object... arguments) {
		this.errCode = errCode;
		super.setErrorArguments(arguments);
	}
	
	public ExcelImportException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExcelImportException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public ExcelImportException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
