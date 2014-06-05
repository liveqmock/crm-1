/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPaymentService.java
 * @package com.deppon.crm.module.recompense.server.service 
 * @author zouming
 * @version 0.1 2013-1-26上午11:16:03
 */
package com.deppon.crm.module.recompense.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IPaymentService.java
 * @package com.deppon.crm.module.recompense.server.service 
 * @author zouming
 * @version 0.1 2013-1-26上午11:16:03
 */

public interface IPaymentService {
	
	/**
	 * 
	 * <p>
	 * Description:根据名字模糊匹配查询开户银行信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-26上午11:17:18
	 * @param name
	 * @return
	 * List<AccountBank>
	 * @update 2013-1-26上午11:17:18
	 */
	List<AccountBank> searchBankListByName(String name);
}
