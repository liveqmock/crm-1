package com.deppon.crm.module.marketing.server.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition;

/**   
 * <p>
 * 客户开发Manager接口<br />
 * </p>
 * @title IDevelopPlanManager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-3-23
 */

public interface IPlanManager {
	String updatePlan(Plan plan, String[] custList,String[] contactIds, User user,String bisYd);
	/**
	 * 
	 * <p>
	 * 创建客户开发计划<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-23
	 * @param developPlan 开发计划
	 * @param customerList 客户列表
	 * @return
	 * boolean
	 */
	String createPlan(Plan developPlan,String[] custList,String[] contactIds, User user);
	
	/**
	 * 
	 * <p>
	 * 根据用户Id查询本部门历史开发计划列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param userId 用户Id
	 * @return
	 * List<Plan>
	 */
	List<Plan> searchDepartmentPlanList(Plan plan,String planType, User user);
	
	/**
	 * 
	 * <p>
	 * Description:根据主题关键字模糊匹配对应的plan<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Mar 26, 2012
	 * @param theme
	 * @return
	 * List<Plan>
	 */
	List<Plan> searchDepartmentDevelopPlanList(String theme, String deptId, String planType);
	List<Plan> searchDepartmentDevelopPlanList(String deptId, String planType);
	
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
	 * HashMap
	 * 	planlist : 计划结果
	 *  plancount：计划总数
	 */
	HashMap<String, Object> searchPlan(PlanDevelopCondition condition,int start, int limit, User user) ;
	
	/**
	 * <p>
	 * Description:开发计划删除<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-31
	 * @param id
	 * @return
	 * boolean
	 */
	boolean deletePlan(String[] id, User user);
	
	/**
	 * <p>
	 * Description:开发计划详情查询<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-31
	 * @param id
	 * @return
	 * HashMap<String, Object> 
	 * 	plandetail : 计划明细
	 *  custlist：	 计划关联客户
	 */
	HashMap<String, Object>  searchPlanDetail(String id, String plantype);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-3-31
	 * @param planId
	 * @return
	 * List<String>
	 */
	List<String> searchDevelopPlan(String planId, User user);
	/**
	 * <p>
	 * 根据Id查询开发计划信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param id
	 * @return
	 * Plan
	 */
	Plan getPlanById(String id);

	/**
	 * 
	 * <p>
	 * 根据计划Id查询封装客户的VO<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-31
	 * @param planId
	 * @return
	 * PlanMaintainVo
	 */
//	List<ScatterCustomer> getCustomerbyPlanId(String planId);
	List<CustomerVo> getMemberbyPlanId(String planId);
	
	/**
	 * 
	 * <p>
	 * 更新与新增<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param plan 封装的计划
	 * @param custIds 客户id数组
	 * @param contactIds 联系人id数组
	 * @return
	 * boolean
	 */
	String savePlan(Plan plan,String[] custIds,String[] contactIds, User user,String bisYd);
	
	/**
	 * 
	 * <p>
	 * 会员维护计划查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-9
	 * @param vo
	 * @return
	 * Map<String, Object>
	 */
	Map<String, Object> searchMemberList(CustomerVo vo,int start ,int limit, User user);
	
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
	List<Plan> searchPlansByStatusList(List<String> notInStatusList, int start, int limit);
	
	/**
	 * 
	 * <p>
	 * Description:开发状态定时器，检索并处理需要更新的Plan和Schedule<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 10, 2012
	 * @param plans
	 * @param date
	 * @return
	 * List<Plan>
	 */
	List<Plan> processPlansStatusByDate(List<Plan> plans, Date date);

	/**
	 * 
	 * <p>
	 * Description:开发状态定时器，<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 10, 2012
	 * @param plans
	 * @return
	 * boolean
	 */
	boolean updatePlanList(List<Plan> plans);
	

	/**
	 * 
	 * <p>
	 * Description:客户维护定时器，每月自动创建维护计划- 取客户编号列表<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 17, 2012
	 * @return customerNumber列表
	 * List<String>
	 */
//	List<String> getAutoMonthlyMaintainPlanCustomerList(Date queryDate);

	/**
	 * 
	 * <p>
	 * Description:客户维护定时器，每月自动创建维护计划 - 创建计划<br />
	 * </p>
	 * @author zhujunyong
	 * @version 0.1 Apr 18, 2012
	 * @param customerNumbers
	 * @return
	 * boolean
	 */
//	boolean createNewAutoMonthlyMaintainPlan(List<String> customerNumbers);	
	
	
	
	Map<String, Object> searchCustomerList4Plan(SearchCustomerCondition condition, int start,int limit, User user);

	/**
	 * <p>
	 * Description: 统计每天计划数量（未执行、执行中）<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-5-28
	 * @return
	 * boolean
	 */
	boolean processPlanTODO();
	
	/**
	 * 
	 * <p>
	 * 根据计划名次模糊查询日程ids<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-7-24
	 * @param condition
	 * @return
	 * List<String>
	 */
	List<String> searchSchedulesByPlanNameDim(PlanDevelopCondition condition);

	/**
	 * <p>
	 * Description: 创建固定月计划-Job<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-10-29
	 * void
	 */
	void createMonthlyPlanByJob();
	Map<String,Object> getExecuteDep(Set<String> depIds, String deptName, int start,
			int limit);
	/**
	 * <p>
	 * Description:  校验计划执行人是否已经异动 或 离职-- 修改计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-20
	 * @param planId
	 * @return Map<String,Object>
	 */
	 Map<String,Object> isOutOrLeave(String planId);
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
	 * 
	 * <p>
	 * 拼接快递权限部门<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-1-11
	 * @param dep
	 * @param user
	 * @return
	 * String[]
	 */
	String[] makeUpExpressExecuteDeptids(String dep, User user);
	/**
	 * 
	 * <p>
	 * 拼接快递所有权限部门<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-1-11
	 * @param dep
	 * @param user
	 * @return
	 * String[]
	 */
	String[] makeUpAllExpressExecuteDeptids(String dep, User user,
			String position);
	/**
	 * 
	 * <p>
	 * 拼接所有零担权限部门<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-1-11
	 * @param dep
	 * @param user
	 * @return
	 * String[]
	 */
	String[] makeUpAllTLTExecuteDeptids(String dep, User user);
	/**
	 * 
	 * <p>
	 * 拼接零担权限部门<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014-1-11
	 * @param dep
	 * @param user
	 * @return
	 * String[]
	 */
	String[] makeUpTLTExecuteDeptids(String dep, User user);
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
	int countForsearchPlansByStatusList(List<String> notInStatusList);
}
