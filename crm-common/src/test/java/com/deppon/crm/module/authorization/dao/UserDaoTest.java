package com.deppon.crm.module.authorization.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.testutil.TestUtil;
import com.deppon.crm.module.organization.shared.domain.Employee;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:UserDao测试类<br />
 * </p>
 * 
 * @title UserDaoTest.java
 * @package com.deppon.crm.module.authorization.dao
 * @author 华龙
 * @version 0.1 2012-12-26
 */
public class UserDaoTest extends TestCase {
	static User user = new User();
	static IUserDao userDao;
	static {
		user.setCreateDate(new Date());
		user.setCreateUser("");
		// user.setDeptids();
		Employee emp = new Employee();
		emp.setEmpName("许");
		emp.setEmpCode("987546526");
		user.setEmpCode(emp);
		user.setInvalidDate(new Date());
		user.setLastLogin(new Date());
		user.setLoginName(""+new Random());
		user.setModifyDate(new Date());
		user.setPassword("123");
		// user.setRoleids(roleids);
		// user.setRoles(roles);
		// user.setStatus("y");
		user.setValidDate(new Date());
		user.setId("22476");
		userDao = TestUtil.userDao;
	}

	/**
	 * 
	 * <p>
	 * Description:测试getAll<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetAll() {
		userDao.getAll(user);
	}

	/**
	 * 
	 * <p>
	 * Description:testUpdate<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testUpdate() {
		userDao.update(user);
	}

	/**
	 * 
	 * <p>
	 * Description:testInsert<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testInsert() {
		//TODO 因delte无法删除 插入方法暂时注释掉
//		user.setId("22476");
//		userDao.insert(user);
	}

	/**
	 * 
	 * <p>
	 * Description:testDeleteById<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testDeleteById() {
		//TODO  删除代码有问题
		
		
		//userDao.deleteById(user.getId());
	}

	/**
	 * 
	 * <p>
	 * Description:测试getAll<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetAllLimit() {
		userDao.getAll(user,1,1);
	}

	/**
	 * 
	 * <p>
	 * Description:testCountByUser<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testCountByUser() {
		User user1=new User();
		Employee e=new Employee();
		user.setEmpCode(e);
		userDao.count(user1);
		
		userDao.count(user);
		
	}

	/**
	 * 
	 * <p>
	 * Description:testGetLastModifyTim<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetLastModifyTime() {
		userDao.getLastModifyTime();
	}

	/**
	 * 
	 * <p>
	 * Description:testgetByLastModifyDate<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testgetByLastModifyDate() {
		userDao.getByLastModifyDate(null);
		userDao.getByLastModifyDate(new Date());
	}

	/**
	 * 
	 * <p>
	 * Description:测试getAll<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetByLoginName() {
		userDao.getByLoginName("许华龙");
	}

	/**
	 * 
	 * <p>
	 * Description:testUpdateUserPassword<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testUpdateUserPassword() {
		userDao.updateUserPassword(user);
	}

	/**
	 * 
	 * <p>
	 * Description:testUpdateLastLoginDate<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testUpdateLastLoginDate() {
		userDao.updateLastLoginDate(user);
		
	}

	/**
	 * 
	 * <p>
	 * Description:testGetById<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetById() {
		userDao.getById("1");
		
	}

	/**
	 * 
	 * <p>
	 * Description:testQueryAllByDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testQueryAllByDeptId() {
		
		userDao.queryAllByDeptId("238217", 10, 1);
	}

	/**
	 * 
	 * <p>
	 * Description:testCountbyDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testCountbyDeptId() {
		userDao.count("238217");
	}

	/**
	 * 
	 * <p>
	 * Description:testGetUserRoleFunDeptById<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetUserRoleFunDeptById() {
		userDao.getUserRoleFunDeptById("22476");
	}

	/**
	 * 
	 * <p>
	 * Description:testGetManagerUserIdByDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetManagerUserIdByDeptId() {
		userDao.getManagerUserIdByDeptId("22476");
	}

	/**
	 * 
	 * <p>
	 * Description:testGetUsersByDeptIds<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testGetUsersByDeptIds() {
		List<String> ids=new ArrayList<String>();
		ids.add("238217");
		userDao.getUsersByDeptIds(ids);
	}

	/**
	 * 
	 * <p>
	 * Description:testQueryUsersByDeptAndRole<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */

	@Test
	public void testQueryUsersByDeptAndRole() {
		userDao.queryUsersByDeptAndRole("1", "1");
	}

	/**
	 * 
	 * <p>
	 * Description:testGetManagerEmployeeIdByDeptId<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-12-26 void
	 */
	@Test
	public void testGetManagerEmployeeIdByDeptId() {
		userDao.getManagerEmployeeIdByDeptId("238217");
	}
	/**
	 * 同步UUMS 数据，进行相应的数据同步，权限分配
	 * @author ZhangZW
	 * @update 2014-1-6 
	 * @param empCode changeType postion orgId
	 * @return
	 */
	@Test
	public void testUserSync() {
		/*String empCode="T13320";
		String changeType="UPDATE";
		String postion="大领导";
		String orgId="11333";*/
		/*String empCode="T13320";
		String changeType="DELETE";
		String postion="大领导";
		String orgId="11333";*/
		String empCode="010999";
		String changeType="INSERT";
		String postion="大区总经理";
		String orgId="611";
		userDao.syncUser(empCode, changeType, postion, orgId);
	}
}