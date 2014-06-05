package com.deppon.crm.module.authorization.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.deppon.crm.module.authorization.server.manager.ITreeManager;
import com.deppon.crm.module.authorization.server.manager.impl.TreeManager;
import com.deppon.crm.module.authorization.shared.domain.Tree;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Employee;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title TreeManager.java
 * @package com.deppon.crm.module.authorization.manager
 * @author 华龙
 * @version 0.1 2012-12-26
 */
public class TreeManagerTest extends TestCase {
	static ITreeManager treeManager = null;
	static User user = new User();
	static Tree tree = new Tree();

	static {

		treeManager =  TestUtil.treeManager;
		tree.setFunctionCode("");
		tree.setNoteurl("/login/aa.action");
		tree.setUserid(106138);
		
		user.setCreateDate(new Date());
		user.setCreateUser("");
		// user.setDeptids();
		Employee emp = new Employee();
		emp.setEmpName("许");
		emp.setEmpCode("987546526");
		user.setEmpCode(emp);
		user.setInvalidDate(new Date());
		user.setLastLogin(new Date());
		user.setLoginName("" + new Random());
		user.setModifyDate(new Date());
		user.setPassword("123");
		// user.setRoleids(roleids);
		// user.setRoles(roles);
		// user.setStatus("y");
		user.setValidDate(new Date());
		user.setId("22476");
	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeByUser<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testGetTreeByUser() {
		treeManager.getTreeByUser(user);

	}

	/**
	 * 
	 * <p>
	 * Description:testGetTreeByUser<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testsavaOrUpdateTree() {
		List<Tree> list = new ArrayList<Tree>();
		treeManager.savaOrUpdateTree(list, user);

	}

}
