package com.deppon.crm.module.interfaces.workflow;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

/**
 * 礼品申请结果结果返回服务
 * @author davidcun @2012-3-23
 */
@WebService
@SOAPBinding(parameterStyle=ParameterStyle.BARE)
public interface IGiftApplyResultService {

	/**
	 * <P>需求：LMS-2</p>
	 * 礼品申请结果返回
	 * @author davidcun 2012-3-23
	 * @param       
	 * @return      
	 * @Throws 
	 *
	 */
	public boolean returnGiftApplyResult(@WebParam(name="resultInfo") WorkflowApplyResultInfo resultInfo) throws  CrmBusinessException;
	
	/**
	 * <P>需求：LMS-2</p>
	 * 礼品申请结果返回
	 * @author 罗典  2012-9-11
	 * @param       
	 * @return      
	 * @Throws 
	 *
	 */
	public boolean returnGiftResult(String xmlResultInfo)throws  CrmBusinessException;
}
