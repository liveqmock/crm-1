/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ComplaintAproveOutTimeJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author zouming
 * @version 0.1 2013-4-7下午6:57:39
 */
package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * 
 * @author Administrator
 *
 */
public class OrderDelayToUnAcceptJob extends GridJob {


	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("OrderDelayToUnAcceptJob begin time:" + new Date());
		IOrderManager orderManager = getBean("orderManager", IOrderManager.class);
		orderManager.updateDelayToUnaccept();
		System.out.println("OrderDelayToUnAcceptJob end time:" + new Date());
	}

}
