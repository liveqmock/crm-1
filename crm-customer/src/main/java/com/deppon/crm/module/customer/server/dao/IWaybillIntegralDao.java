package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;

public interface IWaybillIntegralDao {
	
	/**
	 * <p>
	 * Description:通过积分运单信息 统计其数量<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param waybillIntegral
	 * @return long
	 */
	public long countSearchWaybillIntegrals(WaybillIntegral waybillIntegral);
	
	/**
	 * <p>
	 * Description:已积分运单条件 分页查询  积分运单信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param waybillIntegral
	 * @param start
	 * @param limit
	 * @return List<WaybillIntegral>
	 */
	public List<WaybillIntegral> searchWaybillIntegrals(WaybillIntegral waybillIntegral,int start,int limit);
	
	/**
	 * <p>
	 * Description:通过联系人ID 分页查询 已积分运单信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param contactIdList
	 * @param start
	 * @param limit
	 * @return List<WaybillIntegral>
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

	/**
	 * <p>
	 * Description:通过ID查询积分运单信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @param id
	 * @return WaybillIntegral
	 */
	public WaybillIntegral getWaybillIntegralById(String id);
	
}
