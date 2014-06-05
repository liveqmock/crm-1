package com.deppon.crm.module.frameworkimpl.server.service;

import java.sql.Timestamp;

import com.deppon.foss.framework.service.IService;

/**
 * 令牌处理服务类
 * @author Administrator
 *
 */
public interface ITokenService extends IService{

	/**
	 * 保存令牌对象
	 * @param id
	 * @param token
	 */
	public void save(String id, String token);

	/**
	 * 通过ID得到令牌对象
	 * @param id
	 * @return
	 */
	public String queryById(String id);

	/**
	 * 通过ID删除令牌对象
	 * @param id
	 */
	public void removeById(String id);

	/**
	 * 删除所有的令牌对象
	 * @param timestamp 
	 */
	public void removeAll(Timestamp timestamp);

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-12
	 * @param key
	 * @param value
	 * @param timestamp
	 * void
	 */
	public void save(String key, String value, Timestamp timestamp);
}
