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

package com.deppon.crm.module.recompense.server.utils;

public class Constants {
	// 系统用户ID
	public static final String SYSTEM_SUPER_USER_ID = "1";

	// 提示消息
	public static final String RECOMPENSE_CUSTOMER_MESSAGE = "您好！我司已经就您申请的索赔出具理赔意见，我司当地营业部会尽快和您联系，您也可致电当地营业部({0})。谢谢！";
	// 索赔
	public static final String RECOMPENSE_MANAGER_MESSAGE = "您好，贵部提交的单号{0}的索赔初步处理意见已经完成，赔款金额{1}元，请加快与客户协商！";
	// 凭证号
	public static final String RECOMPENSE_VOUCHER = "凭证号";
	// 提醒
	public static final String RECOMPENSE_TODO_SUBMITED_MESSAGE = "提醒：您有{0}个超两天未处理理赔需要处理，请进行处理！";
	// 提醒
	public static final String RECOMPENSE_TODO_ONLINEAPPLY_MESSAGE = "提醒：贵部门有一个在线理赔申请(凭证号：{0})需要处理，请进行处理！";
	// 代办事宜的类型
	public static final String TODO_TASK_TYPE = "RECOMPENSE_MESSAGE";
	// 代办事宜的类型
	public static final String TODO_SUBMITED_TASK_TYPE = "RECOMPENSE_TODO_SUBMITED";
	// OA差错类型
	// OA异常签收类型
	public static final int OA_ACCIDENT_ABNORMAL_SIGN = 2;
	// OA丢货类差错
	public static final int OA_ACCIDENT_LOST_GOODS = 14;

	// 角色
	public static final String ROLE_MANAGER = "3";
	// 角色
	public static final String ROLE_ADMIN = "4";
	// 角色
	public static final String ROLE_CASHER = "5";
	// 角色
	public static final String ROLE_OACLIENT = "6";
	// 角色
	public static final String ROLE_ERPCLIENT = "7";
	// 角色
	public static final String ROLE_ATTENDANT = "8";
	// 区域客服
	public static final String ROLE_CUSTOMSERVICE = "9";
	//快递经理
	public static final String ROLE_EXPRESS = "10";

	// 理赔外部工作流类型
	// ****************************
	// 理赔外部工作流类型-----正常审批类型
	public static final int WORKFLOW_TYPE_NORMAL = 1;
	// 理赔外部工作流类型-----多赔审批类型
	public static final int WORKFLOW_TYPE_OVERPAY = 2;
	// 理赔外部工作流类型-----费控审批类型
	public static final int WORKFLOW_TYPE_PAYMENT = 3;
	// 外部工作流工作流状态------提交
	public static final int WORKFLOW_STATUS_SUBMIT = 1;
	// 外部工作流工作流状态 ------拒绝
	public static final int WORKFLOW_STATUS_REFUSE = 2;
	// 外部工作流工作流状态 ------同意
	public static final int WORKFLOW_STATUS_APPROVE = 3;
	// 外部工作流工作流状态 ------汇款失败
	public static final int WORKFLOW_STATUS_PAYFAILED = 4;
	// *************************

	/*
	 * 理赔类型
	 */
	// 异常签收理赔
	public static final String ABNORMAL_SIGN = "abnormal";
	// 丢货理赔
	public static final String LOST_GOODS = "lost_goods";;
	// 未开单理赔
	public static final String UNBILLED = "unbilled";

	/*
	 * 理赔方式
	 */
	// 快速理赔
	public static final String FAST_TYPE = "fast";
	// 正常理赔
	public static final String NORMAL_TYPE = "normal";
	// 在线理赔
	public static final String ONLINE_TYPE = "online";
	// 多赔
	public static final String OVERPAY_TYPE = "overpay";

