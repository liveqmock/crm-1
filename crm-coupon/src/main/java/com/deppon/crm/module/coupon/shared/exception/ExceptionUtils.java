/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ExceptionUtils.java
 * @package com.deppon.crm.module.coupon.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-11-22
 */
package com.deppon.crm.module.coupon.shared.exception;

import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description: 异常处理工具类<br />
 * </p>
 * @title ExceptionUtils.java
 * @package com.deppon.crm.module.coupon.shared.exception 
 * @author ZhuPJ
 * @version 0.1 2012-11-22
 */

public class ExceptionUtils {
	// 根据异常类型创建异常
	public static void createCouponException(CouponExceptionType type){
		createCouponException(type, null);
	}
	// 根据异常类型创建异常
	public static void createCouponException(CouponExceptionType type, Object...para){
		CouponException e = new CouponException(
				type);
		throw new GeneralException(e.getErrorCode(), e.getMessage(), e, para) {
			private static final long serialVersionUID = 1L;};
	}
}
