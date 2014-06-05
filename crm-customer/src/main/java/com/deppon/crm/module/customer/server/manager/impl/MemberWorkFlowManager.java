package com.deppon.crm.module.customer.server.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.common.server.workflow.WorkflowManager;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberWorkFlowManager;
import com.deppon.crm.module.customer.server.utils.Assert;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.deppon.crm.module.customer.shared.exception.WorkFlowException;
import com.deppon.crm.module.customer.shared.exception.WorkFlowExceptionType;
/**
 * 
 * <p>
 * Description:客户工作流管理<br />
 * </p>
 * @title MemberWorkFlowManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 106138
 * @version 0.1 2014年4月16日
 */
public class MemberWorkFlowManager implements IMemberWorkFlowManager{
	//修改会员服务层
	private IAlterMemberManager alterMemberManager;

	public IAlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public WorkflowManager getWorkflowManage() {
		return workflowManage;
	}

	private WorkflowManager workflowManage ;
	
	public void setWorkflowManage(WorkflowManager workflowManage) {
		this.workflowManage = workflowManage;
	}
	/**
	 * 
	 * <p>
	 * 启动一个会员修改工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-11
	 * @param dataTypes
	 * @return
	 * String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long createNewModifyMemberWorkFlow(Set<String> dataTypes,String memberId,List<ApproveData> appDatas){
		if(checkDataTypes(dataTypes)){
			Map input = new HashMap();
			input.put("dataTypes", dataTypes);
			input.put("memberId", memberId);
			input.put("appData", appDatas);
			String userId =	ContextUtil.getCreateOrModifyUserId();
			long workId = workflowManage.initWorkflow(userId, Constant.ModifyMemberWorkflowName, Constant.MemberModifyWK_StartAction,input);
			if(workId == -1) {
				throw new WorkFlowException(WorkFlowExceptionType.ErrorCreateWorkFlow);
			}
			alterMemberManager.updateMemberLastWorkFlowId(memberId, String.valueOf(workId));
			return workId;
		}
		return -1;
	}

	/**
	 * 
	 * <p>
	 * Description:检测数据类型<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param dataTypes
	 * @return boolean
	 * 如果数据类型不为Constant.AccountDateType 和 Constant.BaseDateType,
	 * 则跑出 数据类型异常
	 */
	private boolean checkDataTypes(Set<String> dataTypes) {
		//检查dataTypes是否为空集合
		Assert.notEmpty(dataTypes, "dataTypes must has values");
		
		//检查dataTypes里面的值是否是合格数据
		for (String dateType : dataTypes) {
			if(Constant.AccountDateType.equals(dateType) || Constant.BaseDateType.equals(dateType)){
				continue;
			}else{
				throw new WorkFlowException(WorkFlowExceptionType.DataTypeError,new Object[]{dateType});
			}
		}
		return true;
	}


	/**
	 * 
	 * <p>
	 * Description:根据审批结果更新数据<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-12
	 * void
	 */
	@Override
	public void commitModifyMemberExamin(ExamineRecord record) {
		Assert.notNull(record, "record must not null !");
		
		//进行审批操作。
		long workflowId = record.getWorkFlowId();
		boolean result = record.getResult();
		//得到调用者
		String userId = ContextUtil.getCurrentUserEmpId();
		
		//得到action Id。
		int actionId = getDisPoseActionId(userId,workflowId,result);
		
		commitExamin(record, actionId);
		
	}

  // 检测是否有审批权限
	private boolean checkAuthority(String userId, int actionId,long workflowId) {
		int[] actions = workflowManage.getAvailableActions(userId, workflowId);
		for (int action : actions) {
			if(action == actionId){
				return true;
			}
		}
		throw new WorkFlowException(WorkFlowExceptionType.NoAuthor);
	}

    /***
     * 
     * <p>
     * Description:获取审批工作流的功能类型<br />
     * </p>
     * @author 王明明
     * @version 0.1 2013-2-2
     * @param userId
     * @param workflowId
     * @param result
     * @return
     * int
     */
	private int getDisPoseActionId(String userId,long workflowId, boolean result) {
		int[] currentSteps = workflowManage.getCurrentSteps(userId, workflowId);
		
		if(currentSteps.length == 0){
			throw new WorkFlowException(WorkFlowExceptionType.WorkFlowEnd);
		}
		
		if(currentSteps.length > 1){
			throw new WorkFlowException(WorkFlowExceptionType.WorkFlowHasNoSplit);
		}
		
		int currentStep = currentSteps[0];
		
		if(currentStep == Constant.BaseDataStep){
			if(result){
				return Constant.MemberModifyWK_BasePass;
			}else{
				return Constant.MemberModifyWK_BaseNoPass;
			}
		}else if(currentStep == Constant.AccountDataStep){
			if(result){
				return Constant.MemberModifyWK_AccountPass;
			}else{
				return Constant.MemberModifyWK_AccountNoPass;
			}
		}
		return -1;
	}
	/**
	 * 
	 * <p>
	 * 创建特殊会员工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @return
	 * long
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long createSepCreateMemberWorkFlow(String memberId) {
		Map input = new HashMap();
		input.put("memberId", memberId);
		String userId =	ContextUtil.getCurrentUserEmpId();
		long workId = workflowManage.initWorkflow(userId, Constant.SepCreateMemberWorkflowName, Constant.SepCreateWK_StartAction,input);
		if(workId == -1) {
			throw new WorkFlowException(WorkFlowExceptionType.ErrorCreateWorkFlow);
		}
		alterMemberManager.updateMemberLastWorkFlowId(memberId, String.valueOf(workId));
		return workId;
	}

	/**
	 * 
	 * <p>
	 * 特殊会员创建代办<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param record
	 * void
	 */
	@Override
	public void commitSepCreateMemberExamin(ExamineRecord record) {
		Assert.notNull(record, "record must not null !");
		
		//得到action Id。
		int actionId = getSepCreateMemberAction(record.getResult());
		commitExamin(record, actionId);
	}

