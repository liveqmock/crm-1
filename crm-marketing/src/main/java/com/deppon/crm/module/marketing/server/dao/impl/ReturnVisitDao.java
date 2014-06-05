/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IReturnVisitDao;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */

public class ReturnVisitDao extends iBatis3DaoImpl implements IReturnVisitDao {
	// 回访信息主体
	private static final String NAMESPACE_RETURNVISIT = "com.deppon.crm.module.marketing.shared.domain.ReturnVisit.";
	// 插入回访信息
	private static final String INSERT_RETURNVISIT="insertReturnVisit";	
	// 回访信息——客户意见
	private static final String INSERT_RETURNVISITOPINION="insertOpinion";	
	// 回访信息——走货潜力
	private static final String INSERT_PLANVOLUMEPOTENTIAL="insertVolumePotential";
	// 回访录入页面初始化
	private static final String SELECT_INITDEVDATABYSCHEID = "getDevInitDataByScheId";
	// 回访录入页面初始化
	private static final  String SELECT_INITMATDATABYSCHEID = "getMatInitDataByScheId";
	// 回访录入页面初始化
	private static final  String SELECT_INITDEVDATABYSCHEID_BYCUST = "getDevInitDataByCust";
	// 回访录入页面初始化
	private static final  String SELECT_INITMATDATABYSCHEID_BYCUST = "getMatInitDataByCust";
	//根据客户ID查询回访集合
	private static final String SELECT_RVIDS_BYCUSTID = "queryRvListByCustId";
	//分页查询客户意见
	private static final String SELECT_RVOPTIONLIST_BYMARKETINGID = "queryRvOptionByMarketingIds";
	// 统计营销意见
	private static final String COUNT_RVOPTIONLIST_BYMARKETINGID = "countRvOptionByMarketingIds";
	//分页查询走货潜力
	private static final String SELECT_RVVOLUMEPOTENTIAL_BYMARKETINGID = "queryRvPotentialByMarketingIds";
	//走货潜力计数
	private static final String COUNT_RVVOLUMEPOTENTIAL_BYMARKETINGID = "countRvPotentialByMarketingIds";
	// 回访信息-- 查询列表
	private static final String SELECT_RETURNVISIT = "queryReturnVisit";
	// 回访信息-- 查询总数
	private static final String SELECT_RETURNVISITCOUNT = "queryReturnVisitCount";
	// 回访信息 -- 查询回访详情
	private static final String SELECTONE_RETURNVISIT = "getReturnVisit";
	// 回访信息 -- 查询回访客户意见详情
	private static final String SELECT_RETURNVISITOPINION = "getReturnVisitOpinion";
	// 回访信息 -- 查询回访走货潜力详情
	private static final String SELECT_RETURNVISITVOLUMEPOTENTIAL = "getReturnVisitVolumePotential";
	// 固定计划-同步回访-日程列表
	private static final String SYNFIXPLANRETURNVISIT = "synFixPlanReturnVisit";
	
	/**(non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao
	 * #saveReturnVisit(com.deppon.crm.module.marketing.shared.domain.ReturnVisit)
	 */
	@Override
	public String saveReturnVisit(ReturnVisit rv) {
		//查询
		this.getSqlSession().insert(NAMESPACE_RETURNVISIT + INSERT_RETURNVISIT, rv);
		//返回ID
		return rv.getId();
	}

