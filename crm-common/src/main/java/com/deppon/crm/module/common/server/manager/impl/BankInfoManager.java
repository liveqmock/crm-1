/**   
 * Description:这里写描述<br />
 * @title AreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.deppon.crm.module.common.server.cache.CityCacheDeal;
import com.deppon.crm.module.common.server.manager.IAreaAddressManager;
import com.deppon.crm.module.common.server.manager.IBankInfoManager;
import com.deppon.crm.module.common.server.service.IAreaAddressService;
import com.deppon.crm.module.common.server.service.IBankInfoService;

import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BankView;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankInfoManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author ouy
 * @version 0.1 2012-3-31
 */
public class BankInfoManager implements IBankInfoManager {
	private IBankInfoService bankInfoService;
	@Override
	public List<BankView> getBankInfoByBV(BankView bv) {
		return bankInfoService.getBankInfoByBV(bv);
	}
	public IBankInfoService getBankInfoService() {
		return bankInfoService;
	}
	public void setBankInfoService(IBankInfoService bankInfoService) {
		this.bankInfoService = bankInfoService;
	}
}