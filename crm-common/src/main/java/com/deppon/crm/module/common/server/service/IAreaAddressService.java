/**   
 * Description:这里写描述<br />
 * @title IAreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.service;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.AreaSearchCondition;
import com.deppon.foss.framework.service.IService;

/**   
 * Description:这里写描述<br />
 * @title IAreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public interface IAreaAddressService extends IService {
	List<Area> getAreaByCity(String cityId);
	/**
	 * @作者：罗典
	 * @描述：根据编码查询有效的区域信息
	 * @时间：2013-01-31
	 * @参数：编码
	 * @返回：区域信息
	 * */
	public Area queryVaildAreaByNum(String number);
	/** 
	 * @description 根据id查询对应城市
	 * @author 毛建强
	 * @2012-3-15
	 * @return 返回区域集合
	 */
	List<Area> getAreaById(String id);
	
	/**
	* @Title: 		insertArea
	* @Description: TODO(新增区县)
	* @param 		@param area    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void insertArea(Area area);
	
	/**
	* @Title: 		updateArea
	* @Description: TODO(更新区县)
	* @param 		@param area    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void updateArea(Area area);
	
	/**
	* @Title: 		getAreaById
	* @Description: TODO(根据ID获取区县信息)
	* @param 		@param areaId
	* @param 		@return    设定文件
	* @return 		Area    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Area getAreaById(BigDecimal areaId);
	
	/**
	* @Title: 		deleteArea
	* @Description: TODO(删除区县)
	* @param 		@param area    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public void deleteArea(Area area);
	
	/**
	* @Title: 		searchAreasByName
	* @Description: TODO(根据区县名称返回区县列表)
	* @param 		@param name    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public List<Area> searchAreasByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchAreaByName
	* @Description: TODO(根据区县名称返回区县列表)
	* @param 		@param name    设定文件
	* @return 		void    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Area searchAreaByName(AreaSearchCondition condition);
	
	/**
	* @Title: 		searchAreasCountByName
	* @Description: TODO(根据区县名称返回区县列表记录数)
	* @param 		@param name    设定文件
	* @return 		Integer    返回类型
	* @throws
	* @see <a href="package.html#section">查看流程图</a>
	*/
	public Integer searchAreasCountByName(AreaSearchCondition condition);
}
