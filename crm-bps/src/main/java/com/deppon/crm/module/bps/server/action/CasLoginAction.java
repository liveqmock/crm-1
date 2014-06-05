package com.deppon.crm.module.bps.server.action;

import com.deppon.crm.module.bps.server.util.Constant;
import com.deppon.crm.module.frameworkimpl.server.util.EncryptUtil;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.exception.security.UserNotLoginException;
import com.deppon.foss.framework.server.components.security.SecurityNonCheckRequired;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class CasLoginAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private String busino;
	
	private String claimNo;
	
	private String processInstId;
	
	private String procInstId;
	
	private String processDefName;
	
	private String jumpUrl;
	
	private String workItemId;
	
	public void setBusino(String busino) {
		this.busino = busino;
	}
	

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}


	public String getJumpUrl() {
		return jumpUrl;
	}


	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}


	
	
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	
	

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}


	@SecurityNonCheckRequired
	public String detailWorkflow() {
		IUser user = UserContext.getCurrentUser();
        //用户未登录
        if (user == null) {
            throw new UserNotLoginException();
        }
        processDefName=EncryptUtil.decrypt(processDefName,Constant.WORKFLOW_DESC_KEY);
        if(busino!=null){
        	//获取OA信息地址信息，跳转至工作流详情页面
    		jumpUrl="../bpsworkflow/showDetailWorkflow.action?processInstId="+procInstId+"&processDefName="+processDefName+"&busino="+busino;
        }

		return SUCCESS;
	}
	
	@SecurityNonCheckRequired
	public String approveWorkflow(){
		IUser user = UserContext.getCurrentUser();
        //用户未登录
        if (user == null) {
            throw new UserNotLoginException();
        }
        processDefName=EncryptUtil.decrypt(processDefName,Constant.WORKFLOW_DESC_KEY);
        //获取OA信息地址信息，跳转至工作流审批页面
		jumpUrl="../bpsworkflow/doApproveWorkflow.action?processInstId="+processInstId+"&workItemId="+workItemId+"&processDefName="+processDefName+"&busino="+claimNo;

		return SUCCESS;
	}
	
}
