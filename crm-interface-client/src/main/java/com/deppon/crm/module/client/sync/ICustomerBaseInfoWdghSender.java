package com.deppon.crm.module.client.sync;

import com.deppon.crm.module.client.sync.wdgh.SyncCustInfoRequest;

public interface ICustomerBaseInfoWdghSender {

	/***
	 * 同步网点规划系统
	 * @param request
	 * @return
	 */
	boolean send(String transactionNo,SyncCustInfoRequest request); 
	
	
	public int getTargetSysCode();
}
