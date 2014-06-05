package com.deppon.crm.module.coupon.shared.domain;



import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销计划实体类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-14
 */
public class Marketplan extends BaseEntity{

	private static final long serialVersionUID = 1L;
	// 营销计划编码
	private String planNumber;
	// 营销计划名称
	private String planName;
	// 营销计划状态  未启用、已启用、已终止、已结束
	private String marketStatus;
    // 优惠券类型
	private String couponType;
	// 保存时间
	private Date saveTime;
	//营销计划创建结束时间
	private Date createBeginTime;
	//营销计划创建结束时间
	private Date createEndTime;
	// 发券总数
	private String  couponSendTotal;
	// 未发送数量
	private String  couponNoSendCount;
	// 已发送数量
	private String  couponSendCount;
	// 已使用数量
	private String  couponUseCount;
	// 已过期数量
	private String  couponOverdueCount;
	// 剩余数量
	private String  couponSurplusCount;
	// 优惠券生效时间
	private Date autoBeginTime;
	// 优惠券失效时间
	private Date autoEndTime;
	// 营销计划活动开始时间
	private Date activityBeginTime;
	// 营销计划活动结束时间
	private Date activityEndTime;
	//修改人名字
	private String modifyUserName;
	//业务类型(零担 、快递)
	private String busType;
	
	//市场推广活动ID
	private String activityId;
	//抵扣类型
	private String discountType;
	
