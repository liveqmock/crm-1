package com.deppon.crm.module.bpsworkflow.server.manager;
 

import java.util.List;
import java.util.Map;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.crm.module.bps.shared.domain.BpsWorkflowCondition;
import com.deppon.crm.module.bps.shared.domain.WorkflowApprove;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;


/**
 * 
 * <p>
 * Description:工作流manager<br />
 * </p>
 * 
 * @title IContactWorkflowManager.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author royxhl
 * @version 0.1 2013-11-15
 */
public interface ICRMWorkflowManager {
	/**
	 * 
	 * <p>
	 * Description:查询未处理的工作流<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param workflowCondition
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> findToHandleWorkflow(BpsWorkflowCondition workflowCondition);
	/**
	 * 
	 * <p>
	 * Description:查询已处理工作流<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param workflowCondition
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> findHandledWorkflow(BpsWorkflowCondition workflowCondition);
	/**
	 * 
	 * <p>
	 * Description:工作流查询<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param workflowCondition
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> findWorkflow(BpsWorkflowCondition workflowCondition);
	/**
	 * 
	 * <p>
	 * Description查询工作流审批记录述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param proId
	 * @return
	 * List<ApprovalInfo>
	 */
	List<ApprovalInfo> queryApprovalInfoByProcessInstID(long proId);
	/**
	 * 
	 * <p>
	 * Description:工作流审批<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param workflowApprove
	 * @return
	 * boolean
	 */
	boolean workflowApprove(WorkflowApprove workflowApprove);
	/**
	 * 
	 * <p>
	 * Description:获得当前审批人<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @param proId
	 * @return
	 * String
	 */
	String getCurrentApproval(long proId);
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-19
	 * @param workItemId
	 * @param processType
	 * void
	 */
	ContractWorkflowInfo findContractWorkflowInfoByWorkNo(String workItemId, String processType);
//	/**
//	 * <p>
//	 * Description:根据工作流号查询操作人br />
//	 * </p>
//	 * @author pgj
//	 * @version 0.1 2013-11-28
//	 * @param workflowNo
//	 * @return
//	 * Department
//	 */
//	User searchOperaDeptByWorkflowId(String workflowNo);

	/**
	 * <p>
	 *	Description: 根据业务编查询大客户工作流信息
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-5
	 * @param 
	 * @return
	 * BigCustomerWorkFlowInfo
	 */
	KeyCustomerWorkflowInfo findBigCustomerWorkflowInfoByWorkNo(String workItemId, String processType);
	/**
	 * <p>
	 *	Description: 根据业务编码查询疑似重复客户工作流信息
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-7
	 * @param workItemId
	 * @param processType
	 * @return
	 * RepetitiveCustWorkFlowInfo
	 */
	RepetitiveCustWorkFlowInfo findRepetitiveCustWorkFlowInfoByWorkNo(String workItemId, String processType);
	/**
	 * <p>
	 *	Description: 根据工作流号查询营销活动详情
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-22
	 * @param workItemId
	 * @param processType
	 * @return
	 * MarketActivity
	 */
	MarketActivity findMarketActivityInfoByWorkNum(String workItemId,String processType);
	/**
	 * 根据工作流号查询工作流信息
	 * @param workFlowNo
	 * @param workFlowType
	 * @return
	 */
	public WorkFlowInfo findWorkFlowInfoEncByBusino(String workFlowNo,String workFlowType);
	/**
	 * 
	* @Title: oneKeyForApprove 
	* @Description: 一键审批
	* @author LiuY 
	* @@param workflowApprove
	* @@return boolean
	* @throws
	 */
	public boolean oneKeyForApprove(WorkflowApprove workflowApprove);
	/**
	 * 
	* @Title: getNormalClaim 
	* @Description:根据工作流号查询
	* @author LiuY 
	* @@param processInstId
	* @@return NormalClaim
	* @throws
	 */
	NormalClaim getNormalClaim(Long processInstId);
	/**
	 * 
	* @Title: getDeptChargeByProcessinstId 
	* @Description: 入部门费用
	* @author LiuY 
	* @@param processInstId
	* @@return List<DeptCharge>
	* @throws
	 */
	List<DeptCharge> getDeptChargeByProcessinstId(long processInstId);
	/**
	 * 
	* @Title: getIssueItemByProcessinstId 
	* @Description: 出险信息 
	* @author LiuY 
	* @@param processInstId
	* @@return List<IssueItem>
	* @throws
	 */
	List<IssueItem> getIssueItemByProcessinstId(long processInstId);
	/**
	 * 
	* @Title: getAwardItemByProcessinstId 
	* @Description: 奖罚明细
	* @author LiuY 
	* @@param processInstId
	* @@return List<AwardItem>
	* @throws
	 */
	List<AwardItem> getAwardItemByProcessinstId(long processInstId);
	/**
	 * 
	* @Title: getResponsibleDeptByProcessinstId 
	* @Description: 责任部门
	* @author LiuY 
	* @@param processInstId
	* @@return List<ResponsibleDept>
	* @throws
	 */
	List<ResponsibleDept> getResponsibleDeptByProcessinstId(long processInstId);
	/**
	 * 
	* @Title: getDetailOverpayByWorkNumber 
	* @Description: 多赔详情
	* @author LiuY 
	* @@param workNumber
	* @@return Overpay
	* @throws
	 */
	 public Overpay getDetailOverpayByWorkNumber(String workNumber);
	 /**
	  * 
	 * @Title: getOverpayFileList 
	 * @Description: 获取多赔上传的文件
	 * @author LiuY 
	 * @@param processInstId
	 * @@return List<FileInfo>
	 * @throws
	  */
	 public List<FileInfo> getOverpayFileList(String processInstId);
	 /**
	  * 
	 * @Title: getServiceRecoveryByOaWorkflowNum 
	 * @Description: 服务补救详情
	 * @author LiuY 
	 * @@param workflowNo
	 * @@return ServiceRecovery
	 * @throws
	  */
	 public ServiceRecovery getServiceRecoveryByOaWorkflowNum(String workflowNo);
}
