package com.deppon.crm.module.authorization.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.server.service.impl.UserService;
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
 * @title AuthorizeServiceTest.java
 * @package com.deppon.crm.module.authorization.service
 * @author 华龙
 * @version 0.1 2012-12-26
 */
public class UserServiceTest extends TestCase {
	static IUserService userService = null;
	static User user = new User();
	static {
		userService = TestUtil.userService;

	}

	/**
	 * 
	 * <p>
	 * Description:testQueryAll<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testQueryAll() {
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
		userService.queryAll(user);
		System.out.println();
	}
	/**
	 * 
	 * <p>
	 * Description:testQueryAll<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testQueryAllLimit() {
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
		userService.queryAll(user,10,1);
	}
	/**
	 * 
	 * <p>
	 * Description:testSave<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

/*	public void testSave() {
		List<String> roleIds = new ArrayList<String>();
		List<String> deptIds = new ArrayList<String>();
		User user = new User();
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
		try {
			userService.save(null, roleIds, deptIds);
		} catch (Exception e) {
		}
		user.setLoginName("");
		try {
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		user.setLoginName(null);
		try {
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		user.setLoginName("106138");
		try {
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		user.setPassword(null);
		try {
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		user.setPassword("");
		try {
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		user.setPassword("qqqqqq");
		user.setStatus(null);
		try {
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		try {
			user.setStatus((byte) 1);
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
		try {
			user.setStatus((byte) 2);
			userService.save(user, roleIds, deptIds);
		} catch (Exception e) {
		}
	}*/
	/**
	 * 
	 * <p>
	 * Description:testFindByLoginName<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testCountByUser() {
		userService.count(user);
		
	}
	/**
	 * 
	 * <p>
	 * Description:testFindByLoginName<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testFindByLoginName() {
		userService.findByLoginName("1111");
	}
	
	/**
	 * 
	 * <p>
	 * Description:testUpdatePassword<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testUpdatePassword() {
		try {
			userService.updatePassword(null);
		} catch (Exception e) {
		}
		user.setId(null);
		user.setPassword("qqqqqq");
		try {
			userService.updatePassword(user);
		} catch (Exception e) {
		}
		user.setId("");
		try {
			userService.updatePassword(user);
		} catch (Exception e) {
		}
		user.setId("22477");
		try {
			userService.updatePassword(user);
		} catch (Exception e) {
		}
		user.setPassword("");
		try {
			userService.updatePassword(user);
		} catch (Exception e) {
		}
		user.setPassword(null);
		try {
			userService.updatePassword(user);
		} catch (Exception e) {
		}
		user.setPassword("qqqqqq");
		try {
			userService.updatePassword(user);
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * <p>
	 * Description:testQetUserDeptIds<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testqueryAllByDeptId() {
		userService.queryAllByDeptId("1", 10, 1);
	}

	/**
	 * 
	 * <p>
	 * Description:testQetUserDeptIds<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testCount() {
		userService.count("11");
	}

	/**
	 * 
	 * <p>
	 * Description:testQetUserDeptIds<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testQetUserDeptIds() {
		userService.getUserDeptIds("1");
	}

	/**
	 * 
	 * <p>
	 * Description:testQueryManagerUserIdByDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testQueryManagerUserIdByDeptId() {
		try {
			userService.queryManagerUserIdByDeptId(null);
		} catch (Exception e) {
		}
		try {
			userService.queryManagerUserIdByDeptId("");
		} catch (Exception e) {
		}
		userService.queryManagerUserIdByDeptId("100");
	}

	/**
	 * 
	 * <p>
	 * Description:testqueryUsersByDeptIds<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testqueryUsersByDeptIds() {
		try {
			userService.queryUsersByDeptIds(null);
		} catch (Exception e) {
		}
		List<String> deptIds = new ArrayList<String>();
		deptIds.add("111");
		userService.queryUsersByDeptIds(deptIds);
	}

	/**
	 * 
	 * <p>
	 * Description:testQueryUsersByDeptAndRole<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testQueryUsersByDeptAndRole() {
		userService.queryUsersByDeptAndRole("1", "1");
	}

	/**
	 * 
	 * <p>
	 * Description:testGetUserById<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testGetUserById() {
		userService.getUserById("1");
	}

	/**
	 * 
	 * <p>
	 * Description:testQueryManagerEmployeeIdByDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testGetUserRoleFunDeptById() {
		userService.getUserRoleFunDeptById("111");
	}

	/**
	 * 
	 * <p>
	 * Description:testQueryManagerEmployeeIdByDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-27 void
	 */
	@Test
	public void testQueryManagerEmployeeIdByDeptId() {
		userService.queryManagerEmployeeIdByDeptId("1");
	}
}
