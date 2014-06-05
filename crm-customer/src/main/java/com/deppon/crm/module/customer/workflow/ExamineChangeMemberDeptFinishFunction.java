package com.deppon.crm.module.customer.workflow;

import java.util.Map;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.spi.WorkflowEntry;
/**
 * 
 * <p>
 * Description:审批变更归属部门工作流<br />
 * </p>
 * @title ExamineChangeMemberDeptFinishFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ExamineChangeMemberDeptFinishFunction implements FunctionProvider{
	/**
	 * 会员manager
	 */
	private IMemberManager memberManager ;
	/**
	 * 
	 * <p>
	 * Description:设置memberManager<br />
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
	 * Description:审批变更归属部门工作流<br />
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
		//得到审批结果
		String examineResult = (String) args.get("examineResult");
		//获得id
		String appId = ps.getString("appId");
		//如果审批结果为空 不做任何操作
		if(examineResult == null){
		}
		//获得osworkflow工作流实体
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
		//当前工作流ID
	    long wkid = entry.getId();
	    //审批结果转换
		boolean result = Boolean.valueOf(examineResult);
		//调用会员manager进行相应操作
		memberManager.disposeChangeMemberDeptByExamineResult(appId, String.valueOf(wkid), result);
	}

}
