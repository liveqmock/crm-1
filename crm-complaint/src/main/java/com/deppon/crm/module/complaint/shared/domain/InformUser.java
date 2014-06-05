package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class InformUser extends BaseEntity{
	//工单责任划分结果ID
	private String dutyResId;
	//姓名
	private String userName;
	//工号
	private String userNo;
	//职位
	private String userPosition;
	//联系方式
	private String userContact;
	//部门ID
	private String deptId;
	//用户ID
	private String userId;
	public String getDutyResId() {
		return dutyResId;
	}
	public void setDutyResId(String dutyResId) {
		this.dutyResId = dutyResId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	public String getUserContact() {
		return userContact;
	}
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
