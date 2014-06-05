/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ScheduleManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author Administrator
 * @version 0.1 2012-3-27
 */
package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.marketing.server.action.CustomerVo;
import com.deppon.crm.module.marketing.server.action.DevelopScheduleVO;
import com.deppon.crm.module.marketing.server.action.ToDoPojo;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.service.IScheduleService;
import com.deppon.crm.module.marketing.server.utils.ScheduleValiateUtils;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportuntiyConstants;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.Plan;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition;
import com.deppon.crm.module.marketing.shared.exception.ScheduleException;
import com.deppon.crm.module.marketing.shared.exception.ScheduleExceptionType;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * 日程操作实现类
 * </p>
 * @title ScheduleManager.java
 * @package com.deppon.crm.module.marketing.server.manager.impl 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public class ScheduleManager implements IScheduleManager {
	//日程服务
	private IScheduleService scheduleService;
	//计划管理
	private IPlanManager planManager;
	// 消息管理
	private IMessageManager messageManager;
	//疑重客户管理
	private IRepeatedCustManager repeatedCustManager;
	//商机管理
	private IBusinessOpportunityManager businessOpportunityManager;
	/**
	 * @return scheduleService : return the property scheduleService.
	 */
	public IScheduleService getScheduleService() {
		return scheduleService;
	}

	/**
	 * @param scheduleService : set the property scheduleService.
	 */
	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	/**
	 * @return planManager : return the property planManager.
	 */
	public IPlanManager getPlanManager() {
		return planManager;
	}

	/**
	 * @param planManager : set the property planManager.
	 */
	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}

	/**
	 * @return messageManager : return the property messageManager.
	 */
	public IMessageManager getMessageManager() {
		return messageManager;
	}

	/**
	 * @param messageManager : set the property messageManager.
	 */
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}
	/**
	 * @param repeatedCustManager : set the property repeatedCustManager.
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}

	public void setBusinessOpportunityManager(
			IBusinessOpportunityManager businessOpportunityManager) {
		this.businessOpportunityManager = businessOpportunityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#
	 * deleteSchedule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteSchedule(String planId, String custid) {
		return scheduleService.deleteDevelopSchedule(planId, custid);
	}

	/**
	 * <p>
	 * 保存日程操作
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-01-28
	 * @param sche 日程对象
	 * @param user 当前用户
	 * @return boolean
	 */
	public boolean saveSchedule(Schedule sche,User user) {
		if (StringUtils.isEmpty(sche.getId())) {
			// 创建日程
			return this.createSchedule(sche,user);
		}
		// 更新日程
		return this.updateSchedule(sche,user);

	}

	/**
	 * 
	 * <p>
	 * 创建日程,创建无计划日程<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-26
	 * @param ids
	 * @return boolean
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#createSchedules(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Transactional
	@Override
	public boolean createSchedule(Schedule sche,User user) {
		try {
			//结果定义
			boolean rs = false;
			boolean isRepeat = repeatedCustManager.isCustExistsRepeat(sche.getCustId());
			boolean isExist = !businessOpportunityManager.queryBusinessOpportunityActiveByCustId(sche.getCustId()).isEmpty();
			//验证日程状态
			sche = ScheduleValiateUtils.checkScheduleStatus(sche);
			//设置创建人
			sche.setCreateUserId(user.getEmpCode().getId());
			//设置执行部门
			if(StringUtils.isEmpty(sche.getExeDeptId())){
				sche.setExeDeptId(user.getEmpCode().getDeptId().getId());				
			}
			//设置执行人
			if(StringUtils.isEmpty(sche.getExeManId())){
				sche.setExeManId(user.getEmpCode().getId());				
			}
			//日程对象验证
			if (ScheduleValiateUtils.isPassedValidate(sche,isRepeat,isExist)) {
				rs = scheduleService.createDevelopSchedules(sche);
			}
//			if (StringUtils.isNotEmpty(sche.getType())
//					&& sche.getType().equals(MarketingConstance.DEVELOP_TYPE)
//					&& sche.getTime() != null) {
//				this.changeBusinessstate(sche.getCustId(), user);
//			}
			return rs;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}
	
	/**
	 * 创建有有计划日程，直接保存
	 */
	@Override
	public boolean createScheduleByPlan(Schedule sche){
		try {
			if(sche==null){
				throw new ScheduleException(ScheduleExceptionType.scheduleCannotBeNull);
			}
			return scheduleService.createDevelopSchedules(sche);
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#
	 * getAllSchedule(com.deppon.crm.module.marketing.shared.domain.Plan)
	 */
	@Override
	public List<Schedule> getAllSchedule(Plan plan) {
		return scheduleService.getAllSchedule(plan);
	}

	/**
	 * 
	 * <p>
	 * 根据客户Id，客户类型查询客户详情<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param custId
	 *            客户Id
	 * @param custType
	 *            客户类型
	 * @return PotentialCustomer 客户对象
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#getCustomerDetail(java.lang.String,
	 *      java.lang.String)
	 */
//	@Override
//	public PotentialCustomer getCustomerDetail(String custId, String custType) {
//		if (custType.equals(MarketingConstance.POTENTIAL_CUSTOMER)) {
//			return pcManager.getPotentialCustomerById(custId);
//		}
//		return scManager.getScatterCustomerById(custId);
//	}

	/**
	 * 
	 * <p>
	 * 日程是否可以更新<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @return boolean
	 */
	@Transactional
	public boolean updateSchedule(Schedule newSchedule,User user) {
		boolean rs =false;
		try {
			if (ScheduleValiateUtils.isNotEmptySchedule(newSchedule)){
				// 获得日程对象
				String scheduleId = newSchedule.getId();
				// 获得旧日程对象
				Schedule oldSchedule = this.getScheduleById(scheduleId);
				Plan plan = null;
				if (StringUtils.isNotEmpty(oldSchedule.getPlanId())){
					// 获得日程对应的开发计划
					plan = planManager.getPlanById(oldSchedule.getPlanId());
				}

				if (ScheduleValiateUtils.canUpdateSchedule(plan, oldSchedule, newSchedule,user)) {
					// 根据日期设置日程状态
					newSchedule = ScheduleValiateUtils.checkScheduleStatus(newSchedule);
					newSchedule.setModifyUser(user.getEmpCode().getId());
					rs = scheduleService.updateSchedule(newSchedule);
				}
//				if (oldSchedule.getType().equals(MarketingConstance.DEVELOP_TYPE)){
//					this.changeBusinessstate(oldSchedule.getCustId(), user);
//				}
			}
			return rs;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * 根据Id查询日程对象<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param id
	 *            id
	 * @return Schedule 日程对象
	 */
	public Schedule getScheduleById(String id) {
		return scheduleService.getScheduleById(id);
	}

	/**
	 * 
	 * <p>
	 * 仅更新日程的状态<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-28
	 * @param schedule
	 * @return boolean
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#updateScheduleStatus(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Override
	public boolean updateScheduleStatus(Schedule schedule) {
		return scheduleService.updateSchedule(schedule);
	}

	/**
	 * <p>
	 * 根据计划的Id查询对应的客户id<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-3-31
	 * @param planId
	 * @return List<String>
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#getCustomerIdByPlanId(java.lang.String)
	 */
	@Override
	public List<String> getCustomerIdByPlanId(String planId) {
		Plan plan = new Plan();
		plan.setId(planId);
		List<Schedule> scheList = scheduleService.getAllSchedule(plan);
		List<String> custIdList = new ArrayList<String>();
		for (Schedule sche : scheList) {
			custIdList.add(sche.getCustId());
		}
		return custIdList;

	}

	/**
	 * 
	 * <p>
	 * 查询日程详细信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-1
	 * @param condition
	 * @param start
	 * @param limit
	 * @return List<ScheduleQueryResultObject>
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchCustomerList(com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition,
	 *      int, int)
	 * @description 日程新增查询，与开发计划新增客户查询共用
	 */
	@Override
	public List<ScheduleQueryResultObject> searchCustomerList(
			CustomerVo condition, int start, int limit,User user) {
		// 日程新增查询
		try {
			ScheduleValiateUtils.canSearchCustomerInfo(condition);
			return scheduleService.searchMaintainCustList(condition,start,limit);
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}
	
	
	/**
	 * 
	 * <p>
	 * 查询客户详细信息（开发阶段管理）<br />
	 * </p>
	 * 
	 * @author 张斌
	 * @version 0.1 2014-4-15
	 * @param condition
	 * @param start
	 * @param limit
	 * @return List<ScheduleQueryResultObject>
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchCustomerList(com.deppon.crm.module.marketing.shared.domain.PlanScheduleQueryCondition,
	 *      int, int)
	 * @description 查询客户详细信息（开发阶段管理）
	 */
	@Override
	public List<ScheduleQueryResultObject> searchCustomerListForMemberExtend(
			CustomerVo condition, int start, int limit,User user) {
		return scheduleService.searchMaintainCustList(condition,start,limit);
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#countSearchCustomerList(com.deppon.crm.module.marketing.server.action.CustomerVo)
	 */
	@Override
	public int countSearchCustomerList(CustomerVo condition) {
		return scheduleService.countSearchMaintainCustList(condition);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#
	 * deleteSchedule(com.deppon.crm.module.marketing.shared.domain.Schedule)
	 */
	@Transactional
	@Override
	public boolean deleteSchedule(String[] scheduleIds,User user) {
		Plan plan =null;
		try {
			for (String scheduleId : scheduleIds) {
				// 查询日程对象
				Schedule sche = scheduleService.getScheduleById(scheduleId);
				if(sche==null){
					continue;
				}
				// 查询是否关联开发计划
				String planId = sche.getPlanId();
				if(StringUtils.isNotEmpty(planId)){
					plan = planManager.getPlanById(planId);					
				}

				/* 关联计划的日程：在数据库中清空该记录的日程信息，并重置状态为“已指派” */
				if (plan != null) {
					if (!ScheduleValiateUtils.isScheduleCannDelete(plan, sche,user)) {
						return false;
					}
					sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
					sche.setTime(null);
				    scheduleService.updateSchedule(sche);
				    continue;
				}else{
					/* 无关联计划的日程：在数据库中删除该记录 */
					//验证权限
					// 只能操作自己的日程
					if (!ScheduleValiateUtils.canDeleteSchedule(sche,user)) {
						throw new ScheduleException(
								ScheduleExceptionType.cannotModifyNotSelfSchedule);
					}
					//如果日程状态为已完成，则不能删除
					if(sche.getStatus().equals(MarketingConstance.SCHEDULE_FINISH)){
						throw new ScheduleException(ScheduleExceptionType.cannotDeleteFinishSchedule);
					}
					scheduleService.delete(sche);
					continue;
				}
			}
			return true;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * 根据日程id删除日程<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-6
	 * @param scheduleId
	 * @return boolean
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#deleteScheduleById(java.lang.String)
	 */
	@Override
	public boolean deleteScheduleById(String planId) {
		return scheduleService.deleteScheduleById(planId);
	}

	/**
	 * 
	 * <p>
	 * 日程管理-日程查询<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param vo
	 * @return List<DevelopScheduleVO>
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchSchedule(com.deppon.crm.module.marketing.server.action.DevelopScheduleVO)
	 */
	@SuppressWarnings("serial")
	@Override
	public List<DevelopScheduleVO> searchSchedule(DevelopScheduleVO vo,
			int start, int limit,User user) {
		
		try {
			if (ScheduleValiateUtils.canSearchSchedule(vo)) {
				if(StringUtils.isEmpty(vo.getExecuteDeptId())){
					throw new ScheduleException(ScheduleExceptionType.mustSelectDept);
				}
				//如果查询的是开发日程
//				if (vo.getScheduleType().equals(MarketingConstance.DEVELOP_TYPE)) {
//					return scheduleService.searchSchedule(vo, start, limit);
//				}
				//查询日程
				return scheduleService.searchMaintainSchedule(vo, start, limit);
			}
			return null;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 
	 * <p>
	 * 日程查询统计总条数<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-4-7
	 * @param vo
	 * @return int
	 * */
	@Override
	public int countForSearchSchedule(DevelopScheduleVO vo) {
		try {
			if (vo == null) {
				throw new ScheduleException(
						ScheduleExceptionType.queryConditionCannotBeNull);
			}
//			if(vo.getScheduleType().equals(MarketingConstance.DEVELOP_TYPE)){
//				return scheduleService.countForSearchSchedule(vo);			
//			}
			return scheduleService.countForSearchMaintainSchedule(vo);
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,e.getErrorArguments()) {
			};
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#
	 * deleteScheduleByContact(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteScheduleByContact(String planId, String contactId) {
		return scheduleService.deleteScheduleByContact(planId, contactId);
	}

	/**
	 * 
	 * <p>
	 * 通过更新计划更新日程执行人，在更新日程之前执行<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-19
	 * @param planId
	 * @param exeManId
	 * @return
	 * boolean
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#updOrginalSchdule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updOrginalSchdule(String planId, String exeManId) {
		if(StringUtils.isNotEmpty(planId)&&StringUtils.isNotEmpty(exeManId)){
			return scheduleService.updOrginalSchdule(planId,exeManId);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchCustomerList4Plan(com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition, int, int, com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	public Map<String, Object> searchCustomerList4Plan(
			SearchCustomerCondition condition, int start, int limit, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		CustomerVo vo = new CustomerVo();
		//设置查询条件
		vo.setDeptId(user.getEmpCode().getDeptId().getId());
		vo.setContactIds(condition.getContactIds());
		vo.setCustNumbers(condition.getCustNumbers());
		//分页查询
		List<ScheduleQueryResultObject> list = scheduleService
				.searchMaintainCustList(vo, start, limit);
		//查询总数
		int count = scheduleService.countSearchMaintainCustList(vo);
		//封装结果
		map.put("list", list);
		map.put("count", count);
		
		return map;
	}
	/**
	 * 
	 * <p>
	 * 修改业务状态
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param pcId
	 * @param user
	 * void
	 */
//	private void changeBusinessstate(String pcId, User user){
//		PotentialCustomer pc = pcManager.getPotentialCustomerById(pcId);
//		// 商机状态为空或为定位客户，更新商机状态为：接触客户
//		if (StringUtils.isEmpty(pc.getBussinesState())
//				|| pc.getBussinesState().equals(
//						MarketingConstance.FIXED_POSITION)) {
//			pc.setModifyUser(user.getEmpCode().getId());
//			pc.setModifyDate(new Date());
//			pc.setBussinesState(MarketingConstance.CONTACT_CUSTOMER);
//			//更新潜客
//			pcManager.updatePotentialCustomer(pc);
//		}
//	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchScheduleSelf(com.deppon.crm.module.marketing.server.action.CustomerVo, int, int, com.deppon.crm.module.authorization.shared.domain.User)
	 */
	@Override
	public Map<String, Object> searchScheduleSelf(
			String execuserid, String scheduleType, int start, int limit) {
		//结果对象
		Map<String, Object> map = new HashMap<String, Object>();
		List<DevelopScheduleVO> list = new ArrayList<DevelopScheduleVO>();
		int count = 0;
		//日程类型为开发
		if (scheduleType.equals(MarketingConstance.DEVELOP_TYPE)) {
			list = scheduleService.searchDEVScheduleSelf(execuserid, start, limit);
			count = scheduleService.getDEVScheduleSelfCount(execuserid);
		}else{
			//日程类型为维护
			list = scheduleService.searchMATScheduleSelf(execuserid, start, limit);
			count = scheduleService.getMATScheduleSelfCount(execuserid);
		}
		//封装查询结果
		map.put("list", list);
		map.put("count", count);
		return map;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#searchScheduleByStatusList()
	 */
	@Override
	public List<Schedule> searchScheduleByStatus(String status,int start,int limit) {
		return scheduleService.searchScheduleByStatus(status,start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#processScheduleStatus(java.util.List, java.lang.String)
	 */
	@Override
	public boolean processScheduleStatus(List<Schedule> scList,
			String status) {
		//迭代日程列表
		for (Schedule sc : scList){
			sc.setStatus(status);
			try {
				//更新
				scheduleService.updateSchedule(sc);
			} catch (Exception e) {
			}
		}
		return true;
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
	private void toDoMessageInfo(List<ToDoPojo> list, String tasktype, String info) {
		for (ToDoPojo todo:list){
			Message msg = new Message();
			msg.setCreateUser(MarketingConstance.SYSTEM_ADMIN);
			msg.setCreateDate(new Date());
			msg.setUserid(todo.getUserid());
			msg.setTasktype(tasktype);
			msg.setIshaveinfo(String.format(info, todo.getTaskcount()));
			msg.setTaskcount(1);

			messageManager.addMessage(msg);
		}		
	}
	
	/**
	 * <p>
	 * 待办消息
	 * </p>
	 * @author suyujun
	 * @return boolean 
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#processScheduleTODO()
	 */
	@Override
	public boolean processScheduleTODO() {
		// 统计前先删除
		messageManager.deleteMessageByType(Constant.TASK_TYPE_NEW_DEVSCHEDULE);
	
		Schedule sche = new Schedule();
		// 统计每日过期日程
		sche.setStatus(MarketingConstance.SCHEDULE_OVERDUE);
		List<ToDoPojo> overdueList = scheduleService.searchSchedule4Job(sche);
		toDoMessageInfo(overdueList, Constant.TASK_TYPE_NEW_DEVSCHEDULE, "已过期日程(%s 条)");
		
		// 统计每日已指派日程
		sche.setStatus(MarketingConstance.SCHEDULE_ASSIGNED);
		List<ToDoPojo> assignedList = scheduleService.searchSchedule4Job(sche);
		toDoMessageInfo(assignedList, Constant.TASK_TYPE_NEW_DEVSCHEDULE, "已指派日程(%s 条)");
		
		// 统计每日待进行日程
		sche.setStatus(MarketingConstance.SCHEDULE_EXECUTING);
		List<ToDoPojo> executingList = scheduleService.searchSchedule4Job(sche);
		toDoMessageInfo(executingList, Constant.TASK_TYPE_NEW_DEVSCHEDULE, "执行中日程(%s 条)");
		
		return true;
	}
	
	/**
	 * <p>
	 * 根据id查询潜散客
	 * </p>
	 * @author suyujun
	 * @param id
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#getPcScDetail(java.lang.String)
	 */
//	@Override
//	public ScatterCustomer getPcScDetail(String id) {
//		return scManager.getScatterCustomerById(id);
//	}
	/**
	 * <p>
	 * Description: 查出已经回访过的 日程 集合<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-10-17
	 */
	@Override
	public List<Schedule> searchScheduleByReturnVisit(int start, int limit) {
		return scheduleService.searchScheduleByReturnVisit(start,limit);
	}
	 /**
	  * 
	  * <p>
	  * 商机日程查询<br />
	  * </p>
	  * @author 苏玉军
	  * @version 0.1 2014/03/20
	  * @param vo
	  * @return
	  * List<DevelopScheduleVO>
	  */
	@Override
	public List<DevelopScheduleVO> searchBusinessSchedule(DevelopScheduleVO vo,
			int start, int limit, User user) {
		List<DevelopScheduleVO> list = new ArrayList<DevelopScheduleVO>();
		try {
			if (ScheduleValiateUtils.canSearchBusinessSchedule(vo)) {
				list = scheduleService.searchBusinessSchedule(vo, start, limit);
			}
			return list;
		} catch (BusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#countForsearchScheduleByReturnVisit()
	 */
	@Override
	public int countForsearchScheduleByReturnVisit() {
		return scheduleService.countForsearchScheduleByReturnVisit();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#countForsearchScheduleByStatus(java.lang.String)
	 */
	@Override
	public int countForsearchScheduleByStatus(String status) {
		return scheduleService.countForsearchScheduleByStatus(status);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.manager.IScheduleManager#changeStatusToCompleteForBO(java.lang.String)
	 */
	@Override
	public void changeStatusToCompleteForBO(String custId) {
		/**
		 * 更新该客户下所有联系人下的未结束日程为已完成
		 */
		//1、查询需要更新的信息，为待办服务
		List<ToDoPojo> toChangeStatus = scheduleService.searchTodoInfo(custId);
		//2、更新该客户下所在日程状态为已完成
		scheduleService.updAllScheduleToCompleteByCustId(custId);
		//3、待办通知
		toDoMessageInfo(toChangeStatus, BusinessOpportuntiyConstants.TASK_TYPE_MESSAGE, "该客户已经创建商机，相关未完成的日程状态变更为已完成，请周知！");
	}
}
