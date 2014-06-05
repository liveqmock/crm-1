package com.deppon.crm.module.scheduler.server.service;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;

import com.deppon.crm.module.scheduler.server.job.JobConfig;


public interface SchedulerService {
	
	/**
	 * 
	 * @param jobDetail
	 * @return
	 * @throws SchedulerException
	 */
	public boolean remove(JobDetail jobDetail) throws SchedulerException;
	
	/**
	 * 
	 * @param config
	 * @throws Exception
	 */
	public void schedule(JobConfig conf) throws Exception;
	
}
