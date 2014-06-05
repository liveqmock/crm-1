package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IWarnLostCustManager;
import com.deppon.crm.module.marketing.server.manager.impl.WarnLostCustManagerImp;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 流失预警名单生成
 * 流失预警客户更新
 * */
public class WarnLostCustCreateJob extends GridJob{
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		WarnLostCustManagerImp warnLostManager=
				this.getBean("warnLostManager", WarnLostCustManagerImp.class);
		warnLostManager.createWarnList();
	}

}
