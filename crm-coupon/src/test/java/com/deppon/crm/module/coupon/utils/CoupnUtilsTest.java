package com.deppon.crm.module.coupon.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;

public class CoupnUtilsTest {
	@Test
	public void testProduceErrMsg(){
		CouponUtils.produceErrMsg(CouponExceptionType.couponIsntExsit, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponIsOverdue, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponIsUsed, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponIneffectiveDate, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponAmountType, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponAppreciationType, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponProductType, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponOrderSource, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponCustLevel, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponCustTrade, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponLineAreaType, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.couponAmountTypeException, new Object());
		CouponUtils.produceErrMsg(CouponExceptionType.AutoCouponCostTypeIsNull, new Object());
		
	}
	@Test
	public void testAmountInfoToMap(){
		List<AmountInfo> aiList = new ArrayList<AmountInfo>();
		AmountInfo ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_PACKAGINGFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_INSURANCEFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_CODFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_CODFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode(MarketingCouponConstance.COST_TYPE_PICKUPFEE);
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		ai = new AmountInfo();
		ai.setValuationCode("123");
		ai.setValuationAmonut(new BigDecimal("123"));
		aiList.add(ai);
		
		CouponUtils.amountInfoToMap(aiList);
		
	}
	
	@Test
	public void testConditionTypeToMap(){
		List<ConditionType> list = new ArrayList<ConditionType>();
		
		ConditionType c = new ConditionType();
		c.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		c.setValue("123");
		list.add(c);
		
		c = new ConditionType();
		c.setType(MarketingCouponConstance.BIND_TYPE_ORDER);
		c.setValue("123");
		list.add(c);
		
		c = new ConditionType();
		c.setType(MarketingCouponConstance.BIND_TYPE_TRADE);
		c.setValue("123");
		list.add(c);
		
		c = new ConditionType();
		c.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		c.setValue("123");
		list.add(c);
		
		c = new ConditionType();
		c.setType("123");
		c.setValue("123");
		list.add(c);
		
		CouponUtils.conditionTypeToMap(list);
		
	}
	@Test
	public void testDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(1362982270000L);
		System.out.println(sdf.format(d));
		d = new Date(1362983012887L);
		System.out.println(sdf.format(d));
	}
}
