package com.deppon.crm.module.interfaces.order;

import javax.jws.WebService;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.domain.Order;
/**
 * ERP接口
 * @author davidcun @2012-4-23
 */
@WebService
public interface IERPOrderService {
	/**
	 * 通过订单号查询订单信息
	 * <p>需求编号：ERP-3,BHO-4</p>
	 * @author davidcun
	 * @param  orderNumber 订单号
	 * @return 检索到的订单信息对象
	 * @throws CrmBusinessException 服务异常。可能的异常代码：9999（该订单号不存在） 
	 */
	public Order searchOrder(String orderNumber) throws CrmBusinessException;
}
