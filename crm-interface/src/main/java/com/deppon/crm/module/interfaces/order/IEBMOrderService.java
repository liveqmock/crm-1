package com.deppon.crm.module.interfaces.order;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.order.domain.Order;
/**
 * 电商接口
 * @author davidcun @2012-4-23
 */
@WebService
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE,style=Style.DOCUMENT)
public interface IEBMOrderService {

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
	 * EBM-5
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-8
	 *
	 */
	public Order queryOrderByChannelNumber(String channelOrderNumber) throws CrmBusinessException ;
	
	/**
	 * @作者：罗典
	 * @时间：2012-6-29
	 * @描述：官网绑定淘宝商城ID修改此客户相关的订单信息
	 * @参数：订单信息(只包含部分发货人信息)
	 * @返回值：影响行数
	 * */
	public int bindShangchengId(Order order) throws CrmBusinessException;
}
