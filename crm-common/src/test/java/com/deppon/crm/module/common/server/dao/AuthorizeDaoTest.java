package com.deppon.crm.module.common.server.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.server.dao.IAuthorizeDao;
import com.deppon.crm.module.organization.shared.domain.Department;

public class AuthorizeDaoTest {
	private IAuthorizeDao authorizeDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			authorizeDao = ctx.getBean(IAuthorizeDao.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllleafByParentId() {
		List<String> lisStrings = new ArrayList<String>();
		lisStrings.clear();
		lisStrings.add("68716");
		lisStrings.add("464253");
//		lisStrings.add("464254");
		List<Department> list = authorizeDao.getAllleafByParentId(lisStrings, 0, Integer.MAX_VALUE);
//		for (Department department : list) {
//			System.out.println(department.getId() + "bbb");
//			System.out.println(department.getDeptName() + "aa");
//		}
		assertNotNull(list);
	}
	@Test
	public void testCountAllleafByParentId() {
		List<String> lisStrings = new ArrayList<String>();
		lisStrings.clear();
		lisStrings.add("68716");
		lisStrings.add("464253");
//		lisStrings.add("464254");
		int i = authorizeDao.countAllleafByParentId(lisStrings);
//		System.out.println(i + "aaaaaaaaaaaaaaaaaa");
		assertNotNull(i);
	}
	
	@Test
	public void testGetAllleafByParentIdAndLikeDeptName() {
		List<String> lisStrings = new ArrayList<String>();
		lisStrings.clear();
		lisStrings.add("687161111111");
		lisStrings.add("464253");
//		lisStrings.add("464254");
		String name = "本部";
		List<Department> list = authorizeDao.getAllleafByParentIdAndLikeDeptName(lisStrings,name,0,Integer.MAX_VALUE);
		assertNotNull(list);
	}
	
	@Test
	public void testCountAllleafByParentIdAndLikeDeptName() {
		List<String> lisStrings = new ArrayList<String>();
		lisStrings.clear();
//		lisStrings.add("464253");
		lisStrings.add("464253");
		lisStrings.add("464254");
		String name = "本部";
		int i = authorizeDao.countAllleafByParentIdAndLikeDeptName(lisStrings,name);	
		assertNotNull(i);
	}
}
