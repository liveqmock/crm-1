package com.deppon.crm.module.authorization.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.authorization.server.service.IFunctionService;
import com.deppon.crm.module.authorization.server.service.IRoleService;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;


/**
 * 角色管理WEB接入层
 * 
 */
public class RoleAction extends AbstractAction {

	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;
	
	//注入roleService
	private IRoleService roleService;
	
	//注入functionService
	private IFunctionService functionService;
	
	private static final long serialVersionUID = 2500490309411668064L;

	// 分页最大记录数
	private int limit;
	
	// 分页开始记录数
	private int start;
	
	// 总记录数
	private Long totalCount;
	
	//页面上传角色ID
	private String roleId;

	// 提示信息
	private String message;

	//角色对象
	private Role role;

	//角色对象信息集合
	private List<Role> roleList;
	
	//被选中的权限对象集合
	private List<Function> chooesFunctions;
	
	//需要查询角色的用户id
	private String userId;
	
	/**
	 * 查询所有角色对象	
	 * @return
	 */
	@JSON
	public String findAllRole() {
		roleList = roleService.queryAll(role,limit,start);
		totalCount = roleService.count(role);
		return SUCCESS;
	}

	/**
	 * 保存角色对象
	 * @return
	 */
	@JSON
	public String saveRole() {
		//设置新增记录的人员ID
		role.setCreateUser(UserContext.getCurrentUser().getId());
		//设置修改记录的人员ID
		role.setModifyUser(UserContext.getCurrentUser().getId());
		List<String> functionIds = new ArrayList<String>();
		createFunctionIds(functionIds);
		role.setFunctionIds(functionIds);
		roleService.save(role);
		message = messageBundle.getMessage(getLocale(),"i18n.authorization.saveSuccess");
		return SUCCESS;
	}
	
	/**
	 * 更新角色对象
	 * @return
	 */
	@JSON
	public String updateRole() {
		//设置修改记录的人员ID
		role.setModifyUser(UserContext.getCurrentUser().getId());
		List<String> functionIds = new ArrayList<String>();
		createFunctionIds(functionIds);
		role.setFunctionIds(functionIds);
		roleService.update(role);
		message = messageBundle.getMessage(getLocale(),"i18n.authorization.updateSuccess");
		return SUCCESS;
	}

	private void createFunctionIds(List<String> functionIds) {
		for(Function function : chooesFunctions){
			if(function.getValidFlag()!=null&&function.getValidFlag()){
				List<Function> functions = functionService.queryAllChildFunctionById(function.getId());
				for(Function f : functions){
					functionIds.add(f.getId());
				}
			}else{
				functionIds.add(function.getId());				
			}
		}
	}
	
//	/**
//	 * 
//	 * @description 查询当前用户已授权的角色
//	 * @version 1.0
//	 * @author patern
//	 * @update 2011-10-25 上午08:36:26
//	 */
//	public String findAllRoles() {
//			userId = UserContext.getCurrentUser().getId();
//			roleList =  authorizeService.queryAllAuthorizeRole(userId);
//			return SUCCESS;
//	}
	
	/**
	 * 根据ID删除角色对象
	 * @return
	 */
	@JSON
	public String deleteRole() {
		
		roleService.removeById(roleId);
		message = messageBundle.getMessage(getLocale(),"i18n.authorization.deleteSuccess");
		return SUCCESS;
	}	
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleId() {
		return roleId;
	}
	 
	public String getMessage() {
		return message;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setChooesFunctions(List<Function> chooesFunctions) {
		this.chooesFunctions = chooesFunctions;
	}
	
//	public String getOldRoleName() {
//		return oldRoleName;
//	}
//
//	public void setOldRoleName(String oldRoleName) {
//		this.oldRoleName = oldRoleName;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}
}
