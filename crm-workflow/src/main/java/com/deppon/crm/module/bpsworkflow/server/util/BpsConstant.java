package com.deppon.crm.module.bpsworkflow.server.util;



/**
 * <p>
 * Description:bps工作流常量<br />
 * </p>
 * 
 * @title BpsConstant.java
 * @package com.deppon.crm.module.customer.bpsworkflow.util
 * @author pgj
 * @version 0.1 2013-11-15
 */
public class BpsConstant {
	// 零担合同申请流程名称
	public static final String LTT_NEW_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppLTL";
	// 快递合同申请流程名称
	public static final String EX_NEW_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppExpress";
	// 零担合同修改流程名称
	public static final String LTT_UPDATE_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtLTL";
	// 快递合同修改流程名称
	public static final String EX_UPDATE_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtExpress";
	// 月结合同终止申请流程名称
	public static final String CANCEL_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustTermination";
	// 大客户准入保留申请流程名称
	public static final String KEYCUST_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.customer.customerAccessAndSave";
	// 疑似客户处理流程名称
	public static final String CUSTREPEAT_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.customer.repeatedCostomer";
	// 多赔流程定义名称
	public static final String OVERPAY_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation";
	//区域营销活动
	public static final String MARKETING_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.customer.areaMarketingActivities";
	// 多赔流程实例名称
	public static final String OVERPAT_PROCESS_INSTANCE_NORMALCLAIM = "多赔申请";
	// 多赔流程描述
	public static final String OVERPAY_PROCESS_DESCRIPTION = "多赔申请";
	// 服务补救流程定义名称
	public static String SERVICE_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery";
	// 服务补救流程实例名称
	public static final String SERVICE_PROCESS_INSTANCE_NORMALCLAIM = "服务补救申请";
	// 服务补救流程描述
	public static final String SERVICE_PROCESS_DESCRIPTION = "服务补救申请";
	//流程定义名称
	public static final String RECOMPENSE_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims";
	//流程实例名称
	public static final String PROCESS_INSTANCE_NORMALCLAIM = "常规理赔申请";
	//流程描述
	public static final String PROCESS_DESCRIPTION = "常规理赔申请";
	// 零担流程申请实例名称
	public static final String PROCESS_INSTANCE_LTT_NEW = "零担合同申请";
	// 零担申请流程描述
	public static final String PROCESS_INSTANCE_LTT_NEW_DESCRIPTION = "零担合同申请";
	// 零担流程申请实例名称
	public static final String PROCESS_INSTANCE_EX_NEW = "快递合同申请";
	// 零担申请流程描述
	public static final String PROCESS_INSTANCE_EX_NEW_DESCRIPTION = "快递合同申请";
	// 快递流程申请实例名称
	public static final String PROCESS_INSTANCE_LTT_UPADTE = "零担合同修改";
	// 快递申请流程描述
	public static final String PROCESS_INSTANCE_LTT_UPDATE_DESCRIPTION = "零担合同修改";
	// 快递流程申请实例名称
	public static final String PROCESS_INSTANCE_EX_UPADTE = "快递合同修改";
	// 快递申请流程描述
	public static final String PROCESS_INSTANCE_LTT_UPADTE_DESCRIPTION = "快递合同修改";
	// 月结合同终止申请实例名称
	public static final String PROCESS_INSTANCE_CANCEL = "月结合同终止申请";
	// 月结合同终止申请流程描述
	public static final String PROCESS_INSTANCE_CANCEL_DESCRIPTION = "月结合同终止申请";
	// 大客户准入保留申请实例名称
	public static final String PROCESS_KEYCUST = "大客户准入保留申请";
	// 大客户准入保留申请流程描述
	public static final String PROCESS_KEYCUST_DESCRIPTION = "大客户准入保留申请";
	// 疑似客户处理实例名称
	public static final String PROCESS_CUSTREPEAT = "疑似客户处理";
	// 疑似客户处理流程描述
	public static final String PROCESS_CUSTREPEAT_DESCRIPTION = "疑似客户处理";
	// 常规理赔申请实例名称
	public static final String PROCESS_RECOMPENSE = "常规理赔申请";
	// 常规理赔申请流程描述
	public static final String PROCESS_RECOMPENSE_DESCRIPTION = "常规理赔申请";
	// 服务补救申请实例名称
	public static final String PROCESS_RECOVERY = "服务补救申请";
	// 服务补救申请流程描述
	public static final String PROCESS_RECOVERY_DESCRIPTION = "服务补救申请";
	// 多赔申请实例名称
	public static final String PROCESS_OVERPAY = "多赔申请";
	// 多赔申请流程描述
	public static final String PROCESS_OVERPAY_DESCRIPTION = "多赔申请";
	// 区域营销活动实例名称
	public static final String PROCESS_AREA_MARKETING = "区域营销活动";
	// 区域营销活动流程描述
	public static final String PROCESS_AREA_MARKETING_DESCRIPTION = "区域营销活动";
	//开始时间
	public static final String STARTTIME = " 00:00:00";
	//结束时间
	public static final String ENDTIME = " 23:59:59";
	//没有工作流
	public static final String NOWORKFLOW = "NOWORKFLOW";
	// 流程业务编码(18位且以4位系统编码开头)
	public static final String CRM_BIZ_CODE = "ICRM";
	// 工作流加密解密key
	public static final String WORKFLOW_DESC_KEY = "deppon_oa";
	// 零担合同申请流程名称
	public static final String CONTRACT_LTT_NEW_SERACH = "CONTRACT_LTT_NEW";
	// 快递合同申请流程名称
	public static final String CONTRACT_EX_NEW_SERACH = "CONTRACT_EX_NEW";
	// 零担合同修改流程名称
	public static final String CONTRACT_LTT_UPDATE_SERACH = "CONTRACT_LTT_UPDATE";
	// 快递合同修改流程名称
	public static final String CONTRACT_EX_UPDATE_SERACHE = "CONTRACT_EX_UPDATE";
	// 月结合同终止申请流程名称
	public static final String CONTRACT_CANCEL_SEARCH = "CONTRACT_CANCEL";
	// 常规理赔申请流程名称
	public static final String RECOMPENSE_APPLY_SEARCH = "RECOMPENSE_APPLY";
	// 服务补救申请流程名称
	public static final String RECOVERY_APPLY_SEARCH = "RECOVERY_APPLY";
	// 多赔申请流程名称
	public static final String OVERPAY_APPLY_SEARCH = "OVERPAY_APPLY";
	// 大客户申请流程名称
	public static final String KEYCUST_APPLY_SEARCH = "KEYCUST_APPLY";
	// 疑似重复客户处理流程名称
	public static final String CUSTREPEAT_PROCESS_SEARCH = "CUSTREPEAT_PROCESS";
	// 区域营销活动流程名称
	public static final String AREA_MARKETING_SEARCH = "AREA_MARKETING";
	// 事业部合同管理专员
	public static final String CONTRACT_MANAGER = "合同管理专员";
	// 事业部标杆编码
	public static final String WFS_CONTRACTAPP_GROUP = "WFS_CONTRACTAPP_GROUP";
}
