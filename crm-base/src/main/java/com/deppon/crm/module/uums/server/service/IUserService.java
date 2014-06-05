package com.deppon.crm.module.uums.server.service;

import java.util.Map;

import com.deppon.crm.module.uums.shared.domain.UserInfo;
public interface IUserService {
	/**
	 * 员工数据新增
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(UserInfo entity);
	/**
	 * 员工数据更新
	 * @param BaseUUEntity 
	 * 
	 */
	public int updateById(UserInfo entity,String userId);
	public int updateByCode(UserInfo entity,String userId);

	/**
	 * 员工数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int deleteByUserid(String userId) ;
	public int deleteByUserCode(Map<String,String> empMap) ;

	/**
	 * 数据校验	
	 * @param BaseUUEntity 
	 * 
	 */
	public UserInfo searchByUserId(String userID) ;
	public UserInfo searchByUserCode(String userCode) ;
	
	public int returnById(String userid);
	public int returnByCode(UserInfo entity);
}
