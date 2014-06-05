/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ContractApproveOperate.java
 * @package com.deppon.crm.module.customer.bpsworkflow 
 * @author pgj
 * @version 0.1 2013-11-16
 */
package com.deppon.crm.module.customer.bpsworkflow;

import java.util.Date;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.workflow.domain.ContractInfo;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;

/**
 * <p>
 * Description:合同工作流审批操作类<br />
 * </p>
 * 
 * @title ContractApproveOperate.java
 * @package com.deppon.crm.module.customer.bpsworkflow
 * @author pgj
 * @version 0.1 2013-11-16
 */
@Transactional
public class ContractApproveOperate {
	// 合同manager
	private IContractManager contractManager;
	// bps工作流manager
	private IBpsWorkflowManager bpsWorkflowManager;

	/**
	 * @param contractManager
	 *            : set the property contractManager.
	 */
	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}

	/**
	 * @param bpsWorkflowManager
	 *            : set the property bpsWorkflowManager.
	 */
	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * IContractManager
	 */
	public IContractManager getContractManager() {
		return contractManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @return
	 * IBpsWorkflowManager
	 */
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}
	/**
	 * 
	 * <p>
	 * Description:工作流审批<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param busino
	 * @param workItemid
	 * @param procesId
	 * @param wkStatus
	 * @param wkman
	 * @param approveOpinion
	 * @param wktime
	 * @return
	 * boolean
	 */
	public boolean contractApprove(String busino, Long workItemid,
			Long procesId, boolean wkStatus, String wkman,
			String approveOpinion, Date wktime) {
		//d调用bpsjar包 的方法进行审批 返回一个map结果集 包含三个参数 success  isEnd isDisagree
		Map<String, Object> map = bpsWorkflowManager.workflowApprove(
				workItemid, procesId, wkStatus?"0":"1",
				approveOpinion, null);
		//获得审批结果是否成功
		Boolean isSuccess=(Boolean) map.get("sucess");
		if(!isSuccess){
			return isSuccess;
		}
		//如果是是结束 则更新合同操作日志未同意
		if ((Boolean) map.get("isEnd")) {
			//true
			contractManager.contractApprove(busino, (Boolean) map.get("isEnd"),
					wkman, wktime);
		}
		//如果不同意 则更新合同操作日志为不同意
		if ((Boolean) map.get("isDisAgree")) {
			//fasle
			contractManager.contractApprove(busino, false,
					wkman, wktime);
		}
		return isSuccess;
	}

	/**
	 * 
	 * <p>
	 * Description:生成合同信息<br />
	 * </p>
	 * 
	 * @author royxhl
	 * @version 0.1 2013-11-19
	 * @param contract
	 * @param map
	 * @return ContractWorkflowInfo
	 */
	public ContractWorkflowInfo createContractWorkflowInfo(Contract contract,
			Map<String, String> map) {

		ContractInfo contractInfo = contractManager.createContractInfo(
				contract, map);
		ContractWorkflowInfo contractWorkflowInfo = new ContractWorkflowInfo();
		BeanUtils.copyProperties(contractInfo, contractWorkflowInfo);

		return contractWorkflowInfo;
	}

}
