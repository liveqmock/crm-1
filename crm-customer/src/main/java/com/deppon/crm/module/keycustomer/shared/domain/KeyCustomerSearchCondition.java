package com.deppon.crm.module.keycustomer.shared.domain;

import java.util.Date;

/**
 * 
 * <p>
 * Description:大客户查询条件<br />
 * </p>
 * @title KeyCustomerSearchCondition.java
 * @package com.deppon.crm.module.keycustomer.shared.domain 
 * @author 106138
 * @version 0.1 2014-3-4
 */ 
public class KeyCustomerSearchCondition {
	//归属部门
	private String belongDept;
	//客户编码
	private String custNum;
	//客户名称
	private  String custName;
	//联系人名字
	private String contactName;
	//联系人编码
	private String contactNum;
	//审批状态
	private String  approvalStatus;
	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate;
	//每页条数
	private int limit;
	//开始
	private int start;
	//类型 in out
	private String type;
	//大客户列表状态  1有效 0 无效
	private String listStatus;
	public String getBelongDept() {
		return belongDept;
	}
	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustNum() {
		return custNum;
	}
	/**
	 * <p>
	 * Description:custNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * <p>
	 * Description:custName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * <p>
	 * Description:contactNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	/**
	 * <p>
	 * Description:approvalStatus<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * <p>
	 * Description:approvalStatus<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	/**
	 * <p>
	 * Description:startDate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * <p>
	 * Description:startDate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * <p>
	 * Description:endDate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * <p>
	 * Description:endDate<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * <p>
	 * Description:limit<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public int getStart() {
		return start;
	}
	/**
	 * <p>
	 * Description:start<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * <p>
	 * Description:type<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getType() {
		return type;
	}
	/**
	 * <p>
	 * Description:type<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * <p>
	 * Description:listStatus<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getListStatus() {
		return listStatus;
	}
	/**
	 * <p>
	 * Description:listStatus<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setListStatus(String listStatus) {
		this.listStatus = listStatus;
	}
	/**
	 * <p>
	 * Description:belongDept<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}
	
}
