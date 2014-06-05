package com.deppon.crm.module.coupon.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;

public class CouponUtilsTest {
	@Test
	public void testProduceErrMsg(){
		CouponExceptionType errorType = null;
		errorType = CouponExceptionType.couponAppreciationType;
		String para = "couponValue";
		CouponUtils.produceErrMsg(errorType, para);
		errorType = CouponExceptionType.couponProductType;
		CouponUtils.produceErrMsg(errorType, para);
		errorType = CouponExceptionType.couponOrderSource;
		CouponUtils.produceErrMsg(errorType, para);
		errorType = CouponExceptionType.couponCustLevel;
		CouponUtils.produceErrMsg(errorType, para);
		errorType = CouponExceptionType.couponCustTrade;
		CouponUtils.produceErrMsg(errorType, para);
		errorType= CouponExceptionType.couponLineAreaType;
		CouponUtils.produceErrMsg(errorType, para);
		errorType = CouponExceptionType.onlyNoUseMarketPlanCanEdit;
		CouponUtils.produceErrMsg(errorType, para);
	}
	
	@Test
	public void testAmountInfoToMap(){
		List<AmountInfo> aiList = new ArrayList<AmountInfo>();
		AmountInfo amountInfo = new AmountInfo();
		aiList.add(amountInfo);
		amountInfo.setValuationCode(MarketingCouponConstance.COST_TYPE_PACKAGINGFEE);// 包装费
		CouponUtils.amountInfoToMap(aiList);
		amountInfo.setValuationCode(MarketingCouponConstance.COST_TYPE_INSURANCEFEE);  // 保价费
		CouponUtils.amountInfoToMap(aiList);
		amountInfo.setValuationCode(MarketingCouponConstance.COST_TYPE_CODFEE); // 代收费
		CouponUtils.amountInfoToMap(aiList);
		amountInfo.setValuationCode(MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE);  // 送货费
		CouponUtils.amountInfoToMap(aiList);
		amountInfo.setValuationCode(MarketingCouponConstance.COST_TYPE_PICKUPFEE); // 接货费
		CouponUtils.amountInfoToMap(aiList);
	}
	
	@Test
	public void testConditionTypeToMap(){
		List<ConditionType> conditionTypes = new ArrayList<ConditionType>();
		ConditionType conditionType = new ConditionType();
		conditionType.setValue("value");
		conditionTypes.add(conditionType);
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		CouponUtils.conditionTypeToMap(conditionTypes);
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_ORDER);
		CouponUtils.conditionTypeToMap(conditionTypes);
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_TRADE);
		CouponUtils.conditionTypeToMap(conditionTypes);
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		CouponUtils.conditionTypeToMap(conditionTypes);
		conditionType.setType(MarketingCouponConstance.BIND_CODE_ORDER);

		CouponUtils.conditionTypeToMap(conditionTypes);

		CouponUtils.conditionTypeToMap(conditionTypes);
	}
	/*@Test
	public void testMain(){
		String[] args = {"AmountCost"};
		CouponUtils.main(args);
>>>>>>> .r5575
	}*/

//	@Test
//	public void testFilePath(){
//		File a = new File("D:"+File.separator+"a.txt");
//		System.out.println(a.exists());
//		System.out.println(a.getPath());
//		System.out.println(a.getAbsolutePath());
//		System.out.println(a.getAbsoluteFile());
//		try {
//			System.out.println(a.getCanonicalPath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
