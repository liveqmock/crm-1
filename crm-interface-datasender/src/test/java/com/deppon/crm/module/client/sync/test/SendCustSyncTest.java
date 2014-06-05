package com.deppon.crm.module.client.sync.test;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.sync.impl.CustomBaseInfoExecutorContext;

public class SendCustSyncTest {
	CustomBaseInfoExecutorContext context = null;

	@Before
	public void setUp() {
		context = (CustomBaseInfoExecutorContext) SpringHelper
				.getApplicationContext().getBean(
						"CustomBaseInfoExecutorContext");
	}

	@Test
	public void testSend() throws InterruptedException {
		context.execute();
		Thread.sleep(10000000);
	} 
}
