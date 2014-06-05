package com.deppon.crm.module.common.server.dao;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.dao.impl.HeadDao;

public class HeadDaoTest extends TestCase{
	private HeadDao headDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	
	@Override
	protected void setUp() throws Exception {
		try{
			if(ctx==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			headDao = ctx.getBean(HeadDao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void testGetAllHeadDetail(){
//		headDao.getAllHeadDetail();
	}
	
	public void testgetLastModifyTime(){
		headDao.getLastModifyTime();
	}
}
