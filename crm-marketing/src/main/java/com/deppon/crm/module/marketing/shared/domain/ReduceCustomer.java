/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ReduceCustomer.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 18, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description:发货量减少客户POJO类，用于每日计划生成，定时器使用<br />
 * 以customerNumber为hashCode和equals判断基准
 * </p>
 * @title ReduceCustomer.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 18, 2012
 */

public class ReduceCustomer {
	//客户ID
	private String custId;
	//客户编码
	private String custNumber;
	//当前金额
	private int currAmount;
	//上月金额
	private int lastAmount;
	//客户等级
	private String degree;
	//部门ID
	private String deptId;
	//联系人ID
	private String contactId;
	/**
	 * @return custId : return the property custId.
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId : set the property custId.
	 */
	public void setCustId(String custId) {
		this.custId = custId;
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
	 * @return currAmount : return the property currAmount.
	 */
	public int getCurrAmount() {
		return currAmount;
	}
	/**
	 * @param currAmount : set the property currAmount.
	 */
	public void setCurrAmount(int currAmount) {
		this.currAmount = currAmount;
	}
	/**
	 * @return lastAmount : return the property lastAmount.
	 */
	public int getLastAmount() {
		return lastAmount;
	}
	/**
	 * @param lastAmount : set the property lastAmount.
	 */
	public void setLastAmount(int lastAmount) {
		this.lastAmount = lastAmount;
	}
	/**
	 * @return degree : return the property degree.
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree : set the property degree.
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
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
	 * @return contactId : return the property contactId.
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId : set the property contactId.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((custNumber == null) ? 0 : custNumber.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//obj与this相等
		if (this == obj){
			//true
			return true;
		}
		//obj为空
		if (obj == null){
			//false
			return false;
		}
		//类型不一致
		if (getClass() != obj.getClass()){
			//false
			return false;
		}
		//类型转换
		ReduceCustomer other = (ReduceCustomer) obj;
		//空
		if (custNumber == null) {
			//不空
			if (other.custNumber != null){
				//false
				return false;
			}
			//不等
		} else if (!custNumber.equals(other.custNumber)){
			//false
			return false;
		}
		return true;
	}
}
