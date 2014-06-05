package com.deppon.crm.module.common.server.service.impl;

import com.deppon.crm.module.common.server.dao.IPilotCityDao;
import com.deppon.crm.module.common.server.service.IPilotCityService;
import com.deppon.crm.module.common.shared.domain.PilotCity;

/**
 * 试点城市Service
 * @author 胡傲果
 * @date 2013-07-26
 */
public class PilotCityService implements IPilotCityService{
	private IPilotCityDao pilotCityDao;
	
	/**
	 * 查询试点城市
	 * @param pilotCity
	 * @return
	 */
	@Override
	public PilotCity getPilotCity(PilotCity pilotCity) {
		return pilotCityDao.getPilotCity(pilotCity);
	}

	/**
	 * 检查是否是试点城市
	 * @param cityCode 行政区域编码
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-26
	 */
	@Override
	public boolean checkPilotCity(String cityCode) {
		return pilotCityDao.checkPilotCity(cityCode);
	}
	
	/**
	 * 检查是否是试点城市
	 * @param cityName 城市名称
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-27
	 */
	@Override
	public boolean checkPilotCityByName(String cityName) {
		return pilotCityDao.checkPilotCityByName(cityName);
	}

	/**
	 * 新增试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	@Override
	public void insertPilotCity(PilotCity pilotCity) {
		pilotCityDao.insertPilotCity(pilotCity);
	}

	/**
	 * 更新试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	@Override
	public void updatePilotCity(PilotCity pilotCity) {
		pilotCityDao.updatePilotCity(pilotCity);
	}

	/**
	 * 删除试点城市
	 * @param id 试点城市ID
	 * @date 2013-07-26
	 */
	@Override
	public void deletePilotCity(String id) {
		pilotCityDao.deletePilotCity(id);
	}
	
	public void setPilotCityDao(IPilotCityDao pilotCityDao) {
		this.pilotCityDao = pilotCityDao;
	}

}
