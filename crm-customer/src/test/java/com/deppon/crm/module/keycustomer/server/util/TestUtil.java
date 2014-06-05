package com.deppon.crm.module.keycustomer.server.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.customer.server.dao.IContactDao;
import com.deppon.crm.module.custrepeat.server.dao.IRepeatedCustDao;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.keycustomer.server.dao.impl.KeyCustomerDao;
import com.deppon.crm.module.keycustomer.server.manager.impl.KeyCustomerManager;
import com.deppon.foss.framework.server.context.AppContext;


public class TestUtil {
	public static ClassPathXmlApplicationContext factory;
	public static KeyCustomerDao keyCustomerDao;
	public static KeyCustomerManager keyCustomerManager;
	
	
	public static IRepeatedCustDao repeatedCustDao;
	public static IRepeatedCustManager repeatedCustManager;
	public static IContactDao contactDao;
	

	static {
		AppContext.initAppContext("application", "", "");
		String[] resource = { "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml" };
		factory = new ClassPathXmlApplicationContext(resource);
		
		factory = new ClassPathXmlApplicationContext(resource);
		keyCustomerDao=(KeyCustomerDao) factory.getBean("keyCustomerDao");
		keyCustomerManager=(KeyCustomerManager) factory.getBean("keyCustomerManager");
		
		
		repeatedCustDao = (IRepeatedCustDao) factory.getBean("repeatedCustDao");
		repeatedCustManager = (IRepeatedCustManager) factory.getBean("repeatedCustManager");

	}

}
