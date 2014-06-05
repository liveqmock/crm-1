package com.deppon.crm.module.common.server.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.service.impl.MessageService;

import junit.framework.TestCase;

public class MessageServiceTest extends TestCase {
	private MessageService messageService; 
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };
	protected void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			messageService = ctx.getBean(MessageService.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testMessageService(){
		String userid="28993";
		String deptId="11117";
		Long s=messageService.seacherExceptOrderCount(userid, deptId);//seacherExceptOrderCount
		System.out.println(s);
		s=messageService.seacherMsgInfoCount(userid, deptId); //
		System.out.println(s);
		s=messageService.seacherOrderCount(userid, deptId);//ORDER COUNT
		System.out.println(s);
	}
}
