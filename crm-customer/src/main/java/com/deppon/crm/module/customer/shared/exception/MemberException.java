package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class MemberException extends GeneralException{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructer
	 */
	public MemberException(MemberExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}
	
	public MemberException(MemberExceptionType errorType,Object... arguments){
		this(errorType);
		super.setErrorArguments(arguments);
	}
	
		
	private MemberException() {
		super();
		// TODO Auto-generated constructor stub
	}

	private MemberException(String errCode, String message, Throwable cause,
			Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}

	private MemberException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	private MemberException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	private MemberException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public MemberException(Throwable cause,MemberExceptionType errorType) {
		this(cause);
		this.errCode = errorType.getErrCode();
	}
	
	
	
}
