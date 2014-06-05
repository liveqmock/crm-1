package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;

public interface IWorkFLowManager {
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
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:查询工作流<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<WorkFlow>
	 */
	public List<WorkFlow> searchMydisposeWorkFlow(WorkFlowCondition condition,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:统计<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param condition
	 * @return
	 * int
	 */
	public int countSearchMydisposeWorkFlow(WorkFlowCondition condition);

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
	public int countSearchWorkFlow(WorkFlowCondition condition);
	/**
	 * 
	 * <p>
	 * 保存工作流信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-18
	 * @param workFLow
	 * void
	 */
	public void saveWorkFlow(WorkFlow workFLow);
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
	public void updateWorkFlowStatusByWorkFLowId(String workStatus,long workFLowId);
}