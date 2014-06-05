package com.deppon.crm.module.common.shared.domain;

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
	/**
	 * 是否有效：true，有效；false，无效
	 */
	private Boolean status;
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the hasAgent
	 */
	public String getHasAgent() {
		return hasAgent;
	}
	/**
	 * @param hasAgent the hasAgent to set
	 */
	public void setHasAgent(String hasAgent) {
		this.hasAgent = hasAgent;
	}
	/**
	 * @return the isPilot
	 */
	public String getIsPilot() {
		return isPilot;
	}
	/**
	 * @param isPilot the isPilot to set
	 */
	public void setIsPilot(String isPilot) {
		this.isPilot = isPilot;
	}
	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}


}
