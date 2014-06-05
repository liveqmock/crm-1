package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketActivityConstance {
	/**
	 * 关联类型
	 */
	//客户群
	public static final String ACTIVITY_RELATION_CLIENT = "CUSTOMER_BASE";
	//市场推广活动
	public static final String ACTIVITY_RELATION_ACTIVITY = "ACTIVITY";	
	/**
	 * 客户群多选类型
	 */
	//一级行业
	public static final String TYPE_TRADE="TRADE";
	//二级级行业
	public static final String TYPE_SECOND_TRADE="SECOND_TRADE";
	//产品类型
	public static final String TYPE_PRODUCT_TYPE="PRODUCT_TYPE";
	//客户类型
	public static final String TYPE_CUST_TYPE="CUST_TYPE";
	//客户等级
	public static final String TYPE_CUST_GRADE="CUST_GRADE";
	//货量潜力
	public static final String TYPE_CUST_POTENTIAL="CUST_POTENTIAL";
	//客户来源
	public static final String TYPE_CUST_SOURCE="CUST_SOURCE";	
	//订单来源
	public static final String  TYPE_ORDER_SOURCE="ORDER_SOURCE";
	//运输方式
	public static final String  TYPE_ORDER_TRANSPORT="TRANSPORT";
	//客户属性
	public static final String TYPE_CUST_CATEGORY="CUST_CATEGORY";
	//提货方式	
	public static final String TYPE_DELIVERY_MODE="DELIVERY_MODE";
	//合作意向
	public static final String TYPE_COOPIN_TENTION="COOPIN_TENTION";
	
	/**
	 * 客户群状态
	 */
	
	//未使用
	public static final String CLIENTBASE_STATUS_UNUSED="UNUSED";
	//使用中
	public static final String CLIENTBASE_STATUS_USEING="USEING";
	//下发中
	public static final String CLIENTBASE_STATUS_ISSUED_IN="ISSUED_IN";
	//下发完成
	public static final String CLIENTBASE_STATUS_ISSUED_COMPLETE="ISSUED_COMPLETE";
	//已作废
	public static final String CLIENTBASE_STATUS_CANCELLATION="CANCELLATION";
	/**
	 * 业务类别
	 */
	//零担
	public static final String ACTIVITY_CATEGORY_LTT = "LTT";
	//快递
	public static final String ACTIVITY_CATEGORY_EXPRESS = "EXPRESS";
	/**
	 * 业务类型
	 */
	//全国市场推广活动
	public static final String ACTIVITY_TYPE_NATION = "NATIONWIDE";
	//区域市场推广活动
	public static final String ACTIVITY_TYPE_REGION = "REGION";
	/**
	 * 营销活动关联组部门ID,快递市场营销组部门ID
	 */
	//营销活动管理组权限
	public static final String AUTH_LTT_SEARCHALL = "/marketing/createLttActivity.action";
	//快递市场营销组权限
	public static final String AUTH_EXPRESS_SEARCHALL = "/marketing/createExpressActivity.action";
//	//营销活动关联组部门ID
//	public static final String AUTH_DEPT_LTT = "106782";
//	//快递市场营销组部门ID
//	public static final String AUTH_DEPT_EXPRESS = "465665";
	/**
	 * 市场推广活动开单品名分割符
	 */
	public static final String ITEMNAME_DIVISION = "、";
	/**
	 * 部门树中办公门户机构,总裁默认不可选中
	 */
	public static final List<String> CANNOT_SELECT_LIST = new ArrayList<String>();
	/**
	 * 不能选中标识
	 */
	public static final boolean CAN_NOT_SELECT = false;
	/**
	 * 部门性质
	 */
	//大区
	public static final String DEPT_CHARACTER_BIGREGION = "BIGREGION";
	//事业部
	public static final String DEPT_CHARACTER_DIVISION = "DIVISION";
	//营销活动管理组
	public static final String DEPT_CHARACTER_LTTGROUP = "LTTGROUP";
	//快递市场营销组
	public static final String DEPT_CHARACTER_EXPGROUP = "EXPGROUP";
	/**
	 * 物品类型(申请物品信息,优惠券,折扣)
	 */
	//申请物品
	public static final String OPTION_APPLYGOODS = "APPLYGOODS";
	//优惠券
	public static final String OPTION_COUPON = "COUPON";
	//折扣
	public static final String OPTION_DISCOUNT = "DISCOUNT";
	/**
	 * 附件表中的sourceType
	 */
	public static final String SOURCE_TYPE_ACTIVITY = "ACTIVITIES";
	/**
	 * 抵扣类型/优惠类型
	 */
	//运费
	public static final String PREFER_TYPE_FRT = "FRT";
	//包装费
	public static final String PREFER_TYPE_BZ = "BZ";
	//保价费
	public static final String PREFER_TYPE_BF = "BF";
	//代收费
	public static final String PREFER_TYPE_HK = "HK";
	//送货费
	public static final String PREFER_TYPE_SH = "SH";
	//接货费
	public static final String PREFER_TYPE_JH = "JH";
	//综合信息费
	public static final String PREFER_TYPE_ZHXX = "ZHXX";
	/**
	 * 抵扣类型List
	 */
	public static final List<String> discountType = new ArrayList<String>();
	/**
	 * 活动编码
	 */
	//零担市场推广活动
	public static final String ACTIVITY_CODE_LTT = "L";
	//快递市场推广活动
	public static final String ACTIVITY_CODE_EXPRESS = "E";
	//全国市场推广活动
	public static final String ACTIVITY_CODE_NATION = "C";
	//区域市场推广活动
	public static final String ACTIVITY_CODE_REGION = "R";
	/**
	 * 市场推广活动状态
	 */
	//已制定
	public static final String ACTIVITY_STATUS_ESTABLISHED = "ESTABLISHED";
	//审批中
	public static final String ACTIVITY_STATUS_PROCESSES = "PROCESSING";
	//审批通过
	public static final String ACTIVITY_STATUS_PROCESSED = "PROCESSED";
	//审批失败
	public static final String ACTIVITY_STATUS_DISAGREE = "DISAGREE";
	//折扣已生效
	public static final String ACTIVITY_STATUS_EXECUTED = "EXECUTED";
	//下发完成
	public static final String ACTIVITY_STATUS_PLANCREATED = "PLANCREATED";
	//已终止
	public static final String ACTIVITY_STATUS_TERMINATED = "TERMINATED";
	/**
	 * 折扣工作流状态
	 */
	//工作流不存在
	public static final String WORKFLOW_STATUS_NONE="10";
	//工作流重复
	public static final String WORKFLOW_STATUS_REPEAT="9";
	//工作流已审批
	public static final String WORKFLOW_STATUS_PASS="4";
	/**
	 * 区域线路类型
	 * 
	 */
	//出发区域
	public static final String LINE_TYPE_LEAVE="LEAVE";
	//到达区域
	public static final String LINE_TYPE_ARRIVE="ARRIVE";
	//走货线路
	public static final String LINE_TYPE_LEAVE_ARRIVE="LEAVE_ARRIVE";
	/**
	 * 系统更新客户群及市场推广活动填写更新人ID
	 */
	//系统管理员ID
	public static final String SYS_ADMIN_ID = "86301";
	/**
	 * 查询未保存完整或已过期的市场推广活动
	 */
	//未保存完整
	public static final String SEARCH_USELESS_ACTIVITY = "useless";
	//已过期
	public static final String SEARCH_COMPLETE_ACTIVITY = "complete";
	/**
	 * 部门树 最高层级
	 */
	public static final int MAX_TREE_LEVEL = 7;
	/**
	 * 市场推广活动异常信息
	 */
	//未生产工作流
	public static final String ACTIVITY_EXCEPTION_NOWORKFLOW = "WORKFLOW";
	//折扣信息未推送至FOSS
	public static final String ACTIVITY_EXCEPTION_NODISCOUNT = "DISCOUNT";
	/**
	 * 异常信息是否已处理
	 */
	//未处理
	public static final String ACTIVITY_EXCEPTION_UNHANDLE = "0";
	//已处理
	public static final String ACTIVITY_EXCEPTION_HANDLED = "1";
	/**
	 * 市场推广活动推送FOSS标识该市场推广活动是过期还是有效(N表示过期  Y表示有效)
	 */
	//过期
	public static final String FOSS_ACTIVITY_STATUS_N = "N";
	//有效
	public static final String FOSS_ACTIVITY_STATUS_Y = "Y";
	/**
	 * 前台grid是否修改
	 */
	public static final String CLIENTBASE_ISMODIFY="1";
	/**
	 * 开单金额、货物重量、货物体积校验正则表达式，最大8位整数3位小数
	 */
	public static final String NUMBER_REGEX = "^\\d{1,8}(\\.\\d{1,3})?$";
	/**
	 * 抵扣类型正则表达式
	 */
	public static final String DISCOUNT_REGEX = "^(100)|(\\d\\d?(\\.\\d\\d?)?)$";
	/**
	 * 抵扣类型列表
	 */
	public static final List<String> ListPreferType = new ArrayList<String>();
	/**
	 * 可以生成区域价格折扣工作流的市场推广活动状态
	 */
	public static final List<String> REGIONDISSTATUS = new ArrayList<String>();
	/**
	 * 可以生成全国价格折扣工作流的市场推广活动状态
	 */
	public static final List<String> NATIONDISSTATUS = new ArrayList<String>();
	
	
	//零担查询function
	public static final String LOGISTICSPRODUCTFUNCTION = "/coupon/selectLogisticsProductType.action";
	//快递查询function
	public static final String EXPRESSPRODUCTFUNCTION = "/coupon/selectExpressProductType.action";
//	/**
//	 * 营销活动管理小组部门列表
//	 */
//	public static final List<String> LTTGROUPLIST = new ArrayList<String>();
//	/**
//	 * 快递市场营销组列表
//	 */
//	public static final List<String> EXPRESSGROUPLIST = new ArrayList<String>();
	static{
		ListPreferType.add(PREFER_TYPE_FRT);
		ListPreferType.add(PREFER_TYPE_BZ);
		ListPreferType.add(PREFER_TYPE_BF);
		ListPreferType.add(PREFER_TYPE_HK);
		ListPreferType.add(PREFER_TYPE_SH);
		ListPreferType.add(PREFER_TYPE_JH);
		ListPreferType.add(PREFER_TYPE_ZHXX);		
		REGIONDISSTATUS.add(ACTIVITY_STATUS_PROCESSED);
		REGIONDISSTATUS.add(ACTIVITY_STATUS_PLANCREATED);
		NATIONDISSTATUS.add(ACTIVITY_STATUS_ESTABLISHED);
		NATIONDISSTATUS.add(ACTIVITY_STATUS_PLANCREATED);
		discountType.add(PREFER_TYPE_FRT);
		discountType.add(PREFER_TYPE_BZ);
		discountType.add(PREFER_TYPE_BF);
		discountType.add(PREFER_TYPE_HK);
		discountType.add(PREFER_TYPE_SH);
		discountType.add(PREFER_TYPE_JH);
		discountType.add(PREFER_TYPE_ZHXX);
//		LTTGROUPLIST.add(AUTH_DEPT_LTT);
//		EXPRESSGROUPLIST.add(AUTH_DEPT_EXPRESS);
		CANNOT_SELECT_LIST.add("103");
		CANNOT_SELECT_LIST.add("104");
	}
	public static int TREE_NODE_READY = 1;
		
}
