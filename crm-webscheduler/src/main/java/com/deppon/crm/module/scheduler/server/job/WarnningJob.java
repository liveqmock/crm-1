package com.deppon.crm.module.scheduler.server.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import com.deppon.foss.framework.server.components.jobgrid.GridJob;
import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobScheduleView;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobWarnning;

public class WarnningJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		JobGridManager jobGridManager = getBean("jobGridManager",
				JobGridManager.class);
		List<JobScheduleView> jsvl = jobGridManager.queryAllJobSchedule();
		for (JobScheduleView jobScheduleView : jsvl) {
			JobWarnning jw = jobScheduleView.getJobWarnning();
			if (null != jw && null != jw.getWarnType()
					&& "2".equals(jw.getWarnType())) {
				int count = jobGridManager.queryVetoedCountByJobName(
						jw.getJobGroup(), jw.getJobName(), jw.getFailTime());
				if (count >= jw.getFailCount()) {
					jobGridManager.insertJobMessage(createJobMessage(jw));
				}
			}
		}

		System.out.println(new Date() + ":WarnningJob");
	}

	private JobMessage createJobMessage(JobWarnning jw) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JobMessage jm = new JobMessage();
		jm.setEmail(jw.getEmail());
		jm.setMobile(jw.getMobile());
		String subject = df.format(new Date()) + ":JobGroup is:"
				+ jw.getJobGroup() + ",JobName is:" + jw.getJobName()
				+ " execution vetoed times more than the warning value! ";
		jm.setSubject(subject);
		String content = subject
				+ " Ps:This is auto message, don't reply to the message !";
		jm.setContent(content);
		return jm;

	}

}
