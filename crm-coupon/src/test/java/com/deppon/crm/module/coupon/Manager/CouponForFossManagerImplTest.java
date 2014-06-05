package com.deppon.crm.module.coupon.Manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.coupon.server.manager.ICouponForFossManager;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.manager.impl.CouponForFossManagerImpl;
import com.deppon.crm.module.coupon.server.manager.impl.CouponManagerImpl;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.coupon.utils.SpringTestHelper;

public class CouponForFossManagerImplTest {
	private  ICouponForFossManager couponForFossManagerImpl;
	private  ICouponManager couponManager;
	private static String couponNumber = "1353401741818";
	@Before
	public void setUp() throws Exception {
		couponForFossManagerImpl = (ICouponForFossManager) SpringTestHelper.get().getBean(CouponForFossManagerImpl.class);
		couponManager = (ICouponManager) SpringTestHelper.get().getBean(CouponManagerImpl.class);
	}
	@Test
	public void testChangeListCouponOverdue(){
		List<String> couponNums = new ArrayList<String>();
		couponForFossManagerImpl.changeListCouponOverdue(couponNums);
		couponNums.add("1353401741818");
		couponForFossManagerImpl.changeListCouponOverdue(couponNums);
		couponNums.add("110");
		couponForFossManagerImpl.changeListCouponOverdue(couponNums);
	}
	@Test
	public void testChangeCouponInvalidWB(){
		couponForFossManagerImpl.changeCouponInvalidWB(couponNumber);
		couponForFossManagerImpl.changeCouponInvalidWB(null);
		couponForFossManagerImpl.changeCouponInvalidWB("1353401741818");
	}
	@Test
	public void testCheckCouponRule(){
		WaybillInfo wbInfo = new WaybillInfo();
		wbInfo.setWaybillNumber("110");
		wbInfo.setOrderNumber("5726");
		wbInfo.setOrderSource("110");
		wbInfo.setProduceType("110");
		wbInfo.setTotalAmount(new BigDecimal(1));
		wbInfo.setArrivedDept("110");
		wbInfo.setLeaveDept("110");
		wbInfo.setLeaveOutDept("110");
		wbInfo.setArrivedOutDept("110");
		List<AmountInfo> list = new ArrayList<AmountInfo>();
		AmountInfo a = new AmountInfo();
		a.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		a.setValuationAmonut(new BigDecimal(200));
		list.add(a);
		a = new AmountInfo();
		a.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
		a.setValuationAmonut(new BigDecimal(200));
		list.add(a);
		wbInfo.setAmountList(list);
		String couponNum = "131106719340835";
		couponManager.checkCouponRule(couponNum, wbInfo);
		wbInfo.setCustNumber("110");
		couponForFossManagerImpl.checkCouponRule(couponNum, wbInfo);
		try{
			couponForFossManagerImpl.checkCouponRule(couponNum, wbInfo);
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	@Test
	public void testChangeCouponStatusUsing(){
		WaybillInfo wbInfo = new WaybillInfo();
		wbInfo.setWaybillNumber("110");
		wbInfo.setOrderNumber("110");
		wbInfo.setOrderSource("110");
		wbInfo.setProduceType("110");
		wbInfo.setTotalAmount(new BigDecimal(1));
		wbInfo.setArrivedDept("110");
		wbInfo.setLeaveDept("110");
		wbInfo.setLeaveOutDept("110");
		wbInfo.setArrivedOutDept("110");
		List<AmountInfo> list = new ArrayList<AmountInfo>();
		wbInfo.setAmountList(list);
		String couponNum = "1353401741818";
		couponManager.checkCouponRule(couponNum, wbInfo);
		wbInfo.setCustNumber("110");
		couponForFossManagerImpl.changeCouponStatusUsing(couponNum, wbInfo);
	}
}
