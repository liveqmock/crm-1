package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:短信发送前台显示<br />
 * </p>
 * @author ZhouYuan
 * @version 0.1 2012-11-21
 */
public class SendCouponVO extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//营销计划ID
	private int marketPlanId;
	//营销计划编码
	private String planNumber;
	//营销计划名称
	private String planName;
	//优惠券类型
	private String couponType;
	//营销计划状态
	private String marketPlanStatus;
	//剩余优惠券数量
	private int balance;
	//营销开始时间
	private Date beginTime;
	//营销结束时间
	private Date endTime;
	//短信内容
	private String sms;
	//优惠券金额
	private String couponValue;
	//优惠券 总数量
	private int couponQuantity;
	//已发送数量
	private int sendedNum;
	//优惠券生成标识
	private String typeId;
	
	/**
	 * <p>
	 * Description: 获得营销计划ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销计划ID
	 */
	public int getMarketPlanId() {
		//营销计划ID
		return marketPlanId;
	}
	/**
	 * <p>
	 * Description: 设置营销计划ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setMarketPlanId(int marketPlanId) {
		//营销计划ID
		this.marketPlanId = marketPlanId;
	}
	/**
	 * <p>
	 * Description: 获得营销计划编码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销计划编码
	 */
	public String getPlanNumber() {
		//营销计划编码
		return planNumber;
	}
	/**
	 * <p>
	 * Description: 设置营销计划编码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setPlanNumber(String planNumber) {
		//营销计划编码
		this.planNumber = planNumber;
	}
	/**
	 * <p>
	 * Description: 获得营销计划名称<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销计划名称
	 */
	public String getPlanName() {
		//营销计划名称
		return planName;
	}
	/**
	 * <p>
	 * Description: 设置营销计划名称<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setPlanName(String planName) {
		//营销计划名称
		this.planName = planName;
	}
	/**
	 * <p>
	 * Description: 获得优惠券类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券类型
	 */
	public String getCouponType() {
		//优惠券类型
		return couponType;
	}
	/**
	 * <p>
	 * Description: 设置优惠券类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCouponType(String couponType) {
		//优惠券类型
		this.couponType = couponType;
	}
	/**
	 * <p>
	 * Description: 获得营销计划状态<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销计划状态
	 */
	public String getMarketPlanStatus() {
		//营销计划状态
		return marketPlanStatus;
	}
	/**
	 * <p>
	 * Description: 设置营销计划状态<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setMarketPlanStatus(String marketPlanStatus) {
		//营销计划状态
		this.marketPlanStatus = marketPlanStatus;
	}
	/**
	 * <p>
	 * Description: 获得剩余优惠券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 剩余优惠券数量
	 */
	public int getBalance() {
		//剩余优惠券数量
		return balance;
	}
	/**
	 * <p>
	 * Description: 设置剩余优惠券数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setBalance(int balance) {
		//剩余优惠券数量
		this.balance = balance;
	}
	/**
	 * <p>
	 * Description: 获得营销开始时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销开始时间
	 */
	public Date getBeginTime() {
		//营销开始时间
		return beginTime;
	}
	/**
	 * <p>
	 * Description: 设置营销开始时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setBeginTime(Date beginTime) {
		//营销开始时间
		this.beginTime = beginTime;
	}
	/**
	 * <p>
	 * Description: 获得营销结束时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销结束时间
	 */
	public Date getEndTime() {
		//营销结束时间
		return endTime;
	}
	/**
	 * <p>
	 * Description: 设置营销结束时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setEndTime(Date endTime) {
		//营销结束时间
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description: 获得短信内容<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 短信内容
	 */
	public String getSms() {
		//短信内容
		return sms;
	}
	/**
	 * <p>
	 * Description: 设置短信内容<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setSms(String sms) {
		//短信内容
		this.sms = sms;
	}
	/**
	 * <p>
	 * Description: 获得优惠券金额<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券金额
	 */
	public String getCouponValue() {
		//优惠券金额
		return couponValue;
	}
	/**
	 * <p>
	 * Description: 设置优惠券金额<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCouponValue(String couponValue) {
		//优惠券金额
		this.couponValue = couponValue;
	}
	/**
	 * <p>
	 * Description: 获得运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public int getCouponQuantity() {
		//优惠券 总数量
		return couponQuantity;
	}
	/**
	 * <p>
	 * Description: 设置运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCouponQuantity(int couponQuantity) {
		//优惠券 总数量
		this.couponQuantity = couponQuantity;
	}
	/**
	 * <p>
	 * Description: 获得已发送数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 已发送数量
	 */
	public int getSendedNum() {
		//已发送数量
		return sendedNum;
	}
	/**
	 * <p>
	 * Description: 设置已发送数量<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setSendedNum(int sendedNum) {
		//已发送数量
		this.sendedNum = sendedNum;
	}
	/**
	 * <p>
	 * Description: 获得券生成标识<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 券生成标识
	 */
	public String getTypeId() {
		//优惠券生成标识
		return typeId;
	}
	/**
	 * <p>
	 * Description: 设置券生成标识<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setTypeId(String typeId) {
		//优惠券生成标识
		this.typeId = typeId;
	}
	
	
}
