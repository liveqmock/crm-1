package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.logmoniting.server.manager.impl.LogInfoManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * @Description:
 	 * 凌晨1点半
	 * 根据每天统计的前十位的Action请求次数和前一天前十位的Action请求次数进行对比，
	 * 如果两天前十位的Action名称不一样，则以邮件的方式通知开发人员确认是否存在问题
 * @title CompareTheTopTenActionLogInfoTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-7-31
 */
public class CompareTopTenActionTimerJob extends GridJob{

	private static LogInfoManager logInfoManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		logInfoManager = getBean("logInfoManager", LogInfoManager.class);
		logInfoManager.compareTheTopTenActionLogInfoByOneDay();
	}

}
