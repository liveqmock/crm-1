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
//import com.deppon.crm.module.uums.server.dao.IPositionDao;
//import com.deppon.crm.module.uums.server.dao.IUserDao;
//import com.deppon.crm.module.uums.server.dao.Imp.CompanyDaoImp;
//import com.deppon.crm.module.uums.server.dao.Imp.EmployeeDaoImp;
//import com.deppon.crm.module.uums.server.dao.Imp.PositionDaoImp;
//import com.deppon.crm.module.uums.server.dao.Imp.UserDaoImp;
//import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
//import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
//import com.deppon.crm.module.uums.shared.domain.PostionInfo;
//import com.deppon.crm.module.uums.shared.domain.UserInfo;
//
///**作者：zouming
// *创建时间：2012-12-26
// *最后修改时间：2012-12-26
// *描述：
// */
//public class positionDaoTest {
//	private static ApplicationContext factory;
//	private IPositionDao positonDao;
//	private PostionInfo position;
//	@Before
//	public void setUp(){
//		String[] resource = new String[] { "authorization/virtualpositionBeanTest.xml" };
//		//String[] resource = new String[] { "crm-baseNew/src/main/resources/com/deppon/crm/module/uums/server/META-INF/spring.xml" };
//		if (factory == null) {
//			factory = new ClassPathXmlApplicationContext(resource);
//		}
//		positonDao = factory.getBean(PositionDaoImp.class);
//	}
//	@Test
//	public void insert(){
//		position=new PostionInfo();
//		position.setChangeDate(new Date());
//		position.setLastModifyTime(new Date());
//		position.setTheMainCode("45");
//		position.setTheMainId("1");
//		position.setPositionName("IT");
//		positonDao.insert(position);
//	}
//	@Test
//	public void update(){
//		position=new PostionInfo();
//		position.setChangeDate(new Date());
//		position.setLastModifyTime(new Date());
//		position.setTheMainCode("47");
//		position.setPositionName("yy");
//		position.setTheMainId("1");
//		positonDao.updateById(position, "12");
//		
//	}
//	@Test
//	public void delete(){
//		//positonDao.deleteByCode("45");
//		positonDao.deleteById("1");
//	}
//	@Test
//	public void select(){
//		position=positonDao.searchByName("IT");
//		System.out.println(position.getTheMainCode());
//	}
//}
