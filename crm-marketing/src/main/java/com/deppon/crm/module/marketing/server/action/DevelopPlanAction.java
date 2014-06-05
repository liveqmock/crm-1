package com.deppon.crm.module.marketing.server.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IUserManager;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.PotentialScatterVo;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class DevelopPlanAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	//是否是监控
	private String isMonitor = "false";
	// 计划管理Manager
	private IPlanManager planManager;
	// 开发计划
	private Plan developPlan;
	// 客户列表
	private String[] custList;
	private String[] contactIds;
	// 开发计划客户信息
	private List<ScatterCustomer> listScatterCustomerInfo;
	// 查询条件Vo
	private CustomerVo customerVo;
	//客户信息列表
	private List<PotentialScatterVo> listCustomerInfo;
	//  开发计划Vo
	private PlanScheduleQueryCondition planMaintainVo;
	//计划
	private Plan plan;
	//计划id
	private String planId;
	// 起始页
	private int start;
	// 每页显示条数
	private int limit;
	//总条数
	private Long totalCount;
	// 开发计划查询参数
	private PlanDevelopCondition planCondition;
	//开发计划列表
	private List<PlanList> planList;
	// 开发计划id
	private String[] planeIds;
	// 计划执行人
	private List<Employee> planeDraftList;
	//计划制定者
	private String planeDraft;
	// 计划主题
	private List<Plan> planNameList;
	//执行部门
	private String executeDept;
	//问卷生效时间
	private Date validTime;
	//问卷时效时间
	private Date invalidTime;
	
	public void setContactIds(String[] contactIds) {
		this.contactIds = contactIds;
	}

	public String getExecuteDept() {
		return executeDept;
	}
	public void setExecuteDept(String executeDept) {
		this.executeDept = executeDept;
	}

	//部门名称
	private String deptName;
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	private String bisYd;
	public void setBisYd(String bisYd) {
		this.bisYd = bisYd;
	}
	public String getBisYd() {
		return bisYd;
	}

	//当前登录部门
	private String currentDept = null;
	//执行部门列表
	private List<Department> executeDeptList;
	private List<CustomerVo> listCustomer;
	public List<CustomerVo> getListCustomer() {
		return listCustomer;
	}
	public void setListCustomer(List<CustomerVo> listCustomer) {
		this.listCustomer = listCustomer;
	}

	// 用户信息 接口
	private IUserManager userManager;
	

	// 获取当前用户
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
	
	// 获取当前用户所属部门
	private String getCurrentUserDeptId(){
		return getCurrentUser().getEmpCode().getDeptId().getId();
	}

	/**
	 * 
	 * <p>
	 * 创建、更新计划
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
	@JSON
	public String savePlan() {
		planManager.savePlan(developPlan, custList, contactIds, this.getCurrentUser(),bisYd);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * 根据客户信息查询客户
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
//	@SuppressWarnings("unchecked")
//	@JSON
//	public String searchCustomerList() {
//		if(StringUtils.isBlank(customerVo.getDeptId())){
//			customerVo.setDeptId(getCurrentUserDeptId());
//		}
//		Map<String, Object> map = planManager.searchCustomerList(customerVo, start,
//				limit, this.getCurrentUser());		
//		listCustomerInfo = (List<PotentialScatterVo>) map.get("list");
//		totalCount = Long.valueOf( map.get("count").toString());
//		return SUCCESS;
//	}
	
	/**
	 * 查询所拥有的部门数量
	 * @author 张登
	 * @return
	 */
	@JSON
	public String searchDeptCount(){
		totalCount=Long.valueOf(this.getCurrentUser().getDeptids().size()); 
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 根据客户信息查询客户
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchMemberList() {
		if(StringUtils.isBlank(customerVo.getDeptId())){
			customerVo.setDeptId(getCurrentUserDeptId());
		}
		Map<String, Object> map = planManager.searchMemberList(customerVo, start,
				limit, this.getCurrentUser());		
		listCustomer = (List<CustomerVo>) map.get("list");
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}	
	
	/**
	 * 
	 * <p>
	 * 根据客户信息查询客户
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
	@JSON
	public String searchMemberListByPlanId() {
		listCustomer = planManager.getMemberbyPlanId(planId);
		return SUCCESS;
	}
	/**
	 * 打印计划
	 * @author 盛诗庆
	 * @version 0.1 2014-01-16
	 * @revision 新增
	 */
//	@SuppressWarnings("unchecked")
//	public String printPlan(){
//		if(StringUtils.isBlank(customerVo.getDeptId())){
//			customerVo.setDeptId(getCurrentUserDeptId());
//		}
//		Map<String, Object> map = planManager.searchCustomerList(customerVo, start,
//				limit, this.getCurrentUser());		
//		listCustomerInfo = (List<PotentialScatterVo>) map.get("list");
//		totalCount = Long.valueOf( map.get("count").toString());
//		
//		return SUCCESS;
//	}
	
	/**
	 * 
	 * <p>
	 * 修改显示计划
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
	@JSON
	public String searchPlanById() {
		plan = planManager.getPlanById(planId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 判断是否离职
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
	@JSON
	public String isOutOrLeave() {
		Map<String,Object> planMap=planManager.isOutOrLeave(planId);
		bisYd=(String)planMap.get("isOutOrLeave");
		plan=(Plan)planMap.get("plan");
		invalidTime = (Date) planMap.get("invalidTime");
		validTime = (Date) planMap.get("validTime");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 修改计划显示已选择用户
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 * @revision 盛诗庆 返回客户信息增加客户地址
	 */
//	@JSON
//	public String searchCustomerbyPlanId() {
//		listScatterCustomerInfo = planManager.getCustomerbyPlanId(planId);
//		return SUCCESS;
//	}

	/**
	 * Description:删除客户开发计划<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param
	 * @return
	 */
	@JSON
	public String deleteDevelopPlane() {
		//批量删除开发计划
		planManager.deletePlan(planeIds, this.getCurrentUser());
		return SUCCESS;
	}

//	/**
//	 * Description:查询计划制定者<br />
//	 * @author 毛建强
//	 * @version 0.1 2012-3-29
//	 * @param
//	 * @return
//	 */
//	@JSON
//	public String searchPlaneDraft() {
//		// 有部门时查询部门，无部门时查询默认授权部门
//		String[] depIds;
//		if (StringUtils.isEmpty(executeDept)) {
//			Set<String> deps = getCurrentUser().getDeptids();
//			depIds = new String[deps.size()];
//			deps.toArray(depIds);
//			depIds = new String[]{getCurrentUserDeptId()};
//		}else{
//			depIds = new String[]{executeDept};
//		}
//		
//		//查询计划制定者
//		planeDraftList = userManager.getAllEmployeesByDeptId(depIds);
//		return SUCCESS;
//	}

	/**
	 * 
	 * Description:查询部门<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-30
	 * @param
	 * @return
	 */
	@JSON
	public String searchExecuteDept() {
		executeDeptList = userManager.getAllAuthedDepts(this.getCurrentUser());
		return SUCCESS;
	}
	
	
	/**
	 * Description:查询计划制定者<br />
	 * @author zhangdeng
	 * @version 0.1 2012-3-29
	 * @param
	 * @return
	 */
	@JSON
	public String queryEmployeesByDeptId() {
		//有部门时查询部门，无部门时查询默认部门
		if (StringUtils.isEmpty(executeDept)) {
			executeDept = getCurrentUserDeptId();
		}
		//查询计划制定者
		planeDraftList = userManager.getEmployeesByDeptId(executeDept);
		return SUCCESS;
	}
	
	/**
	 * 
	 * Description:根据部门名称查询部门<br />
	 * @author zhangdeng
	 * @version 0.1 2012-3-30
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String queryDeptListByDeptName() {
		Map<String, Object> map = new HashMap<String, Object>();
		//修改开发计划监控的部门查询方法 auth:李春雨
		if("true".equals(isMonitor)){
			map=userManager.queryDeptListByDeptNameForMonitor(this.getCurrentUser(), deptName, start, limit);
		}else{
			map=userManager.queryDeptListByDeptName(this.getCurrentUser(), deptName, start, limit);
		}
		//获取列表
		executeDeptList = (List<Department>) map.get("executeDeplist");
		//获取分页总条数
		totalCount = Long.valueOf(map.get("totalcount").toString());
		return SUCCESS;
	}
//	
//	/**
//	 * Description:获取当前登录部门
//	 * @author 毛建强
//	 * @version 0.1 2012-5-16
//	 * @param 
//	 * @return
//	 */
//	public String searchCurrentDept(){
//		List<Department> deptList = userManager.getAllAuthedDepts(this.getCurrentUser());
//		if(deptList!=null && deptList.size()==1){
//			currentDept = getCurrentUserDeptId();
//		}
//		return SUCCESS;
//	}
	/**
	 * Description:计划管理主页面获取计划列表<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-31
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchDevelopPlane() {
		//获取权限部门列表
//		this.searchExecuteDept();
		HashMap<String, Object> map = new HashMap<String, Object>();
		//查询计划列表
		map = this.planManager.searchPlan(planCondition, start, limit, this.getCurrentUser());
		//获取列表
		planList = (List<PlanList>) map.get("planlist");
		//获取分页总条数
		totalCount = Long.valueOf( map.get("plancount").toString());
		return SUCCESS;
	}

	//get 和set方法 在下面
	public String[] getPlaneIds() {
		return planeIds;
	}
	public void setPlaneIds(String[] planeIds) {
		this.planeIds = planeIds;
	}
	public List<Employee> getPlaneDraftList() {
		return planeDraftList;
	}
	public void setPlaneDraftList(List<Employee> planeDraftList) {
		this.planeDraftList = planeDraftList;
	}
	public String getPlaneDraft() {
		return planeDraft;
	}
	public void setPlaneDraft(String planeDraft) {
		this.planeDraft = planeDraft;
	}
	public List<Plan> getPlanNameList() {
		return planNameList;
	}
	public void setPlanNameList(List<Plan> planNameList) {
		this.planNameList = planNameList;
	}
	public List<Department> getExecuteDeptList() {
		return executeDeptList;
	}
	public void setExecuteDeptList(List<Department> executeDeptList) {
		this.executeDeptList = executeDeptList;
	}
	public PlanDevelopCondition getPlanCondition() {
		return planCondition;
	}
	public void setPlanCondition(PlanDevelopCondition planCondition) {
		this.planCondition = planCondition;
	}
	public List<PlanList> getPlanList() {
		return planList;
	}
	public void setPlanList(List<PlanList> planList) {
		this.planList = planList;
	}
	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}
	public Plan getDevelopPlan() {
		return developPlan;
	}
	public void setDevelopPlan(Plan developPlan) {
		this.developPlan = developPlan;
	}
	public void setCustList(String[] custList) {
		this.custList = custList;
	}
	public List<ScatterCustomer> getListScatterCustomerInfo() {
		return listScatterCustomerInfo;
	}
	public void setListScatterCustomerInfo(
			List<ScatterCustomer> listScatterCustomerInfo) {
		this.listScatterCustomerInfo = listScatterCustomerInfo;
	}

	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	public PlanScheduleQueryCondition getPlanMaintainVo() {
		return planMaintainVo;
	}
	public void setPlanMaintainVo(PlanScheduleQueryCondition planMaintainVo) {
		this.planMaintainVo = planMaintainVo;
	}
	public List<PotentialScatterVo> getListCustomerInfo() {
		return listCustomerInfo;
	}
	public void setListCustomerInfo(List<PotentialScatterVo> listCustomerInfo) {
		this.listCustomerInfo = listCustomerInfo;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	public String getCurrentDept() {
		return currentDept;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-15
	 */
	public String getIsMonitor() {
		return isMonitor;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-1-15
	 * @param isMonitor the isMonitor to set
	 */
	public void setIsMonitor(String isMonitor) {
		this.isMonitor = isMonitor;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}


}
