package com.deppon.crm.module.duty.server.manager.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.manager.ISpecialDayManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.server.service.ICellphoneMessageInfoService;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.crm.module.duty.server.service.IDutyDealProcessService;
import com.deppon.crm.module.duty.server.service.IDutyDeptService;
import com.deppon.crm.module.duty.server.service.IDutyFeedbackService;
import com.deppon.crm.module.duty.server.service.IDutyResultService;
import com.deppon.crm.module.duty.server.service.IDutyService;
import com.deppon.crm.module.duty.server.service.IFeedAttachService;
import com.deppon.crm.module.duty.server.service.IInformUserService;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.server.util.DutyUtils;
import com.deppon.crm.module.duty.server.util.DutyValidator;
import com.deppon.crm.module.duty.shared.domain.CommitDutyResultVO;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDealProcess;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.crm.module.duty.shared.domain.DutyQueryResult;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.duty.shared.domain.InformUser;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyResultVO;
import com.deppon.crm.module.duty.shared.exception.DutyException;
import com.deppon.crm.module.duty.shared.exception.DutyExceptionType;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * Description:工单责任 manager 实现<br />
 * </p>
 * @title DutyManager.java
 * @package com.deppon.crm.module.duty.server.manager.impl 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
@Transactional
public class DutyManager implements IDutyManager {
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(DutyManager.class);
	//责任 Service
	private IDutyService dutyService;
	//节假日管理
	private ISpecialDayManager specialDayManager;
	/**
	 * @param specialDayManager : set the property specialDayManager.
	 */
	public void setSpecialDayManager(ISpecialDayManager specialDayManager) {
		this.specialDayManager = specialDayManager;
	}
	//private IMessageManager messageManager;
	/**
	 * @param dutyService : set the property dutyService.
	 */
	public void setDutyService(IDutyService dutyService) {
		this.dutyService = dutyService;
	}
	
	//责任反馈记录service
	private IDutyFeedbackService dutyFeedbackService;
	/**
	 * @param dutyFeedbackService : set the property dutyFeedbackService.
	 */
	public void setDutyFeedbackService(IDutyFeedbackService dutyFeedbackService) {
		this.dutyFeedbackService = dutyFeedbackService;
	}
	
	//责任划分service
	private IDutyResultService dutyResultService;
	/**
	 * @param dutyResultService the dutyResultService to set
	 */
	public void setDutyResultService(IDutyResultService dutyResultService) {
		this.dutyResultService = dutyResultService;
	}
	
	//通知对象service
	private IInformUserService informUserService;
	
	/**
	 * @param informUserService the informUserService to set
	 */
	public void setInformUserService(IInformUserService informUserService) {
		this.informUserService = informUserService;
	}
	
	//处理经过service
	private IDutyDealProcessService dutyDealProcessService;
	/**
	 * @param dutyDealProcessService the dutyDealProcessService to set
	 */
	public void setDutyDealProcessService(
			IDutyDealProcessService dutyDealProcessService) {
		this.dutyDealProcessService = dutyDealProcessService;
	}
	
	//发送短信
	private ICellphoneMessageInfoService cellphoneMessageInfoService;
	
	/**
	 * @param cellphoneMessageInfoService the cellphoneMessageInfoService to set
	 */
	public void setCellphoneMessageInfoService(
			ICellphoneMessageInfoService cellphoneMessageInfoService) {
		this.cellphoneMessageInfoService = cellphoneMessageInfoService;
	}
	
