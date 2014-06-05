package com.deppon.crm.module.uums.dao;

import org.junit.Test;
import junit.framework.TestCase;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.organization.server.dao.impl.EmployeeDao;

public class ORGDaoTest extends TestCase {
	static DepartmentDao orgDao = null;
	static {
		orgDao=TestUtil.orgDao;
		
	}
	@Test
	public void testSync() {
		String deptStandCode="DP13306";
		String changeType="UPDATE";
		String oldParentStandCode="DP10897";
		String parentStandCode="DP00060";
		orgDao.syncDep(deptStandCode, changeType, oldParentStandCode);
	}
}