package com.deppon.crm.module.logmoniting.shared.domain;

/**
 * @Description:用于计算进行排序的结果集--ReqLogInfo_sortCount<br />
 * @title ReqLogInfo_sortCount.java
 * @package com.deppon.crm.module.logmoniting.shared.domain
 * @author CoCo
 * @version 0.1 2013-7-25
 */
public class ReqLogInfo_sortCount {

	private String id;
	// 计算的数量
	private long count;
	// 请求次数统计 (单位时间)
	private long requestCount;
	// 请求最长时长 针对年月日时统计该数据
	private long maxRequestTime;
	// 请求平均时间统计 (单位时间)
	private long avgRequestTime;
	//总时间 (单位时间)
	private long requestTimeCount;
	// url
	private String url;
	// 模块名
	private String modulename;
	/**
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public String getId() {
		return id;
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
	 * Description:id<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Description:count<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public long getCount() {
		return count;
	}
	/**
	 * Description:count<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setCount(long count) {
		this.count = count;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public long getRequestCount() {
		return requestCount;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}
	/**
	 * Description:maxRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public long getMaxRequestTime() {
		return maxRequestTime;
	}
	/**
	 * Description:maxRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setMaxRequestTime(long maxRequestTime) {
		this.maxRequestTime = maxRequestTime;
	}
	/**
	 * Description:avgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public long getAvgRequestTime() {
		return avgRequestTime;
	}
	/**
	 * Description:avgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setAvgRequestTime(long avgRequestTime) {
		this.avgRequestTime = avgRequestTime;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Description:url<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
}
