/**   
 * <p>
 * Description:包<br />
 * </p>
 * @title UnboundException.java
 * @package com.deppon.crm.module.order.shared.exception 
 * @author Administrator
 * @version 0.1 2012-9-8
 */
package com.deppon.crm.module.order.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UnboundException.java
 * @package com.deppon.crm.module.order.shared.exception 
 * @author 苏玉军
 * @version 0.1 2012-9-8
 */
public class UnboundException extends BusinessException {
	
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 3743100229488523875L;
	/**
	 * 
	 * constructer
	 * @param type
	 */
	public UnboundException(UnboundExceptionType type){
		this.errCode = UnboundExceptionType.getValue(type);
	}
	
}
