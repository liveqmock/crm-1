package com.deppon.crm.module.customer.server.service.impl;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.server.dao.IWaybillIntegralDao;
import com.deppon.crm.module.customer.server.service.IWaybillIntegralService;
import com.deppon.crm.module.customer.shared.domain.integral.WaybillIntegral;
/**
 * 
 * <p>
 * Description:WaybillIntegralService<br />
 * </p>
 * @title WaybillIntegralService.java
 * @package com.deppon.crm.module.customer.server.service.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class WaybillIntegralService implements IWaybillIntegralService{
	
	/**
	 * dao
	 */
	private IWaybillIntegralDao waybillIntegralDao;
	/**
	 * 
	 * <p>
	 * Description:waybillIntegralDao<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param waybillIntegralDao
	 * void
	 */
	public void setWaybillIntegralDao(IWaybillIntegralDao waybillIntegralDao) {
		this.waybillIntegralDao = waybillIntegralDao;
	}
	/**
	 * <p>
	 * Description:得到当前时间积分日期 的积分运单信息<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-2-27
	 * @return
	 * List<WaybillIntegral>
	 */
	@Override
	public List<WaybillIntegral> getNeedDisPoseWaybills() {
		WaybillIntegral waybillIntegral = new WaybillIntegral();
		waybillIntegral.setIntegDate(new Date());
		return waybillIntegralDao.searchWaybillIntegrals(waybillIntegral, 0, 10000000);
	}
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
	@Override
	public List<WaybillIntegral> searchWaybillIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		return waybillIntegralDao.searchWaybillIntegralForContactId(contactIdList, start, limit);
	}
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
	@Override
	public long countSearchWaybillIntegralForContactId(
			List<String> contactIdList) {
		return waybillIntegralDao.countSearchWaybillIntegralForContactId(contactIdList);
	}

}
