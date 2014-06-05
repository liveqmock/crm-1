package com.deppon.crm.test.client.fin;

import org.junit.Test;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.test.client.common.OperaterTest;

public class DepartmentOperateImplTest extends OperaterTest {

	@Test
	public void test() {
		try {
			String  nn = departmentOperate.querySubCompanyNameByCode("DP01563");
			System.out.println(nn);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

}
 