	//代办
	private IMessageManager messageManager;
	/**
	 * @param messageManager the messageManager to set
	 */
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}
	//userService
	private IUserService userService;
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	//附件service
	private IFeedAttachService feedAttachService;
	//责任部门serveice
	private IDutyDeptService dutyDeptService;
	
	/**
	 * @param dutyDeptService the dutyDeptService to set
	 */
	public void setDutyDeptService(IDutyDeptService dutyDeptService) {
		this.dutyDeptService = dutyDeptService;
	}
	/**
	 * @param feedAttachService the feedAttachService to set
	 */
	public void setFeedAttachService(IFeedAttachService feedAttachService) {
		this.feedAttachService = feedAttachService;
	}
	
	private IDepartmentService departmentService;
	
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private static String EXPRESS="EXPRESS";//快递
	private static String UNEXPRESS="UNEXPRESS";//零担
	
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-2-26
	 */
	@SuppressWarnings("serial")
	public Map<String,Object> queryDutyList(QueryDutyCondition queryDutyCondition,int start,int limit){
		// 结果集map
		Map<String,Object> map = new HashMap<String,Object>();
		// 校验查询条件
		DutyValidator.queryDutyListValidator(queryDutyCondition);
		// 编号类型
		String typeId = DutyConstants.DUTY_QUERY.get(queryDutyCondition.getTypeId());
		// 编号类型名字
		String typeName = queryDutyCondition.getTypeName();
		// 获取凭证号
		queryDutyCondition.setTypeId(typeId);
		// 凭证号下拉框查询可以忽略其他所有查询条件
		if(StringUtils.isNotEmpty(typeId) && StringUtils.isNotEmpty(typeName)){
			queryDutyCondition =new QueryDutyCondition();
			queryDutyCondition.setTypeId(typeId);
			queryDutyCondition.setTypeName(typeName);
		}
		// 工单责任查询
		List<DutyQueryResult> dutyList= dutyService.queryDutyList(queryDutyCondition,start,limit);
		// 工单责任查询 - 条数
		int totalCount = dutyService.queryDutyListCount(queryDutyCondition);
		// 如果查询结果 为空
		/*if(null == dutyList || 0 == dutyList.size()){
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListResultIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}*/
		// 结果集返回
		map.put("dutyList", dutyList);
		map.put("totalCount", totalCount);
		return map;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任接入责任<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return duty
	 */
	@Transactional
	public Map<String,Object> receiveDuty( User user,Integer type){
		int receiveNum = 0;
		List<Duty> dutys = null;
		int rows = 0;
		synchronized (Duty.class) {
			//查询用户已经接入的工单数
			receiveNum = countReceiveDuty(user);
			//检查是否超过最大允许接入数
			DutyValidator.checkMaxReceiveDutyNum(receiveNum, DutyConstants.MAXRECEIVENUM);
			//查询未被接入的工单的查询条件
			SearchDutyCondition sdc = DutyUtils.getUnreceiveDutyCondition(user);
			sdc.setBusinessModel(type.equals(1)?EXPRESS:UNEXPRESS);
			//设置接入条数
			sdc.setRowNum(DutyConstants.RECEIVENUM);
			//按接入条数查询未被接入的工单
			dutys = dutyService.searchDutyByCondition(sdc);
			//检查是否有可接入的工单
			DutyValidator.checkReceiveDutyNum(dutys);
			for( Duty duty : dutys){
				//设置更新该条数据设置为已接入
				duty = DutyUtils.convertUpdateDuty(duty, user);
				//更新数据库
				rows = dutyService.updateDutyById(duty);
			}
		}
		//封装返回实体
		Map<String,Object> result = new HashMap<String,Object>();
		if(rows > 0){// 若更新到数据，则返回当前数据
			result.put("totalCount", new Long(receiveNum+1));
			result.put("receiveDuty", dutys);
		}
		return result;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任经理查询责任<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return duty
	 */
	public Map<String,Object> searchDutyByCondition(User user,SearchDutyCondition sdc ,int start,int limit){
		//设置经理查询条件
		sdc = DutyUtils.getManagerSearchDutyCondition(sdc, user);
		//计算查询结果数
		int totalCount = dutyService.countDutyByCondition(sdc);
		//查询符合条件的结果
		List<Duty> receiveDuty = dutyService.searchDutyByCondition(sdc, start, limit);		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("totalCount", totalCount);
		map.put("receiveDuty", receiveDuty);
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:工单责任计算未被接入的工单<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return int
	 */
	// http://192.168.17.183:8081/
	public Map<String,Integer> countUnreceiveDuty( User user){
		//设置查询条件为未被接入的工单
		SearchDutyCondition sdc = DutyUtils.getUnreceiveDutyCondition(user);
		sdc.setBusinessModel(EXPRESS);
		int expressCount= dutyService.countDutyByCondition(sdc);
		sdc.setBusinessModel(UNEXPRESS);
		int unExpressCount= dutyService.countDutyByCondition(sdc);
		int totalCount=expressCount+unExpressCount;
		Map<String,Integer> map =new HashMap<String, Integer>();
		map.put("expressCount", expressCount);
		map.put("unExpressCount", unExpressCount);
		map.put("totalCount", totalCount);
		return map;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任计算接入人已接入的工单已被接入的工单<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return int
	 */
	public int countReceiveDuty(User user){
		SearchDutyCondition sdc = DutyUtils.getReceiveDutyCondition(user);		
		return dutyService.countDutyByCondition(sdc);
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任--初始化工单责任接入页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 * @return Map<String,Object>
	 */
	public Map<String,Object> initReceiveDuty(User user){
		
		//创建查询未被接入工单的查询条件
		SearchDutyCondition sdc =
				DutyUtils.createSearchReceiveDutyCondition(user);
		//查询已接入的工单
		List<Duty> receiveDuty = dutyService.searchDutyByCondition(sdc);
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("totalCount", receiveDuty.size());
		map.put("receiveDuty", receiveDuty);
		return map;
	}


	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public Map<String,Object> getDutyDetail(String dutyId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Duty dutyDetail = dutyService.getDutyDetail(dutyId);
		List<DutyDealProcess> dutyDealProcess = dutyService.getDutyDealProcess(dutyId);
		List<DutyResult> dutyResult = dutyService.getDutyResult(dutyId);
		List<String> userNameList = dutyService.getuserNameList(dutyId);
		StringBuffer userNames = new StringBuffer();
		for(String str : userNameList){
			userNames.append(str);
			userNames.append(",");
		}
		map.put("dutyDetail", dutyDetail);
		map.put("dutyDealProcess",dutyDealProcess);
		map.put("dutyResult", dutyResult);
		map.put("userNames", userNames.toString());
		
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("dutyId", dutyId);
		map.put("dutyFeedbackList", searchDutyFeedbackByDutyId(searchParam));
		return map;
	}
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-11
	 */
	@Override
	public Duty getDutyDomainDetail(String dutyId) {
		return dutyService.getDutyDetail(dutyId);
	}
	/**
	 * <p>
	 * Description: 工单查询-展开表格<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-14
	 */
	@Override
	public List<DutyQueryResult> queryDutyDetailList(String dutyResultId) {
		if(StringUtils.isEmpty(dutyResultId)){
			return null;
		}
		return (List<DutyQueryResult>) dutyService.queryDutyDetailList(dutyResultId);
	}
	
	/**
	 * <p>
	 * Description:工单责任 -查询-工单责任详情<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-11
	 */
	@Override
	public Duty searchDutyById(String dutyId) {
		if(dutyId==null || "".equals(dutyId.trim())){
			return null;
		}
		return dutyService.getDutyDetail(dutyId);
	}
	
	@Override
	public void getDutyById(String dutyId, boolean temporary, boolean responsibility, boolean submit) {
		if(!StringUtils.isEmpty(dutyId)) {
			Duty duty = dutyService.getDutyDetail(dutyId);
			if(duty != null) {
				String status = duty.getStatus();
				String surveyResult = duty.getSurveyResult();
				if(temporary) {
					if(status.equalsIgnoreCase(DutyConstants.ALLOCATED) && 
							surveyResult.equalsIgnoreCase(DutyConstants.DUTY_SURVEYRESULT_INNER)) {
						//该责任已提交
						DutyException e = new DutyException(DutyExceptionType.DUTY_SUBMIT);
						throw new GeneralException(
								e.getErrorCode(), 
								e.getMessage(), 
								e,
								new Object[]{}) {
						};
					}else if(surveyResult.equalsIgnoreCase(DutyConstants.DUTY_SURVEYRESULT_NORESPONSIBILITY) && 
							status.equalsIgnoreCase(DutyConstants.ALLOCATED)) {
						//该责任已确认无责
						DutyException e = new DutyException(DutyExceptionType.DUTY_SURVEYRESULT_NORESPONSIBILITY);
						throw new GeneralException(
								e.getErrorCode(), 
								e.getMessage(), 
								e,
								new Object[]{}) {
						};
					}
				}else if(temporary) {
					if(status.equalsIgnoreCase(DutyConstants.ALLOCATED) && 
							surveyResult.equalsIgnoreCase(DutyConstants.DUTY_SURVEYRESULT_INNER)) {
						//该责任已提交
						DutyException e = new DutyException(DutyExceptionType.DUTY_SUBMIT);
						throw new GeneralException(
								e.getErrorCode(), 
								e.getMessage(), 
								e,
								new Object[]{}) {
						};
					}
				}else if(submit) {
					if(surveyResult.equalsIgnoreCase(DutyConstants.DUTY_SURVEYRESULT_NORESPONSIBILITY) && 
							status.equalsIgnoreCase(DutyConstants.ALLOCATED)) {
						//该责任已确认无责
						DutyException e = new DutyException(DutyExceptionType.DUTY_SURVEYRESULT_NORESPONSIBILITY);
						throw new GeneralException(
								e.getErrorCode(), 
								e.getMessage(), 
								e,
								new Object[]{}) {
						};
					}
				}
			}
		}
		
	}
	
	
	/**
	 * <p>
	 * Description:根据工单责任ID，查询责任反馈记录列表<br />
	 * @author xiaohongye
	 * @param searchParam
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-14
	 */
	@Override
	public List<DutyFeedback> searchDutyFeedbackByDutyId(Map<String,Object> searchParam) {
		//校验传入的工单责任划分ID是否为空
		DutyValidator.checkDutyId((String)searchParam.get("dutyId"));
		return dutyFeedbackService.searchDutyFeedbackByDutyId(searchParam);
	}
	/**
	 * <p>
	 * 工单责任审批列表<br />
	 * 1、统计员进入该界面默认显示其区域内所有“反馈待审批”的工单。
	 * 2、质检组员进入该界面默认显示所有责任状态为“反馈审批中”的工单。
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-11
	 * @param queryDutyCondition
	 * @param start
	 * @param limit
	 * @param user
	 * @return
	 * Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryDutyApprovalList(
			QueryDutyCondition queryDutyCondition, int start, int limit,
			User user) {
		Map<String,Object> result = new HashMap<String, Object>();
		//结果集
		List<DutyQueryResult> data = new ArrayList<DutyQueryResult>();
		//结果总数
		int totalCount = 0;
		//用户首次进入该页面，查询条件可能为空
		if(queryDutyCondition == null){
			queryDutyCondition = new QueryDutyCondition();
		}
		// 统计员角色 默认显示状态为  “反馈待审批”
		/*if(user.getRoleids().contains(DutyConstants.STATISTICS_ROLE_ID)){
			//统计员
			String dpetId = user.getEmpCode().getDeptId().getId();
			//获得大区
			//Department bigArea = departmentService.getBigAreaByDeptId(dpetId);
			//获得大区下的所有部门
//			List<Department> depts = departmentService.queryAllChildDeptByDeptId(bigArea.getId());
//			List<String> deptIdList = new ArrayList<String>();
//			for(Department dept : depts){
//				deptIdList.add(dept.getId());
//			}
			List<String> deptIdList = new ArrayList<String>();
			deptIdList.add(dpetId);
			//设置权限部门
			queryDutyCondition.getDeptIds().addAll(deptIdList);
			//设置角色
			queryDutyCondition.setRoleId(DutyConstants.STATISTICS_ROLE_ID);
		}*/
		//编码不为空，直接查询，忽略其他条件
		String typeId = queryDutyCondition.getTypeId();
		//当处理编号或者凭证号为空时，对其他查询条件进行验证。否则忽略其他条件
		if(null == typeId || "".equals(typeId.trim())){
			DutyValidator.validateCondition(queryDutyCondition);
		}
		
		//质检员
		if(user.getRoleids().contains(DutyConstants.QUALITYINSPECTOR_ROLE_ID)) {
			queryDutyCondition.setDutyInspector(true);
		}else if(user.getRoleids().contains(DutyConstants.BUSQUALITYINSPECTOR_ROLE_ID)){//事业部质检员
			queryDutyCondition.setDutyInspector(false);
		}
		//事业部质检员只显示本事业部下的工单责任
		if(!queryDutyCondition.getDutyInspector()) {
			String currDeptId = user.getEmpCode().getDeptId().getId();
			List<Department> deptList = departmentService.getAllParentDeptByDeptId(currDeptId);
			for (Department department : deptList) {
				String deptLevel = department.getDeptLevel().toString();
				if("4".equals(deptLevel)) {     // 4-事业部级别
					String standardCode = department.getStandardCode();
					queryDutyCondition.setBussDeptCode(standardCode);
					break;
				}
			}
		}
		
		data = dutyService.queryDutyApprovalList(queryDutyCondition,start,limit);
		totalCount = dutyService.countQueryDutyApprovalList(queryDutyCondition);
		result.put("data", data);
		result.put("totalCount", totalCount);
		return result;
	}
	/**
	 * <p>
	 * Description:统计员审批操作：根据工单责任反馈ID和工单责任划分ID更新工单责任反馈记录和工单责任划分结果<br />
	 * @author xiaohongye
	 * @param dutyFeedback
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void statisOperationOfApproval(DutyFeedback dutyFeedback,
			String approvalType, User user) {
		//1 校验进行此操作的用户是否拥有工单责任审批统计员的角色
		DutyValidator.checkRoleOfStatistics(user);
		//2 校验进行审批的工单责任反馈ID是否为空
		String feedbackId = dutyFeedback.getFeedbackId();
		DutyValidator.checkFeedbackId(feedbackId);
		//3 校验工单责任划分结果Id是否为空
		DutyValidator.checkFeedbackId(dutyFeedback.getDetailId());
		
		//在进行处理之前再次根据工单责任反馈ID查询一次此条工单责任反馈记录，返回最新的责任状态和统计员所属部门等信息
		DutyFeedback beforeDutyFeedback  = dutyFeedbackService.searchDutyFeedbackByFeedbackId(feedbackId);
		String dutyStatus = beforeDutyFeedback.getStatus();
//		String statisDeptId = beforeDutyFeedback.getStatDeptId();
		//4 校验该统计员是否具有审批此条责任反馈的权利
//		DutyValidator.checkRoleOfApproval(user, statisDeptId);
		//5 校验当前此条责任反馈的状态是否为责任待审批
		DutyValidator.checkStatisDutyStatus(dutyStatus);
		
		//校验条件全部通过，执行审批操作
//		dutyFeedback.setStatUserId(user.getEmpCode().getEmpCode());
//		dutyFeedback.setStatUserName(user.getEmpCode().getEmpName());
		
		//校验approvalType审批类型的值设置是否正确
		DutyValidator.checkStatApprovalType(approvalType);
		//统计员审批退回
		if(DutyConstants.STATISTICS_APPROVAL_DISAGREE.equals(approvalType)){
			dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_TRUNING_BACK);
			dutyFeedback.setAppStatus(DutyConstants.DUTY_STATUS_TRUNING_BACK);
			dutyFeedbackService.dutyApproval(dutyFeedback);
			//待办事宜
			messageManager.addMessage(generateMessage(beforeDutyFeedback,user));
		}
		//统计员审批同意
		else{
			dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_APPROVALING);
			dutyFeedback.setAppStatus(DutyConstants.DUTY_STATUS_APPROVALING);
			dutyFeedbackService.dutyApproval(dutyFeedback);
		}
		
	}
	/**
	 * <p>
	 * 质检员审批同意：根据工单责任反馈ID和工单责任划分ID更新工单责任反馈记录和工单责任划分结果<br />
	 * 质检组员同意责任状态都更新为反馈有效，调查结果分别更新为“不成立”
	 * @author suyujun
	 * @param dutyFeedback
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public synchronized void  callerAgreeOfApproval(DutyFeedback dutyFeedback,User user) {
		//检查用户是否为质检员角色
		DutyValidator.checkRoleOfQualityInspector(user);
		//获得责任划分ID
		String dutyDivideId = dutyFeedback.getDetailId();
		//责任反馈ID
		String feedBackId = dutyFeedback.getFeedbackId();
		//验证参数合法性
		DutyValidator.validateParam(dutyDivideId,feedBackId);
		//实时查询工单反馈信息
		DutyFeedback feedback =  dutyFeedbackService.searchDutyFeedbackByFeedbackId(feedBackId);
		//验证反馈状态
		DutyValidator.validateFeedBackAppStatus(feedback);
		//责任划分信息
		DutyResult dutyResult = dutyResultService.selectDutyResultByPrimaryKey(dutyDivideId);
		//责任状态更新为反馈有效
		dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_FEEDBACK_VALID);
		dutyResult.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_WHRONG);
		// 调查结果不成立
		dutyFeedback.setApprovalResult(DutyConstants.DUTY_SURVEYRESULT_WHRONG);
		//审批状态——反馈有效
		dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_FEEDBACK_VALID);
		//质检员ID 及用户
		dutyFeedback.setCallCenDeptId(user.getEmpCode().getDeptId().getId());
		dutyFeedback.setCallCenUser(user.getEmpCode().getEmpName());
		dutyFeedback.setCallcenterTime(new Date());
		dutyFeedback.setCallCenUserId(user.getEmpCode().getEmpCode());
		
		//更新到到数据库
		dutyFeedbackService.updateDutyFeedback(dutyFeedback);
		dutyResultService.updateDutyResult(dutyResult);
		//查询出所有的责任划分结果
		List<DutyResult> dutyList = dutyService.getDutyResult(dutyResult.getDutyId());
		List<String> statusList = new ArrayList<String>();
		//状态结果集合
		for(DutyResult dr:dutyList){
			statusList.add(dr.getSurveyResult());
		}
		//状态结果集不包含调查中，则更新责任状态为调查完成
		if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
			Duty duty = dutyService.getDutyDetail(dutyResult.getDutyId());
			duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
			dutyService.updateDutyById(duty);
		}
	}
	
	/**
	 * 质检员重新划分  责任
	 */
	
	
	/**
	 * <p>
	 * Description:质检员审批退回：根据工单责任反馈ID和工单责任划分ID更新工单责任反馈记录和工单责任划分结果<br />
	 * 质检组员不同意责任状态都更新为反馈无效，调查结果分别更新为“成立”（待办事宜提示“责任反馈无效
	 * @author suyujun
	 * @param dutyFeedback
	 * @version V0.1 
	 * @Date 2013-3-18
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public synchronized void callerDisagreeOfApproval(DutyFeedback dutyFeedback,User user) {
		//检查用户是否为质检员角色
		DutyValidator.checkRoleOfQualityInspector(user);
		//获得责任划分ID
		String dutyDivideId = dutyFeedback.getDetailId();
		//责任反馈ID
		String feedBackId = dutyFeedback.getFeedbackId();
		//验证参数合法性
		DutyValidator.validateParam(dutyDivideId,feedBackId);
		//审批意见
		String appOpinion = dutyFeedback.getCallCenDescision();
		//审批依据
//		String according = dutyFeedback.getAccording();
		boolean isSelect = dutyFeedback.getIsSelected();
		//实时查询工单反馈信息
		DutyFeedback feedback =  dutyFeedbackService.searchDutyFeedbackByFeedbackId(feedBackId);
		//验证反馈状态
		DutyValidator.validateFeedBackAppStatus(feedback);
		//责任划分信息
		DutyResult dutyResult = dutyResultService.selectDutyResultByPrimaryKey(dutyDivideId);
		//如勾选了“退回营业部重新反馈”，
		//审批意见必填,提交后，调查结果依然为调查中，责任状态更新为“责任退回”。
		//此时反馈期限由当前退回时间+24小时（节假日顺延）重新计算。
		if(DutyConstants.SELECTED == isSelect){
			//审批意见，必填项
			DutyValidator.validateAppOpinion(appOpinion);
			//调查中
			dutyResult.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_DOING);
			dutyResult.setDutyDeadLine(this.generateNewDeadLine(new Date()));
			dutyResult.setStatus(DutyConstants.EFFECTIVE);//生效
			dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_DUTY_TRUNING_BACK);//责任退回
			
			dutyFeedback.setAppStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID);//审批状态为反馈无效
			dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_DUTY_TRUNING_BACK);//责任状态为责任退回
		}else if(DutyConstants.NOT_SELECTED == isSelect){
			/**
			 * b、	如未勾选了“退回营业部重新反馈” 
			 * 则审批依据、审批意见必填。责任状态更新为“反馈无效”，
			 * 调查结果更新为“成立”。反馈部门待办事宜提示“责任反馈无效（*）。
			 * 其他与统计员“退回”操作一样
			 */
			//验证审批意见和审批依据
//			DutyValidator.validateAppOpinionAndAccording(appOpinion,according);
			//反馈无效
			dutyFeedback.setAppStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID);
//			dutyFeedback.setAccording(dutyFeedback.getAccording());
			dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID);
			
			dutyResult.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_RIGHT);
			dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID);
		}
		//质检员ID 及用户
		dutyFeedback.setCallCenUserId(user.getEmpCode().getEmpCode());
		dutyFeedback.setCallCenUser(user.getEmpCode().getEmpName());
		//更新到到数据库
		dutyFeedbackService.updateDutyFeedback(dutyFeedback);
		dutyResultService.updateDutyResult(dutyResult);
		//待办事宜
		messageManager.addMessage(generateMessage(feedback,user));
		
		//查询出所有的责任划分结果
		List<DutyResult> dutyList = dutyService.getDutyResult(dutyResult.getDutyId());
		List<String> statusList = new ArrayList<String>();
		//状态结果集合
		for(DutyResult dr:dutyList){
			statusList.add(dr.getSurveyResult());
		}
		//状态结果集不包含调查中，则更新责任状态为调查完成
		if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
			Duty duty = dutyService.getDutyDetail(dutyResult.getDutyId());
			duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
			dutyService.updateDutyById(duty);
		}
	}
	
	/**
	 * 
	 * <p>
	 * 根据当前时间计算新的反馈时限<br />
	 * </p>
	 * @author suyujun
	 * @version 0.1 2013-3-26
	 * @param date
	 * @return
	 * Date
	 */
	public Date generateNewDeadLine(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int wd = 0;
		while(true){
			//加24小时
			cal.add(Calendar.HOUR, 24);
			//wd>0 表示24小时后的日期属于工作日
			wd = specialDayManager.getActualWorkdays(date, cal.getTime());
			if(wd > 0){
				break;
			}
		}
		return cal.getTime();
	}
	/**
	 * 
	 * <p>
	 * 生成代办事宜<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-21
	 * @param feedback 反馈对象
	 * @param user 当前用户
	 * @return Message 代办事宜
	 */
	private Message generateMessage(DutyFeedback feedback , User user){
		Message message = new Message();
		message.setCreateUser(user.getId());
		message.setCreateDate(new Date());
		message.setDeptId(Integer.valueOf(feedback.getFeedDeptId()));
		message.setUserid(Integer.valueOf(feedback.getFeedUserId()));
		if(user.getRoleids().contains(DutyConstants.STATISTICS_ROLE_ID)){
			message.setIshaveinfo(DutyConstants.DUTY_STA_TODO_WORD);
			message.setTasktype(DutyConstants.DUTY_TODO_STA_RETURNBACK);			
		}else if(user.getRoleids().contains(DutyConstants.QUALITYINSPECTOR_ROLE_ID) || 
				user.getRoleids().contains(DutyConstants.BUSQUALITYINSPECTOR_ROLE_ID)){
			message.setIshaveinfo(DutyConstants.DUTY_INS_TODO_WORD);
			message.setTasktype(DutyConstants.DUTY_TODO_INS_RETURNBACK);
		}
		message.setTaskcount(1);
		return message;
	}
	
	/**
	 * <p>
	 * Description:工单责任升级改造：责任审批同意后操作<br />
	 * @author andy
	 * @version V0.1 
	 * @Date 2014-1-13
	 * </p>
	 */
	@Override
	public void commitApprovalAgree(CommitDutyResultVO commitDutyResultVO, User user) {
		//处理经过
		DutyDealProcess dutyDealProcess = commitDutyResultVO.getDutyDealProcess();
		String dutyId = dutyDealProcess.getDutyId();
		
		commitDutyResultVO.setDutyDetail(dutyService.getDutyDetail(dutyId));
		boolean isUpdate = false;//是否修改过，如果修改过，则要修改duty的调查状态
		//划分结果
		if(CollectionUtils.isEmpty(commitDutyResultVO.getDutyResultList())){
			
		}else{
			isUpdate= true;
			for(DutyResult record : commitDutyResultVO.getDutyResultList()){
				record.setDutyId(dutyId);
				record.setStatus(DutyConstants.EFFECTIVE);//设置状态是生效的
				record.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_FEEDBACK );//待反馈
				this.settingDutyDeptType(record);
				dutyResultService.insertDutyResult(record);
				//处理经过（记录下自动生成的信息）
		    	recordDutyDealProcess(record,null,DutyConstants.STSTUS_ADD,user,dutyId);//自动记录处理经过
			}
		}
        
        if(isUpdate){
        	//查询出所有的责任划分结果
			List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
			List<String> statusList = new ArrayList<String>();
			if(!CollectionUtils.isEmpty(dutyList)){
				Set<String> set = new HashSet<String>();
				//状态结果集合
				for(DutyResult dr:dutyList){
					set.add(dr.getDivideType()+dr.getDutyPerId());
					statusList.add(dr.getSurveyResult());
				}
				if(dutyList.size()>set.size()){
					DutyException e = new DutyException(
							DutyExceptionType.haveSameDutyResult);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}
			//状态结果集不包含调查中，则更新责任状态为调查完成
			if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
				Duty duty = dutyService.getDutyDetail(dutyId);
				duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
				dutyService.updateDutyById(duty);
			}else{//如果不是，则为调查中
				Duty duty = dutyService.getDutyDetail(dutyId);
				duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_INNER);
				dutyService.updateDutyById(duty);
			}
        }
        //所有划分结果
    	List<DutyResult> dutyResultList = searchDutyResults(dutyId);//查询出该责任下的所有划分结果（有效地）
    	Map<String,DutyResult> map = new HashMap<String,DutyResult>();
    	if(!CollectionUtils.isEmpty(dutyResultList)){
        	for(DutyResult dutyRedult:dutyResultList){
        		map.put(dutyRedult.getVirtualCode(),dutyRedult);
        	}
    	}
        //通知对象
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDutyInformUserList())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getDutyInformUserList()){
				record.setDutyId(dutyId);
				informUserService.insertInformUser(record);
				//生成待办事宜
	        	createMessage(commitDutyResultVO.getDealNumber(),record,DutyConstants.STSTUS_ADD,map.get(record.getVirtualCode()));
			}
			//发送短信（新增的短信）
	    	List<CellphoneMessageInfo> cellphoneMessageInfoList = convertToMsgInfo(commitDutyResultVO.getDealNumber(),commitDutyResultVO.getAddInformUserRecords(),DutyConstants.STSTUS_ADD,map);
	    	cellphoneMessageInfoService.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
		}
        
        
        //处理经过（记录下用户输入的信息）
    	dutyDealProcess.setOperaprId(user.getId());
    	dutyDealProcess.setOpratorName(user.getEmpCode().getEmpName());
    	dutyDealProcess.setTreatStates(DutyConstants.TREATSTATUS_PERSON);
    	dutyDealProcess.setOperatorTime(new Date());
    	dutyDealProcessService.insertDutyDealProcess(dutyDealProcess);
    	
    	//质检员同意
    	this.callerAgreeOfApproval(commitDutyResultVO.getDutyFeedback(), user);
	
	}
	
	/**
	 * <p>
	 * Description:原来不是为划分完成的状态，现在提交的操作<br />
	 * @author zhangbin
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	public void commitTemporaryDutyResults(CommitDutyResultVO commitDutyResultVO,User user) {
		//处理经过
		DutyDealProcess dutyDealProcess = commitDutyResultVO.getDutyDealProcess();
		String dutyId = dutyDealProcess.getDutyId();	
		//修改duty的状态
		dutyService.updateStatus(DutyConstants.ALLOCATED, user.getId(), dutyId);//以划分状态
		//划分结果
		if(CollectionUtils.isEmpty(commitDutyResultVO.getAddDutyResultRecords())){
			
		}else{
			for(DutyResult record : commitDutyResultVO.getAddDutyResultRecords()){
				record.setDutyId(dutyId);
				record.setStatus(DutyConstants.EFFECTIVE);//设置状态是生效的
				
				this.settingDutyDeptType(record);//责任划分 对部门进行种类表示
				dutyResultService.insertDutyResult(record);
			}
		}
        if(CollectionUtils.isEmpty(commitDutyResultVO.getUpdateDutyResultRecords())){
			
		}else{
			for(DutyResult record : commitDutyResultVO.getUpdateDutyResultRecords()){
				record.setModifyUser(user.getId());
				
				this.settingDutyDeptType(record);//责任划分 对部门进行种类表示
				dutyResultService.updateDutyResult(record);
			}
		}
        
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDeleteDutyResultRecords())){
			
		}else{
			List<String> fids = new ArrayList<String>();
			for(DutyResult record : commitDutyResultVO.getDeleteDutyResultRecords()){
				fids.add(record.getId());
			}
			dutyResultService.deleteDutyResultsStatus(fids);
		}
        //查询出所有的责任划分结果
		List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
		if(!CollectionUtils.isEmpty(dutyList)){
			Set<String> set = new HashSet<String>();
			//状态结果集合
			for(DutyResult dr:dutyList){
				set.add(dr.getDivideType()+dr.getDutyPerId());
			}
			if(dutyList.size()>set.size()){
				DutyException e = new DutyException(
						DutyExceptionType.haveSameDutyResult);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
				};
			}
		}
        //将该责任下的所有dutystatus进行修改，修改为
        DutyResult dutyResult = new DutyResult();
        dutyResult.setDutyId(dutyId);
        dutyResult.setModifyUser(user.getId());
        dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_FEEDBACK );//待反馈状态
        dutyResultService.updateDutySatatus(dutyResult);
        //在划分结果处理完之后要查出划分结果
        //所有划分结果
    	List<DutyResult> dutyResultList = searchDutyResults(dutyId);//查询出该责任下的所有划分结果（有效地）
    	Map<String,DutyResult> map = new HashMap<String,DutyResult>();
    	if(!CollectionUtils.isEmpty(dutyResultList)){
        	for(DutyResult dutyRedult:dutyResultList){
        		map.put(dutyRedult.getVirtualCode(),dutyRedult);
        	}
    	}
        //通知对象
        if(CollectionUtils.isEmpty(commitDutyResultVO.getAddInformUserRecords())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getAddInformUserRecords()){
				record.setDutyId(dutyId);
				informUserService.insertInformUser(record);
			}
		}
        if(CollectionUtils.isEmpty(commitDutyResultVO.getUpdateInformUserRecords())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getUpdateInformUserRecords()){
				informUserService.updateInformUser(record);
			}//这段代买不会执行到（应为通知对象是不会修改的）
		}
        
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDeleteInformUserRecords())){
			
		}else{
			List<String> fids = new ArrayList<String>();
			for(InformUser record : commitDutyResultVO.getDeleteInformUserRecords()){
				fids.add(record.getId());
			}
			informUserService.deleteInformUsers(fids);
		}
        //处理经过（记录下用户输入的信息）
    	dutyDealProcess.setOperaprId(user.getId());
    	dutyDealProcess.setTreatStates(DutyConstants.TREATSTATUS_PERSON);
    	dutyDealProcess.setOpratorName(user.getEmpCode().getEmpName());
    	dutyDealProcess.setOperatorTime(new Date());
    	dutyDealProcessService.insertDutyDealProcess(dutyDealProcess);
		//处理经过（记录下自动生成的信息）
    	if(!CollectionUtils.isEmpty(dutyResultList)){
        	for(DutyResult record:dutyResultList){
        		recordDutyDealProcess(record,null,DutyConstants.STSTUS_ADD,user,dutyId);//自动记录处理经过
        	}
    	}
		
    	List<InformUser> informUserList = searchInformUsers(dutyId);//查询出该责任下的所有通知对象
    	if(!CollectionUtils.isEmpty(informUserList)){
    		//生成代办
        	for(InformUser infoemUser:informUserList){
	        	createMessage(commitDutyResultVO.getDutyDetail().getDealNumber(),infoemUser,DutyConstants.STSTUS_ADD,map.get(infoemUser.getVirtualCode()));
        	}
        	//发送短信
        	List<CellphoneMessageInfo> cellphoneMessageInfoList = convertToMsgInfo(commitDutyResultVO.getDutyDetail().getDealNumber(),informUserList,DutyConstants.STSTUS_ADD,map);
        	cellphoneMessageInfoService.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
    	}
	}
	
	/**
	 * <p>
	 * Description:原来是划分完成的状态，现在提交的操作<br />
	 * @author zhangbin
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	public void commitCompleteDutyResults(CommitDutyResultVO commitDutyResultVO,User user) {
		//处理经过
		DutyDealProcess dutyDealProcess = commitDutyResultVO.getDutyDealProcess();
		String dutyId = dutyDealProcess.getDutyId();
		boolean isUpdate = false;//是否修改过，如果修改过，则要修改duty的调查状态
		//划分结果
		if(CollectionUtils.isEmpty(commitDutyResultVO.getAddDutyResultRecords())){
			
		}else{
			isUpdate= true;
			for(DutyResult record : commitDutyResultVO.getAddDutyResultRecords()){
				record.setDutyId(dutyId);
				record.setStatus(DutyConstants.EFFECTIVE);//设置状态是生效的
				record.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_FEEDBACK );//待反馈
				
				this.settingDutyDeptType(record);//责任划分 对部门进行种类表示
				dutyResultService.insertDutyResult(record);
				//处理经过（记录下自动生成的信息）
		    	recordDutyDealProcess(record,null,DutyConstants.STSTUS_ADD,user,dutyId);//自动记录处理经过
			}
		}
        if(CollectionUtils.isEmpty(commitDutyResultVO.getUpdateDutyResultRecords())){
			
		}else{
			isUpdate= true;
			for(DutyResult record : commitDutyResultVO.getUpdateDutyResultRecords()){
				record.setModifyUser(user.getId());
				DutyResult oldDutyResult = dutyResultService.selectDutyResultByPrimaryKey(record.getId());
				record.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_FEEDBACK );//待反馈(如果对一条划分结果进行了修改，则状态变为待反馈)
				recordDutyDealProcess(record,oldDutyResult,DutyConstants.STSTUS_UPDATE,user,dutyId);
				
				this.settingDutyDeptType(record);//责任划分 对部门进行种类表示
				dutyResultService.updateDutyResult(record);
				dutyFeedbackService.deleteFeedbackByDetailId(record.getId());//删除该划分结果下的所有反馈记录
			}
		}
        
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDeleteDutyResultRecords())){
			
		}else{
			isUpdate= true;
			List<String> fids = new ArrayList<String>();
			for(DutyResult record : commitDutyResultVO.getDeleteDutyResultRecords()){
				dutyFeedbackService.deleteFeedbackByDetailId(record.getId());//删除该划分结果下的所有反馈记录
				//处理经过（记录下自动生成的信息）
		    	recordDutyDealProcess(record,null,DutyConstants.STSTUS_DELETE,user,dutyId);//自动记录处理经过
				fids.add(record.getId());
			}
			dutyResultService.deleteDutyResultsStatus(fids);
		}
        if(isUpdate){
        	//查询出所有的责任划分结果
			List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
			List<String> statusList = new ArrayList<String>();
			if(!CollectionUtils.isEmpty(dutyList)){
				Set<String> set = new HashSet<String>();
				//状态结果集合
				for(DutyResult dr:dutyList){
					set.add(dr.getDivideType()+dr.getDutyPerId());
					statusList.add(dr.getSurveyResult());
				}
				if(dutyList.size()>set.size()){
					DutyException e = new DutyException(
							DutyExceptionType.haveSameDutyResult);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {

								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;
					};
				}
			}
			//状态结果集不包含调查中，则更新责任状态为调查完成
			if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
				Duty duty = dutyService.getDutyDetail(dutyId);
				duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
				dutyService.updateDutyById(duty);
			}else{//如果不是，则为调查中
				Duty duty = dutyService.getDutyDetail(dutyId);
				duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_INNER);
				dutyService.updateDutyById(duty);
			}
        }
        //所有划分结果
    	List<DutyResult> dutyResultList = searchDutyResults(dutyId);//查询出该责任下的所有划分结果（有效地）
    	Map<String,DutyResult> map = new HashMap<String,DutyResult>();
    	if(!CollectionUtils.isEmpty(dutyResultList)){
        	for(DutyResult dutyRedult:dutyResultList){
        		map.put(dutyRedult.getVirtualCode(),dutyRedult);
        	}
    	}
        //通知对象
        if(CollectionUtils.isEmpty(commitDutyResultVO.getAddInformUserRecords())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getAddInformUserRecords()){
				record.setDutyId(dutyId);
				informUserService.insertInformUser(record);
				//生成待办事宜
	        	createMessage(commitDutyResultVO.getDutyDetail().getDealNumber(),record,DutyConstants.STSTUS_ADD,map.get(record.getVirtualCode()));
			}
			//发送短信（新增的短信）
	    	List<CellphoneMessageInfo> cellphoneMessageInfoList = convertToMsgInfo(commitDutyResultVO.getDutyDetail().getDealNumber(),commitDutyResultVO.getAddInformUserRecords(),DutyConstants.STSTUS_ADD,map);
	    	cellphoneMessageInfoService.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
		}
        if(CollectionUtils.isEmpty(commitDutyResultVO.getUpdateInformUserRecords())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getUpdateInformUserRecords()){
				informUserService.updateInformUser(record);
			}
		}
        
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDeleteInformUserRecords())){
			
		}else{
			List<String> fids = new ArrayList<String>();
			for(InformUser record : commitDutyResultVO.getDeleteInformUserRecords()){
				fids.add(record.getId());
				//生成待办事宜
				createMessage(commitDutyResultVO.getDutyDetail().getDealNumber(),record,DutyConstants.STSTUS_DELETE,map.get(record.getVirtualCode()));//生成删除代办
			}
			informUserService.deleteInformUsers(fids);
			//发送短信(删除的短信)
	    	List<CellphoneMessageInfo> cellphoneMessageInfoList = convertToMsgInfo(commitDutyResultVO.getDutyDetail().getDealNumber(),commitDutyResultVO.getDeleteInformUserRecords(),DutyConstants.STSTUS_DELETE,null);
	    	cellphoneMessageInfoService.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
		}
        
        //处理经过（记录下用户输入的信息）
    	dutyDealProcess.setOperaprId(user.getId());
    	dutyDealProcess.setOpratorName(user.getEmpCode().getEmpName());
    	dutyDealProcess.setTreatStates(DutyConstants.TREATSTATUS_PERSON);
    	dutyDealProcess.setOperatorTime(new Date());
    	dutyDealProcessService.insertDutyDealProcess(dutyDealProcess);
    	
    	
    	
	}
	
	/**
	 * <p>
	 * Description: 生成一条代办<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dutyResultList	记录处理经过的划分结果
	 * @param status 状态（ADD,UPDATE,DELETE）
	 */
	public  void createMessage(String dealNumber,InformUser informUser,String status,DutyResult dutyResult) {
	        if(dutyResult!=null){
	        	//处理经过
				String message = null;
				if(DutyConstants.STSTUS_ADD.equals(status)){
					if(DutyConstants.DEVIDETYPE_DEPT.equals(dutyResult.getDivideType())){
						message  = "您好，工单编号"+dealNumber+",新增责任部门"+informUser.getDeptName();
					}else if(DutyConstants.DEVIDETYPE_EMP.equals(dutyResult.getDivideType())){
						message  = "您好，工单编号"+dealNumber+",新增责任人"+informUser.getUserName();
					}else{
						
					}
				}else if(DutyConstants.STSTUS_DELETE.equals(status)){
					if(DutyConstants.DEVIDETYPE_DEPT.equals(dutyResult.getDivideType())){
						message  = "您好，工单编号"+dealNumber+",删除原责任部门"+informUser.getDeptName();
					}else if(DutyConstants.DEVIDETYPE_EMP.equals(dutyResult.getDivideType())){
						message  = "您好，工单编号"+dealNumber+",删除原责任人"+informUser.getUserName();
					}else{
						
					}
				}
				Message todoMessage = new Message();
				todoMessage.setTasktype(DutyConstants.DUTY_MESSAGE);//责任提示消息
				todoMessage.setTaskcount(1);
				todoMessage.setIshaveinfo(message);
				User user = userService.findByLoginName(informUser.getUserNo());
				if(user==null||StringUtils.isEmpty(user.getId())){//判断如果user是空则跳过生成代办
					return;
				}
				todoMessage.setUserid(Integer.parseInt(user.getId()));
				messageManager.addMessage(todoMessage);
	        }		
	}
	/**
	 * <p>
	 * Description: 自动生成的处理经过<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dutyResultNew	新划分结果
	 * @param dutyResultOld	原有划分结果
	 * @param dutyId	责任ID
	 * @param status 状态（ADD,UPDATE,DELETE）
	 */
	public  void recordDutyDealProcess(DutyResult dutyResultNew,DutyResult dutyResultOld,String status,User user,String dutyId) {
			//处理经过
			DutyDealProcess dutyDealProcess = new DutyDealProcess();
			String treatProcess = null;
			if(DutyConstants.STSTUS_ADD.equals(status)){
				if(DutyConstants.DEVIDETYPE_DEPT.equals(dutyResultNew.getDivideType())){
					treatProcess  = "新增责任部门"+dutyResultNew.getDutyPerName();
				}else if(DutyConstants.DEVIDETYPE_EMP.equals(dutyResultNew.getDivideType())){
					treatProcess  = "新增责任人"+dutyResultNew.getDutyPerName();
				}else{
					
				}
			}else if(DutyConstants.STSTUS_UPDATE.equals(status)){
				//记录原责任部门*****更改为*****
				String oldDivideType = dutyResultOld.getDivideType();
				String newDivideType = dutyResultNew.getDivideType();
				String oldDutyPerId = dutyResultOld.getDutyPerId();
				String newDutyPerId = dutyResultNew.getDutyPerId();
				if(!oldDivideType.equals(newDivideType)||!oldDutyPerId.equals(newDutyPerId)){//当划分类型不相等或者责任人ID不相等就说明划分对象改变了
					if(DutyConstants.DEVIDETYPE_DEPT.equals(oldDivideType)){
						treatProcess = "记录原责任部门"+dutyResultOld.getDutyPerName()+"更改为";
					}else if(DutyConstants.DEVIDETYPE_EMP.equals(oldDivideType)){
						treatProcess = "记录原责任人"+dutyResultOld.getDutyPerName()+"更改为";
					}else{
						
					}
					if(DutyConstants.DEVIDETYPE_DEPT.equals(newDivideType)){
						treatProcess = treatProcess+"责任部门"+dutyResultNew.getDutyPerName();
					}else if(DutyConstants.DEVIDETYPE_EMP.equals(newDivideType)){
						treatProcess = treatProcess+"责任人"+dutyResultNew.getDutyPerName();
					}else{
						
					}
				}
			}else if(DutyConstants.STSTUS_DELETE.equals(status)){
				if(DutyConstants.DEVIDETYPE_DEPT.equals(dutyResultNew.getDivideType())){
					treatProcess  = "删除原责任部门"+dutyResultNew.getDutyPerName();
				}else if(DutyConstants.DEVIDETYPE_EMP.equals(dutyResultNew.getDivideType())){
					treatProcess  = "删除原责任人"+dutyResultNew.getDutyPerName();
				}else{
					
				}
			}
			 //处理经过
			dutyDealProcess.setDutyId(dutyId);
	    	dutyDealProcess.setOperaprId(user.getId());
	    	dutyDealProcess.setTreatProcess(treatProcess);
	    	dutyDealProcess.setTreatStates(DutyConstants.TREATSTATUS_SYS);
	    	dutyDealProcess.setOpratorName(user.getEmpCode().getEmpName());
	    	dutyDealProcess.setOperatorTime(new Date());
	    	if(!StringUtils.isEmpty(treatProcess)){
	    		dutyDealProcessService.insertDutyDealProcess(dutyDealProcess);
	    	}else{
	    		return;
	    	}
	}
	
	/**
	 * <p>
	 * Description: 把通知对象数据传入变为临时存储的cellphoneMessageInfo<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dealNumber		工单编号
	 * @param informUserList	通知对象
	 * @param status 状态（ADD,DELETE）如果状态为DELETE，map可以为null
	 * @param map 里面存着虚拟编码，通过虚拟编码可以找到划分结果
	 * @return
	 * List<CellphoneMessageInfo>
	 */
	public  List<CellphoneMessageInfo> convertToMsgInfo(String dealNumber,List<InformUser> informUserList,String status, Map<String,DutyResult> map) {
	    List<CellphoneMessageInfo> msgInfos=new ArrayList<CellphoneMessageInfo>();
	    if(!CollectionUtils.isEmpty(informUserList)){
	    	for(InformUser record:informUserList){
	    		CellphoneMessageInfo info=new CellphoneMessageInfo();
	    		if(StringUtils.isEmpty(record.getUserContact())){//手机号码为空则不发送短信
	    			continue;
	    		}
	    		info.setPhoneNumber(record.getUserContact());
	    		info.setSenderEmpCode(record.getUserNo());
	    		String message = null;
	    		if(DutyConstants.STSTUS_ADD.equals(status)){
					message = convertToAddMsgInfo(dealNumber,map.get(record.getVirtualCode()));
	    		}else if(DutyConstants.STSTUS_DELETE.equals(status)){
	    			message = convertToDeleteMsgInfo(dealNumber);
	    		}
	    		info.setMsgContent(message);
	    		info.setSendStandardDeptCode(DutyConstants.STANDARD_DEPARTMENT_CODE);
	    		msgInfos.add(info);	    	
	    	}
	    }
		return msgInfos;
	}
	
	/**
	 * <p>
	 * Description:生成新增短信通知信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dealNumber		工单编号
	 * @param informUser	通知对象
	 * @return message 短信内容
	 */
	public  String convertToAddMsgInfo(String dealNumber,DutyResult dutyResult) {
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String someBody = null;
	    		String message = null;
	    		if(dutyResult!=null){
	    			String  dutyDeadLine= df.format(dutyResult.getDutyDeadLine());
	    			someBody = dutyResult.getDutyPerName();
//		    		if(StringUtils.isEmpty(dutyResult.getBusType())){
//		    			message = "您好，工单编号"+dealNumber+"，划分为"+someBody+"有责,如有异议请于"+dutyDeadLine+"前进行反馈，请及时处理。";
//		    		}else{
//		    			message = "您好，工单编号"+dealNumber+"，"+dutyResult.getBusType()+"，划分为"+someBody+"有责,如有异议请于"+dutyDeadLine+"前进行反馈，请及时处理。";
//		    		}	
	    			if(StringUtils.isEmpty(dutyResult.getBusType())){
		    			message = "您好，工单编号"+dealNumber+"，划分为"+someBody+"有责,如有异议请于"+dutyDeadLine+"前进行反馈，请及时处理。";
		    		}else{
		    			message = "您好，工单编号"+dealNumber+"，"+dutyResult.getBusType()+"，划分为"+someBody+"有责,请及时配合任务部门处理客户问题。";
		    		}
		    		
			        return message;
	    		}else{
	    			message = "您好，工单编号"+dealNumber+"责任划分完毕，请及时查看并协助处理。";//当划分类型为空，则发送这些内容
	    			return message;
	    		}
	    		
	}
	
	
	/**
	 * <p>
	 * Description:生成删除短信通知信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-20
	 * @param dealNumber		工单编号
	 * @param informUser	通知对象
	 * @return message 短信内容
	 */
	public  String convertToDeleteMsgInfo(String dealNumber) {
	    String message = "工单编号"+dealNumber+"，责任划分已被呼叫中心撤销";
		return message;
	}
	/**
	 * 
	 * <p>
	 * Description:提交责任划分结果<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-18
	 */
	@Override
	public void commitDutyResults(CommitDutyResultVO commitDutyResultVO,
			User user) {
		if(StringUtils.isEmpty(commitDutyResultVO.getDutyDetail().getComplaintid())){
			DutyException e = new DutyException(
					DutyExceptionType.complaintidIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		//如果是以划分的则进行一种操作
		if(DutyConstants.ALLOCATED.equals(commitDutyResultVO.getDutyDetail().getStatus())){
			commitCompleteDutyResults(commitDutyResultVO,user);
		}else{//为划分完成，则是另外一种操作
			updateDutySurveyResult(commitDutyResultVO,user);
			commitTemporaryDutyResults(commitDutyResultVO,user);
		}
	}
	
	/**
	 * 设置责任划分部门的类型- 用途责任划分
	 * -1 在三种经营本部之外的部门或个人
	 * 0  在三种经营本部之内的部门或个人
	 * 1 配置的特殊部门
	 * @param dutyResult 划分的责任部门
	 */
	private void settingDutyDeptType(DutyResult dutyResult){
		String divideType = dutyResult.getDivideType();
		if(DutyConstants.DEVIDETYPE_DEPT.equals(divideType)){//划分类型若为部门
			dutyResult.setDutyDeptId(dutyResult.getDutyPerId());
		}else if(DutyConstants.DEVIDETYPE_EMP.equals(divideType)){//划分类型若为个人
			//查询员工所在部门
			Department dept =dutyDeptService.searchDepartmentByEmpId(dutyResult.getDutyPerId());
			if(dept != null){
				dutyResult.setDutyDeptId(dept.getId());
			}
		}
		
		//验证部门或个人所在部门是否为特殊部门
		Integer count = dutyDeptService.searchDutyDeptById(dutyResult.getDutyDeptId());
		
		if(count != null && 0 == count.intValue()){//代表不是特殊部门
			//验证部门或个人所在部门是否为三种经营本部的部门
			count = dutyDeptService.searchDutyDeptType(dutyResult.getDutyDeptId());
			//count 0 表示三种经营本部之内部门 -1 表示之外部门
			count = (count != null && count == 1)?0:-1;
		}
		
		if(count == null){//若为null 则默认为不可反馈部门
			count = -1;
		}
		dutyResult.setDutyDepartmentCC(count.toString());
	}
	
	/**
	 * <p>
	 * Description:修改DUTY的为调查中<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-15
	 */
	private void updateDutySurveyResult(CommitDutyResultVO commitDutyResultVO,User user){
		//修改划分人
		Duty duty = new Duty();
		//id
		duty.setId(commitDutyResultVO.getDutyDetail().getId());
		//划分人ID
		duty.setAppDutyUserId(user.getId());
		//划分人名称
		duty.setAppDutyUser(user.getEmpCode().getEmpName());
		//划分人名称
		duty.setAppDutyTime(new Date());
		//修改时间
		duty.setModifyDate(new Date());
		//修改人
		duty.setModifyUser(user.getId());
		//修改duty的调查状态为调查中
		duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_INNER);
		//修改这些数据
		dutyService.updateDutyById(duty);
	}
	/**
	 * 
	 * <p>
	 * Description:确认无责<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public void sureNoResponsibility(Duty dutyDetail,User user) {
		//修改duty的调查状态为确认无责
		dutyService.updateSurveyResult(DutyConstants.DUTY_SURVEYRESULT_NORESPONSIBILITY, user.getId(), dutyDetail.getId());//调查完成（无责）
		//修改duty的状态为已划分
		dutyService.updateStatus(DutyConstants.ALLOCATED, user.getId(), dutyDetail.getId());//以划分状态
		DutyResult dutyResultSearch = new DutyResult();
		dutyResultSearch.setDutyId(dutyDetail.getId());
		List<DutyResult> dutyResultList = dutyResultService.searchDutyResult(dutyResultSearch);//查询该工单下的所有划分结果
		if(!CollectionUtils.isEmpty(dutyResultList)){
			for(DutyResult record :dutyResultList){//循环划分结果，删除该划分结果下的反馈记录
				dutyFeedbackService.deleteFeedbackByDetailId(record.getId());//删除该划分结果下的所有反馈记录
			}
		}
		dutyResultService.deleteDutyResultsByDutyIdStatus(dutyDetail.getId());//逻辑删除划分结果

		if(DutyConstants.ALLOCATED.equals(dutyDetail.getStatus())){//如果是以划分状态，则要发短信通知
			List<InformUser> informUserList = searchInformUsers(dutyDetail.getId());
			List<CellphoneMessageInfo> cellphoneMessageInfoList = convertToMsgInfo(dutyDetail.getDealNumber(),informUserList,DutyConstants.STSTUS_DELETE,null);
	    	cellphoneMessageInfoService.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
		}else{
			
		}
		informUserService.deleteInformUsersByDutyId(dutyDetail.getId());//删除通知对象
		 //处理经过(确认无责)
		DutyDealProcess dutyDealProcess = new DutyDealProcess();
		dutyDealProcess.setDutyId(dutyDetail.getId());
   	    dutyDealProcess.setOperaprId(user.getId());
   	    dutyDealProcess.setTreatStates(DutyConstants.TREATSTATUS_SYS);
   	    dutyDealProcess.setTreatProcess("确认无责");
   	    dutyDealProcess.setOpratorName(user.getEmpCode().getEmpName());
   	    dutyDealProcess.setOperatorTime(new Date());
   	    dutyDealProcessService.insertDutyDealProcess(dutyDealProcess);
   	    //填写的无责处理经过
   	    DutyDealProcess dutyDealProcessPerson = new DutyDealProcess();
   	    dutyDealProcessPerson.setDutyId(dutyDetail.getId());
   	    dutyDealProcessPerson.setOperaprId(user.getId());
   	    dutyDealProcessPerson.setTreatStates(DutyConstants.TREATSTATUS_PERSON);
   	    dutyDealProcessPerson.setTreatProcess(dutyDetail.getProcessPass());
   	    dutyDealProcessPerson.setOpratorName(user.getEmpCode().getEmpName());
   	    dutyDealProcessPerson.setOperatorTime(new Date());
	    dutyDealProcessService.insertDutyDealProcess(dutyDealProcessPerson);
	}
	/**
	 * 
	 * <p>
	 * Description:查询划分结果<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	@Override
	public List<DutyResult> searchDutyResults(String dutyId) {
		if(StringUtils.isNotEmpty(dutyId)){
			DutyResult dutyResult = new DutyResult();
			dutyResult.setDutyId(dutyId);
			dutyResult.setStatus(DutyConstants.EFFECTIVE);
			List<DutyResult> dutyResultList = dutyResultService.searchDutyResult(dutyResult);
			return dutyResultList;
		}else{
			DutyException e = new DutyException(
					DutyExceptionType.dutyIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:查询通知对象<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	@Override
	public List<InformUser> searchInformUsers(String dutyId) {
		if(StringUtils.isNotEmpty(dutyId)){
			InformUser informUser = new InformUser();
			informUser.setDutyId(dutyId);
			List<InformUser> informUserList = informUserService.searchInformUser(informUser);
			return informUserList;
		}else{
			DutyException e = new DutyException(
					DutyExceptionType.dutyIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:查询处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	@Override
	public List<DutyDealProcess> searchDutyDealProcess(String dutyId) {
		if(StringUtils.isNotEmpty(dutyId)){
			DutyDealProcess dutyDealProcess = new DutyDealProcess();
			dutyDealProcess.setDutyId(dutyId);
			List<DutyDealProcess> dutyDealProcessList = dutyDealProcessService.searchDutyDealProcess(dutyDealProcess);
			return dutyDealProcessList;
		}else{
			DutyException e = new DutyException(
					DutyExceptionType.dutyIdIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
	}
	/**
	 * 
	 * <p>
	 * Description:暂存责任划分结果<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-19
	 */
	@Override
	public void temporaryDutyResults(CommitDutyResultVO commitDutyResultVO,
			User user) {
		//处理经过
		DutyDealProcess dutyDealProcess = commitDutyResultVO.getDutyDealProcess();
		String dutyId = dutyDealProcess.getDutyId();
		dutyService.updateProcessPass(dutyDealProcess.getTreatProcess(), user.getId(),dutyId);
		//修改duty的状态
		dutyService.updateStatus(DutyConstants.TEMPORARY, user.getId(), dutyId);
		//划分结果
		if(CollectionUtils.isEmpty(commitDutyResultVO.getAddDutyResultRecords())){
			
		}else{
			for(DutyResult record : commitDutyResultVO.getAddDutyResultRecords()){
				record.setDutyStatus(DutyConstants.TEMPORARY);//状态为暂存
				record.setDutyId(dutyId);
				record.setStatus(DutyConstants.EFFECTIVE);
				this.settingDutyDeptType(record);//责任划分 对部门进行种类表示
				dutyResultService.insertDutyResult(record);
			}
		}
        if(CollectionUtils.isEmpty(commitDutyResultVO.getUpdateDutyResultRecords())){
			
		}else{
			for(DutyResult record : commitDutyResultVO.getUpdateDutyResultRecords()){
				record.setModifyUser(user.getId());
				this.settingDutyDeptType(record);//责任划分 对部门进行种类表示
				dutyResultService.updateDutyResult(record);
			}
		}
        
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDeleteDutyResultRecords())){
			
		}else{
			List<String> fids = new ArrayList<String>();
			for(DutyResult record : commitDutyResultVO.getDeleteDutyResultRecords()){
				fids.add(record.getId());
			}
			dutyResultService.deleteDutyResultsStatus(fids);
		}
        //通知对象
        if(CollectionUtils.isEmpty(commitDutyResultVO.getAddInformUserRecords())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getAddInformUserRecords()){
				record.setDutyId(dutyId);
				informUserService.insertInformUser(record);
			}
		}
        if(CollectionUtils.isEmpty(commitDutyResultVO.getUpdateInformUserRecords())){
			
		}else{
			for(InformUser record : commitDutyResultVO.getUpdateInformUserRecords()){
				informUserService.updateInformUser(record);
			}
		}
        
        if(CollectionUtils.isEmpty(commitDutyResultVO.getDeleteInformUserRecords())){
			
		}else{
			List<String> fids = new ArrayList<String>();
			for(InformUser record : commitDutyResultVO.getDeleteInformUserRecords()){
				fids.add(record.getId());
			}
			informUserService.deleteInformUsers(fids);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:查询最近的处理经过<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-20
	 */
	@Override
	public DutyDealProcess selectMaxDutyDealProcess(String dutyId) {
		DutyDealProcess dutyDealProcess = new DutyDealProcess();
		dutyDealProcess.setDutyId(dutyId);
		dutyDealProcess.setTreatStates(DutyConstants.TREATSTATUS_PERSON);
		return dutyDealProcessService.selectMaxDutyDealProcess(dutyDealProcess);
	}
	/**
	 * 
	 * <p>
	 * Description:责任认领<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-22
	 */
	@Override
	public void dutyClaim(String dutyId,String rsId, User user) {
		//1、	点击“确认”该工单责任划分结果中“调查结果”如果为“调查中”则更新为“成立”。
		//2、        责任认领后，反馈责任记录中反馈内容记录“责任认领”，并记录反馈人、反馈时间。
		DutyResult resut = dutyResultService.selectDutyResultByPrimaryKey(rsId);//查询被认领的责任划分结果
//		List<DutyResult> dutyResultList = searchDutyResults(dutyId);//查询该责任下的所有划分结果
		if(resut == null){//没有认领的划分结果
			DutyException e = new DutyException(DutyExceptionType.dutyClaimNoOne);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {
						private static final long serialVersionUID = 1L;
			};
		}
		
		if(//的时候才执行如下操作,划分结果的状态为责任认领
				DutyConstants.DUTY_STATUS_WAITING_FEEDBACK.equals(resut.getDutyStatus())//责任待反馈
				|| 
				DutyConstants.DUTY_STATUS_DUTY_TRUNING_BACK.equals(resut.getDutyStatus()) //责任退回（质检员）
		){
			if(DutyConstants.DEVIDETYPE_DEPT.equals(resut.getDivideType())){//如果划分类型为部门
				if(user.getEmpCode().getDeptId().getId().equals(resut.getDutyPerId())){
					dutyClaimOp(resut,user);
				}
			}else if(DutyConstants.DEVIDETYPE_EMP.equals(resut.getDivideType())){//如果划分类型为个人
				if(user.getEmpCode().getId().equals(resut.getDutyPerId())){
					dutyClaimOp(resut,user);
				}
			}
		}
		
		//查询出所有的责任划分结果
		List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
		List<String> statusList = new ArrayList<String>();
		//状态结果集合
		for(DutyResult dr:dutyList){
			statusList.add(dr.getSurveyResult());
		}
		//状态结果集不包含调查中，则更新责任状态为调查完成
		if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
			Duty duty = dutyService.getDutyDetail(dutyId);
			duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
			dutyService.updateDutyById(duty);
		}
//		if(!CollectionUtils.isEmpty(dutyResultList)){
//			boolean canClaim = false;//是否有认领的划分结果
//			for(DutyResult record : dutyResultList){
//				if(DutyConstants.DUTY_STATUS_WAITING_FEEDBACK.equals(record.getDutyStatus())
//						||DutyConstants.DUTY_STATUS_TRUNING_BACK.equals(record.getDutyStatus())
//						||DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK.equals(record.getDutyStatus())){//只有状态为责任待反馈,统计员反馈退回,质检员反馈退回的时候才执行如下操作,划分结果的状态为责任认领
//					if(DutyConstants.DEVIDETYPE_DEPT.equals(record.getDivideType())){//如果划分类型为部门
//						if(user.getEmpCode().getDeptId().getId().equals(record.getDutyPerId())){
//							canClaim= true;
//							dutyClaimOp(record,user);
//						}
//					}else if(DutyConstants.DEVIDETYPE_EMP.equals(record.getDivideType())){//如果划分类型为个人
//						if(user.getEmpCode().getId().equals(record.getDutyPerId())){
//							canClaim= true;
//							dutyClaimOp(record,user);
//						}else{}
//					}else{}
//				}
//			}
//			
//			//查询出所有的责任划分结果
//			List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
//			List<String> statusList = new ArrayList<String>();
//			//状态结果集合
//			for(DutyResult dr:dutyList){
//				statusList.add(dr.getSurveyResult());
//			}
//			//状态结果集不包含调查中，则更新责任状态为调查完成
//			if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
//				Duty duty = dutyService.getDutyDetail(dutyId);
//				duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
//				dutyService.updateDutyById(duty);
//			}
//			
//		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任认领具体数据库操作<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-22
	 */
	public void dutyClaimOp(DutyResult record, User user) {
		//将该责任下的所有dutystatus进行修改，修改为责任认领
	    DutyResult dutyResult = new DutyResult();
	    dutyResult.setId(record.getId());
	    dutyResult.setModifyUser(user.getId());
	    dutyResult.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_RIGHT);//调查结果是成立
	    if(DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK.equals(record.getDutyStatus())){//质检员退回状态，责任认领之后划分结果状态是”反馈无效“
		    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID );//反馈无效
	    }else{
		    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_DUTY_ACCEPT );//责任认领
	    }
	    dutyResultService.updateDutyResult(dutyResult);
	    //生成反馈记录
	    DutyFeedback dutyFeedback = new DutyFeedback();
	    dutyFeedback.setDetailId(record.getId());
	    dutyFeedback.setDutyId(record.getDutyId());
	    if(DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK.equals(record.getDutyStatus())){//质检员退回状态，责任认领之后反馈记录状态是”反馈无效“
	    	dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID );//反馈无效
	    }else{
	    	dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_DUTY_ACCEPT );//责任认领
	    }
	    dutyFeedback.setFeedUserId(user.getId());
	    dutyFeedback.setFeedUserName(user.getEmpCode().getEmpName());
	    dutyFeedback.setFeedDeptId(user.getEmpCode().getDeptId().getId());
	    dutyFeedback.setFeedDeptName(user.getEmpCode().getDeptId().getDeptName());
	    dutyFeedback.setDescribe(DutyConstants.DUTY_CLAIMC_CH);//中文“责任认领”
	    dutyFeedback.setFeedBackTime(new Date());
	    dutyFeedbackService.insertDutyFeedback(dutyFeedback);
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任反馈<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-22
	 */
	@Override
	public void dutyFeedBack(String dutyId,DutyFeedback dutyFeedback, User user,List<FeedAttach> fileInfoList) {
		//1、“责任反馈”文本框内容保存至“责任反馈记录”中“责任反馈内容”，
		//2、“提交”成功后该责任部门的“工单责任反馈”或“责任反馈记录”中的责任状态更新为“反馈待审批”，流入所选责任的部门中的统计员的工单责任审批中。
		List<DutyResult> dutyResultList = searchDutyResults(dutyId);//查询该责任下的所有划分结果
		if(!CollectionUtils.isEmpty(dutyResultList)){
			String detailId = dutyFeedback.getDetailId().trim();
			boolean canClaim = false;//是否有认领的划分结果
			for(DutyResult record : dutyResultList){
				//过滤掉未反馈的责任部门
				if(!record.getId().trim().equals(detailId)){continue;}
				if(
						DutyConstants.DUTY_STATUS_WAITING_FEEDBACK.equals(record.getDutyStatus())
						|| DutyConstants.DUTY_STATUS_DUTY_TRUNING_BACK.equals(record.getDutyStatus())
				){//只有责任待反馈/责任退回才能反馈信息
					if(DutyConstants.DEVIDETYPE_DEPT.equals(record.getDivideType())){//如果划分类型为部门
						if(user.getEmpCode().getDeptId().getId().equals(record.getDutyPerId())){
							canClaim= true;
							dutyFeedBack(record,user,dutyFeedback,fileInfoList);
						}
					}else if(DutyConstants.DEVIDETYPE_EMP.equals(record.getDivideType())){//如果划分类型为个人
						if(user.getEmpCode().getId().equals(record.getDutyPerId())){
							canClaim= true;
							dutyFeedBack(record,user,dutyFeedback,fileInfoList);
						}
					}
				}
			}
			if(!canClaim){
				DutyException e = new DutyException(
						DutyExceptionType.dutyFeedBackNoOne);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
				};
			}
		}
	}
	/**
	 * 
	 * <p>
	 * Description:责任反馈具体数据库操作<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-22
	 */
	public void dutyFeedBack(DutyResult record, User user,DutyFeedback dutyFeedback,List<FeedAttach> fileInfoList) {
		String dutyDepartmentCC = record.getDutyDepartmentCC();
		if(// 不满足反馈条件
				!DutyConstants.SPECIALDUTYDEPT.equals(dutyDepartmentCC) //1 特殊部门
				&&
				!DutyConstants.ORDINARYDUTYDEPT.equals(dutyDepartmentCC) //0 经营本部内的部门
		){
			DutyException e = new DutyException(
					DutyExceptionType.duty_not_FeedBack);//不满足反馈条件，不能反馈
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		if(record.getDutyDeadLine().getTime()<=new Date().getTime()){
			DutyException e = new DutyException(
					DutyExceptionType.hadFeedBackExtended);//判断反馈是否超期
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			};
		}
		
		
		//将该责任下的所有dutystatus进行修改，修改为反馈待审批
	    DutyResult dutyResult = new DutyResult();
	    dutyResult.setId(record.getId());
	    dutyResult.setModifyUser(user.getId());
//	    if(DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK.equals(record.getDutyStatus())){//质检员退回状态，反馈时划分结果状态是”反馈审批中“
//		    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_APPROVALING );//反馈审批中
//	    }else{
//		    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_APPROVAL );//责任待审批
//	    }
	    
	    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_APPROVAL );//责任待审批
	    dutyResultService.updateDutyResult(dutyResult);
	    //生成反馈记录
	   
	    dutyFeedback.setDetailId(record.getId());
	    dutyFeedback.setDutyId(record.getDutyId());
