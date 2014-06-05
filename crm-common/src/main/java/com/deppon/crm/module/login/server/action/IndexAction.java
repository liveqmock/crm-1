package com.deppon.crm.module.login.server.action;

import org.apache.struts2.ServletActionContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ICheckHardwareManager;
import com.deppon.crm.module.common.server.manager.impl.LadingstationDepartmentManager;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.CheckResultInfo;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.components.security.SecurityNonCheckRequired;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 主页面
 * 
 * @author Administrator
 * 
 */
public class IndexAction extends AbstractAction {

	private static final long serialVersionUID = 4545162877027512021L;

	// 当前用户
	private String currentUserName;

	// 当前用户登录名
	private String currentUserLoginName;

	// 当前用户所属部门
	private String currentUserDeptName;

	// 加密TOKEN
	private String requestToken = null;

	// 判断是否加载香港部门标识 1 表示加载
	private String isHongKong = null;

	//
	private LadingstationDepartmentManager ladingstationDepartmentManager;

	public void setLadingstationDepartmentManager(
			LadingstationDepartmentManager ladingstationDepartmentManager) {
		this.ladingstationDepartmentManager = ladingstationDepartmentManager;
	}

	public String getIsHongKong() {
		return isHongKong;
	}

	public void setIsHongKong(String isHongKong) {
		this.isHongKong = isHongKong;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	// 检验校验错误信息输出
	private String errorHtml = null;

	public String getErrorHtml() {
		return errorHtml;
	}

	// 硬件信息Token校验Manager
	private ICheckHardwareManager iCheckHardwareManager = null;

	public void setiCheckHardwareManager(
			ICheckHardwareManager iCheckHardwareManager) {
		this.iCheckHardwareManager = iCheckHardwareManager;
	}

	// 封装用户信息
	private UserInfo user;

	public UserInfo getUser() {
		return user;
	}

	/**
	 * 主页面
	 */
	@SecurityNonCheckRequired
	public String login() throws Exception {
		String uri = ServletActionContext.getRequest().getRequestURI();
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		String localIp = ServletActionContext.getRequest().getLocalAddr();
		/**
		 * 开启登陆器验证功能
		 */
		if ("127.0.0.1".equals(ip)) {
			// 本地登陆时，不进行校验
			return "login";
		} else {
			IUser user = UserContext.getCurrentUser();
			if (user == null) {
				CheckResultInfo result = iCheckHardwareManager
						.checkHardWareToken(requestToken);
				if (result.isResult()) {
					return "login";
				} else {
					errorHtml = result.getMessage();
					return ERROR;
				}
			}
			return super.execute();
		}
//		IUser user = UserContext.getCurrentUser();
//		if (user == null) {
//			return "login";
//		}else{
//			return super.execute();
//		}
	}

	/**
	 * 主页面
	 */
	public String showIndex() throws Exception {
		IUser user = UserContext.getCurrentUser();
		if (user == null) {
			return "login";
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 主页面用户信息栏
	 * 
	 * @return
	 */
	public String bottom() {
		IUser user = UserContext.getCurrentUser();
		if (user == null) {
			return "login";
		}
		return SUCCESS;
	}

	/**
	 * 主页面用户信息栏
	 * 
	 * @return
	 */
	public String bottomMessage() {
		IUser user = UserContext.getCurrentUser();
		currentUserLoginName = ((User) user).getLoginName();
		currentUserDeptName = ((User) user).getEmpCode().getDeptId()
				.getDeptName();
		currentUserName = ((User) user).getEmpCode().getEmpName();
		return SUCCESS;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public String queryUserInfo() {
		IUser userContext = UserContext.getCurrentUser();
		user = new UserInfo();
		user.setEmpId(((User) userContext).getEmpCode().getId());// 职员ID
		user.setDeptCode(((User) userContext).getEmpCode().getDeptId()
				.getDeptCode());// 部门编号
		user.setDeptName(((User) userContext).getEmpCode().getDeptId()
				.getDeptName());// 部门名称
		user.setEmpName(((User) userContext).getEmpCode().getEmpName());// 用户名称
		user.setEmpCode(((User) userContext).getEmpCode().getEmpCode());// 用户编号
		user.setLoginName(((User) userContext).getLoginName());// 登录名
		user.setUserId(userContext.getId());// 用户Id
		user.setDeptId(((User) userContext).getEmpCode().getDeptId().getId());// 部门Id
		user.setStandardCode(((User) userContext).getEmpCode().getDeptId()
				.getStandardCode());// 部门标杆编码
		user.setRoleids(((User) userContext).getRoleids());// 用户所拥有的角色信息ID集合
		user.setDeptids(((User) userContext).getDeptids());// 用户所拥有的部门信息ID集合
		user.setLastLogin(((User) userContext).getLastLogin());// 用户最后登录时间
		user.setPosition(((User) userContext).getEmpCode().getPosition());
		if (Constant.ISHONGKONG_STRING.equals(isHongKong) && null != isHongKong) {
			user.setDeptCityLocation(ladingstationDepartmentManager
					.getDeptCityLocation(((User) userContext).getEmpCode()
							.getDeptId().getId()));
		}
		return SUCCESS;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public String getCurrentUserLoginName() {
		return currentUserLoginName;
	}

	public String getCurrentUserDeptName() {
		return currentUserDeptName;
	}

}
