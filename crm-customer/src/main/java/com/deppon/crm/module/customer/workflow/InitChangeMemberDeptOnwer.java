package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.Constant;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:初始化变更归属部门参与者<br />
 * </p>
 * @title InitChangeMemberDeptOnwer.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class InitChangeMemberDeptOnwer implements FunctionProvider{

	/**
	 * @author 078823
	 * @date 2014-04-11
	 * @description:初始化变更归属部门参与者</br>
	 * 主要用于action请求日志
	 * *
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//部门id
		String formDeptId = (String) transientVars.get("formDeptId");
		//部门经理
		String deptOwner = getDeptOwner(formDeptId);
		transientVars.put("deptOwner", deptOwner);
	}
	
	private String getDeptOwner(String formDeptId) {
		return formDeptId+"_"+Constant.BizManager;
	}

}
