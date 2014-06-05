/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BpsConstant.java
 * @package com.deppon.crm.module.customer.bpsworkflow.util 
 * @author pgj
 * @version 0.1 2013-11-15
 */
package com.deppon.crm.module.bps.server.util;

/**   
 * <p>
 * Description:bps工作流常量<br />
 * </p>
 * @title BpsConstant.java
 * @package com.deppon.crm.module.customer.bpsworkflow.util 
 * @author pgj
 * @version 0.1 2013-11-15
 */

public class Constant {
	//零担合同申请流程名称
	public static final String LTT_NEW_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppLTL";
	//快递合同申请流程名称
	public static final String EX_NEW_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppExpress";
	//零担合同修改流程名称
	public static final String LTT_UPDATE_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtLTL";
	//快递合同修改流程名称
	public static final String EX_UPDATE_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtExpress";
	
	
	//零担流程申请实例名称
	public static final String PROCESS_INSTANCE_LTT_NEW = "零担合同申请";
	
	//零担申请流程描述
	public static final String PROCESS_INSTANCE_LTT_NEW_DESCRIPTION = "零担合同申请";
	
	//零担流程申请实例名称
	public static final String PROCESS_INSTANCE_EX_NEW = "快递合同新签申请";
	
	//零担申请流程描述
	public static final String PROCESS_INSTANCE_EX_NEW_DESCRIPTION = "快递合同新签申请";
	
	//快递流程申请实例名称
	public static final String PROCESS_INSTANCE_LTT_UPADTE = "零担合同修改";
	
	//快递申请流程描述
	public static final String PROCESS_INSTANCE_LTT_UPDATE_DESCRIPTION = "零担合同新签修改";
	
	//快递流程申请实例名称
	public static final String PROCESS_INSTANCE_EX_UPADTE = "快递合同新签修改";
	
	//快递申请流程描述
	public static final String PROCESS_INSTANCE_LTT_UPADTE_DESCRIPTION = "快递合同新签修改";
	
	
	public static final String STARTTIME = " 00:00:00";
	public static final String ENDTIME = " 23:59:59";

	
	//流程业务编码(18位且以4位系统编码开头)
	public static final String CRM_BIZ_CODE = "ICRM";
	
	// 附件表sourceType
	public static final String WORKFLOW_SOURCE_TYPE = "WORKFLOW";
	
	//工作流加密解密key
	public static final String WORKFLOW_DESC_KEY="deppon_oa";
	public static final String CLAIMS_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims";
	public static final String SERVICERECOVERY_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery";
	public static final String OVERPAY_PROCESS_DEFINITION_NAME = "com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation";

}
