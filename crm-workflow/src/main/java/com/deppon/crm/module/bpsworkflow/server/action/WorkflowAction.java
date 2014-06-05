package com.deppon.crm.module.bpsworkflow.server.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.crm.module.bps.shared.domain.BpsWorkflowCondition;
import com.deppon.crm.module.bps.shared.domain.WorkflowApprove;
import com.deppon.crm.module.bpsworkflow.server.manager.ICRMWorkflowManager;
import com.deppon.crm.module.bpsworkflow.server.util.BpsConstant;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.opensymphony.xwork2.ModelDriven;
 
/**
 * 
 * <p>
 * Description:工作流管理action<br />
 * </p>
 * @title WorkflowAction.java
 * @package com.deppon.crm.module.workflow.server.action 
 * @author 106138
 * @version 0.1 2014-2-27
 */
public class WorkflowAction extends AbstractAction implements
		ModelDriven<BpsWorkflowCondition> { 
	private static final long serialVersionUID = -4873263073416831380L;
	//工作流信息
	private List<WorkFlowInfo> workflowList = new ArrayList<WorkFlowInfo>();
	//工作流查询条件
	private BpsWorkflowCondition workflowCondition = new BpsWorkflowCondition();
	//工作流管理manager
	private ICRMWorkflowManager crmWorkflowManager;
	
	/**
	* @Title: setCrmWorkflowManager 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param crmWorkflowManager void
	* @throws
	 */
	public void setCrmWorkflowManager(ICRMWorkflowManager crmWorkflowManager) {
		this.crmWorkflowManager = crmWorkflowManager;
	}

	//合同工作流信息
	private ContractWorkflowInfo contractWorkflowInfo;
	// 当前审批人
	private String approver;
	// 审批对象
	private WorkflowApprove workflowApprove;
	// 工作流号
	private String processInstId;
	// 工作流审批记录
	private List<ApprovalInfo> approvalInfoList;
	//工作流号码
	private String busino;
	//流程定义名称
	private String processDefName;
	//大客户工作流信息
	private KeyCustomerWorkflowInfo keyCustomerWorkflowInfo;
	//疑似重复客户工作流信息
	private RepetitiveCustWorkFlowInfo repetitiveCustWorkFlowInfo;
	//营销实体
	private MarketActivity marketActivity;
	//多赔实体
	private Overpay overpay;
	//多赔附件
	private List<FileInfo> overpayFileList;
	//服务补救实体
	private ServiceRecovery serviceRecovery;
	//入部门费用
	private List<DeptCharge> deptChargeList;
	//出险信息
	private List<IssueItem> issueItemList;
	//奖罚明细
	private List<AwardItem> awardItemList;
	//责任部门
	private List<ResponsibleDept>  responsibleDeptList;
	//常规理赔实体
	private NormalClaim normalClaim;
	//工作流信息实体
	private WorkFlowInfo wfi;
	//工作流号
	private String workFlowNo;
	//工作流类型
	private String workFlowType; 
	/**
	 * 
	 * <p>
	 * Description:未处理工作流<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-2-27
	 * @return
	 * String
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String findToHandleWorkflow() {
		//根据条件查询未处理的工作流
		Map<String, Object> map = crmWorkflowManager
				.findToHandleWorkflow(getWorkflowCondition());
		//查询到的工作流列表
		workflowList = (List<WorkFlowInfo>) map.get("list");
		//总数
		totalCount = (Long) map.get("totalCount") == 0 ? 1l :(Long) map.get("totalCount");
		return SUCCESS;

	}

/**
 * 
 * <p>
 * Description:已处理工作流<br />
 * </p>
 * @author 106138
 * @version 0.1 2014-2-27
 * @return
 * String
 */
	@SuppressWarnings("unchecked")
	@JSON
	public String findHandledWorkflow() {

		Map<String, Object> map = crmWorkflowManager
				.findHandledWorkflow(getWorkflowCondition());
		workflowList = (List<WorkFlowInfo>) map.get("list");
		totalCount = (Long) map.get("totalCount") == 0 ? 1l :(Long) map.get("totalCount");
		return SUCCESS;

	}

	/**
	 * 
	 * <p>
	 * Description:工作流查询<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-2-27
	 * @return
	 * String
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String findWorkflow() {
		//根据条件查询工作流
		 Map<String, Object> map = crmWorkflowManager
		 .findWorkflow(getWorkflowCondition());
		 workflowList = (List<WorkFlowInfo>) map.get("list");
		 totalCount = (Long) map.get("totalCount") == 0 ? 1l : (Long) map.get("totalCount");
		return SUCCESS;
	}
	/**
	 * 
	* @Title: getWorkflowCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return BpsWorkflowCondition
	* @throws
	 */
	public BpsWorkflowCondition getWorkflowCondition() {
		return workflowCondition;
	}

	/**
	 * 
	 * <p>
	 * Description:查询工作流审批记录<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-2-27
	 * @return
	 * String
	 */
	@JSON
	public String queryApprovalInfo() {
	//把前台的加密后的工作流号解密
		long proId = Long.parseLong(EncryptUtil.decrypt(processInstId,
				BpsConstant.WORKFLOW_DESC_KEY));
		//根据工作流号查询
		approvalInfoList=crmWorkflowManager
				.queryApprovalInfoByProcessInstID(proId);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:工作流审批<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-2-27
	 * @return
	 * String
	 */
	@JSON
	public String workflowApprove() {
		//解密流程id
		long processId=Long.parseLong(EncryptUtil.decrypt(
				workflowApprove.getProcessInstIdEnc(),
				BpsConstant.WORKFLOW_DESC_KEY));
		//解密工作项
		long workItemId=Long.parseLong(EncryptUtil.decrypt(
				workflowApprove.getWorkItemIdEnc(),
				BpsConstant.WORKFLOW_DESC_KEY));
		//解密业务编号
		String busino = EncryptUtil.decrypt(workflowApprove.getBusino(),
				BpsConstant.WORKFLOW_DESC_KEY);
		//在工作流信息设置上的上列信息
		workflowApprove.setProcessInstId(processId);
		workflowApprove.setWorkItemId(workItemId);
		workflowApprove.setBusino(busino);
		//进行工作流审批
		boolean result = crmWorkflowManager
				.workflowApprove(workflowApprove);
		return result ? SUCCESS : ERROR;
	}

	/**
	 * 
	 * <p>
	 * Description:获取当前审批人<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @return String
	 */
	@JSON
	public String findCurrentApproval() {
		//解密
		long proId = Long.parseLong(EncryptUtil.decrypt(processInstId,
				BpsConstant.WORKFLOW_DESC_KEY));
		//或者当前审批人信息
		approver = crmWorkflowManager.getCurrentApproval(proId);
		return SUCCESS;
	}
	/**
	 * 获取待一键审批的工作流信息
	 * @return
	 */
	@JSON
	public String findOneKeyForApproveWorkFlow(){
		wfi = crmWorkflowManager.findWorkFlowInfoEncByBusino(workFlowNo, workFlowType);
		return SUCCESS;
	}
	/**
	 * 
	* @Title: oneKeyForApprove 
	* @Description: 一键审批 
	* @author LiuY 
	* @return String
	* @throws
	 */
	@JSON
	public String oneKeyForApprove(){
		crmWorkflowManager.oneKeyForApprove(workflowApprove);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:流程图<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-15
	 * @return String
	 */
	@JSON
	public String generateWorkflowImage() {
		//只需要解密工作流号到前台即可
		processInstId = (EncryptUtil.decrypt(processInstId,
				BpsConstant.WORKFLOW_DESC_KEY));
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询对应的合同信息<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-19
	 * @return ContractWorkflowInfo
	 */
	@JSON
	public String findContractWorkflowInfoByWorkNo() {
		busino = EncryptUtil.decrypt(busino,
				BpsConstant.WORKFLOW_DESC_KEY);
		 contractWorkflowInfo= crmWorkflowManager.findContractWorkflowInfoByWorkNo(
				 busino, processDefName);
		return SUCCESS;

	}
	
	/**
	 * <p>
	 *	Description: 大客户工作流-根据工作流号查询工作流
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-4
	 * @return
	 * String
	 */
	@JSON
	public String findBigCustomerWorkflowInfoByWorkNo(){
		busino = EncryptUtil.decrypt(busino,
				BpsConstant.WORKFLOW_DESC_KEY);
		keyCustomerWorkflowInfo = crmWorkflowManager.findBigCustomerWorkflowInfoByWorkNo(
				 busino, processDefName);
		 return SUCCESS;
	}
	/**
	 * <p>
	 *	Description: 疑似重复客户工作流-根据工作流号查询工作流
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-4
	 * @return
	 * String
	 */
	@JSON
	public String findRepetitiveCustByWorkNo(){
		busino = EncryptUtil.decrypt(busino,
				BpsConstant.WORKFLOW_DESC_KEY);
		repetitiveCustWorkFlowInfo = crmWorkflowManager.findRepetitiveCustWorkFlowInfoByWorkNo(busino, processDefName);
		 return SUCCESS;
	}
	/**
	 * <p>
	 *	Description: 区域营销工作流--根据工作流号查询工作流
	 * </p> 
	 * @author LiuY
	 * @date 2014-3-22
	 * @return
	 * String
	 */
	@JSON
	public String findMarketActivityByWorkNo(){
		busino = EncryptUtil.decrypt(busino,
				BpsConstant.WORKFLOW_DESC_KEY);
		marketActivity = crmWorkflowManager.findMarketActivityInfoByWorkNum(busino,processDefName);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:多赔工作流详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-11
	 * @return
	 * String
	 */
	@JSON
	public String findOverpayByProNum(){
		String proId=EncryptUtil.decrypt(processInstId,BpsConstant.WORKFLOW_DESC_KEY);		
		overpay=crmWorkflowManager.getDetailOverpayByWorkNumber(proId);
		overpayFileList =crmWorkflowManager.getOverpayFileList(proId);
		return SUCCESS;
		
	}
	/**
	 * 
	 * <p>
	 * Description:服务补救工作流详情<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2014-1-11
	 * @return
	 * String
	 */
	@JSON
	public String findServiceByProNum(){
		String proId=EncryptUtil.decrypt(processInstId,BpsConstant.WORKFLOW_DESC_KEY);		
		serviceRecovery=crmWorkflowManager.getServiceRecoveryByOaWorkflowNum(proId);
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:工作流号查询理赔工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-7-29
	 * @return
	 * String
	 */
	@JSON
	public String findNormalClaimByProNum(){
		long proId=Long.parseLong(EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY));		
		normalClaim=crmWorkflowManager.getNormalClaim(proId);
		//入部门费用 
		deptChargeList= crmWorkflowManager.getDeptChargeByProcessinstId(proId);
		//出险信息
		issueItemList= crmWorkflowManager.getIssueItemByProcessinstId(proId);
		//奖罚明细
		awardItemList= crmWorkflowManager.getAwardItemByProcessinstId(proId);
		//责任部门
		responsibleDeptList= crmWorkflowManager.getResponsibleDeptByProcessinstId(proId);
		return SUCCESS;
		
	}
	/**
	 * 以下为get/set方法
	 * *****************************************************************************************
	 */
	/**
	 * 
	* @Title: setWorkflowCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param workflowCondition void
	* @throws
	 */
	public void setWorkflowCondition(BpsWorkflowCondition workflowCondition) {
		this.workflowCondition = workflowCondition;
	}
	/**
	 * 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return 
	* @throws
	 */
	@Override
	public BpsWorkflowCondition getModel() {
		return workflowCondition;
	}
	/**
	 * 
	* @Title: getProcessInstId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return String
	* @throws
	 */
	public String getProcessInstId() {
		return processInstId;
	}
	/**
	 * 
	* @Title: setProcessInstId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param processInstId void
	* @throws
	 */
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	/**
	 * 
	* @Title: getContractWorkflowInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return ContractWorkflowInfo
	* @throws
	 */
	public ContractWorkflowInfo getContractWorkflowInfo() {
		return contractWorkflowInfo;
	}
	/**
	 * 
	* @Title: setContractWorkflowInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param contractWorkflowInfo void
	* @throws
	 */
	public void setContractWorkflowInfo(
			ContractWorkflowInfo contractWorkflowInfo) {
		this.contractWorkflowInfo = contractWorkflowInfo;
	}
	/**
	 * 
	* @Title: getApprovalInfoList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<ApprovalInfo>
	* @throws
	 */
	public List<ApprovalInfo> getApprovalInfoList() {
		return approvalInfoList;
	}
	/**
	 * 
	* @Title: setApprovalInfoList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param approvalInfoList void
	* @throws
	 */
	public void setApprovalInfoList(List<ApprovalInfo> approvalInfoList) {
		this.approvalInfoList = approvalInfoList;
	}
	/**
	 * 
	* @Title: getProcessDefName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return String
	* @throws
	 */
	public String getProcessDefName() {
		return processDefName;
	}
	/**
	 * 
	* @Title: setProcessDefName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param processDefName void
	* @throws
	 */
	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}
	/**
	 * 
	* @Title: getApprover 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return String
	* @throws
	 */
	public String getApprover() {
		return approver;
	}
	/**
	 * 
	* @Title: getWfi 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return WorkFlowInfo
	* @throws
	 */
	public WorkFlowInfo getWfi() {
		return wfi;
	}
	/**
	 * 
	* @Title: setWfi 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param wfi void
	* @throws
	 */
	public void setWfi(WorkFlowInfo wfi) {
		this.wfi = wfi;
	}
	/**
	 * 
	* @Title: setApprover 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param approver void
	* @throws
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}
	/**
	 * 
	* @Title: getWorkflowApprove 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return WorkflowApprove
	* @throws
	 */
	public WorkflowApprove getWorkflowApprove() {
		return workflowApprove;
	}
	/**
	 * 
	* @Title: setWorkflowApprove 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param workflowApprove void
	* @throws
	 */
	public void setWorkflowApprove(WorkflowApprove workflowApprove) {
		this.workflowApprove = workflowApprove;
	}

	/**
	 *@author chenaichun
	 * @date 2013-12-17 上午11:30:59 
	 *@return the busino
	 */
	public String getBusino() {
		return busino;
	}

	/**
	 *@author chenaichun
	 * @date 2013-12-17 上午11:31:00 
	 * @param busino the busino to set
	 */
	public void setBusino(String busino) {
		this.busino = busino;
	}
	/**
	 * 
	* @Title: getWorkflowList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<WorkFlowInfo>
	* @throws
	 */
	public List<WorkFlowInfo> getWorkflowList() {
		return workflowList;
	}
	/**
	 * @return the keyCustomerWorkflowInfo
	 */
	public KeyCustomerWorkflowInfo getKeyCustomerWorkflowInfo() {
		return keyCustomerWorkflowInfo;
	}
	/**
	 * @param keyCustomerWorkflowInfo the keyCustomerWorkflowInfo to set
	 */
	public void setKeyCustomerWorkflowInfo(
			KeyCustomerWorkflowInfo keyCustomerWorkflowInfo) {
		this.keyCustomerWorkflowInfo = keyCustomerWorkflowInfo;
	}
	/**
	 * @return the repetitiveCustWorkFlowInfo
	 */
	public RepetitiveCustWorkFlowInfo getRepetitiveCustWorkFlowInfo() {
		return repetitiveCustWorkFlowInfo;
	}
	/**
	 * @param repetitiveCustWorkFlowInfo the repetitiveCustWorkFlowInfo to set
	 */
	public void setRepetitiveCustWorkFlowInfo(
			RepetitiveCustWorkFlowInfo repetitiveCustWorkFlowInfo) {
		this.repetitiveCustWorkFlowInfo = repetitiveCustWorkFlowInfo;
	}
	/**
	 * @return the marketActivity
	 */
	public MarketActivity getMarketActivity() {
		return marketActivity;
	}
	/**
	 * @param marketActivity the marketActivity to set
	 */
	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
	}
	/**
	 * 
	* @Title: getWorkFlowNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return String
	* @throws
	 */
	public String getWorkFlowNo() {
		return workFlowNo;
	}
	/**
	 * 
	* @Title: setWorkFlowNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param workFlowNo void
	* @throws
	 */
	public void setWorkFlowNo(String workFlowNo) {
		this.workFlowNo = workFlowNo;
	}
	/**
	 * 
	* @Title: getWorkFlowType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return String
	* @throws
	 */
	public String getWorkFlowType() {
		return workFlowType;
	}
	/**
	 * 
	* @Title: setWorkFlowType 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param workFlowType void
	* @throws
	 */
	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}
	/**
	 * 
	* @Title: getOverpay 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return Overpay
	* @throws
	 */
	public Overpay getOverpay() {
		return overpay;
	}
	/**
	 * 
	* @Title: setOverpay 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param overpay void
	* @throws
	 */
	public void setOverpay(Overpay overpay) {
		this.overpay = overpay;
	}
	/**
	 * 
	* @Title: getOverpayFileList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<FileInfo>
	* @throws
	 */
	public List<FileInfo> getOverpayFileList() {
		return overpayFileList;
	}
	/**
	 * 
	* @Title: setOverpayFileList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param overpayFileList void
	* @throws
	 */
	public void setOverpayFileList(List<FileInfo> overpayFileList) {
		this.overpayFileList = overpayFileList;
	}
	/**
	 * 
	* @Title: getServiceRecovery 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return ServiceRecovery
	* @throws
	 */
	public ServiceRecovery getServiceRecovery() {
		return serviceRecovery;
	}
	/**
	 * 
	* @Title: setServiceRecovery 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param serviceRecovery void
	* @throws
	 */
	public void setServiceRecovery(ServiceRecovery serviceRecovery) {
		this.serviceRecovery = serviceRecovery;
	}
	/**
	 * 
	* @Title: getDeptChargeList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<DeptCharge>
	* @throws
	 */
	public List<DeptCharge> getDeptChargeList() {
		return deptChargeList;
	}
	/**
	 * 
	* @Title: setDeptChargeList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param deptChargeList void
	* @throws
	 */
	public void setDeptChargeList(List<DeptCharge> deptChargeList) {
		this.deptChargeList = deptChargeList;
	}
	/**
	 * 
	* @Title: getIssueItemList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<IssueItem>
	* @throws
	 */
	public List<IssueItem> getIssueItemList() {
		return issueItemList;
	}
	/**
	 * 
	* @Title: setIssueItemList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param issueItemList void
	* @throws
	 */
	public void setIssueItemList(List<IssueItem> issueItemList) {
		this.issueItemList = issueItemList;
	}
	/**
	 * 
	* @Title: getAwardItemList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<AwardItem>
	* @throws
	 */
	public List<AwardItem> getAwardItemList() {
		return awardItemList;
	}
	/**
	 * 
	* @Title: setAwardItemList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param awardItemList void
	* @throws
	 */
	public void setAwardItemList(List<AwardItem> awardItemList) {
		this.awardItemList = awardItemList;
	}
	/**
	 * 
	* @Title: getResponsibleDeptList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return List<ResponsibleDept>
	* @throws
	 */
	public List<ResponsibleDept> getResponsibleDeptList() {
		return responsibleDeptList;
	}
	/**
	 * 
	* @Title: setResponsibleDeptList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param responsibleDeptList void
	* @throws
	 */
	public void setResponsibleDeptList(List<ResponsibleDept> responsibleDeptList) {
		this.responsibleDeptList = responsibleDeptList;
	}
	/**
	 * 
	* @Title: getNormalClaim 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @return NormalClaim
	* @throws
	 */
	public NormalClaim getNormalClaim() {
		return normalClaim;
	}
	/**
	 * 
	* @Title: setNormalClaim 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author LiuY 
	* @param normalClaim void
	* @throws
	 */
	public void setNormalClaim(NormalClaim normalClaim) {
		this.normalClaim = normalClaim;
	}

	
	
	

}
