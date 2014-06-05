package com.deppon.crm.module.client.order;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.ErpRemind;
import com.deppon.ebm.OrderStatusReqInfo;
import com.deppon.erp.custAndOrder.ExceptionOrder;
import com.deppon.foss.crm.ResultDetal;
import com.deppon.foss.crm.SyncOrderLockInfo;
import com.deppon.foss.crm.CancelOrderResponse;
import com.deppon.ows.OrderCouponBackRequest;
/** 
 * 与订单相关的外围系统接口封装，主要包括约车操作
 * @author davidcun @2012-3-21
 */
public interface IOrderOperate {

	/**
	 * @作者：王明明
	 * @时间：2013-9-7
	 * @描述： 订单下单失败推送优惠券状态给官网
	 * @参数：优惠券推送对象(优惠券编码，订单编码，订单状态)
	 * */
	public boolean orderCouponBack(OrderCouponBackRequest orderCouponBackRequest);

	/**
	 *
	 * @description 根据订单号取消FOSS中的订单
	 * @author wugenbin_吴根斌
	 * @version 2013-7-26
	 * @param
	 * @return
	 */
	public CancelOrderResponse cancelOrder(String orderNumber)throws CrmBusinessException;

/**
 * @作者：王明明
 * @时间：2013-8-8
 * @描述： 推送订单锁屏信息
 * @参数：订单锁屏信息
 * */
	public List<ResultDetal> syncOrderLockInfo(List<SyncOrderLockInfo> syncOrderLockInfo)throws CrmBusinessException;;

	/**
	 * @作者：罗典
	 * @时间：2012-8-29
	 * @描述： 根据修改时间查询时间段内被修改的订单状态信息
	 * @参数：订单被修改时间段
	 * */
	public List<OrderStatusReqInfo> queryEBMOrderStatus(Date beginDate,Date endDate)  throws CrmBusinessException;

	/**
	 * @作者：罗典
	 * @时间：2012-8-29
	 * @描述：根据订单修改时间查询ERP同步的订单状态信息
	 * @参数：订单被修改时间段
	 * */
	public List<ExceptionOrder> queryERPOrderStatus(Date beginDate,Date endDate)  throws CrmBusinessException;


	/**
	 * 订单约车，接货单新增操作
	 * <p>需求编号：ERP-2</p>
	 * @author davidcun 2012-3-20
	 * @param   appointmentCarInfo {@link AppointmentCarInfo} 约车所需要的信息封装类
	 * @return  返回布尔值，表示约车操作是否成功
	 * @Throws
	 */
	public boolean appointmentCar(AppointmentCarInfo appointmentCarInfo) throws CrmBusinessException;


	/**
	 * <p>需求编号：EBM-4</p>
	 * CRM系统向渠道系统同步的订单状态信息
	 * @author davidcun 2012-3-22
	 * @param  statusInfo 渠道订单状态信息
	 * @return
	 * @Throws
	 *
	 */
	public boolean updateOrderStatus(ChannelOrderStatusInfo statusInfo) throws CrmBusinessException;

	/**
	 * 批量更新渠道订单状态
	 * @param  返回更新失败的订单号
	 * @return
	 * @Throws
	 * @author davidcun 2012-5-19
	 *
	 */
	public List<String> updateOrderStatus(List<ChannelOrderStatusInfo> infos) throws CrmBusinessException;

	/**
	 * @描述：ERP消息提醒
	 * @作者：罗典
	 * @时间：2012-10-25
	 * @参数：ErpRemind 消息提醒参数集合
	 * @返回值：无
	 * */
	public String sendErpRemind(List<ErpRemind> reminds) throws CrmBusinessException;
}
