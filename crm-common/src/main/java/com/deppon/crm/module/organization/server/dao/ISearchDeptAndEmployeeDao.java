package com.deppon.crm.module.organization.server.dao;
/**.
 * 
 *<p>作 者：张斌 最后修改时间：2012年5月23日 描 述：
 * 查询职员和部门的DAO层接口
 * </p>
 */
import java.util.List;

import com.deppon.crm.module.login.server.action.UserInfo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;

public interface ISearchDeptAndEmployeeDao {
	/**
	 * .
	 * <p>
	 * 根据部门名称查询符合条件的部门个数<br/>
	 * 方法名：countDeptByName
	 * </p>
	 * 
	 * @param deptName
	 *            部门名称
	 * @author 张斌
	 * @时间 2012-5-23
	 * @since JDK1.6
	 */
	public Long countDeptByName(String deptName);
	/**
	 * .
	 * <p>
	 * 根据部门名称查询部门<br/>
	 * 方法名：searchDeptByName
	 * </p>
	 * 
	 * @param deptName
	 *            部门名称
	 * @author 张斌
	 * @时间 2012-5-23
	 * @since JDK1.6
	 */
	public List<Department> searchDeptByName(String deptName,int start,int limit);
	/**
	 * .
	 * <p>
	 * 根据查詢條件查询符合条件的职员个数<br/>
	 * 方法名：countEmpByCondition
	 * </p>
	 * 
	 * @param condition
	 *            查询条件
	 * @author 张斌
	 * @时间 2012-5-23
	 * @since JDK1.6
	 */
	public Long countEmpByCondition(SearchEmpAndDeptCondition condition);
	/**
	 * .
	 * <p>
	 * 根据查詢條件查询职员<br/>
	 * 方法名：searchEmpByCondition
	 * </p>
	 * 
	 * @param condition
	 *            查询条件
	 * @author 张斌
	 * @时间 2012-5-23
	 * @since JDK1.6
	 */
	public List<UserInfo> searchEmpByCondition(SearchEmpAndDeptCondition condition,Integer start,Integer limit);

	/**
	 * <p>
	 * Description: 根据标杆编码查询部门结构<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-17
	 * @param standardcode
	 * @return List<DeptInfo>
	 */
	public List<DeptInfo> searchDeptStructure(String standardcode);
}
