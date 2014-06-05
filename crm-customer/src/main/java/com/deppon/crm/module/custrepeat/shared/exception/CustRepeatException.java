package com.deppon.crm.module.custrepeat.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class CustRepeatException extends GeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustRepeatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustRepeatException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造器
	 * @param errCode
	 * @param arguments
	 */
	public CustRepeatException(String errCode,Object... arguments) {
		this.errCode = errCode;
		super.setErrorArguments(arguments);
	}
	/**
	 * 构造器
	 * @param message
	 * @param cause
	 */
	public CustRepeatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造器
	 * @param msg
	 */
	public CustRepeatException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造器
	 * @param cause
	 */
	public CustRepeatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
