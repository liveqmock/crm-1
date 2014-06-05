/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProvinceDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProvinceDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public interface IProvinceDao {
	
	/**
	 * @作者：罗典
	 * @描述：根据编码查询省份信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：省份信息
	 * */
	public Province queryVaildProvinceByNum(String number);
	
	
	public List<Province> getAllProvince();
	
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
	* @Description: TODO(删除省份)
	* @param 		@param province    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteProvince(Province province);
	
	/**
	* @Title: 		searchProvincesByName
	* @Description: TODO(根据省份名称返回省份列表)
	* @param 		@param name    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public List<Province> searchProvincesByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchProvinceByName
	* @Description: TODO(根据省份名称返回省份列表)
	* @param 		@param name    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Province searchProvinceByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchProvincesCountByName
	* @Description: TODO(根据省份名称返回省份列表记录数)
	* @param 		@param name    设定文件
	* @return 		Integer    返回类型
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
	 * Description:查询城市所在省份<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-6-4
	 * @param cityId
	 * @return
	 * Province
	 */
	public Province getProvincesByCityId(Integer cityId);
}
