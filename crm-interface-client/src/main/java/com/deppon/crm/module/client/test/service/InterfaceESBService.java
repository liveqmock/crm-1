package com.deppon.crm.module.client.test.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.deppon.foss.framework.entity.IRole;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.interfaces.esb.CrmBusinessException;
import com.deppon.interfaces.esb.IESBWorkFlowResultService;
import com.deppon.interfaces.esb.WorkflowApplyResultInfo;

public class InterfaceESBService {
	@Resource
	IESBWorkFlowResultService eSBWorkFlowResultService;

	public void returnContractApplyResult(String workflowId) {
		IUser user = new IUser(){

			@Override
			public String getId() {
				return "";
			}

			@Override
			public void setId(String id) {
				
			}

			@Override
			public List<IRole> getRoles() {
				return null;
			}

			@Override
			public void setRoles(List<IRole> roles) {
				
			}

			@Override
			public Set<String> getRoleids() {
				return null;
			}

			@Override
			public void setRoleids(Set<String> roleids) {
				
			}

			@Override
			public Set<String> queryAccessUris() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setUserName(String userName) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getUserName() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		UserContext.setCurrentUser(user);
		
		
		WorkflowApplyResultInfo info = new WorkflowApplyResultInfo();
		info.setWorkflowNumber(workflowId);
		info.setExmineResult(true);
		info.setExminePerson("075586");
		info.setExamineDate(new Date());
		try {
			eSBWorkFlowResultService.returnContractApplyResult(info);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	public void returnNormalRecompenseResult(String workflowId) {
		WorkflowApplyResultInfo info = new WorkflowApplyResultInfo();
		info.setWorkflowNumber(workflowId);
		info.setExmineResult(true);
		info.setExminePerson("075586");
		info.setExamineDate(new Date());
		try {
			eSBWorkFlowResultService.returnNormalRecompenseResult(info);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	public void returnMuchRecompenseResult(String workflowId) {
		WorkflowApplyResultInfo info = new WorkflowApplyResultInfo();
		info.setWorkflowNumber(workflowId);
		info.setExmineResult(true);
		info.setExminePerson("075586");
		info.setExamineDate(new Date());
		try {
			eSBWorkFlowResultService.returnMuchRecompenseResult(info);
		} catch (CrmBusinessException e) {
			e.printStackTrace();
		}
	}

	public IESBWorkFlowResultService geteSBWorkFlowResultService() {
		return eSBWorkFlowResultService;
	}

	public void seteSBWorkFlowResultService(
			IESBWorkFlowResultService eSBWorkFlowResultService) {
		this.eSBWorkFlowResultService = eSBWorkFlowResultService;
	}

}