	/*
	 * 理赔状态
	 */
	// 待受理
	public static final String STATUS_WAIT_ACCEPT = "WaitAccept";
	// 已拒绝
	public static final String STATUS_REJECTED = "Rejected";
	// 已作废
	public static final String STATUS_INVALID = "Invalid";
	// 在线理赔付款失败
	public static final String STATUS_ONLINE_PAY_FAILED = "OnlinePayFailed";
	// 未处理
	public static final String STATUS_SUBMITED = "Submited";
	// 在线理赔审批退回
	public static final String STATUS_ONLINE_REFUSED = "OnlineRefused";
	// 资料确认
	public static final String STATUS_DOC_CONFIRMED = "DocConfirmed";
	// 初步处理
	public static final String STATUS_HANDLED = "Handled";
	// 金额确认
	public static final String STATUS_AMOUNT_CONFIRMED = "AmountConfirmed";
	// 审批中
	public static final String STATUS_IN_OA_APPROVE = "InOaApprove";
	// 多赔审批中
	public static final String STATUS_IN_OVERPAY_APPROVE = "InOverpayApprove";
	// 审批完成
	public static final String STATUS_APPROVED = "Approved";
	// 多赔审批完
	public static final String STATUS_OVERPAY_APPROVED = "OverpayApproved";
	// 冲账中
	public static final String STATUS_IN_BALANCE = "InBalance";
	// 冲账成功
	public static final String STATUS_BALANCE_FAILED = "BalanceFailed";
	// 冲账失败
	public static final String STATUS_BALANCED = "Balanced";
	// 付款中
	public static final String STATUS_IN_PAYMENT = "InPayment";
	// 付款失败
	public static final String STATUS_PAY_FAILED = "PayFailed";
	// 免赔处理
	public static final String STATUS_EXEMPTED = "Exempted";
	// 付款完成
	public static final String STATUS_PAID = "Paid";
	// 付款退回
	public static final String STATUS_PAYMENT_RETURNED = "PaymentReturned";

	// --------------------------------------------------------------------
	public static final String LIST_TYPE_ORIGINAL = "original";
	// 增加
	public static final String LIST_TYPE_ADD = "add";
	// 删除
	public static final String LIST_TYPE_DELETE = "delete";
	// 更新
	public static final String LIST_TYPE_UPDATE = "update";

	/*************** 工作流名称 BEGIN **************/
	public static final String WORKFLOW_CONTEXT = "context";

	// 理赔
	public static final String RECOMPENSE_WORKFLOW_NAME = "recompense";
	public static final String RECOMPENSE_APPLICATION = "recompenseApplication";
	public static final String RECOMPENSE_CURRENT_USER = "currentUser";
	public static final String RECOMPENSE_ISSUEITEM_MAP = "issueItemMap";
	public static final String RECOMPENSE_GOODSTRANS_MAP = "goodsTransMap";
	public static final String RECOMPENSE_ATTACHMENT_MAP = "attachmentMap";

	public static final String RECOMPENSE_DEPTCHARGE_MAP = "deptChargeMap";
	public static final String RECOMPENSE_RESPONSIBLEDEPT_MAP = "responsibleDeptMap";
	public static final String RECOMPENSE_MESSAGEREMINDER_MAP = "messageReminderMap";
	public static final String RECOMPENSE_AWARDITEM_MAP = "awardItemMap";
	public static final String RECOMPENSE_ADMIN_DEPT_LIST = "adminDeptList";

	public static final String RECOMPENSE_APPLICATION_ID = "appId";

	/*************** 工作流名称 END **************/

	/*************** 理赔模块业务 BEGIN **************/
	public static final String RECOMPENSE_LEAVE_DEPT = "leaveDept";
	public static final String RECOMPENSE_REPORT_DEPT = "reportDept";
	/*************** 理赔模块业务 END **************/
	/*************** 理赔模块工作流ACTION ID BEGIN **************/
	// 免赔处理
	public static final int RECOMPENSE_EXEMPT_ACTION = 9010;

