/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMonitorPlanService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.server.service;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMonitorPlanService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */

public interface IMonitorPlanService {

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
	 List<MonitorPlan> searchMonitorList(MonitorPlanQueryCondition condition);

	/**
	 * <p>
	 * 查询监控明细信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-9
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MonitorPlanDetail>
	 */
	 List<MonitorPlanDetail> getMonitorDetail(MonitorPlanQueryCondition condition,int start, int limit);
	
	/**
	 * 
	 * <p>
	 * 查询监控明细总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-25
	 * @param condition
	 * @return
	 * int
	 */
	 int getMonitorDetailCount(MonitorPlanQueryCondition condition);

}
