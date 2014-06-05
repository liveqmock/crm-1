/**
 * Filename:	ICityService.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-5-8 下午3:27:08
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-5-8	    
 */

package com.deppon.crm.module.common.server.service;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;


public interface ICityService {
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的城市信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：城市信息
	 * */
	public City queryVaildCityByNum(String number);
	/**
	* @Title: 		insertCity
	* @Description: TODO(新增城市)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void insertCity(City city);
	
	/**
	* @Title: 		deleteCity
	* @Description: TODO(删除城市)
	* @param 		@param city    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteCity(City city);
	
	/**
	* @Title: 		getCityById
	* @Description: TODO(根据ID获取城市信息)
	* @param 		@param cityId    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public City getCityById(BigDecimal cityId);
	
	/**
	* @Title: 		updateCity
	* @Description: TODO(更新城市)
	* @param 		@param city    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void updateCity(City city);
	
	/**
	* @Title: 		searchCitysByName
	* @Description: TODO(根据城市名称返回城市列表)
	* @param 		@param name    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public List<City> searchCitysByName(AreaSearchCondition condition);
	
	
	/**
	* @Title: 		searchCityByName
	* @Description: TODO(根据城市名称返回城市列表)
	* @param 		@param name    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public City searchCityByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchCitysCountByName
	* @Description: TODO(根据城市名称返回城市列表记录数)
	* @param 		@param name    设定文件
	* @return 		Integer    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Integer searchCitysCountByName(AreaSearchCondition condition);
	
	/**
	 * 
	 * <p>
	 * Description:根据省份id查城市<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-5
	 * @param provinceId
	 * @return
	 * List<City>
	 */
	public List<City> getCityByProvinceId(Integer provinceId);
}
