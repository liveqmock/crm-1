package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class ScatterException extends GeneralException {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -12034370385824604L;

	public ScatterException(ScatterExceptionType excpetionType){
		super();
		this.errCode = excpetionType.getErrCode();
	}
	
	public ScatterException(ScatterExceptionType exceptionType, Object...arguments) {
		super();
		this.errCode = exceptionType.getErrCode();
		setErrorArguments(arguments);
	}
}
