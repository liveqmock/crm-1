package com.deppon.crm.module.coupon.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.deppon.crm.module.coupon.server.dao.ICouponDao;
import com.deppon.crm.module.coupon.server.dao.impl.CouponDaoImpl;
import com.deppon.crm.module.coupon.server.utils.CouponCellphoneUtils;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.coupon.utils.SpringTestHelper;

public class CouponDaoTest {
	private ICouponDao couponDao;
	
	@Before
	public void setUp() throws Exception {
		couponDao = (ICouponDao) SpringTestHelper.get().getBean(CouponDaoImpl.class);
	}
	@Test
	public void testUpdateCouponStatus(){
		String couponCode="40";
		List<String> couponNums=new ArrayList<String>();
		couponNums.add("110");
		couponDao.updateCouponStatus(couponCode, couponNums);
	}
	
	@Test
	public void testSearchCouponsByNums(){
		List<String> couponNums=new ArrayList<String>();
		String couponNum = "110"; 
		couponNums.add(couponNum);
		couponDao.searchCouponsByNums(couponNums);
	}
	
	@Test
	public void testGetCouponByNum(){
		String couponNum = "110";
		couponDao.getCouponByNum(couponNum);
	}
	
	@Test
	public void testUpdateOneCouponStatus(){
		String couponCode="00";
		String couponNum = "110";
		couponDao.updateOneCouponStatus(couponCode, couponNum);
	}	
	
	@Test
	public void testCreateCoupon(){
		List<Coupon> cl1 = new ArrayList<Coupon>();
		for (int i =0;i<2;i++){
			Coupon c = new Coupon();
			c.setCouponNumber(System.currentTimeMillis()+i+"");
			c.setMarketPlanId("43");
			c.setTypeId(MarketingCouponConstance.COUPON_SENDAUTO);
			c.setCreateruleId("23");
			c.setUseruleId("23");
			c.setStatus(MarketingCouponConstance.MARKETPLAN_NOUSE);
			c.setUnderDept("xxxx");
			c.setSendtelPhone("13812345678");
			c.setUsetelPhone("13812345678");
			c.setSourceWBNumber("Order0001");
			c.setSourceWBValue("1111112");
			c.setUseWBNumber("Order1001");
			c.setUseWBValue("100");
			c.setUseCouponValue("200");
			cl1.add(c);
		}
		//boolean rs1 = couponDao.createCoupon(cl1);
		Coupon c = new Coupon();
		c.setCouponNumber(System.currentTimeMillis()+"");
		c.setMarketPlanId("242");
		c.setTypeId("AUTOCOUPON");
		c.setCreateruleId("202");
		c.setUseruleId("222");
		c.setStatus("10");
		c.setUnderDept("1111");
		c.setSendtelPhone("13333333310");
		//c.setUsetelPhone("0");
		c.setSourceWBNumber("1234564444");
		c.setSourceWBValue("2000" );
		//c.setUseWBNumber("123");
		//c.setUseWBValue("22");
		c.setUseCouponValue("50");
		List<Coupon> ss = new ArrayList<Coupon>();
		ss.add(c);
		//couponDao.createCoupon(ss);
		//Assert.assertTrue(rs1);

	}

