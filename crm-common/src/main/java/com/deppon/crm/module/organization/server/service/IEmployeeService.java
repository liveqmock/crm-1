package com.deppon.crm.module.organization.server.service;

import java.util.List;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.service.IService;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述：
 * T_ORG_EMPLOYEE员工表的Service的接口（查找符合条件的所有员工信息，并分页、查找符合条件的个数）
 */
public interface IEmployeeService extends IService {
	/**
	 * 查找符合条件的所有员工信息，并分页
	 * getAll
	 * @param employee,limit,start,deptName
	 * @return List<Department>
	 * @since JDK1.6
	 */
	List<Employee> queryAll(String deptName,Employee employee, int limit, int start);
	/**
	 * 查找符合条件的个数
	 * count
	 * @param employee,deptName
	 * @return Long
	 * @since JDK1.6
	 */
	Long count(String deptName,Employee employee);
	/*--------------------------------备用方法----------------------------------------------------------*/
	/**
	 * 查找符合条件的所有员工信息
	 * getAll
	 * @param employee
	 * @return List<Department>
	 * @since JDK1.6
	 */
	List<Employee> queryAll(Employee employee);
	/**
	 * 添加职员
	 */
	void add(Employee employee);
	/**
	 * 更新职员
	 */
	void update(Employee employee);
	/**
	 * 删除职员
	 */
	void removeById(String id);
	/**
	 * 查找符合条件的所有员工信息，并分页
	 * getAll
	 * @param employee,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	List<Employee> queryAll(Employee employee, int limit, int start);
	/**
	 * 查找符合条件的个数
	 * count
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	Long count(Employee employee);
	
	/**
	 * 查找部门下所有员工
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	List<Employee> getAllEmployeesByDeptId(Department deptId);
	
	/**
	 * 查找部门下所有员工
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	List<Employee> getAllEmployeesByDeptId(String deptId);

	/*
	 * 根据员工ID查询员工信息
	 */
	Employee getEmpById(String id);
	/**
	 * 
	 * <p>
	 * Description:根据工号查询员工<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-13
	 * @param code
	 * @return
	 * Employee
	 */
	public Employee getEmpByCode(String code);
	/**
	 * 同步UUMS 数据，进行相应的数据同步，权限分配
	 * @author ZhangZW
	 * @update 2014-1-6 
	 * @param empCode changeType postion orgId
	 * @return
	 */
	void  syncEmp(String empCode,String changeType,String postion,String orgId);
	
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
