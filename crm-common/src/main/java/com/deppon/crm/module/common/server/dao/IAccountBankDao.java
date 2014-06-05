/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IAreaDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IAccountBankDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ouy
 * @version 0.1 2012-3-30
 */

public interface IAccountBankDao {
	List<AccountBank> getAccountBank();
}
