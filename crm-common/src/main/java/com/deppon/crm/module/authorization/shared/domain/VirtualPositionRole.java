package com.deppon.crm.module.authorization.shared.domain;

/**
 * 
 * <p>
 * 虚拟岗位与角色对应对象<br />
 * </p>
 * @title VirtualPositionRole.java
 * @package com.deppon.crm.module.authorization.shared.domain 
 * @author suyujun
 * @version 0.1 2013-11-26
 */
public class VirtualPositionRole {
	private String id;
	//虚拟岗位Id
	private String vpId;
	//角色Id
	private String roleId;
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return vpId : return the property vpId.
	 */
	public String getVpId() {
		return vpId;
	}
	/**
	 * @param vpId : set the property vpId.
	 */
	public void setVpId(String vpId) {
		this.vpId = vpId;
	}
	/**
	 * @return roleId : return the property roleId.
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId : set the property roleId.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
