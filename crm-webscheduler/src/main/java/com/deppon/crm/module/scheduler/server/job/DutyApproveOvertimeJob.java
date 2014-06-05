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

import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description:工单责任认领审批超期定时器<br />
 * </p>
 * 
 * @title ComplaintAproveOutTimeJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author zouming
 * @version 0.1 2013-4-7下午6:57:39
 */

public class DutyApproveOvertimeJob extends GridJob {

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2013-4-7下午6:58:11
	 * @param context
	 * @throws JobExecutionException
	 * @update 2013-4-7下午6:58:11
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("DutyApproveOvertimeJob begin time:" + new Date());
		IDutyManager dutyManager = getBean("dutyManager", IDutyManager.class);
		dutyManager.feedBackApprovalExtended();
		System.out.println("DutyApproveOvertimeJob end time:" + new Date());
	}

}
