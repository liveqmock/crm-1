package com.deppon.crm.module.common.server.dao;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.organization.server.dao.ISearchDeptAndEmployeeDao;
import com.deppon.crm.module.organization.server.dao.impl.SearchDeptAndEmployeeDaoImpl;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;

public class SearchDeptAndEmployeeDaoTest extends TestCase{
	private ISearchDeptAndEmployeeDao searchDeptAndEmployeeDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };
	@Override
	protected void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			searchDeptAndEmployeeDao = ctx.getBean(SearchDeptAndEmployeeDaoImpl.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * .
	 * <p>
	 * 测试countDeptByName<br/>
	 * 方法名：testCountDeptByName
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-24
	 * @since JDK1.6
	 */
	public void testCountDeptByName() {
		String deptName = "上海";
		Assert.assertTrue((searchDeptAndEmployeeDao.countDeptByName(deptName))>= 0);
		Assert.assertTrue((searchDeptAndEmployeeDao.countDeptByName(null))==null);
	}
	
	/**
	 * .
	 * <p>
	 * 测试countEmpByCondition<br/>
	 * 方法名：testCountEmpByCondition
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-24
	 * @since JDK1.6
	 */
	public void testCountEmpByCondition() {
		SearchEmpAndDeptCondition condition = new SearchEmpAndDeptCondition();
		Assert.assertTrue((searchDeptAndEmployeeDao.countEmpByCondition(null))>= 0);
		Assert.assertTrue((searchDeptAndEmployeeDao.countEmpByCondition(condition))>= 0);
		condition.setEmpCode("089499");
		Assert.assertTrue((searchDeptAndEmployeeDao.countEmpByCondition(condition))==1);
		condition.setDeptName("%上海%");
//		Assert.assertTrue((searchDeptAndEmployeeDao.countEmpByCondition(condition))==0);
	}
	
	/**
	 */
	public void testSearchDeptStructure(){
		String standCode = "";
		List<?> l = searchDeptAndEmployeeDao.searchDeptStructure(standCode);
		Assert.assertTrue(l.size() == 0);
		standCode = "DP00461"; // 营业部
		Assert.assertTrue(searchDeptAndEmployeeDao.searchDeptStructure(
				standCode).size() == 1);
		standCode = "DP00460"; // 营业区
//		Assert.assertTrue(searchDeptAndEmployeeDao.searchDeptStructure(
//				standCode).size() > 1);
	}
}
