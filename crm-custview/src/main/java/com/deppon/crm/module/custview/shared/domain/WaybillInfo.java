/**   
 * <p>
 * Description:运单信息
 * </p>
 * @title WaybillInfo.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author 106143
 * @version 0.1 2014-4-9上午9:09:27
 */
package com.deppon.crm.module.custview.shared.domain;

import java.util.Date;

/**   
 * <p>
 * Description:运单信息
 * </p>
 * @title WaybillInfo.java
 * @package com.deppon.crm.module.custview.shared.domain 
 * @author 106143
 * @version 0.1 2014-4-9上午9:09:27
 */

public class WaybillInfo {
	//运单号
	private String waybillNum;
	// 收货人
	private String consignee;
	// 收货人电话
	private String consigneePhone;
	// 收货人手机
	private String consigneeMobile;
	// 收货人地址
	private String consigneeAddress;
	/* 运输性质(三级产品)： 
	 * 精准汽运（长途）LRF 
	 * 精准卡航 FLF 
	 * 精准汽运（短途） SRF 
	 * 精准城运  FSF 
	 * 汽运偏线 PLF 
	 * 精准空运 AF 
	 * 整车 FV
	 */
	protected String tranProperty;
	// 到达网点名称
	private String ladingStationName;
	// 到达网点标杆编码
	private String ladingStationNumber;
	// 签收时间
	private Date sendTime;
	// 收货客户编码
	private String consigneeNumber;
	/**
	 *@return  waybillNum;
	 */
	public String getWaybillNum() {
		return waybillNum;
	}
	/**
	 * @param waybillNum : set the property waybillNum.
	 */
	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}
	/**
	 *@return  consignee;
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * @param consignee : set the property consignee.
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 *@return  consigneePhone;
	 */
	public String getConsigneePhone() {
		return consigneePhone;
	}
	/**
	 * @param consigneePhone : set the property consigneePhone.
	 */
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	/**
	 *@return  consigneeMobile;
	 */
	public String getConsigneeMobile() {
		return consigneeMobile;
	}
	/**
	 * @param consigneeMobile : set the property consigneeMobile.
	 */
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}
	/**
	 *@return  consigneeAddress;
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	/**
	 * @param consigneeAddress : set the property consigneeAddress.
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 *@return  tranProperty;
	 */
	public String getTranProperty() {
		return tranProperty;
	}
	/**
	 * @param tranProperty : set the property tranProperty.
	 */
	public void setTranProperty(String tranProperty) {
		this.tranProperty = tranProperty;
	}
	/**
	 *@return  ladingStationName;
	 */
	public String getLadingStationName() {
		return ladingStationName;
	}
	/**
	 * @param ladingStationName : set the property ladingStationName.
	 */
	public void setLadingStationName(String ladingStationName) {
		this.ladingStationName = ladingStationName;
	}
	/**
	 *@return  ladingStationNumber;
	 */
	public String getLadingStationNumber() {
		return ladingStationNumber;
	}
	/**
	 * @param ladingStationNumber : set the property ladingStationNumber.
	 */
	public void setLadingStationNumber(String ladingStationNumber) {
		this.ladingStationNumber = ladingStationNumber;
	}
	/**
	 *@return  consigneeNumber;
	 */
	public String getConsigneeNumber() {
		return consigneeNumber;
	}
	/**
	 * @param consigneeNumber : set the property consigneeNumber.
	 */
	public void setConsigneeNumber(String consigneeNumber) {
		this.consigneeNumber = consigneeNumber;
	}
	/**
	 *@return  sendTime;
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime : set the property sendTime.
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}