	// 初始化
	public static final int RECOMPENSE_WORKFLOW_INIT = 100;
	// 创建
	public static final int RECOMPENSE_SUBMIT_ACTION = 210;
	// 理赔上报保存
	public static final int RECOMPENSE_REPORT_SAVE_ACTION = 220;
	// 删除上报理赔申请
	public static final int RECOMPENSE_REPORT_DELETE_ACTION = 230;
	// 拒绝在线理赔申請
	public static final int RECOMPENSE_REJECT_ONLINE_ACTION = 240;
	// 理赔专员资料确认
	public static final int RECOMPENSE_DOC_CONFIRM_ACTION = 250;
	// 理赔专员在线理赔审批通过
	public static final int RECOMPENSE_ONLINE_CONFIRM_ACTION = 260;
	// 理赔专员在线理赔审批退回
	public static final int RECOMPENSE_ONLINE_REJECT_ACTION = 270;
	// 在线理赔重新付款
	public static final int RECOMPENSE_ONLINE_REPAY_ACTION = 280;
	// 理赔专员资料确认退回
	public static final int RECOMPENSE_DOCUMENT_REJECT_ACTION = 310;
	// 理赔专员保存提交理赔处理
	public static final int RECOMPENSE_HANDLE_ACTION = 320;
	// 理赔专员初步处理退回
	public static final int RECOMPENSE_HANDLE_REJECT_ACTION = 330;
	// 业务经理理赔金额确认
	// public static final int RECOMPENSE_AMOUNT_CONFIRM_ACTION = 340;
	// 业务经理提交多赔申请
	public static final int RECOMPENSE_OVERPAY_SUBMIT_ACTION = 350;
	// 理赔专员金额确认退回
	public static final int RECOMPENSE_AMOUNT_CONFIRMED_REJECT_ACTION = 360;
	// 理赔专员金额确认后提交审批
	public static final int RECOMPENSE_AMOUNT_CONFIRMED_SUBMIT_ACTION = 370;
	// 理赔专员审批完成后多赔处理
	public static final int RECOMPENSE_OVERPAY_CONFIRM_ACTION = 380;
	// 理赔专员多賠處理退回
	public static final int RECOMPENSE_OVERPAY_REJECT_ACTION = 390;

	// 收银员提交冲账申请
	// public static final int RECOMPENSE_BALANCE_ACTION = 410;
	// 收银员提交付款申请
	public static final int RECOMPENSE_PAYMENT_ACTION = 420;
	// 取消快速理赔
	public static final int RECOMPENSE_FAST_CANCEL_ACTION = 430;
	// 理赔专员免赔处理
	// public static final int RECOMPENSE_APPROVED_EXEMPT_ACTION = 440;

	// OA工作流审核通过
	public static final int RECOMPENSE_OA_APPROVE_CONFIRM_ACTION = 510;
	// OA工作流审核驳回
	public static final int RECOMPENSE_OA_APPROVE_REJECT_ACTION = 520;
	// OA多赔工作流审核通过
	public static final int RECOMPENSE_OVERPAY_APPROVE_CONFIRM_ACTION = 530;
	// OA多赔工作流审核驳回
	public static final int RECOMPENSE_OVERPAY_APPROVE_REJECT_ACTION = 540;

	// ERP付款通过
	public static final int RECOMPENSE_PAYMENT_CONFIRM_ACTION = 610;
	// ERP付款失败
	public static final int RECOMPENSE_PAYMENT_REJECT_ACTION = 620;
	public static final String ALL = "ALL";

	public static final String ONLINE_PAYFAIL_TODO_MESSAGE = "贵部门有一个在线理赔(凭证号：{0})付款失败需要处理，请进行处理！";
	public static final String ONLINE_MODIFY_CARDNUMBER_TODO_MESSAGE = "贵部门有一个在线理赔(凭证号：{0})客户已修改账号，请进行处理！";

