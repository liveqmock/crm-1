package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IQuestionnaireManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.service.IPlanService;
import com.deppon.crm.module.marketing.server.service.IScheduleService;
import com.deppon.crm.module.marketing.server.utils.PlanValidateUtils;
import com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.PlanDevelopCondition;
import com.deppon.crm.module.marketing.shared.domain.PlanList;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireConstance;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.marketing.shared.exception.PlanException;
import com.deppon.crm.module.marketing.shared.exception.PlanExceptionType;
import com.deppon.crm.module.marketing.shared.exception.ReturnVisitException;
import com.deppon.crm.module.marketing.shared.exception.ReturnVisitExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * 客户开发计划Manager实现<br />
 * </p>
 * 
 * @title PlanManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl
 * @author Administrator
 * @version 0.1 2012-3-23
 */

public class PlanManager implements IPlanManager {
	// 开发计划服务类
	private IPlanService planService;
	// 消息管理
	private IMessageManager messageManager;
	// 日程Manager
	private IScheduleManager scheduleManager;
	// 日程Service
	private IScheduleService scheduleService;
	// 会员Manager
	private IMemberManager memberManager;
	private IAlterMemberManager alterMemberManager;
	
	// 投诉Manager
	private IComplaintManager complaintManager;
	// 认证用户Service
	private IUserService userService;
	//授权部门查询
	private IAuthorizeService authorizeService;
	//问卷管理
	private IQuestionnaireManager questionnaireManager;
	//疑重客户管理
	private IRepeatedCustManager repeatedCustManager;
	//商机管理
	private IBusinessOpportunityManager businessOpportunityManager;
	//消息管理
	private IMessageBundle messageBundle;
	// 降级客户执行时间点
	private int month;
	
	public int getMonth() {
		return month;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	/**
	 * @param scheduleService
	 *            : set the property scheduleService.
	 */
	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	public void setPlanService(IPlanService planService) {
		this.planService = planService;
	}

	/**
	 * @param memberManager
	 *            : set the property memberManager.
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * @param complaintManager
	 *            : set the property complaintManager.
	 */
	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}

