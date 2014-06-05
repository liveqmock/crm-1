package com.deppon.crm.module.customer.server.testutils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.CustomerTableName;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.customer.server.utils.CustomerLogUtil;
public class CustomerLogUtilTest {
	
	

	/**
	 * 
	 * <p>
	 * Description:测试创建客户数据同步的同步实体<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_createCustTranList(){
		List<CustTransationOperation> list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA, OperationFlg.I, "1332");
	    for (CustTransationOperation custTransationOperation : list) {
			Assert.assertEquals(CustomerTableName.T_CUST_CUSTBASEDATA, custTransationOperation.getTableName());
			Assert.assertEquals(OperationFlg.I, custTransationOperation.getOptFlg());
			Assert.assertEquals("1332", custTransationOperation.getKeyword());
		}
	    
	    List<String> idList = new ArrayList<String>();
	    idList.add("1332");
	    idList.add("1331");
	    idList.add("1333");
	    
		 list = CustomerLogUtil.createCustTranList(CustomerTableName.T_CUST_CUSTBASEDATA, OperationFlg.I, idList);
	    for (CustTransationOperation custTransationOperation : list) {
			Assert.assertEquals(CustomerTableName.T_CUST_CUSTBASEDATA, custTransationOperation.getTableName());
			Assert.assertEquals(OperationFlg.I, custTransationOperation.getOptFlg());
			Assert.assertTrue(idList.contains(custTransationOperation.getKeyword()));
		}
	    
	    CustomerLogUtil.syncDataToFoss(CustomerTableName.T_CUST_CUSTBASEDATA, OperationFlg.I, idList);
	
	}

}
