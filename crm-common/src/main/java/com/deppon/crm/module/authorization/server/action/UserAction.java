package com.deppon.crm.module.authorization.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.server.service.IRoleService;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 用户管理WEB接入层
 * 
 * @author patern
 * @date :2011-10-21
 */
public class UserAction extends AbstractAction {

	private static final long serialVersionUID = -7002028275518010396L;
	//修改：增加超级管理的工号定义 修改人：张斌 修改时间：2013-07-16
	private static final String CURRENT_USER_ID = "sysadmin";
	private static final String CURRENT_USER_172 = "000000";

	// 注入departmentService
	private IDepartmentService departmentService;

	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;

	// 注入userService
	private IUserService userService;

	// 注入employeeService
	private IEmployeeService employeeService;

	// 注入roleService
	private IRoleService roleService;

	// 分页最大记录数
	private int limit;

	// 分页开始记录数
	private int start;

	// 总记录数
	private Long totalCount;

	// 用户ID
	private String userId;

	// 提示信息
	private String message;

	// 页面上传用户信息
	private User user;

	// 职员对象
	private Employee empCode;

	// 用户信息集合
	private List<User> userList;

	// 角色对象信息集合
	private List<Role> roleList;

	// 部门信息集合
	private List<Department> deptList;

	// 职员信息集合
	private List<Employee> employeeList;

	// 已选择的角色信息集合
	private List<String> chooesRoles;

	// 已选择的部门信息集合
	private List<Department> chooseDepts;
	//已删除的部门信息集合
	private List<Department> chooesRemoveDepts;

	// 部门ID
	private String deptId;
	
	//部门名
	private String deptName;
	
