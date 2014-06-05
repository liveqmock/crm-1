package com.deppon.crm.module.interfaces.fin;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.deppon.crm.module.interfaces.foss.domain.BackFreightCheckRequest;
import com.deppon.crm.module.interfaces.foss.domain.BackFreightCheckResponse;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.foss.domain.ObjectFactory;

@WebService(targetNamespace = "http://www.deppon.com/crm/interface/CrmService", name = "IFinToCrmService")
@XmlSeeAlso({ ObjectFactory.class })
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
/**
 * @作者：罗典
 * @描述：财务系统对接CRM接口服务
 * @时间：2012-11-28
 * */
public interface IFinToCrmService {
	
	/**
	 * @作者：罗典
	 * @描述：校验是否存在有效的退运费信息
	 * @时间：2012-11-21
	 * @参数：运单号
	 * @返回：是否存在有效的退运费信息
	 * */
	@WebResult(name = "backFreightCheckResponse", targetNamespace = "http://www.deppon.com/crm/inteface/foss/domain", partName = "response")
	@WebMethod(action = "http://www.deppon.com/crm/interface/CrmService/backFreightCheck")
	public BackFreightCheckResponse backFreightCheck(
			@WebParam(partName = "request", name = "backFreightCheckRequest", targetNamespace = "http://www.deppon.com/crm/inteface/foss/domain") BackFreightCheckRequest request)
			throws CommException;
}
