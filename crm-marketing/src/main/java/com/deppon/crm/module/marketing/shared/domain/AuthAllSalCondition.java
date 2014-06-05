package com.deppon.crm.module.marketing.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:营销效果评估导出权限下数据查询条件<br />
 * </p>
 * @title AuthAllSalCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 2013-01-17
 */

public class AuthAllSalCondition extends BaseEntity{
	//部门Id
	private String deptId;
	//权限
	private String authority;
	//日期
	private String dateStr;
	//潜客来源
	private String potenSource;
	//业务类别
	private String custCategory;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getPotenSource() {
		return potenSource;
	}
	public void setPotenSource(String potenSource) {
		this.potenSource = potenSource;
	}
	public String getCustCategory() {
		return custCategory;
	}
	public void setCustCategory(String custCategory) {
		this.custCategory = custCategory;
	}
	
}
