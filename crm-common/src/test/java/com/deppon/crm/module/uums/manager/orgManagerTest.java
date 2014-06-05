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
//import com.deppon.crm.module.uums.server.manager.impl.SyncUumsOrgInfoManager;
//import com.deppon.crm.module.uums.server.manager.impl.syncFacedManager;
//import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
//import com.deppon.crm.module.uums.shared.domain.OrgInfo;
//
///**作者：zouming
// *创建时间：2012-12-26
// *最后修改时间：2012-12-26
// *描述：
// */
//public class orgManagerTest {
//	private static ApplicationContext factory;
//	private ISyncUumsDataManager orgManager;
//	private OrgInfo orgInfo;
//	@Before
//	public void setUp(){
//		String[] resource = new String[] { "authorization/virtualpositionBeanTest.xml" };
//		//String[] resource = new String[] { "crm-baseNew/src/main/resources/com/deppon/crm/module/uums/server/META-INF/spring.xml" };
//		if (factory == null) {
//			factory = new ClassPathXmlApplicationContext(resource);
//		}
//		orgManager = factory.getBean(syncFacedManager.class);
//	}
//	@Test
//	public void insert(){
//		orgInfo=new OrgInfo();
//		orgInfo.setAreaCode("py");
//		orgInfo.setBigArea(false);
//		orgInfo.setCareerDept(false);
//		orgInfo.setChangeDate(new Date());
//		orgInfo.setCostCenterCode("112");
//		orgInfo.setCostCenterName("ppu");
//		orgInfo.setDeptAttribute("pp");
//		orgInfo.setDeptShortName("py");
//		orgInfo.setDisplayOrder("45");
//		orgInfo.setEhrDeptCode("456");
//		orgInfo.setInvalidDate(new Date());
//		orgInfo.setLeaf(false);
//		orgInfo.setOrgAddress("zzj");
//		orgInfo.setOrgAttribute("y");
//		orgInfo.setOrgCity("py");
//		orgInfo.setOrgCountry("ch");
//		orgInfo.setOrgDesc("dee");
//		orgInfo.setOrgFax("fax");
//		orgInfo.setOrgEmail("email");
//		orgInfo.setOrgLevel("1");
//		orgInfo.setOrgManager("zpj");
//		orgInfo.setOrgName("name");
//		orgInfo.setOrgPhone("phone");
//		orgInfo.setOrgProvince("hn");
//		orgInfo.setSubCompName("dp");
//		orgInfo.setOrgSeq("seq");
//		orgInfo.setTheMainCode("23");
//		orgInfo.setTheMainId("1");
//		orgInfo.setValidDate(new Date());
//		orgInfo.setChangeType(SyncUumsOrgInfoManager.INSERT);
//		orgManager.check(orgInfo);
//	}
//	@Test
//	public void update(){
//		orgInfo=new OrgInfo();
//		orgInfo.setAreaCode("222");
//		orgInfo.setBigArea(false);
//		orgInfo.setCareerDept(false);
//		orgInfo.setChangeDate(new Date());
//		orgInfo.setCostCenterCode("222");
//		orgInfo.setCostCenterName("222");
//		orgInfo.setDeptAttribute("222");
//		orgInfo.setDeptShortName("222");
//		orgInfo.setDisplayOrder("222");
//		orgInfo.setEhrDeptCode("222");
//		orgInfo.setInvalidDate(new Date());
//		orgInfo.setLeaf(false);
//		orgInfo.setOrgAddress("222");
//		orgInfo.setOrgAttribute("222");
//		orgInfo.setOrgCity("222");
//		orgInfo.setOrgCountry("222");
//		orgInfo.setOrgDesc("2");
//		orgInfo.setOrgFax("22222");
//		orgInfo.setOrgEmail("2");
//		orgInfo.setOrgLevel("2");
//		orgInfo.setOrgManager("2222");
//		orgInfo.setOrgName("2");
//		orgInfo.setOrgPhone("2222");
//		orgInfo.setOrgProvince("2222");
//		orgInfo.setSubCompName("222");
//		orgInfo.setOrgSeq("2222");
//		orgInfo.setTheMainCode("23");
//		orgInfo.setTheMainId("1");
//		orgInfo.setValidDate(new Date());
//		orgInfo.setChangeType(SyncUumsOrgInfoManager.UPDATE);
//		orgManager.check(orgInfo);
//	}
//	@Test
//	public void delete(){
//		orgInfo=new OrgInfo();
//		orgInfo.setAreaCode("222");
//		orgInfo.setBigArea(false);
//		orgInfo.setCareerDept(false);
//		orgInfo.setChangeDate(new Date());
//		orgInfo.setCostCenterCode("222");
//		orgInfo.setCostCenterName("222");
//		orgInfo.setDeptAttribute("222");
//		orgInfo.setDeptShortName("222");
//		orgInfo.setDisplayOrder("222");
//		orgInfo.setEhrDeptCode("222");
//		orgInfo.setInvalidDate(new Date());
//		orgInfo.setLeaf(false);
//		orgInfo.setOrgAddress("222");
//		orgInfo.setOrgAttribute("222");
//		orgInfo.setOrgCity("222");
//		orgInfo.setOrgCountry("222");
//		orgInfo.setOrgDesc("2");
//		orgInfo.setOrgFax("22222");
//		orgInfo.setOrgEmail("2");
//		orgInfo.setOrgLevel("2");
//		orgInfo.setOrgManager("2222");
//		orgInfo.setOrgName("2");
//		orgInfo.setOrgPhone("2222");
//		orgInfo.setOrgProvince("2222");
//		orgInfo.setSubCompName("222");
//		orgInfo.setOrgSeq("2222");
//		orgInfo.setTheMainCode("23");
//		orgInfo.setTheMainId("1");
//		orgInfo.setChangeDate(new Date());
//		//orgInfo.setValidDate(new Date());
//		orgInfo.setChangeType(SyncUumsOrgInfoManager.DELETED);
//		orgManager.check(orgInfo);
//	}
//	@Test
//	public void select(){
//		//companyInfo=companyDao.searchByCode("4444");
//		//System.out.println(companyInfo.getCompanyName());
//		//companyDao.deleteById("111");
//	}
//}
