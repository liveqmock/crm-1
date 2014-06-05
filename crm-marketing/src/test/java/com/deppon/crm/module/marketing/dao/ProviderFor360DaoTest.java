/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360DaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author Administrator
 * @version 0.1 2012-4-24
 */
package com.deppon.crm.module.marketing.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.impl.ProviderFor360Dao;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ProviderFor360DaoTest.java
 * @package com.deppon.crm.module.marketing.dao
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */

public class ProviderFor360DaoTest {
//	private ProviderFor360Dao dao;
//
//	/**
//	 * <p>
//	 * Description:这里写描述<br />
//	 * </p>
//	 * 
//	 * @author 苏玉军
//	 * @version 0.1 2012-4-24
//	 * @throws java.lang.Exception
//	 *             void
//	 */
//	@Before
//	public void setUp() throws Exception {
//		dao = SpringTestHelper.get().getBean(ProviderFor360Dao.class);
//	}
//
//	/**
//	 * <p>
//	 * Description:这里写描述<br />
//	 * </p>
//	 * 
//	 * @author 苏玉军
//	 * @version 0.1 2012-4-24
//	 * @throws java.lang.Exception
//	 *             void
//	 */
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testGetLastestMaintainInfo() {
//		String custId = "1862";
//		ReturnVisit rv = dao.getLastestMaintainInfo(custId);
//		Assert.assertNull(rv);
//
//		custId = "525267";
//		rv = dao.getLastestMaintainInfo(custId);
//		Assert.assertNotNull(rv);
//	}
//
//	@Test
//	public void testGetPlanScheduleDetail() {
//		String custId = "525267"; 
//		List<ReturnVisit> list = new ArrayList<ReturnVisit>();
//		list = dao.getPlanScheduleDetail(custId);
//		Assert.assertNotNull(list);
//	}
//
//	@Test
//	public void testGetVisitRecords() {
//		String custId = "525267";
//		List<ReturnVisit> list = new ArrayList<ReturnVisit>();
//		list = dao.getVisitRecords(custId);
//		Assert.assertNotNull(list);
//	}
}