	//审批处理操作
	private void commitExamin(ExamineRecord record,int actionId){
		Assert.notNull(record, "record must not null !!");
		
		//进行审批操作。
		long workflowId = record.getWorkFlowId();
		//得到调用者
		String userId = ContextUtil.getCurrentUserEmpId();
		
		//检查是否有权限进行审批.
		boolean hasAuth = checkAuthority(userId,actionId,workflowId);
		if(hasAuth){
			//构建参数
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("opinion",record.getOpinion());
			
			workflowManage.doAction(userId, workflowId, actionId, map);
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:获取特殊会员创建审批结果<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param result
	 * @return
	 * int
	 */
	private int getSepCreateMemberAction(boolean result) {
		if(result){
			return Constant.CreateSepMemberWK_Pass;
		}else{
			return Constant.CreateSepMemberWK_NoPass;
		}
	}
	/**
	 * 
	 * <p>
	 * 创建联系人变更工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param string 
	 * @return
	 * long
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long createContactVaryWorkFlow(String appId,String memberId,String fromDeptId,
			String toDeptId) {
		Map input = new HashMap();
		input.put("appId", appId);
		input.put("fromDeptId", fromDeptId);
		input.put("toDeptId", toDeptId);
		String userId =	ContextUtil.getCurrentUserEmpId();
		long workId = workflowManage.initWorkflow(userId, Constant.ContactVaryWorkFlowName, Constant.ContactVaryWK_StartAction,input);
		if(workId == -1) {
			throw new WorkFlowException(WorkFlowExceptionType.ErrorCreateWorkFlow);
		}
		alterMemberManager.updateMemberLastWorkFlowId(memberId, String.valueOf(workId));
		return workId;
	}
	/**
	 * 
	 * <p>
	 * 联系人变更审批提交<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-4-14
	 * @param record
	 * void
	 */
	@Override
	public void commitContactVaryExamin(ExamineRecord record) {
		Assert.notNull(record, "record must not null !");
		
		//得到action Id。
		int actionId = getContactVaryAction(record.getResult());
		commitExamin(record, actionId);
	}

	/**
	 * 
	 * <p>
	 * Description:获取联系人变更结果类型<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param result
	 * @return
	 * int
	 */
	private int getContactVaryAction(Boolean result) {
		if(result){
			return Constant.ContactVaryWK_Pass;
		}else{
			return Constant.ContactVaryWK_NoPass;
		}
	}
	/**
	 * 
	 * <p>
	 * 创建一个会员归属部门变更工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-6
	 * @param appId
	 * @param formDeptId
	 * @return
	 * long
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long createChangeMemberDeptWorkFlow(String memberId, String formDeptId) {
		Map input = new HashMap();
		input.put("appId", memberId);
		input.put("formDeptId", formDeptId);
		String userId =	ContextUtil.getCurrentUserEmpId();
		long workId = workflowManage.initWorkflow(userId, Constant.ChangeMemberDeptWorkFlowName, Constant.ChangeMemberDeptWK_StartAction,input);
		if(workId == -1) {
			throw new WorkFlowException(WorkFlowExceptionType.ErrorCreateWorkFlow);
		}
		alterMemberManager.updateMemberLastWorkFlowId(memberId, String.valueOf(workId));
		return workId;
	}

	/**
	 * 
	 * <p>
	 * 会员归属部门变更提交<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-6
	 * @param record
	 * void
	 */
	@Override
	public void commitChangeMemberDeptExamin(ExamineRecord record) {
		Assert.notNull(record, "record must not null !");	
		
		//得到action Id。	
		int actionId = getChangeMemberDeptAction(record.getResult());
		commitExamin(record, actionId);
	}

	/**
	 * 
	 * <p>
	 * Description:获取审批工作流状态 成功or失败<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param result
	 * @return
	 * int
	 */
	private int getChangeMemberDeptAction(Boolean result) {
		if(result){
			return Constant.ChangeMemberDeptWK_ExamineSuccess;
		}else{
			return Constant.ChangeMemberDeptWK_ExamineFail;
		}
	}
	/**
	 * 
	 * <p>
	 * 启动一个合同新增--月发月送增值折扣 --工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-3
	 * @param contractId
	 * @return
	 * long
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long createContractAddWorkFLow(String contractId) {
		Map input = new HashMap();
		input.put("appId", contractId);
		String userId =	ContextUtil.getCurrentUserEmpId();
		long workId = workflowManage.initWorkflow(userId, Constant.ContractAddWK_Name, Constant.ContractAddWK_StartAction,input);
		if(workId == -1) {
			throw new WorkFlowException(WorkFlowExceptionType.ErrorCreateWorkFlow);
		}
		return workId;
	}
	/**
	 * 
	 * <p>
	 * 审批合同新增--月发月送增值折扣 --工作流<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-8-3
	 * @param record
	 * void
	 */
	@Override
	public void commitContractAddExamin(ExamineRecord record) {
		Assert.notNull(record, "record must not null !");	
		//得到action Id。	
		int actionId = getContractAddAction(record.getResult());
		commitExamin(record, actionId);
	}

	/**
	 * 
	 * <p>
	 * Description:获取月发月送折扣审批类型  同意or不同意<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param result
	 * @return
	 * int
	 */
	private int getContractAddAction(Boolean result) {
		if(result){
			return Constant.ContractAddWK_Pass;
		}else{
			return Constant.ContractAddWK_NoPass;
		}
	}
}
