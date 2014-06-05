package com.deppon.crm.module.keycustomer.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:大客户准入或者预退出列表,通过type区分(in为准入,out为退出)<br />
 * </p>
 * 
 * @title KeyCustomerList.java
 * @package com.deppon.crm.module.keycustomer.shared.domain
 * @author 106138
 * @version 0.1 2014-3-4
 */ 
public class KeyCustomer extends BaseEntity {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 7345003637557185392L;
	// 归属部门id
	private String deptId;
	//归属部门名字
	private String deptName;
	//客户id
	private String custId;
	//近三个月发货金额之和
	private String sumAmount;
	//近三个月发货金额
	private String amountOfSignMent;
	//信用预警次数
	private int creditTimes;
	//类型 表示列表的类型 ---in为准入 out 为退出
	private String type;
	//状态 0无效 1有效
	private String status;
	// 联系人编码
	private String contactNum;
	// 联系人姓名
	private String contactName;
	// 客户编码
	private String customerNum;
	// 客户名称
	private String customerName;
	// 客户等级
	private String custDegree;
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:deptId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * <p>
	 * Description:deptName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * <p>
	 * Description:custId<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * <p>
	 * Description:sumAmount<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getSumAmount() {
		return sumAmount;
	}
	/**
	 * <p>
	 * Description:sumAmount<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}
	/**
	 * <p>
	 * Description:amountOfSignMent<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getAmountOfSignMent() {
		return amountOfSignMent;
	}
	/**
	 * <p>
	 * Description:amountOfSignMent<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setAmountOfSignMent(String amountOfSignMent) {
		this.amountOfSignMent = amountOfSignMent;
	}
	/**
	 * <p>
	 * Description:creditTimes<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public int getCreditTimes() {
		return creditTimes;
	}
	/**
	 * <p>
	 * Description:creditTimes<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCreditTimes(int creditTimes) {
		this.creditTimes = creditTimes;
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
	 * Description:status<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <p>
	 * Description:status<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * Description:customerNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustomerNum() {
		return customerNum;
	}
	/**
	 * <p>
	 * Description:customerNum<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * <p>
	 * Description:customerName<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * <p>
	 * Description:custDegree<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public String getCustDegree() {
		return custDegree;
	}
	/**
	 * <p>
	 * Description:custDegree<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setCustDegree(String custDegree) {
		this.custDegree = custDegree;
	}
	
	
}
