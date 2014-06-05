package com.deppon.crm.module.coupon.Manager;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.coupon.server.manager.ICouponForSchedual;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.manager.impl.CouponForSchedualImpl;
import com.deppon.crm.module.coupon.server.manager.impl.CouponManagerImpl;
import com.deppon.crm.module.coupon.utils.SpringTestHelper;
public class CouponForSchedualTest {
	private  ICouponForSchedual couponForSchedual;
	private  ICouponManager couponManager;

	@Before
	public void setUp() throws Exception {
		couponForSchedual = (ICouponForSchedual) SpringTestHelper.get().getBean(CouponForSchedualImpl.class);
		couponManager =(ICouponManager)SpringTestHelper.get().getBean(CouponManagerImpl.class);
	}
	@Test
	public void testAutoSendCoupon(){
		couponForSchedual.autoSendCoupon(new Date(System.currentTimeMillis()-1000*60*60*24),new Date());
	}
	@Test
	public void testUpdateMarketPlanStatesByOverdue(){
		couponForSchedual.updateMarketPlanStatesByOverdue();
	}
	@Test
	public void testUpdateCouponStatesByOverdue(){
		couponForSchedual.updateCouponStatesByOverdue();
	}
	@Test
	public void testSendCouponMsg(){
		try {
			couponForSchedual.sendCouponMsg();
		} catch(Exception ex){
			ex.printStackTrace();
	}
		
	}
	@Test
	public void testCreateCouponHandBackground(){
		couponForSchedual.createCouponHandBackground();
	} 
}
