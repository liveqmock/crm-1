package com.deppon.crm.module.authorization.shared.domain;

import java.util.List;

/**
 * 
 * <p>
 * 虚拟岗位角色 Vo<br />
 * </p>
 * @title VirtualPositionRoleVo.java
 * @package com.deppon.crm.module.authorization.shared.domain 
 * @author suyujun
 * @version 0.1 2013-11-28
 */
public class VirtualPositionRoleVo {
	//虚拟岗位ID
	private String virtualPositionId;
	//虚拟岗位名称
	private String virtualPositionName;
	//虚拟岗位对应的角色
	private List<String> roles;
	//虚拟岗位对应的角色列表所转化成的字符串
	private String roleDesc;
	//虚拟岗位描述
	private String virtualPositionDesc;
	/**
	 * @param roleDesc the roleDesc to get
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	/**
	 * @return virtualPositionId : return the property virtualPositionId.
	 */
	public String getVirtualPositionId() {
		return virtualPositionId;
	}
	/**
	 * @param virtualPositionId : set the property virtualPositionId.
	 */
	public void setVirtualPositionId(String virtualPositionId) {
		this.virtualPositionId = virtualPositionId;
	}
	/**
	 * @return virtualPositionName : return the property virtualPositionName.
	 */
	public String getVirtualPositionName() {
		return virtualPositionName;
	}
	/**
	 * @param virtualPositionName : set the property virtualPositionName.
	 */
	public void setVirtualPositionName(String virtualPositionName) {
		this.virtualPositionName = virtualPositionName;
	}
	/**
	 * @return roles : return the property roles.
	 */
	/**
	 * @return roles : return the property roles.
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles : set the property roles.
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	/**
	 * @return virtualPositionDesc : return the property virtualPositionDesc.
	 */
	public String getVirtualPositionDesc() {
		return virtualPositionDesc;
	}
	/**
	 * @param virtualPositionDesc : set the property virtualPositionDesc.
	 */
	public void setVirtualPositionDesc(String virtualPositionDesc) {
		this.virtualPositionDesc = virtualPositionDesc;
	}
	
}
