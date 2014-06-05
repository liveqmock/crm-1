package com.deppon.crm.module.coupon.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.coupon.server.dao.IMarketPlanDao;
import com.deppon.crm.module.coupon.server.dao.impl.MarketPlanDaoImpl;
import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.utils.SpringTestHelper;

public class MarketPlanDaoTest {
	private  IMarketPlanDao marketPlanDaoImpl;
	private  Marketplan marketplan;
	private static String marketplanId;
	private static String couponAutoId;
	private static String couponHandId;
	private static String marketPlanNumber;
	private static String couponRuleId;
	@Before
	public void setUp() throws Exception {
	marketPlanDaoImpl = (IMarketPlanDao) SpringTestHelper.get().getBean(MarketPlanDaoImpl.class);
		
	}
	@Test
	public void testAddMarketplanVOByMarketplan(){
		marketplan = new Marketplan();
		marketplan.setCreateUser("123");
		marketplan.setModifyUser("321");
		marketplan.setPlanName("钟琼的营销计划");
		marketplan.setMarketStatus("20");
		marketplan.setCouponType("20");
		marketPlanDaoImpl.addMarketplanVOByMarketplan(marketplan);
		marketplanId = marketplan.getId();
	}
	@Test
	public void testAddMarketplanVOByRuleCouponHand(){
		RuleCouponHand ruleCouponHand = new RuleCouponHand();
		ruleCouponHand.setCouponQuantity("1000");
		ruleCouponHand.setCouponValue("100");
		ruleCouponHand.setMarketPlanId(marketplanId);
		ruleCouponHand.setTypeId("10");
		marketPlanDaoImpl.addMarketplanVOByRuleCouponHand(ruleCouponHand);
	}
	@Test
	public void testAddMarketplanVOByRuleCouponAuto(){
		RuleCouponAuto ruleCouponAuto = new RuleCouponAuto();
		ruleCouponAuto.setMarketPlanId(marketplanId);
		ruleCouponAuto.setTypeId("20");
		ruleCouponAuto.setAutoBeginTime(new Date());
		ruleCouponAuto.setAutoEndTime(new Date());
		marketPlanDaoImpl.addMarketplanVOByRuleCouponAuto(ruleCouponAuto);
		couponAutoId = ruleCouponAuto.getId();
	}
	@Test
	public void testAddMarketplanVOByRuleCouponRule(){
		CouponRule couponRule = new CouponRule();
		couponRule.setBegintime(new Date());
		couponRule.setEndtime(new Date());
		couponRule.setMarketPlanId(marketplanId);
		couponRule.setCostMode("1");
		couponRule.setCostAddedType("2");
		couponRule.setCostType("10");
		couponRule.setValue("100");
		couponRule.setSmsContent("what?");
		couponRule.setDescribe("helloworld!");
		marketPlanDaoImpl.addMarketplanVOByRuleCouponRule(couponRule);
	}
	@Test
	public void testAddMarketplanVOByConditionType(){
		ConditionType conditionType = new ConditionType();
		conditionType.setRuleType("10");
		conditionType.setCouponAutoId("110");
		conditionType.setType("10");
		conditionType.setValue("11");
		marketPlanDaoImpl.addMarketplanVOByConditionType(conditionType);
	}
	@Test
	public void testAddMarketplanVOByGoodsLine(){
		GoodsLine goodsLine = new GoodsLine();
		goodsLine.setRuleType("10");
		goodsLine.setCouponAutoId("110");
		goodsLine.setBeginDeptOrCityId("110");
		goodsLine.setBeginDeptOrCityName("匪警");
		goodsLine.setEndDeptOrCityId("119");
		goodsLine.setEndDeptOrCityName("火警");
		goodsLine.setRegdemand("112");
		marketPlanDaoImpl.addMarketplanVOByGoodsLine(goodsLine);
	}
	@Test
	public void testSearchMarketPlan(){
		Marketplan marketplan = new Marketplan();
		marketplan.setPlanName("琼");
		List<Marketplan> list = marketPlanDaoImpl.searchMarketPlan(marketplan, 1, 20);
		Assert.assertEquals(true, null!=list&&0!=list.size());
	}
	@Test
	public void testSearchMarketPlanDetail(){
		marketPlanDaoImpl.searchMarketPlanDetail(marketplanId);
		

	}
	@Test
	public void testSearchRuleCouponHand(){
		marketPlanDaoImpl.searchRuleCouponHand(marketplanId);
	}
	@Test
	public void testSearchRuleCouponAuto(){
		marketPlanDaoImpl.searchRuleCouponAuto(marketplanId);
	}
	@Test
	public void testSearchRuleCPAByConditionTypes(){
		String ruleCouponAutoType = MarketingCouponConstance.CONDITIONTYPE_COUPONSEND;
		marketPlanDaoImpl.searchRuleCPAByConditionTypes("110",ruleCouponAutoType);
	}
	@Test
	public void testSearchRuleCPAByautoCouponCost(){
		marketPlanDaoImpl.searchRuleCPAByautoCouponCost("110");
	}
	@Test
	public void testSearchRuleCPAByGoodsLine(){
		String ruleCouponAutoType = MarketingCouponConstance.CONDITIONTYPE_COUPONSEND;
		List<GoodsLine> goodsLine = marketPlanDaoImpl.searchRuleCPAByGoodsLine("110", ruleCouponAutoType);
		System.out.println(goodsLine);
	}
	
