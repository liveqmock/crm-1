package com.deppon.crm.module.workflow.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.bpms.module.shared.domain.ApprovalInfo;
import com.deppon.bpms.module.shared.domain.WorkFlowInfo;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.crm.module.recompense.shared.domain.AwardItem;
import com.deppon.crm.module.recompense.shared.domain.DeptCharge;
import com.deppon.crm.module.recompense.shared.domain.IssueItem;
import com.deppon.crm.module.recompense.shared.domain.Overpay;
import com.deppon.crm.module.recompense.shared.domain.ResponsibleDept;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.workflow.server.manager.INormalClaimManager;
import com.deppon.crm.module.workflow.server.util.NormalClaimUtil;
import com.deppon.crm.module.workflow.shared.domain.NormalClaim;
import com.deppon.crm.module.workflow.shared.domain.NormalClaimCondition;
import com.deppon.crm.module.workflow.shared.domain.WorkflowApprove;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * <p>
 * Description:理赔工作流Action<br />
 * </p>
 * @title NormalClaimAction.java
 * @package com.deppon.crm.module.workflow.server.action 
 * @author liuHuan
 * @version 0.1 2013-7-29
 */
public class NormalClaimAction extends AbstractAction implements ModelDriven<NormalClaimCondition>{
	
	
	private INormalClaimManager normalClaimManager;
	
	
	
	public void setNormalClaimManager(INormalClaimManager normalClaimManager) {
		this.normalClaimManager = normalClaimManager;
	}
	//工作流号
	private String processInstId;
	
	//入部门费用
	private List<DeptCharge> deptChargeList;
	//出险信息
	private List<IssueItem> issueItemList;
	
	//奖罚明细
	private List<AwardItem> awardItemList;
	
	//责任部门
	private List<ResponsibleDept>  responsibleDeptList;
	
	private NormalClaim normalClaim;
	
	private List<WorkFlowInfo> workflowList;
	
	private Long totalCount;
	
	//工作流审批记录
	private List<ApprovalInfo> approvalInfoList;
	
	private NormalClaimCondition normalClaimCondition = new NormalClaimCondition();
	
	//当前审批人
	private  String approver;
	
	//审批对象
	private WorkflowApprove workflowApprove;
	
	//是否为印章管理员
	private boolean canChoose;
	
	//是否能上传
	private boolean canUpload;
	
	private ServiceRecovery serviceRecovery;
	
	private Overpay overpay;
	
	private List<FileInfo> overpayFileList;

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}


	public String getProcessInstId() {
		return processInstId;
	}


	public boolean isCanUpload() {
		return canUpload;
	}


	public NormalClaim getNormalClaim() {
		return normalClaim;
	}
	
	
	public List<DeptCharge> getDeptChargeList() {
		return deptChargeList;
	}


	public List<IssueItem> getIssueItemList() {
		return issueItemList;
	}


	public List<AwardItem> getAwardItemList() {
		return awardItemList;
	}


	public List<ResponsibleDept> getResponsibleDeptList() {
		return responsibleDeptList;
	}


	public List<WorkFlowInfo> getWorkflowList() {
		return workflowList;
	}


	public Long getTotalCount() {
		return totalCount;
	}


	public List<ApprovalInfo> getApprovalInfoList() {
		return approvalInfoList;
	}


	public String getApprover() {
		return approver;
	}

	
	public void setWorkflowApprove(WorkflowApprove workflowApprove) {
		this.workflowApprove = workflowApprove;
	}



	public boolean isCanChoose() {
		return canChoose;
	}
	

	public ServiceRecovery getServiceRecovery() {
		return serviceRecovery;
	}


	public Overpay getOverpay() {
		return overpay;
	}

	

	public List<FileInfo> getOverpayFileList() {
		return overpayFileList;
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
		normalClaim=normalClaimManager.getNormalClaim(proId);
		//入部门费用 
		deptChargeList= normalClaimManager.getDeptChargeByProcessinstId(proId);
		//出险信息
		issueItemList= normalClaimManager.getIssueItemByProcessinstId(proId);
		//奖罚明细
		awardItemList= normalClaimManager.getAwardItemByProcessinstId(proId);
		//责任部门
		responsibleDeptList= normalClaimManager.getResponsibleDeptByProcessinstId(proId);
		return SUCCESS;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:未处理的工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @return
	 * String
	 */
	@JSON
	public String findToHandleWorkflow(){
		Map<String,Object> map =normalClaimManager.findToHandleWorkflow(normalClaimCondition);
		workflowList=(List<WorkFlowInfo>)map.get("list");
		totalCount=(Long)map.get("totalCount");
		return SUCCESS;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:已处理的工作流<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @return
	 * String
	 */
	@JSON
	public String findHandledWorkflow(){
		Map<String,Object> map =normalClaimManager.findHandledWorkflow(normalClaimCondition);
		workflowList=(List<WorkFlowInfo>)map.get("list");
		totalCount=(Long)map.get("totalCount");
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:工作流查询<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @return
	 * String
	 */
	@JSON
	public String findWorkflow(){
		Map<String,Object> map =normalClaimManager.findWorkflow(normalClaimCondition);
		workflowList=(List<WorkFlowInfo>)map.get("list");
		totalCount=(Long)map.get("totalCount");
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:查询工作流审批记录<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @return
	 * String
	 */
	@JSON
	public String queryApprovalInfo(){
		long proId=Long.parseLong(EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY));	
		approvalInfoList=normalClaimManager.queryApprovalInfoByProcessInstID(proId);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:工作流审批<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-9
	 * @return
	 * String
	 */
	@JSON
	public String workflowApprove(){
		workflowApprove.setProcessInstId(Long.parseLong(EncryptUtil.decrypt(workflowApprove.getProcessInstIdEnc(),NormalClaimUtil.WORKFLOW_DESC_KEY)));
		workflowApprove.setWorkItemId(Long.parseLong(EncryptUtil.decrypt(workflowApprove.getWorkItemIdEnc(),NormalClaimUtil.WORKFLOW_DESC_KEY)));
		boolean result = normalClaimManager.workflowApprove(workflowApprove);
		return result ? SUCCESS : ERROR;
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取当前审批人<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-10
	 * @return
	 * String
	 */
	@JSON
	public String findCurrentApproval(){
		long proId=Long.parseLong(EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY));	
	    approver=normalClaimManager.getCurrentApproval(proId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:流程图<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-15
	 * @return
	 * String
	 */
	@JSON
	public String generateWorkflowImage(){
		processInstId=EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY);		
		return SUCCESS;
	}
	
	
	/**
	 * 判断是审批相关权限
	 * <p>
	 * Description:判断是审批相关权限<br />
	 * </p>
	 * @author liuHuan
	 * @version 0.1 2013-8-20
	 * @return
	 * String
	 */
	@JSON
	public String findApprovePermission(){
		long proId=Long.parseLong(EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY));	
		canChoose= normalClaimManager.findCanChoose(proId);//是否能选不同意
		canUpload=normalClaimManager.findCanUpload();//是否有上传权限
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
		String proId=EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY);		
		serviceRecovery=normalClaimManager.getServiceRecoveryByOaWorkflowNum(proId);
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
		String proId=EncryptUtil.decrypt(processInstId,NormalClaimUtil.WORKFLOW_DESC_KEY);		
		overpay=normalClaimManager.getDetailOverpayByWorkNumber(proId);
		overpayFileList =normalClaimManager.getOverpayFileList(proId);
		return SUCCESS;
		
	}
	
	
	@Override
	public NormalClaimCondition getModel() {
		return normalClaimCondition;
	}

}
