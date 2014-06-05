package com.deppon.crm.module.customer.shared.domain.base;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;

/**
 * 
 * <p>
 * Description:行业与品名映射<br />
 * </p> 
 * 
 * @title GoodTradeMapping.java
 * @package com.deppon.crm.module.customer.shared.domain
 * @author 106138
 * @version 0.1 2014-3-18
 */
public class GoodTradeMapping extends BaseEntity {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -4708980872631427420L;
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	// 待映射品名
	private String articleName;
	// 发货票数占比
	private String delayaccounting;
	// 一级行业编码
	private String firstTradeCode;
	// 二级行业编码
	private String secondTradeCode;
	// 关联人id
	private int associatedPersonId;	
	//关联人
	private String associatedPerson;
	// 关联时间
	private Date associatedTime;
	// 关联状态
	private String associatedStatus;
	// 备注
	private String remark;
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return articleName : return the property articleName.
	 */
	public String getArticleName() {
		return articleName;
	}
	/**
	 * @param articleName : set the property articleName.
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return delayaccounting : return the property delayaccounting.
	 */
	public String getDelayaccounting() {
		return delayaccounting;
	}
	/**
	 * @param delayaccounting : set the property delayaccounting.
	 */
	public void setDelayaccounting(String delayaccounting) {
		this.delayaccounting = delayaccounting;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return firstTradeCode : return the property firstTradeCode.
	 */
	public String getFirstTradeCode() {
		return firstTradeCode;
	}
	/**
	 * @param firstTradeCode : set the property firstTradeCode.
	 */
	public void setFirstTradeCode(String firstTradeCode) {
		this.firstTradeCode = firstTradeCode;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return secondTradeCode : return the property secondTradeCode.
	 */
	public String getSecondTradeCode() {
		return secondTradeCode;
	}
	/**
	 * @param secondTradeCode : set the property secondTradeCode.
	 */
	public void setSecondTradeCode(String secondTradeCode) {
		this.secondTradeCode = secondTradeCode;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return associatedPersonId : return the property associatedPersonId.
	 */
	public int getAssociatedPersonId() {
		return associatedPersonId;
	}
	/**
	 * @param associatedPersonId : set the property associatedPersonId.
	 */
	public void setAssociatedPersonId(int associatedPersonId) {
		this.associatedPersonId = associatedPersonId;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return associatedPerson : return the property associatedPerson.
	 */
	public String getAssociatedPerson() {
		return associatedPerson;
	}
	/**
	 * @param associatedPerson : set the property associatedPerson.
	 */
	public void setAssociatedPerson(String associatedPerson) {
		this.associatedPerson = associatedPerson;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return associatedTime : return the property associatedTime.
	 */
	public Date getAssociatedTime() {
		return associatedTime;
	}
	/**
	 * @param associatedTime : set the property associatedTime.
	 */
	public void setAssociatedTime(Date associatedTime) {
		this.associatedTime = associatedTime;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return associatedStatus : return the property associatedStatus.
	 */
	public String getAssociatedStatus() {
		return associatedStatus;
	}
	/**
	 * @param associatedStatus : set the property associatedStatus.
	 */
	public void setAssociatedStatus(String associatedStatus) {
		this.associatedStatus = associatedStatus;
	}
	/**
	 *@user pgj
	 *2014-4-11上午8:08:57
	 * @return remark : return the property remark.
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark : set the property remark.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
