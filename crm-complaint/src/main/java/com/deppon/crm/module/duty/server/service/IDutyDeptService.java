package com.deppon.crm.module.duty.server.service;

import java.util.List;

import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.organization.shared.domain.Department;

public interface IDutyDeptService {

	/**
	 * <p>
	 *	Description: 查询所有特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-8
	 * @return
	 * List<DutyDept>
	 */
	public List<DutyDept> searchAllDutyDept();

	
	/**
	 * <p>
	 *	Description: 添加工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDept
	 * void
	 */
	public void saveDutyDept(DutyDept dutyDept);
	/**
	 * <p>
	 *	Description: 根据Id查询部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param deptId
	 * @return 是否存在 0不存在  1存在
	 */
	public int searchDutyDeptById(String deptId);
	
	/**
	 * <p>
	 *	Description: 删除工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDeptList
	 * void
	 */
	public void deleteDutyDept(List<DutyDept> dutyDeptList);
	
	
	/**
	 * <p>
	 *	Description: 查询责任部门是否为营业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-14
	 * @param deptId 待校验部门ID
	 * @return
	 * int 若部门在为营业部，即该部门在经营本部下，返回1，不在返回-1
	 */
	public int searchDutyDeptType(String deptId);
	
	/**
	 * <p>
	 *	Description: 查询员工所在部门
	 * </p> 
	 * @author hpf
	 * @date 2014-1-14
	 * @param empId  员工ID
	 * @return
	 */
	public Department searchDepartmentByEmpId(String empId);
	
	/**
	 * <p>
	 *	Description: 查询多个部门是否为同一个事业部
	 * </p> 
	 * @author hpf
	 * @date 2014-1-16
	 * @return
	 * List<DutyDept>
	 */
	boolean isSameDepartmentByDeptIds(List<String> deptIdList);

	/**
	 * <p>
	 *	Description: 根据用户ID查询其所在事业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-7
	 * @param userid 用户id
	 * @return String 事业部id
	 */
	public String searchBusinessByUser(String userid);
	
	/**
	 * <p>
	 *	Description: 根据部门ID查询其所在事业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-7
	 * @param deptId 部门id
	 * @return String 事业部id
	 */
	public String searchBusinessByDept(String deptId);
}
