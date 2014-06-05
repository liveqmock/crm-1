package com.deppon.crm.module.coupon.Manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.manager.impl.CouponForSchedualImpl;
import com.deppon.crm.module.coupon.server.manager.impl.CouponManagerImpl;
import com.deppon.crm.module.coupon.server.service.ICouponService;
import com.deppon.crm.module.coupon.server.service.impl.CouponServiceImpl;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.CouponTypeVo;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.coupon.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

public class CouponManagerTest {
	private  ICouponManager couponManager;
	private  ICouponService couponServiceImpl;
	private static MarketPlaneVO marketPlaneVO = new MarketPlaneVO();
	private static User user = new User();
	private static String marketPlanId;
	private static String marketPlanNumber = "MP20121203025";
	private static List<ConditionType> conditionType1;
	private static List<ConditionType> conditionType2;
	static{
		
	}
	@Before
	public void setUp() throws Exception {
		couponManager = (ICouponManager) SpringTestHelper.get().getBean(CouponManagerImpl.class);
		couponServiceImpl = (ICouponService) SpringTestHelper.get().getBean(CouponServiceImpl.class);
		Employee employee = new Employee();
		employee.setId("110");
		user.setId("22207");
		user.setEmpCode(employee);
		Department d = new Department();
		d.setStandardCode("123");
		employee.setDeptId(d);
		Marketplan marketPlan = new Marketplan();
		marketPlan.setPlanName("新建营销计划-测试用例5");
		marketPlan.setCouponType(MarketingCouponConstance.COUPON_SENDAUTO);
		//marketPlan.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		marketPlaneVO.setMarketplan(marketPlan);
		conditionType1 = new ArrayList<ConditionType>();
		ConditionType conditionType11 = new ConditionType();
		conditionType11.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		conditionType11.setValue("物流");
		conditionType1.add(conditionType11);
		ConditionType conditionType12 = new ConditionType();
		conditionType12.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		conditionType12.setValue("强等级");
		conditionType1.add(conditionType12);
		ConditionType conditionType13 = new ConditionType();
		conditionType13.setType(MarketingCouponConstance.BIND_TYPE_ORDER);
		conditionType13.setValue("官网");
		conditionType1.add(conditionType13);
		ConditionType conditionType14 = new ConditionType();
		conditionType14.setType(MarketingCouponConstance.BIND_TYPE_TRADE);
		conditionType14.setValue("IT行业");
		conditionType1.add(conditionType14);
		
		conditionType2 = new ArrayList<ConditionType>();
		ConditionType conditionType21 = new ConditionType();
		conditionType21.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		conditionType21.setValue("物流");
		conditionType2.add(conditionType21);
		ConditionType conditionType22 = new ConditionType();
		conditionType22.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		conditionType22.setValue("强等级");
		conditionType2.add(conditionType22);
		ConditionType conditionType23 = new ConditionType();
		conditionType23.setType(MarketingCouponConstance.BIND_TYPE_ORDER);
		conditionType23.setValue("官网");
		conditionType2.add(conditionType13);
		ConditionType conditionType24 = new ConditionType();
		conditionType24.setType(MarketingCouponConstance.BIND_TYPE_TRADE);
		conditionType24.setValue("IT行业");
		conditionType2.add(conditionType24);
		
		
		List<AutoCouponCost> autoCouponCost1 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost11 = new AutoCouponCost();
		autoCouponCost11.setCostType("运费");
		autoCouponCost11.setCostDown("100");
		autoCouponCost11.setCoupoCost("50");
		autoCouponCost1.add(autoCouponCost11);
		
		
		List<GoodsLine> goodsLines1 = new ArrayList<GoodsLine>();
		GoodsLine goodsLine11= new GoodsLine();
		goodsLine11.setRegdemand("走货线路");
		goodsLine11.setBeginDeptOrCityId("长沙");
		goodsLine11.setEndDeptOrCityId("上海");
		goodsLines1.add(goodsLine11);
		
		List<GoodsLine> goodsLines2 = new ArrayList<GoodsLine>();
		GoodsLine goodsLine22= new GoodsLine();
		goodsLine22.setRegdemand("走货线路");
		goodsLine22.setBeginDeptOrCityId("长沙");
		goodsLine22.setEndDeptOrCityId("北京");
		goodsLines2.add(goodsLine22);
		
		RuleCouponAuto ruleCouponAuto = new RuleCouponAuto();
		DateFormat parseTime=new SimpleDateFormat("yyyy-MM-dd");
		ruleCouponAuto.setAutoBeginTime(parseTime.parse("2014-11-01"));
		ruleCouponAuto.setAutoEndTime(parseTime.parse("2014-12-01"));
		ruleCouponAuto.setConditionTypes(conditionType1);
		ruleCouponAuto.setAutoCouponCost(autoCouponCost1);
		ruleCouponAuto.setCreateGoodsLine(goodsLines1);
		marketPlaneVO.setRuleCouponAuto(ruleCouponAuto);
		
		CouponRule couponRule = new CouponRule();
		couponRule.setBegintime(parseTime.parse("2014-11-01"));
		couponRule.setEndtime(parseTime.parse("2014-12-01"));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		couponRule.setGoodsLines(goodsLines2);
		couponRule.setConditionTypes(conditionType2);
		marketPlaneVO.setCouponRule(couponRule);
		couponManager.createMarketPlanCoupon(marketPlaneVO, user);
		marketPlanId = marketPlaneVO.getMarketplan().getId();
	}
	@Test
	public void testMatchMarketPlanByConditionType1(){
		couponManager.matchMarketPlanByConditionType(conditionType1, conditionType2);
	}
	@Test
	public void testMatchMarketPlanByConditionType2(){
		CouponTypeVo couponTypeVos = new CouponTypeVo();
		String[] makeProductTypes =new String[]{"类型1"};//产品类型
		String[] makeOrderSourceTypes = new String[]{"类型1"};//订单来源
		String[] makeCustomerLevelTypes = new String[]{"类型1"};//客户等级
		String[] makeCustomerTradeTypes = new String[]{"类型1"};//客户行业
		String[] useProductTypesUse = new String[]{"类型1"};//产品类型
		String[] useOrderSourceTypesUse = new String[]{"类型1"};//订单来源
		String[] useCustomerLevelTypesUse = new String[]{"类型1"};//客户等级
		String[] useCustomerTradeTypesUse = new String[]{"类型1"};//客户行业
		couponTypeVos.setMakeCustomerLevelType(makeCustomerLevelTypes);
		couponTypeVos.setMakeCustomerTradeType(makeCustomerTradeTypes);
		couponTypeVos.setMakeOrderSourceType(makeOrderSourceTypes);
		couponTypeVos.setMakeProductType(makeProductTypes);
		couponTypeVos.setUseCustomerLevelType(useCustomerLevelTypesUse);
		couponTypeVos.setUseCustomerTradeType(useCustomerTradeTypesUse);
		couponTypeVos.setUseOrderSourceType(useOrderSourceTypesUse);
		couponTypeVos.setUseProductType(useProductTypesUse);
		couponManager.matchMarketPlanByConditionType(couponTypeVos);
	}
	@Test
	public void testSetListConditionType(){
		StringBuffer bufUseProductTypesUse = new StringBuffer();//产品类型
		StringBuffer bufUseOrderSourceTypesUse = new StringBuffer();//订单来源
		StringBuffer bufUseCustomerLevelTypesUse = new StringBuffer();//客户等级
		StringBuffer bufUseCustomerTradeTypesUse = new StringBuffer();//客户行业
		String makeProductTypeId = MarketingCouponConstance.BIND_TYPE_PRODUCT;//产品类型
		String makeOrderSourceTypeId = MarketingCouponConstance.BIND_TYPE_ORDER;//订单来源
		String makeCustomerLevelTypeId = MarketingCouponConstance.BIND_TYPE_LEVEL;//客户等级
		String makeCustomerTradeTypeId = MarketingCouponConstance.BIND_TYPE_TRADE;//客户行业
		String[] bufTypesId = {makeProductTypeId,makeOrderSourceTypeId,makeCustomerLevelTypeId,makeCustomerTradeTypeId};
		((CouponManagerImpl) couponManager).setListConditionType(bufTypesId,conditionType1, bufUseProductTypesUse, 
				bufUseOrderSourceTypesUse, bufUseCustomerLevelTypesUse,bufUseCustomerTradeTypesUse);
	}
	@Test 
	public void testsetCouponTypeVo(){
		String[] makeProductTypes =new String[]{"类型1"};//产品类型
		String useType = MarketingCouponConstance.CONDITIONTYPE_COUPONUSE;
		String makeProductTypeId = MarketingCouponConstance.BIND_TYPE_PRODUCT;//产品类型
		List<ConditionType> conditionTypeUseList = new ArrayList<ConditionType>();
		((CouponManagerImpl) couponManager).setCouponTypeVo(makeProductTypes, useType, makeProductTypeId, conditionTypeUseList);
	}
	@Test
	public void testUpdateCouponStatus(){
		String couponCode="10";
		List<String> couponNums=new ArrayList<String>();
		List<String> couponNums1=new ArrayList<String>();
		couponNums.add("1353401741818");
		couponManager.updateCouponStatus(couponCode, couponNums);
		couponManager.updateCouponStatus("", null);
		couponManager.updateCouponStatus(couponCode, null);
		couponManager.updateCouponStatus(couponCode, couponNums1);
	}
	
