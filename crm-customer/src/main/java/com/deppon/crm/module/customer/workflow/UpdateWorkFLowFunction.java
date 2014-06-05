package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IWorkFLowManager;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.spi.WorkflowEntry;
/**
 * 
 * <p>
 * Description:更新工作流<br />
 * </p>
 * @title UpdateWorkFLowFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class UpdateWorkFLowFunction implements FunctionProvider{
	//工作流manager
	private IWorkFLowManager workFLowManager;
	
	/**
	 * 
	 * <p>
	 * Description:setWorkFLowManager<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param workFLowManager
	 * void
	 */
	public void setWorkFLowManager(IWorkFLowManager workFLowManager) {
		this.workFLowManager = workFLowManager;
	}
	
	/**
	 * 
	 * <p>
	 * Description:更新工作流状态<br />
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
		
		String status = (String) args.get("workFlowStatus");
		if(ValidateUtil.objectIsEmpty(status)){
			status = (String) transientVars.get("workFlowStatus");
		}
		if(ValidateUtil.objectIsEmpty(status)){
			throw new RuntimeException("没有找到维护的工作流状态");
		}
		
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
	    long wkid = entry.getId();//当前工作流ID
		workFLowManager.updateWorkFlowStatusByWorkFLowId(status, wkid);
		
	}

}
