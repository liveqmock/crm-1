package com.deppon.crm.module.order.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:订单操作日志<br />
 * </p>
 * @title OrderOperationLog.java
 * @package com.deppon.crm.module.order.shared.domain 
 * @author zouming
 * @version 0.1 2013-1-22上午11:17:45
 */
public class OrderOperationLog extends BaseEntity {
	
	//是当前对象可序列化
	private static final long serialVersionUID = 1L;

	// 发货联系人名称
	private String contactName;
	// 操作类别
	private String operatorType;
	// 操作人ID
	private String operatorId;
	// 操作人所属组织ID
	private String operatorOrgId;
	// 订单ID
	private String orderId;
	// 操作内容
	private String operatorContent;
	// 操作时间
	private Date operatorTime;
	/**
	 *@return  contactName;
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName : set the property contactName.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 *@return  operatorType;
	 */
	public String getOperatorType() {
		return operatorType;
	}
	/**
	 * @param operatorType : set the property operatorType.
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	/**
	 *@return  operatorId;
	 */
	public String getOperatorId() {
		return operatorId;
	}
	/**
	 * @param operatorId : set the property operatorId.
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 *@return  operatorOrgId;
	 */
	public String getOperatorOrgId() {
		return operatorOrgId;
	}
	/**
	 * @param operatorOrgId : set the property operatorOrgId.
	 */
	public void setOperatorOrgId(String operatorOrgId) {
		this.operatorOrgId = operatorOrgId;
	}
	/**
	 *@return  orderId;
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId : set the property orderId.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 *@return  operatorContent;
	 */
	public String getOperatorContent() {
		return operatorContent;
	}
	/**
	 * @param operatorContent : set the property operatorContent.
	 */
	public void setOperatorContent(String operatorContent) {
		this.operatorContent = operatorContent;
	}
	/**
	 *@return  operatorTime;
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}
	/**
	 * @param operatorTime : set the property operatorTime.
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
}