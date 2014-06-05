/*package com.deppon.crm.test.client.log;

import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.client.common.DbInterfaceProvider;
import com.deppon.crm.module.client.common.domain.InterfaceConfig;
import com.deppon.crm.test.client.util.SpringHelper;


public class LogTest  {
	
	@Test
	public void test1(){
		DbInterfaceProvider interfaceProvider = (DbInterfaceProvider) SpringHelper.getApplicationContext().getBean("interfaceProvider");
		Map<String, InterfaceConfig> map = interfaceProvider.load();
		System.out.println(map.size());
		System.out.println(map.get("com.deppon.crm.module.client.order.impl.WaybillOperateImpl"));
	}
}
*/