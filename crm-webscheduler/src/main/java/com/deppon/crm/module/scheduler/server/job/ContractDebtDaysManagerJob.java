package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**   
 * Description:合同月结天数管理 每天接口提供的中间表 将天数减1<br />
 * @title ContractDebtDaysManagerJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @version 0.1 2013-5-16
 */
public class ContractDebtDaysManagerJob extends GridJob{

	private static ContractManager contractManager;
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean(
				"contractManager", ContractManager.class);
		System.out.println("contractDebtDaysManager----begin");
		contractManager.contractDebtDaysManager();
	}
}