//	    if(DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK.equals(record.getDutyStatus())){//质检员退回状态，反馈时反馈记录状态是”反馈审批中“
//	    	dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_APPROVALING );//反馈审批中
//	    }else{
//	    	dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_WAITING_APPROVAL );//责任待审批
//	    }
	    dutyFeedback.setStatus(DutyConstants.DUTY_STATUS_WAITING_APPROVAL );//反馈待审批
	    
	    dutyFeedback.setFeedUserId(user.getId());
	    dutyFeedback.setFeedUserName(user.getEmpCode().getEmpName());
	    String deptId = user.getEmpCode().getDeptId().getId();
	    dutyFeedback.setFeedDeptId(deptId);
	    dutyFeedback.setFeedDeptName(user.getEmpCode().getDeptId().getDeptName());
	    dutyFeedback.setFeedBackTime(new Date());
	    //反馈部门为普通经营本部下的部门
	    if(DutyConstants.ORDINARYDUTYDEPT.equals(dutyDepartmentCC)){
	    	//查询出部门所在事业部的标杆编码
	    	String bussDeptCode=dutyDeptService.searchBusinessByDept(deptId);
	    	//将部门标杆编码存至反馈记录中
	    	dutyFeedback.setBussDeptCode(bussDeptCode);
	    }else if(DutyConstants.SPECIALDUTYDEPT.equals(dutyDepartmentCC)){
	    	dutyFeedback.setBussDeptCode("0");
	    }
	    else{
	    	//若不是经营本部下的事业部 或 为经营本部下事业部的特殊部门，所对应事业部字段值为“-1”
	    	dutyFeedback.setBussDeptCode("-1");
	    }
	    dutyFeedbackService.insertDutyFeedback(dutyFeedback);
	    if(!CollectionUtils.isEmpty(fileInfoList)){
	    	for (FeedAttach fileInfo : fileInfoList) {
				fileInfo.setCreateUser(user.getEmpCode().getId());
				fileInfo.setCreateDate(new Date());
				fileInfo.setFeedId(dutyFeedback.getFeedbackId());
				feedAttachService.insertFeedAttach(fileInfo);
			}
	    }
	}
	/**
	 * 
	 * <p>
	 * Description:反馈超期操作<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */
	@Override
	public void feedBackExtended() {
		DutyResult dutyResultSearchWaitFeedBack = new DutyResult();//查询待反馈的划分结果
		dutyResultSearchWaitFeedBack.setStatus(DutyConstants.EFFECTIVE);
		dutyResultSearchWaitFeedBack.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_FEEDBACK);//待反馈
		dutyResultSearchWaitFeedBack.setDutyDeadLine(new Date());
		List<DutyResult> dutyResultListWaitFeedBack = dutyResultService.searchDutyResult(dutyResultSearchWaitFeedBack);//查询所有的待反馈划分结果，超期的
		
		DutyResult dutyResultSearchInspectorBack = new DutyResult();//查询质检员退回的划分结果
		dutyResultSearchInspectorBack.setStatus(DutyConstants.EFFECTIVE);
		dutyResultSearchInspectorBack.setDutyStatus(DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK);//质检员退回
		dutyResultSearchInspectorBack.setDutyDeadLine(new Date());
		List<DutyResult> dutyResultListInspectorBack = dutyResultService.searchDutyResult(dutyResultSearchInspectorBack);//查询质检员退回划分结果，超期的
		
		DutyResult dutyResultSearchBack = new DutyResult();//查询待反馈的划分结果
		dutyResultSearchBack.setStatus(DutyConstants.EFFECTIVE);
		dutyResultSearchBack.setDutyStatus(DutyConstants.DUTY_STATUS_TRUNING_BACK);//统计员退回
		dutyResultSearchBack.setDutyDeadLine(new Date());
		List<DutyResult> dutyResultListBack = dutyResultService.searchDutyResult(dutyResultSearchBack);//查询所有统计员退回划分结果，超期的
		
		List<DutyResult> dutyResultList = new ArrayList<DutyResult>();
		dutyResultList.addAll(dutyResultListWaitFeedBack);
		dutyResultList.addAll(dutyResultListInspectorBack);
		dutyResultList.addAll(dutyResultListBack);
		Set<String> setDutyIds = new HashSet<String>();//存放dutyId
		if(!CollectionUtils.isEmpty(dutyResultList)){
			for(DutyResult record : dutyResultList){
				//将该责任下的所有dutystatus进行修改，修改为责任反馈超期
			    DutyResult dutyResult = new DutyResult();
			    dutyResult.setId(record.getId());
			    setDutyIds.add(record.getDutyId());//存入dutyId
			    dutyResult.setModifyUser(DutyConstants.SYS_USERID);
			    dutyResult.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_RIGHT);//调查结果是成立
			    if(DutyConstants.DUTY_STATUS_INSPECTOR_TRUNING_BACK.equals(record.getDutyStatus())){//质检员退回状态，责任认领之后划分结果状态是”反馈无效“
				    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_FEEDBACK_INVALID );//反馈无效
			    }else{
				    dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_FEEDBACK_EXCEED );//责任反馈超期
			    }
			    dutyResultService.updateDutyResult(dutyResult);
			}
		}
		if(!CollectionUtils.isEmpty(setDutyIds)){
			//拿出所有的dutyId便利，看其划分结果是否为全部的成立或者不成立
			for(String dutyId:setDutyIds){
				//查询出所有的责任划分结果
				List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
				List<String> statusList = new ArrayList<String>();
				//状态结果集合
				for(DutyResult dr:dutyList){
					statusList.add(dr.getSurveyResult());
				}
				//状态结果集不包含调查中，则更新责任状态为调查完成
				if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
					Duty duty = dutyService.getDutyDetail(dutyId);
					duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
					dutyService.updateDutyById(duty);
				}
			}
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:审批超期操作<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-4-8
	 */
	@Override
	public void feedBackApprovalExtended() {
		DutyResult dutyResultSearchApproval = new DutyResult();//查询待审批的划分结果
		dutyResultSearchApproval.setStatus(DutyConstants.EFFECTIVE);
		dutyResultSearchApproval.setDutyStatus(DutyConstants.DUTY_STATUS_WAITING_APPROVAL);//责任待审批
		dutyResultSearchApproval.setDutyDeadLine(new Date());
		List<DutyResult> dutyResultListApproval = dutyResultService.searchDutyResult(dutyResultSearchApproval);//查询所有的待审批划分结果，超期的
		Set<String> setDutyIds = new HashSet<String>();//存放dutyId
		if(!CollectionUtils.isEmpty(dutyResultListApproval)){
			for(DutyResult record : dutyResultListApproval){
				//修改反馈信息中的数据
				DutyFeedback dutyFeedback = dutyFeedbackService.searchDutyFeedbackByDutyResultId(record.getId());
				if(dutyFeedback!=null&&dutyFeedback.getFeedbackId()!=null&&!"".equals(dutyFeedback.getFeedbackId().trim())){
					DutyFeedback updateFeedback = new DutyFeedback();
					updateFeedback.setFeedbackId(dutyFeedback.getFeedbackId());
					updateFeedback.setStatus(DutyConstants.DUTY_STATUS_APPROVAL_EXCEED);//审批超期
					dutyFeedbackService.updateDutyFeedbackAll(updateFeedback);
				}
				//将该责任下的所有dutystatus进行修改，修改为责任审批超期
			    DutyResult dutyResult = new DutyResult();
			    dutyResult.setId(record.getId());
			    setDutyIds.add(record.getDutyId());
			    dutyResult.setModifyUser(DutyConstants.SYS_USERID);
			    dutyResult.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_RIGHT);//调查结果是成立
				dutyResult.setDutyStatus(DutyConstants.DUTY_STATUS_APPROVAL_EXCEED );//审批超期
			    dutyResultService.updateDutyResult(dutyResult);
			}
		}
		if(!CollectionUtils.isEmpty(setDutyIds)){
			//拿出所有的dutyId便利，看其划分结果是否为全部的成立或者不成立
			for(String dutyId:setDutyIds){
				//查询出所有的责任划分结果
				List<DutyResult> dutyList = dutyService.getDutyResult(dutyId);
				List<String> statusList = new ArrayList<String>();
				//状态结果集合
				for(DutyResult dr:dutyList){
					statusList.add(dr.getSurveyResult());
				}
				//状态结果集不包含调查中，则更新责任状态为调查完成
				if(!statusList.contains(DutyConstants.DUTY_SURVEYRESULT_DOING)){
					Duty duty = dutyService.getDutyDetail(dutyId);
					duty.setSurveyResult(DutyConstants.DUTY_SURVEYRESULT_COMPLETION);
					dutyService.updateDutyById(duty);
				}
			}
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:责任反馈主查询（当前登录用户被划分的责任）<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-23
	 */
	@Override
	public Map searchDutyFeedBackMain(User user,int start,int limit) {
		List<SearchDutyResultVO> list =  dutyResultService.searchDutyFeedBackMain(user,start,limit);
		Integer totalCount = dutyResultService.searchDutyFeedBackMainCount(user);
		//封装到map中返回
		Map map = new HashMap();
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}
	/**
	 * 
	 * <p>
	 * Description:查询附件信息<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-3-27
	 */
	@Override
	public List<FeedAttach> searchFeedBackFileInfo(String feedBackId) {
		FeedAttach fileInfo = new FeedAttach();
		fileInfo.setFeedId(feedBackId);
		return feedAttachService.searchFeedAttach(fileInfo);
	}
	/**
	 * <p>
	 * Description:批量删除附件信息<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-27
	 * @param fids
	 * @return void
	 */
	@Override
	public void deleteFeedAttachs(List<String> feedAttachIds) {
		feedAttachService.deleteFeedAttachs(feedAttachIds);
	}
	/**
	 * <p>
	 * Description:查询统计员部门<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-28
	 * @param feedAttach
	 * @return void
	 */
	@Override
	public List<Department> searchStatisticiansDept() {
		return feedAttachService.searchStatisticiansDept();
	}
	/**
	 * 
	 * <p>
	 * 得到反馈超期日期<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-1
	 * @param date
	 * @return
	 * Date
	 */
	@Override
	public Date feedBackExtendedTime(int day){
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int wd = 0;
		if(day>100){
			day = 100;
		}
		if(day<1){
			return null;
		}
		while(true){
			//加24小时
			cal.add(Calendar.HOUR, 24);
			//wd>0 表示24小时后的日期属于工作日
			wd = specialDayManager.getActualWorkdays(date, cal.getTime())-1;//把当天除掉
			if(wd == day){
				break;
			}
		}
		return cal.getTime();
	}
	/**
	 * <p>
	 * Description:根据工单责任ID查询工单责任划分结果
	 * </p>
	 * @author 	zouming
	 * @extends	@see com.deppon.crm.module.duty.server.manager.IDutyManager#searchDutyResult(java.lang.String)
	 * @version 0.1 2013-4-27下午2:35:39
	 * @param dutyId
	 * @return
	 * @update 	2013-4-27下午2:35:39
	 */
	@Override
	public List<DutyResult> searchDutyResult(String dutyId) {
		return dutyService.getDutyResult(dutyId);
	}
	/**
	 * <p>
	 * Description:查询统计员所在部门<br />
	 * @author 张斌
	 * @param feedbackId
	 * @return
	 * @version V0.1 
	 * @Date 2013-6-8
	 */
	@Override
	public String searchStatDeptName(String feedbackId) {
		return dutyFeedbackService.searchStatDeptName(feedbackId);
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
	public void saveDutyDept(DutyDept dutyDept,User user){
		//校验工单责任部门是否为空
		DutyValidator.validDutyDeptIsNull(dutyDept);
		//查询工单责任部门是否已经存在，若存在返回1，不存在返回0
		int isTrue=searchDutyDeptById(dutyDept.getDeptId().toString());
		//若工单特殊责任部门已经存在，向前台抛出异常
		DutyValidator.validDutyDeptIsHave(isTrue);
		//添加创建者ID
		dutyDept.setCreateUser(user.getId());
		//保存工单特殊责任部门
		dutyDeptService.saveDutyDept(dutyDept);
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
		return dutyDeptService.searchDutyDeptById(deptId);
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
		//校验责任部门集合是否为空
		DutyValidator.validDutyDeptList(dutyDeptList);
		//删除工单特殊责任部门集合
		dutyDeptService.deleteDutyDept(dutyDeptList);
	}
	/**
	 * <p>
	 *	Description: 查询工单特殊责任部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-9
	 * @return
	 * List<DutyDept>
	 */
	public List<DutyDept> searchDutyDepts(){
		return dutyDeptService.searchAllDutyDept();
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
	@Override
	public boolean isSameDepartmentByDeptIds(DutyResult reuslt,User user){
		List<String> deptIdList = new ArrayList<String>();
		String divideType = reuslt.getDivideType();
		if(DutyConstants.DEVIDETYPE_DEPT.equals(divideType)){//划分类型若为部门
			reuslt.setDutyDeptId(reuslt.getDutyPerId());
		}else if(DutyConstants.DEVIDETYPE_EMP.equals(divideType)){//划分类型若为个人
			//查询员工所在部门
			Department dept =dutyDeptService.searchDepartmentByEmpId(reuslt.getDutyPerId());
			if(dept != null){
				reuslt.setDutyDeptId(dept.getId());
			}
		}
		//添加赛选出来的部门编号
		deptIdList.add(reuslt.getDutyDeptId());
		
		//当前登录人所在部门编号
		deptIdList.add(user.getEmpCode().getDeptId().getId());
		
		if(dutyDeptService.isSameDepartmentByDeptIds(deptIdList)==false){
			DutyException e = new DutyException(
					DutyExceptionType.isNotSameBusinessDepartment);//所选部门或个人非本事业部，请重新选择。
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
						private static final long serialVersionUID = 1L;
			};
		}
		return true;
	}
}