	// 公司
	public static final String ACCOUNT_PROPERTIES_PUBLIC = "PUBLIC_ACCOUNT";
	// 个人
	public static final String ACCOUNT_PROPERTIES_PRIVATE = "PRIVATE_ACCOUNT";
	// 收银员账号
	public static final String ACCOUNT_PROPERTIES_CASHIER_ACCOUNT = "BACKUP_ACCOUNT";
	// 汇款
	public static final String CUST_DROWMONEY_TYPE_REMIT = "REMIT";
	// 冲账后付现
	public static final String CUST_DROWMONEY_TYPE_CASH_AFTER_STRIKE_A_BALANCE = "CASH_AFTER_STRIKE_A_BALANCE";
	// 现金
	public static final String CUST_DROWMONEY_TYPE_CASH = "CASH";
	// 冲账后汇款
	public static final String CUST_DROWMONEY_TYPE_REMIT_AFTER_STRIKE_A_BALANCE = "REMIT_AFTER_STRIKE_A_BALANCE";
	// 全额冲账
	public static final String CUST_DROWMONEY_TYPE_ALL_STRIKE_A_BALANCE = "ALL_STRIKE_A_BALANCE";
	// 运输类型 空运 汽运

	public static final String TRANS_VEHICLE = "TRANS_VEHICLE"; // 汽运
	public static final String TRANS_VEHICLE_NAME = "汽运"; // 汽运
	public static final String TRANS_AIRCRAFT = "TRANS_AIRCRAFT"; // 空运
	public static final String TRANS_AIRCRAFT_NAME = "空运"; // 空运
	// TODO 待接口确认
	public static final String TRANS_EXPRESS = "TRANS_EXPRESS"; // 快递
	public static final String TRANS_EXPRESS_NAME = "快递"; // 快递

	// 理赔监控短信模板（未处理）
	public static final String MESSAGE_SUBMITED_CONTENT = "您好，运单号{0}由{1}上报理赔，截止目前{2}天仍处于未处理状态，请您协助跟进，尽快收齐并提交理赔资料至事业部理赔专员。（理赔研究小组）";
	// 理赔监控短信模板（初步处理），短信通知对象包含"区域经理"以上（包含区域经理）任一对象时，
	// 按照模板 MESSAGE_HANDLED_U_CONTENT 发送，否则按照模板 MESSAGE_HANDLED_D_CONTENT 发送
	public static final String MESSAGE_HANDLED_D_CONTENT = "您好，运单号{0}由{1}上报理赔，截止目前{2}天仍处于初步处理状态，请您协助跟进，及时与客户确定金额或通过升级处理机制进行处理。（理赔研究小组）";
	public static final String MESSAGE_HANDLED_U_CONTENT = "您好，运单号{0}由{1}上报理赔，截止目前{2}天仍处于初步处理状态，由于协商时间较长，根据升级处理机制的内容，请领导协助营业部处理。（理赔研究小组）";
	// 理赔监控短信模板（审批中）
	public static final String MESSAGE_INOAAPPROVE_CONTENT = "您好，运单号{0}由{1}上报理赔，截止目前{2}天仍处于审批中状态，请您及时快递理赔协议书（一式三份）至事业部印章管理员处并跟进理赔工作流审批进程。（理赔研究小组）";
	// 理赔监控短信模板（审批完成）
	public static final String MESSAGE_APPROVED_CONTENT = "您好，运单号{0}由{1}上报理赔，截止目前{2}天仍处于审批完成状态，为避免客户投诉，请您协助跟进，催促收银员及时申请付款并快递资料至部门审核会计处进行报销。（理赔研究小组）";
	// 理赔监控短信模板（付款中）
	public static final String MESSAGE_INPAYMENT_CONTENT = "您好，运单号{0}由{1}上报理赔，截止目前{2}天仍处于付款中状态，为避免客户投诉，请您协助跟进，催促收银员及时跟进付款工作流并快递资料至部门审核会计处进行报销。（理赔研究小组）";

