package com.deppon.crm.module.client.workflow.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 常规理赔需要传入的DTO定义
 * @author davidcun
 * @2012-3-14
 * description
 */
public class NormalRecompenseInfo {

	//申请人工号
	private String applyPersonCode;
	//线索工号,报案人经理工号
	private String clueUserId;
	//运单号/差错编号
	private String transportOrErrorCode;
	//保价人/发货联系人
	private String InsuredUnits;
	//联系电话
	private String contactPhone;
	//运输类型
	private String haulType;
	//收货部门（名称）
	private String receivingDept;
	//始发站
	private String startingStation;
	//货物名称
	private String goodsName;
	//货物属性：件/重/体
	private String goodsAttribute;
	//保险金额
	private double insuredAmount;
	//目标部门，到达部门
	private String targetDept;
	//发货日期
	private Date sendingDate;
	//出险日期
	private Date dangerDate;
	//所属区域(名称)
	private String area;
	//理赔类型(名称)
	private String claimsType;
	//冲账方式
	private String offsetTypt;
	//报案人(名称)
	private String caseReporter;
	//报案部门(名称)
	private String reportDept;
	//报案日期
	private Date reportDate;
	//处理人(名称)
	private String handler;
	//处理日期
	private Date handleDate;
	//其他费用说明
	private String otherCost;
	//索赔金额
	private double claimAmount;
	//责任部门/大区（如果有多个则进行组装：重庆大区,江门大区）
	private String responsibileDept;
	//正常理赔金额
	private double normalAmount;
	//实际理赔金额
	private double actualClaimsAmount;
	//入公司费用
	private double tocompanyCost;
	
	
	//出险信息
	private List<AccidentDescription> accidentDescriptionInfos;
	
	//入部门费用
	private List<IndeptCharges> indeptCharges;
	
	//奖罚明细列表
	private List<RewardPunishment> rewardPunishments;
	
	
	/**
	 * 入部门费用
	 * @author davidcun @2012-3-30
	 */
	public static class IndeptCharges{
		//部门（名称）
		private String dept;
		//费用
		private double charges;
		
		public String getDept() {
			return dept;
		}
		
		public void setDept(String dept) {
			this.dept = dept;
		}
		
		public double getCharges() {
			return charges;
		}
		
		public void setCharges(double charges) {
			this.charges = charges;
		}
	}
	/**
	 * 奖罚明细
	 * @author davidcun @2012-3-30
	 */
	public static class RewardPunishment{
		//奖罚类型：奖励/惩罚
		private String rewardPunishmentType;
		//处理对象：部门(标杆编码)/员工工号
		private String disposeTarget;
		//处理类型：部门或者员工
		private String processType;
		//奖罚金额
		private double money;

		public String getRewardPunishmentType() {
			return rewardPunishmentType;
		}

		public void setRewardPunishmentType(String rewardPunishmentType) {
			this.rewardPunishmentType = rewardPunishmentType;
		}

		public String getDisposeTarget() {
			return disposeTarget;
		}

		public void setDisposeTarget(String disposeTarget) {
			this.disposeTarget = disposeTarget;
		}

		public double getMoney() {
			return money;
		}

		public void setMoney(double money) {
			this.money = money;
		}

		public String getProcessType() {
			return processType;
		}

		public void setProcessType(String processType) {
			this.processType = processType;
		}
	}
	/**
	 * 出险描述
	 * @author davidcun @2012-3-30
	 */
	public static class AccidentDescription{
		//出险类型：破损、潮湿、污染、内物短少、整件丢失、整票丢失、冒领及其他
		private String accidentType;
		
		//出险件数
		private int accidentAcount;
		//出险描述
		private String description;
		public String getAccidentType() {
			return accidentType;
		}
		public void setAccidentType(String accidentType) {
			this.accidentType = accidentType;
		}
		public int getAccidentAcount() {
			return accidentAcount;
		}
		public void setAccidentAcount(int accidentAcount) {
			this.accidentAcount = accidentAcount;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}

	public String getApplyPersonCode() {
		return applyPersonCode;
	}


	public void setApplyPersonCode(String applyPersonCode) {
		this.applyPersonCode = applyPersonCode;
	}


	public String getClueUserId() {
		return clueUserId;
	}


	public void setClueUserId(String clueUserId) {
		this.clueUserId = clueUserId;
	}


