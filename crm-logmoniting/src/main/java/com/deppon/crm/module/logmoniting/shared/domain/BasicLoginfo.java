package com.deppon.crm.module.logmoniting.shared.domain;

import java.io.Serializable;

/**   
 * @Description:基本信息，主要维护log是否预警，已经时长，次数定义等<br />
 * @title BasicLoginfo.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-6-18
 */
public class BasicLoginfo implements Serializable{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 7884226346070389088L;
	private String id;// ID
    private long createDate;// 创建日期
    private String createUser;//创建人
    private long modifyDate;// 修改日期
    private String modifyUser;//修改人
	//模块名
	private String modulename;
	//Action名称
	private String actionName;
	//Action URI
	private String url;
	//是否启用预警
	private boolean invalid;
	//是否黑名单
	private boolean blackList;
	//请求时间
	private long baseTime;
	//请求时间浮动值
	private double timeFloat;
	//请求次数
	private long baseCount;
	//请求次数浮动值
	private double countFloat;
	//通知人员邮箱
	private String mailPerson;
	

	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Description:invalid<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public boolean isInvalid() {
		return invalid;
	}
	/**
	 * Description:invalid<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}
	/**
	 * Description:blackList<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public boolean isBlackList() {
		return blackList;
	}
	/**
	 * Description:blackList<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setBlackList(boolean blackList) {
		this.blackList = blackList;
	}
	/**
	 * Description:baseTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public long getBaseTime() {
		return baseTime;
	}
	
	/**
	 * Description:timeFloat<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public double getTimeFloat() {
		return timeFloat;
	}
	/**
	 * Description:timeFloat<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setTimeFloat(double timeFloat) {
		this.timeFloat = timeFloat;
	}
	/**
	 * Description:countFloat<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public double getCountFloat() {
		return countFloat;
	}
	/**
	 * Description:countFloat<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setCountFloat(double countFloat) {
		this.countFloat = countFloat;
	}
	/**
	 * Description:baseTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setBaseTime(long baseTime) {
		this.baseTime = baseTime;
	}
	/**
	 * Description:baseCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setBaseCount(long baseCount) {
		this.baseCount = baseCount;
	}
	/**
	 * Description:timeFloat<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setTimeFloat(long timeFloat) {
		this.timeFloat = timeFloat;
	}
	/**
	 * Description:baseCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public long getBaseCount() {
		return baseCount;
	}

	/**
	 * Description:mailPerson<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public String getMailPerson() {
		return mailPerson;
	}
	/**
	 * Description:mailPerson<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setMailPerson(String mailPerson) {
		this.mailPerson = mailPerson;
	}
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-6-25
	 */
	public String getId() {
		return id;
	}
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-6-25
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Description:createUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-25
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * Description:createUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-25
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/**
	 * Description:createDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-28
	 */
	public long getCreateDate() {
		return createDate;
	}
	/**
	 * Description:createDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-28
	 */
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	/**
	 * Description:modifyDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-28
	 */
	public long getModifyDate() {
		return modifyDate;
	}
	/**
	 * Description:modifyDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-28
	 */
	public void setModifyDate(long modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * Description:modifyUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-25
	 */
	public String getModifyUser() {
		return modifyUser;
	}
	/**
	 * Description:modifyUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-25
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
}
