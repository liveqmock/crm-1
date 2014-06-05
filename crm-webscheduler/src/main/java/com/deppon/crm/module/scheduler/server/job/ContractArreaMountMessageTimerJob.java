package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * @Description:月结额度提醒，每天7点半和下午1点30 提醒<br />
 * @title ContractArreaMountMessageTimerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-8-23
 */
public class ContractArreaMountMessageTimerJob extends GridJob{
	
	private static ContractManager contractManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean("contractManager", ContractManager.class);
		contractManager.saveCustInfoForArreaAmoutMessage();
	}
}
