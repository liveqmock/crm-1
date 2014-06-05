package com.deppon.crm.module.scheduler.server.job;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.manager.impl.CustCreditManagerImpl;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description: 信用较差客户待办提醒<br/>
 * 4、最长一笔临欠达到 N  天（含）的临时欠款客户
 * 5、距离规定结款日期前   N   天未还款的月结客户
 * 6、最长还款周期（月结天数）到期前  N  天未还款的月结客户
 * 7、信用额度使用率达到  N %的月结客户
 * 8、营业部临时欠款总额使用率达到 N % 
 * 
 * 每日凌晨对满足上面条件序号为4、5、6、7、8中任一个的，生成待办
 * 
 * </p>
 * @title CustCreditBadToDoJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author andy
 * @version 1.0
 * @date 2014-04-04
 *
 */
public class CustCreditBadToDoJob extends GridJob{
	private static ICustCreditManager custCreditManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		custCreditManager = getBean("custCreditManager", CustCreditManagerImpl.class);
		Log logger = LogFactory.getLog(CustCreditBadToDoJob.class);
		logger.info("CustCreditBadToDoJob_开始时间：" + new Date());
		try {
			custCreditManager.generationCustCreditToDo();
		} catch (ParseException e) {
			e.printStackTrace();
			logger.info("信用较差客户待办提醒error：" + new Date());
		}
		logger.info("CustCreditBadToDoJob_结束时间：" + new Date());
	}
}
