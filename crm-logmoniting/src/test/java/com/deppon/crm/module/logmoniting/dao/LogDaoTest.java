package com.deppon.crm.module.logmoniting.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Sort;

import com.deppon.crm.module.frameworkimpl.server.log.bean.LogInfo;
import com.deppon.crm.module.frameworkimpl.server.log.bean.ReqLogInfo;
import com.deppon.crm.module.logmoniting.server.utils.DateUtil;
import com.deppon.crm.module.logmoniting.server.utils.LogUtil;
import com.deppon.crm.module.logmoniting.server.utils.ValidateUtil;
import com.deppon.crm.module.logmoniting.shared.domain.BasicLoginfo;
import com.deppon.crm.module.logmoniting.shared.domain.Constant;
import com.deppon.crm.module.logmoniting.shared.domain.LogInfoCondition;
import com.deppon.crm.module.logmoniting.shared.domain.LogStatistics;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_averageCount;
import com.deppon.crm.module.logmoniting.shared.domain.ReqLogInfo_count;
import com.deppon.crm.module.logmoniting.utils.BeanUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

public class LogDaoTest extends BeanUtils{
	
	@Before
	public void setUP() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * @Description:action 基础设置（保存）<br />
	 * @version 0.1 2013-6-18
	 * @param basicLoginfo
	 * void
	 */
	@Test
	public void testSaveBaseLogInfo(){
		for (int i = 0; i < 2; i++) {
			System.out.println(new Date().toLocaleString());
			BasicLoginfo basicLoginfo = new BasicLoginfo();
			basicLoginfo.setId(LogUtil.getUUID());
	    	basicLoginfo.setCreateUser("86301");
	    	basicLoginfo.setCreateDate(System.currentTimeMillis());
	    	basicLoginfo.setActionName("customer_test");
	    	basicLoginfo.setBaseCount(999999);
	    	basicLoginfo.setBaseTime(999999);
	    	basicLoginfo.setBlackList(false);
	    	basicLoginfo.setInvalid(false);
	    	basicLoginfo.setCountFloat(1);
	    	basicLoginfo.setMailPerson("小文-----@qq.com");
	    	basicLoginfo.setModulename("coco_coco");
	    	basicLoginfo.setTimeFloat(8888888);
	    	basicLoginfo.setUrl("553509117@qq.com" + i);
	    	logDao.saveBaseLogInfo(basicLoginfo);
		}
		
	}
	/**
	 * @Description:得到所有的基础设置action信息<br />
	 * @version 0.1 2013-6-27
	 * @return
	 * List<BasicLoginfo>
	 */
	@Test
	public void testGetAllBasicLoginfos(){
		List<BasicLoginfo> list = logDao.getAllBasicLoginfos();
		System.out.println(list.size());
		if (list.size() >0) {
			for (BasicLoginfo basicLoginfo2 : list) {
//				if (basicLoginfo2.getId().equals("1c5831c2-a3bc-426a-829b-b4438e2dbb35")) {
//					System.out.println(basicLoginfo2.getCountFloat());
//					System.out.println(basicLoginfo2.getId());
//				}
				if (basicLoginfo2.isBlackList() == false && basicLoginfo2.isInvalid() == false) {
					System.out.println(basicLoginfo2.getId());
				}
//				if (basicLoginfo2.getActionName().equals("customer_test")) {
//					System.out.println(basicLoginfo2.getActionName());
//					System.out.println(basicLoginfo2.getId());
//					System.out.println(basicLoginfo2.getUrl());
//					System.out.println(basicLoginfo2.getBaseCount());
//				}
			}
		}
	}
	
	/**
	 * @Description:action 基础设置（修改）--最终通过ID去修改<br />
	 * @version 0.1 2013-6-18
	 * @param basicLoginfo
	 * void
	 */
	@Test
	public void testUpdateBaseLogInfo(){
		//TODO 存在问题
		//1c5831c2-a3bc-426a-829b-b4438e2dbb35
		BasicLoginfo basicLoginfo = new BasicLoginfo();
		basicLoginfo.setId("8153c07f-7195-4080-bf04-390f4b3bbd14");
    	basicLoginfo.setBaseCount(99999999);
    	basicLoginfo.setBaseTime(6888888888888888l);
    	basicLoginfo.setBlackList(false);
    	basicLoginfo.setInvalid(false);
    	basicLoginfo.setCountFloat(7777777777l);
    	basicLoginfo.setMailPerson("李国文@qq.com");
    	basicLoginfo.setModifyUser("李国文");
		logDao.updateBaseLogInfo(basicLoginfo);
		System.out.println(basicLoginfo.getActionName());
		System.out.println(basicLoginfo.getCountFloat());
		System.out.println(basicLoginfo.isInvalid());
	}
	
