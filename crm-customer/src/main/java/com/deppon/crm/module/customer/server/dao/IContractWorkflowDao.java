package com.deppon.crm.module.customer.server.dao;

import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;

/**
 * 
 * <p>
 * Description：合同工作流dao />
 * </p>
 * @title IContractWorkflowService.java
 * @package com.deppon.crm.module.customer.server.service 
 * @author 106138
 * @version 0.1 2013-12-19
 */
public interface IContractWorkflowDao {
	/**
	 * 
	 * <p>
	 * Description:保存合同工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2013-12-19
	 * @param info
	 * void
	 */
	void saveContractWorkflowInfo(ContractWorkflowInfo info);
	/**
	 * 
	 * <p>
	 * Description:根据业务编查询合同工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2013-12-19
	 * @param busino
	 * @return
	 * ContractWorkflowInfo
	 */
	
	ContractWorkflowInfo queryContractWorkflowInfoByBusino(String busino);
	
}
