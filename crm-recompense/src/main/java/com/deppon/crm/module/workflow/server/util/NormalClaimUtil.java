package com.deppon.crm.module.workflow.server.util;

/**
 * 理赔工作流
 * @author andy
 * @version 0.1 2013-8-2上午11:11:05
 */
public class NormalClaimUtil {
	//流程定义名称
	public static final String PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims";
	
	//流程实例名称
	public static final String PROCESS_INSTANCE_NORMALCLAIM = "常规理赔申请";
	
	//流程描述
	public static final String PROCESS_DESCRIPTION = "常规理赔申请";
	
	//流程业务编码(18位且以4位系统编码开头)
	public static final String CRM_BIZ_CODE = "ICRM";
	
	// 附件表sourceType
	public static final String WORKFLOW_SOURCE_TYPE = "WORKFLOW";
	
	// 向快递业务管理部负责人发送短信
	public static final String EXPRESSDELIVERY_MSGCONTENT = 
			"领导您好，单号{0}的索赔处理已经完成，理赔类型{1}，理赔金额{2}元，请您查阅！";
	
	public static final String OVERPAY_EXPRESSDELIVERY_MSGCONTENT = 
			"领导您好，单号{0}的多赔处理已经完成，理赔类型{1}，理赔金额{2}元，其中多赔金额{3}元，请您查阅！";
	
	//工作流加密解密key
	public static final String WORKFLOW_DESC_KEY="deppon_oa";
	
	
	
	//多赔流程定义名称
	public static String OVERPAY_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation";
	
	//多赔流程实例名称
	public static final String OVERPAT_PROCESS_INSTANCE_NORMALCLAIM = "多赔申请";
	
	//多赔流程描述
	public static final String OVERPAY_PROCESS_DESCRIPTION = "多赔申请";
	
	
	
	//服务补救流程定义名称
	public static String SERVICE_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery";
	
	//服务补救流程实例名称
	public static final String SERVICE_PROCESS_INSTANCE_NORMALCLAIM = "服务补救申请";
	
	//服务补救流程描述
	public static final String SERVICE_PROCESS_DESCRIPTION = "服务补救申请";
}
