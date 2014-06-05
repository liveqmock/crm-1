/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPaymentDao.java
 * @package com.deppon.crm.module.recompense.server.dao.impl 
 * @author zouming
 * @version 0.1 2013-1-26上午11:13:51
 */
package com.deppon.crm.module.recompense.server.dao;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPaymentDao.java
 * @package com.deppon.crm.module.recompense.server.dao.impl 
 * @author zouming
 * @version 0.1 2013-1-26上午11:13:51
 */

public interface IPaymentDao {
	/**
	 * 
	 * <p>
	 * Description:模糊匹配查询开户银行信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-26上午11:14:11
	 * @param name
	 * @return
	 * List<AccountBank>
	 * @update 2013-1-26上午11:14:11
	 */
	List<AccountBank> searchBankListByName(String name);
}
