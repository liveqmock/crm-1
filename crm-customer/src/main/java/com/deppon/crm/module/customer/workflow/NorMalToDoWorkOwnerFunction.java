package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.utils.WorkFlowUtil;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

public class NorMalToDoWorkOwnerFunction implements FunctionProvider{

	/**
	 * @author 078823
	 * @date 2014-04-11
	 * @description:生成代办事项</br>
	 * 主要用于action请求日志
	 * *(non-Javadoc)
	 * @see com.opensymphony.workflow.FunctionProvider.execute(Map transientVars, Map args, PropertySet ps) 
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		String owner = WorkFlowUtil.getOwner(transientVars, args);
		boolean hasNextStep = true;
		
		transientVars.put("owner",owner);
		transientVars.put("hasNextStep",hasNextStep);
	}

}
