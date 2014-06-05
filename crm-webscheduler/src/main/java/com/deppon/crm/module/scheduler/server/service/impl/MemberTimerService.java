package com.deppon.crm.module.scheduler.server.service.impl;

import com.deppon.crm.module.scheduler.server.dao.ICustomerGetTempDao;
import com.deppon.crm.module.scheduler.server.service.IMemberTimerService;
import com.deppon.crm.module.scheduler.shared.domain.TimerProcedureName;

public class MemberTimerService implements IMemberTimerService {
	
	private ICustomerGetTempDao custTempDao;

	@Override
	public void memberUpgrade() {
		custTempDao.callStoredProcedure(TimerProcedureName.MEMBERUPGRADE.getStr());
	}

	@Override
	public void memberFall() {
		custTempDao.callStoredProcedure(TimerProcedureName.MEMBERFALL.getStr());
	}

	public ICustomerGetTempDao getCustTempDao() {
		return custTempDao;
	}

	public void setCustTempDao(ICustomerGetTempDao custTempDao) {
		this.custTempDao = custTempDao;
	}

	
	
}
