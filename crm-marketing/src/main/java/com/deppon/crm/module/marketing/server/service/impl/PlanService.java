
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.dao.IPlanDao;
import com.deppon.crm.module.marketing.server.service.IPlanService;
import com.deppon.crm.module.marketing.shared.domain.NeedMaintainCustomer;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * 客户开发Service实现类型<br />
 * </p>
 * @title DevelopPlanService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author Administrator
 * @version 0.1 2012-3-23
 */

public class PlanService implements IPlanService{
	private IPlanDao planDao;
	
	public void setPlanDao(IPlanDao planDao) {
		this.planDao = planDao;
	}
	
	/**
	 * 
	 * <p>
	 * 查询该部门的历史开发计划列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @return
	 * List<Plan>
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchDepartmentPlanList(java.lang.String)
	 */
	@Override
	public List<Plan> searchDepartmentPlanList(String execDeptId, String topic, String planType) {
		return planDao.searchDepartmentPlanList(execDeptId, topic, planType);
	}

	/**
	 * <p>
	 * 创建开发计划<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param plan
	 * @param customerList
	 * @return
	 * boolean
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#createPlan(com.deppon.crm.module.marketing.shared.domain.Plan, java.util.List)
	 */
	@Override
	public String createPlan(Plan plan) {
		return planDao.createPlan(plan);
	}
	
	@Override
	public List<PlanList> searchPlan(PlanDevelopCondition condition,
			int start, int limit) {
		if(StringUtils.isNotEmpty(condition.getPlanName())){
			String planName = "%"+condition.getPlanName()+"%";
			condition.setPlanName(planName);
		}
		return planDao.searchPlan(condition, start, limit);
	}

	@Override
	public boolean deletePlan(String[] id) {
		return planDao.deletePlanDevelop(id);
	}

	
	@Override
	public List<String> searchScheduleIdByPlanId(String planId) {
		return planDao.searchScheduleIdByPlanId(planId);
	}

