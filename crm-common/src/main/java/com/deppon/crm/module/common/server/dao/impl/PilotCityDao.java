package com.deppon.crm.module.common.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IPilotCityDao;
import com.deppon.crm.module.common.shared.domain.PilotCity;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 试点城市DAO
 * @author 胡傲果
 * @date 2013-07-26
 */
public class PilotCityDao extends iBatis3DaoImpl implements IPilotCityDao {
	/**   
	 *试点城市 命名空间
	 */
	private static final String NAMESPACE="com.deppon.crm.module.common.shared.domain.PilotCity.";
	/**
	 * 检查是否是试点城市
	 */
	private static final String CHECK_PILOT_CITY = "checkPilotCity";
	/**
	 * 根据城市名检查是否是试点城市
	 */
	private static final String CHECK_PILOT_CITY_BY_NAME = "checkPilotCityByName";
	/**
	 * 新增试点城市
	 */
	private static final String INSERT_PILOT_CITY = "insertPilotCity";
	/**
	 * 更新试点城市
	 */
	private static final String UPDATE_PILOT_CITY = "updatePilotCity";
	/**
	 * 删除试点城市
	 */
	private static final String DELETE_PILOT_CITY = "deletePilotCity";
	
	/**
	 * 查询试点城市
	 */
	private static final String GET_PILOT_CITY = "getPilotCity";

	/**
	 * 检查是否是试点城市
	 * @param cityCode 行政区域编码
	 * @return 是试点城市返回true，否则返回false
	 * @date 2013-07-26
	 */
	@Override
	public boolean checkPilotCity(String cityCode) {
		String isPilot = (String) getSqlSession().selectOne(NAMESPACE + CHECK_PILOT_CITY, cityCode);
		return isPilot != null && isPilot.equalsIgnoreCase("y");
	}
	
	@Override
	public boolean checkPilotCityByName(String cityName) {
		@SuppressWarnings("rawtypes")
		List result = getSqlSession().selectList(NAMESPACE + CHECK_PILOT_CITY_BY_NAME, cityName);
		if(result == null || result.isEmpty()){
			return false;
		}
		if(result.size() > 1){
			return false;
		}
		String isPilot = (String) result.get(0);
		return isPilot != null && isPilot.equalsIgnoreCase("y");
	}

	/**
	 * 新增试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	@Override
	public void insertPilotCity(PilotCity pilotCity) {
		getSqlSession().insert(NAMESPACE + INSERT_PILOT_CITY, pilotCity);
	}

	/**
	 * 更新试点城市
	 * @param pilotCity 试点城市
	 * @date 2013-07-26
	 */
	@Override
	public void updatePilotCity(PilotCity pilotCity) {
		getSqlSession().update(NAMESPACE + UPDATE_PILOT_CITY, pilotCity);
	}

	/**
	 * 删除试点城市
	 * @param id 试点城市ID
	 * @date 2013-07-26
	 */
	@Override
	public void deletePilotCity(String id) {
		getSqlSession().delete(NAMESPACE + DELETE_PILOT_CITY, id);
	}

	/**
	 * 查询试点城市
	 * @param pilotCity
	 * @return
	 */
	@Override
	public PilotCity getPilotCity(PilotCity pilotCity) {
		return (PilotCity) getSqlSession().selectOne(NAMESPACE + GET_PILOT_CITY, pilotCity);
	}

}