	/**(non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao
	 * #saveReturnVisitOpinion(com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion)
	 */
	@Override
	public boolean saveReturnVisitOpinion(ReturnVisitOpinion rvo) {
		//查询
		return this.getSqlSession().insert(NAMESPACE_RETURNVISIT + INSERT_RETURNVISITOPINION, rvo) > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao
	 * #saveReturnVisitVolumePotential(com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential)
	 */
	@Override
	public boolean saveReturnVisitVolumePotential(
			ReturnVisitVolumePotential rvvp) {
		//返回新增结果
		return this.getSqlSession().insert(NAMESPACE_RETURNVISIT + INSERT_PLANVOLUMEPOTENTIAL, rvvp) > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 根据回访查询条件，分页返回回访信息列表
	 */
	public List<ReturnVisit> getReturnVisitList(
			ReturnVisitQueryCondition condition, int start, int limit) {
		//分页参数
		RowBounds rowBounds = new RowBounds(start, limit);
		//返回结果
		return this.getSqlSession().selectList(NAMESPACE_RETURNVISIT + SELECT_RETURNVISIT , condition, rowBounds);
	}

	/**
	 * 根据Id查询回访信息
	 */
	@Override
	public ReturnVisit getReturnVisit(int id) {
		//数据转换
		Integer returnVisitId = Integer.valueOf(id);
		//查询结果
		return (ReturnVisit) this.getSqlSession().selectOne(NAMESPACE_RETURNVISIT + SELECTONE_RETURNVISIT, returnVisitId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#getReturnVisitOpinionList(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReturnVisitOpinion> getReturnVisitOpinionList(int returnVisitId) {
		//查询
		return this.getSqlSession().selectList(NAMESPACE_RETURNVISIT + SELECT_RETURNVISITOPINION, returnVisitId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#getReturnVisitVolumePotential(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReturnVisitVolumePotential> getReturnVisitVolumePotentialList(
			int returnVisitId) {
		//查询
		return this.getSqlSession().selectList(NAMESPACE_RETURNVISIT + SELECT_RETURNVISITVOLUMEPOTENTIAL, returnVisitId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#getReturnVisitCount(com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition)
	 */
	@Override
	public int getReturnVisitCount(ReturnVisitQueryCondition condition) {
		//查询
		return (Integer) getSqlSession().selectOne(NAMESPACE_RETURNVISIT + SELECT_RETURNVISITCOUNT, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#initCreateReturnvisitPage(java.lang.String)
	 */
//	@Override
//	public ReturnVisit initDevCreateReturnvisitPage(String id) {		
//		//查询
//		return (ReturnVisit) getSqlSession().selectOne(NAMESPACE_RETURNVISIT + SELECT_INITDEVDATABYSCHEID, id);
//	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#initMatCreateReturnvisitPage(java.lang.String)
	 */
	@Override
	public ReturnVisit initMatCreateReturnvisitPage(String id) {
		//查询
		return (ReturnVisit) getSqlSession().selectOne(NAMESPACE_RETURNVISIT + SELECT_INITMATDATABYSCHEID, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#initDevCreateReturnvisitPageByCust(java.lang.String)
	 */
//	@Deprecated
//	@Override
//	public ReturnVisit initDevCreateReturnvisitPageByCust(String id) {
//		//查询
//		return (ReturnVisit) getSqlSession().selectOne(NAMESPACE_RETURNVISIT + SELECT_INITDEVDATABYSCHEID_BYCUST, id);
//	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#initMatCreateReturnvisitPageByCust(java.lang.String)
	 */
	@Override
	public ReturnVisit initMatCreateReturnvisitPageByCust(String id) {
		//查询
		return (ReturnVisit) getSqlSession().selectOne(NAMESPACE_RETURNVISIT + SELECT_INITMATDATABYSCHEID_BYCUST, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#getReturnVisitLog(com.deppon.crm.module.marketing.shared.domain.ReturnVisit)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ReturnVisit> getReturnVisitLog(ReturnVisitQueryCondition condition) {
		//查询
		return getSqlSession().selectList(NAMESPACE_RETURNVISIT+SELECT_RETURNVISIT, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#queryContactIdListByCustId(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> queryRvListByCustId(Map<String, String> paraMap) {
		//查询
		return this.getSqlSession().selectList(NAMESPACE_RETURNVISIT + SELECT_RVIDS_BYCUSTID,paraMap);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#queryRvOptionByMarketingIds(java.util.List, int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ReturnVisitOpinionVo> queryRvOptionByMarketingIds(
			List<String> marketingIds, int start, int limit) {
		//分页
		RowBounds rb = new RowBounds(start, limit);
		//查询
		return this.getSqlSession().selectList(
				NAMESPACE_RETURNVISIT + SELECT_RVOPTIONLIST_BYMARKETINGID,marketingIds,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#queryRvPotentialByMarketingIds(java.util.List, int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ReturnVisitVolumePotentialVo> queryRvPotentialByMarketingIds(
			List<String> marketingIds, int start, int limit) {
		//分页
		RowBounds rowBounds = new RowBounds(start, limit);
		////查询
		return this.getSqlSession().selectList(
				NAMESPACE_RETURNVISIT + SELECT_RVVOLUMEPOTENTIAL_BYMARKETINGID, marketingIds, rowBounds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#countRvOptionByMarketingIds(java.util.List)
	 */
	@Override
	public int countRvOptionByMarketingIds(List<String> marketingIds) {
		//查询
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE_RETURNVISIT+COUNT_RVOPTIONLIST_BYMARKETINGID,marketingIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IReturnVisitDao#countRvPotentialByMarketingIds(java.util.List)
	 */
	@Override
	public int countRvPotentialByMarketingIds(List<String> marketingIds) {
		//查询
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE_RETURNVISIT+COUNT_RVVOLUMEPOTENTIAL_BYMARKETINGID,marketingIds);
	}
	/**
	 * 
	 * <p>
	 * 固定计划回访同步<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-03-26
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> synFixPlanReturnVisit(ReturnVisit rv) {
		return (List<Schedule>) this.getSqlSession().selectList(
				NAMESPACE_RETURNVISIT+SYNFIXPLANRETURNVISIT,rv);
	}

	/**
	 * 根据回访ID删除回访主记录
	 */
	@Override
	public void deleteReturnVisitById(String id) {
		this.getSqlSession().delete(NAMESPACE_RETURNVISIT + "deleteReturnVisitById", id);
	}
}
