package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * <p>
 * 定时器声明-合同失效
 * </p>
 * @title ContractTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-2-20
 */
public class ContractTimerJob extends GridJob{

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ContractManager contractManager = getBean("contractManager", 
				ContractManager.class);
		Log logger = LogFactory.getLog(ContractTimerJob.class);
		contractManager.cancelTimeOutContract();
		logger.info(new Date() + " contractTimerJob");	
	}
}
