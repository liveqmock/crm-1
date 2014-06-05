package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.IPrefrentialDealManager;
import com.deppon.crm.module.customer.server.manager.impl.PrefrentialDealManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 自动激活带生效的合同月发月送优惠方案 定时器
 * <p>
 * TODO<br />
 * </p>
 * @title EffectPrefrentialDealJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-15
 */
public class EffectPrefrentialDealJob extends GridJob{
	private IPrefrentialDealManager prefrentialDealManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		prefrentialDealManager = getBean("prefrentialDealManager", PrefrentialDealManager.class);
		prefrentialDealManager.prefrentialdealAutoWork();
	}
	
}
