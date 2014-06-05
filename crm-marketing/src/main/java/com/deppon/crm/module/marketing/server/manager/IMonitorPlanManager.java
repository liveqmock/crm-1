/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMonitorPlanManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.server.manager;

import java.util.HashMap;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;

/**   
 * <p>
 * Description:监控计划<br />
 * </p>
 * @title IMonitorPlanManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author ZhuPJ
 * @version 0.1 2012-3-27
 */

public interface IMonitorPlanManager {

	/**
	 * <p>
	 * Description: 查询监控信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-9
	 * @param condition
	 * @return
	 * List<MonitorPlan>
	 */
	 List<MonitorPlan> searchMonitorList(MonitorPlanQueryCondition condition, User user);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-9
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MonitorPlanDetail>
	 */
	 HashMap<String, Object> getMonitorDetail(MonitorPlanQueryCondition condition,int start, int limit, User user);
}
