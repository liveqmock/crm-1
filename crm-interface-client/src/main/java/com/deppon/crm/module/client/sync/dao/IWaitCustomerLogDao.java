package com.deppon.crm.module.client.sync.dao;

import java.util.List;

import com.deppon.crm.module.client.sync.domain.TransactionNoInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfoDetail;

public interface IWaitCustomerLogDao {

	public abstract void insertWriteCustomerLog(WaitCustomerLogInfo logInfo);
	public abstract void insertWriteCustomerLogDetail(WaitCustomerLogInfoDetail logInfo);
	public abstract TransactionNoInfo getTransactionNo();
	
	/**
	 * 检索待发送记录 检索条件为HANDLE_TYPE IN(‘A’OR’N’) AND STATUS = ‘U’。
	 * @return 待发送记录列表
	 */
	public abstract List<WaitCustomerLogInfo> getWaitingSendDatas();
	
	/**
	 * 更新标记为发送中
	 * @param fid
	 */
	public abstract void updateSendingFlg(String fid);
	/**
	 * 更新标记为成功
	 * @param fid
	 */
	public abstract void updateSuccessFlg(String fid, String jsonData,int err_sys);
	
	/**
	 * 更新标记为失败
	 * @param waitCustomerLogInfo
	 */
	public abstract void updateFailFlg(WaitCustomerLogInfo waitCustomerLogInfo);
	
	/**
	 * 取得明细数据
	 * @param fid
	 * @return
	 */
	public abstract List<WaitCustomerLogInfoDetail> getWaitingSendDetails(String fid);
}