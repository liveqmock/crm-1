package com.deppon.crm.module.duty.server.dao.impl;

import java.util.List;
import com.deppon.crm.module.duty.server.dao.IDutyDeptDao;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class DutyDeptDaoImpl extends iBatis3DaoImpl implements IDutyDeptDao{

	private static final String NAMESPACE_DUTY = "com.deppon.crm.module.duty.shared.domain.DutyDept.";
	//查询所有工单特殊责任部门
	private static final String SEARCHALLDUTYDEPT = "searchAllDutyDept";
	//添加工单特殊责任部门
	private static final String INSERTDUTYDEPT = "insertDutyDept";
	//查询特殊责任部门是否存在
	private static final String SEARCHDUTYDEPTBYID="searchDutyDeptById";
	//删除工单特殊责任部门
	private static final String DELETEDUTYDEPTLIST = "deleteDutyDeptList";
	//查询该部门是否在经营本部下
	private static final String SEARCHDUTYDEPTTYPE = "searchDutyDeptType";
	//
	private static final String SEARCHDUTYEMPTYPE = "searchDutyEmpType";
	//根据EMPID查询部门信息
	private static final String SEARCH_DEPARTMENT_BYEMPID = "searchDepartmentByEmpId";
	
	//严重多个部门是否同一个事业部级别
	private static final String IS_SAME_DEPARTMENT_BY_DEPTIDS = "isSameDepartmentByDeptIds";
	//根据个人查询其所在事业部
	private static final String SEARCHBUSINESSBYUSER = "searchBusinessByUser";
	//根据部门查询其所在事业部
	private static final String SEARCHBUSINESSBYDEPT = "searchBusinessByDept";
	/**
	 * <p>
	 *	Description: 根据事业部查询此事业部下所有营业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-7
	 * @param deptid 事业部Id
	 * @return List<Department>  该事业部下营业部的集合
	 */
	public List<Department> searchSalesDeptByBusinessDept(String deptid){
		return null;
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
		return (String)this.getSqlSession().selectOne(NAMESPACE_DUTY+SEARCHBUSINESSBYUSER, userid);
	}
	
	
	
	
	/**
	 * <p>
	 *	Description: 根据用户ID查询其所在事业部
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-7
	 * @param deptId 部门id
	 * @return String 事业部id
	 */
	public String searchBusinessByDept(String deptId){
		return (String)this.getSqlSession().selectOne(NAMESPACE_DUTY+SEARCHBUSINESSBYDEPT, deptId);
	}
	
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
		return this.getSqlSession().selectList(NAMESPACE_DUTY+SEARCHALLDUTYDEPT);
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
		this.getSqlSession().insert(NAMESPACE_DUTY+INSERTDUTYDEPT, dutyDept);
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
		return (Integer)(this.getSqlSession().selectOne(NAMESPACE_DUTY+SEARCHDUTYDEPTBYID, deptId));
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
		this.getSqlSession().delete(NAMESPACE_DUTY +DELETEDUTYDEPTLIST , dutyDeptList);
	}
	
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
	public int searchDutyDeptType(String deptId){
		return (Integer)(this.getSqlSession().selectOne(NAMESPACE_DUTY+SEARCHDUTYDEPTTYPE, deptId));
	}
	/**
	 * <p>
	 *	Description: 查询员工是否在经营本部下
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-14
	 * @param empId  待校验员工ID
	 * @return
	 * int 若该员工在经营本部下，返回1，不在返回-1
	 */
	public int searchDutyEmpType(String empId){
		return (Integer)(this.getSqlSession().selectOne(NAMESPACE_DUTY+SEARCHDUTYEMPTYPE,empId));
	}


	@Override
	public Department searchDepartmentByEmpId(String empId) {
		return (Department) this.getSqlSession().selectOne(NAMESPACE_DUTY+SEARCH_DEPARTMENT_BYEMPID,empId);
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
	@SuppressWarnings("unchecked")
	public boolean isSameDepartmentByDeptIds(List<String> deptIdList){
		List<DutyDept> deptList =this.getSqlSession().selectList(NAMESPACE_DUTY+IS_SAME_DEPARTMENT_BY_DEPTIDS,deptIdList);
		
		return (deptList == null || deptList.size() !=1)?false:true;
	}
	
	
}
