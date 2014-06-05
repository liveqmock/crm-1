/**
 * Filename:	IProvinceService.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-5-8 下午2:21:06
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
import com.deppon.crm.module.common.shared.domain.Province;


public interface IProvinceService {
	/**
	 * @作者：罗典
	 * @描述：根据编码查询省份信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：省份信息
	 * */
	public Province queryVaildProvinceByNum(String number);
	/**
	* @Title: 		insertProvince
	* @Description: TODO(新增省份)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void insertProvince(Province province);
	
	
	/**
	* @Title: 		updateProvince
	* @Description: TODO(更新区县)
	* @param 		@param city    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void updateProvince(Province province);
	
	/**
	* @Title: 		deleteProvince
	* @Description: TODO(删除区县)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteProvince(Province province);
	
	/**
	* @Title: 		searchProvincesByName
	* @Description: TODO(根据省份名称返回省份列表)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public List<Province> searchProvincesByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchProvinceByName
	* @Description: TODO(根据省份名称返回省份列表)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Province searchProvinceByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchProvincesCountByName
	* @Description: TODO(根据省份名称返回省份列表记录数)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Integer searchProvincesCountByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		getProvinceById
	* @Description: TODO(根据ID获取省份信息)
	* @param 		@param provinceId    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Province getProvinceById(BigDecimal provinceId);
	/**
	 * 
	 * <p>
	 * Description:查询所有省份<br />
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
	 * Description:通过城市查省份<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-4
	 * @param cityId
	 * @return
	 * Province
	 */
	public Province getProvincesByCityId(Integer cityId);
}
