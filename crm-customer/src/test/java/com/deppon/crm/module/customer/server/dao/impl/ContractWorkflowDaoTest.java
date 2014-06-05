package com.deppon.crm.module.customer.server.dao.impl;

import java.sql.SQLException;

import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.ContractWorkflowInfo;

public class ContractWorkflowDaoTest extends BeanUtil{
	ContractWorkflowInfo workflowInfo = null;
	public void setUp(){
 		workflowInfo = new ContractWorkflowInfo();
		workflowInfo.setBusino("TEST001001");
	}
	@Test
	public void testsaveContractWorkflowInfo(){
		contractWorkflowDao.saveContractWorkflowInfo(workflowInfo);
	}
	@Test
	public void testQueryContractWorkflowInfoByBusino(){
		ContractWorkflowInfo info=contractWorkflowDao.queryContractWorkflowInfoByBusino(workflowInfo.getBusino());
		assertNotNull(info);
		String sql = "delete from t_cust_contractworkflowinfo " +
				"where fbusino = '"+workflowInfo.getBusino()+"'";
		try {
			SpringTestHelper.getConnection().createStatement().execute(sql);
		} catch (SQLException e) {
			assertFalse(true);
		}
	}
}
