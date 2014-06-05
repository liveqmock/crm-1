package com.deppon.crm.module.organization.server.action;

import java.util.List;

import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述： T_ORG_EMPLOYEE员工表的action（分页按条件获取所有员工信息）
 */
public class EmployeeAction extends AbstractAction {

	private static final long serialVersionUID = -2070011022862387862L;

	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;

	// 注入employeeService
	private IEmployeeService employeeService;

	// 分页查询，每页个数限制
	private int limit;

	// 分页查询的起始页
	private int start;

	// 总共有多少条数据
	private Long totalCount;

	// 员工id
	private String employeeId;

	// 消息
	private String message;

	// 前台传来的Employee对象，用来做查询条件
	private Employee employee;

	// 查询返回的Employee对象集合
	private List<Employee> employeeList;

	// 前台传来的部门名称，用来模糊查询部门编号
	private String deptName;

	/**
	 * 分页按条件获取所有员工信息
	 * 
	 * @param deptName
	 *            ,employee,limit,start
	 * @return
	 */
	public String findAllEmployee() {
		employeeList = employeeService.queryAll(deptName, employee, limit,
				start);
		totalCount = employeeService.count(deptName, employee);
		return SUCCESS;
	}

	/*--------------------------------元素get/set方法----------------------------------------------------------*/

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	public int getStart() {
		return start;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/*---------------------------------------备用方法-------------------------------------------------------*/
	/**
	 * 保存员工信息
	 * 
	 * @return
	 */
	public String saveEmployee() {
		employeeService.add(employee);
		message = messageBundle.getMessage(getLocale(),
				"i18n.organization.saveSuccess");
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deleteEmployee() {
		employeeService.removeById(employeeId);
		message = messageBundle.getMessage(getLocale(),
				"i18n.organization.deleteSuccess");
		return SUCCESS;
	}

	/**
	 * 修改员工信息
	 * 
	 * @return
	 */
	public String updateEmployee() {
		employeeService.update(employee);
		message = messageBundle.getMessage(getLocale(),
				"i18n.organization.updateSuccess");
		return SUCCESS;
	}

	/**
	 * 获取部门下所有员工信息
	 * 
	 * @return
	 */
	public String EmployeeByDeptCodeForPage() {
		if (employee.getDeptId().getId() == null) {
			return SUCCESS;
		}
		employeeList = employeeService.queryAll(employee, limit, start);
		totalCount = employeeService.count(employee);
		return SUCCESS;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
