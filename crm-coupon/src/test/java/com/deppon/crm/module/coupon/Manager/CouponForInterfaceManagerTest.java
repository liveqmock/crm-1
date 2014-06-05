package com.deppon.crm.module.coupon.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.coupon.server.manager.ICouponForInterfaceManager;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.manager.impl.CouponForInterfaceManagerImpl;
import com.deppon.crm.module.coupon.server.service.ICouponService;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.utils.SpringTestHelper;

public class CouponForInterfaceManagerTest {
	private  ICouponForInterfaceManager couponForInterfaceImpl;
	
	@Before
	public void setUp() throws Exception {
		couponForInterfaceImpl = (ICouponForInterfaceManager) SpringTestHelper.get().getBean(CouponForInterfaceManagerImpl.class);		
	}
	@Test
	public void testCreateCouponForInterface(){
//		CouponForInterface cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01475");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setBeginTime(new Date());
//		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000L));
//		cfi.setCouponValue("50");
//		cfi.setCostMode("2");
//		cfi.setCostType("FRT");
//		cfi.setValue("50");
//		cfi.setDiscount("100");
//		cfi.setCostAddedType("BZ");
//		cfi.setCostAdded("20");
//		cfi.setProductType("FLF,FSF,LRF");
//		cfi.setOrderType("ONLINE,TAOBAO,ALIBABA");
//		cfi.setCustLevel("NORMAL,GOLD,PLATINUM");
//		cfi.setCustTrade("CLOTH_SPIN,ITRONIC_FURNITURE");
//		cfi.setRegdemand("2");
//		List<GoodsLine> gs = new ArrayList<GoodsLine>();
//		for( int i=0; i< 3;i++){
//			GoodsLine g = new GoodsLine();
//			g.setBeginDeptOrCityId("DP01475");
//			g.setBeginDeptOrCityName("杭州萧山区新塘营业部");
//			g.setEndDeptOrCityId("DP00470");
//			g.setEndDeptOrCityName("广州白云区太和营业部");
//			gs.add(g);
//		}
//		cfi.setGoodsLines(gs);
//		cfi.setSmsContent("NEWOPEN");
//		cfi.setDescribe("NEWOPEN");
//		couponForInterfaceImpl.createCouponForInterface(cfi);
//		cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("1");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setBeginTime(new Date());
//		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000L));
//		cfi.setCouponValue("50");
//		cfi.setCostMode("2");
//		cfi.setCostType("FRT");
//		cfi.setValue("50");
//		cfi.setDiscount("100");
//		cfi.setCostAddedType("BZ");
//		cfi.setCostAdded("20");
//		cfi.setProductType("FLF,FSF,LRF");
//		cfi.setOrderType("ONLINE,TAOBAO,ALIBABA");
//		cfi.setCustLevel("NORMAL,GOLD,PLATINUM");
//		cfi.setCustTrade("CLOTH_SPIN,ITRONIC_FURNITURE");
//		cfi.setRegdemand("2");
//		gs = new ArrayList<GoodsLine>();
//		for( int i=0; i< 3;i++){
//			GoodsLine g = new GoodsLine();
//			g.setBeginDeptOrCityId("DP01475");
//			g.setBeginDeptOrCityName("杭州萧山区新塘营业部");
//			g.setEndDeptOrCityId("DP00470");
//			g.setEndDeptOrCityName("广州白云区太和营业部");
//			gs.add(g);
//		}
//		cfi.setGoodsLines(gs);
//		cfi.setSmsContent("NEWOPEN");
//		cfi.setDescribe("NEWOPEN");
//		try {
//			couponForInterfaceImpl.createCouponForInterface(cfi);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		CouponForInterface cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01910");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setBeginTime(new Date());
//		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000L));
//		cfi.setCouponValue("50");
//		cfi.setCostMode("2");
//		cfi.setCostType("FRT");
//		cfi.setValue("50");
//		cfi.setDiscount("100");
//		cfi.setCostAddedType("BZ");
//		cfi.setCostAdded("20");	
//		cfi.setRegdemand("2");
//		List gs = new ArrayList<GoodsLine>();
//		for( int i=0; i< 3;i++){
//			GoodsLine g = new GoodsLine();
//			g.setBeginDeptOrCityId("DP01910");
//			g.setBeginDeptOrCityName("上海嘉定区浏翔公路营业部");
//			g.setEndDeptOrCityId("DP00470");
//			g.setEndDeptOrCityName("广州白云区太和营业部");
//			gs.add(g);
//		}
//		cfi.setGoodsLines(gs);
//		try {
//			couponForInterfaceImpl.createCouponForInterface(cfi);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01924");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setBeginTime(new Date());
//		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000L));
//		cfi.setCouponValue("50");
//		cfi.setCostMode("2");
//		cfi.setCostType("FRT");
//		cfi.setValue("50");
//		cfi.setCostAddedType("BZ");
//		cfi.setCostAdded("20");	
//		cfi.setRegdemand("2");
//		gs = new ArrayList<GoodsLine>();
//		for( int i=0; i< 3;i++){
//			GoodsLine g = new GoodsLine();
//			g.setBeginDeptOrCityId("DP01924");
//			g.setBeginDeptOrCityName("成都武侯区金花镇营业部");
//			g.setEndDeptOrCityId("DP00470");
//			g.setEndDeptOrCityName("广州白云区太和营业部");
//			gs.add(g);
//		}
//		cfi.setGoodsLines(gs);
//		try {
//			couponForInterfaceImpl.createCouponForInterface(cfi);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			couponForInterfaceImpl.createCouponForInterface(cfi);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		CouponForInterface cfi = new CouponForInterface();
		cfi.setDeptStandardCode("DP01687");
		cfi.setActivityType("NEWOPEN");
		cfi.setBeginTime(new Date());
		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000L));
		cfi.setCouponValue("50");
		cfi.setCostMode("2");
		cfi.setCostType("2");
		cfi.setValue("50");
		cfi.setCostAddedType("BZ");
		cfi.setCostAdded("20");	
		cfi.setRegdemand("2");
		List<GoodsLine> gs = new ArrayList<GoodsLine>();
		for( int i=0; i< 3;i++){
			GoodsLine g = new GoodsLine();
			g.setBeginDeptOrCityId("DP01687");
			g.setBeginDeptOrCityName("湛江霞山区建设路营业部");
			g.setEndDeptOrCityId("DP00470");
			g.setEndDeptOrCityName("广州白云区太和营业部");
			gs.add(g);
		}
		cfi.setGoodsLines(gs);
		try {
			couponForInterfaceImpl.createCouponForInterface(cfi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testTime(){
		long l = System.currentTimeMillis()+(30*24*60*60*1000L);
		Date d1 = new Date(l);
		Date d2 = new Date();
		System.out.println(System.currentTimeMillis());
		System.out.println(l);
		System.out.println(d2.getTime());
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d1.getTime()-d2.getTime());
	}
}
