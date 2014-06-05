package com.deppon.crm.module.recompense.server.manager.workflow.condition;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;

/**
 * 
 * @ClassName: RoleDepartmentOnly
 * @Description: 用户权限校验
 * @author
 * @date 2012-4-21
 * 
 */
public class RoleDepartmentOnly implements Condition {
	// 理赔service
	private RecompenseService recompenseService;

	/**
	 * 
	 * <p>
	 * Description:getRecompenseService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return RecompenseService
	 */
	public RecompenseService getRecompenseService() {
		return recompenseService;
	}

	/**
	 * 
	 * <p>
	 * Description:setRecompenseService<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseService
	 *            void
	 */
	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

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
		// 角色验证
		RoleOnly roleOnly = new RoleOnly();
		if (!roleOnly.passesCondition(transientVars, args, ps)) {
			return false;
		}
		// 获取验证的用户和理赔对象
		User user = (User) transientVars.get(Constants.RECOMPENSE_CURRENT_USER);
		List<String> deptIds = (List<String>) transientVars
				.get(Constants.RECOMPENSE_ADMIN_DEPT_LIST);
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		// 判断理赔对象是否存在
		if (app == null || app.getReportDept() == null
				|| app.getDeptId() == null) {
			String appId = ps.getString(Constants.RECOMPENSE_APPLICATION_ID);
			app = recompenseService.getRecompenseApplicationById(appId);
		}
		String deptId = app.getReportDept();
		String areaId = app.getDeptId();
		// 收银员所在部门
		String cashierDeptId = app.getCashierDept();
		// 进行金额确认的部门
		String expressDeptId = app.getConfirmAmountDept();
		// 根据用户数据和理赔数据进行匹配,是否合理
		String status = app.getStatus();
		return isDeptMatch(user, app.getId(), deptId, areaId, deptIds, status,
				cashierDeptId, expressDeptId);
	}

	/**
	 * 
	 * @Title: isDeptMatch
	 * @Description: 用户的数据权限部门是否与业务数据匹配
	 * @param @param user
	 * @param @param appId
	 * @param @param deptId
	 * @param @param areaId
	 * @param @param deptIds
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	private boolean isDeptMatch(User user, String appId, String deptId,
			String areaId, List<String> deptIds, String status,
			String cashierDeptId, String expressDeptId) {
		// 获得角色id
		Set<String> roleIds = user.getRoleids();
		if (roleIds.contains("4")) {
			if (deptIds == null || deptIds.size() == 0) {
				deptIds = recompenseService.getDeptIdsByUserId(user.getId());
			}
			for (String item : deptIds) {
				if (item.equals(areaId)) {
					return true;
				}
			}
			return false;
			// 如果是初步处理的话金额确认由金额确认部门确认
		} else if (roleIds.contains("3")
				&& status.equals(Constants.STATUS_HANDLED)) {
			return expressDeptId.equals(user.getEmpCode().getDeptId().getId());
			// 如果是收银员的话由收银员所在部门
		} else if (roleIds.contains("5")) {
			return cashierDeptId.equals(user.getEmpCode().getDeptId().getId());
		}

		else {
			return deptId.equals(user.getEmpCode().getDeptId().getId());
		}
	}
}
