package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberWorkFlowManager;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDeptView;
import com.deppon.crm.module.customer.shared.domain.ContractExaminView;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.domain.MemberExaminView;
import com.deppon.crm.module.customer.shared.domain.MyToDoWorkFlow;
import com.deppon.crm.module.customer.shared.domain.WorkFlowCondition;
import com.deppon.crm.module.customer.shared.domain.integral.ContactVaryExaminView;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 我要处理的工作流action<br />
 * </p>
 * @title MyWorkFlowDealManagerAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-7
 */

public class MyWorkFlowDealManagerAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -3244988792224574332L;
	private IAlterMemberManager alterMemberManager;//弹出框查会员manage
	private IMemberWorkFlowManager memberWorkFlowManager;//工作流处理manage
	private IIntegralManager integralManager;//积分管理 manager
	private IMemberManager memberManager;//会员manage
	private IContractManager contractManager;//合同manager
	//我的工作流查询条件
	WorkFlowCondition workflowCondition = new WorkFlowCondition();
	//工作流查询结果
	private List<MyToDoWorkFlow> workflowSearchResult = new ArrayList<MyToDoWorkFlow>();
	//查看工作流 进行审批
	private MemberExaminView memberExaminView = new MemberExaminView();
	//工作流 实体
	private TodoWorkflow todoWorkflow = new TodoWorkflow();
	// 工作流审批结果
	private ExamineRecord examineRecord = new ExamineRecord();
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
	/**
	 * 
	 * <p>
	 * Description:查询我要处理的工作流信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String searchWorkflowList(){
		if (workflowCondition.getEndDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(workflowCondition.getEndDate());
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE,59); 
			cal.set(Calendar.SECOND,59);
			workflowCondition.setEndDate(cal.getTime());
		}
		workflowSearchResult = alterMemberManager.searchMyWorkflowByCondition(workflowCondition,start,limit);
		totalCount = Long.valueOf(alterMemberManager.countWorkflowByCondition(workflowCondition));
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 会员信息维护 进行审批<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String acquireModifyMemberExaminView(){
		memberExaminView = memberManager.createModifyMemberExaminView(todoWorkflow.getApplicationId(),todoWorkflow.getWorkflowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 特殊会员 进行审批<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String acquireSepMemberExaminView(){
		memberExaminView = memberManager.createSepMemberExaminView(todoWorkflow.getApplicationId(),todoWorkflow.getWorkflowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:我要处理的工作流审批 操作 创建特殊会员<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-13
	 * @return
	 * String
	 */
	public String commitSepCreateMemberExamin(){
		memberWorkFlowManager.commitSepCreateMemberExamin(examineRecord);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:我要处理的工作流审批 操作 修改会员信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-13
	 * @return
	 * String
	 */
	public String commitModifyMemberExamin(){
		memberWorkFlowManager.commitModifyMemberExamin(examineRecord);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:我要处理的工作流审批 操作 修改联系人挂靠<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.2 2012-5-3
	 * @return
	 * String
	 */
	public String commitMemberContactRalationExamin(){
		memberWorkFlowManager.commitContactVaryExamin(examineRecord);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 变更联系人 进行审批<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-5-02
	 * @return
	 * String
	 */
	public String acquireContactVary(){
		contactVaryExaminView = integralManager.createContactVaryExaminView(todoWorkflow.getApplicationId(),todoWorkflow.getWorkflowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 变更会员归属部门<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-08
	 * @return
	 * String
	 */
	public String acquireChangeMemberDept(){
		changeMemberDeptView = memberManager.getChangeMemberDeptView(todoWorkflow.getWorkflowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 变更会员归属部门 进行审批<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-08
	 * @return
	 * String
	 */
	public String commitChangeMemberDeptExamin(){
		memberWorkFlowManager.commitChangeMemberDeptExamin(examineRecord);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 合同新增 进行审批<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-08
	 * @return
	 * String
	 */
	public String acquireContractAdd(){
		contractExaminView = contractManager.createContractAddExaminView(todoWorkflow.getApplicationId(),todoWorkflow.getWorkflowId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:查看我要处理的工作流 合同新增 进行审批<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-08
	 * @return
	 * String
	 */
	public String commitContractAddExamin(){
		memberWorkFlowManager.commitContractAddExamin(examineRecord);
		return SUCCESS;
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

	public WorkFlowCondition getWorkflowCondition() {
		return workflowCondition;
	}

	public void setWorkflowCondition(WorkFlowCondition workflowCondition) {
		this.workflowCondition = workflowCondition;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public List<MyToDoWorkFlow> getWorkflowSearchResult() {
		return workflowSearchResult;
	}

	public void setMemberWorkFlowManager(
			IMemberWorkFlowManager memberWorkFlowManager) {
		this.memberWorkFlowManager = memberWorkFlowManager;
	}
	public TodoWorkflow getTodoWorkflow() {
		return todoWorkflow;
	}
	public void setTodoWorkflow(TodoWorkflow todoWorkflow) {
		this.todoWorkflow = todoWorkflow;
	}
	public ExamineRecord getExamineRecord() {
		return examineRecord;
	}
	public void setExamineRecord(ExamineRecord examineRecord) {
		this.examineRecord = examineRecord;
	}
	public MemberExaminView getMemberExaminView() {
		return memberExaminView;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
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
}
