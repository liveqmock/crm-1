package com.deppon.crm.module.common.shared.domain;

import java.util.Date;

/**
 * 描述:理赔运单信息
 * 
 * @author huangzhanming 创建日期 :2012-1-5
 */
public class Waybill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725176955126329656L;
	
	private String id;

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

	// 保价金额
	private Double insurAmount;

	// 保价人
	private String insured;
	
	//发货人姓名
	private String shipperName;

	// 联系电话
	private String telephone;

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
	//运单状态
	private String status;

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getInsurAmount() {
		return insurAmount;
	}

	public void setInsurAmount(Double insurAmount) {
		this.insurAmount = insurAmount;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getReceiveDept() {
		return receiveDept;
	}

	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	public String getInsured() {
		return insured;
	}

	public void setInsured(String insured) {
		this.insured = insured;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getPwv() {
		return pwv;
	}

	public void setPwv(String pwv) {
		this.pwv = pwv;
	}

	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endStation == null) ? 0 : endStation.hashCode());
		result = prime * result
				+ ((goodsName == null) ? 0 : goodsName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((insurAmount == null) ? 0 : insurAmount.hashCode());
		result = prime * result + ((insured == null) ? 0 : insured.hashCode());
		result = prime * result
				+ ((packaging == null) ? 0 : packaging.hashCode());
		result = prime * result + ((pwv == null) ? 0 : pwv.hashCode());
		result = prime * result
				+ ((receiveDept == null) ? 0 : receiveDept.hashCode());
		result = prime * result
				+ ((sendDate == null) ? 0 : sendDate.hashCode());
		result = prime * result
				+ ((startStation == null) ? 0 : startStation.hashCode());
		result = prime * result
				+ ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result
				+ ((transType == null) ? 0 : transType.hashCode());
		result = prime * result
				+ ((waybillId == null) ? 0 : waybillId.hashCode());
		result = prime * result
				+ ((waybillNumber == null) ? 0 : waybillNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Waybill other = (Waybill) obj;
		if (endStation == null) {
			if (other.endStation != null)
				return false;
		} else if (!endStation.equals(other.endStation))
			return false;
		if (goodsName == null) {
			if (other.goodsName != null)
				return false;
		} else if (!goodsName.equals(other.goodsName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (insurAmount == null) {
			if (other.insurAmount != null)
				return false;
		} else if (!insurAmount.equals(other.insurAmount))
			return false;
		if (insured == null) {
			if (other.insured != null)
				return false;
		} else if (!insured.equals(other.insured))
			return false;
		if (packaging == null) {
			if (other.packaging != null)
				return false;
		} else if (!packaging.equals(other.packaging))
			return false;
		if (pwv == null) {
			if (other.pwv != null)
				return false;
		} else if (!pwv.equals(other.pwv))
			return false;
		if (receiveDept == null) {
			if (other.receiveDept != null)
				return false;
		} else if (!receiveDept.equals(other.receiveDept))
			return false;
		if (sendDate == null) {
			if (other.sendDate != null)
				return false;
		} else if (!sendDate.equals(other.sendDate))
			return false;
		if (startStation == null) {
			if (other.startStation != null)
				return false;
		} else if (!startStation.equals(other.startStation))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (transType == null) {
			if (other.transType != null)
				return false;
		} else if (!transType.equals(other.transType))
			return false;
		if (waybillId == null) {
			if (other.waybillId != null)
				return false;
		} else if (!waybillId.equals(other.waybillId))
			return false;
		if (waybillNumber == null) {
			if (other.waybillNumber != null)
				return false;
		} else if (!waybillNumber.equals(other.waybillNumber))
			return false;
		return true;
	}
}
