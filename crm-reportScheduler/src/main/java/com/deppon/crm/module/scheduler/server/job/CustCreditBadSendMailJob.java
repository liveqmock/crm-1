package com.deppon.crm.module.scheduler.server.job;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.manager.impl.CustCreditManagerImpl;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;

/**
 * <p>
 * Description: 信用较差客户邮件推送<br/>
 * 1、临时欠款超过 N  天未还款临时欠款客户
 * 2、按照月结合同规定时间后 N   天未还款的月结客户
 * 3、逾期  N  天未还款的客户
 * 4、最长一笔临欠达到 N  天（含）的临时欠款客户
 * 5、距离规定结款日期前   N   天未还款的月结客户
 * 
 * 每月16号凌晨、1号凌晨对满足上面条件序号为1、2、3、4、5中的任一个的，生成excel名单，对邮件提醒至相应的人员
 * 
 * </p>
 * @title CustCreditBadSendMailJob
 * @package com.deppon.crm.module.scheduler.server.job
 * @author andy
 * @version 1.0
 * @date 2014-04-04
 *
 */
public class CustCreditBadSendMailJob extends GridJob{
	private static ICustCreditManager custCreditManager;
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		custCreditManager = getBean("custCreditManager", CustCreditManagerImpl.class);
		Log logger = LogFactory.getLog(CustCreditBadSendMailJob.class);
		logger.info("CustCreditBadSendMailJob_开始时间：" + new Date());
		try {
			custCreditManager.sendCustCreditMail();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		logger.info("CustCreditBadSendMailJob_结束时间：" + new Date());
	}
}
