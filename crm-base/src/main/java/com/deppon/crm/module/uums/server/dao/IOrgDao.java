package com.deppon.crm.module.uums.server.dao;

import com.deppon.crm.module.uums.shared.domain.OrgInfo;
public interface IOrgDao {
	/**
	 * 员工数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(OrgInfo entity);
	/**
	 * 员工数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int updateById(OrgInfo entity,String orgId);
	public int updateByCode(OrgInfo entity,String orgCode);

	/**
	 * 员工数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int deleteById(String orgId) ;
	public int deleteByCode(String orgCode) ;

	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	public OrgInfo searchById(String orgId) ;
	public OrgInfo searchByCode(String orgCode) ;
	public int reDeleteByCode(String orgId);
}
