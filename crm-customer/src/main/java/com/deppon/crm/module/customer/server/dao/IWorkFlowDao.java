package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;

/**
 * 
 * <p>
 * Description:我的工作流的查询界面<br />
 * </p>
 * @title IWorkFlowDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author bxj
 * @version 0.2 2012-4-18
 */
public interface IWorkFlowDao {
	
	/**
	 * <p>
	 * Description:通过工作流查询条件 查询工作流信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * List<WorkFlow>
	 */
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition);
	
	/**
	 * <p>
	 * Description:通过工作流查询条件 分页查询  工作流信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<WorkFlow>
	 */
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition,int start,int limit);
	
	/**
	 * <p>
	 * Description:保存工作流信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param workFLow
	 * void
	 */
	public void insertWorkFlow(WorkFlow workFLow);
	
	/**
	 * <p>
	 * Description:根据工作流号，当前操作人，修改工作流信息的状态<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param workStatus
	 * @param workFLowId
	 * @param modifyUser
	 * void
	 */
	public void updateWorkFlowStatusByWorkFLowId(String workStatus,long workFLowId, String modifyUser);

	/**
	 * <p>
	 * Description:通过工作流查询条件 统计工作流信息的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * int
	 */
	public int countSearchWorkFlow(WorkFlowCondition condition);

	/**
	 * <p>
	 * Description:通过工作流查询条件 查询有审批记录的工作流信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<WorkFlow>
	 */
	public List<WorkFlow> searchMydisposeWorkFlow(WorkFlowCondition condition,
			int start, int limit);

	/**
	 * <p>
	 * Description:通过工作流查询条件 统计有审批记录的工作流信息的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-26
	 * @param condition
	 * @return
	 * int
	 */
	public int countSearchMydisposeWorkFlow(WorkFlowCondition condition);
}
