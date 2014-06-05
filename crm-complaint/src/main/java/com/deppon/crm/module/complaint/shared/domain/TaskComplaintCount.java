package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Description:部门工单管理用户，部门各统计数<br />
 * </p>
 * 
 * @title TaskComplaintCount.java
 * @package com.deppon.crm.module.complaint.shared.domain
 * @author justin.xu
 * @version 0.1 2012-6-6
 */
public class TaskComplaintCount {

	// 待办事宜数量
	private Integer taskcount;
	// 类型：部门，个人
	private String processType;
	// 处理id:部门id,个人id
	private Integer processId;
	// taskcount get方法
	public Integer getTaskcount() {
		return taskcount;
	}
	/**
	 * set get 方法
	 */
	// taskcount set方法
	public void setTaskcount(Integer taskcount) {
		this.taskcount = taskcount;
	}
	// processType get方法
	public String getProcessType() {
		return processType;
	}
	// processType set方法
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	// processId get方法
	public Integer getProcessId() {
		return processId;
	}
	// processId set方法
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
}
