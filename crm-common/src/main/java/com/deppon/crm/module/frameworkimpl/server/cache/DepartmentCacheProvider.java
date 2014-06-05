
package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;

/**
 * 功能权限缓存数据提供对象
 * 
 * @author ztjie
 * 
 */
public class DepartmentCacheProvider implements
		IBatchCacheProvider<String, Department> {

	private IDepartmentService departmentService;

	/**
	 * 返回最后修改时间
	 */
	public Date getLastModifyTime() {
		 
		return getDepartmentService().getLastModifyTime();
	}

	/**
	 * 返回最新的部门数据
	 */
	public Map<String, Department> get() {
		Map<String, Department> map = new HashMap<String, Department>();
		List<Department> departments = getDepartmentService().getAllDepartments();
		for (Department department : departments) {
			map.put(department.getId(), department);
		}
		return map;
	}
	
	public Department get(String deptId){
		return getDepartmentService().getDepartmentById(deptId);
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	

	

}

