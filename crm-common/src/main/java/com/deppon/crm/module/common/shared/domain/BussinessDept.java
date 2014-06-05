/**   
 * @title BussinessDept.java
 * @package com.deppon.crm.module.common.shared.domain
 * @description 营业部门实体（可能是营业部或者是车队）  
 * @author 潘光均
 * @update 2012-3-24 下午1:38:38
 * @version V1.0   
 */
package com.deppon.crm.module.common.shared.domain;

import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @description 营业部门实体（可能是营业部或者是车队）  
 * @author 潘光均
 * @version 0.1 2012-3-24
 *@date 2012-3-24
 */

public class BussinessDept  extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -3518173788012852342L;
	//部门名称
	private String deptName;
	//部门编码
	private String deptCode;
	//部门省份
	private Province province;
	//部门城市
	private City city;
	//网点城市是否有A/B货
	private Boolean ifHashGoodsType;  
	//部门区域
	private Area region;
	//部门地址
	private String deptAddress;
	//部门备注
	private String deptContext;
	//部门联系方式
	private String contactMethod;
	//始发外场
	private Boolean ifOutField;
	//始发中转
	private Boolean ifTransit;
	//是否启用
	private Boolean ifEnable;
	//所属区域
	private Department belongArea;
	//是否开业
	private Boolean ifOpen;
	//组织id
	private Integer organizeId;
	//是否到达
	private Boolean ifArrive;
	//是否出发
	private Boolean ifLeave;
	//是否送货上门
	private Boolean ifHomeDelivery;
	//是否自提
	private Boolean ifSelfDelivery;
	//是否外发
	private Boolean ifOutward;
	//是否车队
	private Boolean ifVehicleTeam;
	//车队是否有PDA
	private Boolean ifHavePDA;
	// erpid调用约车接口时用
	private String erpId;
	// 是否事业部	
	private Boolean ifDivision;
	// 是否大区	
	private Boolean ifBigRegion;
	//是否快递大区
	private Boolean isExpressRegion;
	// 是否小区	
	private Boolean ifSmallRegion;
	// 是否营业部
	private Boolean ifBussinessDept;
	// 部门服务区坐标编号	
	private String depCoordinate;
	// 派送区坐标编号	
	private String deliveryCoordinate;
	/**		修改人：张斌
	修改时间：2013-7-30
	修改内容：无(新增)
	修改原因：增加省市区县名称*/
	//begin
	//省名称
	private String provinceName;
	//城市名称
	private String cityName;
	//区县名称
	private String regionName;
	// 是否顶级车队
		private Boolean isTopFleet;
	/**
	 * @return the provinceName
	 */
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * @param provinceName the provinceName to set
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    //end
	public Boolean getIsExpressRegion() {
		return isExpressRegion;
	}

	public void setIsExpressRegion(Boolean isExpressRegion) {
		this.isExpressRegion = isExpressRegion;
	}

	public Boolean getIfDivision() {
		return ifDivision;
	}

	public void setIfDivision(Boolean ifDivision) {
		this.ifDivision = ifDivision;
	}

	public Boolean getIfBigRegion() {
		return ifBigRegion;
	}

	public void setIfBigRegion(Boolean ifBigRegion) {
		this.ifBigRegion = ifBigRegion;
	}

	public Boolean getIfSmallRegion() {
		return ifSmallRegion;
	}

	public void setIfSmallRegion(Boolean ifSmallRegion) {
		this.ifSmallRegion = ifSmallRegion;
	}

	public Boolean getIfBussinessDept() {
		return ifBussinessDept;
	}

	public void setIfBussinessDept(Boolean ifBussinessDept) {
		this.ifBussinessDept = ifBussinessDept;
	}

	public String getDepCoordinate() {
		return depCoordinate;
	}

	public void setDepCoordinate(String depCoordinate) {
		this.depCoordinate = depCoordinate;
	}

	public String getDeliveryCoordinate() {
		return deliveryCoordinate;
	}

	public void setDeliveryCoordinate(String deliveryCoordinate) {
		this.deliveryCoordinate = deliveryCoordinate;
	}

	public String getErpId() {
		return erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Province getProvince() {
		return province;
	}
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Area getRegion() {
		return region;
	}
	public void setRegion(Area region) {
		this.region = region;
	}
	public String getDeptAddress() {
		return deptAddress;
	}
	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}
	public String getDeptContext() {
		return deptContext;
	}
	public void setDeptContext(String deptContext) {
		this.deptContext = deptContext;
	}
	public String getContactMethod() {
		return contactMethod;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	public Boolean getIfOutField() {
		return ifOutField;
	}
	public void setIfOutField(Boolean ifOutField) {
		this.ifOutField = ifOutField;
	}
	public Boolean getIfTransit() {
		return ifTransit;
	}
	public void setIfTransit(Boolean ifTransit) {
		this.ifTransit = ifTransit;
	}
	public Boolean getIfEnable() {
		return ifEnable;
	}
	public void setIfEnable(Boolean ifEnable) {
		this.ifEnable = ifEnable;
	}
	public Boolean getIfOpen() {
		return ifOpen;
	}
	public void setIfOpen(Boolean ifOpen) {
		this.ifOpen = ifOpen;
	}
	public Integer getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}
	public Boolean getIfArrive() {
		return ifArrive;
	}
	public void setIfArrive(Boolean ifArrive) {
		this.ifArrive = ifArrive;
	}
	public Boolean getIfLeave() {
		return ifLeave;
	}
	public void setIfLeave(Boolean ifLeave) {
		this.ifLeave = ifLeave;
	}
	public Boolean getIfHomeDelivery() {
		return ifHomeDelivery;
	}
	public void setIfHomeDelivery(Boolean ifHomeDelivery) {
		this.ifHomeDelivery = ifHomeDelivery;
	}
	public Boolean getIfSelfDelivery() {
		return ifSelfDelivery;
	}
	public void setIfSelfDelivery(Boolean ifSelfDelivery) {
		this.ifSelfDelivery = ifSelfDelivery;
	}
	public Boolean getIfOutward() {
		return ifOutward;
	}
	public void setIfOutward(Boolean ifOutward) {
		this.ifOutward = ifOutward;
	}
	public Boolean getIfVehicleTeam() {
		return ifVehicleTeam;
	}
	public void setIfVehicleTeam(Boolean ifVehicleTeam) {
		this.ifVehicleTeam = ifVehicleTeam;
	}
	public Department getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(Department belongArea) {
		this.belongArea = belongArea;
	}
	public Boolean getIfHashGoodsType() {
		return ifHashGoodsType;
	}
	public void setIfHashGoodsType(Boolean ifHashGoodsType) {
		this.ifHashGoodsType = ifHashGoodsType;
	}
	public Boolean getIfHavePDA() {
		return ifHavePDA;
	}
	public void setIfHavePDA(Boolean ifHavePDA) {
		this.ifHavePDA = ifHavePDA;
	}
	public Boolean getIsTopFleet() {
		return isTopFleet;
	}

	public void setIsTopFleet(Boolean isTopFleet) {
		this.isTopFleet = isTopFleet;
	}
	
	
}
