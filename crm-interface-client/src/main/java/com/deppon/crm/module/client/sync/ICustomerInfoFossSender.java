package com.deppon.crm.module.client.sync;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sync.foss.WaitingSendFossRequest;

public interface ICustomerInfoFossSender {

	public abstract boolean send(String transactionNo, WaitingSendFossRequest waitingSenderRequest) throws CrmBusinessException;
	int getTargetSysCode();

}