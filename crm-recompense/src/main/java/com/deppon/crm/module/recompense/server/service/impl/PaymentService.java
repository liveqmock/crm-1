/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PaymentService.java
 * @package com.deppon.crm.module.recompense.server.service.impl 
 * @author zouming
 * @version 0.1 2013-1-26上午11:16:19
 */
package com.deppon.crm.module.recompense.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.recompense.server.dao.IPaymentDao;
import com.deppon.crm.module.recompense.server.service.IPaymentService;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PaymentService.java
 * @package com.deppon.crm.module.recompense.server.service.impl 
 * @author zouming
 * @version 0.1 2013-1-26上午11:16:19
 */

public class PaymentService implements IPaymentService{
	
	private IPaymentDao paymentDao;
	
	/**
	 *@return  paymentDao;
	 */
	public IPaymentDao getPaymentDao() {
		return paymentDao;
	}

	/**
	 * @param paymentDao : set the property paymentDao.
	 */
	public void setPaymentDao(IPaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	/**
	 * <p>
	 * Description:根据名字模糊匹配查询开户银行信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-26上午11:17:57
	 * @param name
	 * @return
	 * @update 2013-1-26上午11:17:57
	 */
	@Override
	public List<AccountBank> searchBankListByName(String name) {
		return paymentDao.searchBankListByName(name);
	}
}
