package com.deppon.crm.module.customer.server.manager.impl;

import java.util.List;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IContractWorkflowManager;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IContractWorkflowService;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.shared.util.string.StringUtil;


/**
 * 
 * <p>
 * Description:合同工作流Manager实现类<br />
 * </p>
 * 
 * @title ContractWorkflowManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author royxhl
 * @version 0.1 2013-11-15
 */
public class ContractWorkflowManager implements IContractWorkflowManager {
	// 合同service
	private IContractService contractService;
	

	// 用户service
	private IUserService userService;
	// 员工service
	private IEmployeeService employeeService;
	// 工作流审批
	private IContractWorkflowService contractWorkflowService;



	

/**
 * 
 * <p>
 * Description:根据工作号查询工作流信息<br />
 * </p>
 * @author 106138
 * @version 0.1 2014年4月11日
 * @param workItemId
 * @param processType
 * @return
 *
 */
	@Override
	public ContractWorkflowInfo findContractWorkflowInfoByWorkNo(
			String workItemId, String processType) {
		ContractWorkflowInfo contractWorkflowInfo = null;
		//如果不為空
		if (StringUtil.isNotEmpty(workItemId)) {
			//進行查詢
			contractWorkflowInfo = contractWorkflowService
					.queryContractWorkflowInfoByBusino(workItemId);
		}
		return contractWorkflowInfo;
	}

	/**
	 * 
	 * <p>
	 * Description:根据工作流号查询操作人部门<br />
	 * </p>
	 * 
	 * @author pgj
	 * @version 0.1 2013-11-28
	 * @param workflowNo
	 * @return Department
	 */
	@Override
	public User searchOperaDeptByWorkflowId(String workflowNo) {
		List<ContractOperatorLog> contractLogList = contractService
				.searchOperaLogByWorkflowId(workflowNo);
		String userId = contractLogList.get(0).getCreateUser();
		Employee emp = employeeService.getEmpById(userId);
		User user = userService.findByLoginName(emp.getEmpCode());
		return userService.getUserRoleFunDeptById(user.getId());
	}
	/**
	 * <p>
	 * Description:contractService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IContractService getContractService() {
		return contractService;
	}

	/**
	 * <p>
	 * Description:contractService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}

	/**
	 * <p>
	 * Description:userService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * <p>
	 * Description:userService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * <p>
	 * Description:employeeService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * <p>
	 * Description:employeeService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * <p>
	 * Description:contractWorkflowService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public IContractWorkflowService getContractWorkflowService() {
		return contractWorkflowService;
	}

	/**
	 * <p>
	 * Description:contractWorkflowService<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月15日
	 */
	public void setContractWorkflowService(IContractWorkflowService contractWorkflowService) {
		this.contractWorkflowService = contractWorkflowService;
	}
}
