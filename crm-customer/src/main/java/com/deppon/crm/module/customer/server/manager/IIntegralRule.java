package com.deppon.crm.module.customer.server.manager;

import com.deppon.crm.module.customer.shared.domain.integral.IntegralOperation;

/**
 * 
 * <p>积分规则接口<br />
 * </p>
 * @title IIntegralRule.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author Administrator
 * @version 0.1 2012-4-19
 */
public interface IIntegralRule {
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * boolean
	 */
	public boolean isValidate();
	/**
	 * 把调出联系人的积分全部转移到主联系人积分上
	 */
	public IntegralOperation getBizIntegralOperation();

}
