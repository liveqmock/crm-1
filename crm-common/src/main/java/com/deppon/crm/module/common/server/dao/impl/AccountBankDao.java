/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AreaDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IAccountBankDao;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AccountBankDao.java
 * @package com.deppon.crm.module.common.server.dao.impl 
 * @author ouy
 * @version 0.1 2012-3-30
 */

public class AccountBankDao extends iBatis3DaoImpl implements IAccountBankDao {

	@Override
	public List<AccountBank> getAccountBank() {
		// TODO Auto-generated method stub
		return null;
	}
}
