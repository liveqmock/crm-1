package com.deppon.crm.module.common.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;



public class LadingStationDepartmentException extends BusinessException{

	/**
	 * constructer
	 */
	public LadingStationDepartmentException(LadingStationDepartmentExceptionType type) {
		this.errCode=LadingStationDepartmentExceptionType.getValue(type);
	}	
	
}
