package com.deppon.crm.module.duty.shared.domain;

/**
 * 
 * <p>
 * Description:通知对象<br />
 * </p>
 * @title ProcResult.java
 * @package com.deppon.crm.module.duty.shared.domain 
 * @author ouy
 * @version 0.1 2013-3-5
 */
public class InformUser {
	
	private String id;//id
	private String dutyId;//工单责任ID
	private String userName;//姓名
	private String userNo;//工号
	private String userPosition;//职位
	private String userContact;//联系方式
	private String deptId;//部门ID
	private String userId;//用户ID
	private String virtualCode;//划分结果虚拟编码
	private String deptName;//部门名称


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}
	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	/**
	 * @return the userPosition
	 */
	public String getUserPosition() {
		return userPosition;
	}
	/**
	 * @param userPosition the userPosition to set
	 */
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	/**
	 * @return the userContact
	 */
	public String getUserContact() {
		return userContact;
	}
	/**
	 * @param userContact the userContact to set
	 */
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	/**
	 * @return the dutyId
	 */
	public String getDutyId() {
		return dutyId;
	}
	/**
	 * @param dutyId the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the virtualCode
	 */
	public String getVirtualCode() {
		return virtualCode;
	}
	/**
	 * @param virtualCode the virtualCode to set
	 */
	public void setVirtualCode(String virtualCode) {
		this.virtualCode = virtualCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
