package com.deppon.crm.module.coupon.server.manager.impl;

import com.deppon.crm.module.coupon.server.manager.ICouponForInterfaceManager;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
/**   
 * <p>
 * Description:对应接口生产优惠券及营销计划<br />
 * </p>
 * @title CouponForInterfaceManagerImpl.java
 * @package com.deppon.crm.module.coupon.server.manager 
 * @author ZhouYuan
 * @version 0.1 2013-09-06
 */
public class CouponForInterfaceManagerImpl implements ICouponForInterfaceManager {
	//优惠券Manager接口
	private ICouponManager couponManagerImpl;
	/**
	 * <p>
	 * Description: 生产新点优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 * @param CouponForInterface
	 * @return String
	 */
	public String createCouponForInterface(CouponForInterface couForIn){
		return couponManagerImpl.createCouponForInterface(couForIn);
	}
	/***************************get set方法***********************************/
	/**
	 * 
	 * @param setCouponManagerImpl : return the property :void
	 */
	public void setCouponManagerImpl(ICouponManager couponManagerImpl) {
		this.couponManagerImpl = couponManagerImpl;
	}	
	
	
	
}
