package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.Date;
/**
 * 
 * <p>
 * Description:会员积分查询条件<br />
 * </p>
 * @title MemberIntegCondition.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class MemberIntegCondition {
	//部门编码
	private String deptId;
	//客户编码
	private String customerCode;
	//客户名称
	private String customerName;
	//联系人编码
	private String contactCode;
	//联系人
	private String contactName;
	//开单日期 --开始
	private Date startDate;
	//开单日期--结束
	private Date endDate;
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:customerCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	/**
	 * <p>
	 * Description:customerCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * <p>
	 * Description:contactCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactCode() {
		return contactCode;
	}
	/**
	 * <p>
	 * Description:contactCode<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:startDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * <p>
	 * Description:startDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * <p>
	 * Description:endDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * <p>
	 * Description:endDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
