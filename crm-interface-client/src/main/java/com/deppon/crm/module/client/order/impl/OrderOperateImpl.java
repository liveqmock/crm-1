package com.deppon.crm.module.client.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Check;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.DateUtils;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.client.order.domain.AppointmentCarInfo;
import com.deppon.crm.module.client.order.domain.ChannelOrderStatusInfo;
import com.deppon.crm.module.client.order.domain.ErpRemind;
import com.deppon.crm.module.client.order.domain.OrderQueryResInfo;
import com.deppon.ebm.EbmResponse;
import com.deppon.ebm.OrderStatusReqInfo;
import com.deppon.ebm.OrderStatusTraceInfo;
import com.deppon.ebm.QueryOrderStatusService;
import com.deppon.erp.custAndOrder.ERPBusinessException_Exception;
import com.deppon.erp.custAndOrder.ExceptionOrder;
import com.deppon.erp.custAndOrder.ExceptionOrderArray;
import com.deppon.erp.custAndOrder.IOrderAndCustService;
import com.deppon.erp.payment.IErp2CrmMsgService;
import com.deppon.erp.payment.Msg;
import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;
import com.deppon.foss.crm.CancelOrderRequest;
import com.deppon.foss.crm.CancelOrderResponse;
import com.deppon.foss.crm.CommonException;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;
import com.deppon.foss.crm.GoodsBillReceiveRequest;
import com.deppon.foss.crm.GoodsBillReceiveResponse;
import com.deppon.foss.crm.ResultDetal;
import com.deppon.foss.crm.SyncOrderLockInfo;
import com.deppon.foss.crm.SyncOrderLockInfoRequest;
import com.deppon.foss.crm.SyncOrderLockInfoResponse;
import com.deppon.ows.IOrderCouponBackService;
import com.deppon.ows.OrderCouponBackReponse;
import com.deppon.ows.OrderCouponBackRequest;

public class OrderOperateImpl implements IOrderOperate {

	private static Log log = LogFactory.getLog(OrderOperateImpl.class);
	private ESBService esb2erpAppointCar;
	private ESBService esb2ebmStatus;
	private QueryOrderStatusService ebmOrderStatusService;
	private IOrderAndCustService orderAndCustService;
	private IErp2CrmMsgService erp2CrmMsgService;
	private CustomerService customerService;
	private IOrderCouponBackService crmToOwsWebService;

	/**
	 * @作者：王明明
	 * @时间：2013-9-7
	 * @描述： 订单下单失败推送优惠券状态给官网
	 * @参数：优惠券推送对象(优惠券编码，订单编码，订单状态)
	 * */
	@Override
	public boolean orderCouponBack(OrderCouponBackRequest orderCouponBackRequest) {

		com.deppon.ows.ESBHeader esbHeader = new com.deppon.ows.ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.ORDER_STATUE_COUPON_BACK);
		esbHeader.setBusinessId(UUID.randomUUID().toString());
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<com.deppon.ows.ESBHeader> holder = new Holder<com.deppon.ows.ESBHeader>(esbHeader);
		try {
			OrderCouponBackReponse response = crmToOwsWebService.orderCouponBack(holder, orderCouponBackRequest);
		    if("Y".equals(response.getIsSuccess())){
		    	return true;
		    }
		} catch (com.deppon.ows.CommonException e) {
			log.info("订单优惠券状态推送失败",e);
		} catch (Exception e) {
			log.info("订单优惠券状态推送失败",e);
		}
		return false;
	}
