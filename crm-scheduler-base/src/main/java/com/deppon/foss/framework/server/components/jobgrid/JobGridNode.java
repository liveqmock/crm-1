package com.deppon.foss.framework.server.components.jobgrid;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.deppon.foss.framework.server.components.jobgrid.dao.JobLoggingDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobMessageDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobPlanningDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobScheduleDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.JobWarnningDao;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JDBCContants;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobLoggingDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobMessageDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobPlanningDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobScheduleDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.dao.jdbc.JobWarnningDaoImpl;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobLogging;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobPlanning;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobSchedule;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleCondition;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleView;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;
import com.deppon.foss.framework.server.components.jobgrid.impl.GridJobListener;
import com.deppon.foss.framework.server.components.jobgrid.impl.GridJobUtils;
import com.deppon.foss.framework.server.components.jobgrid.impl.GridTriggerListener;
import com.deppon.foss.framework.server.components.jobgrid.impl.JobLinkListener;

/**
 * 任务集群节点
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0</p>
 * @author bulong.xu
 * @date 2012-11-13 上午8:51:19
 * @since
 * @version
 */
public class JobGridNode implements ApplicationContextAware, InitializingBean {
    private GridTriggerListener gobalTriggerListener;
    private GridJobListener gobalJobListener;
    private JobLinkListener jobLinkListener;
    private ApplicationContext context;
    private String dataSourceName = "myDS";
    private JobScheduleDao scheduleDao;
	private JobLoggingDao loggingDao;
	private JobPlanningDao planningDao;
	private JobWarnningDao warnningDao;
	private JobMessageDao messageDao;
    private Scheduler scheduler;

    protected JobScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    protected void setScheduleDao(JobScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public JobLoggingDao getLoggingDao() {
		return loggingDao;
	}

	public void setLoggingDao(JobLoggingDao loggingDao) {
		this.loggingDao = loggingDao;
	}

	public JobPlanningDao getPlanningDao() {
		return planningDao;
	}

	public void setPlanningDao(JobPlanningDao planningDao) {
		this.planningDao = planningDao;
	}

	public JobWarnningDao getWarnningDao() {
		return warnningDao;
	}

	public void setWarnningDao(JobWarnningDao warnningDao) {
		this.warnningDao = warnningDao;
	}

	public JobMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(JobMessageDao messageDao) {
		this.messageDao = messageDao;
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == scheduleDao) {
            scheduleDao = new JobScheduleDaoImpl(getDataSourceName());
        }
        if (null == loggingDao) {
        	loggingDao = new JobLoggingDaoImpl(getDataSourceName());
        }
        if (null == planningDao) {
        	planningDao = new JobPlanningDaoImpl(getDataSourceName());
        }
        if (null == warnningDao) {
        	warnningDao = new JobWarnningDaoImpl(getDataSourceName());
        }
        if (null == messageDao) {
        	messageDao = new JobMessageDaoImpl(getDataSourceName());
        }
		gobalJobListener = new GridJobListener();
		gobalJobListener.setApplicationContext(context);
		// JobLoggingDao logDao = new JobLoggingDaoImpl(getDataSourceName());
		gobalJobListener.setLoggingDao(loggingDao);
		gobalJobListener.setMessageDao(messageDao);
		gobalJobListener.setWarnningDao(warnningDao);
		scheduler.addGlobalJobListener(gobalJobListener);

        jobLinkListener=new JobLinkListener();;
        jobLinkListener.setApplicationContext(context);
        scheduler.addGlobalJobListener(jobLinkListener);
        
        gobalTriggerListener = new GridTriggerListener();
        scheduler.addGlobalTriggerListener(gobalTriggerListener);

    }

    
    public static JobGridNode createNode(ApplicationContext context, Scheduler scheduler) throws Exception {
        JobGridNode node = new JobGridNode();
        node.setApplicationContext(context);
        node.setScheduler(scheduler);
        node.afterPropertiesSet();
        return node;
    }

