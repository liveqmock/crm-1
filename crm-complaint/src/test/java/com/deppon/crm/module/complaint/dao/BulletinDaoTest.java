package com.deppon.crm.module.complaint.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.BulletinDaoImpl;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;

public class BulletinDaoTest extends TestCase {
	private BulletinDaoImpl bulletinDao;
	private static ApplicationContext ctx = null;
	private static Bulletin bulletin = new Bulletin();
	
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			bulletinDao = ctx.getBean(BulletinDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		bulletin.setBulletinid(new BigDecimal(0103));
		bulletin.setCompid(new BigDecimal(17));
		bulletin.setBulletinname("CRM组员1");
		bulletin.setCreateuserid(new Integer(106));
		bulletin.setLastmodifyuserid(new BigDecimal(106));
	}

	@Test
	public void testSearchBulletinByCompId() {
		BigDecimal db=new BigDecimal(323);
		List<Bulletin> bulletinList=bulletinDao.searchBulletinByCompId(db);
		bulletinDao.searchBulletinToProcByCompId(db);
		System.out.println(bulletinList);
	}
	
	@Test
	public void testSaveBulletin() {
		List<Bulletin> bulletins = new ArrayList<Bulletin>();
//		bulletins.add(this.bulletin);
		Bulletin bull = new Bulletin();
		bull.setBulletinid(new BigDecimal(0105));
		bull.setCompid(new BigDecimal(17));
		bull.setBulletinname("CRM组员2");
		bull.setCreateuserid(new Integer(105));
		bull.setLastmodifyuserid(new BigDecimal(105));
		bull.setBulletinJobId("1205");
		bull.setBulletinDeptName("业务部门A");
		bull.setBulletinTel("62580000");
//		bull.setJobCode("134499802718111");
		bull.setJobCode(null);
		bull.setPosition("软件开发工程师");
		bull.setDealType("employee");
		bulletins.add(bull);
		int resultInt = 0;
		resultInt = bulletinDao.saveBulletin(bulletins);
		//assertEquals("insert one bulletin: ", 2, resultInt);	
	}

	@Test
	public void testSaveBulletin2() {
		List<Bulletin> bulletins = new ArrayList<Bulletin>();
//		bulletins.add(this.bulletin);
		Bulletin bull = new Bulletin();
		bull.setBulletinid(new BigDecimal(0105));
		bull.setCompid(new BigDecimal(17));
		bull.setBulletinname("CRM组员2");
		bull.setCreateuserid(new Integer(105));
		bull.setLastmodifyuserid(new BigDecimal(105));
		bull.setBulletinJobId("1205");
		bull.setBulletinDeptName("业务部门A");
		bull.setBulletinTel("62580000");
		bull.setJobCode("1344998027188");
		bull.setPosition("软件开发工程师");
		bull.setDealType("employee");
		bulletins.add(bull);
		int resultInt = 0;
		resultInt = bulletinDao.saveBulletin(bulletins);
		assertEquals("insert one bulletin: ", 1, resultInt);	
	}
	
	@Test
	public void testDeleteBulletinById(){
		BigDecimal id = new BigDecimal(1249);
		bulletinDao.deleteOldBulletinsByCompId(id);
	}
}
