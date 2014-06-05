package com.deppon.crm.module.duty.server.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.util.DateUtils;
import com.deppon.crm.module.complaint.server.util.DateUtil;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.exception.DutyException;
import com.deppon.crm.module.duty.shared.exception.DutyExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * 
 * <p>
 * Description:工单责任验证<br />
 * </p>
 * 
 * @author 钟琼
 * @version 0.1 2013-3-7
 */
public class DutyValidator {
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询验证<br />
	 * </p>
	 * 
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	@SuppressWarnings({ "serial", "unused" })
	public static void queryDutyListValidator(
			QueryDutyCondition queryDutyCondition) {
		// 工单责任查询条件为空
		if (null == queryDutyCondition) {
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListConditionIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 上报开始时间
		Date reportbeginDate = queryDutyCondition.getReportTimeBegin();
		// 上报结束时间
		Date reportendDate = queryDutyCondition.getReportTimeEnd();
		// 反馈开始时间
		Date feedbackBeginDate = queryDutyCondition.getFeedBackTimeBegin();
		// 反馈结束时间
		Date feedbackEndDate = queryDutyCondition.getFeedBackTimeEnd();
		// 责任划分开始时间
		Date appDutyTimeBegin = queryDutyCondition.getAppDutyTimeBegin();
		// 责任划分结束时间
		Date appDutyTimeEnd = queryDutyCondition.getAppDutyTimeEnd();

		if(2 == DutyUtils.dateIsNull(reportbeginDate, reportendDate)){
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListTimeIsLose);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 工单责任查询条件-上报时间大于31天
		Long reportTime = DutyUtils.differ(reportbeginDate, reportendDate);
		if ((3 == DutyUtils.dateIsNull(reportbeginDate, reportendDate))&& (reportTime > 2678400000L || reportTime < 0L)) {
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListReportTimeIsLong);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		if(2 == DutyUtils.dateIsNull(feedbackBeginDate, feedbackEndDate)){
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListTimeIsLose);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 工单责任查询条件-反馈时间大于31天
		Long feedbackTime = DutyUtils.differ(feedbackBeginDate, feedbackEndDate);
		if ((3 == DutyUtils.dateIsNull(feedbackBeginDate, feedbackEndDate))&&(feedbackTime > 2678400000L || feedbackTime < 0L)) {
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListFeedbackTimeIsLong);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		if(2 == DutyUtils.dateIsNull(appDutyTimeBegin, appDutyTimeEnd)){
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListTimeIsLose);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 工单责任查询条件-责任划分时间大于31天
		Long appDutyTime = DutyUtils.differ(appDutyTimeBegin, appDutyTimeEnd);
		if ((3 == DutyUtils.dateIsNull(appDutyTimeBegin, appDutyTimeEnd))&&(appDutyTime > 2678400000L || appDutyTime < 0L)) {
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListAppDutyTimeIsLong);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 责任划分人
		String appDutyUser = queryDutyCondition.getAppDutyUser(); 
		// 责任部门
		String dutyDept = queryDutyCondition.getDutyDept(); 
		// 反馈部门
		String feedbackDept = queryDutyCondition.getFeedbackDept();
		// 责任状态
		String dutyStates = queryDutyCondition.getDutyStates(); 
		// 调查结果
		String surveyResult = queryDutyCondition.getSurveyResult(); 
		// 呼叫中心审批人
		String callCenterUser = queryDutyCondition.getCallCenterUser();
		// 3种查询时间
		Date[] queryDate = {reportbeginDate,feedbackBeginDate,appDutyTimeBegin};
		//责任划分人、责任部门、反馈部门、调查结果、责任状态、呼叫中心审批人组合查询时必须有一
		//个时间条件。否则提示“请选择查询时间段”
		if(!DutyUtils.checkArrayCondition(appDutyUser,dutyDept,feedbackDept,dutyStates,surveyResult,callCenterUser,queryDate)){
			DutyException e = new DutyException(
					DutyExceptionType.queryDutyListArrayConditionIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * <p>
	 * Description:检查接入工单责任时是否查询到未被接入的工单
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static void checkReceiveDutyNum(List<Duty> dutys){
		if( dutys == null || dutys.size()<1 ){
			DutyException e = new DutyException(
					DutyExceptionType.searchDutyReceiveIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * <p>
	 * Description:检查接入工单是否大于最大接入数
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static void checkMaxReceiveDutyNum(int num,int maxNum){
		if( num >= maxNum){
			DutyException e = new DutyException(
					DutyExceptionType.searchMaxDutyReceiveBeyondMaxReceive);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * <p>
	 * Description:检查是否有未接入的工单
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static void checkUnreceiveDutyNum(int num){
		if( num <= 0){
			DutyException e = new DutyException(
					DutyExceptionType.searchDutyReceiveIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * <p>
	 * Description:检查上报类型是否合法
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	@SuppressWarnings("serial")
	public static void checkReportType(String reportType){
		//如果上报类型既不是投诉也不是异常
		if( !DutyConstants.DUTYREPORTTYPE_COMPLAINT.equals(reportType) 
				&& !DutyConstants.DUTYREPORTTYPE_UNUSUAL.equals(reportType)){
			DutyException e = new DutyException(
					DutyExceptionType.searchDutyReceiveReportTypeIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:检查责任ID是否为空<br />
	 * @author xiaohongye
	 * @param detailId
	 * @version V0.1 
	 * @Date 2013-3-14
	 */
	@SuppressWarnings("serial")
	public static void checkDutyId(String dutyId){
		//校验传入的工单责任划分ID是否为空
		if(dutyId == null || "".equals(dutyId)){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_FEEDBACK_DUTYID_CAN_NOT_NULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:检查责任反馈ID是否为空<br />
	 * @author xiaohongye
	 * @param feedbackId
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@SuppressWarnings("serial")
	public static void checkFeedbackId(String feedbackId){
		//校验传入的工单责任反馈ID是否为空
		if(feedbackId == null || "".equals(feedbackId.trim())){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_FEEDBACK_FEEDBACKID_CAN_NOT_NULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:检查责任划分结果ID是否为空<br />
	 * @author xiaohongye
	 * @param detailId
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@SuppressWarnings("serial")
	public static void checkDetailId(String detailId){
		//校验传入的工单责任划分结果ID是否为空
		if(detailId == null || "".equals(detailId.trim())){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_FEEDBACK_DETAILID_CAN_NOT_NULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	
	/**
	 * <p>
	 * Description:校验进行此操作的用户是否拥有工单责任审批统计员的角色<br />
	 * @author xiaohongye
	 * @param user
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@SuppressWarnings("serial")
	public static void checkRoleOfStatistics(User user){
		//校验进行工单责任审批的人员是否具有统计员的角色
		if((user == null) || (user.getRoleids() == null) || (!(user.getRoleids().contains(DutyConstants.STATISTICS_ROLE_ID)))){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_APPROVAL_ROLE_NOT_STATISTICS);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:校验进行此操作的用户是否拥有工单责任审批质检员的角色<br />
	 * @author suyujun
	 * @param user
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@SuppressWarnings("serial")
	public static void checkRoleOfCaller(User user){
		//校验进行工单责任审批的人员是否具有质检员的角色
		if((user == null) || (user.getRoleids() == null) || (!(user.getRoleids().contains(DutyConstants.QUALITYINSPECTOR_ROLE_ID))) || 
				(!(user.getRoleids().contains(DutyConstants.BUSQUALITYINSPECTOR_ROLE_ID)))){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_APPROVAL_ROLE_NOT_STATISTICS);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:校验进行此操作的用户是否拥有审批此条工单责任反馈的权限<br />
	 * @author xiaohongye
	 * @param user
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@SuppressWarnings("serial")
	public static void checkRoleOfApproval(User user,String staDeptId){
		if(!(user.getEmpCode().getDeptId().getId().equals(staDeptId.trim()))){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_APPROVAL_ROLE_CAN_NOT_DO);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:检查统计员审批时责任状态是否为责任待审批<br />
	 * @author xiaohongye
	 * @param feedbackId
	 * @version V0.1 
	 * @Date 2013-3-20
	 */
	@SuppressWarnings("serial")
	public static void checkStatisDutyStatus(String dutyStatus){
		if(!(DutyConstants.DUTY_STATUS_WAITING_APPROVAL.equals(dutyStatus))){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_APPROVAL_STATISTICS_CAN_NOT_DO);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * <p>
	 * Description:统计员审批出现异常，无法判断审批类型是同意还是退回<br />
	 * @author xiaohongye
	 * @param approvalType
	 * @version V0.1 
	 * @Date 2013-3-22
	 */
	@SuppressWarnings("serial")
	public static void checkStatApprovalType(String approvalType){
		if(!(DutyConstants.STATISTICS_APPROVAL_DISAGREE.equals(approvalType)) &&
				!(DutyConstants.STATISTICS_APPROVAL_AGREE.equals(approvalType))){
			DutyException e = new DutyException(
					DutyExceptionType.DUTY_APPROVAL_STATISTICS_CAN_NOT_APPROVAL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	/**
	 * 
	 * <p>
	 * 检验查询条件的业务规则<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-12
	 * @param queryDutyCondition
	 * void
	 */
	@SuppressWarnings("serial")
	public static void validateCondition(QueryDutyCondition queryDutyCondition) {
		Date startTime = queryDutyCondition.getFeedBackTimeBegin();
		Date endTime = queryDutyCondition.getFeedBackTimeEnd();
		if(startTime!=null && endTime!=null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			cal.add(Calendar.DATE, +31);
			Calendar calend = Calendar.getInstance();
			calend.setTime(endTime);
			if (cal.before(calend)) {
				DutyException e = new DutyException(DutyExceptionType.queryDutyListFeedbackTimeIsLong);
				throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
						new Object[]{}) {
				};
			}			
		}
	}
	/**
	 * <p>
	 * 校验当前用户是否有质检员角色<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-20
	 * @param user
	 * void
	 */
	@SuppressWarnings("serial")
	public static void checkRoleOfQualityInspector(User user) {
		Set<String> roles = user.getRoleids();
		if(roles==null || roles.size() == 0 ){
			if(
					!roles.contains(DutyConstants.QUALITYINSPECTOR_ROLE_ID)  &&
					!roles.contains(DutyConstants.BUSQUALITYINSPECTOR_ROLE_ID)
			){
				DutyException e = new DutyException(DutyExceptionType.DUTY_APPROVAL_ROLE_NOT_CALLER);
				throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
						new Object[]{}) {
				};
			}
		}
	}
	/**
	 * <p>
	 * 校验参数是否为空<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-20
	 * @param dutyDivideId
	 * void
	 */
	@SuppressWarnings("serial")
	public static void validateParam(String dutyDivideId,String feedBackId) {
		if(StringUtils.isEmpty(dutyDivideId)){
			DutyException e = new DutyException(DutyExceptionType.DUTY_FEEDBACK_DETAILID_CAN_NOT_NULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
			};
		}
		if(StringUtils.isEmpty(feedBackId)){
			DutyException e= new DutyException(DutyExceptionType.DUTY_FEEDBACK_FEEDBACKID_CAN_NOT_NULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
			};
		}
	}
	/**
	 * <p>
	 * 检验审批意见是否填写<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-21
	 * @param appOpinion
	 * void
	 */
	@SuppressWarnings("serial")
	public static void validateAppOpinion(String appOpinion) {
		if(StringUtils.isEmpty(appOpinion)){
			DutyException e = new DutyException(DutyExceptionType.APPROVAL_OPINION_IS_NULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
					new Object[]{}) {
			};
		}
	}
	/**
	 * <p>
	 * 检验审批依据是否填写<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-21
	 * @param appOpinion
	 * void
	 */
	@SuppressWarnings("serial")
	public static void validateAppAccording(String according) {
		if(StringUtils.isEmpty(according)){
			DutyException e = new DutyException(DutyExceptionType.APPROVAL_ACCORDING_IS_NULL);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
					new Object[]{}) {
			};
		}
	}
	/**
	 * <p>
	 * 验证审批意见和审批依据<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-21
	 * @param appOpinion
	 * @param according
	 * void
	 */
	public static void validateAppOpinionAndAccording(String appOpinion, String according) {
		validateAppOpinion(appOpinion);
		validateAppAccording(according);
	}
	/**
	 * <p>
	 * 校验是否有查询结果<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-21
	 * @param dutys
	 * @param according
	 * void
	 */
	public static void checkSearchDutyList(int total){
		if( total == 0){
			DutyException e = new DutyException(DutyExceptionType.searchDutyListIsNull);
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
					new Object[]{}) {
			};
		}
	}
	public static void validateFeedBackAppStatus(DutyFeedback feedback) {
		String appStatus = feedback.getStatus();
		if(StringUtils.isNotEmpty(appStatus)){
			if(	
					DutyConstants.DUTY_STATUS_FEEDBACK_VALID.equals(appStatus) || //反馈有效
					DutyConstants.DUTY_STATUS_FEEDBACK_INVALID.equals(appStatus) || //反馈无效
					DutyConstants.DUTY_STATUS_DUTY_TRUNING_BACK.equals(appStatus)
			){
				DutyException e = new DutyException(DutyExceptionType.feedbackAlreadyProcessed);
				throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				};
			}
		}
	}
	/**
	 * <p>
	 *	Description: 判断特殊部门是否为空
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param dutyDept
	 * void
	 */
	public static void validDutyDeptIsNull(DutyDept dutyDept){
		if(null==dutyDept){
			DutyException e = new DutyException(
					DutyExceptionType.DUTYDEPTISNULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		
	}
	/**
	 * <p>
	 *	Description: 判断特殊责任部门是否存在
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-10
	 * @param isHave
	 * void
	 */
	public static void validDutyDeptIsHave(int isHave){
		if(1==isHave){
			DutyException e = new DutyException(
					DutyExceptionType.DUTYDEPTISHAVE);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	
	public static void validDutyDeptList(List<DutyDept> dutyDeptList){
		if(null == dutyDeptList){
			DutyException e = new DutyException(
					DutyExceptionType.DUTYDEPTLISTISNULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
}
