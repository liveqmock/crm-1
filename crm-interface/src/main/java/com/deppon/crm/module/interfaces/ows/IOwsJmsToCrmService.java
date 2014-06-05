/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IOwsJmsToCrmService.java
 * @package com.deppon.crm.module.interfaces.ows
 * @author 105681
 * @version 0.1 2014-4-14
 */
package com.deppon.crm.module.interfaces.ows;

import com.deppon.crm.module.interfaces.ows.domain.CommonExceptionInfo_Exception;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerRequest;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerResponse;
/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IOwsJmsToCrmService.java
 * @package com.deppon.crm.module.interfaces.ows
 * @author 105681
 * @version 0.1 2014-4-14
 */

public interface IOwsJmsToCrmService {
	/**
	 *
	 * <p>
	 * Description:创建官网用户<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param request
	 * @return
	 * @throws CommonExceptionInfo_Exception
	 * CreateOwsCustomerResponse
	 */
	public CreateOwsCustomerResponse createOwsCustomer(
			CreateOwsCustomerRequest request)
			throws CommonExceptionInfo_Exception;
}
