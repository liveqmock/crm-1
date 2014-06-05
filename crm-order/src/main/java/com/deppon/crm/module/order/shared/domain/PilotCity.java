package com.deppon.crm.module.order.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 试点城市
 * @author 胡傲果
 */
public class PilotCity extends BaseEntity {
	private static final long serialVersionUID = -759736771624242253L;
	/**
	 * 行政区域编码
	 */
	private String cityCode;
	/**
	 * 区域全称
	 */
	private String cityName;
	/**
	 * 是否落地配城市：y，是；n，否
	 */
	private String hasAgent;
	/**
	 * 是否试点城市：y，是；n，否
	 */
	private String isPilot;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHasAgent() {
		return hasAgent;
	}

	public void setHasAgent(String hasAgent) {
		this.hasAgent = hasAgent;
	}

	public String getIsPilot() {
		return isPilot;
	}

	public void setIsPilot(String isPilot) {
		this.isPilot = isPilot;
	}

}
