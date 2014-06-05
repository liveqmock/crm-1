package com.deppon.crm.module.recompense.server.manager.workflow.function;

import java.util.Map;

import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;

/**
 * 
 * <p>
 * Description:下一步<br />
 * </p>
 * 
 * @title FindNextOwnerFunction.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.function
 * @author roy
 * @version 0.1 2013-1-21
 */

public class FindNextOwnerFunction implements FunctionProvider {

	public void execute(Map transientVars, Map args, PropertySet ps) {
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		String deptId = app.getReportDept();
		String areaId = app.getDeptId();

		String nextRole = (String) args.get("nextRole");
		// 非空校验
		if (nextRole == null || nextRole.equals("")) {
			return;
		}
		// 拆分字符串
		String[] roleIds = nextRole.split(",");
		// 用foreach遍历
		for (String roleId : roleIds) {
			if (!roleId.equals("")) {
				String nextDept = getDepartment(deptId, areaId, roleId);
				transientVars.put(roleId, nextDept + "_" + roleId);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:获得部门<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-21
	 * @param deptId
	 * @param areaId
	 * @param roleId
	 * @return String
	 */
	private String getDepartment(String deptId, String areaId, String roleId) {
		if (roleId.equals("4")) {
			return areaId;
		} else {
			return deptId;
		}
	}

}
