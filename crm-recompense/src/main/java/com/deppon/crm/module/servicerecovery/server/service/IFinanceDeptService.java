package com.deppon.crm.module.servicerecovery.server.service;

import java.util.List;

import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;

/**
 * Description: IFinanceDeptService.java Create on 2012-11-13 下午4:25:56
 * 
 * @author 华龙
 * @version 1.0
 */
public interface IFinanceDeptService {
	//得到部门list
	List<FinanceDept> searchFinanceList();

}
