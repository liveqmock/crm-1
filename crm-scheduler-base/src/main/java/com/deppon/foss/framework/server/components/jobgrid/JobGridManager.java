package com.deppon.foss.framework.server.components.jobgrid;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleCondition;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleView;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;
import com.deppon.foss.framework.server.components.jobgrid.exception.CrmSchedulerException;
import com.deppon.foss.framework.server.components.jobgrid.exception.CrmSchedulerExceptionType;

public final class JobGridManager implements ApplicationContextAware,
		InitializingBean {
	private Map<String, JobGridNode> gridNodeMap = new HashMap<String, JobGridNode>();
	private ApplicationContext appContext;
	private JobGridNode localNode;
	private Log LOG = LogFactory.getLog(JobGridManager.class);
	
	/**
	 * 实例名称
	 */
	private String jobInstance;
	public void setLocalNode(JobGridNode localNode) {
		this.localNode = localNode;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
	}

	public JobGridNode getGridNode(String clusterName) {
		return gridNodeMap.get(clusterName);
	}

	public JobGridNode register(String configLoacation) throws Exception {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setConfigLocation(new ClassPathResource(configLoacation));
		factory.afterPropertiesSet();
		Scheduler scheduler = factory.getObject();
		JobGridNode node = JobGridNode.createNode(appContext, scheduler);
		gridNodeMap.put(node.getClusterName(), node);
		return node;
	}

	// ************************** 规则管理 **************************

	/**
	 * 
	 * <p>
	 * Description:规则管理 - 查询集合<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param instanceGroup
	 * @param start
	 * @param limit
	 * @return List<JobPlanning>
	 */
	public List<JobPlanning> queryJobPlanning(String instanceGroup, int start,
			int limit) {
		return localNode.queryJobPlanning(instanceGroup, start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:规则管理 - 查询集合 Count<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param instanceGroup
	 * @return int
	 */
	public int queryJobPlanningCount(String instanceGroup) {
		return localNode.queryJobPlanningCount(instanceGroup);
	}

	/**
	 * 
	 * <p>
	 * Description:规则管理 - 查询详情<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param planningId
	 *            规则Id
	 * @return JobPlanning
	 */
	public JobPlanning queryJobPlanningById(String planningId) {
		return localNode.queryJobPlanningById(planningId);
	}

	/**
	 * 
	 * <p>
	 * Description:规则管理 - 删除<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param planningId
	 *            规则Id void
	 */
	public void deleteJobPlanningById(String planningId) {
		localNode.deleteJobPlanningById(planningId);
	}

	/**
	 * 
	 * <p>
	 * Description:规则管理 - 保存<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobPlanning
	 *            规则信息 void
	 */
	public void saveJobPlanning(JobPlanning jobPlanning) {
		// 规则信息 is null 跳出执行方法
		if (null == jobPlanning) {
			return;
		}
		// 规则修改
		if (null != jobPlanning.getId() && !"".equals(jobPlanning.getId())) {
			localNode.updateJobPlanning(jobPlanning);
		} else {
			// 规则新增
			localNode.insertJobPlanning(jobPlanning);
		}
	}

	// ************************** 日志查询查看 **************************
	/**
	 * 
	 * <p>
	 * Description:日志 管理 - 查询<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param startDate
	 *            开始时间-必要条件
	 * @param endDate
	 *            结束时间-必要条件
	 * @param start
	 * @param limit
	 * @return List<JobLogging>
	 */
	public List<JobLogging> queryJobLogging(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate, int start,
			int limit) {
		return localNode.queryJobLogging(jobName, jobGroup, triggerName,
				startDate, endDate, start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:日志 管理 - 查询 Count <br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param startDate
	 *            开始时间-必要条件
	 * @param endDate
	 *            结束时间-必要条件
	 * @return int
	 */
	public int queryJobLoggingCount(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate) {
		return localNode.queryJobLoggingCount(jobName, jobGroup, triggerName,
				startDate, endDate);
	}

	/**
	 * 
	 * <p>
	 * Description:日志管理 - 查看详情<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param logId
	 *            日志ID
	 * @return JobLogging
	 */
	public JobLogging queryJobLoggingById(String logId) {
		return localNode.queryJobLoggingById(logId);
	}

	// ************************** 任务管理方法 **************************
	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询集合<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param start
	 * @param limit
	 * @return
	 * @throws SchedulerException
	 *             List<JobScheduleView>
	 */
	public List<JobScheduleView> queryJobScheduleView(JobScheduleCondition condition) {
		try {
			condition.setJobInstance(this.getJobInstance());
			return localNode.queryJobScheduleView(condition);
		} catch (SchedulerException e) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_QUERY_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询Count<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @return int
	 */
	public int queryJobScheduleCount(JobScheduleCondition condition) {
		return localNode.queryJobScheduleCount(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询集合All<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @return
	 * @throws SchedulerException
	 *             List<JobScheduleView>
	 */
	public List<JobScheduleView> queryAllJobSchedule() {
		try {
			return localNode.queryAllJobSchedule();
		} catch (SchedulerException e) {
			LOG.error("queryAllJobSchedule error");
		}
		return new ArrayList<JobScheduleView>();
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度 - 查询详情<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId
	 *            任务ID
	 * @return
	 * @throws SchedulerException
	 *             JobScheduleView
	 */
	public JobScheduleView queryJobScheduleViewById(String scheduleId) {
		try {
			return localNode.queryJobScheduleViewById(scheduleId);
		} catch (SchedulerException e) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_QUERY_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度-删除<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId
	 *            任务ID
	 * @throws SchedulerException
	 *             void
	 */
	public void deleteJobScheduleById(String scheduleId) {
		try {
			localNode.deleteJobScheduleById(scheduleId);
		} catch (SchedulerException e) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_DELETE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度-新增或修改<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jobSchedule
	 *            任务信息
	 * @param jobWarnning
	 *            预警时效信息
	 * @throws SchedulerException
	 *             void
	 */
	public void saveJobSchedule(JobSchedule jobSchedule, JobWarnning jobWarnning) {
		if (jobSchedule.getTriggerType() == 1
				&& !CronExpression.isValidExpression(jobSchedule
						.getTriggerExpression())) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_CRON_EXPRESSION_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
		try {
			// jobSchedule == null 不错任何操作
			if (null == jobSchedule) {
				return;
			}
			// 修改定时器
			if (null != jobSchedule.getId() && !"".equals(jobSchedule.getId())) {
				localNode.updateJobSchedule(jobSchedule, jobWarnning);
			} else {// 新增定时器
				localNode.insertJobSchedule(jobSchedule, jobWarnning);
			}
		} catch (ParseException e) {
			LOG.error("invaild cron expression", e);
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_CRON_EXPRESSION_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		} catch (ClassNotFoundException e) {
			LOG.error("can't find job class:" + jobSchedule.getJobClass());
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_CLASS_NOT_FOUND_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		} catch (SchedulerException e) {
			LOG.error("save schedule error:" + jobSchedule.getJobClass());
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_SAVE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度-停止<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId
	 *            任务ID
	 * @throws SchedulerException
	 *             void
	 */
	public void stopJobScheduleById(String scheduleId) {
		try {
			localNode.stopJobScheduleById(scheduleId);
		} catch (SchedulerException e) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_STOP_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度-启动<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId
	 *            任务ID
	 * @throws SchedulerException
	 *             void
	 */
	public void startJobScheduleById(String scheduleId) {
		try {
			localNode.startJobScheduleById(scheduleId);
		} catch (SchedulerException e) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_START_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:任务调度-执行<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param scheduleId
	 *            任务ID
	 * @throws SchedulerException
	 *             void
	 */
	public void executeJobScheduleById(String scheduleId) {
		try {
			localNode.executeJobScheduleById(scheduleId);
		} catch (SchedulerException e) {
			CrmSchedulerException re = new CrmSchedulerException(
					CrmSchedulerExceptionType.SCHEDULER_EXECUTE_ERROR);
			throw new GeneralException(re.getErrorCode(), re.getMessage(), re,
					new Object[] {}) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * Description:消息管理 - 查询并消息发送<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @return List<JobMessage>
	 */
	public List<JobMessage> queryAllJobMessageUnsend() {
		return localNode.queryAllJobMessageUnsend();
	}

	/**
	 * 
	 * <p>
	 * Description:消息管理 - 插入消息<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param jm
	 *            void
	 */
	public void insertJobMessage(JobMessage jm) {
		localNode.insertJobMessage(jm);
	}

	/**
	 * 
	 * <p>
	 * Description:消息管理 - 更新消息发送<br />
	 * </p>
	 * 
	 * @author 侯斌飞
	 * @version 0.1 2013-2-25
	 * @param messageId
	 *            消息Id void
	 */
	public void updateJobMessageSend(String messageId) {
		localNode.updateJobMessageSend(messageId);
	}

	public int queryVetoedCountByJobName(String jobGroup, String jobName,
			int minute) {
		return localNode.queryVetoedCountByJobName(jobGroup, jobName, minute);
	}

	public String getJobInstance() {
		return jobInstance;
	}

	public void setJobInstance(String jobInstance) {
		this.jobInstance = jobInstance;
	}
	
}
