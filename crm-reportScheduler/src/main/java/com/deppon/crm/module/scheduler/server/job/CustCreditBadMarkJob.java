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
 * Description: 信用较差客户标记<br/>
 * 1、临时欠款超过 N  天未还款临时欠款客户
 * 2、按照月结合同规定时间后 N   天未还款的月结客户
 * 3、逾期  N  天未还款的客户
 * 
 * 每月16号凌晨、1号凌晨对只要满足上面条件序号为1、2、3中的任一个，标记为信用较差客户
 * 
 * </p>
 * @title CustCreditBadMarkJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author andy
 * @version 1.0
 * @date 2014-04-04
 *
 */
public class CustCreditBadMarkJob extends GridJob{
	private static ICustCreditManager custCreditManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		custCreditManager = getBean("custCreditManager", CustCreditManagerImpl.class);
		Log logger = LogFactory.getLog(CustCreditBadMarkJob.class);
		logger.info("CustCreditBadMarkJob_开始时间：" + new Date());
		try {
			custCreditManager.updateCustBadCredit();
		} catch (ParseException e) {
			e.printStackTrace();
			logger.info("信用较差客户标记error" + new Date());
		}
		logger.info("CustCreditBadMarkJob_结束时间：" + new Date());
	}
}
