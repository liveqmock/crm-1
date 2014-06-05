package com.deppon.crm.module.organization.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.organization.server.dao.IEmployeeDao;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 作 者：张斌 最后修改时间：2011年10月20日 描 述：
 * T_ORG_EMPLOYEE员工表的DAO的实现（查找符合条件的所有员工信息，并分页、查找符合条件的个数）
 */
@SuppressWarnings("unchecked")
public class EmployeeDao extends iBatis3DaoImpl implements IEmployeeDao {
	/**
	 * 查找符合条件的所有员工信息，并分页 getAll
	 * 
	 * @param employee
	 *            ,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	public List<Employee> getAll(String deptName, Employee employee, int limit,
			int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<Employee> list = null;
		Employee model = new Employee();
		if (employee != null) {
			if (employee.getDeptId() != null
					&& employee.getDeptId().getId() != null) {
				model.setDeptId(employee.getDeptId());
			} else {
				if (employee.getEmpCode() != null
						&& !"".equals(employee.getEmpCode())) {
					String empCode = "%" + employee.getEmpCode() + "%";
					model.setEmpCode(empCode);
				}
				if (employee.getEmpName() != null
						&& !"".equals(employee.getEmpName())) {
					String empName = "%" + employee.getEmpName() + "%";
					model.setEmpName(empName);
				}
				model.setGender(employee.getGender());
				if (employee.getBirthdate() != null) {
					model.setBirthdate(employee.getBirthdate());
				}
				if (employee.getCreateDate() != null) {
					model.setCreateDate(employee.getCreateDate());
				}
				model.setStatus(employee.getStatus());
				if (employee.getInDate() != null) {
					model.setInDate(employee.getInDate());
				}
				if (employee.getOutDate() != null) {
					model.setOutDate(employee.getOutDate());
				}
				if (employee.getOfferTel() != null
						&& !"".equals(employee.getOfferTel())) {
					String offerTel = "%" + employee.getOfferTel() + "%";
					model.setOfferTel(offerTel);
				}
				if (employee.getPosition() != null
						&& !"".equals(employee.getPosition())) {
					String position = "%" + employee.getPosition() + "%";
					model.setPosition(position);
				}
				if (employee.getMobileNumber() != null
						&& !"".equals(employee.getMobileNumber())) {
					String mobileNumber = "%" + employee.getMobileNumber()
							+ "%";
					model.setMobileNumber(mobileNumber);
				}

			}
			if (deptName != null && !"".equals(deptName)) {
				deptName = "%" + deptName + "%";
				model.setCreateUser(deptName);
			}
		}

		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.organization.shared.domain.Employee.getAll",
						model, rowBounds);
		return list;
	}

	/**
	 * 查找符合条件的个数 count
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	public Long count(String deptName, Employee employee) {
		Employee model = new Employee();
		if (employee != null) {
			if (employee.getDeptId() != null
					&& employee.getDeptId().getId() != null) {
				model.setDeptId(employee.getDeptId());
			} else {
				if (employee.getEmpCode() != null
						&& !"".equals(employee.getEmpCode())) {
					String empCode = "%" + employee.getEmpCode() + "%";
					model.setEmpCode(empCode);
				}
				if (employee.getEmpName() != null
						&& !"".equals(employee.getEmpName())) {
					String empName = "%" + employee.getEmpName() + "%";
					model.setEmpName(empName);
				}
				model.setGender(employee.getGender());
				if (employee.getBirthdate() != null) {
					model.setBirthdate(employee.getBirthdate());
				}
				if (employee.getCreateDate() != null) {
					model.setCreateDate(employee.getCreateDate());
				}
				model.setStatus(employee.getStatus());
				if (employee.getInDate() != null) {
					model.setInDate(employee.getInDate());
				}
				if (employee.getOutDate() != null) {
					model.setOutDate(employee.getOutDate());
				}
				if (employee.getOfferTel() != null
						&& !"".equals(employee.getOfferTel())) {
					String offerTel = "%" + employee.getOfferTel() + "%";
					model.setOfferTel(offerTel);
				}
				if (employee.getPosition() != null
						&& !"".equals(employee.getPosition())) {
					String position = "%" + employee.getPosition() + "%";
					model.setPosition(position);
				}
				if (employee.getMobileNumber() != null
						&& !"".equals(employee.getMobileNumber())) {
					String mobileNumber = "%" + employee.getMobileNumber()
							+ "%";
					model.setMobileNumber(mobileNumber);
				}

			}
			if (deptName != null && !"".equals(deptName)) {
				deptName = "%" + deptName + "%";
				model.setCreateUser(deptName);
			}
		}
		Long count = (Long) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.organization.shared.domain.Employee.count",
						model);
		return count;
	}

	/*--------------------------------备用方法----------------------------------------------------------*/
	/**
	 * 查找符合条件的所有员工信息 getAll
	 * 
	 * @param employee
	 * @return List<Department>
	 * @since JDK1.6
	 */
	public List<Employee> getAll(Employee employee) {
		List<Employee> list = null;
		Employee model = new Employee();
		if (employee != null) {
			if (employee.getId() != null && !"".equals(employee.getId())) {
				String id = "%" + employee.getId() + "%";
				model.setId(id);
			}
			model.setDeptId(employee.getDeptId());
			if (employee.getEmpCode() != null
					&& !"".equals(employee.getEmpCode())) {
				String empCode = "%" + employee.getEmpCode() + "%";
				model.setEmpCode(empCode);
			}
			if (employee.getEmpName() != null
					&& !"".equals(employee.getEmpName())) {
				String empName = "%" + employee.getEmpName() + "%";
				model.setEmpName(empName);
			}
			model.setGender(employee.getGender());
			model.setBirthdate(employee.getBirthdate());
			model.setStatus(employee.getStatus());
			model.setInDate(employee.getInDate());
			model.setOutDate(employee.getOutDate());
			if (employee.getOfferTel() != null
					&& !"".equals(employee.getOfferTel())) {
				String offerTel = "%" + employee.getOfferTel() + "%";
				model.setOfferTel(offerTel);
			}
			if (employee.getOfferAddress() != null
					&& !"".equals(employee.getOfferAddress())) {
				String offerAddress = "%" + employee.getOfferAddress() + "%";
				model.setOfferAddress(offerAddress);
			}
			if (employee.getOfferZipCode() != null
					&& !"".equals(employee.getOfferZipCode())) {
				String offerZipCode = "%" + employee.getOfferZipCode() + "%";
				model.setOfferZipCode(offerZipCode);
			}
			if (employee.getOfferEmail() != null
					&& !"".equals(employee.getOfferEmail())) {
				String offerEmail = "%" + employee.getOfferEmail() + "%";
				model.setOfferEmail(offerEmail);
			}
			if (employee.getMobileNumber() != null
					&& !"".equals(employee.getMobileNumber())) {
				String mobileNumber = "%" + employee.getMobileNumber() + "%";
				model.setMobileNumber(mobileNumber);
			}
			if (employee.getHomeTel() != null
					&& !"".equals(employee.getHomeTel())) {
				String homeTel = "%" + employee.getHomeTel() + "%";
				model.setHomeTel(homeTel);
			}
			if (employee.getHomeAddress() != null
					&& !"".equals(employee.getHomeAddress())) {
				String homeAddress = "%" + employee.getHomeAddress() + "%";
				model.setHomeAddress(homeAddress);
			}
			if (employee.getHomeZipCode() != null
					&& !"".equals(employee.getHomeZipCode())) {
				String homeZipCode = "%" + employee.getHomeZipCode() + "%";
				model.setHomeZipCode(homeZipCode);
			}
			if (employee.getPersonEmail() != null
					&& !"".equals(employee.getPersonEmail())) {
				String personEmail = "%" + employee.getPersonEmail() + "%";
				model.setPersonEmail(personEmail);
			}
		}
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.organization.shared.domain.Employee.getAll",
						model);
		return list;
	}

	/**
	 * 更新职员
	 */
	public void update(Employee employee) {
		getSqlSession()
				.update("com.deppon.crm.module.organization.shared.domain.Employee.update",
						employee);
	}

	/**
	 * 添加职员
	 */
	public void insert(Employee employee) {
		getSqlSession()
				.insert("com.deppon.crm.module.organization.shared.domain.Employee.insert",
						employee);
	}

	/**
	 * 删除职员
	 */
	public void deleteById(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.organization.shared.domain.Employee.deleteById",
						id);
	}

	/**
	 * 查找符合条件的所有员工信息，并分页 getAll
	 * 
	 * @param employee
	 *            ,limit,start
	 * @return List<Department>
	 * @since JDK1.6
	 */
	public List<Employee> getAll(Employee employee, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<Employee> list = null;
		Employee model = new Employee();
		if (employee != null) {
			if (employee.getId() != null && !"".equals(employee.getId())) {
				String id = "%" + employee.getId() + "%";
				model.setId(id);
			}
			model.setDeptId(employee.getDeptId());
			if (employee.getEmpCode() != null
					&& !"".equals(employee.getEmpCode())) {
				String empCode = employee.getEmpCode() + "%";
				model.setEmpCode(empCode);
			}
			if (employee.getEmpName() != null
					&& !"".equals(employee.getEmpName())) {
				String empName = "%" + employee.getEmpName() + "%";
				model.setEmpName(empName);
			}
			model.setGender(employee.getGender());
			model.setBirthdate(employee.getBirthdate());
			model.setStatus(employee.getStatus());
			model.setInDate(employee.getInDate());
			model.setOutDate(employee.getOutDate());
			if (employee.getOfferTel() != null
					&& !"".equals(employee.getOfferTel())) {
				String offerTel = "%" + employee.getOfferTel() + "%";
				model.setOfferTel(offerTel);
			}
			if (employee.getOfferAddress() != null
					&& !"".equals(employee.getOfferAddress())) {
				String offerAddress = "%" + employee.getOfferAddress() + "%";
				model.setOfferAddress(offerAddress);
			}
			if (employee.getOfferZipCode() != null
					&& !"".equals(employee.getOfferZipCode())) {
				String offerZipCode = "%" + employee.getOfferZipCode() + "%";
				model.setOfferZipCode(offerZipCode);
			}
			if (employee.getOfferEmail() != null
					&& !"".equals(employee.getOfferEmail())) {
				String offerEmail = "%" + employee.getOfferEmail() + "%";
				model.setOfferEmail(offerEmail);
			}
			if (employee.getMobileNumber() != null
					&& !"".equals(employee.getMobileNumber())) {
				String mobileNumber = "%" + employee.getMobileNumber() + "%";
				model.setMobileNumber(mobileNumber);
			}
			if (employee.getHomeTel() != null
					&& !"".equals(employee.getHomeTel())) {
				String homeTel = "%" + employee.getHomeTel() + "%";
				model.setHomeTel(homeTel);
			}
			if (employee.getHomeAddress() != null
					&& !"".equals(employee.getHomeAddress())) {
				String homeAddress = "%" + employee.getHomeAddress() + "%";
				model.setHomeAddress(homeAddress);
			}
			if (employee.getHomeZipCode() != null
					&& !"".equals(employee.getHomeZipCode())) {
				String homeZipCode = "%" + employee.getHomeZipCode() + "%";
				model.setHomeZipCode(homeZipCode);
			}
			if (employee.getPersonEmail() != null
					&& !"".equals(employee.getPersonEmail())) {
				String personEmail = "%" + employee.getPersonEmail() + "%";
				model.setPersonEmail(personEmail);
			}
		}
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.organization.shared.domain.Employee.getAll",
						model, rowBounds);
		return list;
	}

	/**
	 * 查找符合条件的个数 count
	 * 
	 * @param department
	 * @return Long
	 * @since JDK1.6
	 */
	public Long count(Employee employee) {
		Employee model = new Employee();
		if (employee != null) {
			if (employee.getId() != null && !"".equals(employee.getId())) {
				String id = "%" + employee.getId() + "%";
				model.setId(id);
			}
			model.setDeptId(employee.getDeptId());
			if (employee.getEmpCode() != null
					&& !"".equals(employee.getEmpCode())) {
				String empCode = employee.getEmpCode() + "%";
				model.setEmpCode(empCode);
			}
			if (employee.getEmpName() != null
					&& !"".equals(employee.getEmpName())) {
				String empName = "%" + employee.getEmpName() + "%";
				model.setEmpName(empName);
			}
			model.setGender(employee.getGender());
			model.setBirthdate(employee.getBirthdate());
			model.setStatus(employee.getStatus());
			model.setInDate(employee.getInDate());
			model.setOutDate(employee.getOutDate());
			if (employee.getOfferTel() != null
					&& !"".equals(employee.getOfferTel())) {
				String offerTel = "%" + employee.getOfferTel() + "%";
				model.setOfferTel(offerTel);
			}
			if (employee.getOfferAddress() != null
					&& !"".equals(employee.getOfferAddress())) {
				String offerAddress = "%" + employee.getOfferAddress() + "%";
				model.setOfferAddress(offerAddress);
			}
			if (employee.getOfferZipCode() != null
					&& !"".equals(employee.getOfferZipCode())) {
				String offerZipCode = "%" + employee.getOfferZipCode() + "%";
				model.setOfferZipCode(offerZipCode);
			}
			if (employee.getOfferEmail() != null
					&& !"".equals(employee.getOfferEmail())) {
				String offerEmail = "%" + employee.getOfferEmail() + "%";
				model.setOfferEmail(offerEmail);
			}
			if (employee.getMobileNumber() != null
					&& !"".equals(employee.getMobileNumber())) {
				String mobileNumber = "%" + employee.getMobileNumber() + "%";
				model.setMobileNumber(mobileNumber);
			}
			if (employee.getHomeTel() != null
					&& !"".equals(employee.getHomeTel())) {
				String homeTel = "%" + employee.getHomeTel() + "%";
				model.setHomeTel(homeTel);
			}
			if (employee.getHomeAddress() != null
					&& !"".equals(employee.getHomeAddress())) {
				String homeAddress = "%" + employee.getHomeAddress() + "%";
				model.setHomeAddress(homeAddress);
			}
			if (employee.getHomeZipCode() != null
					&& !"".equals(employee.getHomeZipCode())) {
				String homeZipCode = "%" + employee.getHomeZipCode() + "%";
				model.setHomeZipCode(homeZipCode);
			}
			if (employee.getPersonEmail() != null
					&& !"".equals(employee.getPersonEmail())) {
				String personEmail = "%" + employee.getPersonEmail() + "%";
				model.setPersonEmail(personEmail);
			}
		}
		Long count = (Long) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.organization.shared.domain.Employee.count",
						model);
		return count;
	}

	public List<Employee> getAllEmployeesByDeptId(Department deptId) {
		Employee empl = new Employee();
		empl.setDeptId(deptId);
		return this
				.getSqlSession()
				.selectList(
						"com.deppon.crm.module.organization.shared.domain.Employee.getAllEmployeesByDeptId",
						empl);
	}
	
	public List<Employee> getAllEmployeesByDeptId(String deptId) {
		return this
				.getSqlSession()
				.selectList(
						"com.deppon.crm.module.organization.shared.domain.Employee.getAllEmployeesByDeptmentId",
						deptId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.organization.server.dao.IEmployeeDao#getEmpById
	 * (java.lang.String)
	 */
	@Override
	public Employee getEmpById(String id) {
		return (Employee) this
				.getSqlSession()
				.selectOne(
						"com.deppon.crm.module.organization.shared.domain.Employee.getEmpById",
						id);
	}

	@Override
	public Employee getEmpByCode(String code) {
		return (Employee) this.getSqlSession()
		.selectOne(
				"com.deppon.crm.module.organization.shared.domain.Employee.getEmpByCode",
				code);
	}
	public int deleteByCode(String empCode){
		int res=this.getSqlSession().delete("com.deppon.crm.module.organization.shared.domain.Employee.deleteByCode",empCode);
		return res;
	}
	/**
	 * 同步UUMS 数据，进行相应的数据同步，权限分配
	 * @author ZhangZW
	 * @update 2014-1-6 
	 * @param empCode changeType postion orgId
	 * @return
	 */
	@Override
	public int syncEmp(String empCode, String changeType, String postion,
			String orgId) {
		int res=0;
		Map<String,String> map=new HashMap<String,String>();
		map.put("empCode", empCode);
		map.put("changeType", changeType);
		map.put("position", postion);
		map.put("orgId", orgId);
		res=getSqlSession().update("com.deppon.crm.module.organization.shared.domain.Employee.syncEmp", map);
		return res;
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
		return this.getSqlSession().selectList(
				"com.deppon.crm.module.organization.shared.domain.Employee.getAllEmployeeByDeptCode", deptCode);
	}
}
