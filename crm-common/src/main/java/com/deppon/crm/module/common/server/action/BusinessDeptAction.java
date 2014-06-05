package com.deppon.crm.module.common.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Delete;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.exception.security.UserNotLoginException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * <p>
 * 部门action<br />
 * </p>
 * @title BusinessDeptAction.java
 * @package com.deppon.crm.module.common.server.action
 * @author 李学兴
 * @version 0.2 2012-4-1
 */
public class BusinessDeptAction extends AbstractAction {
	// 注入LadingstationDepartmentManager
	private ILadingstationDepartmentManager departmentManager;
	// 注入departmentService
	private IDepartmentService departmentService;
	// 当前登录用户所属部门所属城市
	private City currentCity =new City();
	// 当前登录用户所属部门集合
	private List<Map<String,String>> currentUserDeptList = new ArrayList<Map<String,String>>();
	/**
	 * 
	 * <p>
	 * 获得当前登录用户所在部门所在城市<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-31
	 */
	public String acquireDeptCity(){
		User user =(User)UserContext.getCurrentUser();
		if(user == null){
			throw new UserNotLoginException();
		}
		BussinessDept dept = departmentManager.getBusDeptById(user.getEmpCode().getDeptId().getId());
		if(dept != null && dept.getCity() != null){
			currentCity = dept.getCity();
			if(dept.getProvince() != null&&!StringUtils.isEmpty(dept.getProvince().getId())
					&&!StringUtils.isEmpty(dept.getProvince().getName())){
				currentCity.setProvince(dept.getProvince());
				currentCity.setProvinceId(Integer.valueOf(dept.getProvince().getId()));
				currentCity.setProvinceName(dept.getProvince().getName());
			}
			
		}
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
	@Deprecated
	public String acquireCurrentUserDeptList() {
		String currentUserId = UserContext.getCurrentUser().getId();//queryAuthedDepts
		for(Department department: departmentService.queryLeftDepts(currentUserId, null, null)){
			Map<String,String> map = new HashMap<String,String>();
			map.put("deptId", department.getId());
			map.put("deptName", department.getDeptName());
			currentUserDeptList.add(map);
		}
		return SUCCESS;
	}
	public void setDepartmentManager(
			ILadingstationDepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}
	public List<Map<String, String>> getCurrentUserDeptList() {
		return currentUserDeptList;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public City getCurrentCity() {
		return currentCity;
	}
}
