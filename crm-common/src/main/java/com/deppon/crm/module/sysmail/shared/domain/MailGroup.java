package com.deppon.crm.module.sysmail.shared.domain;

import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 邮件组管理<br />
 * </p>
 * @title MailGroup.java
 * @package com.deppon.crm.module.sysmail.shared.domain 
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public class MailGroup extends BaseEntity {
	private static final long serialVersionUID = -3880476032597734155L;
	private String groupName;
	private String groupCode;
	//private List<MailAccount> accounts;
	/**
	 * @return groupName : return the property groupName.
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            : set the property groupName.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return groupCode : return the property groupCode.
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @param groupCode
	 *            : set the property groupCode.
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @return accounts : return the property accounts.
	 */
//	public List<MailAccount> getAccounts() {
//		return accounts;
//	}

	/**
	 * @param accounts : set the property accounts.
	 */
//	public void setAccounts(List<MailAccount> accounts) {
//		this.accounts = accounts;
//	}
	
}