	/****************set  get  方法*******************************************/
	/**
	 * <p>
	 * Description: 获得修改人名字<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 修改人名字
	 */
	public String getModifyUserName() {
		//修改人名字
		return modifyUserName;
	}
	/**
	 * <p>
	 * Description: 设置修改人名字<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setModifyUserName(String modifyUserName) {
		//修改人名字
		this.modifyUserName = modifyUserName;
	}
	/**
	 * <p>
	 * Description: 获得营销计划编码<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划编码
	 */
	public String getPlanNumber() {
		// 营销计划编码
		return planNumber;
	}
	/**
	 * <p>
	 * Description: 设置营销计划编码<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setPlanNumber(String planNumber) {
		// 营销计划编码
		this.planNumber = planNumber;
	}
	/**
	 * <p>
	 * Description: 获得营销计划名称<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划名称
	 */
	public String getPlanName() {
		// 营销计划名称
		return planName;
	}
	/**
	 * <p>
	 * Description: 设置营销计划名称<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setPlanName(String planName) {
		// 营销计划名称
		this.planName = planName;
	}
	/**
	 * <p>
	 * Description: 获得营销计划状态  未启用、已启用、已终止、已结束<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划状态  未启用、已启用、已终止、已结束
	 */
	public String getMarketStatus() {
		// 营销计划状态  未启用、已启用、已终止、已结束
		return marketStatus;
	}
	/**
	 * <p>
	 * Description: 设置营销计划状态  未启用、已启用、已终止、已结束<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setMarketStatus(String marketStatus) {
		// 营销计划状态  未启用、已启用、已终止、已结束
		this.marketStatus = marketStatus;
	}
	/**
	 * <p>
	 * Description: 获得优惠券类型<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 优惠券类型
	 */
	public String getCouponType() {
		 // 优惠券类型
		return couponType;
	}
	/**
	 * <p>
	 * Description: 设置优惠券类型<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponType(String couponType) {
		 // 优惠券类型
		this.couponType = couponType;
	}
	/**
	 * <p>
	 * Description: 获得保存时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 保存时间
	 */
	public Date getSaveTime() {
		// 保存时间
		return saveTime;
	}
	/**
	 * <p>
	 * Description: 设置保存时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setSaveTime(Date saveTime) {
		// 保存时间
		this.saveTime = saveTime;
	}
	/**
	 * <p>
	 * Description: 获得运单号<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public Date getCreateEndTime() {
		//营销计划创建结束时间
		return createEndTime;
	}
	/**
	 * <p>
	 * Description: 设置运单号<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCreateEndTime(Date createEndTime) {
		//营销计划创建结束时间
		this.createEndTime = createEndTime;
	}
	/**
	 * <p>
	 * Description: 获得发券总数<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 发券总数
	 */
	public String getCouponSendTotal() {
		// 发券总数
		return couponSendTotal;
	}
	/**
	 * <p>
	 * Description: 设置发券总数<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponSendTotal(String couponSendTotal) {
		// 发券总数
		this.couponSendTotal = couponSendTotal;
	}
	/**
	 * <p>
	 * Description: 获得未发送数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 未发送数量
	 */
	public String getCouponNoSendCount() {
		// 未发送数量
		return couponNoSendCount;
	}
	/**
	 * <p>
	 * Description: 设置未发送数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponNoSendCount(String couponNoSendCount) {
		// 未发送数量
		this.couponNoSendCount = couponNoSendCount;
	}
	/**
	 * <p>
	 * Description: 获得已发送数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 已发送数量
	 */
	public String getCouponSendCount() {
		// 已发送数量
		return couponSendCount;
	}
	/**
	 * <p>
	 * Description: 设置已发送数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponSendCount(String couponSendCount) {
		// 已发送数量
		this.couponSendCount = couponSendCount;
	}
	/**
	 * <p>
	 * Description: 获得已使用数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public String getCouponUseCount() {
		// 已使用数量
		return couponUseCount;
	}
	/**
	 * <p>
	 * Description: 设置已使用数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponUseCount(String couponUseCount) {
		// 已使用数量
		this.couponUseCount = couponUseCount;
	}
	/**
	 * <p>
	 * Description: 获得已过期数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 已过期数量
	 */
	public String getCouponOverdueCount() {
		// 已过期数量
		return couponOverdueCount;
	}
	/**
	 * <p>
	 * Description: 设置已过期数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponOverdueCount(String couponOverdueCount) {
		// 已过期数量
		this.couponOverdueCount = couponOverdueCount;
	}
	/**
	 * <p>
	 * Description: 获得优惠券生效时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public Date getAutoBeginTime() {
		// 优惠券生效时间
		return autoBeginTime;
	}
	/**
	 * <p>
	 * Description: 设置优惠券生效时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setAutoBeginTime(Date autoBeginTime) {
		// 优惠券生效时间
		this.autoBeginTime = autoBeginTime;
	}
	/**
	 * <p>
	 * Description: 获得优惠券失效时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 优惠券失效时间
	 */
	public Date getAutoEndTime() {
		// 优惠券失效时间
		return autoEndTime;
	}
	/**
	 * <p>
	 * Description: 设置优惠券失效时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setAutoEndTime(Date autoEndTime) {
		// 优惠券失效时间
		this.autoEndTime = autoEndTime;
	}
	/**
	 * <p>
	 * Description: 获得营销计划活动开始时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划活动开始时间
	 */
	public Date getActivityBeginTime() {
		// 营销计划活动开始时间
		return activityBeginTime;
	}
	/**
	 * <p>
	 * Description: 设置营销计划活动开始时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setActivityBeginTime(Date activityBeginTime) {
		// 营销计划活动开始时间
		this.activityBeginTime = activityBeginTime;
	}
	/**
	 * <p>
	 * Description: 获得营销计划活动结束时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划活动结束时间
	 */
	public Date getActivityEndTime() {
		// 营销计划活动结束时间
		return activityEndTime;
	}
	/**
	 * <p>
	 * Description: 设置营销计划活动结束时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setActivityEndTime(Date activityEndTime) {
		// 营销计划活动结束时间
		this.activityEndTime = activityEndTime;
	}
	/**
	 * <p>
	 * Description: 获得营销计划创建结束时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 营销计划创建结束时间
	 */
	public Date getCreateBeginTime() {
		//营销计划创建结束时间
		return createBeginTime;
	}
	/**
	 * <p>
	 * Description: 设置营销计划创建结束时间<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCreateBeginTime(Date createBeginTime) {
		//营销计划创建结束时间
		this.createBeginTime = createBeginTime;
	}
	/**
	 * <p>
	 * Description: 获得剩余数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 * @return 剩余数量
	 */
	public String getCouponSurplusCount() {
		// 剩余数量
		return couponSurplusCount;
	}
	/**
	 * <p>
	 * Description: 设置剩余数量<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-02-02
	 */
	public void setCouponSurplusCount(String couponSurplusCount) {
		// 剩余数量
		this.couponSurplusCount = couponSurplusCount;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}


}
