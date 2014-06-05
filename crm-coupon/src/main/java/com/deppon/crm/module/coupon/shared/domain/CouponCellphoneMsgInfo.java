package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:优惠劵短信发送实体<br />
 * </p>
 * @author ZhouYuan
 * @version 0.1 2012-11-21
 */
public class CouponCellphoneMsgInfo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//优惠劵编码
	private String couponNumber;
	//发送手机号码
	private String phoneNumber;
	//短信内容
	private String msgContent;
	//发送部门编码
	private String sendStandardDeptCode;
	//发送员工编码
	private String senderEmpCode;
	//发送状态
	private String sended;
	//发送时间
	private Date createTime;
	
	/**
	 * @return type : return the property type.
	 */
	public String getCouponNumber() {
		//优惠劵编码
		return couponNumber;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setCouponNumber(String couponNumber) {
		//优惠劵编码
		this.couponNumber = couponNumber;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getPhoneNumber() {
		//发送手机号码
		return phoneNumber;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setPhoneNumber(String phoneNumber) {
		//发送手机号码
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getMsgContent() {
		//短信内容
		return msgContent;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setMsgContent(String msgContent) {
		//短信内容
		this.msgContent = msgContent;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getSendStandardDeptCode() {
		//发送部门编码
		return sendStandardDeptCode;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setSendStandardDeptCode(String sendStandardDeptCode) {
		//发送部门编码
		this.sendStandardDeptCode = sendStandardDeptCode;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getSenderEmpCode() {
		//发送员工编码
		return senderEmpCode;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setSenderEmpCode(String senderEmpCode) {
		//发送员工编码
		this.senderEmpCode = senderEmpCode;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getSended() {
		//发送状态
		return sended;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setSended(String sended) {
		//发送状态
		this.sended = sended;
	}
	/**
	 * @return type : return the property type.
	 */
	public Date getCreateTime() {
		//发送时间
		return createTime;
	}
	/**
	 * @return type : return the property type.
	 */
	public void setCreateTime(Date createTime) {
		//发送时间
		this.createTime = createTime;
	}
	/**
	 * @return type : return the property type.
	 */
	public String toString(){
		return "{"+couponNumber+" :"+phoneNumber+" :"+msgContent+" :"+sendStandardDeptCode+" :"
				+senderEmpCode+" :"+sended+" :"+createTime+"}";
	}
}
