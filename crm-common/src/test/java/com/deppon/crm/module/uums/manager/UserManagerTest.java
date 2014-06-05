//package com.deppon.crm.module.uums.manager;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext; 
//import com.deppon.crm.module.uums.server.dao.ICompanyDao;
//import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
//import com.deppon.crm.module.uums.server.manager.ISyncUumsDataManager;
//import com.deppon.crm.module.uums.server.manager.impl.SyncUumsCompanyInfoManager;
//import com.deppon.crm.module.uums.server.manager.impl.SyncUumsUserInfoManager;
//import com.deppon.crm.module.uums.server.manager.impl.syncFacedManager;
//import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
//import com.deppon.crm.module.uums.shared.domain.UserInfo;
//
///**作者：zouming
// *创建时间：2012-12-26
// *最后修改时间：2012-12-26
// *描述：
// */
//public class UserManagerTest {
//	private static ApplicationContext factory;
//	private ISyncUumsDataManager syncManager;
//	private UserInfo userInfo;
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
//		userInfo=new UserInfo();
//		userInfo.setDesPassword("dfdfd");
//		userInfo.setTemp(false);
//		userInfo.setEmpName("rrr");
//		userInfo.setGender("1");
//		userInfo.setInvalidDate(new Date());
//		userInfo.setLastModifyTime(new Date());
//		userInfo.setOrgBenchmarkCode("tttt");
//		userInfo.setOrgCode("23");
//		userInfo.setOrgName("ppp");
//		userInfo.setPosition("bi");
//		userInfo.setSysName("crm");
//		userInfo.setTemp(true);
//		userInfo.setTheChangeId("787887");
//		userInfo.setTheMainCode("77");
//		userInfo.setValidDate(new Date());
//		userInfo.setWorkCode("wd");
//		userInfo.setChangeType(SyncUumsUserInfoManager.INSERT);
//		syncManager.check(userInfo);
//	}
//	@Test
//	public void update(){
//		userInfo=new UserInfo();
//		userInfo.setDesPassword("tttttt");
//		userInfo.setTemp(false);
//		userInfo.setEmpName("2222");
//		userInfo.setGender("2");
//		userInfo.setInvalidDate(new Date());
//		userInfo.setLastModifyTime(new Date());
//		userInfo.setOrgBenchmarkCode("yyyyyy");
//		userInfo.setOrgCode("23");
//		userInfo.setOrgName("tttttttttttttttttttttttttttt");
//		userInfo.setPosition("IT");
//		userInfo.setSysName("vbvb");
//		userInfo.setTemp(true);
//		userInfo.setTheMainCode("67");
//		userInfo.setValidDate(new Date());
//		userInfo.setWorkCode("222");
//		userInfo.setChangeType(SyncUumsUserInfoManager.UPDATE);
//		syncManager.check(userInfo);
//	}
//	@Test
//	public void delete(){
//		userInfo=new UserInfo();
//		userInfo.setDesPassword("dfdfd");
//		userInfo.setTemp(false);
//		userInfo.setEmpName("rrr");
//		userInfo.setGender("1");
//		userInfo.setInvalidDate(new Date());
//		userInfo.setLastModifyTime(new Date());
//		userInfo.setOrgBenchmarkCode("tttt");
//		userInfo.setOrgCode("rtrt");
//		userInfo.setOrgName("ppp");
//		userInfo.setPosition("1");
//		userInfo.setSysName("crm");
//		userInfo.setTemp(true);
//		userInfo.setTheChangeId("787887");
//		userInfo.setTheMainCode("67");
//		userInfo.setTheMainId("1");
//		userInfo.setValidDate(new Date());
//		userInfo.setWorkCode("wd");
//		userInfo.setChangeType(SyncUumsUserInfoManager.REUSED);
//		syncManager.check(userInfo);
//	}
//	@Test
//	public void select(){
//		//companyInfo=companyDao.searchByCode("4444");
//		//System.out.println(companyInfo.getCompanyName());
//		//companyDao.deleteById("111");
//	}
//}
