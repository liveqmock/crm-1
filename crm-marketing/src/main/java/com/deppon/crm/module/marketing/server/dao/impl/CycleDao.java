/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-4-13
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.ICycleDao;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title CycleDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl
 * @author 苏玉军
 * @version 0.1 2012-4-13
 */

public class CycleDao extends iBatis3DaoImpl implements ICycleDao {
	private static final String NAMESPACE_CYCLE = "com.deppon.crm.module.marketing.shared.domain.ReturnVisit.";
	// 根据客户查询联系人的回访记录
	private static final String SEARCH_RVRECORDS = "searchReturnVisitRecords";
	// 根据Id 查询
	private static final String SEARCH_FROMCYCLE = "searchCustFromCycle";
	//分页查询总数
	private static final String SEARCH_COUNTFORSEARCHCUST="countForSearchCustFromCycle";
	//查询最后一次发货时间
	private static final String QUERY_LASTTIME_BYCUSTNUM = "queryLastTimeByCustNum";
	//查询客户线路分析
//	private static final String QUERY_PATH_ANALYSIS ="queryPathAnalysis";
	//发货线路分析查询
	private static final String QUERY_PATH_ANALYSIS_DELIVER = "queryDeliverPathAnalysis";
	//到货线路分析查询
	private static final String QUERY_PATH_ANALYSIS_RECEIVE = "queryReceivePathAnalysis";
	//快递发货线路分析查询
	private static final String QUERY_EXPRESS_PATH_ANALYSIS_DELIVER = "queryExpressDeliverPathAnalysis";
	//快递到货线路分析查询
	private static final String QUERY_EXPRESS_PATH_ANALYSIS_RECEIVE = "queryExpressReceivePathAnalysis";
	/**
	 * 
	 * <p>
	 * 联系人回访记录查询<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-13
	 * @param custId
	 *            ，客户Id
	 * @return List<ReturnVisit>
	 * @see com.deppon.crm.module.marketing.server.dao.ICycleDao#searchReturnVisitRecords(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReturnVisit> searchReturnVisitRecords(String custId) {
		//查询
		return this.getSqlSession().selectList(
				NAMESPACE_CYCLE + SEARCH_RVRECORDS, custId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.marketing.server.dao.ICycleDao#searchCustFromCycle
	 * (java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleQueryResultObject> searchCustFromCycle(List<String> ids,int start,int limit,String deptId) {
		//查询参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		//客户id
		map.put("custIds", ids);
		//部门ID
		map.put("deptId", deptId);
		//分页参数
		RowBounds rb = new RowBounds(start, limit);
		//查询
		return this.getSqlSession().selectList(NAMESPACE_CYCLE+SEARCH_FROMCYCLE, map, rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICycleDao#countForSearchCustFromCycle(java.util.List, java.lang.String)
	 */
	@Override
	public int countForSearchCustFromCycle(List<String> ids, String deptId) {
		//参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		//客户id集合
		map.put("custIds", ids);
		//部门
		map.put("deptId", deptId);
		//查询
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_CYCLE+SEARCH_COUNTFORSEARCHCUST, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.ICycleDao#queryLastTimeByCustNum(java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Date queryLastTimeByCustNum(String custNum,String deptId,Date queryDate) {
		//查询参数
		Map map = new HashMap();
		//客户编码
		map.put("custNum", custNum);
		//部门ID
		map.put("deptId", deptId);
		//查询日期
		map.put("queryDate", queryDate);
		//查询
		return (Date) this.getSqlSession().selectOne(NAMESPACE_CYCLE + QUERY_LASTTIME_BYCUSTNUM, map);
	}
	/**
	 * 
	 * <p>
	 * 查询客户线路分析数据<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-3-25
	 * @param memberNumber
	 * @param endDate
	 * @param analysisCustomerType
	 * @return List<PathAnalysisInfo>
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PathAnalysisInfo> queryPathAnalysis(String memberNumber,
//			Date endDate, String analysisCustomerType) {
//		Map<String,Object> args = new HashMap<String, Object>();
//		args.put("memberNumber", memberNumber);
//		args.put("endDate", endDate);
//		args.put("analysisCustomerType", analysisCustomerType);
//		return this.getSqlSession().selectList(NAMESPACE_CYCLE + QUERY_PATH_ANALYSIS, args);
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PathAnalysisInfo> queryDeliverPathAnalysis(String memberNumber,
			Date endDate) {
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("memberNumber", memberNumber);
		args.put("endDate", endDate);
		return this.getSqlSession().selectList(NAMESPACE_CYCLE+QUERY_PATH_ANALYSIS_DELIVER,args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PathAnalysisInfo> queryReceivePathAnalysis(String memberNumber,
			Date endDate) {
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("memberNumber", memberNumber);
		args.put("endDate", endDate);
		return this.getSqlSession().selectList(NAMESPACE_CYCLE + QUERY_PATH_ANALYSIS_RECEIVE, args);
	}
	/**
	 * 
	 * <p>
	 * 查询快递发货线路分析<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-3-17
	 * @param memberNumber
	 * @param endDate
	 * @return
	 * List<PathAnalysisInfo>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PathAnalysisInfo> queryExpressDeliverPathAnalysis(
			String memberNumber, Date endDate) {
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("memberNumber", memberNumber);
		args.put("endDate", endDate);
		return this.getSqlSession().selectList(NAMESPACE_CYCLE + QUERY_EXPRESS_PATH_ANALYSIS_DELIVER, args);
	}
	/**
	 * 
	 * <p>
	 * 查询快递到货线路分析<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-3-17
	 * @param memberNumber
	 * @param endDate
	 * @return
	 * List<PathAnalysisInfo>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PathAnalysisInfo> queryExpressReceivePathAnalysis(
			String memberNumber, Date endDate) {
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("memberNumber", memberNumber);
		args.put("endDate", endDate);
		return this.getSqlSession().selectList(NAMESPACE_CYCLE + QUERY_EXPRESS_PATH_ANALYSIS_RECEIVE, args);
	}
	
}