	@Override
	public List<Plan> searchPlanByTheme(String theme, String deptId, String planType) {
		return planDao.searchPlanByTheme(theme, deptId, planType);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchDepartmentPlanList(java.util.Set)
	 */
	/*@Override
	public List<Plan> searchDepartmentPlanList(Set<String> deptIds,String planType) {
		return planDao.searchDepartmentPlanList(deptIds, planType);
	}*/

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#updatePlan(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@Override
	public boolean updatePlan(Plan plan) {
		return planDao.updatePlan(plan);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getPlanDetail(java.lang.String)
	 */
	@Override
	public List<String> getPlanCustIdList(String planId) {
		return planDao.queryPlanCustIdList(planId);
	}

	@Override
	public PlanList searchPlanDetail(String id) {
		return planDao.searchPlanDetail(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getPlanById(java.lang.String)
	 */
	@Override
	public Plan getPlanById(String id) {
		return planDao.searchPlanById(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getPlanCount(com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition)
	 */
	@Override
	public int getPlanCount(PlanDevelopCondition condition) {
		return planDao.getPlanCount(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchMemberList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public List<CustomerVo> searchMemberList(CustomerVo vo,int start,int limit) {
		if(StringUtils.isNotEmpty(vo.getCustName())){
			String custName="%"+vo.getCustName()+"%";
			vo.setCustName(custName);
		}
		
		if(StringUtils.isNotEmpty(vo.getLinkManName())){
			String linkManName="%"+vo.getLinkManName()+"%";
			vo.setLinkManName(linkManName);
		}
		return	planDao.searchMemberList(vo,start,limit);
	}

	public int searchMemberCount(CustomerVo vo){
		if(StringUtils.isNotEmpty(vo.getCustName())){
			String custName="%"+vo.getCustName()+"%";
			vo.setCustName(custName);
		}
		
		if(StringUtils.isNotEmpty(vo.getLinkManName())){
			String linkManName="%"+vo.getLinkManName()+"%";
			vo.setLinkManName(linkManName);
		}
		return planDao.searchMemberCount(vo);
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getPlanContactIdList(java.lang.String)
	 */
	@Override
	public List<String> getPlanContactIdList(String id) {
		return planDao.getPlanContactIdList(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchPlansByStatusList(java.util.List)
	 */
	@Override
	public List<Plan> searchPlansByStatusList(List<String> notInStatusList,int start,int limit) {
		return planDao.searchPlansByStatusList(notInStatusList,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#updatePlanStatusForTimer(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@Override
	public boolean updatePlanStatus(Plan plan) {
		return planDao.updatePlanStatusForTimer(plan);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getMemberList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CustomerVo> getMemberList(String id, String type) {
		return planDao.getMaintainMemberList(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#queryDeliverTop50Customer(java.lang.String)
	 */
	@Override
	public List<NeedMaintainCustomer> queryDeliverTop50Customer(String queryDate) {
		return planDao.queryDeliverTop50Customer(queryDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#queryReceiveTop50Customer(java.lang.String)
	 */
	@Override
	public List<NeedMaintainCustomer> queryReceiveTop50Customer(String queryDate) {
		return planDao.queryReceiveTop50Customer(queryDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#queryLostCustomer(java.lang.String)
	 */
	@Override
	public List<NeedMaintainCustomer> queryLostCustomer(String queryDate) {
		return planDao.queryLostCustomer(queryDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#queryDelayCustomer(java.lang.String)
	 */
	@Override
	public List<NeedMaintainCustomer> queryDelayCustomer(String queryDate) {
		return planDao.queryDelayCustomer(queryDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#queryReduceCustomer(java.util.Date)
	 */
	@Override
	public List<ReduceCustomer> queryReduceCustomer(Date date) {
		return planDao.queryReduceCustomer(date);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#queryExistedCustomerInPlan(java.util.Map)
	 */
	@Override
	public List<String> queryExistedCustomerInPlan(Map<String, Object> map) {
		return planDao.queryExistedCustomerInPlan(map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getUserByEmpId(java.lang.String)
	 */
	@Override
	public User getUserByEmpId(String id) {
		return planDao.getUserByEmpId(id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchPlan4Job()
	 */
	@Override
	public List<ToDoPojo> searchPlan4Job() {
		return planDao.searchPlan4Job();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#getPlanCreatorList(com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition)
	 */
	@Override
	public List<Employee> getPlanCreatorList(Map<String, Object> map) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchSchedulesByPlanNameDim(java.lang.String)
	 */
	@Override
	public List<String> searchSchedulesByPlanNameDim(PlanDevelopCondition condition) {
		return planDao.searchSchedulesByPlanNameDim(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#createMonthlyPlanByJob()
	 */
	@Override
	public void createMonthlyPlanByJob() {
		planDao.createMonthlyPlanByJob();
	}
	//2012-10-31  根据名称 模糊查询 授权部门
	@Override
	public List<Department> searchExecuteDep(Set<String> depIds, String deptName,int start,
			int limit) {
		List<Department> list = planDao.searchExecuteDep(depIds,deptName,start,limit);
		return list;
	}

	@Override
	public String searchExecuteDepCount(Set<String> depIds, String deptName) {
		String totalCount= planDao.searchExecuteDepCount(depIds,deptName);
		return totalCount;
	}
	/**
	 * <p>
	 * Description:  通过计划执行人查找员工<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-19
	 * @param execuserId
	 * @return Employee
	 */
	@Override
	public Employee searchEmployeeByCode(String execuserId) {
		return planDao.searchEmployeeByCode(execuserId);
	}

	/** 
	 * <p>
	 * 开发计划新增——潜客<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<PotentialCustomer>
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchPotentialCustomerList(com.deppon.crm.module.customer.shared.domain.PotentialCustomerCondition, int, int)
	 */
//	@Override
//	public List<PotentialScatterVo> searchPotentialCustomerList(
//			PotentialCustomerCondition pcCondition, int start, int limit) {
//		return planDao.searchPotentialCustomerList(pcCondition,start,limit);
//	}

	/** 
	 * <p>
	 * 查询总数 --开发计划新增，潜客查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @return
	 * int
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#countPotentialCustomer(com.deppon.crm.module.customer.shared.domain.PotentialCustomerCondition)
	 */
//	@Override
//	public int countPotentialCustomer(PotentialCustomerCondition pcCondition) {
//		return planDao.countPotentialCustomer(pcCondition);
//	}

	/**
	 * <p>
	 * 开发计划新增散客查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param scCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<PotentialScatterVo>
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#searchScatterCustomer(com.deppon.crm.module.customer.shared.domain.ScatterCustomerCondition, int, int)
	 */
//	@Override
//	public List<PotentialScatterVo> searchScatterCustomer(
//			ScatterCustomerCondition scCondition, int start, int limit) {
//		return planDao.searchScatterCustomer(scCondition,start,limit);
//	}

	/**
	 * <p>
	 * 开发计划新增散客查询——统计总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @return
	 * int
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#countScatterCustomer(com.deppon.crm.module.customer.shared.domain.PotentialCustomerCondition)
	 */
//	@Override
//	public int countScatterCustomer(ScatterCustomerCondition pcCondition) {
//		return planDao.countScatterCustomer(pcCondition);
//	}
	/**
	 * <p>
	 * Description:根据部门ID 用户编码 查询该部门的下级部门<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-11-30
	 * @param empCode,deptId
	 * @return List<String>
	 */
	@Override
	public List<String> searchAuthBussinessDeptByParentId(String deptId,String empCode){
		Map<String, String> condition = new HashMap<String,String>();
		condition.put("empCode", empCode);
		condition.put("deptId", deptId);
		return planDao.searchAuthBussinessDeptByParentId(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IPlanService#countForsearchPlansByStatusList(java.util.List)
	 */
	@Override
	public int countForsearchPlansByStatusList(List<String> notInStatusList) {
		return planDao.countForsearchPlansByStatusList(notInStatusList);
	}
}
