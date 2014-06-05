/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CycleCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author Administrator
 * @version 0.1 2012-4-12
 */
package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

/**   
 * <p>
 * 发货周期表 到货周期表查询条件<br />
 * </p>
 * @title CycleCondition.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author 苏玉军
 * @version 0.1 2012-4-12
 */

public class CycleCondition {
	//部门
	private String deptId;
	//查询日期
	private Date date;
	//客户编码
	private String custNumber;
	//客户分组
	private String groupId;
	//维护人
	private String maintainManId;
	//标识发货周期或者到货
	private String cycleType;
	/**
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
	/**
	 * @return date : return the property date.
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date : set the property date.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return custNumber : return the property custNumber.
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * @param custNumber : set the property custNumber.
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	/**
	 * @return groupId : return the property groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId : set the property groupId.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return maintainManId : return the property maintainManId.
	 */
	public String getMaintainManId() {
		return maintainManId;
	}
	/**
	 * @param maintainManId : set the property maintainManId.
	 */
	public void setMaintainManId(String maintainManId) {
		this.maintainManId = maintainManId;
	}
	/**
	 * @return cycleType : return the property cycleType.
	 */
	public String getCycleType() {
		return cycleType;
	}
	/**
	 * @param cycleType : set the property cycleType.
	 */
	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}
}
