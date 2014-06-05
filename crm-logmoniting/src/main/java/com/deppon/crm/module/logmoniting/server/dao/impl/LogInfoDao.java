package com.deppon.crm.module.logmoniting.server.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DbCallback;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.crm.module.logmoniting.server.dao.ILogInfoDao;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_count;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;


public class LogInfoDao implements ILogInfoDao{
	private MongoOperations mongoTemplate;
	//统计中间表
	private static final String REQLOGINFO_COUNT = "reqLogInfo_count";
	private static final String REQLOGINFO_AVERAGECOUNT = "reqLogInfo_averageCount";
	private static final String REQLOGINFO_SORTCOUNT = "reqLogInfo_sortCount";
	
	/**
	 * Description:mongoTemplate<br />
	 * @author CoCo
	 * @version 0.1 2013-6-27
	 */
	public void setMongoTemplate(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	/**
	 * Description:mongoTemplate<br />
	 * @author CoCo
	 * @version 0.1 2013-7-29
	 */
	public MongoOperations getMongoTemplate() {
		return mongoTemplate;
	}


	@Override
	public void saveBaseLogInfo(BasicLoginfo basicLoginfo) {
		mongoTemplate.insert(basicLoginfo);
	}

	@Override
	public void updateBaseLogInfo(BasicLoginfo basicLoginfo) {
		Update update = Update.update("invalid", basicLoginfo.isInvalid());
		update.set("blackList", basicLoginfo.isBlackList());
		update.set("baseTime", basicLoginfo.getBaseTime());
		update.set("timeFloat", basicLoginfo.getTimeFloat());
		update.set("baseCount", basicLoginfo.getBaseCount());
		update.set("countFloat", basicLoginfo.getCountFloat());
		update.set("mailPerson", basicLoginfo.getMailPerson());
		update.set("modifyDate", basicLoginfo.getModifyDate());
		update.set("modifyUser", basicLoginfo.getModifyUser());
		mongoTemplate.updateFirst(new Query(Criteria.where("id").is(basicLoginfo.getId())), update,BasicLoginfo.class);
	}

	@Override
	public List<BasicLoginfo> searchAllBaseLogInfoByQuery(Class<BasicLoginfo> basicLoginfo,Query query) {
		return mongoTemplate.find(query, basicLoginfo);
	}

	@Override
	public List<LogStatistics> searchAllLogStatisticsByQuery(Class<LogStatistics> logStatistics,Query query){
		return mongoTemplate.find(query, logStatistics);
	}
	
	@Override
	public void saveLogStatistics(LogStatistics logStatistics) {
		mongoTemplate.insert(logStatistics);
	}

	@Override
	public List<ReqLogInfo> getReqLogInfosByQuery(
			Class<ReqLogInfo> reqLogInfo, Query query) {
		return mongoTemplate.find(query, reqLogInfo);
	}


	@Override
	public List<BasicLoginfo> getAllBasicLoginfos() {
		return mongoTemplate.findAll(BasicLoginfo.class);
	}

	@Override
	public BasicLoginfo findBaseLogInfoById(BasicLoginfo basicLoginfo) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(basicLoginfo.getId())), BasicLoginfo.class);
	}

	@Override
	public void saveLogStatisticsByList(List<LogStatistics> logStatistics) {
		mongoTemplate.insertAll(logStatistics);
	}

	@Override
	public LogStatistics findOneLogStatisticsByQuery(
			Class<LogStatistics> logStatistics, Query query) {
		return mongoTemplate.findOne(query, logStatistics);
	}

	@Override
	public ReqLogInfo_count findReqLogInfo_countByURI(Class<ReqLogInfo_count> reqLogInfo_count,Query query) {
		return mongoTemplate.findOne(query, ReqLogInfo_count.class, REQLOGINFO_COUNT);
	}
	
	@Override
	public ReqLogInfo_averageCount findReqLogInfo_averageCountByURI(Class<ReqLogInfo_averageCount> reqLogInfo_averageCount,Query query){
		return mongoTemplate.findOne(query, reqLogInfo_averageCount, REQLOGINFO_AVERAGECOUNT);
	}
	
	@Override
	public String excuteProcedureForOneHourRequestInfo(final long reqStartTime,final long reqEndTime) {
		return mongoTemplate.execute(new DbCallback<String>(){
			public String doInDB(DB db) throws MongoException,
			DataAccessException {
				return  (String) db.eval("getReqLogInfoCount("+reqStartTime+","+ reqEndTime+")".toString());
			}
			});
	}

	@Override
	public String excuteProcedureForCompareActionAverageContrast(
			LogInfoCondition logInfoCondition) {
		final int year = logInfoCondition.getYear();
		final int month = logInfoCondition.getMonth();
		final int day = logInfoCondition.getDay();
		return mongoTemplate.execute(new DbCallback<String>(){
			public String doInDB(DB db) throws MongoException,
			DataAccessException {
				return  (String) db.eval(
						"getReqLogInfo_averageCount("+year+"," + month+","+day +")".toString());
			}
			});
	}

	@Override
	public DBCursor getDBCursorForSortAction(DBObject dbObject) {
		DBCollection dbCollection = mongoTemplate.getCollection(REQLOGINFO_SORTCOUNT);
		DBCursor dBCursor = dbCollection.find(dbObject).sort(dbObject);
		return dBCursor;
	}

	@Override
	public String excuteProcedureForSortActionLogInfo(
			LogInfoCondition logInfoCondition) {
		final int year = logInfoCondition.getYear();//年
		final int beginmonth = logInfoCondition.getBeginmonth();
		final int endmonth = logInfoCondition.getEndmonth();
		final int beginday = logInfoCondition.getBeginday();
		final int endday = logInfoCondition.getEndday();
		final int beginhour = logInfoCondition.getBeginhour();
		final int endhour = logInfoCondition.getEndhour();
		return mongoTemplate.execute(new DbCallback<String>(){
			public String doInDB(DB db) throws MongoException,
			DataAccessException {
				return  (String) db.eval(
						"getReqLogInfo_SortCount("+year+"," + beginmonth+","+endmonth + ","+ beginday + ","+endday+"," + beginhour + "," + endhour+")".toString());
			}
			});
	}

	@Override
	public void removeReqLogInfoByQuery(Class<ReqLogInfo> reqLogInfo,Query query) {
		mongoTemplate.remove(query, reqLogInfo);
	}

	@Override
	public void removeLogInfoByQuery(Class<LogInfo> logInfo, Query query) {
		mongoTemplate.remove(query, logInfo);
	}

	@Override
	public List<LogInfo> searchLogInfosByQuery(Class<LogInfo> logInfo,
			Query query) {
		return mongoTemplate.find(query, logInfo);
	}
	
	@Override
	public void removeBaseLogInfoByQuery(Class<BasicLoginfo> basicLoginfo,Query query) {
		mongoTemplate.remove(query, basicLoginfo);
	}
	
	@Override
	public void removeLogStatisticsByQuery(Class<LogStatistics> logStatistics,
			Query query) {
		mongoTemplate.remove(query, logStatistics);
	}
}
