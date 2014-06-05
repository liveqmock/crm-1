/**   
 * @title LadingstationDepartmentManagerTest.java
 * @package com.deppon.crm.module.common.server.manager
 * @description what to do
 * @author 潘光均
 * @update 2012-3-28 上午10:04:46
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.manager;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * @description fuction  
 * @author 潘光均
 * @version 0.1 2012-3-28
 *@date 2012-3-28
 */

public class LadingstationDepartmentManagerTest extends TestCase{

	static ClassPathXmlApplicationContext factory;
	private static ILadingstationDepartmentManager manager;
	private static User user;

	private static LadingstationDepartment ld = null;
	static {
		String resource = "LadingstationDepartmentDaoBeanTest.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		manager = (ILadingstationDepartmentManager) factory
				.getBean("ladingstationDepartmentManager");
		ld = new LadingstationDepartment();
		ld.setCreateUser("1");
		ld.setModifyUser("1");
		ld.setCreateDate(new Date());
		ld.setModifyDate(new Date());
		BussinessDept dept1=new BussinessDept();
		dept1.setId("33");
		BussinessDept dept2=new BussinessDept();
		dept1.setId("34");
		ld.setBeginLading(dept1);
		ld.setAcceptDept(dept2);
		ld.setIfReceive("YES");
		ld.setResource("ONLINE");
		user = new User();
		user.setId("106139");
		UserContext.setCurrentUser(user);
//		manager.save(ld);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
//	@Before
//	public void setUp() throws Exception {
//		
//	}
	/**
	 * @description function.  
	 * @author 潘光均
	 * @version 0.1 2012-3-28
	 * @param b true or false   
	 *@date 2012-3-28
	 * @return none
	 * @update 2012-3-28 上午10:04:46
	 */
//	@After
//	public void tearDown() throws Exception {
////		manager.delete(ld.getId());
//	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#getLadingstationDepartmentService()}.
	 */
	@Test
	public void testGetLadingstationDepartmentService() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#setLadingstationDepartmentService(com.deppon.crm.module.common.server.service.ILadingstationDepartmentService)}.
	 */
	@Test
	public void testSetLadingstationDepartmentService() {
//		fail("Not yet implemented");
	}

	
	public void testSave() {
		LadingstationDepartment ladingstationDepartment = new LadingstationDepartment();
		ladingstationDepartment.setResource("J"+new Date().getTime());
		manager.save(ladingstationDepartment);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#update(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}.
	 */
	
	public void testUpdate() {
		LadingstationDepartment ladingstationDepartment = new LadingstationDepartment();
		ladingstationDepartment.setResource("JJ"+new Date().getTime());
		ladingstationDepartment.setId("106139");
		manager.update(ladingstationDepartment);
	}
	public void testEdit(){
		LadingstationDepartment ladingstationDepartment = new LadingstationDepartment();
		Department beginLadingDeptN = new Department();
		ladingstationDepartment.setBeginLadingDeptN(beginLadingDeptN);
		beginLadingDeptN.setStandardCode("DEPPON");
		beginLadingDeptN.setId("106139");
		try{
			manager.edit(ladingstationDepartment);
		}catch(Exception e){}
		
	}
	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#delete(java.lang.String)}.
	 */
	
	public void testDelete() {
		String ladingstationDepartmentId = "106139";
		manager.delete(ladingstationDepartmentId);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#searchByCondition(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}.
	 */
	@Test
	public void testSearchByCondition() {
		LadingstationDepartment ladingstationDepartment = new LadingstationDepartment();
		Department beginLadingDeptN = new Department();
		ladingstationDepartment.setBeginLadingDeptN(beginLadingDeptN);
		beginLadingDeptN.setStandardCode("DEPPON");
		beginLadingDeptN.setId("106139");
		manager.searchByCondition(ladingstationDepartment, 0, 2);
	}

	public void testGetLeaveBusDeptByName() {
		BusDeptSearchCondition condtion = new BusDeptSearchCondition();
		condtion.setDeptName("南通通州区平潮营业部");
		long number = manager.getBussinessNumber(condtion);
		assertEquals(1, number);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#getArriveBusDeptByName(com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition)}.
	 */
	@Test
	public void testGetArriveBusDeptByName() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#getAcceptDeptByLeaveBusDeptName(java.lang.String)}.
	 */
	@Test
	public void testGetAcceptDeptByConfigurator() {
		assertNotNull(manager);
		String deptName="34";
		boolean isReceiveGoods = true;
		String resource ="ONLINE";
		List<LadingstationDepartment> dept = manager.getAcceptDeptByConfigurator(deptName,isReceiveGoods,resource);
		assertEquals(dept.size(), 0);
		
		deptName="230";
		dept = manager.getAcceptDeptByConfigurator(deptName,isReceiveGoods,resource);
		assertEquals(dept.size(), 0);
		
	}

	/**
	 * Test method for {@link com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager#getBusDeptById(java.lang.String)}.
	 */
	@Test
	public void testGetBusDeptByName() {
		
		BussinessDept dept = manager.getBusDeptById("11174");
		System.out.println(dept.getDeptCode());
		City city = dept.getCity();
		Province pro = dept.getProvince();
		Area reg = dept.getRegion();
//		assertNotNull(city);
//		assertNotNull(pro);
//		assertNotNull(reg);

//		assertNotNull(dept);
		manager.getBusDeptByName("11174");
		manager.getBusDeptByCode("11174");
		manager.getBusDeptByErpId("11174");
		manager.getAllLandingStationDepartment(0, 2);
		manager.getAllLandingStationDepartmentCount();

	}
	public void testcreateLadingstationDepartment(){
		String startNetId = "106139";
		String acceptDept = "106139";
		String orderResource = "JONE";
		String ifReceive = "0";
		try{
			manager.createLadingstationDepartment(startNetId, acceptDept, orderResource, ifReceive, user);
		}catch(Exception e){}
		manager.getDepartmentByDeptName(acceptDept);
		manager.invalidLadingstationRelation(acceptDept);
		manager.getBusDeptByDeptId(acceptDept);
	}
	public void testGetBusDeptByCode() {
		BussinessDept dept = manager.getBusDeptByCode("DP08272");
		assertEquals(dept.getDeptName(), "南通通州区平潮营业部");
	}
	
//	@Test
//	public void testGetBusDeptByErpId() {
//		assertNotNull(manager);
//		String erpId = "WLQ6lAElEADgAF2owKgCZcznrtQ=";
//		BussinessDept dept = manager.getBusDeptByErpId(erpId);
//		assertNotNull(dept);
//		
//	}
	
	/**
	 * 
	 * <p>
	 * Description:测试：根据标杆编码查询营业部门<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-1-8
	 * void
	 */
	@Test
	public void testGetBusDeptByStandardCode() {
		assertNotNull(manager);
		String standardCode = "DP01421";
		BussinessDept dept = manager.getBusDeptByStandardCode(standardCode);
		assertNotNull(dept);
	}
	
	/**
	 * @description 通过部门id查询网点信息.  
	 * @author 潘光均
	 * @version 0.1 2012-6-7
	 * @param 
	 *@date 2012-6-7
	 * @return BussinessDept
	 * @update 2012-6-7 下午4:52:59
	 */
	@Test
	public void testGetBusDeptByDeptId(){
		BussinessDept dept = manager.getBusDeptByDeptId("11469");
		assertNotNull(dept);
	}
}
