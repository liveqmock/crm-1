/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DevelopPlanDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author Administrator
 * @version 0.1 2012-3-23
 */
package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.dao.IPlanDao;
import com.deppon.crm.module.marketing.shared.domain.CustomerList;
import com.deppon.crm.module.marketing.shared.domain.NeedMaintainCustomer;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.ReduceCustomer;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * 开发计划DAO实现类<br />
 * </p>
 * @title DevelopPlanDao.java
 * @package com.deppon.crm.module.marketing.server.dao.impl 
 * @author 苏玉军
 * @version 0.1 2012-3-23
 */

public class PlanDao extends iBatis3DaoImpl implements IPlanDao {
	
	private static final String NAMESPACE_PLAN="com.deppon.crm.module.marketing.shared.domain.Plan.";
	//新增开发计划
	private static final String INSERT_PLAN="insertPlan";
	//查询开发计划列表
	private static final String QUERY_DEVELOPLIST="queryDevelopList";

	// 查询开发计划
	private static final String PLANDEVELOP_SEARCH = "searchPlanDevelop";
	private static final String PLANDEVELOP_LIST_COUNT = "getPlanListCount";
	
	
	// 查询开发计划
	private static final String PLANDEVELOPDETAIL_SEARCH = "searchPlanDevelopDetail";
	
	//删除开发计划
	private static final String PLANDEVELOP_DELETE = "deletePlanDevelop";

	//查询计划对应的日程id列表
	private static final String QUERY_SCHEDULE_IDS = "selectScheduleIds";
	//更新开发计划
	private static final String PLANDEVELOP_UPDATE ="updatePlan";
	private static final String QUERY_PLAN_THEME = "getPlansByTheme";
	//根据Id查询开发计划
	private static final String QUERY_PLAN_BYID="getPlanById";
	//根据开发计划Id查询开发客户
	private static final String QUERY_PLAN_CUSTIDLIST="queryPlanCustIdList";
	//查询会员信息
	private static final String QUERYMEMBER="queryMember";
	private static final String QUERYMEMBER_COUNT="queryMemberCount";
	//查询联系人ids
	private static final String QUERYCONTACTS="queryContactIds";
	
	//开发状态定时器，查找非已完成和已过期的计划
	private static final String SEARCHPLANSBYSTATUSLIST = "searchPlansByStatusList";
	//开发状态定时器，更新状态
	private static final String UPDATEPLANSTATUSFORTIMER = "updatePlanStatusForTimer";

	private static final String QUERY_DEVELOP_CUSTOMER		= "getDevelopCustomerList";
	
	private static final String QUERY_MAINTAIN_CUSTOMER	= "getMaintainMemberList";
	// 发货金额前50名
	private static final String QUERYDELIVERTOP50CUSTOMER = "queryDeliverTop50Customer";
	// 到货金额前50名
	private static final String QUERYRECEIVETOP50CUSTOMER = "queryReceiveTop50Customer";
	// 查询流失客户
	private static final String QUERYLOSTCUSTOMER = "queryLostCustomer";
	// 查询发货频率超期客户
	private static final String QUERYDELAYCUSTOMER = "queryDelayCustomer";
	// 查询发货量减少客户
	private static final String QUERYREDUCECUSTOMER = "queryReduceCustomer";
	// 查询已经安排进固定计划的客户ID
	private static final String QUERYEXISTEDCUSTOMERINPLAN = "queryExistedCustomerInPlan";
	//根据计划名次模糊查询日程IDS
	private static final String QUERY_SCHEDULEIDS_BYDIM =  "searchSchedulesByPlanNameDim";
	//查询潜客信息——开发计划新增
	private static final String SEARCH_POTENTIAL_CUSTOMER_LIST = "searchPotentialCustomerList";
	//查询潜客信息——开发计划新增——总数
	private static final String COUNT_SEARCH_POTENTIAL_CUSTOMER_LIST = "countSearchPotentialCustomerList";
	//查询散客信息——开发计划新增
	private static final String SEARCH_SCATTER_CUSTOMER="searchScatterCustomer";
	//查询散客信息——开发计划新增——查询总数
	private static final String COUNT_SEARCH_SCATTER_CUSTOMER="countSearchScatterCustomer";
	//根据部门ID和客户编码查询该部门下属的营业部
	private static final String SEARCHAUTHBUSSINESSDEPTBYPARENTID="searchAuthBussinessDeptByParentId";
	/**
	 * 
	 * <p>
	 * 查询该部门的历史开发计划列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @return
	 * List<Plan>
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchDepartmentPlanList(java.lang.String)
	 */
	@Override
	public List<Plan> searchDepartmentPlanList(String execDeptId, String topic, String planType) {
		Map<String,String > map=new HashMap<String,String>();
		map.put("execDeptId", execDeptId);
		map.put("planType", planType);
		if (StringUtils.isNotEmpty(topic)){
			map.put("topic", "%"+topic+"%");
		}
		return this.getSqlSession().selectList(NAMESPACE_PLAN+QUERY_DEVELOPLIST,map);
	}

