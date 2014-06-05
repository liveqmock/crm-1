package com.deppon.crm.module.logmoniting.shared.Excepion;

import com.deppon.foss.framework.exception.GeneralException;


public class LogInfoException extends GeneralException{
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 6135945934914756326L;

	/**
	 * constructer
	 */
	public LogInfoException(LogInfoExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}
	
	public LogInfoException(LogInfoExceptionType errorType,Object... arguments){
		this(errorType);
		super.setErrorArguments(arguments);
	}
}
