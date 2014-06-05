/**
 * @description
 * @author 赵斌
 * @2012-4-25
 * @return
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * @author Administrator
 *
 */
public class OrderOperationLog extends BaseEntity {

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
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * <p>
	 * Description:contactName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * <p>
	 * Description:operatorType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOperatorType() {
		return operatorType;
	}
	/**
	 * <p>
	 * Description:operatorType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	/**
	 * <p>
	 * Description:operatorId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOperatorId() {
		return operatorId;
	}
	/**
	 * <p>
	 * Description:operatorId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * <p>
	 * Description:operatorOrgId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOperatorOrgId() {
		return operatorOrgId;
	}
	/**
	 * <p>
	 * Description:operatorOrgId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorOrgId(String operatorOrgId) {
		this.operatorOrgId = operatorOrgId;
	}
	/**
	 * <p>
	 * Description:orderId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * <p>
	 * Description:orderId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * <p>
	 * Description:operatorContent<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOperatorContent() {
		return operatorContent;
	}
	/**
	 * <p>
	 * Description:operatorContent<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorContent(String operatorContent) {
		this.operatorContent = operatorContent;
	}
	/**
	 * <p>
	 * Description:operatorTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}
	/**
	 * <p>
	 * Description:operatorTime<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
