/**   
 * @title OrderExceptionType.java
 * @package com.deppon.crm.module.order.shared.exception
 * @description 订单异常类型
 * @author 潘光均
 * @update 2012-3-9 下午4:34:39
 * @version V1.0   
 */
package com.deppon.crm.module.order.shared.exception;

/**
 * @description 订单异常类型
 * @author 潘光均
 * @version 0.1 2012-3-9
 * @date 2012-3-9
 */

public enum OrderExceptionType {
	/**
	 * autor：kuang
	 * date：2013-09-03
	 * 修改原因：增加延迟操作
	 * 描述：订单在此状态不能延迟
	 */
	ORDER_CANNOT_DELAY("i18n.order.err.orderCannotDelay"),
	PILOTCITY_PACKAGE_CHANGE_NOT("i18n.pilotorder.notchangepackage"),
	PILOTCITY_PACKAGE_CHANGE_NOT2("i18n.pilotorder.notchangepackage2"),
	/*		
	* 修改人：张斌
	* 修改时间：2017-7-30
	* 修改内容：无（新增）
	* 修改原因：增加枚举，试点城市快递订单校验
	*/
	//begin
	/**
	 * 快递订单不允许进行约车操作
	 */
	PILOTCITY_CALLCAR_NOT("i18n.pilotorder.notcallcar"),
	/**
	 * FOSS不允许撤销该订单
	 */
	PILOTCITY_FOSS_NOT("i18n.pilotorder.fossnot"),
	/**
	 * 不能修改为非试点城市快递订单
	 */
	PILOTCITY_CHANGE_NOT("i18n.pilotorder.notchange"),
	/**
	 * 非试点城市快递订单不允许创建
	 */
	NOTPILOTCITY_CREATE_NOT("i18n.notpilotorder.notcreate"),
	//end
	/**
	 *该订单已约车，不需要重复约车 
	 */
	ORDER_HADBOOKVIHECLE("i18n.order.error.hadBookVihecle"),
	/**
	 * 订单对象为空
	 */
	ORDER_NULL("i18n.order.error.orderNull"),
	/**
	 * 订单保存失败
	 */
	ORDER_EXCEPTION("i18n.order.system.exception"),
	/**
	 * 订单保存失败
	 */
	ORDER_SAVE_EXP("i18n.order.error.orderNull"),
	/**
	 * 数据持久化异常
	 */
	ORDER_DATABASE_EXCEPTION("i18n.order.error.database.exception"),
	/**
	 * 用户未登录
	 */
	USER_NOT_LOGIN_EXCEPTION("i18n.order.err.userNotLogionException"),
	/**
	 * 订单信息不完整
	 */
	ORDER_INFORMATION_INCOMPLETE("i18n.order.err.orderInfoIncomplete"),
	/**
	 * 订单状态必须为待受理
	 */
	ORDER_STATUS_MUSTBEUNDEAL("i18n.order.error.orderStatusMustBeUndeal"),
	/**
	 * 打回原因没有填写
	 */
	ORDER_FIGHTBACKREASON_MUSTBEREQUIRED("i18n.order.error.refuseReasonNull"),
	/**
	 * 订单不能更新
	 */
	ORDER_CANNOT_UPDATE("i18n.order.err.orderCannotUpdate"),
	/**
	 * 订单来源为官网时保价金额不能更改
	 */
	ORDER_INSUREDAMOUNT_CANNOTUPDATE("i18n.order.err.insuredAmountCanNotUpdate"),
	/**
	 * 订单不能撤消
	 */
	ORDER_CANNOT_CANCEL("i18n.order.err.orderCannotCancel"),
	/**
	 * 订单不是相同的信息
	 */
	ORDER_NOT_SAME_INFO("i18n.order.err.orderNotSameInfo"),
	/**
	 * 订单不在可以拒绝状态
	 */
	ORDER_NOT_REFUSEABLE("i18n.order.err.orderNotRefuseable"),
	/**
	 * 订单中有不可以分配的状态
	 */
	ORDER_NOT_ALL_ASSIGNABLE("i18n.order.err.orderNotAllAssignable"),
	/**
	 * 手机和电话不能同时为空
	 */
	ORDER_MOBILEANDPHONE_MUSTBEONE("i18n.order.err.mobileOrPhoneMustOne"),

