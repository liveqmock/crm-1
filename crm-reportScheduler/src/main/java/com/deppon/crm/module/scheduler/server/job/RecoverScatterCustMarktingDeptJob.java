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
 *  * Description: 若匹配成功的客户，其营销权限部门在60天内没有<br>
 * 对客户信息做任何更新且未进行回访录入回访信息，也为制定回访计划，<br>
 * 则将该客户的营销权限部门字段变更为电销管理小组；<br>
 * 若客户的营销权限部门对客户的信息有更新或者录入了回访信息，<br>
 * 或者制定了回访计划，则营销权限部门字段不做变更；<br>
 * 归属权现或营销权限做变更之后，需要同步信息到相关系统中（CC系统，FOSS系统）
 * 
 * 每日凌晨 
 * 
 * </p>
 * @title CustCreditBadInsertJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author andy
 * @version 1.0
 * @date 2014-04-04
 *
 */
public class RecoverScatterCustMarktingDeptJob extends GridJob{
	private static IMemberManager memberManager;
	private static int dealDay = 60;
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		memberManager = getBean("memberManager", MemberManager.class);
		Log logger = LogFactory.getLog(RecoverScatterCustMarktingDeptJob.class);
		logger.info("RecoverScatterCustMarktingDeptJob_开始时间：" + new Date());
		memberManager.recoverScatterMarktingDeptByDay(dealDay);
		logger.info("RecoverScatterCustMarktingDeptJob_结束时间：" + new Date());
	}
}
