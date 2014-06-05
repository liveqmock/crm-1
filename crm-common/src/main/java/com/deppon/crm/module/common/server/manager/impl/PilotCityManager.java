package com.deppon.crm.module.common.server.manager.impl;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.common.server.manager.IPilotCityManager;
import com.deppon.crm.module.common.server.service.IPilotCityService;
import com.deppon.crm.module.common.shared.domain.PilotCity;
import com.deppon.crm.module.common.server.util.Constant;

/**
 * 试点城市Manager
 * @author 胡傲果
 * @date 2013-07-26
 */
public class PilotCityManager implements IPilotCityManager{
	private IPilotCityService pilotCityService;
	
	/**
	 * 查询试点城市
	 * @param pilotCity
	 * @return
	 */
	@Override
	public PilotCity getPilotCity(PilotCity pilotCity) {
		return pilotCityService.getPilotCity(pilotCity);
	}

	/**
	 * 检查是否是试点城市
	 * @param cityCode 行政区域编码
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-26
	 */
	@Override
	public boolean checkPilotCity(String cityCode) {
		return pilotCityService.checkPilotCity(cityCode);
	}
	
	/**
	 * 检查是否是试点城市
	 * @param cityName 城市名称
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-27
	 */
	@Override
	public boolean checkPilotCityByName(String cityName) {
		if(StringUtils.isBlank(cityName)){
			return false;
		}
		return pilotCityService.checkPilotCityByName(cityName.trim());
	}

	/**
	 * 新增试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	@Override
	public void insertPilotCity(PilotCity pilotCity) {
		pilotCityService.insertPilotCity(pilotCity);
	}

	/**
	 * 更新试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	@Override
	public void updatePilotCity(PilotCity pilotCity) {
		pilotCityService.updatePilotCity(pilotCity);
	}

	/**
	 * 删除试点城市
	 * @param id 试点城市ID
	 * @date 2013-07-26
	 */
	@Override
	public void deletePilotCity(String id) {
		pilotCityService.deletePilotCity(id);
	}
	
	/**
	 * @description 校验带订单是否为试点城市快递订单
	 * @author 张斌
	 * @version 0.1 2013-7-27
	 * @param String contactCity 接入方联系人所在城市  String transportMode 订单的运输方式
	 * @return boolean
	 */
	//begin
	@Override
	public boolean checkIsPilotCityOrder(String contactCity,String transportMode) {
		if(StringUtils.isEmpty(contactCity)){
			return false;
		}
		if(StringUtils.isEmpty(transportMode)){
			return false;
		}
		if(!Constant.ORDER_TRANSTYPE_AGENT_PACKAGE.equals(transportMode)){
			return false;
		}
		if(!this.checkPilotCityByName(contactCity)){
			return false;
		}
		return true;
	}
	//end
	
	public void setPilotCityService(IPilotCityService pilotCityService) {
		this.pilotCityService = pilotCityService;
	}

	@Override
	public boolean checkAgentCityByName(String name) {
		return false;
	}
	
}
