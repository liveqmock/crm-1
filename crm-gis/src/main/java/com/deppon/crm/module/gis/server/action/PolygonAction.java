package com.deppon.crm.module.gis.server.action;

import java.net.URLDecoder;

import com.deppon.crm.module.gis.server.service.IPolygonService;
import com.deppon.crm.module.gis.shared.domain.PolygonEntity;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class PolygonAction extends AbstractAction{
	
	/*
	 * 范围
	 */
	private String id;
	/*
	 * 范围实体
	 */
	private PolygonEntity polygonEntity;
	
	/*
	 *	service 
	 */
	private IPolygonService polygonService;
	/**
	 * 
	 * 保存范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	public String savePolygon() {
		polygonEntity = polygonService.savePolygon(polygonEntity);
		return SUCCESS;
	}
	/**
	 * 
	 * 更新范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	public String updatePolygon() {
		polygonService.updatePolygon(polygonEntity);
		return SUCCESS;

	}
	/**
	 * 
	 * 删除范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	public String deletePolygonById() {
		if(id!=null||!"".equals(id)){
			id= polygonService.deletePolygonById(URLDecoder.decode(id));
		}
		return SUCCESS;
	}
	/**
	 * 
	 * 查询范围 
	 * @author 073102-foss-wuzhizhong
	 * @date 2013-4-20 下午06:09:36
	 * @return
	 * @see
	 */
	public String selectPolygonById() {
		if(id!=null||!"".equals(id)){
			polygonEntity =polygonService.selectPolygonById(URLDecoder.decode(id));
		}
		return SUCCESS;

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PolygonEntity getPolygonEntity() {
		return polygonEntity;
	}
	public void setPolygonEntity(PolygonEntity polygonEntity) {
		this.polygonEntity = polygonEntity;
	}
	public void setPolygonService(IPolygonService polygonService) {
		this.polygonService = polygonService;
	}

}
