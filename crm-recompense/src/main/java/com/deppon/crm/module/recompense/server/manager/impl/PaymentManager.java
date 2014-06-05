/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PaymentManager.java
 * @package com.deppon.crm.module.recompense.server.manager 
 * @author zouming
 * @version 0.1 2013-1-26上午11:11:05
 */
package com.deppon.crm.module.recompense.server.manager.impl;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.recompense.server.manager.IPaymentManager;
import com.deppon.crm.module.recompense.server.service.IPaymentService;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PaymentManager.java
 * @package com.deppon.crm.module.recompense.server.manager 
 * @author zouming
 * @version 0.1 2013-1-26上午11:11:05
 */

public class PaymentManager implements IPaymentManager{
	private IPaymentService paymentService;
	
	/**
	 *@return  paymentService;
	 */
	public IPaymentService getPaymentService() {
		return paymentService;
	}

	/**
	 * @param paymentService : set the property paymentService.
	 */
	public void setPaymentService(IPaymentService paymentService) {
		this.paymentService = paymentService;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-26上午11:20:20
	 * @param name
	 * @return
	 * @update 2013-1-26上午11:20:20
	 */
	@Override
	public List<AccountBank> searchBankListByName(String name) {
		return paymentService.searchBankListByName(name);
	}
}
