//package com.deppon.crm.module.uums.manager;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext; 
//import com.deppon.crm.module.uums.server.dao.ICompanyDao;
//import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
//import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
//import com.deppon.crm.module.uums.server.manager.impl.SyncUumsCompanyInfoManager;
//import com.deppon.crm.module.uums.server.manager.impl.syncFacedManager;
//import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
//
///**作者：zouming
// *创建时间：2012-12-26
// *最后修改时间：2012-12-26
// *描述：
// */
//public class SyncManagerTest {
//	private static ApplicationContext factory;
//	private ISyncUumsDataManager syncManager;
//	private CompanyInfo companyInfo;
//	@Before
//	public void setUp(){
//		String[] resource = new String[] { "authorization/virtualpositionBeanTest.xml" };
//		//String[] resource = new String[] { "crm-baseNew/src/main/resources/com/deppon/crm/module/uums/server/META-INF/spring.xml" };
//		if (factory == null) {
//			factory = new ClassPathXmlApplicationContext(resource);
//		}
//		syncManager = factory.getBean(syncFacedManager.class);
//	}
//	@Test
//	public void insert(){
//		companyInfo=new CompanyInfo();
//		companyInfo.setBriefintro("454");
//		companyInfo.setTheMainCode("5454");
//		companyInfo.setFax1("5454");
//		companyInfo.setTheMainCode("4444");
//		companyInfo.setCompanyStandCode("7878");
//		companyInfo.setChangeType(SyncUumsCompanyInfoManager.INSERT);
//		syncManager.check(companyInfo);
//	}
//	@Test
//	public void update(){
//		companyInfo=new CompanyInfo();
//		companyInfo.setBriefintro("5555");
//		companyInfo.setFax1("5555");
//		companyInfo.setTheMainCode("4444");
//		companyInfo.setCompanyStandCode("555555");
//		companyInfo.setChangeType(SyncUumsCompanyInfoManager.UPDATE);
//		syncManager.check(companyInfo);
//	}
//	@Test
//	public void delete(){
//		companyInfo=new CompanyInfo();
//		companyInfo.setBriefintro("5555");
//		companyInfo.setFax1("5555");
//		companyInfo.setTheMainCode("4444");
//		companyInfo.setCompanyStandCode("555555");
//		companyInfo.setChangeType(SyncUumsCompanyInfoManager.DELETE);
//		syncManager.check(companyInfo);
//	}
//	@Test
//	public void select(){
//		//companyInfo=companyDao.searchByCode("4444");
//		//System.out.println(companyInfo.getCompanyName());
//		//companyDao.deleteById("111");
//	}
//}
