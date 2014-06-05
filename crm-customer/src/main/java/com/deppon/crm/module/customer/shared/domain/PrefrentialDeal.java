/**   
 * @title PrefrentialDeal.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @description 月发月送折扣优惠方案实体  
 * @author 潘光均
 * @update 2013-3-8 下午4:35:48
 * @version V1.0   
 */
package com.deppon.crm.module.customer.shared.domain;

import java.util.Date;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * The Class PrefrentialDeal.
 * 
 * @description 月发月送折扣优惠方案实体
 * @author 潘光均
 * @version 0.1 2013-3-8
 * @date 2013-3-8
 */

public class PrefrentialDeal extends BaseEntity {

	/** The Constant serialVersionUID. @fields serialVersionUID */

	private static final long serialVersionUID = -917887408408001908L;

	// 方案名称
	/** The deal name. */
	private String dealName;
	// 方案生效时间
	/** The begin time. */
	private Date beginTime;
	// 方案失效时间
	/** The end time. */
	private Date endTime;
	// 方案编码
	/** The deal number. */
	private String dealNumber;
	// 方案状态   1为有效 2为无效 3为待生效
	/** The status. */
	private String status;
	// 方案条目集合
	/** The deal items. */
	private List<PrefrentialDealItem> dealItems;
	//方案类型
	private String preferType;
	/**
	 * Gets the deal items.
	 * 
	 * @return the deal items
	 */
	public List<PrefrentialDealItem> getDealItems() {
		return dealItems;
	}

	/**
	 * Sets the deal items.
	 * 
	 * @param dealItems
	 *            the new deal items
	 */
	public void setDealItems(List<PrefrentialDealItem> dealItems) {
		this.dealItems = dealItems;
	}

	/**
	 * Gets the deal name.
	 * 
	 * @return the deal name
	 */
	public String getDealName() {
		return dealName;
	}

	/**
	 * Sets the deal name.
	 * 
	 * @param dealName
	 *            the new deal name
	 */
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	/**
	 * Gets the begin time.
	 * 
	 * @return the begin time
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * Sets the begin time.
	 * 
	 * @param beginTime
	 *            the new begin time
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * Gets the end time.
	 * 
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 * 
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the deal number.
	 * 
	 * @return the deal number
	 */
	public String getDealNumber() {
		return dealNumber;
	}

	/**
	 * Sets the deal number.
	 * 
	 * @param dealNumber
	 *            the new deal number
	 */
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 *@user pgj
	 *2013-9-21上午9:22:56
	 * @return preferType : return the property preferType.
	 */
	public String getPreferType() {
		return preferType;
	}

	/**
	 * @param preferType : set the property preferType.
	 */
	public void setPreferType(String preferType) {
		this.preferType = preferType;
	}

}
