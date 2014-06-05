package com.deppon.crm.module.custview.shared.domain;

import com.deppon.crm.module.customer.shared.domain.Member;

/**
 * @description 会员信息
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class MemberView {
	// 会员基本信息
	private Member member;
	// 集中结算部门
	private String focusDeptName;
	// 所属区域名称
	private String areaName;
	// 所属部门
	private String deptName;
	// 创建人名称
	private String createrName;
	// 创建部门名称
	private String createDeptName;
	// 更新人名称
	private String updaterName;
	// 更新人所在部门
	private String updateDeptName;
	/**
	 * @return the member
	 * @author 潘光均
	 * @version v0.1
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * @return the focusDeptName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getFocusDeptName() {
		return focusDeptName;
	}
	/**
	 * @param focusDeptName the focusDeptName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setFocusDeptName(String focusDeptName) {
		this.focusDeptName = focusDeptName;
	}
	/**
	 * @return the areaName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * @param areaName the areaName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * @return the deptName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the createrName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	/**
	 * @return the createDeptName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getCreateDeptName() {
		return createDeptName;
	}
	/**
	 * @param createDeptName the createDeptName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setCreateDeptName(String createDeptName) {
		this.createDeptName = createDeptName;
	}
	/**
	 * @return the updaterName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getUpdaterName() {
		return updaterName;
	}
	/**
	 * @param updaterName the updaterName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setUpdaterName(String updaterName) {
		this.updaterName = updaterName;
	}
	/**
	 * @return the updateDeptName
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getUpdateDeptName() {
		return updateDeptName;
	}
	/**
	 * @param updateDeptName the updateDeptName to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setUpdateDeptName(String updateDeptName) {
		this.updateDeptName = updateDeptName;
	}
}
