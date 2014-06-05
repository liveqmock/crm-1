package com.deppon.crm.module.logmoniting.server.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.crm.module.logmoniting.server.manager.ILogInfoManager;
import com.deppon.crm.module.logmoniting.server.service.IExceptionErrorCodeService;
import com.deppon.crm.module.logmoniting.server.service.ILogInfoService;
import com.deppon.crm.module.logmoniting.server.utils.DateUtil;
import com.deppon.crm.module.logmoniting.server.utils.LogUtil;
import com.deppon.crm.module.logmoniting.server.utils.ValidateUtil;
import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoException;
import com.deppon.crm.module.logmoniting.shared.Excepion.LogInfoExceptionType;
import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfo;
import com.deppon.crm.module.logmoniting.shared.domain.ActionLogInfoViewObject;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.ErrorCodeInfo;
import com.deppon.crm.module.logmoniting.shared.domain.ExceptionErrorCode;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_count;
import com.deppon.foss.framework.server.context.UserContext;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class LogInfoManager implements ILogInfoManager {

	private static Logger log = Logger.getLogger(LogInfoManager.class);
	private ILogInfoService logInfoService;
	private IExceptionErrorCodeService exceptionErrorCodeService;
	//邮件manager
	private MailManager mailManager;
	/**
	 * Description:exceptionErrorCodeService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public IExceptionErrorCodeService getExceptionErrorCodeService() {
		return exceptionErrorCodeService;
	}
	/**
	 * Description:exceptionErrorCodeService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 */
	public void setExceptionErrorCodeService(
			IExceptionErrorCodeService exceptionErrorCodeService) {
		this.exceptionErrorCodeService = exceptionErrorCodeService;
	}
	/**
	 * Description:logInfoService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public ILogInfoService getLogInfoService() {
		return logInfoService;
	}
	/**
	 * Description:logInfoService<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public void setLogInfoService(ILogInfoService logInfoService) {
		this.logInfoService = logInfoService;
	}
	/**
	 * Description:mailManager<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public MailManager getMailManager() {
		return mailManager;
	}
	/**
	 * Description:mailManager<br />
	 * @author CoCo
	 * @version 0.1 2013-7-15
	 */
	public void setMailManager(MailManager mailManager) {
		this.mailManager = mailManager;
	}


	@Override
	public void saveBaseLogInfo(BasicLoginfo basicLoginfo) {
		//验证参数是否为空
		LogUtil.validateBasicLoginInfo(basicLoginfo);
		//验证参数的合法性
		LogUtil.validateBasicLoginInfoIsRight(basicLoginfo);
		//验证action是否已经新增?
		Query query = new Query();
		//设置查询条件url唯一
		query.addCriteria(Criteria.where("url").is(basicLoginfo.getUrl()));
		List<BasicLoginfo> basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class, query);
		if (basicLoginfos != null && basicLoginfos.size() > 0) {
			//基础action信息已经添加,请核实！
			throw new LogInfoException(LogInfoExceptionType.BasicActionInfo_Has_AlreadyExist);
		}
		//ID---UUID
		basicLoginfo.setId(LogUtil.getUUID());
		//创建时间
		basicLoginfo.setCreateDate(System.currentTimeMillis());
		//创建人
		basicLoginfo.setCreateUser(UserContext.getCurrentUser().getId());
		//保存
		logInfoService.saveBaseLogInfo(basicLoginfo);
	}

	@Override
	public void updateBaseLogInfo(BasicLoginfo basicLoginfo) {
		if (ValidateUtil.objectIsEmpty(basicLoginfo)) {
			//对象为空，抛异常
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		}else {
			basicLoginfo.setModifyUser(UserContext.getCurrentUser().getId());
			basicLoginfo.setModifyDate(System.currentTimeMillis());
			//验证参数是否为空
			LogUtil.validateBasicLoginInfo(basicLoginfo);
			//验证参数的合法性
			LogUtil.validateBasicLoginInfoIsRight(basicLoginfo);
			logInfoService.updateBaseLogInfo(basicLoginfo);
		}
	}

	@Override
	public List<ActionLogInfoViewObject> integratedQueryInfosByCondition(LogInfoCondition condition){
		if (ValidateUtil.objectIsEmpty(condition)) {
			//对象为空，抛异常
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		} else {
			LogUtil.validateLogInfoCondition(condition);
		}
		//前端grid展示结果
		List<ActionLogInfoViewObject> actionViewObjects = new ArrayList<ActionLogInfoViewObject>();
		//得到action的查询结果--分页
		List<BasicLoginfo> basicLoginfos = this.getBasicLoginfos(condition,Constant.WHETHER_PAGING);
		
		if (basicLoginfos!=null && basicLoginfos.size()>0) {
			//查询条件的封装
			LogUtil.assemblingDataForCondition(condition);
			//查询前端需要的结果集
			completeActionViewObjects(basicLoginfos,condition,actionViewObjects);
		}else {
			actionViewObjects = null;
		}
		return actionViewObjects;
	}
	
	/**
	 * @Description:得到统计信息，并封装对象给予前端<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @param basicLoginfos
	 * @param condition
	 * void
	 */
	private  List<ActionLogInfoViewObject> completeActionViewObjects(List<BasicLoginfo> basicLoginfos,
			LogInfoCondition condition,List<ActionLogInfoViewObject> actionViewObjects) {
		for (BasicLoginfo basicLoginfo : basicLoginfos) {
			Query query = null;//查询条件
			List<LogStatistics> logStatistics = null;//统计对象 
			ActionLogInfoViewObject actionViewObject = null;
			List<Integer> integers = new ArrayList<Integer>();
			if (Constant.STATISTICALFREQUENCY_HOUR.equals(condition.getStatisticalFrequency())) {
					query = new Query();
					for (int i = condition.getBeginhour();i<=condition.getEndhour();i++) {
						if (!integers.contains(Integer.valueOf(i))) {
							integers.add(Integer.valueOf(i));
						}else {
							continue;
						}
					}
					query.addCriteria(Criteria.where("url").is(basicLoginfo.getUrl()).and("year").is(condition.getYear())
							.and("month").is(condition.getMonth()).and("day").is(condition.getDay()).and("hour").in(integers));
			}
			if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
				query = new Query();
				for (int i = condition.getBeginday();i<=condition.getEndday();i++) {
					if (!integers.contains(Integer.valueOf(i))) {
						integers.add(Integer.valueOf(i));
					}else {
						continue;
					}
				}
				query.addCriteria(Criteria.where("url").is(basicLoginfo.getUrl()).and("year").is(condition.getYear())
						.and("month").is(condition.getMonth()).and("day").in(integers));
			}
			if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
				query = new Query();
				for (int i = condition.getBeginmonth();i<=condition.getEndmonth();i++) {
					if (!integers.contains(Integer.valueOf(i))) {
						integers.add(Integer.valueOf(i));
					}else {
						continue;
					}
				}
				query.addCriteria(Criteria.where("url").is(basicLoginfo.getUrl()).and("year").is(condition.getYear())
						.and("month").in(integers));
			}
			logStatistics = new ArrayList<LogStatistics>();
			actionViewObject = new ActionLogInfoViewObject();
			logStatistics = logInfoService.searchAllLogStatisticsByQuery(LogStatistics.class, query);
			LogUtil.completeActionLogInfoViewObjectByCopy(actionViewObject,basicLoginfo,logStatistics);
			actionViewObjects.add(actionViewObject);
		}
		return actionViewObjects;
	}
	
	@Override
	public List<LogStatistics> searchLogStatisticsByCondition(
			LogInfoCondition condition) {
		if (ValidateUtil.objectIsEmpty(condition)) {
			//对象为空，抛异常
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		} else {
			LogUtil.validateLogInfoCondition(condition);
			if (StringUtils.isEmpty(condition.getBaseLoginfoId()) || StringUtils.isEmpty(condition.getUri())) {
				throw new LogInfoException(LogInfoExceptionType.SearchConditionParam_iS_Null);
			}
			LogUtil.assemblingDataForCondition(condition);
		}
		List<LogStatistics> logStatistics = new ArrayList<LogStatistics>();
		searchLogAndCompleteStatistics(logStatistics,condition);
		return logStatistics;
	}

	/**
	 * @Description:根据查询条件查询某一个action在某个时间段查询的请求次数图或者请求时长图<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @return List<LogStatistics>
	 */
	private List<LogStatistics> searchLogAndCompleteStatistics(List<LogStatistics> list,
			LogInfoCondition condition){
		Query query = null;//查询条件
		LogStatistics logStatistics = null;//统计对象 
		if (Constant.STATISTICALFREQUENCY_HOUR.equals(condition.getStatisticalFrequency())) {
			for (int i = condition.getBeginhour();i<= condition.getEndhour();i++) {
				query = new Query();
				logStatistics = new LogStatistics();
				query.addCriteria(Criteria.where("url").is(condition.getUri()).and("year").is(condition.getYear())
						.and("month").is(condition.getMonth()).and("day").is(condition.getDay()).and("hour").is(i));
				logStatistics = logInfoService.findOneLogStatisticsByQuery(LogStatistics.class, query);
				if (logStatistics != null && !StringUtils.isEmpty(logStatistics.getId())) {
					list.add(logStatistics);
				}else {
					logStatistics = new LogStatistics();
					LogUtil.completeLogStatisticsByNull(logStatistics,condition,i);
					list.add(logStatistics);
				}
			}
		}
		if (Constant.STATISTICALFREQUENCY_DAY.equals(condition.getStatisticalFrequency())) {
			//TODO 待优化
			for (int i = condition.getBeginday(); i <= condition.getEndday(); i++) {
				query =  new Query();
				logStatistics = new LogStatistics();
				query.addCriteria(Criteria.where("url").is(condition.getUri()).and("year").is(condition.getYear())
						.and("month").is(condition.getMonth()).and("day").is(i));
				assebleData(query, condition, logStatistics, i, list);
			}
		}
		if (Constant.STATISTICALFREQUENCY_MONTH.equals(condition.getStatisticalFrequency())) {
			//TODO 待优化
			for (int i = condition.getBeginmonth(); i <= condition.getEndmonth(); i++) {
				query =  new Query();
				logStatistics = new LogStatistics();
				query.addCriteria(Criteria.where("url").is(condition.getUri()).and("year").is(condition.getYear())
						.and("month").is(i));
				assebleData(query, condition, logStatistics, i, list);
			}
		}
		
		if (list!= null && list.size() < 1) {
			list = new ArrayList<LogStatistics>();
		}
		return list;
	}

	private void assebleData(Query query,LogInfoCondition condition,LogStatistics logStatistics,
			int i,List<LogStatistics> list){
		List<LogStatistics> list2 = logInfoService.searchAllLogStatisticsByQuery(LogStatistics.class, query);
		if (list2 != null && list2.size() > 0) {
			LogUtil.completeLogStatistics(condition,list2,logStatistics,i);
		}else {
			LogUtil.completeLogStatisticsByNull(logStatistics,condition,i);
		}
		list.add(logStatistics);
	}
	
	@Override
	public BasicLoginfo findBaseLogInfoById(BasicLoginfo basicLoginfo) {
		return logInfoService.findBaseLogInfoById(basicLoginfo);
	}

	@Override
	public void assemblingLogInfoDataForOneHour() {
		
		//查询需要统计日志信息的基础BasicLoginfo信息
		List<BasicLoginfo> basicLoginfos = logInfoService.getAllBasicLoginfos();
		if (ValidateUtil.objectIsEmpty(basicLoginfos)) {
			return ;
		}
		/**
		 * 变量声明
		 */
		List<LogStatistics> logStatisticsList = new ArrayList<LogStatistics>();//统计信息集合
		LogStatistics newlogStatistics = null;//当前时间action查询的统计信息
		LogStatistics oldlogStatistics = null;//当前时间前一天action查询的统计信息
		Query query = null;//查询条件
		Date date = new Date();//当前时间
		long currentTimeLong = date.getTime();//当前时间的毫秒数
		Date oldDay = null;//当前时间前一天时间
		Date oldHour = DateUtil.returnPreviousHour(date, 1);//当前时间前一小时
		//判断是否需要发送邮件 true-发送邮件 false-不发送邮件
		boolean sendEmail = LogUtil.validateActionToEmailByDay(date);
		List<ActionLogInfo> actionLogInfos = null;//日志发送的对象list
		ActionLogInfo actionLogInfo = null;//某个需要发送的action邮件对象
		if (sendEmail) {
			actionLogInfos = new ArrayList<ActionLogInfo>();
			oldDay = DateUtil.returnPreviousDay(oldHour, 1);
		}

		if (StringUtils.isEmpty(logInfoService.excuteProcedureForOneHourRequestInfo(currentTimeLong-Constant.OneHourForLongMills, currentTimeLong))) {
			//调用存储过程 计算前一个小时action的请求日志信息
			return ;
		}
		
 		for (BasicLoginfo loginfo : basicLoginfos) {
 			query = new Query();//查询条件
 			query.addCriteria(Criteria.where("url").is(loginfo.getUrl()));
 			//查询出某一个action某一个时间段的请求统计信息
 			ReqLogInfo_count count = logInfoService.findReqLogInfo_countByURI(ReqLogInfo_count.class, query);
			newlogStatistics = new LogStatistics();
			//日志统计信息的基础信息收集
			LogUtil.completeDateForLogStatistics(count,loginfo,newlogStatistics,oldHour);
			logStatisticsList.add(newlogStatistics);//集合添加数据
			
			/**
			 * 1.判断是否需要发送邮件
			 * 2.和昨天的这个时间段进行action日志信息的比较
			 * 3.超出浮动值就发送邮件
			 * 4.前提是 预警和黑名单的action请求
			 * 5.针对某一个action,如果没找到昨天的统计信息，则不进行比较,也不发送邮件
			 */
			if(sendEmail && Constant.TrueValue == loginfo.isBlackList()
					/*&& Constant.TrueValue == loginfo.isInvalid()*/){
				actionLogInfo = new ActionLogInfo();
				//组装发送邮件的actionInfo数据
				query = new Query();
				query.addCriteria(Criteria.where("url").is(loginfo.getUrl())
						.and("year").is(DateUtil.getYear(oldDay)).and("month").is(DateUtil.getMonth(oldDay))
						.and("day").is(DateUtil.getDay(oldDay)).and("hour").is(DateUtil.getHours(oldDay)));
				//查询昨天这一时间是否有统计信息,有则进行判断是否达到预警的条件,没有查询到或者没有达到预警条件,进入下一个循环
				oldlogStatistics = logInfoService.findOneLogStatisticsByQuery(LogStatistics.class, query);
				if (oldlogStatistics != null && !StringUtils.isEmpty(oldlogStatistics.getId())
						&& LogUtil.validateFloatValueToSendMail(loginfo, oldlogStatistics,newlogStatistics)) {
					//满足条件才封装数据，发送邮件。
					actionLogInfo = new ActionLogInfo();
					MailManager.completeActionInfoForSendMailByOneHour(loginfo, oldlogStatistics, newlogStatistics, actionLogInfo);
					actionLogInfos.add(actionLogInfo);
				}
			}
			if (logStatisticsList.size()%500 == 0) {
				//500条插一次
				logInfoService.saveLogStatisticsByList(logStatisticsList);
				logStatisticsList = new ArrayList<LogStatistics>();
			}
		}
		
		if (logStatisticsList!= null && logStatisticsList.size() > 0) {
			//不足500的最后插一次
			logInfoService.saveLogStatisticsByList(logStatisticsList);
		}
		if (sendEmail && actionLogInfos!= null && actionLogInfos.size() > 0) {
			//对对比之后的结果进行邮件发送
			mailManager.actionInfoErrorForOneHour(actionLogInfos);
		}
	}
	
	/**
	 * @Description:私有封装--查询基础action信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * @param condition
	 * @return List<BasicLoginfo>
	 */
	@Override
	public List<BasicLoginfo> getBasicLoginfos(LogInfoCondition condition,String type){
		List<BasicLoginfo> basicLoginfos = new ArrayList<BasicLoginfo>();//基础action查询结果
		Query query = null;//查询条件
		if (StringUtils.isEmpty(condition.getUri()) && StringUtils.isEmpty(condition.getModulename())
				&& StringUtils.isEmpty(condition.getActionName())) {
			//当url 模块名和action名字都为空的时候,查询所有 基础action信息--外加分页条件
			if (!StringUtils.isEmpty(type)&&Constant.WHETHER_PAGING.equals(type)) {
				query = new Query();
				query.skip(condition.getStart()).limit(condition.getLimit());
				basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class, query);
			}else {
				basicLoginfos = logInfoService.getAllBasicLoginfos();
			}
		}else {
			query = new Query();
			if (!StringUtils.isEmpty(condition.getUri())) {
				//url不为空
				query.addCriteria(Criteria.where("url").is(condition.getUri()));
			}
			if (!StringUtils.isEmpty(condition.getModulename())) {
				//模块名不为空
				query.addCriteria(Criteria.where("modulename").is(condition.getModulename()));
			}
			if (!StringUtils.isEmpty(condition.getActionName())) {
				//action名字不为空
				query.addCriteria(Criteria.where("actionName").is(condition.getActionName()));
			}
			if (!StringUtils.isEmpty(type) && Constant.WHETHER_PAGING.equals(type)) {
				query.skip(condition.getStart()).limit(condition.getLimit()).sort();
			}
			basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class,query);
		}
		return basicLoginfos;
	}
	
	
	@Override
	public void compareActionAverageContrastByOneDay() {
		LogInfoCondition logInfoCondition = new LogInfoCondition();
		Date date = new Date();
		/**
		 * 设置时间
		 */
		logInfoCondition.setYear(DateUtil.getYear(DateUtil.returnPreviousDay(date, 1)));
		logInfoCondition.setMonth(DateUtil.getMonth(DateUtil.returnPreviousDay(date, 1)));
		logInfoCondition.setDay(DateUtil.getDay(DateUtil.returnPreviousDay(date, 1)));
		//调用存储过程
		if (!StringUtils.isEmpty(logInfoService.excuteProcedureForCompareActionAverageContrast(logInfoCondition))) {
			Query query = new Query();
			query.addCriteria(Criteria.where("blackList").is(Constant.TrueValue));
			//黑名单--需要监控的对象
			List<BasicLoginfo> basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class, query);
			List<ActionLogInfo> list = null;
			ActionLogInfo actionLogInfo = null;
			if (basicLoginfos != null && basicLoginfos.size() > 0) {
				list = new ArrayList<ActionLogInfo>();
				for (BasicLoginfo basicLoginfo : basicLoginfos) {
					query = new Query();
					query.addCriteria(Criteria.where("url").is(basicLoginfo.getUrl()));
					//中间表查询统计之后的数据
					ReqLogInfo_averageCount count = logInfoService.findReqLogInfo_averageCountByURI(ReqLogInfo_averageCount.class, query);
					
					if (count != null && !StringUtils.isEmpty(count.getId())
						&& LogUtil.validateMaxFloatValueToSendMail(count, basicLoginfo)) {
						//判断是否需要发送邮件
						actionLogInfo = new ActionLogInfo();
						MailManager.completeActionAverageContrastByOneDay(count, basicLoginfo, actionLogInfo);
						list.add(actionLogInfo);
					}else
						continue;
				}
				if (list != null && list.size() >0) {
					mailManager.compareActionAverageContrastByOneDay(list);
				}
			}else {
				log.warn("there hava no data to compare,QQQ");
				return ;
			}
		}else {
			log.warn("this is error,please check the right of the code" );
			return ;
		}
	}
	
	
	/**
	 * @Description:拿到排序的结果<br />
	 * @author CoCo
	 * @version 0.1 2013-7-26
	 * @param selectDBOject
	 * @param logInfoCondition
	 * @return DBCursor
	 */
	private List<ActionLogInfo> getActionLogInfosByConditionAndDBObject(DBObject selectDBOject,
			LogInfoCondition logInfoCondition) {
		// 根据条件封装查询条件
		LogUtil.completeConditionDataForDBObject(selectDBOject, logInfoCondition);
		// 排序查询
		DBCursor dBCursor = logInfoService.getDBCursorForSortAction(selectDBOject);
		List<ActionLogInfo> actionLogInfos = null;
		if (dBCursor == null || dBCursor.size() <= 0) {
			log.warn("this data is error");
			actionLogInfos = null;
		} else {
			ActionLogInfo actionLogInfo = null;
			Query query = null;
			List<BasicLoginfo> basicLoginfos = null;
			actionLogInfos = new ArrayList<ActionLogInfo>();
			for (DBObject dBObject : dBCursor.size()-logInfoCondition.getLevel() < 0?
					dBCursor:dBCursor.skip(dBCursor.size()-logInfoCondition.getLevel())) {
				actionLogInfo = new ActionLogInfo();
				query = new Query();
				query.addCriteria(Criteria.where("url").is(dBObject.get("url")));
				basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class, query);
				if (basicLoginfos != null && basicLoginfos.size() > 0) {
					MailManager.completeTopTenActionInfoForSendMailByOneDay(basicLoginfos.get(0), dBObject, actionLogInfo);
					actionLogInfos.add(actionLogInfo);
				}else {
					log.warn("this BasicLoginfo data is error");
					continue;
				}
			}
		}
		return actionLogInfos;
	}
	
	/**
	 * @Description:调用过程<br />
	 * @author CoCo
	 * @version 0.1 2013-7-31
	 * @param logInfoCondition 条件
	 * @param date 时间
	 * @param level 级别
	 * @param type 统计方式
	 * void
	 */
	private void excuteProcedure(LogInfoCondition logInfoCondition, Date date,int level,String type){
		//查询条件封装--统计频率为天，统计方式为平均时间或者统计次数，level为10条
		LogUtil.comoleteConditionForTimer(logInfoCondition, date, level,
				Constant.STATISTICALFREQUENCY_DAY, type);
		
		if (StringUtils.isEmpty(logInfoService.excuteProcedureForSortActionLogInfo(logInfoCondition))) {
			//过程执行失败，返回空数据
			log.warn("this result is error");
			return ;
		}
	}
	
	@Override
	public void compareTheTopTenActionLogInfoByOneDay() {
		Date yesterday = DateUtil.returnPreviousDay(new Date(), 1);//昨天
		Date beforeYesterday = DateUtil.returnPreviousDay(yesterday, 1);//前天
		int level = 10;//前十
		LogInfoCondition logInfoCondition = new LogInfoCondition();//查询条件
		//调用存储过程--统计方式为天
		excuteProcedure(logInfoCondition,yesterday,level,Constant.STATISTICALMETHODS_REQUESTTIME);
		
		//声明Mongodb原生态object作为查询条件
		DBObject selectDBOject = new BasicDBObject();
		//拿到昨天统计方式为天，统计频率为平均时间 排序的数据
		List<ActionLogInfo> yesterDayTimeCursor = getActionLogInfosByConditionAndDBObject(selectDBOject, logInfoCondition);
		selectDBOject = new BasicDBObject();//拿到昨天统计方式为天，统计频率为请求次数 排序的数据
		logInfoCondition.setStatisticalMethods(Constant.STATISTICALMETHODS_REQUESTCOUNT);
		//拿到昨天统计方式为天，统计频率为统计次数 排序的数据
		List<ActionLogInfo> yesterDayCountCursor = getActionLogInfosByConditionAndDBObject(selectDBOject, logInfoCondition);
		
		logInfoCondition = new LogInfoCondition();
		//调用存储过程--统计方式为天
		excuteProcedure(logInfoCondition,beforeYesterday,level,Constant.STATISTICALMETHODS_REQUESTTIME);
		selectDBOject = new BasicDBObject();//声明Mongodb原生态object作为查询条件
		//拿到前天统计方式为天，统计频率为平均时间 排序的数据
		List<ActionLogInfo> beforeyesterDayTimeCursor = getActionLogInfosByConditionAndDBObject(selectDBOject, logInfoCondition);
		
		//拿到前天统计方式为天，统计频率为请求次数 排序的数据
		selectDBOject = new BasicDBObject();
		logInfoCondition.setStatisticalMethods(Constant.STATISTICALMETHODS_REQUESTCOUNT);
		//拿到前天统计方式为天，统计频率为统计次数 排序的数据
		List<ActionLogInfo> beforeyesterDayCountCursor = getActionLogInfosByConditionAndDBObject(selectDBOject, logInfoCondition);
		
		List<ActionLogInfo> timeCursorList = new ArrayList<ActionLogInfo>();
		//前两天前十的请求时间 比较
		if (LogUtil.validateIfSendEmailForTopTenCompare(yesterDayTimeCursor, beforeyesterDayTimeCursor, logInfoCondition)) {
			//组装数据
			LogUtil.completeActionLogInfoForTopTenAction(timeCursorList, yesterDayTimeCursor, logInfoCondition, 1);
			LogUtil.completeActionLogInfoForTopTenAction(timeCursorList, beforeyesterDayTimeCursor, logInfoCondition, 2);
		}
		
		List<ActionLogInfo> countCursorList = new ArrayList<ActionLogInfo>();
		//前两天前十的请求次数 比较
		if (LogUtil.validateIfSendEmailForTopTenCompare(yesterDayCountCursor, beforeyesterDayCountCursor, logInfoCondition)) {
			//组装数据
			LogUtil.completeActionLogInfoForTopTenAction(countCursorList, yesterDayCountCursor, logInfoCondition, 1);
			LogUtil.completeActionLogInfoForTopTenAction(countCursorList, beforeyesterDayCountCursor, logInfoCondition, 2);
		}
		mailManager.CompareTheTopTenActionLogInfoByOneDay(timeCursorList, countCursorList);
	}
	
	
	
	@Override
	public Map<String, List<LogStatistics>> getTopActionLogInfoByCondition(
			LogInfoCondition logInfoCondition) {
		if (ValidateUtil.objectIsEmpty(logInfoCondition)) {
			//对象为空，抛异常
			throw new LogInfoException(LogInfoExceptionType.Object_is_Null);
		} else {
			//条件的验证
			LogUtil.validateLogInfoCondition(logInfoCondition);
			if (StringUtils.isEmpty(logInfoCondition.getStatisticalMethods()) || ValidateUtil.objectIsEmpty(logInfoCondition.getLevel())
					|| logInfoCondition.getLevel() == 0) {
				//统计方式和级别同时为空，跑异常
				throw new LogInfoException(LogInfoExceptionType.SearchConditionParam_iS_Null);
			}
			//封装数据
			LogUtil.assemblingDataForCondition(logInfoCondition);
		}
		if (StringUtils.isEmpty(logInfoService.excuteProcedureForSortActionLogInfo(logInfoCondition))) {
			//过程执行失败，返回空数据
			log.warn("this result is error");
			return null;
		}
		//声明Mongodb原生态object作为查询条件
		DBObject selectDBOject = new BasicDBObject();
		//根据条件封装查询条件
		LogUtil.completeConditionDataForDBObject(selectDBOject,logInfoCondition);
		//排序查询
		DBCursor dBCursor = logInfoService.getDBCursorForSortAction(selectDBOject);//.sort(selectDBOject)
		if ((dBCursor == null) || (dBCursor != null && dBCursor.size() <= 0)) {
			log.warn("this data is error");
			return null;
		}
		/*else {
			if (dBCursor.size() - logInfoCondition.getLevel() < 0) {
				throw new LogInfoException(LogInfoExceptionType.SortResult_IS_ERROR);
			}
		}
		 */		
		String url = null;//url
		List<LogStatistics> logStatistics = null;
		Map<String, List<LogStatistics>> maps = new HashMap<String, List<LogStatistics>>();
		for (DBObject dbObject : dBCursor.size()-logInfoCondition.getLevel() < 0?dBCursor:dBCursor.skip(dBCursor.size() - logInfoCondition.getLevel())) {
			url = (String) dbObject.get("url");
			logStatistics = new ArrayList<LogStatistics>();
			logInfoCondition.setUri(url);
			searchLogAndCompleteStatistics(logStatistics,logInfoCondition);//封装具体统计数据
			maps.put(LogUtil.regixUrlToActionName(url), logStatistics);
		}
		return maps;
	}
	

	@Override
	public void removeLogForOneWeek() {
		//当前时间的前一周
		Date date = DateUtil.returnPreviousHour(new Date(), 1);
		Query query = new Query();
		query.addCriteria(Criteria.where("reqStartTime").lte(date.getTime()));
		//清除前一周的请求日志信息
		logInfoService.removeReqLogInfoByQuery(ReqLogInfo.class, query);
		
		//根据条件清除系统日志--日志级别不为error的全删除
		query = new Query();
		List<String> list = new ArrayList<String>();
		list.add(Constant.WARN_LOG);
		list.add(Constant.INFO_LOG);
		list.add(Constant.WARNING_LOG);
		query.addCriteria(Criteria.where("createDate").lte(date.getTime()));
		query.addCriteria(Criteria.where("logLevel").in(list));
		logInfoService.removeLogInfoByQuery(LogInfo.class, query);
		
		//根据条件清除系统日志--日志级别为error的,但是编码不为空的全删除
		query = new Query();
		query.addCriteria(Criteria.where("createDate").lte(date.getTime()));
		query.addCriteria(Criteria.where("logLevel").is(Constant.ERROR_LOG));
		query.addCriteria(Criteria.where("errorCode").ne(""));
		logInfoService.removeLogInfoByQuery(LogInfo.class, query);
	}
	
	@Override
	public void completeExceptionErrorCode() {
		List<ExceptionErrorCode> errorCodes = exceptionErrorCodeService.getAllExceptionErrorCodes();
		if (errorCodes != null && errorCodes.size() > 0) {
			/**
			 * 变量声明
			 */
			Date date = new Date();//当前时间
			Date oldDate = DateUtil.returnPreviousHour(date, 12);//前12小时
			Query query = new Query();//查询条件
			List<LogInfo> logInfos = null;//系统日志集合
			ErrorCodeInfo errorCodeInfo = null;//异常编码信息实体对象，用于发送邮件
			List<ErrorCodeInfo> errorCodeInfos = new ArrayList<ErrorCodeInfo>();
		
			for (ExceptionErrorCode exceptionErrorCode : errorCodes) {
				//条件添加
				query.addCriteria(Criteria.where("createDate").lte(date.getTime()));
				query.addCriteria(Criteria.where("createDate").gte(oldDate.getTime()));
				query.addCriteria(Criteria.where("errorCode").is(exceptionErrorCode.getErrorCode()));
				//查询系统日志
				logInfos = logInfoService.searchLogInfosByQuery(LogInfo.class, query);
				
				errorCodeInfo = new ErrorCodeInfo();
				//对象封装
				errorCodeInfo.setErrorCode(exceptionErrorCode.getErrorCode());
				errorCodeInfo.setExceptionInfo(exceptionErrorCode.getExceptionInfo());
				errorCodeInfo.setModuleName(exceptionErrorCode.getModuleName());
				if (logInfos!=null && logInfos.size() > 0) {
					errorCodeInfo.setCount(logInfos.size());
				}else {
					errorCodeInfo.setCount(0);
				}
				errorCodeInfos.add(errorCodeInfo);
			}
			mailManager.completeExceptionErrorCode(errorCodeInfos);
		}else {
			log.warn("there have no datas");
			return ;
		}
	}
	
	@Override
	public void removeLogInfoByUrl(String url) {
		if (StringUtils.isEmpty(url)) {
			throw new LogInfoException(LogInfoExceptionType.ObjectParames_Are_Null);
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("url").is(url));//条件封装
		
		List<BasicLoginfo> basicLoginfos = logInfoService.searchAllBaseLogInfoByQuery(BasicLoginfo.class, query);
		if (basicLoginfos != null && basicLoginfos.size() > 0) {
			//删除action基本信息
			logInfoService.removeBaseLogInfoByQuery(BasicLoginfo.class, query);
			//删除action的统计信息
			logInfoService.removeLogStatisticsByQuery(LogStatistics.class, query);
		}else {
			throw new LogInfoException(LogInfoExceptionType.SearchResultObject_isNull);
		}
	}
}
