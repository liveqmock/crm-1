/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title IProviderFor360Service.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author Administrator
 * @version 0.1 2012-4-24
 */
package com.deppon.crm.module.marketing.server.service;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;

/**   
 * <p>
 * 给360视图提供接口操作<br />
 * </p>
 * @title IProviderFor360Service.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */

public interface IProviderFor360Service {

	/**
	 * <p>
	 * 查询客户最后一次维护信息<br />
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
	 * 查询客户的计划日程信息<br />
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
	 * 查询客户回访记录<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * List<ReturnVisit>
	 */
	 List<ReturnVisit> getVisitRecords(String custId);

}
