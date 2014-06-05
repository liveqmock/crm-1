package com.deppon.crm.module.authorization.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.shared.domain.Role;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IAbCityManager;
import com.deppon.crm.module.common.shared.domain.AbCity;
import com.deppon.crm.module.common.shared.domain.AbCitySearchCondition;
import com.deppon.crm.module.common.shared.domain.TreeNode;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 授权管理WEB接入层
 * suyujun
 * 
 */
public class AuthorizeAction extends AbstractAction {

	private static final long serialVersionUID = -4992864527052506934L;
	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;

	//修改：增加超级管理的工号定义 修改人：ztjie 修改时间：2012-04-17
	private static final String CURRENT_USER_ID = "sysadmin";
	private static final String CURRENT_USER_172 = "000000";

	// 注入authorizeService
	private IAuthorizeService authorizeService;

	// 注入userService
	private IDepartmentService departmentService;

	// 用户ID
	private String userId;

	// 提示信息
	private String message;

	// 角色对象列表
	private List<Role> roleList;

	// 上传的父节点id，也对应部门树的ID(ID)
	private String node;
	// 当前登录用户所属部门集合
	private List<Map<String,String>> currentUserDeptList = new ArrayList<Map<String,String>>();

	// 节点对象数组
	@SuppressWarnings("rawtypes")
	private List<TreeNode> nodes;

	// 已选择部门对象列表
	private List<Department> chooesDepts;
	//选择删除的部门对象列表
	private List<Department> chooesRemoveDepts;

	// 已选择角色对象列表
	private List<String> chooesRoles;
	//大小城市Manager
	private IAbCityManager cityManager=null;
	//大小城市集合
	private List<AbCity>  cityList = null;
	private AbCitySearchCondition citySearchCondition = new AbCitySearchCondition();
	//分页信息
	//private Integer start;
	//private Integer limit;
	private Long totalCount;
	//是否子节点被选
	private Boolean childChecked;
	//是否修改了部门树
	private Boolean updateDept;
	/**
	 * 保存用户授权信息
	 */
	@JSON
	public String saveAuthorize() {
		List<String> departmentIds = new ArrayList<String>();
		List<String> departmentIdsRemove = new ArrayList<String>();
		createDeptmentIds(departmentIds,departmentIdsRemove);
		authorizeService.save(userId, departmentIds,departmentIdsRemove, chooesRoles);
		message = messageBundle.getMessage(getLocale(),
				"i18n.authorization.saveSuccess");
		return SUCCESS;
	}

	private void createDeptmentIds(List<String> departmentIds,List<String> departmentIdsRemove) {
		for (Department dept : chooesDepts) {
			if (dept.getStatus() != null && dept.getStatus()) {
				List<Department> depts = departmentService
						.queryAllChildDeptByDeptId(dept.getId());
				for (Department d : depts) {
					departmentIds.add(d.getId());
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
					departmentIdsRemove.add(d.getId());
				}
			} else {
				departmentIdsRemove.add(dept.getId());
			}
		}
	}

