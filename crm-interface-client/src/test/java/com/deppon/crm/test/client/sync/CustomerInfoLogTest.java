/*
package com.deppon.crm.test.client.sync;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerInfoLogTest extends TestCase {

	ClassPathXmlApplicationContext factory;

	
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 
	@BeforeClass
	protected void setUp() throws Exception {
		String resource = "com/deppon/crm/module/client/server/META-INF/spring.sync.api.xml";
		factory = new ClassPathXmlApplicationContext(resource);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 
	@After
	protected void tearDown() throws Exception {
		;
	}

	
	@Test
	public void testGetTransactionNo() {
		try {
			List<CustTransationOperation> custTransationOperationList = new LinkedList<CustTransationOperation>();
			
			CustTransationOperation opt1 = new CustTransationOperation();
			opt1.setTableName(CustTransationOperation.CustomerTableName.T_CUST_CUSTBASEDATA);
			opt1.setOptFlg(OperationFlg.U);
			opt1.setKeyword("1776");
			
			CustTransationOperation opt2 = new CustTransationOperation();
			opt2.setTableName(CustTransationOperation.CustomerTableName.T_CUST_PREFERENCEADDRESS);
			opt2.setOptFlg(OperationFlg.U);
			opt2.setKeyword("1773");
			
			CustTransationOperation opt3 = new CustTransationOperation();
			opt3.setTableName(CustTransationOperation.CustomerTableName.T_CUST_CONTRACT);
			opt3.setOptFlg(OperationFlg.U);
			opt3.setKeyword("545");

			custTransationOperationList.add(opt1);
			custTransationOperationList.add(opt2);
			custTransationOperationList.add(opt3);
			assertTrue(customerInfoLog.writeCustomerLog(custTransationOperationList));
		} catch (CrmBusinessException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTransactionNo165() {
		try {
			List<CustTransationOperation> custTransationOperationList = new LinkedList<CustTransationOperation>();

			CustTransationOperation opt1 = new CustTransationOperation();
			opt1.setTableName(CustTransationOperation.CustomerTableName.T_CUST_CUSTBASEDATA);
			opt1.setOptFlg(OperationFlg.U);
			opt1.setKeyword("40000264");

			custTransationOperationList.add(opt1);
			assertTrue(customerInfoLog
					.writeCustomerLog(custTransationOperationList));
		} catch (CrmBusinessException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTransactionNo() {
		SimulateAction1 action1 = new SimulateAction1();
		SimulateAction2 action2 = new SimulateAction2();
		SimulateAction3 action3 = new SimulateAction3();
		Thread t1 = new Thread(action1, "Action1");
		Thread t2 = new Thread(action2, "Action2");
		Thread t3 = new Thread(action3, "Action3");
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(5 * 60 * 1000);
			if (! t1.isInterrupted()) t1.interrupt();
			if (! t2.isInterrupted()) t2.interrupt();
			if (! t3.isInterrupted()) t3.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}	
}
*/