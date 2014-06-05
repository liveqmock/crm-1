package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:提供给定时器使用的实体。一天使用一次
 * 求出每个action统计信息的平均时长，平均请求次数，
 * 和最大请求时间-存放中间reqLogInfo_averageCount
 * <br />
 * @title ReqLogInfo_averageCount.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-7-19
 */
public class ReqLogInfo_averageCount {
	// key--reqLogInfo_count 表
	private String id;
	// value值 reqLogInfo_count 表
	private long count;
	// 请求次数统计 总次数 （1小时）
	private long requestCount;
	// 请求最长时长 针对年月日时统计该数据
	private long maxRequestTime;
	// 请求时间统计 平均时间（1小时）
	private long avgRequestTime;
	//总次数
	private long requestTimeCount;
	// 模块名
	private String modulename;
	// url
	private String url;
	
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public String getId() {
		return id;
	}
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Description:count<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public long getCount() {
		return count;
	}
	/**
	 * Description:count<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setCount(long count) {
		this.count = count;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public long getRequestCount() {
		return requestCount;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}
	/**
	 * Description:maxRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public long getMaxRequestTime() {
		return maxRequestTime;
	}
	/**
	 * Description:maxRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setMaxRequestTime(long maxRequestTime) {
		this.maxRequestTime = maxRequestTime;
	}
	/**
	 * Description:avgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public long getAvgRequestTime() {
		return avgRequestTime;
	}
	/**
	 * Description:avgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setAvgRequestTime(long avgRequestTime) {
		this.avgRequestTime = avgRequestTime;
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
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-8-29
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
}
