package com.deppon.crm.module.organization.server.service.impl;
/**.
 * 
 *<p>作 者：张斌 最后修改时间：2012年5月23日 描 述：
 * 查询职员和部门的SERVICE层实现
 * </p>
 */
import java.util.List;

import com.deppon.crm.module.login.server.action.UserInfo;
import com.deppon.crm.module.organization.server.dao.ISearchDeptAndEmployeeDao;
import com.deppon.crm.module.organization.server.service.ISearchDeptAndEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;

public class SearchDeptAndEmployeeServiceImpl implements
		ISearchDeptAndEmployeeService {
	//dao层接口
	private ISearchDeptAndEmployeeDao searchDeptAndEmployeeDao;
    public ISearchDeptAndEmployeeDao getSearchDeptAndEmployeeDao() {
		return searchDeptAndEmployeeDao;
	}
    
	public void setSearchDeptAndEmployeeDao(
			ISearchDeptAndEmployeeDao searchDeptAndEmployeeDao) {
		this.searchDeptAndEmployeeDao = searchDeptAndEmployeeDao;
	}

	//-----------------------------------------------------------------
	@Override
	public List<Department> searchDeptByName(String deptName,int start,int limit) {
		return searchDeptAndEmployeeDao.searchDeptByName(deptName,start,limit);
	}

	@Override
	public List<UserInfo> searchEmpByCondition(
			SearchEmpAndDeptCondition condition,int start,int limit) {
		return searchDeptAndEmployeeDao.searchEmpByCondition(condition,start,limit);
	}

	@Override
	public Long countEmpByCondition(SearchEmpAndDeptCondition condition) {
		return searchDeptAndEmployeeDao.countEmpByCondition(condition);
	}

	@Override
	public Long countDeptByName(String deptName) {
		return searchDeptAndEmployeeDao.countDeptByName(deptName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.organization.server.service.ISearchDeptAndEmployeeService#searchDeptStructure(java.lang.String)
	 */
	@Override
	public List<DeptInfo> searchDeptStructure(String standardcode) {
		return searchDeptAndEmployeeDao.searchDeptStructure(standardcode);
	}

}
