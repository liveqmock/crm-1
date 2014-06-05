package com.deppon.crm.module.recompense.shared.domain;

/**
 * 
 * <p>
 * Description:信息提醒<br />
 * </p>
 * @title MessageReminder.java
 * @package com.deppon.crm.module.recompense.shared.domain 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class MessageReminder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4947140860836568045L;
	//编号
	private String id;
	// 理赔单Id
	private String recompenseId;
	// 提醒方式
	private String reminderMethod;
	// 提醒人工号
	private String userNumber;
	// 提醒人姓名
	private String userName;
	// 提醒人手机号
	private String phoneNum;
	// 提醒人所在部门
	private String deptName;

	/**
	 * constructer
	 */
	public MessageReminder() {
		super();
	}

	/**
	 * constructer
	 * 
	 * @param recompenseId
	 * @param reminderMethod
	 * @param userNumber
	 * @param userName
	 * @param phoneNum
	 * @param deptName
	 */
	public MessageReminder(String recompenseId, String reminderMethod,
			String userNumber, String userName, String phoneNum, String deptName) {
		this.recompenseId = recompenseId;
		this.reminderMethod = reminderMethod;
		this.userNumber = userNumber;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.deptName = deptName;
	}

	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return recompenseId : return the property recompenseId.
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * @param recompenseId
	 *            : set the property recompenseId.
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	/**
	 * @return reminderMethod : return the property reminderMethod.
	 */
	public String getReminderMethod() {
		return reminderMethod;
	}

	/**
	 * @param reminderMethod
	 *            : set the property reminderMethod.
	 */
	public void setReminderMethod(String reminderMethod) {
		this.reminderMethod = reminderMethod;
	}

	/**
	 * @return userNumber : return the property userNumber.
	 */
	public String getUserNumber() {
		return userNumber;
	}

	/**
	 * @param userNumber
	 *            : set the property userNumber.
	 */
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * @return userName : return the property userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            : set the property userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return phoneNum : return the property phoneNum.
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum
	 *            : set the property phoneNum.
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return deptName : return the property deptName.
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            : set the property deptName.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
