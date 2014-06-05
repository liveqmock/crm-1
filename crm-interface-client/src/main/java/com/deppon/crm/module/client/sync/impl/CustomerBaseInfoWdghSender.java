package com.deppon.crm.module.client.sync.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.esb.domain.ESBClientHeader;
import com.deppon.crm.module.client.esb.trans.CrmToEsbRequestSender;
import com.deppon.crm.module.client.esb.trans.EsbUtil;
import com.deppon.crm.module.client.sync.ICustomerBaseInfoWdghSender;
import com.deppon.crm.module.client.sync.wdgh.ObjectFactory;
import com.deppon.crm.module.client.sync.wdgh.SyncCustInfoRequest;

public class CustomerBaseInfoWdghSender implements ICustomerBaseInfoWdghSender {	
	
	private static Log log = LogFactory.getLog(CustomerBaseInfoWdghSender.class);
	
	private CrmToEsbRequestSender esbRequestSender;
	/***
	 * 同步网点规划系统
	 * @param request
	 * @return
	 */
	@Override
	public boolean send(String transactionNo,SyncCustInfoRequest request) {
		String xmlStr;
		try {
			xmlStr = XmlJaxbMapper.writeValue(request,ObjectFactory._SyncCustInfoRequest_QNAME);
			ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SYNC_CUSTMOTER_TO_WDGH, 
					transactionNo);
			log.info("同步数据--->"+xmlStr);
			esbHeader.setExchangePattern(2);
			esbRequestSender.send(xmlStr, esbHeader);
		   
		} catch (CrmBusinessException e) {
			log.error("客户数据同步网点规划异常",e);
			return false;
		}
		return true;
	}
	public CrmToEsbRequestSender getEsbRequestSender() {
		return esbRequestSender;
	}
	public void setEsbRequestSender(CrmToEsbRequestSender esbRequestSender) {
		this.esbRequestSender = esbRequestSender;
	}
	
	
	public int getTargetSysCode(){
		return Constant.WDGH_CODE;
	}
	
	public String getTargetSysCodeDesc(){
		return "WDGH";
	}
	
}
