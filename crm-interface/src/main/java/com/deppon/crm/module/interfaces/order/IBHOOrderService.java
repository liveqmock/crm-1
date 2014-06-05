package com.deppon.crm.module.interfaces.order;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Holder;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.domain.ComplaintOrderBindRequest;
import com.deppon.crm.module.interfaces.order.domain.ComplaintOrderBindResponse;
import com.deppon.crm.module.interfaces.order.domain.OrderQueryCondition;
import com.deppon.crm.module.interfaces.order.domain.Order;
/** 
 * 网厅接口
 * @author davidcun @2012-4-23
 */
@WebService
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE,style=Style.DOCUMENT)
public interface IBHOOrderService {
	

	/**
     *订单创建，此接口针对官网投诉 在CRM工单管理生成工单信息
	 * @param complaintOrderBindRequest
	 * @return returns com.deppon.crm.bho.ComplaintOrderBindResponse
	 */
	@WebMethod
	@WebResult(name = "ComplaintOrderBindResponse", targetNamespace = "http://www.deppon.com/bho/remote/crm/domain/entity", partName = "ComplaintOrderBindResponse")
	public ComplaintOrderBindResponse bhoCreateOrder(
			@WebParam(name = "ComplaintOrderBindRequest", targetNamespace = "http://www.deppon.com/bho/remote/crm/domain/entity", partName = "ComplaintOrderBindRequest") ComplaintOrderBindRequest complaintOrderBindRequest)throws CrmBusinessException;

	/**
	 * 订单更新，此接口是针对ESB(来源为电商)提供的，其中订单编号是必须的
	 * <p>需求编号：BHO-2、EBM-2</p>
	 * @param   order {@link Order}
	 * @return  是否成功    
	 * @Throws 
	 * @author davidcun 2012-3-26
	 *
	 */
	public boolean updateOrder(@WebParam(name="updateOrder") Order order) throws CrmBusinessException;

	/**
	 * 撤销订单服务
	 * <p>需求编号：EBM-3</p>
	 * @param channelOrderNumber 单号(渠道单号或者订单号)、channelSource订单来源、remark撤销原因、用户ID
	 * @param channelUserId 渠道用户ID
	 * @return 操作是否成功    
	 * @Throws {@link CrmBusinessException} 
	 * @author davidcun 2012-3-28
	 *
	 */
	public boolean cancelOrder(@WebParam(name="channelOrderNumber") String channelOrderNumber
			,@WebParam(name="channelSource") String channelSource,@WebParam(name="remark") String remark) 
			throws CrmBusinessException;
	
	/**
	 * 条件查询客户订单信息，查询条件参见参数说明
	 * <p>需求编号:BHO-3</p>
	 * @param  queryCondition {@link OrderQueryCondition} 客户订单查询条件封装类
	 * @return 订单信息集合     
	 * @Throws {@link CrmBusinessException}
	 * @author davidcun 2012-3-28
	 *
	 */
	public List<Order> queryOrders(@WebParam(name="queryCondition",mode=WebParam.Mode.INOUT) Holder<OrderQueryCondition> queryCondition) throws CrmBusinessException;

	/**
	 * 通过订单号查询订单信息
	 * <p>需求编号：ERP-3,BHO-4</p>
	 * @author davidcun
	 * @param  orderNumber 订单号
	 * @return 检索到的订单信息对象
	 * @throws CrmBusinessException 服务异常。可能的异常代码：9999（该订单号不存在） 
	 */
	public Order searchOrder(@WebParam(name="orderNumber") String orderNumber) throws CrmBusinessException;

}
