package com.deppon.crm.module.coupon.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.coupon.server.utils.CouponMsgUtil;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;

public class CouponMsgUtilTest {

	@Test
	public void testCreateCouponMsg(){
		SendCouponVO scvo = new SendCouponVO();
		scvo.setMarketPlanId(264);
		scvo.setPlanName("测试130");
		scvo.setPlanNumber("MP20121129004");
		scvo.setCouponType("HANDCOUPON");
		scvo.setMarketPlanStatus("10");
		scvo.setBalance(111);
		scvo.setBeginTime(new Date());
		scvo.setEndTime(new Date(System.currentTimeMillis()+1000000000));
		scvo.setSms("亲爱的用户,您好!");
		scvo.setCouponValue("200");
		scvo.setCouponQuantity(10000);
		scvo.setSendedNum(123);
		
		CouponCellphone ccp = new CouponCellphone();
		ccp.setCellphone("12311112222");
		ccp.setSendStatus("0");
		ccp.setValidity("0");
		
		String coupon = "20122233111111111";
		
		String empCode = "123232";
		
		String deptCode = "34234234";
		
		CouponMsgUtil.createCouponMsg(scvo, ccp, coupon, empCode, deptCode);
		
		WaybillCoupon wb = new WaybillCoupon();
		wb.setId(1000000+"");
		wb.setWaybillNumber(1234567890+"");
		wb.setMarketPlanId("MP20121128015");
		wb.setUnderDept("111111");
		wb.setSendTelPhone(1320000000+"");
		wb.setWbValue("2000");
		wb.setValue("50");
		wb.setStatus("0");
		wb.setCreateTime(new Date());
		CouponMsgUtil.createCouponMsg(wb, coupon, empCode, deptCode);
		
		Coupon c = new Coupon();
		c.setCouponNumber(System.currentTimeMillis()+"");
		c.setMarketPlanId("43");
		c.setTypeId(MarketingCouponConstance.COUPON_SENDAUTO);
		c.setCreateruleId("23");
		c.setUseruleId("23");
		c.setStatus(MarketingCouponConstance.COUPON_NOSEND);
		c.setUnderDept("xxxx");
		c.setSendtelPhone("13812345678");
		c.setUsetelPhone("13812345678");
		c.setSourceWBNumber("Order0001");
		c.setSourceWBValue("1111112");
		c.setUseWBNumber("Order1001");
		c.setUseWBValue("100");
		c.setUseCouponValue("200");
		
		CouponMsgUtil.createCouponMsg(c, "亲爱的用户! ", new Date(), empCode);

	}
	@Test
	public void testCreateMessage(){
		CouponMsgUtil.createMessage("亲爱的用户! ", null, "123456123456789", new Date());
	}
	@Test
	public void testCreateCouponMsg1(){
		List<WaybillCoupon> wbCoupons = new ArrayList<WaybillCoupon>();
		List<Coupon> coupons = new ArrayList<Coupon>();
		String empCode = "123";
		String deptCode = "123";
		WaybillCoupon wb = new WaybillCoupon();
		wb.setMsgContent("您好!");
		wb.setValue("1234");
		wb.setEndTime(new Date());
		Coupon c = new Coupon();
		c.setCouponNumber("2012123456789");
		wbCoupons.add(wb);
		
		coupons.add(c);
		try {
			CouponMsgUtil.createCouponMsg(wbCoupons, coupons, empCode, deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		wb = new WaybillCoupon();
		wb.setMsgContent("您好!");
		wb.setValue("1234");
		wb.setEndTime(new Date());
		wbCoupons.add(wb);
		try {
			CouponMsgUtil.createCouponMsg(wbCoupons, coupons, empCode, deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
