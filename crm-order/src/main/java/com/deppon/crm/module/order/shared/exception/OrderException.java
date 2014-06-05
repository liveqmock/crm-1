/**   
 * @title OrderException.java
 * @package com.deppon.crm.module.order.shared.exception
 * @description 订单异常处理类
  * @author 潘光均
 * @update 2012-3-9 下午4:30:28
 * @version V1.0   
 */
package com.deppon.crm.module.order.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;

/**
 * @description 订单exception  
 * @author 潘光均
 * @version 0.1 2012-3-9
 *@date 2012-3-9
 */

public class OrderException extends BusinessException{

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 8927081270074039087L;
	/**
	 * constructer
	 */
	public OrderException(OrderExceptionType type) {
		this.errCode=type.getErrorCode();
	}
}
