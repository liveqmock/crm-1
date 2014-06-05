package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:优惠券实体类<br />
 * </p>
 * @author 钟琼
 * @version 0.1 2012-11-13
 */
public class Coupon extends BaseEntity{
	/** 
	 * @fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;
	//短信重发次数
	private int smstimes;
	//短信发送时间
	private Date sendTime;
	//优惠券生成时间
	private Date createTime;
	//优惠券使用时间
	private Date useTime;
	//优惠券编码
	private String couponNumber;
	//营销计划ID
	private String marketPlanId;
	//优惠券类型ID
	private String typeId;
	//优惠券生成规则ID
	private String createruleId;
	//优惠券使用规则ID
	private String useruleId;
	//优惠券状态
	private String status;
	//优惠券归属部门
	private String underDept;
	//优惠券发送手机号码
	private String sendtelPhone;
	//优惠券发送使用号码
	private String usetelPhone;
	//来源运单编码
	private String sourceWBNumber;
	//生成优惠券运单的开单金额
	private String sourceWBValue;
	//使用运单编码
	private String useWBNumber;
	//使用优惠券运单的开单金额
	private String useWBValue;
	//使用优惠券的面值
	private String useCouponValue;
	
/***************************set get 方法*********************************************/
	/**
	 * 短信重发次数的get方法
	 * @return
	 */
	public int getSmstimes() {
		//短信重发次数
		return smstimes;
	}
	/**
	 * 短信重发次数的set方法
	 * @return
	 */
	public void setSmstimes(int smstimes) {
		//短信重发次数
		this.smstimes = smstimes;
	}
	/**
	 * 短信发送时间的get方法
	 * @return
	 */
	public Date getSendTime() {
		//短信发送时间
		return sendTime;
	}
	/**
	 * 短信发送时间的set方法
	 * @return
	 */
	public void setSendTime(Date sendTime) {
		//短信发送时间
		this.sendTime = sendTime;
	}
	/**
	 * 优惠券生成时间的get方法
	 * @return
	 */
	public Date getCreateTime() {
		//优惠券生成时间
		return createTime;
	}
	/**
	 * 优惠券生成时间的set方法
	 * @return
	 */
	public void setCreateTime(Date createTime) {
		//优惠券生成时间
		this.createTime = createTime;
	}
	/**
	 * 优惠券使用时间的get方法
	 * @return
	 */
	public Date getUseTime() {
		//优惠券使用时间
		return useTime;
	}
	/**
	 * 优惠券使用时间的set方法
	 * @return
	 */
	public void setUseTime(Date useTime) {
		//优惠券使用时间
		this.useTime = useTime;
	}
	/**
	 * 优惠券编码的get方法
	 * @return
	 */
	public String getCouponNumber() {
		//优惠券编码
		return couponNumber;
	}
	/**
	 * 优惠券编码的set方法
	 * @return
	 */
	public void setCouponNumber(String couponNumber) {
		//优惠券编码
		this.couponNumber = couponNumber;
	}
	/**
	 * 营销计划ID的get方法
	 * @return
	 */
	public String getMarketPlanId() {
		//营销计划ID
		return marketPlanId;
	}
	/**
	 * 营销计划ID的set方法
	 * @return
	 */
	public void setMarketPlanId(String marketPlanId) {
		//营销计划ID
		this.marketPlanId = marketPlanId;
	}
	/**
	 * 优惠券类型ID的get方法
	 * @return
	 */
	public String getTypeId() {
		//优惠券类型ID
		return typeId;
	}
	/**
	 * 优惠券类型ID的set方法
	 * @return
	 */
	public void setTypeId(String typeId) {
		//优惠券类型ID
		this.typeId = typeId;
	}
	/**
	 * 优惠券生成规则ID的get方法
	 * @return
	 */
	public String getCreateruleId() {
		//优惠券生成规则ID
		return createruleId;
	}
	/**
	 * 优惠券生成规则ID的set方法
	 * @return
	 */
	public void setCreateruleId(String createruleId) {
		//优惠券生成规则ID
		this.createruleId = createruleId;
	}
	/**
	 * 优惠券使用规则ID的get方法
	 * @return
	 */
	public String getUseruleId() {
		//优惠券使用规则ID
		return useruleId;
	}
	/**
	 * 优惠券使用规则ID的set方法
	 * @return
	 */
	public void setUseruleId(String useruleId) {
		//优惠券使用规则ID
		this.useruleId = useruleId;
	}
	/**
	 * 优惠券状态的get方法
	 * @return
	 */
	public String getStatus() {
		//优惠券状态
		return status;
	}
	/**
	 * 优惠券状态的set方法
	 * @return
	 */
	public void setStatus(String status) {
		//优惠券状态
		this.status = status;
	}
	/**
	 * 优惠券归属部门的get方法
	 * @return
	 */
	public String getUnderDept() {
		//优惠券归属部门
		return underDept;
	}
	/**
	 * 优惠券归属部门的set方法
	 * @return
	 */
	public void setUnderDept(String underDept) {
		//优惠券归属部门
		this.underDept = underDept;
	}
	/**
	 * 优惠券发送手机号码的get方法
	 * @return
	 */
	public String getSendtelPhone() {
		//优惠券发送手机号码
		return sendtelPhone;
	}
	/**
	 * 优惠券发送手机号码的set方法
	 * @return
	 */
	public void setSendtelPhone(String sendtelPhone) {
		//优惠券发送手机号码
		this.sendtelPhone = sendtelPhone;
	}
	/**
	 * 优惠券发送使用号码的get方法
	 * @return
	 */
	public String getUsetelPhone() {
		//优惠券发送使用号码
		return usetelPhone;
	}
	/**
	 * 优惠券发送使用号码的set方法
	 * @return
	 */
	public void setUsetelPhone(String usetelPhone) {
		//优惠券发送使用号码
		this.usetelPhone = usetelPhone;
	}
	/**
	 * 来源运单编码的get方法
	 * @return
	 */
	public String getSourceWBNumber() {
		//来源运单编码
		return sourceWBNumber;
	}
	/**
	 * 来源运单编码的set方法
	 * @return
	 */
	public void setSourceWBNumber(String sourceWBNumber) {
		//来源运单编码
		this.sourceWBNumber = sourceWBNumber;
	}
	/**
	 * 生成优惠券运单的开单金额的get方法
	 * @return
	 */
	public String getSourceWBValue() {
		//生成优惠券运单的开单金额
		return sourceWBValue;
	}
	/**
	 * 生成优惠券运单的开单金额的set方法
	 * @return
	 */
	public void setSourceWBValue(String sourceWBValue) {
		//生成优惠券运单的开单金额
		this.sourceWBValue = sourceWBValue;
	}
	/**
	 * 使用运单编码的get方法
	 * @return
	 */
	public String getUseWBNumber() {
		//使用运单编码
		return useWBNumber;
	}
	/**
	 * 使用运单编码的set方法
	 * @return
	 */
	public void setUseWBNumber(String useWBNumber) {
		//使用运单编码
		this.useWBNumber = useWBNumber;
	}
	/**
	 * 使用优惠券运单的开单金额的get方法
	 * @return
	 */
	public String getUseWBValue() {
		//使用优惠券运单的开单金额
		return useWBValue;
	}
	/**
	 * 使用优惠券运单的开单金额的set方法
	 * @return
	 */
	public void setUseWBValue(String useWBValue) {
		//使用优惠券运单的开单金额
		this.useWBValue = useWBValue;
	}
	/**
	 * 使用优惠券的面值的get方法
	 * @return
	 */
	public String getUseCouponValue() {
		//使用优惠券的面值
		return useCouponValue;
	}
	/**
	 * 使用优惠券的面值的set方法
	 * @return
	 */
	public void setUseCouponValue(String useCouponValue) {
		//使用优惠券的面值
		this.useCouponValue = useCouponValue;
	}
	
}
