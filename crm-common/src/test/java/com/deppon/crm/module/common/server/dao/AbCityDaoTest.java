package com.deppon.crm.module.common.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.AbCityDao;
import com.deppon.crm.module.common.shared.domain.AbCity;
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;

public class AbCityDaoTest extends TestCase{

	private IAbCityDao cityDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			cityDao = ctx.getBean(AbCityDao.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAbCity() {
		AbCitySearchCondition condition=new AbCitySearchCondition();
		List<AbCity> list=cityDao.getAbCity(condition);
		assertNotNull(list);
		condition.setLimit(1);
		condition.setStart(0);
		cityDao.getAbCity(condition);
		condition.setLimit(0);
		cityDao.getAbCity(condition);
	}

}
