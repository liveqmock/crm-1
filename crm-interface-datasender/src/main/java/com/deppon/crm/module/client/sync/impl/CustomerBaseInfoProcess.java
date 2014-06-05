package com.deppon.crm.module.client.sync.impl;

import java.util.List;

import com.deppon.crm.module.client.jobs.ILogProcess;
import com.deppon.crm.module.client.jobs.impl.LogStatus;
import com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;

public class CustomerBaseInfoProcess implements ILogProcess<WaitCustomerLogInfo> {
	IWaitCustomerLogDao waitCustomerLogDao;
	/**
	 * @deprecated
	 */
	@Override
	public void update(List<WaitCustomerLogInfo> logs, LogStatus status) {
		;
	}

	@Override
	public boolean update(WaitCustomerLogInfo waitCustomerLogInfo, LogStatus status) {
		switch (status) {
		case SUCCESS:
			// 更新成功标记
			waitCustomerLogDao.updateSuccessFlg(waitCustomerLogInfo.getFID(), waitCustomerLogInfo.getDATA(),waitCustomerLogInfo.getERR_SYS());
			break;
		case FAIL:
			// 更新错误标记
			waitCustomerLogDao.updateFailFlg(waitCustomerLogInfo);
			break;
		}
		return false;
	}
	public IWaitCustomerLogDao getWaitCustomerLogDao() {
		return waitCustomerLogDao;
	}

	public void setWaitCustomerLogDao(IWaitCustomerLogDao waitCustomerLogDao) {
		this.waitCustomerLogDao = waitCustomerLogDao;
	}
}
