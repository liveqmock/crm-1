package com.deppon.crm.module.gis.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class PolygonEntity extends BaseEntity{
	
	/**
	 * TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = -1460767139973565218L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 范围id
	 */
	private String polygonID;
	/**
	 * 区域坐标类型
	 */
	private String type;
	/**
	 * 区域坐标的百度范围
	 */
	private String google;
	/**
	 * 区域坐标的百度范围类型
	 */
	private String baidu;
    /**
     * 区域坐标名称
    */
	private String name;
	/**
	 * 区域坐标描述
	 */
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPolygonID() {
		return polygonID;
	}
	public void setPolygonID(String polygonID) {
		this.polygonID = polygonID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGoogle() {
		return google;
	}
	public void setGoogle(String google) {
		this.google = google;
	}
	public String getBaidu() {
		return baidu;
	}
	public void setBaidu(String baidu) {
		this.baidu = baidu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
