package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.logmoniting.server.manager.impl.LogInfoManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * @Description:清除日志(请求日志和系统日志)--提供定时器调用,一周一次<br />
 * @title RemoveLogTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-7-31
 */
public class RemoveLogTimerJob extends GridJob{

	private static LogInfoManager logInfoManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		logInfoManager = getBean("logInfoManager", LogInfoManager.class);
		logInfoManager.removeLogForOneWeek();
	}

}

