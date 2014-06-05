package com.deppon.crm.module.coupon.shared.exception;

import com.deppon.foss.framework.exception.BusinessException;



/**   
 * <p>
 * Description:优惠券异常类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-13
 */
public class CouponException extends BusinessException{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;

	public CouponException(CouponExceptionType errorType){
		super();
		this.errCode=errorType.getErrCode();
	}
}
