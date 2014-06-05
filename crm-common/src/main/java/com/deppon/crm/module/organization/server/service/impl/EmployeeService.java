package com.deppon.crm.module.organization.server.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.organization.server.dao.IEmployeeDao;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.organization.shared.exception.EmployeeException;
import com.deppon.crm.module.organization.shared.exception.EmployeeExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
/**
 * 
	作        者：张斌
	最后修改时间：2011年10月20日
	描        述： T_ORG_EMPLOYEE员工表的Service的实现（查找符合条件的所有员工信息，并分页、查找符合条件的个数）
 */
@Transactional(readOnly = true)
public class EmployeeService implements IEmployeeService {

	private IEmployeeDao employeeDao;

	/**
	 * 查找符合条件的所有员工信息，并分页
	 * getAll
	 * @param employee,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	public List<Employee> queryAll(String deptName,Employee employee, int limit, int start) {
		if(employee==null){
			EmployeeException e = new EmployeeException(EmployeeExceptionType.IsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				private static final long serialVersionUID = 1L;};
		}
		return employeeDao.getAll(deptName,employee,limit,start);
	}
	/**
	 * 查找符合条件的个数
	 * count
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	public Long count(String deptName,Employee employee) {
		Long num=employeeDao.count(deptName,employee);
		if(num==0){
			num=(long) 1;
		}
		return num;
	}
	
	
/*--------------------------------备用方法----------------------------------------------------------*/
	/**
	 * 查找符合条件的所有员工信息
	 * getAll
	 * @param employee
	 * @return List<Department>
	 * @since JDK1.6
	 */
	public List<Employee> queryAll(Employee employee) {
		return employeeDao.getAll(employee);
	}
	/**
	 * 添加职员
	 */
	@SuppressWarnings("serial")
	@Transactional
	public void add(Employee employee) {
		if (employee == null) {
			EmployeeException e = new EmployeeException(EmployeeExceptionType.IsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		employeeDao.insert(employee);
	}
	/**
	 * 更新职员
	 */
	@SuppressWarnings("serial")
	@Transactional
	public void update(Employee employee) {
		if (employee == null) {
			EmployeeException e = new EmployeeException(EmployeeExceptionType.IsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		if(employee.getId()==null&&"".equals(employee.getId().trim())){
			EmployeeException e = new EmployeeException(EmployeeExceptionType.IdNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		employeeDao.update(employee);
	}
	/**
	 * 删除职员
	 */
	@SuppressWarnings("serial")
	@Transactional
	public void removeById(String employeeId) {
		if (employeeId == null || "".equals(employeeId.trim())) {
			EmployeeException e = new EmployeeException(EmployeeExceptionType.IdNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {};
		}
		employeeDao.deleteById(employeeId);
	}
	
	public List<Employee> getAllEmployeesByDeptId(Department deptId) {
		return this.employeeDao.getAllEmployeesByDeptId(deptId);
	}
	
	public List<Employee> getAllEmployeesByDeptId(String deptId) {
		return this.employeeDao.getAllEmployeesByDeptId(deptId);
	}
	
	public List<Employee> queryAll(Employee employee, int limit, int start) {
		return employeeDao.getAll(employee,limit,start);
	}
	public Long count(Employee employee) {
		return employeeDao.count(employee);
	}
	public IEmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.organization.server.service.IEmployeeService#getEmpById
	 * (java.lang.String)
	 */
	@Override
	public Employee getEmpById(String id) {
		return this.employeeDao.getEmpById(id);
	}
	
	public Employee getEmpByCode(String code){
		if(code==null||"".equals(code)){
			return null;
		}
			return employeeDao.getEmpByCode(code);
	}
	@Override
	public void syncEmp(String empCode, String changeType, String postion,
			String orgId) {
		 employeeDao.syncEmp(empCode, changeType, postion, orgId);
	}
	
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
	@Override
	public List<Employee> getAllEmployeeByDeptCode(String deptCode) {
		return employeeDao.getAllEmployeeByDeptCode(deptCode);
	}
}
