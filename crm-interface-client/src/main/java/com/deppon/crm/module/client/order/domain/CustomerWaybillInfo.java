/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerWaybillInfo.java
 * @package com.deppon.crm.module.client.order.domain
 * @author 105681
 * @version 0.1 2014-4-15
 */
package com.deppon.crm.module.client.order.domain;

import java.util.ArrayList;
import java.util.List;

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @title CustomerWaybillInfo.java
	 * @package com.deppon.crm.module.client.order.domain
	 * @author 105681
	 * @version 0.1 2014-4-15
	 */

public class CustomerWaybillInfo {

	private List<CustomerWaybill> customerWaybills;

	private int totalNum;

	public List<CustomerWaybill> getCustomerWaybill() {

		if (customerWaybills == null) {
			customerWaybills = new ArrayList<CustomerWaybill>();
        }
        return this.customerWaybills;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

}
