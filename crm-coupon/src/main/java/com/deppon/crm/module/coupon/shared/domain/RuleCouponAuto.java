package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:自动发券规则 实体类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-14
 */
public class RuleCouponAuto extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	// 营销计划ID
	private String marketPlanId;
	// 优惠券类型ID
	private String typeId;
	// 返券期限开始时间
	private Date autoBeginTime;
	// 返券期限结束时间
	private Date autoEndTime;
	// 使用条件信息
	private List<ConditionType> conditionTypes;
	// 返券金额
	private List<AutoCouponCost> autoCouponCost;
	// 线路区域要求 , 10 走货线路、20  发货区域、30 到达区域
	private List<GoodsLine> createGoodsLine;
	
	/****************set  get  方法*******************************************/
	/**
	 * <p>
	 * Description: 获得营销计划ID<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划ID
	 */
	public String getMarketPlanId() {
		// 营销计划ID
		return marketPlanId;
	}
	/**
	 * <p>
	 * Description: 设置营销计划ID<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setMarketPlanId(String marketPlanId) {
		// 营销计划ID
		this.marketPlanId = marketPlanId;
	}
	/**
	 * <p>
	 * Description: 获得优惠券类型ID<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 优惠券类型ID
	 */
	public String getTypeId() {
		// 优惠券类型ID
		return typeId;
	}
	/**
	 * <p>
	 * Description: 设置优惠券类型ID<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setTypeId(String typeId) {
		// 优惠券类型ID
		this.typeId = typeId;
	}
	/**
	 * <p>
	 * Description: 获得返券期限开始时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 返券期限开始时间
	 */
	public Date getAutoBeginTime() {
		// 返券期限开始时间
		return autoBeginTime;
	}
	/**
	 * <p>
	 * Description: 设置返券期限开始时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setAutoBeginTime(Date autoBeginTime) {
		// 返券期限开始时间
		this.autoBeginTime = autoBeginTime;
	}
	/**
	 * <p>
	 * Description: 获得返券期限结束时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 返券期限结束时间
	 */
	public Date getAutoEndTime() {
		// 返券期限结束时间
		return autoEndTime;
	}
	/**
	 * <p>
	 * Description: 设置返券期限结束时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setAutoEndTime(Date autoEndTime) {
		// 返券期限结束时间
		this.autoEndTime = autoEndTime;
	}
	/**
	 * <p>
	 * Description: 获得使用条件信息<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 使用条件信息
	 */
	public List<ConditionType> getConditionTypes() {
		// 使用条件信息
		return conditionTypes;
	}
	/**
	 * <p>
	 * Description: 设置使用条件信息<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setConditionTypes(List<ConditionType> conditionTypes) {
		// 使用条件信息
		this.conditionTypes = conditionTypes;
	}
	/**
	 * <p>
	 * Description: 获得返券金额<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 返券金额
	 */
	public List<AutoCouponCost> getAutoCouponCost() {
		// 返券金额
		return autoCouponCost;
	}
	/**
	 * <p>
	 * Description: 设置返券金额<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setAutoCouponCost(List<AutoCouponCost> autoCouponCost) {
		// 返券金额
		this.autoCouponCost = autoCouponCost;
	}
	/**
	 * <p>
	 * Description: 获得线路区域要求 , 10 走货线路、20  发货区域、30 到达区域<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 线路区域要求 , 10 走货线路、20  发货区域、30 到达区域
	 */
	public List<GoodsLine> getCreateGoodsLine() {
		// 线路区域要求 , 10 走货线路、20  发货区域、30 到达区域
		return createGoodsLine;
	}
	/**
	 * <p>
	 * Description: 设置线路区域要求 , 10 走货线路、20  发货区域、30 到达区域<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCreateGoodsLine(List<GoodsLine> createGoodsLine) {
		// 线路区域要求 , 10 走货线路、20  发货区域、30 到达区域
		this.createGoodsLine = createGoodsLine;
	} 
}
