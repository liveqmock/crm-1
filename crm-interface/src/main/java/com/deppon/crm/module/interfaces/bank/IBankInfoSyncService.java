package com.deppon.crm.module.interfaces.bank;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoNotificationRequest;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoNotificationResponse;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoNotificationRequest;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoNotificationResponse;

/**
 * @作者: 罗典
 * @时间：2012-12-21下午4:16:04
 * @描述：银企基础资料同步服务
 */
public interface IBankInfoSyncService {
	/**
	 * @throws CrmBusinessException
	 * @作者：罗典
	 * @时间：2012-12-21
	 * @描述：同步银行支行
	 * */
	public BankInfoNotificationResponse receiveBank(
			BankInfoNotificationRequest request) throws CrmBusinessException;

	/**
	 * @作者：罗典
	 * @时间：2012-12-21
	 * @描述：同步省份城市
	 * */
	public ProvinceCityInfoNotificationResponse receiveProvinceCity(
			ProvinceCityInfoNotificationRequest request) throws CrmBusinessException;
}
