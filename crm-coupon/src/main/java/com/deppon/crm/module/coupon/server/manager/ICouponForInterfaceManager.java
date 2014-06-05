package com.deppon.crm.module.coupon.server.manager;

import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
/**   
 * <p>
 * Description:对应接口生产优惠券及营销计划<br />
 * </p>
 * @title ICouponForInterfaceManager.java
 * @package com.deppon.crm.module.coupon.server.manager 
 * @author ZhouYuan
 * @version 0.1 2013-09-06
 */
public interface ICouponForInterfaceManager {
	
	/**
	 * <p>
	 * Description: 生产新点优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 * @param CouponForInterface
	 * @return String
	 */
	String createCouponForInterface(CouponForInterface cfi);
}
