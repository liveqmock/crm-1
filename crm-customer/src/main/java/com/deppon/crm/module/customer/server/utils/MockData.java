package com.deppon.crm.module.customer.server.utils;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.customer.shared.domain.Constant;

/**
 * 
 * <p>
 * Description:MockData<br />
 * </p>
 * 
 * @title MockData.java
 * @package com.deppon.crm.module.customer.server.utils
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class MockData {
	/**
	 * 
	 * <p>
	 * Description:获得mock数据<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return List<WaybillInfo>
	 */
	public static List<WaybillInfo> getMockData() {
		/**
		 * 新建运单信息
		 */
		List<WaybillInfo> list = new ArrayList<WaybillInfo>();
		/**
		 * 单个运单
		 */
		WaybillInfo waybillInfo = new WaybillInfo();
		/**
		 * 设值
		 */
		waybillInfo.setWaybillNumber("007");
		/**
		 * 设值
		 */
		waybillInfo.setCustomerType(Constant.ArriveCustomer);
		/**
		 * 设值
		 */
		waybillInfo.setPrepaid(200);
		/**
		 * 设值
		 */
		waybillInfo.setFreightCollect(300);
		/**
		 * 设值
		 */
		waybillInfo.setServicefee(30);
		/**
		 * 设值
		 */
		waybillInfo.setCollectionCharges(50);
		/**
		 * 设值
		 */
		list.add(waybillInfo);
		/**
		 * 单个运单
		 */
		waybillInfo = new WaybillInfo();
		/**
		 * 设值
		 */
		waybillInfo.setWaybillNumber("007");
		/**
		 * 设值
		 */
		waybillInfo.setCustomerType(Constant.LeaveCustomer);
		/**
		 * 设值
		 */
		waybillInfo.setPrepaid(300);
		/**
		 * 设值
		 */
		waybillInfo.setFreightCollect(100);
		/**
		 * 设值
		 */
		waybillInfo.setServicefee(30);
		/**
		 * 设值
		 */
		waybillInfo.setCollectionCharges(50);
		/**
		 * 设值
		 */
		list.add(waybillInfo);
		/**
		 * 单个运单
		 */
		waybillInfo = new WaybillInfo();
		/**
		 * 设值
		 */
		waybillInfo.setWaybillNumber("007");
		/**
		 * 设值
		 */
		waybillInfo.setCustomerType(Constant.ArriveCustomer);
		/**
		 * 设值
		 */
		waybillInfo.setPrepaid(200);
		/**
		 * 设值
		 */
		waybillInfo.setFreightCollect(100);
		/**
		 * 设值
		 */
		waybillInfo.setServicefee(30);
		/**
		 * 设值
		 */
		waybillInfo.setCollectionCharges(50);
		/**
		 * 设值
		 */
		list.add(waybillInfo);
		/**
		 * 设值
		 */
		// 返回
		return list;
	}
}
