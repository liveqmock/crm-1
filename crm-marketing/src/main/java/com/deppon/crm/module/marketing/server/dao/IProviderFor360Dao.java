package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;

/**   
 * <p>
 * 提供给360视图模块的接口<br />
 * </p>
 * @title IProviderFor360Dao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */

public interface IProviderFor360Dao {

	/**
	 * <p>
	 * 查询最后一次回访信息<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * ReturnVisit
	 */
	 ReturnVisit getLastestMaintainInfo(String custId);

	/**
	 * <p>
	 * 查询计划明细<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * List<ReturnVisit>
	 */
	 List<ReturnVisit> getPlanScheduleDetail(String custId);

	/**
	 * <p>
	 * 查询回访记录<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * List<ReturnVisit>
	 */
	 List<ReturnVisit> getVisitRecords(String custId);

}
