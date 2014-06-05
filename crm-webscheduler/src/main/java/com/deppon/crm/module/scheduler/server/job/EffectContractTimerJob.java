package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 定时器声明-合同生效
 * </p>
 * @title EffectContractTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-2-20
 */
public class EffectContractTimerJob extends GridJob{

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IContractManager contractManager = getBean("contractManager", 
				IContractManager.class);
		Log logger = LogFactory.getLog(EffectContractTimerJob.class);
		contractManager.effectWaitEffectContract();
		logger.info(new Date() + ":effectContractTimerJob");
	}
}
