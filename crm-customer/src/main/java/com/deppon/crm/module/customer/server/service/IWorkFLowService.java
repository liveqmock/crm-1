package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
/**
 * 
 * <p>
 * Description:工作流service<br />
 * </p>
 * @title IWorkFLowService.java
 * @package com.deppon.crm.module.customer.server.service 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IWorkFLowService {
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
	public List<WorkFlow> searchWorkFLow(WorkFlowCondition condition,int start,int limit);
	/**
	 * 
	 * <p>
	 * Description:插入工作流<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workFLow
	 * void
	 */
	public void insertWorkFlow(WorkFlow workFLow);
	/**
	 * 
	 * <p>
	 * Description:更新工作流状态<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workStatus
	 * @param workFLowId
	 * @param modifyUser
	 * void
	 */
	
	public void updateWorkFlowStatusByWorkFLowId(String workStatus,long workFLowId, String modifyUser);
	/**
	 * 
	 * <p>
	 * Description:统计工作流条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param condition
	 * @return
	 * int
	 */
	public int countSearchWorkFlow(WorkFlowCondition condition);
	/**
	 * 
	 * <p>
	 * Description:查询待审批工作流<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<WorkFlow>
	 */
	public List<WorkFlow> searchMydisposeWorkFlow(WorkFlowCondition condition,
			int start, int limit);
	/**
	 * 
	 * <p>
	 * Description:统计待审批条数<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param condition
	 * @return
	 * int
	 */
	public int countSearchMydisposeWorkFlow(WorkFlowCondition condition);
}
