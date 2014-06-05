package com.deppon.crm.module.scheduler.server.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * 合同生效提醒定时器
 * </p>
 * @title RemanidEffectContractJob.java
 * @package com.deppon.crm.module.scheduler.server.job
 * @author 唐亮
 * @version 0.1 2013-5-15
 */
public class RemanidEffectContractJob extends GridJob{
	private IContractManager contractManager;
	private MessageManager messageManager;
	/**
	 * 
	 * @description 新合同生效时，在其绑定部门待办事项中， 给予系统信息提示“您部门所绑定的序号为XXXXX的合同，
	 *              已由合同归属部门新签为XXXXX，现已生效可继续使用，请在系统查看！”
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param
	 * @date 2012-9-19
	 * @return void
	 * @update 2012-9-19 下午1:42:00
	 */
	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		contractManager = getBean("contractManager", ContractManager.class);
		messageManager = getBean("messageManager", MessageManager.class);
		List<Contract> contracts = contractManager
				.searchAllWaitEffectContractByTime(new Date());
		if (null != contracts) {
			for (int i = 0; i < contracts.size(); i++) {
				//判断如果没有原合同序号就不是改签合同，是新增的待生效合同，故不用提醒
				if (StringUtils.isEmpty(contracts.get(i).getBeforeContractNum())) {
					continue;
				}
				//消息提醒到部门
				if (null != contracts.get(i).getContractDepartList()
						&& 0 < contracts.get(i).getContractDepartList().size()) {
					for (ContractDept dept : contracts.get(i)
							.getContractDepartList()) {
						if (dept.isDept()) {
							continue;
						}
						Message todoMessage = new Message();
						todoMessage
								.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
						todoMessage.setTaskcount(1);
						todoMessage.setDeptId(Integer.parseInt(dept
								.getContractDept().getId()));
						todoMessage.setIshaveinfo("您部门所绑定的序号为"
								+ contracts.get(i).getBeforeContractNum()
								+ "的合同， 已由合同归属部门新签为"
								+ contracts.get(i).getContractNum()
								+ "，现已生效可继续使用，请在系统查看！");
						messageManager.addMessage(todoMessage);
					}
				}
			}
		}
	
	}
	
}
