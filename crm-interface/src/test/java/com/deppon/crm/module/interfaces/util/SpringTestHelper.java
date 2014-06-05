package com.deppon.crm.module.interfaces.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestHelper {
	private static ApplicationContext ac = null;

	public static ApplicationContext getApplicationContext() {
		if (ac == null) {
			synchronized (SpringTestHelper.class) {
				if (ac == null) {
					ac = new ClassPathXmlApplicationContext(
							"com/deppon/crm/module/interfaces/server/META-INF/springTest.xml");
				}
			}
		}
		return ac;
	}
}
