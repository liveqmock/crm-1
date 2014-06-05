/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DepartmentDaoImplTest.java
 * @package com.deppon.crm.module.scheduler.server.dao.impl 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */
package com.deppon.crm.module.scheduler.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.job.BeanContextHelper;
import com.deppon.crm.module.scheduler.server.dao.IDepartmentDao;
import com.deppon.crm.module.scheduler.server.dao.ISyncDepartmentDao;
import com.deppon.crm.module.scheduler.server.dao.impl.DepartmentDaoImpl;
import com.deppon.crm.module.scheduler.server.dao.impl.SyncDepartmentDao;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;
import com.deppon.crm.module.scheduler.shared.domain.DepartmentExt;
import com.deppon.crm.module.scheduler.shared.domain.OaDepartmentEntity;

public class SyncDepartmentDaoTest{

	private ISyncDepartmentDao departmentDao;
	private OaDepartmentEntity entity = new OaDepartmentEntity();
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 May 4, 2012
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		departmentDao = (SyncDepartmentDao) SpringTestHelper.get().getBean(SyncDepartmentDao.class);
		entity.setCreateDate(new Date());
		entity.setCreateUser("043260");
		entity.setDesc("描述");
		entity.setEndDate(new Date());
		entity.setFax("02131350806");
		entity.setLinkTel("02131350806");
		entity.setManagerId("043260");
		entity.setModifyDate(new Date());
		entity.setModifyUser("000000");
		entity.setOrgAddr("上海市青浦区");
		entity.setOrgClose("0");
		entity.setOrgCode("12322");
		entity.setOrgLevel(5);
		entity.setOrgName("北京啦啦啦营业部");
		entity.setOrgSeq("12131313.113");
		entity.setParentComCode("北京事业部");
		entity.setParentOrgId("123456");
		entity.setStartDate(new Date());
		entity.setZipCode("201702");
		entity.setSortNo(2);
	}

	@Test
	public void testQueryOADepartments() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.MONTH, -3);
		Date date  = cal.getTime();
		List<OaDepartmentEntity> list = departmentDao.queryUpdateListByTime(date);
		System.out.println(list.size());
	}
	
	@Test
	public void testCount(){
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.MONTH, -3);
		Date date  = cal.getTime();
		int total = departmentDao.countRecord(date);
		System.out.println(total);
	}
	
	@Test
	public void testCountDeptById(){
		String id =".103.104.220220.16233.16235.";
		int i = departmentDao.countDepartmentByDeptSeq(id);
		Assert.assertEquals(1, i);
	}
	
	@After
	public void tearDown() throws Exception {
		String createUser ="043260";
		boolean b = departmentDao.deleteByCreateUser(createUser);
		//Assert.assertTrue(b);
	}
	@Test
	public void testInsert(){
		boolean b = departmentDao.insertDept(entity);
		Assert.assertTrue(b);
	}
	
	@Test
	public void testUpdateDept(){
		departmentDao.insertDept(entity);
		entity.setDesc("描述呀描述");
		boolean b = departmentDao.updateDept(entity);
		Assert.assertTrue(b);
	}
	
	@Test
	public void testQueryDeptById(){
		int orgId=10350;
		OaDepartmentEntity dept =  departmentDao.queryDeptById(orgId);
		Assert.assertNotNull(dept);
		
		orgId=333333;
		dept =  departmentDao.queryDeptById(orgId);
		Assert.assertNull(dept);
	}
	@Test
	public void testGetgetSysDate(){
		Date d = departmentDao.getSysDate();
		System.out.println(d);
		Assert.assertNotNull(d);
	}
}
