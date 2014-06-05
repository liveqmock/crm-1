package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.spi.WorkflowEntry;
/**
 * 
 * <p>
 * Description:审批客户修改工作流<br />
 * </p>
 * @title ExamineFinishFuntion.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ExamineFinishFuntion implements FunctionProvider{
	
	private IAlterMemberManager alterMemberManager;
	
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	
	/**
	 * 
	 * <p>
	 * Description:审批客户修改工作流<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//审批结果
		String examineResult = (String) args.get("examineResult");
		if(examineResult == null){
		}
		//工作流实体
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
	    long wkid = entry.getId();//当前工作流ID
		boolean result = Boolean.valueOf(examineResult);
		//调用业务操作
		alterMemberManager.disposeWorkflow(String.valueOf(wkid), result);
	}

}
