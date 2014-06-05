/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityCacheProvider.java
 * @package com.deppon.crm.module.common.server.cache 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.ICityDao;
import com.deppon.crm.module.common.server.dao.IProvinceDao;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;

/**   
 * <p>
 * Description:城市、省份缓存<br />
 * </p>
 * @title CityCacheProvider.java
 * @package com.deppon.crm.module.common.server.cache 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */
public class CityCacheProvider implements IBatchCacheProvider<String, Object> {
	//注入省份dao
	private IProvinceDao provinceDao;
	//注入城市dao
	private ICityDao cityDao;
	@Override
	public Date getLastModifyTime() {
		return cityDao.getLastModifyTime();
	}


	/**
	 * @description 设置缓存放入哪些数据
	 * @author 毛建强
	 * @2012-3-23
	 * @return 缓存的map
	 */
	public Map<String, Object> get() {
		List<Province> provinceList = provinceDao.getAllProvince();
		List<City> cityList = cityDao.getAllCity();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("province", provinceList);
		map.put("city", cityList);
		List<City> commonCities = new ArrayList<City>();
		for(City c:cityList){
			if(getDefaultCitiesList().contains(c.getNumber())){
				commonCities.add(c);
			}
		}
		map.put("commonCities", commonCities);
		return map;
	}
	
	/**
	 * <p>
	 * Description:获取默认的城市<br />
	 * </p>
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param 
	 * @return 默认城市列表
	 */
	public List<String> getDefaultCitiesList(){
		List<String> list  = new ArrayList<String>();
		list.add("110000-1");
		list.add("120000-1");
		list.add("310000-1");
		list.add("440100");
		list.add("440300");
		list.add("610100");
		list.add("140100");
		return list;
	}	
	
	
	public IProvinceDao getProvinceDao() {
		return provinceDao;
	}
	public void setProvinceDao(IProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
	public ICityDao getCityDao() {
		return cityDao;
	}
	public void setCityDao(ICityDao cityDao) {
		this.cityDao = cityDao;
	}

}
