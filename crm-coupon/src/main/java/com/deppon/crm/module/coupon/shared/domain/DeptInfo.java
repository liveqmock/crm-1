/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title DeptInfo.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-14
 */
package com.deppon.crm.module.coupon.shared.domain;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		// 事业部名称
		return orgDeptName;
	}
	/**
	 * @param orgDeptName : set the property orgDeptName.
	 */
	public void setOrgDeptName(String orgDeptName) {
		// 事业部名称
		this.orgDeptName = orgDeptName;
	}
	/**
	 * @return orgCode : return the property orgCode.
	 */
	public String getOrgCode() {
		// 事业部编码
		return orgCode;
	}
	/**
	 * @param orgCode : set the property orgCode.
	 */
	public void setOrgCode(String orgCode) {
		// 事业部编码
		this.orgCode = orgCode;
	}
	/**
	 * @return bigDeptName : return the property bigDeptName.
	 */
	public String getBigDeptName() {
		// 大区名称
		return bigDeptName;
	}
	/**
	 * @param bigDeptName : set the property bigDeptName.
	 */
	public void setBigDeptName(String bigDeptName) {
		// 大区名称
		this.bigDeptName = bigDeptName;
	}
	/**
	 * @return bigCode : return the property bigCode.
	 */
	public String getBigCode() {
		// 大区编码
		return bigCode;
	}
	/**
	 * @param bigCode : set the property bigCode.
	 */
	public void setBigCode(String bigCode) {
		// 大区编码
		this.bigCode = bigCode;
	}
	/**
	 * @return smaDeptName : return the property smaDeptName.
	 */
	public String getSmaDeptName() {
		// 小区名称
		return smaDeptName;
	}
	/**
	 * @param smaDeptName : set the property smaDeptName.
	 */
	public void setSmaDeptName(String smaDeptName) {
		// 小区名称
		this.smaDeptName = smaDeptName;
	}
	/**
	 * @return smaCode : return the property smaCode.
	 */
	public String getSmaCode() {
		// 小区编码
		return smaCode;
	}
	/**
	 * @param smaCode : set the property smaCode.
	 */
	public void setSmaCode(String smaCode) {
		// 小区编码
		this.smaCode = smaCode;
	}
	/**
	 * @return salesDept : return the property salesDeptName.
	 */
	public String getSalesDeptName() {
		// 营业部名称
		return salesDeptName;
	}
	/**
	 * @param salesDept : set the property salesDeptName.
	 */
	public void setSalesDeptName(String salesDeptName) {
		// 营业部名称
		this.salesDeptName = salesDeptName;
	}
	/**
	 * @return salesCode : return the property salesCode.
	 */
	public String getSalesCode() {
		// 营业部编码
		return salesCode;
	}
	/**
	 * @param salesCode : set the property salesCode.
	 */
	public void setSalesCode(String salesCode) {
		// 营业部编码
		this.salesCode = salesCode;
	}
	
}
