package com.deppon.crm.module.marketing.server.manager.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.marketing.server.manager.IBoCustomerManager;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.service.IBusinessOpportunityService;
import com.deppon.crm.module.marketing.server.utils.BusinessOpportunityValidator;
import com.deppon.crm.module.marketing.shared.domain.BoOperationLog;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCustomer;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportuntiyConstants;
import com.deppon.crm.module.marketing.shared.exception.BusinessOpportunityException;
import com.deppon.crm.module.marketing.shared.exception.BusinessOpportunityExceptionType;

/**
 * Title: IBusinessOpportunityManager.java Description: 商机管理Manager
 * 
 * @author huangzhanming
 * @created 2014-3-25 下午2:04:39
 */
public class BusinessOpportunityManager implements IBusinessOpportunityManager {
	private IBusinessOpportunityService businessOpportunityService;

	private IBoCustomerManager boCustomerManager;

	// 待办
	private MessageManager messageManager;

	// 用戶
	private IUserService userService;

	private IScheduleManager scheduleManager;

	public void setBusinessOpportunityService(
			IBusinessOpportunityService businessOpportunityService) {
		this.businessOpportunityService = businessOpportunityService;
	}

	public void setBoCustomerManager(IBoCustomerManager boCustomerManager) {
		this.boCustomerManager = boCustomerManager;
	}

	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	/**
	 * @discription 商机查询分页
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:16
	 * @param boc
	 * @return
	 */
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc) {
		// 查询条件转换
		BusinessOpportunityCondition condition = filterCondition(boc);
		return businessOpportunityService
				.queryBusinessOpportunityByCondition(condition);
	}

	/**
	 * @discription 商机查询分页计数
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:14
	 * @param boc
	 * @return
	 */
	@Override
	public long countBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc) {
		// 查询条件转换
		BusinessOpportunityCondition condition = filterCondition(boc);
		return businessOpportunityService
				.countBusinessOpportunityByCondition(condition);
	}

	/**
	 * @discription 商机查询优先级内容过滤
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:25:38
	 * @param boc
	 * @return
	 */
	private BusinessOpportunityCondition filterCondition(
			BusinessOpportunityCondition boc) {
		BusinessOpportunityCondition newbo = new BusinessOpportunityCondition();
		if (null == boc) {
			return newbo;
		} else if (StringUtils.isNotEmpty(boc.getBoNumber())) {
			// 商机编号不为空时精确查询
			newbo.setBoNumber(boc.getBoNumber());
			newbo.setStart(boc.getStart());
			newbo.setLimit(boc.getLimit());
		} else if (StringUtils.isNotEmpty(boc.getCustomerId())) {
			// 客户名称不为空时精确查询
			newbo.setCustomerId(boc.getCustomerId());
			newbo.setStart(boc.getStart());
			newbo.setLimit(boc.getLimit());
		} else if (StringUtils.isNotEmpty(boc.getCustomerNum())) {
			// 客户编码不为空时精确查询
			newbo.setCustomerNum(boc.getCustomerNum());
			newbo.setStart(boc.getStart());
			newbo.setLimit(boc.getLimit());
		} else if (StringUtils.isNotEmpty(boc.getContactMobile())) {
			// 联系人手机不为空时精确查询
			newbo.setContactMobile(boc.getContactMobile());
			newbo.setStart(boc.getStart());
			newbo.setLimit(boc.getLimit());
		} else if (StringUtils.isNotEmpty(boc.getContactName())
				&& StringUtils.isNotEmpty(boc.getContactPhone())) {
			// 联系人电话+联系人名称不为空时精确查询
			newbo.setContactName(boc.getContactName());
			newbo.setContactPhone(boc.getContactPhone());
			newbo.setStart(boc.getStart());
			newbo.setLimit(boc.getLimit());
		} else {
			// 商机名称模糊查询
			if (StringUtils.isNotEmpty(boc.getBoName())) {
				boc.setBoName("%" + boc.getBoName() + "%");
			}
			// 其他查询必须在一年范围内
			BusinessOpportunityValidator.isBetweenYear(boc.getBeginTime(),
					boc.getEndTime());
			boc.setEndTime(DateUtils.addDays(boc.getEndTime(), 1));
			newbo = boc;
		}
		return newbo;

	}

	/**
	 * @discription 获取指定商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:10
	 * @param id
	 * @return
	 */
	@Override
	public BusinessOpportunity queryBusinessOpportunityById(String id) {
		return businessOpportunityService.queryBusinessOpportunityById(id);
	}

	/**
	 * @discription 创建商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:08
	 * @param bo
	 */
	@Override
	public void createBusinessOpportunity(BusinessOpportunity bo) {
		// 查询当前商机客户的有效商机
		// 如有未关闭商机则终止
		String custId = bo.getCustomer().getCustId();
		if (isExistedActiveByCustId(custId)) {
			throw new BusinessOpportunityException(
					BusinessOpportunityExceptionType.existedCustActiveBO);
		}
		// 初始化创建商机基本信息
		Date current = new Date();
		bo.setCreateTime(current);
		bo.setModifyTime(current);
		bo.setBoStatus(BusinessOpportuntiyConstants.BO_STATUS_ONGOING);
		bo.setBoStep(BusinessOpportuntiyConstants.BO_STEP_CONTACT);
		// 校验商机的信息完整性
		if (BusinessOpportunityValidator.checkBusinessOpportunityCreateInfo(bo)) {
			// 获取对应的事业部ID
			String bizId = boCustomerManager.queryBizDeptBySubDept(bo
					.getDeptId());
			// 如果是大客户的话则所属事业部为部门ID
			if (StringUtils.isEmpty(bizId)) {
				bo.setBizId(bo.getDeptId());
			} else {
				bo.setBizId(bizId);
			}
			String boId = businessOpportunityService
					.createBusinessOpportunity(bo);
			// 生成商机阶段变更日志
			List<BoOperationLog> logList = getBusinessOpportunityLog(boId,
					null, bo.getBoStep(), bo.getCreator().getId());
			if (!logList.isEmpty()) {
				businessOpportunityService.addBusinessOpportunityLog(logList);
			}
			// 发送待办事宜给客户所属部门负责人
			String managerId = boCustomerManager.queryCustDeptManagerId(custId);
			if (StringUtils.isNotEmpty(managerId)) {
				// 获取商机信息
				BusinessOpportunityCustomer cust = businessOpportunityService
						.queryBusinessOpportunityById(boId).getCustomer();
				// 封装待办事宜信息
				Message todoMessage = new Message();
				// 商机待办事宜类型
				todoMessage
						.setTasktype(BusinessOpportuntiyConstants.TASK_TYPE_MESSAGE);
				todoMessage.setTaskcount(1);
				// 待办事宜文本描述
				String msg = MessageFormat
						.format(BusinessOpportuntiyConstants.CREATE_MESSAGE,
								new Object[] { cust.getCustName(),
										cust.getCustNumber() });
				todoMessage.setIshaveinfo(msg);
				todoMessage.setUserid(Integer.parseInt(managerId));
				// 发送待办事宜
				messageManager.addMessage(todoMessage);
			}
			// 调用接口关闭已有的日程
			scheduleManager.changeStatusToCompleteForBO(custId);
		}
	}

	/**
	 * @discription 修改保存商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:05
	 * @param bo
	 */
	@Override
	public void saveBusinessOpportunity(BusinessOpportunity bo) {
		// 获取修改前商机
		BusinessOpportunity last = businessOpportunityService
				.queryBusinessOpportunityById(bo.getId());
		// 获取客户行业
		bo.setCustomer(last.getCustomer());
		// 与当前商机进行信息比较
		if (BusinessOpportunityValidator.checkBusinessOpportunityInfo(bo,
				last.getBoStep())) {
			// 更新修改时间
			Date current = new Date();
			bo.setModifyTime(current);
			// 通过商机信息获取商机阶段
			String newStep = getBusinessOpportunityStep(bo, last.getBoStep());
			bo.setBoStep(newStep);
			// 通过商机信息获取商机状态
			String newStatus = getBusinessOpportunityStatus(bo, last);
			bo.setBoStatus(newStatus);
			// 修改保存商机信息
			businessOpportunityService.saveBusinessOpportunity(bo);
			// 生成商机阶段操作日志
			List<BoOperationLog> logList = getBusinessOpportunityLog(
					bo.getId(), last.getBoStep(), newStep, bo.getModifier()
							.getId());
			// 记录商机阶段操作日志
			if (!logList.isEmpty()) {
				businessOpportunityService.addBusinessOpportunityLog(logList);
			}
		}
	}

	/**
	 * @discription 获取新的商机状态
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:29:44
	 * @param bo
	 * @param lastStep
	 * @return
	 */
	public static String getBusinessOpportunityStatus(BusinessOpportunity bo,
			BusinessOpportunity last) {
		if (StringUtils.equals(bo.getBoStatus(),
				BusinessOpportuntiyConstants.BO_STATUS_FAILURE)
				|| StringUtils.equals(bo.getBoStatus(),
						BusinessOpportuntiyConstants.BO_STATUS_SUCCESS)) {
			return bo.getBoStatus();
		}
		// 预计成功时间获取
		Date expectSuccessDate = last.getExpectSuccessDate();
		if (null != bo.getExpectSuccessDate()) {
			expectSuccessDate = bo.getExpectSuccessDate();
		}
		// 修改时间获取
		Date lastUpdateTime = bo.getModifyTime();
		String newStatus = last.getBoStatus();
		// 判断是否超期进行
		if (DateUtils.addDays(expectSuccessDate, 1).after(lastUpdateTime)) {
			newStatus = BusinessOpportuntiyConstants.BO_STATUS_ONGOING;
		} else {
			newStatus = BusinessOpportuntiyConstants.BO_STATUS_EXTENDED;
		}
		return newStatus;
	}

	/**
	 * @discription 获取新的商机阶段
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:29:44
	 * @param bo
	 * @param lastStep
	 * @return
	 */
	public static String getBusinessOpportunityStep(BusinessOpportunity bo,
			String lastStep) {
		String newStep = BusinessOpportuntiyConstants.BO_STEP_CONTACT;
		if (StringUtils.isNotEmpty(bo.getCustomer().getCustFirstType())
				&& StringUtils.isNotEmpty(bo.getCustomer().getCustSecondType())
				&& StringUtils.isNotEmpty(bo.getIsBidProject())
				&& StringUtils.isNotEmpty(bo.getIsBOConfirm())
				&& StringUtils.equals(bo.getIsBOConfirm(), "1")) {
			newStep = BusinessOpportuntiyConstants.BO_STEP_ANALYZE;
			if (StringUtils.isNotEmpty(bo.getCustomerDemandDesc())) {
				newStep = BusinessOpportuntiyConstants.BO_STEP_SCHEME;
				if (StringUtils.isNotEmpty(bo.getSolutionDesc())) {
					newStep = BusinessOpportuntiyConstants.BO_STEP_OFFER;
					if (StringUtils.isNotEmpty(bo.getCompetitiveInfo())) {
						if (StringUtils.equals(lastStep,
								BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
							newStep = BusinessOpportuntiyConstants.BO_STEP_DELIVER;
						} else {
							newStep = BusinessOpportuntiyConstants.BO_STEP_OFFER;
						}
					}
				}
			}
		}
		return newStep;
	}

	/**
	 * @discription 获取阶段转换的变更日志
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:29:14
	 * @param boId
	 * @param lastStep
	 * @param newStep
	 * @param operator
	 * @return
	 */
	public static List<BoOperationLog> getBusinessOpportunityLog(String boId,
			String lastStep, String newStep, String operator) {
		Date current = new Date();
		List<BoOperationLog> logList = new ArrayList<BoOperationLog>();
		// 创建商机阶段日志
		BoOperationLog createLog = getBoOperationLog(boId, null,
				BusinessOpportuntiyConstants.BO_STEP_CONTACT, operator, current);
		// 需求分析阶段日志
		BoOperationLog analyzeLog = getBoOperationLog(boId,
				BusinessOpportuntiyConstants.BO_STEP_CONTACT,
				BusinessOpportuntiyConstants.BO_STEP_ANALYZE, operator, current);
		// 制定计划阶段日志
		BoOperationLog schemeLog = getBoOperationLog(boId,
				BusinessOpportuntiyConstants.BO_STEP_ANALYZE,
				BusinessOpportuntiyConstants.BO_STEP_SCHEME, operator, current);
		// 竞标投标阶段日志
		BoOperationLog offerLog = getBoOperationLog(boId,
				BusinessOpportuntiyConstants.BO_STEP_SCHEME,
				BusinessOpportuntiyConstants.BO_STEP_OFFER, operator, current);
		// 持续发货阶段日志
		BoOperationLog deliverLog = getBoOperationLog(boId,
				BusinessOpportuntiyConstants.BO_STEP_OFFER,
				BusinessOpportuntiyConstants.BO_STEP_DELIVER, operator, current);
		if (StringUtils.isEmpty(lastStep)) {
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_CONTACT)) {
				// 如原商机阶段为空，并且新商机阶段为初步联系
				logList.add(createLog);
			}
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_CONTACT)) {
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_ANALYZE)) {
				// 如原商机阶段为初步联系，并且新商机阶段为需求分析
				logList.add(analyzeLog);
			}
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_SCHEME)) {
				// 如原商机阶段为初步联系，并且新商机阶段为制定计划
				logList.add(analyzeLog);
				logList.add(schemeLog);
			}
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_OFFER)) {
				// 如原商机阶段为初步联系，并且新商机阶段为竞标投标
				logList.add(analyzeLog);
				logList.add(schemeLog);
				logList.add(offerLog);
			}
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
				// 如原商机阶段为初步联系，并且新商机阶段为持续发货
				logList.add(analyzeLog);
				logList.add(schemeLog);
				logList.add(offerLog);
				logList.add(deliverLog);
			}
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_ANALYZE)) {
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_SCHEME)) {
				// 如原商机阶段为需求分析，并且新商机阶段为制定计划
				logList.add(schemeLog);
			}
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_OFFER)) {
				// 如原商机阶段为需求分析，并且新商机阶段为竞标投标
				logList.add(schemeLog);
				logList.add(offerLog);
			}
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
				// 如原商机阶段为需求分析，并且新商机阶段为持续发货
				logList.add(schemeLog);
				logList.add(offerLog);
				logList.add(deliverLog);
			}
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_SCHEME)) {
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_OFFER)) {
				// 如原商机阶段为制定计划，并且新商机阶段为竞标投标
				logList.add(offerLog);
			}
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
				// 如原商机阶段为制定计划，并且新商机阶段为持续发货
				logList.add(offerLog);
				logList.add(deliverLog);
			}
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_OFFER)) {
			if (StringUtils.equals(newStep,
					BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
				// 如原商机阶段为竞标投标，并且新商机阶段为持续发货
				logList.add(deliverLog);
			}
		} else if (StringUtils.equals(lastStep,
				BusinessOpportuntiyConstants.BO_STEP_DELIVER)) {
			// 如原商机阶段为持续发货
		}
		return logList;
	}

	/**
	 * @discription 生成指定的商机阶段变更日志
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:28:29
	 * @param boId
	 * @param lastStep
	 * @param newStep
	 * @param operator
	 * @param doneTime
	 * @return
	 */
	public static BoOperationLog getBoOperationLog(String boId,
			String lastStep, String newStep, String operator, Date doneTime) {
		BoOperationLog log = new BoOperationLog();
		log.setBoId(boId);
		log.setPrevStep(lastStep);
		log.setCurrentStep(newStep);
		log.setOperator(operator);
		log.setOperationTime(doneTime);
		return log;
	}

	/**
	 * @discription 提醒商机待办事宜
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:02
	 */
	@Override
	public void execBusinessOpportunityReminder() {
		List<BusinessOpportunity> boList = businessOpportunityService
				.queryBusinessOpportunityForRemind();
		for (BusinessOpportunity bo : boList) {
			// 发送待办事宜
			Message todoMessage = new Message();
			// 商机待办事宜类型
			todoMessage
					.setTasktype(BusinessOpportuntiyConstants.TASK_TYPE_MESSAGE);
			todoMessage.setTaskcount(1);
			// 待办事宜文本描述
			String msg = MessageFormat.format(
					BusinessOpportuntiyConstants.TODO_MESSAGE, new Object[] {
							bo.getBoName(), bo.getBoNumber() });
			todoMessage.setIshaveinfo(msg);
			// 获取员工信息
			User user = userService.selectByCode(bo.getOperator().getEmpCode());
			todoMessage.setUserid(Integer.parseInt(user.getId()));
			// 发送待办事宜
			messageManager.addMessage(todoMessage);
		}
		businessOpportunityService.updateBusinessOpportunityInRemind();
	}

	/**
	 * @discription 商机转入休眠
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:03:51
	 */
	@Override
	public void execBusinessOpportunityToDormant() {
		businessOpportunityService.updateBusinessOpportunityInDormant();
	}

	/**
	 * @discription 商机竞标投标转持续发货
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:02:36
	 */
	@Override
	public void execBusinessOpportunityInSuccess() {
		businessOpportunityService.updateBusinessOpportunityInSuccess();
	}

	/**
	 * @discription 查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityActiveByCustId(
			String custId) {
		return businessOpportunityService.queryActiveByCust(custId, null);
	}

	/**
	 * @discription 是否存在查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	@Override
	public boolean isExistedActiveByCustId(String custId) {
		// 查询对应客户的商机信息
		List<BusinessOpportunity> list = queryBusinessOpportunityActiveByCustId(custId);
		// 判断是否有数据
		if (null != list && !list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @discription 查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custNum
	 * @return
	 */
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityActiveByCustNum(
			String custNum) {
		return businessOpportunityService.queryActiveByCust(null, custNum);
	}

	/**
	 * @discription 是否存在查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	@Override
	public boolean isExistedActiveByCustNum(String custNum) {
		// 查询对应客户的商机信息
		List<BusinessOpportunity> list = queryBusinessOpportunityActiveByCustNum(custNum);
		// 判断是否有数据
		if (null != list && !list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @discription 商机竞标投标转持续发货
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:02:36
	 */
	@Override
	public void execBusinessOpportunityToStepDeliver() {
		// 生成日志
		businessOpportunityService.addBusinessOpportunityStepDeliverLog();
		// 修改阶段
		businessOpportunityService.updateBusinessOpportunityStepDeliver();
	}

	/**
	 * @discription 商机效果评估查询
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param brc
	 * @return
	 */
	@Override
	public List<BoReportInfo> queryBoReportByCondition(BoReportCondition brc) {
		List<BoReportInfo> briList = businessOpportunityService
				.queryBoReportByCondition(brc);
		return briList;
	}

	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityByCustId(
			String custId) {
		BusinessOpportunityCondition condition = new BusinessOpportunityCondition();
		condition.setCustomerId(custId);
		return businessOpportunityService
				.queryBusinessOpportunityByCondition(condition);
	}

	/**
	 * @discription 商机效果评估报表日报表
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @return
	 */
	@Override
	public void calcBoReportForDaily() {
		businessOpportunityService.calcBoReportForDaily();
	}

}
