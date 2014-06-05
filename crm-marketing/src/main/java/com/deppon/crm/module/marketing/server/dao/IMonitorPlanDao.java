/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMonitorPlanDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IMonitorPlanDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */

public interface IMonitorPlanDao {
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
//	 List<MonitorPlanDetail> getDevMonitorDetail(MonitorPlanQueryCondition condition, int start, int limit);
	/**
	 * 
	 * <p>
	 * 查询监控明细总数
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param condition
	 * @return
	 * int
	 */
//	 int getDevMonitorDetailCount(MonitorPlanQueryCondition condition);

	/**
	 * 
	 * <p>
	 * 分页查询监控明细
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<MonitorPlanDetail>
	 */
	 List<MonitorPlanDetail> getMatMonitorDetail(MonitorPlanQueryCondition condition, int start, int limit);
	/**
	 * 
	 * <p>
	 * 监控计划查询 
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-23
	 * @param condition
	 * @return
	 * int
	 */
	 int getMatMonitorDetailCount(MonitorPlanQueryCondition condition);
}