	//职员工号
	private String employeeCode;
	//是否子节点被选
	private Boolean childChecked;
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * 
	 * @description 重置用户密码
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-24 上午11:53:29
	 */
	public String updatePassword() {
		userService.updatePassword(user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.authorization.updateSuccess");
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查找所有用户/或按部门Id查询
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-21 下午02:27:36
	 */
	public String findAllUser() {
		if (deptId != null && !deptId.equals("")) {
			userList = userService.queryAllByDeptId(deptId, limit, start);
			totalCount = userService.count(deptId);
		} else {
			userList = userService.queryAll(user, limit, start);
			totalCount = userService.count(user);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查找所有员工
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-21 下午02:27:36
	 */
	public String findAllEmployee() {
		employeeList = employeeService.queryAll(empCode, limit, start);
		totalCount = employeeService.count(empCode);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 保存用户
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-21 下午02:27:36
	 */
	public String saveUser() {
		List<String> departmentIds = new ArrayList<String>();
		List<String> departmentIdsRemove = new ArrayList<String>();
		createDeptmentIds(departmentIds,departmentIdsRemove);
		// 设置新增记录的人员ID
		user.setCreateUser(UserContext.getCurrentUser().getId());
		// 设置修改记录的人员ID
		user.setModifyUser(UserContext.getCurrentUser().getId());
		userService.save(user, chooesRoles, departmentIds,departmentIdsRemove);
		message = messageBundle.getMessage(getLocale(),
				"i18n.authorization.addSuccess");
		return SUCCESS;
	}

	
	private void createDeptmentIds(List<String> departmentIds,List<String> departmentIdsRemove) {
		String currentUserId = UserContext.getCurrentUser().getId();
		//当前登录用户可以分配的部门
		List<String> deptIds = authorizeService.queryAllDepartmentIdByUserId(currentUserId);
		for (Department dept : chooseDepts) {
			if (dept.getStatus() != null && dept.getStatus()) {
				List<Department> depts = departmentService
						.queryAllChildDeptByDeptId(dept.getId());
				for (Department d : depts) {
					if(deptIds.contains(d.getId())){
						departmentIds.add(d.getId());
					}
				}
			} else {
				departmentIds.add(dept.getId());
			}
		}
		for (Department dept : chooesRemoveDepts) {
			if (dept.getStatus() != null && dept.getStatus()) {
				List<Department> depts = departmentService
						.queryAllChildDeptByDeptId(dept.getId());
				for (Department d : depts) {
					if(deptIds.contains(d.getId())){
						departmentIdsRemove.add(d.getId());
					}
				}
			} else {
				departmentIdsRemove.add(dept.getId());
			}
		}
	}
	/**
	 * 
	 * @description function 更新用户
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-21 下午02:27:36
	 */
	public String updateUser() {
		List<String> departmentIds = new ArrayList<String>();
		List<String> departmentIdsRemove = new ArrayList<String>();
		createDeptmentIds(departmentIds,departmentIdsRemove);
		// 设置修改记录的人员ID
		user.setModifyUser(UserContext.getCurrentUser().getId());
		userService.update(user, chooesRoles, departmentIds,departmentIdsRemove);
		message = messageBundle.getMessage(getLocale(),
				"i18n.authorization.updateSuccess");
		return SUCCESS;
	}

	/**
	 * 查询当前授权用户所剩余的可分配的角色
	 * 
	 * @return
	 */
	@JSON
	public String findLeftRoles() {
		String currentUserId = UserContext.getCurrentUser().getId();
		roleList = roleService.queryLeftRoles(currentUserId, userId);
		return SUCCESS;
	}

	/**
	 * 
	 * @description 查询当前授权用户给用户已经分配的角色
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-25 上午08:36:26
	 */
	public String findAuthedRoles() {
		String currentUserId = UserContext.getCurrentUser().getId();
		roleList = roleService.queryAuthedRoles(currentUserId, userId);
		return SUCCESS;
	}

	/**
	 * 查询当前授权用户所剩余的可分配的部门
	 * 
	 * @return
	 */
	@JSON
	public String findLeftDepts() {
		String currentUserId = UserContext.getCurrentUser().getId();
		deptList = departmentService.queryLeftDepts(currentUserId, userId,deptName);
		return SUCCESS;
	}
	/**
	 * 查询当前授权用户所剩余的可分配的部门
	 * 
	 * @return
	 */
	@JSON
	public String findLeftDeptsByEmpCode() {
		String currentUserId = UserContext.getCurrentUser().getId();
		deptList = departmentService.queryLeftDeptsByEmpCode(currentUserId,employeeCode,deptName);
		return SUCCESS;
	}
	/**
	 * 
	 * @description 查询当前授权用户给用户已经分配的部门
	 * @version 1.0
	 * @author patern
	 * @update 2011-10-25 上午08:36:26
	 */
	public String findAuthedDepts() {
		String currentUserId = UserContext.getCurrentUser().getId();
		deptList = departmentService.queryAuthedDepts(currentUserId, userId);
		return SUCCESS;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
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

	public Employee getEmpCode() {
		return this.empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}

	public void setChooesRoles(List<String> chooesRoles) {
		this.chooesRoles = chooesRoles;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setChooseDepts(List<Department> chooseDepts) {
		this.chooseDepts = chooseDepts;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	//用户管理中分配部门数据权限，改为树形结构----------------------------------------
	// 注入authorizeService
	private IAuthorizeService authorizeService;
	
	/**
	 * @param authorizeService the authorizeService to set
	 */
	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}
	// 节点对象数组
	@SuppressWarnings("rawtypes")
	private List<TreeNode> nodes;
	
	/**
	 * @return the nodes
	 */
	public List<TreeNode> getNodes() {
		return nodes;
	}
	// 上传的父节点id，也对应部门树的ID(ID)
	private String node;
	
	/**
	 * @param node the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * @param chooesRemoveDepts the chooesRemoveDepts to set
	 */
	public void setChooesRemoveDepts(List<Department> chooesRemoveDepts) {
		this.chooesRemoveDepts = chooesRemoveDepts;
	}

	/**
	 * @param childChecked the childChecked to set
	 */
	public void setChildChecked(Boolean childChecked) {
		this.childChecked = childChecked;
	}
	/**
	 * 部门选择树
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@JSON
	public String loadDepartmentUserChooesTree() {
		//得到当前登录用户
		User currentUser = (User)(UserContext.getCurrentUser());
		Department dept = new Department();
		Department parentDept = new Department();
		parentDept.setId(node);
		dept.setParentCode(parentDept);
		//修改人:张斌
		//修改时间：2013-07-16
		//修改说明：当是超级管理员的时候，可以分配所有的部门数据权限
		//得到当前用户工号
		String currentUserEmpCode = currentUser.getEmpCode().getEmpCode();
		//超级管理员则查询全部待授权角色信息
		//得到当前展开部门节点的下一级子节点
		List<Department> deptList = departmentService.queryDirectChildDepts(dept);	
		List<String> deptIds = new ArrayList<String>();
		//得到当前登录用户所有可分配的部门
		if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
			//超级管理员，不需要去查询出来
		}else{
			deptIds = authorizeService.queryAllDepartmentIdByUserId(currentUser.getId());
		}
		List<String> deptIdsRed = new ArrayList<String>();
		if (userId != null) {
		//得到当前选择用户已经拥有的部门
		    deptIdsRed = userService.getUserDeptIds(userId);
		}
		//得到当前选择的用户，已经分配的。并且当前登录用户可分配的
		List<String> selectUserDeptIds = new ArrayList<String>();
		if (userId != null) {
			if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
				//如果是超级管理员，查询出被选择用户所拥有的所有部门权限
				selectUserDeptIds = authorizeService.getHavedDepartmentId(null,userId,node);
			}else{
				//如果不是超级管理员，查询出被选择用户所拥有的部门权限，与当前登陆用户可分配的部门数据权限的交集
				selectUserDeptIds = authorizeService.getHavedDepartmentId(currentUser.getId(),userId,node);
			}
		}
		
		//修改结束
		nodes = new ArrayList<TreeNode>();
		for (Department deptartment : deptList) {
			if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
				//如果是超级管理员则不需要执行下面的判断
			}else{
				//先判断该部门节点是否要留
				int begin = 0 ;
				int end =0;
				int size = 1000;//每次查询1000条
				boolean isNext = true;
				while (true) {
					end =(begin+size)>deptIds.size()?deptIds.size():begin+size;
					if(end == 0){
						break;
					}
					List<String> depts = deptIds.subList(begin,end);
					//得到当前节点（deptartment）所拥有的可分配的子节点个数
					Integer count = authorizeService.checkIsHaveChildNode(deptartment.getId(), depts);
					//如果不是空，则不跳过，如果是空，这个节点不会出现在现在界面，则跳过
					if(count!=null&&count!=0){
						isNext = false;
					}
					if(end>=deptIds.size()){
						break;
					}
					begin+=size;
				};
				if(isNext){
					continue;
				}
			}
			
			TreeNode<Department> treeNode = new TreeNode<Department>();
			if(childChecked){
				//判断是否有子节点被选则--------------------
				int beginRed = 0 ;
				int endRed =0;
				int sizeRed = 1000;//每次查询1000条
				while (true) {
					endRed =(beginRed+sizeRed)>deptIdsRed.size()?deptIdsRed.size():beginRed+sizeRed;
					if(endRed == 0){
						break;
					}
					List<String> depts = deptIdsRed.subList(beginRed,endRed);
					//得到当前节点（deptartment）所拥有的可分配的子节点个数
					Integer count = authorizeService.checkIsHaveChildNode(deptartment.getId(), depts);
					//如果不是空，则不跳过，如果是空，这个节点不会现在在界面，则跳过
					if(count!=null&&count!=0){
						treeNode.setChildChecked(true);
					}
					if(endRed>=deptIdsRed.size()){
						break;
					}
					beginRed+=sizeRed;
				};
			}
			//---------------------------------------------
			treeNode.setId(deptartment.getId());
			treeNode.setText(deptartment.getDeptName());
			// 判断叶子节点
			if (deptartment.getLeaf() != null) {
				treeNode.setLeaf(deptartment.getLeaf());
			} else {
				treeNode.setLeaf(false);
			}
			if (deptartment.getParentCode() != null) {
				treeNode.setParentId(deptartment.getParentCode().getId());
			} else {
				treeNode.setParentId(null);
			}
			Boolean flag = false;
			if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
				//如果是超级管理员则不需要执行下面的判断
			}else{
				//判断是否有勾选框和是否有勾选框
				if (deptIds != null && deptIds.size() > 0) {
					if (deptIds.contains(deptartment.getId())) {//如果当前节点是当前登录用户可分配的节点，则flag=false(下面还要判断是false?true)
						flag = false;
					}else{//如果当前节点是当前登录用户不可分配的节点，则flag=null
						flag = null;
					}
				}
			}
			//在当前用户已拥有的部门信息中设置是勾选状态
			if (selectUserDeptIds != null && selectUserDeptIds.size() > 0) {
				if (selectUserDeptIds.contains(deptartment.getId())) {
					flag = true;
				}
			}
			treeNode.setChecked(flag);
			//修改结束
			treeNode.setEntity(deptartment);
			nodes.add(treeNode);
		}
		return SUCCESS;
	}
}
