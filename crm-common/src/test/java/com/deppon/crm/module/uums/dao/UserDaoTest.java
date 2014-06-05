//package com.deppon.crm.module.uums.dao;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext; 
//import com.deppon.crm.module.uums.server.dao.ICompanyDao;
//import com.deppon.crm.module.uums.server.dao.IEmployeeDao;
//import com.deppon.crm.module.uums.server.dao.IUserDao;
//import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
//import com.deppon.crm.module.uums.server.dao.Imp.EmployeeDaoImp;
//import com.deppon.crm.module.uums.server.dao.Imp.UserDaoImp;
//import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
//import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
//import com.deppon.crm.module.uums.shared.domain.UserInfo;
//
///**作者：zouming
// *创建时间：2012-12-26
// *最后修改时间：2012-12-26
// *描述：
// */
//public class UserDaoTest {
//	private static ApplicationContext factory;
//	private IUserDao userDao;
//	private UserInfo userInfo;
//	@Before
//	public void setUp(){
//		String[] resource = new String[] { "authorization/virtualpositionBeanTest.xml" };
//		//String[] resource = new String[] { "crm-baseNew/src/main/resources/com/deppon/crm/module/uums/server/META-INF/spring.xml" };
//		if (factory == null) {
//			factory = new ClassPathXmlApplicationContext(resource);
//		}
//		userDao = factory.getBean(UserDaoImp.class);
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
//		userDao.insert(userInfo);
//	}
//	@Test
//	public void update(){
//		/*userInfo=new UserInfo();
//		userInfo.setDesPassword("pppp");
//		userInfo.setTemp(false);
//		userInfo.setEmpName("pppp");
//		userInfo.setGender("1");
//		userInfo.setInvalidDate(new Date());
//		userInfo.setLastModifyTime(new Date());
//		userInfo.setOrgBenchmarkCode("ppp");
//		userInfo.setOrgCode("pppp");
//		userInfo.setOrgName("ppp");
//		userInfo.setPosition("1");
//		userInfo.setSysName("pppp");
//		userInfo.setTemp(true);
//		userInfo.setTheChangeId("pppp");
//		userInfo.setTheMainCode("67");
//		userInfo.setTheMainId("1");
//		userInfo.setValidDate(new Date());
//		userInfo.setWorkCode("pppp");
//		userDao.updateByCode(userInfo, "1");
//		*/
//		userInfo=new UserInfo();
//		userInfo.setDesPassword("UUUU");
//		userInfo.setTemp(false);
//		userInfo.setEmpName("UUUUU");
//		userInfo.setGender("2");
//		userInfo.setInvalidDate(new Date());
//		userInfo.setLastModifyTime(new Date());
//		userInfo.setOrgBenchmarkCode("UUUU");
//		userInfo.setOrgCode("UUU");
//		userInfo.setOrgName("UUU");
//		userInfo.setPosition("2");
//		userInfo.setSysName("UUUU");
//		userInfo.setTemp(false);
//		userInfo.setTheChangeId("UUUU");
//		userInfo.setTheMainCode("67");
//		userInfo.setTheMainId("2");
//		userInfo.setValidDate(new Date());
//		userInfo.setWorkCode("uuuuuu");
//		userDao.updateByCode(userInfo, "1");
//	}
//	@Test
//	public void delete(){
//		userDao.deleteByUserCode("67");
//	}
//	@Test
//	public void select(){
//		userInfo=userDao.searchByUserId("80001002");
//		//userInfo=userDao.searchByUserCode("67");
//		System.out.println(userInfo.getGender());
//	}
//}
