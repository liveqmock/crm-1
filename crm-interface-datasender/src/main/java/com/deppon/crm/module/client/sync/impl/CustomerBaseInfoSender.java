package com.deppon.crm.module.client.sync.impl;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.jobs.ILogSender;
import com.deppon.crm.module.client.sync.ICustomerBaseInfoWdghSender;
import com.deppon.crm.module.client.sync.ICustomerInfoCcSender;
import com.deppon.crm.module.client.sync.ICustomerInfoFossSender;
import com.deppon.crm.module.client.sync.cc.WaitingSendCcRequest;
import com.deppon.crm.module.client.sync.dao.ISearchMemberInfosDao;
import com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao;
import com.deppon.crm.module.client.sync.domain.CustTransationOperation;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfoDetail;
import com.deppon.crm.module.client.sync.domain.WaitingSendRequest;
import com.deppon.crm.module.client.sync.domain.dto.TCustAccountOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractTaxOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractdeptOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustbasedata;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustbasedataOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustlinkmanOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferenceaddressOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferentialOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustShuttleaddressOperation;
import com.deppon.crm.module.client.sync.wdgh.CustDetalInfo;
import com.deppon.crm.module.client.sync.wdgh.SyncCustInfoRequest;
import com.deppon.crm.module.client.sync.foss.WaitingSendFossRequest;

public class CustomerBaseInfoSender implements ILogSender<WaitCustomerLogInfo> {
	
	private static Logger logger = Logger.getLogger(CustomerBaseInfoSender.class);
	IWaitCustomerLogDao waitCustomerLogDao;
	ISearchMemberInfosDao searchMemberInfosDao;
	private ICustomerInfoCcSender customerInfoCcSender;
	private ICustomerInfoFossSender customerInfoFossSender;
	private ICustomerBaseInfoWdghSender customerBaseInfoWdghSender;
	
	private final String CUSTOMTER_TO_WDGH = "T_CUST_CUSTBASEDATA,T_CUST_CUSTLINKMAN,T_CUST_PREFERENCEADDRESS,";

	/**
	 * @deprecated 
	 */
	@Override
	public int[] send(List<WaitCustomerLogInfo> logs) {
		return null;
	}

