package com.deppon.crm.module.client.sync.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.client.sync.IMemberDataSenderController;

public class MemberDataSenderTest {
	private ClassPathXmlApplicationContext factory;
	private IMemberDataSenderController dataSender;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		String resource = "com/deppon/crm/module/client/server/META-INF/spring.sync.sender.mutil.thread.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		dataSender =  (IMemberDataSenderController) factory.getBean("MemberDataSenderController");
		Assert.assertNotNull(dataSender);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSend() {
		dataSender.send();
		assertTrue(true);
	}

}
