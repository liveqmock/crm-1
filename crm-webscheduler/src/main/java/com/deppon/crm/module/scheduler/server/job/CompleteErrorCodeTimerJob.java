package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.logmoniting.server.manager.impl.LogInfoManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * @Description:
 * 		异常编码的收集，每半天执行一次，并相应的发送邮件
	 *  根据收集的非正常的业务异常和系统日志记录的日志中的异常进行比对，若发
		发现日志中存在这个异常，以邮件的方式通知开发人员进行问题跟踪及解决。
		把异常出现的次数，以及出现异常的详细信息一起发送。
 * @title CompleteErrorCodeTimmerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-7-31
 */
public class CompleteErrorCodeTimerJob extends GridJob{

	private static LogInfoManager logInfoManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		logInfoManager = getBean("logInfoManager", LogInfoManager.class);
		logInfoManager.completeExceptionErrorCode();
	}

}
