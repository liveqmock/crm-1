package com.deppon.crm.module.common.server.cache;

import java.util.ArrayList;
import java.util.List;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;

@SuppressWarnings("rawtypes")
public class CityCacheDeal {

	private CacheManager cacheManager;

	/**
	 * Description:获取常用城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public List<City> getCommonCities() {
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<City> cityList = cloneList((List<City>) cityCache.get("commonCities"));
		List<City> commonCities = new ArrayList<City>();
		for(City c:cityList){
			c.setName(c.getProvinceName()+"-"+c.getName());
			c.setId(c.getProvinceId()+"-"+c.getId());
			commonCities.add(c);
		}
		return commonCities;
	}
	/**
	 * Description:获取所有省份<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Province> getAllProvinceList() {
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<Province> provinceList = (List<Province>) cityCache.get("province");
		return provinceList;
	}
	
	/**
	 * Description:获取对应省份的城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param 省份ID
	 * @return 城市列表
	 */
	@SuppressWarnings("unchecked")
	public List<City> getCityByProvince(String provinceId){
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<City> cityList = cloneList((List<City>)cityCache.get("city"));
		List<City> targetList = new ArrayList<City>();
		//遍历城市列表，获取传进来省份的城市列表
		for(City c:cityList){
			if(c.getProvinceId()!=null){
				if(provinceId.equals(c.getProvinceId()+"")){
					c.setName(c.getProvinceName()+"-"+c.getName());
					c.setId(c.getProvinceId()+"-"+c.getId());
					targetList.add(c);
				}
			}
		}
		return targetList;
	}
	
	/**
	 * Description:输入汉字匹配对应城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param 查询条件(汉字)
	 * @return 城市列表
	 */
	@SuppressWarnings("unchecked")
	public List<City> searchCityByChinese(String name){
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<City> targetList = new ArrayList<City>();
		List<City> cityList = cloneList((List<City>)cityCache.get("city"));
		for(City c:cityList){
			if(c.getName()!=null && c.getName().indexOf(name.trim())==0){
				c.setName(c.getProvinceName()+"-"+c.getName());
				c.setId(c.getProvinceId()+"-"+c.getId());
				targetList.add(c);
			}
		}
		//如果城市名称不能匹配上，则去匹配省份名称
		if(targetList.size()==0){
			List<Province> provinceList = (List<Province>) cityCache.get("province");
			for(Province p:provinceList){
				if(p.getName()!=null && p.getName().indexOf(name.trim())==0){
					for(City c:getCityByProvince(p.getId())){
						targetList.add(c);
					}
						
				}
			}
		}
		return targetList;
	}
	/**
	 * Description:输入Pinyin匹配对应城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<City> searchCityByEnglish(String name){
		//转化为小写
		name = name.trim().toLowerCase();
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<City> targetList = new ArrayList<City>();
		List<City> cityList = cloneList((List<City>)cityCache.get("city"));
		//根据城市拼音简称匹配
		for(City c:cityList){
			if(c.getPyjm()!=null && c.getPyjm().indexOf(name.trim())==0){
				c.setName(c.getProvinceName()+"-"+c.getName());
				c.setId(c.getProvinceId()+"-"+c.getId());
				targetList.add(c);
			}
		}
		//根据城市拼音匹配
		if(targetList.size()==0){
			for(City c:cityList){
				if(c.getPinyin()!=null && c.getPinyin().indexOf(name.trim())==0){
					c.setName(c.getProvinceName()+"-"+c.getName());
					c.setId(c.getProvinceId()+"-"+c.getId());
					targetList.add(c);
				}
			}
		}
		//如果城市不能匹配上，则去匹配省份拼音简称
		if(targetList.size()==0){
			List<Province> provinceList = (List<Province>) cityCache.get("province");
			for(Province p:provinceList){
				if(p.getPyjm()!=null && p.getPyjm().indexOf(name.trim())==0){
					for(City c:getCityByProvince(p.getId())){
						targetList.add(c);
					}
				}
			}
			//匹配城市拼音
			if(targetList.size()==0){
				for(Province p:provinceList){
					if(p.getPinyin()!=null && p.getPinyin().indexOf(name.trim())==0){
						for(City c:getCityByProvince(p.getId())){
							targetList.add(c);
						}
					}
				}
			}
		}
		return targetList;
	} 
	/**
	 * 
	 * Description:克隆一份缓存中的城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-20
	 * @param 缓存中城市列表
	 * @return
	 */
	private List<City> cloneList(List<City> cityList){
		List<City> newCityList = new ArrayList<City>();
		//循环遍历，将缓存中城市克隆一份
		for(City c:cityList){
			City city = new City();
			city.setId(c.getId());
			city.setName(c.getName());
			city.setCityNumber(c.getCityNumber());
			city.setPinyin(c.getPinyin());
			city.setPyjm(c.getPyjm());
			city.setStatus(c.getStatus());
			city.setProvince(c.getProvince());
			city.setProvinceId(c.getProvinceId());
			city.setProvinceName(c.getProvinceName());
			newCityList.add(city);
		}
		return newCityList;
	}
	/**
	 * Description:获取缓存<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-16
	 * @param
	 * @return
	 */
	public CacheManager getInstanceCache() {
		if (cacheManager == null) {
			cacheManager = CacheManager.getInstance();
		}
		return cacheManager;
	}
	
	/**
	 * 
	 * Description:根据id查询对应城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param 城市id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<City> searchCityById(String id){
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<City> cityList = cloneList((List<City>)cityCache.get("city"));
		List<City> targetList = new ArrayList<City>();
		for(City c:cityList){
			if(c.getId()!=null && c.getId().equals(id)){
				c.setName(c.getProvinceName()+"-"+c.getName());
				c.setId(c.getProvinceId()+"-"+c.getId());
				targetList.add(c);
				break;
			}
		}
		return targetList;
	}
	/**
	 * 
	 * Description:根据id查询对应省份<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Province> searchProvinceById(String id){
		ICache<String, Object> cityCache = getInstanceCache().getCache(
				CityCacheProvider.class.getName());	
		List<Province> provinceList = (List<Province>)cityCache.get("province");
		List<Province> targetList = new ArrayList<Province>();
		for(Province p:provinceList){
			if(p.getId()!=null &&p.getId().equals(id)){
				targetList.add(p);
				break;
			}
		}
		return targetList;
	}
}
