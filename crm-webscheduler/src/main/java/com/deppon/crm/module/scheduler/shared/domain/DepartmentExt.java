/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DepartmentExt.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */
package com.deppon.crm.module.scheduler.shared.domain;

import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:CRM部门表实体，从OA/ERP导数据用<br />
 * </p>
 * @title DepartmentExt.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author zhujunyong
 * @version 0.1 May 4, 2012
 */

public class DepartmentExt extends Department{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -5481971202415651297L;
	// 父id
	private String parentId;
	// 标杆编码
	private String standardCode;
	/**
	 * @return parentId : return the property parentId.
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId : set the property parentId.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
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
	
	
	
}
