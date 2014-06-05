package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销效果评估报表权限实体<br />
 * </p>
 * @title AssessAuthority.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class AssessAuthority extends BaseEntity{
	//营销效果评估报表的查询权限
	private String assessAuthority;
	//权限部门
	private AssessDept assessDept;
	
	public String getAssessAuthority() {
		return assessAuthority;
	}
	public void setAssessAuthority(String assessAuthority) {
		this.assessAuthority = assessAuthority;
	}
	public AssessDept getAssessDept() {
		return assessDept;
	}
	public void setAssessDept(AssessDept assessDept) {
		this.assessDept = assessDept;
	}
	
}
