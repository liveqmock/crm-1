/**   
 * @title Region.java
 * @package com.deppon.crm.module.order.shared.domain
 * @description 行政区域实体类
 * @author 潘光均
 * @update 2012-3-8 下午7:19:09
 * @version V1.0   
 */
package com.deppon.crm.module.common.shared.domain;

/**
 * @description 行政区域实体类  
 * @author 潘光均
 * @version 0.1 2012-3-8
 *@date 2012-3-8
 */

public class Region {
	/**
	 * constructer
	 * @param id
	 * @param regionCode
	 * @param regionName
	 * @param regionLevel
	 * @param parentCode
	 */
	public Region(Integer id, String regionCode, String regionName,
			Integer regionLevel, Region parentCode) {
		super();
		this.id = id;
		this.regionCode = regionCode;
		this.regionName = regionName;
		this.regionLevel = regionLevel;
		this.parentCode = parentCode;
	}
	private Integer id;
	//区域code
	private String regionCode;
	//区域名称
	private String regionName;
	//区域级别
	private Integer regionLevel;
	//父区域
	private Region parentCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Integer getRegionLevel() {
		return regionLevel;
	}
	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}
	public Region getParentCode() {
		return parentCode;
	}
	public void setParentCode(Region parentCode) {
		this.parentCode = parentCode;
	}
}
