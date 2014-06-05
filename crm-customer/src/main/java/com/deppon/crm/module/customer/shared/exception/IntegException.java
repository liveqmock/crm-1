package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class IntegException extends GeneralException{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructer
	 */
	public IntegException(IntegExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}
	
	public IntegException(IntegExceptionType errorType,Object... arguments){
		this(errorType);
		super.setErrorArguments(arguments);
	}
	
		
	private IntegException() {
		super();
		// TODO Auto-generated constructor stub
	}

	private IntegException(String errCode, String message, Throwable cause,
			Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}

	public IntegException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	private IntegException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	private IntegException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public IntegException(Throwable cause,IntegExceptionType errorType) {
		this(cause);
		this.errCode = errorType.getErrCode();
	}
	

}
