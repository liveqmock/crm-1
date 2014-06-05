package com.deppon.crm.module.interfaces.customer.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * 客户标签实体
 * </p>
 * @title Label.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author 唐亮
 * @version 0.1 2013-4-11
 */
public class Label extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//标签名字
	private String labelName;
	//部门Id
	private String deptId;
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
