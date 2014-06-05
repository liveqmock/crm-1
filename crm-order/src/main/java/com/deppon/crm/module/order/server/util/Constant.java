package com.deppon.crm.module.order.server.util;

/**
 * @description 常量类，用于定义order里所有常量
 * @author 潘光均
 * @version 0.1 2012-3-14
 * @date 2012-3-14
 */

public class Constant {
	// 潜客来源——订单潜客
	public static final String ORDER_CUSTOMER = "ORDER_CUSTOMER";
	// 订单全部状态
	public static final int SEARCH_MAX_DAY_RANGE = 30;

	// 订单全部状态
	public static final String ORDER_STATUS_ALL = "ALL";
	// 已延迟
	public static final String ORDER_STATUS_DELAY = "DELAY";
	// 待分配
	public static final String ORDER_STATUS_WAIT_ALLOT = "WAIT_ALLOT";
	// 待受理
	public static final String ORDER_STATUS_WAIT_ACCEPT = "WAIT_ACCEPT";
	// 已受理
	public static final String ORDER_STATUS_ACCEPT = "ACCEPT";
	// 已拒绝
	public static final String ORDER_STATUS_REJECT = "REJECT";
	// 已撤销
	public static final String ORDER_STATUS_CANCEL = "CANCEL";
	// 已开单
	public static final String ORDER_STATUS_GOT = "GOT";
	// 运输中
	public static final String ORDER_STATUS_IN_TRANSIT = "IN_TRANSIT";
	// 签收成功
	public static final String ORDER_STATUS_SINGSUCCESS = "SIGNSUCCESS";
	// 签收异常
	public static final String ORDER_STATUS_SIGNFAIL = "SIGNFAIL";
	// 已约车
	public static final String ORDER_SATUTS_SHOUTCAR = "SHOUTCAR";
	// 已退回
	public static final String ORDER_SATUTS_GOBACK = "GOBACK";
	// 揽货失败
	public static final String ORDER_SATUTS_FAILGOT = "FAILGOT";
	// 接货中
	public static final String ORDER_SATUTS_RECEIPTING = "RECEIPTING";

	// 渠道类型,自身渠道
	public static final String ORDER_CHANNEL_TYPE_SELF = "SELF";

	// 订单分配的操作记录
	public static final String ORDER_OPERATE_ASSIGN_LOG = "订单状态修改为{0},始发网点为{1},受理部门为{2}.";
	// 订单拒绝的操作记录
	public static final String ORDER_OPERATE_REFUSE_LOG = "订单状态修改为{0}.";
	// 订单反馈
	public static final String ORDER_OPERATE_FEEDBACKINFO_LOG = "反馈内容为：{0}.";
	// 订单状态
	public static final String ORDER_STATUS = "订单状态";
	// 延迟时间
	public static final String ORDER_DELAYTIME = "延迟时间";
	// 订单始发网点
	public static final String ORDER_STARTSTATION = "订单始发网点";
	// 订单操作记录通用
	public static final String ORDER_OPERATE_LOG = "{0}从{1}修改为{2}.";
	// 省
	public static final String PROVINCE = "省";
	// 市
	public static final String CITY = "市/区";
	// 县
	public static final String AREA = "县";
	// 已签收
	public static final String WAYBILL_STATUS_ASSIGNED = "0";// "已签收";

	/**
	 * 订单操作
	 */
	public static final String ORDER_OPERATION_UPDATE = "MODIFY";
	// 订单打回
	public static final String ORDER_OPERATION_RETURNBACK = "RETURN_BACK";
	// 撤销
	public static final String ORDER_OPERATION_CANCEL = "CANCEL";
	// 催单
	public static final String ORDER_OPERATION_URGE = "URGE";
	// 揽货失败
	public static final String ORDER_OPERATION_RECIVEGOODSFAIL = "FAIL_GOT";
	// 分单
	public static final String ORDER_OPERATION_ASSIGN = "DISPATURE";
	// 拒绝
	public static final String ORDER_OPERATION_REFUSE = "REFUSE";
	// 受理
	public static final String ORDER_OPERATION_PROCESS = "ACCEPT";
	// 约车
	public static final String ORDER_OPERATION_BOOKVEHICLE = "BOOK_VEHICLE";
	// 备注
	public static final String ORDER_OPERATION_REMARK = "REMARK";
	// 延迟
	public static final String ORDER_OPERATION_DELAY = "DELAY";