	/**
	 * 该状态下不能催单
	 */
	ORDER_NOT_URGE("i18n.order.err.notUrge"),
	/**
	 * 此订单不能催单，创建30分钟后才可催单
	 */
	ORDER_NOT_URGE30("i18n.order.err.notUrge30"),
	/**
	 * 此订单不能催单，催单15分钟后才可催单
	 */
	ORDER_NOT_URGE15("i18n.order.err.notUrge15"),
	/**
	 * 此订单不能打回，在待受理状态下才可打回
	 */
	ORDER_NOT_RETURNORDER("i18n.order.err.notReturn"),
	/**
	 * 营业部订单不能打回
	 */
	ORDER_BUSINESS_DEPT_NOT_RETURNORDER("i18n.order.err.businesDeptNotReturn"),
	/**
	 * 此订单不能揽货失败操作
	 */
	ORDER_NOT_ACCPETFAIL("i18n.order.err.notAccpetfail"),
	/**
	 * 订单在此状态不能受理
	 */
	ORDER_CANNOT_ACCEPT("i18n.order.err.orderCannotAccept"),
	/**
	 * 订单不需要约车
	 */
	ORDER_BOOKVIHICE_NOTREQUIRED("i18n.order.err.orderNotRequiredBookVehice"),
	/**
	 * 是否尾板车不能为空
	 */
	ORDER_BOOKVIHICEINFO_ISTAILBOARD("i18n.order.err.IsTailBoard"),
	/**
	 * 用车部门不能为空
	 */
	ORDER_BOOKVIHICEINFO_USEVEHICLEDEPT("i18n.order.err.UseVehicleDept"),
	/**
	 * 派车车队不能为空
	 */
	ORDER_BOOKVIHICEINFO_VEHICLETEAM("i18n.order.err.VehicleTeam"),
	/**
	 * 订单该状态不能约车
	 */
	ORDER_BOOKVIHICE_FAIL("i18n.order.err.bookVihiceFail"),
	/**
	 * 订单该状态不能约车
	 */
	ORDER_BOOKVIHICE_CALLINTERFACEFAIL(
			"i18n.order.err.bookVihiceCallInterfaceFail"),
	/**
	 * 同步erp失败
	 */
	ORDER_SENDORDERTOERP_FAIL("i18n.order.err.synchronizationErpFaill"),
	/**
	 * 订单已签收
	 */
	WAYBILL_STAUTS_ASSIGNED("i18n.order.err.waybillAssigned"),
	/**
	 * 订单已关联
	 */
	WAYBILL_ALREADYASSOCIATE("i18n.order.err.waybillAlreadyAssociate"),
	/**
	 * 用户未登录
	 */
	ORDER_USER_NOTLOGIN("i18n.order.err.userNotLogin"),
	/**
	 * 接货时间为空
	 */
	ORDER_INFORMATION_ACCEPTTIME_INCOMPLETE("i18n.order.err.acceptTimeNull"),
	/**
	 * 查询时间超出查询范围
	 */
	ORDER_NOT_DATE_OUTRANGE("i18n.order.err.timeOutofRange"),
	/**
	 * 查询订单部存在
	 */
	WAYBILL_NOT_EXIST("i18n.order.err.waybillNotExist"),
	/**
	 * 此运单与订单已绑定
	 */
	WAYBILL_BERELATED("i18n.order.err.waybillBeRelated"),
	/**
	 * 查询不到此订单信息
	 */
	ORDER_ISNOTEXSITS("il8n.order.orderIsNotExsit"),
	/**
	 * 定运单关联失败
	 */
	ORDER_ASSOCIATE_FAIL("i18n.order.associateFail"), USER_INFORMATION_INCOMPLETE(
			"用户信息不完整"),
	/**
	 * 运输性质不存在
	 */
	NO_SUCH_TRANSTYPE("i18n.order.noSuchTransType"),
	/**
	 * 提货方式不存在
	 */
	NO_SUCH_DELIVERMODE("i18n.order.noSuchDeliverType"),
	/**
	 * 如果是官网，渠道单号不能为空
	 */
	ORDER_CHANNELNUMBER_MUST("i18n.order.channelNumberMust"),
	/**
	 * 不能跨渠道修改订单
	 */
	CANNOT_MODITY_WEBORDER("i18n.order.cannotModityWebOrder"),
	/**
	 * 约车时，到达网点对应的erpId不能为空
	 */
	BOOKVIHICE_ERPID_CANNOTNULL("i18n.order.cannotNullErpId"),
	/**
	 * 货物数量、体积、重量不能小于0
	 */
	BOOKVIHICE_NUM_CANNOTZERO("i18n.order.cannotNullZero"),
	// 9999
	BOOKVIHICE_NUM_GOODSNUMBER("i18n.order.err.goodsNumber"),
	// 999
	BOOKVIHICE_NUM_TOTALVOLUME("i18n.order.err.totalVolume"),
	// 999999
	BOOKVIHICE_NUM_TOTALWEIGHT("i18n.order.err.totalWeight"),

