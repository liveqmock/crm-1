package com.deppon.crm.module.scheduler.server.manager;

/**
 * @description 定时控制枚举.
 * @author 安小虎
 * @version 0.1
 * @date 2012-6-1
 */

public enum TimerSchedulingControlEnum {
	/*
	 * 客户开发模块
	 */
	// 发货金额前50名客户
	T_CRM_DELIVERTOP50CUSTMER("T_CRM_DELIVERTOP50CUSTMER"),
	// 到达金额前50名客户
	T_CRM_RECEIVETOP50CUSTMER("T_CRM_RECEIVETOP50CUSTMER"),
	// 流失客户
	T_CRM_CUSTOMERLOSEDETAIL("T_CRM_CUSTOMERLOSEDETAIL"),
	// 频率超期客户
	T_CRM_DELIVEROVERDUE("T_CRM_DELIVEROVERDUE"),

	/*
	 * 客户模块
	 */
	// 潜客信息报表
	T_CRM_WAYBILLCLIENT("T_CRM_WAYBILLCLIENT"),
	// 散客信息表
	T_CRM_DISPERSECLIENT("T_CRM_DISPERSECLIENT"),
	// 会员升级报表
	T_CRM_MEMBERUPGRADE("T_CRM_MEMBERUPGRADE"),
	// 会员降级报表
	T_CRM_MEMBERFALL("T_CRM_MEMBERFALL"),
	// 会员积分明细报表
	T_CRM_INTEGRALDETAIL("T_CRM_INTEGRALDETAIL"),
	// 散客升级报表
	T_CRM_NOTMEMBERTOMEMBER("T_CRM_NOTMEMBERTOMEMBER");

	/*
	 * 构造
	 */
	private TimerSchedulingControlEnum(String str) {
		this.str = str;
	}

	/*
	 * 属性
	 */
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	/*
	 * 获得值
	 */
	public static String getValue(TimerSchedulingControlEnum type) {
		switch (type) {
		case T_CRM_DELIVERTOP50CUSTMER:
			return T_CRM_DELIVERTOP50CUSTMER.getStr();
		case T_CRM_RECEIVETOP50CUSTMER:
			return T_CRM_RECEIVETOP50CUSTMER.getStr();
		case T_CRM_CUSTOMERLOSEDETAIL:
			return T_CRM_CUSTOMERLOSEDETAIL.getStr();
		case T_CRM_DELIVEROVERDUE:
			return T_CRM_DELIVEROVERDUE.getStr();
		case T_CRM_WAYBILLCLIENT:
			return T_CRM_WAYBILLCLIENT.getStr();
		case T_CRM_DISPERSECLIENT:
			return T_CRM_DISPERSECLIENT.getStr();
		case T_CRM_MEMBERUPGRADE:
			return T_CRM_MEMBERUPGRADE.getStr();
		case T_CRM_MEMBERFALL:
			return T_CRM_MEMBERFALL.getStr();
		case T_CRM_INTEGRALDETAIL:
			return T_CRM_INTEGRALDETAIL.getStr();
		default:
			return "";
		}

	}
}
