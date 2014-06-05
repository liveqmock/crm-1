package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.logmoniting.server.manager.impl.LogInfoManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * @Description:
 	 * 每天凌晨1点跑
	 * 前提是黑名单
	 * 每天根据每个Action设置的请求时间的最大值，和请求次数的最大值，
	 * 对每天汇总的所有请求的请求时间和请求次数的统计进行对比，
	 * 如果发现请求次数或者请求时长超过设置的最大值，
	 * 则以邮件的形式通知开发人员说明Action请求时间过长或者请求次数过多。
 * @title CompareActionAverageContrastTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-7-31
 */
public class CompareActionAverageTimerJob extends GridJob{

	private static LogInfoManager logInfoManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		logInfoManager = getBean("logInfoManager", LogInfoManager.class);
		logInfoManager.compareActionAverageContrastByOneDay();
	}

}

