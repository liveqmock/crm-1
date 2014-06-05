package com.deppon.crm.module.customer.server.manager.impl;

import java.util.List;
import com.deppon.crm.module.customer.server.manager.IWorkFLowManager;
import com.deppon.crm.module.customer.server.service.IWorkFLowService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
public class WorkFLowManager implements IWorkFLowManager{
	
	private IWorkFLowService workFLowService;

	public void setWorkFLowService(IWorkFLowService workFLowService) {
		this.workFLowService = workFLowService;
	}
	/**
	 * 
	 * <p>
	 * 分页查询我的工作流信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-18
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<WorkFlow>
	 */
	@Override
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition,
			int start, int limit) {
		//当工作流id 不存在时，为我的工作流查询
		if(condition.getWorkflowId() == null || condition.getWorkflowId()==0){
			condition.setCreateUser(ContextUtil.getCurrentUserEmpId());
		}
		return workFLowService.searchWorkFLow(condition, start, limit);
	}
	/**
	 * 
	 * <p>
	 * 保存工作流信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.2 2012-4-18
	 * @param workFLow
	 * void
	 */
	@Override
	public void saveWorkFlow(WorkFlow workFLow) {
		String createUser = ContextUtil.getCreateOrModifyUserId();
		workFLow.setCreateUser(createUser);
		workFLowService.insertWorkFlow(workFLow);
	}
	/**
	 * 
	 * <p>
	 * 维护工作流的状态<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-18
	 * @param workStatus
	 * @param workFLowId
	 * void
	 */
	@Override
	public void updateWorkFlowStatusByWorkFLowId(String workStatus,
			long workFLowId) {
		String modifyUser = ContextUtil.getCurrentUserEmpId();
		workFLowService.updateWorkFlowStatusByWorkFLowId(workStatus, workFLowId,modifyUser);
	}
	/**
	 * 
	 * <p>
	 * 统计该查询条件的工作流信息总数<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-18
	 * @param condition
	 * @return
	 * int
	 */
	@Override
	public int countSearchWorkFlow(WorkFlowCondition condition) {
		//当工作流id 不存在时，为我的工作流查询
		if(condition.getWorkflowId() == null || condition.getWorkflowId()==0){
			condition.setCreateUser(ContextUtil.getCurrentUserEmpId());
		}
		return workFLowService.countSearchWorkFlow(condition);
	}
	/**
	 * 
	 * <p>
	 *  查询我的作废工作流<br />
	 * </p>
	 * @author 王明明
	 * @version 0.2 2012-4-18
	 * @param workFLow
	 * void
	 */
	@Override
	public List<WorkFlow> searchMydisposeWorkFlow(WorkFlowCondition condition,
			int start, int limit) {
		condition.setCreateUser(ContextUtil.getCurrentUserEmpId());
		return workFLowService.searchMydisposeWorkFlow(condition,start,limit);
	}
	/**
	 * 
	 * <p>
	 *  查询我的作废工作流总条数<br />
	 * </p>
	 * @author 王明明
	 * @version 0.2 2012-4-18
	 * @param workFLow
	 * void
	 */
	@Override
	public int countSearchMydisposeWorkFlow(WorkFlowCondition condition) {
		condition.setCreateUser(ContextUtil.getCurrentUserEmpId());
		return workFLowService.countSearchMydisposeWorkFlow(condition);
	}

}
