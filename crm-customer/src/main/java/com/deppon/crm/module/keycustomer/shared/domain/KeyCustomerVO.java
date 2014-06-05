package com.deppon.crm.module.keycustomer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:大客户信息<br />
 * </p>
 * @title KeyCustomerVO.java
 * @package com.deppon.crm.module.keycustomer.shared.domain 
 * @author 106138
 * @version 0.1 2014-3-7
 */ 
public class KeyCustomerVO extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -8221771957081563885L;
	//客户id
	private String custId;
	//成为大客户时间
	private Date beginKeyCustomerTime;
	//是否特殊申请 
	private Boolean isSpecialCustomer;
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
	 * Description:beginKeyCustomerTime<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Date getBeginKeyCustomerTime() {
		return beginKeyCustomerTime;
	}
	/**
	 * <p>
	 * Description:beginKeyCustomerTime<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setBeginKeyCustomerTime(Date beginKeyCustomerTime) {
		this.beginKeyCustomerTime = beginKeyCustomerTime;
	}
	/**
	 * <p>
	 * Description:isSpecialCustomer<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public Boolean getIsSpecialCustomer() {
		return isSpecialCustomer;
	}
	/**
	 * <p>
	 * Description:isSpecialCustomer<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 */
	public void setIsSpecialCustomer(Boolean isSpecialCustomer) {
		this.isSpecialCustomer = isSpecialCustomer;
	}
	

}
