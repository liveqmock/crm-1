package com.deppon.crm.module.client.sync.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.sync.ICustomerInfoFossSender;
import com.deppon.crm.module.client.sync.foss.WaitingSendFossRequest;
import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;

public class CustomerInfoFossSender implements ICustomerInfoFossSender {
	private static Log log = LogFactory.getLog(CustomerInfoFossSender.class);
	private ESBService esbService;
	/***
	 * @des 发送数据到 FOSS
	 * @author w-mm
	 * @createtime 2013-11-05
	 * @param transactionNo 事务号
	 *   waitingSenderRequest 推送foss的对象
	 */
	@Override
	public boolean send(String transactionNo,
			WaitingSendFossRequest waitingSenderRequest)
			throws CrmBusinessException {
		try {
				String jsonString = JsonMapperUtil
						.writeValue(waitingSenderRequest);
                log.debug("发送FOSS数据-->"+jsonString);

				EsbJsonRequest ejr = new EsbJsonRequest();
				ejr.setSystemName(Constant.SYSTEM_NAME);
				ejr.setServiceCode(Constant.ESB2ERP_CUSTOMERDATASYNC);
				ejr.setTargetSystems(Constant.FOSS_CODE_DESC);
				ejr.setRequestId(transactionNo);
				ejr.setBody(jsonString);

				EsbJsonResponse esbJsonResponse = esbService.process(ejr);
				if (esbJsonResponse != null
						&& "SUCCESS".equalsIgnoreCase(esbJsonResponse
								.getStatus())) {
					return true;
				} else {
					String esbMessage = "";
					if (esbJsonResponse != null) {
						esbMessage = esbJsonResponse.getMessage();
					}
					if (log.isErrorEnabled()) {
						log.error(String.format("esb returned a error: %s",
								esbMessage));
					}
					throw new CrmBusinessException(
							ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,
							esbMessage);
				}

		}catch (Exception_Exception e) {
			log.error("客户主数据同步FOSS异常",e);
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e, e.getFaultInfo().getMessage());
		} catch (org.apache.cxf.interceptor.Fault fault) {
			if (fault.getCause() instanceof java.net.SocketTimeoutException) {
              log.warn("客户主数据同步FOSS异常",fault);
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_TIME_OUT, fault, fault.getMessage());
			} else {
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, fault, fault.getMessage());
			}
		} catch (Exception e) {
            log.error("客户主数据同步FOSS异常", e);
            throw new CrmBusinessException(
					ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,
					e.getMessage());
		}
   }

	@Override
	public int getTargetSysCode() {
		return Constant.FOSS_CODE;
	}
	
	public ESBService getEsbService() {
		return esbService;
	}

	public void setEsbService(ESBService esbService) {
		this.esbService = esbService;
	}

	
}