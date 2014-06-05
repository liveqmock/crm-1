/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UserManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-4-19
 */
package com.deppon.crm.module.marketing.server.manager.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.service.ILadingstationDepartmentService;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.server.manager.IUserManager;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title UserManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-4-19
 */

public class UserManager implements IUserManager {
	// 用户Service
	private IUserService userService;
	// 职员Service
	private IEmployeeService employeeService;
	// 部门Service
	private IDepartmentService departmentService;
	//授权部门查询service
	private IAuthorizeService authorizeService;
	//
	private ILadingstationDepartmentService ladingstationDepartmentService;

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-5-30
	 */
	public ILadingstationDepartmentService getLadingstationDepartmentService() {
		return ladingstationDepartmentService;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-5-30
	 * @param ladingstationDepartmentService the ladingstationDepartmentService to set
	 */
	public void setLadingstationDepartmentService(
			ILadingstationDepartmentService ladingstationDepartmentService) {
		this.ladingstationDepartmentService = ladingstationDepartmentService;
	}
	/**
	 * @return authorizeService : return the property authorizeService.
	 */
	public IAuthorizeService getAuthorizeService() {
		return authorizeService;
	}

	/**
	 * @param authorizeService : set the property authorizeService.
	 */
	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	/**
	 * @return userService : return the property userService.
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @return employeeService : return the property employeeService.
	 */
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @return departmentService : return the property departmentService.
	 */
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param employeeService : set the property employeeService.
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @param departmentService : set the property departmentService.
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#getUserDeptIds(java.lang.String)
	 */
	@Override
	public List<String> getUserDeptIds(String userId) {
		//查询用户部门ID
		return userService.getUserDeptIds(userId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#queryManagerUserIdByDeptId(java.lang.String)
	 */
	@Override
	public String queryManagerUserIdByDeptId(String deptId) {
		//查询经理ID
		return userService.queryManagerEmployeeIdByDeptId(deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#queryUsersByDeptIds(java.util.List)
	 */
	@Override
	public List<User> queryUsersByDeptIds(List<String> deptIds) {
		//查询用户
		return userService.queryUsersByDeptIds(deptIds);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#getAllEmployeesByDeptId(com.deppon.crm.module.organization.shared.domain.Department)
	 */
	@Override
	public List<Employee> getAllEmployeesByDeptId(String[] deptIds) {
		// 查询部门下所有职员
		// 没有部门的情况下，默认为所有授权部门
		List<Employee> list = new ArrayList<Employee>() ;
		//部门ID
		for (String deptId : deptIds){
			//部门对象
			Department dept = new Department();
			//设置部门ＩＤ
			dept.setId(deptId);
			//查询员工信息
			List<Employee> emps = employeeService.getAllEmployeesByDeptId(dept);
			//添加到员工列表
			list.addAll(emps);
		}
		//返回结果
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#getAllEmployeesByDeptId(com.deppon.crm.module.organization.shared.domain.Department)
	 */
	@Override
	public List<Employee> getEmployeesByDeptId(String deptIds) {
		// 查询部门下所有职员
		Department dept = new Department();
		//设置id
		dept.setId(deptIds);
		//查询员工
		return employeeService.getAllEmployeesByDeptId(dept);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#getAllAuthedDepts(com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	public List<Department> getAllAuthedDepts(User user) {
		//结果定义
		List<Department> list = new ArrayList<Department>();
		if (user == null){
			//返回空值
			return list;
		}
		//权限部门
		Set<String> depIds = user.getDeptids();
		//循环授权部门
		for (String id:depIds){
			//部门查询
			Department dep = departmentService.queryById(id);
			//添加到结果
			list.add(dep);
		}
		//返回结果
		return list;
	}
	/**
	 * 通过部门名称模糊分页查询部门信息
	 */
	@Override
	public Map<String, Object> queryDeptListByDeptName(User user, String deptName,
			int start, int limit) {
		boolean isBusiDept = false;
		//结果定义
		Map<String,Object> map = new HashMap<String, Object>();
		//当前用户ID
		String userId = user.getId();
		// 岗位名称
		String position = user.getEmpCode().getPosition();
		//标杆编码
		List<Department> executeDeplist = new ArrayList<Department>();
		int totalcount = 0;
		String deptStandardCode = user.getEmpCode().getDeptId().getStandardCode();
		BussinessDept dept = ladingstationDepartmentService.getBusDeptByDeptId(user.getEmpCode().getDeptId().getId());
		//判断是否营业部
        if (null != dept && dept.getIfBussinessDept() != null && dept.getIfBussinessDept()) {
        	isBusiDept = true;
        }
  
		//快递岗位
		if(StringUtils.isNotEmpty(MarketingConstance.EXPRESS_POSITIONS.get(position)) ||
				MarketingConstance.EXPRESS_MARKETING_GROUP_STANDARDCODE.equals(deptStandardCode)){
			/**
			 * @author 043260
			 * @description 新增点部经理/分部高级经理/快递大区总经理/快递市场营销组总监
			 * 				查询映射的营业部
			 * 使用标杆编码实现
			 */
			if(isBusiDept){
				//是营业部，按数据权限查询
				//模糊查询部门列表
				executeDeplist = authorizeService.getAllDepartmentByUserIdAndLikeDeptName(userId, deptName, start, limit);
				//统计总数
				totalcount = authorizeService.countAllDepartmentByUserIdAndLikeDeptName(userId, deptName);
			}else{
				executeDeplist = authorizeService.getAllDeptMappedExpress(userId, deptName,deptStandardCode, start, limit);
				totalcount = authorizeService.countAllDeptMappedExpress(userId, deptName,deptStandardCode);				
			}			
		}else{
			//模糊查询部门列表
			executeDeplist = authorizeService.getAllDepartmentByUserIdAndLikeDeptName(userId, deptName, start, limit);
			//统计总数
			totalcount = authorizeService.countAllDepartmentByUserIdAndLikeDeptName(userId, deptName);
		}
		//封装结果
		map.put("executeDeplist", executeDeplist);
		//封装结果
		map.put("totalcount", totalcount);
		//返回结果
		return map;			
	}
	
	/** 
	 * 查询当前用户的默认部门
	 * @see com.deppon.crm.module.marketing.server.manager.IUserManager#getDefaultDep(com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	public Department getDefaultDep(User user) {
		//部门ID
		String id = user.getEmpCode().getDeptId().getId();
		//根据部门ID查询部门对象
		return departmentService.queryById(id);
	}

	@Override
	public Map<String, Object> queryDeptListByDeptNameForMonitor(User user,
			String deptName, int start, int limit) {
		Map<String,Object> result = new HashMap<String, Object>();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		String userId = user.getId();
		List<Department> executeDeplist = new ArrayList<Department>();
		int totalcount = 0;
		String position = user.getEmpCode().getPosition();
		if(StringUtils.isNotEmpty(MarketingConstance.EXPRESS_POSITIONS.get(position))
				||MarketingConstance.EXPRESS_MARKETING_GROUP_STANDARDCODE.equals(deptCode)){
			//1.若为快递市场营销组，则只查询 所有的大区和分部
			if(MarketingConstance.EXPRESS_MARKETING_GROUP_STANDARDCODE.equals(deptCode)){
				executeDeplist = authorizeService.getAllRegionAndDivision(deptName,start,limit);
				totalcount =  authorizeService.countAllRegionAndDivision(deptName);
			}else{
				//2.其余查询 快递大区/分部/点部/ 营业部
				executeDeplist = authorizeService.getAllDeptMappedExpressForMonitor(userId, deptName, start, limit);
				totalcount = authorizeService.countAllDeptMappedExpressForMonitor(userId, deptName);
			}
		}else{
			//模糊查询部门列表
			executeDeplist = authorizeService.getAllDepartmentByUserIdAndLikeDeptName(userId, deptName, start, limit);
			//统计总数
			totalcount = authorizeService.countAllDepartmentByUserIdAndLikeDeptName(userId, deptName);
		}
		//封装结果
		result.put("executeDeplist", executeDeplist);
		//封装结果
		result.put("totalcount", totalcount);
		//返回结果
		return result;			
	}
}
