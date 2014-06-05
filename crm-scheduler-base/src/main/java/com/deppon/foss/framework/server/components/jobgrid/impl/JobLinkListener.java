package com.deppon.foss.framework.server.components.jobgrid.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JobLinkListener implements JobListener, ApplicationContextAware {
	private static final String LISTENER_NAME = "JobLinkListener";
	private Log LOG = LogFactory.getLog(JobLinkListener.class);
	private ApplicationContext appContext;

	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		JobDetail jobDetail = context.getJobDetail();
		Trigger trigger = context.getTrigger();
		Scheduler scheduler = context.getScheduler();
		String nextJob = context.getMergedJobDataMap().getString("nextjob");
		if (null != nextJob) {
			try {
				// JobDetail nextJobDetail = scheduler.getJobDetail(nextJob,
				// jobDetail.getGroup());
				// JobDetail nextnewJobDetail = new JobDetail("X", null,
				// nextJobDetail.getClass());
				// Trigger nextTrigger = TriggerUtils.makeSecondlyTrigger(0, 0);
				// nextTrigger.setName("C");
				// // nextTrigger.setName(trigger.getGroup());
				// nextTrigger.setStartTime(new Date());
				//
				// scheduler.scheduleJob(nextJobDetail, nextTrigger);

				// System.out.println(nextJobDetail.getJobClass());
				// String[] jobNames =
				// scheduler.getJobNames(jobDetail.getGroup());
				//
				scheduler.triggerJob(nextJob, jobDetail.getGroup());
				// for (String jobName : jobNames) {
				// System.out.println("jobNames = : " + jobName);
				// this.scheduler.triggerJob(jobName, groupName);
				// }
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.appContext = applicationContext;
	}

}
