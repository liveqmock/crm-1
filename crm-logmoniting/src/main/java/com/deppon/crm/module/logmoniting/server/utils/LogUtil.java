package com.deppon.crm.module.logmoniting.server.utils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Order;

import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoException;
import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoExceptionType;
import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfo;
import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfoViewObject;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_count;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

/**   
 * @Description:验证类<br />
 * @title LogValidate.java
 * @package com.deppon.crm.module.logmoniting.server.utils 
 * @author CoCo
 * @version 0.1 2013-6-20
 */
public class LogUtil {

	/**
	 * @Description:组装 日志统计的信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @param logStatistics 新增的日志统计信息
	 * @param count 查询的统计信息
	 * @param loginfo 基础action信息
	 * @param date 时间
	 * void
	 */
	public static void completeDateForLogStatistics(ReqLogInfo_count count,BasicLoginfo loginfo,
			LogStatistics logStatistics,Date date) {
		if (count != null && !StringUtils.isEmpty(count.getId())) {
			logStatistics.setRequestCount(count.getCount());//一小时总次数
			logStatistics.setMaxRequestTime(count.getMaxRequestTime());//请求最长时长 针对年月日时统计该数据
			logStatistics.setAvgRequestTime(count.getAvgRequestTime());//请求时间统计  平均时间（1小时）
			logStatistics.setRequestTimeCount(count.getRequestTimeCount());//请求时间统计  总时间（1小时）
		}else {
			//无请求
			logStatistics.setRequestCount(0);//一小时总次数
			logStatistics.setMaxRequestTime(0);//一小时请求最长时间
			logStatistics.setAvgRequestTime(0);//请求时间统计  平均时间（1小时）
			logStatistics.setRequestTimeCount(0);//请求时间统计  总时间（1小时）
		}
		//关联BasicLoginfo信息ID
		logStatistics.setBaseLoginfoId(loginfo.getId());
		logStatistics.setModulename(loginfo.getModulename());//模块名
		logStatistics.setUrl(loginfo.getUrl());//url
		
		logStatistics.setYear(DateUtil.getYear(date));
		logStatistics.setMonth(DateUtil.getMonth(date));
		logStatistics.setDay(DateUtil.getDay(date));
		logStatistics.setHour(DateUtil.getHours(date));
		//ID---UUID
		logStatistics.setId(getUUID());
		//创建时间
		logStatistics.setCreateDate(System.currentTimeMillis());
		//创建人
		logStatistics.setCreateUser(Constant.SYSTEM_USER);
	}
	
	
	/**
	 * @Description:当数据不存在时，根据统计频率 组装数据给前端展示<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param logStatistics 统计信息list
	 * @param condition 查询条件
	 * @param i 月数或者天数或者日数
	 * void
	 */
	public static void completeLogStatisticsByNull(LogStatistics logStatistics,LogInfoCondition condition,int i){
			logStatistics.setUrl(condition.getUri());//url
			logStatistics.setMaxRequestTime(0l);//请求最大值
			logStatistics.setRequestCount(0l);//请求总次数值
			logStatistics.setAvgRequestTime(0l);//请求平均时间值
			logStatistics.setYear(condition.getYear());//年
			if (Constant.STATISTICALFREQUENCY_HOUR.equals(condition.getStatisticalFrequency())) {
				logStatistics.setMonth(condition.getMonth());//月
				logStatistics.setDay(condition.getDay());//日
				logStatistics.setHour(i);
			}
			if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
				logStatistics.setMonth(condition.getMonth());//月
				logStatistics.setDay(i);//日
			}
			if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
				logStatistics.setMonth(i);//月
			}
	}
	/**
	 * @Description:根据条件封装查询条件<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param logInfoCondition
	 * void
	 */
	public static void completeConditionDataForDBObject(DBObject selectDBOject,LogInfoCondition logInfoCondition){
		if (!StringUtils.isEmpty(logInfoCondition.getModulename())) {
			//模块名不为空
			selectDBOject.put("modulename", logInfoCondition.getModulename());
		}
		if (Constant.STATISTICALMETHODS_REQUESTTIME.equals(logInfoCondition.getStatisticalMethods())) {
			selectDBOject.put("avgRequestTime",  Order.ASCENDING);//平均时间
			selectDBOject.put("avgRequestTime",  new BasicDBObject(QueryOperators.EXISTS, true));
		}else if (Constant.STATISTICALMETHODS_REQUESTCOUNT.equals(logInfoCondition.getStatisticalMethods())) {
			selectDBOject.put("requestCount",  Order.ASCENDING);//请求次数
			selectDBOject.put("requestCount",  new BasicDBObject(QueryOperators.EXISTS, true));
		}
	}
	
	/**
	 * @Description:查询到数据时,根据统计频率 封装统计信息数据给前端展示<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param condition 查询条件
	 * @param list2 统计信息list
	 * @param logStatistics 统计信息
	 * @param i 月数或者天数
	 *  void
	 */
	public static void completeLogStatistics(LogInfoCondition condition,List<LogStatistics> list2,
			LogStatistics logStatistics,int i){
		long requestCount = 0l;//总次数
		long maxRequestTime = 0l;//最大请求时间
		long requestTimeCount = 0l;//请求时间
		for (LogStatistics logStatistics2 : list2) {
			//总时长
			requestTimeCount += logStatistics2.getRequestTimeCount();
			//总次数
			requestCount += logStatistics2.getRequestCount();
			if (maxRequestTime <= logStatistics2.getMaxRequestTime()) {
				maxRequestTime = logStatistics2.getMaxRequestTime();
			}
		}
		logStatistics.setMaxRequestTime(maxRequestTime);//最大值
		logStatistics.setRequestCount(requestCount);//总次数
		if (requestCount != 0) {
			logStatistics.setAvgRequestTime(requestTimeCount/requestCount);//平均时间
		}else {
			logStatistics.setAvgRequestTime(0l);//平均时间
		}
		logStatistics.setUrl(condition.getUri());//url
		logStatistics.setYear(condition.getYear());//年
		if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
			logStatistics.setMonth(condition.getMonth());//月
			logStatistics.setDay(i);//日
		}
		if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
			logStatistics.setMonth(i);//月
		}
	}
	
	/**
	 * @Description:产生UUID<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 * @return String
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString(); 
	}
	
	/**
	 * @Description:验证参数的合法性，列如 浮动值只能为0-1<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * @param basicLoginfo
	 * void
	 */
	public static void validateBasicLoginInfoIsRight(BasicLoginfo basicLoginfo){
		LogInfoExceptionType reason = null;
		if (basicLoginfo.getCountFloat() < 0 || basicLoginfo.getCountFloat() > 1) {
			//请求次数浮动值 必须0 -1
			reason = LogInfoExceptionType.TimeFloatAndRequestFloatMustBeZeroToOne;
		}
		if (basicLoginfo.getTimeFloat() < 0 || basicLoginfo.getTimeFloat() > 1) {
			//请求次数浮动值 必须0 -1
			reason = LogInfoExceptionType.TimeFloatAndRequestFloatMustBeZeroToOne;
		}
		if (basicLoginfo.getBaseCount() < 0 || basicLoginfo.getBaseTime() < 0) {
			//请求次数和请求时间不能小于0
			reason = LogInfoExceptionType.TimeCount_And_RequestCount_CanNotBeforeZero;
		}
		if (String.valueOf(basicLoginfo.getBaseCount()).indexOf(".") > 0
				|| String.valueOf(basicLoginfo.getBaseTime()).indexOf(".") > 0) {
			//不能有小数点--请求次数或者请求时间不能为小数
			reason = LogInfoExceptionType.TimeCount_And_RequestCount_CanNotAsDecimal;
		}
		if (!ValidateUtil.objectIsEmpty(reason)) {
			throw new LogInfoException(reason);
		}
		
	}
	/**
	 * @Description:验证log Action基础维护基本信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-20
	 * @param basicLoginfo
	 * void
	 */
	public static void validateBasicLoginInfo(BasicLoginfo basicLoginfo) {
		LogInfoExceptionType reason = null;
		if (ValidateUtil.objectIsEmpty(basicLoginfo)) {
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (StringUtils.isEmpty(basicLoginfo.getModulename())) {
			//模块名
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (StringUtils.isEmpty(basicLoginfo.getActionName())) {
			//action名字
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (StringUtils.isEmpty(basicLoginfo.getUrl())) {
			//action的url
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (ValidateUtil.objectIsEmpty(basicLoginfo.isInvalid())) {
			//是否启用预警
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (ValidateUtil.objectIsEmpty(basicLoginfo.isBlackList())) {
			//是否黑名单
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (ValidateUtil.objectIsEmpty(basicLoginfo.getBaseTime())) {
			//请求时间
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (ValidateUtil.objectIsEmpty(basicLoginfo.getTimeFloat())) {
			//请求时间浮动值
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (ValidateUtil.objectIsEmpty(basicLoginfo.getBaseCount())) {
			//请求次数
			reason = LogInfoExceptionType.LogInfo_isNull;
		} else if (ValidateUtil.objectIsEmpty(basicLoginfo.getCountFloat())) {
			//请求次数浮动值
			reason = LogInfoExceptionType.LogInfo_isNull;
		} 
		/*else if (StringUtils.isEmpty(basicLoginfo.getMailPerson())) {
			//通知人员邮箱
			reason = LogInfoExceptionType.LogInfo_isNull;
		}*/		
		if (!ValidateUtil.objectIsEmpty(reason)) {
			throw new LogInfoException(reason);
		}
	}
	

	
	/**
	 * @Description:验证查询条件是否为空<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * void
	 */
	public static void validateLogInfoCondition(LogInfoCondition logInfoCondition) {
		if (ValidateUtil.objectIsEmpty(logInfoCondition)) {
			//对象为空
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		}  
		if (StringUtils.isEmpty(logInfoCondition.getModulename())
				&& StringUtils.isEmpty(logInfoCondition.getUri())
				&& ValidateUtil.objectIsEmpty(logInfoCondition.getBeginDate())
				&& ValidateUtil.objectIsEmpty(logInfoCondition.getEndDate())
				&& StringUtils.isEmpty(logInfoCondition.getStatisticalFrequency())
				&& ValidateUtil.objectIsEmpty(logInfoCondition.getEndDate())
				&& StringUtils.isEmpty(logInfoCondition.getStatisticalMethods())
				&& ValidateUtil.objectIsEmpty(logInfoCondition.getLevel())
				&&StringUtils.isEmpty(logInfoCondition.getActionName())
				&& StringUtils.isEmpty(logInfoCondition.getBaseLoginfoId())) {
			//查询条件全为空
			throw new LogInfoException(LogInfoExceptionType.ObjectParames_Are_Null);
		} 
		if (StringUtils.isEmpty(logInfoCondition.getStatisticalFrequency())) {
			//查询条件:统计频率必须不能为空！
			throw new LogInfoException(LogInfoExceptionType.StatisticalFrequencyIsEmpty);
		}
		if ((ValidateUtil.objectIsEmpty(logInfoCondition.getBeginDate()) 
				&& !ValidateUtil.objectIsEmpty(logInfoCondition.getEndDate()))
				|| (!ValidateUtil.objectIsEmpty(logInfoCondition.getBeginDate()) 
					&& ValidateUtil.objectIsEmpty(logInfoCondition.getEndDate()))) {
			//统计开始时间 和结束时间 有一个为空
			throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
		} else if (!ValidateUtil.objectIsEmpty(logInfoCondition.getBeginDate()) 
				&& !ValidateUtil.objectIsEmpty(logInfoCondition.getEndDate())) {
			//如果统计开始时间和结束时间都不为空,则从新验证
			validateStatisticaltime(logInfoCondition);
		}
	}
	
	/**
	 * @Description:组装数据--查询条件<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * @param condition
	 * void
	 */
	public static void assemblingDataForCondition(LogInfoCondition condition) {
		//时间为空	
		if (ValidateUtil.objectIsEmpty(condition.getBeginDate())&& ValidateUtil.objectIsEmpty(condition.getEndDate())) {
				Date date = new Date();
				if (Constant.STATISTICALFREQUENCY_HOUR.equals(condition.getStatisticalFrequency())) {
					//统计的开始时间和结束时间为空,则将查询条件的年月日时 都设为当前时间的年月日时的前一个小时
					//小时
					condition.setBeginDate(DateUtil.returnPreviousHour(date, 1));
					condition.setEndDate(date);
					completeDateForCondition(condition);
				} else if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
					//统计的开始时间和结束时间为空,则将查询条件的年月日时 都设为当前时间的年月日时的前一天
					//天
					condition.setBeginDate(DateUtil.returnPreviousDay(DateUtil.returnPreviousHour(date, DateUtil.getHours(date)), 1));
					condition.setEndDate(DateUtil.returnPreviousHour(date, DateUtil.getHours(date)));
					completeDateForCondition(condition);
				} else if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
					//统计的开始时间和结束时间为空,则将查询条件的年月日时 都设为当前时间的年月日时的前一个月
					//月
					date = DateUtil.returnPreviousDay(DateUtil.returnPreviousHour(date, DateUtil.getHours(date)), DateUtil.getDay(date)-1);
					condition.setEndDate(date);
					condition.setBeginDate(DateUtil.returnPreviousMonth(DateUtil.returnPreviousDay(date, DateUtil.getDay(date)-1), 1));
					completeDateForCondition(condition);
				}
		}else {
			//时间不为空
			completeDateForCondition(condition);
		}
}
	
	/**
	 * @Description:组装数据--查询条件--时 日 月<br />
	 * @author CoCo
	 * @version 0.1 2013-6-21
	 * @param condition 查询条件对象
	 * void
	 */
	public static void completeDateForCondition(LogInfoCondition condition) {
		Date beginDate = condition.getBeginDate();
		Date endDate = condition.getEndDate();
		
		if (Constant.STATISTICALFREQUENCY_HOUR.equals(condition.getStatisticalFrequency())) {
			//小时
			condition.setYear(DateUtil.getYear(beginDate));
			condition.setMonth(DateUtil.getMonth(beginDate));
			condition.setBeginmonth(DateUtil.getMonth(beginDate));
			condition.setEndmonth(DateUtil.getMonth(endDate));
			condition.setDay(DateUtil.getDay(beginDate));
			condition.setBeginday(DateUtil.getDay(beginDate));
			if (DateUtil.getDay(beginDate) != DateUtil.getDay(endDate)) {
				condition.setEndday(DateUtil.getDay(beginDate));
			}else {
				condition.setEndday(DateUtil.getDay(endDate));
			}
			condition.setHour(DateUtil.getHours(beginDate));
			condition.setBeginhour(DateUtil.getHours(beginDate));
			condition.setEndhour(DateUtil.getHours(DateUtil.returnPreviousHour(endDate, 1)));
		}
		if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
			//日
			condition.setYear(DateUtil.getYear(beginDate));
			condition.setMonth(DateUtil.getMonth(beginDate));
			condition.setBeginmonth(DateUtil.getMonth(beginDate));
			if (DateUtil.getMonth(beginDate) != DateUtil.getMonth(endDate)) {
				condition.setEndmonth(DateUtil.getMonth(beginDate));
			}else {
				condition.setEndmonth(DateUtil.getMonth(endDate));
			}
			condition.setDay(DateUtil.getDay(beginDate));
			condition.setBeginday(DateUtil.getDay(beginDate));
			condition.setEndday(DateUtil.getDay(DateUtil.returnPreviousHour(endDate, 1)));
			condition.setHour(DateUtil.getHours(beginDate));
			condition.setBeginhour(DateUtil.getHours(beginDate));
			condition.setEndhour(DateUtil.getHours(DateUtil.returnPreviousHour(endDate, 1)));
		}
		if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
			//月
			condition.setYear(DateUtil.getYear(beginDate));
			condition.setMonth(DateUtil.getMonth(beginDate));
			condition.setBeginmonth(DateUtil.getMonth(beginDate));
			condition.setEndmonth(DateUtil.getMonth(DateUtil.returnPreviousHour(endDate, 1)));
			condition.setDay(DateUtil.getDay(beginDate));
			condition.setBeginday(DateUtil.getDay(beginDate));
			condition.setEndday(DateUtil.getDay(DateUtil.returnPreviousHour(endDate, 1)));
			condition.setHour(DateUtil.getHours(beginDate));
			condition.setBeginhour(DateUtil.getHours(beginDate));
			condition.setEndhour(DateUtil.getHours(DateUtil.returnPreviousHour(endDate, 1)));
		}
	}

	/**
	 * @Description:封装给前端展示的列表对象<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @param actionLogInfoViewObject 前端展示对象
	 * @param logStatistics	日志统计信息
	 * @param basicLoginfo action基础信息
	 * void
	 */
	public static void completeActionLogInfoViewObjectByCopy(ActionLogInfoViewObject actionLogInfoViewObject,
			BasicLoginfo basicLoginfo,List<LogStatistics> logStatistics){
		actionLogInfoViewObject.setActionName(basicLoginfo.getActionName());
		actionLogInfoViewObject.setId(basicLoginfo.getId());
		actionLogInfoViewObject.setModulename(basicLoginfo.getModulename());
		actionLogInfoViewObject.setUrl(basicLoginfo.getUrl());
		actionLogInfoViewObject.setCreateTime(basicLoginfo.getCreateDate());
		if (logStatistics != null && logStatistics.size() > 0) {
			long maxRequestTime = 0;
			long requestCount = 0;
			long requestTimeCount = 0;
			for (LogStatistics logStatistics2 : logStatistics) {
				requestCount += logStatistics2.getRequestCount();
				requestTimeCount += logStatistics2.getRequestTimeCount();
				if (maxRequestTime <= logStatistics2.getMaxRequestTime()) {
					maxRequestTime = logStatistics2.getMaxRequestTime();
				}
			}
			if (requestCount != 0) {
				actionLogInfoViewObject.setAvgRequestTime(requestTimeCount/requestCount);
			}else {
				actionLogInfoViewObject.setAvgRequestTime(0);
			}
			actionLogInfoViewObject.setMaxRequestTime(maxRequestTime);
			actionLogInfoViewObject.setRequestCount(requestCount);
		}else {
			actionLogInfoViewObject.setAvgRequestTime(0);
			actionLogInfoViewObject.setMaxRequestTime(0);
			actionLogInfoViewObject.setRequestCount(0);
		}
	}
	/**
	 * @Description:统计时间不为空的时候 ，判断其选择时间是否有问题--日、时、月
	 * TODO 验证条件可以放松，满足更多的需求<br />
	 * @author CoCo
	 * @version 0.1 2013-6-26
	 * @param condition
	 * void
	 */
	public static void validateStatisticaltime(LogInfoCondition condition) {
		LogInfoExceptionType reason = null;
		Date beginDate = condition.getBeginDate();//开始时间
		Date endDate = condition.getEndDate();//结束时间
		if (endDate.after(new Date()) || beginDate.after(new Date())) {
			throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
		}
		if (beginDate.after(endDate) || beginDate.equals(endDate)) {
			//统计开始时间 大于结束时间
			throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
		}
	
		if (Constant.STATISTICALFREQUENCY_HOUR.equals(condition.getStatisticalFrequency())) {
			if ((DateUtil.getYear(endDate)- DateUtil.getYear(beginDate)) > 0) {
				//超过一年
				throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
			}
			//统计频率为小时 --不能超过24小时
			if ((endDate.getTime() - beginDate.getTime()) > 24*Constant.OneHourForLongMills
					|| (endDate.getTime() - beginDate.getTime()) < Constant.OneHourForLongMills) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
			if (DateUtil.getDay(beginDate) != DateUtil.getDay(endDate)
					&& (DateUtil.getHours(endDate) != 0)) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
		}
		
		if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
			if ((DateUtil.getYear(endDate)- DateUtil.getYear(beginDate)) > 0) {
				//超过一年
				throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
			}
			if (DateUtil.getMonth(endDate) -  DateUtil.getMonth(beginDate) > 1) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
			if (DateUtil.getMonth(beginDate) != DateUtil.getMonth(endDate)
					&& DateUtil.getDay(endDate) != 1) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
			if (DateUtil.getMonth(beginDate) == DateUtil.getMonth(endDate)
					&&( DateUtil.getDay(endDate) == DateUtil.getDay(beginDate)
					||(endDate.getTime() - beginDate.getTime()) < 24*Constant.OneHourForLongMills)) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
			if (DateUtil.getHours(beginDate) != DateUtil.getHours(endDate)
					|| DateUtil.getHours(beginDate) != 0 
					|| DateUtil.getHours(endDate) != 0) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
		}
		
		if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
			if ((DateUtil.getYear(endDate)- DateUtil.getYear(beginDate)) > 1) {
				//超过一年
				throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
			}else if ((DateUtil.getYear(endDate)- DateUtil.getYear(beginDate)) == 1
					&& (DateUtil.getMonth(endDate) != 1
					||DateUtil.getDay(beginDate) != 1 
					|| DateUtil.getDay(endDate) != 1)) {
				throw new LogInfoException(LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error);
			}
			if (DateUtil.getYear(beginDate) == DateUtil.getYear(endDate)
				&& (DateUtil.getMonth(endDate) == DateUtil.getMonth(beginDate))) {
				//同一年时间不能同月
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
			if (DateUtil.getDay(beginDate) != DateUtil.getDay(endDate)
					|| DateUtil.getDay(beginDate) != 1 
					|| DateUtil.getDay(endDate) != 1) {
				reason = LogInfoExceptionType.BeginTime_Or_EndTime_Is_Error;
			}
		}
		if (!ValidateUtil.objectIsEmpty(reason)) {
			throw new LogInfoException(reason);
		}
	}	
	
	/**
	 * @Description:action统计信息 对于预警和黑名单的action 通过时间判断是否需要发送邮件
	 * 如果是星期天 星期六 星期一 就不发送邮件
	 * <br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 * @param date
	 * @return
	 * boolean
	 */
	public static  boolean validateActionToEmailByDay(Date date){
		boolean flag = true;
		int day = DateUtil.getDayForWeek(date);
		if(Constant.Monday == day){
			flag = false;
		}
		if (Constant.Saturday == day) {
			flag = false;
		}
		if(Constant.Sunday == day){
			flag = false;
		}
		return flag;
	}
	

	/**
	 * @Description: 预警条件的判断
	 * 每小时统计一次每个Action的请求次数，如果改次数超过了前一天该时段请求及浮动计算的值，
	 * 就以邮件的形式通知开发人员改Action出现异常请求。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-13
	 * @param basicLoginfo--基础action信息
	 * @param logStatistics--1小时统计的action的统计信息
	 * @return boolean
	 */
	public static boolean validateFloatValueToSendMail(BasicLoginfo basicLoginfo,
			LogStatistics oldlogStatistics,LogStatistics newlogStatistics){
		boolean flag = false;
		if ((1+ basicLoginfo.getCountFloat())*oldlogStatistics.getRequestCount() 
				< newlogStatistics.getRequestCount()){
			//(1 + 浮动值） * 昨天1小时的请求总次数 < 今天1小时的请求总次数
			flag = true;
		}
		if ((1- basicLoginfo.getCountFloat())*oldlogStatistics.getRequestCount()
				> newlogStatistics.getRequestCount()) {
			//(1 - 浮动值 ） * 昨天1小时的请求总次数 > 今天1小时的请求总次数
			flag = true;
		}
		
		if ((1 + basicLoginfo.getTimeFloat()) *oldlogStatistics.getAvgRequestTime()
				< newlogStatistics.getAvgRequestTime()){
			//（1 + 浮动值 ） * 昨天1小时的请求平均时长< 今天1小时的请求平均时长
			flag = true;
		}
		if ((1- basicLoginfo.getTimeFloat())*oldlogStatistics.getAvgRequestTime() 
				> newlogStatistics.getAvgRequestTime()) {
			//（1 - 浮动值）*昨天1小时action信息请求平均时长 > 今天1小时的请求平均时长
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Description:
	 * 每天根据每个Action设置的请求时间的最大值，
	 * 和请求次数的最大值，对每天汇总的所有请求的请求时间和请求次数的统计进行对比，
	 * 如果发现请求次数或者请求时长超过设置的最大值，则以邮件的形式通知开发人员说明Action请求时间过长或者请求次数过多。<br />
	 * @author CoCo
	 * @version 0.1 2013-7-22
	 * @param count
	 * @param basicLoginfo
	 * @return
	 * boolean
	 */
	public static boolean validateMaxFloatValueToSendMail(ReqLogInfo_averageCount count,BasicLoginfo basicLoginfo){
		boolean flag = false;
		if ((1+basicLoginfo.getTimeFloat()) * basicLoginfo.getBaseTime() < count.getAvgRequestTime()) {
			flag = true;
		}
		if ((1+basicLoginfo.getCountFloat()) * basicLoginfo.getBaseCount() < count.getRequestCount()) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Description:通过url得到action的名字<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param url
	 * @return
	 * String
	 */
	public static String regixUrlToActionName(String url){
		return url.substring(url.lastIndexOf("/")+1, url.length());
	}
	
	 	/**
	 * @Description:为定时器提供封装查询条件<br />
	 * @author CoCo
	 * @version 0.1 2013-7-26
	 * @param logInfoCondition 条件
	 * @param date 时间
	 * @param level 级别 条数
	 * @param statisticalFrequency 统计方式
	 * @param statisticalMethods 统计频率
	 * void
	 */
	public static void  comoleteConditionForTimer(LogInfoCondition logInfoCondition,Date date,int level,String statisticalFrequency,String statisticalMethods){
		logInfoCondition.setBeginDate(date);
		logInfoCondition.setEndDate(date);
		logInfoCondition.setLevel(level);
		logInfoCondition.setStatisticalFrequency(statisticalFrequency);//统计频率
		logInfoCondition.setStatisticalMethods(statisticalMethods);//统计频率为 
		logInfoCondition.setYear(DateUtil.getYear(date));
		logInfoCondition.setMonth(DateUtil.getMonth(date));
		logInfoCondition.setBeginmonth(DateUtil.getMonth(date));
		logInfoCondition.setEndmonth(DateUtil.getMonth(date));
		logInfoCondition.setDay(DateUtil.getDay(date));
		logInfoCondition.setBeginday(DateUtil.getDay(date));
		logInfoCondition.setEndday(DateUtil.getDay(date));
		logInfoCondition.setHour(0);//小时默认0
		logInfoCondition.setBeginhour(0);//小时开始默认0
		logInfoCondition.setEndhour(23);//小时结束默认23
	}
	
	/**
	 * @Description:判断是否需要发送邮件，用于两天前十的action的比较<br />
	 * @author CoCo
	 * @version 0.1 2013-7-26
	 * @param yesterDaydBCursor
	 * @param beforeyesterDayCursor
	 * @param logInfoCondition
	 * @return boolean true 发送  false不发送
	 */
	public static boolean validateIfSendEmailForTopTenCompare(List<ActionLogInfo> yesterDaydBCursor,List<ActionLogInfo> beforeyesterDayCursor,LogInfoCondition logInfoCondition){
		boolean flag = true;
		
		if ((yesterDaydBCursor == null || yesterDaydBCursor.size() <=0)
				&& (beforeyesterDayCursor == null || beforeyesterDayCursor.size() <= 0)) {
			return false;
		}else if ((yesterDaydBCursor == null || yesterDaydBCursor.size() <=0 )
				&& (beforeyesterDayCursor != null && beforeyesterDayCursor.size() > 0)) {
			return true;
		}
		else if ((beforeyesterDayCursor == null || beforeyesterDayCursor.size() <=0 )
				&& (yesterDaydBCursor != null && yesterDaydBCursor.size() > 0)) {
			return true;
		}
		for (ActionLogInfo actionLogInfo1 : yesterDaydBCursor) {
			for (ActionLogInfo actionLogInfo2 : beforeyesterDayCursor) {
				if (actionLogInfo1.getUrl().equals(actionLogInfo2.getUrl())) {
					flag = false;
				}else {
					continue;
				}
			}
			if (flag == true) {
				break;
			}else {
				continue;
			}
		} 
		return flag;
	}
	/**
	 * @Description:封装发送邮件的数据--用于前十的比较<br />
	 * @author CoCo
	 * @version 0.1 2013-7-31
	 * @param actionLogInfos
	 * @param dbCursor
	 * @param logInfoCondition
	 * @param i 调用次数
	   void
	 */
	public static void completeActionLogInfoForTopTenAction(List<ActionLogInfo> actionLogInfos,
			List<ActionLogInfo> oldDatas,LogInfoCondition logInfoCondition,int i){
		if (oldDatas == null || oldDatas.size() <= 0) {
			if (i == 1) {
				ActionLogInfo actionLogInfo = new ActionLogInfo();
				actionLogInfo.setActionName("间隔");
				actionLogInfo.setModulename("间隔");
				actionLogInfos.add(actionLogInfo);
			}
			return ;
		}else {
			actionLogInfos.addAll(oldDatas);
		}
		if (i == 1) {
			ActionLogInfo actionLogInfo = new ActionLogInfo();
			actionLogInfo.setActionName("间隔");
			actionLogInfo.setModulename("间隔");
			actionLogInfos.add(actionLogInfo);
		}
	} 
}