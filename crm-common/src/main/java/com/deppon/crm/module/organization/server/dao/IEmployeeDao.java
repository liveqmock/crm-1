package com.deppon.crm.module.organization.server.dao;

import java.util.List;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述：
 * T_ORG_EMPLOYEE员工表的DAO的接口（查找符合条件的所有员工信息，并分页、查找符合条件的个数）
 */
public interface IEmployeeDao {
	/**
	 * 查找符合条件的所有员工信息，并分页 getAll
	 * 
	 * @param employee
	 *            ,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	List<Employee> getAll(String deptName, Employee employee, int limit,
			int start);

	/**
	 * 查找符合条件的个数 count
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	Long count(String deptName, Employee employee);

	/*--------------------------------备用方法----------------------------------------------------------*/
	/**
	 * 查找符合条件的所有员工信息 getAll
	 * 
	 * @param employee
	 * @return List<Department>
	 * @since JDK1.6
	 */
	List<Employee> getAll(Employee employee);

	/**
	 * 更新职员
	 */
	void update(Employee employee);

	/**
	 * 添加职员
	 */
	void insert(Employee employee);

	/**
	 * 删除职员
	 */
	void deleteById(String id);

	/**
	 * 查找符合条件的所有员工信息，并分页 getAll
	 * 
	 * @param employee
	 *            ,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	List<Employee> getAll(Employee employee, int limit, int start);

	/**
	 * 查找符合条件的个数 count
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	Long count(Employee employee);

	/**
	 * 根据组织ID获取该组织部门下的所有员工
	 */
	List<Employee> getAllEmployeesByDeptId(Department deptId);
	
	/**
	 * 根据组织ID获取该组织部门下的所有员工
	 */
	List<Employee> getAllEmployeesByDeptId(String deptId);

	/*
	 * 根据员工ID查询员工信息
	 */
	Employee getEmpById(String id);
	/**
	 * 
	 * <p>
	 * Description:根据员工code查询员工信息br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-13
	 * @param code
	 * @return
	 * Employee
	 */
	Employee getEmpByCode(String code);
	/**
	 * 同步UUMS 数据，进行相应的数据同步，权限分配
	 * @author ZhangZW
	 * @update 2014-1-6 
	 * @param empCode changeType postion orgId
	 * @return
	 */
	int  syncEmp(String empCode,String changeType,String postion,String orgId);
	
	/**
	 * 
	 * <p>
	 * 根据部门编号查询有效员工列表
	 * <br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-19
	 * @param deptCode
	 * @return List<Employee>
	 * 
	 */
	List<Employee> getAllEmployeeByDeptCode(String deptCode);
}
