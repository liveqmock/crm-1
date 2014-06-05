package com.deppon.crm.module.client.sync.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.sync.dao.ISearchMemberInfosDao;
import com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.TransactionNoInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfoDetail;

/**
 * 
 * @author li 写入客户主数据待同步日志表
 * 
 */
public class CustomerInfoLog {

	private static Log log = LogFactory.getLog(CustomerInfoLog.class);
//	@Autowired
	static IWaitCustomerLogDao waitCustomerLogDao;
//	@Autowired
	static ISearchMemberInfosDao searchMemberInfosDao;
	// 待发送日志的缓存
	private final static ThreadLocal<List<CustTransationOperation>> cache = new ThreadLocal<List<CustTransationOperation>>();

	/**
	 * 清空线程本地变量，建议在Action的开头调用一次，以避免万一存在前一个请求遗漏下来的数据
	 */
	public static void init() {
		cache.remove();
	}

	/**
	 * 
	 * 写入客户主数据待同步日志表
	 * 
	 * @param custTransationOperationList
	 *            客户编码
	 * @return 是否成功
	 */
	public static boolean writeCustomerLog(List<CustTransationOperation> custTransationOperationList)	 {
		// 这里改为不检查空，开发组存在传入空的可能。
		/*
		Check.notNull(custTransationOperationList, "WriteCustomerLog.writeCustomerLog method arguments custTransationOperationList can not be null");
		Check.notZero(custTransationOperationList.size(), "WriteCustomerLog.writeCustomerLog method arguments custTransationOperationList can not be Empty");
		 */
		if (cache.get() == null) {
			cache.set(new LinkedList<CustTransationOperation>());
		}
		cache.get().addAll(custTransationOperationList);
		return true;
	}

	/**
	 * 将缓存在线程本地变量中的数据写入待发送Log表。
	 * <B>应确保在Action结束之前调用</B>
	 * @return 是否成功
	 */
	public static boolean commit() {
		// Patten 0 - 全量数据传输，各相关表的数据都作为操作模式U传输，ERP端相关数据判断之后再做操作。
		//加入判断
		if (null == cache.get() || cache.get().size()<1) {
			log.debug("can not to be commited,because no data for this operation");
			return false;
		}
		insertWaitingLogTable(cache.get(), "0");
		cache.remove();
		return true;
	}

	/**
	 * @param memberNumber
	 * @param jsonString
	 */
	private static void insertWaitingLogTable(List<CustTransationOperation> custTransationOperationList, String pattern) {
		// 生成事务编号
		TransactionNoInfo transInfo = waitCustomerLogDao.getTransactionNo();
		String transactionNo = transInfo.getTRANSACTION_NO();

		// 插入客户主数据待发送列表
		WaitCustomerLogInfo logInfo = new WaitCustomerLogInfo();
		logInfo.setTRANSACTION_NO(transactionNo);
		logInfo.setCUSTOMER_NO(null);
		logInfo.setPATTERN(pattern);
		waitCustomerLogDao.insertWriteCustomerLog(logInfo);

		// 插入客户主数据待发送明细
		for (CustTransationOperation custTransationOperation : custTransationOperationList) {
			WaitCustomerLogInfoDetail logInfoDetail = new WaitCustomerLogInfoDetail();
			logInfoDetail.setTRANSACTION_NO(transactionNo);
			logInfoDetail.setTABLE_NAME(custTransationOperation.getTableName().name());
			logInfoDetail.setTABLE_KEYWORD(custTransationOperation.getKeyword());
			logInfoDetail.setOPERATION_FLG(custTransationOperation.getOptFlg().name());
			waitCustomerLogDao.insertWriteCustomerLogDetail(logInfoDetail);
		}
	}

	public IWaitCustomerLogDao getWaitCustomerLogDao() {
		return waitCustomerLogDao;
	}

	public void setWaitCustomerLogDao(IWaitCustomerLogDao waitCustomerLogDao) {
		CustomerInfoLog.waitCustomerLogDao = waitCustomerLogDao;
	}

	public ISearchMemberInfosDao getSearchMemberInfosDao() {
		return searchMemberInfosDao;
	}

	public void setSearchMemberInfosDao(ISearchMemberInfosDao searchMemberInfosDao) {
		CustomerInfoLog.searchMemberInfosDao = searchMemberInfosDao;
	}
}