package com.deppon.crm.module.customer.workflow;

import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.WorkFlowUtil;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:校验是否有权限执行<br />
 * </p>
 * @title RoleOnlyCondition.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class RoleOnlyCondition implements Condition {
	
	/**
	 * 
	 * <p>
	 * Description:校验是否有权限执行<br />
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
	@Override
	public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//获得当前登录部门
		String dept = ContextUtil.getCurrentUserDeptId();
		//获得当前登录用户role
		Set<String> roles = ContextUtil.getCurrentUserRoleIds();
		for (String role : roles) {
			String owner = WorkFlowUtil.getOwner(transientVars, args);//工作流设置的下一步执行角色
			String[] os = owner.split("_");
			if (os != null && os.length == 2){
				String qulifiedDept = os[0];
				String qulifiedRole = os[1];
				if(( dept.equals(qulifiedDept) ) 
					&& role.equals(qulifiedRole)){
					return true;
				}
			}
		}
		return false;
	}
	
	
}
