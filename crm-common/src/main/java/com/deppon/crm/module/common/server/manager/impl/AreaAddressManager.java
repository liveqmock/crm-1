/**   
 * Description:这里写描述<br />
 * @title AreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.deppon.crm.module.common.server.cache.CityCacheDeal;
import com.deppon.crm.module.common.server.manager.AreaAddressValidator;
import com.deppon.crm.module.common.server.manager.IAreaAddressManager;
import com.deppon.crm.module.common.server.service.IAreaAddressService;
import com.deppon.crm.module.common.server.service.ICityService;
import com.deppon.crm.module.common.server.service.IProvinceService;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * Description:Manager<br />
 * @title AreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class AreaAddressManager implements IAreaAddressManager {
	//注入service
	private IAreaAddressService areaAddressService;
	
	private IProvinceService iProvinceService;
	
	private ICityService iCityService;



	public void setiCityService(ICityService iCityService) {
		this.iCityService = iCityService;
	}


	public void setiProvinceService(IProvinceService iProvinceService) {
		this.iProvinceService = iProvinceService;
	}

	public void setAreaAddressService(IAreaAddressService areaAddressService) {
		this.areaAddressService = areaAddressService;
	}

	//此处不用注入
	private CityCacheDeal cityCacheDeal = new CityCacheDeal();
	
	/**
	 * @description 获取常用城市
	 * @author 毛建强
	 * @2012-3-16
	 * @return
	 */
	public List<City> getCommonCities() {
		return cityCacheDeal.getCommonCities();
	}

	/**
	 * @description 获取对应省份的城市
	 * @author 毛建强
	 * @2012-3-16
	 * @return
	 */
	public List<City> getCityByProvince(String provinceId) {
		if(provinceId==null || "".equals(provinceId)){
			return null;
		}
		return cityCacheDeal.getCityByProvince(provinceId);
	}

	/**
	 * @description 获取对应城市的区域
	 * @author 毛建强
	 * @2012-3-16
	 * @return
	 */
	public List<Area> getAreaByCity(String cityId) {
		if(cityId==null||"".equals(cityId)){
			return null;
		}
		List<Area> areaList = new ArrayList<Area>();
		for(Area a:areaAddressService.getAreaByCity(cityId)){
			//将其省份、城市都添加上
			a.setName(a.getCity().getProvince().getName()+"-"+a.getCity().getName()+"-"+a.getName());
			a.setId(a.getCity().getProvince().getId()+"-"+a.getCity().getId()+"-"+a.getId());
			areaList.add(a);
		}
		return areaList;
	}

	/**
	 * @description 获取所有省份列表
	 * @author 毛建强
	 * @2012-3-16
	 * @return
	 */
	public List<Province> getAllProvinceList() {
		return cityCacheDeal.getAllProvinceList();
	}

	/**
	 * @description 根据名字查询对应城市
	 * @author 毛建强
	 * @2012-3-16
	 * @return
	 */
	public List<City> searchCityByName(String name) {
		if(name==null||"".equals(name)){
			return null;
		}
		//采用正则表达式匹配，是否是汉字
		String regx = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
		Pattern pattern = Pattern.compile(regx);  
        boolean tf = pattern.matcher(name).matches(); 
        //如果为汉字，则匹配汉字方法，反之匹配拼音方法
        if(tf){
        	return cityCacheDeal.searchCityByChinese(name);
        }else{
        	return cityCacheDeal.searchCityByEnglish(name);
        }
	}

	/**
	 * 
	 * Description:根据id查询对应城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param 城市id
	 * @return
	 */
	public List<Province> searchProvinceById(String id) {
		if(id==null||"".equals(id)){
			return null;
		}
		return cityCacheDeal.searchProvinceById(id);
	}

	/**
	 * 
	 * Description:根据id查询对应城市<br />
	 * @author 毛建强
	 * @version 0.1 2012-3-27
	 * @param 城市id
	 * @return
	 */
	public List<City> searchCityById(String id) {
		if(id==null||"".equals(id)){
			return null;
		}
		return cityCacheDeal.searchCityById(id);
	}


	/**
	 * @description 根据id查询对应区域
	 * @author 毛建强
	 * @2012-3-28
	 * @return
	 */
	public List<Area> searchAreaById(String id) {
		if(id==null||"".equals(id)){
			return null;
		}
		List<Area> areaList = new ArrayList<Area>();
		for(Area a:areaAddressService.getAreaById(id)){
			//将其省份、城市都添加上
			a.setName(a.getCity().getProvince().getName()+"-"+a.getCity().getName()+"-"+a.getName());
			a.setId(a.getCity().getProvince().getId()+"-"+a.getCity().getId()+"-"+a.getId());
			areaList.add(a);
		}
		return areaList;
	}
	
	/**
	* @Title: 		saveProvince
	* @Description: TODO(保存省份信息)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void saveOrUpdateProvince(Province province){
		if(province!=null&&province.getFid()!=null)
			iProvinceService.updateProvince(province);
		else if(province!=null)
			iProvinceService.insertProvince(province);
	}
	
	/**
	* @Title: 		saveCity
	* @Description: TODO(保存城市信息)
	* @param 		@param city    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void saveOrUpdateCity(City city){
		if(city!=null&&city.getFid()!=null)
			iCityService.updateCity(city);
		else if(city!=null)
			iCityService.insertCity(city);
	}
	
	/**
	* @Title: 		saveArea
	* @Description: TODO(保存区县信息)
	* @param 		@param area    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void saveOrUpdateArea(Area area){
		if(area!=null&&area.getFid()!=null)
			areaAddressService.updateArea(area);
		else if(area!=null)
			areaAddressService.insertArea(area);
	}

	@Override
	public void deleteAreas(List<Area> areas) {
		// TODO Auto-generated method stub
		if(areas!=null&&areas.size()>0){
			Iterator iter=areas.iterator();
			while(iter.hasNext()){
				Area area=(Area)iter.next();
				areaAddressService.deleteArea(area);
			}
		}
		
	}

	@Override
	public void deleteCitys(List<City> citys) {
		// TODO Auto-generated method stub
		if(citys!=null&&citys.size()>0){
			Iterator iter=citys.iterator();
			while(iter.hasNext()){
				City city=(City)iter.next();
				city=iCityService.getCityById(city.getFid());
				AreaAddressValidator validator =new AreaAddressValidator();
				if(validator.isDeleteableCity(city))
					iCityService.deleteCity(city);
			}
		}
	}

	@Override
	public void deleteProvinces(List<Province> provinces) {
		// TODO Auto-generated method stub
		if(provinces!=null&&provinces.size()>0){
			Iterator iter=provinces.iterator();
			while(iter.hasNext()){
				Province province=(Province)iter.next();
				province=iProvinceService.getProvinceById(province.getFid());
				AreaAddressValidator validator =new AreaAddressValidator();
				if(validator.isDeleteableProvince(province))
					iProvinceService.deleteProvince(province);
			}
		}
	}

	@Override
	public Map searchProvincesByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		List<Province> provinces=iProvinceService.searchProvincesByName(condition);
		Integer count=iProvinceService.searchProvincesCountByName(condition);
		Map map=new HashMap();
		map.put("provinces", provinces);
		map.put("count", count);
		return map;
	}

	@Override
	public Map searchCitysByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		List<City> citys=iCityService.searchCitysByName(condition);
		Integer count=iCityService.searchCitysCountByName(condition);
		Map map=new HashMap();
		map.put("citys", citys);
		map.put("count", count);
		return map;
	}

	@Override
	public Map searchAreasByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		List<Area> areas=areaAddressService.searchAreasByName(condition);
		Integer count=areaAddressService.searchAreasCountByName(condition);
		Map map=new HashMap();
		map.put("areas", areas);
		map.put("count", count);
		return map;
	}
	
	public List<Province> getAllProvince(){
		return iProvinceService.getAllProvince();
	}


	@Override
	public Province searchProvincesByCityId(Integer cityId) {
		// TODO Auto-generated method stub
		return iProvinceService.getProvincesByCityId(cityId);
	}


	@Override
	public List<City> getCityByProvinceId(Integer provinceId) {
		// TODO Auto-generated method stub
		return iCityService.getCityByProvinceId(provinceId);
		
	}


	@Override
	public Province searchProvinceByName(AreaSearchCondition condition) {
		return iProvinceService.searchProvinceByName(condition);
	}


	@Override
	public City searchCityByName(AreaSearchCondition condition) {
		return iCityService.searchCityByName(condition);
	}


	@Override
	public Area searchAreaByName(AreaSearchCondition condition) {
		return areaAddressService.searchAreaByName(condition);
	}
}
