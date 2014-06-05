package com.deppon.crm.module.common.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.organization.server.dao.IDepartmentDao;
import com.deppon.crm.module.organization.server.dao.impl.DepartmentDao;
import com.deppon.crm.module.organization.shared.domain.Department;

public class DepartmentDaoTest extends TestCase {
	private IDepartmentDao departmentDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };

	@Before
	public void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			departmentDao = ctx.getBean(DepartmentDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetByLikeDeptName() {
		departmentDao.getByLikeDeptName("%大区");
	}

	@Test
	public void testGetByLikeDeptNameRow() {
		departmentDao.getByLikeDeptNameRow("%大区", 5);
	}

	@Test
	public void testGetDepartment() {
		departmentDao.getDeptByCode("J03020502");
	}

	@Test
	public void testGetByLikeDeptNameByDeptId() {
		departmentDao.getByLikeDeptNameByDeptId("%大区", "49708");
	}

	@Test
	public void testGetByLikeDeptNameByDeptCode() {
		departmentDao.getByLikeDeptNameByDeptCode("%大区", "J01030206");
		departmentDao.getByLikeDeptNameByDeptCode("%大区", null);
	}

	@Test
	public void testGetBizDeptByDeptId() {
		departmentDao.getAllBizDept("265");
		departmentDao.getAllBizDeptList("265");
		departmentDao.getBizDeptByDeptId("265", "10723");
	}

	@Test
	public void testGetAll() {
		List<Department> depList = departmentDao.getAll(new Integer(0),
				new Integer(0));
		int retInt = 0;
		if (null != depList && depList.size() > 0) {
			retInt = depList.size();
		}
		// assertEquals(, retInt);
		assertNotNull(depList);
	}
	
	@Test
	public void testQueryDeptByNameAndStandardCode() {
		departmentDao.queryDeptByNameAndStandardCode("%事业部", "DP00017");
	}
	
}
