/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BAMPLogSwitchUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */
package com.deppon.crm.module.frameworkimpl.server.util;

import com.deppon.ar.bamp.common.dispatch.switchs.LogSwitch;
import com.deppon.crm.module.frameworkimpl.server.service.IPropInfoService;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BAMPLogSwitchUtil.java
 * @package com.deppon.crm.module.frameworkimpl.server.util 
 * @author ZhuPJ
 * @version 0.1 2013-12-7
 */

public class BAMPLogSwitchUtil extends LogSwitch {	
	private LogSwitch logSwitch;
	private IPropInfoService propInfoService;
	
	/**
	 * <p>
	 * Description: 初始化方法，将开关配置项设置到LogSwitch中。坑爹的代码<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2013-12-18 void
	 */
	public void initLogSwitch() {
		Integer lswitch = Integer.valueOf(PropInfoUtil.getValue(propInfoService, "localReqLogSwitch"));
		logSwitch.setLocalReqLogSwitch(lswitch);
	}
	
	public IPropInfoService getPropInfoService() {
		return propInfoService;
	}
	
	public void setPropInfoService(IPropInfoService propInfoService) {
		this.propInfoService = propInfoService;
	}
	
	public LogSwitch getLogSwitch() {
		return logSwitch;
	}

	public void setLogSwitch(LogSwitch logSwitch) {
		this.logSwitch = logSwitch;
	}
}
