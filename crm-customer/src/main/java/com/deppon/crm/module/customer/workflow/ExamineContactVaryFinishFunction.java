package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IIntegralManager;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:审批客户联系人挂靠关系变更工作流<br />
 * </p>
 * @title ExamineContactVaryFinishFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ExamineContactVaryFinishFunction implements FunctionProvider{
	/**
	 * 联系人manager
	 */
	private IIntegralManager integralManager;
	
	public void setIntegralManager(IIntegralManager integralManager) {
		this.integralManager = integralManager;
	}

	/**
	 * 
	 * <p>
	 * Description:审批客户联系人挂靠关系变更工作流<br />
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
		String examineResult = (String) args.get("examineResult");
		String appId = ps.getString("appId");
		if(examineResult == null){
			//TODO 边界值
		}
		//转换boolean
		boolean result = Boolean.valueOf(examineResult);
		//调用进行业务操作
		integralManager.disposeContactVaryByExamineResult(appId, result);
	}

}
