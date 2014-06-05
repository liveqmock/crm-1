package com.deppon.crm.module.marketing.manager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.deppon.crm.module.marketing.server.manager.IMessageSendManager;
import com.deppon.crm.module.marketing.server.manager.impl.MessageSendManager;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

public class MessageSendManagerTest {
	IMessageSendManager messageSendManager;
	public static ApplicationContext factory;
//	static String[] resource = new String[] { "com/deppon/crm/module/marketing/server/META-INF/DataSource.xml,com/deppon/crm/module/marketing/server/META-INF/CommonTest.xml" };
	@Before
	public void setUp(){	
//		factory = new ClassPathXmlApplicationContext(resource);
		messageSendManager = SpringTestHelper.get().getBean(MessageSendManager.class);
	}
	@Test
	public void testSendMessageFromExcel(){
//		messageSendManager.sendMessageFromExcel();
	}
}
