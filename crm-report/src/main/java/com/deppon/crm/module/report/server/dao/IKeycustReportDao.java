package com.deppon.crm.module.report.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.SingleProductAmount;
import com.deppon.crm.module.report.shared.domain.SingleRoadAmount;
import com.deppon.crm.module.report.shared.domain.SingleShipmentAging;
import com.deppon.crm.module.report.shared.domain.SingleShipmentQuality;
/**
 * 大客户走货报告dao层接口
 * @author yw
 *
 */
public interface IKeycustReportDao {
	/**
	 * 近三个月走货量
	 * @param custNum
	 * @author yw 
	 * @return List<ShipmentAmount>
	 */
	List<ShipmentAmount> queryShipmentAmount(String custNum);
	/**
	 * 近三个月产品货量
	 * @param custNum
	 * @author yw 
	 * @return List<SingleProductAmount>
	 */
	List<SingleProductAmount> queryProductAmount(String custNum);
	/**
	 * 近三个月产品货量
	 * @param custNum
	 * @author yw
	 * @return List<SingleRoadAmount>
	 */
	List<SingleRoadAmount> queryRoadAmount(String custNum);
	/**
	 * 近三个月走货时效
	 * 
	 * @param custNum
	 * @author yw
	 * @return List<SingleShipmentAging>
	 */
	List<SingleShipmentAging> queryShipmentAging(String custNum);

	/**
	 * 近三个月走货质量
	 * @author yw
	 * @param custNum
	 * @return List<SingleShipmentQuality>
	 */
	List<SingleShipmentQuality> queryShipmentQuality(Map<String, String> Map);
}
