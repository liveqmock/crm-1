package com.deppon.crm.module.gis.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

public class BaiduPointJSONEntity extends BaseEntity{
	/**
	 * TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = -8007508771349795941L;
	/**
	 * 是否成功解析 0 表示成功；1表示失败
	 */
	private String error;
	/**
	 * 解析返回的x坐标
	 */
	private String x;
	/**
	 * 解析返回的Y坐标
	 */
	private String y;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
}
