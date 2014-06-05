/**
 * Filename:	ProvinceService.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-5-8 下午2:21:57
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-5-8	    
 */

package com.deppon.crm.module.common.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.common.server.dao.IProvinceDao;
import com.deppon.crm.module.common.server.service.IProvinceService;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.Province;


public class ProvinceService implements IProvinceService{

	private IProvinceDao iProvinceDao;
	/**
	 * @作者：罗典
	 * @描述：根据编码查询省份信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：省份信息
	 * */
	public Province queryVaildProvinceByNum(String number){
		return iProvinceDao.queryVaildProvinceByNum(number);
	}
	
	public IProvinceDao getiProvinceDao() {
		return iProvinceDao;
	}

	public void setiProvinceDao(IProvinceDao iProvinceDao) {
		this.iProvinceDao = iProvinceDao;
	}

	@Override
	public void insertProvince(Province province) {
		// TODO Auto-generated method stub
		iProvinceDao.insertProvince(province);
	}

	@Override
	public void deleteProvince(Province province) {
		// TODO Auto-generated method stub
		iProvinceDao.deleteProvince(province);
	}

	@Override
	public Province getProvinceById(BigDecimal provinceId) {
		// TODO Auto-generated method stub
		return iProvinceDao.getProvinceById(provinceId);
	}

	@Override
	public void updateProvince(Province province) {
		// TODO Auto-generated method stub
		iProvinceDao.updateProvince(province);
	}

	@Override
	public List<Province> searchProvincesByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		return iProvinceDao.searchProvincesByName(condition);
	}

	@Override
	public Integer searchProvincesCountByName(
			AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		return iProvinceDao.searchProvincesCountByName(condition);
	}

	@Override
	public List<Province> getAllProvince() {
		// TODO Auto-generated method stub
		return iProvinceDao.getAllProvince();
	}

	@Override
	public Province getProvincesByCityId(Integer cityId) {
		// TODO Auto-generated method stub
		return iProvinceDao.getProvincesByCityId(cityId);
	}

	@Override
	public Province searchProvinceByName(AreaSearchCondition condition) {
		return iProvinceDao.searchProvinceByName(condition);
	}
}
