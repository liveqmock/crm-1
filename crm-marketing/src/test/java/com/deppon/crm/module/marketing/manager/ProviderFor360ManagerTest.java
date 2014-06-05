/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360ManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-4-24
 */
package com.deppon.crm.module.marketing.manager;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.manager.impl.ProviderFor360Manager;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360ManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */

public class ProviderFor360ManagerTest {
	private ProviderFor360Manager manager;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		manager = SpringTestHelper.get().getBean(ProviderFor360Manager.class);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.ProviderFor360Manager#getLastestMaintainInfo(java.lang.String)}.
	 */
	@Test
	public void testGetLastestMaintainInfo() {
		String custId = "10000";
		ReturnVisit rv = manager.getLastestMaintainInfo(custId);
		Assert.assertNull(rv);
		
		custId = "525";
		rv =  manager.getLastestMaintainInfo(custId);
//		Assert.assertNotNull(rv);
		
		custId = "200";
		rv =  manager.getLastestMaintainInfo(custId);
//		Assert.assertNull(rv);
		
		custId = "";
		rv = manager.getLastestMaintainInfo(custId);
//		Assert.assertNotNull(rv);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.ProviderFor360Manager#getPlanScheduleDetail(java.lang.String)}.
	 */
	@Test
	public void testGetPlanScheduleDetail() {
		String custId =  "";
		List<ReturnVisit> list = manager.getPlanScheduleDetail(custId);
		Assert.assertEquals(0, list.size());
	
		custId =  "525";
		list = manager.getPlanScheduleDetail(custId);
		//Assert.assertNotSame(0, list.size());
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.ProviderFor360Manager#getVisitRecords(java.lang.String)}.
	 */
	@Test
	public void testGetVisitRecords() {
		String custId =  "";
		List<ReturnVisit> list = manager.getVisitRecords(custId);
		Assert.assertEquals(0, list.size());
	
		custId =  "525";
		list = manager.getVisitRecords(custId);
//		Assert.assertNotSame(0, list.size());
	}
	@Test 
	public void testGetProviderFor360Service(){
		manager.getProviderFor360Service();
	}

}
