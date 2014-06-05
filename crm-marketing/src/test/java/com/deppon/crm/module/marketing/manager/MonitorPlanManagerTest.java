/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author ZhuPJ
 * @version 0.1 2012-4-5
 */
package com.deppon.crm.module.marketing.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IMonitorPlanManager;
import com.deppon.crm.module.marketing.server.manager.impl.MonitorPlanManager;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanManagerTest.java
 * @package com.deppon.crm.module.marketing.manager 
 * @author ZhuPJ
 * @version 0.1 2012-4-5
 */

public class MonitorPlanManagerTest {
	private IMonitorPlanManager monitorPlanManager;
	User user;
	

	@Before
	public void setUp() throws Exception {
		monitorPlanManager = (IMonitorPlanManager) SpringTestHelper.get().getBean(MonitorPlanManager.class);

		user = new User();
		user.setId("1");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("238217", null);
		map.put("1", null);
		map.put("250218", null);
		map.put("22241", null);
		map.put("26238", null);
		Set<String> ids = map.keySet();
		user.setDeptids(ids);
		Employee emp = new Employee();
		emp.setId("1");
		Department dept=new Department();
		dept.setId("1");
		emp.setDeptId(dept);
		user.setEmpCode(emp);
	}

	@Test
	public void testSearchMonitorPlanList() {
		MonitorPlanQueryCondition condition = new MonitorPlanQueryCondition();
		Date start = DateUtils.setMonths(DateUtils.setDays(new Date(), 1), 2); // 3/1
		Date end = DateUtils.setMonths(DateUtils.setDays(new Date(), 15), 2); // 3/15
		try {
			// 查询日期超过3个月
			end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 6); // 7/30
			condition.setPlanStartDate(start);
			condition.setPlanOverDate(end);

			List<?> list = monitorPlanManager.searchMonitorList(condition,user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
		
		try {
			// 查询日期未超过3个月
			end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 4); // 5/30
			condition.setPlanStartDate(start);
			condition.setPlanOverDate(end);
			// 查询条件全为空
			condition.setExecdeptId("");
			condition.setExecuserId("");
			condition.setTopic("");
			condition.setPlanStatus("");
			
			List<?> list = monitorPlanManager.searchMonitorList(condition,user);
		} catch (GeneralException e) {
			// TODO: handle exception
		}

		try {
			// 查询日期未超过3个月
			end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 4); // 5/30
			condition.setPlanStartDate(start);
			condition.setPlanOverDate(end);
			
			condition.setExecdeptId("");
			condition.setExecuserId("");
			condition.setPlanStatus("10");
			condition.setTopic("");
			condition.setPlanType("dev");
			
			List<?> list = monitorPlanManager.searchMonitorList(condition,user);
			Assert.assertNotNull(list);
		} catch (GeneralException e) {
			// TODO: handle exception
		}

		try {
			// 查询日期未超过3个月
			end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 4); // 5/30
			condition.setPlanStartDate(start);
			condition.setPlanOverDate(end);
			
			condition.setExecdeptId("1");
			condition.setExecuserId("");
			condition.setPlanStatus("10");
			condition.setTopic("");
			condition.setPlanType("dev");
			
			List<?> list = monitorPlanManager.searchMonitorList(condition,user);
			Assert.assertNotNull(list);
		} catch (GeneralException e) {
			// TODO: handle exception
		}

		try {
			// 查询日期未超过3个月
			end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 4); // 5/30
			condition.setPlanStartDate(start);
			condition.setPlanOverDate(end);
			
			condition.setExecdeptId("1");
			condition.setExecuserId("");
			condition.setPlanStatus("10");
			condition.setTopic("每日");
			condition.setPlanType("mat");
			
			List<?> list = monitorPlanManager.searchMonitorList(condition,user);
			Assert.assertNotNull(list);
		} catch (GeneralException e) {
			// TODO: handle exception
		}
	}
	

//	@Test
//	public void testSearchMonitorPlanDetail() {
//		Date start = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 3);
//		Date end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 5);
//		MonitorPlanQueryCondition condition = new MonitorPlanQueryCondition();
//		condition.setPlanStartDate(start);
//		condition.setPlanOverDate(end);
//		condition.setExecdeptIds(new String[]{"11333"});
////		condition.setExecuserId("76993");
////		condition.setPlanId("143");
//		condition.setPlanStatus("40");
//		condition.setPlanType("dev");
//		condition.setExecdeptId("11333");
//		
//		HashMap<String, Object> map = monitorPlanManager.getMonitorDetail(
//				condition, 0, 10,user);
//		
//		List<?> list= (List<MonitorPlanDetail>) map.get("mplist");
//		Integer totalCount = (Integer) map.get("mpcount");
//		Assert.assertNotNull(list);
//		
//		condition.setTopic("测试");
//		map = monitorPlanManager.getMonitorDetail(
//				condition, 0, 10,user);
//		
//		try {
//			// 查询日期大于3个月
//			start = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 5);
//			end = DateUtils.setMonths(DateUtils.setDays(new Date(), 28), 10);
//			condition.setPlanStartDate(start);
//			condition.setPlanOverDate(end);
//			map = monitorPlanManager.getMonitorDetail(
//					condition, 0, 10,user);
//		} catch (GeneralException e) {
//			// TODO: handle exception
//		}
//		
//	}
}
