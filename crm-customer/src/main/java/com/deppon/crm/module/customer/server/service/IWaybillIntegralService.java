package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
/**
 * 
 * <p>
 * Description:积分运单<br />
 * </p>
 * @title IWaybillIntegralService.java
 * @package com.deppon.crm.module.customer.server.service 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IWaybillIntegralService {
	
	/**
	 * <p>
	 * Description:得到当前时间积分日期 的积分运单信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @return
	 * List<WaybillIntegral>
	 */
	public List<WaybillIntegral> getNeedDisPoseWaybills();
	
	/**
	 * <p>
	 * Description:通过联系人ID 分页查询 已积分运单信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return
	 * List<WaybillIntegral>
	 */
	public List<WaybillIntegral> searchWaybillIntegralForContactId(List<String> contactIdList, int start, int limit);

	/**
	 * <p>
	 * Description:通过联系人ID 统计 已积分运单的数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @return
	 * long
	 */
	public long countSearchWaybillIntegralForContactId(List<String> contactIdList);

}
