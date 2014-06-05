package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class MemberEffectException extends GeneralException{

	public MemberEffectException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberEffectException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}
	
	public MemberEffectException(String errCode,Object... arguments) {
		this.errCode = errCode;
		super.setErrorArguments(arguments);
	}
	
	public MemberEffectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MemberEffectException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public MemberEffectException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
