package com.deppon.crm.module.common.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class CheckHardWareException extends GeneralException {

	public CheckHardWareException(){
		super();
	}
	
	public CheckHardWareException(String errCode,Object... arguments){
		this.errCode = errCode;
		setErrorArguments(arguments) ;
	}
	
	public CheckHardWareException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
	}

	public CheckHardWareException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckHardWareException(String msg) {
		super(msg);
	}

	public CheckHardWareException(Throwable cause) {
		super(cause);
	}
}
