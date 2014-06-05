package com.deppon.crm.module.coupon.server.manager.impl;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.coupon.server.manager.ICouponForFossManager;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.server.utils.CouponValidator;
import com.deppon.crm.module.coupon.shared.domain.AmountInfo;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.shared.domain.Order;

/**
 * <p>
 * Description: Foss运单作废接口<br />
 * </p> 
 * @author 钟琼
 * @version 0.1 2012-11-13
 */
public class CouponForFossManagerImpl implements ICouponForFossManager {
	
	private ICouponManager couponManagerImpl;
	private IOrderManager orderManager;

	/**
	 * <p>
	 * Description: 过期的优惠券，状态为已过期。<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNum 优惠券编码（列表）
	 * @return String
	 */
	@Override
	public String changeListCouponOverdue(List<String> couponNums) {
		if(0 == couponNums.size() ||
				MarketingCouponConstance.COUPONNUMS < couponNums.size()){
			return MarketingCouponConstance.COUPONNUMSISNULL;
		}
		couponManagerImpl.updateCouponStatus(
						MarketingCouponConstance.COUPON_OVERDUE, couponNums);
		return MarketingCouponConstance.PROCESSSUCCESS;
	
	}
	
	/**
	 * <p>
	 * Description: 
	 * 运单作废： 1.如果是手动发券：未发送：优惠券状态变为未发送；已发送：优惠券状态变为已发送；<br />
	 * 			 2.如果是自动发券：优惠券状态变为已发送；<br />
	 *           3.过期的优惠券，状态为已过期。<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNum 优惠券编码（列表）
	 * @return String
	 */
	@Override
	public String changeCouponInvalidWB(String couponNum) {
		// 校验 couponNum 是否为空
		if(StringUtils.isEmpty(couponNum)){
			return MarketingCouponConstance.COUPONNUMSISNULL;
		}
		// 根据优惠券编码 查询优惠券
		Coupon coupon=couponManagerImpl.getCouponByNum(couponNum);
		// 校验优惠券存在不存在
		CouponValidator.checkCouponIsNull(coupon);
		// 根据优惠券编码查询使用规则
		CouponRule rule = couponManagerImpl.getCouponRuleByMarketPlanId(coupon.getMarketPlanId());
		// 校验是有规则不能为空
		CouponValidator.checkCouponRuleIsNotNull(rule);
		// 根据条件返回相应的状态
		String typeId = CouponValidator.checkUseIsOverDue(
				coupon.getTypeId(),new Date().getTime(),rule.getEndtime().getTime(),coupon.getSendtelPhone());
		//状态更新
		couponManagerImpl.updateOneCouponStatu(typeId, couponNum);
		return MarketingCouponConstance.PROCESSSUCCESS;
	}
	/**
	 * <p>
	 * Description: 优惠券规则验证<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-12
	 * @param couponNum
	 * @param wbInfo
	 * @return
	 * String[] 返回数组，
	 * 		下标0：“true”/“false”
	 * 		下表1：true时代表金额，false时代表错误编码
	 * 		下标2: 抵扣类型
	 */
	@Override
	public String[] checkCouponRule(String couponNum, WaybillInfo wbInfo) {
		// 检查运单信息是否符合规则
		CouponValidator.checkWaybillInfoIsNull(wbInfo);
		// 订单号
		String orderNumber = wbInfo.getOrderNumber();
		Order order = null;
		// 订单不为空的话
		if(StringUtils.isNotEmpty(orderNumber)){
			// 通过订单号查询 订单详细信息
			order = orderManager.getOrderWaybill(orderNumber);
			if(null != order){
				// 如果订单不空，get其订单来源放入运单中
				wbInfo.setOrderSource(order.getResource());
			}
		}
		matchBillingByWbinFo(wbInfo);
		return couponManagerImpl.checkCouponRule(couponNum, wbInfo);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.coupon.server.manager.ICouponForFossManager#changeCouponStatusUsing(java.lang.String, com.deppon.crm.module.coupon.shared.domain.WaybillInfo)
	 */
	@Override
	public String[] changeCouponStatusUsing(String couponNum, WaybillInfo wbInfo) {
		String[] rs = this.checkCouponRule(couponNum, wbInfo);
		if(Boolean.valueOf(rs[0])){
			// 根据编码获取优惠券
			Coupon coupon = couponManagerImpl.getCouponByNum(couponNum);
			// 使用运单编码
			coupon.setUseWBNumber(wbInfo.getWaybillNumber());
			// 使用优惠券运单的开单金额
//			Map<String, BigDecimal> aiMap = CouponUtils.amountInfoToMap(wbInfo.getAmountList());
//			coupon.setUseWBValue(aiMap.get(MarketingCouponConstance.COST_TYPE_BILLING).toString());
			coupon.setUseWBValue(wbInfo.getTotalAmount().toString());
			// 优惠券使用时间
			coupon.setUseTime(new Date());
			// 使用手机号码
			coupon.setUsetelPhone(wbInfo.getLeaveMobile());
			// 优惠券使用状态
			coupon.setStatus(MarketingCouponConstance.COUPON_USING);
			// 更新优惠券
			couponManagerImpl.updateCoupon(coupon);
		}
		return rs;
	}
	/**
	 * <p>
	 * Description: 将开单金额，放入运单明细表中<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-1-10
	 * @param couponNum
	 * @param wbInfo
	 * @return wbInfo
	 */
	private WaybillInfo matchBillingByWbinFo(WaybillInfo wbInfo){
		// 创建一个运单金额明细
		AmountInfo amount = new AmountInfo();
		BigDecimal amountWayInfo = wbInfo.getTotalAmount();
		if(null == wbInfo.getTotalAmount()){
			amountWayInfo = new BigDecimal(0);
		}
		// 开单金额 == 运单总额
		amount.setValuationAmonut(amountWayInfo);
		// code == billing（开单金额）
		amount.setValuationCode(MarketingCouponConstance.COST_TYPE_BILLING);
		wbInfo.getAmountList().add(amount);
		return wbInfo;
	}

	public void setCouponManagerImpl(ICouponManager couponManagerImpl) {
		this.couponManagerImpl = couponManagerImpl;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

}