	/**
	 * @param userService
	 *            : set the property userService.
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * @param authorizeService
	 *            : set the property authorizeService.
	 */
	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	public void setQuestionnaireManager(IQuestionnaireManager questionnaireManager) {
		this.questionnaireManager = questionnaireManager;
	}
	
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	public void setBusinessOpportunityManager(
			IBusinessOpportunityManager businessOpportunityManager) {
		this.businessOpportunityManager = businessOpportunityManager;
	}
	
	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	/**
	 * 
	 * <p>
	 * 创建客户开发计划<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-23
	 * @param developPlan 客户计划创建
	 * @param customerList
	 *            客户列表
	 * @return boolean
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#createPlan(com.deppon.crm.module.marketing.shared.domain.Plan,
	 *      java.util.List)
	 */
	@Transactional
	@Override
	public String createPlan(Plan plan, String[] customerList,
			String[] contactIds, User user) {
		String tips = "";
		try {
			List<Plan> planList = this.searchDepartmentPlanList(plan,
					plan.getPlanType(), user);
			if (PlanValidateUtils.isPassedValidate(plan, customerList,
					planList, user.getEmpCode().getDeptId().getId())) {
				// 设置计划状态
				plan = this.updatePlanStatusByDate(plan);
				// 获取创建人的Id
				plan.setCreateUserId(user.getEmpCode().getId());
				planService.createPlan(plan);
				tips = this.createSchedule(plan, customerList, contactIds, user);
				if(StringUtils.isNotEmpty(plan.getSurveyId())){
					questionnaireManager.updateSurveyStatusByID(plan.getSurveyId(), QuestionnaireConstance.OPERATE_TYPE_ADD);
				}
			}
			return tips;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
	
	private String createSchedule(Plan plan, String[] customerList,
			String[] contactIds, User user) {
//		String planType = plan.getPlanType();
		// 计划类型为开发类型，直接创建日程
/*		if (planType.equals(MarketingConstance.DEVELOP_TYPE)) {
			// 往日程表中插入数据
			for (String custId : customerList) {
				Schedule sche = this.formSchedule(plan, custId);
				scheduleManager.createSchedule(sche, user);
			}
			messageManager.addMessage(toPlanMessageInfo(plan, user));
			return true;
		}*/
		//疑似重复客户数量
		int repeatNum = 0;
		//存在未关闭商机客户数量
		int oppoNum = 0;
		//客户是否为疑似重复客户
		boolean isRepeat = false;
		//客户是否存在未关闭商机
		boolean isOppo = false;
		//提示框内容
		StringBuffer tips = new StringBuffer();
		for (int i = 0; i < contactIds.length; i++) {
			//验证客户是否为疑似重复
			isRepeat = repeatedCustManager.isCustExistsRepeat(customerList[i]);
			//验证客户是否存在未关闭商机
			isOppo = !businessOpportunityManager.queryBusinessOpportunityActiveByCustId(customerList[i]).isEmpty();
			//有一个存在，不创建日程
			if(isRepeat || isOppo){
				if(isRepeat){
					//计数
					repeatNum ++;					
				}
				if(isOppo){
					//计数
					oppoNum ++;
				}
				continue;
			}
			Schedule sche = this.formSchedule(plan, customerList[i],contactIds[i]);
			scheduleManager.createSchedule(sche, user);
		}
		messageManager.addMessage(toPlanMessageInfo(plan, user));
		//组装提示框消息
		if(oppoNum > 0){
			tips.append(messageBundle.getMessage(getLocale(), "i18n.developPlan.createPlanOppoTips", oppoNum));	
			tips.append(",");
		}
		if(repeatNum > 0){
			tips.append(messageBundle.getMessage(getLocale(), "i18n.developPlan.createPlanRepeatCustTips", oppoNum));		
		}
		//返回
		return tips.toString();
	}

	/**
	 * 
	 * <p>
	 * 根据用户Id查询本部门历史开发计划列表<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param userId
	 *            用户Id
	 * @return List<Plan>
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#searchDepartmentPlanList(java.lang.String)
	 */
	@Override
	public List<Plan> searchDepartmentPlanList(Plan plan, String planType,
			User user) {
		//String exeDeptId = user.getEmpCode().getDeptId().getId();
		String exeDeptId = plan.getExecdeptid();
		return planService.searchDepartmentPlanList(exeDeptId, plan.getTopic(),
				planType);
	}

	@SuppressWarnings("serial")
	/**
	 * @author 苏玉军
	 * @description 快递一期数据权限查询，如果没有选择下来列表中的部门，
	 * 				如果是快递岗位，就查询所有的映射部门
	 */
	@Override
	public HashMap<String, Object> searchPlan(PlanDevelopCondition condition,
			int start, int limit, User user) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			//按照条件查询出计划总条数
			int count=0;
			List<PlanList> list=new ArrayList<PlanList>();
			if (PlanValidateUtils.validateDevelopPlanSearch(condition)) {
				String position = user.getEmpCode().getPosition();
				String dep = condition.getExecuteDept();
				//如果前台传过来的执行部门条件为空
				if (StringUtils.isEmpty(dep)) {
					/**
					 * @author 苏玉军
					 * @更新 如果是属于快递管理岗位，或者用户是 快递市场研究组，可以查询所有部门的开发计划
					 */
					String deptCode = user.getEmpCode().getDeptId().getStandardCode();
					if(StringUtils.isNotEmpty(MarketingConstance.EXPRESS_POSITIONS.get(position))
							||MarketingConstance.EXPRESS_MARKETING_GROUP_STANDARDCODE.equals(deptCode)){
						condition.setExecuteDepts(this.makeUpAllExpressExecuteDeptids(dep, user,deptCode));
					}else{
						condition.setExecuteDepts(this.makeUpAllTLTExecuteDeptids(dep, user));
					}
				}else{
					if(StringUtils.isNotEmpty(MarketingConstance.EXPRESS_POSITIONS.get(position))){
						condition.setExecuteDepts(this.makeUpExpressExecuteDeptids(dep, user));
					}else{
						condition.setExecuteDepts(this.makeUpTLTExecuteDeptids(dep, user));
					}
					
					if(condition.getExecuteDepts().length == 0){
						condition.setExecuteDepts(new String[]{dep});
					}
					
				}
				condition.setExecuteDept(null);
				//验证部门数量是否超过规定值
				PlanValidateUtils.validateDeptNum(condition);
				list= planService.searchPlan(condition, start,
						limit);
				count = planService.getPlanCount(condition);
			}
			map.put("planlist", list);
			map.put("plancount", count);
			return map;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
	
	/**
	 * 根据下拉列表选择的部门，查找子部门及下级部门Id，针对快递
	 */
	@Override
	public String[] makeUpExpressExecuteDeptids(String dep, User user) {
		String userId = user.getId();
		String deptCode = user.getEmpCode().getDeptId().getStandardCode();
		List<String> deptIds = authorizeService.getChildDeptIds(dep,userId,deptCode);
		String[] result = new String[deptIds.size()];
		return deptIds.toArray(result);
	}

	@Override
	public String[] makeUpAllExpressExecuteDeptids(String dep, User user,String deptCode) {
		String userId = user.getId();
		
		List<Department> list = authorizeService.getAllDeptMappedExpress(userId, "", deptCode, 0, 100000);
		List<String> deptIds = new ArrayList<String>();
		String[] result = new String[list.size()];
		for(Department dept : list){
			deptIds.add(dept.getId());
		}
		return deptIds.toArray(result);
	}

	@Override
	public String[] makeUpAllTLTExecuteDeptids(String dep, User user) {
		String[] depIds = null;
		Set<String> deps = user.getDeptids();
		depIds = new String[deps.size()];
		deps.toArray(depIds);
		return depIds;
	}
	
	@Override
	public String[] makeUpTLTExecuteDeptids(String dep,User user){
		List<String> depts = 
				planService.searchAuthBussinessDeptByParentId
				(dep, user.getEmpCode().getEmpCode());
		return depts.toArray(new String[0]);
	}
	
	@Transactional
	@Override
	public boolean deletePlan(String[] ids, User user) {
		try {
			List<Plan> plans = new ArrayList<Plan>();
			if (PlanValidateUtils.checkDeleteIdsIsNotNull(ids)) {
				for (String planId : ids) {
					Plan plan = planService.getPlanById(planId);
					if(plan == null){
						throw new PlanException(PlanExceptionType.doNotExistPlan);
					}
					List<Schedule> schedules = scheduleManager.getAllSchedule(plan);
					plan.setScheduleList(schedules);
					plans.add(plan);
				}
			}

			boolean planRs = false;
			if (PlanValidateUtils.canDeletePlan(plans, user.getEmpCode()
					.getId())) {
				planRs = planService.deletePlan(ids);
				for(Plan plan : plans){
					if(StringUtils.isNotEmpty(plan.getSurveyId())){
						questionnaireManager.updateSurveyStatusByID(plan.getSurveyId(), QuestionnaireConstance.OPERATE_TYPE_DELETE);						
					}
				}
				for (String planId : ids) {
					scheduleManager.deleteScheduleById(planId);
				}
			}
			return planRs;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * 根据开发计划、客户Id组装日程实体<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-24
	 * @param plan
	 * @param custId
	 * @return Schedule
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#formSchedule(com.deppon.crm.module.marketing.shared.domain.Plan,
	 *      java.lang.String)
	 */
	// @Override
	private Schedule formSchedule(Plan plan, String custId) {

		Schedule sche = new Schedule();
		sche.setCustId(custId);
		sche.setPlanId(plan.getId());
		sche.setTime(null);
		// 执行人
		sche.setExeManId(plan.getExecuserid());
		// 执行部门
		sche.setExeDeptId(plan.getExecdeptid());
		// 日程状态 已指派
		sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		sche.setType(plan.getPlanType());
		sche.setCustType(plan.getCustType());
		sche.setCreateUserId(plan.getCreateUserId());
		sche.setContactId(plan.getContactId());
		return sche;
	}

	/**
	 * 
	 * <p>
	 * 创建维护计划的同时创建维护日程，与开发计划的不同<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-10
	 * @param plan
	 * @param custIds
	 * @param contactIds
	 * @return Schedule
	 */
	private Schedule formSchedule(Plan plan, String custId, String contactId) {

		Schedule sche = new Schedule();
		sche.setCustId(custId);
		sche.setPlanId(plan.getId());
		sche.setTime(null);
		// 执行人
		sche.setExeManId(plan.getExecuserid());
		// 执行部门
		sche.setExeDeptId(plan.getExecdeptid());
		// 日程状态 已指派
		sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		sche.setType(plan.getPlanType());
		sche.setCustType(plan.getCustType());
		sche.setCreateUserId(plan.getCreateUserId());
		sche.setContactId(contactId);
		return sche;
	}

	/**
	 * 
	 * <p>
	 * 创建客户开发计划<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-23
	 * @param developPlan
	 *            开发计划
	 * @param customerList
	 *            客户列表
	 * @return boolean
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#updatePlan(com.deppon.crm.module.marketing.shared.domain.Plan,
	 *      java.util.List)
	 */
	@Transactional
	@Override
	public String updatePlan(Plan plan, String[] custList,
			String[] contactIds, User user,String bisYd) {
		String tips ="";
		// 更新计划状态
		plan = this.updatePlanStatusByDate(plan);
		// 获得执行部门ID 2013.4.22 by syj 
		String execDeptId = plan.getExecdeptid();
		// 获得当前用户的部门Id
//		String deptId = user.getEmpCode().getDeptId().getId();
		String userId = user.getEmpCode().getId();
		//部门经理ID
		String managerId = userService.queryManagerEmployeeIdByDeptId(execDeptId);
		plan.setLastModifyUserId(userId);

		List<Plan> planList = this.searchDepartmentPlanList(plan,
				plan.getPlanType(), user);
		Plan oldPlan = this.getPlanById(plan.getId());
		String oldQueId = oldPlan.getSurveyId();
		String newQueId = plan.getSurveyId();
		if(StringUtils.isNotEmpty(oldQueId)){
			if(StringUtils.isEmpty(newQueId) || !newQueId.equals(oldQueId)){//修改计划新的问卷id不为空,且计划id不变
				questionnaireManager.updateSurveyStatusByID(oldQueId, QuestionnaireConstance.OPERATE_TYPE_DELETE);
			}
		}
		// 获得该plan关联的所有日程对象
		List<Schedule> scheList = scheduleManager.getAllSchedule(plan);
		try {
			String[] userAndManagerId = {userId,managerId};
			// 根据计划执行人查询员工
			Employee emp = planService.searchEmployeeByCode(oldPlan.getExecuserid());
			if (PlanValidateUtils.canUpdatePlan(plan, oldPlan, planList , scheList, userAndManagerId, contactIds,emp,bisYd,custList)) {
				// 更新计划
				planService.updatePlan(plan);
				// 开发计划
				tips = this.updateSchedle(plan, custList, contactIds, user);
				if(StringUtils.isNotEmpty(plan.getSurveyId())){
					questionnaireManager.updateSurveyStatusByID(plan.getSurveyId(), QuestionnaireConstance.OPERATE_TYPE_ADD);					
				}
			}
			return tips;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	private String updateSchedle(Plan plan, String[] custList,
			String[] contactIds, User user) {
		//疑似重复客户数量
		int repeatNum = 0;
		//存在未关闭商机客户数量
		int oppoNum = 0;
		//客户是否为疑似重复客户
		boolean isRepeat = false;
		//客户是否存在未关闭商机
		boolean isOppo = false;
		//提示框内容
		StringBuffer tips = new StringBuffer();		
		// 更新相关日程的执行人
		scheduleManager.updOrginalSchdule(plan.getId(), plan.getExecuserid());
		// 开发计划
/*		if (plan.getPlanType().equals(MarketingConstance.DEVELOP_TYPE)) {
			List<String> oldList = planService.getPlanCustIdList(plan.getId());
			List<String> addList = this.getNewAddCustomerList(oldList, custList);
			List<String> deleteList = this.getDeleteCustomerList(oldList,custList);
			// 删除日程
			if (deleteList != null) {
				for (String custId : deleteList) {
					scheduleManager.deleteSchedule(plan.getId(), custId);
				}
			}
			// 添加日程
			if (addList != null) {
				for (String custId : addList) {

					Schedule sche = this.formSchedule(plan, custId);
					scheduleManager.createSchedule(sche, user);
				}
			}
		} else {*/
			List<String> oldList = planService.getPlanContactIdList(plan.getId());
			List<String> addList = this.getNewAddCustomerList(oldList, contactIds);
			List<String> deleteList = this.getDeleteCustomerList(oldList,contactIds);
			// 维护计划下的日程
			// 删除日程
			if (deleteList != null) {
				for (String contactId : deleteList) {
					scheduleManager.deleteScheduleByContact(plan.getId(),
							contactId);
				}
			}
			// 添加日程
			if (addList != null) {
				for (int i = 0; i < addList.size(); i++) {
					int k = 0;
					String str = addList.get(i);
					for (int j = 0; j < contactIds.length; j++) {
						if (!contactIds[j].equals(str)) {
							continue;
						}
						k = j;
						String custId = custList[k];
						String contactId = contactIds[k];
						isRepeat = repeatedCustManager.isCustExistsRepeat(custId);
						isOppo = !businessOpportunityManager.queryBusinessOpportunityActiveByCustId(custId).isEmpty();
						if(isRepeat || isOppo){
							if(isRepeat){
								repeatNum ++;
							}
							if(isOppo){
								oppoNum ++;
							}
							continue;
						}
						Schedule sche = this.formSchedule(plan, custId,
								contactId);
						scheduleManager.createSchedule(sche, user);
					}
				}
			}
//		}
		// TODO 更新计划消息生成代办事宜
		messageManager.addMessage(toPlanMessageInfo(plan, user));
		//组装提示框消息
		if(oppoNum > 0){
			tips.append(messageBundle.getMessage(getLocale(), "i18n.developPlan.createPlanOppoTips", oppoNum));	
			tips.append(",");
		}
		if(repeatNum > 0){
			tips.append(messageBundle.getMessage(getLocale(), "i18n.developPlan.createPlanRepeatCustTips", oppoNum));		
		}
		//返回
		return tips.toString();		
	}

	/**
	 * 
	 * <p>
	 * 设置计划状态<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param Plan
	 * @return boolean
	 */
	private Plan updatePlanStatusByDate(Plan plan) {
		Date start = plan.getBeginTime();
		Date now = new Date();
		// 计划开始时间和当前时间为同一天，执行中
		if (PlanValidateUtils.isSameDay(now, start)) {
			plan.setStatus(MarketingConstance.EXECUTING);
		} else {
			// 否则 未执行
			plan.setStatus(MarketingConstance.NOTEXECUTE);
		}
		return plan;
	}

	@SuppressWarnings("serial")
	@Override
	public List<Plan> searchDepartmentDevelopPlanList(String theme, String deptId,
			String planType) {
		if (StringUtils.isBlank(theme) || theme.length() <= 4) {
			ReturnVisitException e = new ReturnVisitException(
					ReturnVisitExceptionType.themeCannotBeNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		if (StringUtils.isNotBlank(theme)) {
			theme = "%" + theme + "%";
		}
		return planService.searchPlanByTheme(theme, deptId, planType);
	}

	@Override
	public List<Plan> searchDepartmentDevelopPlanList(String deptId, String planType) {
		return planService.searchPlanByTheme("%", deptId, planType);
	}

	@Override
	public List<String> searchDevelopPlan(String planId, User user) {
		return planService.searchScheduleIdByPlanId(planId);
	}

	/**
	 * 
	 * <p>
	 * 获得新添加的客户列表<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param oldList
	 * @param newList
	 * @return List<PotentialCustomer>
	 */
	private List<String> getNewAddCustomerList(List<String> oldList,
			String[] newList) {
		List<String> retList = new ArrayList<String>();
		for (String pc : newList) {
			if (!oldList.contains(pc)) {
				retList.add(pc);
			}
		}
		return retList;
	}

	/**
	 * 
	 * <p>
	 * 获得要删除的客户列表<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param oldList
	 * @param newList
	 * @return List<PotentialCustomer>
	 */
	private List<String> getDeleteCustomerList(List<String> oldList,
			String[] newList) {
		List<String> retList = new ArrayList<String>();
		List<String> newList1 = new ArrayList<String>();
		for (int i = 0; i < newList.length; i++) {
			newList1.add(newList[i]);
		}
		for (String pc : oldList) {
			if (!newList1.contains(pc)) {
				retList.add(pc);
			}
		}
		return retList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.marketing.server.manager.IPlanManager#searchPlanDetail
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public HashMap<String, Object> searchPlanDetail(String id, String plantype) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (PlanValidateUtils.canSearchPlanDetail(id)) {
			List<CustomerVo> list = planService.getMemberList(id, plantype);
			map.put("custlist", list);
		}
		return map;
	}

	/**
	 * <p>
	 * 根据Id查询开发计划信息<br />
	 * </p>
	 * 
	 * @author 苏玉军R
	 * @version 0.1 2012-3-28
	 * @param id
	 * @return Plan
	 */
	public Plan getPlanById(String id) {
		try {
			if (StringUtils.isEmpty(id)) {
				throw new PlanException(PlanExceptionType.planIdCannotBeNull);
			}
			Plan p = planService.getPlanById(id);
			if(p == null){
				throw new PlanException(PlanExceptionType.doNotExistPlan);
			}
			return p;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * 根据计划Id查询客户列表<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-31
	 * @param planId
	 * @return PlanMaintainVo
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#getCustomerbyPlanId(java.lang.String)
	 */
//	@Override
//	public List<ScatterCustomer> getCustomerbyPlanId(String planId) {
//		/**
//		 * 1、根据计划的Id查询从日程表中查询对象的客户Id，返回List<String> 
//		 * 2、迭代list，从客户表中找到对应的客户信息返回
//		 */
//		List<ScatterCustomer> customerList = new ArrayList<ScatterCustomer>();
//		List<String> custIdList = scheduleManager.getCustomerIdByPlanId(planId);
//		for (String custId : custIdList) {
//			ScatterCustomer sc = (ScatterCustomer) pcManager
//					.getPotentialCustomerById(custId);
//			// 因为Id唯一，只查询一次
//			if (sc != null) {
//				customerList.add(sc);
//				continue;
//			}
//			PotentialCustomer pc = scManager.getScatterCustomerById(custId);
//			if (pc != null) {
//				customerList.add((ScatterCustomer) pc);
//				continue;
//			}
//
//		}
//		return customerList;
//	}

	/**
	 * 更新与新增操作集成，方便前台操作
	 * 
	 */
	@Transactional
	@Override
	public String savePlan(Plan plan, String[] custIds, String[] contactIds,
			User user,String bisYd) {
		if (StringUtils.isEmpty(plan.getId())) {
			return this.createPlan(plan, custIds, contactIds, user);
		}
		return this.updatePlan(plan, custIds, contactIds, user,bisYd);
	}

	/**
	 * 
	 * <p>
	 * 会员维护计划查询<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-9
	 * @param vo
	 * @return Map<String, Object>
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#searchMemberList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public Map<String, Object> searchMemberList(CustomerVo vo, int start,
			int limit, User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = 0;
		try {
			// 查询条件不能为空
			if (ScheduleValiateUtils.canSearchCustomerInfo(vo)) {
				if(vo.getDeptId()==null || vo.getDeptId().equals("")){
					vo.setDeptId(user.getEmpCode().getDeptId().getId());
				}
				List<CustomerVo> voList = new ArrayList<CustomerVo>();
				voList = planService.searchMemberList(vo, start, limit);
				count = planService.searchMemberCount(vo);

				result.put("list", voList);
				result.put("count", count);
			}
			return result;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#
	 * searchPlansByStatusList(java.util.List)
	 */
	@Override
	public List<Plan> searchPlansByStatusList(List<String> notInStatusList,int start,int limit) {
		List<Plan> plans = planService.searchPlansByStatusList(notInStatusList,start,limit);
		for (Plan plan : plans) {
			List<Schedule> schedules = scheduleManager.getAllSchedule(plan);
			plan.setScheduleList(schedules);
		}
		return plans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#
	 * processPlansStatusByDate(java.util.List, java.util.Date)
	 */
	@Override
	public List<Plan> processPlansStatusByDate(List<Plan> plans, Date date) {
		List<Plan> result = new ArrayList<Plan>();
		// 检查计划列表 和 日期是否为空
		if (plans == null || date == null) {
			// 参数为空，就返回空的计划列表
			return result;
		}
		for (Plan plan : plans) {
			int count = 0;// 已完成计划计数器
			// 如果在当前时间在计划的时间范围之内
			if (PlanValidateUtils.dateCompareTo(date, plan.getBeginTime()) >= 0
					&& PlanValidateUtils.dateCompareTo(date, plan.getEndTime()) <= 0) {				
				boolean updatedPlan = false;
				// 将计划更新为执行中
				if (!StringUtils.equals(MarketingConstance.EXECUTING,
						plan.getStatus())) {
					plan.setStatus(MarketingConstance.EXECUTING);
					plan.setModifyDate(date);
					plan.setModifyUser(MarketingConstance.TIMERUSERID);
					result.add(plan);
					updatedPlan = true;
				}

				List<Schedule> updatedSchedule = new ArrayList<Schedule>();
				// 如果该计划下没有日程，则可以不用对日程进行处理
				if (CollectionUtils.isEmpty(plan.getScheduleList())) {
					plan.setScheduleList(updatedSchedule);
					continue;
				}
				// 更新日程状态
				for (Schedule schedule : plan.getScheduleList()) {
					// 日程时间为空说明是未制定状态，不用处理
					if (schedule.getTime() == null) {
						continue;
					}
					if (PlanValidateUtils.dateCompareTo(date,
							schedule.getTime()) == 0
							&& MarketingConstance.SCHEDULE_FORMULATE
									.equals(schedule.getStatus())) {
						schedule.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
						schedule.setModifyDate(date);
						schedule.setModifyUser(MarketingConstance.TIMERUSERID);
						updatedSchedule.add(schedule);
					} else if (PlanValidateUtils.dateCompareTo(date,
							schedule.getTime()) > 0
							&& MarketingConstance.SCHEDULE_FORMULATE
									.equals(schedule.getStatus())) {
						schedule.setStatus(MarketingConstance.SCHEDULE_OVERDUE);
						schedule.setModifyDate(date);
						schedule.setModifyUser(MarketingConstance.TIMERUSERID);
						updatedSchedule.add(schedule);
					}else if (MarketingConstance.SCHEDULE_FINISH.equals(schedule.getStatus())){
						// 日程处于已完成状态，计数器+1
						count += 1;
					}
				}
				// 如果该计划下所有日程均为已完成，则改变此计划状态未已完成。
				if (plan.getScheduleList().size() == count){
					plan.setStatus(MarketingConstance.FINISHED);
					plan.setModifyDate(date);
					plan.setModifyUser(MarketingConstance.TIMERUSERID);
					result.add(plan);
				}
				plan.setScheduleList(updatedSchedule);
				// 如果计划状态未更改，但日程更改了，同样需要把改计划加到返回列表中，但不变更其属性
				if (BooleanUtils.isFalse(updatedPlan)
						&& CollectionUtils.isNotEmpty(updatedSchedule)) {
					result.add(plan);
				}

			} else // 如果计划结束时间已经超过
			if (PlanValidateUtils.dateCompareTo(date, plan.getEndTime()) > 0) {

				List<Schedule> updatedSchedules = new ArrayList<Schedule>();
				for (Schedule schedule : plan.getScheduleList()) {
					if (!StringUtils.equals(MarketingConstance.SCHEDULE_FINISH,
							schedule.getStatus())) {
						schedule.setStatus(MarketingConstance.SCHEDULE_OVERDUE);
						schedule.setModifyDate(date);
						schedule.setModifyUser(MarketingConstance.TIMERUSERID);
						updatedSchedules.add(schedule);
					}
				}
				// 只要有一个日程已过期，则更新计划为已过期
				if (CollectionUtils.isNotEmpty(updatedSchedules)) {
					plan.setStatus(MarketingConstance.OVERDUE);
					plan.setModifyDate(date);
					plan.setModifyUser(MarketingConstance.TIMERUSERID);
					plan.setScheduleList(updatedSchedules);
				} else { // 如果所有日程都完成了，则更新计划为已完成
					plan.setStatus(MarketingConstance.FINISHED);
					plan.setModifyDate(date);
					plan.setModifyUser(MarketingConstance.TIMERUSERID);
					plan.setScheduleList(new ArrayList<Schedule>());
				}

				result.add(plan);
			}else{
				for (Schedule schedule : plan.getScheduleList()) {
					if (MarketingConstance.SCHEDULE_FINISH.equals(schedule.getStatus())){
						// 日程处于已完成状态，计数器+1
						count += 1;
					}
				}
				// 如果该计划下所有日程均为已完成，则改变此计划状态为已完成。
				if (plan.getScheduleList().size() == count){
					plan.setStatus(MarketingConstance.FINISHED);
					plan.setModifyDate(date);
					plan.setModifyUser(MarketingConstance.TIMERUSERID);
					result.add(plan);
				}
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.marketing.server.manager.IPlanManager#updatePlanList
	 * (java.util.List)
	 */
	@Override
	public boolean updatePlanList(List<Plan> plans) {
		boolean response = true;
		for (Plan plan : plans) {
			boolean planResult = planService.updatePlanStatus(plan);
			response = response && planResult;
			if (CollectionUtils.isEmpty(plan.getScheduleList())) {
				continue;
			}
			for (Schedule schedule : plan.getScheduleList()) {
				boolean scheduleResult = scheduleService
						.updateScheduleStatus(schedule);
				response = response && scheduleResult;
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#
	 * getAutoMonthlyMaintainPlanCustomerList()
	 */
//	@Override
//    @Deprecated
//	public List<String> getAutoMonthlyMaintainPlanCustomerList(Date queryDate) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(queryDate);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		// 客户编码列表
//		Set<String> customerNumbers = new HashSet<String>();
//
//		// 发货前50名
//		List<NeedMaintainCustomer> deliverList = planService
//				.queryDeliverTop50Customer(sdf.format(queryDate));
//		for (NeedMaintainCustomer c : deliverList) {
//			customerNumbers.add(c.getCustNumber());
//		}
//		// 到货前50名
//		List<NeedMaintainCustomer> receiveList = planService
//				.queryReceiveTop50Customer(sdf.format(queryDate));
//		for (NeedMaintainCustomer c : receiveList) {
//			customerNumbers.add(c.getCustNumber());
//		}
//		// 流失客户
//		List<NeedMaintainCustomer> lostList = planService.queryLostCustomer(sdf
//				.format(DateUtils.addMonths(queryDate, -2)));
//		for (NeedMaintainCustomer c : lostList) {
//			customerNumbers.add(c.getCustNumber());
//		}
//		// 升级客户
//		List<MemberUpgradeList> upgradeList = memberManager
//				.getMemberUpgrage(new SimpleDateFormat("yyyy-MM")
//						.format(queryDate));
//		for (MemberUpgradeList c : upgradeList) {
//			customerNumbers.add(c.getCustNumber());
//		}
//		// 降级客户，每年一月份统计
//		List<MemberDemotionList> demotionList = new ArrayList<MemberDemotionList>();
//		// 传入月份通常为上月，因此需要+1处理
//		if (cal.get(Calendar.MONTH)+1 == month) {
//			// 查询上年降级客户
//			demotionList = memberManager
//					.getDemotionMemberByStatisticsTime(new SimpleDateFormat(
//							"yyyy").format(DateUtils.addYears(queryDate, -1)));
//		}
//		for (MemberDemotionList c : demotionList) {
//			customerNumbers.add(c.getCustNumber());
//		}
//		// 投诉客户
//		List<String> cList = complaintManager
//				.searchComplaintCustomersByDate(sdf.format(queryDate));
//		List<String> complaintList = new ArrayList<String>();
//		// 根据投诉客户ID，获得客户编码
//		for (String id:cList){
//			Member m = alterMemberManager.getMemberById(id);
//			if (null != m && StringUtils.isNotEmpty(m.getCustNumber())){
//				complaintList.add(m.getCustNumber());
//			}
//		}
//		customerNumbers.addAll(complaintList);
//
//		List<String> result = new ArrayList<String>();
//		result.addAll(customerNumbers);
//		return result;
//	}

//    @Deprecated
//	public boolean createNewAutoMonthlyMaintainPlan(List<String> customerNumbers) {
//		boolean response = true;
//
//		// 新建一个根据部门ID分组的Map容器
//		Map<String, List<NeedMaintainCustomer>> deptCustomerMap = new HashMap<String, List<NeedMaintainCustomer>>();
//		// 填充部门id，联系人id等信息
//		for (String customerNumber : customerNumbers) {
//			if(customerNumber==null || customerNumber.equals("")){
//				continue;
//			}
//			Member m = memberManager.getMemberByCustNumber(customerNumber);
//			if (m == null)
//				continue;
//			NeedMaintainCustomer c = new NeedMaintainCustomer();
//			c.setDeptId(m.getDeptId());
//			c.setContactId(m.getContactId());
//			c.setCustId(m.getId());
//			c.setCustNumber(customerNumber);
//			// 按部门分组
//			if (deptCustomerMap.get(c.getDeptId()) == null) {
//				deptCustomerMap.put(c.getDeptId(),
//						new ArrayList<NeedMaintainCustomer>());
//			}
//			deptCustomerMap.get(c.getDeptId()).add(c);
//		}
//		// 新建部门id和部门经理对应map
//		Map<String, String> deptManagerMap = new HashMap<String, String>();
//		Set<String> deptIdSet = deptCustomerMap.keySet();
//		for (String deptId : deptIdSet) {
//			if (StringUtils.isEmpty(deptId)){
//				continue;
//			}
//			String managerId = userService.queryManagerEmployeeIdByDeptId(deptId);			
//			deptManagerMap.put(deptId, managerId);
//		}
//
//		// 创建计划
//		Calendar now = Calendar.getInstance();
//		Calendar end = Calendar.getInstance();
//		end.set(Calendar.DATE, end.getActualMaximum(Calendar.DATE));
//		String theme = new SimpleDateFormat("yyyyMM").format(now.getTime());
//		for (String deptId : deptIdSet) {
//			if (deptManagerMap.get(deptId) == null){
//				// 部门无经理时，跳过此记录
//				continue;
//			}
//			List<NeedMaintainCustomer> list = deptCustomerMap.get(deptId);
//
//			Department department = new Department();
//			department.setId(deptId);
//			Employee employee = new Employee();
//			employee.setDeptId(department);
//			employee.setId(deptManagerMap.get(deptId));
//			User manager = new User();
//			manager.setEmpCode(employee);
//			manager.setId(deptManagerMap.get(deptId));
//			// 构造plan实体
//			Plan plan = new Plan();
//			plan.setCreateUserId(MarketingConstance.TIMERUSERID);
//			plan.setCreateTime(now.getTime());
//			plan.setLastModifyUserId(manager.getId());
//			plan.setLastUpdateTime(now.getTime());
//			plan.setExecdeptid(deptId);
//			plan.setExecuserid(manager.getId());	// 执行人为经理
//
//			plan.setTopic("每月固定维护计划" + theme);
//			plan.setActivedesc("货量前50名客户、升降级客户、投诉客户等");
//			plan.setBeginTime(now.getTime());
//			plan.setEndTime(end.getTime());
//			plan.setPlanType(MarketingConstance.FIXEDPLAN_MONTHLY_TYPE);
//			// 构造 客户Id数组 和 对应的主要联系人数组
//			String[] custIds = new String[list.size()];
//			String[] contactIds = new String[list.size()];
//			for (int i = 0; i < list.size(); i++) {
//				custIds[i] = list.get(i).getCustId();
//				contactIds[i] = list.get(i).getContactId();
//			}
//			createPlan(plan, custIds, contactIds, manager);
////			response = response && result;
//		}
//		return response;
//	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.marketing.server.manager.IPlanManager#getMemberbyPlanId
	 * (java.lang.String)
	 */
	@Override
	public List<CustomerVo> getMemberbyPlanId(String planId) {
		return planService.getMemberList(planId,
				MarketingConstance.DEVELOP_TYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#
	 * searchCustomerList4Plan
	 * (com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition)
	 */
	@Override
	public Map<String, Object> searchCustomerList4Plan(
			SearchCustomerCondition condition, int start, int limit, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String type = condition.getType();
		int count = 0;
		if (type.equals(MarketingConstance.PAGE_TYPE_CYCLE)) {
			// 发到货周期，根据客户编码数组查询会员
			List<CustomerVo> list = new ArrayList<CustomerVo>();
			String[] numbers = condition.getCustNumbers();
			if (numbers.length > 0) {
				CustomerVo vo = new CustomerVo();
				vo.setDeptId(user.getEmpCode().getDeptId().getId());
				vo.setCustNumbers(numbers);
				list = this.planService.searchMemberList(vo, start, limit);
				count = this.planService.searchMemberCount(vo);
			}
			map.put("members", list);
			map.put("count", count);

		} else if (type.equals(MarketingConstance.PAGE_TYPE_ME)) {
			// 五公里地图，根据客户联系人数组查询会员
			//可视化营销中查询需要指定计划的客户信息列表
			List<CustomerVo> list = new ArrayList<CustomerVo>();
			String[] custIds = condition.getCustIds();
			String[] contactIds = condition.getContactIds();
			if (custIds != null && custIds.length > 0) {
				CustomerVo vo = new CustomerVo();
				//设置查询部门id
				if(!("".equals(condition.getDeptId()))){
					vo.setDeptId(condition.getDeptId());
				}
				else{
					vo.setDeptId(user.getEmpCode().getDeptId().getId());
				}
				//设置客户id的查询条件
				vo.setCustIds(custIds);
				list = this.planService.searchMemberList(vo, start, limit);
				count = this.planService.searchMemberCount(vo);
			}else if(contactIds != null && contactIds.length > 0){
				CustomerVo vo = new CustomerVo();
				//设置查询部门id
				if(!("".equals(condition.getDeptId()))){
					vo.setDeptId(condition.getDeptId());
				}
				else{
					vo.setDeptId(user.getEmpCode().getDeptId().getId());
				}
				//设置客户id的查询条件
				vo.setContactIds(contactIds);
				list = this.planService.searchMemberList(vo, start, limit);
				count = this.planService.searchMemberCount(vo);
			}
			map.put("members", list);
			map.put("count", count);
		} 
//		else {
//			// 五公里地图，根据客户ID数组查询潜散客
//			List<ScatterCustomer> list = new ArrayList<ScatterCustomer>();
//			String[] custIds = condition.getCustIds();
//			if (custIds.length > 0) {
//				// 数组最大值
//				int end = start + limit < custIds.length ? start + limit : custIds.length;
//				for (int i=start; i < end ; i++){
//					ScatterCustomer sc = scManager.getScatterCustomerById(custIds[i]);
//					list.add(sc);
//				}
//			}
//			count = custIds.length;
//			map.put("members", list);
//			map.put("count", count);
//		}
		return map;
	}	

	/**
	 * 
	 * <p>
	 * 设置代办事宜格式<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-5-5
	 * @param plan
	 *            开发维护计划
	 * @param user
	 *            当前用户
	 * @return Message
	 */
	private Message toPlanMessageInfo(Plan plan, User user) {
		// 待办事宜中用到User，因此需要重新处理，是否可以提供公共模块统一处理？？
		User empUser = planService.getUserByEmpId(plan.getExecuserid());
		if (empUser == null){
			// 未找到用户
			return null;
		}
		
		Message msg = new Message();
		msg.setCreateUser(user.getId());
		msg.setCreateDate(new Date());
		msg.setDeptId(Integer.parseInt(user.getEmpCode().getDeptId().getId()));
		msg.setUserid(Integer.parseInt(empUser.getId()));
		msg.setTasktype(Constant.TASK_TYPE_NEW_DEVPLAN);
		if (plan.getPlanType().equals(MarketingConstance.DEVELOP_TYPE)){
			msg.setIshaveinfo("新增开发计划："+plan.getTopic());
		}else{
			msg.setIshaveinfo("新增维护计划："+plan.getTopic());
		}
		msg.setTaskcount(1);
		return msg;
	}

	private void toDoMessageInfo(List<ToDoPojo> list, String tasktype, String info) {
		String adminId = MarketingConstance.TIMERUSERID;
		for (ToDoPojo todo:list){
			Message msg = new Message();
			msg.setCreateUser(adminId);
			msg.setCreateDate(new Date());
			msg.setUserid(todo.getUserid());
			msg.setTasktype(tasktype);
			msg.setIshaveinfo(String.format(info, todo.getTaskcount()));
			msg.setTaskcount(1);

			messageManager.addMessage(msg);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#processPlanTODO()
	 */
	@Override
	public boolean processPlanTODO() {
		List<ToDoPojo> list = planService.searchPlan4Job();
		toDoMessageInfo(list, Constant.TASK_TYPE_NEW_DEVPLAN, "未完成计划(%s 条)");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#searchSchedulesByPlanNameDim(java.lang.String)
	 */
	@Override
	public List<String> searchSchedulesByPlanNameDim(PlanDevelopCondition condition) {
		return planService.searchSchedulesByPlanNameDim(condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#createMonthlyPlanByJob()
	 */
	@Override
	public void createMonthlyPlanByJob() {
		planService.createMonthlyPlanByJob();
	}
	//2012-10-31  根据名称 模糊查询 授权部门
	@Override
	public Map<String,Object> getExecuteDep(Set<String> depIds,String deptName, int start,
			int limit) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Department> executeDeplist = planService.searchExecuteDep(depIds,deptName,start,limit);
		String totalcount=planService.searchExecuteDepCount(depIds,deptName);
		map.put("executeDeplist", executeDeplist);
		map.put("totalcount", totalcount);
		return map;
	}
	/**
	 * <p>
	 * Description:  校验计划执行人是否已经异动 或 离职-- 修改计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-20
	 * @param planId
	 * @return Map<String,Object>
	 */
	@Override
	public  Map<String,Object> isOutOrLeave(String planId){
		Map<String,Object> map = new HashMap<String,Object>();
		// 此计划执行人 没有 异动或 离职
		map.put("isOutOrLeave", "0");
		Plan plan = this.getPlanById(planId);
		//根据问卷id查询计划引用问卷详情
		String surveyId = plan.getSurveyId();
		if(StringUtils.isNotEmpty(surveyId)){
			QuestionnaireInfo questionnaire = questionnaireManager.queryQuestionnaireById(surveyId);
			if(questionnaire  != null){
				map.put("invalidTime", questionnaire.getInvalidTime());
				map.put("validTime", questionnaire.getEffectiveTime());							
			}
		}
		// 根据计划执行人查询员工
		Employee emp = planService.searchEmployeeByCode(plan.getExecuserid());
		// 获得该plan关联的所有日程对象
		List<Schedule> scheList = scheduleManager.getAllSchedule(plan);
		boolean isOutOrLeave = PlanValidateUtils.isOutOrLeave(emp, plan);
		boolean isCanUpdate = PlanValidateUtils.canUpdatePlan(plan, scheList, isOutOrLeave);
		if(!isOutOrLeave){
			map.put("isOutOrLeave", "1");
		}
		map.put("plan", plan);
		return map;
	}
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
		//查询部门下的权限营业部
		List<String> deptIds = planService.searchAuthBussinessDeptByParentId(deptId, empCode);
		//如果查询结果为空
		if(deptIds == null || deptIds.size()==0){
			//新建一个集合
			deptIds = new ArrayList<String>();
			//将查询deptid放到集合中
			deptIds.add(deptId);
		}
		return deptIds;
	}
	/**
	 * 
	 * <p>
	 * Description:本地化获取<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年4月4日
	 * @return
	 * Locale
	 */
	private Locale getLocale() {
        ActionContext ctx = ActionContext.getContext();
        if (ctx != null) {
            return ctx.getLocale();
        } else {
            return null;
        }
    }

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IPlanManager#countForsearchPlansByStatusList(java.util.List)
	 */
	@Override
	public int countForsearchPlansByStatusList(List<String> notInStatusList) {
		return planService.countForsearchPlansByStatusList(notInStatusList);
	}
}