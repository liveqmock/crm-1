package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
public class BaseDataDaoTest extends BeanUtil{

	@Before
	public void setUp(){
		
	}

	@Test
	public void testGetBankCityByBankProvinceId(){
		String string = "wwNiEgEuEADgA4V6wKjeATcYh+k=";
		List<Map<String, String>> list = baseDataDao.getBankCityByBankProvinceId(string);
		Assert.assertNotNull(list);
	}
	@Test
	public void testGetBankProvince(){
		List<Map<String, String>> list = baseDataDao.getBankProvince();
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testGetAccountBank(){
		List<Map<String, String>> list = baseDataDao.getAccountBank();
		Assert.assertNotNull(list);
	}
	@Test
	public void testQueryAuthrityBusnessDept(){
		List list = baseDataDao.queryAuthrityBusnessDept("DP12780");
		Assert.assertNotNull(list);
	}
	@Test
	public void testQueryAuthorityBusinessDeptByName(){
		List list = baseDataDao.queryAuthorityBusinessDeptByName("DP00073", 0, 10);
		Assert.assertTrue(listIsNull(list));
		User user = new User();
		Employee e = new Employee();
		Department d = new Department();
		d.setStandardCode("DP12743");
		d.setDeptName("上海浦东大道营业部");
		e.setDeptId(d);
		user.setEmpCode(e);
		UserContext.setCurrentUser(user);
		list = baseDataDao.queryAuthorityBusinessDeptByName("上海", 0, 10);
		Integer count = baseDataDao.countAuthorityBusinessDeptByName("上海");
		Assert.assertFalse(listIsNull(list));
		Assert.assertNotNull(count);
	}
	@Test
	public void testQueryPointDepartment(){
		List list = baseDataDao.queryPointDepartment("DP00635");
		Assert.assertFalse(listIsNull(list));
	}
	private boolean listIsNull(List list){
		if(list==null||list.size()==0)
			return true;
		return false;
	}
}

