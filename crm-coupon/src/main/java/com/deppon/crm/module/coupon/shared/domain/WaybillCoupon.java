package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

public class WaybillCoupon extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//运单号
	private String waybillNumber;
	//营销计划ID 
	private String marketPlanId;
	//营销计划编码
	private String marketPlanNumber;
	//优惠券类型
	private String couponType;
	//优惠券归属部门
	private String underDept;
	//发送优惠券手机号
	private String sendTelPhone;
	//来源运单开单金额
	private String wbValue;
	//优惠券金额
	private String value;
	//记录使用标识(0,1)0未使用 1已使用
	private String status;
	//记录创建时间
	private Date createTime;
	//优惠券生成规则ID
	private String createRuleId;
	//优惠券使用规则ID 
	private String useRuleId;
	//短信发送内容
	private String msgContent;
	//优惠券使用开始时间
	private Date beginTime;
	//优惠券使用结束时间
	private Date endTime;
	/**
	 * <p>
	 * Description: 获得运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public String getWaybillNumber() {
		//运单号
		return waybillNumber;
	}
	/**
	 * <p>
	 * Description: 设置运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setWaybillNumber(String waybillNumber) {
		//运单号
		this.waybillNumber = waybillNumber;
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
	 * Description: 获得营销计划ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 营销计划ID
	 */
	public String getMarketPlanId() {
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
	public void setMarketPlanId(String marketPlanId) {
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
	public String getMarketPlanNumber() {
		//营销计划编码
		return marketPlanNumber;
	}
	/**
	 * <p>
	 * Description: 设置营销计划编码<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setMarketPlanNumber(String marketPlanNumber) {
		//营销计划编码
		this.marketPlanNumber = marketPlanNumber;
	}
	/**
	 * <p>
	 * Description: 获得优惠券归属部门<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券归属部门
	 */
	public String getUnderDept() {
		//优惠券归属部门
		return underDept;
	}
	/**
	 * <p>
	 * Description: 设置优惠券归属部门<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setUnderDept(String underDept) {
		//优惠券归属部门
		this.underDept = underDept;
	}
	/**
	 * <p>
	 * Description: 获得发送优惠券手机号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 发送优惠券手机号
	 */
	public String getSendTelPhone() {
		//发送优惠券手机号
		return sendTelPhone;
	}
	/**
	 * <p>
	 * Description: 设置发送优惠券手机号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setSendTelPhone(String sendTelPhone) {
		//发送优惠券手机号
		this.sendTelPhone = sendTelPhone;
	}
	/**
	 * <p>
	 * Description: 获得来源运单开单金额<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 来源运单开单金额
	 */
	public String getWbValue() {
		//来源运单开单金额
		return wbValue;
	}
	/**
	 * <p>
	 * Description: 设置来源运单开单金额<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setWbValue(String wbValue) {
		//来源运单开单金额
		this.wbValue = wbValue;
	}
	/**
	 * <p>
	 * Description: 获得优惠券金额<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券金额
	 */
	public String getValue() {
		//优惠券金额
		return value;
	}
	/**
	 * <p>
	 * Description: 设置运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setValue(String value) {
		//优惠券金额
		this.value = value;
	}
	/**
	 * <p>
	 * Description: 获得运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public String getStatus() {
		//记录使用标识(0,1)0未使用 1已使用
		return status;
	}
	/**
	 * <p>
	 * Description: 设置运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setStatus(String status) {
		//记录使用标识(0,1)0未使用 1已使用
		this.status = status;
	}
	/**
	 * <p>
	 * Description: 获得运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 运单号
	 */
	public Date getCreateTime() {
		//记录创建时间
		return createTime;
	}
	/**
	 * <p>
	 * Description: 设置运单号<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCreateTime(Date createTime) {
		//记录创建时间
		this.createTime = createTime;
	}
	/**
	 * <p>
	 * Description: 获得优惠券生成规则ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券生成规则ID
	 */
	public String getCreateRuleId() {
		//优惠券生成规则ID
		return createRuleId;
	}
	/**
	 * <p>
	 * Description: 设置优惠券生成规则ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setCreateRuleId(String createRuleId) {
		//优惠券生成规则ID
		this.createRuleId = createRuleId;
	}
	/**
	 * <p>
	 * Description: 获得优惠券使用规则ID <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券使用规则ID 
	 */
	public String getUseRuleId() {
		//优惠券使用规则ID 
		return useRuleId;
	}
	/**
	 * <p>
	 * Description: 设置优惠券使用规则ID <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setUseRuleId(String useRuleId) {
		//优惠券使用规则ID 
		this.useRuleId = useRuleId;
	}
	/**
	 * <p>
	 * Description: 获得短信发送内容<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 短信发送内容
	 */
	public String getMsgContent() {
		//短信发送内容
		return msgContent;
	}
	/**
	 * <p>
	 * Description: 设置短信发送内容<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setMsgContent(String msgContent) {
		//短信发送内容
		this.msgContent = msgContent;
	}
	/**
	 * <p>
	 * Description: 获得优惠券使用开始时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券使用开始时间
	 */
	public Date getBeginTime() {
		//优惠券使用开始时间
		return beginTime;
	}
	/**
	 * <p>
	 * Description: 设置优惠券使用开始时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setBeginTime(Date beginTime) {
		//优惠券使用开始时间
		this.beginTime = beginTime;
	}
	/**
	 * <p>
	 * Description: 获得优惠券使用结束时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 * @return 优惠券使用结束时间
	 */
	public Date getEndTime() {
		//优惠券使用结束时间
		return endTime;
	}
	/**
	 * <p>
	 * Description: 设置优惠券使用结束时间<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-02-02
	 */
	public void setEndTime(Date endTime) {
		//优惠券使用结束时间
		this.endTime = endTime;
	} 

}	
