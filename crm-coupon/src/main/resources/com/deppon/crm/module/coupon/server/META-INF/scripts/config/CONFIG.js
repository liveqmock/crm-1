/**
 * <p>
 * 定义全局变量
 * </p>
 * @author 张登
 * @时间 2012-11-28
 */


/**
 * 优惠券发券规则 ： 手动发券、自动发券
 */
//手动发券
var COUPON_SENDHAND="HANDCOUPON";
//自动发券
var COUPON_SENDAUTO="AUTOCOUPON";


/**
 * 营销计划状态 ： 未启用、已启用、已终止、已结束
 */
// 未启用
var MARKETPLAN_NOUSE = "10";
// 已启用
var MARKETPLAN_USING = "20";
// 已终止
var MARKETPLAN_STOP = "30";
// 已结束
var MARKETPLAN_FINISH = "40";


/**
 * 线路区域要求 	(0、1、2、3)空、走货线路、发货区域、到达区域
 */
// 空、
var GOOD_LINE_NULL = "1";
// 走货线路、
var GOOD_LINE_AREA = "2";
// 发货区域、
var GOOD_LINE_LEAVE = "3";
// 到达区域
var GOOD_LINE_ARRIVED = "4";