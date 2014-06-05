package com.deppon.crm.test.client.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {

	private static ApplicationContext context;
	
	public static ApplicationContext getApplicationContext(){
		if (context==null) {
			synchronized (SpringHelper.class) {
				if (context==null) {
					context = 
						new ClassPathXmlApplicationContext(
								"com/deppon/crm/module/client/server/META-INF/spring-test.xml");
				}
			}
		}
		return context;
	}
}
