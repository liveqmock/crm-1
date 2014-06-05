package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:初始化审批角色<br />
 * </p>
 * @title InitStepUser.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class InitStepUser implements FunctionProvider{
	/**
	 * 会员manager
	 */
	private IMemberManager memberManager;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberManager
	 * void
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	
	/**
	 * 
	 * <p>
	 * Description:初始化审批角色<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	@SuppressWarnings("rawtypes")
	/*
	 * 	baseDateOwner 基础数据修改角色
	 * 	accountDateOwner 账号修改数据
	 */
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		String memberId = ps.getString("appId");
		boolean isContract = memberManager.isContractMember(memberId);
		
		//设置账号审批人为本部门经理
		ps.setString("accountDateOwner", ps.getString("bizManager"));
		if(isContract){
			//是合同客户的话 基础数据审批人为 客户关系部
			ps.setString("baseDateOwner", ps.getString("customerManager"));
		}else{
			//不是合同客户的话 基础数据审批人为  事业部
		}
	}

}
