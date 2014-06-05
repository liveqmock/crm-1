package com.deppon.crm.module.logmoniting.shared.domain;

import java.util.Date;

/**   
 * @Description:logInfo 查询条件<br />
 * @title LogInfoCondition.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-6-18
 */
public class LogInfoCondition {

	//action -id
	private String baseLoginfoId; 
	//模块名
	private String modulename;
	//uri 请求名称
	private String uri;
	//action名称
	private String actionName;
	//统计开始时间
	private Date beginDate;
	//统计结束时间
	private Date endDate;
	/**
	 * 统计频率 年 月 日 时
	 */
	private String statisticalFrequency;
	private int year;
	private int month;
	private int day;
	private int hour;
	/**
	 * 综合查询使用
	 */
	private int beginhour; //小时的开始
	private int endhour;//小时的结尾
	private int beginday;//天数的开始
	private int endday;//天数的结尾
	private int beginmonth;//月的开始
	private int endmonth;//月的结尾
	/**
	 * 统计方式--请求时间统计（requestTime）/请求次数统计 (requestCount)
	 */
	private String statisticalMethods;
	//级别
	private int level;
	
	//分页
	private int start;//
	private int limit;//
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
	 * Description:uri<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * Description:uri<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * Description:beginDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * Description:hour<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * Description:hour<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * Description:beginDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * Description:endDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Description:endDate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Description:level<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * Description:level<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * Description:statisticalFrequency<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public String getStatisticalFrequency() {
		return statisticalFrequency;
	}
	/**
	 * Description:statisticalFrequency<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public void setStatisticalFrequency(String statisticalFrequency) {
		this.statisticalFrequency = statisticalFrequency;
	}
	/**
	 * Description:year<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Description:year<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * Description:month<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Description:beginmonth<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 */
	public int getBeginmonth() {
		return beginmonth;
	}
	/**
	 * Description:beginmonth<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 */
	public void setBeginmonth(int beginmonth) {
		this.beginmonth = beginmonth;
	}
	/**
	 * Description:endmonth<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 */
	public int getEndmonth() {
		return endmonth;
	}
	/**
	 * Description:endmonth<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 */
	public void setEndmonth(int endmonth) {
		this.endmonth = endmonth;
	}
	/**
	 * Description:month<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * Description:day<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public int getDay() {
		return day;
	}
	/**
	 * Description:day<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * Description:statisticalMethods<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public String getStatisticalMethods() {
		return statisticalMethods;
	}
	/**
	 * Description:statisticalMethods<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 */
	public void setStatisticalMethods(String statisticalMethods) {
		this.statisticalMethods = statisticalMethods;
	}
	
	/**
	 * Description:beginhour<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public int getBeginhour() {
		return beginhour;
	}
	/**
	 * Description:beginhour<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public void setBeginhour(int beginhour) {
		this.beginhour = beginhour;
	}
	/**
	 * Description:endhour<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public int getEndhour() {
		return endhour;
	}
	/**
	 * Description:endhour<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public void setEndhour(int endhour) {
		this.endhour = endhour;
	}
	/**
	 * Description:beginday<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public int getBeginday() {
		return beginday;
	}
	/**
	 * Description:beginday<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public void setBeginday(int beginday) {
		this.beginday = beginday;
	}
	/**
	 * Description:endday<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public int getEndday() {
		return endday;
	}
	/**
	 * Description:endday<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public void setEndday(int endday) {
		this.endday = endday;
	}
	/**
	 * Description:baseLoginfoId<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public String getBaseLoginfoId() {
		return baseLoginfoId;
	}
	/**
	 * Description:baseLoginfoId<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public void setBaseLoginfoId(String baseLoginfoId) {
		this.baseLoginfoId = baseLoginfoId;
	}
	/**
	 * Description:start<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 */
	public int getStart() {
		return start;
	}
	/**
	 * Description:start<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * Description:limit<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * Description:limit<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
