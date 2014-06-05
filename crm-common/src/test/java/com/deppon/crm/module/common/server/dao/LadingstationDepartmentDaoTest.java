/**   
 * @title LadingstationDepartmentDaoTest.java
 * @package com.deppon.crm.module.common.server.dao
 * @description what to do
 * @author 安小虎
 * @update 2012-3-23 上午8:22:28
 * @version V1.0   
 */
package com.deppon.crm.module.common.server.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BusDeptSearchCondition;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.organization.shared.domain.Department;

/**
 * @description 始发网点与受理部门关系DAO测试用例
 * @author 安小虎
 * @version 0.1 2012-3-23
 * @date 2012-3-23
 */

public class LadingstationDepartmentDaoTest extends TestCase {

	ClassPathXmlApplicationContext factory;
	private ILadingstationDepartmentDao ldDao;
	private LadingstationDepartment ld;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		String resource = "LadingstationDepartmentDaoBeanTest.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		ldDao = (ILadingstationDepartmentDao) factory
				.getBean("ladingstationDepartmentDao");

		ld = new LadingstationDepartment();
		ld.setCreateUser("1");
		ld.setModifyUser("1");
		ld.setCreateDate(new Date());
		ld.setModifyDate(new Date());
		BussinessDept dept1 = new BussinessDept();
		dept1.setId("33");
		BussinessDept dept2 = new BussinessDept();
		dept1.setId("34");
		ld.setBeginLading(dept1);
		ld.setAcceptDept(dept2);
		ld.setIfReceive("YES");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		this.ldDao.delete(ld.getId());
	}

	
	
	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#save(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testSave() {
		assertNotNull(this.ldDao);
		this.ldDao.save(ld);
	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#update(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdate() {
		assertNotNull(this.ldDao);
		this.ldDao.save(ld);

		this.ldDao.update(ld);
	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#delete(java.lang.String)}
	 * 的测试方法。
	 */
	@Test
	public void testGetById() {
		assertNotNull(this.ldDao);
		this.ldDao.save(ld);

		this.ldDao.getById(ld.getId());
	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#searchByCondition(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testSearchByCondition() {
		Date beforeCreate=new Date();
		assertNotNull(this.ldDao);
		this.ldDao.save(ld);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Date tomorrow=cal.getTime();
		
		ld.setCreateDateBegin(beforeCreate);
		ld.setCreateDateEnd(tomorrow);
		List<LadingstationDepartment> list=ldDao.searchByCondition(ld,0,999);
		assertNotNull(list);
		assertTrue(list.size()==1);
		
		List<LadingstationDepartment> all=ldDao.searchByCondition(new LadingstationDepartment(), 0, 25);
		assertNotNull(all);
		assertTrue(all.size()>0);
	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#getArriveBusDeptByName(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testGetArriveBusDeptByName() {
		assertNotNull(this.ldDao);
		String deptName = "上海";
		BusDeptSearchCondition con = new BusDeptSearchCondition();
		con.setIfArrive(true);
		con.setDeptName(deptName);
		List<BussinessDept> list = ldDao.getArriveBusDeptByName(con);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				assertTrue(list.get(i).getDeptName().indexOf(deptName) >= 0);
				assertTrue(list.get(i).getIfArrive());
			}
		}
	}

	@Test
	public void testGetTopDeptByName() {
		assertNotNull(this.ldDao);
		String deptName = "上海";
		BusDeptSearchCondition con = new BusDeptSearchCondition();
		con.setStart(0);
		con.setLimit(10);
		con.setIfVehicleTem(true);
		con.setDeptName(deptName);
		List<BussinessDept> list = ldDao.getLeaveBusDeptByName(con);
		
		System.out.println("testGetTopDeptByName");

	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#getArriveBusDeptByName(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
//	@Test
//	public void testGetLeaveBusDeptByName() {
//		assertNotNull(this.ldDao);
//		String deptName = "上海";
//		BusDeptSearchCondition con = new BusDeptSearchCondition();
//		con.setStart(0);
//		con.setLimit(10);
//		con.setIfLeave(true);
//		con.setDeptName(deptName);
//		List<BussinessDept> list = ldDao.getLeaveBusDeptByName(con);
//		if (list != null && list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				assertTrue(list.get(i).getDeptName().indexOf(deptName) >= 0);
//				assertTrue(list.get(i).getIfLeave());
//			}
//		}
//	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#getBusDeptById(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testGetBusDeptById() {
		assertNotNull(this.ldDao);

		BussinessDept dept = ldDao.getBusDeptById("8");

	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#getBusDeptByName(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testGetBusDeptByName() {
		assertNotNull(this.ldDao);
		String deptName = "上海车队1";
		BussinessDept dept = ldDao.getBusDeptByName(deptName);

	}
	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#getBusDeptByErpId(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	@Test
	public void testGetBusDeptByErpId() {
		assertNotNull(this.ldDao);
		String erpId = "WLQ6lAElEADgAF2owKgCZcznrtQ=";
		BussinessDept dept = ldDao.getBusDeptByErpId(erpId);
//		assertNotNull(dept);
		
	}
	
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
		assertNotNull(this.ldDao);
		String standardCode = "DP01421";
		BussinessDept dept = ldDao.getBusDeptByStandardCode(standardCode);
		assertNotNull(dept);
	}

	/**
	 * {@link com.deppon.crm.module.common.server.dao.impl.LadingstationDepartmentDao#getBusDeptByName(com.deppon.crm.module.common.shared.domain.LadingstationDepartment)}
	 * 的测试方法。
	 */
	public void testGetLandingStationDepartment() {
		List<LadingstationDepartment> ladList = ldDao
				.getAllLandingStationDepartment(0, 25);


	}
	
	@Test
	public void testGetAllLandingStationDepartmentCount(){
		assertTrue(ldDao.getAllLandingStationDepartmentCount()>0);
	}
	

	@Test
	public void testSearchExistsByCondition(){
		LadingstationDepartment ls = new LadingstationDepartment();
		ls.setIfReceive("YES");
		ls.setResource("CALLCENTER");
		Department d = new Department();
		d.setId("270");
		ls.setBeginLadingDeptN(d);
		Department a = new Department();
		a.setId("156777");
		ls.setAcceptDeptN(a);
		int i = ldDao.searchExistsLadingstationByCondition(ls);

		ls.setId("40001864");
		i = ldDao.searchExistsLadingstationByCondition(ls);
		System.out.println(i);
	}
	@Test
	public void testgetCountByCondition(){
		LadingstationDepartment ladingstationDepartment = new LadingstationDepartment();
		ladingstationDepartment.setId("110");
		ldDao.getCountByCondition(ladingstationDepartment);
	}
	@Test
	public void testgetAcceptDeptByConfigurator(){
		String deptId = "110";
		boolean isReceiveGoods = false;
		String resource = "110";
		ldDao.getAcceptDeptByConfigurator(deptId, isReceiveGoods, resource);
		isReceiveGoods = true;
		ldDao.getAcceptDeptByConfigurator(deptId, isReceiveGoods, resource);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：保存网点信息
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	@Test
	public void testsaveLadingstationDepartment(){
		Area area = new Area();
		area.setId("111");
		area.setCreateDate(new Date());
		area.setCreateUser("createUser");
		area.setModifyDate(new Date());
		area.setModifyUser("modifyUser");
		area.setFid(new BigDecimal(1)); 
		area.setAreaID(new BigDecimal(1));
		area.setIsCityLevel("isCityLevel");
		area.setLastModifyName("lastModifyName"); 
		area.setCityName("cityName");
		area.setCityId(Integer.valueOf("1"));
		area.setProvinceId(Integer.valueOf("1"));
		area.setName("name");
		area.setNumber("number");
		area.setStatus("status");
		City city = new City();
		city.setId("111");
		city.setCreateDate(new Date());
		city.setCreateUser("111");
		city.setModifyDate(new Date());
		city.setModifyUser("111");
		city.setFid(new BigDecimal(1));
		city.setCityID(new BigDecimal(1));
		city.setName("name");
		city.setNumber("number");
		city.setPinyin("pinyin");
		city.setPyjm("pyjm");
		city.setStatus("status");
		city.setCityNumber("cityNumber");
		city.setIsDirCity(Integer.valueOf("1"));
		city.setProvinceId(Integer.valueOf("1"));
		city.setProvinceName("provinceName");
		city.setLastModifyName("lastModifyName");
		Province province = new Province();
		province.setId("111");
		province.setCreateDate(new Date());
		province.setCreateUser("111");
		province.setModifyDate(new Date());
		province.setModifyUser("111");
		province.setFid(new BigDecimal(1));
		province.setProvinceID(new BigDecimal(1));
		province.setName("name");
		province.setNumber("number");
		province.setPinyin("pinyin");
		province.setPyjm("pyjm");
		province.setStatus("status");
		province.setLastModifyName("lastModifyName");
		BussinessDept bussinessDept = new BussinessDept();
//		bussinessDept.setId("111");
		bussinessDept.setCreateDate(new Date());
		bussinessDept.setCreateUser("1111");
		bussinessDept.setModifyDate(new Date());
		bussinessDept.setModifyUser("111");
		bussinessDept.setDeptName("deptName");
		bussinessDept.setDeptCode("deptCode");
		bussinessDept.setProvince(province);
		bussinessDept.setCity(city);
		bussinessDept.setIfHashGoodsType(true);
		bussinessDept.setRegion(area);
		bussinessDept.setDeptAddress("deptAddress");
		bussinessDept.setDeptContext("deptContext");
		bussinessDept.setContactMethod("contactMethod");
		bussinessDept.setIfOutField(true);
		bussinessDept.setIfTransit(true);
		bussinessDept.setIfEnable(true);
//		bussinessDept.setBelongArea(Department);
		bussinessDept.setIfOpen(true);
		bussinessDept.setOrganizeId(Integer.valueOf("1"));
		bussinessDept.setIfArrive(true);
		bussinessDept.setIfLeave(true);
		bussinessDept.setIfHomeDelivery(true);
		bussinessDept.setIfSelfDelivery(true);
		bussinessDept.setIfOutward(true);
		bussinessDept.setIfVehicleTeam(true);
		bussinessDept.setIfHavePDA(true);
		bussinessDept.setErpId("erpId");
		bussinessDept.setIfDivision(true);
		bussinessDept.setIfBigRegion(true);
		bussinessDept.setIfSmallRegion(true);
		bussinessDept.setIfBussinessDept(true);
		bussinessDept.setDepCoordinate("depCoordinate");
		bussinessDept.setDeliveryCoordinate("deliveryCoordinate");
		
		ldDao.saveLadingstationDepartment(bussinessDept);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据ERPID修改网点信息(现为FOSS的ID)
	 * @参数：FOSS组织信息或FOSS营业部信息(FOSS的营业部信息是延伸于FOSS组织信息，根据标杆编码关联)
	 * */
	@Test
	public void testupdateLadingstationDepartment(){
		Area area = new Area();
		area.setId("111");
		area.setCreateDate(new Date());
		area.setCreateUser("createUser");
		area.setModifyDate(new Date());
		area.setModifyUser("modifyUser");
		area.setFid(new BigDecimal(1));
		area.setAreaID(new BigDecimal(1));
		area.setIsCityLevel("isCityLevel");
		area.setLastModifyName("lastModifyName");
		area.setCityName("cityName");
		area.setCityId(Integer.valueOf("1"));
		area.setProvinceId(Integer.valueOf("1"));
		area.setName("name");
		area.setNumber("number");
		area.setStatus("status");
		City city = new City();
		city.setId("111");
		city.setCreateDate(new Date());
		city.setCreateUser("111");
		city.setModifyDate(new Date());
		city.setModifyUser("111");
		city.setFid(new BigDecimal(1));
		city.setCityID(new BigDecimal(1));
		city.setName("name");
		city.setNumber("number");
		city.setPinyin("pinyin");
		city.setPyjm("pyjm");
		city.setStatus("status");
		city.setCityNumber("cityNumber");
		city.setIsDirCity(Integer.valueOf("1"));
		city.setProvinceId(Integer.valueOf("1"));
		city.setProvinceName("provinceName");
		city.setLastModifyName("lastModifyName");
		Province province = new Province();
		province.setId("111");
		province.setCreateDate(new Date());
		province.setCreateUser("111");
		province.setModifyDate(new Date());
		province.setModifyUser("111");
		province.setFid(new BigDecimal(1));
		province.setProvinceID(new BigDecimal(1));
		province.setName("name");
		province.setNumber("number");
		province.setPinyin("pinyin");
		province.setPyjm("pyjm");
		province.setStatus("status");
		province.setLastModifyName("lastModifyName");
		BussinessDept bussinessDept = new BussinessDept();
		bussinessDept.setId("800001542");
		bussinessDept.setCreateDate(new Date());
		bussinessDept.setCreateUser("1111");
		bussinessDept.setModifyDate(new Date());
		bussinessDept.setModifyUser("111");
		bussinessDept.setDeptName("deptName");
		bussinessDept.setDeptCode("deptCode");
		bussinessDept.setProvince(province);
		bussinessDept.setCity(city);
		bussinessDept.setIfHashGoodsType(true);
		bussinessDept.setRegion(area);
		bussinessDept.setDeptAddress("deptAddress");
		bussinessDept.setDeptContext("deptContext");
		bussinessDept.setContactMethod("SDFASFASDF");
		bussinessDept.setIfOutField(true);
		bussinessDept.setIfTransit(true);
		bussinessDept.setIfEnable(true);
//		bussinessDept.setBelongArea(Department);
		bussinessDept.setIfOpen(true);
		bussinessDept.setOrganizeId(Integer.valueOf("1"));
		bussinessDept.setIfArrive(true);
		bussinessDept.setIfLeave(true);
		bussinessDept.setIfHomeDelivery(true);
		bussinessDept.setIfSelfDelivery(true);
		bussinessDept.setIfOutward(true);
		bussinessDept.setIfVehicleTeam(true);
		bussinessDept.setIfHavePDA(true);
		bussinessDept.setErpId("erpId");
		bussinessDept.setIfDivision(false);
		bussinessDept.setIfBigRegion(false);
		bussinessDept.setIfSmallRegion(false);
		bussinessDept.setIfBussinessDept(false);
		bussinessDept.setDepCoordinate("depCoordinate1");
		bussinessDept.setDeliveryCoordinate("deliveryCoordinate1");
		ldDao.updateLadingstationDepartment(bussinessDept);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2013-1-31
	 * @描述：根据ERPID查询网点信息(现为FOSS的ID)
	 * @参数：ERPID(现为FOSS的ID)
	 * @返回：网点信息
	 * */
	@Test
	public void testqueryBussinessDeptByERPID(){
//		BussinessDept  dept = ldDao.queryBussinessDeptByERPID("erpId");
//		System.out.println(dept);
	}
} 
