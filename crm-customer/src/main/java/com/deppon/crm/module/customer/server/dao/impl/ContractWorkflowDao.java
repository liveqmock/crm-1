package com.deppon.crm.module.customer.server.dao.impl;

import com.deppon.crm.module.customer.server.dao.IContractWorkflowDao;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ContractWorkflowDao extends iBatis3DaoImpl implements
		IContractWorkflowDao {
	private static final String NAMESPACE = "com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo.";
	// 保存审批数据
	private static final String INSERTCONTRACTWORKFLOWINFO = "insertContractWorkflowInfo";
	// 根据工作流ID查询审批数据
	private static final String QUERYCONTRACTWORKFLOWINFOBYBUSINO = "queryContractWorkflowInfoByBusino";

	@Override
	public void saveContractWorkflowInfo(ContractWorkflowInfo info) {
		this.getSqlSession().insert(NAMESPACE + INSERTCONTRACTWORKFLOWINFO,
				info);
	}

	@Override
	public ContractWorkflowInfo queryContractWorkflowInfoByBusino(String busino) {
		return (ContractWorkflowInfo) this.getSqlSession().selectOne(
				NAMESPACE + QUERYCONTRACTWORKFLOWINFOBYBUSINO, busino);
	}

}
