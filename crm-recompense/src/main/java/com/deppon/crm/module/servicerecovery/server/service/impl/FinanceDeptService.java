package com.deppon.crm.module.servicerecovery.server.service.impl;

import java.util.List;

import com.deppon.crm.module.servicerecovery.server.dao.IFinanceDeptDao;
import com.deppon.crm.module.servicerecovery.server.service.IFinanceDeptService;
import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;

/**
 * Description: FinanceDeptService.java Create on 2012-11-13 下午4:26:45
 * 
 * @author 华龙
 * @version 1.0
 */
public class FinanceDeptService implements IFinanceDeptService {
	private IFinanceDeptDao financeDeptDao;

	public IFinanceDeptDao getFinanceDeptDao() {
		return financeDeptDao;
	}

	public void setFinanceDeptDao(IFinanceDeptDao financeDeptDao) {
		this.financeDeptDao = financeDeptDao;
	}
	//得到部门list
	@Override
	public List<FinanceDept> searchFinanceList() {
		return financeDeptDao.searchFinanceDeptList();
	}

}
