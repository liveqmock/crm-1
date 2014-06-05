package com.deppon.crm.module.uums.server.dao;

import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;

public interface ICompanyDao {
	/**
	 * 员工数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(CompanyInfo entity);
	/**
	 * 员工数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int updateByCompanyId(CompanyInfo entity,String companyId);
	public int updateByCompanyCode(CompanyInfo entity,String companyId);

	/**
	 * 员工数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int deleteById(String companyId) ;
	public int deleteByCode(String companyCode) ;

	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	public CompanyInfo searchByCode(String companyCode) ;
	public CompanyInfo searchById(String companyId) ;

}
