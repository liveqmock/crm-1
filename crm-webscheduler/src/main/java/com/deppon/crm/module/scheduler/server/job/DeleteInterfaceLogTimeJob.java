package com.deppon.crm.module.scheduler.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.logmoniting.server.manager.impl.SynchroLogManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * @Description:
 *     一天执行一次,凌晨三点执行
	 * 删除t_if_log日志,保存近15天的日志<br />
 * @title DeleteInterfaceLogTimeJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author CoCo
 * @version 0.1 2013-10-16
 */
public class DeleteInterfaceLogTimeJob extends GridJob{

	private static SynchroLogManager synchroLogManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		synchroLogManager = getBean(
				"synchroLogManager", SynchroLogManager.class);
		//TODO
//		synchroLogManager.deleteInterfaceLogs();
	}
}
