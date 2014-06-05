package com.deppon.crm.module.custrepeat.shared.domain;
/**
 * 疑似重复客户 配置类
 * @author 120750
 *
 */
public class CustRepeatConstants {
	/**
	 * 普通部门
	 */
	public static final String DEPT_ORDINARY= "DEPT_ORDINARY";
	/**
	 * 小区部门
	 */
	public static final String DEPT_SMALL_REGION= "DEPT_SMALL_REGION";
	/**
	 * 大区部门
	 */
	public static final String DEPT_BIG_REGION= "DEPT_BIG_REGION";
	/**
	 * 快递（点部经理、分部高级经理、快递大区总经理）
	 */
	public static final String DEPT_EXPRESS= "DEPT_EXPRESS";
	/**
	 * 特殊的（营业部、派送部、营业区、大区、事业部、400销售管理组、电销管理小组）
	 */
	public static final String DEPT_SPECIAL= "DEPT_SPECIAL";
	/**
	 * 钻石客户
	 */
	public static final String DIAMOND = "DIAMOND";
	/**
	 * 铂金客户
	 */
	public static final String PLATINUM = "PLATINUM";
	/**
	 * 黄金客户
	 */
	public static final String GOLD = "GOLD";
	/**
	 * 普通客户
	 */
	public static final String NORMAL = "NORMAL";
	/**
	 * 固定客户
	 */
	public static final String RC_CUSTOMER = "RC_CUSTOMER";
	/**
	 * 散客
	 */
	public static final String SC_CUSTOMER = "SC_CUSTOMER";
	/**
	 * 潜客
	 */
	public static final String PC_CUSTOMER = "PC_CUSTOMER";
	
	// 已提交
	public static final String WORKFLOW_STATUS_IN_APPROVE = "1";
	// 已同意
	public static final String WORKFLOW_STATUS_AGREED = "2";
	// 未同意
	public static final String WORKFLOW_STATUS_UNAGREE = "3";
	
	/**
	 * 电销管理小组
	 */
	public static final String ESALESDEPT = "DP09788";
	/**
	 * 400销售管理组
	 */
	public static final String SALESMANAGEDEPT = "DP09787";
	/**
	 * 快递相关
	 */
	public static final String EXPRESSDEPT = "快递";
	
	/**
	 * 审批中的客户
	 */
	public static final String search_status_approve = "status_approve";
	/**
	 * 商机.日程.计划
	 */
	public static final String search_status_mobile = "status_mobile";
	
	/**
	 * 点部经理
	 */
	public final static String position_manager = "点部经理";
	/**
	 * 分部高级经理
	 */
	public final static String position_highManager = "分部高级经理";
	/**
	 * 快递大区总经理
	 */
	public final static String position_bossManager = "快递大区总经理";
	
	/**
	 * 营业部
	 */
	public final static String DEPTNAME_SALESDEPT = "营业部";
	/**
	 * 派送部
	 */
	public final static String DEPTNAME_SENDDEPT  = "派送部";
	
	/**
	 * 派送中心
	 */
	public final static String DEPTNAME_SENDDEPT_CENTER  = "派送中心";
	/**
	 * 营业区
	 */
	public final static String DEPTNAME_AREADEPT = "营业区";
	/**
	 * 大区
	 */
	public final static String DEPTNAME_REGIONDEPT = "大区";
	/**
	 * 事业部
	 */
	public final static String DEPTNAME_BUSS = "事业部";
	/**
	 * 400销售管理组
	 */
	public final static String DEPTNAME_400SELL = "400销售管理组";
	
}
