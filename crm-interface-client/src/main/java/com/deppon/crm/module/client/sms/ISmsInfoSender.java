package com.deppon.crm.module.client.sms;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sms.domain.SmsInformation;

/**
 * @作者：罗典
 * @描述：发送短信接口
 * @时间：2012-11-1
 * */
public interface ISmsInfoSender {
	/**
	 * @作者：罗典
	 * @描述：发送短信集合
	 * @时间：2012-11-1
	 * @参数：短信集合
	 * @返回值：是否成功
	 * */
	public boolean sendSms(List<SmsInformation> list)
			throws CrmBusinessException;

	/**
	 * @作者：罗典
	 * @描述：发送单条短信
	 * @时间：2012-11-1
	 * @参数：单条短信消息
	 * @返回值：是否成功
	 * */
	public boolean sendSms(SmsInformation smsInfo) throws CrmBusinessException;
}
