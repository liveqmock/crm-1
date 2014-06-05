package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:特殊固定客户审批工作流<br />
 * </p>
 * @title ExamineCreateSMFinishFuntion.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ExamineCreateSMFinishFuntion implements FunctionProvider{
	/**
	 * 客户manager
	 */
	private IMemberManager memberManager;
	/**
	 * 
	 * <p>
	 * Description:memberManager<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberManager
	 * void
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * 
	 * <p>
	 * Description:特殊固定客户审批工作流<br />
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
		String memberId = ps.getString("appId");
		if(examineResult == null){
			//TODO 边界值
		}
		//转为booleanboolean
		boolean result = Boolean.valueOf(examineResult);
		//TODO 调用接口
		memberManager.disposeSpecialMemberByExamineResult(memberId, result);
	}

}
