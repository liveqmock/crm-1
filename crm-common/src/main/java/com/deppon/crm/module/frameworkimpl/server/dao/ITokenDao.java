package com.deppon.crm.module.frameworkimpl.server.dao;

import java.sql.Timestamp;


/**
 * 令牌数据处理类
 * @author Administrator
 *
 */
public interface ITokenDao {

	/**
	 * 保存令牌数据
	 * @param id
	 * @param token
	 */
	void insert(String id, String token);

	/**
	 * 通过ID得到令牌对象
	 * @param id
	 * @return
	 */
	String getById(String id);

	/**
	 * 通过ID删除令牌对象
	 * @param id
	 * @return
	 */
	void deleteById(String id);

	/**
	 * 删除所有的令牌对象
	 */
	void deleteAll();

	/**
	 * <p>
	 * Description:保存令牌对象<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-2-25
	 * @param key
	 * @param value
	 * @param validTime
	 * void
	 */
	void insert(String key, String value, Timestamp validTime);

	/**
	 * <p>
	 * 删除过期时间之前的TOken<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-2-27
	 * @param time
	 * void
	 */
	void removeAll(Timestamp time);

	/**
	 * <p>
	 * Description:通过ID得到令牌对象<br />
	 * </p>
	 * @author 石应华
	 * @version 0.1 2013-8-29
	 * @param id 
	 * @param currTime 当前时间
	 * @return
	 * String
	 */
    String getById(String id, Timestamp currTime);
}
