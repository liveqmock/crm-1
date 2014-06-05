package com.deppon.crm.module.customer.workflow;

import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.customer.shared.domain.Constant;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;

public class MemberModifyToDoWorkOwnerFunction implements FunctionProvider{
	
	/**
	 * 
	 * <p>
	 * Description:客户修改工作流审批代办生成<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		Set dateTypes = (Set) ps.getObject("dataTypes");
		String dateTypeStr = (String) args.get("dataType");
		String[] types =dateTypeStr.split(",");
		
		String owner = null;
		
		boolean hasNextStep = false;
		
		//审批节点类型 与 审批节点名称 有一个 命名约束 映射
		for (String type : types) {
			if(dateTypes.contains(type)){
				owner = ps.getString(type.replaceAll("Type", "Owner"));
				hasNextStep = true;
				break;
			}
		}
		
		String status ;
		//添加工作流状态
		if(hasNextStep){
			status = Constant.APPROVING;
		}else{
			status = Constant.AGREED;
		}
		
		transientVars.put("owner",owner);
		transientVars.put("hasNextStep",hasNextStep);
		transientVars.put("workFlowStatus",status);

		
		
	}

}
