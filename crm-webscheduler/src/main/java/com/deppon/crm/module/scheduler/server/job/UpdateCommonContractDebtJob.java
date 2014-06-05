package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

public class UpdateCommonContractDebtJob extends GridJob{

	private static ContractManager contractManager;
	
	/**
	 * Description:合同月结天数 通用权限管理修改  晚上八点到早上 九点  半小时扫描一次<br />
	 * @version 0.1 2013-5-15
	 * void
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean(
				"contractManager", ContractManager.class);
		System.out.println("updateCommonContractDebtDay----begin");
		contractManager.updateCommonContractDebtDay();
	}

}
