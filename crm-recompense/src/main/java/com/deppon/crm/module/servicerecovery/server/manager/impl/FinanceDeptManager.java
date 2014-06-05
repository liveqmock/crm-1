package com.deppon.crm.module.servicerecovery.server.manager.impl;

import java.util.List;

import com.deppon.crm.module.servicerecovery.server.manager.IFinanceDeptManager;
import com.deppon.crm.module.servicerecovery.server.service.IFinanceDeptService;
import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;

/**
 * Description: FinanceDeptManager.java Create on 2012-11-13 下午4:24:13
 * 
 * @author 华龙
 * @version 1.0
 */
public class FinanceDeptManager implements IFinanceDeptManager {
	private IFinanceDeptService financeDeptService;

	public IFinanceDeptService getFinanceDeptService() {
		return financeDeptService;
	}

	public void setFinanceDeptService(IFinanceDeptService financeDeptService) {
		this.financeDeptService = financeDeptService;
	}

	/**
	 * 查询财务部
	 */
	public List<FinanceDept> searchFinanceDeptList() {
		return financeDeptService.searchFinanceList();

	}
}