	/**
	 * @throws CrmBusinessException 
	 * 
	 */
	public boolean send(WaitCustomerLogInfo waitCustomerLogInfo) {
    	try {
			Map<BigDecimal, Integer> custBasedataMap = new HashMap<BigDecimal , Integer>();
			try {
				List<WaitCustomerLogInfoDetail> detailList = waitCustomerLogDao.getWaitingSendDetails(waitCustomerLogInfo.getFID());
				
				for (WaitCustomerLogInfoDetail detail : detailList) {
					CustTransationOperation custTransationOperation = new CustTransationOperation();
					custTransationOperation.setTableName(getTableNameEnum(detail.getTABLE_NAME()));
					custTransationOperation.setKeyword(detail.getTABLE_KEYWORD());
					custTransationOperation.setOptFlg(getOperationFlgEnum(detail.getOPERATION_FLG()));
					TCustCustbasedata custBasedata = getCustBasedataByTableKeyword(custTransationOperation);
					// 对于客户位于审批中状态时，不发送客户信息
					if (custBasedata != null && !"1".equals(custBasedata.getFcuststatus())) {
						if(0==waitCustomerLogInfo.getERR_SYS()){
							waitCustomerLogInfo.setERR_SYS(7);
						}
						//判断是否发送网点规划 
						if(!CUSTOMTER_TO_WDGH.contains(custTransationOperation.getTableName().toString())){
							waitCustomerLogInfo.setERR_SYS(waitCustomerLogInfo.getERR_SYS()^Constant.WDGH_CODE);
						}
						// 根据客户主数据的fid去除重复
						custBasedataMap.put(custBasedata.getFid(), waitCustomerLogInfo.getERR_SYS());
					}
				}
			} catch (CrmBusinessException e) {
				// 更新错误标记
				waitCustomerLogInfo.setERROR_CODE("5001");  // 检索CRM待发送会员数据时出错
				waitCustomerLogInfo.setERR_DESC(e.getMessage().length() >100 ?  e.getMessage().substring(0, 100) : e.getMessage());
				waitCustomerLogInfo.setHANDLE_TYPE("M");
				return true;
			}
			if(custBasedataMap == null || custBasedataMap.size()<1){
				// 更新错误标记
				waitCustomerLogInfo.setERROR_CODE("5001");  // 检索CRM待发送会员数据时出错
				waitCustomerLogInfo.setERR_DESC("未查询出相关客户数据或客户为审批中");
				waitCustomerLogInfo.setHANDLE_TYPE("M");
				return false;
			}

			boolean totalResult = true;
			boolean result ;
			// 对每一个会员信息，得到各相关表的数据。（一般情况一次调用应该只会有一个会员，即传入的各表的待传输信息都是属于同一个会员的）
			for (Map.Entry<BigDecimal ,Integer> entry : custBasedataMap.entrySet()) {
					WaitingSendRequest waitingSenderRequest = getWaitingSendRequest(entry.getKey()+"", 0);
					// 集群方式时，需要解开下面一行代码，更新状态为“处理中”，并且立即提交数据库事务。
					// waitCustomerLogDao.updateSendingFlg(custbasedata.getFid().toString());
					// 数据发送FOSS
					if (( entry.getValue() & customerInfoFossSender.getTargetSysCode()) == customerInfoFossSender.getTargetSysCode()) { 	
						result = sendFoss(waitCustomerLogInfo,waitingSenderRequest);
					    if(!result){ 
					    	totalResult = false; 
					    }
					}
					// 数据发送CC
					if (( entry.getValue() & customerInfoCcSender.getTargetSysCode()) == customerInfoCcSender.getTargetSysCode()) { 	
						result = sendCc(waitCustomerLogInfo,waitingSenderRequest);
						 if(!result){
						    totalResult = false;
						 }					
					} 
					
					// 数据发送WDGH
					if (( entry.getValue() & customerBaseInfoWdghSender.getTargetSysCode()) == customerBaseInfoWdghSender.getTargetSysCode()) { 	
						result = sendCustInfoToWdgh(waitCustomerLogInfo,entry.getKey()+"");
						 if(!result){
						    totalResult = false;
						 }	
					}
				}
             if(!totalResult){
            	 //信息推送失败，需要重新发送
            	 waitCustomerLogInfo.setHANDLE_TYPE("A");
             }
			return totalResult;
		
		} catch (Throwable e) {
			logger.error("不可预期的系统异常5099", e);
			waitCustomerLogInfo.setERROR_CODE("5099"); // 不可预期的异常。
			waitCustomerLogInfo.setERR_DESC("不可预期的系统异常"+(e.getMessage()!=null ?  e.getMessage().substring(0, 100) : e.getMessage()));
			waitCustomerLogInfo.setHANDLE_TYPE("M");
			waitCustomerLogInfo.setERR_SYS(7);
			return false;
		}
	}
	/***
	 * 数据推送至CC
	 * @param waitCustomerLogInfo
	 * @param waitingSendRequest
	 */
	private boolean sendCc(WaitCustomerLogInfo waitCustomerLogInfo,WaitingSendRequest waitingSendRequest){
		try{   
			  WaitingSendCcRequest waitingSendFossRequest = ClientTool.converToWaitingSendCcRequest(waitingSendRequest);
			  customerInfoCcSender.send(waitCustomerLogInfo.getTRANSACTION_NO(), waitingSendFossRequest);
              return true;
		}catch(Exception e){
			logger.info("客户主数据库同步CC失败", e);
			waitCustomerLogInfo.setHANDLE_TYPE("M");
			waitCustomerLogInfo.setERROR_CODE("5002"); // 检索CRM客户主数据相关数据明细数据时出错
			waitCustomerLogInfo.setERR_DESC(e.getMessage().length() >100 ?  e.getMessage().substring(0, 100) : e.getMessage());
			return false;
		}	
	}
	/***
	 * 数据推送至FOSS
	 * @param waitCustomerLogInfo
	 * @param waitingSendRequest
	 */
	private boolean sendFoss(WaitCustomerLogInfo waitCustomerLogInfo,WaitingSendRequest waitingSendRequest){
		try{   
			  WaitingSendFossRequest waitingSendFossRequest = ClientTool.converToWaitingSendFossRequest(waitingSendRequest);
			  customerInfoFossSender.send(waitCustomerLogInfo.getTRANSACTION_NO(), waitingSendFossRequest);
             return true;
		}catch(Exception e){
			logger.info("客户主数据库同步FOSS失败", e);
			waitCustomerLogInfo.setHANDLE_TYPE("M");
			waitCustomerLogInfo.setERROR_CODE("5002"); // 检索CRM客户主数据相关数据明细数据时出错
			waitCustomerLogInfo.setERR_DESC(e.getMessage().length() >100 ?  e.getMessage().substring(0, 100) : e.getMessage());
			return false;
			}
		}	