	/**
	 * 短信模板
	 */
	// 订单出发提醒信息
	public static final String ORDER_MESSAGE_CREATE = "{0}，您好！您发往{1}的订单号为{2}，货物跟踪查询请拨打400830555，德邦客户中心竭诚为您服务！";
	// 订单受理提醒信息
	public static final String ORDER_MESSAGE_ACCEPT = "您好！您发往{0},收货人为：{1} 的网上订单订单号{2} 已受理";
	// 渠道订单受理提醒信息
	public static final String ORDER_MESSAGE_ACCEPT_CHANNEL = "您好！您发往{0}{1},收货人为：{2}的网上订单渠道订单号{3}已受理,详情请联系{4},{5}。";
	// 催单
	public static final String ORDER_MESSAGE_URGE = "催单";
	// 拒绝
	public static final String ORDER_OPERATION_REFUSE_CHN = "拒绝";
	// 受理
	public static final String ORDER_OPERATION_PROCESS_CHN = "受理";
	// 已拒绝
	public static final String ORDER_STATUS_REJECT_CHN = "已拒绝";
	// 已受理
	public static final String ORDER_STATUS_ACCEPT_CHN = "已受理";
	// 已约车
	public static final String ORDER_STATUS_BOOKVECHCEL_CHN = "已约车";
	// 已延迟
	public static final String ORDER_STATUS_DELAY_CHN = "已延迟";
	// 全部
	public static final String ORDER_STATUS_ALL_CHN = "全部";
	// 已撤销
	public static final String ORDER_STATUS_CANCEL_CHN = "已撤销";
	// 已开单
	public static final String ORDER_STATUS_GOT_CHN = "已开单";
	// 运输中
	public static final String ORDER_STATUS_IN_TRANSIT_CHN = "运输中";
	// 签收失败
	public static final String ORDER_STATUS_SIGNFAIL_CHN = "签收失败";
	// 签收成功
	public static final String ORDER_STATUS_SINGSUCCESS_CHN = "签收成功";
	// 待受理
	public static final String ORDER_STATUS_WAIT_ACCEPT_CHN = "待受理";
	// 待分配
	public static final String ORDER_STATUS_WAIT_ALLOT_CHN = "待分配";
	// 空
	public static final String ORDER_MESSAGE_NULL = "空";
	// 原因
	public static final String ORDER_MESSAGE_REASON = "原因是:";
	// 订单号
	public static final String ORDER_NUMBER = "订单号";
	// 催单次数
	public static final String ORDER_URGE_COUNT = "，催单次数";
	/**
	 * 运输性质
	 */
	// 精准空运
	public static final String ORDER_TRANSTYPE_JZKY = "JZKY";
	// 精纯汽运长途
	public static final String ORDER_TRANSTYPE_JZQY_LONG = "JZQY_LONG";
	// 精准汽运短途
	public static final String ORDER_TRANSTYPE_JZQY_SHORT = "JZQY_SHORT";
	// 精准卡航
	public static final String ORDER_TRANSTYPE_JZKH = "JZKH";
	// 精准城运
	public static final String ORDER_TRANSTYPE_JZCY = "JZCY";
	// 汽运偏线
	public static final String ORDER_TRANSTYPE_AGENT_VEHICLE = "AGENT_VEHICLE";
	/**			
	    * 修改人：张斌
		*修改时间：2013-7-27 15:10
		*原有内容：无（新增）
		*修改原因：运输方式包裹常量
	 */
	//begin
	// 包裹
	public static final String ORDER_TRANSTYPE_AGENT_PACKAGE = "PACKAGE";
	//end

	// FOSS运单快递
	public static final String WAYBILL_TRANS_EXPRESS = "TRANS_EXPRESS";
	/**
	 * 提货方式
	 */
	//自提
	public static final String ORDER_DELIVERMODE_PICKSELF = "PICKSELF";
	// 送货上门
	public static final String ORDER_DELIVERMODE_PICKFOOR = "PICKFOOR";
	// 机场自提
	public static final String ORDER_DELIVERMODE_PICKONAIEPORT = "PICKONAIEPORT";
	// 送货上楼
	public static final String ORDER_DELIVERMODE_PICKUPSTAIRS = "PICKUPSTAIRS";
	// 送货(不含上楼)
	public static final String ORDER_DELIVERMODE_PICKNOTUPSTAIRS = "PICKNOTUPSTAIRS";
	// 送货进仓
	public static final String ORDER_DELIVERMODE_DELIVERYSTOCK = "DELIVERY_STOCK";
	// 内部带货自提
	public static final String ORDER_DELIVERMODE_INNERPICKUP = "INNER_PICKUP";
	// 免费送货
	public static final String ORDER_DELIVERMODE_FREEDELIVERY = "FREE_DELIVERY";
	// 自提（不含机场提货费）
	public static final String ORDER_DELIVERMODE_SELFPICKUP = "SELF_PICKUP";
	// 免费自提
	public static final String ORDER_DELIVERMODE_FREEPICKUP = "FREE_PICKUP";
	/**			
	    * 修改人：张斌
		*修改时间：2013-8-01 15:10
		*原有内容：无（新增）
		*修改原因：提货方式：送货
	 */
	//begin
	// 送货
	public static final String ORDER_DELIVERMODE_DELIVER_UP = "DELIVER_UP";
	//end


