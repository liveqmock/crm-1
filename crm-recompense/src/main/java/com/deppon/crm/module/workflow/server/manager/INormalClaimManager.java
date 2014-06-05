package com.deppon.crm.module.workflow.server.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.BpmsParticipant;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.bpmsapi.module.client.domain.BpmsActivity;
import com.deppon.bpmsapi.module.client.domain.BranchRule;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.crm.module.workflow.shared.domain.NormalClaimCondition;
import com.deppon.crm.module.workflow.shared.domain.WorkflowApprove;

/**
 * 
 * <p>
 * Description:理赔工作流管理接口<br />
 * </p>
 * @title INormalClaimService.java
 * @package com.deppon.crm.module.workflow.server.service 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public interface INormalClaimManager {
	

	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-29
	 * @param processInstId
	 * @return
	 * NormalClaim
	 */
	NormalClaim getNormalClaim(Long processInstId);

	/**
	 * 起草工作流
	 * @return
	 */
	long createAndStartProcess();
	
	/**
	 * 起草工作流
	 * @param recompenseNum 理赔单号
	 * @param transType 运输类型
	 * @param reportDept 报案人部门ID
	 * @param reportMan 报案人Id
	 * @param reportDeptName 报案人部门名称
	 * @return
	 */
	String createWorkflow(String recompenseNum, 
			String transType, 
			String reportDept, 
			String reportMan, 
			String reportDeptName);
	
	/**
	 * 根据流程实例ID，判断当前用户是否可以收回流程
	 * @param processInstID 流程实例ID
	 * @return
	 */
	boolean isDrawBackByProcessInstID(
			Long processInstID);
	
	/**
	 * 根据工作项ID，判断当前用户是否可以收回流程
	 * @param workItemID  工作项ID
	 * @return
	 */
	boolean isDrawBackByWorkItemId(
			Long workItemID);
	
	/**
	 * 通过流程实例ID收回流程
	 * @param processInstID 流程实例ID
	 * @return
	 */
	boolean drawBackByProcessInstID(
			Long processInstID);
	
	/**
	 * 通过工作项ID收回流程
	 * @param workItemID  工作项ID
	 * @return
	 */
	boolean drawBackByWorkItemId(
			Long workItemID);
	
	/**
	 * 判断该流程是否可以删除
	 * @param processInstID 流程实例ID
	 * @return
	 */
	boolean isRemoveProcessInstExt( 
			Long processInstID);
	
	/**
	 * 执行删除流程操作
	 * @param processInstID 流程实例ID
	 * @return
	 */
	boolean removeMyProcessInstExt(
			Long processInstID);
	
	/**
	 * 工作流审批
	 * @param workItemID 工作项ID
	 * @param processInstID 流程实例ID
	 * @param approveOperateType 操作类型
	 * @param approveOpinion 审批意见
	 * @param parts 转办参与者数组
	 * @param processDefName 流程定义名称
	 * @return
	 */
	boolean workflowApprove(
			long workItemID, 
			long processInstID, 
			String approveOperateType, 
			String approveOpinion, 
			BpmsParticipant[] parts,String processDefName);
	
	/**
	 * 根据流程实例ID查询审批记录（包括起草、审批同意、审批不同意、退回、收回、转办等）
	 * @param processInstID 流程实例ID
	 * @return
	 */
	List<ApprovalInfo> queryApprovalInfoByProcessInstID(
			long processInstID);
	
	/**
	 * 查询指定系统指定用户的待办流程信息
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param fstandardcode 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	List<WorkFlowInfo> queryTodoItems(
			int start,
			int limit, 
			String processDefName, 
			String applerId, 
			String fstandardcode, 
			String state,
			Date startTime, 
			Date endTime,
			String workflowNo);
	
	/**
	 * 查询指定用户的指定业务系统的待办流程数目
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param fstandardcode 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	long queryTodoCounts(
			String processDefName, 
			String applerId, 
			String fstandardcode, 
			String state, 
			Date startTime, 
			Date endTime,
			String workflowNo);
	
	/**
	 * 查询指定用户的指定系统的已审批流程信息
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param departmentId 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	List<WorkFlowInfo> queryFinishedItems(
			int start,
			int limit, 
			String processDefName, 
			String applerId, 
			String departmentId, 
			String state, 
			Date startTime, 
			Date endTime,
			String workflowNo);
	
	/**
	 * 查询指定用户的指定业务系统的已办工作项数目
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param applerId 申请人工号
	 * @param departmentId 申请人所在部门编码
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	Long queryFinishedCounts(
			String processDefName, 
			String applerId, 
			String departmentId, 
			String state, 
			Date startTime, 
			Date endTime,
			String workflowNo);
	
	/**
	 * 根据业务编号设置流程业务状态
	 * @param bizCode 业务编号
	 * @param state 业务状态
	 * @return
	 */
	boolean setProcessInstBusinessState(
			String bizCode, 
			String state);
	
	/**
	 * 查询流程实例下对应的参与者信息
	 * @param processInstId 流程实例ID
	 * @param activityDefId 环节定义ID
	 * @param flag 1：查询已处理参与者   2：查询环节配置参与者
	 * @return
	 */
	BpmsParticipant[] getActivityParticipant(
			Long processInstId, 
			String activityDefId, 
			String flag);
	
	/**
	 * 结束当前流程
	 * @param processInstId 流程实例ID
	 * @return
	 */
	boolean terminateProcessInstance(
			Long processInstId);
	
	/**
	 * 获取下一步可到达的节点
	 * @param workItemId 工作项ID
	 * @param processInstId 流程实例ID
	 * @param approveOperateType 操作类型
	 * @return
	 */
	BranchRule[] getNextActivities(
			long workItemId,
			long processInstId, 
			int approveOperateType);
	
	/**
	 * 理赔工作流详情
	 * @param workflowNo CRM流程号
	 * @return
	 */
	NormalClaim getNormalClaimByWorkflowNo(
			String workflowNo);
	
	/**
	 * 入部门费用
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<DeptCharge> getDeptChargeByProcessinstId(
			long processInstId);
	
	/**
	 * 出险信息
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<IssueItem> getIssueItemByProcessinstId(
			long processInstId);
	
	/**
	 * 奖罚明细
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<AwardItem> getAwardItemByProcessinstId(
			long processInstId);
	
	/**
	 * 责任部门
	 * @param processInstId 流程实例ID
	 * @return
	 */
	List<ResponsibleDept> getResponsibleDeptByProcessinstId(
			long processInstId);
	
	/**
	 * 获取当前审批人信息
	 * @param processInstId 流程实例ID
	 * @return 审批节点      【姓名   工号】
	 */
	String getCurrentApproval(
			long processInstId);
	
	/**
	 * 查询指定用户的指定系统的我申请的流程信息
	 * @param start 分页开始
	 * @param limit 每页条数
	 * @param processDefName 流程定义名称
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @param workflowNo 工作流号
	 * @return
	 */
	List<WorkFlowInfo> queryMyWorkFlow(
			int start,
			int limit, 
			String processDefName, 
			String state, 
			Date startTime, 
			Date endTime,
			String workflowNo);
	
	/**
	 * 查询指定用户的指定系统的我申请的流程数目
	 * @param processDefName 流程定义名称
	 * @param state 
	 * 1    未提交
       2  未审批
       3  审批中
       4  已同意
       5  未同意
       6  已退回
       7  汇款失败
       8  汇款成功
	 * @param startTime 申请日期
	 * @param endTime 申请日期
	 * @return
	 */
	long queryMyWorkFlowCount(
			String processDefName, 
			String state, 
			Date startTime, 
			Date endTime, 
			String workflowNo);
	
	/**
	 * 
	 * <p>
	 * Description:查询未处理的工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @param normalClaimCondition 查询条件
	 * @return
	 * Map<String,Object> list totalCount
	 */
	 Map<String,Object>  findToHandleWorkflow(NormalClaimCondition normalClaimCondition);
	 
	 
	 /**
	  * 
	  * <p>
	  * Description:查询已处理的工作流<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-9
	  * @param normalClaimCondition 查询条件
	  * @return
	  * Map<String,Object> list totalCount
	  */
	 Map<String,Object>  findHandledWorkflow(NormalClaimCondition normalClaimCondition);
	 
	 
	 /**
	  * 
	  * <p>
	  * Description:工作流查询<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-9
	  * @param normalClaimCondition 查询条件
	  * @return
	  * Map<String,Object> list totalCount
	  */
	 Map<String,Object>  findWorkflow(NormalClaimCondition normalClaimCondition);
	 
	 /**
	  * 
	  * <p>
	  * Description:工作流审批<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-12
	  * @param workflowApprove
	  * @return
	  * boolean
	  */
	 boolean workflowApprove(WorkflowApprove workflowApprove);
	 
	 /**
	  * 获取指定流程下所有的活动定义ID和活动定义名称
	  * @param processDefName 流程定义名称
	  * @return
	  */
	 List<BpmsActivity> queryActivityByProcessDefID(String processDefName);
	 
	 /**
	  * 
	  * <p>
	  * Description:判断是否能选不同意<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-20
	  * @return
	  * boolean
	  */
	 boolean findCanChoose(long processInstID);
	 
	 /**
	  * 
	  * <p>
	  * Description:判断是有上传权限<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2013-8-20
	  * @return
	  * boolean
	  */
	 boolean findCanUpload();
	 
	 
	 /**
	 * 
	 * <p>
	 * Description:服务补救详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-11
	 * @param workflowNo
	 * @return
	 * ServiceRecovery
	 */
	 public ServiceRecovery getServiceRecoveryByOaWorkflowNum(String workflowNo);
	 
	 /**
	  * 
	  * <p>
	  * Description:多赔详情<br />
	  * </p>
	  * @author liuHuan
	  * @version 0.1 2014-1-14
	  * @param workNumber
	  * @return
	  * Overpay
	  */
	  public Overpay getDetailOverpayByWorkNumber(String workNumber) ;
	  
	  /**
	   * 
	   * <p>
	   * Description:获取多赔上传的文件<br />
	   * </p>
	   * @author liuHuan
	   * @version 0.1 2014-2-13
	   * @param processInstId
	   * @return
	   * List<FileInfo>
	   */
	  public List<FileInfo> getOverpayFileList(String processInstId);
}
