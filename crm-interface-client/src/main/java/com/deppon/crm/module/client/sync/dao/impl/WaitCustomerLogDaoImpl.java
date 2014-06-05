package com.deppon.crm.module.client.sync.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao;
import com.deppon.crm.module.client.sync.domain.TransactionNoInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfoDetail;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @author li
 * 客户主数据待发送日志表DAO 
 * */
public class WaitCustomerLogDaoImpl extends iBatis3DaoImpl implements IWaitCustomerLogDao  {
	private final String NAMESPACE = "com.deppon.crm.module.client.shared.domain.WaitCustomerLog.";
	// 插入客户主数据待发送列表
	private final String SELECT_TRANSACTION_NO = "getTransactionNo";
	// 插入客户主数据待发送列表
	private final String INSERT_WAIT_CUSTOMER_LOG = "insert_crm_cmdtbs";
	// 客户主数据待发送明细表
	private final String INSERT_WAIT_CUSTOMER_LOG_DETAIL = "insert_crm_cmdtbsd";
	// 检索待发送记录
	private final String SELECT_WAITING_SEND_DATA = "getWaitingSendDatas";
	// 检索待发送明细
	private final String SELECT_WAITING_SEND_DETAILS = "getWaitingSendDetails";
	// 检索待发送记录状态
	private final String SELECT_WAITING_SEND_STATUS = "getWaitingSendStatus";
	// 更新标记为发送中
	private final String UPDATE_SENDING_FLG = "updateSendingFlg";
	// 更新标记为成功
	private final String UPDATE_SUCCESS_FLG = "updateSuccessFlg";
	// 更新标记为失败
	private final String UPDATE_FAIL_FLG = "updateFailFlg";
	
	// 一次轮询的最大件数
	private static final int MAX_ROW_NUMBER = 800;

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao#insertWriteCustomerLog(com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo)
	 */
	@Override
	public void insertWriteCustomerLog(WaitCustomerLogInfo logInfo) {
		this.getSqlSession().insert(NAMESPACE + INSERT_WAIT_CUSTOMER_LOG, logInfo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao#insertWriteCustomerLogDetail(com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfoDetail)
	 */
	@Override
	public void insertWriteCustomerLogDetail(WaitCustomerLogInfoDetail logInfoDetail) {
		this.getSqlSession().insert(NAMESPACE + INSERT_WAIT_CUSTOMER_LOG_DETAIL, logInfoDetail);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao#getTransactionNo(com.deppon.crm.module.client.sync.domain.TransactionNoInfo)
	 */
	@Override
	public TransactionNoInfo getTransactionNo() {
		TransactionNoInfo result =  (TransactionNoInfo)this.getSqlSession().selectOne(NAMESPACE + SELECT_TRANSACTION_NO);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao#getWaitingSendDatas()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WaitCustomerLogInfo> getWaitingSendDatas() {
		List<WaitCustomerLogInfo> result;
		RowBounds rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, MAX_ROW_NUMBER);
		result = (List<WaitCustomerLogInfo>)this.getSqlSession().selectList(NAMESPACE + SELECT_WAITING_SEND_DATA, rowBounds);
		return result;
	}

	@Override
	public void updateSendingFlg(String fid) {
		WaitCustomerLogInfo object = new WaitCustomerLogInfo();
		object.setFID(fid);
		this.getSqlSession().update(NAMESPACE + UPDATE_SENDING_FLG, object);
		
	}

	@Override
	public void updateSuccessFlg(String fid, String jsonData,int err_sys) {
		WaitCustomerLogInfo object = new WaitCustomerLogInfo();
		object.setFID(fid);
		object.setDATA(jsonData);
		object.setERR_SYS(err_sys);
		this.getSqlSession().update(NAMESPACE + UPDATE_SUCCESS_FLG, object);		
	}

	@Override
	public void updateFailFlg(WaitCustomerLogInfo waitCustomerLogInfo) {
		if ("A".equals(waitCustomerLogInfo.getHANDLE_TYPE())) {
			// 自动重发时，先检查已报错次数，如果已经出错超过3次，则转为需要手动重发
			WaitCustomerLogInfo result = (WaitCustomerLogInfo)this.getSqlSession().selectOne(NAMESPACE + SELECT_WAITING_SEND_STATUS, waitCustomerLogInfo.getFID());
			if (result.getERR_NUMBER().longValue() >= 3) {
				waitCustomerLogInfo.setHANDLE_TYPE("M");
			}
			waitCustomerLogInfo.setERR_SYS(waitCustomerLogInfo.getERR_SYS()|result.getERR_SYS());
		} 
		this.getSqlSession().update(NAMESPACE + UPDATE_FAIL_FLG, waitCustomerLogInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WaitCustomerLogInfoDetail> getWaitingSendDetails(String fid) {
		List<WaitCustomerLogInfoDetail> result;
		result = (List<WaitCustomerLogInfoDetail>)this.getSqlSession().selectList(NAMESPACE + SELECT_WAITING_SEND_DETAILS, fid);
		return result;
	}
}
