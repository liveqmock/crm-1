package com.deppon.crm.module.authorization.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.impl.TreeDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDeptAndRoleDao;
import com.deppon.crm.module.authorization.testutil.TestUtil;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:TreeDao测试<br />
 * </p>
 * 
 * @title TreeDaoTest.java
 * @package com.deppon.crm.module.authorization.dao
 * @author 华龙
 * @version 0.1 2012-12-26
 */

public class UserDeptAndRoleDaoTest extends TestCase {
	static UserDeptAndRoleDao userDeptAndRoleDao = null;
	static {
		userDeptAndRoleDao = TestUtil.userDeptAndRoleDao;
	}

	/**
	 * 
	 * <p>
	 * Description:testInsertUserDeptAuth<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testInsertUserDeptAuth() {
		userDeptAndRoleDao.insertUserDeptAuth("123455678", "12345678");

	}

	/**
	 * 
	 * <p>
	 * Description:testGetUserDeptIds<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetUserDeptIds() {
		userDeptAndRoleDao.getUserDeptIds("123456789");

	}

	

	/**
	 * 
	 * <p>
	 * Description:testInsertUserRoleAuth<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testInsertUserRoleAuth() {
		userDeptAndRoleDao.insertUserRoleAuth("123456789", "123456789");

	}
	/**
	 * 
	 * <p>
	 * Description:testDeleteUserDeptAuthByUserId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testDeleteUserDeptAuthByUserId() {
		userDeptAndRoleDao.deleteUserDeptAuthByUserId("12345678");

	}
	/**
	 * 
	 * <p>
	 * Description:testDeleteUserRoleAuthByUserId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testDeleteUserRoleAuthByUserId() {
		userDeptAndRoleDao.deleteUserRoleAuthByUserId("12345678");

	}
/*	@Test
	public void testDateAuthorityAssignByDept() {
		Map<String,String> m=new HashMap<String,String>();
		m.put("userId","22479" );
		m.put("orgId", "10387");
		userDeptAndRoleDao.dateAuthorityAssignByDept("10387");

	}
	@Test
	public void testRoleAuthorityAssignByPosition() {
		Map<String,String> m=new HashMap<String,String>();
		m.put("userId","22479" );
		m.put("position", "营业区区域经理");
		userDeptAndRoleDao.roleAuthorityAssignByPosition(m);
	}
	@Test
	public void testDateAuthorityAssignByArea() {
		Map<String,String> m=new HashMap<String,String>();
		m.put("userId","22479" );
		m.put("orgId", "10387");
		userDeptAndRoleDao.roleAuthorityAssignByPosition(m);

	}
	@Test
	public void testDateAuthorityAssignByBigArea() {
		Map<String,String> m=new HashMap<String,String>();
		m.put("userId","22479" );
		m.put("orgId", "10387");
		userDeptAndRoleDao.roleAuthorityAssignByPosition(m);

	}*/
}
