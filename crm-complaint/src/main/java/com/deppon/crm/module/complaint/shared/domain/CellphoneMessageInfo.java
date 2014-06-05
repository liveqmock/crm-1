package com.deppon.crm.module.complaint.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 通知对象短信通知临时表
 * @author 邢悦
 *
 */
public class CellphoneMessageInfo extends BaseEntity {

	//对象电话号码
	private String phoneNumber;
	
	//短信内容
	private String msgContent;
	
	//部门标杆编码
	private String sendStandardDeptCode;
	
	//发信人的员工号(EmployeeCode)
	private String senderEmpCode;
	
	//是否已经被发出
	private Integer isSended;

	//每次发送的短信数目（非持久化字段）
	private Integer perCount;
	// phoneNumber get方法
	public String getPhoneNumber() {
		return phoneNumber;
	}
	// phoneNumber set方法
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	// msgContent get方法
	public String getMsgContent() {
		return msgContent;
	}
	// msgContent set方法
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	// senderEmpCode get方法
	public String getSenderEmpCode() {
		return senderEmpCode;
	}
	// senderEmpCode set方法
	public void setSenderEmpCode(String senderEmpCode) {
		this.senderEmpCode = senderEmpCode;
	}
	// sendStandardDeptCode get方法
	public String getSendStandardDeptCode() {
		return sendStandardDeptCode;
	}
	// sendStandardDeptCode set方法
	public void setSendStandardDeptCode(String sendStandardDeptCode) {
		this.sendStandardDeptCode = sendStandardDeptCode;
	}
	// isSended get方法
	public Integer getIsSended() {
		return isSended;
	}
	// isSended set方法
	public void setIsSended(Integer isSended) {
		this.isSended = isSended;
	}
	// perCount get方法
	public Integer getPerCount() {
		return perCount;
	}
	// perCount set方法
	public void setPerCount(Integer perCount) {
		this.perCount = perCount;
	}


	
	
	
	
}
