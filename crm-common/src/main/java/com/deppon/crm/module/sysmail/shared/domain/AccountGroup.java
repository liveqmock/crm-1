package com.deppon.crm.module.sysmail.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 邮件账号——邮件组 配置关系类<br />
 * </p>
 * @title AccountGroup.java
 * @package com.deppon.crm.module.sysmail.shared.domain 
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public class AccountGroup extends BaseEntity {
	private static final long serialVersionUID = -2680373079802894472L;
	private String mailAccountId;
	private String mailGroupId;

	/**
	 * @return mailAccountId : return the property mailAccountId.
	 */
	public String getMailAccountId() {
		return mailAccountId;
	}

	/**
	 * @param mailAccountId
	 *            : set the property mailAccountId.
	 */
	public void setMailAccountId(String mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	/**
	 * @return mailGroupId : return the property mailGroupId.
	 */
	public String getMailGroupId() {
		return mailGroupId;
	}

	/**
	 * @param mailGroupId
	 *            : set the property mailGroupId.
	 */
	public void setMailGroupId(String mailGroupId) {
		this.mailGroupId = mailGroupId;
	}

}
