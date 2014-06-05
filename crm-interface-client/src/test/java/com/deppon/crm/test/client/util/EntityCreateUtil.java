/*package com.deppon.crm.test.client.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.ContractNounInfo;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo;
import com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.GiftApplyInfo.Gift;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.AccidentDescription;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.IndeptCharges;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo.RewardPunishment;

*//**
 * 测试数据工具
 * @author davidcun @2012-4-27
 *//*
public class EntityCreateUtil {

	
	public static NormalRecompenseInfo createNormalRecompenseInfo(){
		NormalRecompenseInfo info = new NormalRecompenseInfo();
		
		List<AccidentDescription> accidentDescriptionInfos = new ArrayList<NormalRecompenseInfo.AccidentDescription>();
		for (int i = 0; i < 10; i++) {
			AccidentDescription ad = new AccidentDescription();
			ad.setAccidentAcount(10);
			ad.setAccidentType("整票丢失");
			ad.setDescription("货物从台州发至广州汽运专线，卸车后，清仓无货，货物丢失！差错编号2012111632725，");
			accidentDescriptionInfos.add(ad);
		}
		info.setAccidentDescriptionInfos(accidentDescriptionInfos);
		info.setActualClaimsAmount(345.98);
		info.setApplyPersonCode("053951");
		info.setArea("上海大区");
		info.setCaseReporter("石小孟");
		info.setClaimAmount(500);
		info.setClaimsType("1");
		info.setClueUserId("026717");//线索工号
		info.setContactPhone("13917182631");
		info.setDangerDate(new Date());
		info.setGoodsAttribute("12");
		info.setGoodsName("货物名称");
		info.setHandleDate(new Date());
		info.setHandler("处理人");
		info.setHaulType("1");
		List<IndeptCharges> indeptCharges = new ArrayList<NormalRecompenseInfo.IndeptCharges>();
		for (int i = 0; i < 10; i++) {
			IndeptCharges charges = new IndeptCharges();
			charges.setCharges(100);
			charges.setDept("顺德陈村枢纽中心卸车八组");
			indeptCharges.add(charges);
		}
		info.setIndeptCharges(indeptCharges);
		
		info.setInsuredAmount(987.4);
		info.setInsuredUnits("保价人");
		info.setNormalAmount(198.0);
		info.setOffsetTypt("冲账方式");
		info.setOtherCost("异常签收信息：OA上报丢货差错，处理编号：2012111632725处理结果类型：公司内部丢货，责任部门顺德陈村枢纽中心卸车八组n货物信息：货物为服装毛衣，共价值2136元，n定损：公司内部丢货，保价2000元，理赔2000元，单责丢货，理赔费用入责任部门：顺德陈村枢纽中心卸车八组,");
		info.setReceivingDept("收货部门");
		info.setReportDate(new Date());
		info.setReportDept("报案部门");
		info.setResponsibileDept("山东大区,成平大区");
		List<RewardPunishment> rewardPunishments = new ArrayList<NormalRecompenseInfo.RewardPunishment>();
		for (int i = 0; i < 10; i++) {
			RewardPunishment rp = new RewardPunishment();
			rp.setDisposeTarget("奖罚对象");
			rp.setMoney(300);
			rp.setProcessType("1");
			rp.setRewardPunishmentType("1");
			rewardPunishments.add(rp);
		}
		info.setRewardPunishments(rewardPunishments);
		info.setSendingDate(new Date());
		info.setStartingStation("始发站");
		info.setTargetDept("到达部门");
		info.setTocompanyCost(4000);
		info.setTransportOrErrorCode("12640701");
		return info;
	}
	*//**
	 * 
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 *//*
	public static MuchRecompenseInfo createMuchRecompense(){
		MuchRecompenseInfo info = new MuchRecompenseInfo();
		info.setAmountinTotal(2000.098);
		info.setApplyPersonCode("053951");
		info.setApplyPersonName("寸代永");
		info.setApplyReason("申请原因");
		info.setDeptAccountant("部门会计");
		info.setEnterpriseDept("W0113010301");
		info.setHasRepayDebt(false);
		info.setRecompensiesMoney(4000);
		info.setTransportOrErrorCode("12345678");
		return info;
	}
	
	public static ContractInfo createContractInfo(){
		ContractInfo contractInfo = new ContractInfo();
		contractInfo.setProcessInstId(1);
		contractInfo.setApplyPersonCode("1");
		contractInfo.setApplyPersonName("1");
		contractInfo.setDivisionCode("1");
		contractInfo.setApplyPersonDept("1");
		contractInfo.setApplyType("1");
		contractInfo.setOverRange("1");
		contractInfo.setContractNumber("1");
		contractInfo.setSubsidiary("1");
		contractInfo.setContractStartDate(new Date());
		contractInfo.setContractEndDate(new Date());
		contractInfo.setCustomerCode("1");
		contractInfo.setCustomerName("1");
		contractInfo.setAmountOfConsignment("1");
		contractInfo.setCustomerAllName("1");
		contractInfo.setNodeSectionType("1");
		contractInfo.setBalanceAccount("1");
		contractInfo.setSettleAccountsDate("1");
		contractInfo.setFreightDiscount("1");
		contractInfo.setPreferentialType("1");
		contractInfo.setGenerationRateDiscount("1");
		contractInfo.setChargeRateDiscount("1");
		contractInfo.setCargoFeeDiscount("1");
		contractInfo.setDeliveryFeeDiscount("1");
		contractInfo.setProtocolContactName("1");
		contractInfo.setContactPhone("1");
		contractInfo.setDiscount("1");
		contractInfo.setModifyContractType("1");
		contractInfo.setOldContractNumber("1");
		contractInfo.setNewBalanceAccount("1");
		contractInfo.setNewFreightDiscount("1");
		contractInfo.setNewPreferentialType("1");
		contractInfo.setNewGenerationRateDiscount("1");
		contractInfo.setNewChargeRateDiscount("1");
		contractInfo.setNewCargoFeeDiscount("1");
		contractInfo.setNewDeliveryFeeDiscount("1");
		contractInfo.setContrctAscriptionDept("1");
		contractInfo.setApplyBondingDept("1");
		contractInfo.setAscriptionDept("1");
		contractInfo.setApplyRenewalDept("1");
		contractInfo.setApplyReason("1");
		List<ContractNounInfo> contractNounInfos = new ArrayList<ContractNounInfo>();
		for (int i = 0; i < 10; i++) {
			ContractNounInfo contractNounInfo = new ContractNounInfo();
			contractNounInfo.setContractId("合同ID");
			contractNounInfo.setContractName("合同名称");
			contractNounInfo.setFileName("文件名称");
			contractNounInfo.setSavePath("/opt/fileUpload/2012-05-29/2012-05-29_b0d892a.xlsx");
			contractNounInfos.add(contractNounInfo);
		}
//		contractInfo.setFile(contractNounInfos);
//		contractInfo.setFreightDiscount(2345.098);
//		info.setGenerationRateDiscount(123.8);
//		info.setGoodsName("货物名称");
//		info.setIDnumber("省份证号码");
//		info.setIndustry("行业");
//		info.setInvoiceDate(23);
//		info.setIsPreferential(true);
//		info.setNodeSectionType("结款方式");
//		info.setOldcontractNum("原合同编号");
//		info.setOldDeptCode("W0101010301");//绑定或者更行所属部门的时候原部门编码需要
//		info.setPreferentialType("优惠类型");
//		info.setProtocolContactName("周瑜");
//		info.setProtocolContactPhone("15898789098");
//		info.setProtocolContactTel("0539-987678");
//		info.setRegisterMoney(234123+"");
//		info.setSettleAccountsDate(16);
//		info.setTaxRegistrationNum("税务登记号");
		return contractInfo;
	}
	
	public static void main(String[] args) throws CrmBusinessException {
		ContractInfo info = EntityCreateUtil.createContractInfo();
		String str = JsonMapperUtil.writeValue(info);
		System.out.println(str);
		WorkFlowApplyInfo wf = new WorkFlowApplyInfo();
		wf.setBizInfo(str);
		wf.setBizType("0007:1");
		System.out.println();
		
		String s = JsonMapperUtil.writeValue(wf);
		System.out.println(s);
		
		WorkFlowApplyInfo ai = JsonMapperUtil.readValue(s, WorkFlowApplyInfo.class);
		
		System.out.println(ai.bizInfo);
		ContractInfo info2 = JsonMapperUtil.readValue(ai.bizInfo, ContractInfo.class);
		
//		System.out.println(info2.getAddress());
		
	}
	
	public static GiftApplyInfo createGiftApplyInfo(){
		GiftApplyInfo giftApplyInfo = new GiftApplyInfo();
//		giftApplyInfo.setApplyPersonNumber("053951");
//		giftApplyInfo.setApplyPersonNumber("070211");
		giftApplyInfo.setApplyPersonNumber("075586");
//		giftApplyInfo.setReceiveDept("DP01563");
//		giftApplyInfo.setApplyPersonNumber("048843");
//		giftApplyInfo.setReceiveDept("DP08474");
//		giftApplyInfo.setApplyPersonNumber("001500");
		giftApplyInfo.setReceiveDept("DP07564");
		giftApplyInfo.setApplyReason("申请原因");
		giftApplyInfo.setPhone("13917182631");
		giftApplyInfo.setReceiveAddress("上海市青浦区徐泾营业部");
//		giftApplyInfo.setReceiveDept("DP07564");
		for (int i = 0; i <2; i++) {
			Gift gift = new Gift();
			gift.setExchangePersonNumber("123");
			gift.setMemberNumber("123");
			gift.setGiftCount(i+1);
			gift.setGiftNumber("CRM_GOODS_1");
			giftApplyInfo.getGifts().add(gift);
		}
		
		return giftApplyInfo;
	}
	
	
	static class WorkFlowApplyInfo{
		private String bizInfo;
		private String bizType;
		public String getBizInfo() {
			return bizInfo;
		}
		public void setBizInfo(String bizInfo) {
			this.bizInfo = bizInfo;
		}
		public String getBizType() {
			return bizType;
		}
		public void setBizType(String bizType) {
			this.bizType = bizType;
		}
		
	}
}
*/