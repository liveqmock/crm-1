package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:计算每个小时请求的统计信息实体--对应Mongodb-ReqLogInfo_count<br />
 * @title ReqLogInfo_count.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-7-16
 */
public class ReqLogInfo_count {
	//key--reqLogInfo_count 表
	private String id;

	private long count;
	 //请求次数统计  总次数 （1小时）
	private long requestCount;
	 //请求最长时长 针对年月日时统计该数据
	private long maxRequestTime;
	//请求总时间
	private long requestTimeCount;
		//请求时间统计  平均时间（1小时）
	private long avgRequestTime;
		//url
	private String url;
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public String getId() {
		return id;
	}

	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
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
}
