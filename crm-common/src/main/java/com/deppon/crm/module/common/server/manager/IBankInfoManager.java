/**   
 * Description:这里写描述<br />
 * @title IAreaAddressManager.java
 * @package com.deppon.crm.module.common.server.manager.impl 
 * @author 毛建强
 * @version 0.1 2012-3-16
 */
package com.deppon.crm.module.common.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.BankView;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IBankInfoManager.java
 * @package com.deppon.crm.module.common.server.manager 
 * @author ouy
 * @version 0.1 2012-3-31
 */
public interface IBankInfoManager {
	public List<BankView> getBankInfoByBV(BankView bv);
}
