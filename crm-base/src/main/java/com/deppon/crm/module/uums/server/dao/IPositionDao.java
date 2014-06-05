package com.deppon.crm.module.uums.server.dao;

import java.util.List;

import com.deppon.crm.module.uums.shared.domain.PostionInfo;
public interface IPositionDao {
	/**
	 * 员工数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(PostionInfo entity);
	/**
	 * 员工数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int updateById(PostionInfo entity,String positionId);
	public int updateByCode(PostionInfo entity,String positionId);


	/**
	 * 员工数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int deleteByCode(String positonCode) ;
	public int deleteById(String positonId) ;

	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	public PostionInfo searchById(String positionId) ;
	public PostionInfo searchByCode(String positionCode) ;
	public List<PostionInfo> searchByName(String positionCode) ;
}
