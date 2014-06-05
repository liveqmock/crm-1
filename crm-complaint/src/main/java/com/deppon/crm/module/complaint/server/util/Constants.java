/**
 * Filename:	Constants.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-2-13 下午3:12:44
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-2-13	    
 */

package com.deppon.crm.module.complaint.server.util;

import java.util.Map;

public interface Constants {

	/*************** 工单模块处理状态 Begin **************/

	/**
	 * 工单未提交
	 */
	String COMPLAINT_STATUS_UNSUBMIT = "UNSUBMIT";

	/**
	 * 工单待处理
	 */
	String COMPLAINT_STATUS_PENDING = "PENDING";
	/**
	 * 工单上报退回
	 */
	String COMPLAINT_STATUS_REPORT_RETURNED = "REPORT_RETURNED";
	/**
	 * 投诉待审批
	 */
	String COMPLAINT_STATUS_WAIT_APPROVAL = "WAIT_APPROVAL";
	/**
	 * 投诉已升级
	 */
	String COMPLAINT_STATUS_UPGRADED = "UPGRADED";
	/**
	 * 投诉审批退回
	 */
	String COMPLAINT_STATUS_APPROVAL_RETURNED = "APPROVAL_RETURNED";
	/**
	 * 投诉升级退回
	 */
	String COMPLAINT_STATUS_UPGRADED_RETURNED = "UPGRADED_RETURNED";

	/**
	 * 待反馈
	 */
	String COMPLAINT_STATUS_WAITE_RESPONSE = "WAITE_RESPONSE";

	/**
	 * 部门申请延时处理
	 */
	String COMPLAINT_DEPT_STATUS_APPLY_MORETIME = "APPLY_MORETIME";

	/**
	 * 已核实
	 */
	String COMPLAINT_STATUS_VERIFIED = "VERIFIED";

	/**
	 * 已回访
	 */
	String COMPLAINT_STATUS_REVISEIT = "REVISEIT";
	/**
	 * 已结束
	 */
	/*
	 * public String COMPLAINT_STATUS_END = "END";
	 */

	/**
	 * 后台已处理
	 */
	String COMPLAINT_STATUS_BACKGROUND = "BACKGROUND";

	/**
	 * 前台已处理
	 */
	String COMPLAINT_STATUS_FRONTROUND = "FRONTGROUND";

	/**
	 * 反馈未解决
	 */
	String COMPLAINT_PROCESS_STATUS_UNRESOVLE = "FEEDBACK_UNRESOLVE";
	/**
	 * 反馈已解决
	 */
	String COMPLAINT_PROCESS_STATUS_RESOVLE = "FEEDBACK_RESOLVED";
	/**
	 * 已到期
	 */
	String COMPLAINT_PROCESS_STATUS_EXPIRED = "FEEDBACK_EXPIRED";

	/**
	 * 部分部门退回，从处理过来的
	 */
	String COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS = "DEPT_RETURNED_PROCESS";

	/**
	 * 部分部门退回，从审批过来的
	 */
	String COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL = "DEPT_RETURNED_APPROVAL";

	/**
	 * 部分部门退回，从升级过来的
	 */
	String COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE = "DEPT_RETURNED_UPGRADE";

	/**
	 * 所有部门退回，从处理过来的
	 */
	String COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL = "DEPT_RETURNED_PROCESS_ALL";

	/**
	 * 所有部门退回，从审批过来的
	 */
	String COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL = "DEPT_RETURNED_APPROVAL_ALL";

	/**
	 * 所有部门退回，从升级过来的
	 */
	String COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL = "DEPT_RETURNED_UPGRADE_ALL";

	/**
	 * 工单申请延时,工单来自处理
	 */
	String COMPLAINT_APPLY_DELAY_TO_PROCESS = "COMPLAINT_APPLY_DELAY_TO_PROSS";
	/**
	 * 工单申请延时,工单来自审批
	 */
	String COMPLAINT_APPLY_DELAY_TO_APPROVAL = "COMPLAINT_APPLY_DELAY_TO_APP";
	/**
	 * 工单申请延时,工单来自升级
	 */
	String COMPLAINT_APPLY_DELAY_TO_UPGRADE = "COMPLAINT_APPLY_DELAY_TO_UPGR";

