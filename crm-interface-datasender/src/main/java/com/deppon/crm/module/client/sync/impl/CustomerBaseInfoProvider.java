package com.deppon.crm.module.client.sync.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.jobs.ILogProvider;
import com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;

public class CustomerBaseInfoProvider implements ILogProvider<WaitCustomerLogInfo> {
	IWaitCustomerLogDao waitCustomerLogDao;

	/**
	 * @author li
	 * @deprecated
	 */
	@Override
	public Map<String, List<WaitCustomerLogInfo>> load() {
		return null;
	}

	@Override
	public List<WaitCustomerLogInfo> loadList() {
		List<WaitCustomerLogInfo> waitingList = waitCustomerLogDao.getWaitingSendDatas();
		return waitingList;
	}

	@Override
	public List<WaitCustomerLogInfo> loadList(int size) {
		// 在waitCustomerLogDao中控制size
		return loadList();
	}

	@Override
	public void clearCache() {
		;
	}
	

	public IWaitCustomerLogDao getWaitCustomerLogDao() {
		return waitCustomerLogDao;
	}

	public void setWaitCustomerLogDao(IWaitCustomerLogDao waitCustomerLogDao) {
		this.waitCustomerLogDao = waitCustomerLogDao;
	}
}
