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

import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankView;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IAccountBranchDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ouy
 * @version 0.1 2012-3-30
 */

public interface IAccountBranchDao {
	public List<BankView> getAccountBranchs(BankView bv);
	public List<AccountBranch> getAccountBranchById(Integer id);
}
