package com.deppon.crm.module.custrepeat.shared.domain;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SearchDeptCondition.java
 * @package com.deppon.crm.module.custrepeat.shared.domain 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class SearchDeptCondition {

	//部门ID
	private String deptId;
	//营业部
	private String salesDept;
	//派送部
	private String sendDept;
	//大区
	private String regionDept;
	//营业区
	private String areaDept;
	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the salesDept
	 */
	public String getSalesDept() {
		return salesDept;
	}
	/**
	 * @param salesDept the salesDept to set
	 */
	public void setSalesDept(String salesDept) {
		this.salesDept = salesDept;
	}
	/**
	 * @return the sendDept
	 */
	public String getSendDept() {
		return sendDept;
	}
	/**
	 * @param sendDept the sendDept to set
	 */
	public void setSendDept(String sendDept) {
		this.sendDept = sendDept;
	}
	/**
	 * @return the regionDept
	 */
	public String getRegionDept() {
		return regionDept;
	}
	/**
	 * @param regionDept the regionDept to set
	 */
	public void setRegionDept(String regionDept) {
		this.regionDept = regionDept;
	}
	/**
	 * @return the areaDept
	 */
	public String getAreaDept() {
		return areaDept;
	}
	/**
	 * @param areaDept the areaDept to set
	 */
	public void setAreaDept(String areaDept) {
		this.areaDept = areaDept;
	}
}
