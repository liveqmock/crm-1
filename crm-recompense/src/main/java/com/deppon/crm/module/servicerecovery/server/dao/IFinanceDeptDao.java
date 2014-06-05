package com.deppon.crm.module.servicerecovery.server.dao;

import java.util.List;

import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;

/**
 * 
 * @description：查询财务部Dao接口
 * @author 华龙
 * @version 1.0
 * @date 2012-11-13 下午15:43:34
 */
public interface IFinanceDeptDao {
	/**
	 * 查询财务部
	 */
	public List<FinanceDept> searchFinanceDeptList();

}
