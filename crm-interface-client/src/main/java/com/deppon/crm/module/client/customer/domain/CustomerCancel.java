package com.deppon.crm.module.client.customer.domain;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCancel.java
 * @package com.deppon.crm.module.client.customer.domain 
 * @author wugenbin_吴根斌
 * @version 0.1 2013-5-9
 */
public class CustomerCancel {

	private String customerCoder;
	public String getCustomerCoder() {
		return customerCoder;
	}
	public void setCustomerCoder(String customerCoder) {
		this.customerCoder = customerCoder;
	}
	public boolean isCustomerBlankOut() {
		return isCustomerBlankOut;
	}
	public void setCustomerBlankOut(boolean isCustomerBlankOut) {
		this.isCustomerBlankOut = isCustomerBlankOut;
	}
	private boolean isCustomerBlankOut;
	
}
