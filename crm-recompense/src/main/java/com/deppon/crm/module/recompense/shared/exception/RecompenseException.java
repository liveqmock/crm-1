package com.deppon.crm.module.recompense.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * @ClassName: RecompenseException
 * @Description: 理賠異常
 * @author
 * @date 2012-3-30 下午4:47:58
 * 
 */
public class RecompenseException extends BusinessException {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 3966975724171418367L;

	/**
	 * constructer
	 */
	public RecompenseException(RecompenseExceptionType type) {
		this.errCode = RecompenseExceptionType.getValue(type);
	}
}
