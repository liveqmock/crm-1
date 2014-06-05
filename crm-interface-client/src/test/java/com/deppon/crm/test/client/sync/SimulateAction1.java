/*package com.deppon.crm.test.client.sync;

import java.util.LinkedList;
import java.util.List;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;

class SimulateAction1 implements Runnable {
	private int count = 0;
	@Override
	public void run() {
		do {
			if (count == 0) {
				CustomerInfoLog.init();
			}
			List<CustTransationOperation> custTransationOperationList = new LinkedList<CustTransationOperation>();
			
			if (count == 1) {
				CustTransationOperation opt1 = new CustTransationOperation();
				opt1.setTableName(CustTransationOperation.CustomerTableName.T_CUST_CUSTBASEDATA);
				opt1.setOptFlg(OperationFlg.I);
				opt1.setKeyword("1776");
				
				custTransationOperationList.add(opt1);
				CustomerInfoLog.writeCustomerLog(custTransationOperationList);
			}
			
			if (count == 2) {
				CustTransationOperation opt2 = new CustTransationOperation();
				opt2.setTableName(CustTransationOperation.CustomerTableName.T_CUST_PREFERENCEADDRESS);
				opt2.setOptFlg(OperationFlg.I);
				opt2.setKeyword("1773");
				custTransationOperationList.add(opt2);
				CustomerInfoLog.writeCustomerLog(custTransationOperationList);
			}
			
			if (count == 3) {
				CustTransationOperation opt3 = new CustTransationOperation();
				opt3.setTableName(CustTransationOperation.CustomerTableName.T_CUST_CONTRACT);
				opt3.setOptFlg(OperationFlg.I);
				opt3.setKeyword("545");
				custTransationOperationList.add(opt3);
				CustomerInfoLog.writeCustomerLog(custTransationOperationList);
			}		
			try {
				Thread.sleep(Math.round(Math.random() * 5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (count++ < 3);
		CustomerInfoLog.commit();
	}
}*/