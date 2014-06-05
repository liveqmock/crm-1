/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReturnVisitUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhuPJ
 * @version 0.1 2012-4-1
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.MarketingConstance;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.Schedule;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.AnswerMainInfo;
import com.deppon.crm.module.marketing.shared.exception.ReturnVisitException;
import com.deppon.crm.module.marketing.shared.exception.ReturnVisitExceptionType;

/**
 * 
 * <p>
 * 回访验证工具类
 * </p>
 * @title ReturnVisitValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author 苏玉军
 * @version 0.1 2013-1-28
 */
public class ReturnVisitValidateUtils {

	/**
	 * <p>
	 * 验证回访查询条件
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-18
	 * @param condition
	 * @return
	 * boolean
	 */
	public static boolean canGetReturnVisitList(ReturnVisitQueryCondition condition){
		// 回访录入查询-
		if (condition == null) { 
			//查询条件不能全为空
			throw new ReturnVisitException(ReturnVisitExceptionType.queryConditionIsNull);
		}
		// 回访录入查询-
		if (!condition.validateBeginEndTime()) {
			//结束时间不能早于开始时间，查询时间差不能超过3个月
			throw new ReturnVisitException(ReturnVisitExceptionType.beginEndTimeOffset);
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description: 验证可否创建回访信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-12
	 * @param rv
	 * @param rvo
	 * @return
	 * boolean
	 */
	public static boolean canCreateReturnVisit(ReturnVisit rv, List<ReturnVisit> list,
			List<ReturnVisitOpinion> rvo,List<AnswerMainInfo> answerMainInfoList) {
		// 已回访过-
		if (list != null && list.size() > 0){
			//不可重复回访
			throw new ReturnVisitException(
					ReturnVisitExceptionType.dontRepeatRV);
		}
		// 无法保存-
		if (rv == null){
			//回访信息错误
			throw new ReturnVisitException(
					ReturnVisitExceptionType.TrackDateError);
		}
		if(CollectionUtils.isEmpty(answerMainInfoList)){
			// 无法保存-
			if (rvo == null || rvo.isEmpty()){
				//未填写客户意见
				throw new ReturnVisitException(
						ReturnVisitExceptionType.TrackDateError);
			}
		}
		// 无法保存-
		if (StringUtils.isEmpty(rv.getWay())){
			//未选中回访类型
			throw new ReturnVisitException(
					ReturnVisitExceptionType.notTraceWay);
		}
		return true;
	}

	/**
	 * <p>
	 * Description: 验证可否创建跟踪日程<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-4-10
	 * @param rv
	 * @return
	 * boolean
	 */
	public static boolean canCreateSchedule(ReturnVisit rv){
		//日程时间
		Date d1 = rv.getSchedule();
		//为空返回false
		if (d1 == null) return false;
		//与当前时间进行比较
		int i = PlanValidateUtils.compareToDate(d1, new Date());
		//回访时间小于当前日期
		if (i<0){
			return false;
		}
		return true;
		
	}
	
	/**
	 * 
	 * <p>
	 * 初始化回访页面数据
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param rv
	 * @param list
	 * @param user
	 * @return
	 * boolean
	 */
	public static boolean canInitCreatePageData(ReturnVisit rv, List<ReturnVisit> list, User user){
		if (list != null && list.size() > 0){
			// 已回访过			
			throw new ReturnVisitException(
					ReturnVisitExceptionType.dontRepeatRV);
		}
		
		if (rv == null){
			//初始化异常
			throw new ReturnVisitException(
					ReturnVisitExceptionType.initCreatePageFail);
		}
		// 缺少日程ID——
		if (StringUtils.isEmpty(rv.getScheduleId())){
			//初始化异常
			throw new ReturnVisitException(
					ReturnVisitExceptionType.initCreatePageFail);
		}
		// 缺少回访类型-
//		if (StringUtils.isEmpty(rv.getScheType())) {
//			//初始化异常
//			throw new ReturnVisitException(
//					ReturnVisitExceptionType.initCreatePageFail);
//		}
		return true;
	}
	
	/**
	 * 
	 * <p>
	 * 验证日程是否可回访<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param sche
	 * @param user
	 * @return
	 * boolean
	 */
	public static boolean canReturnVisit(Schedule sche, User user){
		// 此日程状态不允许 回访
		if (MarketingConstance.SCHEDULE_OVERDUE.equals(sche.getStatus())){
			//不允许回访
			throw new ReturnVisitException(
					ReturnVisitExceptionType.returnVisitSceStatusError);
		}
		// 未设置执行人-
		if (sche.getExeManId() == null){
			//回访数据异常，无法正常执行
			throw new ReturnVisitException(
					ReturnVisitExceptionType.returnVisitDataError);
		}
		// 执行人不一致-
		if (!sche.getExeManId().equals(user.getEmpCode().getId())){
			//无权回访
			throw new ReturnVisitException(
					ReturnVisitExceptionType.noPermissions);
		}
		// 日程状态错误-无法重复回访
		if (MarketingConstance.SCHEDULE_FINISH.equals(sche.getStatus())){
			// 已回访过			
			throw new ReturnVisitException(
					ReturnVisitExceptionType.dontRepeatRV);
		}
		// 未设置日程-
		if (sche.getTime() == null){
			//无法回访
			throw new ReturnVisitException(
					ReturnVisitExceptionType.scheduleIsNull);
		}
		return true;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 043260
	 * @version 0.1 2014年4月22日
	 * @param isRepeat
	 * @param isExist
	 * @param rs
	 * void
	 */
	public static void validateRepeatAndBo(boolean isExist,ReturnVisit rs,String deptName) {
		if(StringUtils.isNotEmpty(deptName)){
			throw new ReturnVisitException(ReturnVisitExceptionType.RepeatCust,new Object[]{rs.getPsCustomerName(),deptName});
		}
		if(isExist){
			throw new ReturnVisitException(ReturnVisitExceptionType.existUnClosedBO,new Object[]{rs.getPsCustomerName()});
		}
	}
}
