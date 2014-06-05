package com.deppon.crm.module.scheduler.server.manager;


import java.util.List;

import com.deppon.crm.module.customer.server.manager.IWaybillIntegralManager;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;

/**
 * 
 * <p>
 * 积分运单的定时器<br />
 * </p>
 * @title TimerWaybillIntegral.java
 * @package com.deppon.crm.module.scheduler.server.manager 
 * @author bxj
 * @version 0.2 2012-6-8
 */
public class TimerWaybillIntegral {
	
	private IWaybillIntegralManager waybillIntegralManager ;

	public void setWaybillIntegralManager(
			IWaybillIntegralManager waybillIntegralManager) {
		this.waybillIntegralManager = waybillIntegralManager;
	}
	
	public void timerWaybillIntegral(){
		List<WaybillIntegral> list = waybillIntegralManager.getNeedDisPoseWaybills();
		waybillIntegralManager.disPoseWaybillIntegral(list);
		
	}
	
	
	
	
}