	/**
	 * 
	 * <p>
	 * 创建开发计划<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param plan
	 * @param customerList
	 * @return
	 * boolean
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#createPlan(com.deppon.crm.module.marketing.shared.domain.Plan, java.util.List)
	 */
	@Override
	public String createPlan(Plan plan) {
		this.getSqlSession().insert(NAMESPACE_PLAN+INSERT_PLAN, plan);
		return plan.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanList> searchPlan(PlanDevelopCondition condition,int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		return (List<PlanList> )this.getSqlSession().selectList(NAMESPACE_PLAN + PLANDEVELOP_SEARCH
				, condition, rowBounds);	
	}

	@Override
	public boolean deletePlanDevelop(String[] id) {
		return this.getSqlSession().update(NAMESPACE_PLAN + PLANDEVELOP_DELETE, id) > 0;
	}

	/**
	 * <p>
	 * 插入日程<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param sche
	 * @return
	 * boolean
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#createSchedule(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 *//*
	@Override
	public boolean createSchedule(Schedule sche) {
		return this.getSqlSession().insert(NAMESPACE_SCHEDULE+SCHEDULE_INSERT,sche)>0? true:false;
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<String> searchScheduleIdByPlanId(String planId) {
		return this.getSqlSession().selectList(NAMESPACE_PLAN + QUERY_SCHEDULE_IDS, planId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> searchPlanByTheme(String theme, String deptId, String planType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("theme", theme);
		map.put("deptId", deptId);
		map.put("type", planType);
		return this.getSqlSession().selectList(NAMESPACE_PLAN + QUERY_PLAN_THEME, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#updatePlan(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@Override
	public boolean updatePlan(Plan plan) {
		return this.getSqlSession().update(NAMESPACE_PLAN+PLANDEVELOP_UPDATE, plan)>0;
	}

	@Override
	public PlanList searchPlanDetail(String id) {		
		return (PlanList) this.getSqlSession().selectOne(NAMESPACE_PLAN
				+PLANDEVELOPDETAIL_SEARCH,id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchPlanById(java.lang.String)
	 */
	@Override
	public Plan searchPlanById(String id) {
		return (Plan) this.getSqlSession().selectOne(NAMESPACE_PLAN+QUERY_PLAN_BYID, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryPlanCustIdList(java.lang.String)
	 */
	@Override
	public List<String> queryPlanCustIdList(String id) {
		return this.getSqlSession().selectList(NAMESPACE_PLAN+QUERY_PLAN_CUSTIDLIST, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#getPlanCount(com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition)
	 */
	@Override
	public int getPlanCount(PlanDevelopCondition condition) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_PLAN+PLANDEVELOP_LIST_COUNT, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchMemberList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public List<CustomerVo> searchMemberList(CustomerVo vo,int start,int limit) {
		RowBounds rb=new RowBounds(start, limit);
		return (List<CustomerVo>)this.getSqlSession().selectList(NAMESPACE_PLAN+QUERYMEMBER, vo, rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#getPlanContactIdList(java.lang.String)
	 */
	@Override
	public List<String> getPlanContactIdList(String id) {
		return this.getSqlSession().selectList(NAMESPACE_PLAN+QUERYCONTACTS, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchPlansByStatusList(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> searchPlansByStatusList(List<String> notInStatusList,int start,int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return (List<Plan>) getSqlSession().selectList(NAMESPACE_PLAN + SEARCHPLANSBYSTATUSLIST, notInStatusList,rb);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#updatePlanStatusForTimer(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@Override
	public boolean updatePlanStatusForTimer(Plan plan) {
		return getSqlSession().update(NAMESPACE_PLAN + UPDATEPLANSTATUSFORTIMER, plan) > 0;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#getDevelopCustomerList(java.lang.String)
	 */
	@Override
	public List<CustomerList> getDevelopCustomerList(String id) {
		return this.getSqlSession().selectList(NAMESPACE_PLAN + QUERY_DEVELOP_CUSTOMER, id);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#getMaintainMemberList(java.lang.String)
	 */
	@Override
	public List<CustomerVo> getMaintainMemberList(String id) {
		return this.getSqlSession().selectList(NAMESPACE_PLAN + QUERY_MAINTAIN_CUSTOMER, id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryDeliverTop50Customer(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NeedMaintainCustomer> queryDeliverTop50Customer(String queryDate) {
		return getSqlSession().selectList(NAMESPACE_PLAN + QUERYDELIVERTOP50CUSTOMER, queryDate); 
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryReceiveTop50Customer(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NeedMaintainCustomer> queryReceiveTop50Customer(String queryDate) {
		return getSqlSession().selectList(NAMESPACE_PLAN + QUERYRECEIVETOP50CUSTOMER, queryDate); 
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryLostCustomer(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NeedMaintainCustomer> queryLostCustomer(String queryDate) {
		return getSqlSession().selectList(NAMESPACE_PLAN + QUERYLOSTCUSTOMER, queryDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryDelayCustomerList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NeedMaintainCustomer> queryDelayCustomer(String queryDate) {
		return getSqlSession().selectList(NAMESPACE_PLAN + QUERYDELAYCUSTOMER, queryDate);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryReduceCustomer(java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReduceCustomer> queryReduceCustomer(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		cal.add(Calendar.MONTH, -1);
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH) + 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		map.put("year1", year1);
		map.put("month1", month1);
		return getSqlSession().selectList(NAMESPACE_PLAN + QUERYREDUCECUSTOMER, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#queryExistedCustomerInPlan(java.util.Date, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryExistedCustomerInPlan(Map<String, Object> map) {
		return getSqlSession().selectList(NAMESPACE_PLAN + QUERYEXISTEDCUSTOMERINPLAN, map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchMemberCount(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public int searchMemberCount(CustomerVo vo) {		
		return (Integer) getSqlSession().selectOne(NAMESPACE_PLAN + QUERYMEMBER_COUNT, vo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#getUserByEmpId(java.lang.String)
	 */
	@Override
	public User getUserByEmpId(String id) {
		return (User) getSqlSession().selectOne(NAMESPACE_PLAN+"getUserByEmpId", id);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchPlan4Job()
	 */
	@Override
	public List<ToDoPojo> searchPlan4Job() {		
		return getSqlSession().selectList(NAMESPACE_PLAN+"searchPlanByStatus4Job");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#getPlanCreatorList(com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition)
	 */
	@Override
	public List<Employee> getPlanCreatorList(Map<String, Object> map) {
		return getSqlSession().selectList(NAMESPACE_PLAN+"getPlanCreatorList", map);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchSchedulesByPlanNameDim(java.lang.String)
	 */
	@Override
	public List<String> searchSchedulesByPlanNameDim(PlanDevelopCondition condition) {
		return this.getSqlSession().selectList(NAMESPACE_PLAN+QUERY_SCHEDULEIDS_BYDIM, condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#createMonthlyPlanByJob()
	 */
	@Override
	public void createMonthlyPlanByJob() {
		Map<String, Object> param = new HashMap<String, Object>();
		this.getSqlSession().selectOne(NAMESPACE_PLAN + "createMonthlyPlanByJob", param);
	}

	public Set<Set> getExecuteDep(Set<String> deptIds){
		int splitNum = 900; // 一组 900 个
		int inArrayNum = deptIds.size() % splitNum == 0 ? deptIds.size()
				/ splitNum : deptIds.size() / splitNum + 1;// 参数值一共能分割成多少组，记录总组数
		Set<Set>  deptIdsSet=new HashSet<Set>();
	    String[] obj = new String[]{};
	    Iterator<String> deptIdsIterator = deptIds.iterator();
	    int num=0;
	    Set<String> set=new HashSet<String>();
	    while(deptIdsIterator.hasNext()){
	    	set.add(deptIdsIterator.next());
	    	num++;
	    	if(num==splitNum){
	    		deptIdsSet.add(set);
	    		num=0;set=new HashSet<String>();
	    		continue;
	    	}else if(!deptIdsIterator.hasNext()){	deptIdsSet.add(set);}
	    	
	     }
		return deptIdsSet;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> searchExecuteDep(Set<String> deptIds,
			String deptName, int start, int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		RowBounds boud=new RowBounds(start, limit);
		Set<Set> deptIdsSet=getExecuteDep(deptIds);
	    map.put("deptIdsSet", deptIdsSet);
		map.put("deptName", deptName);
		return this.getSqlSession().selectList(NAMESPACE_PLAN + "searchExecuteDep",map,boud);
	}

	@Override
	public String searchExecuteDepCount(Set<String> deptIds, String deptName) {
		Map<String,Object> map = new HashMap<String,Object>();
		Set<Set> deptIdsSet=getExecuteDep(deptIds);
		map.put("deptIdsSet", deptIdsSet);
		map.put("deptName", deptName);
		return (String) this.getSqlSession().selectOne(NAMESPACE_PLAN + "searchExecuteDepCount",map);
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
		return (Employee) this.getSqlSession().selectOne(NAMESPACE_PLAN + "searchEmployeeByCode",execuserId);
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<PotentialCustomer>
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchPotentialCustomerList(com.deppon.crm.module.customer.shared.domain.PotentialCustomerCondition, int, int)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PotentialScatterVo> searchPotentialCustomerList(
//			PotentialCustomerCondition pcCondition, int start, int limit) {
//		RowBounds rowBounds = new RowBounds(start, limit); 
//		return this.getSqlSession().selectList(NAMESPACE_PLAN + SEARCH_POTENTIAL_CUSTOMER_LIST, pcCondition, rowBounds);
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
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#countPotentialCustomer(com.deppon.crm.module.customer.shared.domain.PotentialCustomerCondition)
	 */
//	@Override
//	public int countPotentialCustomer(PotentialCustomerCondition pcCondition) {
//		return (Integer) this.getSqlSession().selectOne(NAMESPACE_PLAN + COUNT_SEARCH_POTENTIAL_CUSTOMER_LIST, pcCondition);
//	}

	/**
	 * <p>
	 * 开发计划新增，散客查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param scCondition
	 * @param start
	 * @param limit
	 * @return
	 * List<PotentialScatterVo>
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#searchScatterCustomer(com.deppon.crm.module.customer.shared.domain.ScatterCustomerCondition, int, int)
	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PotentialScatterVo> searchScatterCustomer(
//			ScatterCustomerCondition scCondition, int start, int limit) {
//		RowBounds rowBounds = new RowBounds(start, limit); 
//		return this.getSqlSession().selectList(NAMESPACE_PLAN + SEARCH_SCATTER_CUSTOMER, scCondition, rowBounds);
//	}

	/**
	 * <p>
	 * 开发计划新增，散客总数查询<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-14
	 * @param pcCondition
	 * @return
	 * int
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#countScatterCustomer(com.deppon.crm.module.customer.shared.domain.PotentialCustomerCondition)
	 */
//	@Override
//	public int countScatterCustomer(ScatterCustomerCondition pcCondition) {
//		return (Integer) this.getSqlSession().selectOne(NAMESPACE_PLAN + COUNT_SEARCH_SCATTER_CUSTOMER, pcCondition);
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
	public List<String> searchAuthBussinessDeptByParentId(Map<String,String> condition){
		return (List<String>)this.getSqlSession().selectList(NAMESPACE_PLAN+SEARCHAUTHBUSSINESSDEPTBYPARENTID,condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.dao.IPlanDao#countForsearchPlansByStatusList(java.util.List)
	 */
	@Override
	public int countForsearchPlansByStatusList(List<String> notInStatusList) {
		return (Integer) this.getSqlSession().selectOne(NAMESPACE_PLAN + "countForsearchPlansByStatusList",notInStatusList);
	}
}
