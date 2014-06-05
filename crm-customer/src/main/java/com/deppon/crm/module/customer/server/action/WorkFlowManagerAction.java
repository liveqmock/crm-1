package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.IWorkFLowManager;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDeptView;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.ContractExaminView;
import com.deppon.crm.module.customer.shared.domain.MemberExaminView;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVaryExaminView;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 我的工作流action<br />
 * </p>
 * @title WorkFlowManagerAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-7
 */

public class WorkFlowManagerAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -3958403945196118609L;
	private IWorkFLowManager workFLowManager;//工作流manage
	private IMemberManager memberManager;//会员manage
	private IIntegralManager integralManager;//积分管理 manager
	private IContractManager contractManager;//合同manager
	//我的工作流查询条件
	private WorkFlowCondition workflowCondition = new WorkFlowCondition();
	//查看工作流 进行审批
	private MemberExaminView memberExaminView = new MemberExaminView();
	private List<WorkFlow> workFlowList = new ArrayList<WorkFlow>();
	private WorkFlow workFlow = new WorkFlow();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
	//变更联系人
	private ContactVaryExaminView contactVaryExaminView;
	//会员变更部门
	private ChangeMemberDeptView changeMemberDeptView;
	//合同新增
	private ContractExaminView contractExaminView;
	//查询按钮类别
	private String searchButtonType;
	/**
	 * 
	 * <p>
	 * Description:查询我的工作流信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-18
	 * @return
	 * String
	 */
	public String searchWorkFLow(){
		if (workflowCondition.getEndDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(workflowCondition.getEndDate());
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE,59); 
			cal.set(Calendar.SECOND,59);
			workflowCondition.setEndDate(cal.getTime());
		}
		//我的工作流
		if(Constant.SerachButton_APPLIED.equals(searchButtonType)){
			workFlowList = workFLowManager.searchWorkFLow(workflowCondition, start, limit);
			totalCount = Long.valueOf(workFLowManager.countSearchWorkFlow(workflowCondition));
		}
		//我的已处理的工作流
		else if(Constant.SerachButton_APPROVED.equals(searchButtonType)){
			workFlowList = workFLowManager.searchMydisposeWorkFlow(workflowCondition, start, limit);
			totalCount = Long.valueOf(workFLowManager.countSearchMydisposeWorkFlow(workflowCondition));
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 查看工作流信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-18
	 * @return
	 * String
	 */
	public String viewModifyMemberExaminView(){
		memberExaminView = memberManager.createModifyMemberExaminView(workFlow.getAppId(),workFlow.getWorkFlowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 特殊会员 <br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String viewSepMemberExaminView(){
		memberExaminView = memberManager.createSepMemberExaminView(workFlow.getAppId(),workFlow.getWorkFlowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我的工作流 变更联系人 <br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-5-02
	 * @return
	 * String
	 */
	public String viewContactVary(){
		contactVaryExaminView = integralManager.createContactVaryExaminView(workFlow.getAppId(),workFlow.getWorkFlowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 变更会员归属部门<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-5-02
	 * @return
	 * String
	 */
	public String viewChangeMemberDept(){
		changeMemberDeptView = memberManager.getChangeMemberDeptView(workFlow.getWorkFlowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 合同新增 <br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-08
	 * @return
	 * String
	 */
	public String viewContractAddExamin(){
		contractExaminView = contractManager.createContractAddExaminView(workFlow.getAppId(),workFlow.getWorkFlowId());
		return SUCCESS;
	}
	
	
	public WorkFlowCondition getWorkflowCondition() {
		return workflowCondition;
	}
	public void setWorkflowCondition(WorkFlowCondition workflowCondition) {
		this.workflowCondition = workflowCondition;
	}
	public void setWorkFLowManager(IWorkFLowManager workFLowManager) {
		this.workFLowManager = workFLowManager;
	}
	public List<WorkFlow> getWorkFlowList() {
		return workFlowList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public MemberExaminView getMemberExaminView() {
		return memberExaminView;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public WorkFlow getWorkFlow() {
		return workFlow;
	}
	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}
	public ContactVaryExaminView getContactVaryExaminView() {
		return contactVaryExaminView;
	}
	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}
	public ChangeMemberDeptView getChangeMemberDeptView() {
		return changeMemberDeptView;
	}
	public ContractExaminView getContractExaminView() {
		return contractExaminView;
	}
	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}
	public void setSearchButtonType(String searchButtonType) {
		this.searchButtonType = searchButtonType;
	}
}
