package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.marketing.server.manager.IMaterializedViewManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 散客用户每天需要进行的数据刷新：
 * 1.在物化视图模式中，采用complete的刷新模式
 * 2.在中间表模式下，采用增量更新的刷新模式
 * */
public class MvPsRefreshJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {

		IMaterializedViewManager mvManager=
				this.getBean("materViewManagerImpl", IMaterializedViewManager.class);
		System.out.println("mvManager.reFreshMVPSCustomerDaily(),散客客户中间表增量更新");

		mvManager.reFreshMVPSCustomerDaily();
	}

}
