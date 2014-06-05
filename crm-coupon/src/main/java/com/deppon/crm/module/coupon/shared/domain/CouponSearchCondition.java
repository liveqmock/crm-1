/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponSearchCondition.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-21
 */
package com.deppon.crm.module.coupon.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description: 优惠券查询条件<br />
 * </p>
 * @title CouponSearchCondition.java
 * @package com.deppon.crm.module.coupon.shared.domain 
 * @author ZhuPJ
 * @version 0.1 2012-11-21
 */

public class CouponSearchCondition extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 归属部门
	private String underDept;
	// 营销计划名称
	private String planName;
	// 营销计划编码
	private String planNumber;
	// 优惠券编码
	private String couponNumber;
	// 手机号码（优惠券发送手机号码）
	private String sendtelPhone;
	//	优惠券状态
	private String status;
	// 使用时间（优惠券使用时间）- 开始时间
	private Date useTimeStart;
	// 使用时间（优惠券使用时间）- 结束时间
	private Date useTimeEnd;
	// 发送时间（短信发送时间）- 开始时间
	private Date sendTimeStart;
	// 发送时间（短信发送时间）- 结束时间
	private Date sendTimeEnd;
	// 部门seq编码（查询条件）
	private String deptSeq;
	// 操作用户ID
	private String usrId;
	// 操作用户所属部门标杆编码-用于区别身份（DP07139）该部门下用户可以查询到手动券
	private String usrDept;
	// 事业部标志
	private String managerDept;
	
	/**
	 * @return the managerDept
	 */
	public String getManagerDept() {
		return managerDept;
	}
	/**
	 * @param managerDept the managerDept to set
	 */
	public void setManagerDept(String managerDept) {
		this.managerDept = managerDept;
	}
	/**
	 * @return usrDept : return the property usrDept.
	 */
	public String getUsrDept() {
		// 操作用户所属部门标杆编码-用于区别身份（DP07139）该部门下用户可以查询到手动券
		return usrDept;
	}
	/**
	 * @param usrDept : set the property usrDept.
	 */
	public void setUsrDept(String usrDept) {
		// 操作用户所属部门标杆编码-用于区别身份（DP07139）该部门下用户可以查询到手动券
		this.usrDept = usrDept;
	}
	/**
	 * @return usrId : return the property usrId.
	 */
	public String getUsrId() {
		// 操作用户ID
		return usrId;
	}
	/**
	 * @param usrId : set the property usrId.
	 */
	public void setUsrId(String usrId) {
		// 操作用户ID
		this.usrId = usrId;
	}
	/**
	 * @return deptSeq : return the property deptSeq.
	 */
	public String getDeptSeq() {
		// 部门seq编码（查询条件
		return deptSeq;
	}
	/**
	 * @param deptSeq : set the property deptSeq.
	 */
	public void setDeptSeq(String deptSeq) {
		// 部门seq编码（查询条件
		this.deptSeq = deptSeq;
	}
	/**
	 * @return planNumber : return the property planNumber.
	 */
	public String getPlanNumber() {
		// 营销计划编码
		return planNumber;
	}
	/**
	 * @param planNumber : set the property planNumber.
	 */
	public void setPlanNumber(String planNumber) {
		// 营销计划编码
		this.planNumber = planNumber;
	}
	/**
	 * @return useTimeStart : return the property useTimeStart.
	 */
	public Date getUseTimeStart() {
		// 使用时间（优惠券使用时间）- 开始时间
		return useTimeStart;
	}
	/**
	 * @param useTimeStart : set the property useTimeStart.
	 */
	public void setUseTimeStart(Date useTimeStart) {
		// 使用时间（优惠券使用时间）- 开始时间
		this.useTimeStart = useTimeStart;
	}
	/**
	 * @return useTimeEnd : return the property useTimeEnd.
	 */
	public Date getUseTimeEnd() {
		// 使用时间（优惠券使用时间）- 结束时间
		return useTimeEnd;
	}
	/**
	 * @param useTimeEnd : set the property useTimeEnd.
	 */
	public void setUseTimeEnd(Date useTimeEnd) {
		// 使用时间（优惠券使用时间）- 结束时间
		this.useTimeEnd = useTimeEnd;
	}
	/**
	 * @return sendTimeStart : return the property sendTimeStart.
	 */
	public Date getSendTimeStart() {
		// 发送时间（短信发送时间）- 开始时间
		return sendTimeStart;
	}
	/**
	 * @param sendTimeStart : set the property sendTimeStart.
	 */
	public void setSendTimeStart(Date sendTimeStart) {
		// 发送时间（短信发送时间）- 开始时间
		this.sendTimeStart = sendTimeStart;
	}
	/**
	 * @return sendTimeEnd : return the property sendTimeEnd.
	 */
	public Date getSendTimeEnd() {
		// 发送时间（短信发送时间）- 结束时间
		return sendTimeEnd;
	}
	/**
	 * @param sendTimeEnd : set the property sendTimeEnd.
	 */
	public void setSendTimeEnd(Date sendTimeEnd) {
		// 发送时间（短信发送时间）- 结束时间
		this.sendTimeEnd = sendTimeEnd;
	}
	/**
	 * @return underDept : return the property underDept.
	 */
	public String getUnderDept() {
		// 归属部门
		return underDept;
	}
	/**
	 * @param underDept : set the property underDept.
	 */
	public void setUnderDept(String underDept) {
		// 归属部门
		this.underDept = underDept;
	}
	/**
	 * @return planName : return the property planName.
	 */
	public String getPlanName() {
		// 营销计划名称
		return planName;
	}
	/**
	 * @param planName : set the property planName.
	 */
	public void setPlanName(String planName) {
		// 营销计划名称
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
	 * @return sendtelPhone : return the property sendtelPhone.
	 */
	public String getSendtelPhone() {
		// 手机号码（优惠券发送手机号码）
		return sendtelPhone;
	}
	/**
	 * @param sendtelPhone : set the property sendtelPhone.
	 */
	public void setSendtelPhone(String sendtelPhone) {
		// 手机号码（优惠券发送手机号码）
		this.sendtelPhone = sendtelPhone;
	}
	/**
	 * @return status : return the property status.
	 */
	public String getStatus() {
		//		优惠券状态
		return status;
	}
	/**
	 * @param status : set the property status.
	 */
	public void setStatus(String status) {
		//		优惠券状态
		this.status = status;
	}
}
