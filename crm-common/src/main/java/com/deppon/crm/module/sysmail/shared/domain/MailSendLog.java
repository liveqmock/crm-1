package com.deppon.crm.module.sysmail.shared.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p>
 * 邮件发送日志<br />
 * </p>
 * @title MailSendLog.java
 * @package com.deppon.crm.module.sysmail.shared.domain 
 * @author suyujun
 * @version 0.1 2013-9-25
 */
public class MailSendLog implements Serializable{
	private static final long serialVersionUID = 7732301125603362752L;
	private String id;
	private String address;
	private Date sendTime;
	private String moduleName;
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return address : return the property address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address : set the property address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return sendTime : return the property sendTime.
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime : set the property sendTime.
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * @return moduleName : return the property moduleName.
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName : set the property moduleName.
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