	/***
	 * 数据推送至网点规划系统
	 * @param waitCustomerLogInfo
	 * @param memberId
	 * @return
	 */
	private boolean sendCustInfoToWdgh(WaitCustomerLogInfo waitCustomerLogInfo,String memberId){
		try{
			CustDetalInfo detailInfo = this.searchMemberInfosDao.getSenderWdghInfo(memberId);
			detailInfo.setTransactionCode(waitCustomerLogInfo.getTRANSACTION_NO());
			SyncCustInfoRequest request = new SyncCustInfoRequest();
			request.getCustDetalInfo().add(detailInfo);
			customerBaseInfoWdghSender.send(waitCustomerLogInfo.getTRANSACTION_NO(), request);
			return true;
		}catch(Exception e){
			logger.error("客户主数据库同步WDGH5099", e);
			waitCustomerLogInfo.setERROR_CODE("5099"); // 不可预期的异常。
			waitCustomerLogInfo.setERR_DESC("不可预期的系统异常"+(e.getMessage()!=null ?  e.getMessage().substring(0, 100) : e.getMessage()));
			waitCustomerLogInfo.setHANDLE_TYPE("A");
			return false;
		}
	}
	
	/**
	 * 
	 * @param optFlg
	 * @return
	 * @throws CrmBusinessException
	 */
	private CustTransationOperation.OperationFlg getOperationFlgEnum(String optFlg) throws CrmBusinessException {
		if ("I".equals(optFlg)) {
			return CustTransationOperation.OperationFlg.I;
		} else if ("U".equals(optFlg)) {
			return CustTransationOperation.OperationFlg.U;
		}  else if ("D".equals(optFlg)) {
			return CustTransationOperation.OperationFlg.D;
		}  else {
			throw new CrmBusinessException("Not exists operation flag.");
		}
	}
	
	/**
	 * @return
	 * @throws CrmBusinessException 
	 */
	private CustTransationOperation.CustomerTableName getTableNameEnum(String tableName) 
			throws CrmBusinessException {
		if ("T_CUST_CUSTBASEDATA".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_CUSTBASEDATA;
		} else if ("T_CUST_CUSTLINKMAN".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_CUSTLINKMAN;
		} else if ("T_CUST_SHUTTLEADDRESS".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_SHUTTLEADDRESS;
		} else if ("T_CUST_CONTRACT".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_CONTRACT;
		} else if ("T_CUST_ACCOUNT".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_ACCOUNT;
		} else if ("T_CUST_PREFERENCEADDRESS".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_PREFERENCEADDRESS;
		} else if ("T_CUST_CONTRACTDEPT".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_CONTRACTDEPT;
		} else if ("T_CUST_PREFERENTIAL".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_PREFERENTIAL;
		} else if ("T_CUST_CONTRACTTAX".equals(tableName)) {
			return CustTransationOperation.CustomerTableName.T_CUST_CONTRACTTAX;
		}else {
			throw new CrmBusinessException("Not exists table name.");
		}
	}

