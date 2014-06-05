package com.deppon.crm.module.interfaces.workflow;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.foss.domain.ObjectFactory;


/**
 * @作者：罗典
 * @描述：工作流审批结果返回
 * @时间：2012-11-12
 * */
@WebService
public interface IWorkflowApplyResultService {
	/**
	 * @作者：罗典
	 * @描述：退运费审批工作流结果返回
	 * @时间：2012-11-18
	 * @参数：工作流审批参数
	 * @返回：是否成功
	 * */
	public boolean returnBackFreightResult(String resultInfo) throws CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @描述：服务补救审批工作流结果返回
	 * @时间：2012-11-18
	 * @参数：工作流审批参数
	 * @返回：是否成功
	 * */
	public boolean returnServiceRecoveryResult(String resultInfo) throws CrmBusinessException;

}
