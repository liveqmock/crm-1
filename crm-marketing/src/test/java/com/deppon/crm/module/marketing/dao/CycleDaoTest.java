/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author Administrator
 * @version 0.1 2012-4-13
 */
package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.dao.ICycleDao;
import com.deppon.crm.module.marketing.server.dao.impl.CycleDao;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author 苏玉军
 * @version 0.1 2012-4-13
 */

public class CycleDaoTest {
	private ICycleDao cycleDao;
	private final int START=1;
	private final int LIMIT=10;
	private User user;
	List<String> ids = new ArrayList<String>();
	String deptId = "11469";
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @throws java.lang.Exception
	 * void
	 */
	@Before
	public void setUp() throws Exception {
		cycleDao = (ICycleDao)SpringTestHelper.get().getBean(CycleDao.class);
		ids.add("400053889");
		ids.add("400043998");
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @throws java.lang.Exception
	 * void
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.dao.impl.CycleDao#searchReturnVisitRecords(java.lang.String)}.
	 */
	@Test
	public void testSearchReturnVisitRecords() {
		String custId="15451";
		List<ReturnVisit> list=cycleDao.searchReturnVisitRecords(custId);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void  testSearchCustFromCycle(){
		List<ScheduleQueryResultObject> list = cycleDao.searchCustFromCycle(ids, START, LIMIT, deptId);
		Assert.assertNotNull(list);
		for(ScheduleQueryResultObject obj:list){
			System.out.println(obj);
		}
	}
	
	@Test
	public void testCountSearchCustFromCycle(){
		try {
			int i = cycleDao.countForSearchCustFromCycle(ids, deptId);
			Assert.assertNotNull(i);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryLastTimeByCustNum(){
		String custNum = "400053889";
		Date date1 = new Date();
		Date date = cycleDao.queryLastTimeByCustNum(custNum, deptId,date1);
//		Assert.assertNotNull(date);
	}
	
	@Test
	public void testQueryDeliverPathAnalysis(){
		String custNum = "400053889";
		List list = cycleDao.queryDeliverPathAnalysis(custNum, new Date());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testQueryReceivePathAnalysis(){
		String custNum = "400053889";
		List list = cycleDao.queryReceivePathAnalysis(custNum, new Date());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testQueryPathAnalysis(){
		String custNum = "400053889";
		List<PathAnalysisInfo> result = cycleDao.queryExpressReceivePathAnalysis(custNum,new Date());
		Assert.assertNotNull(result);
		
	}
}
