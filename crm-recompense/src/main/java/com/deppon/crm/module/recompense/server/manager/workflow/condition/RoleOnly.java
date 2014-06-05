package com.deppon.crm.module.recompense.server.manager.workflow.condition;

import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.foss.framework.server.context.UserContext;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;

/**
 * 
 * <p>
 * Description:权限<br />
 * </p>
 * 
 * @title RoleOnly.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.condition
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RoleOnly implements Condition {
	/**
	 * 
	 * <p>
	 * Description:passesCondition<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 * 
	 */
	public boolean passesCondition(Map transientVars, Map args, PropertySet ps) {
		//从args获取role
		String role = (String) args.get("role");
		//从transientVars获取用户对象
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		if (user == null) {
			 user = (User) UserContext.getCurrentUser();
		}
		Set<String> roleIds = user.getRoleids();
		//是否为空
		if (role != null) {
			for (String roleId : roleIds) {
				if (roleId.equals(role)) {
					return true;
				}
			}
		}
		return false;
	}

}
