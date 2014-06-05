package com.deppon.crm.module.interfaces.order;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.domain.OrderStatusInfo;
import com.deppon.crm.module.interfaces.order.domain.Order;
/**
 * ESB接口
 * @author davidcun @2012-4-23
 */
@WebService
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE,style=Style.DOCUMENT)
public interface IESBOrderService {

	/**
	 * 订单保存功能，此接口是针对网厅、ESB(来源为电商)提供的
	 * <p>需求编号：BHO-1、EBM-1</p>
	 * @param  orderOrigin 表示订单来源
	 * @return 保存是否成功
	 * @Throws 
	 * @author davidcun 2012-3-26
	 *
	 */
	public boolean createOrder(@WebParam(name="createOrder") Order order) throws CrmBusinessException;
	
	/**
	 * crm向外提供的订单状态更新接口服务，目前是提供给ERP使用
	 * <p>需求编号：ERP-4</p>
	 * @author davidcun 2012-3-22
	 * @param   orderStateInfo {@link OrderStatusInfo} 返回的状态信息及一些相关必须信息的封装类 
	 * @return  
	 * @Throws {@link CrmBusinessException}
	 *
	 */
	public boolean updateOrderStatus(@WebParam(name="orderStateInfo") OrderStatusInfo orderStateInfo) throws CrmBusinessException;
}
