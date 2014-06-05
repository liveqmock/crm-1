/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BpsConstant.java
 * @package com.deppon.crm.module.customer.bpsworkflow.util 
 * @author pgj
 * @version 0.1 2013-11-15
 */
package com.deppon.crm.module.customer.bpsworkflow.util;

/**   
 * <p>
 * Description:bps工作流常量<br />
 * </p>
 * @title BpsConstant.java
 * @package com.deppon.crm.module.customer.bpsworkflow.util 
 * @author pgj
 * @version 0.1 2013-11-15
 */

public class BpsConstant {
	//零担合同申请流程名称
	public static final String LTT_NEW_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppLTL";
	//快递合同申请流程名称
	public static final String EX_NEW_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignAppExpress";
	//零担合同修改流程名称
	public static final String LTT_UPDATE_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtLTL";
	//快递合同修改流程名称
	public static final String EX_UPDATE_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustSignEidtExpress";
	// 月结合同终止申请流程名称
	public static final String CANCEL_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.administLegal.monCustTermination";
	//大客户准入保留工作流
	public static final String KEYCUSTOMER_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.customer.customerAccessAndSave";
	public static final String REPEATEDCUST_PROCESSNAME = "com.deppon.bpms.module.crm.bpsdesign.customer.repeatedCostomer";
	
	//零担流程申请实例名称
	public static final String PROCESS_INSTANCE_LTT_NEW = "零担月结合同签订申请";
	
	//零担申请流程描述
	public static final String PROCESS_INSTANCE_LTT_NEW_DESCRIPTION = "零担月结合同签订申请";
	
	//零担流程申请实例名称
	public static final String PROCESS_INSTANCE_EX_NEW = "快递月结合同签订申请";
	
	//零担申请流程描述
	public static final String PROCESS_INSTANCE_EX_NEW_DESCRIPTION = "快递月结合同签订申请";
	
	//快递流程申请实例名称
	public static final String PROCESS_INSTANCE_LTT_UPADTE = "零担月结合同修改";
	
	//快递申请流程描述
	public static final String PROCESS_INSTANCE_LTT_UPDATE_DESCRIPTION = "零担月结合同修改";
	
	//快递流程申请实例名称
	public static final String PROCESS_INSTANCE_EX_UPADTE = "快递月结合同修改";
	
	//快递申请流程描述
	public static final String PROCESS_INSTANCE_LTT_UPADTE_DESCRIPTION = "快递月结合同修改";
	
	//月结合同终止申请实例名称
	public static final String PROCESS_INSTANCE_CANCEL ="月结合同终止申请";
	//月结合同终止申请流程描述
    public static final String PROCESS_INSTANCE_CANCEL_DESCRIPTION = "月结合同终止申请";
	//快递流程申请实例名称
	public static final String PROCESS_INSTANCE_KEYCUSTOMER = "大客户准入保留申请";
	
	//快递申请流程描述
	public static final String PROCESS_INSTANC_EKEYCUSTOMER_DESCRIPTION = "大客户准入保留申请";
	
	public static final String PROCESS_REPEATEDCUST_DESTORY= "疑似重复客户作废申请";
	
	
	public static final String STARTTIME = " 00:00:00";
	public static final String ENDTIME = " 23:59:59";

	
	//流程业务编码(18位且以4位系统编码开头)
	public static final String CRM_BIZ_CODE = "ICRM";

	//工作流加密解密key
	public static final String WORKFLOW_DESC_KEY="deppon_oa";
	
	//零担合同申请流程名称
		public static final String LTT_NEW_SERACH = "LTT_NEW";
		//快递合同申请流程名称
		public static final String EX_NEW_SERACH = "EX_NEW";
		//零担合同修改流程名称
		public static final String LTT_UPDATE_SERACH = "LTT_UPDATE";
		//快递合同修改流程名称
		public static final String EX_UPDATE_SERACHE ="EX_UPDATE"; 
		//月结合同终止申请流程名称
		public static final String CANCEL_SEARCH = "CANCEL";
		//事业部合同管理专员
		public static final String CONTRACT_MANAGER="合同管理专员";
		//事业部标杆编码
		public static final String WFS_CONTRACTAPP_GROUP="WFS_CONTRACTAPP_GROUP";
}
