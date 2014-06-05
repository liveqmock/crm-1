package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销效果评估报表权限部门<br />
 * </p>
 * @title AssessDept.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class AssessDept extends BaseEntity{
	//部门名字
	private String deptName;
	//权限
	private String authority;
	//所属层级
	private String hierarchy;
	//部门负责人empcode
	private String principal;
	//下级部门
	private AssessDept childDept;
	

	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public AssessDept getChildDept() {
		return childDept;
	}
	public void setChildDept(AssessDept childDept) {
		this.childDept = childDept;
	}
	public String getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
}