/**
 * @作者：王明明
 * @时间：2013-8-8
 * @描述： 推送订单锁屏信息
 * @参数：订单锁屏信息
 * @return 推送结果明细
 * @throws CrmBusinessException
 * */
	public List<ResultDetal> syncOrderLockInfo(List<SyncOrderLockInfo> syncOrderLockInfo) throws CrmBusinessException{
		try {
			ESBHeader esbHeader = new ESBHeader();
			esbHeader.setVersion("0.1");
			esbHeader.setRequestId(UUID.randomUUID().toString());
			esbHeader.setEsbServiceCode(Constant.SYNC_ORDER_LOCK_INFO);
			esbHeader.setBusinessId(UUID.randomUUID().toString());
			esbHeader.setSourceSystem("CRM");
			esbHeader.setExchangePattern(1);
			esbHeader.setMessageFormat("SOAP");
			Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);

			SyncOrderLockInfoRequest request = new SyncOrderLockInfoRequest();
			List<SyncOrderLockInfo> syncOrderLockInfos = request
					.getSyncOrderLockInfo();
			for (SyncOrderLockInfo orderLockInfo : syncOrderLockInfo) {
				syncOrderLockInfos.add(orderLockInfo);
			}

			SyncOrderLockInfoResponse response = customerService
					.syncOrderLockInfo(request, holder);
			return response.getResultInfo();
		}catch(Exception e){
			log.info("推送订单锁屏信息出错",e);
			throw new CrmBusinessException("1005",e.getMessage());
		}
	}


	/**
	 * @描述：ERP消息提醒
	 * @作者：罗典
	 * @时间：2012-10-25
	 * @参数：ErpRemind 消息提醒参数集合
	 * @返回值：无
	 * */
	@Override
	public String sendErpRemind(List<ErpRemind> reminds) throws CrmBusinessException {
		Check.notNull(reminds,"interface-client check_error:remind is null,please check it.");
		try{
			List<Msg> msgList = new ArrayList<Msg>();
			for(ErpRemind remind : reminds){
				Msg msg = ClientTool.convertErpRemindToMsg(remind);
				msgList.add(msg);
			}
			String result = erp2CrmMsgService.insertCrmMsg(msgList);
			if(result!=null && result.equals("Insert Success")){
				return "success";
			}else{
				log.info(result);
				return result;
			}
		}catch(Exception e){
			throw new CrmBusinessException("1005",e.getMessage());
		}
	}
	/**
	 * @作者：罗典
	 * @时间：2012-8-29
	 * @描述： 根据修改时间查询时间段内被修改的ERP订单状态信息
	 * */
	@Override
	public List<ExceptionOrder> queryERPOrderStatus(Date beginDate, Date endDate)
			throws CrmBusinessException {
		ExceptionOrderArray orderArray= new ExceptionOrderArray();
		try {
			orderArray = orderAndCustService.queryExceptionOrders(beginDate, endDate);
		}
		catch(ERPBusinessException_Exception ex){
			throw new CrmBusinessException("1005",ex.getFaultInfo().getErrorCode());
		}
		catch (Exception e) {
			throw new CrmBusinessException("1005",e);
		}
		return orderArray.getItem();
	}

	/**
	 * @作者：罗典
	 * @时间：2012-8-29
	 * @描述： 根据修改时间查询时间段内被修改的电商订单状态信息
	 * */
	@Override
	public List<OrderStatusReqInfo> queryEBMOrderStatus(Date beginDate,Date endDate) throws CrmBusinessException{
		OrderQueryResInfo info = new OrderQueryResInfo();
		SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setBeginDate(DateUtils.format(beginDate, YYYYMMDDHHMMSS_FORMAT));
		info.setEndDate(DateUtils.format(endDate, YYYYMMDDHHMMSS_FORMAT));
		OrderStatusTraceInfo orderStatusTraceInfo =
				ebmOrderStatusService.queryOrderStatus(JsonMapperUtil.writeValue(info));
		if(orderStatusTraceInfo.getResult()!=null && orderStatusTraceInfo.getResult().equals("false")){
			throw new CrmBusinessException("1005",orderStatusTraceInfo.getReason());
		}
		return orderStatusTraceInfo.getOrders();
	}

	/**
	 *
	 * @description 根据订单号取消FOSS中的订单
	 * @author wugenbin_吴根斌
	 * @version 2013-7-26
	 * @param
	 * @return
	 */
	@Override
	public CancelOrderResponse cancelOrder(String orderNumber)
			throws CrmBusinessException {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.FOSS_CANCEL_ORDER);
		esbHeader.setBusinessId(orderNumber);
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		CancelOrderRequest request = new CancelOrderRequest();
		request.setOrderNumber(orderNumber);
		try {

			CancelOrderResponse response = customerService.cancelOrder(request,
					holder);
			return response;

		} catch (CommonException e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getFaultInfo()
							.getExceptioncode(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e.getMessage());
		}
	}
	/**
	 * 订单约车，接货单新增操作
	 * <p>需求编号：ERP-2</p>
	 * @author davidcun 2012-3-20
	 * @updator 罗典  2012-11-6
	 * @param   appointmentCarInfo {@link AppointmentCarInfo} 约车所需要的信息封装类
	 * @return  返回布尔值，表示约车操作是否成功
	 * @Throws
	 */
	@Override
	public boolean appointmentCar(AppointmentCarInfo appointmentCarInfo) throws CrmBusinessException{
		// 订单编号
		String orderNumber = appointmentCarInfo.getOrderNumber();
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.VEHICLE);
		esbHeader.setBusinessId(orderNumber);
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		GoodsBillReceiveRequest request = ClientTool.convertToGoodsBill(appointmentCarInfo);
		try {
			GoodsBillReceiveResponse response = customerService.sendGoodsBillReceive(holder,request);
			if(!response.isResult()){
				throw new CrmBusinessException("1002",response.getReason());
			}
			return true;
		}
		catch (CrmBusinessException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage(),e);
		}
		catch (CommonException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getExceptioncode(),e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
//		customerService.sendGoodsBillReceive(payload);
	/*

		EsbJsonRequest ejr = new EsbJsonRequest();
		ejr.setSystemName(Constant.SYSTEM_NAME);
		ejr.setServiceCode(Constant.ESB2ERP_POINTMENTCAR);
		// 货物类型  A是1 B是2  无是null
		if(appointmentCarInfo.getGoodsType() == null ||
				appointmentCarInfo.getGoodsType().equals("")){
			appointmentCarInfo.setGoodsType(null);
		}else if(appointmentCarInfo.getGoodsType().equals("A")){
			appointmentCarInfo.setGoodsType("1");
		}else if(appointmentCarInfo.getGoodsType().equals("B")){
			appointmentCarInfo.setGoodsType("2");
		}else{
			appointmentCarInfo.setGoodsType(null);
		}
		String carInfo = JsonMapperUtil.writeValue(appointmentCarInfo);
		ejr.setBody(carInfo);

		try {
			EsbJsonResponse response = esb2erpAppointCar.process(ejr);
			if ("SUCCESS".equalsIgnoreCase(response.getStatus())) {
				return true;
			} else {
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,response.getMessage());

			}

		} catch (Exception_Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e,e.getFaultInfo().getMessage());
		}*/
	}

	@Override
	public boolean updateOrderStatus(ChannelOrderStatusInfo statusInfo) throws CrmBusinessException{
		List<ChannelOrderStatusInfo> infos = new ArrayList<ChannelOrderStatusInfo>();
		infos.add(statusInfo);
		List<String> result = updateOrderStatus(infos);
		if (result==null || result.size()==0) {
			return true;
		}
		return false;

	}

	@Override
	public List<String> updateOrderStatus(List<ChannelOrderStatusInfo> infos)
			throws CrmBusinessException {
		EsbJsonRequest ejr = new EsbJsonRequest();
		ejr.setSystemName(Constant.SYSTEM_NAME);
		ejr.setServiceCode(Constant.ESB2EBM_ORDERSTATUS);
		ejr.setBody(JsonMapperUtil.writeValue(infos));
		try {
			EsbJsonResponse esbJsonResponse = esb2ebmStatus.process(ejr);
			String result = esbJsonResponse.getResponse();
			EbmResponse ebmResponse = JsonMapperUtil.readValue(result, EbmResponse.class);
			if ("SUCCESS".equalsIgnoreCase(esbJsonResponse.getStatus())) {
				if (!"SUCCESS".equalsIgnoreCase(ebmResponse.getStatus())) {
					if (log.isInfoEnabled()) {
						log.info(String.format("invoke ebm some order has error {%s} ",ebmResponse.getMessage()));
					}
					List<String> rst = new ArrayList<String>();
					String[] orderNumbers = ebmResponse.getMessage().split(",");
					for (String string : orderNumbers) {
						rst.add(string);
					}
					return rst;
				}else {
					return new ArrayList<String>();
					//throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,String.format("esb invoke ebm have a error: %s",ebmResponse.getMessage()));
				}
			}else {
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,esbJsonResponse.getMessage());
			}
		} catch (Exception_Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e,e.getFaultInfo().getMessage());
		}
	}

	public ESBService getEsb2erpAppointCar() {
		return esb2erpAppointCar;
	}

	public void setEsb2erpAppointCar(ESBService esb2erpAppointCar) {
		this.esb2erpAppointCar = esb2erpAppointCar;
	}

	public ESBService getEsb2ebmStatus() {
		return esb2ebmStatus;
	}

	public void setEsb2ebmStatus(ESBService esb2ebmStatus) {
		this.esb2ebmStatus = esb2ebmStatus;
	}

	public QueryOrderStatusService getEbmOrderStatusService() {
		return ebmOrderStatusService;
	}

	public void setEbmOrderStatusService(
			QueryOrderStatusService ebmOrderStatusService) {
		this.ebmOrderStatusService = ebmOrderStatusService;
	}

	public IOrderAndCustService getOrderAndCustService() {
		return orderAndCustService;
	}

	public void setOrderAndCustService(IOrderAndCustService orderAndCustService) {
		this.orderAndCustService = orderAndCustService;
	}
	public IErp2CrmMsgService getErp2CrmMsgService() {
		return erp2CrmMsgService;
	}
	public void setErp2CrmMsgService(IErp2CrmMsgService erp2CrmMsgService) {
		this.erp2CrmMsgService = erp2CrmMsgService;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public IOrderCouponBackService getCrmToOwsWebService() {
		return crmToOwsWebService;
	}
	public void setCrmToOwsWebService(IOrderCouponBackService crmToOwsWebService) {
		this.crmToOwsWebService = crmToOwsWebService;
	}

}
