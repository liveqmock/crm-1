package com.deppon.crm.module.customer.server.service;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.foss.framework.server.web.message.IMessageBundle;

public class ExamineRecordServiceTest extends BeanUtil{
	
    private Mockery context = new Mockery();
    private IMessageBundle messageBundle;
	
    @Before
    public void setUp(){
    	messageBundle = context.mock(IMessageBundle.class);
    	examineRecordService.setMessageBundle(messageBundle);
    }
	
	
	@Test
	public void test_getCurrentPeople(){
		
		context.checking(new Expectations(){
			{
			exactly(2).of(messageBundle).getMessage("i18n.member.currentExaminerIsOver");will(returnValue("没有流程号"));
			}
		});
		String message = examineRecordService.getCurrentPeople(12234435);
		Assert.assertEquals("没有流程号", message);

	}
}
