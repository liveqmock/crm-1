package com.deppon.crm.module.report.server.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;


import com.deppon.crm.module.report.shared.domain.ProductAmount;
import com.deppon.crm.module.report.shared.domain.RoadAmount;
import com.deppon.crm.module.report.shared.domain.ShipmentAging;
import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.ShipmentQuality;
import com.deppon.crm.module.report.shared.domain.ShipmentQualityForChart;
import com.deppon.crm.module.report.shared.domain.SingleShipmentAging;
/**
 *	大客户走货报告manager层接口 
 * @author yw
 */
public interface IKeycustReportManager {
	/**
	 * 近三个月走货量
	 */
	List<ShipmentAmount> queryShipmentAmount(String custNum);
	/**
	 *近三个月产品货量 
	 * @param custNum 
	 */
	List<ProductAmount> queryProductAmount(String custNum);
	/**
	 *近三个月线路货量 
	 * @param custNum 
	 */
	List<RoadAmount> queryRoadAmount(String custNum);
	/**
	 * 近三个月走货时效
	 * @param custNum
	 * @return
	 */
	List<ShipmentAging> queryShipmentAging(String custNum);
	/**
	 * 近三个月走货质量
	 * @param custNum
	 * @return
	 */
	List<ShipmentQuality> queryShipmentQuality(String custNum);
	/**
	 * 近三个月走货质量(得到图表数据模式)
	 * @param custNum
	 * @return
	 */
	List<ShipmentQualityForChart> queryShipmentQualityForChart(String custNum);
	//校验客户编码,是否可以进行报表查询
	void checkCustNum(String custNum);
	/**
	 * 导出excel
	 * @param custNum
	 * @param isAddMarketing 
	 * @return
	 *description:此方法是用于导出excel,后面用导出doc文档代替了 所以注释掉
	 */
//	public FileInputStream exportKeyCustomerExcel(String custNum, Boolean isAddMarketing);
	FileInputStream exportKeyCustomerWord(String custNum,boolean isAddMarketing);
}
