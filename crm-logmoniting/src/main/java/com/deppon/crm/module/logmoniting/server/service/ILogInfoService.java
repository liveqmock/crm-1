package com.deppon.crm.module.logmoniting.server.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_count;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public interface ILogInfoService {

	/**
	 * @Description:action 基础设置（保存）<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 * @param basicLoginfo
	 * void
	 */
	public void saveBaseLogInfo(BasicLoginfo basicLoginfo);

	/**
	 * @Description:保存统计之后的日志请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @param logStatistics
	 * void
	 */
	public void saveLogStatistics(LogStatistics logStatistics);
	
	/**
	 * @Description:批量保存  保存统计之后的日志请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 * @param logStatistics
	 * void
	 */
	public void saveLogStatisticsByList(List<LogStatistics> logStatistics);
	
	/**
	 * @Description:得到所有的基础设置action信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @return
	 * List<BasicLoginfo>
	 */
	public List<BasicLoginfo> getAllBasicLoginfos();
	
	/**
	 * @Description:action 基础设置（修改）--最终通过ID去修改）<br />
	 * @author CoCo
	 * @version 0.1 2013-6-18
	 * @param basicLoginfo
	 * void
	 */
	public void updateBaseLogInfo(BasicLoginfo basicLoginfo);
	
	/**
	 * @Description:通过ID去查询BasicLoginfo<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @param basicLoginfo
	 * void
	 */
	public BasicLoginfo findBaseLogInfoById(BasicLoginfo basicLoginfo);
	
	
	/**
	 * @Description:根据条件查询基础设置的action信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @param basicLoginfo
	 * @return
	 * List<T>
	 */
	public List<BasicLoginfo> searchAllBaseLogInfoByQuery(Class<BasicLoginfo> basicLoginfo,Query query);
	
	/**
	 * @Description:通过时间和url查询前一个小时 action的请求日志信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 * @param reqLogInfo
	 * @param query
	 * @return
	 * List<ReqLogInfo>
	 */
	public List<ReqLogInfo> getReqLogInfosByQuery(Class<ReqLogInfo> reqLogInfo,Query query);

	/**
	 * @Description:通过条件查询 日志的统计信息--一条<br />
	 * @author CoCo
	 * @version 0.1 2013-7-1
	 * @param logStatistics
	 * @param query
	 * @return LogStatistics
	 */
	public LogStatistics findOneLogStatisticsByQuery(Class<LogStatistics> logStatistics,Query query);

	/**
	 * @Description:通过条件查询 日志的统计信息--多条<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @param logStatistics 统计信息
	 * @param query 条件
	 * @return List<LogStatistics>
	 */
	public List<LogStatistics> searchAllLogStatisticsByQuery(Class<LogStatistics> logStatistics,Query query);
	
	/**
	 * @Description:利用url到中间表查询每次统计的请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * @param reqLogInfo_count
	 * @param query
	 * @return
	 * ReqLogInfo_count
	 */
	public ReqLogInfo_count findReqLogInfo_countByURI(Class<ReqLogInfo_count> reqLogInfo_count,Query query);

	/**
	 * @Description:利用url到中间表reqLogInfo_searchCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 * @param reqLogInfo_searchCount
	 * @param query
	 * @return ReqLogInfo_averageCount
	 */
	public ReqLogInfo_averageCount findReqLogInfo_averageCountByURI(Class<ReqLogInfo_averageCount> reqLogInfo_averageCount,Query query);
	
	/**
	 * @Description:调用存储过程,取得某个时间段日志的统计信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @return
	 * String
	 */
	public String excuteProcedureForOneHourRequestInfo(long startTime,long endTime);

	/**
	 * @Description:以天为单位。求出每个action统计信息的平均时长，平均请求次数，和最大请求时间-存放中间reqLogInfo_averageCount<br />
	 * @author CoCo
	 * @version 0.1 2013-7-19
	 * @param logInfoCondition
	 * @return String
	 */
	public String excuteProcedureForCompareActionAverageContrast(LogInfoCondition logInfoCondition);

	/**
	 * @Description:根据传入条件，计算数据存入中间表，用于排序，例如前十等等<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param logInfoCondition
	 * @return String
	 */
	public String excuteProcedureForSortActionLogInfo(LogInfoCondition logInfoCondition);

	/**
	 * @Description:利用原生态api 根据查询条件 对查询结果进行排序<br />
	 * @author CoCo
	 * @version 0.1 2013-7-25
	 * @param dbObject
	 * @return DBCursor
	 */
	public DBCursor getDBCursorForSortAction(DBObject dbObject); 
	
	/**
	 * @Description:根据条件,清除请求日志<br />
	 * @author CoCo
	 * @version 0.1 2013-7-26
	 * @param reqLogInfo
	 * @param query
	 * void
	 */
	public void removeReqLogInfoByQuery(Class<ReqLogInfo> reqLogInfo,Query query);

	/**
	 * @Description:根据条件,清除系统日志<br />
	 * @author CoCo
	 * @version 0.1 2013-7-26
	 * void
	 */
	public void removeLogInfoByQuery(Class<LogInfo> logInfo,Query query);

	/**
	 * @Description:根据条件查询系统日志<br />
	 * @author CoCo
	 * @version 0.1 2013-7-30
	 * @param logInfo
	 * @param query
	 * @return List<LogInfo>
	 */
	public List<LogInfo> searchLogInfosByQuery(Class<LogInfo> logInfo,Query query);
	
	/**
	 * @Description:根据条件 action基础信息删除<br />
	 * @author CoCo
	 * @version 0.1 2013-8-20
	 * @param basicLoginfo
	 * void
	 */
	public void removeBaseLogInfoByQuery(Class<BasicLoginfo> basicLoginfo,Query query);
	
	
	/**
	 * @Description:根据条件删除删除action的统计信息<br />
	 * @author CoCo
	 * @version 0.1 2013-8-20
	 * @param logStatistics
	 * @param query
	 * void
	 */
	public void removeLogStatisticsByQuery(Class<LogStatistics> logStatistics,Query query);
	
}
