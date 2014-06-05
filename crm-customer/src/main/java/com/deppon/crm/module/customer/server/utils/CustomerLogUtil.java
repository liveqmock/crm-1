package com.deppon.crm.module.customer.server.utils;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.CustomerTableName;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation.OperationFlg;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;

/**
 * 
 * <p>
 * 用户数据同步工具<br />
 * </p>
 * @title CustomerLogUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author bxj
 * @version 0.2 2012-6-11
 */
public class CustomerLogUtil {
	/**
	 * 
	 * <p>
	 * 创建客户数据同步的同步实体<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @param table
	 * @param flg
	 * @param id
	 * @return
	 * List<CustTransationOperation>
	 */
	public static List<CustTransationOperation> createCustTranList(CustomerTableName table,OperationFlg flg,String id){
		List<CustTransationOperation> list = new ArrayList<CustTransationOperation>();
		list.add(createCustTran(table,flg,id));
		return list;
	}
	
	private static CustTransationOperation  createCustTran(CustomerTableName table,OperationFlg flg,String id){
		CustTransationOperation operation = new CustTransationOperation();
		operation.setKeyword(id);
		operation.setOptFlg(flg);
		operation.setTableName(table);
		return operation;
	}
	
	
	public static List<CustTransationOperation> createCustTranList(
			CustomerTableName table, OperationFlg flg,
			List<String> idList) {
		List<CustTransationOperation> list = new ArrayList<CustTransationOperation>();
		for (String id : idList) {
			list.add(createCustTran(table,flg,id));
		}
		return list;
	}
	
	public static void writeCustomerLog(List<CustTransationOperation> list){
		if(list.isEmpty()) {
			return;
		}
		CustomerInfoLog.writeCustomerLog(list);
	}
	
	public static void syncDataToFoss(CustomerTableName table, OperationFlg flg,
			List<String> idList){
		//TODO 李国文
		List<CustTransationOperation> list = null;
		for (String id : idList) {
			list = createCustTranList(table,flg,id);
			writeCustomerLog(list);
		}
	}
	
}
