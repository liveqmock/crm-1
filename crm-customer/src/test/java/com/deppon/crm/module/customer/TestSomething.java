package com.deppon.crm.module.customer;

import java.util.List;

import com.deppon.crm.module.customer.server.service.impl.ContractService;
import com.deppon.crm.module.customer.server.util.SpringTestHelper;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;

public class TestSomething {

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-13
	 * @param args
	 * void
	 */
	public static void main(String[] args) {
		
		ContractService contractService = SpringTestHelper.getBean(ContractService.class);

//		List<Double> moneyList = contractService.getLatelyDeliverMoney("207112",3);
//		for (Double double1 : moneyList) {
//			System.out.println(double1);
//		}
//		System.out.println("-----over----------");
//		
//		List<Contract> contractList = contractService.getContractsByMemberId("2071");
//		for (Contract contract : contractList) {
//			System.out.println(contract);
//		}
		List<ContractOperatorLog> logs = contractService.searchOperaLogByWorkflowId("1778748.8491091914");
		Contract contract = logs.get(0).getContract();
		System.out.println(contract.getMember().getId());
		System.out.println(contract.getMember().getCustNumber());
		System.out.println("-----over2----------");
	
	
	}

}