	public String getTransportOrErrorCode() {
		return transportOrErrorCode;
	}


	public void setTransportOrErrorCode(String transportOrErrorCode) {
		this.transportOrErrorCode = transportOrErrorCode;
	}


	public String getInsuredUnits() {
		return InsuredUnits;
	}


	public void setInsuredUnits(String insuredUnits) {
		InsuredUnits = insuredUnits;
	}


	public String getContactPhone() {
		return contactPhone;
	}


	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}


	public String getHaulType() {
		return haulType;
	}


	public void setHaulType(String haulType) {
		this.haulType = haulType;
	}


	public String getReceivingDept() {
		return receivingDept;
	}


	public void setReceivingDept(String receivingDept) {
		this.receivingDept = receivingDept;
	}


	public String getStartingStation() {
		return startingStation;
	}


	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getGoodsAttribute() {
		return goodsAttribute;
	}


	public void setGoodsAttribute(String goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}


	public double getInsuredAmount() {
		return insuredAmount;
	}


	public void setInsuredAmount(double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}


	public String getTargetDept() {
		return targetDept;
	}


	public void setTargetDept(String targetDept) {
		this.targetDept = targetDept;
	}


	public Date getSendingDate() {
		return sendingDate;
	}


	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}


	public Date getDangerDate() {
		return dangerDate;
	}


	public void setDangerDate(Date dangerDate) {
		this.dangerDate = dangerDate;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getClaimsType() {
		return claimsType;
	}


	public void setClaimsType(String claimsType) {
		this.claimsType = claimsType;
	}


	public String getOffsetTypt() {
		return offsetTypt;
	}


	public void setOffsetTypt(String offsetTypt) {
		this.offsetTypt = offsetTypt;
	}


	public String getCaseReporter() {
		return caseReporter;
	}


	public void setCaseReporter(String caseReporter) {
		this.caseReporter = caseReporter;
	}


	public String getReportDept() {
		return reportDept;
	}


	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	public String getHandler() {
		return handler;
	}


	public void setHandler(String handler) {
		this.handler = handler;
	}


	public Date getHandleDate() {
		return handleDate;
	}


	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}


	public String getOtherCost() {
		return otherCost;
	}


	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}


	public double getClaimAmount() {
		return claimAmount;
	}


	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}


	public String getResponsibileDept() {
		return responsibileDept;
	}


	public void setResponsibileDept(String responsibileDept) {
		this.responsibileDept = responsibileDept;
	}


	public double getNormalAmount() {
		return normalAmount;
	}


	public void setNormalAmount(double normalAmount) {
		this.normalAmount = normalAmount;
	}

	public double getActualClaimsAmount() {
		return actualClaimsAmount;
	}

	public void setActualClaimsAmount(double actualClaimsAmount) {
		this.actualClaimsAmount = actualClaimsAmount;
	}

	public double getTocompanyCost() {
		return tocompanyCost;
	}

	public void setTocompanyCost(double tocompanyCost) {
		this.tocompanyCost = tocompanyCost;
	}
	
	public List<AccidentDescription> getAccidentDescriptionInfos() {
		return accidentDescriptionInfos;
	}

	public void setAccidentDescriptionInfos(
			List<AccidentDescription> accidentDescriptionInfos) {
		this.accidentDescriptionInfos = accidentDescriptionInfos;
	}

	public List<IndeptCharges> getIndeptCharges() {
		return indeptCharges;
	}

	public void setIndeptCharges(List<IndeptCharges> indeptCharges) {
		this.indeptCharges = indeptCharges;
	}

	public List<RewardPunishment> getRewardPunishments() {
		return rewardPunishments;
	}

	public void setRewardPunishments(List<RewardPunishment> rewardPunishments) {
		this.rewardPunishments = rewardPunishments;
	}
	
	public static void main(String[] args) {
		NormalRecompenseInfo recompenseInfo = new NormalRecompenseInfo();
		
		List<AccidentDescription> ads = new ArrayList<AccidentDescription>();
		
		recompenseInfo.setAccidentDescriptionInfos(ads);
		ObjectMapper mapper = new ObjectMapper();
		
		String str = "";
		try {
			str = mapper.writeValueAsString(recompenseInfo);
			System.out.println(str);
			NormalRecompenseInfo info = mapper.readValue(str, NormalRecompenseInfo.class);
			System.out.println(info.getAccidentDescriptionInfos());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
