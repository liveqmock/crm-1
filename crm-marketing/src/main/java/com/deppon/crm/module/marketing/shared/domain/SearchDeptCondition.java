package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:区域划分查询下级部门<br />
 * </p>
 * @title SearchDeptCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhouYuan
 * @version 0.1 2013-05-27
 */
public class SearchDeptCondition extends BaseEntity {

	private static final long serialVersionUID = 1L;
	//部门ID
	private String deptId;
	//上级部门ID
	private String parentId;
	//权限性质
	private String authCharacter;
	//所属模块
	private String owner;
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
	 * 上级部门ID Get方法
	 */
	public String getParentId() {
		//上级部门ID
		return parentId;
	}
	/**
	 * 上级部门ID Set方法
	 */
	public void setParentId(String parentId) {
		//上级部门ID
		this.parentId = parentId;
	}
	/**
	 * 权限性质 Get方法
	 */
	public String getAuthCharacter() {
		//权限性质
		return authCharacter;
	}
	/**
	 * 权限性质 Set方法
	 */
	public void setAuthCharacter(String authCharacter) {
		//权限性质
		this.authCharacter = authCharacter;
	}
	/**
	 * 所属模块 Get方法
	 */
	public String getOwner() {
		//所属模块
		return owner;
	}
	/**
	 * 所属模块 Set方法
	 */
	public void setOwner(String owner) {
		//所属模块
		this.owner = owner;
	}
}