	/**
	 * 得到用户待授权角色
	 * 
	 * @return
	 */
	@JSON
	public String findAllChooesRole() {
		//修改人:ztjie
		//修改时间：2012-04-17
		//修改说明：当是超级管理员的时候，可以查询到所有的角色信息，
		//			非超级管理的时候，只能查询到授权到的信息
		//修改前：
		//String currentUserId = UserContext.getCurrentUser().getId();
		//roleList = authorizeService.queryAllChooesRole(userId);
		//修改后:
		//得到当前登录用户
		User currentUser = (User)(UserContext.getCurrentUser());
		//得到当前用户工号
		String currentUserEmpCode = currentUser.getEmpCode().getEmpCode();
		//超级管理员则查询全部待授权角色信息
		if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
			roleList = authorizeService.queryAllChooesRole(null,userId);			
		}else{
			roleList = authorizeService.queryAllChooesRole(currentUser.getId(),userId);
		}
		return SUCCESS;
	}

	/**
	 * 得到用户已授权角色
	 * 
	 * @return
	 */
	@JSON
	public String findAllAuthorizeRole() {
		//修改人:ztjie
		//修改时间：2012-04-17
		//修改说明：当是超级管理员的时候，可以查询到所有已授权的角色信息，
		//			非超级管理的时候，只能查询到已授权到的信息
		//修改前：
		//String currentUserId = UserContext.getCurrentUser().getId();
		//roleList = authorizeService.queryAllAuthorizeRole(userId);
		//修改后:
		//得到当前登录用户
		User currentUser = (User)(UserContext.getCurrentUser());
		//得到当前用户工号
		String currentUserEmpCode = currentUser.getEmpCode().getEmpCode();
		//超级管理员则查询全部已授权角色信息
		if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
			roleList = authorizeService.queryAllAuthorizeRole(null,userId);		
		}else{
			roleList = authorizeService.queryAllAuthorizeRole(currentUser.getId(),userId);
		}
		return SUCCESS;
	}
	/**
	 * 查询大小城市集合
	 * 
	 * @author hpf
	 * @return
	 */
	@JSON
	public String searchCityList() {
		citySearchCondition.setStart(start);
		citySearchCondition.setLimit(limit);
		Map<String,Object> result = cityManager.selectAbCity(this.citySearchCondition);
		this.cityList = (List<AbCity>)result.get("abCityList");
		this.totalCount = Long.valueOf(result.get("abCityCount").toString());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * 获取当前用户可选择的部门集合<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-31
	 * @修改时间 2012-5-10
	 * @修改内容 换id为city城市
	 */
	public String acquireCurrentUserDeptList() {
		for(Department department: authorizeService.getMyAuthDepts()){
			Map<String,String> map = new HashMap<String,String>();
			map.put("deptId", department.getId());
			map.put("deptName", department.getDeptName());
			currentUserDeptList.add(map);
		}
		return SUCCESS;
	}

	/**
	 * 部门选择树
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@JSON
	public String loadDepartmentChooesTree() {
		Department dept = new Department();
		Department parentDept = new Department();
		parentDept.setId(node);
		dept.setParentCode(parentDept);
		List<Department> deptList = departmentService
				.queryDirectChildDepts(dept);
		// List<Department> leafDeptList =
		// departmentService.queryLeafChildDeptByDeptId(node);
		List<String> deptIds = new ArrayList<String>();
		//这个选择的用户被授权的部门权限
		if (userId != null) {
			deptIds = authorizeService.queryAllDepartmentIdByUserId(userId);
		}
		//修改人:ztjie
		//修改时间：2012-04-17
		//修改说明：增加得到当前用户已授权的部门信息
		//修改前：
		//修改后:
		//得到当前登录用户
		User currentUser = (User)(UserContext.getCurrentUser());
		//得到当前用户工号
		String currentUserEmpCode = currentUser.getEmpCode().getEmpCode();
		//得到当前用户可授权的部门信息
		List<String> currentUserAuthedDeptIds = new ArrayList<String>();
		if (currentUser.getId() != null) {
			if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){//超级管理员可以授权所有部门权限，那么这里就不要查询了
				//currentUserAuthedDeptIds = authorizeService.queryChildDepartmentIdByUserId(null,userId,node);
			}else{
				currentUserAuthedDeptIds = authorizeService.queryChildDepartmentIdByUserId(null,currentUser.getId(),node);//查询出当前登陆用户可授权部门
			}
		}
		//修改结束
		nodes = new ArrayList<TreeNode>();
		for (Department deptartment : deptList) {
			TreeNode<Department> treeNode = new TreeNode<Department>();
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
			boolean flag = false;
			treeNode.setChildChecked(false);//默认是子节点不被选中
			if(childChecked){
				if (deptIds != null && deptIds.size() > 0) {
					if (deptIds.contains(deptartment.getId())) {
						flag = true;//表示复选框被选择
					}
					int begin = 0 ;
					int end =0;
					int size = 1000;//每次查询1000条
					while (true) {
						end =(begin+size)>deptIds.size()?deptIds.size():begin+size;
						if(end == 0){
							break;
						}
						//修改人:zhangbin
						//修改时间：2013-07-3 9:15
						List<String> depts = deptIds.subList(begin,end);
						Integer count = authorizeService.checkIsHaveChildNode(deptartment.getId(), depts);
						if(count!=null&&count!=0){
							treeNode.setChildChecked(true);
							break;//跳出for循环
						}
						if(end>=deptIds.size()){
							break;
						}
						begin+=size;
					}
				}
			}
			//修改人:ztjie
			//修改时间：2012-04-17
			//修改说明：当是超级管理员的时候，可以选择所有部门信息，
			//			非超级管理的时候，只能选择已授权到的部门信息
			//修改前：
			//treeNode.setChecked(flag);
			//修改后:
			//超级管理员则选择全部已授权部门信息
			if(CURRENT_USER_ID.equals(currentUserEmpCode)||CURRENT_USER_172.equals(currentUserEmpCode)){
				//设置用户已授权的部门信息
				treeNode.setChecked(flag);
			//非超级管理员则只可选择自己已授权部门信息
			}else{
				//在当前用户已授权的部门信息中设置用户已授权的部门信息
				//不是超级管理员只能授权当前登录用户可授权的部门权限
				//别的部门可以看见，但是不选择
				if (currentUserAuthedDeptIds != null && currentUserAuthedDeptIds.size() > 0) {
					if (currentUserAuthedDeptIds.contains(deptartment.getId())) {
						treeNode.setChecked(flag);
					}
				}
			}
			//修改结束
			treeNode.setEntity(deptartment);
			nodes.add(treeNode);
		}
		return SUCCESS;
	}
	
	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setCityManager(IAbCityManager cityManager) {
		this.cityManager = cityManager;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the node
	 */
	public String getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * @return the currentUserDeptList
	 */
	public List<Map<String, String>> getCurrentUserDeptList() {
		return currentUserDeptList;
	}

	/**
	 * @param currentUserDeptList the currentUserDeptList to set
	 */
	public void setCurrentUserDeptList(List<Map<String, String>> currentUserDeptList) {
		this.currentUserDeptList = currentUserDeptList;
	}

	/**
	 * @return the nodes
	 */
	public List<TreeNode> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the chooesDepts
	 */
	public List<Department> getChooesDepts() {
		return chooesDepts;
	}

	/**
	 * @param chooesDepts the chooesDepts to set
	 */
	public void setChooesDepts(List<Department> chooesDepts) {
		this.chooesDepts = chooesDepts;
	}

	/**
	 * @return the chooesRoles
	 */
	public List<String> getChooesRoles() {
		return chooesRoles;
	}

	/**
	 * @param chooesRoles the chooesRoles to set
	 */
	public void setChooesRoles(List<String> chooesRoles) {
		this.chooesRoles = chooesRoles;
	}

	/**
	 * @return the cityList
	 */
	public List<AbCity> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<AbCity> cityList) {
		this.cityList = cityList;
	}

	/**
	 * @return the citySearchCondition
	 */
	public AbCitySearchCondition getCitySearchCondition() {
		return citySearchCondition;
	}

	/**
	 * @param citySearchCondition the citySearchCondition to set
	 */
	public void setCitySearchCondition(AbCitySearchCondition citySearchCondition) {
		this.citySearchCondition = citySearchCondition;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param messageBundle the messageBundle to set
	 */
	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	/**
	 * @param childChecked the childChecked to set
	 */
	public void setChildChecked(Boolean childChecked) {
		this.childChecked = childChecked;
	}

	/**
	 * @param updateDept the updateDept to set
	 */
	public void setUpdateDept(Boolean updateDept) {
		this.updateDept = updateDept;
	}

	/**
	 * @param chooesRemoveDepts the chooesRemoveDepts to set
	 */
	public void setChooesRemoveDepts(List<Department> chooesRemoveDepts) {
		this.chooesRemoveDepts = chooesRemoveDepts;
	}


	
	
	
	
}
