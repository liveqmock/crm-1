package com.deppon.crm.module.client.common.exception;

import com.deppon.foss.framework.exception.GeneralException;

public class NoSuchServiceUrl extends GeneralException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1681490919298909410L;

	public NoSuchServiceUrl() {
		super();
	}

	public NoSuchServiceUrl(String errCode, String message, Throwable cause,
			Object... arguments) {
		super(errCode, message, cause, arguments);
	}

	public NoSuchServiceUrl(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchServiceUrl(String msg) {
		super(msg);
	}

	public NoSuchServiceUrl(Throwable cause) {
		super(cause);
	}

	
}
