package com.deppon.crm.module.customer.server.manager;


import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;


/**
 * 
 * <p>
 * Description:合同工作流manager<br />
 * </p>
 * 
 * @title IContactWorkflowManager.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author royxhl
 * @version 0.1 2013-11-15
 */
public interface IContractWorkflowManager {

	
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author royxhl
	 * @version 0.1 2013-11-19
	 * @param workItemId
	 * @param processType
	 * void
	 */
	ContractWorkflowInfo findContractWorkflowInfoByWorkNo(String workItemId, String processType);
	/**
	 * <p>
	 * Description:根据工作流号查询操作人br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-11-28
	 * @param workflowNo
	 * @return
	 * Department
	 */
	User searchOperaDeptByWorkflowId(String workflowNo);

}
