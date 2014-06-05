package com.deppon.crm.module.coupon.server.utils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;


public class CouponUtils {
	//一天起始的八位数字
	public static int number;
	//当前优惠券中间八位
	public static int current;
	//当天日期
	public static String today;
	//Random实体
	public static Random random; 
	//日期转换格式
	public static final String transfer = "yyMMdd";
	static{
		//设置今天的日期
		today = CouponMsgUtil.formatDate(new Date(), transfer);
		number = 10000000 + (int)System.currentTimeMillis()%10000000;
		current = number;
		random = new Random();
	}
	/**
	 * <p>
	 * Description: 根据错误编码返回错误提示信息（优惠券校验用）<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-15
	 * @param errorType		异常类型
	 * @param para			参数数组
	 * @return String
	 */
	public static String produceErrMsg(CouponExceptionType errorType, Object...para){
		//错误信息提示-您输入的优惠券编码不存在
		String couponIsntExsit 			= "您输入的优惠券编码不存在！";
		//错误信息提示-您的优惠券已过期，不能使用
		String couponIsOverdue 			= "您的优惠券已过期，不能使用！";
		//错误信息提示-您的优惠券已使用，不可重复使用
		String couponIsUsed 			= "您的优惠券已使用，不可重复使用！(waybill:{0};value:{1})";
		//错误信息提示-优惠券尚未生效，请于{0}至{1}期间使用
		String couponIneffectiveDate 	= "优惠券尚未生效，请于{0}至{1}期间使用！";
		//错误信息提示-{0}费至少为{1}元，方可使用该优惠券
		String couponAmountType 		= "{0}费至少为{1}元，方可使用该优惠券！";
		//错误信息提示-[0]费至少为[1]元，方可使用该优惠券
		String couponAppreciationType 	= "{0}至少为{1}元，方可使用该优惠券！";
		//错误信息提示-产品类型不符合优惠券使用要求，该优惠券的产品类型要求如下：{0}
		String couponProductType 		= "产品类型不符合优惠券使用要求，该优惠券的产品类型要求如下：{0}！";
		//错误信息提示-订单来源不符合优惠券使用要求，该优惠券的订单来源要求如下：{0}
		String couponOrderSource 		= "订单来源不符合优惠券使用要求，该优惠券的订单来源要求如下：{0}！";
		//错误信息提示-客户等级不符合优惠券使用要求，当前客户等级为：{0}，该优惠券的客户等级要求如下：{1}
		String couponCustLevel 			= "客户等级不符合优惠券使用要求，当前客户等级为：{0}，该优惠券的客户等级要求如下：{1}！";
		//错误信息提示-客户行业不符合优惠券使用要求，当前客户所属行业为：[0]，该优惠券的客户行业要求为：{1}
		String couponCustTrade 			= "客户行业不符合优惠券使用要求，当前客户所属行业为：{0}，该优惠券的客户行业要求为：{1}！";
		//错误信息提示-{0}不符合优惠券使用要求，详情请查看该优惠券的使用规则
		String couponLineAreaType 		= "{0}不符合优惠券使用要求，详情请查看该优惠券的使用规则！";
		//错误信息提示-优惠券{0}校验出现未知异常
		String defaultType 				= "优惠券{0}校验出现未知异常！";
		//错误信息提示-开单金额或运费不能为空
		String couponAmountTypeException = "开单金额或运费不能为空！";
		
		switch (errorType) {
			case couponIsntExsit:
				/**
				 * 优惠券编码不存在：您输入的优惠券编码不存在！
				 */
				return MessageFormat.format(couponIsntExsit, para);
			case couponIsOverdue:
				/**
				 * 优惠券状态为已过期：您的优惠券已过期，不能使用！
				 */
				return MessageFormat.format(couponIsOverdue, para);
			case couponIsUsed:
				/**
				 * 优惠券状态为已使用：您的优惠券已使用，不可重复使用！
				 */
				return MessageFormat.format(couponIsUsed, para);
			case couponIneffectiveDate:
				/**
				 * 当前时间早于优惠券生效时间：优惠券尚未生效，请于****年**月**日至****年**月**日期间使用！
				 */
				return MessageFormat.format(couponIneffectiveDate, para);
			case couponAmountType:
				/**
				 * 开单金额或运费不满足最低费用要求：**费至少为**元，方可使用该优惠券！
				 */
				return MessageFormat.format(couponAmountType, para);
			case couponAppreciationType:
				/**
				 * 增值费不满足最低金额要求：**费至少为**元，方可使用该优惠券！
				 */
				return MessageFormat.format(couponAppreciationType, para);
			case couponProductType:
				/**
				 * 产品类型不符合使用条件：产品类型不符合优惠券使用要求，该优惠券的产品类型要求如下：****、****！
				 */
				return MessageFormat.format(couponProductType, para);
			case couponOrderSource:
				/**
				 * 订单来源不符合使用条件：订单来源不符合优惠券使用要求，该优惠券的订单来源要求如下：****、****！
				 */
				return MessageFormat.format(couponOrderSource, para);
			case couponCustLevel:
				/**
				 * 客户等级不符合使用条件：客户等级不符合优惠券使用要求，当前客户等级为：****，该优惠券的客户等级要求如下：****、****！
				 */
				return MessageFormat.format(couponCustLevel, para);
			case couponCustTrade:
				/**
				 * 客户行业不符合使用条件：客户行业不符合优惠券使用要求，当前客户所属行业为：****，该优惠券的客户行业要求为：****、****！
				 */
				return MessageFormat.format(couponCustTrade, para);
			case couponLineAreaType:
				/**
				 * 走货线路（或为发货区域、到达区域，具体类型与优惠券使用条件一致）不符合优惠券使用要求，详情请查看该优惠券的使用规则！
				 */
				return MessageFormat.format(couponLineAreaType, para);
			case couponAmountTypeException:
				/**
				 * 开单金额或运费不能为空！
				 */
				return MessageFormat.format(couponAmountTypeException,"!");	
			default:
				return MessageFormat.format(defaultType, para);
		}
	}
	
