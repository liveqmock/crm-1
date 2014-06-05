package com.deppon.crm.module.keycustomer.shared.domain;

/**
 * 
 * <p>
 * Description:大客户常量<br />
 * </p>
 * 
 * @title Constant.java
 * @package com.deppon.crm.module.keycustomer.shared.domain
 * @author 106138
 * @version 0.1 2014-3-7
 */ 
public class Constant {
	//1
	public final static String NUMONE = "1";
	//0
	public final static String NUMZERO = "0";
	// 已提交
	public static final String WORKFLOW_STATUS_IN_APPROVE = "1";
	// 已同意
	public static final String WORKFLOW_STATUS_AGREED = "2";
	// 未同意
	public static final String WORKFLOW_STATUS_UNAGREE = "3";
	//准入
	public static final String IN = "in";
	//退出
	public static final String OUT = "out";
	//邮件分组
	public static final String KEYCUSTOMERMAILGROUP = "keycustomer";
	//准入存储过程
	public static final String CREATEKEYINLIST = "sp_crm_createkeyinList";
	//准出存储过程
	public static final String CREATEKEYOUTLIST = "sp_crm_createkeyoutList";
	//删除大客户标记过程
	public static final String AUTODELETEKEYCUSTOMER = "sp_crm_autoDeleteKeyCustomer";

}
