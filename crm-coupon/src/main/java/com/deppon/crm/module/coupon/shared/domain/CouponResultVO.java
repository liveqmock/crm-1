/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponResultVO.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-21
 */
package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 优惠券查询结果<br />
 * </p>
 * @title CouponResultVO.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-21
 */

public class CouponResultVO extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 营销计划名称	-MarketPlan
	private String planName;
	// 优惠券编码
	private String couponNumber;
	// 优惠券金额
	private String useCouponValue;
	// 优惠券状态
	private String status;
	// 生效日期(使用期限开始时间) -CouponRule
	private Date begintime;
	// 失效日期(使用期限结束时间) -CouponRule
	private Date endtime;
	// 金额要求(使用优惠券的金额要求) -CouponRule 金额类型对应的金额值
	private String value;
	// 来源运单号（来源运单编码）
	private String sourceWBNumber;
	// 来源运单金额（生成优惠券运单的开单金额）
	private String sourceWBValue;
	// 使用运单号（使用运单编码）
	private String useWBNumber;
	// 使用运单金额（使用优惠券运单的开单金额）
	private String useWBValue;
	// 发送手机号码 （优惠券发送手机号码）
	private String sendtelPhone;
	// 发送时间（短信发送时间）
	private Date sendTime;
	// 使用手机号码（优惠券发送使用号码）
	private String usetelPhone;
	// 使用时间（优惠券使用时间）
	private Date useTime;
	// 优惠券归属部门
	private String underDept;
	// 优惠券归属部门(名称)
	private String underDeptName;
	// 营销计划ID
	private String marketPlanId;
	
	/**
	 * @return underDeptName : return the property underDeptName.
	 */
	public String getUnderDeptName() {
		return underDeptName;
	}
	/**
	 * @param underDeptName : set the property underDeptName.
	 */
	public void setUnderDeptName(String underDeptName) {
		this.underDeptName = underDeptName;
	}
	/**
	 * @return marketPlanId : return the property marketPlanId.
	 */
	public String getMarketPlanId() {
		// 营销计划ID	-MarketPlan
		return marketPlanId;
	}
	/**
	 * @param marketPlanId : set the property marketPlanId.
	 */
	public void setMarketPlanId(String marketPlanId) {
		// 营销计划ID	-MarketPlan
		this.marketPlanId = marketPlanId;
	}
	/**
	 * @return planName : return the property planName.
	 */
	public String getPlanName() {
		// 营销计划名称	-MarketPlan
		return planName;
	}
	/**
	 * @param planName : set the property planName.
	 */
	public void setPlanName(String planName) {
		// 营销计划名称	-MarketPlan
		this.planName = planName;
	}
	/**
	 * @return couponNumber : return the property couponNumber.
	 */
	public String getCouponNumber() {
		// 优惠券编码
		return couponNumber;
	}
	/**
	 * @param couponNumber : set the property couponNumber.
	 */
	public void setCouponNumber(String couponNumber) {
		// 优惠券编码
		this.couponNumber = couponNumber;
	}
	/**
	 * @return useCouponValue : return the property useCouponValue.
	 */
	public String getUseCouponValue() {
		// 使用运单金额（使用优惠券运单的开单金额）
		return useCouponValue;
	}
	/**
	 * @param useCouponValue : set the property useCouponValue.
	 */
	public void setUseCouponValue(String useCouponValue) {
		// 使用运单金额（使用优惠券运单的开单金额）
		this.useCouponValue = useCouponValue;
	}
	/**
	 * @return status : return the property status.
	 */
	public String getStatus() {
		// 优惠券状态
		return status;
	}
	/**
	 * @param status : set the property status.
	 */
	public void setStatus(String status) {
		// 优惠券状态
		this.status = status;
	}
	/**
	 * @return begintime : return the property begintime.
	 */
	public Date getBegintime() {
		// 生效日期(使用期限开始时间) -CouponRule
		return begintime;
	}
	/**
	 * @param begintime : set the property begintime.
	 */
	public void setBegintime(Date begintime) {
		// 生效日期(使用期限开始时间) -CouponRule
		this.begintime = begintime;
	}
	/**
	 * @return endtime : return the property endtime.
	 */
	public Date getEndtime() {
		// 失效日期(使用期限结束时间) -CouponRule
		return endtime;
	}
	/**
	 * @param endtime : set the property endtime.
	 */
	public void setEndtime(Date endtime) {
		// 失效日期(使用期限结束时间) -CouponRule
		this.endtime = endtime;
	}
	/**
	 * @return value : return the property value.
	 */
	public String getValue() {
		// 金额要求(使用优惠券的金额要求) -CouponRule 金额类型对应的金额值
		return value;
	}
	/**
	 * @param value : set the property value.
	 */
	public void setValue(String value) {
		// 金额要求(使用优惠券的金额要求) -CouponRule 金额类型对应的金额值
		this.value = value;
	}
	/**
	 * @return sourceWBNumber : return the property sourceWBNumber.
	 */
	public String getSourceWBNumber() {
		// 来源运单号（来源运单编码）
		return sourceWBNumber;
	}
	/**
	 * @param sourceWBNumber : set the property sourceWBNumber.
	 */
	public void setSourceWBNumber(String sourceWBNumber) {
		// 来源运单号（来源运单编码）
		this.sourceWBNumber = sourceWBNumber;
	}
	/**
	 * @return sourceWBValue : return the property sourceWBValue.
	 */
	public String getSourceWBValue() {
		// 来源运单金额（生成优惠券运单的开单金额）
		return sourceWBValue;
	}
	/**
	 * @param sourceWBValue : set the property sourceWBValue.
	 */
	public void setSourceWBValue(String sourceWBValue) {
		// 来源运单金额（生成优惠券运单的开单金额）
		this.sourceWBValue = sourceWBValue;
	}
	/**
	 * @return useWBNumber : return the property useWBNumber.
	 */
	public String getUseWBNumber() {
		// 使用运单号（使用运单编码）
		return useWBNumber;
	}
	/**
	 * @param useWBNumber : set the property useWBNumber.
	 */
	public void setUseWBNumber(String useWBNumber) {
		// 使用运单号（使用运单编码）
		this.useWBNumber = useWBNumber;
	}
	/**
	 * @return useWBValue : return the property useWBValue.
	 */
	public String getUseWBValue() {
		// 使用运单金额
		return useWBValue;
	}
	/**
	 * @param useWBValue : set the property useWBValue.
	 */
	public void setUseWBValue(String useWBValue) {
		// 使用运单金额
		this.useWBValue = useWBValue;
	}
	/**
	 * @return sendtelPhone : return the property sendtelPhone.
	 */
	public String getSendtelPhone() {
		// 发送手机号码 （优惠券发送手机号码）
		return sendtelPhone;
	}
	/**
	 * @param sendtelPhone : set the property sendtelPhone.
	 */
	public void setSendtelPhone(String sendtelPhone) {
		// 发送手机号码 （优惠券发送手机号码）
		this.sendtelPhone = sendtelPhone;
	}
	/**
	 * @return sendTime : return the property sendTime.
	 */
	public Date getSendTime() {
		// 发送时间（短信发送时间）
		return sendTime;
	}
	/**
	 * @param sendTime : set the property sendTime.
	 */
	public void setSendTime(Date sendTime) {
		// 发送时间（短信发送时间）
		this.sendTime = sendTime;
	}
	/**
	 * @return usetelPhone : return the property usetelPhone.
	 */
	public String getUsetelPhone() {
		// 使用手机号码（优惠券发送使用号码）
		return usetelPhone;
	}
	/**
	 * @param usetelPhone : set the property usetelPhone.
	 */
	public void setUsetelPhone(String usetelPhone) {
		// 使用手机号码（优惠券发送使用号码）
		this.usetelPhone = usetelPhone;
	}
	/**
	 * @return useTime : return the property useTime.
	 */
	public Date getUseTime() {
		// 使用时间（优惠券使用时间）
		return useTime;
	}
	/**
	 * @param useTime : set the property useTime.
	 */
	public void setUseTime(Date useTime) {
		// 使用时间（优惠券使用时间）
		this.useTime = useTime;
	}
	/**
	 * @return underDept : return the property underDept.
	 */
	public String getUnderDept() {
		// 优惠券归属部门
		return underDept;
	}
	/**
	 * @param underDept : set the property underDept.
	 */
	public void setUnderDept(String underDept) {
		// 优惠券归属部门
		this.underDept = underDept;
	}

}
