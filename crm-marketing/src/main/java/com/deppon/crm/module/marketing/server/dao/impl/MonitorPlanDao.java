/**   
 * <p>
 * 监控计划DAO抽取<br />
 * </p>
 * @title MonitorPlanDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlan;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanDetail;
import com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MonitorPlanDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-28
 */

public class MonitorPlanDao extends iBatis3DaoImpl implements IMonitorPlanDao {
	//NAMESPACE
	private static final String NAMESPACE_MONITORPLAN="com.deppon.crm.module.marketing.shared.domain.MonitorPlan.";
	//查询开发计划列表
	private static final String QUERY_MONITORPLANLIST					="searchMonitorPlan";
	//查询开发计划监控明细
//	private static final String QUERY_DEV_MONITORPLANLIST_DETAIL		="searchDevMonitorPlanDetail";
	//查询开发计划监控总数
//	private static final String QUERY_DEV_MONITORPLANLIST_DETAIL_COUNT	="getDevMonitorPlanDetailCount";
	//查询维护计划监控明细
	private static final String QUERY_MAT_MONITORPLANLIST_DETAIL		="searchMatMonitorPlanDetail";
	//查询维护计划监控总数
	private static final String QUERY_MAT_MONITORPLANLIST_DETAIL_COUNT	="getMatMonitorPlanDetailCount";

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao#searchMonitorList(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorPlan> searchMonitorList(
			MonitorPlanQueryCondition condition) {
		//查询
		return this.getSqlSession().selectList(
				NAMESPACE_MONITORPLAN + QUERY_MONITORPLANLIST, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao#getMonitorDetail(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MonitorPlanDetail> getDevMonitorDetail(
//			MonitorPlanQueryCondition condition,int start, int limit) {
//		//分页
//		RowBounds rowBounds = new RowBounds(start, limit);
//		//查询
//		return this.getSqlSession().selectList(
//				NAMESPACE_MONITORPLAN + QUERY_DEV_MONITORPLANLIST_DETAIL,
//				condition, rowBounds);
//
//	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao#getMonitorDetailCount(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
//	@Override
//	public int getDevMonitorDetailCount(MonitorPlanQueryCondition condition) {
//		//查询
//		return (Integer) this.getSqlSession().selectOne(NAMESPACE_MONITORPLAN + QUERY_DEV_MONITORPLANLIST_DETAIL_COUNT, condition);
//	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao#getMonitorDetail(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorPlanDetail> getMatMonitorDetail(
			MonitorPlanQueryCondition condition,int start, int limit) {
		//查询
		RowBounds rowBounds = new RowBounds(start, limit);
		//查询
		return this.getSqlSession().selectList(
				NAMESPACE_MONITORPLAN + QUERY_MAT_MONITORPLANLIST_DETAIL,
				condition, rowBounds);

	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IMonitorPlanDao#getMonitorDetailCount(com.deppon.crm.module.marketing.shared.domain.MonitorPlanQueryCondition)
	 */
	@Override
	public int getMatMonitorDetailCount(MonitorPlanQueryCondition condition) {	
		//查询
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_MONITORPLAN + QUERY_MAT_MONITORPLANLIST_DETAIL_COUNT, condition);
	}
}
