/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title LadingStation.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */
package com.deppon.crm.module.scheduler.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:CRM网点信息实体<br />
 * </p>
 * @title LadingStation.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */

public class LadingStation extends BaseEntity {

	private static final long serialVersionUID = -9196089983431363589L;
	
	// 部门名称
	private String deptName;
	// 部门地址
	private String detpAddress;
	// 部门备注
	private String remark;
	// 部门联系方式
	private String contact;
	// 是否外场
	private String isOutfield;
	// 是否中转
	private String isTransit;
	// 是否启用
	private String isEnable;
	// 所属区县
	private String area;
	// 新省份
	private String provinceId;
	// 新城市
	private String cityId;
	// 新区县
	private String areaId;
	// 是否开业
	private String isOpen;
	// 组织ID
	private String orgId;
	// 是否到达
	private String isArrive;
	// 是否出发
	private String isLeave;
	// 是否送货上门
	private String isHomeDelivery;
	// 是否自提
	private String isSelfDelivery;
	// 是否外发
	private String isOutward;
	// 是否车队
	private String isTeam;
	// 是否有PDA
	private String isHavePDA;
	// ERPID
	private String erpId;
	// CRM标杆编码（OA获取，OA不存在时从ERP获取，见SQL）
	private String standardCode;
	
	/**
	 * @return standardCode : return the property standardCode.
	 */
	public String getStandardCode() {
		return standardCode;
	}
	/**
	 * @param standardCode : set the property standardCode.
	 */
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	/**
	 * @return deptName : return the property deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName : set the property deptName.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return detpAddress : return the property detpAddress.
	 */
	public String getDetpAddress() {
		return detpAddress;
	}
	/**
	 * @param detpAddress : set the property detpAddress.
	 */
	public void setDetpAddress(String detpAddress) {
		this.detpAddress = detpAddress;
	}
	/**
	 * @return remark : return the property remark.
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark : set the property remark.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return contact : return the property contact.
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact : set the property contact.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return isOutfield : return the property isOutfield.
	 */
	public String getIsOutfield() {
		return isOutfield;
	}
	/**
	 * @param isOutfield : set the property isOutfield.
	 */
	public void setIsOutfield(String isOutfield) {
		this.isOutfield = isOutfield;
	}
	/**
	 * @return isTransit : return the property isTransit.
	 */
	public String getIsTransit() {
		return isTransit;
	}
	/**
	 * @param isTransit : set the property isTransit.
	 */
	public void setIsTransit(String isTransit) {
		this.isTransit = isTransit;
	}
	/**
	 * @return isEnable : return the property isEnable.
	 */
	public String getIsEnable() {
		return isEnable;
	}
	/**
	 * @param isEnable : set the property isEnable.
	 */
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * @return area : return the property area.
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area : set the property area.
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return provinceId : return the property provinceId.
	 */
	public String getProvinceId() {
		return provinceId;
	}
	/**
	 * @param provinceId : set the property provinceId.
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * @return cityId : return the property cityId.
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId : set the property cityId.
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return areaId : return the property areaId.
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId : set the property areaId.
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 * @return isOpen : return the property isOpen.
	 */
	public String getIsOpen() {
		return isOpen;
	}
	/**
	 * @param isOpen : set the property isOpen.
	 */
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	/**
	 * @return orgId : return the property orgId.
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId : set the property orgId.
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return isArrive : return the property isArrive.
	 */
	public String getIsArrive() {
		return isArrive;
	}
	/**
	 * @param isArrive : set the property isArrive.
	 */
	public void setIsArrive(String isArrive) {
		this.isArrive = isArrive;
	}
	/**
	 * @return isLeave : return the property isLeave.
	 */
	public String getIsLeave() {
		return isLeave;
	}
	/**
	 * @param isLeave : set the property isLeave.
	 */
	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}
	/**
	 * @return isHomeDelivery : return the property isHomeDelivery.
	 */
	public String getIsHomeDelivery() {
		return isHomeDelivery;
	}
	/**
	 * @param isHomeDelivery : set the property isHomeDelivery.
	 */
	public void setIsHomeDelivery(String isHomeDelivery) {
		this.isHomeDelivery = isHomeDelivery;
	}
	/**
	 * @return isSelfDelivery : return the property isSelfDelivery.
	 */
	public String getIsSelfDelivery() {
		return isSelfDelivery;
	}
	/**
	 * @param isSelfDelivery : set the property isSelfDelivery.
	 */
	public void setIsSelfDelivery(String isSelfDelivery) {
		this.isSelfDelivery = isSelfDelivery;
	}
	/**
	 * @return isOutward : return the property isOutward.
	 */
	public String getIsOutward() {
		return isOutward;
	}
	/**
	 * @param isOutward : set the property isOutward.
	 */
	public void setIsOutward(String isOutward) {
		this.isOutward = isOutward;
	}
	/**
	 * @return isTeam : return the property isTeam.
	 */
	public String getIsTeam() {
		return isTeam;
	}
	/**
	 * @param isTeam : set the property isTeam.
	 */
	public void setIsTeam(String isTeam) {
		this.isTeam = isTeam;
	}
	/**
	 * @return isHavePDA : return the property isHavePDA.
	 */
	public String getIsHavePDA() {
		return isHavePDA;
	}
	/**
	 * @param isHavePDA : set the property isHavePDA.
	 */
	public void setIsHavePDA(String isHavePDA) {
		this.isHavePDA = isHavePDA;
	}
	/**
	 * @return erpId : return the property erpId.
	 */
	public String getErpId() {
		return erpId;
	}
	/**
	 * @param erpId : set the property erpId.
	 */
	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
		
}
