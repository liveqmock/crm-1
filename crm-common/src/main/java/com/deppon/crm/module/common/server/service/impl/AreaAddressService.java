/**   
 * Description:这里写描述<br />
 * @title AreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.common.server.dao.IAreaDao;
import com.deppon.crm.module.common.server.service.IAreaAddressService;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;

/**   
 * Description:这里写描述<br />
 * @title AreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class AreaAddressService implements IAreaAddressService {

	//注入areaDao
	private IAreaDao areaDao;
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的区域信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：区域信息
	 * */
	public Area queryVaildAreaByNum(String number){
		return areaDao.queryVaildAreaByNum(number);
	}

	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}
		
	/**
	 * @description
	 * @author 毛建强
	 * @2012-3-16
	 * @return
	 */
	public List<Area> getAreaByCity(String cityId) {
		return areaDao.getAreaByCity(cityId);
	}

	/** 
	 * @description 根据id查询对应城市
	 * @author 毛建强
	 * @2012-3-15
	 * @return 返回区域集合
	 */
	public List<Area> getAreaById(String id) {
		return areaDao.getAreaById(id);
	}

	@Override
	public void insertArea(Area area) {
		// TODO Auto-generated method stub
		areaDao.insertArea(area);
	}

	@Override
	public void deleteArea(Area area) {
		// TODO Auto-generated method stub
		areaDao.deleteArea(area);
	}

	@Override
	public void updateArea(Area area) {
		// TODO Auto-generated method stub
		areaDao.updateArea(area);
	}

	@Override
	public Area getAreaById(BigDecimal areaId) {
		// TODO Auto-generated method stub
		return areaDao.getAreaById(areaId);
	}

	@Override
	public List<Area> searchAreasByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		return areaDao.searchAreasByName(condition);
	}

	@Override
	public Integer searchAreasCountByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		return areaDao.searchAreasCountByName(condition);
	}

	@Override
	public Area searchAreaByName(AreaSearchCondition condition) {
		return areaDao.searchAreaByName(condition);
	}

}