	/*************** 工单模块处理状态 End **************/

	/*************** 工单模块解决状态 Begin **************/
	/**
	 * 未解决
	 */
	String COMPLAINT_RESOVLE_STATUS_UNRESOVLE = "UNRESOLVE";
	/**
	 * 已解决
	 */
	String COMPLAINT_RESOVLE_STATUS_RESOVLE = "RESOLVED";

	/*************** 工单模块解决状态 End **************/

	/*************** 工单模块任务部门处理状态 Begin **************/
	/**
	 * 任务部门已退回
	 */
	String COMPLAINT_TASK_STATUS_RETURNED = "TASK_RETURNED";

	/**
	 * 任务部门已退回过处理人
	 */
	String COMPLAINT_TASK_IS_BACKED = "1";

	/* 同意延时 */
	String COMPLAINT_TASK_STATUS_AGREE_MORETIME = "AGREE_MORETIME";

	/* 拒绝延时 */
	String COMPLAINT_TASK_STATUS_REFUSE_MORETIME = "REFUSE_MORETIME";

	/*************** 工单模块任务部门处理状态 End **************/

	/*************** 工单模块反馈退回记录表（feedbackreson type） Begin **************/
	/**
	 * 记录类型：反馈记录
	 */
	String COMPLAINT_RECORD_TYPE_FEEDBACK = "RECORD_FEEDBACK";
	/**
	 * 记录类型：退回记录
	 */
	String COMPLAINT_RECORD_TYPE_RETURN = "RECORD_RETURN";
	/**
	 * 记录类型：呼叫中心核实
	 */
	String FEED_BACK_REASION_TYPE_VERYFIY = "FEED_BACK_REASION_TYPE_RETURN";
	/**
	 * 记录类型：呼叫中心核实
	 */
	String FEED_BACK_REASION_TYPE_DELAY = "FEED_BACK_REASION_TYPE_DELAY";

	/*************** 工单模块反馈退回记录表 End **************/

	/**
	 * 工单已被接入状态(工单处理)
	 */
	String COMPLAINT_ACCESS_STATUS = "COMPLAINT_ACCESS";

	/*************** 工单模块操作记录表 Begin **************/
	/**
	 * 操作类型：审批提交至反馈
	 */
	String COMPLAINT_OPERATE_TYPE_FROM_APPROVAL = "FROMAPPROVAL";

	/**
	 * 操作类型：升级提交至反馈
	 */
	String COMPLAINT_OPERATE_TYPE_FROM_UPGRADE = "FROMUPGRADE";

	/**
	 * 操作类型：处理提交至反馈
	 */
	String COMPLAINT_OPERATE_TYPE_FROM_PROCESS = "FROMPROCESS";

	/**
	 * 操作类型：延时申请来源于审批
	 */
	String COMPLAINT_OPERATE_TYPE_DELAY_FROM_APPROVAL = "DELAY_FORM_APPROVAL";
	/**
	 * 操作类型：延时申请来源于升级
	 */
	String COMPLAINT_OPERATE_TYPE_DELAY_FROM_UPGRADE = "DELAY_FROM_UPGRADE";
	/**
	 * 操作类型：延时申请来源于完成处理
	 */
	String COMPLAINT_OPERATE_TYPE_DELAY_FROM_PROCESS = "DELAY_FROM_PROCESS";
	/**
	 * 操作类型：部门退回来源于审批
	 */
	String COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL = "RETURN_FROM_APPROVAL";
	/**
	 * 操作类型：部门退回来源于升级
	 */
	String COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE = "RETURN_FROM_UPGRADE";
	/**
	 * 操作类型：部门退回来源于完成处理
	 */
	String COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS = "RETURN_FROM_PROCESS";

	/*************** 工单模块操作记录表 End **************/

	/**
	 * 投诉待审批权限ID
	 */
	String COMPLAINT_FUNCTION_WAIT_APPROVAL = "55";

	/**
	 * 投诉已升级权限ID
	 */
	String COMPLAINT_FUNCTION_UPGRADED = "56";

