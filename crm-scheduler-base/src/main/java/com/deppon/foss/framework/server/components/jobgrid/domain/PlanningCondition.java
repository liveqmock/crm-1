package com.deppon.foss.framework.server.components.jobgrid.domain;

/**
 * @description 定时任务调度控制.
 * @author 侯斌飞
 * @version 0.1
 * @date 2013-1-14
 */

public class PlanningCondition {
	private Integer start;
	private Integer limit;
	
	private String id;
	/**
	 * 实例组
	 */
	private String instanceId;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
