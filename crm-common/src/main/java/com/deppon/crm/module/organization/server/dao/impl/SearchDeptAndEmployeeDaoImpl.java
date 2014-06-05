package com.deppon.crm.module.organization.server.dao.impl;
/**.
 * 
 *<p>作 者：张斌 最后修改时间：2012年5月23日 描 述：
 * 查询职员和部门的DAO层实现
 * </p>
 */
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.login.server.action.UserInfo;
import com.deppon.crm.module.organization.server.dao.ISearchDeptAndEmployeeDao;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class SearchDeptAndEmployeeDaoImpl extends iBatis3DaoImpl implements ISearchDeptAndEmployeeDao  {
	
	private static final String DEPTNAMESPACE = "com.deppon.crm.module.organization.shared.domain.Department.";
	private static final String EMPNAMESPACE = "com.deppon.crm.module.authorization.shared.domain.User.";
	private static final String SEARCH_DEPT_BYNAME = "searchDeptByName";
	private static final String SEARCH_EMP_CONDITION = "searchEmpByCondition";
	private static final String COUNT_DEPT_BYNAME = "countDeptByName";
	private static final String COUNT_EMP_CONDITION = "countEmpByCondition";
	private static final String SELECT_DEPT_STRUCTURE_INFO = "select_dept_structure_info";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> searchDeptByName(String deptName,int start,int limit) {
		RowBounds bounds = new RowBounds(start,limit);
		return this.getSqlSession()
				.selectList(DEPTNAMESPACE + SEARCH_DEPT_BYNAME,
						deptName,bounds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> searchEmpByCondition(
			SearchEmpAndDeptCondition condition,Integer start,Integer limit) {
		RowBounds bounds = new RowBounds(start,limit);
		return this.getSqlSession()
				.selectList(EMPNAMESPACE + SEARCH_EMP_CONDITION,
						condition,bounds);
	}

	@Override
	public Long countDeptByName(String deptName) {
		if(deptName==null||"".equals(deptName)){
			return null;
		}
		return (Long) this.getSqlSession()
				.selectOne(DEPTNAMESPACE + COUNT_DEPT_BYNAME,
						deptName);
	}

	@Override
	public Long countEmpByCondition(
			SearchEmpAndDeptCondition condition) {
		return (Long) this.getSqlSession()
				.selectOne(EMPNAMESPACE + COUNT_EMP_CONDITION,
						condition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptInfo> searchDeptStructure(String standardcode) {
		RowBounds bounds = new RowBounds(0,10);
		// 根据客户编码查询部门接口-优惠券使用，默认只查10条
		return this.getSqlSession().selectList(
				DEPTNAMESPACE + SELECT_DEPT_STRUCTURE_INFO, standardcode, bounds);
	}

}
