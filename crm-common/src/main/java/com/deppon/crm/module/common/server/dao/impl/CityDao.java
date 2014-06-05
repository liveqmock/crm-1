/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.ICityDao;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CityDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */

public class CityDao extends iBatis3DaoImpl implements ICityDao {
	
	private static final String NAMESPACE_CITY = "com.deppon.crm.module.common.shared.domain.City.";
	private static final String INSERT_CITY="insertCity";
	private static final String UPDATE_CITY="updateCity";
	private static final String DELETE_CITY="deleteCity";
	private static final String QUERY_CITY="queryCityByID";
	private static final String SEARCH_CITY="searchCitysByName";
	private static final String SEARCH_CITY_ONE="searchCityByName";
	private static final String SEARCH_CITY_COUNT="searchCitysCountByName";
	private static final String GET_CITY_BY_PROVINCEID="queryCityByProvinceId";
	//根据编码查询订单城市形象
	private static final String QUERY_CITY_NUM="queryCityByNum";
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的城市信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：城市信息
	 * */
	public City queryVaildCityByNum(String number){
		return (City)this.getSqlSession().selectOne(NAMESPACE_CITY+QUERY_CITY_NUM,number);
	}
	/**
	 * @description 获取对应城市的区域
	 * @author 毛建强
	 * @2012-3-15
	 * @return 返回区域集合
	 */
	@SuppressWarnings("unchecked")
	public List<City> getCityByProvince(String provinceId) {
		return this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.City.getCityByProvince",provinceId);
	}

	/**
	 * @description 获得所有城市
	 * @author 毛建强
	 * @2012-3-15
	 * @return 城市集合
	 */
	@SuppressWarnings("unchecked")
	public List<City> getAllCity() {
		return this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.City.getAllCity");
	}

	/**
	 * @description 获取最近一次修改时间
	 * @author 毛建强
	 * @2012-3-15
	 * @return 城市集合
	 */
	public Date getLastModifyTime() {
		return (Date) this.getSqlSession().selectOne("com.deppon.crm.module.common.shared.domain.City.getLastModifyTime");
	}

	@Override
	public void insertCity(City city) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert(NAMESPACE_CITY+INSERT_CITY, city);
	}

	@Override
	public void deleteCity(City city) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete(NAMESPACE_CITY+DELETE_CITY, city);
	}

	@Override
	public City getCityById(BigDecimal cityId) {
		// TODO Auto-generated method stub
		return (City) this.getSqlSession().selectOne(
				NAMESPACE_CITY + QUERY_CITY, cityId);
	}

	@Override
	public void updateCity(City city) {
		// TODO Auto-generated method stub
		this.getSqlSession().update(NAMESPACE_CITY+UPDATE_CITY, city);
	}

	@Override
	public List<City> searchCitysByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		List<City> list = null;
		if (null ==condition.getLimit() || 1> condition.getLimit()) {
			list = this.getSqlSession()
					.selectList(NAMESPACE_CITY
							+ SEARCH_CITY,
							condition);
		} else {
			RowBounds bounds = new RowBounds(condition.getStart(),
					condition.getLimit());
			list = this.getSqlSession()
					.selectList(NAMESPACE_CITY
							+ SEARCH_CITY,
							condition, bounds);
		}
		return list;
	}

	@Override
	public Integer searchCitysCountByName(AreaSearchCondition condition) {
		// TODO Auto-generated method stub
		Integer count = (Integer) this
				.getSqlSession()
				.selectOne(NAMESPACE_CITY+SEARCH_CITY_COUNT,
						condition);
		return count;
	}

	@Override
	public List<City> getCityByProvinceId(Integer provinceId) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("provinceId",provinceId);
		List<City> cityList=this.getSqlSession()
				.selectList(NAMESPACE_CITY+GET_CITY_BY_PROVINCEID,map);
		return cityList;
	}
	@Override
	public City searchCityByName(AreaSearchCondition condition) {
		City city = null;
			city = (City) this.getSqlSession()
					.selectOne(NAMESPACE_CITY
							+ SEARCH_CITY_ONE,
							condition);
		return city;
	}
}
