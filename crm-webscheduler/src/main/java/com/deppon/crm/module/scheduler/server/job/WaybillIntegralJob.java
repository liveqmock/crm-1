package com.deppon.crm.module.scheduler.server.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.IWaybillIntegralManager;
import com.deppon.crm.module.customer.server.manager.impl.WaybillIntegralManager;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 *  积分运单的定时器<br />
 * </p>
 * @title WaybillIntegralJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-15
 */
public class WaybillIntegralJob extends GridJob{
	private IWaybillIntegralManager waybillIntegralManager ;
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		waybillIntegralManager = getBean("waybillIntegralManager", WaybillIntegralManager.class);
		List<WaybillIntegral> list = waybillIntegralManager.getNeedDisPoseWaybills();
		waybillIntegralManager.disPoseWaybillIntegral(list);
	}
}
