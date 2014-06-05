/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao;
import com.deppon.crm.module.marketing.server.service.IMonitorPlanService;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */

public class MonitorPlanService implements IMonitorPlanService {
	//监控计划查询封装
	private IMonitorPlanDao monitorPlanDao;

	/**
	 * @param monitorPlanDao : set the property monitorPlanDao.
	 */
	public void setMonitorPlanDao(IMonitorPlanDao monitorPlanDao) {
		this.monitorPlanDao = monitorPlanDao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMonitorPlanService#searchMonitorList(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@Override
	public List<MonitorPlan> searchMonitorList(
			MonitorPlanQueryCondition condition) {
		//条件查询监控计划列表
		return monitorPlanDao.searchMonitorList(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMonitorPlanService#getMonitorDetail(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@Override
	public List<MonitorPlanDetail> getMonitorDetail(
			MonitorPlanQueryCondition condition,int start, int limit) {
		//查询的计划类型为开发类型
//		if (condition.getPlanType().equals(MarketingConstance.DEVELOP_TYPE)){
//			return monitorPlanDao.getDevMonitorDetail(condition, start, limit);
//		}else{
			//计划类型为维护类型
		return monitorPlanDao.getMatMonitorDetail(condition, start, limit);
//		}
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IMonitorPlanService#getMonitorDetailCount(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@Override
	public int getMonitorDetailCount(MonitorPlanQueryCondition condition) {
//		if (condition.getPlanType().equals(MarketingConstance.DEVELOP_TYPE)){
//			//查询的计划类型为开发类型
//			return monitorPlanDao.getDevMonitorDetailCount(condition);
//		}else{
			//计划类型为维护类型
		return monitorPlanDao.getMatMonitorDetailCount(condition);
//		}
		
	}

}
