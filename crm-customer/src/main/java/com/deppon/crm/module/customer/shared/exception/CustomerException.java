/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ConditionException.java
 * @package com.deppon.crm.module.customer.shared.exception 
 * @author Administrator
 * @version 0.1 2012-3-2
 */
package com.deppon.crm.module.customer.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ConditionException.java
 * @package com.deppon.crm.module.customer.shared.exception
 * @author Administrator
 * @version 0.1 2012-3-2
 */

public class CustomerException extends GeneralException {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -3163279505800162966L;


	/**
	 * constructer
	 */
	public CustomerException(CustomerExceptionType errorType) {
		super();
		this.errCode = errorType.getErrCode();

	}


	public CustomerException(CustomerExceptionType errorType,
			Object[] objects) {
		super();
		this.errCode = errorType.getErrCode();
		setErrorArguments(objects);
	}

}
