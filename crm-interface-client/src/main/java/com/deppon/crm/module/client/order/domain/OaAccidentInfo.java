package com.deppon.crm.module.client.order.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaAccidentInfo {
	
	//差错编号
	private String accidentCode;
	//运单号
	private String waybillNumber;
	//差错名称（差错类型对应的关系）
	private String accidentName;
	//差错类型
	private int accidentType;
	//出险描述
	private String accidentDescription;
	private List<ErrorRewardPunishment> rewardPunishments;
	
	
	//----------未开单时候需要返回信息
	//包装
	private String packaging;
	//保价人（即发货联系人）
	private String contactName;
	//联系方式
	private String contactType;
	//收货部门
	private String receivingDept;
	//目的站
	private String destinationStation;
	//发货日期
	private Date dateShiped;
	//货物名称
	private String cargoName;
	//件/重/体
	private String cargoWeight;
	//保价金额
	private double insuredAmount;
	//运输类型
	private String transportType;
	//始发站
	private String startStation;
	
	
	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getReceivingDept() {
		return receivingDept;
	}

	public void setReceivingDept(String receivingDept) {
		this.receivingDept = receivingDept;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public Date getDateShiped() {
		return dateShiped;
	}

	public void setDateShiped(Date dateShiped) {
		this.dateShiped = dateShiped;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(String cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	public double getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public String getAccidentName() {
		return accidentName;
	}

	public void setAccidentName(String accidentName) {
		this.accidentName = accidentName;
	}

	public int getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(int accidentType) {
		this.accidentType = accidentType;
	}

	public String getAccidentDescription() {
		return accidentDescription;
	}

	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getAccidentCode() {
		return accidentCode;
	}

	public void setAccidentCode(String accidentCode) {
		this.accidentCode = accidentCode;
	}
	
	public static class ErrorRewardPunishment{
//		处理结果描述
		private String processResult;
//		处理对象
		private String processTarget;
//		奖罚说明
		private String rewardPunishmentDescription;
//		奖罚金额
		private double money;
//		处理时间
		private Date processDate;
//		处理人
		private String processPerson;
		public String getProcessResult() {
			return processResult;
		}
		public void setProcessResult(String processResult) {
			this.processResult = processResult;
		}
		public String getProcessTarget() {
			return processTarget;
		}
		public void setProcessTarget(String processTarget) {
			this.processTarget = processTarget;
		}
		public String getRewardPunishmentDescription() {
			return rewardPunishmentDescription;
		}
		public void setRewardPunishmentDescription(String rewardPunishmentDescription) {
			this.rewardPunishmentDescription = rewardPunishmentDescription;
		}
		public double getMoney() {
			return money;
		}
		public void setMoney(double money) {
			this.money = money;
		}
		public Date getProcessDate() {
			return processDate;
		}
		public void setProcessDate(Date processDate) {
			this.processDate = processDate;
		}
		public String getProcessPerson() {
			return processPerson;
		}
		public void setProcessPerson(String processPerson) {
			this.processPerson = processPerson;
		}
	}

	public List<ErrorRewardPunishment> getRewardPunishments() {
		if (rewardPunishments==null) {
			rewardPunishments = new ArrayList<OaAccidentInfo.ErrorRewardPunishment>();
		}
		return rewardPunishments;
	}

	public void setRewardPunishments(List<ErrorRewardPunishment> rewardPunishments) {
		this.rewardPunishments = rewardPunishments;
	}

	@Override
	public String toString() {
		return "OaAccidentInfo [accidentCode=" + accidentCode
				+ ", waybillNumber=" + waybillNumber + ", accidentName="
				+ accidentName + ", accidentType=" + accidentType
				+ ", accidentDescription=" + accidentDescription
				+ ", rewardPunishments=" + rewardPunishments + ", packaging="
				+ packaging + ", contactName=" + contactName + ", contactType="
				+ contactType + ", receivingDept=" + receivingDept
				+ ", destinationStation=" + destinationStation
				+ ", dateShiped=" + dateShiped + ", cargoName=" + cargoName
				+ ", cargoWeight=" + cargoWeight + ", insuredAmount="
				+ insuredAmount + ", transportType=" + transportType
				+ ", startStation=" + startStation + "]";
	}
}
