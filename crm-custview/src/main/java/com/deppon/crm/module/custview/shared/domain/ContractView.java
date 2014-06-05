package com.deppon.crm.module.custview.shared.domain;

import com.deppon.crm.module.customer.shared.domain.ContractDetailView;

/**
 * @description 合同信息
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */

public class ContractView {
	
	private ContractDetailView contract;// 合同基本信息
	
	private String createrName;// 创建人名称
    
	private String modifyUser;//修改人

	// 创建部门名称
	private String createDeptName;

	/**
	 * @return the contract
	 * @author 潘光均
	 * @version v0.1
	 */
	public ContractDetailView getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setContract(ContractDetailView contract) {
		this.contract = contract;
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
	 * @return the modifyUser
	 * @author 潘光均
	 * @version v0.1
	 */
	public String getModifyUser() {
		return modifyUser;
	}

	/**
	 * @param modifyUser the modifyUser to set
	 * @author 潘光均
	 * @version v0.1
	 */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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

}
