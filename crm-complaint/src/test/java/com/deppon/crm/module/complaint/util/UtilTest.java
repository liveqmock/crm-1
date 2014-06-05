package com.deppon.crm.module.complaint.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.foss.framework.server.context.AppContext;

/**
 * 测试  ApplicationContext 获取
 * @author 侯斌飞
 *
 */
public class UtilTest {
	//spring 上下文
	private static ApplicationContext ctx = null;
	/**
	 * 获取 spring 上下文
	 * @return ApplicationContext
	 */
	public static ApplicationContext getAppContext(){
//		String[] xmls = new String[]{
//				"CommonTest.xml",
//				"classpath*:com/deppon/crm/module/authorization/server/META-INF/spring.xml",
//				"classpath*:com/deppon/crm/module/frameworkimpl/server/META-INF/spring.xml",
//				"classpath*:com/deppon/crm/module/authorization/server/META-INF/spring.xml",
//				"classpath*:com/deppon/crm/module/client/server/META-INF/spring.xml",
//				"classpath*:com/deppon/crm/module/duty/server/META-INF/spring.xml",
//				"classpath*:com/deppon/foss/framework/server/META-INF/spring.xml",
//				"classpath*:com/deppon/crm/module/complaint/server/META-INF/spring.xml"
//		};
		
		
		String[] xmls = new String[]{
				"DaoBeanTest.xml","ServiceBeanTest.xml","ManagerBeanTest.xml"
		};
		try {
			if (ctx == null) {
				AppContext.initAppContext("application", "","");
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctx;
	}
}
