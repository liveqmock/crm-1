/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author 043260
 * @version 0.1 2014年4月22日
 */
package com.deppon.crm.module.marketing.dao;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao;
import com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author 043260
 * @version 0.1 2014年4月22日
 */

public class MonitorPlanDaoTest {
	private IMonitorPlanDao monitorPlanDao;
	private MonitorPlanQueryCondition condition = null;

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年4月22日
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		monitorPlanDao = SpringTestHelper.get().getBean(MonitorPlanDao.class);
		condition = new MonitorPlanQueryCondition();
		condition.setExecdeptId("11469");
		condition.setPlanOverDate(new Date());
		condition.setPlanStartDate(new Date());
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao#searchMonitorList(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)}.
	 */
	@Test
	public void testSearchMonitorList() {
		List<MonitorPlan> list = monitorPlanDao.searchMonitorList(condition);
		Assert.assertNotNull(list);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao#getDevMonitorDetail(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition, int, int)}.
	 */
//	@Test
//	public void testGetDevMonitorDetail() {
//		List<MonitorPlanDetail>  list = monitorPlanDao.getDevMonitorDetail(condition, 0, 10);
//		Assert.assertNotNull(list);
//	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao#getDevMonitorDetailCount(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)}.
	 */
//	@Test
//	public void testGetDevMonitorDetailCount() {
//		int i = monitorPlanDao.getDevMonitorDetailCount(condition);
//	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao#getMatMonitorDetail(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition, int, int)}.
	 */
	@Test
	public void testGetMatMonitorDetail() {
		monitorPlanDao.getMatMonitorDetail(condition, 0, 10);
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao#getMatMonitorDetailCount(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)}.
	 */
	@Test
	public void testGetMatMonitorDetailCount() {
		monitorPlanDao.getMatMonitorDetailCount(condition);
	}

}
