package com.deppon.crm.test.client.sms;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.test.client.util.SpringHelper;

public class SmsInfoTest {
	ISmsInfoSender sender;
	@Before
	public void init(){
		sender = (ISmsInfoSender) SpringHelper.getApplicationContext().getBean("smsInfoSender");
	}
	
	@Test
	public void testSmsInfoSender(){
		SmsInformation smsInfo = new SmsInformation();
		smsInfo.setMobile("13916520093");
		smsInfo.setMsgContent("罗典测试.");
		smsInfo.setSendDept(null);
		smsInfo.setSender(null);
		smsInfo.setMsgType(Constant.SMS_RECOMPENSE_CODE);
		try {
			sender.sendSms(smsInfo);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
		System.out.println(smsInfo.getUnionId());
	}
}
