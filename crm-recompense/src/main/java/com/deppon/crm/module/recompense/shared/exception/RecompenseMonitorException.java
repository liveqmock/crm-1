package com.deppon.crm.module.recompense.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * @ClassName: RecompenseMonitorException
 * @Description: 理赔监控异常
 * @author
 * @date 2013-7-5
 * 
 */
public class RecompenseMonitorException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructer
	 */
	public RecompenseMonitorException(RecompenseMonitorExceptionType type) {
		this.errCode = RecompenseMonitorExceptionType.getValue(type);
	}
}
