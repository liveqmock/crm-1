/**   
 * @title BaseModelDaoJdbcImplTest.java
 * @package com.deppon.crm.recompense.dao.impl
 * @description what to do
 * @author 潘光均
 * @update 2012-2-13 下午2:01:30
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.impl.RoleDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.dao.impl.BaseModelDaoImpl;
import com.deppon.crm.module.recompense.server.util.TestUtil;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;

/**
 * @description 实现xxx
 * @author 潘光均
 * @version 0.1 2012-2-13
 * @date 2012-2-13
 */

public class BaseModelDaoImplTest extends TestCase {
	static ClassPathXmlApplicationContext factory;
	private static BaseModelDaoImpl baseModelDao = null;
	private static RoleDao roleDao = null;
	private static DepartmentDao departmentDao = null;
	private static UserDao userDao = null;

	private UserRoleDeptRelation urd = null;

	private static Role role = null;
	private static Department dept = null;
	private static User user = null;

	static {
//		AppContext.initAppContext("application", "");
//		String resource = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml";
//		factory = new ClassPathXmlApplicationContext(resource);
//		baseModelDao = (BaseModelDaoImpl) factory.getBean("baseModelDao");
//		roleDao = (RoleDao) factory.getBean("roleDao");
//		departmentDao = (DepartmentDao) factory.getBean("departmentDao");
//		userDao = (UserDao) factory.getBean("userDao");
		baseModelDao = TestUtil.baseModelDao;
		roleDao = TestUtil.roleDao;
		departmentDao = TestUtil.departmentDao;
		userDao = TestUtil.userDao;
		/*role = roleDao.getAll().get(0);
		dept = departmentDao.getAll(null).get(0);
		user = userDao.getAll(null).get(0);
		原来代码需要把role dept user 全部查出来 数据量大
		*/
		role = roleDao.getById("1004");
		dept = departmentDao.getById("24237");
		user = userDao.getById("27929");
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		urd = new UserRoleDeptRelation();
		urd.setRole(role);
		urd.setDept(dept);
		urd.setUser(user);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		this.baseModelDao.deleteUserRoleDeptRelationById(urd.getId());
	}

	@SuppressWarnings("static-access")
	@Test
	public void testGetByUserId() {
		UserRoleDeptRelation urd01 = new UserRoleDeptRelation();
//		assertFalse(baseModelDao.insertUserRoleDepartment(urd01));
		//1.插入空值
//		urd01 = new UserRoleDeptRelation();
		assertTrue(baseModelDao.insertUserRoleDepartment(urd01));
		
		//2.urd不为空
		assertTrue(baseModelDao.insertUserRoleDepartment(urd));
		
		this.baseModelDao.getUserRoleDeptRelationByUserId(user.getId());
		this.baseModelDao.getUserRoleDeptRelationByUserId(null);
	}

	@SuppressWarnings("static-access")
	public void testGetAll() {
		assertTrue(baseModelDao.insertUserRoleDepartment(urd));
		this.baseModelDao.getAllUserRoleDeptRelation(0, 10);
	}

	@SuppressWarnings("static-access")
	public void testDeleteByUserId() {
		assertTrue(baseModelDao.insertUserRoleDepartment(urd));
		assertTrue(this.baseModelDao.deleteUserRoleDepartmentByUserId(urd
				.getUser().getId()));
		
		assertFalse(
				this.baseModelDao.
				deleteUserRoleDepartmentByUserId("888888888888"));
	}
	
	@Test
	public void testInsertUserRoleDepartment(){
		String roleId = "1004";
		String userId = "27929";
		
		//1.部门ID列表为空
		List<String> departIds = null;
		baseModelDao.insertUserRoleDepartment(userId,roleId,departIds);
		
		//2.部门ID列表不为空，但是长度为0
		departIds = new ArrayList<String>();
		baseModelDao.insertUserRoleDepartment(userId,roleId,departIds);
		
		//3.部门ID列表不为空，但是长度大于0
		departIds.add("24237");
		assertTrue(baseModelDao.insertUserRoleDepartment(userId,roleId,departIds));
		
		urd.setId(baseModelDao.getUserRoleDeptRelationByUserId("").get(0).getId());
		System.err.println(urd.getId());
	}
	
	@Test
	public void testGetUserRoleDeptRelationByDeptId(){
		String deptId = urd.getDept().getId();
		System.err.println(
				baseModelDao.getUserRoleDeptRelationByDeptId(deptId));
		urd.setId(baseModelDao.getUserRoleDeptRelationByUserId("").get(0).getId());
	}

}
