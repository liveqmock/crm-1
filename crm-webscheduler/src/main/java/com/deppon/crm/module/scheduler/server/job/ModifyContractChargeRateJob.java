package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 修改月发月送合同运费折扣定时器
 * <p>
 * TODO<br />
 * </p>
 * @title ModifyContractChargeRateJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-15
 */
public class ModifyContractChargeRateJob extends GridJob{
	private IContractManager contractManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean("contractManager", ContractManager.class);
		contractManager.updateMonthSendRate();
		//提交事务
//		CustomerInfoLog.commit();
	}
}
