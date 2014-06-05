package com.deppon.crm.module.gis.server.service;

import com.deppon.crm.module.gis.shared.domain.PolygonEntity;

public interface IPolygonService {
	
	/**
	 * 
	 * 保存范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	PolygonEntity savePolygon(PolygonEntity polygonEntity);
	/**
	 * 
	 * 更新范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	PolygonEntity updatePolygon(PolygonEntity polygonEntity);
	/**
	 * 
	 * 删除范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	String deletePolygonById(String id);
	/**
	 * 
	 * 查询范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	PolygonEntity selectPolygonById(String id);
	/**
	 * 
	 * <p>TODO(根据id更新百度范围)</p> 
	 * @author 073102-foss-Tommy Wu
	 * @date 2013-5-11 上午09:34:59
	 * @param polygon
	 * @see
	 */
	void updatePolygonBaiduById(PolygonEntity polygon);
	


}
