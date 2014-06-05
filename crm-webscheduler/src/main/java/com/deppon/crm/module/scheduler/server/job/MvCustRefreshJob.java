package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IMaterializedViewManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 固定客户每天需要进行的刷新：物化视图的complete更新
 * */
public class MvCustRefreshJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		IMaterializedViewManager mvManager=
				this.getBean("materViewManagerImpl", IMaterializedViewManager.class);
		System.out.println("mvManager.reFreshMVCuststomerDaily(),固定客户物化视图刷新");
		mvManager.reFreshMVCuststomerDaily();
	}

}
