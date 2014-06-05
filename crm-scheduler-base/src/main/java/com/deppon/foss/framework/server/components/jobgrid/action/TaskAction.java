package com.deppon.foss.framework.server.components.jobgrid.action;

import java.util.List;

import org.quartz.SchedulerException;

import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleCondition;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleView;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**作者：zouming
 *创建时间：2012-12-28
 *最后修改时间：2012-12-28
 *描述：
 */
/**
 * <p>
 * Description:任务管理<br />
 * </p>
 * 
 * @title TaskAction.java
 * @package com.deppon.crm.module.scheduler.server.action
 * @author zouming
 * @version 0.1 2012-12-28下午6:47:37
 */
public class TaskAction extends AbstractAction {
	private JobGridManager jobGridManager;
	private JobScheduleCondition condition;
//	private int totalCount;

	private List<JobScheduleView> jobScheduleViewList;
	private JobScheduleView jobScheduleView;
	private List<JobSchedule> jobScheduleList;
	private JobSchedule jobSchedule;
	private JobWarnning jobWarnning;

	public JobWarnning getJobWarnning() {
		return jobWarnning;
	}

	public void setJobWarnning(JobWarnning jobWarnning) {
		this.jobWarnning = jobWarnning;
	}

	public JobScheduleView getJobScheduleView() {
		return jobScheduleView;
	}

	public void setJobSchedule(JobSchedule jobSchedule) {
		this.jobSchedule = jobSchedule;
	}

	public List<JobSchedule> getJobScheduleList() {
		return jobScheduleList;
	}

	public List<JobScheduleView> getJobScheduleViewList() {
		return jobScheduleViewList;
	}

	public JobSchedule getJobSchedule() {
		return jobSchedule;
	}

//	public int getTotalCount() {
//		return totalCount;
//	}

	public void setJobGridManager(JobGridManager jobGridManager) {
		this.jobGridManager = jobGridManager;
	}

	// 前台传后台 set
	private int start;
	private int limit;
	private String scheduleId;

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public JobScheduleCondition getCondition() {
		return condition;
	}

	public void setCondition(JobScheduleCondition condition) {
		this.condition = condition;
	}

	/**
	 * 
	 * <p>
	 * Description:按照输入条件任务查询<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2012-12-29上午10:59:34
	 * @return String
	 * @update 2012-12-29上午10:59:34
	 */
	@JSON
	public String searchTaskByCondition() {
		condition.setStart(start);
		condition.setLimit(limit);
		jobScheduleViewList = jobGridManager.queryJobScheduleView(condition);
		totalCount = Long.valueOf(jobGridManager.queryJobScheduleCount(condition));
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:按照ID查询任务<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-1-11下午6:20:26 void
	 * @update 2013-1-11下午6:20:26
	 */
	public String searchJobSchedualById() {
		jobScheduleView = jobGridManager.queryJobScheduleViewById(condition
				.getId());
		jobSchedule = jobScheduleView.getJobSchedule();
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:任务新增<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-12-29上午10:59:48
	 * @return String
	 * @update 2012-12-29上午10:59:48
	 */
	// @JSON
	public String submitTask() {
		jobGridManager.saveJobSchedule(jobSchedule, jobWarnning);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:任务修改<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-12-29上午11:00:04
	 * @return String
	 * @update 2012-12-29上午11:00:04
	 */
	public String modifyTask() {
		jobGridManager.saveJobSchedule(jobSchedule, jobWarnning);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:任务删除<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.2 2013-1-16上午10:01:53
	 * @return String
	 * @update 2013-1-16上午10:01:53
	 */
	public String deleteTask() {
		jobGridManager.deleteJobScheduleById(condition.getId());
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:任务启动<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.2 2013-1-16上午10:01:53
	 * @return String
	 * @update 2013-1-16上午10:01:53
	 */
	public String startupTask() {
		jobGridManager.startJobScheduleById(condition.getId());
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:任务执行<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.2 2013-1-16上午10:01:53
	 * @return String
	 * @update 2013-1-16上午10:01:53
	 */
	public String executeTask() {
		jobGridManager.executeJobScheduleById(condition.getId());
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:任务停止<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.2 2013-1-16上午10:01:53
	 * @return String
	 * @update 2013-1-16上午10:01:53
	 */
	public String stopTask() {
		jobGridManager.stopJobScheduleById(condition.getId());
		return SUCCESS;
	}
}