	@Test
	public void testUpdateCoupon(){
		String couponNum = "1353401741817";
		
		// 根据编码获取优惠券
//		Coupon coupon = couponDao.getCouponByNum(couponNum);
		Coupon coupon = new Coupon();
		
		coupon.setCouponNumber("1353401741817");
		// 使用运单编码
		coupon.setUseWBNumber("ZPJ"+System.currentTimeMillis());
		// 使用优惠券运单的开单金额
		coupon.setUseWBValue("1000000");
		// 优惠券使用时间
		coupon.setUseTime(new Date());
		// 优惠券使用状态
		coupon.setStatus(MarketingCouponConstance.COUPON_USING);
		// 使用手机号
		coupon.setUsetelPhone("13817648223");
		// 发送次数（0为不发送）
		coupon.setSmstimes(0);
		
		// 使用更新
		couponDao.updateCoupon(coupon);

//		// 取得更新结果
//		coupon = couponDao.getCouponByNum(couponNum);
//		System.out.println(String.format("%s,%s,%s,%s,%s,%s", 
//				coupon.getUseWBNumber(), 
//				coupon.getUseWBValue(), 
//				coupon.getUsetelPhone(),
//				coupon.getUseTime(), 
//				coupon.getStatus(), 
//				coupon.getSmstimes()));
//		
//		// 发送次数(1为发送)
//		coupon.setSmstimes(1);
//		// 发送更新
//		couponDao.updateCoupon(coupon);
//		// 取得更新结果
//		coupon = couponDao.getCouponByNum(couponNum);
//		System.out.println(String.format("%s,%s,%s,%s,%s,%s", 
//				coupon.getUseWBNumber(), 
//				coupon.getUseWBValue(), 
//				coupon.getUsetelPhone(),
//				coupon.getUseTime(), 
//				coupon.getStatus(), 
//				coupon.getSmstimes()));
		
	}
	@Test
	public void testSearchSendCouponVO(){
		String planNumber = "MP20121121001";
		Map<String,String> map = new HashMap<String,String>();
		map.put("planNumber", planNumber);
		map.put("status", "10");
		SendCouponVO sendCouponVO = couponDao.searchSendCouponVO(map);
		//System.out.println(sendCouponVO);
	}
	@Test
	public void testCreateCouponPhoneMsg(){
		CouponCellphoneMsgInfo c = new CouponCellphoneMsgInfo();
		c.setCouponNumber("43");
		c.setPhoneNumber("13723453434");
		c.setMsgContent("大沙发熟练地将发生的了风景阿萨德了发生");
		c.setSendStandardDeptCode("DP08068");
		c.setSenderEmpCode("069407");
		c.setSended("0");
		c.setCreateTime(new Date(0));
		couponDao.createCouponPhoneMsg(c);
	}
	@Test
	public void testSelectCouponByMarketPlanIdAndStatus(){
		Coupon coupon = new Coupon();
		coupon.setMarketPlanId("43");
		coupon.setStatus("10");
		couponDao.selectCouponByMarketPlanIdAndStatus(coupon, 0, 100);
	}
	@Test
	public void testUpdateCouponByCouponNumber(){
		Coupon coupon = new Coupon();
		coupon.setCouponNumber("1353401741817");
		coupon.setSendtelPhone("13323333222");
		coupon.setStatus("20");
		couponDao.updateCouponByCouponNumber(coupon);
	}
	@Test
	public void testSearchSendCouponMsg(){
		couponDao.searchSendCouponMsg(MarketingCouponConstance.COUPON_MSG_SENDED_UNSEND,0,500);
	}
	@Test
	public void testUpdateSendCouponMsg(){
		CouponCellphoneMsgInfo ccmi = new CouponCellphoneMsgInfo();
		ccmi.setSended(MarketingCouponConstance.COUPON_MSG_SENDED_SUCCESS);
		ccmi.setId("141");
		couponDao.updateSendCouponMsg(ccmi);
	}
	@Test
	public void testCleanInvalid(){
		List<CouponCellphone> phones = new ArrayList<CouponCellphone>();
		for(int i=0;i<100;i++){
			CouponCellphone c = new CouponCellphone();
			c.setCellphone("11111111111");
			c.setSendStatus("0");
			c.setValidity("0");
			phones.add(c);
		}
		System.out.println(phones);
		CouponCellphoneUtils.deleteRepeat(phones);
		System.out.println(phones);
	}
	
	@Test
	public void testSelectCouponByMutiCondition(){
		CouponSearchCondition condition = new CouponSearchCondition();
		condition.setPlanNumber("MP20121116002");
		List<?> list = couponDao.selectCouponByMutiCondition(condition, 0, 10);
		condition.setPlanNumber("1");
		List<?> list1 = couponDao.selectCouponByMutiCondition(condition, -1, 10);
		List<?> list2 = couponDao.selectCouponByMutiCondition(condition, 1, -10);

		// 查询手动券
		condition.setUsrDept("DP07139"); // 营销活动管理小组-此小组可以查询到手动券
		List<?> list3 = couponDao.selectCouponByMutiCondition(condition, 0, 10);
		
		condition.setUnderDept(null);
		// 按权限查询
		condition.setUsrId("22207");
		List<?> list4 = couponDao.selectCouponByMutiCondition(condition, 0, 10);
		// 按部门查询
		condition.setDeptSeq(".103.104.265.475.50502.11500.");
		List<?> list5 = couponDao.selectCouponByMutiCondition(condition, 0, 10);
		
		
	}
	@Test
	public void testSearchWaybillCouponByStatus(){
		couponDao.searchWaybillCouponByStatus(MarketingCouponConstance.WAYBILL_COUPON_STATUS_UNUSE,0,100);
		
	}
	@Test
	public void testUpdateWaybillCouponByStatus(){
		WaybillCoupon wc = new WaybillCoupon();
		wc.setId("1");
		wc.setStatus(MarketingCouponConstance.WAYBILL_COUPON_STATUS_USED);
		couponDao.updateWaybillCouponByStatus(wc);
	}
	@Test
	public void testCountCouponByMutiCondition(){
		CouponSearchCondition condition = new CouponSearchCondition();
		condition.setUnderDept("11469");
		couponDao.countCouponByMutiCondition(condition);
	}
	
	@Test 
	public void testGetDeptSeq(){
		String code = "DP02169";
		String a = couponDao.getDeptSeq(code);
		code = "111";
		String b = couponDao.getDeptSeq(code);
		System.out.println(a);
		System.out.println(b);
	}
	@Test
	public void testCallableCreateCouponHand(){
		String ss = couponDao.callableCreateCouponHand("HANDCOUPON");
	}
	@Test
	public void testCallableSendMsgAuto(){
		
		couponDao.callableSendMsgAuto(new Date(System.currentTimeMillis()-1000*60*60*24),new Date(),"DP23431", "12343");

	}
	@Test
	public void testCallableCreateHandCouponImm(){
		couponDao.callableCreateHandCouponImm(20, "55", "60", "300");
	}
}
