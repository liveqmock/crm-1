/**   
 * Description:这里写描述<br />
 * @title IAreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * Description:这里写描述<br />
 * @title IAreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public interface IAreaAddressManager {
	List<City> getCommonCities();
	List<City> getCityByProvince(String provinceId);
	List<Area> getAreaByCity(String cityId);
	List<Province> getAllProvinceList();
	List<City> searchCityByName(String name);
	List<Province> searchProvinceById(String id);
	List<City> searchCityById(String id);
	List<Area> searchAreaById(String id);
	
	/**
	* @Title: 		saveProvince
	* @Description: TODO(保存省份信息)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void saveOrUpdateProvince(Province province);
	
	/**
	* @Title: 		saveCity
	* @Description: TODO(保存城市信息)
	* @param 		@param city    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void saveOrUpdateCity(City city);
	
	/**
	* @Title: 		saveArea
	* @Description: TODO(保存区县信息)
	* @param 		@param area    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void saveOrUpdateArea(Area area);
	
	/**
	* @Title: 		deleteArea
	* @Description: TODO(删除区县信息)
	* @param 		@param areas    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteAreas(List<Area> areas);
	
	/**
	* @Title: 		deleteCitys
	* @Description: TODO(删除城市信息)
	* @param 		@param citys    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteCitys(List<City> citys);
	
	/**
	* @Title: 		deleteProvinces
	* @Description: TODO(删除省份信息)
	* @param 		@param provinces    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteProvinces(List<Province> provinces);
	
	/**
	* @Title: 		searchProvinceByName
	* @Description: TODO(根据名称查询省份信息列表)
	* @param 		@param name
	* @param 		@return    设定文件
	* @return 		List<Province>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Map searchProvincesByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchProvinceByName
	* @Description: TODO(根据名称查询省份信息列表)
	* @param 		@param name
	* @param 		@return    设定文件
	* @return 		List<Province>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Province searchProvinceByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchCityByName
	* @Description: TODO(根据名称查询城市信息列表)
	* @param 		@param name
	* @param 		@return    设定文件
	* @return 		List<City>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Map searchCitysByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchCityByName
	* @Description: TODO(根据名称查询城市信息列表)
	* @param 		@param name
	* @param 		@return    设定文件
	* @return 		List<City>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public City searchCityByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchAreasByName
	* @Description: TODO(根据名称查询区县信息列表)
	* @param 		@param name
	* @param 		@return    设定文件
	* @return 		List<Area>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Map searchAreasByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchAreaByName
	* @Description: TODO(根据名称查询区县信息列表)
	* @param 		@param name
	* @param 		@return    设定文件
	* @return 		List<Area>    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Area searchAreaByName(AreaSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:查询所有的省份<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-4
	 * @return
	 * List<Province>
	 */
	public List<Province> getAllProvince();
	/**
	 * 
	 * <p>
	 * Description:查询城市所在省<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-5
	 * @param cityId
	 * @return
	 * Province
	 */
	public Province searchProvincesByCityId(Integer cityId);
	/**
	 * 
	 * <p>
	 * Description:通过省份id查所有城市<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-5
	 * @param provinceId
	 * @return
	 * List<City>
	 */
	public List<City> getCityByProvinceId(Integer provinceId);
}
