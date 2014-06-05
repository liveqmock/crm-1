package com.deppon.crm.module.duty.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class DutyException extends BusinessException{
	public DutyException(DutyExceptionType errorType){
		super();
		this.errCode=errorType.getErrorCode();
	}
	
	public DutyException(DutyExceptionType errorType, Object...args){
		super();
		this.errCode=errorType.getErrorCode();
		this.setErrorArguments(args);
	}
}
