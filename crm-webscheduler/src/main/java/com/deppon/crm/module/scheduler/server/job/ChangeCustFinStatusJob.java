package com.deppon.crm.module.scheduler.server.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 更新散客和固定客户的财务冻结字段定时器
 * </p>
 * @title ChangeCustFinStatusJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-17
 */
public class ChangeCustFinStatusJob extends GridJob{
	private IMemberManager memberManager;
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		memberManager = getBean("memberManager", IMemberManager.class);
		memberManager.changeCustFinStatus();
		CustomerInfoLog.commit();
	}
}
