package com.deppon.crm.module.common.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

public class NoticeException extends BusinessException {

	/**
	 * constructer
	 */
	public NoticeException(NoticeExceptionType type) {
		super();
		this.errCode =type.getErrorCode() ;
	}

}
