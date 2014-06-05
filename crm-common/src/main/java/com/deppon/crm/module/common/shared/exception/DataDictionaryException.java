package com.deppon.crm.module.common.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class DataDictionaryException extends GeneralException{

	public DataDictionaryException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DataDictionaryException(String errCode,Object... arguments){
		this.errCode = errCode;
		setErrorArguments(arguments) ;
	}
	
	public DataDictionaryException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}

	public DataDictionaryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataDictionaryException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public DataDictionaryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
