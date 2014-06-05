/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author ZhuPJ
 * @version 0.1 2012-3-29
 */
package com.deppon.crm.module.marketing.dao;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao;
import com.deppon.crm.module.marketing.server.dao.impl.MonitorPlanDao;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author ZhuPJ
 * @version 0.1 2012-3-29
 */
public class MonitorPlanTest {
//	public IMonitorPlanDao monitorPlanDao;
//	
//	@Before
//	public void setUp() throws Exception {
//		monitorPlanDao = (IMonitorPlanDao) SpringTestHelper.get().getBean(
//				MonitorPlanDao.class);		
//	}
//	
//	@Test
//	public void testSearchMonitorPlanDetail(){
//		Date start = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 2);
//		Date end = DateUtils.setDays(new Date(), 28);
//		MonitorPlanQueryCondition condition = new MonitorPlanQueryCondition();
//		condition.setPlanStartDate(start);
//		condition.setPlanOverDate(end);
//		condition.setExecdeptIds(new String[]{"49708"});
//		condition.setExecuserId("18706");
//		condition.setPlanId("143");
//		condition.setPlanStatus("20");
//		condition.setPlanType("dev");
//		
//		List<?> list = monitorPlanDao.getDevMonitorDetail(condition, 0, 10);
//		Assert.assertNotNull(list);
//		monitorPlanDao.getDevMonitorDetailCount(condition);
//		
//		
//		condition.setPlanStartDate(start);
//		condition.setPlanOverDate(end);
//		condition.setExecdeptId("11469");
//		condition.setExecuserId("18706");
//		condition.setPlanStatus("20");
//		condition.setPlanType("mat");
//		 
//		list = monitorPlanDao.getMatMonitorDetail(condition, 0, 10);
//		Assert.assertNotNull(list);
//		monitorPlanDao.getMatMonitorDetailCount(condition);
//	}
//
//	@Test
//	public void testSearchMonitorPlanList(){
//		Date start = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 2);
//		Date end = DateUtils.setDays(new Date(), 28);
//		MonitorPlanQueryCondition condition = new MonitorPlanQueryCondition();
//		condition.setPlanStartDate(start);
//		condition.setPlanOverDate(end);
//		condition.setExecdeptIds(new String[]{"11469","49709"});
//		condition.setExecuserId("18706");
//		condition.setPlanType("dev");
//		
//		List<MonitorPlan> list = monitorPlanDao.searchMonitorList(condition);
//		
//		for (int i=0;i<list.size();i++){
//			MonitorPlan mp = list.get(i);
//			int total = mp.getTotal();
//			int execute = mp.getExecute();
//			double d = 0;
//			DecimalFormat df = new DecimalFormat("0.00%");
//			if (total != 0){
//				d = (execute/(float)total);
//			}else{
//				d = 0;
//			}
//			
//			mp.setCompletionRate(df.format(d));
//		}
//		
//		Assert.assertNotNull(list);
//		
//		
//	}
//	
}
