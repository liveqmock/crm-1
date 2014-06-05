/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DeptInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-14
 */
package com.deppon.crm.module.organization.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DeptInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-14
 */

public class DeptInfo extends BaseEntity{
	// 事业部名称
	private String orgDeptName;
	// 事业部编码
	private String orgCode;
	// 大区名称
	private String bigDeptName;
	// 大区编码
	private String bigCode;
	// 小区名称
	private String smaDeptName;
	// 小区编码
	private String smaCode;
	// 营业部名称
	private String salesDeptName;
	// 营业部编码
	private String salesCode;
	
	/**
	 * @return orgDeptName : return the property orgDeptName.
	 */
	public String getOrgDeptName() {
		return orgDeptName;
	}
	/**
	 * @param orgDeptName : set the property orgDeptName.
	 */
	public void setOrgDeptName(String orgDeptName) {
		this.orgDeptName = orgDeptName;
	}
	/**
	 * @return orgCode : return the property orgCode.
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * @param orgCode : set the property orgCode.
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * @return bigDeptName : return the property bigDeptName.
	 */
	public String getBigDeptName() {
		return bigDeptName;
	}
	/**
	 * @param bigDeptName : set the property bigDeptName.
	 */
	public void setBigDeptName(String bigDeptName) {
		this.bigDeptName = bigDeptName;
	}
	/**
	 * @return bigCode : return the property bigCode.
	 */
	public String getBigCode() {
		return bigCode;
	}
	/**
	 * @param bigCode : set the property bigCode.
	 */
	public void setBigCode(String bigCode) {
		this.bigCode = bigCode;
	}
	/**
	 * @return smaDeptName : return the property smaDeptName.
	 */
	public String getSmaDeptName() {
		return smaDeptName;
	}
	/**
	 * @param smaDeptName : set the property smaDeptName.
	 */
	public void setSmaDeptName(String smaDeptName) {
		this.smaDeptName = smaDeptName;
	}
	/**
	 * @return smaCode : return the property smaCode.
	 */
	public String getSmaCode() {
		return smaCode;
	}
	/**
	 * @param smaCode : set the property smaCode.
	 */
	public void setSmaCode(String smaCode) {
		this.smaCode = smaCode;
	}
	/**
	 * @return salesDept : return the property salesDeptName.
	 */
	public String getSalesDeptName() {
		return salesDeptName;
	}
	/**
	 * @param salesDept : set the property salesDeptName.
	 */
	public void setSalesDeptName(String salesDeptName) {
		this.salesDeptName = salesDeptName;
	}
	/**
	 * @return salesCode : return the property salesCode.
	 */
	public String getSalesCode() {
		return salesCode;
	}
	/**
	 * @param salesCode : set the property salesCode.
	 */
	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}
	
}
