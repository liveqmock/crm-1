package com.deppon.crm.module.logmoniting.shared.domain;

/**   
 * @Description:显示前端的grid查询结果<br />
 * @title ActionViewObject.java
 * @package com.deppon.crm.module.logmoniting.shared.domain 
 * @author CoCo
 * @version 0.1 2013-7-16
 */
public class ActionLogInfoViewObject {
	//action_ID
	private String id;
	//action名字
	private String actionName;
	//url
	private String url;
	//模块名称
	private String modulename;
	//请求次数
	private long requestCount;
	//平均时长
	private long avgRequestTime;
	//请求最长时长
	private long maxRequestTime;
	//创建时间
	private long createTime;
	
	/**
	 * Description:createTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public long getCreateTime() {
		return createTime;
	}
	/**
	 * Description:createTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-18
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
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
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * Description:actionName<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
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
	 * @version 0.1 2013-7-16
	 */
	public String getModulename() {
		return modulename;
	}
	/**
	 * Description:modulename<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public long getRequestCount() {
		return requestCount;
	}
	/**
	 * Description:requestCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 */
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}
	/**
	 * Description:avgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 */
	public long getAvgRequestTime() {
		return avgRequestTime;
	}
	/**
	 * Description:avgRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 */
	public void setAvgRequestTime(long avgRequestTime) {
		this.avgRequestTime = avgRequestTime;
	}
	/**
	 * Description:maxRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 */
	public long getMaxRequestTime() {
		return maxRequestTime;
	}
	/**
	 * Description:maxRequestTime<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 */
	public void setMaxRequestTime(long maxRequestTime) {
		this.maxRequestTime = maxRequestTime;
	}
	
}
