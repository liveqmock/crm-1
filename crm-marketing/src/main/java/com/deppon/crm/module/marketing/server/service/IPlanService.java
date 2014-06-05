/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IDevelopPlanService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author Administrator
 * @version 0.1 2012-3-23
 */
package com.deppon.crm.module.marketing.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.shared.domain.NeedMaintainCustomer;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;

/**   
 * <p>
 * 客户开发计划Service接口<br />
 * </p>
 * @title IDevelopPlanService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author 苏玉军
 * @version 0.1 2012-3-23
 */

public interface IPlanService {
	/**
	 * 
	 * <p>
	 * 查询该部门的历史开发计划列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @return
	 * List<Plan>
	 */
	public List<Plan> searchDepartmentPlanList(String execDeptId, String topic, String planType);

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
	 */
	public String createPlan(Plan plan);
	
	/**
	 * <p>
	 * Description:开发计划查询<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-30
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 * List<PlanList>
	 */
	public List<PlanList> searchPlan(PlanDevelopCondition condition,int start, int limit) ;
	
	/**
	 * <p>
	 * Description: 查询记录总数<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-6
	 * @param condition
	 * @return
	 * int
	 */
	public int getPlanCount(PlanDevelopCondition condition);

	/**
	 * 开发计划删除
	 * @param id
	 * @return
	 */
	public boolean deletePlan(String[] id);
	
	/**
	 * 开发计划详情查询
	 * @param id
	 * @return
	 */
	public PlanList searchPlanDetail(String id);
	
	/**
	 * <p>
	 * 更新计划<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param plan
	 * void
	 */
	public boolean updatePlan(Plan plan);
	
	/**
	 * @param planId
	 * @return
	 * List<PotentialCustomer>
	 *//*
	public List<PotentialCustomer> getPlanDetail(String planId);*/
	
	/**
	 * 
	 * <p>
	 * Description:通过planId查询对应的scheduleId列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param planId
	 * @return
	 * List<String>
	 */
	public List<String> searchScheduleIdByPlanId(String planId);

	/**
	 * 
	 * <p>
	 * Description:回访录入查询-开发主题下拉列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param theme
	 * @return
	 * List<Plan>
	 */
	public List<Plan> searchPlanByTheme(String theme, String deptId, String planType);
	/**
	 * <p>
	 * @version 0.1 2012-3-26
	 * @param deptId
	 * @return
	 * List<Plan>
	 */
	//List<Plan> searchDepartmentPlanList(String deptId);
	/**
	 * <p>
	 * 根据Id查询计划<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param id
	 * @return
	 * Plan
	 */
	public Plan getPlanById(String id);
	/**
	 * <p>
	 *  根据Id查询关联客户<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-29
	 * @param planId
	 * @return
	 * List<String>
	 */
	List<String> getPlanCustIdList(String planId);

	/**
	 * <p>
	 * 维护计划新增查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-9
	 * @param vo
	 * @return
	 * List<CustomerVo>
	 */
	public List<CustomerVo> searchMemberList(CustomerVo vo,int start,int limit);
	public int searchMemberCount(CustomerVo vo);

	/**
	 * <p>
	 * 根据planId查询对应的联系人id集合<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-10
	 * @param id
	 * @return
	 * List<String>
	 */
	public List<String> getPlanContactIdList(String id);

	/**
	 * 
	 * <p>
	 * Description:开发状态定时器，查找非已完成和已过期的计划<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 10, 2012
	 * @param notInStatusList
	 * @param limit 
	 * @param start 
	 * @return
	 * List<Plan>
	 */
	public List<Plan> searchPlansByStatusList(List<String> notInStatusList, int start, int limit);
	
	/**
	 * 
	 * <p>
	 * Description:开发状态定时器，更新计划状态<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 10, 2012
	 * @param plan
	 * @return
	 * boolean
	 */
	public boolean updatePlanStatus(Plan plan);
	
	public List<CustomerVo> getMemberList(String id, String type);
	
	/**
	 * 
	 * <p>
	 * Description:客户维护每月定时计划，发货前50<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 17, 2012
	 * @param queryDate
	 * @return
	 * List<NeedMaintainCustomer>
	 */
	public List<NeedMaintainCustomer> queryDeliverTop50Customer(String queryDate);
	
