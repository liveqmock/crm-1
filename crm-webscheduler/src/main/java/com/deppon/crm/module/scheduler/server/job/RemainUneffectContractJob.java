package com.deppon.crm.module.scheduler.server.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 30天内到期合同提醒定时器
 * </p>
 * @title RemainUneffectContractJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-15
 */
public class RemainUneffectContractJob extends GridJob{
	private IContractManager contractManager;
	private MessageManager messageManager;
	
	/**
	 * 
	 * @description 30天内到期合同提醒.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return void
	 * @update 2012-9-19 下午1:42:00
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean("contractManager", ContractManager.class);
		messageManager = getBean("messageManager", MessageManager.class);
		List<Contract> contracts = contractManager
				.searchUneffectContractIn30Days();
		if (null != contracts && 0 < contracts.size()) {
			for (Contract contract : contracts) {
				Message todoMessage = new Message();
				todoMessage
						.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
				todoMessage.setTaskcount(1);
				todoMessage.setDeptId(Integer.parseInt(contract.getDept()
						.getId()));
				todoMessage.setIshaveinfo("您部门序号为" + contract.getContractNum()
						+ "的合同即将到期，请尽快新签合同，否则逾期将无法使用合同。");
				messageManager.addMessage(todoMessage);
			}
		}
	}
}
