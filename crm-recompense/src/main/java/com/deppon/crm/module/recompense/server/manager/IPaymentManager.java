/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPaymentManager.java
 * @package com.deppon.crm.module.recompense.server.manager 
 * @author zouming
 * @version 0.1 2013-1-26上午11:12:17
 */
package com.deppon.crm.module.recompense.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPaymentManager.java
 * @package com.deppon.crm.module.recompense.server.manager 
 * @author zouming
 * @version 0.1 2013-1-26上午11:12:17
 */

public interface IPaymentManager {
	/**
	 * 
	 * <p>
	 * Description:模糊匹配查询开户银行信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-26上午11:19:53
	 * @param name
	 * @return
	 * List<AccountBank>
	 * @update 2013-1-26上午11:19:53
	 */
	List<AccountBank> searchBankListByName(String name);

}
