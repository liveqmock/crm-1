/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.server.dao.IReturnVisitDao;
import com.deppon.crm.module.marketing.server.service.IReturnVisitService;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.Schedule;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-3-26
 */

public class ReturnVisitService implements IReturnVisitService {
	//营销管理DAO
	private IReturnVisitDao returnVisitDao;
	
	/**
	 * @param returnVisitDao : set the property returnVisitDao.
	 */
	public void setReturnVisitDao(IReturnVisitDao returnVisitDao) {
		this.returnVisitDao = returnVisitDao;
	}


	/**
	 * 分页查询客户回访列表
	 */
	@Override
	public List<ReturnVisit> getReturnVisitList(
			ReturnVisitQueryCondition condition, int start, int limit) {
		//分页查询
		return returnVisitDao.getReturnVisitList(condition, start, limit);
	}


	/**
	 * 根据营销id查询回访信息
	 */
	@Override
	public ReturnVisit getReturnVisit(int id) {
		//根据营销id查询回访信息
		return returnVisitDao.getReturnVisit(id);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#getReturnVisitOpinionList(int)
	 */
	@Override
	public List<ReturnVisitOpinion> getReturnVisitOpinionList(int returnVisitId) {
		//根据营销ID查询客户意见
		return returnVisitDao.getReturnVisitOpinionList(returnVisitId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#getReturnVisitVolumePotential(int)
	 */
	@Override
	public List<ReturnVisitVolumePotential> getReturnVisitVolumePotential(int returnVisitId) {
		//根据营销ID查询走货潜力
		return returnVisitDao.getReturnVisitVolumePotentialList(returnVisitId);
	}


	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#getReturnVisitCount(com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition)
	 */
	@Override
	public int getReturnVisitCount(ReturnVisitQueryCondition condition) {
		//查询回访总数
		return returnVisitDao.getReturnVisitCount(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#saveReturnVisit(com.deppon.crm.module.marketing.shared.domain.ReturnVisit, java.util.List, java.util.List)
	 */
	@Override
	public boolean saveReturnVisit(ReturnVisit rv,
			List<ReturnVisitOpinion> rvoList,
			List<ReturnVisitVolumePotential> rvvpList) {
		//保存回访信息
		String id = returnVisitDao.saveReturnVisit(rv);
		//未保存成功返回
		if (id.equals("0") || StringUtils.isEmpty(id)){
			return false;
		}
		//循环客户意见列表
		if (rvoList != null && !rvoList.isEmpty()){
			for (ReturnVisitOpinion rvo : rvoList){
				// 设置关联回访ID
				rvo.setReturnVisitId(id); 
				//保存客户意见
				returnVisitDao.saveReturnVisitOpinion(rvo);
			}
		}
		//循环走货潜力列表
		if (rvvpList != null && !rvvpList.isEmpty()){
			for (ReturnVisitVolumePotential rvvp : rvvpList){
				// 设置关联回访ID
				rvvp.setReturnVisitId(id);	
				//保存货量潜力
				returnVisitDao.saveReturnVisitVolumePotential(rvvp);				
			}
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#initCreateReturnvisitPage(java.lang.String)
	 */
	@Override
	public ReturnVisit initCreateReturnvisitPage(String id, String type) {
		return returnVisitDao.initMatCreateReturnvisitPage(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#initCreateReturnvisitPageByCust(java.lang.String, java.lang.String)
	 */
	@Override
	public ReturnVisit initCreateReturnvisitPageByCust(String id, String type) {
		//查询初始化数据
		return returnVisitDao.initMatCreateReturnvisitPageByCust(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#getReturnVisitLog(com.deppon.crm.module.marketing.shared.domain.ReturnVisit)
	 */
	@Override
	public List<ReturnVisit> getReturnVisitLog(ReturnVisitQueryCondition condition) {
		//查询回访日志
		return returnVisitDao.getReturnVisitLog(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#queryContactIdListByCustId(java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<String> queryRvListByCustId(Map map) {
		//根据客户ID查询回访信息
		return returnVisitDao.queryRvListByCustId(map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#queryRvOptionByMarketingIds(java.util.List, int, int)
	 */
	@Override
	public List<ReturnVisitOpinionVo> queryRvOptionByMarketingIds(
			List<String> marketingIds, int start, int limit) {
		//根据营销计划ids查询客户意见列表
		return returnVisitDao.queryRvOptionByMarketingIds(marketingIds,start,limit);
	}

	/**
	 * 
	 * <p>
	 * 分页查询货量潜力<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-11-9
	 * @param marketingIds
	 * @param start
	 * @param limit
	 * @return
	 * List<ReturnVisitVolumePotential>
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#queryRvPotentialByMarketingIds(java.util.List, int, int)
	 */
	@Override
	public List<ReturnVisitVolumePotentialVo> queryRvPotentialByMarketingIds(
			List<String> marketingIds, int start, int limit) {
		//根据营销计划 ids 查询 走货潜力
		return returnVisitDao.queryRvPotentialByMarketingIds(marketingIds,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#countRvOptionByMarketingIds(java.util.List)
	 */
	@Override
	public int countRvOptionByMarketingIds(List<String> marketingIds) {
		//根据营销计划ids 查询客户意见总数
		return returnVisitDao.countRvOptionByMarketingIds(marketingIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IReturnVisitService#countRvPotentialByMarketingIds(java.util.List)
	 */
	@Override
	public int countRvPotentialByMarketingIds(List<String> marketingIds) {
		//根据营销计划ids 查询走货潜力总数
		return returnVisitDao.countRvPotentialByMarketingIds(marketingIds);
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
	@Override
	public List<Schedule> synFixPlanReturnVisit(ReturnVisit rv) {
		return returnVisitDao.synFixPlanReturnVisit(rv);
	}

}
