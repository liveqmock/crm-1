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
 * Description: 派送部纯到达散客：</br>
1、	派送部纯到达散客第一次发货，则将其归属部门变更为发货部门；</br>
2、	派送部纯到达散客第一次发货，则将其营销权限部门变更为客户之前归属的派送部；</br>
 * </p>
 * @title DispartureScatterCustMarktingDeptJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author pgj
 * @version 1.0
 * @date 2014-04-09
 *
 */
public class DispartureScatterCustMarktingDeptJob extends GridJob{
	private IMemberManager memberManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		memberManager = getBean("memberManager", MemberManager.class);
		Log logger = LogFactory.getLog(DispartureScatterCustMarktingDeptJob.class);
		logger.info("DispartureScatterCustMarktingDeptJob_开始时间：" + new Date());
		memberManager.dispartureSatterMarktingDept();
		logger.info("DispartureScatterCustMarktingDeptJob_结束时间：" + new Date());
	}
}
