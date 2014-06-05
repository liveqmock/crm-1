package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IWorkFlowDao;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 * <p>
 * Description：工作流dao<br />
 * </p>
 * @title WorkFlowDao.java
 * @package com.deppon.crm.module.customer.server.dao.impl 
 * @author 106138
 * @version 0.1 2014年4月10日
 */
public class WorkFlowDao extends iBatis3DaoImpl implements IWorkFlowDao{
	private static final String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.WorkFlow.";
	

	@Override
	public void insertWorkFlow(WorkFlow workFLow) {
		this.getSqlSession().insert(NAME_SPACE+"insertWorkFlow",workFLow);
	}

	@Override
	public void updateWorkFlowStatusByWorkFLowId(String workStatus,
			long workFLowId,String modifyUser) {
		Assert.notNull(workStatus, "workStatus must not null!");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status",workStatus);
		map.put("workFlowId", workFLowId);
		map.put("modifyUser", modifyUser);
		
		this.getSqlSession().update(NAME_SPACE+"updateWorkFlowStatusByWorkFLowId",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition) {
		return this.getSqlSession().selectList(NAME_SPACE+"searchWorkFLow",condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchWorkFLow",condition,rowBounds);
	}

	@Override
	public int countSearchWorkFlow(WorkFlowCondition condition) {
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"countSearchWorkFlow",condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkFlow> searchMydisposeWorkFlow(WorkFlowCondition condition,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchMydisposeWorkFlow",condition,rowBounds);
	}

	@Override
	public int countSearchMydisposeWorkFlow(WorkFlowCondition condition) {
		return (Integer) this.getSqlSession().selectOne(NAME_SPACE+"countSearchMydisposeWorkFlow",condition);
	}


}
