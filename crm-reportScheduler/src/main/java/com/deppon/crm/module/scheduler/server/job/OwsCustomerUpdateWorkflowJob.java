package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.impl.MemberManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description: 官网暂存修改信息工作流启动：</br>
 * </p>
 * @title CustCreditBadInsertJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author pgj
 * @version 1.0
 * @date 2014-04-09
 *
 */
public class OwsCustomerUpdateWorkflowJob extends GridJob{
	private IMemberManager memberManager;
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		memberManager = getBean("memberManager", MemberManager.class);
		Log logger = LogFactory.getLog(OwsCustomerUpdateWorkflowJob.class);
		logger.info("OwsCustomerUpdateWorkflowJob_开始时间：" + new Date());
		memberManager.geneCustomerUpdateWorkflow();
		logger.info("OwsCustomerUpdateWorkflowJob_结束时间：" + new Date());
	}
}