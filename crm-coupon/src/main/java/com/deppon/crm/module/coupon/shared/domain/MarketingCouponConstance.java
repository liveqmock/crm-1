package com.deppon.crm.module.coupon.shared.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MarketingCouponConstance {
	/**
	 * 优惠券状态 ： 未发送、已发送、已使用、已过期
	 */
	//未发送
	public static final String COUPON_NOSEND="10";
	
	//已发送
	public static final String COUPON_SENDING="20";
	
	//已使用
	public static final String COUPON_USING="30";
	
	//已过期
	public static final String COUPON_OVERDUE="40";
	
	/**
	 * 优惠券发券规则 ： 手动发券、自动发券
	 */
	//手动发券
	public static final String COUPON_SENDHAND="HANDCOUPON";
	//自动发券
	public static final String COUPON_SENDAUTO="AUTOCOUPON";
	/**
	 * 优惠券手动发券规则 ： 
	 */
	//手动发券 超过1W条数据 标志
	public static final String COUPON_LARGERHAND="FORSCHEDUAL";
	//手动发券 未生成优惠券
	public static final String COUPON_NOCREATE="NOCREATE";
	//手动发券 生成完毕
	public static final String COUPON_CREATED="CREATED";
	//手动发券 接口生成优惠券
	public static final String COUPON_FORINTERFACE="FORINTERFACE";
	// 手动发券最大 数量
	public static final int COUPON_MAXHAND = 100000;
	// 手动发券立即生成时的 数量
	public static final int COUPON_NOWHAND = 10000;
	/**
	 * 营销计划状态 ： 未启用、已启用、已终止、已结束
	 */
	// 未启用
	public static final String MARKETPLAN_NOUSE = "10";
	// 已启用
	public static final String MARKETPLAN_USING = "20";
	// 已终止
	public static final String MARKETPLAN_STOP = "30";
	// 已结束
	public static final String MARKETPLAN_FINISH = "40";
	/**
	 * 约束条件信息所属类型(产品类型、客户登记、订单来源、客户行业): 发券规则  or 优惠劵使用规则
	 */
	public static final String CONDITIONTYPE_COUPONSEND = "10";
	public static final String CONDITIONTYPE_COUPONUSE 	= "20";
	/**
	 * 抵扣方式
	 */
	// 分级抵扣
	public static final String COST_MODE_RATED			= "2";
	// 严格抵扣
	public static final String COST_MODE_STRICT			= "1";
	/**
	 * 增值费要求开关
	 */
	public static final String ADDED_MODE_OPEN			= "1";
	public static final String ADDED_MODE_CLOSE			= "0";
	
	/**
	 * 费用类型
	 */
	// 运费
	public static final String COST_TYPE_TRANSPORTFEE 		= "FRT";
	// 开单金额
	public static final String COST_TYPE_BILLING 			= "BILLING";
	// 包装费
	public static final String COST_TYPE_PACKAGINGFEE 		= "BZ";
	// 保价费
	public static final String COST_TYPE_INSURANCEFEE 		= "BF";
	// 代收费
	public static final String COST_TYPE_CODFEE				= "HK";
	// 送货费
	public static final String COST_TYPE_DELIVERYGOODSFEE 	= "SH";
	// 接货费
	public static final String COST_TYPE_PICKUPFEE			= "JH";
	// 运费
	public static final String COST_TYPE_TRANSPORTFEE_NAME 	= "运费";
	// 开单金额
	public static final String COST_TYPE_BILLING_NAME 		= "开单金额";
	// 包装费
	public static final String COST_TYPE_PACKAGINGFEE_NAME 	= "包装费";
	// 保价费
	public static final String COST_TYPE_INSURANCEFEE_NAME 	= "保价费";
	// 代收费
	public static final String COST_TYPE_CODFEE_NAME		= "代收费";
	// 送货费
	public static final String COST_TYPE_DELIVERYGOODSFEE_NAME 	= "送货费";
	// 接货费
	public static final String COST_TYPE_PICKUPFEE_NAME		= "接货费";

	/**
	 * 约束条件类型	（1、2、3、4）产品类型、客户等级、订单来源、客户行业
	 */
	// 产品类型
	public static final String BIND_TYPE_PRODUCT 	= "1";
	// 客户等级
	public static final String BIND_TYPE_LEVEL 		= "2";
	// 订单来源
	public static final String BIND_TYPE_ORDER 		= "3";
	// 客户行业
	public static final String BIND_TYPE_TRADE 		= "4";
	
	// 产品类型-Code
	public static final String BIND_CODE_PRODUCT 	= "PRODUCT_CODE";
	// 客户等级-Code
	public static final String BIND_CODE_LEVEL 		= "LEVEL_CODE";
	// 订单来源-Code
	public static final String BIND_CODE_ORDER 		= "ORDER_CODE";
	// 客户行业-Code
	public static final String BIND_CODE_TRADE 		= "TRADE_CODE";
	// 产品类型-中文名称
	@SuppressWarnings("serial")
	public static final Map<String, String> BIND_CODE_PRODUCT_NAME = new  HashMap<String,String>() {
		{
	         put( "FLF" ,  "精准卡航" );
	         put( "FSF" ,  "精准城运" );
	         put( "LRF" ,  "精准汽运(长途)" );
	         put( "SRF" ,  "精准汽运(短途)" );
	         put( "PLF" ,  "汽运偏线" );
	         put( "FV" ,  "整车" );
	         put( "AF" ,  "精准空运" );
	         put( "PACKAGE", "经济快递");
	     }
			 };
	// 客户等级-中文名称
	@SuppressWarnings("serial")
	public static final Map<String, String> BIND_CODE_LEVEL_NAME = new  HashMap<String,String>() {
		{
	         put( "NORMAL" ,  "普通客户" );
	         put( "GOLD" ,  "黄金客户" );
	         put( "PLATINUM" ,  "铂金客户" );
	         put( "DIAMOND" ,  "钻石客户" );
	     }
	 };
	// 订单来源-中文名称
	@SuppressWarnings("serial")
	public static final Map<String, String> BIND_CODE_ORDER_NAME = new  HashMap<String,String>() {
		{
	         put( "ONLINE" ,  "网上营业厅" );
	         put( "TAOBAO" ,  "淘宝网" );
	         put( "ALIBABA" ,  "阿里巴巴网" );
	         put( "YOUSHANG" ,  "金蝶友商网" );
	         put( "SHANGCHENG" ,  "淘宝商城" );
	         put( "CALLCENTER" ,  "呼叫中心" );
	         put( "BUSINESS_DEPT" ,  "营业部" );
	         put( "ALL" ,  "全部" );
	     }
	 };
	// 客户行业-中文名称
	@SuppressWarnings("serial")
	public static final Map<String, String> BIND_CODE_TRADE_NAME = new  HashMap<String,String>() {
		{
	         put( "CLOTH_SPIN" ,  "服装纺织类" );
	         put( "ITRONIC_FURNITURE" ,  "工业电子类" );
	         put( "LIFE_ELECTRIC" ,  "生活电器类" );
	         put( "LIFEELECTRONIC" ,  "生活电子类" );
	         put( "DAILYUSE_COSMETIC" ,  "生活类化妆品类" );
	         put( "FOOD_DRUG" ,  "食品药品类" );
	         put( "PLASTIC_BUILD" ,  "塑料建材类" );
	         put( "IRCONWARE_INSTRUMENT" ,  "五金仪表类" );
	         put( "FRAGILE" ,  "易损易碎类" );
	         put( "OTHER" ,  "其它类" );
	         put( "FURNITURE" ,  "家具类" );
	     }
	 };
	/**
	 * 线路区域要求 	(1、2、3、4)空、走货线路、发货区域、到达区域
	 */
	// 空、
	public static final String GOOD_LINE_NULL 		= "1";
	// 走货线路、
	public static final String GOOD_LINE_AREA		= "2";
	// 发货区域、
	public static final String GOOD_LINE_LEAVE 		= "3";
	// 到达区域
	public static final String GOOD_LINE_ARRIVED 	= "4";

	/**
	 * CouponForFossManagerImpl 返回值类型
	 */

	public static final String PROCESSFAILED = "操作失败！";

	public static final int COUPONNUMS = 900;

	public static final String COUPONNUMSISWRONG = "参数错误";
	public static final String COUPONNUMSISNULL = "优惠券编码为空";
	public static final String COUPONNUMSISNOTEXSIT = "使用规则不存在";
	public static final String PROCESSSUCCESS = "操作成功！";
	/**
	 * 短信发券手机号码状态 (0,1,2)未发送 发送失败 发送成功
	 */
	//未发送
	public static final String PHONE_SEND_STATUS_UNSEND = "0";
	//发送失败
	public static final String PHONE_SEND_STATUS_SENDUNSCE = "1";
	//发送成功
	public static final String PHONE_SEND_STATUS_SENDSCE = "2";
	/**
	 * 短信发券手机号码有效状态(0,1)无效 有效
	 */
	//无效
	public static final String PHONE_VALIDATE_INVALID = "0";
	//有效
	public static final String PHONE_VALIDATE_VALIDITY = "1";
	/**
	 * CouponCellphoneMsgInfo 中sended 的发送状态(0,1,2)未发送 发送失败 发送成功
	 */
	//未发送
	public static final String COUPON_MSG_SENDED_UNSEND = "0";
	//发送失败
	public static final String COUPON_MSG_SENDED_FAIL = "1";
	//发送成功
	public static final String COUPON_MSG_SENDED_SUCCESS = "2";
	/**
	 * 短信发送部门编码
	 */
	public static final String COUPON_MSG_SEND_DEPT = "DP08068";
	/**
	 * 业务编码  YWLX20121105095210
	 */
	public static final String COUPON_MSG_MSGTYPE = "YWLX20121105095210";
	//优惠券生成Excel最大数量
	public static final int MAX_COUPON_EXCEL_NUM = 20000;
	//生成优惠券报表标题
	public static final Map<String,String> couponTitle;
	//优惠券状态对照表
	public static final Map<String,String> couponStatusStr;
	static{
		couponTitle = new LinkedHashMap<String,String>();
		//营销计划名称
		couponTitle.put("营销计划名称", "planName");
		//优惠券编码
		couponTitle.put("优惠券编码", "couponNumber");
		//优惠券金额
		couponTitle.put("优惠券金额", "useCouponValue");
		//优惠券状态
		couponTitle.put("优惠券状态", "status");
		//生效日期
		couponTitle.put("生效日期", "begintime");
		//失效日期
		couponTitle.put("失效日期", "endtime");
		//金额要求
		couponTitle.put("金额要求", "value");
		//来源运单号
		couponTitle.put("来源运单号", "sourceWBNumber");
		//来源运单金额
		couponTitle.put("来源运单金额", "sourceWBValue");
		//使用运单号
		couponTitle.put("使用运单号", "useWBNumber");
		//使用运单金额
		couponTitle.put("使用运单金额", "useWBValue");
		//发送手机号码
		couponTitle.put("发送手机号码", "sendtelPhone");
		//发送时间
		couponTitle.put("发送时间", "sendTime");
		//使用手机号码
		couponTitle.put("使用手机号码", "usetelPhone");
		//使用时间
		couponTitle.put("使用时间", "useTime");
		//优惠券归属部门
		couponTitle.put("优惠券归属部门", "underDeptName");	
		
		
		couponStatusStr = new LinkedHashMap<String,String>();
		//未发送
		couponStatusStr.put(COUPON_NOSEND, "未发送");
		//已发送
		couponStatusStr.put(COUPON_SENDING, "已发送");
		//已使用
		couponStatusStr.put(COUPON_USING, "已使用");
		//已过期
		couponStatusStr.put(COUPON_OVERDUE, "已过期");
		
		
	}
	//短信发券日期转换格式
	public static final String COUPON_MSG_DATE_FORMAT = "yy年MM月dd日";
	/**
	 * 向短信平台每次发送短信条数
	 */
	public static final int SEND_COUPON_LIMITNUMBER = 500;
	//每次从短信发送表取出的数据条数
	public static final int SEND_COUPON_FETCHFROMMSG = 2000;
	/**
	 * 短信发券时发送短信数目限制最大值
	 */
	public static final int MAX_SEND_COUPON_NUMBER = 1000;
	/**
	 * 运单中间表使用状态(0,1)0未使用 1已使用
	 */
	//未使用
	public static final String WAYBILL_COUPON_STATUS_UNUSE = "0";
	//已使用
	public static final String WAYBILL_COUPON_STATUS_USED = "1";
	/**
	 * 运单中间表发送短信定时任务每次最大查询数量限制
	 */
	public static final int WAYBILL_COUPON_MAXSEARCHNUM = 1000;
	//自动发券的默认发券柜员
	public static final String AUTO_SEND_MSG_EMPCODE = "111111";
	//自动发券的默认发券部门
	public static final String AUTO_SEND_MSG_DEPTCODE = "DP08068";
	// 优惠券查询-部门-事业部
	public static final String SHIYEBU_NAME = "%事业部";
	// 优惠券查询-部门-事业部-查询
	public static final String SHIYEBU_NAME_QUERY = "^[\\u4e00-\\u9fa5]+事业部$"; ;
	// 优惠券查询-营销活动管理小组 部门标杆编码
	public static final String MARKETING_DEPT_CODE = "DP07139";
	// 优惠券查询-事业部标志
	public static final String MANAGER_DEPT_FLAG = "managerDept";
	/**
	 * 优惠券快递与零担查询优惠券function
	 */
	//零担查询function
	public static final String LOGISTICSPRODUCTFUNCTION = "/coupon/selectLogisticsProductType.action";
	//快递查询function
	public static final String EXPRESSPRODUCTFUNCTION = "/coupon/selectExpressProductType.action";
	//手动券查询function
	public static final String HANDCOUPONFUNCTION= "/coupon/searchHandCoupon.action";
		/**
	 * 接口创建营销计划默认创建人为系统管理员
	 */
	public static String ADMIN_ID = "86301";
	public static String ADMIN_NAME = "系统管理员";
	/**
	 * 业务类型（区别快递和零担业务）
	 */
	//快递
	public static String BUSTYPE_EXP="EXPRESS";
	//零担
	public static String BUSTYPE_LIN="LINGDAN";
	/**
	 * 优惠券来源 手动 自动 新点 
	 */
	//手动创建
	public static String COUPON_SOURCE_HAND="HANDCOUPON";
	//自动创建
	public static String COUPON_SOURCE_AUTO="AUTOCOUPON";
	//新点优惠券
	public static String COUPON_SOURCE_NEWOPEN="NEWOPEN";
}
