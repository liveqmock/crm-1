package com.deppon.crm.module.sysmail.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * 邮件接收人——邮箱账号<br />
 * </p>
 * 
 * @title MailAccount.java
 * @package com.deppon.crm.module.sysmail.shared.domain
 * @author suyujun
 * @version 0.1 2013-9-18
 */
public class MailAccount extends BaseEntity {
	private static final long serialVersionUID = -1234666551776297850L;
	private String receiverName;
	private String emailAddress;
	// true 有效 false 无效
	private boolean isValid;
	private String empCode;

	/**
	 * @return receiverName : return the property receiverName.
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName
	 *            : set the property receiverName.
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return emailAddress : return the property emailAddress.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            : set the property emailAddress.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return isValid : return the property isValid.
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 *            : set the property isValid.
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return empCode : return the property empCode.
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            : set the property empCode.
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

}