	@Test
	public void testGetCouponByMarketPlanId(){
		marketPlanDaoImpl.getCouponByMarketPlanId(marketplanId);
		
	}
	@Test
	public void testGetCouponTypeByMarketPlanId(){
		String marketplanId = "MP20121120004";
		//String cr = marketPlanDaoImpl.getCouponTypeByMarketPlanId(marketplanId);
		//Assert.assertNotNull(cr);
	}
	@Test
	public void testSearchMarketPlanCount(){
		Marketplan marketPlan = new Marketplan();
		marketPlan.setPlanNumber("110");
		String count= marketPlanDaoImpl.searchMarketPlanCount(marketPlan);
		}
	@Test
	public void testUpdateMarketPlan(){
		String marketplanCode = MarketingCouponConstance.MARKETPLAN_USING;
		Marketplan marketplan = new Marketplan();
		marketplan.setPlanNumber("110");
		marketplan.setMarketStatus(marketplanCode);
		marketPlanDaoImpl.updateMarketPlan(marketplan);
	}
	@Test
	public void testUpdateCouponHand(){
		RuleCouponHand ruleCouponHand =new RuleCouponHand();
		ruleCouponHand.setId("11");
		ruleCouponHand.setTypeId("AUTOCUOPON");
		marketPlanDaoImpl.updateCouponHand(ruleCouponHand);
	}
	@Test
	public void testUpdateAutoConditionTypes(){
		List<ConditionType> conditionType1 = new ArrayList<ConditionType>();
		ConditionType conditionType11 = new ConditionType();
		conditionType11.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		conditionType11.setValue("物流");
		conditionType1.add(conditionType11);
		marketPlanDaoImpl.updateAutoConditionTypes(conditionType1,"110");
	}
	@Test
	public void testUpdateRuleConditionTypes(){
		List<ConditionType> conditionType1 = new ArrayList<ConditionType>();
		ConditionType conditionType11 = new ConditionType();
		conditionType11.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		conditionType11.setValue("物流");
		conditionType1.add(conditionType11);
		marketPlanDaoImpl.updateRuleConditionTypes(conditionType1,"110");
	}
	@Test
	public void testUpdateAutoCouponCost(){
		List<AutoCouponCost> autoCouponCost1 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost11 = new AutoCouponCost();
		autoCouponCost11.setCostType("运费");
		autoCouponCost11.setCostDown("100");
		autoCouponCost11.setCoupoCost("50");
		autoCouponCost1.add(autoCouponCost11);
		marketPlanDaoImpl.updateAutoCouponCost(autoCouponCost1,"110");
	}
	@Test
	public void testUpdateAutoCreateGoodsLine(){
		List<GoodsLine> goodsLines1 = new ArrayList<GoodsLine>();
		GoodsLine goodsLine11= new GoodsLine();
		goodsLine11.setRegdemand("走货线路");
		goodsLine11.setBeginDeptOrCityId("长沙");
		goodsLine11.setEndDeptOrCityId("上海");
		goodsLine11.setCouponAutoId(couponAutoId);
		goodsLine11.setEndDeptOrCityName("上海");
		goodsLines1.add(goodsLine11);
		marketPlanDaoImpl.updateAutoCreateGoodsLine(goodsLines1,"110");
	}
	@Test
	public void testUpdateRuleCreateGoodsLine(){
		List<GoodsLine> goodsLines1 = new ArrayList<GoodsLine>();
		GoodsLine goodsLine11= new GoodsLine();
		goodsLine11.setRegdemand("走货线路");
		goodsLine11.setBeginDeptOrCityId("长沙");
		goodsLine11.setEndDeptOrCityId("上海");
		goodsLine11.setCouponAutoId(couponAutoId);
		goodsLines1.add(goodsLine11);
		marketPlanDaoImpl.updateRuleCreateGoodsLine(goodsLines1,"110");
	}
	@Test
	public void tesUpdateCouponRule(){
		CouponRule couponRule = new CouponRule();
		couponRule.setDescribe("110");
		marketPlanDaoImpl.updateCouponRule(couponRule);
	}
	@Test
	public void tesDeleteCouponHand(){
		marketPlanDaoImpl.deleteCouponHand("110");
	}
	@Test
	public void tesDeleteCouponAuto(){
		marketPlanDaoImpl.deleteCouponAuto("110");
	}
	@Test
	public void tesDeleteConditionTypes(){
		marketPlanDaoImpl.deleteConditionTypes("110","110");
	}
	@Test
	public void tesDeleteCouponCost(){
		marketPlanDaoImpl.deleteCouponCost("110","110");
	}
	@Test
	public void tesDeleteCreateGoodsLine(){
		marketPlanDaoImpl.deleteCreateGoodsLine("110","110");
	}
	@Test
	public void tesDeleteCouponRule(){
		marketPlanDaoImpl.deleteCouponRule("110");
	}
	@Test
	public void tesUpdateMarketPlanForSchedual(){
		marketPlanDaoImpl.updateMarketPlanForSchedual();
	}
	@Test
	public void tesUpdateCouponStatesByOverdueForSchedual(){
		marketPlanDaoImpl.updateCouponStatesByOverdueForSchedual();
	}
	@Test
	public void testUpdateCouponAuto(){
		RuleCouponAuto ruleCouponAuto = new RuleCouponAuto();
		ruleCouponAuto.setMarketPlanId(marketplanId);
		ruleCouponAuto.setTypeId("20");
		ruleCouponAuto.setAutoBeginTime(new Date());
		ruleCouponAuto.setAutoEndTime(new Date());
		marketPlanDaoImpl.updateCouponAuto(ruleCouponAuto);
	}
	@Test
	public void testDeleteMarketPlan(){
		marketPlanDaoImpl.deleteMarketPlan(marketplanId);
	}
	@Test
	public void testSearchHandMarketPlanByCouponTypeAndTypeId(){
		marketPlanDaoImpl.searchHandMarketPlanByCouponTypeAndTypeId(
				MarketingCouponConstance.COUPON_SENDHAND, 
				MarketingCouponConstance.COUPON_LARGERHAND);
	}
	@Test
	public void testSearchMPNameMarketPlanByDeptStandardCodeAndActivityType(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deptStCode", "DP00489");
		map.put("activityType", "NEWOPEN");
		map.put("beginTime", new Date());
		marketPlanDaoImpl.searchMPNameMarketPlanByDeptStandardCodeAndActivityType(map);
	}
}
