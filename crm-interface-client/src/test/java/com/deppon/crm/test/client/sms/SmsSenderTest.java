/*package com.deppon.crm.test.client.sms;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sms.ISmsSender;
import com.deppon.crm.module.client.sms.domain.SmsInfo;
import com.deppon.crm.test.client.util.SpringHelper;

public class SmsSenderTest {

	ISmsSender sender;
	
	@Before
	public void init(){
		sender = (ISmsSender) SpringHelper.getApplicationContext().getBean("smsSender");
	}
	
	@Test
	public void testSender() throws InterruptedException{
		SmsInfo info = new SmsInfo("13917182631", "接收内容", "营业部", "davidcun");
		try {
			for (int i = 0; i < 1; i++) {
				
				System.out.println(sender.sendSms(info));
			}
			
			Thread.sleep(10000);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}
}
*/