package com.deppon.crm.module.interfaces.bank.impl;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.common.server.service.IBankProvinceCityService;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;
import com.deppon.crm.module.interfaces.bank.IBankInfoSyncService;
import com.deppon.crm.module.interfaces.bank.domain.BankInfo;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoNotificationRequest;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoNotificationResponse;
import com.deppon.crm.module.interfaces.bank.domain.BankInfoProcessResult;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfo;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoNotificationRequest;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoNotificationResponse;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfoProcessResult;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;

/**
 * @作者: 罗典
 * @时间：2012-12-21下午4:31:37
 * @描述：
 */
public class BankInfoSyncServiceImpl implements IBankInfoSyncService {
	// 银行省份城市基础资料操作服务
	private IBankProvinceCityService bankProvinceCityService;

	/**
	 * @作者：罗典
	 * @时间：2012-12-21
	 * @描述：同步银行支行
	 * */
	@Override
	public BankInfoNotificationResponse receiveBank(BankInfoNotificationRequest request) throws CrmBusinessException{
		BankInfoNotificationResponse response = new BankInfoNotificationResponse();
		List<BankInfoProcessResult> processResultList = response.getProcessResult();
		NullOrEmptyValidator.checkNull(request,	"BankInfoNotificationRequest");
		NullOrEmptyValidator.checkNull(request.getBankInfo(),"BankInfoNotificationRequest.getBankInfo()");
		for (BankInfo bankInfo : request.getBankInfo()) {
			BankInfoProcessResult result = new BankInfoProcessResult();
			result.setResult(true);
			try{
				// 上级编码为空时，则为银行
				if (bankInfo.getSuperiorBankId() == null ||
						bankInfo.getSuperiorBankId().equals("")) {
					NullOrEmptyValidator.checkNull(bankInfo.getBankId(),"bankInfo.getBankId()");
					AccountBank accountBank = IntefacesTool.convertToAccountBank(bankInfo);
					// 已存在的生效银行信息(根据编码)
					AccountBank oldBank = bankProvinceCityService.getAccountBankByCode(bankInfo.getBankId());
					result.setBankId(bankInfo.getBankId());
					result.setOperateCode(bankInfo.getOperateCode());
					// 不存在生效记录则新增,存在则修改
					if(oldBank == null){
						bankProvinceCityService.insertAccountBank(accountBank);
						result.setOperateCode("1");
					}else{
						accountBank.setId(oldBank.getId());
						bankProvinceCityService.updateAccountBank(accountBank);
						result.setOperateCode("2");
					}
				}
				// 处理支行信息
				else {
					NullOrEmptyValidator.checkNull(bankInfo.getBankId(),"bankInfo.getBankId()");
					NullOrEmptyValidator.checkNull(bankInfo.getSuperiorBankId(),"bankInfo.getSuperiorBankId()");
					NullOrEmptyValidator.checkNull(bankInfo.getProvenceId(),"bankInfo.getProvenceId()");
					NullOrEmptyValidator.checkNull(bankInfo.getCityId(),"bankInfo.getCityId()");
					// 查询出银行信息
					AccountBank accountBank = 
							bankProvinceCityService.getAccountBankByCode(bankInfo.getSuperiorBankId());
					// 查询不到银行编码，请核实
					NullOrEmptyValidator.checkNull(accountBank,bankInfo.getSuperiorBankId(),"0040");
					// 查询省份信息
					BankProvince bankProvince = 
							bankProvinceCityService.getBankProvinceByCode(bankInfo.getProvenceId());
					// 查询不到省份编码，请核实
					NullOrEmptyValidator.checkNull(bankProvince,bankInfo.getProvenceId(),"0041");
					// 查询城市信息
					BankCity bankCity = bankProvinceCityService.getBankCityByCode(bankInfo.getCityId());
					// 查询不到城市编码，请核实
					NullOrEmptyValidator.checkNull(bankCity,bankInfo.getCityId(),"0042");
					// 已存在的生效支行信息(根据编码)
					AccountBranch oldBranch = 
							bankProvinceCityService.getAccountBranchByCode(bankInfo.getBankId());
					AccountBranch newBranch = IntefacesTool.convertToAccountBranch(bankInfo);
					newBranch.setBankId(accountBank.getId());
					newBranch.setProvinceId(bankProvince.getId());
					newBranch.setCityId(bankCity.getId());
					result.setBankId(bankInfo.getBankId());
					// 不存在生效记录则新增,存在则修改
					if (oldBranch == null) {
						bankProvinceCityService.insertAccountBranch(newBranch);
						result.setOperateCode("1");
					}else{
						newBranch.setId(oldBranch.getId());
						bankProvinceCityService.updateAccountBranch(newBranch);
						result.setOperateCode("2");
					}
				}
				result.setBankId(bankInfo.getBankId());
				result.setReason("SUCCESS");
				processResultList.add(result);
				response.setSuccessCount(response.getSuccessCount()+1);
			} catch (CrmBusinessException e){
				e.printStackTrace();
				result.setReason(e.getMessage());
				result.setResult(false);
				processResultList.add(result);
				response.setFailedCount(response.getFailedCount()+1);
			}catch(Exception e){
				e.printStackTrace();
				result.setResult(false);
				result.setReason(e.getMessage());
				processResultList.add(result);
				response.setFailedCount(response.getFailedCount()+1);
			}
		}
		return response;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-12-21
	 * @描述：同步省份城市
	 * */
	@Override
	public ProvinceCityInfoNotificationResponse receiveProvinceCity ( 
			ProvinceCityInfoNotificationRequest request) throws CrmBusinessException{
		ProvinceCityInfoNotificationResponse response = new ProvinceCityInfoNotificationResponse();
		 List<ProvinceCityInfoProcessResult>  processResultList = response.getProcessResult();
		NullOrEmptyValidator.checkNull(request,	"ProvinceCityInfoNotificationRequest");
		NullOrEmptyValidator.checkNull(request.getProvinceCityInfo(),
				"BankInfoNotificationRequest.getProvinceCityInfo()");
		for (ProvinceCityInfo provinceCityInfo : request.getProvinceCityInfo()) {
			ProvinceCityInfoProcessResult result = new ProvinceCityInfoProcessResult();
			result.setResult(false);
			result.setProvinceCityId(provinceCityInfo.getProvinceCityId());
			try{
				// 省份编码为空，则为省份信息
				if(provinceCityInfo.getProvenceId() == null ||
						provinceCityInfo.getProvenceId().equals("")	){
					BankProvince oldProvince = bankProvinceCityService.
							getBankProvinceByCode(provinceCityInfo.getProvinceCityId());
					BankProvince newProvince = IntefacesTool.convertToBankProvince(provinceCityInfo);
					// 不存在生效记录则新增,存在则修改
					if(oldProvince == null){
						result.setOperateCode("1");
						bankProvinceCityService.insertBankProvince(newProvince);
					}else{
						result.setOperateCode("2");
						newProvince.setId(oldProvince.getId());
						bankProvinceCityService.updateBankProvince(newProvince);
					}
				}else{
					BankProvince bankProvince = bankProvinceCityService.
							getBankProvinceByCode(provinceCityInfo.getProvenceId());
					// 查询不到省份编码，请核实
					NullOrEmptyValidator.checkNull(bankProvince,provinceCityInfo.getProvenceId(),"0041");
					BankCity newCity = IntefacesTool.convertToBankCity(provinceCityInfo);
					newCity.setProvinceId(bankProvince.getId());
					BankCity oldCity = bankProvinceCityService.
							getBankCityByCode(provinceCityInfo.getProvinceCityId());
					// 不存在生效记录则新增,存在则修改
					if(oldCity == null){
						result.setOperateCode("1");
						bankProvinceCityService.insertBankCity(newCity);
					}else{
						result.setOperateCode("2");
						newCity.setId(oldCity.getId());
						bankProvinceCityService.updateBankCity(newCity);
					}
				}
				result.setResult(true);
				result.setProvinceCityId(provinceCityInfo.getProvinceCityId());
				result.setReason("SUCCESS");
				response.setSuccessCount(response.getSuccessCount()+1);
			}catch(CrmBusinessException e){
				e.printStackTrace();
				result.setResult(false);
				result.setReason(e.getMessage());
				processResultList.add(result);
				response.setFailedCount(response.getFailedCount()+1);
			}catch(Exception e){
				e.printStackTrace();
				result.setResult(false);
				result.setReason(e.getMessage());
				processResultList.add(result);
				response.setFailedCount(response.getFailedCount()+1);
			}
		}
		return response;
	}

	public IBankProvinceCityService getBankProvinceCityService() {
		return bankProvinceCityService;
	}

	public void setBankProvinceCityService(
			IBankProvinceCityService bankProvinceCityService) {
		this.bankProvinceCityService = bankProvinceCityService;
	}

}