	/**
	 * 
	 * <p>
	 * Description:客户维护每月定时计划，到货前50<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 17, 2012
	 * @param queryDate
	 * @return
	 * List<NeedMaintainCustomer>
	 */
	public List<NeedMaintainCustomer> queryReceiveTop50Customer(String queryDate);

	/**
	 * 
	 * <p>
	 * Description:客户维护每月定时计划，流失客户<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 17, 2012
	 * @param queryDate
	 * @return
	 * List<NeedMaintainCustomer>
	 */
	public List<NeedMaintainCustomer> queryLostCustomer(String queryDate);	

	/**
	 * 
	 * <p>
	 * Description:客户维护每日定时计划，发货频率超期客户<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 18, 2012
	 * @param queryDate
	 * @return
	 * List<NeedMaintainCustomer>
	 */
	public List<NeedMaintainCustomer> queryDelayCustomer(String queryDate);

	/**
	 * 
	 * <p>
	 * Description:客户维护每日定时计划，发货量减少客户，以传入的日期参数为基准，查询本月相比上月环比发货量减少的客户<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 18, 2012
	 * @param date 
	 * @return
	 * List<ReduceCustomer>
	 */
	public List<ReduceCustomer> queryReduceCustomer(Date date);

	/**
	 * 
	 * <p>
	 * Description:客户维护每日定时计划，排除已经安排进每日计划的客户<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 19, 2012
	 * @param map
	 * @return
	 * List<String>
	 */
	public List<String> queryExistedCustomerInPlan(Map<String, Object> map);
	
	/**
	 * <p>
	 * Description: 根据EmpID 获取User 对象<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-28
	 * @param id
	 * @return
	 * User
	 */
	public User getUserByEmpId(String id);
	
	/**
	 * <p>
	 * Description: 检索未完成/未过期的计划 <br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-29
	 * @param status
	 * @return
	 * List<ToDoPojo>
	 */
	public List<ToDoPojo> searchPlan4Job();
	
	/**
	 * <p>
	 * Description: 根据条件查询计划创建者<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-7-3
	 * @param map
	 * @return
	 * List<Employee>
	 */
	public List<Employee> getPlanCreatorList(Map<String, Object> map);

	/**
	 * <p>
	 * 根据计划名次模糊查询日程ids<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-24
	 * @param dimPlanName
	 * @return
	 * List<String>
	 */
	public List<String> searchSchedulesByPlanNameDim(PlanDevelopCondition condition);
	
	/**
	 * <p>
	 * Description: 创建固定月计划-Job<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-10-29
	 * void
	 */
	public void createMonthlyPlanByJob();

	public List<Department> searchExecuteDep(Set<String> depIds, String deptName,int start,
			int limit);

	public String searchExecuteDepCount(Set<String> depIds, String deptName);
	/**
	 * <p>
	 * Description:  通过计划执行人查找员工<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-19
	 * @param execuserId
	 * @return Employee
	 */
	public Employee searchEmployeeByCode(String execuserid);

	/**
	 * <p>
	 * 开发计划新增——潜客查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<PotentialCustomer>
	 */
//	public List<PotentialScatterVo> searchPotentialCustomerList(
//			PotentialCustomerCondition pcCondition, int start, int limit);

	/**
	 * <p>
	 * 查询总数 --开发计划新增，潜客查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @return
	 * int
	 */
//	public int countPotentialCustomer(PotentialCustomerCondition pcCondition);

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
	 */
//	public List<PotentialScatterVo> searchScatterCustomer(
//			ScatterCustomerCondition scCondition, int start, int limit);

	/**
	 * <p>
	 * 开发计划新增散客查询——统计总数<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @return
	 * int
	 */
//	public int countScatterCustomer(ScatterCustomerCondition pcCondition);
	/**
	 * <p>
	 * Description:根据部门ID 用户编码 查询该部门的下级部门<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-11-30
	 * @param empCode,deptId
	 * @return List<String>
	 */
	List<String> searchAuthBussinessDeptByParentId(String deptId,String empCode);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年5月20日
	 * @param notInStatusList
	 * @return
	 * int
	 */
	public int countForsearchPlansByStatusList(List<String> notInStatusList);
}
