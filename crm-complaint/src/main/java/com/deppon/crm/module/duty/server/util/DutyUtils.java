package com.deppon.crm.module.duty.server.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;

/**
 * 
 * <p>
 * Description:工单责任辅助类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2013-3-7
 */
public class DutyUtils {
	/**
	 * 
	 * <p>
	 * Description:工单责任辅助类-两个时间相隔天数<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	public static Long differ(Date date1, Date date2) {
		if( null == date1 || null == date2){
			return 0L;
		}
		return date2.getTime()- date1.getTime();
	} 
	/**
	 * 
	 * <p>
	 * Description:工单责任辅助类-判断时间是否为空<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	public static boolean dateIsNull(Date date) { 
		if(null == date){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:工单责任辅助类-判断时间是否为空<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	public static int dateIsNull(Date date1,Date date2) { 
		boolean beginDate = dateIsNull(date1);
		boolean endDate = dateIsNull(date2);
		if(beginDate && endDate){
			// 时间全为空
			return 1;
		}
		if(beginDate ^ endDate){
			// 有一个时间为空
			return 2;
		}
		// 时间全不为空
		return 3;
	}
	/**
	 * <p>
	 * Description:工单责任辅助类-责任划分人、责任部门、反馈部门、调查结果、责任状态、呼叫中心审批人组合查询时必须有一
	 * 个时间条件。否则提示“请选择查询时间段”<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	public static boolean checkArrayCondition(String appDutyUser,
			String dutyDept, String feedbackDept,String dutyStates, 
			String surveyResult,String callCenterUser, Date[] queryDate) {
		if(objHasNull(appDutyUser,dutyDept,feedbackDept,dutyStates,surveyResult,callCenterUser)){
			return  true;
		}
		if(objHasNotNull(queryDate)){
			return true;
		}
		return false;
	}
	/**
	 * <p>
	 * Description:检查数组是否全为空
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	public static boolean objHasNull(String... object){
		for(String str : object){
			if(StringUtils.isEmpty(str)){
				return true;
			}
		}
		return false;
	}
	/**
	 * <p>
	 * Description:检查数组是否存在空
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	public static boolean objHasNotNull(Date[] queryDate){
		for(Date date : queryDate){
			if(null != date){
				return true;
			}
		}
		return false;
	}
	/**
	 * <p>
	 * Description:工单接入将未接入的工单设置为接入工单
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static Duty convertUpdateDuty(Duty duty,User user){
		
		//设置为已接入
		duty.setStatus(DutyConstants.RECEIVED);
		//设置接入人
		duty.setReceiveUser(user.getEmpCode().getEmpName());
		//设置接入人ID
		duty.setReceiveUserId(user.getId());
		//设置接入时间
		duty.setReceiveTime(new Date());
		//设置修改人ID
		duty.setModifyUser(user.getId());
		//设置修改时间
		duty.setModifyDate(new Date());
		//设置未被接入状态
		duty.setUnReceiveStatus(DutyConstants.UNRECEIVE);
		return duty;
	}
	/**
	 * <p>
	 * Description:封装查询已被接入工单的查询实体
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static SearchDutyCondition createSearchReceiveDutyCondition(User user){
		SearchDutyCondition sdc = new SearchDutyCondition();
		//设置用户ID
		sdc.setReceiveUserId(user.getId());
		//设置上报类型
		sdc.setReportType(getSearchReportType(user.getRoleids()));
		//设置状态为已接入
//		sdc.setStatus(DutyConstants.RECEIVED);
		List<String> status = new ArrayList<String>();
		//已接入
		status.add(DutyConstants.RECEIVED);
		//暂存
		status.add(DutyConstants.TEMPORARY);
		//查询暂存和已接入的共担责任
		sdc.setStatus(status);
		return sdc;
	}
	/**
	 * <p>
	 * Description:根据用户权限获得相应的上报类型
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static String getSearchReportType(Set<String> roleIds){
		String reportType = null;
		//如果权限包含投诉处理员或投诉处理经理则
		if( roleIds.contains(DutyConstants.RECEIVE_AUTH_COMPLAINTEMP) || 
				roleIds.contains(DutyConstants.RECEIVE_AUTH_COMPLAINTMAN)){
			//上报类型为投诉
			reportType = DutyConstants.DUTYREPORTTYPE_COMPLAINT;
			//如果权限包含异常处理员或异常处理经理则
		}else if(roleIds.contains(DutyConstants.RECEIVE_AUTH_UNUSUALEMP) || 
				roleIds.contains(DutyConstants.RECEIVE_AUTH_UNUSUALMAN)){
			//上报类型为异常
			reportType = DutyConstants.DUTYREPORTTYPE_UNUSUAL;
		}
		DutyValidator.checkReportType(reportType);
		return reportType;
	}

	/**
	 * <p>
	 * Description:设置经理查询条件
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static SearchDutyCondition getManagerSearchDutyCondition(SearchDutyCondition sdc,User user){
		//根据权限查询上报类型并设置上报类型
		sdc.setReportType(DutyUtils.getSearchReportType(user.getRoleids()));
		List<String> status = new ArrayList<String>();
		//如果为第一次进入页面
		if(sdc.getFirstLoad()==DutyConstants.FIRST_LOAD_IN_MANAGER){
			//未接入
			status.add(DutyConstants.UNRECEIVE);
		//如果不是第一次进入页面
		}else{
			status.add(DutyConstants.RECEIVED);
			status.add(DutyConstants.TEMPORARY);
		}
		//查询
		sdc.setStatus(status);
		return sdc;
	}
	/**
	 * <p>
	 * Description:设置未被接入的工单条件
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static SearchDutyCondition getUnreceiveDutyCondition(User user){
		SearchDutyCondition sdc = new SearchDutyCondition();
		//设置状态为已接入
		List<String> status = new ArrayList<String>();
		status.add(DutyConstants.UNRECEIVE);
		sdc.setStatus(status);
		//设置上报类型
		sdc.setReportType(DutyUtils.getSearchReportType(user.getRoleids()));
		return sdc;
		
	}
	/**
	 * <p>
	 * Description:设置已接入的工单条件
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-8
	 */
	public static SearchDutyCondition getReceiveDutyCondition(User user){
		SearchDutyCondition sdc = new SearchDutyCondition();
		//将用户ID加入到查询条件
		sdc.setReceiveUserId(user.getId());
		//设置状态为已接入
		List<String> status = new ArrayList<String>();
		//已接入
		status.add(DutyConstants.RECEIVED);
		//暂存
		status.add(DutyConstants.TEMPORARY);
		sdc.setStatus(status);
		//设置上报类型
		sdc.setReportType(DutyUtils.getSearchReportType(user.getRoleids()));
		return sdc;
		
	}
}
