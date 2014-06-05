/**
 * Filename:	OrderVirtualDataProducer.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM1
 * @author:		
 * @version:	
 * create at:	2012-4-21 下午2:35:24
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-4-21	    
 */

package com.deppon.crm.module.complaint.server.util;

import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.server.manager.impl.OrderManager;
import com.deppon.crm.module.order.shared.domain.Order;

/**
 * 
 * @description 通过运单号查询订单信息.
 * @author 潘光均
 * @version 0.1 2012-3-19
 * @param b
 *            true or false
 * @date 2012-3-19
 * @return none
 * @update 2012-3-19 上午11:41:31
 */
public class OrderVirtualDataProducer extends OrderManager implements IOrderManager{
	/**
	 * 
	 * @description 通过运单号查询订单信息.
	 * @author 潘光均
	 * @version 0.1 2012-3-19
	 * @param b
	 *            true or false
	 * @date 2012-3-19
	 * @return none
	 * @update 2012-3-19 上午11:41:31
	 */
	public Order getOrderWaybillNum(String waybillNumber){
		Order order =new Order();
		
		return order;
	}


}
