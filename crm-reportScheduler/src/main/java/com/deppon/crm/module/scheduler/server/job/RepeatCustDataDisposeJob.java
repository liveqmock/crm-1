package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 0 0 4 1 * ?
 * 每月1号 凌晨4点
 * @author 120750
 *
 */
public class RepeatCustDataDisposeJob extends GridJob{
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		Log logger = LogFactory.getLog(RepeatCustCleanMarkStatusJob.class);
		IRepeatedCustManager repeatedCustManager = getBean("repeatedCustManager",
				IRepeatedCustManager.class);
		logger.info("RepeatCustDataDisposeJob开始时间："+new Date());
		repeatedCustManager.disposeRepeatCustData();
		logger.info("RepeatCustDataDisposeJob结束时间："+new Date());
	}
}
