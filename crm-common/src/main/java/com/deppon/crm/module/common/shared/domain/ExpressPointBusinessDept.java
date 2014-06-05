package com.deppon.crm.module.common.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:点部与营业部映射关系<br />
 * </p>
 * 
 * @title ExpressPointBusinessDept.java
 * @package com.deppon.crm.module.common.shared.domain
 * @author roy
 * @version 0.1 2013-7-31
 */
public class ExpressPointBusinessDept extends BaseEntity {
	private static final long serialVersionUID = -759736771624242253L;
	/**
	 * 点部标杆编码
	 */
	private String expressPointCode;
	/**
	 * 点部标杆部门名称
	 */
	private String expressPointName;
	/**
	 * 营业部标杆编码
	 */
	private String businessDeptCode;
	/**
	 * 营业部名字
	 */
	private String businessDeptName;

	public String getExpressPointCode() {
		return expressPointCode;
	}

	public void setExpressPointCode(String expressPointCode) {
		this.expressPointCode = expressPointCode;
	}

	public String getExpressPointName() {
		return expressPointName;
	}

	public void setExpressPointName(String expressPointName) {
		this.expressPointName = expressPointName;
	}

	public String getBusinessDeptCode() {
		return businessDeptCode;
	}

	public void setBusinessDeptCode(String businessDeptCode) {
		this.businessDeptCode = businessDeptCode;
	}

	public String getBusinessDeptName() {
		return businessDeptName;
	}

	public void setBusinessDeptName(String businessDeptName) {
		this.businessDeptName = businessDeptName;
	}

}
