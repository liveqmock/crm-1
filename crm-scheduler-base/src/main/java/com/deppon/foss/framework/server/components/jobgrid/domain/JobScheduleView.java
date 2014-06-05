package com.deppon.foss.framework.server.components.jobgrid.domain;

import java.io.Serializable;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class JobScheduleView implements Serializable {

	private static final long serialVersionUID = 7151430944984747692L;

	private JobSchedule jobSchedule;

	private JobWarnning jobWarnning;

	private Trigger trigger;

	private JobDetail jobDetail;

	private int triggerState;

	public JobSchedule getJobSchedule() {
		return jobSchedule;
	}

	public void setJobSchedule(JobSchedule jobSchedule) {
		this.jobSchedule = jobSchedule;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public int getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(int triggerState) {
		this.triggerState = triggerState;
	}

	public JobWarnning getJobWarnning() {
		return jobWarnning;
	}

	public void setJobWarnning(JobWarnning jobWarnning) {
		this.jobWarnning = jobWarnning;
	}

}
