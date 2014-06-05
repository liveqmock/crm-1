/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Area.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.shared.domain;

import java.math.BigDecimal;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:区域实体<br />
 * </p>
 * @title Area.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public class Area extends BaseEntity {

	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 7172282785690031451L;
	
	//主键ID
	private BigDecimal fid;
			
	//区县ID
	private BigDecimal areaID;
	
	//是否县级市
	private String isCityLevel;
	//最后修改人姓名
	private String lastModifyName;
	//城市名字
	private String cityName;
	//城市id
	private Integer cityId;
	private Integer provinceId;
	public BigDecimal getAreaID() {
		return areaID;
	}
	public void setAreaID(BigDecimal areaID) {
		this.areaID = areaID;
	}
	public String getIsCityLevel() {
		return isCityLevel;
	}
	public void setIsCityLevel(String isCityLevel) {
		this.isCityLevel = isCityLevel;
	}
	//名称
	private String name;
	//编码
	private String number;
	//状态
	private String status;
	//城市
	private City city;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public BigDecimal getFid() {
		return fid;
	}
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getLastModifyName() {
		return lastModifyName;
	}
	public void setLastModifyName(String lastModifyName) {
		this.lastModifyName = lastModifyName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
}
