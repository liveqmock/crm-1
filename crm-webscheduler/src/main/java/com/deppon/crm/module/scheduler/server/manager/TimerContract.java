package com.deppon.crm.module.scheduler.server.manager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.shared.domain.Contract;

public class TimerContract {
	private IContractManager contractManager;
	private MessageManager messageManager;

	public IContractManager getContractManager() {
		return contractManager;
	}


	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}


	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}

	/**
	 * 
	 * @description 失效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return void
	 * @update 2012-9-19 下午1:42:58
	 */
	public void cancelTimeOutContract() {
		contractManager.cancelTimeOutContract();
	}

	/**
	 * 
	 * @description 生效待生效合同.  
	 * @author 潘光均
	 * @version 0.1 2012-9-19
	 * @param 
	 *@date 2012-9-19
	 * @return void
	 * @update 2012-9-19 下午1:42:30
	 */
	public void effectContract(){
		System.out.println("=============effectContract=============");
		contractManager.effectWaitEffectContract();
	}
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
	public void remainUneffectContract() {
		System.out.println("=============remainUneffectContract++++++++++++++++++++");
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
	public void remanidEffectContract() {
		System.out.println("=============remanidEffectContract++++++++++++++++++++");
		List<Contract> contracts = contractManager
				.searchAllWaitEffectContractByTime(new Date());
		if (null != contracts) {
			for (int i = 0; i < contracts.size(); i++) {
				//判断如果没有原合同序号就不是改签合同，是新增的待生效合同，故不用提醒
				if (StringUtils.isEmpty(contracts.get(i).getBeforeContractNum())) {
					continue;
				}
				//消息提醒到部门
				if (null != contracts.get(i).getContractDepartList()) {
					Message todoMessage = new Message();
					todoMessage
							.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
					todoMessage.setTaskcount(1);
					todoMessage.setDeptId(Integer.parseInt(contracts.get(i)
							.getDept().getId()));
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
