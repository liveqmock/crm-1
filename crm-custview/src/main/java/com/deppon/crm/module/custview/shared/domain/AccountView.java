package com.deppon.crm.module.custview.shared.domain;

import com.deppon.crm.module.customer.shared.domain.Account;

/**
 * @description 财务信息
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class AccountView {
	// 财务基本信息
	private Account account;
	// 创建人名称
	private String createrName;
	// 创建部门名称
	private String createDeptName;
	// 更新人名称
	private String updaterName;
	// 更新人所在部门
	private String updateDeptName;
	/**
	 * @return the account
	 * @author 潘光均
	 * @version v0.1
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setAccount(Account account) {
		this.account = account;
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
