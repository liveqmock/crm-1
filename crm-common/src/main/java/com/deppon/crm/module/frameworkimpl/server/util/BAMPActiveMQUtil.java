/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ActiveMQCRMUtil.java
 * @package com.deppon.crm.module.common.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.util;

import com.deppon.ar.bamp.client.jms.ActiveMQUtil;
import com.deppon.crm.module.frameworkimpl.server.service.IPropInfoService;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BAMPActiveMQUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class BAMPActiveMQUtil extends ActiveMQUtil {
	private IPropInfoService propInfoService;

	public void initActiveMQ(){
		String url = (String) PropInfoUtil.getValue(propInfoService, "brokerUrl");
		super.setBrokerUrl(url);
		super.startConnectionFactory();
	}
	
	public IPropInfoService getPropInfoService() {
		return propInfoService;
	}
	public void setPropInfoService(IPropInfoService propInfoService) {
		this.propInfoService = propInfoService;
	}

}
