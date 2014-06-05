package com.deppon.crm.module.client.email;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class DpMailServiceImpl implements DpMailService {

	private Log log = LogFactory.getLog(DpMailServiceImpl.class);

	private JavaMailSenderImpl mailSender;

	@Override
	public boolean sendMail(String subject, String content, String[] to) {
		try {
			mailSend(subject, content, to);
		} catch (MessagingException e1) {
			log.info("send email failure");
			return false;
		}
		return true;
	}

	private void mailSend(String subject, String content, String[] to)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelp = new MimeMessageHelper(message, true,
				"GBK");
		List<String> mailTo = new ArrayList<String>();
		for (String t : to) {
			if (t != null) {
				mailTo.add(t);
			}
		}
		messageHelp.setFrom(mailSender.getUsername());
		messageHelp.setTo((String[]) mailTo.toArray(new String[mailTo.size()]));
		messageHelp.setSubject(subject);
		messageHelp.setText(content, true);
		mailSender.send(message);
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

}
