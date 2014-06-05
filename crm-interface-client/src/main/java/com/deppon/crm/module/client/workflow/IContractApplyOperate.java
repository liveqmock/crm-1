package com.deppon.crm.module.client.workflow;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.domain.ContractApplyType;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.client.workflow.domain.WorkFlowState;

/**
 * 合同审批工作流申请
 * @author davidcun
 * @2012-3-14
 * description
 */
public interface IContractApplyOperate {
	/**
	 * @作者：罗典
	 * @时间：2013-1-30
	 * @描述：删除OA工作流(暂时只有客户模块合同功能会使用此接口)
	 * @参数：工作流编号，工作流类型
	 * @返回：是否成功
	 * */
	public boolean deleteWorkflow(String workflowNum,String workflowType)throws CrmBusinessException;
	/**
	 * <p>需求点：OA-8,OA-11,OA-12,OA-13,OA-14</p>
	 * 合同信息新增或者其中某些信息修改之后需要审批，就调用此接口，针对不同的值提供不同审批类型。
	 * description:
	 * @author davidcun 2012-3-14
	 * @param  oldValue 对合同信息修改之前的信息
	 * @param newValue 合同信息修改之后的信息
	 * @param applyType 合同审批类型
	 * @return {@link String} 返回工作流号
	 * @exception 
	 */
	public String contractApply(ContractInfo contractInfo,ContractApplyType applyType) throws CrmBusinessException;
	/**
	 * <p>需求点：OA-8,OA-11,OA-12,OA-13,OA-14</p>
	 * 合同信息新增或者其中某些信息修改之后需要审批，就调用此接口，针对不同的值提供不同审批类型。
	 * description:
	 * @author davidcun 2012-3-14
	 * @param  oldValue 对合同信息修改之前的信息
	 * @param newValue 合同信息修改之后的信息
	 * @param applyType 合同审批类型
	 * @return {@link String} 返回工作流号
	 * @exception 
	 */
	public String contractApply(ContractInfo contractInfo,ContractApplyType applyType,String bizCode) throws CrmBusinessException;
	/**
	 * @作者:罗典
	 * @时间：2012-8-30
	 * @描述：根据工作流审批时间段及类型查询工作流相关信息集合
	 * @参数： 起始时间  结束时间  工作流类型：(WFS_HT_001 月结 WFS_LP_001 常规理陪 WFS_LP_002 多赔)
	 */
	public List<WorkFlowState> queryWorkFlowApproveState(Date beginDate,Date endDate,String workflowType)throws CrmBusinessException;
}
