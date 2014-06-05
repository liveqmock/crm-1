package com.deppon.crm.module.duty.server.service.impl;

import java.util.List;

import com.deppon.crm.module.duty.server.dao.IDutyDeptDao;
import com.deppon.crm.module.duty.server.service.IDutyDeptService;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.organization.shared.domain.Department;

public class DutyDeptServiceImpl implements IDutyDeptService{

	private IDutyDeptDao dutydeptdao;
	/**
	 * <p>
	 *	Description: 查询所有特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-8
	 * @return
	 * List<DutyDept>
	 */
	public List<DutyDept> searchAllDutyDept(){
		return dutydeptdao.searchAllDutyDept();
	}
	
	/**
	 * <p>
	 *	Description: 添加工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDept
	 * void
	 */
	public void saveDutyDept(DutyDept dutyDept){
		dutydeptdao.saveDutyDept(dutyDept);
	}
	/**
	 * <p>
	 *	Description: 根据Id查询部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param deptId
	 * @return 是否存在 0不存在  1存在
	 */
	public int searchDutyDeptById(String deptId){
		return dutydeptdao.searchDutyDeptById(deptId);
	}
	
	/**
	 * <p>
	 *	Description: 删除工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDeptList
	 * void
	 */
	public void deleteDutyDept(List<DutyDept> dutyDeptList){
		dutydeptdao.deleteDutyDept(dutyDeptList);
	}
	
	

	/**
	 * <p>
	 *	Description: 根据用户ID查询其所在事业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-7
	 * @param userid 用户id
	 * @return String 事业部id
	 */
	public String searchBusinessByUser(String userid){
		return dutydeptdao.searchBusinessByUser(userid);
	}
	
	/**
	 * <p>
	 *	Description: 根据部门ID查询其所在事业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-7
	 * @param deptId 部门id
	 * @return String 事业部id
	 */
	public String searchBusinessByDept(String deptId){
		return dutydeptdao.searchBusinessByDept(deptId);
	}
	
	
	/**
	 * @return the dutydeptdao
	 */
	public IDutyDeptDao getDutydeptdao() {
		return dutydeptdao;
	}
	/**
	 * @param dutydeptdao the dutydeptdao to set
	 */
	public void setDutydeptdao(IDutyDeptDao dutydeptdao) {
		this.dutydeptdao = dutydeptdao;
	}

	@Override
	public int searchDutyDeptType(String deptId) {
		return dutydeptdao.searchDutyDeptType(deptId);
	}


	@Override
	public Department searchDepartmentByEmpId(String empId) {
		return dutydeptdao.searchDepartmentByEmpId(empId);
	}
	
	/**
	 * <p>
	 *	Description: 查询多个部门是否为同一个事业部
	 * </p> 
	 * @author hpf
	 * @date 2014-1-16
	 * @return
	 * List<DutyDept>
	 */
	@Override
	public boolean isSameDepartmentByDeptIds(List<String> deptIdList){
		return dutydeptdao.isSameDepartmentByDeptIds(deptIdList);
	}
}
