package com.deppon.crm.module.common.server.util;

public class Constant {

	public static final String CONFIG_ORDER_RECEIVE_GOODS_YES = "YES";
	public static final String CONFIG_ORDER_RECEIVE_GOODS_NO = "NO";

	// 特别消息
	public static final String TASK_TYPE_SUPER_MESSAGE = "SUPER_MESSAGE";
	// 订单未分配
	public static final String TASK_TYPE_ORDER_UNASSIGN = "ORDER_UNASSIGN";
	// 订单未受理
	public static final String TASK_TYPE_ORDER_UNACCEPT = "ORDER_UNACCEPT";
	// 订单已受理
	public static final String TASK_TYPE_ORDER_ACCEPTED = "ORDER_ACCEPTED";
	// 订单已退回
	public static final String TASK_TYPE_ORDER_GOBACK = "ORDER_GOBACK";
	// 订单消息提醒
	public static final String TASK_TYPE_ORDER_MESSAGE = "ORDER_MESSAGE";
	// 理赔提示消息
	public static final String TASK_TYPE_RECOMPENSE_MESSAGE = "RECOMPENSE_MESSAGE";
	// 理赔提示消息
	public static final String TASK_TYPE_CUSTMER_MESSAGE = "CUSTMER_MESSAGE";
	// 客户信用
	public static final String TASK_TYPE_CUSTCREDIT_MESSAGE = "CUSTCREDIT_MESSAGE";
	// 部门信用
	public static final String TASK_TYPE_DEPTCREDIT_MESSAGE = "DEPTCREDIT_MESSAGE";
	//计划与维护
	// 计划提醒
	public static final String TASK_TYPE_NEW_DEVPLAN 			= "NEW_PLAN";		
	// 日程提醒
	public static final String TASK_TYPE_NEW_DEVSCHEDULE 		= "NEW_SCHEDULE";
	/**
	 * 工单功能类型
	 */
	public static final String COMPLAINT_REPORT_RETURN="COMPLAINT_REPORT_RETURN";//上报退回处理
	public static final String COMPLAINT_WORKBENCH="COMPLAINT_WORKBENCH";//待办工单
	public static final String COMPLAINT_TASK="COMPLAINT_TASK";//部门工单管理
	public static final String COMPLAINT_VERIFY="COMPLAINT_VERIFY";//工单回访
	public static final String COMPLAINT_REALTIME_REPORT_RETURN="COMPLAINT_RT_REPORT_RETURN";//新上报退回
	
	/**
	 * 始发网点与受理部门基础资料--订单来源
	 */

	// 网单(含渠道、官网)
	public static final String CONFIG_ORDER_RESOURCE_ONLINE = "ONLINE";
	// 400
	public static final String CONFIG_ORDER_RESOURCE_400 = "400";
	// 全部
	public static final String CONFIG_ORDER_RESOURCE_ALL = "ALL";

	//始发网点与受理部门关系有效
	public static final Integer LADINGSTATION_DEPARTMENT_VALID=1;
	
	//始发网点与受理部门关系无效
	public static final Integer LADINGSTATION_DEPARTMENT_INVALID=0;
	
	//是香港的标识
	public static final String ISHONGKONG_STRING = "1";
	//大陆的标识
	public static final String ISMAINLAND_STRING = "0";
	//香港岛的ID
	public static final String HONGKONG_ID = "373";
	//香港名字
	public static final String HONGKONG_NAME = "香港";
	/**
	 * 标识符--客户管理小组相当于全公司权限
	 */
	public static final String CUSTOMER_ALLAUTHORIZE_DEPARTMENT = "CUSTOMER_ALLAUTHORIZE_DEPARTMENT";
	//给客户管理小组赋予全公司权限的部门ID-
	public static final String CUSTOMER_ALLAUTHOR_DEPTIDS = "464252,464253,464254";
	
	/**			
	    * 修改人：张斌
		*修改时间：2013-7-27 15:10
		*原有内容：无（新增）
		*修改原因：运输方式包裹常量
	 */
	// 包裹
	public static final String ORDER_TRANSTYPE_AGENT_PACKAGE = "PACKAGE";
}
