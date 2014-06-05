/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SyncEmployeeDaoTest.java
 * @package com.deppon.crm.module.scheduler.dao 
 * @author Administrator
 * @version 0.1 2012-7-5
 */
package com.deppon.crm.module.scheduler.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.scheduler.server.dao.ISyncEmployeeDao;
import com.deppon.crm.module.scheduler.server.dao.impl.SyncEmployeeDao;
import com.deppon.crm.module.scheduler.service.testUtil.SpringTestHelper;
import com.deppon.crm.module.scheduler.shared.domain.OaEmployeeEntity;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title SyncEmployeeDaoTest.java
 * @package com.deppon.crm.module.scheduler.dao
 * @author 苏玉军
 * @version 0.1 2012-7-5
 */

public class SyncEmployeeDaoTest {
	OaEmployeeEntity e =  new OaEmployeeEntity();
	private ISyncEmployeeDao employeeDao;

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @throws java.lang.Exception
	 *             void
	 */
	@Before
	public void setUp() throws Exception {
		employeeDao = SpringTestHelper.get().getBean(SyncEmployeeDao.class);
		e.setId("123456789");
		e.setBirthday(new Date());
		e.setCreateDate(new Date());
		e.setCreateUser("043260");
		e.setEmpCode("143260");
		e.setEmpName("suyujun");
		e.setEmpStatus("1");
		e.setGender("1");
		e.sethAddress("上海市青浦区徐泾镇明珠路");
		e.sethTel("02131350806");
		e.sethZipCode("201702");
		e.setInDate(new Date());
		e.setJobName("软件开发工程师");
		e.setMobileNo("13917097761");
		e.setModifyDate(new Date());
		e.setModifyUser("043256");
		e.setoAddress("甘肃兰州");
		e.setoEmail("dpsyj@deppon.com");
		e.setOrgId(151217);
		e.setoTel("09315641217");
		e.setOutDate(new Date());
		e.setoZipCode("730010");
		e.setpEmail("605372707");
		e.setRemark("lalala");
		e.setWorkExp("CRM开发");
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-7-5
	 * @throws java.lang.Exception
	 *             void
	 */
	@After
	public void tearDown() throws Exception {
		String empCode = "143260";
		boolean b = employeeDao.deleteEmployee(empCode);
		Assert.assertTrue(b);
		employeeDao = null;
	}

	/**
	 * Test method for
	 * {@link com.deppon.crm.module.scheduler.server.dao.impl.SyncEmployeeDao#queryEmployeeByDate(java.util.Date)}
	 * .
	 */
	@Test
	public void testQueryEmployeeByDate() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(2012, 5, 1);
		Date date = cal.getTime();
		List<OaEmployeeEntity> list = employeeDao.queryEmployeeByDate(date);
		Assert.assertNotNull(list);
		System.out.println(list.size());
	}
	
	@Test
	public void testCountEmpByEmpCode(){
		String empCode = "043260";
		int i = employeeDao.countEmpByEmpCode(empCode);
		Assert.assertEquals(1,i);
		
		empCode="1233333";
		i = employeeDao.countEmpByEmpCode(empCode);
		Assert.assertEquals(0, i);
	}
	
	@Test
	public void testInsertEmployoee(){
		boolean b = employeeDao.insertEmployee(e);
		Assert.assertTrue(b);
	}
	@Test
	public void testUpdateEmployee(){
		boolean b = employeeDao.insertEmployee(e);
		Assert.assertTrue(b);
		e.setJobName("高级软件开发工程师");
		e.setMobileNo("11111111111");
		b = employeeDao.updateEmployee(e);
		Assert.assertTrue(b);
	}
}