    public String getInstanceId() throws SchedulerException {
        return scheduler.getSchedulerInstanceId();
    }

    public String getClusterName() throws SchedulerException {
        return scheduler.getSchedulerName();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void addJob(JobDetail jobDetail, boolean replace) throws SchedulerException {
        scheduler.addJob(jobDetail, replace);
    }

    public boolean deleteJob(String jobName, String groupName) throws SchedulerException {
        return scheduler.deleteJob(jobName, groupName);
    }

    public void triggerJob(String jobName, String groupName) throws SchedulerException {
        scheduler.triggerJob(jobName, groupName);
    }

    public List<?> getCurrentlyExecutingJobs() throws SchedulerException {
        return scheduler.getCurrentlyExecutingJobs();
    }

    public String[] getJobGroupNames() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

    public String[] getJobNames(String groupName) throws SchedulerException {
        return scheduler.getJobNames(groupName);
    }

    public Set<?> getPausedTriggerGroups() throws SchedulerException {
        return scheduler.getPausedTriggerGroups();
    }

    public String getSchedulerName() throws SchedulerException {
        return scheduler.getSchedulerName();
    }

    public Trigger[] getTriggersOfJob(String jobName, String groupName) throws SchedulerException {
        return scheduler.getTriggersOfJob(jobName, groupName);
    }

    public Trigger getTrigger(String triggerName, String triggerGroup) throws SchedulerException {
        return scheduler.getTrigger(triggerName, triggerGroup);
    }

    public String[] getTriggerNames(String groupName) throws SchedulerException {
        return scheduler.getTriggerNames(groupName);
    }

    public int getTriggerState(String triggerName, String triggerGroup) throws SchedulerException {
        return scheduler.getTriggerState(triggerName, triggerGroup);
    }

    public void pauseAll() throws SchedulerException {
        scheduler.pauseAll();
    }

    public void pauseJob(String jobName, String groupName) throws SchedulerException {
        scheduler.pauseJob(jobName, groupName);
    }

    public void pauseJobGroup(String groupName) throws SchedulerException {
        scheduler.pauseJobGroup(groupName);
    }

    public void pauseTrigger(String triggerName, String groupName) throws SchedulerException {
        scheduler.pauseTrigger(triggerName, groupName);
    }

    public void pauseTriggerGroup(String groupName) throws SchedulerException {
        scheduler.pauseTriggerGroup(groupName);
    }

    public void resumeAll() throws SchedulerException {
        scheduler.resumeAll();
    }

    public void resumeJob(String jobName, String groupName) throws SchedulerException {
        scheduler.resumeJob(jobName, groupName);
    }

    public void resumeJobGroup(String groupName) throws SchedulerException {
        scheduler.resumeJobGroup(groupName);
    }

    public void resumeTrigger(String triggerName, String groupName) throws SchedulerException {
        scheduler.resumeTrigger(triggerName, groupName);
    }

    public void resumeTriggerGroup(String groupName) throws SchedulerException {
        scheduler.resumeTriggerGroup(groupName);
    }

    public Date scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        Date result = scheduler.scheduleJob(jobDetail, trigger);
        JobSchedule js = createSchedule(jobDetail, trigger);
        scheduleDao.insert(js);
        return result;
    }
    public Date scheduleJobUndo(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        Date result = scheduler.scheduleJob(jobDetail, trigger);
        return result;
    }

    private JobSchedule createSchedule(JobDetail jobDetail, Trigger trigger) {
        JobSchedule schedule = new JobSchedule();
//        schedule.setClusterName(GridJobUtils.getClusterName(scheduler));
        schedule.setDescription(jobDetail.getDescription());
        schedule.setJobClass(jobDetail.getJobClass().getName());
        schedule.setJobData(GridJobUtils.toPropertiesString(jobDetail.getJobDataMap()));
        schedule.setJobGroup(jobDetail.getGroup());
        schedule.setJobName(jobDetail.getName());
        if (trigger instanceof CronTrigger) {
            CronTrigger ct = (CronTrigger) trigger;
            schedule.setTriggerExpression(ct.getCronExpression());
            schedule.setTriggerGroup(ct.getGroup());
            schedule.setTriggerName(ct.getName());
            schedule.setTriggerType(JDBCContants.TRIGGER_TYPE_CRON);
        }
        return schedule;
    }

