package com.deppon.crm.module.scheduler.server.service.impl;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.deppon.crm.module.scheduler.server.job.JobConfig;
import com.deppon.crm.module.scheduler.server.service.SchedulerService;


/**
 * 
 * @author Administrator
 *
 */
public class SchedulerServiceImpl implements SchedulerService {

	private Scheduler scheduler;

	@Override
	public boolean remove(JobDetail jobDetail) throws SchedulerException {
		return removeJob(jobDetail);
	}

	@Override
	public void schedule(JobConfig conf) throws Exception {
		String triggerName = conf.getTriggerName();
		String triggerGroup = conf.getTriggerGroup();
		Date startTime = conf.getStartTime();
		Date endTime = conf.getEndTime();
		String cronExpression = conf.getCronExpression();

//		//构建触发器对象
//		Trigger trigger = newTrigger()
//				.withIdentity(triggerName, triggerGroup)
//				.startAt(startTime)
//				.endAt(endTime)
//				.withSchedule(cronSchedule(cronExpression))
//				.build();
//
//		String jobName = conf.getJobName();
//		String jobGroup = conf.getJobGroup();
//		
//		//构建工作细节对象
//		JobDetail jobDetail = newJob(WorkDispatcher.class)
//				.withIdentity(jobName, jobGroup)
//				.build();

		// schedule(jobDetail, trigger);
	}

	/**
	 * 派发作业
	 * 
	 * @param name
	 * @param group
	 * @param cronStr
	 * @param jobDtl
	 * @throws Exception
	 */
	private void schedule(JobDetail jobDetail, Trigger trigger)
			throws Exception {
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 移除作业
	 * 
	 * @param jobDetail
	 * @return
	 * @throws SchedulerException
	 */
	private boolean removeJob(JobDetail jobDetail) throws SchedulerException {
		// scheduler.pauseJob(jobDetail.getKey());
		// return scheduler.deleteJob(jobDetail.getKey());
		return true;// 测试
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}

