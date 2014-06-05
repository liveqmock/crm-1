package com.deppon.crm.module.complaint.server.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.complaint.server.dao.IFeedBackReasionDao;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.order.server.dao.ISequenceTool;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class FeedBackReasionDaoImpl extends iBatis3DaoImpl implements IFeedBackReasionDao{
	
	private static final String NAMESPACE_FEEDBACKREASION 			= "com.deppon.crm.module.complaint.shared.domain.FeedBackReasion.";
	private static final String SEARCH_FEEDBACKREASION_BY_COMPID  	= "selectFeedBackReasionByCompId";
	private static final String INSERT_FEEDBACKREASION				= "insertFeedBackReasion";
	private static final String UPDATE_FEEDBACKREASION				= "updateFeedBackReasion";
	
	// 通用序列工具
	private ISequenceTool sequenceTool;
	/**
	 * 
	 * <p>
	 * Description:通过compid查退回原因<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-17
	 * @return
	 * List<Complaint>
	 */
	@Override
	public List<FeedBackReasion> searchFeedBackReasionByCompId(BigDecimal compId) {
		// TODO Auto-generated method stub
		Map<String,BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("compId", compId);
		return this.getSqlSession().selectList(NAMESPACE_FEEDBACKREASION+SEARCH_FEEDBACKREASION_BY_COMPID,map);
	}
	/**
	 * 
	 * @description 反馈退回记录DAO新增接口.
	 * @author Lee
	 * @version 0.1 2012-3-13
	 * @param 反馈退回记录对象
	 * @date 2012-4-20
	 * @return 反馈退回记录对象
	 * @update 2012-4-20 上午9:11:57
	 */
	@Override
	public FeedBackReasion saveFeedBackReasion(FeedBackReasion feedBackReasion) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(NAMESPACE_FEEDBACKREASION+INSERT_FEEDBACKREASION, feedBackReasion);
		return feedBackReasion;
	}
	/**
	 * <p>
	 * Description: 更新反馈记录<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-3
	 * @param feedBackReasion
	 * @return
	 * boolean
	 */
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.complaint.server.dao.IFeedBackReasionDao#updateFeedBackReasion(com.deppon.crm.module.complaint.shared.domain.FeedBackReasion)
	 */
	@Override
	public boolean updateFeedBackReasion(FeedBackReasion feedBackReasion) {
		this.getSqlSession().insert(NAMESPACE_FEEDBACKREASION+UPDATE_FEEDBACKREASION, feedBackReasion);
		return true;
	}
}
