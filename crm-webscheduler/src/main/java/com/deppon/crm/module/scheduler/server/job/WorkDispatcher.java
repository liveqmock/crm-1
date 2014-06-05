package com.deppon.crm.module.scheduler.server.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkDispatcher implements Job{
	
    private static Logger _log = LoggerFactory.getLogger(WorkDispatcher.class);


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//执行业务处理
		_log.info("WorkDispatcher job star.");
		System.out.println("bussness logic");
	}

}
