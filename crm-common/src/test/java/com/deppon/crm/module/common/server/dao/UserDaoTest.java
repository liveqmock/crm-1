/**
 * @description
 * @author 赵斌
 * @2012-3-20
 * @return
 */
package com.deppon.crm.module.common.server.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.IUserDao;
import com.deppon.crm.module.authorization.server.dao.impl.UserDao;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.organization.server.dao.IDepartmentDao;
import com.deppon.crm.module.organization.server.dao.IEmployeeDao;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.organization.server.dao.impl.EmployeeDao;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**
 * @author Administrator
 * 
 */
public class UserDaoTest extends TestCase {
	private static ApplicationContext ctx = null;
	private IUserDao userDao;

	private IDepartmentDao departmentDao;

	private IEmployeeDao employeeDao;
	
	String[] xmls = new String[]{"UseBeanTest.xml"};
	
	
	protected void setUp() throws Exception {
		try {
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			userDao = ctx.getBean(UserDao.class);
			departmentDao = ctx.getBean(DepartmentDao.class);
			employeeDao = ctx.getBean(EmployeeDao.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetManagerUserIdByDeptId() {
		String userId = userDao.getManagerUserIdByDeptId("732");
		Assert.assertEquals(userId, "21741");
	}
	@Test
	public void testGetUsersByDeptIds() {
		List<String> deptIds = departmentDao.getAllChildIdById("368");
		List<User> users = userDao.getUsersByDeptIds(deptIds);
		Assert.assertTrue(users.size() > 0);
	}
	@Test
	public void testGetAll() {
		User user = new User();
		Employee employee = new Employee();
		employee.setEmpName("%黄%");
		user.setEmpCode(employee);
		List<User> users = userDao.getAll(user, 20, 0);
		Assert.assertTrue(users.size() > 0);
	}
	@Test
	public void testGetById() {
		User user = userDao.getById("229492");
		System.out.println(user.getEmpCode().getEmpName());
	}
	@Test
	public void testQueryUsersByDeptAndRole() {
		userDao.queryUsersByDeptAndRole("49708", "3");
	}
	@Test
	public void testGetEmpById() {
		this.employeeDao.getEmpById("25972");
	}
	@Test
	public void testGetUserRoleFunDeptById() {
		User user = userDao.getByLoginName("074544");
		user = userDao.getUserRoleFunDeptById(user.getId());
	}
	@Test
	public void testGetManagerEmployeeIdByDeptId() {
		String userId = userDao.getManagerEmployeeIdByDeptId("123779");
//		Assert.assertEquals(userId, "43483");
	}
}
