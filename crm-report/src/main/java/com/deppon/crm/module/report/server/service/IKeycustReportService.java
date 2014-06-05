package com.deppon.crm.module.report.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.SingleProductAmount;
import com.deppon.crm.module.report.shared.domain.SingleRoadAmount;
import com.deppon.crm.module.report.shared.domain.SingleShipmentAging;
import com.deppon.crm.module.report.shared.domain.SingleShipmentQuality;
/**
 *大客户走货报告service层接口
 */
public interface IKeycustReportService {
	/**
	 * 近三个月走货量
	 */
	List<ShipmentAmount> queryShipmentAmount(String custNum);

	/**
	 * 近三个月产品货量
	 * 
	 * @param custNum
	 */
	List<SingleProductAmount> queryProductAmount(String custNum);

	/**
	 * 近三个月线路货量
	 * 
	 * @param custNum
	 */
	List<SingleRoadAmount> queryRoadAmount(String custNum);

	/**
	 * 近三个月走货时效
	 * 
	 * @param custNum
	 * @return
	 */
	List<SingleShipmentAging> queryShipmentAging(String custNum);

	/**
	 * 近三个月走货质量
	 * 
	 * @param map
	 */
	List<SingleShipmentQuality> queryShipmentQuality(Map<String, String> map);
}