	/**
	 * 根据各表的主键得到对应的客户基础数据的记录信息
	 * @param custTransationOperation 待发送的表信息
	 * @return 客户基础数据的记录信息
	 */
	private TCustCustbasedata getCustBasedataByTableKeyword(CustTransationOperation custTransationOperation) {
		TCustCustbasedata custBasedata = null;
		switch (custTransationOperation.getTableName()) {
		case T_CUST_CUSTBASEDATA:
			// 客户基础数据表
			custBasedata = searchMemberInfosDao.searchMemberInfo(custTransationOperation.getKeyword());
			break;
		case T_CUST_ACCOUNT:
			// 客户账户资料表
			custBasedata = searchMemberInfosDao.getCustomerInfoByAccount(custTransationOperation.getKeyword());
			break;
		case T_CUST_CONTRACT:
			// 客户合同表
			custBasedata = searchMemberInfosDao.getCustomerInfoByContract(custTransationOperation.getKeyword());
			break;
		case T_CUST_CONTRACTDEPT:
			// 客户部门-合同关联表
			custBasedata = searchMemberInfosDao.getCustomerInfoByContractDept(custTransationOperation.getKeyword());
			break;
		case T_CUST_CUSTLINKMAN:
			// 客户联系人表
			custBasedata = searchMemberInfosDao.getCustomerInfoByLinkMan(custTransationOperation.getKeyword());
			break;
		case T_CUST_PREFERENCEADDRESS:
			// 客户联系人-接送货地址关联表
			custBasedata = searchMemberInfosDao.getCustomerInfoByPreferenceAddress(custTransationOperation.getKeyword());
			break;
		case T_CUST_PREFERENTIAL:
			// 客户优惠信息表
			custBasedata = searchMemberInfosDao.getCustomerInfoByPreferential(custTransationOperation.getKeyword());
			break;
		case T_CUST_SHUTTLEADDRESS:
			// 客户接送货地址表
			custBasedata = searchMemberInfosDao.getCustomerInfoByShuttleAddress(custTransationOperation.getKeyword());
			break;
		case T_CUST_CONTRACTTAX:
			/**合同发票标记子公司*/
			custBasedata = searchMemberInfosDao.getCustomerInfoByContractTax(custTransationOperation.getKeyword());
			break;
		default:
			break;
		}
		return custBasedata;
	}
	
