package com.deppon.crm.module.recompense.shared.domain;

import java.util.Date;

/**
 * 
 * <p>
 * Description：运单信息<br />
 * </p>
 * 
 * @title Waybill.java
 * @package com.deppon.crm.module.recompense.shared.domain
 * @author roy
 * @version 0.1 2013-1-21
 */
public class Waybill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725176955126329656L;
	// 编号
	private String id;
	// 理赔单关联Id
	private String recompenseId;

	// 运单ID
	private String waybillId;

	// 运单号
	private String waybillNumber;

	// 货物名称
	private String goodsName;

	// 运输类型
	private String transType;
	// 收货部门
	private String receiveDept;
	//到达部门
	private String arrivedDept;

	// 保价金额
	private Double insurAmount;

	// 保价人
	private String insured;

	// 联系电话
	private String telephone;
	
	
	//收货人
	private String consignee;
	//收货人联系方式
	private String consigneePhone;

	// 始发站
	private String startStation;

	// 目的地
	private String endStation;

	// 包装
	private String packaging;

	// 件/重/体
	private String pwv;

	// 发货日期
	private Date sendDate;
	// 出发客户ID
	private String leaveCustomerId;
	// 到达客户ID
	private String arriveCustomerId;

	/**
	 * constructer
	 */
	public Waybill() {
		super();
	}

	/**
	 * constructer
	 * 
	 * @param waybillNumber
	 * @param goodsName
	 * @param transType
	 * @param receiveDept
	 * @param insurAmount
	 * @param insured
	 * @param telephone
	 * @param startStation
	 * @param endStation
	 * @param packaging
	 * @param pwv
	 * @param sendDate
	 * @param leaveCustomerId
	 * @param arriveCustomerId
	 */
	public Waybill(String waybillNumber, String goodsName, String transType,
			String receiveDept, Double insurAmount, String insured,
			String telephone, String startStation, String endStation,
			String packaging, String pwv, Date sendDate,
			String leaveCustomerId, String arriveCustomerId) {
		this.waybillNumber = waybillNumber;
		this.goodsName = goodsName;
		this.transType = transType;
		this.receiveDept = receiveDept;
		this.insurAmount = insurAmount;
		this.insured = insured;
		this.telephone = telephone;
		this.startStation = startStation;
		this.endStation = endStation;
		this.packaging = packaging;
		this.pwv = pwv;
		this.sendDate = sendDate;
		this.leaveCustomerId = leaveCustomerId;
		this.arriveCustomerId = arriveCustomerId;
	}
	
	/**
	 * <p>
	 * Description:consignee<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public String getConsignee() {
		return consignee;
	}

	/**
	 * <p>
	 * Description:consignee<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/**
	 * <p>
	 * Description:consigneePhone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public String getConsigneePhone() {
		return consigneePhone;
	}

	/**
	 * <p>
	 * Description:consigneePhone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-11
	 */
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getRecompenseId() {
		return recompenseId;
	}

	/**
	 * <p>
	 * Description:recompenseId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setRecompenseId(String recompenseId) {
		this.recompenseId = recompenseId;
	}

	/**
	 * <p>
	 * Description:waybillId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getWaybillId() {
		return waybillId;
	}

	/**
	 * <p>
	 * Description:waybillId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}

	/**
	 * <p>
	 * Description:waybillNumber<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * <p>
	 * Description:goodsName<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * <p>
	 * Description:transType<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * <p>
	 * Description:receiveDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getReceiveDept() {
		return receiveDept;
	}

	/**
	 * <p>
	 * Description:receiveDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	/**
	 * <p>
	 * Description:insurAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Double getInsurAmount() {
		return insurAmount;
	}

	/**
	 * <p>
	 * Description:insurAmount<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsurAmount(Double insurAmount) {
		this.insurAmount = insurAmount;
	}

	/**
	 * <p>
	 * Description:insured<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getInsured() {
		return insured;
	}

	/**
	 * <p>
	 * Description:insured<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setInsured(String insured) {
		this.insured = insured;
	}

	/**
	 * <p>
	 * Description:telephone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * <p>
	 * Description:telephone<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * <p>
	 * Description:startStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getStartStation() {
		return startStation;
	}

	/**
	 * <p>
	 * Description:startStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	/**
	 * <p>
	 * Description:endStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getEndStation() {
		return endStation;
	}

	/**
	 * <p>
	 * Description:endStation<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	/**
	 * <p>
	 * Description:packaging<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPackaging() {
		return packaging;
	}

	/**
	 * <p>
	 * Description:packaging<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	/**
	 * <p>
	 * Description:pwv<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getPwv() {
		return pwv;
	}

	/**
	 * <p>
	 * Description:pwv<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setPwv(String pwv) {
		this.pwv = pwv;
	}

	/**
	 * <p>
	 * Description:sendDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * <p>
	 * Description:sendDate<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * <p>
	 * Description:leaveCustomerId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getLeaveCustomerId() {
		return leaveCustomerId;
	}

	/**
	 * <p>
	 * Description:leaveCustomerId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setLeaveCustomerId(String leaveCustomerId) {
		this.leaveCustomerId = leaveCustomerId;
	}

	/**
	 * <p>
	 * Description:arriveCustomerId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public String getArriveCustomerId() {
		return arriveCustomerId;
	}

	/**
	 * <p>
	 * Description:arriveCustomerId<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 */
	public void setArriveCustomerId(String arriveCustomerId) {
		this.arriveCustomerId = arriveCustomerId;
	}

	/**
	 * <p>
	 * Description:arrivedDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-6
	 */
	public String getArrivedDept() {
		return arrivedDept;
	}

	/**
	 * <p>
	 * Description:arrivedDept<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-3-6
	 */
	public void setArrivedDept(String arrivedDept) {
		this.arrivedDept = arrivedDept;
	}


}
