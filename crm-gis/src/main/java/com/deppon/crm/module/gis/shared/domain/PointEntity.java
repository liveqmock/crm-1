package com.deppon.crm.module.gis.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * TODO(描述类的职责)
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:Administrator,date:2013-5-7 下午07:21:18,content:TODO </p>
 * @author 073102-foss-Tommy Wu
 * @date 2013-5-7 下午07:21:18
 * @since
 * @version
 */
public class PointEntity extends BaseEntity {
	/**
	 * TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 381966261041032215L;
	private String baiduLng;
	private String baiduLat;
	private String googleLng;
	private String googleLat;
	private String type;
	private String address;
	private String desc;
	private String name;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBaiduLng() {
		return baiduLng;
	}
	public void setBaiduLng(String baiduLng) {
		this.baiduLng = baiduLng;
	}
	public String getBaiduLat() {
		return baiduLat;
	}
	public void setBaiduLat(String baiduLat) {
		this.baiduLat = baiduLat;
	}
	public String getGoogleLng() {
		return googleLng;
	}
	public void setGoogleLng(String googleLng) {
		this.googleLng = googleLng;
	}
	public String getGoogleLat() {
		return googleLat;
	}
	public void setGoogleLat(String googleLat) {
		this.googleLat = googleLat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
