package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class WorkFlowException extends GeneralException{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructer
	 */
	public WorkFlowException(WorkFlowExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();
	}
	
	
	public WorkFlowException(WorkFlowExceptionType errorType,Object... arguments){
		this(errorType);
		super.setErrorArguments(arguments);
	}
	
		
	private WorkFlowException() {
		super();
		// TODO Auto-generated constructor stub
	}

	private WorkFlowException(String errCode, String message, Throwable cause,
			Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}

	private WorkFlowException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	private WorkFlowException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	private WorkFlowException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public WorkFlowException(Throwable cause,WorkFlowExceptionType errorType) {
		this(cause);
		this.errCode = errorType.getErrCode();
	}
	
}
