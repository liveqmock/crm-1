/*package com.deppon.crm.test.client.customer;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.deppon.crm.module.client.common.domain.WaitCustomerLogInfo;
import com.deppon.crm.test.client.common.OperaterTest;

public class SyncCallbackDaoImplTest extends OperaterTest {
	
	@Test
	public void queryWaitCustomerLogInfoTest(){
			syncCallbackDao.queryWaitCustomerLogInfo("2012061300000000000100");
	}
	
	@Test
	public void updateWaitCustomerLogInfo(){
		WaitCustomerLogInfo waitCustomerLogInfo = new WaitCustomerLogInfo();
		waitCustomerLogInfo.setFID("1");
		waitCustomerLogInfo.setTRANSACTION_NO("2012061300000000000100");
		waitCustomerLogInfo.setCUSTOMER_NO("1");
		waitCustomerLogInfo.setCRATE_TIME(new Date());
		waitCustomerLogInfo.setFINISH_TIME(new Date());
		waitCustomerLogInfo.setHANDLE_TYPE("1");
		waitCustomerLogInfo.setSTATUS("1");
		waitCustomerLogInfo.setTARGET("1");
		waitCustomerLogInfo.setERROR_CODE("1");
		waitCustomerLogInfo.setERR_DESC("1");
		waitCustomerLogInfo.setERR_NUMBER(new BigDecimal(1));
		waitCustomerLogInfo.setERR_TIME(new Date());
		waitCustomerLogInfo.setPATTERN("1");
		waitCustomerLogInfo.setDATA("1");
		syncCallbackDao.updateWaitCustomerLogInfo(waitCustomerLogInfo);
	}
	
}
*/