/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360Service.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author Administrator
 * @version 0.1 2012-4-24
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IProviderFor360Dao;
import com.deppon.crm.module.marketing.server.service.IProviderFor360Service;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ProviderFor360Service.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */

public class ProviderFor360Service implements IProviderFor360Service {
	//360视图接口操作
	private IProviderFor360Dao providerFor360Dao;
	/**
	 * @return providerFor360Dao : return the property providerFor360Dao.
	 */
	public IProviderFor360Dao getProviderFor360Dao() {
		return providerFor360Dao;
	}

	/**
	 * @param providerFor360Dao : set the property providerFor360Dao.
	 */
	public void setProviderFor360Dao(IProviderFor360Dao providerFor360Dao) {
		this.providerFor360Dao = providerFor360Dao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IProviderFor360Service#getLastestMaintainInfo(java.lang.String)
	 */
	@Override
	public ReturnVisit getLastestMaintainInfo(String custId) {
		return providerFor360Dao.getLastestMaintainInfo(custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IProviderFor360Service#getPlanScheduleDetail(java.lang.String)
	 */
	@Override
	public List<ReturnVisit> getPlanScheduleDetail(String custId) {
		return providerFor360Dao.getPlanScheduleDetail(custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.marketing.server.service.IProviderFor360Service#getVisitRecords(java.lang.String)
	 */
	@Override
	public List<ReturnVisit> getVisitRecords(String custId) {
		return providerFor360Dao.getVisitRecords(custId);
	}

}
