package com.deppon.foss.framework.server.components.jobgrid.action;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
import com.deppon.foss.framework.server.components.jobgrid.domain.LogSearchCondition;
import com.deppon.foss.framework.server.components.security.SecurityNonCheckRequired;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * 
 * <p>
 * Description:日志查询Action<br />
 * </p>
 * @title LogAction.java
 * @package com.deppon.crm.module.scheduler.server.action 
 * @author 华龙
 * @version 0.1 2012-12-28
 */
public class LogAction extends AbstractAction {
	private LogSearchCondition logSearchCondition;
	private int limit;
	private int start;
	//日志列表
	private List<JobLogging> logList;
	public List<JobLogging> getLogList() {
		return logList;
	}
//	public int getTotalCount() {
//		return totalCount;
//	}
//	private int totalCount;
	
	//任务调度
	private JobGridManager job ;
	public void setJob(JobGridManager job) {
		this.job = job;
	}
		
		
	public LogSearchCondition getLogSearchCondition() {
		return logSearchCondition;
	}
	public void setLogSearchCondition(LogSearchCondition logSearchCondition) {
		this.logSearchCondition = logSearchCondition;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2012-12-28
	 * @return
	 * String
	 */
	@JSON
	public String searchLogByCondition(){
		logList = job.queryJobLogging(logSearchCondition.getJobName(),
				logSearchCondition.getJobGroup(),
				logSearchCondition.getTriggerName(),
				logSearchCondition.getStartDate(),
				logSearchCondition.getEndDate(), start, limit);
		
		totalCount = Long.valueOf(job.queryJobLoggingCount(logSearchCondition.getJobName(),
				logSearchCondition.getJobGroup(),
				logSearchCondition.getTriggerName(),
				logSearchCondition.getStartDate(),
				logSearchCondition.getEndDate()));
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:监控定时器日志<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-21
	 * @return
	 * String
	 */
	@SuppressWarnings("deprecation")
	@JSON
	@SecurityNonCheckRequired
	public String searchLogList(){
		Date begin = new Date();
		begin.setHours(0);
		begin.setMinutes(0);
		begin.setSeconds(0);
		logList = job.queryJobLogging(null,null,null,begin,new Date(),1, 10);
		return SUCCESS;
	}
}
