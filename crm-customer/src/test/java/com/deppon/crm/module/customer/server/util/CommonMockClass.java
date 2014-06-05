package com.deppon.crm.module.customer.server.util;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;

public class CommonMockClass implements ISmsInfoSender{

	@Override
	public boolean sendSms(List<SmsInformation> list)
			throws CrmBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendSms(SmsInformation smsInfo) throws CrmBusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}
