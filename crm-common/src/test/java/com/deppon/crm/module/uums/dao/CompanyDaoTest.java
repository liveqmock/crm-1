//package com.deppon.crm.module.uums.dao;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext; 
//import com.deppon.crm.module.uums.server.dao.ICompanyDao;
//import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
//import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
//
///**作者：zouming
// *创建时间：2012-12-26
// *最后修改时间：2012-12-26
// *描述：
// */
//public class CompanyDaoTest {
//	private static ApplicationContext factory;
//	private ICompanyDao companyDao;
//	private CompanyInfo companyInfo;
//	@Before
//	public void setUp(){
//		String[] resource = new String[] { "authorization/virtualpositionBeanTest.xml" };
//		//String[] resource = new String[] { "crm-baseNew/src/main/resources/com/deppon/crm/module/uums/server/META-INF/spring.xml" };
//		if (factory == null) {
//			factory = new ClassPathXmlApplicationContext(resource);
//		}
//		companyDao = factory.getBean(CompanyDaoImp.class);
//	}
//	@Test
//	public void insert(){
//		companyInfo=new CompanyInfo();
//		companyInfo.setBriefintro("454");
//		companyInfo.setTheMainId("111");
//		companyInfo.setTheMainCode("5454");
//		companyInfo.setFax1("5454");
//		companyInfo.setTheMainCode("4444");
//		companyInfo.setIsHasAccount("1");
//		companyInfo.setCompanyStandCode("7878");
//		companyInfo.setOwnerShareRate((double)122);
//		companyInfo.setWorkingUnit(false);
//		companyInfo.setSeal(false);
//		companyDao.insert(companyInfo);
//		
//	}
//	@Test
//	public void update(){
//		companyInfo=new CompanyInfo();
//		companyInfo.setBriefintro("5555");
//		//companyInfo.setTheMainId("5454");
//		companyInfo.setFax1("66666");
//		companyInfo.setTheMainCode("4444");
//		companyInfo.setCompanyStandCode("0000");
//		companyInfo.setIsHasAccount("1");
//		companyInfo.setOwnerShareRate((double)122);
//		companyInfo.setWorkingUnit(false);
//		companyInfo.setSeal(false);
//		companyDao.updateByCompanyCode(companyInfo, "4444");
//		/*companyInfo.setBriefintro("99999");
//		companyInfo.setFax1("99999");
//		companyInfo.setTheMainCode("99999");
//		companyInfo.setTheMainId("5454");
//		companyInfo.setCompanyStandCode("99999");
//		companyDao.updateByCompanyId(companyInfo, "111");*/
//	}
//	@Test
//	public void delete(){
////		companyInfo=new CompanyInfo();
////		companyInfo.setBriefintro("5555");
////		companyInfo.setTheMainId("5454");
////		companyInfo.setFax1("66666");
////		companyInfo.setTheMainCode("88888");
////		companyInfo.setCompanyStandCode("0000");
//		//companyDao.deleteByCode("4444");
//		companyDao.deleteById("111");
//	}
//	@Test
//	public void select(){
//		companyInfo=companyDao.searchByCode("4444");
//		System.out.println(companyInfo.getCompanyName());
//		//companyDao.deleteById("111");
//	}
//}
