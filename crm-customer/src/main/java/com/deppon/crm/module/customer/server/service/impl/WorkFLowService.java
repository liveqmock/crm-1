package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;

import com.deppon.crm.module.customer.server.dao.IWorkFlowDao;
import com.deppon.crm.module.customer.server.service.IWorkFLowService;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;

public class WorkFLowService implements IWorkFLowService{
	
	private IWorkFlowDao workFlowDao;
	
	public void setWorkFlowDao(IWorkFlowDao workFlowDao) {
		this.workFlowDao = workFlowDao;
	}

	@Override
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition,
			int start, int limit) {
		return workFlowDao.searchWorkFLow(condition, start, limit);
	}

	@Override
	public void insertWorkFlow(WorkFlow workFLow) {
		workFlowDao.insertWorkFlow(workFLow);
	}

	@Override
	public void updateWorkFlowStatusByWorkFLowId(String workStatus,
			long workFLowId,String modifyUser) {
		workFlowDao.updateWorkFlowStatusByWorkFLowId(workStatus, workFLowId,modifyUser);
	}

	@Override
	public int countSearchWorkFlow(WorkFlowCondition condition) {
		return workFlowDao.countSearchWorkFlow(condition);
	}

	@Override
	public List<WorkFlow> searchMydisposeWorkFlow(WorkFlowCondition condition,
			int start, int limit) {
		return workFlowDao.searchMydisposeWorkFlow(condition,start,limit);
	}

	@Override
	public int countSearchMydisposeWorkFlow(WorkFlowCondition condition) {
		return workFlowDao.countSearchMydisposeWorkFlow(condition);
	}
	
	
}
