/**   
 * Description:这里写描述<br />
 * @title IAreaAddressService.java
 * @package com.deppon.crm.module.common.server.service.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.BankView;
import com.deppon.foss.framework.service.IService;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IBankInfoService.java
 * @package com.deppon.crm.module.common.server.service 
 * @author ouy
 * @version 0.1 2012-3-31
 */

public interface IBankInfoService extends IService {
	public List<BankView> getBankInfoByBV(BankView bv);
}
