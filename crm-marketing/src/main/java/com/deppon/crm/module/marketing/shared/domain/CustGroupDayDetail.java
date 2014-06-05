package com.deppon.crm.module.marketing.shared.domain;

import java.util.List;

/**   
 * <p>
 * Description:客户分组-客户列表详情-查看每天的数据/>
 * </p>
 * @title CustGroupDayDetail.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author ZhouYuan
 * @version 2013.11.26
 */
public class CustGroupDayDetail {
	//客户编码
	private String custNumber;
	//客户名称
	private String custName;
	//部门名称
	private String deptName;
	//部门ID
	private String deptId;
	//金额列表
	private List<CustGroupDayAmount> amountList;
	
	private Day days;
	
	//本月发到货周期
	private String queryMonthCycle;
	/**
	 * @param days the days to get
	 */
	public Day getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(Day days) {
		this.days = days;
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
	 * @return custName : return the property custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custNumber : set the property custNumber.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return deptName : return the property deptName.
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName : set the property deptName.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @param amountList : set the property amountList.
	 */
	public List<CustGroupDayAmount> getAmountList() {
		return amountList;
	}
	/**
	 * @param amountList : set the property amountList.
	 */
	public void setAmountList(List<CustGroupDayAmount> amountList) {
		this.amountList = amountList;
	}
	public String getQueryMonthCycle() {
		return queryMonthCycle;
	}
	public void setQueryMonthCycle(String queryMonthCycle) {
		this.queryMonthCycle = queryMonthCycle;
	}
	
}
