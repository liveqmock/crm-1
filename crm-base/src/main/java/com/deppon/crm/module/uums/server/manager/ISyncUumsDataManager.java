package com.deppon.crm.module.uums.server.manager;

import com.deppon.crm.module.uums.shared.domain.BaseUUEntity;
/**
 * 
 * @description CRM同步uums数据 接口
 * @author zzw
 * @version 0.1 2013-11-25
 */
public interface ISyncUumsDataManager {
	int SUCCESS=1;
	/**
	 * 新增数据插入
	 * @param BaseUUEntity 
	 * 
	 */
	public int insert(BaseUUEntity entity);
	/**
	 * 数据更新
	 * @param BaseUUEntity 
	 * 
	 */

	public int update(BaseUUEntity entity);
	/**
	 * 数据删除
	 * @param BaseUUEntity 
	 * 
	 */
	public int delete(BaseUUEntity entity);
	/**
	 * 数据校验：根据参数判断调用哪一个实现类的哪一个操作（insert update delete）
	 * @param BaseUUEntity 
	 * @return 
	 * @throws Exception 
	 * 
	 */
	public int check(BaseUUEntity entity) throws Exception;
}
