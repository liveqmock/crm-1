package com.deppon.crm.module.common.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;


public class SpecialDayException extends BusinessException{
	private static final long serialVersionUID = 322774313212694275L;
	public SpecialDayException(SpecialDayExceptionType type) {
		this.errCode = SpecialDayExceptionType.getValue(type);
	}
}
