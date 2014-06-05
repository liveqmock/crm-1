package com.deppon.crm.module.common.server.dao;

import com.deppon.crm.module.common.shared.domain.PilotCity;

/**
 * 试点城市DAO接口
 * @author 胡傲果
 * @date 2013-07-26
 */
public interface IPilotCityDao {
	/**
	 * 检查是否是试点城市
	 * @param cityCode 行政区域编码
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-26
	 */
	boolean checkPilotCity(String cityCode);
	/**
	 * 检查是否是试点城市
	 * @param cityName 城市名称
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-27
	 */
	boolean checkPilotCityByName(String cityName);
	/**
	 * 新增试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	void insertPilotCity(PilotCity pilotCity);
	/**
	 * 更新试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	void updatePilotCity(PilotCity pilotCity);
	/**
	 * 删除试点城市
	 * @param id 试点城市ID
	 * @date 2013-07-26
	 */
	void deletePilotCity(String id);
	
	/**
	 * 查询试点城市
	 * @param pilotCity
	 * @return
	 */
	PilotCity getPilotCity(PilotCity pilotCity);
}