	/**
	 * 检索相关表，组装JSON字符串
	 * @param memberNumber 基础信息表的fid
	 * @param pattern 场景
	 * @return JSON字符串
	 * @throws CrmBusinessException
	 */
	private WaitingSendRequest getWaitingSendRequest(String memberNumber, int pattern) throws CrmBusinessException {
		
		// 从数据库中检索出各表的待发送记录，并组装成WaitingSendRequest对象
		WaitingSendRequest waitingSendData = new WaitingSendRequest();
		waitingSendData.setPattern(pattern);
		switch (pattern) {
		case 0:
			// 客户基础信息表
			TCustCustbasedataOperation tcc = searchMemberInfosDao.searchMemberInfo(memberNumber);
			/*// 根据客户实体获取省市Code
			String pCode = searchMemberInfosDao.getProvinceCode(tcc.getFprovinceid().toString());
			String cCode = searchMemberInfosDao.getCityCode(tcc.getFcityid().toString());
			String pName=searchMemberInfosDao.getProvinceName(tcc.getFprovinceid().toString());
			String cName=searchMemberInfosDao.getCityName(tcc.getFcityid().toString());
			tcc.setFprovinceid(new BigDecimal(pCode));
			tcc.setFcityid(new BigDecimal(cCode));
			tcc.setFprovince(pName);
			tcc.setFcity(cName);*/
			waitingSendData.settCustCustbasedata(tcc);
			String custBaseDatafid = waitingSendData.gettCustCustbasedata().getFid().toString();
			// 客户账户表
			List<TCustAccountOperation> accountInfoList = searchMemberInfosDao.searchAccountInfoByCustomId(custBaseDatafid);
			if (accountInfoList != null && accountInfoList.size() > 0) waitingSendData.gettCustAccount().addAll(accountInfoList);
			// 客户接送货地址表
			List<TCustShuttleaddressOperation> shuttleAddressInfoList = searchMemberInfosDao.searchShuttleAddressByCustomerId(custBaseDatafid);
			if (shuttleAddressInfoList != null &&  shuttleAddressInfoList.size() > 0) waitingSendData.gettCustShuttleaddress().addAll(shuttleAddressInfoList);
			// 客户联系人表
			List<TCustCustlinkmanOperation> contactInfoList = searchMemberInfosDao.searchContactInfoByCustomerId(custBaseDatafid);
			if (contactInfoList != null && contactInfoList.size() > 0) waitingSendData.gettCustCustlinkman().addAll(contactInfoList);
			// 客户合同表
			List<TCustContractOperation> contractInfoList = searchMemberInfosDao.searchContractInfoByCustomerId(custBaseDatafid);
			if (contractInfoList != null && contractInfoList.size() > 0) waitingSendData.gettCustContract().addAll(contractInfoList);
			// 客户合同-部门关联表
			// 客户合同优惠表
			//合同发票子公司表
			for (TCustContractOperation contract_element : waitingSendData.gettCustContract()) {
				String contractFid = contract_element.getFid().toString();
				List<TCustContractdeptOperation> contractDeptList = searchMemberInfosDao.searchContractDeptByContractId(contractFid);
				if (contractDeptList != null && contractDeptList.size() > 0) waitingSendData.gettCustContractdept().addAll(contractDeptList);
				List<TCustPreferentialOperation> preferentialInfoList = searchMemberInfosDao.searchPreferentialByContractId(contractFid);
				waitingSendData.gettCustPreferential().addAll(preferentialInfoList);
				/**
				 * 合同税务子公司
				 */
				List<TCustContractTaxOperation> contractTaxInfoList = searchMemberInfosDao.searchContractTaxByContractId(contractFid);
				if (contractTaxInfoList != null && contractTaxInfoList.size() > 0) waitingSendData.gettCustContractTax().addAll(contractTaxInfoList);
			}
			// 客户联系人接送货关联表
			List<TCustPreferenceaddressOperation> preferenceAddressInfoList = searchMemberInfosDao.searchPreferenceAddressByMemberId(custBaseDatafid);
			if (preferenceAddressInfoList != null && preferenceAddressInfoList.size() > 0) waitingSendData.gettCustPreferenceaddress().addAll(preferenceAddressInfoList);
			
			// 检索需要同步的数据对象，并序列化为JSON
			return waitingSendData;
		default:
			return null;
		}
	}
	public IWaitCustomerLogDao getWaitCustomerLogDao() {
		return waitCustomerLogDao;
	}

	public void setWaitCustomerLogDao(IWaitCustomerLogDao waitCustomerLogDao) {
		this.waitCustomerLogDao = waitCustomerLogDao;
	}

	public ISearchMemberInfosDao getSearchMemberInfosDao() {
		return searchMemberInfosDao;
	}

	public void setSearchMemberInfosDao(ISearchMemberInfosDao searchMemberInfosDao) {
		this.searchMemberInfosDao = searchMemberInfosDao;
	}

	public ICustomerInfoCcSender getCustomerInfoCcSender() {
		return customerInfoCcSender;
	}

	public void setCustomerInfoCcSender(ICustomerInfoCcSender customerInfoCcSender) {
		this.customerInfoCcSender = customerInfoCcSender;
	}

	public ICustomerInfoFossSender getCustomerInfoFossSender() {
		return customerInfoFossSender;
	}

	public void setCustomerInfoFossSender(
			ICustomerInfoFossSender customerInfoFossSender) {
		this.customerInfoFossSender = customerInfoFossSender;
	}

	public ICustomerBaseInfoWdghSender getCustomerBaseInfoWdghSender() {
		return customerBaseInfoWdghSender;
	}

	public void setCustomerBaseInfoWdghSender(
			ICustomerBaseInfoWdghSender customerBaseInfoWdghSender) {
		this.customerBaseInfoWdghSender = customerBaseInfoWdghSender;
	}

}
