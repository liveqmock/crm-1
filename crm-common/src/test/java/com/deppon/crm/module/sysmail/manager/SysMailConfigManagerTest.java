package com.deppon.crm.module.sysmail.manager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.sysmail.server.dao.ISysMailConfigDao;
import com.deppon.crm.module.sysmail.server.manager.ISysMailConfigManager;
import com.deppon.crm.module.sysmail.server.manager.ISysMailSendManager;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.util.SysMailConfigUtil;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;

public class SysMailConfigManagerTest {
	private ISysMailConfigManager sysMailConfigManager = null;
	private ISysMailSendManager sysMailSendManager = null;
	private static ApplicationContext ctx = null;
	private String[] xmls = {"SysMailConfigTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			sysMailConfigManager = ctx.getBean(ISysMailConfigManager.class);
			sysMailSendManager = ctx.getBean(ISysMailSendManager.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddMailAccount() {
		MailAccount ma = SysMailConfigUtil.createMailAccount();
		sysMailConfigManager.addMailAccount(ma,new AccountGroup());
	}
	
	@After
	public void clearEnv(){
		sysMailConfigManager.deleteMailAccountById(SysMailConfigUtil.mailAccountId);
	}
	
	@Test
	public void testSendMail(){
		String groupCode = "testsendmail";
		MailInfo info = new MailInfo();
		info.setSubject("lalalal");
		info.setContent("这是测试邮件");
		sysMailSendManager.sendEmail(info, groupCode);
	}
}