	@Test
	public void testUpdateOneCouponStatu(){
		String couponCode = "10";
		String couponNum = "1353325158578";
		couponManager.updateOneCouponStatu(couponCode, couponNum);
		couponManager.updateOneCouponStatu(couponCode, null);
		couponManager.updateOneCouponStatu(null, null);
		couponManager.updateOneCouponStatu(null, couponNum);
	}
	
	@Test
	public void testSearchCouponsByNums(){
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		list.add("1353401741817");
		list.add("1353401741818");
		couponManager.searchCouponsByNums(list);
		couponManager.searchCouponsByNums(null);
		couponManager.searchCouponsByNums(list1);
}
	
	@Test
	public void testGetCouponByNum(){
		String counum = "1353401741817";
		Coupon coupon=couponManager.getCouponByNum(counum);
		counum = "";
		couponManager.getCouponByNum(counum);
	}
	
	@Test
	public void testCreateMarketPlanCoupon() throws ParseException{
		couponManager.createMarketPlanCoupon(marketPlaneVO, user);
		marketPlaneVO.setRuleCouponHand(null);
		couponManager.createMarketPlanCoupon(marketPlaneVO, user);

	}
	@Test
	public void testSearchMarketPlanDetail(){
		marketPlaneVO = couponManager.searchMarketPlanDetail(marketPlanId);
		couponManager.searchMarketPlanDetail("");
		couponManager.searchMarketPlanDetail("1");
		couponManager.searchMarketPlanDetail("292");
	}
	@Test
	public void testSearchMarketPlan(){
		Marketplan marketplan = new Marketplan();
		marketplan.setPlanNumber(marketPlanNumber);
		DateFormat parseTime=new SimpleDateFormat("yyyy-MM-dd");
		try {
			marketplan.setCreateBeginTime(parseTime.parse("2012-01-01"));
			marketplan.setCreateEndTime(parseTime.parse("2012-12-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String,Object> marketplans = couponManager.searchMarketPlan(user,marketplan, 0, 20);
}
	
	@Test
	public void testUpdateMarketPlan(){
		marketPlaneVO.getMarketplan().setPlanNumber(marketPlanNumber);
		marketPlaneVO.getMarketplan().setId("1");
		marketPlaneVO.getRuleCouponAuto().setId("42");
		marketPlaneVO.getCouponRule().setId("61");
		couponManager.updateMarketPlan(marketPlaneVO,user);
	}
	@Test
	public void testStartMarketPlanCoupon1() throws ParseException{
		couponManager.startMarketPlanCoupon(marketPlaneVO, user);
		MarketPlaneVO marketPlaneVO1 = new MarketPlaneVO();
		Employee employee = new Employee();
		employee.setId("110");
		user.setId("22207");
		user.setEmpCode(employee);
		Marketplan marketPlan = new Marketplan();
		marketPlan.setPlanName("新建营销计划-测试用例5");
		marketPlaneVO1.setMarketplan(marketPlan);
		List<AutoCouponCost> autoCouponCost1 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost11 = new AutoCouponCost();
		autoCouponCost11.setCostType("运费");
		autoCouponCost11.setCostDown("100");
		autoCouponCost11.setCoupoCost("50");
		autoCouponCost1.add(autoCouponCost11);
		
		DateFormat parseTime=new SimpleDateFormat("yyyy-MM-dd");
		
		CouponRule couponRule = new CouponRule();
		couponRule.setBegintime(parseTime.parse("2014-11-01"));
		couponRule.setEndtime(parseTime.parse("2014-12-01"));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		marketPlaneVO1.setCouponRule(couponRule);
		RuleCouponHand rc = new RuleCouponHand();
		marketPlaneVO1.getMarketplan().setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		marketPlaneVO1.getMarketplan().setMarketStatus("10");
		rc.setCouponQuantity("10");
		rc.setCouponValue("100");
		marketPlaneVO1.setRuleCouponHand(rc);
		couponManager.createMarketPlanCoupon(marketPlaneVO1, user);
		couponManager.startMarketPlanByDetail(marketPlaneVO1, user);
		MarketPlaneVO marketPlaneVO2 = new MarketPlaneVO();
		employee = new Employee();
		employee.setId("110");
		user.setId("22207");
		user.setEmpCode(employee);
		marketPlan = new Marketplan();
		marketPlan.setPlanName("新建营销计划-测试用例5");
		marketPlaneVO2.setMarketplan(marketPlan);
		 autoCouponCost1 = new ArrayList<AutoCouponCost>();
		 autoCouponCost11 = new AutoCouponCost();
		autoCouponCost11.setCostType("运费");
		autoCouponCost11.setCostDown("100");
		autoCouponCost11.setCoupoCost("50");
		autoCouponCost1.add(autoCouponCost11);
		
		parseTime=new SimpleDateFormat("yyyy-MM-dd");
		
		couponRule = new CouponRule();
		couponRule.setBegintime(parseTime.parse("2014-11-01"));
		couponRule.setEndtime(parseTime.parse("2014-12-01"));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		marketPlaneVO2.setCouponRule(couponRule);
		rc = new RuleCouponHand();
		marketPlaneVO2.getMarketplan().setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		marketPlaneVO2.getMarketplan().setMarketStatus("10");
		rc.setCouponQuantity("10001");
		rc.setCouponValue("100");
		marketPlaneVO2.setRuleCouponHand(rc);
		couponManager.createMarketPlanCoupon(marketPlaneVO2, user);
		
		couponManager.startMarketPlanCoupon(marketPlaneVO2, user);
	}
	@Test
	public void testGetCouponRuleByMarketPlanId(){
		couponManager.getCouponRuleByMarketPlanId(marketPlanId);
		couponManager.getCouponRuleByMarketPlanId("");
	}
	@Test
	public void testStartMarketPlan() throws ParseException{
		couponManager.startMarketPlanByDetail(marketPlaneVO, user);
		MarketPlaneVO marketPlaneVO1 = new MarketPlaneVO();
		Employee employee = new Employee();
		employee.setId("110");
		user.setId("22207");
		user.setEmpCode(employee);
		Marketplan marketPlan = new Marketplan();
		marketPlan.setPlanName("新建营销计划-测试用例5");
		marketPlaneVO1.setMarketplan(marketPlan);
		List<AutoCouponCost> autoCouponCost1 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost11 = new AutoCouponCost();
		autoCouponCost11.setCostType("运费");
		autoCouponCost11.setCostDown("100");
		autoCouponCost11.setCoupoCost("50");
		autoCouponCost1.add(autoCouponCost11);
		
		DateFormat parseTime=new SimpleDateFormat("yyyy-MM-dd");
		
		CouponRule couponRule = new CouponRule();
		couponRule.setBegintime(parseTime.parse("2014-11-01"));
		couponRule.setEndtime(parseTime.parse("2014-12-01"));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		marketPlaneVO1.setCouponRule(couponRule);
		RuleCouponHand rc = new RuleCouponHand();
		marketPlaneVO1.getMarketplan().setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		marketPlaneVO1.getMarketplan().setMarketStatus("10");
		rc.setCouponQuantity("10");
		rc.setCouponValue("100");
		marketPlaneVO1.setRuleCouponHand(rc);
		couponManager.createMarketPlanCoupon(marketPlaneVO1, user);
		couponManager.startMarketPlanByDetail(marketPlaneVO1, user);
		MarketPlaneVO marketPlaneVO2 = new MarketPlaneVO();
		employee = new Employee();
		employee.setId("110");
		user.setId("22207");
		user.setEmpCode(employee);
		marketPlan = new Marketplan();
		marketPlan.setPlanName("新建营销计划-测试用例5");
		marketPlaneVO2.setMarketplan(marketPlan);
		 autoCouponCost1 = new ArrayList<AutoCouponCost>();
		 autoCouponCost11 = new AutoCouponCost();
		autoCouponCost11.setCostType("运费");
		autoCouponCost11.setCostDown("100");
		autoCouponCost11.setCoupoCost("50");
		autoCouponCost1.add(autoCouponCost11);
		
		parseTime=new SimpleDateFormat("yyyy-MM-dd");
		
		couponRule = new CouponRule();
		couponRule.setBegintime(parseTime.parse("2014-11-01"));
		couponRule.setEndtime(parseTime.parse("2014-12-01"));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		marketPlaneVO2.setCouponRule(couponRule);
		rc = new RuleCouponHand();
		marketPlaneVO2.getMarketplan().setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		marketPlaneVO2.getMarketplan().setMarketStatus("10");
		rc.setCouponQuantity("10001");
		rc.setCouponValue("100");
		marketPlaneVO2.setRuleCouponHand(rc);
		couponManager.createMarketPlanCoupon(marketPlaneVO2, user);
		
		couponManager.startMarketPlanByDetail(marketPlaneVO2, user);
		
	}
	@Test
	public void testStopMarketPlan(){
		couponManager.stopMarketPlan("", user);
		Marketplan marketPlan = new Marketplan();
		marketPlan.setId("285");
		marketPlan.setMarketStatus("20");
		couponServiceImpl.updateMarketPlan(marketPlan);
		couponManager.stopMarketPlan("285", user);
		try{
			marketPlan = new Marketplan();
			marketPlan.setId("285");
			marketPlan.setMarketStatus("10");
			couponServiceImpl.updateMarketPlan(marketPlan);
			couponManager.stopMarketPlan("285", user);
			}catch(Exception e){System.out.println(e.getMessage());}
		try{
			couponManager.stopMarketPlan("264", user);
			}catch(Exception e){System.out.println(e.getMessage());}
		
	}
	
	@Test
	public void testUpdateMarketPlanForSchedual(){
		couponManager.updateMarketPlanForSchedual();
	}
	@Test
	public void testqueryDeptListByDeptName(){
		couponManager.queryDeptListByDeptName(user, "上海", 0, 10);
	}
	@Test
	public void testUpdateCouponStatesByOverdueForSchedual(){
		couponManager.updateCouponStatesByOverdueForSchedual();
	}
	@Test
	public void testSearchCouponRuleByMarketPlanIdDetail(){
		couponManager.searchCouponRuleByMarketPlanIdDetail(marketPlanId);
		couponManager.searchCouponRuleByMarketPlanIdDetail("");
	}
	@Test
	public void testUpdateMarketPlanStatus(){
		couponManager.updateMarketPlanStatus(MarketingCouponConstance.MARKETPLAN_NOUSE,marketPlanNumber);
		couponManager.updateMarketPlanStatus("","");
		couponManager.updateMarketPlanStatus("",marketPlanNumber);
		couponManager.updateMarketPlanStatus(MarketingCouponConstance.MARKETPLAN_NOUSE,"");
		
	}
	@Test
	public void testDeleteMarketPlan() throws ParseException{
		couponManager.deleteMarketPlan(marketPlanId);
		MarketPlaneVO marketPlaneVO2 = new MarketPlaneVO();
		Employee employee = new Employee();
		employee.setId("110");
		user.setId("22207");
		user.setEmpCode(employee);
		Marketplan marketPlan = new Marketplan();
		marketPlan.setPlanName("新建营销计划-测试用例5");
		marketPlaneVO2.setMarketplan(marketPlan);
		
		SimpleDateFormat parseTime=new SimpleDateFormat("yyyy-MM-dd");
		
		CouponRule couponRule = new CouponRule();
		couponRule.setBegintime(parseTime.parse("2014-11-01"));
		couponRule.setEndtime(parseTime.parse("2014-12-01"));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		marketPlaneVO2.setCouponRule(couponRule);
		RuleCouponHand rc = new RuleCouponHand();
		marketPlaneVO2.getMarketplan().setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		marketPlaneVO2.getMarketplan().setMarketStatus("10");
		rc.setCouponQuantity("10001");
		rc.setCouponValue("100");
		marketPlaneVO2.setRuleCouponHand(rc);
		couponManager.createMarketPlanCoupon(marketPlaneVO2, user);
		couponManager.deleteMarketPlan(marketPlaneVO2.getMarketplan().getId());
	}
	@Test
	public void testSearchCouponByCondition(){
		User user = new User();
		Employee e = new Employee();
		user.setEmpCode(e);
		Department d = new Department();
		d.setStandardCode("123");
		e.setEmpCode("11111");
		e.setDeptId(d);
		CouponSearchCondition condition = new CouponSearchCondition();
		condition.setSendTimeStart(new Date());
		condition.setUseTimeStart(new Date());
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		//条件1
		condition = new CouponSearchCondition();
		condition.setUseTimeStart(new Date());
	
		try{
		couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		condition.setUseTimeStart(null);
		condition.setUseTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition.setUseTimeEnd(null);
		condition.setSendTimeStart(new Date());
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition.setSendTimeStart(null);
		condition.setSendTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition.setSendTimeStart(new Date());
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition.setSendTimeStart(null);
		condition.setSendTimeEnd(null);
		condition.setUseTimeStart(new Date());
		condition.setUseTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition.setSendTimeStart(new Date());
		condition.setSendTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition = new CouponSearchCondition();
		condition.setSendTimeStart(new Date());
		condition.setSendTimeEnd(new Date(1000*60*60*24*50));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition = new CouponSearchCondition();
		condition.setUseTimeStart(new Date());
		condition.setUseTimeEnd(new Date(1000*60*60*24*50));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition = new CouponSearchCondition();
		condition.setSendTimeStart(new Date());
		condition.setSendTimeEnd(new Date(1000*60*60*24*50));
		condition.setUseTimeStart(new Date());
		condition.setUseTimeEnd(new Date(1000*60*60*24*50));
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition = new CouponSearchCondition();
		condition.setPlanNumber("123222");
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
		}catch(Exception ex){
				ex.printStackTrace();
		}
		condition.setUseTimeStart(new Date());
		try{
			couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			condition.setUseTimeStart(null);
			condition.setUseTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(new Date());
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition.setSendTimeStart(new Date());
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(null);
			condition.setUseTimeStart(new Date());
			condition.setUseTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition.setSendTimeStart(new Date());
			condition.setSendTimeEnd(new Date(System.currentTimeMillis()+1000*60*60*24*30));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition = new CouponSearchCondition();
			condition.setSendTimeStart(new Date());
			condition.setSendTimeEnd(new Date(1000*60*60*24*50));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition = new CouponSearchCondition();
			condition.setUseTimeStart(new Date());
			condition.setUseTimeEnd(new Date(1000*60*60*24*50));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
			condition = new CouponSearchCondition();
			condition.setSendTimeStart(new Date());
			condition.setSendTimeEnd(new Date(1000*60*60*24*50));
			condition.setUseTimeStart(new Date());
			condition.setUseTimeEnd(new Date(1000*60*60*24*50));
			try{
				couponManager.searchCouponByCondition(condition, 0, 100, user);
			}catch(Exception ex){
					ex.printStackTrace();
			}
		//条件二
		
	}
	@Test
	public void testReSendCouponMsg(){
		Coupon c = new Coupon();
//		c.setCouponNumber(CouponUtils.randomCoupon());
		c.setCouponNumber(new Date().getTime()+"");
		c.setMarketPlanId("43");
		c.setTypeId(MarketingCouponConstance.COUPON_SENDHAND);
		c.setCreateruleId("23");
		c.setUseruleId("23");
		c.setStatus(MarketingCouponConstance.COUPON_SENDING);
		c.setUnderDept("xxxx");
		c.setSendtelPhone("13812345678");
		c.setUsetelPhone("13812345678");
		c.setSourceWBNumber("Order0001");
		c.setSourceWBValue("1111112");
		c.setUseWBNumber("Order1001");
		c.setUseWBValue("100");
		c.setUseCouponValue("200");
		couponManager.createCoupon(c);
		User user = new User();
		Employee e = new Employee();
		e.setEmpCode("11111");
		user.setEmpCode(e);
		try{
			couponManager.reSendCouponMsg(c.getCouponNumber(),user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	/*
	@Test
	public void testSendCouponMsg(){
		SendCouponVO scvo = new SendCouponVO();
		scvo.setMarketPlanId(264);
		scvo.setPlanName("测试130");
		scvo.setPlanNumber("MP20121129004");
		scvo.setCouponType("HANDCOUPON");
		scvo.setMarketPlanStatus("10");
		scvo.setBalance(1111);
		scvo.setBeginTime(new Date());
		scvo.setEndTime(new Date(System.currentTimeMillis()+1000000000));
		scvo.setSms("亲爱的用户,您好!");
		scvo.setCouponValue("200");
		scvo.setCouponQuantity(10000);
		scvo.setSendedNum(123);
		User user = new User();
		Employee e = new Employee();
		user.setEmpCode(e);
		e.setEmpCode("11111");
		List<CouponCellphone> phones = new ArrayList<CouponCellphone>();
		List<Coupon> coupons = new ArrayList<Coupon>();
		for(int i = 0; i<2000;i++){
			CouponCellphone p = new CouponCellphone();
			p.setCellphone(10000000000L+new Random().nextInt()+""); 
			p.setSendStatus("0");
			p.setValidity("0");
			Coupon c = new Coupon();
			c.setMarketPlanId("43");
			c.setTypeId(MarketingCouponConstance.COUPON_SENDHAND);
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
			phones.add(p);
			coupons.add(c);
			if(phones.size()>=1000){
				try{
				couponManager.sendCouponMsg(scvo, phones, coupons, user);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				phones = new ArrayList<CouponCellphone>();
				coupons = new ArrayList<Coupon>();
			}
		}
		for(int i=10;i<10;i++){
			long a = new Random().nextInt()+10000000000L;
			CouponCellphone p = new CouponCellphone();
			p.setCellphone(new Long(a).toString());
			Coupon c = new Coupon();
//			c.setCouponNumber(CouponUtils.randomCoupon());
			c.setCouponNumber(new Date().getTime()+"");
			c.setMarketPlanId("43");
			c.setTypeId(MarketingCouponConstance.COUPON_SENDHAND);
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
			phones.add(p);
			coupons.add(c);
		}
		try{
		couponManager.sendCouponMsg(scvo, phones, coupons, user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
//	@Test
//	/**
//	 * 测试插入100万条优惠券 优惠券号码有没有重复的
//	 */
//	public void testCouponNumber(){
//		List<Coupon> coupons = new ArrayList<Coupon>();
//		for(int i = 0; i<1000000;i++){
//			Coupon c = new Coupon();
//			c.setCouponNumber(CouponUtils.randomCoupon());
//			c.setMarketPlanId("43");
//			c.setTypeId(MarketingCouponConstance.COUPON_SENDHAND);
//			c.setCreateruleId("23");
//			c.setUseruleId("23");
//			c.setStatus(MarketingCouponConstance.COUPON_NOSEND);
//			c.setUnderDept("xxxx");
//			c.setSendtelPhone("13812345678");
//			c.setUsetelPhone("13812345678");
//			c.setSourceWBNumber("Order0001");
//			c.setSourceWBValue("1111112");
//			c.setUseWBNumber("Order1001");
//			c.setUseWBValue("100");
//			c.setUseCouponValue("200");
//			coupons.add(c);
//			if(coupons.size()>=1000){
//				couponManager.createCoupon(coupons);
//				coupons = new ArrayList<Coupon>();
//			}
//		}
//	}
	@Test
	public void testSelectCouponByMutiCondition(){
		
		MarketPlaneVO mpvo = new MarketPlaneVO();
		Marketplan mp = new Marketplan();
		mp.setPlanName("创建手动优惠券用于生成excel");
		mp.setMarketStatus(MarketingCouponConstance.MARKETPLAN_NOUSE);
		mp.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		mp.setSaveTime(new Date());
		mp.setCreateDate(new Date());
		mp.setCreateBeginTime(new Date(System.currentTimeMillis() - (long)1000*60*60*24*3));
		mp.setCreateEndTime(new Date(System.currentTimeMillis() +(long)1000*60*60*24*30*2));
		mp.setCreateUser(user.getEmpCode().getId());
		mp.setModifyDate(new Date());
		mp.setModifyUser(user.getEmpCode().getId());
		RuleCouponHand rch = new RuleCouponHand();
		rch.setTypeId(MarketingCouponConstance.COUPON_LARGERHAND);
		rch.setCouponValue("200");
		rch.setCouponQuantity("200");
		CouponRule couponRule = new CouponRule();
		//System.out.println(System.currentTimeMillis()-1000*60*60*24);
		couponRule.setBegintime(new Date(System.currentTimeMillis()));
//		System.out.println("----------------------------------------");
		System.out.println(couponRule.getBegintime()+"---"+couponRule.getBegintime().getTime());
//		System.out.println(System.currentTimeMillis()-(1000*60*60*24));
		couponRule.setEndtime(new Date(System.currentTimeMillis()+(long)1000*60*60*24*30));
//		System.out.println("-------------"+System.currentTimeMillis()+"--"+1000*60*60*24+"--"+1000*60*60*24*30);
//		System.out.println("----------------------------------------");
		System.out.println(couponRule.getEndtime() + "---"+couponRule.getEndtime().getTime());
//		System.out.println(System.currentTimeMillis()+(1000*60*60*24*30));
		couponRule.setCostMode("10");
		couponRule.setCostType("1");
		couponRule.setValue("99");
		couponRule.setDiscount("20");
		couponRule.setUsrType("88");
		couponRule.setCostAddedMode("0");
		couponRule.setSmsContent("yes sms!");
		couponRule.setDescribe("yes discribe!");
		
		mpvo.setMarketplan(mp);
		mpvo.setRuleCouponHand(rch);
		mpvo.setCouponRule(couponRule);
		
		couponManager.startMarketPlanCoupon(mpvo, user);
		
	
		Map<String,Object> map = couponManager.searchMarketPlan(user,mp, 0, 1);
		List<Marketplan> list = (List<Marketplan>)map.get("listMarketPlan");
	//	int count = (Integer)map.get("totalCount");
		CouponForSchedualImpl cfsi = new CouponForSchedualImpl();
		cfsi.setCouponManagerImpl(couponManager);
		cfsi.createCouponHandBackground();
		List<CouponCellphone> phones = new ArrayList<CouponCellphone>();
		for(int i=0;i<20;i++){
			CouponCellphone cp = new CouponCellphone();
			cp.setCellphone("12322223333");
			cp.setSendStatus("0");
			cp.setValidity("0");
			phones.add(cp);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		couponManager.sendCouponByCellphones(list.get(0).getPlanNumber(), phones, user);
		CouponSearchCondition condition = new CouponSearchCondition();
		condition.setPlanName(list.get(0).getPlanName());
		try{
			couponManager.creatCouponReportExcel(condition, "aa.xlsx", "D:\\",user);
		}catch(Exception e){System.out.println(e.getMessage());}
		
		//CouponSearchCondition condition = new CouponSearchCondition();
		//cp.setStatus("20");
//		condition.setStatus("20");
//		condition.setSendtelPhone("123");
//		Map map= couponManager.searchCouponByCondition(condition,0,0,new User());
//		List<CouponResultVO> coupons = (List<CouponResultVO>)map.get("list");
//		for(CouponResultVO couponRes : coupons){
//			//转换Status
//			couponRes.setStatus(
//					MarketingCouponConstance.couponStatusStr.
//					get(couponRes.getStatus()));
//		}
//		CreateExcel.createExcel(MarketingCouponConstance.couponTitle, coupons, "aa.xlsx", "D:\\");
	}
	@Test
	public void testCheckCouponRule(){
		WaybillInfo wbInfo = new WaybillInfo();
		wbInfo.setWaybillNumber("120120230");
//		wbInfo.setOrderNumber("110");
//		wbInfo.setOrderSource("110");
//		wbInfo.setProduceType("110");
//		wbInfo.setTotalAmount(new BigDecimal(118));
		wbInfo.setArrivedDept("DP00358");
		wbInfo.setLeaveDept("DP00358");
		wbInfo.setLeaveOutDept("DP07290");
		wbInfo.setArrivedOutDept("DP07290");
		List<AmountInfo> list = new ArrayList<AmountInfo>();
		AmountInfo a = new AmountInfo();
		a.setValuationAmonut(new BigDecimal(101));
		a.setValuationCode("FRT");
		list.add(a);
		AmountInfo b = new AmountInfo();
		b.setValuationAmonut(new BigDecimal(101));
		b.setValuationCode("SH");
		list.add(b);
		AmountInfo c = new AmountInfo();
		c.setValuationAmonut(new BigDecimal(101));
		c.setValuationCode("BF");
		list.add(c);
		AmountInfo d = new AmountInfo();
		d.setValuationAmonut(new BigDecimal(101));
		d.setValuationCode("RYFJ");
		list.add(d);
		AmountInfo e = new AmountInfo();
		e.setValuationAmonut(new BigDecimal(101));
		e.setValuationCode("ZHXX");
		list.add(e);
		AmountInfo f = new AmountInfo();
		f.setValuationAmonut(new BigDecimal(101));
		f.setValuationCode("SHJCF");
		list.add(f);
		wbInfo.setAmountList(list);
//		String couponNum = "130109309211051";  
		String couponNum = "130122601772339";  
		couponManager.checkCouponRule(couponNum, wbInfo);
		//wbInfo.setCustNumber("110");
		couponManager.checkCouponRule(couponNum, wbInfo);
	}
	@Test
	public void testUpdateCoupon(){
		Coupon coupon = new Coupon();
		coupon.setUsetelPhone("10");
		coupon.setCouponNumber("");
		couponManager.updateCoupon(coupon);
	}
	@Test
	public void testSearchSendCouponVOByMPI(){
		try{
			couponManager.searchSendCouponVOByMPI("MP20121116002");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	@Test
//	public void testBatchImportCellphones(){
//		try{
//			File file = new File("");
//			String fileName = "hello";
//			couponManager.batchImportCellphones(file, fileName);
//		}catch(Exception e){System.out.println(e.getMessage());}
//	}
	@Test
	public void testQueryDeptListByName(){
		couponManager.queryDeptListByName("", 0, 1);
		couponManager.queryDeptListByName("上海", 0, 1);
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
		for(int i = 0 ;i <10 ;i++){
			CouponCellphone c = new CouponCellphone();
			c.setCellphone("1233333333333333");
			c.setSendStatus("0");
			c.setValidity("0");
			phones.add(c);
		}
		couponManager.cleanInvalidCellphones(phones);
		couponManager.deleteRepeatCellphones(phones);
	}
	@Test
	public void testSearchSendCouponMsg(){
		couponManager.searchSendCouponMsg("10",0, 10);
		
	}
	@Test
	public void testCallableSendMsgAuto(){
		couponManager.callableSendMsgAuto(new Date(System.currentTimeMillis()-1000*60*60*24),new Date(),"1111", "d1111");
	}
	@Test
	public void testCreateCouponForInterface(){
		CouponForInterface cfi = new CouponForInterface();
		cfi.setDeptStandardCode("DP00489");
		cfi.setActivityType("NEWOPEN");
		cfi.setBeginTime(new Date());
		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000));
		cfi.setCouponValue("50");
		cfi.setCostMode("2");
		cfi.setCostType("1");
		cfi.setValue("50");
		cfi.setDiscount("100");
		cfi.setCostAddedType("BZ");
		cfi.setCostAdded("20");
		cfi.setProductType("FLF,FSF,LRF");
		cfi.setOrderType("ONLINE,TAOBAO,ALIBABA");
		cfi.setCustLevel("NORMAL,GOLD,PLATINUM");
		cfi.setCustTrade("CLOTH_SPIN,ITRONIC_FURNITURE");
		cfi.setRegdemand("2");
		List<GoodsLine> gs = new ArrayList<GoodsLine>();
		for( int i=0; i< 3;i++){
			GoodsLine g = new GoodsLine();
			g.setBeginDeptOrCityId("DP00461");
			g.setBeginDeptOrCityName("天津河西区营业部");
			g.setEndDeptOrCityId("DP00470");
			g.setEndDeptOrCityName("广州白云区太和营业部");
			gs.add(g);
		}
		cfi.setGoodsLines(gs);
		cfi.setSmsContent("NEWOPEN");
		cfi.setDescribe("NEWOPEN");
		couponManager.createCouponForInterface(cfi);
	}
}
