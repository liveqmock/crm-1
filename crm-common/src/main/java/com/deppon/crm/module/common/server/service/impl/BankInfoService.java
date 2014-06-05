/**   
 * Description:这里写描述<br />
 * @title AreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IAccountBranchDao;
import com.deppon.crm.module.common.server.dao.IAreaDao;
import com.deppon.crm.module.common.server.service.IAreaAddressService;
import com.deppon.crm.module.common.server.service.IBankInfoService;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BankView;

/**   
 * Description:这里写描述<br />
 * @title AreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */

public class BankInfoService implements IBankInfoService{

	//注入accountBranchDao
	private IAccountBranchDao accountBranchDao;

	@Override
	public List<BankView> getBankInfoByBV(BankView bv) {
		// TODO Auto-generated method stub
		List<BankView> bankViewList=accountBranchDao.getAccountBranchs(bv);
		return bankViewList;
	}

	public IAccountBranchDao getAccountBranchDao() {
		return accountBranchDao;
	}

	public void setAccountBranchDao(IAccountBranchDao accountBranchDao) {
		this.accountBranchDao = accountBranchDao;
	}

}
