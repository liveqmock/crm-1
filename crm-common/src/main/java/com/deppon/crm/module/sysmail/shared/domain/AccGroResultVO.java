package com.deppon.crm.module.sysmail.shared.domain;


/**
 * 
 * <p>
 * 账号查询VO<br />
 * </p>
 * @title AccGroResultVo.java
 * @package com.deppon.crm.module.sysmail.shared.domain 
 * @author suyujun
 * @version 0.1 2013-9-24
 */
public class AccGroResultVO {
	private String accountId = "";
	private String receiverName = "";
	private String emailAddress = "";
	private String empCode = "";
	private String groupId = "";
	private String groupName = "";
	private String relationId = "";
	/**
	 * @return receiverName : return the property receiverName.
	 */
	public String getReceiverName() {
		return receiverName;
	}
	/**
	 * @param receiverName : set the property receiverName.
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	/**
	 * @return empCode : return the property empCode.
	 */
	public String getEmpCode() {
		return empCode;
	}
	/**
	 * @param empCode : set the property empCode.
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	/**
	 * @return accountId : return the property accountId.
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId : set the property accountId.
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return emailAddress : return the property emailAddress.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress : set the property emailAddress.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return groupId : return the property groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId : set the property groupId.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return groupName : return the property groupName.
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName : set the property groupName.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return relationId : return the property relationId.
	 */
	public String getRelationId() {
		return relationId;
	}
	/**
	 * @param relationId : set the property relationId.
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
}
