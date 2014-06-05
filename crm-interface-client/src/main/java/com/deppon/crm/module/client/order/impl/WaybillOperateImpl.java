package com.deppon.crm.module.client.order.impl;

import java.math.BigDecimal;
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
import com.deppon.crm.module.client.esb.trans.EsbUtil;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.CustomerWaybillInfo;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillRecompenseInfo;
import com.deppon.foss.crm.BindOrderRequest;
import com.deppon.foss.crm.BindOrderResponse;
import com.deppon.foss.crm.CommonException;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;
import com.deppon.foss.crm.QueryMoneyRequest;
import com.deppon.foss.crm.QueryMoneyResponse;
import com.deppon.foss.waybill.QueryDetailRequest;
import com.deppon.foss.waybill.QueryDetailResponse;
import com.deppon.foss.crm.FossQueryAcctinfoRequest;
import com.deppon.foss.crm.FossQueryAcctinfoResponse;
import com.deppon.foss.waybill.WayBillDetail;
import com.deppon.foss.waybill.WaybillService;

public class WaybillOperateImpl implements IWaybillOperate {

	private static Log log = LogFactory.getLog(WaybillOperateImpl.class);

	private CustomerService customerService;
	private WaybillService waybillService;
	/**
	 * <p>
	 * Description:查询运单<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param custNum
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @param size
	 * @return
	 * @throws CrmBusinessException
	 */
	@Override
	public CustomerWaybillInfo queryCustomerWaybillInfo(String custNum,
			Date beginTime, Date endTime, int currentPage, int pageSize)
			throws CrmBusinessException {
		Check.notNullOrEmpty(custNum, "custNum is null,please check it.");
		try {
			FossQueryAcctinfoRequest request=new com.deppon.foss.crm.FossQueryAcctinfoRequest();
			request.setDeliveryCustomerCode(custNum);
			request.setStartDate(beginTime);
			request.setEndDate(endTime);
			request.setCurrentPage(currentPage);
			request.setPageSize(pageSize);
			Holder<com.deppon.foss.crm.ESBHeader> holder =
					EsbUtil.createCustomerWaybillHeader(Constant.CUST_WAYBILL, custNum, custNum);
			FossQueryAcctinfoResponse response=customerService.fossQueryAcctinfo(holder, request);
			CustomerWaybillInfo customerWaybillInfo=ClientTool.convertToCustomerWaybillInfo(response);
			return customerWaybillInfo;
		} catch (com.deppon.foss.crm.CommonException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getExceptioncode(),e);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：根据运单号查询FOSS运单详情
	 * @时间：2012-11-7
	 * @参数：运单号
	 * @返回值：FOSS运单详情
	 * */
	@Override
	public FossWaybillInfo queryWaybillInfo(String waybillNum)throws CrmBusinessException {
		Check.notNullOrEmpty(waybillNum, "waybillNum is null,please check it.");
		try {
			QueryDetailRequest request = new QueryDetailRequest();
			request.getWaybillNo().add(waybillNum);
			Holder<com.deppon.foss.waybill.ESBHeader> holder =
						EsbUtil.createWaybillHeader(Constant.WAYBILL_DETAIL, waybillNum, waybillNum);
			QueryDetailResponse response = waybillService.queryDetail(holder,request);
			List<FossWaybillInfo> waybillList = ClientTool.convertToFossWaybill(response);
			if(waybillList == null || waybillList.size() == 0){
				return null;
			}
			return waybillList.get(0);
		} catch (com.deppon.foss.waybill.CommonException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getExceptioncode(),e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}



	/**
	 * <p>需求编号：ERP-10</p>
	 * 查询客户发到货金额信息，时时创建会员信息
	 * @param mobilePhone 客户手机
	 * @param customerName 客户姓名
	 * @param customerTelephone 客户固定电话
	 * @author davidcun 2012-4-9
	 * @updator 罗典  2012-11-6
	 */
	@Override
	public List<WaybillInfo> queryWaybillMoney(String mobilePhone,String customerName,
			String customerTelephone)throws CrmBusinessException {
		QueryMoneyRequest request = new QueryMoneyRequest();
		request.setMobile(mobilePhone);
		request.setTelephone(customerTelephone);
		request.setName(customerName);

		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.QUERYMONEY);
		esbHeader.setBusinessId("businessId");
		if(null!=request.getTelephone()&&!"".equals(request.getTelephone())){
			esbHeader.setBusinessId(request.getTelephone());
		}
		if(null!=request.getMobile()&&!"".equals(request.getMobile())){
			esbHeader.setBusinessId(request.getMobile());
		}
		esbHeader.setBusinessDesc1(request.getMobile());
		esbHeader.setBusinessDesc2(request.getTelephone());
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		try
		{
			List<WaybillInfo> waybillInfos = new ArrayList<WaybillInfo>();
			QueryMoneyResponse response = customerService.queryMoney(holder,request);
			if(response != null && response.getAmountInfo() != null
					&& response.getAmountInfo().size()>0){
				waybillInfos = ClientTool.convertToWaybills(response);
			}
			return waybillInfos;
		}catch (CommonException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getExceptioncode(),e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：查询未签收运单
	 * @时间：2013-01-09
	 * @参数：运单号
	 * @返回：未签收运单信息
	 */
	@Override
	public WaybillInfo queryNotClosedWaybill(String waybillNumber)
			throws CrmBusinessException {
		Check.notNullOrEmpty(waybillNumber, "waybillNumber can not be null or \"\"");
		try {
			QueryDetailRequest request = new QueryDetailRequest();
			request.getWaybillNo().add(waybillNumber);
			Holder<com.deppon.foss.waybill.ESBHeader> holder =
						EsbUtil.createWaybillHeader(Constant.WAYBILL_DETAIL, waybillNumber, waybillNumber);
			QueryDetailResponse response = waybillService.queryDetail(holder,request);
			if(response != null && !response.getWayBillDetailList().isEmpty()){
				WayBillDetail detail = response.getWayBillDetailList().get(0);
				if(!detail.isIsSigned()){
					WaybillInfo info = new WaybillInfo();
					info.setShipper(detail.getSender());
					info.setShipperTelephone(detail.getSenderPhone());
					info.setShipperPhone(detail.getSenderMobile());
					info.setShipperAddress(detail.getSenderAddress());
					return info;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：理赔上报查询运单信息
	 * @时间：2013-01-09
	 * @参数：运单号
	 * @返回：理赔上报运单信息
	 */
	@Override
	public WaybillInfo queryWaybillRecompense(String waybillNumber)
			throws CrmBusinessException {
		Check.notNullOrEmpty(waybillNumber, "waybillNumber can not be null or \"\"");
		try{
			QueryDetailRequest request = new QueryDetailRequest();
			request.getWaybillNo().add(waybillNumber);
			Holder<com.deppon.foss.waybill.ESBHeader> holder =
						EsbUtil.createWaybillHeader(Constant.WAYBILL_DETAIL, waybillNumber, waybillNumber);
			QueryDetailResponse response = waybillService.queryDetail(holder,request);
			if(response != null && !response.getWayBillDetailList().isEmpty()){
				WayBillDetail detail = response.getWayBillDetailList().get(0);
				WaybillInfo info = ClientTool.convertToWaybillInfo(detail);
				return info;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}

	}

	/**
	 * <p>需求编号：ERP-9</p>
	 * 调用ERP的接口把运单和订单关联信息传递给ERP系统，如果知道新的运单
	 * @param  orderNumber 订单号
	 * @param waybillNumber 运单号
	 * @return 操作成功与否
	 * @author davidcun 2012-3-24
	 * @updator 罗典 2012-11-6
	 */
	@Override
	public boolean linkWaybill(String orderNumber, String newWaybillNumber,String oldWaybillNumber)
			throws CrmBusinessException {
		Check.notNullOrEmpty(orderNumber, "orderNumber can not be null or \"\"");
		Check.notNullOrEmpty(newWaybillNumber, "waybillNumber can not be null or \"\"");
		try {
			BindOrderRequest request = new BindOrderRequest();
			request.setOrderNo(orderNumber);
			request.setNewWayBillNumber(newWaybillNumber);
			request.setOldWayBillNumber(oldWaybillNumber);
			ESBHeader esbHeader = new ESBHeader();
			esbHeader.setVersion("0.1");
			esbHeader.setRequestId(UUID.randomUUID().toString());
			esbHeader.setEsbServiceCode(Constant.BINDORDER);
			esbHeader.setBusinessId(orderNumber);
			esbHeader.setBusinessDesc1(newWaybillNumber);
			esbHeader.setBusinessDesc2(oldWaybillNumber);
			esbHeader.setSourceSystem("CRM");
			esbHeader.setExchangePattern(1);
			esbHeader.setMessageFormat("SOAP");
			Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
			BindOrderResponse response = customerService.bindOrder(holder,request);
			return response.isResult();
		} catch (CommonException e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getExceptioncode(),e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}

	@Override
	public WaybillRecompenseInfo queryWaybillOnlineRecompense(String waybillNumber)
			throws CrmBusinessException {

		Check.notNullOrEmpty(waybillNumber, "waybillNumber can not be null or \"\"");
		try{
			QueryDetailRequest request = new QueryDetailRequest();
			request.getWaybillNo().add(waybillNumber);
			Holder<com.deppon.foss.waybill.ESBHeader> holder =
						EsbUtil.createWaybillHeader(Constant.WAYBILL_DETAIL, waybillNumber, waybillNumber);
			QueryDetailResponse response = waybillService.queryDetail(holder,request);
			if(response != null && !response.getWayBillDetailList().isEmpty()){
				WayBillDetail detail = response.getWayBillDetailList().get(0);
				WaybillRecompenseInfo info = ClientTool.convertToWaybillRecompenseInfo(detail);
				return info;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e, e.getMessage());
		}
	}

	/*
	 * ERP-14
	 * (non-Javadoc)
	 * @see com.deppon.crm.module.client.order.IWaybillOperate#queryWaybillCustomer(java.lang.String)
	 */
	@Override
	public WaybillInfo queryWaybillCustomer(String waybillNumber)
			throws CrmBusinessException {
		Check.notNullOrEmpty(waybillNumber, "waybillNumber can not be null or \"\"");
		try{
			QueryDetailRequest request = new QueryDetailRequest();
			request.getWaybillNo().add(waybillNumber);
			Holder<com.deppon.foss.waybill.ESBHeader> holder =
						EsbUtil.createWaybillHeader(Constant.WAYBILL_DETAIL, waybillNumber, waybillNumber);
			QueryDetailResponse response = waybillService.queryDetail(holder,request);
			if(response != null && !response.getWayBillDetailList().isEmpty()){
				WayBillDetail detail = response.getWayBillDetailList().get(0);
				WaybillInfo info = ClientTool.convertToWaybillInfo(detail);
				return info;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}

	@Override
	public BigDecimal queryNear3MonthAmount(String custNumber) throws CrmBusinessException{
		throw new CrmBusinessException("1006");
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public WaybillService getWaybillService() {
		return waybillService;
	}

	public void setWaybillService(WaybillService waybillService) {
		this.waybillService = waybillService;
	}





}
