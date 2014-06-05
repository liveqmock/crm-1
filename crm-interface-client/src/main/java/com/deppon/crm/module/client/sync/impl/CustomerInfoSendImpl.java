package com.deppon.crm.module.client.sync.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.order.impl.OrderOperateImpl;
import com.deppon.crm.module.client.sync.ICustomerInfoSend;
import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;

public class CustomerInfoSendImpl implements ICustomerInfoSend {
	private static Log log = LogFactory.getLog(OrderOperateImpl.class);
	private ESBService esbService;
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.impl.ICustomerInfoSend#send(java.lang.String)
	 */
	@Override
	public boolean send(String transactionNo, String jsonString,String targetSystems) throws CrmBusinessException {
		
		EsbJsonRequest ejr = new EsbJsonRequest();
		ejr.setSystemName(Constant.SYSTEM_NAME);
		ejr.setServiceCode(Constant.ESB2ERP_CUSTOMERDATASYNC);
		ejr.setTargetSystems(targetSystems);
		ejr.setRequestId(transactionNo);
		ejr.setBody(jsonString);
		try {
			EsbJsonResponse esbJsonResponse = esbService.process(ejr);
			if (esbJsonResponse !=null && "SUCCESS".equalsIgnoreCase(esbJsonResponse.getStatus())) {
				return true;
			} else {
				String esbMessage = "";
				if (esbJsonResponse != null) {
					esbMessage = esbJsonResponse.getMessage();
				}
				if (log.isErrorEnabled()) {
						log.error(String.format("esb returned a error: %s", esbMessage));
				}
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,esbMessage);	
			}
		} catch (Exception_Exception e) {
			e.printStackTrace();
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e, e.getFaultInfo().getMessage());
		} catch (org.apache.cxf.interceptor.Fault fault) {
			fault.printStackTrace();
			if (log.isWarnEnabled()) {
				log.warn(fault.getMessage());
			}
			if (fault.getCause() instanceof java.net.SocketTimeoutException) {
				if (log.isWarnEnabled()) {
					log.warn(fault.getCause().getMessage());
				}
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_TIME_OUT, fault, fault.getMessage());
			} else {
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, fault, fault.getMessage());
			}
		} catch (Throwable t) {
			t.printStackTrace();
			if (log.isErrorEnabled()) {
				log.error(t.getMessage());
			}
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, t, t.getMessage());
		}
	}	

	public ESBService getEsbService() {
		return esbService;
	}

	public void setEsbService(ESBService esbService) {
		this.esbService = esbService;
	}
}