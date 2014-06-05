/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title searchCustomerCondition.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-4-27
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title searchCustomerCondition.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhuPJ
 * @version 0.1 2012-4-27
 */

public class SearchCustomerCondition {
	// 客户编码
	private String[] custNumbers;
	// 客户ID
	private String[] custIds;
	// 联系人ID
	private String[] contactIds;
	// 类型：1.发到货 2.潜散客 3.会员
	private String type;
	//待查询固定客户的部门id（可视化营销功能中添加）
	private String deptId;
	/**
	 * @param deptId the deptId to get
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return custNumbers : return the property custNumbers.
	 */
	public String[] getCustNumbers() {
		return custNumbers;
	}
	/**
	 * @param custNumbers : set the property custNumbers.
	 */
	public void setCustNumbers(String[] custNumbers) {
		this.custNumbers = custNumbers;
	}
	/**
	 * @return custIds : return the property custIds.
	 */
	public String[] getCustIds() {
		return custIds;
	}
	/**
	 * @param custIds : set the property custIds.
	 */
	public void setCustIds(String[] custIds) {
		this.custIds = custIds;
	}
	/**
	 * @return contactIds : return the property contactIds.
	 */
	public String[] getContactIds() {
		return contactIds;
	}
	/**
	 * @param contactIds : set the property contactIds.
	 */
	public void setContactIds(String[] contactIds) {
		this.contactIds = contactIds;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
