/**
 * Filename:	CityService.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-5-8 下午3:27:21
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-5-8	    
 */

package com.deppon.crm.module.common.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.common.server.dao.ICityDao;
import com.deppon.crm.module.common.server.service.ICityService;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;


public class CityService implements ICityService{
	
	private ICityDao iCityDao;
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的城市信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：城市信息
	 * */
	public City queryVaildCityByNum(String number){
		return iCityDao.queryVaildCityByNum(number);
	}
	public ICityDao getiCityDao() {
		return iCityDao;
	}

	public void setiCityDao(ICityDao iCityDao) {
		this.iCityDao = iCityDao;
	}

	@Override
	public void insertCity(City city) {
		// TODO Auto-generated method stub
		iCityDao.insertCity(city);
	}

	@Override
	public City getCityById(BigDecimal cityId) {
		// TODO Auto-generated method stub
		return iCityDao.getCityById(cityId);
	}

	@Override
	public void deleteCity(City city) {
		// TODO Auto-generated method stub
		iCityDao.deleteCity(city);
	}

	@Override
	public void updateCity(City city) {
		// TODO Auto-generated method stub
		iCityDao.updateCity(city);
	}

	@Override
	public List<City> searchCitysByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		return iCityDao.searchCitysByName(condition);
	}

	@Override
	public Integer searchCitysCountByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		return iCityDao.searchCitysCountByName(condition);
	}

	@Override
	public List<City> getCityByProvinceId(Integer provinceId) {
		// TODO Auto-generated method stub
		return iCityDao.getCityByProvinceId(provinceId);
	}
	@Override
	public City searchCityByName(AreaSearchCondition condition) {
		return iCityDao.searchCityByName(condition);
	}
	

}
