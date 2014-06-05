package com.deppon.crm.module.interfaces.workflow;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseApplyResultInfo;
import com.deppon.crm.module.interfaces.workflow.domain.WorkflowApplyResultInfo;

@WebService
@SOAPBinding(parameterStyle=ParameterStyle.BARE,style=Style.DOCUMENT,use=Use.LITERAL)
public interface IESBWorkFlowResultService {

	/**
	 * <p>需求点：OA-9</p>
	 * 新增合同的审批结果返回
	 * @author davidcun 2012-3-19
	 * @param  
	 * @return 
	 * @throws CrmBusinessException
	 *
	 */
	public boolean returnContractApplyResult(@WebParam(name="ContractResultInfo") WorkflowApplyResultInfo resultInfo) throws CrmBusinessException;

	/**
	 * <p>需求点：OA-2</p>
	 * @param resultInfo 是常规理赔审批结果的返回方法参数
	 * {@link RecompenseApplyResultInfo}包括<b>工作流编号 、处理时间、处理人、
	 * 处理结果(审批通过/审批不通过)、处理意见</b>
	 * @author davidcun 2012-3-19
	 * @return 
	 * @throws CrmBusinessException
	 */
	public boolean returnNormalRecompenseResult(@WebParam(name="normalResultInfo") WorkflowApplyResultInfo resultInfo) throws CrmBusinessException;
	
	/**
	 * <p>需求点：OA-4</p>
	 * 多赔审批结果返回接口方法
	 * description:
	 * @author davidcun 2012-3-16
	 * @param       
	 * @return      
	 * @throws CrmBusinessException 表示可能抛出的业务异常代码
	 *
	 */
	public boolean returnMuchRecompenseResult(@WebParam(name="muchResultInfo") WorkflowApplyResultInfo resultInfo) throws CrmBusinessException;
}
