/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title NeedMaintainCustomer.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 17, 2012
 */
package com.deppon.crm.module.marketing.shared.domain;

/**   
 * <p>
 * Description:客户维护定时器POJO类<br />
 * 以customerNumber为hashCode和equals判断基准
 * </p>
 * @title NeedMaintainCustomer.java
 * @package com.deppon.crm.module.marketing.shared.domain 
 * @author zhujunyong
 * @version 0.1 Apr 17, 2012
 */

public class NeedMaintainCustomer {

	// 部门ID
	private String deptId;
	// 客户编码
	private String custNumber;
	// 客户ID
	private String custId;
	// 主要联系人ID
	private String contactId;
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
		//计算HASHzhi
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
		//当前对象等于obj
		if (this == obj){
			//true
			return true;
		}
		//参数为null
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
		NeedMaintainCustomer other = (NeedMaintainCustomer) obj;
		//客户编码为空
		if (custNumber == null) {
			//不为空
			if (other.custNumber != null){
				//不等
				return false;
			}
		} else if (!custNumber.equals(other.custNumber)){
			return false;
		}
		return true;
	}

	
}
