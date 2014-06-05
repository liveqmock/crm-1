package com.deppon.crm.module.customer.shared.domain;

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
	/**
	 *@user pgj
	 *2014-4-11上午8:12:43
	 * @return labelName : return the property labelName.
	 */
	public String getLabelName() {
		return labelName;
	}
	/**
	 * @param labelName : set the property labelName.
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:12:43
	 * @return deptId : return the property deptId.
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId : set the property deptId.
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}
