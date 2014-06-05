package com.deppon.crm.module.client.sync;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sync.cc.WaitingSendCcRequest;

public interface ICustomerInfoCcSender {

	public abstract boolean send(String transactionNo, WaitingSendCcRequest waitingSenderRequest) throws CrmBusinessException;
	int getTargetSysCode();
}