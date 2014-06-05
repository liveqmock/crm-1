package com.deppon.crm.module.customer.server.testutils;

import java.io.IOException;

import com.deppon.crm.module.customer.server.dao.impl.BaseDataDao;
import com.deppon.crm.module.customer.server.manager.impl.BaseDataManager;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;

public class TestClassCreate {
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-26
	 * @param args
	 * @throws IOException
	 * void
	 */
	public static void main(String[] args) throws IOException {
//		System.out.println(new TestClassCreateUtils().createTestClassContext(ContractManager.class));
		new TestClassCreateUtils().createTestClass(ContractManager.class);
	}
}