	/**
	 * <p>
	 * Description: 运单金额明细实体转换Map方法<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-15
	 * @param ai
	 * @return Map
	 */
	
	public static Map<String, BigDecimal> amountInfoToMap(List<AmountInfo> aiList){
		HashMap<String, BigDecimal> aiMap = new HashMap<String, BigDecimal>();
		/**
		 * 初始化，所有金额类型默认为0
		 */
		// 运费
		aiMap.put(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE, new BigDecimal(0));
		// 开单金额
		aiMap.put(MarketingCouponConstance.COST_TYPE_BILLING, new BigDecimal(0));
		// 包装费
		aiMap.put(MarketingCouponConstance.COST_TYPE_PACKAGINGFEE, new BigDecimal(0));
		// 保价费
		aiMap.put(MarketingCouponConstance.COST_TYPE_INSURANCEFEE, new BigDecimal(0));
		// 代收费
		aiMap.put(MarketingCouponConstance.COST_TYPE_CODFEE, new BigDecimal(0));
		// 送货费
		aiMap.put(MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE, new BigDecimal(0));
		// 接货费
		aiMap.put(MarketingCouponConstance.COST_TYPE_PICKUPFEE, new BigDecimal(0));
		// 循环读取金额类型及金额数据
		for (AmountInfo ai : aiList){
			// 运费
			if (MarketingCouponConstance.COST_TYPE_TRANSPORTFEE.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_TRANSPORTFEE, ai.getValuationAmonut());
				continue;
			}
			// 开单金额
			if (MarketingCouponConstance.COST_TYPE_BILLING.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_BILLING, ai.getValuationAmonut());
				continue;
			}
			// 包装费
			if (MarketingCouponConstance.COST_TYPE_PACKAGINGFEE.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_PACKAGINGFEE, ai.getValuationAmonut());
				continue;
			}
			// 保价费
			if (MarketingCouponConstance.COST_TYPE_INSURANCEFEE.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_INSURANCEFEE, ai.getValuationAmonut());
				continue;
			}
			// 代收费
			if (MarketingCouponConstance.COST_TYPE_CODFEE.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_CODFEE, ai.getValuationAmonut());
				continue;
			}
			// 送货费
			if (MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_DELIVERYGOODSFEE, ai.getValuationAmonut());
				continue;
			}
			// 接货费
			if (MarketingCouponConstance.COST_TYPE_PICKUPFEE.equals(ai.getValuationCode())){
				aiMap.put(MarketingCouponConstance.COST_TYPE_PICKUPFEE, ai.getValuationAmonut());
				continue;
			}
		}
		
		return aiMap;
	}
	
	/**
	 * <p>
	 * Description: 优惠券条件实体转换Map方法<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-16
	 * @param conditionTypes
	 * @return HashMap<String,String>
	 */
	public static HashMap<String, List<String>> conditionTypeToMap(List<ConditionType> conditionTypes){
		HashMap<String, List<String>> ctMap = new HashMap<String, List<String>>();
		// 产品类型
		List<String> pList = new ArrayList<String>();
		// 订单来源
		List<String> oList = new ArrayList<String>();
		// 客户行业
		List<String> tList = new ArrayList<String>();
		// 客户等级
		List<String> lList = new ArrayList<String>();
		
		for (ConditionType ct : conditionTypes){
			if (MarketingCouponConstance.BIND_TYPE_PRODUCT.equals(ct.getType())){
				// 产品类型
				pList.add(ct.getValue());
			}
			if (MarketingCouponConstance.BIND_TYPE_ORDER.equals(ct.getType())){
				// 订单来源
				oList.add(ct.getValue());
			}
			if (MarketingCouponConstance.BIND_TYPE_TRADE.equals(ct.getType())){
				// 客户行业
				tList.add(ct.getValue());
			}
			if (MarketingCouponConstance.BIND_TYPE_LEVEL.equals(ct.getType())){
				// 客户等级
				lList.add(ct.getValue());
			}
		}
		/**
		 * 将结果转为Map
		 */
		// 产品类型
		ctMap.put(MarketingCouponConstance.BIND_CODE_PRODUCT, 	pList);
		// 订单来源
		ctMap.put(MarketingCouponConstance.BIND_CODE_ORDER, 	oList);
		// 客户行业
		ctMap.put(MarketingCouponConstance.BIND_CODE_TRADE, 	tList);
		// 客户等级
		ctMap.put(MarketingCouponConstance.BIND_CODE_LEVEL, 	lList);
		
		return ctMap;
	}

	/**
     * 
     * <p>
     * Description:处理查询出来的发到货路线，如果是到货(发到货类型为“4”)，则给发货名赋值给前台展示<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-7-10
     * @param marketPlaneVO
     * void
     */
    public static void goodsLineOperation(MarketPlaneVO marketPlaneVO) {
        GoodsLine goodsLine = null;
        //处理创建优惠券的线路要求
        if(marketPlaneVO.getRuleCouponAuto()!=null){
            List<GoodsLine> createGoodsLine = marketPlaneVO.getRuleCouponAuto().getCreateGoodsLine();
            for (int i = 0; i < createGoodsLine.size(); i++) {
                goodsLine = createGoodsLine.get(i);
                if (MarketingCouponConstance.GOOD_LINE_ARRIVED.equals(goodsLine.getRegdemand())) {
                    goodsLine.setBeginDeptOrCityName(goodsLine.getEndDeptOrCityName());
                    goodsLine.setBeginDeptOrCityId(goodsLine.getEndDeptOrCityId());
                }
            }
        }
        //处理使用优惠券的线路要求
        if(marketPlaneVO.getCouponRule()!=null){
            List<GoodsLine> goodsLines = marketPlaneVO.getCouponRule().getGoodsLines();
            for (int i = 0; i < goodsLines.size(); i++) {
                goodsLine = goodsLines.get(i);
                if (MarketingCouponConstance.GOOD_LINE_ARRIVED.equals(goodsLine.getRegdemand())) {
                    goodsLine.setBeginDeptOrCityName(goodsLine.getEndDeptOrCityName());
                    goodsLine.setBeginDeptOrCityId(goodsLine.getEndDeptOrCityId());
                }
            }
        }
    }
    /**
     * 
     * <p>
     * Description:前台显示区域名和id都是发货路线的，所以要对传回来的数据进行处理
     * 如果发到货类型为“到货线路”，则要给到货的区域id和区域名称赋值，清空发货区域id和区域名
     * </p>
     * @author 石应华
     * @version 0.1 2013-7-10
     * @param marketPlaneVO
     * void
     */
    public static void saveGoodsLineOperation(MarketPlaneVO marketPlaneVO){
        //创建优惠券的线路要求
        List<GoodsLine> createGoodsLine = marketPlaneVO.getRuleCouponAuto().getCreateGoodsLine();
        GoodsLine goodsLine = null;
        for (int i = 0; i < createGoodsLine.size(); i++) {
            goodsLine = createGoodsLine.get(i);
            //如果发到货类型为“到货线路”，
            //则要给到货的区域id和区域名称赋值，
            //清空发货区域id和区域名
            if(MarketingCouponConstance.GOOD_LINE_ARRIVED.equals(goodsLine.getRegdemand())){
                //把发货名赋值给到货名
                goodsLine.setEndDeptOrCityName(""+goodsLine.getBeginDeptOrCityName());
                goodsLine.setEndDeptOrCityId(""+goodsLine.getBeginDeptOrCityId());
                goodsLine.setBeginDeptOrCityName(null);
                goodsLine.setBeginDeptOrCityId(null);
            }
            //如果发到货类型为“发货线路”，
            //清空到货区域id和区域名
            if(MarketingCouponConstance.GOOD_LINE_LEAVE.equals(goodsLine.getRegdemand())){
                goodsLine.setEndDeptOrCityName(null);
                goodsLine.setEndDeptOrCityId(null);
            }
        }
        //使用优惠券的线路要求
        List<GoodsLine> goodsLines = marketPlaneVO.getCouponRule().getGoodsLines();
        for (int i = 0; i < goodsLines.size(); i++) {
            goodsLine = goodsLines.get(i);
            //如果发到货类型为“到货线路”，
            //则要给到货的区域id和区域名称赋值，
            //清空发货区域id和区域名
            if(MarketingCouponConstance.GOOD_LINE_ARRIVED.equals(goodsLine.getRegdemand())){
                //把发货名赋值给到货名,把发货id赋值给到货id
                goodsLine.setEndDeptOrCityName(""+goodsLine.getBeginDeptOrCityName());
                goodsLine.setEndDeptOrCityId(""+goodsLine.getBeginDeptOrCityId());
                goodsLine.setBeginDeptOrCityName(null);
                goodsLine.setBeginDeptOrCityId(null);
            }
            //如果发到货类型为“发货线路”，
            //清空到货区域id和区域名
            if(MarketingCouponConstance.GOOD_LINE_LEAVE.equals(goodsLine.getRegdemand())){
                goodsLine.setEndDeptOrCityName(null);
                goodsLine.setEndDeptOrCityId(null);
            }
        }
    }
    /**
     * 
     * <p>
     * Description:将接口传来的参数转换为营销计划实体
     * </p>
     * @author ZhouYuan
     * @version 0.1 2013-9-3
     * @param CouponForInterface
     * @return marketPlaneVO
     */
    public static MarketPlaneVO convertCouponForInterfaceToMarketPlaneVO(CouponForInterface cfi,String planName){
    	 //创建营销计划VO
    	 MarketPlaneVO marketPlaneVO = new MarketPlaneVO();
    	 /**
    	  * 设置营销计划实体
    	  */
    	 //创建营销计划实体 
    	 Marketplan marketPlan = new Marketplan();
    	 //设置营销计划名称
    	 marketPlan.setPlanName(planName);
    	 //设置营销计划为已启用
    	 marketPlan.setMarketStatus(MarketingCouponConstance.MARKETPLAN_USING);
    	 //设置优惠券类型为手动发券
    	 marketPlan.setCouponType(MarketingCouponConstance.COUPON_SENDHAND);
    	 //设置创建人
    	 marketPlan.setCreateUser(MarketingCouponConstance.ADMIN_ID);
    	 //设置创建时间
    	 marketPlan.setCreateDate(new Date());
    	 //设置修改人
    	 marketPlan.setModifyUser(MarketingCouponConstance.ADMIN_ID);
    	 //设置修改时间
    	 marketPlan.setModifyDate(new Date());
    	 //设置修改人姓名
    	 marketPlan.setModifyUserName(MarketingCouponConstance.ADMIN_NAME);
    	 //设置营销计划状态为已启用
    	 marketPlan.setMarketStatus(MarketingCouponConstance.MARKETPLAN_USING);
    	 /**
    	  * 1204版本设置新点优惠券的业务类型为零担
    	  */
    	 marketPlan.setBusType(MarketingCouponConstance.BUSTYPE_LIN);
    	 /**
    	  * crm2期 设置抵扣类型为运费
    	  */
    	 marketPlan.setDiscountType(MarketActivityConstance.PREFER_TYPE_FRT);
    	 //设置营销计划规则
    	 marketPlaneVO.setMarketplan(marketPlan);
    	 
    	 /**
    	  * 设置手动券实体
    	  */
    	 RuleCouponHand couponHand = new RuleCouponHand();
    	 //设置优惠券类型为接口创建
    	 couponHand.setTypeId(MarketingCouponConstance.COUPON_FORINTERFACE);
    	 //设置优惠券金额
    	 couponHand.setCouponValue(cfi.getCouponValue());
    	 //设置创建人
    	 couponHand.setCreateUser(MarketingCouponConstance.ADMIN_ID);
    	 //设置创建时间
    	 couponHand.setCreateDate(new Date());
    	 //设置修改人
    	 couponHand.setModifyUser(MarketingCouponConstance.ADMIN_ID);
    	 //设置修改时间
    	 couponHand.setModifyDate(new Date());
    	 //设置部门标杆编码
    	 couponHand.setDeptStCode(cfi.getDeptStandardCode());
    	 //设置活动类型
    	 couponHand.setActivityType(cfi.getActivityType());
    	 //设置marketPlaneVO的手动券规则
    	 marketPlaneVO.setRuleCouponHand(couponHand);
    	 /**
    	  * 设置优惠券使用规则
    	  */
    	 //调用生产使用规则方法
    	 CouponRule couponRule = convertCouponInterfaceToCouponRule(cfi);
    	 //设置marketPlaneVO的使用规则
    	 marketPlaneVO.setCouponRule(couponRule);
    	 return marketPlaneVO;
    	 
    }
    /**
     * 
     * <p>
     * Description:将接口传来的参数转换为转换为使用规则
     * </p>
     * @author ZhouYuan
     * @version 0.1 2013-9-3
     * @param CouponForInterface
     * @return marketPlaneVO
     */
    public static CouponRule convertCouponInterfaceToCouponRule(CouponForInterface cfi){
    	//新建使用规则实体
    	CouponRule couponRule = new CouponRule();
    	//设置金额抵扣类型  默认为“1” 分级抵扣模式，“2”严格抵扣模式
    	couponRule.setCostMode(cfi.getCostMode());
    	//设置 金额类型      默认为“FRT” 运费，“BILING”开单金额
    	couponRule.setCostType(cfi.getCostType());
    	//设置抵扣金额
    	couponRule.setValue(cfi.getValue());
    	//满减金额 分级抵扣模式使用
    	if( MarketingCouponConstance.COST_MODE_RATED.equals(cfi.getCostMode())){
    		couponRule.setDiscount(cfi.getDiscount());
    	}
    	//如果增值费类型 不为空 且 增值费金额为数字或小数
    	//则认为增值费开关为开
    	if( !StringUtils.isEmpty(cfi.getCostAddedType())
    			&&cfi.getCostAdded()!=null && cfi.getCostAdded().matches("^\\d*.{0,1}\\d*$") ){
    		//设置增值费开关为开
    		couponRule.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_OPEN);
    	}else{
    		//设置增值费开关为关
    		couponRule.setCostAddedMode(MarketingCouponConstance.ADDED_MODE_CLOSE);
    	}
    	//设置增值费类型	包装、保价、代收、送货、接货费
    	couponRule.setCostAddedType(cfi.getCostAddedType());
    	//设置增值费金额
    	couponRule.setCostAdded(cfi.getCostAdded());
    	//设置优惠券生效时间
    	couponRule.setBegintime(cfi.getBeginTime());
    	//设置优惠券失效时间
    	couponRule.setEndtime(cfi.getEndTime());
    	//设置短信模板
    	couponRule.setSmsContent(cfi.getSmsContent());
    	//设置优惠券描述
    	couponRule.setDescribe(cfi.getDescribe());
    	
    	List<ConditionType> conditionTypes = new ArrayList<ConditionType>();
    	/**
    	 * 设置产品类型
		 * 产品类型为多个每个类型用逗号隔开,
		 * 将该字符串截取出来转换为conditionType
		 */
    	List<ConditionType> productTypes =covertConditionToConditionList(cfi.getProductType(),
    			MarketingCouponConstance.BIND_CODE_PRODUCT_NAME,
    			MarketingCouponConstance.BIND_TYPE_PRODUCT,
    			CouponExceptionType.couponForInterfaceProductType);
    	//如果产品类型集合不为空则添加到conditionTypes中
    	if( productTypes != null && productTypes.size() > 0){
    		conditionTypes.addAll(productTypes);
    	}
    	/**
    	 * 设置订单来源 
		 * 订单来源 为多个每个类型用逗号隔开,
		 * 将该字符串截取出来转换为conditionType
		 */
    	List<ConditionType> orderTypes=covertConditionToConditionList(cfi.getOrderType(),
    			MarketingCouponConstance.BIND_CODE_ORDER_NAME,
    			MarketingCouponConstance.BIND_TYPE_ORDER,
    			CouponExceptionType.couponForInterfaceOrderType);
    	//如果订单来源集合不为空则添加到conditionTypes中
    	if( orderTypes != null && orderTypes.size() > 0){
    		conditionTypes.addAll(orderTypes);
    	}
  		/**
  		 * 设置 客户等级
		 * 客户等级为多个每个类型用逗号隔开,
		 * 将该字符串截取出来转换为conditionType
		 */
    	List<ConditionType> custLevels=covertConditionToConditionList(cfi.getCustLevel(),
    			MarketingCouponConstance.BIND_CODE_LEVEL_NAME,
    			MarketingCouponConstance.BIND_TYPE_LEVEL,
    			CouponExceptionType.couponForInterfaceCustLevel);
    	//如果客户等级集合不为空则添加到conditionTypes中
    	if( custLevels != null && custLevels.size() > 0){
    		conditionTypes.addAll(custLevels);
    	}
  		/**
  		 * 设置 客户行业
		 * 客户行业为多个每个类型用逗号隔开,
		 * 将该字符串截取出来转换为conditionType
		 */
    	List<ConditionType> custTrades=covertConditionToConditionList(cfi.getCustTrade(),
    			MarketingCouponConstance.BIND_CODE_TRADE_NAME,
    			MarketingCouponConstance.BIND_TYPE_TRADE,
    			CouponExceptionType.couponForInterfaceCustTrade);
    	//如果客户等级集合不为空则添加到conditionTypes中
    	if( custTrades != null && custTrades.size() > 0){
    		conditionTypes.addAll(custTrades);
    	}

      	//将conditionType添加到使用规则实体
      	couponRule.setConditionTypes(conditionTypes);
      	//如果线路要求不为空且线路要求为1,2,3,4		
      	if( cfi.getRegdemand()!=null){   
      		//新建走货线路实体
      		List<GoodsLine> goodsLines = null;
      		//获得走货线路
      		String regdemand = cfi.getRegdemand();
      		if( regdemand.matches("^[1234]$")){
      			goodsLines = new ArrayList<GoodsLine>();      		
	      		//循环走货线路集合
	      		for( GoodsLine g :cfi.getGoodsLines() ){
	      			//如果走货线路不为空
	      			if(g != null ){
	      				//新建走货线路
	      				GoodsLine goodsLin = new GoodsLine();
	      				//设置线路要求
	      				goodsLin.setRegdemand(regdemand);
	      				//设置出发部门标杆编码
	      				goodsLin.setBeginDeptOrCityId(g.getBeginDeptOrCityId());
	      				//设置出发部门名称
	      				goodsLin.setBeginDeptOrCityName(g.getBeginDeptOrCityName());
	      				//设置到达部门标杆编码
	      				goodsLin.setEndDeptOrCityId(g.getEndDeptOrCityId());
	      				//设置到达部门名称
	      				goodsLin.setEndDeptOrCityName(g.getEndDeptOrCityName());
	      				//将走货线路添加到走货线路集合
	      				goodsLines.add(goodsLin);
	      			}
	      		}
			}else{
				ExceptionUtils.createCouponException(CouponExceptionType.couponForInterfaceRegdemand);
			}
      		//设置使用规则走货线路
      		couponRule.setGoodsLines(goodsLines);
      	}
      	//返回走货线路
      	return couponRule;
    }
    /**
     * 
     * <p>
     * Description:转换客户行业，客户等级，订单来源，产品类型字段
     * </p>
     * @author ZhouYuan
     * @version 0.1 2013-9-3
     * @param conditions,conditions,type,exception
     * @return List<ConditionType>
     */
    public static List<ConditionType> covertConditionToConditionList( String conditions,Map<String,String> map,String type,CouponExceptionType exception ){
    	List<ConditionType> conditionTypes = null;
    	if( conditions != null ){
    		String[] conditionStr = conditions.split(",");
	    	conditionTypes = new ArrayList<ConditionType>();
			//循环截取的字符串
			for( String condition : conditionStr ){
				//如果名称不为空则代表该字段合法
				if( map.get(condition)!=null){
					//新建conditionType实体
					ConditionType c = new ConditionType();
					//设置类型
					c.setType(type);
					//设置codition值
					c.setValue(condition);
					//将其添加到产品类型集合
					conditionTypes.add(c);
				}else{
					ExceptionUtils.createCouponException(exception);
				}
			}
    	}
    	return conditionTypes;
    }
     /**
     * 
     * <p>
     * Description:计算优惠券cost
     * </p>
     * @author ZhouYuan
     * @version 0.1 2013-9-3
     * @param rule,coupon
     * @return cost
     */
    public static String calculateCouponCost(Coupon coupon, CouponRule rule,
    		Map<String, BigDecimal> aiMap){
    	String cost = null;
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
				}
			}
			// 比较开单金额
			if (MarketingCouponConstance.COST_TYPE_BILLING.equals(costType)){
				// 比较指定运单金额与规则金额
				if (aiMap.get(MarketingCouponConstance.COST_TYPE_BILLING)
						.compareTo(NumberUtils.createBigDecimal(value)) > -1){
					// 满足规则，抵扣cost元
					cost = amout;
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
				cost = CouponValidator.relativeAmout(wbAmout,
						NumberUtils.createBigDecimal(value),
						NumberUtils.createBigDecimal(discount),
						NumberUtils.createBigDecimal(amout));
			}
			
			// 比较开单金额
			if (MarketingCouponConstance.COST_TYPE_BILLING.equals(costType)){
				// 比较指定运单金额与规则金额
				BigDecimal wbAmout = aiMap.get(MarketingCouponConstance.COST_TYPE_BILLING);
				
				cost = CouponValidator.relativeAmout(wbAmout,
						NumberUtils.createBigDecimal(value),
						NumberUtils.createBigDecimal(discount),
						NumberUtils.createBigDecimal(amout));
			}
	    }
		return cost==null?"0":cost;
    }
    /**
     * 
     * <p>
     * Description:判断用户是否包含快递或零担权限
     * </p>
     * @author ZhouYuan
     * @version 0.1 2013-9-3
     * @param user
     * @return String
     */
    public static String userBusinessType(User user){
    	String busType = null;
    	//如果用户权限包含零担权限
    	if(user.getAccessUris().contains(MarketingCouponConstance.LOGISTICSPRODUCTFUNCTION)){
    		busType = MarketingCouponConstance.BUSTYPE_LIN;
    	//如果用户权限包含快递权限
    	}else if(user.getAccessUris().contains(MarketingCouponConstance.EXPRESSPRODUCTFUNCTION)){
    		busType = MarketingCouponConstance.BUSTYPE_EXP;
    	}
    	return busType;
    }
}
