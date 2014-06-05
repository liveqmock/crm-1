package com.deppon.crm.module.common.server.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;

public class DepartmentServiceTest extends TestCase {

	private IDepartmentService departmentService;
	String[] xmls = new String[] { "DaoBeanTest.xml" };
	ClassPathXmlApplicationContext factory;

	@Override
	protected void setUp() throws Exception {
		try {
			factory = new ClassPathXmlApplicationContext(xmls);
			departmentService = (IDepartmentService) factory
					.getBean("departmentService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetBigAreaList() {
		List<Department> list = departmentService.getBigAreaList();
		for (Department department : list) {
			System.out.println(department.getDeptName());
		}
	}

	public void testGetBigAreaListByName() {
		List<Department> list = departmentService.getBigAreaListByName("成都");
		for (Department department : list) {
			System.out.println(department.getDeptName());
		}
	}

	public void testGetBigAreaByDeptId() {
		Department dept = departmentService.getBigAreaByDeptId("10800");
		if (dept != null) {
			System.out.println(dept.getDeptName());
		} else {
			System.out.println("kong bu meng");
		}
	}

	public void testGetBigAreaByDeptCode() {
		Department dept = departmentService
				.getBigAreaByDeptCode("W011306060103");
		if (dept != null) {
			System.out.println(dept.getDeptName());
		} else {
			System.out.println("kong bu meng");
		}
	}

	public void testGetBizDeptList() {
		List<Department> list = departmentService.getBizDeptList();
		for (Department department : list) {
			System.out.println(department.getDeptName());
		}
	}

	public void testGetBizDeptByDeptId() {
		Department dept = departmentService.getBizDeptByDeptId("10800");
		if (dept != null) {
			System.out.println(dept.getDeptName());
		} else {
			System.out.println("kong bu meng");
		}
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

}
