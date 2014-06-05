package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:提供给发送邮箱的actionInfo信息<br />
 * @title ActionLogInfo.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-6-29
 */
public class ActionLogInfo {

	//模块名
	private String modulename;
	//Action名称
	private String actionName;
	//Action URI
	private String url;
	//基础设置请求时间
	private long baseTime;
	//基础设置请求时间浮动值
	private double timeFloat;
	//基础设置请求次数
	private long baseCount;
	//基础设置请求次数浮动值
	private double countFloat;
	//邮箱
	private String mailPerson;
	
	//请求时间统计  平均时间（1小时）
	private long newAvgRequestTime;
	//请求次数统计  总次数 （1小时）
	private long newRequestCount;
	//请求最长时长 针对年月日时统计该数据
	private long newLongestRequestTime;
	
	
	//请求时间统计  平均时间（1小时）
	private long oldAvgRequestTime;
	//请求次数统计  总次数 （1小时）
	private long oldRequestCount;
	//请求最长时长 针对年月日时统计该数据
	private long oldLongestRequestTime;
	
	
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Description:baseTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public long getBaseTime() {
		return baseTime;
	}
	/**
	 * Description:baseTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setBaseTime(long baseTime) {
		this.baseTime = baseTime;
	}

	/**
	 * Description:baseCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public long getBaseCount() {
		return baseCount;
	}
	/**
	 * Description:baseCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setBaseCount(long baseCount) {
		this.baseCount = baseCount;
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
	 * Description:newRequestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public long getNewRequestCount() {
		return newRequestCount;
	}
	/**
	 * Description:newRequestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setNewRequestCount(long newRequestCount) {
		this.newRequestCount = newRequestCount;
	}
	/**
	 * Description:newLongestRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public long getNewLongestRequestTime() {
		return newLongestRequestTime;
	}
	/**
	 * Description:newLongestRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setNewLongestRequestTime(long newLongestRequestTime) {
		this.newLongestRequestTime = newLongestRequestTime;
	}
	/**
	 * Description:newAvgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public long getNewAvgRequestTime() {
		return newAvgRequestTime;
	}
	/**
	 * Description:newAvgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setNewAvgRequestTime(long newAvgRequestTime) {
		this.newAvgRequestTime = newAvgRequestTime;
	}
	/**
	 * Description:oldAvgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public long getOldAvgRequestTime() {
		return oldAvgRequestTime;
	}
	/**
	 * Description:oldAvgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setOldAvgRequestTime(long oldAvgRequestTime) {
		this.oldAvgRequestTime = oldAvgRequestTime;
	}
	/**
	 * Description:oldRequestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public long getOldRequestCount() {
		return oldRequestCount;
	}
	/**
	 * Description:oldRequestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setOldRequestCount(long oldRequestCount) {
		this.oldRequestCount = oldRequestCount;
	}
	/**
	 * Description:oldLongestRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public long getOldLongestRequestTime() {
		return oldLongestRequestTime;
	}
	/**
	 * Description:oldLongestRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 */
	public void setOldLongestRequestTime(long oldLongestRequestTime) {
		this.oldLongestRequestTime = oldLongestRequestTime;
	}
	/**
	 * Description:mailPerson<br />
	 * @author CoCo
	 * @version 0.1 2013-7-13
	 */
	public String getMailPerson() {
		return mailPerson;
	}
	/**
	 * Description:mailPerson<br />
	 * @author CoCo
	 * @version 0.1 2013-7-13
	 */
	public void setMailPerson(String mailPerson) {
		this.mailPerson = mailPerson;
	}
}
