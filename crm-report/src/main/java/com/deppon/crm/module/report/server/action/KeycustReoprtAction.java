package com.deppon.crm.module.report.server.action;

import java.io.FileInputStream;
import java.util.List;


import com.deppon.crm.module.report.server.manager.IKeycustReportManager;
import com.deppon.crm.module.report.shared.domain.ProductAmount;
import com.deppon.crm.module.report.shared.domain.RoadAmount;
import com.deppon.crm.module.report.shared.domain.ShipmentAging;
import com.deppon.crm.module.report.shared.domain.ShipmentAmount;
import com.deppon.crm.module.report.shared.domain.ShipmentQuality;
import com.deppon.crm.module.report.shared.domain.ShipmentQualityForChart;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * Description 大客户和客户信用报表action层
 * @author yw
 */
public class KeycustReoprtAction extends AbstractAction {
	//客户编码
	private String custNum;
	//大客户走货报告manager
	private IKeycustReportManager keycustReportManager;
	//走货金额集合
	private List<ShipmentAmount> shipmentAmountList;
	//产品货量集合
	private List<ProductAmount> productAmountList;
	//线路货量集合
	private List<RoadAmount> roadAmountList;
	//走货时效集合
	private List<ShipmentAging> shipmentAgingList;
	//走货质量集合
	private List<ShipmentQuality> shipmentQualityList;
	//走货质量(图表所需数据模式)集合
	private List<ShipmentQualityForChart> shipmentQualityForChartList;
	//文件流
	private FileInputStream stream;
	//文件名字
	private String fileName;
	//是否添加营销标语
	private Boolean isAddMarketing = false;
	/**
	 * Description 大客户走货报告文件下载
	 * @author yw
	 */
	@JSON
	public String downReportFile() {
		stream = keycustReportManager.exportKeyCustomerWord(custNum,
				isAddMarketing);
		fileName = custNum + ".doc";
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告查询走货数量
	 * @author yw
	 */
	@JSON
	public String queryShipmentAmount() {
		shipmentAmountList = keycustReportManager.queryShipmentAmount(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告查询产品货量
	 * @author yw
	 */
	@JSON
	public String queryProductAmount() {
		productAmountList = keycustReportManager.queryProductAmount(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告查询线路货量
	 * @author yw
	 */
	@JSON
	public String queryRoadAmount() {
		roadAmountList = keycustReportManager.queryRoadAmount(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告走货时效
	 * @author yw
	 */
	@JSON
	public String queryShipmentAging() {
		shipmentAgingList = keycustReportManager.queryShipmentAging(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告走货质量
	 * @author yw
	 */
	@JSON
	public String queryShipmentQuality() {
		shipmentQualityList = keycustReportManager
				.queryShipmentQuality(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告走货质量(是图表所需要的数据模式)
	 * @author yw
	 */
	@JSON
	public String queryShipmentQualityForChart() {
		shipmentQualityForChartList = keycustReportManager
				.queryShipmentQualityForChart(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告检验运单号
	 * @author yw
	 */
	@JSON
	public String checkCustNum() {
		keycustReportManager.checkCustNum(custNum);
		return SUCCESS;
	}
	/**
	 * Description 大客户走货报告走货质量get方法
	 * @author yw
	 */
	public List<ShipmentQuality> getShipmentQualityList() {
		return shipmentQualityList;
	}
	/**
	 * Description 大客户走货报告走货数量get方法
	 * @author yw
	 */
	public List<ShipmentAmount> getShipmentAmountList() {
		return shipmentAmountList;
	}
	/**
	 * Description 大客户走货报告 manager层set方法
	 * @author yw
	 */
	public void setKeycustReportManager(
			IKeycustReportManager keycustReportManager) {
		this.keycustReportManager = keycustReportManager;
	}
	/**
	 * Description 大客户走货报告 客户编码set方法
	 * @author yw
	 */
	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	/**
	 * Description 大客户走货报告 产品货量get方法
	 * @author yw
	 */
	public List<ProductAmount> getProductAmountList() {
		return productAmountList;
	}
	/**
	 * Description 大客户走货报告 线路货量get方法
	 * @author yw
	 */
	public List<RoadAmount> getRoadAmountList() {
		return roadAmountList;
	}
	/**
	 * Description 大客户走货报告 走货时效get方法
	 * @author yw
	 */
	public List<ShipmentAging> getShipmentAgingList() {
		return shipmentAgingList;
	}
	/**
	 * Description 大客户走货报告 走货时效(图表所需模式)get方法
	 * @author yw
	 */
	public List<ShipmentQualityForChart> getShipmentQualityForChartList() {
		return shipmentQualityForChartList;
	}
	/**
	 * Description 大客户走货报告文件下载 文件名字get方法
	 * @author yw
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * Description 大客户走货报告 文件下载文件流get方法
	 * @author yw
	 */
	public FileInputStream getStream() {
		return stream;
	}
	/**
	 * Description 大客户走货报告 是否添加营销标语set方法
	 * @author yw
	 */
	public void setIsAddMarketing(Boolean isAddMarketing) {
		this.isAddMarketing = isAddMarketing;
	}

}
