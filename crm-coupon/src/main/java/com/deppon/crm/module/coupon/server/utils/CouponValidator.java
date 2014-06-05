/**   
checkCellphoneValidity * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponValidator.java
 * @package com.deppon.crm.module.coupon.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import com.deppon.crm.module.coupon.shared.domain.AutoCouponCost;
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
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;

/**   
 * <p>
 * Description: 优惠券验证工具类 <br />
 * </p>
 * @title CouponValidator.java
 * @package com.deppon.crm.module.coupon.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public class CouponValidator {
	/**   
	 * <p>
	 * Description: 检查优惠券是否为空 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-14
	 * @param coupon 优惠券
	 */
	public static void checkCouponIsNull(Coupon coupon) {
		//优惠券 使用时间为空
		if (null == coupon) {
			ExceptionUtils.createCouponException(CouponExceptionType.couponIsntExsit);
		}
		// 优惠券 营销编码为空
		if(StringUtils.isEmpty(coupon.getMarketPlanId())){
			ExceptionUtils.createCouponException(CouponExceptionType.couponMarketPlanIdIsNull);
		}
		// 优惠券类型id为空
		if(StringUtils.isEmpty(coupon.getTypeId())){
			ExceptionUtils.createCouponException(CouponExceptionType.couponTypeIdIsNull);
		}
		
	}
	/**   
	 * <p>
	 * Description: 检查营销计划创建时，对象属性是否为空 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-14
	 * @param marketPlaneVO 营销计划
	 */
	public static void checkMarketPlaneVOCanCreate(MarketPlaneVO marketPlaneVO) {
		// 检查参数是否为空
		if(null == marketPlaneVO){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlaneVOIsNull);
		}
		Marketplan marketplan = marketPlaneVO.getMarketplan();// 营销计划
		// 校验营销计划是否符合创建规则
		checkMarketPlanCanCreate(marketplan);
		String couponType = marketplan.getCouponType();// 营销计划类型（手动、自动）
		// 如果-营销计划-类型-是-手动发券
		if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType)){
			// 校验手动发券规则是否符合创建规则
			checkRuleCouponHandCanCreate(marketPlaneVO.getRuleCouponHand());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 时间装换格式-只比较天数-不涉及到时分秒
		Date nowTime = null;  // 系统当前时间
		Date beginCouponTime = null;  // 优惠券开始时间
		// 如果-营销计划-类型-是-自动发券
		if(MarketingCouponConstance.COUPON_SENDAUTO.equals(couponType)){
			// 校验自动发券规则是否符合创建规则
			checkRuleCouponAutoCanCreate(
					marketPlaneVO.getRuleCouponAuto(),sdf, beginCouponTime, nowTime);
			
		}
		// 优惠券使用条件校验
		checkCouponRuleCanCreate(marketPlaneVO.getCouponRule(), sdf, beginCouponTime, nowTime);		
	}
	/**
	 * <p>
	 * Description: 校验页面字段格式<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param type        异常类型
	 * @param size          最大位数
	 * @param couponValues     所需检查字段
	 */
	public static void matchValue(CouponExceptionType type,int size,String... couponValues){
		// 校验所需字段是否为空
		if(null == couponValues || 0 == couponValues.length){
			ExceptionUtils.createCouponException(type);
		}
		for(String couponValue : couponValues){
			// 字段必须为数字组成
//			String regEx = "^[1-9][0-9]*"; 
//			boolean isRight = couponValue.matches(regEx);
			// 校验字段是否为空，是否符合组成规则，是否符合最大位数
			if(StringUtils.isEmpty(couponValue) || !couponValue.matches("^[1-9][0-9]*") || size < couponValue.length()){
				ExceptionUtils.createCouponException(type);
			}
		}
				
	}
	/**
	 * <p>
	 * Description: 自动返券的返券金额最多为10条<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-15
	 * @param autoCouponCosts        返券金额
	 */
	public static void checkAutoCouponCost(List<AutoCouponCost> autoCouponCosts){
		// 自动返券为必填项
		if(null == autoCouponCosts || 0 == autoCouponCosts.size()){
			//如果为空 则报空异常
			ExceptionUtils.createCouponException(CouponExceptionType.AutoCouponCostIsNull);
		}
		// 自动返券金额 最多为10条
		if( 10 < autoCouponCosts.size()){
			// 超过10条 报金额条数过多异常
			ExceptionUtils.createCouponException(CouponExceptionType.AutoCouponCostIsLarger);
		}
	}
	/**
	 * <p>
	 * Description: 验证优惠券使用条件<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-11-13
	 * @param coupon        优惠券信息
	 * @param rule          优惠券使用规则
	 * @param memResult     客户信息
	 * @param wbInfo        运单信息
	 * @return String 		抵扣金额
	 */
	public static String canUseCoupon(Coupon coupon, CouponRule rule,
			WaybillInfo wbInfo, List<MemberResult> memResult, List<DeptInfo> leaveDepts, List<DeptInfo> arrivedDepts) {
		/**
		 * 校验顺序：
		 * 1.优惠券编码是否存在、
		 * 2.优惠券状态（已过期、已使用）、
		 * 3.使用期限、
		 * 4.金额要求、
		 * 5.增值费要求、
		 * 6.产品类型、
		 * 7.订单来源、
		 * 8.客户等级、
		 * 9.客户行业、
		 * 10.线路区域要求；
		 */
		// 优惠金额
		String cost = null;
		// 发货部门
		DeptInfo leaveDept = getDeptInfo(leaveDepts);
		// 到货部门
		DeptInfo arrivedDept = getDeptInfo(arrivedDepts);
		// 金额类型Map
		Map<String, BigDecimal> aiMap = CouponUtils.amountInfoToMap(wbInfo.getAmountList());
		
		/********************************** 优惠券使用校验规则-优惠券（状态、日期）条件校验  start ***********************************/
		if(coupon == null){
			// 优惠券编码不存在
			return CouponUtils.produceErrMsg(CouponExceptionType.couponIsntExsit);
		}
		if (MarketingCouponConstance.COUPON_OVERDUE.equals(coupon.getStatus())){
			// 优惠券状态为已过期
			return CouponUtils.produceErrMsg(CouponExceptionType.couponIsOverdue);
		}
		if (MarketingCouponConstance.COUPON_USING.equals(coupon.getStatus())){
			// 优惠券状态为已使用
			return CouponUtils.produceErrMsg(CouponExceptionType.couponIsUsed, 
					coupon.getUseWBNumber(), CouponUtils.calculateCouponCost(coupon, rule, aiMap));
		}
		if (rule.getBegintime().getTime() > System.currentTimeMillis()){
			// 使用期限（未生效）
			String formatString = "yyyy年MM月dd日";
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
			String start = dateFormat.format(rule.getBegintime());
			String end = dateFormat.format(rule.getEndtime());
			
			return CouponUtils.produceErrMsg(CouponExceptionType.couponIneffectiveDate, start, end);
			/********************************** 优惠券使用校验规则-优惠券（状态、日期）条件校验  end ***********************************/
		}

		/********************************** 优惠券使用校验规则-金额条件校验  start ***********************************/
		
		
		// 金额要求不满足
		if (StringUtils.isEmpty(rule.getCostType())){
			return CouponUtils.produceErrMsg(CouponExceptionType.couponAmountTypeException, 
					MarketingCouponConstance.COST_TYPE_TRANSPORTFEE_NAME);
		}
		else{
			String costType = rule.getCostType(); 	// 金额类型（运费/开单金额）
			String value	= rule.getValue();		// 使用金额（价格基数）
			String discount	= rule.getDiscount();	// 抵扣金额基数（分级抵扣时）
			
			String amout	= coupon.getUseCouponValue();	// 优惠券抵扣金额上限
			
			// 严格抵扣
			if (MarketingCouponConstance.COST_MODE_STRICT.equals(rule.getCostMode())){
				// 比较运单金额
				if (MarketingCouponConstance.COST_TYPE_TRANSPORTFEE.equals(costType)){
					// 比较指定运单金额与规则金额
					if (aiMap.get(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE)
							.compareTo(NumberUtils.createBigDecimal(value)) > -1){
						// 满足规则，抵扣cost元
						cost = amout;
					}else{
						// 不满足规则，金额要求不满足
						return CouponUtils.produceErrMsg(CouponExceptionType.couponAmountType, 
								MarketingCouponConstance.COST_TYPE_TRANSPORTFEE_NAME, value);
					}
				}
				// 比较开单金额
				if (MarketingCouponConstance.COST_TYPE_BILLING.equals(costType)){
					// 比较指定运单金额与规则金额
					if (aiMap.get(MarketingCouponConstance.COST_TYPE_BILLING)
							.compareTo(NumberUtils.createBigDecimal(value)) > -1){
						// 满足规则，抵扣cost元
						cost = amout;
					}else{
						// 不满足规则，金额要求不满足
						return CouponUtils.produceErrMsg(CouponExceptionType.couponAmountType, 
								MarketingCouponConstance.COST_TYPE_BILLING_NAME, value);
					}
				}
				
			}
			
			// 分级抵扣
			if (MarketingCouponConstance.COST_MODE_RATED.equals(rule.getCostMode())){
				// 比较运单金额
				if (MarketingCouponConstance.COST_TYPE_TRANSPORTFEE.equals(costType)){
					// 比较指定运单金额与规则金额
					BigDecimal wbAmout = aiMap.get(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE);
					// 计算分级抵扣金额
					cost = relativeAmout(wbAmout,
							NumberUtils.createBigDecimal(value),
							NumberUtils.createBigDecimal(discount),
							NumberUtils.createBigDecimal(amout));
					if ("0".equals(cost)){
						// 抵扣结果为0，不满足规则，金额要求不满足
						return CouponUtils.produceErrMsg(CouponExceptionType.couponAmountType, 
								MarketingCouponConstance.COST_TYPE_TRANSPORTFEE_NAME, value);
					}
				}
				
				// 比较开单金额
				if (MarketingCouponConstance.COST_TYPE_BILLING.equals(costType)){
					// 比较指定运单金额与规则金额
					BigDecimal wbAmout = aiMap.get(MarketingCouponConstance.COST_TYPE_BILLING);
					
					cost = relativeAmout(wbAmout,
							NumberUtils.createBigDecimal(value),
							NumberUtils.createBigDecimal(discount),
							NumberUtils.createBigDecimal(amout));
					if ("0".equals(cost)){
						// 抵扣结果为0，不满足规则，金额要求不满足
						return CouponUtils.produceErrMsg(CouponExceptionType.couponAmountType, 
								MarketingCouponConstance.COST_TYPE_BILLING_NAME, value);
					}
				}
			}
		}
		
		// 增值费要求不满足
		if (MarketingCouponConstance.ADDED_MODE_OPEN.equals(rule.getCostAddedMode())){
			// 规则内-增值费类型
			String type 		= rule.getCostAddedType();
			// 规则内-增值费费用
			String costAdded 	= rule.getCostAdded();
			// 规则内-增值费类型- 显示用
			String typeName 	= changeTyprToName(type);
			
			if (aiMap.get(type)
					.compareTo(NumberUtils.createBigDecimal(costAdded)) < 0){
				// 不满足规则，金额要求不满足
				return CouponUtils.produceErrMsg(CouponExceptionType.couponAppreciationType, 
						typeName, costAdded);
			}
		}
		/********************************** 优惠券使用校验规则-金额条件校验  end ***********************************/

		/********************************** 优惠券使用校验规则-类型条件校验  start ***********************************/
		// 优惠券条件Map
		Map<String, List<String>> ctMap = CouponUtils.conditionTypeToMap(rule.getConditionTypes());
		
		List<String> pList = ctMap.get(MarketingCouponConstance.BIND_CODE_PRODUCT);
		if (pList != null && pList.size() > 0){
			if (!pList.contains(wbInfo.getProduceType())){
				// 不满足规则，产品类型不满足
				pList = changCodeProduct(pList);
				return CouponUtils.produceErrMsg(CouponExceptionType.couponProductType, 
						pList.toString());
			}
		}

		List<String> oList = ctMap.get(MarketingCouponConstance.BIND_CODE_ORDER);
		if (oList != null && oList.size() > 0){
			if (!oList.contains(wbInfo.getOrderSource())){
				// 订单来源不满足
				oList = changCodeOrder(oList);
				return CouponUtils.produceErrMsg(CouponExceptionType.couponOrderSource, 
						oList.toString());
			}
		}
		List<String> tList = ctMap.get(MarketingCouponConstance.BIND_CODE_TRADE);
		MemberResult mr = new MemberResult();
		List<String> tList1 = null;
		if (tList != null && tList.size() > 0){
			tList1 = changCodeTrade(tList);
			if (memResult != null && memResult.size() > 0){
				// 会员-不满足行业
				mr = memResult.get(0);
				if (!tList.contains(mr.getTrade())){
					// 客户行业不满足
					return CouponUtils.produceErrMsg(CouponExceptionType.couponCustTrade, 
							MarketingCouponConstance.BIND_CODE_TRADE_NAME.get(mr.getTrade()), tList1.toString());
				}
			}else{	
				// 非会员, 行业为空
				// 客户行业不满足
				return CouponUtils.produceErrMsg(CouponExceptionType.couponCustTrade, 
						MarketingCouponConstance.BIND_CODE_TRADE_NAME.get(mr.getTrade()), tList1.toString());
			}
		}

		List<String> lList = ctMap.get(MarketingCouponConstance.BIND_CODE_LEVEL);
		List<String> lList1 = null;
		lList1 = changCodeLevel(lList);
		if (lList != null && lList.size() > 0){
			if (memResult != null && memResult.size() > 0){
				
				// 会员-等级不满足
				mr = memResult.get(0);
				if (!lList.contains(mr.getCustGrade())){
					// 客户等级不满足
					return CouponUtils.produceErrMsg(CouponExceptionType.couponCustLevel, 
							MarketingCouponConstance.BIND_CODE_LEVEL_NAME.get(mr.getCustGrade()), lList1.toString());
				}
			}else{
				// 非会员-等级为空
				return CouponUtils.produceErrMsg(CouponExceptionType.couponCustLevel, 
						MarketingCouponConstance.BIND_CODE_LEVEL_NAME.get(mr.getCustGrade()), lList1.toString());
			}
		}
		/********************************** 优惠券使用校验规则-类型条件校验  start ***********************************/
		

		/********************************** 优惠券使用校验规则-区域条件校验  start ***********************************/
		// 线路区域要求不满足
		List<GoodsLine> gls = rule.getGoodsLines();	// 线路区域规则信息
		if (gls != null && gls.size() > 0){
			boolean rs = false;		// 线路区域验证结果
			String lineType = gls.get(0).getRegdemand();
			String lDept = wbInfo.getLeaveDept();		// 运单出发部门
			String aDept = wbInfo.getArrivedDept();		// 运单到达部门
			String loDept = wbInfo.getLeaveOutDept();	// 运单出发外场
			String aoDept = wbInfo.getArrivedOutDept();	// 运单到达外场
			String lineTypeName = matchRegDegmand(lineType);
			for (GoodsLine gl : gls){
				if (MarketingCouponConstance.GOOD_LINE_NULL.equals(lineType)){
					// 未设置，无需判断
				}else if (MarketingCouponConstance.GOOD_LINE_AREA.equals(lineType)){
					// 走货线路-比较外场（出发和到达）
					if (( StringUtils.isEmpty(gl.getBeginDeptOrCityId())||gl.getBeginDeptOrCityId().equals(loDept)) && 
							(StringUtils.isEmpty(gl.getEndDeptOrCityId())||gl.getEndDeptOrCityId().equals(aoDept))) {
						/**
						 * 验证公式：
						 * 		（出发外场相同 or 出发外场为空）  and （到达外场相同  or 到达外场为空）
						 * 验证成功，跳出循环
						 */
						rs = true;
						continue;
					}
				}else if (MarketingCouponConstance.GOOD_LINE_LEAVE.equals(lineType)){
					// 发货区域-比较部门
					if (null == leaveDept){
						// 发货部门信息异常
						return CouponUtils.produceErrMsg(CouponExceptionType.couponUnknow, 
								lineTypeName);
					}
					/**
					 * 验证公式
					 * 		（出发部门=事业部 or 出发部门 = 大区 or 出发部门 = 小区 or 出发部门 = 营业部）
					 * 验证成功，跳出循环
					 */
					if (compareDept(gl.getBeginDeptOrCityId(), leaveDept)){
						rs = true;
						continue;
					}
					
				}else if (MarketingCouponConstance.GOOD_LINE_ARRIVED.equals(lineType)){
					// 到货区域-比较部门
					if (null == arrivedDept){
						// 到货部门信息异常
						return CouponUtils.produceErrMsg(CouponExceptionType.couponUnknow, 
								lineTypeName);
					}
					/**
					 * 验证公式
					 * 		（到货部门=事业部 or 到货部门 = 大区 or 到货部门 = 小区 or 到货部门 = 营业部）
					 * 验证成功，跳出循环
					 */
					if (compareDept(gl.getEndDeptOrCityId(), arrivedDept)){
						rs = true;
						continue;
					}
				}
			}
			if (!rs){
				return CouponUtils.produceErrMsg(CouponExceptionType.couponLineAreaType, 
						lineTypeName);
			}
		}
		/********************************** 优惠券使用校验规则-区域条件校验  end ***********************************/
				
		return cost==null?"0":cost;
	}
	/**
	 * <p>
	 * Description: 转换线路区域类型<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-1-09
	 * @param type		线路区域类型
	 * @return String
	 */
	private static String matchRegDegmand(String lineType) {
		Map<String,String> regdemand = new HashMap<String,String>();
		//线路区域空
		regdemand.put("1", "线路区域空");
		//线路区域
		regdemand.put("2", "线路区域");
		//发货区域
		regdemand.put("3", "发货区域");
		//到货区域
		regdemand.put("4", "到货区域");
		String regdemandName = regdemand.get(lineType);
		if(StringUtils.isEmpty(regdemandName)){
			return "线路区域异常";
		}
		return regdemandName;
	}
	/**
	 * <p>
	 * Description: 转换费用类型<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-06
	 * @param type		费用类型
	 * @return String
	 */
	public static String changeTyprToName(String type) {
		// 运费
		if(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE.equals(type)){
			return MarketingCouponConstance.COST_TYPE_TRANSPORTFEE_NAME;
		}
		// 开单金额
		if(MarketingCouponConstance.COST_TYPE_BILLING.equals(type)){
			return MarketingCouponConstance.COST_TYPE_BILLING_NAME;
		}
		// 包装费
		if(MarketingCouponConstance.COST_TYPE_PACKAGINGFEE.equals(type)){
			return MarketingCouponConstance.COST_TYPE_PACKAGINGFEE_NAME;
		}
		// 保价费
		if(MarketingCouponConstance.COST_TYPE_INSURANCEFEE.equals(type)){
			return MarketingCouponConstance.COST_TYPE_INSURANCEFEE_NAME;
		}
		// 代收费	
		if(MarketingCouponConstance.COST_TYPE_CODFEE.equals(type)){
			return MarketingCouponConstance.COST_TYPE_CODFEE_NAME;
		}
		// 送货费
		if(MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE.equals(type)){
			return MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE_NAME;
		}
		// 接货费
		if(MarketingCouponConstance.COST_TYPE_PICKUPFEE.equals(type)){
			return MarketingCouponConstance.COST_TYPE_PICKUPFEE_NAME;
		}
		return "优惠券校验出现未知异常!";
	}
	/**
	 * <p>
	 * Description: 计算分级抵扣金额<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-15
	 * @param wbAmout		运单金额
	 * @param value			价格基数
	 * @param discount		抵扣基数
	 * @param amout			优惠券抵扣金额
	 * @return String
	 */
	public static String relativeAmout(BigDecimal wbAmout, BigDecimal value, BigDecimal discount, BigDecimal amout){
		/**
		 * min(运单金额/价格基数*抵扣金额基数, 优惠券抵扣金额)
		 * min(wbAmout/value*discount, amout)
		 */
		BigInteger tmp1 = wbAmout.toBigInteger().divide(
				value.toBigInteger()).multiply(discount.toBigInteger());
		BigInteger tmp2 = amout.toBigInteger();
		return tmp1.compareTo(tmp2) >= 0 ? tmp2.toString() : tmp1.toString();
	}
	
	/**
	 * <p>
	 * Description: 发到货区域使用规则校验<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-16
	 * @param dept
	 * @param depts
	 * @return boolean
	 */
	private static boolean compareDept(String dept, DeptInfo depts){
		// 事业部编码
		String orgCode = depts.getOrgCode();
		// 大区编码
		String bigCode = depts.getBigCode();
		// 小区编码
		String smaCode = depts.getSmaCode();
		// 营业部编码
		String salesCode = depts.getSalesCode();

		/**
		 * 验证公式
		 * 		（部门 = 事业部  or 部门 = 大区 or 部门 = 小区 or 部门 = 营业部）
		 * 验证成功，跳出循环
		 */
		if (dept.equals(orgCode) || dept.equals(bigCode)
				|| dept.equals(smaCode) || dept.equals(salesCode)) {
			return true;
		}
		return false;
	}
	/**
	 * <p>
	 * Description: 获取部门组织信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-17
	 * @param list
	 * @return DeptInfo
	 */
	private static DeptInfo getDeptInfo(List<DeptInfo> list){
		// 从列表中获取记录，如果有责返回第一条，如果没有，返回空
		if (list == null || list.size() == 0){
			return null;
		}else{
			return list.get(0);
		}
	}
	/**
	 * <p>
	 * Description: 检查营销计划是否可编辑<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketPlaneVO 营销计划 页面 vo ，marktPlanType 营销计划发券规则 
	 * @return void
	 */
	public static void checkMarketPlaneVOCanUpdate(MarketPlaneVO marketPlaneVO,Marketplan marketPlan) {
		// 优惠券类型 改变 不可保存
		String couponType = marketPlaneVO.getMarketplan().getCouponType();
		if(StringUtils.isEmpty(couponType) || !couponType.equals(marketPlan.getCouponType())){
			ExceptionUtils.createCouponException(CouponExceptionType.couponTypeCanNotChange);
		}
		String couponId = null;
		//获得优惠券创建规则ID
		String couponRuleId = marketPlaneVO.getCouponRule().getId();
		//如果优惠券为手动券
		if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType)){
			couponId = marketPlaneVO.getRuleCouponHand().getId();
		}
		//如果优惠券为自动券
		if(MarketingCouponConstance.COUPON_SENDAUTO.equals(couponType)){
			couponId = marketPlaneVO.getRuleCouponAuto().getId();
		}
		//如果优惠券ID为空
		if(StringUtils.isEmpty(couponId) || StringUtils.isEmpty(couponRuleId)){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlanIdsIsWrong);
		}
		checkMarketPlaneVOCanCreate(marketPlaneVO);
	}
	/**
	 * <p>
	 * Description: 检查手机号码的有效性<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param phone
	 * @return String
	 */
	public static String checkCellphoneValidity(String phone){
		//判断手机号是否是八位数字或是11位以1开头的数字
		if(phone.matches("(^1\\d{10}$)|(^\\d{8}$)")){			
			return MarketingCouponConstance.PHONE_VALIDATE_VALIDITY;
		}else{
			return MarketingCouponConstance.PHONE_VALIDATE_INVALID;
		}
	}
	/**
	 * <p>
	 * Description: 检查手机号码的有效性,在短信发券时使用如果有无效号码抛错<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param phone
	 * @return boolean
	 */
	public static boolean checkCellphoneValid(CouponCellphone phone){
		if( MarketingCouponConstance.PHONE_VALIDATE_INVALID.
				equals(phone.getCellphone())){
			ExceptionUtils.createCouponException(CouponExceptionType.couponCellphoneInvalidity);
		}
		return true;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-3-26
	 * @param d1
	 * @param d2
	 * @return int 
	 * 1：d1晚于d2 
	 * 0：d1等于d2 
	 * -1：d1早于d2
	 */
	public static int compareToDate(Date d1, Date d2) {
		return DateUtils.truncatedCompareTo(d1, d2, Calendar.DATE);
	}
	/**
	 * <p>
	 * Description: 比较时间差<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-20
	 * @param d1
	 * @param d2
	 * @param field 比较时间差单位（年/月/日）
	 * @param amount 比较时间差值
	 * @return
	 * boolean
	 */
	public static boolean comparisonDateByField(Date d1, Date d2, int field, int amount){
		if (null == d1 || null == d2 ) {
			return false;
		}
		Date tmp = null;
		switch (field) {
		case Calendar.DATE:
			tmp = DateUtils.addDays(d1, amount);
			return (compareToDate(tmp,d2) >=0) ? true : false;
		case Calendar.MONTH:
			tmp = DateUtils.addMonths(d1, amount);
			break;
		case Calendar.YEAR:
			tmp = DateUtils.addYears(d1, amount);
			break;
		}
		return (compareToDate(tmp,d2) > 0) ? true : false;
	}
	
	/**
	 * <p>
	 * Description: 验证优惠券查询条件<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-22
	 * @param condition
	 * @return boolean
	 */
	public static boolean canSearchCoupon(CouponSearchCondition condition){
		// 当营销计划名称为空时，使用时间范围与发送时间范围必填其一，且查询时间范围最长为一个月；
		if(StringUtils.isEmpty(condition.getPlanName())){
			if(StringUtils.isEmpty(condition.getCouponNumber()) && StringUtils.isEmpty(condition.getSendtelPhone())){ 
				if ((null == condition.getUseTimeStart() || null == condition.getUseTimeEnd()) &&
						(null == condition.getSendTimeStart() || null == condition.getSendTimeEnd())){
					// 使用时间或发送时间起止异常
					ExceptionUtils.createCouponException(CouponExceptionType.couponSearchTimeError);
				}else{
					if (null == condition.getUseTimeStart() && null == condition.getUseTimeEnd()){
						// 使用时间为空，检查发送时间 
						if (!comparisonDateByField(condition.getSendTimeStart(),
								condition.getSendTimeEnd(), Calendar.MONTH, 1)) {
							// 发送时间范围超过一个月
							ExceptionUtils.createCouponException(CouponExceptionType.couponSearchSendTimeExceed);
						}
					}else 
					if (null == condition.getSendTimeStart() && null == condition.getSendTimeEnd()){
						// 发送时间为空，检查使用时间
						if (!comparisonDateByField(condition.getUseTimeStart(),
								condition.getUseTimeEnd(), Calendar.MONTH, 1)) {
							// 使用时间范围超过一个月
							ExceptionUtils.createCouponException(CouponExceptionType.couponSearchUseTimeExceed);
						}
					}else{
						// 其他情况，两个时间都检查
						if (!comparisonDateByField(condition.getSendTimeStart(),
								condition.getSendTimeEnd(), Calendar.MONTH, 1)) {
							// 发送时间范围超过一个月
							ExceptionUtils.createCouponException(CouponExceptionType.couponSearchSendTimeExceed);
						}
						if (!comparisonDateByField(condition.getUseTimeStart(),
								condition.getUseTimeEnd(), Calendar.MONTH, 1)) {
							// 使用时间范围超过一个月
							ExceptionUtils.createCouponException(CouponExceptionType.couponSearchUseTimeExceed);
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 检查数组为不为空<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-22
	 * @param maketType
	 * @return boolean
	 */
	public static boolean checkAryIsNotNull(String[] maketType) {
		if(null == maketType || 0 == maketType.length || StringUtils.isEmpty(maketType[0])){
			return false;
		}
		return true;
		
	}
	/**
	 * <p>
	 * Description: 检查优惠券数量和手机号码数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @param maketType
	 * @return boolean
	 */
	public static boolean checkCouponCountAndPhoneCount(List<Coupon> coupons,List<CouponCellphone> phones){
		if( phones.size() > coupons.size() ){
			ExceptionUtils.createCouponException(CouponExceptionType.couponCountAndPhonesNotMatch);
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 短信发券后台校验<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @param sendCouponVO
	 * @return boolean
	 */
	public static boolean checkSendCouponVO(SendCouponVO sendCouponVO){
		//营销计划未启用
		if(!MarketingCouponConstance.MARKETPLAN_USING.
				equals(sendCouponVO.getMarketPlanStatus())){
			ExceptionUtils.createCouponException(
					CouponExceptionType.marketPlanStatusUnuse);
		}
		//营销计划不是手动发券
		if(!MarketingCouponConstance.COUPON_SENDHAND.
				equals(sendCouponVO.getCouponType())){
			ExceptionUtils.createCouponException(
					CouponExceptionType.couponCouponTypeNotHanded);
		}
		//营销计划尚未开始
		if(sendCouponVO.getBeginTime().getTime() > System.currentTimeMillis()){
			ExceptionUtils.createCouponException(
					CouponExceptionType.couponMarketPlanStartLater);
		}
		//营销计划已结束
		if(sendCouponVO.getEndTime().getTime() < System.currentTimeMillis()){
			ExceptionUtils.createCouponException(
					CouponExceptionType.couponMarketPlanEnded);
		}
		//优惠券是否已经生成
		if( !MarketingCouponConstance.COUPON_CREATED.equals(
				sendCouponVO.getTypeId())){
			ExceptionUtils.createCouponException(
					CouponExceptionType.couponNotCreated);
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description: 优惠券重发校验<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-27
	 * @param coupon
	 * @return boolean
	 */
	public static boolean checkReSendCoupon(Coupon coupon, int maxTimes, long interval){
		// 优惠券状态校验
		if (!MarketingCouponConstance.COUPON_SENDING.equals(coupon.getStatus())){
			ExceptionUtils.createCouponException(
					CouponExceptionType.reSendCouponErrorStatus);
		}
		// 优惠券发送次数校验
		if (coupon.getSmstimes() >= maxTimes){
			ExceptionUtils.createCouponException(
					CouponExceptionType.reSendCouponErrorTimes);
		}
		// 优惠券发送间隔校验
		long lastSendTime = coupon.getSendTime().getTime();
		long currtTime = System.currentTimeMillis();
		if ((currtTime - lastSendTime)/1000 < interval){
			ExceptionUtils.createCouponException(
					CouponExceptionType.reSendCouponErrorInterval);
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 校验条件信息是否符合查询营销计划规则<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-28
	 * @param marketplan
	 * @return void
	 */
	public static void checkMarketPlaneCanSearch(Marketplan marketplan) {
		// 查询营销计划 起始时间
		Date dateBegin = marketplan.getCreateBeginTime();
		// 查询营销计划 结束时间
		Date dateEnd = marketplan.getCreateEndTime();
		if(null != dateBegin && null != dateEnd ){
			//如果查询起始时间和结束时间之间的差值大于3个月
			if (!comparisonDateByField(dateBegin,dateEnd, Calendar.MONTH, 3)){
				ExceptionUtils.createCouponException(CouponExceptionType.searchMarketPlanDateIsLong);
			}
		}
		//如果业务类型为空则该用户没有权限
		if( StringUtils.isEmpty(marketplan.getBusType())
				||(!MarketingCouponConstance.BUSTYPE_EXP.equals(marketplan.getBusType())
						&&!MarketingCouponConstance.BUSTYPE_LIN.equals(marketplan.getBusType()))){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlanBustypeIsNull);
		}
		
	}
	/**
	 * <p>
	 * Description: 检查短信发券中手机号码条数不能大于最大允许发送<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-28
	 * @param List<CouponCellphone>
	 * @return boolean
	 */
	public static boolean checkMaxSendCouponNumber(List<CouponCellphone> phones){
		//如果手机号码条数大于最大允许发送条数
		if( phones.size() > MarketingCouponConstance.MAX_SEND_COUPON_NUMBER){
			ExceptionUtils.createCouponException(
					CouponExceptionType.maxSendCouponNumberOverflow);
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 检查参数是否为空，并抛出相应异常<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-28
	 * @param objList，type
	 * @return boolean
	 */
	public static boolean checkObjectIsNotNull(String[] objList,CouponExceptionType type){
		for(String str : objList){
			if(StringUtils.isEmpty(str)){
				ExceptionUtils.createCouponException(type);
			}
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 检查参数是否为空，并抛出相应异常<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-28
	 * @param objList，type
	 * @return boolean
	 */
	public static boolean checkObjectIsNotNull(String str,CouponExceptionType type){
		if(StringUtils.isEmpty(str)){
				ExceptionUtils.createCouponException(type);
			}
		return true;
	}
	/**
	 * <p>
	 * Description: 检查营销计划是否为未启用<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-05
	 * @param marketPlanExit
	 * @return boolean
	 */
	public static boolean checkCanStart(Marketplan marketPlanExit){
		if(null != marketPlanExit){
			// 如果 营销计划状态不为  未启用时
			if(!MarketingCouponConstance.MARKETPLAN_NOUSE.equals(marketPlanExit.getMarketStatus())){
					ExceptionUtils.createCouponException(CouponExceptionType.marketPlanStateCanNotOperate);
			}
		}			
		return true;
	}
	/**
	 * <p>
	 * Description: 检查营销计划是否符合创建规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-05
	 * @param marketPlanExit
	 * @return boolean
	 */
	public static boolean checkMarketPlanCanCreate(Marketplan marketPlan){
		// 营销计划为空 不满足
		if(null == marketPlan){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlanIsNull);
		}
		// 营销计划名称
		String planName = marketPlan.getPlanName();
		// 营销计划名称为空 不满足
		if(StringUtils.isEmpty(planName)){
			ExceptionUtils.createCouponException(CouponExceptionType.couponPlanNameIsNull);
		}
		// 营销计划类型
		String couponType = marketPlan.getCouponType();
		// 营销计划类型为空 不满足
		if(StringUtils.isEmpty(couponType)){
			ExceptionUtils.createCouponException(CouponExceptionType.couponPlanTypeIsNull);
		}
		//业务类型不为零担或快递 
		if( StringUtils.isEmpty(marketPlan.getBusType())
				||(!MarketingCouponConstance.BUSTYPE_EXP.equals(marketPlan.getBusType())
						&&!MarketingCouponConstance.BUSTYPE_LIN.equals(marketPlan.getBusType()))){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlanBustypeIsNull);
		}
		//优惠类型为空或者优惠类型不合法
		if( StringUtils.isEmpty(marketPlan.getDiscountType())||
				!MarketActivityConstance.discountType.contains(marketPlan.getDiscountType()) ){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlanDiscountIsIllegal);
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 检查参数手动发券时,已发送优惠券数量是否大于优惠券总数<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param handMarketPlan
	 * @return boolean
	 */
	public static boolean checkHandMarketPlan(HandMarketPlan handMarketPlan){
		//手动发券营销计划不为空
		if(handMarketPlan!=null){
			//手动券可创建条数大于0
			if(handMarketPlan.getCouponCreateNum() >0 ){
				return true;	
			}
		}
		return false;
	}
	/**
	 * <p>
	 * Description: 导出优惠券Excel如果导出数量大于两万则不允许生成<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param handMarketPlan
	 * @return boolean
	 */
	public static boolean checkOutputExcelCouponNum( int count){
		//导出优惠券条数大于20000
		if( count > MarketingCouponConstance.MAX_COUPON_EXCEL_NUM ){
			ExceptionUtils.createCouponException(
					CouponExceptionType.maxCouponExcelNumOverflow );
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 校验优惠券使用规则，参数是否为空<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-07
	 * @param rule优惠券使用规则
	 * @return 
	 */
	public static void checkCouponRuleIsNotNull(CouponRule rule) {
		if(null == rule){
			ExceptionUtils.createCouponException(CouponExceptionType.couponCouponRuleIsNull);
		}
	}
	/**
	 * <p>
	 * Description: 校验优惠券使用规则，检查运单信息是否为空<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-07
	 * @param wbInfo 运单信息
	 * @return 
	 */
	public static void checkWaybillInfoIsNull(WaybillInfo wbInfo) {
		// 运单信息为空
		if(null == wbInfo){
			ExceptionUtils.createCouponException(CouponExceptionType.wbInfoIsntExsit);
		}
		// 运单号为空
		if(StringUtils.isEmpty(wbInfo.getWaybillNumber())){
			ExceptionUtils.createCouponException(CouponExceptionType.waybillNumberIsntExsit);
		}

		// 运单总额  
		if(null == wbInfo.getTotalAmount()){
			ExceptionUtils.createCouponException(CouponExceptionType.totalAmountIsntExsit);
		}

		// 运单金额明细
		if(null == wbInfo.getAmountList()){
			ExceptionUtils.createCouponException(CouponExceptionType.amountListExsit);
		}
	}
	/**
	 * <p>
	 * Description: 校验日期是否过期<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-07
	 * @param time 当前时间，time2 结束时间，couponOverdue 过期状态 ，couponNum 优惠券编码
	 * @return 
	 */
	public static String checkUseIsOverDue(String typeId,long time, long time2,String sendtelPhone) {
		if(time > time2){
			 // 已过期
			return MarketingCouponConstance.COUPON_OVERDUE; 
		}
		if(MarketingCouponConstance.COUPON_SENDHAND.equals(typeId)&&
				StringUtils.isEmpty(sendtelPhone)){
			// 未发送
			return MarketingCouponConstance.COUPON_NOSEND;  
		}
		if(MarketingCouponConstance.COUPON_SENDAUTO.equals(typeId) || 
				MarketingCouponConstance.COUPON_SENDHAND.equals(typeId)&&StringUtils.isNotEmpty(sendtelPhone)){
			// 已发送
			return MarketingCouponConstance.COUPON_SENDING;  
		}
		return "false";
	}
	public static boolean checkCallableFlag(String flag){
		if("1".equals(flag)){
			return true;
		}
		return false;
	}
	/**
	 * <p>
	 * Description: 校验手动发券是否符合创建规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-15
	 * @param ruleCouponHand 手动发券规则
	 * @return 
	 */
	public static void checkRuleCouponHandCanCreate(RuleCouponHand ruleCouponHand) {
		// 手动发券规则为空 不满足
		if(null == ruleCouponHand){
			ExceptionUtils.createCouponException(CouponExceptionType.couponHandIsNull);
		}
		// 手动发券规则-优惠券面值
		String couponValue = ruleCouponHand.getCouponValue();
		// 优惠券使用金额为空 、不为整数、负数、为0 不满足，最长20 位
		matchValue(CouponExceptionType.couponUseValueIsNull,20,couponValue);
		// 手动发券规则-优惠券数量
		String quantity = ruleCouponHand.getCouponQuantity();
		// 优惠券手动发券 发券数量为空 不满足，最长6位
		matchValue(CouponExceptionType.couponHandQuantityIsNull,6,quantity);
		// 手动发券规则-优惠券数量 最大值不能超过10万 
		if(MarketingCouponConstance.COUPON_MAXHAND < Integer.parseInt(quantity)){
			ExceptionUtils.createCouponException(CouponExceptionType.couponHandQuantityIsLarger);
		}
	}
	/**
	 * <p>
	 * Description: 校验自动发券是否符合创建规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-15
	 * @param ruleCouponAuto 自动发券规则
	 * @return 
	 */
	public static void checkRuleCouponAutoCanCreate(RuleCouponAuto ruleCouponAuto,
			SimpleDateFormat sdf,Date beginCouponTime,Date nowTime) {
		// 自动发券规则为空 不满足
		if(null == ruleCouponAuto){
			ExceptionUtils.createCouponException(CouponExceptionType.couponAutoIsNull);
		}
		// 自动优惠券-开始时间
		Date beginTime = ruleCouponAuto.getAutoBeginTime();
		// 自动优惠券-结束时间
		Date endTime = ruleCouponAuto.getAutoEndTime();
		// 校验时间是否符合创建规则
		checkDateCanCreate(sdf, beginCouponTime, nowTime, 
				beginTime, endTime, CouponExceptionType.couponAutoDateIsWrong);
		// 自动优惠券-返券金额为空
		List<AutoCouponCost> autoCouponCosts = ruleCouponAuto.getAutoCouponCost();
		// 校验返券金额；
		checkAutoCouponCost(autoCouponCosts);
		for(AutoCouponCost autoCouponCost:autoCouponCosts){
			//返券金额下限
			matchValue(CouponExceptionType.couponUseValueIsNull,20,autoCouponCost.getCoupoCost(),autoCouponCost.getCostDown());
		}
		beginCouponTime = null;
		nowTime = null;
	}
	/**
	 * <p>
	 * Description: 校验游优惠券使用规则是否符合创建规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-15
	 * @param couponRule 优惠券使用规则
	 * @return 
	 */
	public static void checkCouponRuleCanCreate(CouponRule couponRule,
			SimpleDateFormat sdf,Date beginCouponTime,Date nowTime) {
		// 自动发券规则为空 不满足
		if(null == couponRule){
			ExceptionUtils.createCouponException(CouponExceptionType.couponRuleIsNull);
		}
		// 金额类型
		String costType = couponRule.getCostType();
		// 金额
		String costValue = couponRule.getValue();
		// 打折金额
		String costDiscount = couponRule.getDiscount();
		// 增值费类型
		String addValueMode = couponRule.getCostAddedMode();
		// 增值费金额
		String addValue = couponRule.getCostAdded();
		// 金额类型-为空
		if(StringUtils.isEmpty(costType)){
			ExceptionUtils.createCouponException(CouponExceptionType.AutoCouponCostTypeIsNull);
		}
		// 校验-金额
		matchValue(CouponExceptionType.AutoCouponCostTypeIsNull,20,costValue);
		// 如果-金额类型-是-分级抵扣-则校验costDiscount
		if(MarketingCouponConstance.COST_MODE_RATED.equals(couponRule.getCostMode())){
			matchValue(CouponExceptionType.couponRuleCostDiscountIsWrong,20,costDiscount);
		}
		// 增值费类型-不能为空
		if(StringUtils.isEmpty(addValueMode)){
			ExceptionUtils.createCouponException(CouponExceptionType.couponRuleValueIsWrong);
		}
		// 增值费类型-不为空的话
		if(StringUtils.isNotEmpty(addValueMode) ){
			// 未勾选-增值费类型-0
			if(MarketingCouponConstance.ADDED_MODE_CLOSE.equals(addValueMode) && 
					StringUtils.isNotEmpty(addValue)){
				ExceptionUtils.createCouponException(CouponExceptionType.couponRuleValueIsWrong);
			}
			// 勾选-增值费类型-1
			if(MarketingCouponConstance.ADDED_MODE_OPEN.equals(addValueMode)){
				matchValue(CouponExceptionType.couponRuleValueIsWrong,20,addValue);
			}
		}
		// 使用规则开始日期必须不早于当前日期，结束日期不早于开始日期；
		Date beginTime = couponRule.getBegintime();
		Date endTime = couponRule.getEndtime();
		checkDateCanCreate(sdf, beginCouponTime, nowTime, 
				beginTime, endTime, CouponExceptionType.couponRuleDateIsWrong);
		
		// 短信内容超过80个字 不满足
		String smsContent = couponRule.getSmsContent();
		if(StringUtils.isEmpty(smsContent) || 80 < smsContent.length()){
			ExceptionUtils.createCouponException(CouponExceptionType.couponSmsContentIsLong);
		}
		// 优惠券描述超过500个字 不满足
		String describe = couponRule.getDescribe();
		if(StringUtils.isEmpty(describe) || 500 < describe.length()){
			ExceptionUtils.createCouponException(CouponExceptionType.couponDescribeIsLong);
		}
	}
	/**
	 * <p>
	 * Description: 创建营销计划-检查优惠券时间-是否符合创建规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-12-15
	 * @param couponRule 优惠券使用规则
	 * @return 
	 */
	public static void checkDateCanCreate(SimpleDateFormat sdf,Date beginCouponTime,Date nowTime,
			Date beginTime,Date endTime,CouponExceptionType type){
		// 两个时间 都不允许为空
		if(null == beginTime || null == endTime){
			ExceptionUtils.createCouponException(type);
		}
		try {
			// 现在时间-转换格式-只比较天数-不涉及时分秒
			nowTime = (Date) sdf.parse(sdf.format(new Date()));
			// 优惠券开始时间-转换格式-只比较天数-不涉及时分秒
			beginCouponTime = (Date) sdf.parse(sdf.format(beginTime));
			
		} catch (ParseException e) {
			ExceptionUtils.createCouponException(type);
		}
		// 返券开始日期必须不早于当前日期，返券结束日期不早于返券开始日期；
		if(nowTime.getTime() > beginCouponTime.getTime() || beginTime.getTime() >= endTime.getTime()){

			ExceptionUtils.createCouponException(type);
		}
	}
	/**
	 * <p>
	 * Description: 优惠券查询-登陆用户是营销活动管理小组-判断所选择部门是否是事业部<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	public static boolean checkRightMarketingDept(String deptName){
		if(StringUtils.isEmpty(deptName)){
			return false;
		}
		// 选择具体的事业部
		return deptName.matches(MarketingCouponConstance.SHIYEBU_NAME_QUERY);
	}
	/**
	 * <p>
	 * Description: 产品类型-code转中文名字-用户前台显示<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-27
	 */
	public static List<String> changCodeProduct(List<String> prList){
		if(null == prList && 0 == prList.size()){
			return null;
		}
		List<String> pList = new ArrayList<String>();
		for(String str : prList){
			pList.add(MarketingCouponConstance.BIND_CODE_PRODUCT_NAME.get(str));
		}
		return pList;
	}
	/**
	 * <p>
	 * Description: 订单来源-code转中文名字-用户前台显示<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-27
	 */
	public static List<String> changCodeOrder(List<String> orList){
		if(null == orList && 0 == orList.size()){
			return null;
		}
		List<String> oList = new ArrayList<String>();
		for(String str : orList){
			oList.add(MarketingCouponConstance.BIND_CODE_ORDER_NAME.get(str));
		}
		return oList;
	}
	/**
	 * <p>
	 * Description: 客户行业-code转中文名字-用户前台显示<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-27
	 */
	public static List<String> changCodeTrade(List<String> trList){
		if(null == trList && 0 == trList.size()){
			return null;
		}
		List<String> tList = new ArrayList<String>();
		for(String str : trList){
			tList.add(MarketingCouponConstance.BIND_CODE_TRADE_NAME.get(str));
		}
		return tList;
	}
	/**
	 * <p>
	 * Description: 客户等级-code转中文名字-用户前台显示<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-27
	 */
	public static List<String> changCodeLevel(List<String> drList){
		if(null == drList && 0 == drList.size()){
			return null;
		}
		List<String> dList = new ArrayList<String>();
		for(String str : drList){
			dList.add(MarketingCouponConstance.BIND_CODE_LEVEL_NAME.get(str));
		}
		return dList;
	}
	/**
	 * <p>
	 * Description: 检查接口参数是否传递正确<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-03
	 */
	public static void checkCouponForInterfaceParam(CouponForInterface cfi){
		//检查接口优惠券实体是否为空
		if( cfi == null ){
			ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceIsNull);
		}
		//校验接口实体的标杆编码是否为空
		if( cfi.getDeptStandardCode() == null || "".equals(cfi.getDeptStandardCode())){
			ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceStandardCodeIsNull);
		}
		//校验接口实体的活动类型是否为空
		if( cfi.getActivityType() == null || "".equals(cfi.getActivityType())){
			ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceActivityTypeIsNull);
		}
		//校验优惠券生效时间，若优惠券失效时间小于当前时间则不允许创建
		if( cfi.getEndTime()== null || cfi.getEndTime().getTime()<System.currentTimeMillis()){
			ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceEndTimeIsIllegal);
		}
		//校验优惠券生效时间，若优惠券失效时间小于当前时间则不允许创建
		if( cfi.getBeginTime()== null){
			ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceBeginTimeIsIllegal);
		}
		//优惠券失效时间不能大于生效时间
		if( cfi.getEndTime().getTime()-cfi.getBeginTime().getTime()<=0){
			ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceEndTimeEarlierThanBeginTime);
		}
	}
	/**
	 * <p>
	 * Description: 校验是否有已启用的用于接口的手动券营销计划<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-03
	 */
	public static boolean checkInterfaceMarketPlanIsActive( Coupon coupon ){
		//校验营销计划编码是否为空  
		//没有正在执行的营销计划为false,有正在执行的营销计划为true
		if( coupon == null || "".equals(coupon.getMarketPlanId())){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * <p>
	 * Description: 校验接口创建的营销计划是否符合规则<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 */
	public static void checkInterfaceMarketPlanCanCreate( MarketPlaneVO marketPlanVO){
		// 检查参数是否为空
		if(null == marketPlanVO){
			ExceptionUtils.createCouponException(CouponExceptionType.marketPlaneVOIsNull);
		}
		Marketplan marketplan = marketPlanVO.getMarketplan();// 营销计划
		// 校验营销计划是否符合创建规则
		checkMarketPlanCanCreate(marketplan);
		//校验手动券是否符合规则
		RuleCouponHand couponHand = marketPlanVO.getRuleCouponHand();
		// 手动发券规则为空 不满足
		if(null == couponHand){
			ExceptionUtils.createCouponException(CouponExceptionType.couponHandIsNull);
		}
		// 手动发券规则-优惠券面值
		String couponValue = couponHand.getCouponValue();
		// 优惠券使用金额为空 、不为整数、负数、为0 不满足，最长20 位
		if( couponValue == null || !couponValue.matches("^\\d*.{0,1}\\d*$")){
			ExceptionUtils.createCouponException(CouponExceptionType.couponUseValueIsNull);
		}
		//校验使用规则
		CouponRule couponRule = marketPlanVO.getCouponRule();
		// 自动发券规则为空 不满足
		if(null == couponRule){
				ExceptionUtils.createCouponException(CouponExceptionType.couponRuleIsNull);
		}
		// 金额类型
		String costType = couponRule.getCostType();
		// 金额
		String costValue = couponRule.getValue();
		// 打折金额
		String costDiscount = couponRule.getDiscount();
		// 增值费类型
		String addValueMode = couponRule.getCostAddedMode();
		// 增值费金额
		String addValue = couponRule.getCostAdded();
		// 金额类型-为空  或金额类型不为运费或开单金额
		if( StringUtils.isEmpty(costType)||
				(!MarketingCouponConstance.COST_TYPE_TRANSPORTFEE.equals(costType)
				  &&!MarketingCouponConstance.COST_TYPE_BILLING.equals(costType))){
			ExceptionUtils.createCouponException(CouponExceptionType.AutoCouponCostTypeIsNull);
		}
		// 校验-金额
		matchValue(CouponExceptionType.AutoCouponCostTypeIsNull,20,costValue);
		// 如果-金额类型-是-分级抵扣-则校验costDiscount
		if(MarketingCouponConstance.COST_MODE_RATED.equals(couponRule.getCostMode())){
			matchValue(CouponExceptionType.couponRuleCostDiscountIsWrong,20,costDiscount);
		}
		// 增值费类型-不能为空
		if(StringUtils.isEmpty(addValueMode)){
			ExceptionUtils.createCouponException(CouponExceptionType.couponRuleValueIsWrong);
		}
		// 增值费类型-不为空的话
		if(StringUtils.isNotEmpty(addValueMode) ){
			// 未勾选-增值费类型-0
			if(MarketingCouponConstance.ADDED_MODE_CLOSE.equals(addValueMode) && 
					StringUtils.isNotEmpty(addValue)){
				ExceptionUtils.createCouponException(CouponExceptionType.couponRuleValueIsWrong);
			}
			// 勾选-增值费类型-1
			if(MarketingCouponConstance.ADDED_MODE_OPEN.equals(addValueMode)){
				matchValue(CouponExceptionType.couponRuleValueIsWrong,20,addValue);
			}
		}		
		/**
		 * 校验走货线路
		 */	
		if( couponRule.getGoodsLines() != null && couponRule.getGoodsLines().size() > 0 ){
			//得到走货线路集合
			List<GoodsLine> goodsLines = couponRule.getGoodsLines();
			//循环集合查询走货线路是否相同
			for( int i=0 ; i<goodsLines.size()-1 ; i++){
				GoodsLine g = goodsLines.get(i);
				for(int j=i+1 ; j < goodsLines.size();j++){
					if( g.equals(goodsLines.get(j))){
						ExceptionUtils.createCouponException
							(CouponExceptionType.couponForInterfaceGoodslineRepeat);
					}
				}
			}
			//线路要求不能大于10条
			if( couponRule.getGoodsLines().size() > 10 ){
				ExceptionUtils.createCouponException(CouponExceptionType.couponRuleGoodsLineBeyondMaxSize);
			}
			for( GoodsLine g : couponRule.getGoodsLines()){
				//线路为空则发货区域ID和到货区域ID都为空
				if( MarketingCouponConstance.GOOD_LINE_NULL.equals(g.getRegdemand())){
					if( !StringUtils.isEmpty(g.getBeginDeptOrCityId())
							||!StringUtils.isEmpty(g.getEndDeptOrCityId())){
						ExceptionUtils.createCouponException(CouponExceptionType.couponRuleGoodsLineIsIllegal);
					}
				//线路为走货线路则发货区域或到货区域都不能为空
				}else if( MarketingCouponConstance.GOOD_LINE_AREA.equals(g.getRegdemand())){
					if( StringUtils.isEmpty(g.getBeginDeptOrCityId())
							&& StringUtils.isEmpty(g.getEndDeptOrCityId())){
						ExceptionUtils.createCouponException(CouponExceptionType.couponRuleGoodsLineIsIllegal);
					}
				//线路为发货区域则发货区域ID不能为空
				}else if( MarketingCouponConstance.GOOD_LINE_LEAVE.equals(g.getRegdemand())){
					if( StringUtils.isEmpty(g.getBeginDeptOrCityId())
							||!StringUtils.isEmpty(g.getEndDeptOrCityId())){
						ExceptionUtils.createCouponException(CouponExceptionType.couponRuleGoodsLineIsIllegal);
					}
				//线路为到货区域则到货区域ID不能为空
				}else if( MarketingCouponConstance.GOOD_LINE_ARRIVED.equals(g.getRegdemand())){
					if( StringUtils.isEmpty(g.getEndDeptOrCityId())
							||!StringUtils.isEmpty(g.getBeginDeptOrCityId())){
						ExceptionUtils.createCouponException(CouponExceptionType.couponRuleGoodsLineIsIllegal);
					}
				//其他都为错误
				}else{
					ExceptionUtils.createCouponException(CouponExceptionType.couponRuleGoodsLineIsIllegal);
				}
			}
		}
	}	
}
