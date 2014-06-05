package com.deppon.crm.module.uums.dao;

import org.junit.Test;
import junit.framework.TestCase;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.server.dao.impl.EmployeeDao;

public class employeeDaoTest extends TestCase {
	static EmployeeDao empDao = null;
	static {
		empDao=TestUtil.empDao;
		
	}
	@Test
	public void testSync() {
		String empCode="T13320";
		String changeType="DELETE";
		String postion="大区总经理";
		String orgId="611";
		empDao.syncEmp(empCode, changeType, postion, orgId);
	}
}