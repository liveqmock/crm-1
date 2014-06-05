package com.deppon.crm.module.client.common.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class DownLoadWsdlException extends GeneralException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2528676450585750329L;

	public DownLoadWsdlException(String errCode, String message,
			Throwable cause, Object... arguments) {
		super(errCode, message, cause, arguments);
	}


	public DownLoadWsdlException(String message, Throwable cause) {
		super(message, cause);
	}


	public DownLoadWsdlException(String msg) {
		super(msg);
	}


	public DownLoadWsdlException(Throwable cause) {
		super(cause);
	}
	
	
}
