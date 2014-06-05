/**   
 * @title RecompenseDaoException.java
 * @package com.deppon.crm.module.recompense.shared.exception
 * @description what to do
 * @author 潘光均
 * @update 2012-2-20 下午5:18:55
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.shared.exception;

/**
 * @description 实现xxx  
 * @author 潘光均
 * @version 0.1 2012-2-20
 *@date 2012-2-20
 */

public class RecompenseDaoException extends RuntimeException {
	private String message;
	public RecompenseDaoException(String message){
		this.message = message;
	}
}