    public boolean unscheduleJob(String triggerName, String groupName) throws SchedulerException {
        boolean hadUnscheduled = scheduler.unscheduleJob(triggerName, groupName);
        if (hadUnscheduled) {            
            scheduleDao.deleteByTrigger(groupName, triggerName);
        }
        return hadUnscheduled;
    }

    public boolean unscheduleJobUndo(String triggerName, String groupName) throws SchedulerException {
        boolean hadUnscheduled = scheduler.unscheduleJob(triggerName, groupName);
        return hadUnscheduled;
    }

    public void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }

    public void start() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void setDataSourceName(String dsName) {
        this.dataSourceName = dsName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public <T extends GridJob> Date scheduleCronJob(Class<T> jobClass, String jobName, Properties jobData,
        String triggerName, String cronExpr) throws Exception {
        return scheduleCronJob(jobClass, jobName, "DEFAULT", "", jobData, triggerName, "DEFAULT", cronExpr);
    }

    public <T extends GridJob> Date scheduleCronJob(Class<T> jobClass, String cronExpr) throws Exception {
        String jobName = jobClass.getSimpleName() + "_" + UUID.randomUUID().toString();
        String triggerName = "cron_" + UUID.randomUUID().toString();
        return scheduleCronJob(jobClass, jobName, "DEFAULT", "", null, triggerName, "DEFAULT", cronExpr);
    }

    public <T extends GridJob> Date scheduleCronJob(Class<T> jobClass, Properties jobData, String cronExpr)
        throws Exception {
        String jobName = jobClass.getSimpleName() + "_" + UUID.randomUUID().toString();
        String triggerName = "cron_" + UUID.randomUUID().toString();
        return scheduleCronJob(jobClass, jobName, "DEFAULT", "", jobData, triggerName, "DEFAULT", cronExpr);
    }

    public <T extends GridJob> Date scheduleCronJob(Class<T> jobClass, String jobName, String jobGroup,
        String description, Properties jobData, String triggerName, String triggerGroup, String cronExpr)
        throws Exception {
        JobDetail jobDetail = new JobDetail(jobName, jobGroup, jobClass);
        jobDetail.setDescription(description);
        if (null != jobData) {
            jobDetail.setJobDataMap(GridJobUtils.toJobDataMap(jobData));
        }
        CronTrigger trigger = new CronTrigger(triggerName, triggerGroup, cronExpr);
        return scheduleJob(jobDetail, trigger);
    }

	public JobDetail getJobDetail(String jobName, String jobGroup)
			throws SchedulerException {
		return scheduler.getJobDetail(jobName, jobGroup);
	}

	// ************************** 规则管理 **************************
	public List<JobPlanning> queryJobPlanning(String instanceGroup, int start,
			int limit) {
		return planningDao.queryJobPlanning(instanceGroup, start, limit);
	}

	public int queryJobPlanningCount(String instanceGroup) {
		return planningDao.queryJobPlanningCount(instanceGroup);
	}

	public JobPlanning queryJobPlanningById(String planningId) {
		return planningDao.queryJobPlanningById(planningId);
	}

	public void deleteJobPlanningById(String planningId) {
		planningDao.deleteJobPlanningById(planningId);
	}

	public void insertJobPlanning(JobPlanning jobPlanning) {
		planningDao.insertJobPlanning(jobPlanning);
	}

	public void updateJobPlanning(JobPlanning jobPlanning) {
		planningDao.updateJobPlanning(jobPlanning);

	}

	// ************************** 日志查询查看 **************************

	public List<JobLogging> queryJobLogging(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate, int start,
			int limit) {
		return loggingDao.queryJobLogging(jobName, jobGroup, triggerName,
				startDate, endDate, start, limit);
	}

	public int queryJobLoggingCount(String jobName, String jobGroup,
			String triggerName, Date startDate, Date endDate) {
		return loggingDao.queryJobLoggingCount(jobName, jobGroup, triggerName,
				startDate, endDate);
	}

	public JobLogging queryJobLoggingById(String logId) {
		return loggingDao.queryJobLoggingById(logId);
	}

	// ************************** 任务管理方法 **************************
	public List<JobScheduleView> queryJobScheduleView(JobScheduleCondition condition)
			throws SchedulerException {
		List<JobScheduleView> jsvl = new ArrayList<JobScheduleView>();
		List<JobSchedule> jsl = scheduleDao.queryJobSchedule(condition);
		for (JobSchedule jobSchedule : jsl) {
			JobWarnning jobWarnning = warnningDao.queryJobWarnningByName(
					jobSchedule.getJobName(), jobSchedule.getJobGroup());
			jsvl.add(transToJobScheduleView(jobSchedule,jobWarnning));
		}
		return jsvl;
	}
			
	public int queryJobScheduleCount(JobScheduleCondition condition) {
		return scheduleDao.queryJobScheduleCount(condition);
	}

	public List<JobScheduleView> queryAllJobSchedule()
			throws SchedulerException {
		List<JobScheduleView> jsvl = new ArrayList<JobScheduleView>();
		List<JobSchedule> jsl = scheduleDao.queryAll();
		for (JobSchedule jobSchedule : jsl) {
			JobWarnning jobWarnning = warnningDao.queryJobWarnningByName(
					jobSchedule.getJobName(), jobSchedule.getJobGroup());
			jsvl.add(transToJobScheduleView(jobSchedule, jobWarnning));
		}
		return jsvl;
	}

	public JobScheduleView queryJobScheduleViewById(String scheduleId)
			throws SchedulerException {
		JobSchedule jobSchedule = scheduleDao.queryJobScheduleById(scheduleId);
		JobWarnning jobWarnning = warnningDao.queryJobWarnningByName(
				jobSchedule.getJobName(), jobSchedule.getJobGroup());
		return transToJobScheduleView(jobSchedule,jobWarnning);
	}

	private JobScheduleView transToJobScheduleView(JobSchedule jobSchedule,JobWarnning jobWarnning)
			throws SchedulerException {
		JobScheduleView jsv = new JobScheduleView();
		jsv.setJobSchedule(jobSchedule);
		jsv.setJobWarnning(jobWarnning);
		Trigger trigger = scheduler.getTrigger(jobSchedule.getTriggerName(),
				jobSchedule.getTriggerGroup());
		if(null == trigger){trigger = new CronTrigger();}
		jsv.setTrigger(trigger);
		JobDetail jobDetail = scheduler.getJobDetail(jobSchedule.getJobName(),
				jobSchedule.getJobGroup());
		jsv.setJobDetail(jobDetail);
		int triggerState = scheduler.getTriggerState(
				jobSchedule.getTriggerName(), jobSchedule.getTriggerGroup());
		jsv.setTriggerState(triggerState);
		return jsv;
	}

	public JobSchedule queryJobScheduleById(String scheduleId) {
		JobSchedule js = scheduleDao.queryJobScheduleById(scheduleId);
		return js;
	}

	public void deleteJobScheduleById(String scheduleId)
			throws SchedulerException {
		JobSchedule js = scheduleDao.queryJobScheduleById(scheduleId);
		unscheduleJob(js.getTriggerName(), js.getTriggerGroup());
		warnningDao.deleteJobWarnningByName(js.getJobName(), js.getJobGroup());
	}

	public void insertJobSchedule(JobSchedule jobSchedule, JobWarnning jobWarnning)
			throws ParseException, ClassNotFoundException, SchedulerException {
		scheduleJob(jobSchedule);
		jobWarnning.setJobGroup(jobSchedule.getJobGroup());
		jobWarnning.setJobName(jobSchedule.getJobName());
		warnningDao.insert(jobWarnning);
	}

	public void updateJobSchedule(JobSchedule jobSchedule, JobWarnning jobWarnning)
			throws ParseException, ClassNotFoundException, SchedulerException {
		unscheduleJob(jobSchedule.getTriggerName(),
				jobSchedule.getTriggerGroup());
		warnningDao.deleteJobWarnningByName(jobSchedule.getJobName(),
				jobSchedule.getJobGroup());
		scheduleJob(jobSchedule);
		jobWarnning.setJobGroup(jobSchedule.getJobGroup());
		jobWarnning.setJobName(jobSchedule.getJobName());
		warnningDao.insert(jobWarnning);
	}

	public void stopJobScheduleById(String scheduleId)
			throws SchedulerException {
		JobSchedule jobSchedule = scheduleDao.queryJobScheduleById(scheduleId);
		scheduler.pauseJob(jobSchedule.getJobName(),
				jobSchedule.getJobGroup());
	}

	public void startJobScheduleById(String scheduleId)
			throws SchedulerException {
		JobSchedule jobSchedule = scheduleDao.queryJobScheduleById(scheduleId);
		scheduler.resumeJob(jobSchedule.getJobName(),
				jobSchedule.getJobGroup());
	}

	public void executeJobScheduleById(String scheduleId)
			throws SchedulerException {
		JobSchedule jobSchedule = scheduleDao.queryJobScheduleById(scheduleId);
		scheduler.triggerJob(jobSchedule.getJobName(),
				jobSchedule.getJobGroup());
	}

	public Date scheduleJob(JobSchedule jobSchedule) throws SchedulerException,
			ParseException, ClassNotFoundException {
		Trigger trigger = createTrigger(jobSchedule);
		JobDetail jobDetail = fromJobSchedule(jobSchedule);
		return scheduleJob(jobDetail, trigger);
	}

	private Trigger createTrigger(JobSchedule js) throws ParseException {
		Trigger trigger = null;
		if (JDBCContants.TRIGGER_TYPE_SIMPLE == js.getTriggerType()) {
			String expr = js.getTriggerExpression();
			int splitIndex = expr.indexOf(":");
			int repeatCount = Integer.valueOf(expr.substring(0, splitIndex));
			long repeatInterval = Long.valueOf(expr.substring(splitIndex + 1));
			trigger = new SimpleTrigger(js.getTriggerName(),
					js.getTriggerGroup(), repeatCount, repeatInterval);
		} else if (JDBCContants.TRIGGER_TYPE_CRON == js.getTriggerType()) {
			trigger = new CronTrigger(js.getTriggerName(),
					js.getTriggerGroup(), js.getTriggerExpression());
		}
		return trigger;
	}

	private JobDetail fromJobSchedule(JobSchedule js)
			throws ClassNotFoundException {
		JobDetail jd = new JobDetail();
		jd.setJobClass(Class.forName(js.getJobClass()));
		jd.setDescription(js.getDescription());
		jd.setGroup(js.getJobGroup());
		jd.setName(js.getJobName());
		jd.setJobDataMap(GridJobUtils.fromString(js.getJobData()));
		return jd;
	}

	public List<JobMessage> queryAllJobMessageUnsend() {
		return messageDao.queryAllUnsend();
	}

	public void insertJobMessage(JobMessage jm) {
		messageDao.insertJobMessage(jm);
	}

	public void updateJobMessageSend(String messageId) {
		messageDao.updateJobMessageSend(messageId);
	}

	public int queryVetoedCountByJobName(String jobGroup, String jobName,
			int minute) {
		return loggingDao.queryVetoedCountByJobName(jobGroup, jobName, minute);
	}

}
