package com.deppon.crm.module.recompense.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * 
 * <p>
 * Description:索赔异常<br />
 * </p>
 * @title ClaimException.java
 * @package com.deppon.crm.module.recompense.shared.exception 
 * @author roy
 * @version 0.1 2013-3-1
 */
public class ClaimException extends BusinessException {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 3966975724171418367L;

	/**
	 * constructer
	 */
	public ClaimException(ClaimExceptionType type) {
		this.errCode = ClaimExceptionType.getValue(type);
	}
}
