package com.deppon.crm.module.interfaces.foss.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.WebService;
import javax.xml.ws.Holder;

import org.apache.log4j.Logger;

import com.deppon.crm.module.backfreight.server.manager.IBackFreightManager;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.coupon.server.manager.ICouponForFossManager;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.coupon.shared.exception.CouponException;
import com.deppon.crm.module.interfaces.common.util.Constant;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.fin.IFinToCrmService;
import com.deppon.crm.module.interfaces.foss.FossToCrmService;
import com.deppon.crm.module.interfaces.foss.domain.BackFreightCheckRequest;
import com.deppon.crm.module.interfaces.foss.domain.BackFreightCheckResponse;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.foss.domain.CouponStateRequest;
import com.deppon.crm.module.interfaces.foss.domain.CouponStateResponse;
import com.deppon.crm.module.interfaces.foss.domain.ESBHeader;
import com.deppon.crm.module.interfaces.foss.domain.ModifyOrderLockInfoRequest;
import com.deppon.crm.module.interfaces.foss.domain.ModifyOrderLockInfoResponse;
import com.deppon.crm.module.interfaces.foss.domain.OrderInfo;
import com.deppon.crm.module.interfaces.foss.domain.QueryClaimbillRequest;
import com.deppon.crm.module.interfaces.foss.domain.QueryClaimbillResponse;
import com.deppon.crm.module.interfaces.foss.domain.QueryOrderListRequest;
import com.deppon.crm.module.interfaces.foss.domain.QueryOrderListResponse;
import com.deppon.crm.module.interfaces.foss.domain.SearchOrderRequest;
import com.deppon.crm.module.interfaces.foss.domain.SearchOrderResponse;
import com.deppon.crm.module.interfaces.foss.domain.ValidateCouponRequest;
import com.deppon.crm.module.interfaces.foss.domain.ValidateCouponResponse;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderAcceptInfo;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.shared.domain.GetRecompenseByWayBill;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * @作者：罗典
 * @时间：2012-11-14
 * @描述：与FOSS对接同步接口服务
 * */
@WebService
public class FossToCrmServiceImpl implements FossToCrmService, IFinToCrmService {
	private final Logger logger = Logger.getLogger(FossToCrmServiceImpl.class);
	// 订单
	private IOrderManager orderManager;
	// 优惠券
	private ICouponForFossManager couponForFossManager;
	// 退运费模块
	private IBackFreightManager backFreightManager;

