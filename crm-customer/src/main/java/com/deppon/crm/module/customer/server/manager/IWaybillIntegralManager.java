package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
/**
 * 
 * <p>
 * Description:运单积分<br />
 * </p>
 * @title IWaybillIntegralManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IWaybillIntegralManager {
	/**
	 * 
	 * <p>
	 * Description:获取需要失效的运单积分<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @return
	 * List<WaybillIntegral>
	 */
	public List<WaybillIntegral> getNeedDisPoseWaybills();
	/**
	 * 
	 * <p>
	 * Description:批量修改作废运单积分<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-2-2
	 * @param waybillIntegralList
	 * void
	 */
	public void disPoseWaybillIntegral(List<WaybillIntegral> waybillIntegralList);
}
