/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BpsWorkflowManagerImpl.java
 * @package com.deppon.crm.module.common.server.bpsworkflow.impl 
 * @author pgj
 * @version 0.1 2013-11-14
 */
package com.deppon.crm.module.bps.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpms.module.shared.domain.ProcessInfo;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.bpmsapi.module.client.api.IDpBpmsClientFacade;
import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.bpmsapi.module.client.domain.BranchRule;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title BpsWorkflowManagerImpl.java
 * @package com.deppon.crm.module.common.server.bpsworkflow.impl
 * @author pgj
 * @version 0.1 2013-11-14
 */

public interface IBpsWorkflowManager {
	/**
	 * 
	 * <p>
	 * Description:获取工作流提供客户端<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-14
	 * @param brm
	 *            :分支条件
	 * @param prm
	 *            :参与者
	 * @param empCode
	 *            :员工工号
	 * @param empName
	 *            :员工名称
	 * @return IDpBpmsClientFacade
	 */
	public IDpBpmsClientFacade getClient();

	/**
	 * 
	 * <p>
	 * Description:创建工作流<br />
	 * </p>
	 * 
	 * @author pgj
	 * @param processName
	 *            :流程定义名称
	 * @param appName
	 *            :流程实例名称
	 * @param descName
	 *            :流程描述
	 * @param bussinessCode
	 *            :工作流业务编码
	 * @version 0.1 2013-11-14
	 * @return long
	 */
	public long createAndStartProcess(String processName, String appName, String descName, String bussinessCode);

	/**
	 * 通过流程实例ID收回流程
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @author pgj
	 * @version 0.1 2013-11-14
	 * @return boolean
	 */
	public boolean drawBackByProcessInstID(Long processInstID);

	/**
	 * 通过工作项ID收回流程
	 * 
	 * @param workItemID
	 *            工作项ID
	 * @return
	 */
	public boolean drawBackByWorkItemId(Long workItemID);

	/**
	 * 判断该流程是否可以删除
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 */
	public boolean isRemoveProcessInstExt(Long processInstID);

	/**
	 * 执行删除流程操作
	 * 
	 * @param processInstID
	 *            流程实例ID
	 * @return
	 */
	public boolean removeMyProcessInstExt(Long processInstID);

	/**
	 * 工作流审批
	 * 
	 * @param workItemID
	 *            工作项ID
	 * @param processInstID
	 *            流程实例ID
	 * @param approveOperateType
	 *            操作类型
	 * @param approveOpinion
	 *            审批意见
	 * @param parts
	 *            转办参与者数组
	 * @return
	 */
	public Map<String, Object> workflowApprove(long workItemID, long processInstID, String approveOperateType, String approveOpinion, BpmsParticipant[] parts);

	/**
	 * 查询指定系统指定用户的待办流程信息
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param fstandardcode
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	public List<WorkFlowInfo> queryTodoItems(Map<String, Object> map);

	/**
	 * 查询指定用户的指定业务系统的待办流程数目
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param fstandardcode
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	public long queryTodoCounts(Map<String, Object> map);

	/**
	 * 查询指定用户的指定系统的已审批流程信息
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param departmentId
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	public List<WorkFlowInfo> queryFinishedItems(Map<String, Object> map);

	/**
	 * 查询指定用户的指定业务系统的已办工作项数目
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param applerId
	 *            申请人工号
	 * @param departmentId
	 *            申请人所在部门编码
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	public Long queryFinishedCounts(Map<String, Object> map);

	/**
	 * 查询流程实例下对应的参与者信息
	 * 
	 * @param processInstId
	 *            流程实例ID
	 * @param activityDefId
	 *            环节定义ID
	 * @param flag
	 *            1：查询已处理参与者 2：查询环节配置参与者
	 * @return
	 */
	public BpmsParticipant[] getActivityParticipant(Long processInstId, String activityDefId, String flag);

	/**
	 * 结束当前流程
	 * 
	 * @param processInstId
	 *            流程实例ID
	 * @return
	 */
	public boolean terminateProcessInstance(Long processInstId);

	/**
	 * 获取下一步可到达的节点
	 * 
	 * @param workItemId
	 *            工作项ID
	 * @param processInstId
	 *            流程实例ID
	 * @param approveOperateType
	 *            操作类型
	 * @return
	 */
	public BranchRule[] getNextActivities(long workItemId, long processInstId, int operateType);

	/**
	 * 查询指定用户的指定系统的我申请的流程信息
	 * 
	 * @param start
	 *            分页开始
	 * @param limit
	 *            每页条数
	 * @param processDefName
	 *            流程定义名称
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	public List<WorkFlowInfo> queryMyWorkFlow(Map<String, Object> map);

	/**
	 * 查询指定用户的指定系统的我申请的流程数目
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @param state
	 *            1 未提交 2 未审批 3 审批中 4 已同意 5 未同意 6 已退回 7 汇款失败 8 汇款成功
	 * @param startTime
	 *            申请日期
	 * @param endTime
	 *            申请日期
	 * @param workflowNo
	 *            工作流号
	 * @return
	 */
	public long queryMyWorkFlowCount(Map<String, Object> map);

	/**
	 * 获取指定流程下所有的活动定义ID和活动定义名称
	 * 
	 * @param processDefName
	 *            流程定义名称
	 * @return
	 */
	public List<BpmsActivity> queryActivityByProcessDefID(String processDefName);

	/**
	 * 
	 * <p>
	 * Description:根据流程实例ID查询审批记录（包括起草、审批同意、审批不同意、退回、收回、转办等）
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-19
	 * @param processInstID
	 * @return List<ApprovalInfo>
	 */
	public List<ApprovalInfo> queryApprovalInfoByProcessInstID(long processInstID);

	/**
	 * 
	 * <p>
	 * Description:获得当前审批人<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-15
	 * @param proId
	 * @return String
	 */
	public String getCurrentApproval(long proId);

	/**
	 * 
	 * <p>
	 * Description:工作流生成<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-26
	 * @param bizCode
	 * @param recompenseNum
	 * @param transType
	 * @param reportDept
	 * @param reportMan
	 * @param processDefName
	 * @return String
	 */
	public Map<String, String> createWorkflow(String definitionName, String instanceName, String description, String bizCode);

	/**
	 * 
	 * <p>
	 * Description:获得业务编号<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-29
	 * @return String
	 */
	public String bizCode();

	/**
	 * 
	 * <p>
	 * Description:查询未处理的便签<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-12-2
	 * @param map
	 * @return long
	 */
	public long queryMyProcessCount(Map<String, Object> map);

	/**
	 * <p>
	 * Description: 根据工作流号查询工作流详情
	 * </p>
	 * 
	 * @author LiuY
	 * @date 2014-3-26
	 * @param processInstId
	 * @return WorkFlowInfo
	 */
	public ProcessInfo searchProcessInfoByProcessInstId(String processInstId);
	/**
	 * <p>
	 *	Description: 根据工作流号查询工作流详情
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-26
	 * @param processInstId
	 * @return
	 * WorkFlowInfo
	 */
	public ProcessInfo searchProcessInfoByBusino(String busino);

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月29日
	 * @param map
	 * @return ProcessInfo
	 */
	ProcessInfo searchProcessInfoByCondition(Map<String, Object> map);
	/**
	 * 
	 * <p>
	 * Description:获得当前的一个审批人<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年3月29日
	 * @param processInstID
	 * @return
	 * String
	 */
	String getOneCurrentApproval(long processInstID);

	long getWorkItemByproId(long proID);
}