	BOOKVIHICE_ENDDEPT_CONNOTNULL("i18n.order.endDeptcannotNull"),
	/**
	 * 约车时，储运事项长度不能大于80
	 */
	BOOKVIHICE_TRANSNOTE_CANNOTOVER80(
			"i18n.order.bookVihiceTransnoteCanNotOver80"),
	/**
	 * 绑定联系人，调用接口出错了！
	 */
	BOUNDCONTACT_CALLINTEFACEERROR("i18n.order.boundContactCallIntefaceError"),
	/**
	 * 绑定联系人，订单类型不符合绑定规则！
	 */
	BOUNDCONTACT_ORDERSOURCEERROR("i18n.order.boundContactOrderSourceError"),
	/**
	 * 绑定联系人，订单类型不符合绑定规则！
	 */
	CREATEORDER_DOUBLECHANNELNUMBER("i18n.order.createOrderDoubleChannelNumber"), 
	/**
	 * 提交订单备注，联系人不能为空！
	 */
	CANNOT_ISNULL_CONCATNAME("i18n.oprationlog.submitOprationlogContactnameIsNull"), 
	/**
	 * 提交订单备注，订单id不能为空！
	 */
	CANNOT_ISNULL_ORDERID("i18n.oprationlog.submitOprationlogOrderidIsNull"),
	/**
	 * 提交订单备注，备注内容不能为空！
	 */
	CANNOT_ISNULL_OPERATIONCONTENT("i18n.oprationlog.submitOprationlogOperationcontentIsNull"),
	/**
	 *只能修改下单至本部门的订单 
	 */
	CANNOT_MODIFY_ONLYSELF("i18n.updateOrder.cannotModifyOrderOnlySelf"),
	/*
	 *只能修改呼叫中心的订单 
	 */
	CANNOT_DELAY_NOCENTER("i18n.updateOrder.cannotDelayNoCenter"),
	/**
	 *只能撤销官网的快递订单 
	 */
	ORDER_ONLYCANCEL_ONLINE("i18n.order.onlyCancelOnline"),
	
	/**
	 *快递保价和代收货款均不能超过20000
	 */
	ORDER_SUREACOUNT_PACKAGE("i18n.order.sureAcountPackage"),
	
	/**
	 * 只能修改营业部和呼叫中心订单 
	 */
	CANNOT_MODIFY_ONLYRESOURCEDEPTCC("i18n.updateOrder.onlyUpdateDeptAndCC"),
	REGISTERUSERNOTEXIST("i18n.updateOrder.registerNotExists"),
	/*
	 * 只能撤销呼叫中心订单 
	 */
	ONLYCANCEL_CALLCENTER("il8n.order.onlyCancelCallCenterOrder"),
	
	LIMIT_DELAY("i18n.delayOrder.cannotDelay"),
	
	/**
	 * 订单号规则和原有数据有冲突
	 */
	ORDER_NUMBER_RULE_CONFLICT("i18n.updateOrderNumberRule.conflict"),
	/**
	 * 没有找到匹配的订单号规则
	 */
	ORDER_NUMBER_RULE_NOTFOUND("i18n.ordernumberrule.notfound"),
	/**
	 * 找到过多的匹配的订单号规则
	 */
	ORDER_NUMBER_RULE_FOUNDTOOMUCH("i18n.ordernumberrule.foundtoomuch"),
	/**
	 * 订单号规则里的前缀为null
	 */
	ORDER_NUMBER_RULE_SIGNISNULL("i18n.ordernumberrule.signisnull"),
	/**
	 * 订单号规则前缀已经被其他渠道使用
	 */
	ORDER_NUMBER_RULE_SIGNUSED("i18n.ordernumberrule.signused"),
	/**
	 * 时间查询范围必须在一年内
	 */
	QUERY_ORDERS_DATE_INVALID("i18n.queryorders.dateinvalid");

	private String errorCode;

	private OrderExceptionType(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 *@return  errorCode;
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode : set the property errorCode.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}