	/**
	 * @Description:通过ID去查询BasicLoginfo<br />
	 * @version 0.1 2013-6-27
	 * @param basicLoginfo
	 * void
	 */
	@Test
	public void testFindBaseLogInfoById(){
		BasicLoginfo basicLoginfo = new BasicLoginfo();
		basicLoginfo.setId("1c5831c2-a3bc-426a-829b-b4438e2dbb35");
		basicLoginfo = logDao.findBaseLogInfoById(basicLoginfo);
		if (!ValidateUtil.objectIsEmpty(basicLoginfo)) {
			System.out.println(basicLoginfo.getCountFloat());
			System.out.println(basicLoginfo.getId());
			System.out.println(basicLoginfo.isInvalid());
			System.out.println(basicLoginfo.isBlackList());
			System.out.println(basicLoginfo.getBaseTime());
		}
	
	}
	
	/**
	 * @Description:根据条件查询基础设置的action信息<br />
	 * @version 0.1 2013-6-27
	 * @param basicLoginfo
	 * @return
	 * List<T>
	 */
	@Test
	public void testSearchAllBaseLogInfo(){
		List<BasicLoginfo> list = null;
		BasicLoginfo basicLoginfo = new BasicLoginfo();
		basicLoginfo.setInvalid(false);
		basicLoginfo.setBlackList(false);
		basicLoginfo.setActionName("customer_test");
		basicLoginfo.setUrl("4535");
		basicLoginfo.setModulename("crm-customer");
		Query query = new Query();
//		//设置查询条件为 预警和黑名单
//		query.addCriteria(Criteria.where("invalid").is(basicLoginfo.isBlackList()).and("blackList").is(basicLoginfo.isBlackList()));//预警
//		list = logDao.searchAllBaseLogInfoByQuery(BasicLoginfo.class,query);
//		if (list.size() >0) {
//			System.out.println(list.size());
//		}
		
		list = new ArrayList<BasicLoginfo>();
		query = new Query();
//		query.addCriteria(Criteria.where("url").is(basicLoginfo.getUrl()));
		query.addCriteria(Criteria.where("modulename").is(basicLoginfo.getModulename()));
		query.addCriteria(Criteria.where("actionName").is(basicLoginfo.getActionName()));
		Sort sort = new Sort("modulename", Order.ASCENDING);
		
		list = logDao.searchAllBaseLogInfoByQuery(BasicLoginfo.class,query);
		for (BasicLoginfo basicLoginfo2 : list) {
			System.out.println(basicLoginfo2.getId()+ "--" + basicLoginfo2.getCountFloat());
			
		}
	}
	
	/**
	 * @Description:批量保存  保存统计之后的日志请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-6-29
	 * @param logStatistics
	 * void
	 */
	@Test
	public void testSaveLogStatisticsByList(){
		List<LogStatistics> list = new ArrayList<LogStatistics>();
		
		LogStatistics logStatistics = null;
		
		Date date = DateUtil.returnPreviousHour(new Date(), 1);
		for (int i = 0; i < 8; i++) {
			logStatistics = new LogStatistics();
			logStatistics.setYear(DateUtil.getYear(date));
			logStatistics.setMonth(7);
			logStatistics.setDay(10);
			logStatistics.setHour(i);//TODO
			//ID---UUID
			logStatistics.setId(LogUtil.getUUID());
			//创建时间
			logStatistics.setCreateDate(System.currentTimeMillis());
			//创建人
			logStatistics.setCreateUser("李国文_test");
			logStatistics.setRequestCount(8300);//一小时总次数
			logStatistics.setMaxRequestTime(32300);//请求最长时长 针对年月日时统计该数据
			logStatistics.setAvgRequestTime(500300);////请求时间统计  平均时间（1小
			logStatistics.setBaseLoginfoId("中国人");
			logStatistics.setUrl("coco_customer@qq.com");
			logStatistics.setModulename("customer");
			
			list.add(logStatistics);	
		}
		
		logDao.saveLogStatisticsByList(list);
	}
	
