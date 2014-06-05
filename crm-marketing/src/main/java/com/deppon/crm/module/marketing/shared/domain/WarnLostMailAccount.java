package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.crm.module.sysmail.shared.domain.MailAccount;

/**
 * 
 * <p>
 * 流失客户管理模块邮箱账号<br />
 * </p>
 * 
 * @title WarnLostMailAccount.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author zhangzhenwei
 * @version 0.1 2014-3-13
 */
public class WarnLostMailAccount extends MailAccount {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//添加部门账号的部门信息
	private String deptStandardCode;
	public String getDeptStandardCode() {
		return deptStandardCode;
	}
	public void setDeptStandardCode(String deptStandardCode) {
		this.deptStandardCode = deptStandardCode;
	}
}
