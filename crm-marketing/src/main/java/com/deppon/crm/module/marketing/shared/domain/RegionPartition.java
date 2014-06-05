package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:区域划分实体<br />
 * </p>
 * @title RegionPartition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhouYuan
 * @version 0.1 2013-05-27
 */
public class RegionPartition extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//部门ID
	private String deptId;
	//部门名称
	private String deptName;
	//区域ID
	private String regionId;
	//区域颜色
	private String color;
	/**
	 * 部门ID Get方法
	 */
	public String getDeptId() {
		//部门ID
		return deptId;
	}
	/**
	 * 部门ID Set方法
	 */
	public void setDeptId(String deptId) {
		//部门ID
		this.deptId = deptId;
	}
	/**
	 * 部门名称 Get方法
	 */
	public String getDeptName() {
		//部门名称
		return deptName;
	}
	/**
	 * 部门名称 Set方法
	 */
	public void setDeptName(String deptName) {
		//部门名称
		this.deptName = deptName;
	}
	/**
	 * 区域ID Get方法
	 */
	public String getRegionId() {
		//区域ID
		return regionId;
	}
	/**
	 * 区域ID Set方法
	 */
	public void setRegionId(String regionId) {
		//区域ID
		this.regionId = regionId;
	}
	/**
	 * 区域颜色 Get方法
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 区域颜色 Set方法
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
