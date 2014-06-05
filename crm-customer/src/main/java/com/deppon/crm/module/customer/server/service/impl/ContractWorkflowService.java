package com.deppon.crm.module.customer.server.service.impl;

import com.deppon.crm.module.customer.server.dao.IContractWorkflowDao;
import com.deppon.crm.module.customer.server.service.IContractWorkflowService;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
/**
 * 
 * <p>
 * Description:合同工作流service实现<br />
 * </p>
 * @title ContractWorkflowService.java
 * @package com.deppon.crm.module.customer.server.service.impl 
 * @author 106138
 * @version 0.1 2013-12-19
 */
public class ContractWorkflowService implements IContractWorkflowService {
	private IContractWorkflowDao contractWorkflowDao;
	/**
	 * 
	 * <p>
	 * Description:保存合同工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2013-12-19
	 * @param info
	 *
	 */
	@Override
	public void saveContractWorkflowInfo(ContractWorkflowInfo info) {
		contractWorkflowDao.saveContractWorkflowInfo(info);
		
	}
	/**
	 * 
	 * <p>
	 * Description:根据业务编查询合同工作流信息<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2013-12-19
	 * @param busino
	 * @return
	 *
	 */
	@Override
	public ContractWorkflowInfo queryContractWorkflowInfoByBusino(String busino) {
		return contractWorkflowDao.queryContractWorkflowInfoByBusino(busino);
	}
	public IContractWorkflowDao getContractWorkflowDao() {
		return contractWorkflowDao;
	}
	public void setContractWorkflowDao(IContractWorkflowDao contractWorkflowDao) {
		this.contractWorkflowDao = contractWorkflowDao;
	}

}
