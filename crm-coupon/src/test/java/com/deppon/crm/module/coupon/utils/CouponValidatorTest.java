/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponValidatorTest.java
 * @package com.deppon.crm.module.coupon.utils 
 * @author ZhuPJ
 * @version 0.1 2012-11-22
 */
package com.deppon.crm.module.coupon.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.server.utils.CouponValidator;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description: 优惠券校验测试类<br />
 * </p>
 * @title CouponValidatorTest.java
 * @package com.deppon.crm.module.coupon.utils 
 * @author ZhuPJ
 * @version 0.1 2012-11-22
 */

public class CouponValidatorTest {
	@Test
	public void testReSendCouponErrorInterval(){
		int max_time = 5;
		long interval = 10*60;
		Coupon coupon = new Coupon();
		coupon.setStatus(MarketingCouponConstance.COUPON_NOSEND);
		try {
			// 优惠券状态异常
			coupon.setStatus(MarketingCouponConstance.COUPON_NOSEND);
			CouponValidator.checkReSendCoupon(coupon, max_time, interval);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 优惠券发送次数异常
			coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
			coupon.setSmstimes(6);
			CouponValidator.checkReSendCoupon(coupon, max_time, interval);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 优惠券发送间隔异常
			coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
			coupon.setSmstimes(3);
			coupon.setSendTime(new Date());
			Thread.sleep(1000);// 延迟1秒
			CouponValidator.checkReSendCoupon(coupon, max_time, interval);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			// 优惠券发送间隔异常
			coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
			coupon.setSmstimes(3);
			coupon.setSendTime(DateUtils.addMinutes(new Date(), -10)); // 10分钟前发送
			CouponValidator.checkReSendCoupon(coupon, max_time, interval);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 优惠券发送间隔异常
			coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
			coupon.setSmstimes(3);
			coupon.setSendTime(DateUtils.addMinutes(new Date(), -9)); // 9分钟前发送
			CouponValidator.checkReSendCoupon(coupon, max_time, interval);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 优惠券发送间隔异常
			coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
			coupon.setSmstimes(3);
			coupon.setSendTime(DateUtils.addMinutes(new Date(), -12)); // 12分钟前发送
			CouponValidator.checkReSendCoupon(coupon, max_time, interval);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
	}
	
	@Test
	public void testCanSearchCoupon(){
		CouponSearchCondition condition = new CouponSearchCondition();
		Date start = new Date();
		Date end1 = DateUtils.addMonths(start, 1); 	// 1个月
		Date end2 = DateUtils.addMonths(start, 1); 	// 2个月
		Date end3 = DateUtils.addDays(start, 28); 	// 28天
		
		// 计划名称为空时
		try {
			// 验证查询时间
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 使用时间(结束)不满足
			condition.setUseTimeStart(start);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(null);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 使用时间(开始)不满足
			condition.setUseTimeStart(null);
			condition.setUseTimeEnd(start);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(null);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 发送时间(结束)不满足
			condition.setUseTimeStart(null);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(start);
			condition.setSendTimeEnd(null);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 发送时间(开始)不满足
			condition.setUseTimeStart(null);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(start);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 使用/发送时间（交叉）不满足
			condition.setUseTimeStart(start);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(start);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 使用/发送时间（交叉）不满足
			condition.setUseTimeStart(start);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(start);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 相差1个月 
			condition.setUseTimeStart(start);
			condition.setUseTimeEnd(end1);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(null);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 相差1个月 
			condition.setUseTimeStart(null);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(start);
			condition.setSendTimeEnd(end1);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 相差2个月 
			condition.setUseTimeStart(null);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(start);
			condition.setSendTimeEnd(end2);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 交叉
			condition.setUseTimeStart(start);
			condition.setUseTimeEnd(null);
			condition.setSendTimeStart(start);
			condition.setSendTimeEnd(end3);
			CouponValidator.canSearchCoupon(condition);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 相差28天
			condition.setUseTimeStart(start);
			condition.setUseTimeEnd(end3);
			condition.setSendTimeStart(null);
			condition.setSendTimeEnd(null);
			boolean rs = CouponValidator.canSearchCoupon(condition);
			Assert.assertTrue(rs);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		
		
	}
	@Test
	public void testSelectCouponByMutiCondition(){
//		File file = new File("C:\\workspace\\crm-coupon\\target\\tomcat\\work\\localEngine\\localhost\\crm-coupon\\upload__3132b6b5_13b44f902cd__8000_00000005.tmp");
//		InputStream is = null;
//		try {
//			is = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		File newFile = new File("D:\\aaa1.xlsx");
//		if(!newFile.exists()){
//			try {
//				newFile.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		OutputStream os = null;
//		try {
//			os = new FileOutputStream(newFile);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int i =0;
//		try {
//			while((i=is.read())!=-1){
//				os.write(i);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			is.close();
//			os.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		List<CouponCellphone> list = ReadCellphonesByExcel.getMoblies(newFile, newFile.getName());
//		System.out.println(list);
    }
	@Test
	public void testCanUseCoupon(){
		Coupon coupon = new Coupon();
		CouponRule rule = new CouponRule();
		WaybillInfo wbInfo = new WaybillInfo();
		List<MemberResult> memResult = new ArrayList<MemberResult>();
		List<DeptInfo> leaveDepts = new ArrayList<DeptInfo>();
		List<DeptInfo> arrivedDepts = new ArrayList<DeptInfo>();
		AmountInfo amountInfo1 = new AmountInfo();
		AmountInfo amountInfo2 = new AmountInfo();
		List list = new ArrayList<AmountInfo>();
		List<ConditionType> listCT = new ArrayList<ConditionType>();
		ConditionType conditionType = new ConditionType();
		list.add(amountInfo1);
		wbInfo.setAmountList(list);
		try {
			// 优惠券编码不存在
			coupon =null;
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		coupon = new Coupon();
		coupon.setCouponNumber("1353401741842");
		try {
			// 优惠券已过期
			coupon.setStatus(MarketingCouponConstance.COUPON_OVERDUE);
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			// 优惠券已使用
			coupon.setStatus(MarketingCouponConstance.COUPON_USING);
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			// 优惠券未到开始时间
			rule.setBegintime(sdf.parse("2015年11月30日"));
			rule.setEndtime(sdf.parse("2016年11月30日"));
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (Exception e) {
		} 
		try {
			rule.setBegintime(sdf.parse("2011年11月30日"));
		} catch (ParseException e1) {
		}
		try {
			// 金额要求不满足
			rule.setCostType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE); 	// 金额类型（运费/开单金额）
			rule.setValue("1999");		// 使用金额（价格基数）
			rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
			amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
			BigDecimal bc = new BigDecimal(1000);
			amountInfo1.setValuationAmonut(bc);
			
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (Exception e) {
	}
		try {
			// 比较开单金额
			rule.setCostType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE); 	// 金额类型（运费/开单金额）
			rule.setValue("20");		// 使用金额（价格基数）
			rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
			rule.setDiscount("1");
			coupon.setUseCouponValue("1");
			AmountInfo amountInfo3 = new AmountInfo();
			amountInfo3.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
			BigDecimal bc = new BigDecimal(100);
			amountInfo3.setValuationAmonut(bc);
			WaybillInfo wbInfo1 = new WaybillInfo();
			List list2 = new ArrayList<AmountInfo>();
			list2.add(amountInfo3);
			wbInfo1.setAmountList(list2);
			CouponValidator.canUseCoupon(coupon, rule, wbInfo1, memResult, leaveDepts, arrivedDepts);
		} catch (Exception e) {
		}
		try {
			// 比较开单金额
			rule.setCostType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE); 	// 金额类型（运费/开单金额）
			rule.setValue("999");		// 使用金额（价格基数）
			rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
			amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
			BigDecimal bc = new BigDecimal(1000);
			amountInfo1.setValuationAmonut(bc);
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (Exception e) {
		}
		try {
			// 比较开单金额
			rule.setCostType(MarketingCouponConstance.COST_TYPE_BILLING); 	// 金额类型（运费/开单金额）
			rule.setValue("999");		// 使用金额（价格基数）
			rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
			amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
			BigDecimal bc = new BigDecimal(1000);
			amountInfo1.setValuationAmonut(bc);
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (Exception e) {
		}
		try {
			// 比较开单金额
			rule.setCostType(MarketingCouponConstance.COST_TYPE_BILLING); 	// 金额类型（运费/开单金额）
			rule.setValue("20");		// 使用金额（价格基数）
			rule.setCostMode(MarketingCouponConstance.COST_MODE_RATED);
			rule.setDiscount("1");
			coupon.setUseCouponValue("1");
			amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
			BigDecimal bc = new BigDecimal(100);
			amountInfo1.setValuationAmonut(bc);
			CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
		} catch (GeneralException e) {
		}
	try {
		// 比较开单金额
		rule.setCostType(MarketingCouponConstance.COST_TYPE_BILLING); 	// 金额类型（运费/开单金额）
		rule.setValue("1999");		// 使用金额（价格基数）
		rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
		amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
		BigDecimal bc = new BigDecimal(1000);
		amountInfo1.setValuationAmonut(bc);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {
		// 比较运单金额
		rule.setCostType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE); 	// 金额类型（运费/开单金额）
		rule.setValue("100");		// 使用金额（价格基数）
		rule.setCostMode(MarketingCouponConstance.COST_MODE_RATED);
		rule.setDiscount("100");
		coupon.setUseCouponValue("0");
		amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {
		// 比较开单金额
		rule.setCostType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE); 	// 金额类型（运费/开单金额）
		rule.setValue("20");		// 使用金额（价格基数）
		rule.setCostMode(MarketingCouponConstance.COST_MODE_RATED);
		rule.setDiscount("1");
		coupon.setUseCouponValue("1");
		amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		BigDecimal bc = new BigDecimal(100);
		amountInfo1.setValuationAmonut(bc);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (Exception e) {
	}
	try {
		// 比较开单金额
		rule.setCostType(MarketingCouponConstance.COST_TYPE_BILLING); 	// 金额类型（运费/开单金额）
		rule.setValue("100");		// 使用金额（价格基数）
		rule.setDiscount("120");
		coupon.setUseCouponValue("0");
		amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (Exception e) {
	}
	try {
		// 比较开单金额
		rule.setCostType(MarketingCouponConstance.ADDED_MODE_OPEN); 	// 金额类型（运费/开单金额）
		rule.setValue("20");		// 使用金额（价格基数）
		rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
		rule.setCostAddedMode("1");
		rule.setCostAdded("100");
		rule.setDiscount("1");
		coupon.setUseCouponValue("1");
		rule.setCostAddedType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		amountInfo1.setValuationCode(MarketingCouponConstance.ADDED_MODE_OPEN);
		BigDecimal bc = new BigDecimal(10);
		amountInfo1.setValuationAmonut(bc);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (Exception e) {
	}
	try {
		// 增值费要求不满足
		rule.setCostType(MarketingCouponConstance.ADDED_MODE_OPEN); 	// 金额类型（运费/开单金额）
		rule.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
		rule.setCostAdded("100");
		rule.setCostAddedType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		BigDecimal bc = new BigDecimal(10);
		amountInfo1.setValuationAmonut(bc);
		list.add(amountInfo1);
		wbInfo.setAmountList(list);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {// 不满足规则，产品类型不满足
		// 增值费要求不满足
//		rule.setCostType(null); 	// 金额类型（运费/开单金额）
		rule.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		rule.setCostMode(MarketingCouponConstance.COST_MODE_STRICT);
		rule.setCostAdded("100");
		rule.setCostAddedType(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		amountInfo1.setValuationCode(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		
		BigDecimal bc = new BigDecimal(100);
		amountInfo1.setValuationAmonut(bc);
		list.add(amountInfo1);
		wbInfo.setAmountList(list);
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_PRODUCT);
		conditionType.setValue("来源一");
		listCT.add(conditionType);
		rule.setConditionTypes(listCT);
		wbInfo.setProduceType("来源二");
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {// 订单来源不满足
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		conditionType.setValue("来源一");
		rule.setConditionTypes(listCT);
		wbInfo.setProduceType("来源二");
		listCT.add(conditionType);
		rule.setConditionTypes(listCT);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {// // 会员-不满足行业
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_TRADE);
		conditionType.setValue("来源一");
		rule.setConditionTypes(listCT);
		MemberResult memResult1 = new MemberResult();
		memResult1.setTrade("来源二");
		memResult.add(memResult1);
		listCT.add(conditionType);
		rule.setConditionTypes(listCT);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {//非会员
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_TRADE);
		conditionType.setValue("来源一");
		rule.setConditionTypes(listCT);
		listCT.add(conditionType);
		memResult.remove(0);
		rule.setConditionTypes(listCT);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {//会员-不满足等级
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		conditionType.setValue("来源一");
		rule.setConditionTypes(listCT);
		MemberResult memResult1 = new MemberResult();
		memResult1.setTrade("来源二");
		memResult.add(memResult1);
		listCT.add(conditionType);
		rule.setConditionTypes(listCT);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {//非会员
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		conditionType.setValue("来源一");
		rule.setConditionTypes(listCT);
		listCT.add(conditionType);
		memResult.remove(0);
		rule.setConditionTypes(listCT);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {//线路要求
		listCT = new ArrayList<ConditionType>();
		conditionType.setType(MarketingCouponConstance.BIND_TYPE_LEVEL);
		conditionType.setValue("来源一");
		rule.setConditionTypes(listCT);
		listCT.add(conditionType);
		rule.setConditionTypes(listCT);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {// 走货线路-比较外场（出发和到达）
		List<GoodsLine> goodLines = new ArrayList<GoodsLine>();
		GoodsLine goodsLine = new GoodsLine();
		goodsLine.setRegdemand(MarketingCouponConstance.GOOD_LINE_AREA);
		goodsLine.setBeginDeptOrCityId("11");
		wbInfo.setLeaveDept(null);		// 运单出发部门
		wbInfo.setArrivedDept(null);		// 运单到达部门
		wbInfo.setLeaveOutDept(null);	// 运单出发外场
		wbInfo.setArrivedOutDept(null);	// 运单到达外场
		listCT.remove(0);
		goodLines.add(goodsLine);
		rule.setGoodsLines(goodLines);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {// 发货区域-比较部门
		List<GoodsLine> goodLines = new ArrayList<GoodsLine>();
		GoodsLine goodsLine = new GoodsLine();
		goodsLine.setRegdemand(MarketingCouponConstance.GOOD_LINE_LEAVE);
		goodsLine.setBeginDeptOrCityId(null);
		wbInfo.setLeaveDept(null);		// 运单出发部门
		wbInfo.setArrivedDept(null);		// 运单到达部门
		wbInfo.setLeaveOutDept(null);	// 运单出发外场
		wbInfo.setArrivedOutDept(null);	// 运单到达外场
		goodLines.add(goodsLine);
		rule.setGoodsLines(goodLines);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try {// 发货区域-比较部门
		List<GoodsLine> goodLines = new ArrayList<GoodsLine>();
		GoodsLine goodsLine = new GoodsLine();
		goodsLine.setRegdemand(MarketingCouponConstance.GOOD_LINE_LEAVE);
		goodsLine.setBeginDeptOrCityId(null);
		wbInfo.setLeaveDept("11");		// 运单出发部门
		wbInfo.setArrivedDept("11");		// 运单到达部门
		wbInfo.setLeaveOutDept("22");	// 运单出发外场
		wbInfo.setArrivedOutDept("33");	// 运单到达外场
		goodLines.add(goodsLine);
		rule.setGoodsLines(goodLines);
		DeptInfo leaveDept = new DeptInfo();
		leaveDept.setOrgCode("11");
		leaveDepts.add(leaveDept);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (Exception e) {
		e.printStackTrace();
	}
	try { // 到货区域-比较部门
		List<GoodsLine> goodLines = new ArrayList<GoodsLine>();
		GoodsLine goodsLine = new GoodsLine();
		goodsLine.setRegdemand(MarketingCouponConstance.GOOD_LINE_ARRIVED);
		goodsLine.setBeginDeptOrCityId(null);
		wbInfo.setLeaveDept(null);		// 运单出发部门
		wbInfo.setArrivedDept(null);		// 运单到达部门
		wbInfo.setLeaveOutDept(null);	// 运单出发外场
		wbInfo.setArrivedOutDept(null);	// 运单到达外场
		goodLines.add(goodsLine);
		rule.setGoodsLines(goodLines);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (GeneralException e) {
		System.out.println(e.getErrorCode());
	}
	try { // 到货区域-比较部门
		List<GoodsLine> goodLines = new ArrayList<GoodsLine>();
		GoodsLine goodsLine = new GoodsLine();
		goodsLine.setRegdemand(MarketingCouponConstance.GOOD_LINE_ARRIVED);
		goodsLine.setBeginDeptOrCityId(null);
		wbInfo.setLeaveDept(null);		// 运单出发部门
		wbInfo.setArrivedDept("11");		// 运单到达部门
		wbInfo.setLeaveOutDept(null);	// 运单出发外场
		wbInfo.setArrivedOutDept(null);	// 运单到达外场
		goodLines.add(goodsLine);
		rule.setGoodsLines(goodLines);
		DeptInfo arrivedDept = new DeptInfo();
		arrivedDept.setOrgCode("11");
		arrivedDepts.add(arrivedDept);
		CouponValidator.canUseCoupon(coupon, rule, wbInfo, memResult, leaveDepts, arrivedDepts);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	@Test
	public void testCheckCouponIsNull(){
		Coupon coupon1 = new Coupon();
		try{
		CouponValidator.checkCouponIsNull(null);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try{Coupon coupon2 = new Coupon();
		CouponValidator.checkCouponIsNull(coupon2);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try{Coupon coupon3 = new Coupon();
		coupon3.setMarketPlanId("110");
		CouponValidator.checkCouponIsNull(coupon3);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
	}
	@Test
	public void testCheckMarketPlaneVOCanCreate(){
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(null);
		} catch (GeneralException e) {
		}
		MarketPlaneVO marketPlaneVO = new MarketPlaneVO();
		Marketplan marketPlan = new Marketplan();
		marketPlaneVO.setMarketplan(marketPlan);
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
		}
		marketPlan.setPlanName("新建营销计划-测试用例5");
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
		}
		marketPlan.setCouponType(MarketingCouponConstance.COUPON_SENDAUTO);
		List<ConditionType> conditionType1 = new ArrayList<ConditionType>();
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
		
		List<ConditionType> conditionType2 = new ArrayList<ConditionType>();
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
		CouponRule couponRule;
		couponRule = new CouponRule();
		try {
			ruleCouponAuto.setAutoBeginTime(parseTime.parse("2011-11-01"));
			ruleCouponAuto.setAutoEndTime(parseTime.parse("2011-12-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ruleCouponAuto.setConditionTypes(conditionType1);
		ruleCouponAuto.setCreateGoodsLine(goodsLines1);
		marketPlaneVO.setRuleCouponAuto(ruleCouponAuto);
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		try {
			ruleCouponAuto.setAutoBeginTime(parseTime.parse("2014-11-01"));
			ruleCouponAuto.setAutoEndTime(parseTime.parse("2014-12-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<AutoCouponCost> autoCouponCost1 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost11 = null;
		ruleCouponAuto.setAutoCouponCost(autoCouponCost1);
		ruleCouponAuto.setConditionTypes(conditionType1);
		ruleCouponAuto.setCreateGoodsLine(goodsLines1);
		marketPlaneVO.setRuleCouponAuto(ruleCouponAuto);
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		ArrayList<AutoCouponCost> autoCouponCost2 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost12 = new AutoCouponCost();
		autoCouponCost12.setCostType("运费");
		autoCouponCost12.setCostDown("100");
		autoCouponCost12.setCoupoCost("-1");
		autoCouponCost2.add(autoCouponCost12);
		ruleCouponAuto.setAutoCouponCost(autoCouponCost2);
		ruleCouponAuto.setConditionTypes(conditionType1);
		ruleCouponAuto.setCreateGoodsLine(goodsLines1);
		marketPlaneVO.setRuleCouponAuto(ruleCouponAuto);
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		
		ArrayList<AutoCouponCost> autoCouponCost3 = new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost13 = new AutoCouponCost();
		autoCouponCost13.setCostType("运费");
		autoCouponCost13.setCostDown("100");
		autoCouponCost13.setCoupoCost("10");
		autoCouponCost3.add(autoCouponCost12);
		ruleCouponAuto.setAutoCouponCost(autoCouponCost3);
		couponRule = null;
		marketPlaneVO.setCouponRule(couponRule);
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		couponRule = new CouponRule();
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
		try {
			couponRule.setBegintime(parseTime.parse("2011-11-01"));
			couponRule.setEndtime(parseTime.parse("2011-12-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		couponRule.setSmsContent("yes sms!");
		try{
			CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
	}
	@Test
	public void testMatchValue(){
		try{
			CouponValidator.matchValue(CouponExceptionType.couponSmsContentIsLong,20);
		}catch(Exception e){}
		CouponValidator.matchValue(CouponExceptionType.couponSmsContentIsLong,20,"10","20");
		try{
			CouponValidator.matchValue(CouponExceptionType.couponSmsContentIsLong,20,"aa");
		}catch(Exception e){}
	}
	@Test
	public void testChangeTyprToName(){
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_BILLING);
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_PACKAGINGFEE);
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_INSURANCEFEE);
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_CODFEE);
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE);
		CouponValidator.changeTyprToName(MarketingCouponConstance.COST_TYPE_PICKUPFEE);
		CouponValidator.changeTyprToName("优惠券校验出现未知异常!");
	}
	@Test
	public void testCheckCouponRuleIsNotNull(){
		CouponRule rule = new CouponRule();
		CouponValidator.checkCouponRuleIsNotNull(rule);
		try{
			CouponValidator.checkCouponRuleIsNotNull(null);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
	}
	@Test
	public void testCheckWaybillInfoIsNull(){
		try{
			CouponValidator.checkWaybillInfoIsNull(null);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		WaybillInfo wbInfo = new WaybillInfo();
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setWaybillNumber("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setOrderNumber("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setOrderSource("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setProduceType("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setTotalAmount(new BigDecimal(1));
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setLeaveDept("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setArrivedDept("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setLeaveOutDept("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		wbInfo.setArrivedOutDept("110");
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
		List<AmountInfo> list = new ArrayList<AmountInfo>();
		wbInfo.setAmountList(list);
		try{
			CouponValidator.checkWaybillInfoIsNull(wbInfo);
		} catch (GeneralException e) {
			System.out.println(e.getErrorCode());
		}
	}
	@Test
	public void testCheckUseIsOverDue() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date dateForhead = sdf.parse("2012-11-12");
		String typeId = MarketingCouponConstance.COUPON_SENDHAND;
		CouponValidator.checkUseIsOverDue(typeId, new Date().getTime(), dateForhead.getTime(),"18217471059");
		dateForhead = sdf.parse("2013-11-12");
		CouponValidator.checkUseIsOverDue(typeId, new Date().getTime(), dateForhead.getTime(),"");
		typeId = MarketingCouponConstance.COUPON_SENDAUTO;
		CouponValidator.checkUseIsOverDue(typeId, new Date().getTime(), dateForhead.getTime(),"18217471059");
		CouponValidator.checkUseIsOverDue(null, new Date().getTime(), dateForhead.getTime(),"null");
	}
	@Test
	public void testCheckAutoCouponCost(){
		List<AutoCouponCost> autoCouponCosts =new ArrayList<AutoCouponCost>();
		AutoCouponCost autoCouponCost = new AutoCouponCost();
		try{
			CouponValidator.checkAutoCouponCost(null);
		}catch(Exception e){System.out.println(e.getMessage());}		
		try{
			CouponValidator.checkAutoCouponCost(autoCouponCosts);
		}catch(Exception e){System.out.println(e.getStackTrace());}
		try{
			autoCouponCosts.add(autoCouponCost);
			CouponValidator.checkAutoCouponCost(autoCouponCosts);
		}catch(Exception e){System.out.println(e.getMessage());}
		for(int i=0;i<9;i++){
			autoCouponCosts.add(autoCouponCost);
		}
		try{
			CouponValidator.checkAutoCouponCost(autoCouponCosts);
		}catch(Exception e){System.out.println(e.getMessage());}
		try{
			autoCouponCosts.add(autoCouponCost);
			autoCouponCosts.add(autoCouponCost);
			CouponValidator.checkAutoCouponCost(autoCouponCosts);
		}catch(Exception e){System.out.println(e.getMessage());}
		AutoCouponCost autoCouponCost1 = new AutoCouponCost();
		if(StringUtils.isEmpty(autoCouponCost1.getCostType())){
			System.out.println("what");
		}
	}
	@Test
	public void testCheckMaxSendCouponNumber(){
		List<CouponCellphone> list = new ArrayList<CouponCellphone>();
		CouponCellphone c = null;
		for(int i =0; i<1011;i++){
			c = new CouponCellphone();
			list.add(c);
			if(i%999==0){
				System.out.println(list.size());
				System.out.println(list.size() > MarketingCouponConstance.MAX_SEND_COUPON_NUMBER);
				try {
					CouponValidator.checkMaxSendCouponNumber(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		
		}
		try {
			CouponValidator.checkMaxSendCouponNumber(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list.size());
		System.out.println(list.size() > MarketingCouponConstance.MAX_SEND_COUPON_NUMBER);
	}
	@Test
	public void testCheckHandMarketPlan(){
		HandMarketPlan h = new HandMarketPlan();
		h.setCouponCreateNum(100);
		CouponValidator.checkHandMarketPlan(h);
		h.setCouponCreateNum(0);
		CouponValidator.checkHandMarketPlan(h);
		CouponValidator.checkHandMarketPlan(null);
	}
	@Test
	public void testCheckOutputExcelCouponNum(){
		try {
			CouponValidator.checkOutputExcelCouponNum(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			CouponValidator.checkOutputExcelCouponNum(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			CouponValidator.checkOutputExcelCouponNum(20001);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckRuleCouponHandCanCreate(){
		RuleCouponHand c = null;
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		c.setCouponValue("");
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		c.setCouponValue("aaa");
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		c.setCouponValue("123");
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		c.setCouponValue("123");
		c.setCouponQuantity("");
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		c.setCouponValue("123");
		c.setCouponQuantity("100");
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new RuleCouponHand();
		c.setCouponValue("123");
		c.setCouponQuantity("10001");
		try {
			CouponValidator.checkRuleCouponHandCanCreate(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckCouponRuleCanCreate(){
		CouponRule c = null;
		try {
			CouponValidator.checkCouponRuleCanCreate(c, new SimpleDateFormat(), new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new CouponRule();
		try {
			CouponValidator.checkCouponRuleCanCreate(c, new SimpleDateFormat(), new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new CouponRule();
		c.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		c.setValue("123");
		c.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		c.setCostAdded("123");
		c.setDiscount("123");
		c.setBegintime(new Date(System.currentTimeMillis()-1000*60*60*24));
		c.setEndtime(new Date(System.currentTimeMillis()+1000*60*60*24));
		Date nowDate = new Date();
		Date beginCouponTime = new Date(System.currentTimeMillis()+1000*60*60*24);
		
		try {
			CouponValidator.checkCouponRuleCanCreate(c, new SimpleDateFormat("yyyy-MM-dd"), beginCouponTime, nowDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new CouponRule();
		c.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		c.setValue("123");
		c.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		c.setCostAdded("123");
		c.setDiscount("123");
		c.setBegintime(new Date(System.currentTimeMillis()+1000*60*60*24));
		c.setEndtime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
		nowDate = new Date();
		beginCouponTime = new Date(System.currentTimeMillis()+1000*60*60*24);
		
		try {
			CouponValidator.checkCouponRuleCanCreate(c, new SimpleDateFormat("yyyy-MM-dd"), beginCouponTime, nowDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new CouponRule();
		c.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		c.setValue("123");
		c.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		c.setCostAdded("123");
		c.setDiscount("123");
		c.setBegintime(new Date(System.currentTimeMillis()+1000*60*60*24));
		c.setEndtime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
		c.setSmsContent("adsfasdfasd");
		nowDate = new Date();
		beginCouponTime = new Date(System.currentTimeMillis()+1000*60*60*24);
		
		try {
			CouponValidator.checkCouponRuleCanCreate(c, new SimpleDateFormat("yyyy-MM-dd"), beginCouponTime, nowDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		c = new CouponRule();
		c.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		c.setValue("123");
		c.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		c.setCostAdded("123");
		c.setDiscount("123");
		c.setBegintime(new Date(System.currentTimeMillis()+1000*60*60*24));
		c.setEndtime(new Date(System.currentTimeMillis()+1000*60*60*24*2));
		c.setSmsContent("adsfasdfasd");
		c.setDescribe("asdfasdf");
		nowDate = new Date();
		beginCouponTime = new Date(System.currentTimeMillis()+1000*60*60*24);
		
		try {
			CouponValidator.checkCouponRuleCanCreate(c, new SimpleDateFormat("yyyy-MM-dd"), beginCouponTime, nowDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckCanStart(){
		Marketplan m = null;
		try {
			CouponValidator.checkCanStart(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m = new Marketplan();
		try {
			CouponValidator.checkCanStart(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m = new Marketplan();
		m.setMarketStatus(MarketingCouponConstance.MARKETPLAN_NOUSE);
		try {
			CouponValidator.checkCanStart(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckSendCouponVO(){
		SendCouponVO s = new SendCouponVO();
		s.setMarketPlanStatus(MarketingCouponConstance.MARKETPLAN_USING);
		s.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		s.setBeginTime(new Date(System.currentTimeMillis()-1000*60*60*24));
		s.setEndTime(new Date(System.currentTimeMillis()+1000000));
		s.setTypeId(MarketingCouponConstance.COUPON_CREATED);
		try {
			CouponValidator.checkSendCouponVO(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testCheckCouponCountAndPhoneCount(){
		List<Coupon> coupons = new ArrayList<Coupon>();
		List<CouponCellphone> phones = new ArrayList<CouponCellphone>();
		coupons.add(new Coupon());
		phones.add(new CouponCellphone());
		try {
			CouponValidator.checkCouponCountAndPhoneCount(coupons, phones);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCheckAryIsNotNull(){
		String[] m = null;
		try {
			CouponValidator.checkAryIsNotNull(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m = new String[0];
		try {
			CouponValidator.checkAryIsNotNull(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m = new String[]{"","2"};
		try {
			CouponValidator.checkAryIsNotNull(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m = new String[]{"0","2"};
		try {
			CouponValidator.checkAryIsNotNull(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckMarketPlaneVOCanUpdate(){
		MarketPlaneVO mv = new MarketPlaneVO();
		Marketplan mp = new Marketplan();
		mv.setMarketplan(mp);
		
		try {
			CouponValidator.checkMarketPlaneVOCanUpdate(mv,mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv = new MarketPlaneVO();
		mp = new Marketplan();
		mv.setMarketplan(mp);
		CouponRule cr = new CouponRule();
		mv.setCouponRule(cr);
		RuleCouponHand rh = new RuleCouponHand();
		rh.setId("123");
		mv.setRuleCouponHand(rh);
		mp.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		try {
			CouponValidator.checkMarketPlaneVOCanUpdate(mv,mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv = new MarketPlaneVO();
		mp = new Marketplan();
		mp.setPlanName("aa");
		mv.setMarketplan(mp);
		cr = new CouponRule();
		cr.setId("123");
		cr.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		cr.setValue("123");
		cr.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_CLOSE);
		cr.setCostAdded("1234");
		mv.setCouponRule(cr);
		rh = new RuleCouponHand();
		rh.setId("123");
		rh.setCouponValue("123");
		rh.setCouponQuantity("123");
		mv.setRuleCouponHand(rh);
		mp.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		try {
			CouponValidator.checkMarketPlaneVOCanUpdate(mv,mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv = new MarketPlaneVO();
		mp = new Marketplan();
		mp.setPlanName("aa");
		mv.setMarketplan(mp);
		cr = new CouponRule();
		cr.setId("123");
		cr.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		cr.setValue("123");
		cr.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		cr.setCostAdded("1234");
		cr.setBegintime(new Date(System.currentTimeMillis()+1000*60*60*24*3));
		cr.setEndtime(new Date(System.currentTimeMillis()+1000*60*60*24*6));
		cr.setSmsContent("aa");
		cr.setDescribe("bb");
		cr.setDiscount("123");
		mv.setCouponRule(cr);
		rh = new RuleCouponHand();
		rh.setId("123");
		rh.setCouponValue("123");
		rh.setCouponQuantity("123");
		mv.setRuleCouponHand(rh);
		mp.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
		try {
			CouponValidator.checkMarketPlaneVOCanUpdate(mv,mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		mv = new MarketPlaneVO();
		mp = new Marketplan();
		mp.setPlanName("aa");
		mv.setMarketplan(mp);
		cr = new CouponRule();
		cr.setId("123");
		cr.setCostType(MarketingCouponConstance.COST_MODE_RATED);
		cr.setValue("123");
		cr.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
		cr.setCostAdded("1234");
		cr.setBegintime(new Date(System.currentTimeMillis()+1000*60*60*24*3));
		cr.setEndtime(new Date(System.currentTimeMillis()+1000*60*60*24*6));
		cr.setSmsContent("aa");
		cr.setDescribe("bb");
		cr.setDiscount("123");
		List<AutoCouponCost> list = new ArrayList<AutoCouponCost>();
		AutoCouponCost o = new AutoCouponCost();
		list.add(o);
		
		mv.setCouponRule(cr);
		RuleCouponAuto ra = new RuleCouponAuto();
		ra.setId("123");
		ra.setAutoBeginTime(new Date(System.currentTimeMillis()+1000*60*60*24*5));
		ra.setAutoEndTime(new Date(System.currentTimeMillis()+1000*60*60*24*10));
		mv.setRuleCouponAuto(ra);
		mp.setCouponType(MarketingCouponConstance.COUPON_SENDAUTO);
		try {
			CouponValidator.checkMarketPlaneVOCanUpdate(mv,mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testcheckDateCanCreate() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginCouponTime = null;
		Date endTime = null;
		Date nowTime = null;
		Date beginTime = null;
		CouponExceptionType  type = null;
		try{
			CouponValidator.checkDateCanCreate(sdf, beginCouponTime, nowTime, beginTime, endTime, type);
		}catch(Exception e){}
		endTime = sdf.parse("2013-10-10");
		try{
			CouponValidator.checkDateCanCreate(sdf, beginCouponTime, nowTime, beginTime, endTime, type);
		}catch(Exception e){}
		endTime = null;
		beginTime = sdf.parse("2011-10-10");
		try{
			CouponValidator.checkDateCanCreate(sdf, beginCouponTime, nowTime, beginTime, endTime, type);
		}catch(Exception e){}
		endTime = sdf.parse("2013-10-10");
		try{
			CouponValidator.checkDateCanCreate(sdf, beginCouponTime, nowTime, beginTime, endTime, type);
		}catch(Exception e){}
	}
	@Test
	public void testcheckRuleCouponAutoCanCreate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginCouponTime = null;
		Date nowTime = null;
		RuleCouponAuto ruleCouponAuto = null;
		try{
			CouponValidator.checkRuleCouponAutoCanCreate(ruleCouponAuto, sdf, beginCouponTime, nowTime);
		}catch(Exception e){}
	}
	@Test
	public void testcheckCallableFlag(){
		String flag = "0";
			CouponValidator.checkCallableFlag(flag);
	}
//	@Test
//	public void testcheckWaybillInfoIsNull(){
//		WaybillInfo wbInfo = new WaybillInfo();
//		wbInfo.setLeaveDept("leave");
//		wbInfo.setArrivedDept("arrive");
//		CouponValidator.checkWaybillInfoIsNull(wbInfo);
//	}
	@Test
	public void testChangCodeProduct(){
		List<String> pList = new ArrayList<String>();
		pList.add("FLF");
		pList=CouponValidator.changCodeProduct(pList);
		System.out.println(pList.get(0));
	}
	@Test
	public void testChangChangCodeLevel(){
		List<String> pList = new ArrayList<String>();
		pList.add("FLF");
		pList=CouponValidator.changCodeLevel(pList);
		System.out.println(pList.get(0));
	}
	@Test
	public void testChangChangCodeTrade(){
		List<String> pList = new ArrayList<String>();
		pList.add("FLF");
		pList=CouponValidator.changCodeTrade(pList);
		System.out.println(pList.get(0));
	}
	@Test
	public void testChangChangCodeOrder(){
		List<String> pList = new ArrayList<String>();
		pList.add("FLF");
		pList=CouponValidator.changCodeOrder(pList);
		System.out.println(pList.get(0));
	}
	@Test
	public void testCheckInterfaceMarketPlanCanCreate(){
//		CouponForInterface cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01910");
//		cfi.setActivityType("NEWOPEN");
////		cfi.setBeginTime(new Date());
////		cfi.setEndTime(new Date(System.currentTimeMillis()+30*24*60*60*1000L));
////		cfi.setCouponValue("50");
////		cfi.setCostMode("2");
////		cfi.setCostType("FRT");
////		cfi.setValue("50");
////		cfi.setDiscount("100");
////		cfi.setCostAddedType("BZ");
////		cfi.setCostAdded("20");	
////		cfi.setRegdemand("2");
////		List gs = new ArrayList<GoodsLine>();
////		for( int i=0; i< 3;i++){
////			GoodsLine g = new GoodsLine();
////			g.setBeginDeptOrCityId("DP01910");
////			g.setBeginDeptOrCityName("上海嘉定区浏翔公路营业部");
////			g.setEndDeptOrCityId("DP00470");
////			g.setEndDeptOrCityName("广州白云区太和营业部");
////			gs.add(g);
////		}
////		cfi.setGoodsLines(gs);
//		
//		MarketPlaneVO m = CouponUtils.convertCouponForInterfaceToMarketPlaneVO(cfi,"aa");
//		try{
//			//只填活动类型和部门标杆编码
//			CouponValidator.checkInterfaceMarketPlanCanCreate(m);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01910");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setCouponValue("100");
//		m = CouponUtils.convertCouponForInterfaceToMarketPlaneVO(cfi,"aa");
//		try{
//			//只填活动类型和部门标杆编码和优惠券金额
//			CouponValidator.checkInterfaceMarketPlanCanCreate(m);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01910");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setCouponValue("100");
//		cfi.setCostType(MarketingCouponConstance.COST_TYPE_BILLING);
//		m = CouponUtils.convertCouponForInterfaceToMarketPlaneVO(cfi,"aa");
//		try{
//			//只填活动类型和部门标杆编码和优惠券金额金额类型
//			CouponValidator.checkInterfaceMarketPlanCanCreate(m);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		cfi = new CouponForInterface();
//		cfi.setDeptStandardCode("DP01910");
//		cfi.setActivityType("NEWOPEN");
//		cfi.setCouponValue("100");
//		m = CouponUtils.convertCouponForInterfaceToMarketPlaneVO(cfi,"aa");
//		try{
//			//只填活动类型和部门标杆编码和优惠券金额
//			CouponValidator.checkInterfaceMarketPlanCanCreate(m);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		CouponForInterface cfi = new CouponForInterface();
		cfi.setDeptStandardCode("DP01910");
		cfi.setActivityType("NEWOPEN");
		cfi.setCouponValue("100");
		cfi.setCostType(MarketingCouponConstance.COST_TYPE_BILLING);
		cfi.setValue("20");
		MarketPlaneVO m = CouponUtils.convertCouponForInterfaceToMarketPlaneVO(cfi,"aa");
		try{
			//只填活动类型和部门标杆编码和优惠券金额金额类型金额
			CouponValidator.checkInterfaceMarketPlanCanCreate(m);
		}catch (Exception e) {
			e.printStackTrace();
		}
		cfi = new CouponForInterface();
		cfi.setDeptStandardCode("DP01910");
		cfi.setActivityType("NEWOPEN");
		cfi.setCouponValue("100");
		cfi.setCostType(MarketingCouponConstance.COST_TYPE_BILLING);
		cfi.setValue("20");
		m = CouponUtils.convertCouponForInterfaceToMarketPlaneVO(cfi,"aa");
		try{
			//只填活动类型和部门标杆编码和优惠券金额金额类型金额
			CouponValidator.checkInterfaceMarketPlanCanCreate(m);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