	// foss
	// 自提
	public static final String ORDER_DELIVERMODE_PICKSELF_VHE = "PICKUP";
	// 送货上门
	public static final String ORDER_DELIVERMODE_PICKFOOR_VHE = "PICKFOOR";
	// 机场自提
	public static final String ORDER_DELIVERMODE_PICKONAIEPORT_VHE = "AIRPORT_PICKUP";
	// 送货上楼
	public static final String ORDER_DELIVERMODE_PICKUPSTAIRS_VHE = "DELIVERY_HOME";
	// 送货(不含上楼)
	public static final String ORDER_DELIVERMODE_PICKNOTUPSTAIRS_VHE = "DELIVERY";

	/**
	 * 订单来源
	 */

	// 网上营业厅
	public static final String ORDER_SOURCE_ONLINE = "ONLINE";
	// 呼叫中心
	public static final String ORDER_SOURCE_CALLCENTER = "CALLCENTER";
	// 呼叫中心合肥
	public static final String ORDER_SOURCE_CALLCENTER_HF = "CALLCENTER-HF";
	// 淘宝网
	public static final String ORDER_SOURCE_TAOBAO = "TAOBAO";
	// 阿里巴巴
	public static final String ORDER_SOURCE_ALIBABA = "ALIBABA";
	// 金蝶友商网
	public static final String ORDER_SOURCE_YOUSHANG = "YOUSHANG";
	// 淘宝商城
	public static final String ORDER_SOURCE_SHANGCHENG = "SHANGCHENG";
	// 营业部
	public static final String ORDER_SOURCE_BUSINESS_DEPT = "BUSINESS_DEPT";
	// QQ速递
	public static final String ORDER_SOURCE_QQSUDI = "QQSUDI";
	// 苏宁易购
	public static final String ORDER_SOURCE_SUNING = "SUNING";
	// 微信
	public static final String ORDER_SOURCE_WEIXIN = "WEIXIN";
	// 华强宝
	public static final String ORDER_SOURCE_HUAQIANGBAO = "HUAQIANGBAO";
	// 号百快递业务平台
	public static final String ORDER_SOURCE_HAOBAI = "HAOBAI";
	// 小米黄页
	public static final String ORDER_SOURCE_XIAOMI = "XIAOMI";
	/**
	 * 阿里巴巴会员类型 (暂为接口引用)
	 * */
	// 诚信通会员
	public static final String ORDER_ALIBABA_CXT = "CXT";
	// 普通会员
	public static final String ORDER_ALIBABA_PT = "PT";

	/**
	 * 400订单管理组 标杆编码
	 */
	public static String STANDAR_CODE_400 = "DP00059";
	/**
	 * 只能修改呼叫中心的订单角色ID
	 */
	public static String ROLEID_CALLCENTER_UPDATE = "4008301";
	/**
	 * 只能撤销呼叫中心的订单角色ID
	 */
	public static String ROLEID_CALLCENTER_CANCEL = "4008302";
	/**
	 * 只能延迟呼叫中心的订单角色ID
	 */
	public static String ROLEID_CALLCENTER_DELAY = "4008303";
	
	// 运输类型
	public static final String TRAN_TYPE_FSF = "FSF";
	public static final String TRAN_TYPE_LRF = "LRF";
	public static final String TRAN_TYPE_FLF = "FLF";
	public static final String TRAN_TYPE_SRF = "SRF";
	public static final String TRAN_TYPE_PLF = "PLF";
	public static final String TRAN_TYPE_FV = "FV";
	public static final String TRAN_TYPE_AIR = "WVH";
	public static final String TRAN_TYPE_PACKAGE = "PACKAGE";
	
	
	//特殊字符
	public static final String SPECIAL_2029 = unicodeToString("2029");
	public static final String SPECIAL_2028 = unicodeToString("2028");
	
	private static String unicodeToString(String str){
		return String.valueOf((char) ((int) Integer.valueOf(str, 16)));
	}
	
	//接货地址
	public static final String RECEIVE_GOODS = "RECEIVE_GOODS";
}
