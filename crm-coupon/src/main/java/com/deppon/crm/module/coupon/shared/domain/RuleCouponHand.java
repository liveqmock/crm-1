package com.deppon.crm.module.coupon.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:手动发券规则 实体类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-14
 */
public class RuleCouponHand extends BaseEntity{

	private static final long serialVersionUID = 1L;
	// 营销计划ID
	private String marketPlanId;
	// 优惠券类型ID
	private String typeId;
	// 优惠券金额
	private String couponValue;
	// 优惠券数量
	private String couponQuantity;
	//部门标杆编码
	private String deptStCode;
	//活动类型
	private String activityType;

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
	 * Description: 获得优惠券金额<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 优惠券金额
	 */
	public String getCouponValue() {
		// 优惠券金额
		return couponValue;
	}
	/**
	 * <p>
	 * Description: 设置优惠券金额<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponValue(String couponValue) {
		// 优惠券金额
		this.couponValue = couponValue;
	}
	/**
	 * <p>
	 * Description: 获得优惠券数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 优惠券数量
	 */
	public String getCouponQuantity() {
		// 优惠券数量
		return couponQuantity;
	}
	/**
	 * <p>
	 * Description: 设置优惠券数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponQuantity(String couponQuantity) {
		// 优惠券数量
		this.couponQuantity = couponQuantity;
	}
	/**
	 * <p>
	 * Description: 设置部门标杆编码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 */
	public String getDeptStCode() {
		return deptStCode;
	}
	/**
	 * <p>
	 * Description: 设置部门标杆编码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 */
	public void setDeptStCode(String deptStCode) {
		this.deptStCode = deptStCode;
	}
	/**
	 * <p>
	 * Description: 设置活动类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * <p>
	 * Description: 设置活动类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	} 
	
}
