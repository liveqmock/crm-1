package com.deppon.crm.module.marketing.server.manager;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;

/**   
 * <p>
 * 客户360视图管理提供的接口<br />
 * </p>
 * @title ProviderFor360Manager.java
 * @package com.deppon.crm.module.marketing.server.manager 
 * @author 苏玉军
 * @version 0.1 2012-4-24
 */
public interface IProviderFor360Manager {
	/**
	 * <p>
	 * 根据客户ID获得客户最近一次客户维护信息（客户维护回访信息）<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return List<ReturnVisit>
	 */
	 ReturnVisit getLastestMaintainInfo(String custId);
	
	/**
	 * <p>
	 * 根据客户id获得客户开发/日程综合信息列表<br />
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
	 * 根据客户编码获得客户维护综合信息列表（即客户维护回访信息）<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2012-4-24
	 * @param custId
	 * @return
	 * List<ReturnVisit>
	 */
	 List<ReturnVisit> getVisitRecords(String custId);
}
