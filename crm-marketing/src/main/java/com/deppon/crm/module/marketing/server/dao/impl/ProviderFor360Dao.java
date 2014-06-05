/**   
 * <p>
 * 提供给360视图接口
 * </p>
 * @title ProviderFor360Dao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-4-24
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IProviderFor360Dao;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * 提供给360视图接口
 * </p>
 * @title ProviderFor360Dao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-4-24
 */

public class ProviderFor360Dao extends iBatis3DaoImpl implements IProviderFor360Dao {
	//namespace
	private static final String NAME_SPACE="com.deppon.crm.module.marketing.shared.domain.ReturnVisit.";
	//根据客户ID获得客户最近一次客户维护信息（客户维护回访信息）
	private static final String SEARCH_LASTESTRVINFO = "searchLastestReturnVisitInfo";
	//根据客户Id获得客户开发/日程综合信息列表
	private static final String SEARCH_PLANSCHEDULES = "searchPlanSchedules";
	//根据客户编码获得客户维护综合信息列表（即客户维护回访信息）
	private static final String SEARCH_RETURNVISITINFO =  "searchReturnVisitInfos";
	/**
	 * 查询最后一次的维护信息
	 * @see com.deppon.crm.module.marketing.server.dao.IProviderFor360Dao#getLastestMaintainInfo(java.lang.String)
	 */
	@Override
	public ReturnVisit getLastestMaintainInfo(String custId) {
		//查询
		return (ReturnVisit) this.getSqlSession().selectOne(NAME_SPACE+SEARCH_LASTESTRVINFO, custId);
	}

	/** 
	 * 查询计划日程明细
	 * @see com.deppon.crm.module.marketing.server.dao.IProviderFor360Dao#getPlanScheduleDetail(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReturnVisit> getPlanScheduleDetail(String custId) {
		//查询
		return this.getSqlSession().selectList(NAME_SPACE+SEARCH_PLANSCHEDULES, custId);
	}

	/**
	 * 查询回访明显
	 * @see com.deppon.crm.module.marketing.server.dao.IProviderFor360Dao#getVisitRecords(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReturnVisit> getVisitRecords(String custId) {
		//查询
		return this.getSqlSession().selectList(NAME_SPACE+SEARCH_RETURNVISITINFO, custId);
	}

}
