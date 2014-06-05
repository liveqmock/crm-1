package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.logmoniting.server.manager.impl.LogInfoManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * @Description:提供定时器调用---1小时执行一次，记录和统计日志信息
 *  并与昨天的统计信息进行比较，是否达到预警条件，达到就发送邮件预警
 * @title AssemblingLogInfoDataTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-7-31
 */
public class AssemblingLogInfoDataTimerJob extends GridJob{
	
	private static LogInfoManager logInfoManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		logInfoManager = getBean("logInfoManager", LogInfoManager.class);
		logInfoManager.assemblingLogInfoDataForOneHour();
	}
}
