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

import com.deppon.crm.module.common.server.dao.IAccountBranchDao;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankView;
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

public class AccountBranchDao extends iBatis3DaoImpl implements IAccountBranchDao {

	@Override
	public List<BankView> getAccountBranchs(BankView bv) {
		List<BankView> list = null;
		list =  this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.AccountBranch.getBankInfo",bv);
		return list;
	}

	@Override
	public List<AccountBranch> getAccountBranchById(Integer id) {
		List<AccountBranch> abList=this.getSqlSession().selectList("com.deppon.crm.module.common.shared.domain.AccountBranch.getAccountBranchById",id);
		return abList;
	}
}