	/**
	 * @Description:利用url到中间表查询每小时统计的请求信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * @param reqLogInfo_count
	 * @param query
	 * @return ReqLogInfo_count
	 */
	@Test
	public void testFindReqLogInfo_countByURI(){
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is("/authorization/findAuthedUsualFunction.action"));
		ReqLogInfo_count count = logDao.findReqLogInfo_countByURI(ReqLogInfo_count.class, query);
		System.out.println(count.getMaxRequestTime());
		System.out.println(count.getAvgRequestTime());
		System.out.println(count.getUrl());
	}
	
	
	/**
	 * @Description:调用存储过程,取得某个时间段日志的统计信息<br />
	 * @author CoCo
	 * @version 0.1 2013-7-16
	 * @param startTime 开始时间
	 * @param endTime	结束时间
	 * @return String
	 */
	@Test
	public void testExcuteProcedureForOneHourRequestInfo(){
		Date date = new Date();//当前时间
		long currentTimeLong = date.getTime();//当前时间的毫秒数
		System.out.println(currentTimeLong + "    ddddd");
		System.out.println(currentTimeLong -Constant.OneHourForLongMills + "   ddd");
		System.out.println(logDao.excuteProcedureForOneHourRequestInfo(currentTimeLong -Constant.OneHourForLongMills, currentTimeLong));
	}
	
	/**
	 * @Description:通过条件查询 日志的统计信息--多条<br />
	 * @author CoCo
	 * @version 0.1 2013-7-17
	 * @param logStatistics 统计信息
	 * @param query 条件
	 * @return List<LogStatistics>
	 */
	@Test
	public void testSearchAllLogStatisticsByQuery(){
		LogInfoCondition condition = new LogInfoCondition();
		condition.setUri("coco_customer@qq.com");
		condition.setYear(2013);
		condition.setMonth(7);
		condition.setDay(16);
		Query query = new Query();
		List<LogStatistics> list  = new ArrayList<LogStatistics>();
		query.addCriteria(Criteria.where("url").is(condition.getUri()).and("year").is(condition.getYear())
				.and("month").is(condition.getMonth()).and("day").is(condition.getDay()));
		list = logDao.searchAllLogStatisticsByQuery(LogStatistics.class, query);
		System.out.println(list.size());
		for (LogStatistics logStatistics2 : list) {
			System.out.println(logStatistics2.getDay() + ":" + logStatistics2.getAvgRequestTime() + ":" + logStatistics2.getMaxRequestTime());
			System.out.println(logStatistics2.getId());
		}
		
	}
	
	
	@Test
	public void testExcuteProcedureForSortActionLogInfo(){
		LogInfoCondition condition = new LogInfoCondition();
		condition.setYear(2013);
		condition.setBeginmonth(7);
		condition.setEndmonth(7);
		condition.setBeginday(15);
		condition.setEndday(16);
		condition.setBeginhour(0);
		condition.setEndhour(7);
		String info = logDao.excuteProcedureForSortActionLogInfo(condition);
		System.out.println(info);
	}
	
	@Test
	public void testExcuteProcedureForCompareActionAverageContrast(){
		LogInfoCondition condition = new LogInfoCondition();
		condition.setYear(2013);
		condition.setMonth(7);
		condition.setDay(15);
		String info = logDao.excuteProcedureForCompareActionAverageContrast(condition);
		System.out.println(info);
	}
	
	@Test
	public void testFindReqLogInfo_averageCountByURI(){
		ReqLogInfo_averageCount count = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is("553509117@qq.com"));
		count = logDao.findReqLogInfo_averageCountByURI(ReqLogInfo_averageCount.class, query);
		System.out.println(count.toString());
		System.out.println(count.getAvgRequestTime());
		System.out.println(count.getMaxRequestTime());
		System.out.println(count.getRequestCount());
		
		LogInfoCondition logInfoCondition = new LogInfoCondition();
		Date date = new Date();
		/**
		 * 设置时间
		 */
		logInfoCondition.setYear(DateUtil.getYear(DateUtil.returnPreviousDay(date, 1)));
		logInfoCondition.setMonth(DateUtil.getMonth(DateUtil.returnPreviousDay(date, 1)));
		logInfoCondition.setDay(DateUtil.getDay(DateUtil.returnPreviousDay(date, 1)));
		
		System.out.println(logInfoCondition.getYear());
		System.out.println(logInfoCondition.getMonth());
		System.out.println(logInfoCondition.getDay());
	}
	
	@Test
	public void testGetDBCursorForSortAction(){
		DBObject selectDBOject = new BasicDBObject();
		selectDBOject.put("requestCount",  Order.ASCENDING);
		selectDBOject.put("modulename", "custview");
		selectDBOject.put("requestCount",  new BasicDBObject(QueryOperators.EXISTS, true));
		DBCursor dBCursor = logDao.getDBCursorForSortAction(selectDBOject);
		for (DBObject dbObject : dBCursor.skip(dBCursor.size() - 5)) {
			System.out.println(dbObject.get("modulename")+":" + dbObject.get("requestCount"));
		}
	}
	
	@Test
	public void testRemoveReqLogInfoByQuery(){
		Query query = new Query();
		Date date = new Date();
//		lte(DateUtil.returnPreviousHour(date, 1).getTime())
		query.addCriteria(Criteria.where("reqParameters").is(""));
		logDao.removeReqLogInfoByQuery(ReqLogInfo.class, query);
	}
	
	@Test
	public void testRemoveLogInfoByQuery(){
		Query query = new Query();
		List<String> list = new ArrayList<String>();
		list.add(Constant.WARN_LOG);
		list.add(Constant.INFO_LOG);
		list.add(Constant.WARNING_LOG);
//		list.add(Constant.ERROR_LOG);
		query.addCriteria(Criteria.where("logLevel").in(list));
//		query.addCriteria(Criteria.where("errorCode").exists(true));
		logDao.removeLogInfoByQuery(LogInfo.class, query);
		 
		
		query = new Query();
		query.addCriteria(Criteria.where("logLevel").is(Constant.ERROR_LOG));
		query.addCriteria(Criteria.where("errorCode").ne(""));
		logDao.removeLogInfoByQuery(LogInfo.class, query);
	}
	
	@Test
	public void testSearchLogInfosByQuery(){
		Query query = new Query();
		Date date = new Date();
		Date oldDate = DateUtil.returnPreviousHour(date, 12);
		query.addCriteria(Criteria.where("createDate").lte(date.getTime()));
		query.addCriteria(Criteria.where("createDate").gte(oldDate.getTime()));
		query.addCriteria(Criteria.where("errorCode").is("dd"));
		
		List<LogInfo> list = null;
		list = logDao.searchLogInfosByQuery(LogInfo.class, query);
		for (LogInfo logInfo : list) {
			System.out.println(logInfo.getErrorCode());
		}
	}
	
	@Test
	public void testGetReqLogInfosByQuery(){
		Query query = new Query();
		query.addCriteria(Criteria.where("action").is("action"));
		List<ReqLogInfo> list = logDao.getReqLogInfosByQuery(ReqLogInfo.class, query);
		for (ReqLogInfo reqLogInfo : list) {
			System.out.println(reqLogInfo.getAction());
		}
	}
	
	@Test
	public void testRemoveBaseLogInfoByQuery(){
		Query query = new Query();
		query.addCriteria(Criteria.where("url").is("553509117@qq.com0"));
//		logDao.removeBaseLogInfoByQuery(BasicLoginfo.class, query);
		
		
		List<BasicLoginfo> list = logDao.searchAllBaseLogInfoByQuery(BasicLoginfo.class,query);
		System.out.println(list.size());
	}
	
	@Test
	public void testRemoveLogStatisticsByQuery(){
		Query query = new Query();
		query.addCriteria(Criteria.where("url").is("abcdefg"));
		logDao.removeLogStatisticsByQuery(LogStatistics.class, query);
	}
	
}
