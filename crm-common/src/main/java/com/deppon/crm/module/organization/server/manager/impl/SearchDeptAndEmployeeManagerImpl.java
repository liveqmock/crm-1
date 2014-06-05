package com.deppon.crm.module.organization.server.manager.impl;
/**.
 * 
 *<p>作 者：张斌 最后修改时间：2012年5月23日 描 述：
 * 查询职员和部门的Manager层实现
 * </p>
 */
import java.util.List;

import com.deppon.crm.module.login.server.action.UserInfo;
import com.deppon.crm.module.organization.server.manager.ISearchDeptAndEmployeeManager;
import com.deppon.crm.module.organization.server.service.ISearchDeptAndEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;

public class SearchDeptAndEmployeeManagerImpl implements
		ISearchDeptAndEmployeeManager {
	//service层接口
    private ISearchDeptAndEmployeeService searchDeptAndEmployeeService;
	public ISearchDeptAndEmployeeService getSearchDeptAndEmployeeService() {
		return searchDeptAndEmployeeService;
	}
	public void setSearchDeptAndEmployeeService(
			ISearchDeptAndEmployeeService searchDeptAndEmployeeService) {
		this.searchDeptAndEmployeeService = searchDeptAndEmployeeService;
	}
//--------------------------------------------------------------------
	@Override
	public List<Department> searchDeptByName(String deptName,int start,int limit) {
		deptName = "%"+deptName+"%";
		return searchDeptAndEmployeeService.searchDeptByName(deptName,start,limit);
	}
	
	@Override
	public List<UserInfo> searchEmpByCondition(
			SearchEmpAndDeptCondition condition,int start,int limit) {
		if(condition!=null){
			condition.setDeptName("%"+condition.getDeptName()+"%");
			condition.setEmpName("%"+condition.getEmpName()+"%");
		}
		return searchDeptAndEmployeeService.searchEmpByCondition(condition,start,limit);
	}
	@Override
	public Long countDeptByName(String deptName) {
		deptName = "%"+deptName+"%";
		return searchDeptAndEmployeeService.countDeptByName(deptName);
	}
	@Override
	public Long countEmpByCondition(SearchEmpAndDeptCondition condition) {
		if(condition!=null){
			condition.setDeptName("%"+condition.getDeptName()+"%");
			condition.setEmpName("%"+condition.getEmpName()+"%");
		}
		return searchDeptAndEmployeeService.countEmpByCondition(condition);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.organization.server.manager.ISearchDeptAndEmployeeManager#searchDeptStructure(java.lang.String)
	 */
	@Override
	public List<DeptInfo> searchDeptStructure(String standardcode) {
		// 根据客户编码查询部门接口-优惠券使用
		return searchDeptAndEmployeeService.searchDeptStructure(standardcode);
	}

}
