package com.deppon.crm.module.sysmail.server.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.sysmail.server.manager.ISysMailSendManager;
import com.deppon.crm.module.sysmail.server.service.ISysMailSendService;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.foss.framework.server.adapter.mail.MailException;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;

/**
 * 
 * <p>
 * 邮件发送实现类<br />
 * </p>
 * @title SysMailSendManager.java
 * @package com.deppon.crm.module.sysmail.server.manager.impl 
 * @author suyujun
 * @version 0.1 2013-9-25
 */
public class SysMailSendManager implements ISysMailSendManager {
	private MailSenderService mailSenderService;
	private ISysMailSendService sysMailSendService;
	
	/**
	 * @return mailSenderService : return the property mailSenderService.
	 */
	public MailSenderService getMailSenderService() {
		return mailSenderService;
	}

	/**
	 * @param mailSenderService : set the property mailSenderService.
	 */
	public void setMailSenderService(MailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}

	/**
	 * @return sysMailSendService : return the property sysMailSendService.
	 */
	public ISysMailSendService getSysMailSendService() {
		return sysMailSendService;
	}

	/**
	 * @param sysMailSendService : set the property sysMailSendService.
	 */
	public void setSysMailSendService(ISysMailSendService sysMailSendService) {
		this.sysMailSendService = sysMailSendService;
	}

	/**
	 * 邮件发送实现
	 */
	@Override
	public void sendEmail(MailInfo mailInfo, String groupCode) throws MailException{
		try {
			if(StringUtils.isNotEmpty(groupCode)){
				List<MailAccount> list = sysMailSendService.queryMailAccountByGroupCode(groupCode);
				if(list!=null && list.size() > 0){
					List<String> toSend = new ArrayList<String>();
					for(MailAccount ma : list){
						toSend.add(ma.getEmailAddress());
					}
					mailInfo.setFrom(mailSenderService.getUserName());
					mailInfo.setTo(toSend.toArray(new String[list.size()]));
					mailSenderService.sendExtranetMail(mailInfo);
					//保存发送记录
					sysMailSendService.saveSendLog(toSend);
				}
			}
		} catch (Exception e) {
			List<String> toSend = new ArrayList<String>();
			toSend.add(e.getStackTrace().toString()+e.getMessage());
			sysMailSendService.saveSendLog(toSend);
			throw new MailException(e.getMessage(), e);
		}
	}
}
