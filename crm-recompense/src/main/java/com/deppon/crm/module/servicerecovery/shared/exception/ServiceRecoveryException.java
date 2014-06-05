package com.deppon.crm.module.servicerecovery.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * Description:服务补救异常
 * ServiceRecoveryException.java Create on 2012-10-29 上午10:15:50 
 * @author 华龙
 * @version 1.0
 */
public class ServiceRecoveryException extends BusinessException {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 730630291284082504L;

	/**
	 * constructer
	 */
	public ServiceRecoveryException(ServiceRecoveryExceptionType type) {
		this.errCode = ServiceRecoveryExceptionType.getValue(type);
	}
}