	// 工单模块
	String COMPLAINT_REPORTTYE_REPORT = "COMPLAINT";
	String COMPLAINT_REPORTTYE_EXCEPTION = "UNUSUAL";
	String COMPLAINT_REPEATCODE_REPORT = "D";
	String COMPLAINT_REPEATCODE_EXCEPTION = "Y";

	// 优先级别
	String COMPLAINT_PRIORITY_NORAML = "NORMAL";// 普通
	String COMPLAINT_PRIORITY_HIGH = "HIGH";// 优先

	// 操作动作
	// 提交
	String COMPLAINT_OPERAT_ORTYPE = "SUBMIT";
	// 提交审批
	String COMPLAINT_OPERAT_SUBMIT_APPROVAL = "SUBMIT_APPROVAL";
	// 投诉升级
	String COMPLAINT_OPERAT_COMPLAINT_UPGRADE = "COMPLAINT_UPGRADE";
	// 退回上报人
	String COMPLAINT_OPERAT_RETURN_REPORTOR = "RETURN_REPORTOR";
	// 退回处理人
	String COMPLAINT_OPERAT_RETURN_PROCESSOR = "RETURN_PROCESSOR";
	// 退回提交人
	String COMPLAINT_OPERAT_RETURN_SUBMITOR = "RETURN_SUBMITOR";
	// 延时申请
	String COMPLAINT_OPERAT_DELAY_APPLY = "DELAY_APPLY";
	// 完成处理
	String COMPLAINT_OPERAT_FINISH_DISPATCH = "FINISH_DISPATCH";
	// 后台处理
	String COMPLAINT_OPERAT_BACKGROUND_PROCESS = "BACKGROUND_PROCESS";

	String IF_DELAY_APPLICATION_EFFECTIVE_YES = "1";
	String IF_DELAY_APPLICATION_EFFECTIVE_NO = "0";
	String IF_DELAY_APPLICATION_EFFECTIVE_REFUSE = "2";

	// 是否最终反馈
	String IF_FEED_BACK_YES = "1";// 是最终反馈
	String IF_FEED_BACK_NO = "0";// 不是最终反馈

	/**
	 * 任务部门操作是否超时
	 */
	String RESULT_IF_OVER_TIME_YES = "1";
	String RESULT_IF_OVER_TIME_NO = "0";

	/**
	 * 任务部门操作是否到期
	 */
	String RESULT_MATURITY_YES = "1";
	String RESULT_MATURITY_NO = "0";
	/**
	 * 是否是被绑定列表
	 */
	String TO_BE_BANGING_YES = "YES";
	String TO_BE_BANGING_NO = "NO";
	// /**
	// * 退回
	// */
	// public String FEED_BACK_REASION_TYPE_RETURN="RETURN";
	// /**
	// * 反馈
	// */
	// public String FEED_BACK_REASION_TYPE_FEEDBACK="FEEDBACK";
	/**
	 * 操作类型：反馈登记
	 */
	/*
	 * public String COMPLAINT_OPERATE_TYPE_FEEDBACK="FEEDBACK";
	 *//**
	 * 操作类型：申请延时
	 */
	/*
	 * public String COMPLAINT_OPERATE_TYPE_APPDELAY="DELAY";
	 */

	String COMPLAINT_ADMIN = "000000";// 管理员帐号

	/**
	 * 任务所属
	 */
	String COMPLAINT_PROCESSTYPE_DEPARTMENT = "department";// 所属部门
	String COMPLAINT_PROCESSTYPE_EMPLOYEE = "employee";// 所属个人

	String RETURNCOMPLAINT_TODO_MESSAGE = "您好,工单编号{0}已经被退回！";

	public static final String  FLAGEXPRESS = "K";
	public static final String TRANS_EXPRESS = "TRANS_EXPRESS"; // 快递
	public static final String BUSINESS_PMODEL_EXPRESS = "EXPRESS"; // 快递
	public static final String BUSINESS_MODEL_UNEXPRESS = "UNEXPRESS"; // 零担

	final String CC_SU_ZHOU = "SUZHOU";//CC 苏州
	final String CC_HE_FEI = "HEFEI";//CC 合肥
}

