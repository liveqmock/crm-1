package com.deppon.crm.module.common.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class HelpDocException extends BusinessException {

	/**
	 * constructer
	 */
	public HelpDocException(HelpDocExceptionType type) {
		this.errCode = HelpDocExceptionType.getValue(type);
	}

}
