package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销效果评估报表部门负责人<br />
 * </p>
 * @title AssessDeptPrincipal.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class AssessDeptPrincipal extends BaseEntity{
	//部门ID
	private String deptId;
	//部门名称
	private String deptName;
	//部门负责人empcode
	private String principal;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	
}
