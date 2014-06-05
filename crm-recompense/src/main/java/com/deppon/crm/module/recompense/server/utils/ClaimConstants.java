package com.deppon.crm.module.recompense.server.utils;

public class ClaimConstants {
	// 运输性质
	// 偏线
	public static final String AGENT_VEHICLE = "偏线";
	// 空运
	public static final String DISPATCH_AIR = "空运";


	// 索赔状态
	// 待处理
	public static final String CLAIM_STATUS_WAIT_DO = "WAIT_DO"; //seq 1
	// 待处理
	public static final String CLAIM_STATUS_WAIT_ACCEPT = "WAIT_ACCEPT"; // seq 2
	// 已处理
	public static final String CLAIM_STATUS_HANDLED = "HANDLED"; // seq 3
	// 已免赔
	public static final String CLAIM_STATUS_REMITTED = "REMITTED";  // seq 4
	
	//理赔研究小组部门标杆编码
	public static final String RECOMPENSEGROUPCODE="DP08840";
	
	//发货方 
	public static String SHIPPER="SHIPPER";
	//收货方
	public static String CONSIGNEE="CONSIGNEE";
	//短信内容
	public static final String MESEAGE_CONTENT="您有一条{0}发送给您的凭证号为{1}的索赔信息待受理，请尽快通过CRM核实处理！";
	//代办
	public static final String TODO_MESSAGE="您有一条凭证号为{0}的索赔信息待受理，请及时核实处理！";
	//发送操作日志
	public static final String SEND_CLAIM_OPERATION_LOG = "凭证号为{0}索赔由{1}发送给{2},原因为:{3}.";
	//免赔操作日志
	public static final String REMIT_CLAIM_OPERATION_LOG = "凭证号为{0}的索赔由{1}进行免赔操作";
	//免赔解冻操作日志
	public static final String CANCEL_REMIT_CLAIM_OPERATION_LOG = "凭证号为{0}的索赔由{1}进行免赔解冻操作.";
	

	

}
