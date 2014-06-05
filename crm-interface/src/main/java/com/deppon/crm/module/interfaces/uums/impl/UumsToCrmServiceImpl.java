package com.deppon.crm.module.interfaces.uums.impl;


import java.util.List;

import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.log4j.Logger;

import com.deppon.crm.module.interfaces.common.util.Constant;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.uums.UumsToCrmService;
import com.deppon.crm.module.interfaces.uums.ws.CompanyInfo;
import com.deppon.crm.module.interfaces.uums.ws.ESBHeader;
import com.deppon.crm.module.interfaces.uums.ws.SendCompanyInfoProcessReult;
import com.deppon.crm.module.interfaces.uums.ws.SendCompanyInfoRequest;
import com.deppon.crm.module.interfaces.uums.ws.SendCompanyInfoResponse;
import com.deppon.crm.module.interfaces.uums.ws.SyncCompanyInfosFault;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;

public class UumsToCrmServiceImpl implements UumsToCrmService{

	private com.deppon.crm.module.uums.server.manager.impl.syncFacedManager syncFacedManager;
	private Logger logger = Logger.getLogger(UumsToCrmServiceImpl.class);
	
	@Override
	public SendCompanyInfoResponse syncCompanyInfos(
			SendCompanyInfoRequest syncCompanyInfosRequest,
			Holder<ESBHeader> esbHeader) throws SyncCompanyInfosFault{
		ESBHeader header = esbHeader.value;
		SendCompanyInfoResponse sendCompanyInfoResponse = new SendCompanyInfoResponse();
		List<SendCompanyInfoProcessReult> processResults = sendCompanyInfoResponse.getProcessResult();
		try {
			//参数验证
			IntefacesTool.checkNull(header, Constant.PARAMS_LOSE);
			//响应信息
			header.setResponseId(UUID.randomUUID().toString());
			header.setResultCode(1);
			//参数验证
			IntefacesTool.checkNull(syncCompanyInfosRequest.getCompanyInfo(), Constant.PARAMS_LOSE);
		} catch (CommException e) {
			e.printStackTrace();
		}
		
		int sucessNumber = 0;//成功条数
		int failNumber = 0;//失败条数
		SendCompanyInfoProcessReult sendCompanyInfoProcessReult;//处理结果
		//base模块 子公司对象
		com.deppon.crm.module.uums.shared.domain.CompanyInfo baseCompanyInfo; 
		
		for (CompanyInfo companyInfo : syncCompanyInfosRequest.getCompanyInfo()) {
			sendCompanyInfoProcessReult = new SendCompanyInfoProcessReult();
			sendCompanyInfoProcessReult.setCompanyCode(companyInfo.getCompanyCode());
			baseCompanyInfo = new com.deppon.crm.module.uums.shared.domain.CompanyInfo();
			try {
			//对象复制赋值
			BeanUtils.copyProperties(companyInfo, baseCompanyInfo);
			baseCompanyInfo.setTheChangeId(null);
			baseCompanyInfo.setTheMainCode(companyInfo.getCompanyCode());
			baseCompanyInfo.setTheMainId(null);
			baseCompanyInfo.setChangeDate(companyInfo.getChangeDate());
			baseCompanyInfo.setChangeType(companyInfo.getChangeType());
			//boolean类型值转化
			baseCompanyInfo.setWorkingUnit(companyInfo.isIsWorkingUnit());
			baseCompanyInfo.setSeal(companyInfo.isIsSeal());
			//调用业务模块
			int result = syncFacedManager.check(baseCompanyInfo);
			if (result == 1) {
				sendCompanyInfoProcessReult.setResult(true);
				sucessNumber += 1;
			}else {
				failNumber += 1;
				sendCompanyInfoProcessReult.setResult(false);
				sendCompanyInfoProcessReult.setReason(Constant.UUMS_EXCEPTION);
				}
			}catch(SyncCompanyInfosFault e){
				failNumber += 1;
				sendCompanyInfoProcessReult.setResult(false);
				sendCompanyInfoProcessReult.setReason(e.getMessage());
			}
			catch (Exception e) {
				logger.info("syncCompanyInfos - message:",e);
				failNumber += 1;
				header.setResultCode(0);
				sendCompanyInfoProcessReult.setResult(false);
				sendCompanyInfoProcessReult.setReason(e.getMessage());
			}
			processResults.add(sendCompanyInfoProcessReult);
		}
		sendCompanyInfoResponse.setSuccessCount(sucessNumber);
		sendCompanyInfoResponse.setFailedCount(failNumber);
		return sendCompanyInfoResponse;
	}

	/**
	 * Description:syncFacedManager<br />
	 * @author CoCo
	 * @version 0.1 2013-12-5
	 */
	public com.deppon.crm.module.uums.server.manager.impl.syncFacedManager getSyncFacedManager() {
		return syncFacedManager;
	}

	/**
	 * Description:syncFacedManager<br />
	 * @author CoCo
	 * @version 0.1 2013-12-5
	 */
	public void setSyncFacedManager(
			com.deppon.crm.module.uums.server.manager.impl.syncFacedManager syncFacedManager) {
		this.syncFacedManager = syncFacedManager;
	}
}