	// 理赔模块
    private RecompenseManager recompenseManager;
    /**
	 * @作者：王明明
	 * @描述：修改订单解屏信息
	 * @时间：2013-8-8
	 * @参数：部门标杆编码
	 * @返回：返回该部门下的订单锁屏数和订单提示数，没有的话 返回 0 0
	 * */
    @Override
	public ModifyOrderLockInfoResponse modifyOrderLockInfo(
			ModifyOrderLockInfoRequest request, Holder<ESBHeader> esbHeader)
			throws CommException {
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		IntefacesTool.checkNull(request, Constant.PARAMS_LOSE);
		IntefacesTool.checkNull(request.getDeptCode(), Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		//header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.ORDER_LOCK_INFO);
		try {
			ModifyOrderLockInfoResponse response = new ModifyOrderLockInfoResponse();
			OrderAcceptInfo orderAccepInfo = orderManager
					.getOrderAcceptInfoByDept(request.getDeptCode());
			response.setDeptCode(orderAccepInfo.getStandardCode());
			response.setPromptCount(orderAccepInfo.getWarnNum());
			response.setLockCount(orderAccepInfo.getLockNum());
			return response;

		} catch (Exception e) {
			header.setResultCode(0);
			logger.info("查询订单锁屏异常", e);
			throw new CommException(e.getMessage(), e.getCause());
		}
	}
	/**
	 * @作者：罗典
	 * @描述：校验是否存在有效的退运费信息_FIN
	 * @时间：2012-11-21
	 * @参数：运单号
	 * @返回：是否存在有效的退运费信息
	 * */
	@Override
	public BackFreightCheckResponse backFreightCheck(
			BackFreightCheckRequest request) throws CommException {
		IntefacesTool.checkNull(request, Constant.PARAMS_LOSE);
		IntefacesTool.checkNull(request.getWaybillNum(), Constant.PARAMS_LOSE);
		BackFreightCheckResponse response = new BackFreightCheckResponse();
		response.setCheckResult(false);
		BackFreight backFreight = backFreightManager
				.findValidBackFreightByNum(request.getWaybillNum());
		if (backFreight != null) {
			response.setCheckResult(true);
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：校验是否存在有效的退运费信息_FOSS
	 * @时间：2012-11-21
	 * @参数：运单号
	 * @返回：是否存在有效的退运费信息
	 * */
	@Override
	public BackFreightCheckResponse backFreightCheck(
			BackFreightCheckRequest request, Holder<ESBHeader> esbHeader)
			throws CommException {
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		IntefacesTool.checkNull(request, Constant.PARAMS_LOSE);
		IntefacesTool.checkNull(request.getWaybillNum(), Constant.PARAMS_LOSE);
		BackFreightCheckResponse response = new BackFreightCheckResponse();
		response.setCheckResult(false);
		try {
			BackFreight backFreight = backFreightManager
					.findValidBackFreightByNum(request.getWaybillNum());
			if (backFreight != null) {
				response.setCheckResult(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：查询订单接口 (接口编码: FOSS_CRM_QUERYORDER)
	 * @时间：2012-11-8
	 * @参数：查询条件
	 * @返回：订单信息列表
	 * */
	public QueryOrderListResponse queryOrder(
			QueryOrderListRequest queryOrderListRequest,
			Holder<ESBHeader> esbHeader) throws CommException {
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		IntefacesTool.checkNull(queryOrderListRequest, Constant.PARAMS_LOSE);
		QueryOrderListResponse response = new QueryOrderListResponse();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startLine", queryOrderListRequest.getPageNum());
			map.put("endLine", queryOrderListRequest.getPageSize());
			map.put("orderType", queryOrderListRequest.getOrderType());
			// 如果传入的是诚信通阿里巴巴类型，则加入阿里巴巴会员类型为诚信通条件
			if((com.deppon.crm.module.order.server.util.Constant.ORDER_SOURCE_ALIBABA
					+com.deppon.crm.module.order.server.util.Constant.ORDER_ALIBABA_CXT)
					.equals(queryOrderListRequest.getOrderType())){
				map.put("aliMemberType", com.deppon.crm.module.order.server.util.Constant.ORDER_ALIBABA_CXT);
				map.put("orderType", queryOrderListRequest.getOrderType().replace(
						com.deppon.crm.module.order.server.util.Constant.ORDER_ALIBABA_CXT, ""));
			}
			map.put("acceptStatus", queryOrderListRequest.getAcceptStatus());
			map.put("endDate", queryOrderListRequest.getEndTime());
			map.put("startDate", queryOrderListRequest.getBeginTime());
			map.put("shipperCust", queryOrderListRequest.getShipperCust());
			map.put("shipperLinkman", queryOrderListRequest.getShipperLinkman());
			map.put("shipperMobile", queryOrderListRequest.getShipperMobile());
			map.put("shipperPhone", queryOrderListRequest.getShipperPhone());
			map.put("orderNumber", queryOrderListRequest.getOrderNumber());
			map.put("deptCode", queryOrderListRequest.getDeptCode());
			map.put("transportMode", queryOrderListRequest.getTransType());
			map.put("waybillNumber", queryOrderListRequest.getWaybillNumber());
			List<Order> orderList = orderManager.queryOrders(map);
			int totalCount = orderManager.countOrders(map);
			List<OrderInfo> orderInfoList = response.getOrderLists();
			response.setTotalCount(totalCount);
			for (Order order : orderList) {
				OrderInfo info = IntefacesTool.convertToOrderInfo(order);
				orderInfoList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;

	}

	/**
	 * @作者：罗典
	 * @描述：反使用优惠券接口 (接口编码：CRM_COUPON_STATE)
	 * @时间：2012-11-8
	 * @参数：优惠券编码
	 * @返回：是否成功，异常信息
	 * */
	public CouponStateResponse effectCouponState(CouponStateRequest request,
			Holder<ESBHeader> esbHeader) throws CommException {
		IntefacesTool.checkNull(request, Constant.PARAMS_LOSE);
		IntefacesTool
				.checkNull(request.getCouponNumber(), Constant.PARAMS_LOSE);
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		CouponStateResponse response = new CouponStateResponse();
		response.setIsSuccess(false);

		try {
			String result = couponForFossManager.changeCouponInvalidWB(request
					.getCouponNumber());
			if (result.equals(MarketingCouponConstance.PROCESSSUCCESS)) {
				response.setIsSuccess(true);
				return response;
			} else {
				response.setErrorCode(result);
			}
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("coupon",
					e.getErrorCode());
			response.setErrorCode(errorMsg);
			response.setIsSuccess(false);
			header.setResultCode(0);
		} catch (CouponException e) {
			e.printStackTrace();
			throw new CommException(e.getMessage(), e.getCause());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：优惠券校验与使用接口 (接口编码：CRM_VALIDATE_COUPON)
	 * @时间：2012-11-8
	 * @参数：优惠券编码，是否使用，运单金额信息
	 * @返回：可否使用，优惠金额
	 * */
	public ValidateCouponResponse validateCoupon(ValidateCouponRequest request,
			Holder<ESBHeader> esbHeader) throws CommException {
		IntefacesTool.checkNull(request, Constant.PARAMS_LOSE);
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		ValidateCouponResponse response = new ValidateCouponResponse();
		try {
			WaybillInfo waybillInfo = IntefacesTool
					.convertToWaybillInfo(request.getWaybillInfo());
			String[] result = null;
			if (request.isIsUsed()) {
				result = couponForFossManager.changeCouponStatusUsing(
						request.getCouponNumber(), waybillInfo);
			} else {
				result = couponForFossManager.checkCouponRule(
						request.getCouponNumber(), waybillInfo);
			}
			if (result[0].equals("true")) {
				response.setIsCanUse(true);
				response.setCouponAmount(new BigDecimal(result[1]));
				//抵扣类型
				response.setDeductibleType(result[2]);
			} else {
				response.setIsCanUse(false);
				response.setCanNotUseReason(result[1]);
				response.setCouponAmount(new BigDecimal(0));
				//抵扣类型
				response.setDeductibleType(result[2]);
			}
		} catch (GeneralException e) {
			String errorMsg = IntefacesTool.getMessage("coupon",
					e.getErrorCode());
			response.setCanNotUseReason(errorMsg);
		} catch (CouponException e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}

	/**
	 * @作者：吴根斌
	 * @描述：查询理赔单 (接口编码：CRM_QUERY_ CLAIMBILL)
	 * @时间：2013-4-23
	 * @参数：运单号
	 * @返回：CRM运单详细信息
	 * */
	@Override
	public QueryClaimbillResponse queryClaimbill(QueryClaimbillRequest request,
			Holder<ESBHeader> esbHeader) throws CommException {
		IntefacesTool.checkNull(request, Constant.PARAMS_LOSE);
		IntefacesTool.checkNull(request.getWaybillNum(), Constant.PARAMS_LOSE);
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		QueryClaimbillResponse response = new QueryClaimbillResponse();
		try {
			String waybillNum = request.getWaybillNum();
			GetRecompenseByWayBill claim = recompenseManager
					.getRecompenseByWayBill(waybillNum);
			if (claim != null) {
				response = IntefacesTool.convertToClaimbillResponse(claim);
			}
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：导入订单开单接口 (接口编码：FOSS_CRM_IMPORT)
	 * @时间：2012-11-8
	 * @参数：订单号
	 * @返回：CRM订单详细信息
	 * */
	public SearchOrderResponse searchOrder(
			SearchOrderRequest searchOrderRequest, Holder<ESBHeader> esbHeader)
			throws CommException {
		IntefacesTool.checkNull(searchOrderRequest, Constant.PARAMS_LOSE);
		IntefacesTool.checkNull(searchOrderRequest.getOrderNumber(),
				Constant.PARAMS_LOSE);
		ESBHeader header = esbHeader.value;
		IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		SearchOrderResponse response = new SearchOrderResponse();
		try {
			Order order = orderManager
					.queryOrderByOrderNumber(searchOrderRequest
							.getOrderNumber());
			if (order != null) {

				response.setOrder(IntefacesTool.convertToOrder(order));
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public ICouponForFossManager getCouponForFossManager() {
		return couponForFossManager;
	}

	public void setCouponForFossManager(
			ICouponForFossManager couponForFossManager) {
		this.couponForFossManager = couponForFossManager;
	}

	public IBackFreightManager getBackFreightManager() {
		return backFreightManager;
	}

	public void setBackFreightManager(IBackFreightManager backFreightManager) {
		this.backFreightManager = backFreightManager;
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

}
