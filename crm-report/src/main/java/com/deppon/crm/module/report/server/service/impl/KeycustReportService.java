package com.deppon.crm.module.report.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.report.server.dao.IKeycustReportDao;
import com.deppon.crm.module.report.server.service.IKeycustReportService;
import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.SingleProductAmount;
import com.deppon.crm.module.report.shared.domain.SingleRoadAmount;
import com.deppon.crm.module.report.shared.domain.SingleShipmentAging;
import com.deppon.crm.module.report.shared.domain.SingleShipmentQuality;
/**
 *大客户走货报告service层实现类
 */
public class KeycustReportService implements IKeycustReportService {
	private IKeycustReportDao keycustReportDao;

	/**
	 * 近三个月走货量
	 */
	@Override
	public List<ShipmentAmount> queryShipmentAmount(String custNum) {
		return keycustReportDao.queryShipmentAmount(custNum);
	}

	/**
	 * 近三个月产品货量
	 */
	@Override
	public List<SingleProductAmount> queryProductAmount(String custNum) {
		return keycustReportDao.queryProductAmount(custNum);
	}

	/**
	 * 近三个月线路货量
	 */
	@Override
	public List<SingleRoadAmount> queryRoadAmount(String custNum) {
		return keycustReportDao.queryRoadAmount(custNum);
	}

	/**
	 * 近三个月走货时效
	 */
	@Override
	public List<SingleShipmentAging> queryShipmentAging(String custNum) {
		return keycustReportDao.queryShipmentAging(custNum);
	}

	/**
	 * 近三个月走货质量
	 */
	@Override
	public List<SingleShipmentQuality> queryShipmentQuality(
			Map<String, String> map) {
		return keycustReportDao.queryShipmentQuality(map);
	}
	/**
	 *dao层的set方法 
	 */
	public void setKeycustReportDao(IKeycustReportDao keycustReportDao) {
		this.keycustReportDao = keycustReportDao;
	}
}
