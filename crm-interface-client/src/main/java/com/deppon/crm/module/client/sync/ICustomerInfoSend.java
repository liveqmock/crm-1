package com.deppon.crm.module.client.sync;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;

public interface ICustomerInfoSend {
     /***
      * @author wmm
      * @datetime: 2013-09-05
      * 
      */
	public abstract boolean send(String transactionNo, String jsonString,String targetSystems) throws CrmBusinessException;

}