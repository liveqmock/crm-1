package com.deppon.crm.module.common.server.manager;

import com.deppon.crm.module.common.shared.domain.PilotCity;

/**
 * 试点城市Manager接口
 * @author 胡傲果
 * @date 2013-07-26
 */
public interface IPilotCityManager {
	/**
	 * 查询试点城市
	 * @param pilotCity
	 * @return
	 */
	PilotCity getPilotCity(PilotCity pilotCity);
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
	 * @description 校验带订单是否为试点城市快递订单
	 * @author 张斌
	 * @version 0.1 2013-7-27
	 * @param String contactCity 接入方联系人所在城市  String transportMode 订单的运输方式
	 * @return boolean
	 */
	//begin
	boolean checkIsPilotCityOrder(String contactCity, String transportMode);
	//end
	/**
	 * 
	 * <p>
	 * Description:校验是否落地配城市<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-7-31
	 * @param name
	 * @return
	 * boolean
	 */
	boolean checkAgentCityByName(String name);
	
}
