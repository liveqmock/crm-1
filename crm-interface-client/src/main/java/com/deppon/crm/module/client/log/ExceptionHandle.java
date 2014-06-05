package com.deppon.crm.module.client.log;

import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;

import org.apache.log4j.spi.LoggerFactory;

import com.deppon.crm.module.client.common.InterfaceAddressConfigProvider;
import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;
import com.deppon.crm.module.sysmail.server.manager.ISysMailSendManager;
import com.deppon.crm.module.client.log.domain.TimeRecorder;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;

/**
 * @作者：罗典
 * @时间：2012-12-1
 * @描述：接口异常处理类
 * */
public class ExceptionHandle {
	private static final String GROUP_CODE = "interface";
//	private MailSenderService mailSenderService;
	private InterfaceAddressConfigProvider provider;
	private ISysMailSendManager sysMailSendManager;
	private TimeRecorder recorder;
	public void handFault(String exMsg, String serverAddress) {
		try {
			InterfaceAddressConfig config = provider
					.getEmailInfo(serverAddress);
			 Calendar date =Calendar.getInstance();
			 date.setTime(new Date());
			 int hour = date.get(Calendar.HOUR_OF_DAY);
			if (config != null && config.getEmailReceiver() != null &&
					!config.getEmailReceiver().equals("") && 
					hour > 8 && hour < 20) {
				Date oldDate=recorder.getTimeMap().get(serverAddress);
				Date nowDate=new Date();
				if(null!=oldDate){
					long diff=nowDate.getTime()-oldDate.getTime();
					long minutes=diff/(1000*60);
					if(minutes>=30){
						MailInfo mi = new MailInfo();
						//mi.setFrom(mailSenderService.getUserName());
						//mi.setTo(config.getEmailReceiver().split(";"));
						mi.setSubject("调用外部接口异常: " + config.getTargetSystem());
						mi.setContent(config.getTargetSystem()+config.getAddress() + " " + exMsg);
						//mailSenderService.sendExtranetMail(mi);
						sysMailSendManager.sendEmail(mi, GROUP_CODE);
						recorder.getTimeMap().put(serverAddress, nowDate);
					}
				}else{
					    MailInfo mi = new MailInfo();
						//mi.setFrom(mailSenderService.getUserName());
						//mi.setTo(config.getEmailReceiver().split(";"));
						mi.setSubject("调用外部接口异常: " + config.getTargetSystem());
						mi.setContent(config.getTargetSystem()+config.getAddress() + " " + exMsg);
						//mailSenderService.sendExtranetMail(mi);
						sysMailSendManager.sendEmail(mi, GROUP_CODE);
						recorder.getTimeMap().put(serverAddress, nowDate);
				}
			}else{
				throw new RuntimeException(serverAddress + " not found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void setProvider(InterfaceAddressConfigProvider provider) {
		this.provider = provider;
	}

	public TimeRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(TimeRecorder recorder) {
		this.recorder = recorder;
	}

	public ISysMailSendManager getSysMailSendManager() {
		return sysMailSendManager;
	}

	public void setSysMailSendManager(ISysMailSendManager sysMailSendManager) {
		this.sysMailSendManager = sysMailSendManager;
	}
	
}
