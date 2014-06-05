package com.deppon.crm.module.servicerecovery.server.manager;

import java.util.List;

import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;

/**
 * Description: IFinanceDeptService.java Create on 2012-11-13 下午4:23:15
 * 
 * @author 华龙
 * @version 1.0
 */
public interface IFinanceDeptManager {
	/**
	 * 查询财务部
	 */
	public List<FinanceDept> searchFinanceDeptList();

}
