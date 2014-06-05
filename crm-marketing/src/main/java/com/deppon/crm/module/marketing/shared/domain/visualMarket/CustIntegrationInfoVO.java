/**
 * <p>
 * Description:客户标注弹出的客户信息浮窗中包含的内容<br />
 * </p>
 * @title CustIntegrationInfoVO.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
package com.deppon.crm.module.marketing.shared.domain.visualMarket;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;

/**
 * <p>
 * Description:客户标注弹出的客户信息浮窗中包含的内容<br />
 * </p>
 * @title CustIntegrationInfoVO.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-18
 */
public class CustIntegrationInfoVO {
	//客户基本信息
	private CustomerMarketInfo customerMarketInfo;
	//客户最近半年出发收入列表（固定客户）
	private List<CustMonthlyArriveIncome> arriveIncomeList;
	//回访信息列表（列表中最多存在10条）  ICustomerCallManager queryRvOptionByMarketingIds 传入客户类型（固定客户MEMBER）和客户id
	private List<ReturnVisitOpinionVo> rvOpinionList;
	/**
	 * @param customerMarketInfo the customerMarketInfo to get
	 */
	public CustomerMarketInfo getCustomerMarketInfo() {
		return customerMarketInfo;
	}
	/**
	 * @param customerMarketInfo the customerMarketInfo to set
	 */
	public void setCustomerMarketInfo(CustomerMarketInfo customerMarketInfo) {
		this.customerMarketInfo = customerMarketInfo;
	}
	/**
	 * @param arriveIncomeList the arriveIncomeList to get
	 */
	public List<CustMonthlyArriveIncome> getArriveIncomeList() {
		return arriveIncomeList;
	}
	/**
	 * @param arriveIncomeList the arriveIncomeList to set
	 */
	public void setArriveIncomeList(List<CustMonthlyArriveIncome> arriveIncomeList) {
		this.arriveIncomeList = arriveIncomeList;
	}
	/**
	 * @param rvOpinionList the rvOpinionList to get
	 */
	public List<ReturnVisitOpinionVo> getRvOpinionList() {
		return rvOpinionList;
	}
	/**
	 * @param rvOpinionList the rvOpinionList to set
	 */
	public void setRvOpinionList(List<ReturnVisitOpinionVo> rvOpinionList) {
		this.rvOpinionList = rvOpinionList;
	}
}
