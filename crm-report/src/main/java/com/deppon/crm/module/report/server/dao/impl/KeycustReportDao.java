package com.deppon.crm.module.report.server.dao.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.report.server.dao.IKeycustReportDao;
import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.SingleProductAmount;
import com.deppon.crm.module.report.shared.domain.SingleRoadAmount;
import com.deppon.crm.module.report.shared.domain.SingleShipmentAging;
import com.deppon.crm.module.report.shared.domain.SingleShipmentQuality;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 大客户走货报告dao层实现类
 * @author yw
 *
 */
public class KeycustReportDao extends iBatis3DaoImpl implements
		IKeycustReportDao {
	private static final String NAMESPACE = "com.deppon.crm.module.report.shared.domain.KeycustDelivery.";
	/**
	 * 近三个月走货量
	 * @param custNum
	 * @author yw 
	 * @return List<ShipmentAmount>
	 */
	@Override
	public List<ShipmentAmount> queryShipmentAmount(String custNum) {
		return this.getSqlSession().selectList(
				NAMESPACE + "selectShipmentAmount", custNum);
	}
	/**
	 * 近三个月产品货量
	 * @param custNum
	 * @author yw 
	 * @return List<SingleProductAmount>
	 */
	@Override
	public List<SingleProductAmount> queryProductAmount(String custNum) {
		return this.getSqlSession().selectList(
				NAMESPACE + "selectProductAmount", custNum);
	}
	/**
	 * 近三个月产品货量
	 * @param custNum
	 * @author yw
	 * @return List<SingleRoadAmount>
	 */
	@Override
	public List<SingleRoadAmount> queryRoadAmount(String custNum) {
		return this.getSqlSession().selectList(NAMESPACE + "selectRoadAmount",
				custNum);
	}
	/**
	 * 近三个月走货时效
	 * 
	 * @param custNum
	 * @author yw
	 * @return List<SingleShipmentAging>
	 */
	@Override
	public List<SingleShipmentAging> queryShipmentAging(String custNum) {
		return this.getSqlSession().selectList(
				NAMESPACE + "selectShipmentAging", custNum);
	}
	/**
	 * 近三个月走货质量
	 * @author yw
	 * @param custNum
	 * @return List<SingleShipmentQuality>
	 */
	@Override
	public List<SingleShipmentQuality> queryShipmentQuality(
			Map<String, String> map) {
		return this.getSqlSession().selectList(
				NAMESPACE + "selectShipmentQuality", map);
	}
}