	// 理赔监控短信模板（未处理）
	public static final String MESSAGE_SUBMITED_CONTENT_MORE = "您好，运单号***由***营业部上报理赔，截止目前***天仍处于未处理状态，请您协助跟进，尽快收齐并提交理赔资料至事业部理赔专员。（理赔研究小组）";
	// 理赔监控短信模板（初步处理），短信通知对象包含"区域经理"以上（包含区域经理）任一对象时，
	// 按照模板 MESSAGE_HANDLED_U_CONTENT 发送，否则按照模板 MESSAGE_HANDLED_D_CONTENT 发送
	public static final String MESSAGE_HANDLED_D_CONTENT_MORE = "您好，运单号***由***营业部上报理赔，截止目前***天仍处于初步处理状态，请您协助跟进，及时与客户确定金额或通过升级处理机制进行处理。（理赔研究小组）";
	public static final String MESSAGE_HANDLED_U_CONTENT_MORE = "您好，运单号***由***营业部上报理赔，截止目前***天仍处于初步处理状态，由于协商时间较长，根据升级处理机制的内容，请领导协助营业部处理。（理赔研究小组）";
	// 理赔监控短信模板（审批中）
	public static final String MESSAGE_INOAAPPROVE_CONTENT_MORE = "您好，运单号***由***营业部上报理赔，截止目前***天仍处于审批中状态，请您及时快递理赔协议书（一式三份）至事业部印章管理员处并跟进理赔工作流审批进程。（理赔研究小组）";
	// 理赔监控短信模板（审批完成）
	public static final String MESSAGE_APPROVED_CONTENT_MORE = "您好，运单号***由***营业部上报理赔，截止目前***天仍处于审批完成状态，为避免客户投诉，请您协助跟进，催促收银员及时申请付款并快递资料至部门审核会计处进行报销。（理赔研究小组）";
	// 理赔监控短信模板（付款中）
	public static final String MESSAGE_INPAYMENT_CONTENT_MORE = "您好，运单号***由***营业部上报理赔，截止目前***天仍处于付款中状态，为避免客户投诉，请您协助跟进，催促收银员及时跟进付款工作流并快递资料至部门审核会计处进行报销。（理赔研究小组）";

	// 理赔监控通知对象 （经理）
	public static final String MESSAGE_RECEIVER_MANAGER = "1";
	// 理赔监控通知对象 （理赔专员）
	public static final String MESSAGE_RECEIVER_COMMISSIONER = "2";
	// 理赔监控通知对象 （区域经理）
	public static final String MESSAGE_RECEIVER_AREAMANAGER = "3";
	// 理赔监控通知对象 （大区总经理）
	public static final String MESSAGE_RECEIVER_GENERALMANAGER = "4";
	// 理赔监控通知对象 （事业部办公室主任）
	public static final String MESSAGE_RECEIVER_DIRECTOR = "5";
	// 理赔监控通知对象 （事业部总裁）
	public static final String MESSAGE_RECEIVER_PRESIDENT = "6";
	// 在线理赔方 发货方
	public static final String ONLINECLAIMER_SEND = "send";
	// 在线理赔方 收货方
	public static final String ONLINECLAIMER_RECEIVE = "receive";
	//出发方
	public static final String CLAIMER_SEND = "1";
	//到达方
	public static final String CLAIMER_RECEIVE = "2";
	// 在线理赔监控通知对象 （经理）
	public static final String MESSAGE_RECEIVER_MANAGER_ONLINE = "1";
	// 在线理赔监控通知对象 （区域经理）
	public static final String MESSAGE_RECEIVER_AREAMANAGER__ONLINE = "2";
	// 在线理赔监控通知对象 （大区总经理）
	public static final String MESSAGE_RECEIVER_GENERALMANAGER__ONLINE = "3";
	public static final String RECOMPENSE_EXPRESS_POSITION_MANAGER = "点部经理";

}
