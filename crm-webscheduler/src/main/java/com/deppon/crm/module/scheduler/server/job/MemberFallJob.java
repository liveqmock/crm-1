package com.deppon.crm.module.scheduler.server.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.scheduler.server.service.IMemberTimerService;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.server.service.impl.SchedulingControlService;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 定时器声明-会员降级
 * </p>
 * @title MemberFallJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-2-22
 */
public class MemberFallJob extends GridJob{
	
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ISchedulingControlService schedulingControlService = getBean("schedulingControlService", SchedulingControlService.class);
		IMemberTimerService memberTimerService = getBean("memberTimerService",IMemberTimerService.class );
		MailSenderService mailSenderService = getBean("mailSenderService", MailSenderService.class);
		Log logger = LogFactory.getLog(MemberFallJob.class);
		System.out.println(new Date() + " memberFallJob ,cust fall is Comming.....");
		
		try {
				System.out.println("-----------begin---------------");
				logger.info("会员降级执行开始");
				
				//会员降级
				memberTimerService.memberFall();
				
				logger.info("会员降级执行完了");
				System.out.println("-----------end---------------");
		} catch (Exception e) {
			logger.error("T_CRM_MEMBERFALL execute error", e);

			// 把e.printStackTrace转换成字符串
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			logger.error("T_CRM_MEMBERFALL：" + writer);

			// 给相关开发员发邮件
			String _email = schedulingControlService.searchValueByKey("T_CRM_MEMBERFALL");
			logger.info(_email);
			if (_email != null && !"".equals(_email)) {
				String[] _to = _email.split(";");
				try {
					MailInfo mi = new MailInfo("定时任务执行出错了，表名为：T_CRM_MEMBERFALL",
							writer.toString(), mailSenderService
									.getUserName(), _to);
					mailSenderService.sendExtranetMail(mi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	
	}
}
