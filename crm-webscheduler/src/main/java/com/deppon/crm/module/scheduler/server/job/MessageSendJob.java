package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
import com.deppon.foss.framework.server.components.jobgrid.JobGridManager;
import com.deppon.foss.framework.server.components.jobgrid.domain.JobMessage;

public class MessageSendJob extends GridJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		JobGridManager jobGridManager = getBean("jobGridManager",
				JobGridManager.class);
		MailSenderService mailSenderService = getBean("mailSenderService",
				MailSenderService.class);

		List<JobMessage> jml = jobGridManager.queryAllJobMessageUnsend();
		for (JobMessage jobMessage : jml) {
			String emailTxt = jobMessage.getEmail();
			String mobileTxt = jobMessage.getMobile();
			if (emailTxt != null && !"".equals(emailTxt)) {
				String[] emails = emailTxt.split(";");
				try {
					MailInfo mi = new MailInfo();
					mi.setFrom(mailSenderService.getUserName());// 谁发
					mi.setTo(emails);// 发给谁
					mi.setSubject(jobMessage.getSubject());
					mi.setContent(jobMessage.getContent());
					mailSenderService.sendExtranetMail(mi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (mobileTxt != null && !"".equals(mobileTxt)) {
				// TODO 短信
			}
			jobGridManager.updateJobMessageSend(jobMessage.getId());
		}

		System.out.println(new Date() + ":warnningMessageSend");
	}

}
