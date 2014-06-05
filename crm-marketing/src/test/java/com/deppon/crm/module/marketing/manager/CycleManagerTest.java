/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author Administrator
 * @version 0.1 2012-4-16
 */
package com.deppon.crm.module.marketing.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.ICycleManager;
import com.deppon.crm.module.marketing.server.manager.impl.CycleManager;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.foss.framework.exception.GeneralException;



public class CycleManagerTest {
	private ICycleManager cycleManager;
	private User user;
	private final int START=1;
	private final int LIMIT=10;
	List<String> ids = new ArrayList<String>();
	@Before
	public void setUp() throws Exception {
		cycleManager = SpringTestHelper.get().getBean(CycleManager.class);
		user = DataUtilTest.getUser();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CycleManager#searchCycleList(com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail, int, int)}.
	 */
	@Test
	public void testSearchCycleList() {
		CustomerGroupDetail condition =null;
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map = cycleManager.searchCycleList(condition, 1, 20,new User());
		} catch (GeneralException e) {
			e.printStackTrace();
			try {
				condition = new CustomerGroupDetail();
				map =  cycleManager.searchCycleList(condition, 1, 20,new User());
			} catch (GeneralException e1) {
				e1.printStackTrace();
				// 测试数据库中数据量不足，可查询2012-7-10 编码：20100608-03496759  部门：11431
				condition.setCustNumber("20100608-03496759");
				condition.setDeptId("11431");
				Date d = new Date();
				d = DateUtils.setMonths(d , 7);
				d = DateUtils.setDays(d,10);
				condition.setQueryDate(d);
//				condition = DataUtilTest.getCustomerGroupDetail();
				map =  cycleManager.searchCycleList(condition, 0, 20,new User());
				Assert.assertNotNull(map);
			}
		}
	}
	@Test
	public void testSearchCustFromCycle(){
		try {
			List<ScheduleQueryResultObject> list = cycleManager.searchCustFromCycle(null, START,LIMIT, user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		
		
		try {
			List<ScheduleQueryResultObject> list = cycleManager.searchCustFromCycle(ids, START,LIMIT, user);
			Assert.assertNotNull(list);
		} catch (GeneralException e) {
			e.printStackTrace();
			ids.add("99998");
			ids.add("99999");
			user =  DataUtilTest.getUser();
			List<ScheduleQueryResultObject> list = cycleManager.searchCustFromCycle(ids, START,LIMIT, user);
			Assert.assertNotNull(list);
		}
		
	}
	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CycleManager#searchReturnVisitRecords(java.lang.String)}.
	 */
	@Test
	public void testSearchReturnVisitRecords() {
		cycleManager.searchReturnVisitRecords("613");
	}

	/**
	 * Test method for {@link com.deppon.crm.module.marketing.server.manager.impl.CycleManager#searPathAnalysisInfos(java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testSearPathAnalysisInfos() {
		// 可用编码 40000179, 可用时间6/10
		Date d = new Date(112, 6, 10);
		String membNumber = "40000179";
		try {
			// 会员编码为空
			cycleManager.searPathAnalysisInfos(null, d, "1","");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// 时间为空
			cycleManager.searPathAnalysisInfos(membNumber, null, "1","");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// 类型为空
			cycleManager.searPathAnalysisInfos(membNumber, d, null,"");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// 发货类型
			cycleManager.searPathAnalysisInfos(membNumber, d, "1","");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			// 到货类型
			cycleManager.searPathAnalysisInfos(membNumber, d, "0","");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}
	
	@Test
	public void testCountForSearchCustFromCycle(){
		ids.add("121");
		cycleManager.countForSearchCustFromCycle(ids, user);
	}
	
	@Test
	public void testGetDateDay(){
		CycleManager cm = new CycleManager();
		cm.getDateDay(null);
		cm.getDateDay(new Date());
	}
	
	
//	@Test
//	public void testGetFunction(){
//		CycleManager cm = new CycleManager();
//		cm.getCustomerGroupManager();
//		cm.getCustomerOperate();
//		cm.getCycleService();
//	}
	
	@Test
	public void testPathAnlys(){
		String custNumber = "20081021-012977";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DATE ,28);
		Date date =  cal.getTime();
		 List<PathAnalysisInfo> list = cycleManager.searPathAnalysisInfos(custNumber, date, "0","");
		 Assert.assertNotNull(list);
	}

}
