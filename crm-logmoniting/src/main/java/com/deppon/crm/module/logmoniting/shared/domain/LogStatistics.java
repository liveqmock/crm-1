package com.deppon.crm.module.logmoniting.shared.domain;

import java.io.Serializable;

/**   
 * @Description:T_LOG_BASESTASTICS	LOG信息统计，
 * 用于记录每一个请求的每一小时的平均请求时间和请求时常<br />
 * @title BaseStatics.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-6-18
 */
public class LogStatistics implements Serializable{
	
   /** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = -2019679378740722730L;
	
    private String id;// ID
    private long createDate;// 创建日期
    private String createUser;//创建人
    private long modifyDate;// 修改日期
    private String modifyUser;//修改人
    
	//对应基础log信息，表之间关联 actionId
	private String baseLoginfoId; 
	//action--url
	private String url;
	//模块名
	private String modulename;
	//年
	private int year;
	//月
	private int month;
	//日
	private int day;
	//时
	private int hour;
	//请求时间统计  平均时间（默认1小时）
	private long avgRequestTime;
	//请求次数统计  总次数 （默认1小时）
	private long requestCount;
	//一小时请求的总时间
	private long requestTimeCount;
	//请求最长时长 针对年月日时统计该数据
	private long maxRequestTime;
	
	/**
	 * Description:longestRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public long getMaxRequestTime() {
		return maxRequestTime;
	}
	/**
	 * Description:longestRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public void setMaxRequestTime(long maxRequestTime) {
		this.maxRequestTime = maxRequestTime;
	}
	/**
	 * Description:year<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Description:year<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * Description:month<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * Description:month<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * Description:day<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public int getDay() {
		return day;
	}
	/**
	 * Description:day<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * Description:hour<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * Description:hour<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	/**
	 * Description:requestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public long getAvgRequestTime() {
		return avgRequestTime;
	}
	/**
	 * Description:requestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setAvgRequestTime(long avgRequestTime) {
		this.avgRequestTime = avgRequestTime;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public long getRequestCount() {
		return requestCount;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}

	/**
	 * Description:baseLoginfoId<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 */
	public String getBaseLoginfoId() {
		return baseLoginfoId;
	}
	/**
	 * Description:baseLoginfoId<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 */
	public void setBaseLoginfoId(String baseLoginfoId) {
		this.baseLoginfoId = baseLoginfoId;
	}
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public String getId() {
		return id;
	}
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Description:createUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * Description:createUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
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
	 * @version 0.1 2013-6-26
	 */
	public String getModifyUser() {
		return modifyUser;
	}
	/**
	 * Description:modifyUser<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-7-24
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-7-24
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	/**
	 * Description:requestTimeCount<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public long getRequestTimeCount() {
		return requestTimeCount;
	}
	/**
	 * Description:requestTimeCount<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setRequestTimeCount(long requestTimeCount) {
		this.requestTimeCount = requestTimeCount;
	}
}
