package com.deppon.crm.module.logmoniting.server.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.crm.module.logmoniting.server.dao.ILogInfoDao;
import com.deppon.crm.module.logmoniting.server.service.ILogInfoService;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_count;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class LogInfoService implements ILogInfoService{

	private ILogInfoDao logInfoDao;
	/**
	 * Description:logInfoDao<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 */
	public ILogInfoDao getLogInfoDao() {
		return logInfoDao;
	}

	/**
	 * Description:logInfoDao<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 */
	public void setLogInfoDao(ILogInfoDao logInfoDao) {
		this.logInfoDao = logInfoDao;
	}

	@Override
	public void saveBaseLogInfo(BasicLoginfo basicLoginfo) {
		logInfoDao.saveBaseLogInfo(basicLoginfo);
	}

	@Override
	public void updateBaseLogInfo(BasicLoginfo basicLoginfo) {
		logInfoDao.updateBaseLogInfo(basicLoginfo);
	}

	@Override
	public List<ReqLogInfo> getReqLogInfosByQuery(Class<ReqLogInfo> reqLogInfo,Query query){
		return logInfoDao.getReqLogInfosByQuery(reqLogInfo, query);
	}

	@Override
	public void saveLogStatistics(LogStatistics logStatistics) {
		logInfoDao.saveLogStatistics(logStatistics);
	}

	@Override
	public List<BasicLoginfo> getAllBasicLoginfos() {
		return logInfoDao.getAllBasicLoginfos();
	}

	@Override
	public BasicLoginfo findBaseLogInfoById(BasicLoginfo basicLoginfo) {
		return logInfoDao.findBaseLogInfoById(basicLoginfo);
	}

	@Override
	public List<BasicLoginfo> searchAllBaseLogInfoByQuery(Class<BasicLoginfo> basicLoginfo,Query query) {
		return logInfoDao.searchAllBaseLogInfoByQuery(basicLoginfo,query);
	}

	@Override
	public void saveLogStatisticsByList(List<LogStatistics> logStatistics) {
		logInfoDao.saveLogStatisticsByList(logStatistics);
	}

	@Override
	public LogStatistics findOneLogStatisticsByQuery(
			Class<LogStatistics> logStatistics, Query query) {
		return logInfoDao.findOneLogStatisticsByQuery(logStatistics, query);
	}

	@Override
	public ReqLogInfo_count findReqLogInfo_countByURI(
			Class<ReqLogInfo_count> reqLogInfo_count, Query query) {
		return logInfoDao.findReqLogInfo_countByURI(reqLogInfo_count, query);
	}

	@Override
	public String excuteProcedureForOneHourRequestInfo(long startTime,
			long endTime) {
		return logInfoDao.excuteProcedureForOneHourRequestInfo(startTime, endTime);
	}

	@Override
	public List<LogStatistics> searchAllLogStatisticsByQuery(
			Class<LogStatistics> logStatistics, Query query) {
		return logInfoDao.searchAllLogStatisticsByQuery(logStatistics, query);
	}

	@Override
	public String excuteProcedureForCompareActionAverageContrast(
			LogInfoCondition logInfoCondition) {
		return logInfoDao.excuteProcedureForCompareActionAverageContrast(logInfoCondition);
	}

	@Override
	public ReqLogInfo_averageCount findReqLogInfo_averageCountByURI(
			Class<ReqLogInfo_averageCount> reqLogInfo_averageCount, Query query) {
		return logInfoDao.findReqLogInfo_averageCountByURI(reqLogInfo_averageCount, query);
	}

	@Override
	public String excuteProcedureForSortActionLogInfo(
			LogInfoCondition logInfoCondition) {
		return logInfoDao.excuteProcedureForSortActionLogInfo(logInfoCondition);
	}

	@Override
	public DBCursor getDBCursorForSortAction(DBObject dbObject) {
		return logInfoDao.getDBCursorForSortAction(dbObject);
	}

	@Override
	public void removeReqLogInfoByQuery(Class<ReqLogInfo> reqLogInfo,
			Query query) {
		logInfoDao.removeReqLogInfoByQuery(reqLogInfo, query);
	}

	@Override
	public void removeLogInfoByQuery(Class<LogInfo> logInfo, Query query) {
		logInfoDao.removeLogInfoByQuery(logInfo, query);
	}

	@Override
	public List<LogInfo> searchLogInfosByQuery(Class<LogInfo> logInfo,
			Query query) {
		return logInfoDao.searchLogInfosByQuery(logInfo, query);
	}

	@Override
	public void removeBaseLogInfoByQuery(Class<BasicLoginfo> basicLoginfo,
			Query query) {
		logInfoDao.removeBaseLogInfoByQuery(basicLoginfo, query);
	}

	@Override
	public void removeLogStatisticsByQuery(Class<LogStatistics> logStatistics,
			Query query) {
		logInfoDao.removeLogStatisticsByQuery(logStatistics, query);
	}

}
