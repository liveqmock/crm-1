package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class DevelopScheduleAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	//日程manager
	private IScheduleManager scheduleManager;
	// 计划管理Manager
	private IPlanManager planManager;
	//查询日程条件实体
	private PlanScheduleQueryCondition planScheduleQueryCondition;
	// 起始页
	private int start;
	// 每页显示条数
	private int limit;
	// 总记录数
	private Long totalCount;
	// 查询条件Vo
	private CustomerVo customerVo;
	//日程列表
	private List<ScheduleQueryResultObject> scheduleList;
	// 开发计划id
	private String[] planeIds;
	// 计划主题
	private List<Plan> planNameList;
	//查询日程列表
	private List<DevelopScheduleVO> scheduleVOList;
	//日程VO,对于查询界面
	private DevelopScheduleVO scheduleVO;
	//日程实体
	private Schedule schedule;
	// 类型
	private String planType;
	//计划维护日程
	private List<DevelopScheduleVO> planList;
	//当前所选的部门
	private String currentDeptId;
	
	/**
	 * @param currentDeptId the currentDeptId to set
	 */
	public void setCurrentDeptId(String currentDeptId) {
		this.currentDeptId = currentDeptId;
	}
	public List<DevelopScheduleVO> getPlanList() {
		return planList;
	}
	// 获取当前用户
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//潜散客信息
	private ScatterCustomer detailInfo;
	public ScatterCustomer getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(ScatterCustomer detailInfo) {
		this.detailInfo = detailInfo;
	}
	/**
	 * 
	 * <p>
	 * 修改计划
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 * @revision 盛诗庆 查询条件增加业务类型
	 */
	@JSON
	public String searchCustomerList() {
		scheduleList = scheduleManager.searchCustomerList(customerVo, start,
				limit,this.getCurrentUser());
		totalCount=Long.valueOf(scheduleManager.countSearchCustomerList(customerVo));
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * 修改、保存计划
	 * </p>
	 * 
	 * @author 张登
	 * @version 0.1 2012-3-28
	 */
	@JSON
	public String saveSchedule() {
		scheduleManager.saveSchedule(schedule,this.getCurrentUser());
		return SUCCESS;
	}
	/**
	 * Description:日程管理查询<br />
	 * @author 毛建强
	 * @version 0.1 2012-4-6
	 * @param
	 * @return
	 */
	@JSON
	public String searchDevelopSchedule() {
		//执行部门ID空
		if(StringUtils.isBlank(scheduleVO.getExecuteDeptId())){
			//设置当前用户ID
			scheduleVO.setExecuteDeptId(getCurrentUser().getEmpCode().getDeptId().getId());
		}
		//分页查询日程列表
		scheduleVOList = scheduleManager.searchSchedule(scheduleVO, start,
				limit,this.getCurrentUser());
		//查询总数
		totalCount = Long.valueOf(scheduleManager.countForSearchSchedule(scheduleVO));
		return SUCCESS;
	}
	
	/**
	 * Description:日程管理查询（商机）<br />
	 * @author 张斌
	 * @version 0.1 2014-3-20
	 * @param
	 * @return
	 */
	@JSON
	public String searchBusinessSchedule() {
		//分页查询日程列表
		scheduleVOList = scheduleManager.searchBusinessSchedule(scheduleVO, start,
				limit,this.getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 查询当前登录用户自己的日程信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-31
	 * @return
	 * String
	 */
	@SuppressWarnings("unchecked")
	public String searchScheduleBySelf(){
		//执行人
		String execuserid = getCurrentUser().getEmpCode().getId();
		//分页查询日程
		Map<String, Object> map = scheduleManager.searchScheduleSelf(execuserid, planType, start, limit);
		//日程列表
		planList = (List<DevelopScheduleVO>) map.get("list");
		//总数
		totalCount = Long.valueOf(map.get("count").toString());
		//总数为零
		if(totalCount<=0){
			//设置为1
			totalCount = (long) 1;
		}
		return SUCCESS;
	}

	/**
	 * Description:删除客户开发计划<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param
	 * @return
	 */
	@JSON
	public String deleteDevelopSchedule() {
		//删除日程
		scheduleManager.deleteSchedule(planeIds,this.getCurrentUser());
		return SUCCESS;
	}

	/**
	 * Description:更新日程
	 * @author 毛建强
	 * @version 0.1 2012-4-7
	 * @param
	 * @return
	 */
	@JSON
	public String updateDevelopSchedule() {
		//更新日程
		scheduleManager.updateSchedule(schedule,this.getCurrentUser());
		return SUCCESS;
	}

	/**
	 * Description:查询计划主题<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-29
	 * @param
	 * @return
	 */
	@JSON
	public String searchPlaneTopic() {
		//查询部门开发计划列表
		planNameList = planManager.searchDepartmentDevelopPlanList(currentDeptId, planType);
		return SUCCESS;
	}
	
	/**
	 * Description:查询客户信息<br />
	 * @author 张登
	 * @version 0.1 2012-3-29
	 * @param
	 * @return
	 */
//	@JSON
//	public String searchPcScDetail() {
//		//根据ID查询潜客
//		detailInfo = scheduleManager.getPcScDetail(id);
//		return SUCCESS;
//	}
	
	/**
	 * @return planeIds : get the property planeIds
	 */
	public String[] getPlaneIds() {
		return planeIds;
	}

	/**
	 * @param planeIds : set the property planeIds
	 */
	public void setPlaneIds(String[] planeIds) {
		this.planeIds = planeIds;
	}
	/**
	 * @return planNameList : get the property planNameList
	 */
	public List<Plan> getPlanNameList() {
		return planNameList;
	}

	/**
	 * @param planNameList : set the property planNameList
	 */
	public void setPlanNameList(List<Plan> planNameList) {
		this.planNameList = planNameList;
	}
	
	/**
	 * @return scheduleVOList : get the property scheduleVOList
	 */
	public List<DevelopScheduleVO> getScheduleVOList() {
		return scheduleVOList;
	}

	/**
	 * @param scheduleVOList : set the property scheduleVOList
	 */
	public void setScheduleVOList(List<DevelopScheduleVO> scheduleVOList) {
		this.scheduleVOList = scheduleVOList;
	}
	/**
	 * @return scheduleVO : get the property scheduleVO
	 */
	public DevelopScheduleVO getScheduleVO() {
		return scheduleVO;
	}
	/**
	 * @param scheduleVO : set the property scheduleVO
	 */
	public void setScheduleVO(DevelopScheduleVO scheduleVO) {
		this.scheduleVO = scheduleVO;
	}

	/**
	 * @return schedule : get the property schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	/**
	 * @param schedule : set the property schedule
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	/**
	 * @param scheduleManager : set the property scheduleManager
	 */
	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}
	/**
	 * @return planScheduleQueryCondition : get the property planScheduleQueryCondition
	 */
	public PlanScheduleQueryCondition getPlanScheduleQueryCondition() {
		return planScheduleQueryCondition;
	}

	/**
	 * @param planScheduleQueryCondition : set the property planScheduleQueryCondition
	 */
	public void setPlanScheduleQueryCondition(
			PlanScheduleQueryCondition planScheduleQueryCondition) {
		this.planScheduleQueryCondition = planScheduleQueryCondition;
	}
	/**
	 * @param limit : set the property limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return totalCount : get the property totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @param start : set the property start
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return customerVo : get the property customerVo
	 */
	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	/**
	 * @param customerVo : set the property customerVo
	 */
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	/**
	 * @param totalCount : set the property totalCount
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return scheduleList : get the property scheduleList
	 */
	public List<ScheduleQueryResultObject> getScheduleList() {
		return scheduleList;
	}
	/**
	 * @param scheduleList : set the property scheduleList
	 */
	public void setScheduleList(List<ScheduleQueryResultObject> scheduleList) {
		this.scheduleList = scheduleList;
	}
	/**
	 * @param planManager : set the property planManager
	 */
	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}
	/**
	 * @param planType : set the property planType
	 */
	public void setPlanType(String planType) {
		this.planType = planType;
	}
}
