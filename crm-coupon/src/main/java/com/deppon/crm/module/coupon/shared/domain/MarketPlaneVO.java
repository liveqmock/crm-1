package com.deppon.crm.module.coupon.shared.domain;


import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:新增营销计划-发券规则-手动<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-14
 * @param marketPlaneVO 新增营销计划-发券规则VO
 */
public class MarketPlaneVO extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 690168618983359703L;
	/*****************设置成员变量 开始******************************************/
	// 营销计划
	private Marketplan marketplan;
	// 手动发券
	private RuleCouponHand ruleCouponHand;
	// 自动发券
	private RuleCouponAuto ruleCouponAuto;
	// 优惠券使用条件
	private CouponRule couponRule;
	/*****************设置成员变量 结束***************************************************/
	/**
	 * <p>
	 * Description: 获得营销计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划
	 */
	public Marketplan getMarketplan() {
		// 营销计划
		return marketplan;
	}
	/**
	 * <p>
	 * Description: 设置营销计划<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setMarketplan(Marketplan marketplan) {
		// 营销计划
		this.marketplan = marketplan;
	}
	/**
	 * <p>
	 * Description: 获得手动发券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 手动发券
	 */
	public RuleCouponHand getRuleCouponHand() {
		// 手动发券
		return ruleCouponHand;
	}
	/**
	 * <p>
	 * Description: 设置手动发券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setRuleCouponHand(RuleCouponHand ruleCouponHand) {
		// 手动发券
		this.ruleCouponHand = ruleCouponHand;
	}
	/**
	 * <p>
	 * Description: 获得自动发券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 自动发券
	 */
	public RuleCouponAuto getRuleCouponAuto() {
		// 自动发券
		return ruleCouponAuto;
	}
	/**
	 * <p>
	 * Description: 设置自动发券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setRuleCouponAuto(RuleCouponAuto ruleCouponAuto) {
		// 自动发券
		this.ruleCouponAuto = ruleCouponAuto;
	}
	/**
	 * <p>
	 * Description: 获得优惠券使用条件<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 优惠券使用条件
	 */
	public CouponRule getCouponRule() {
		// 优惠券使用条件
		return couponRule;
	}
	/**
	 * <p>
	 * Description: 设置优惠券使用条件<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponRule(CouponRule couponRule) {
		// 优惠券使用条件
		this.couponRule = couponRule;
	}
	
	/***********************set  get  方法*********************************************/

	
}
