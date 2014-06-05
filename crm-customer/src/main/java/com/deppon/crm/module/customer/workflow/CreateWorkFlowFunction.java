package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IWorkFLowManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.WorkFlow;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.spi.WorkflowEntry;
/**
 * 
 * <p>
 * Description:function<br />
 * </p>
 * @title CreateWorkFlowFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class CreateWorkFlowFunction implements FunctionProvider{
	/**
	 * 工作流manager
	 */
	private IWorkFLowManager workFLowManager;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param workFLowManager
	 * void
	 */
	public void setWorkFLowManager(IWorkFLowManager workFLowManager) {
		this.workFLowManager = workFLowManager;
	}
	/**
	 * 
	 * <p>
	 * Description:保存工作流<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @throws WorkflowException
	 *
	 */
	/**
	 * 
	 * <p>
	 * Description:保存审批工作流<br />
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
		//新建对象
		WorkFlow workFLow = new WorkFlow();
		//获得WorkflowEntry
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
		//得到工作流id
	    long wkid = entry.getId();//当前工作流ID
	    //获得工作流名字
		String wkFlowName = entry.getWorkflowName();
		//获得appid
		String appId = ps.getString("appId");
		//设置工作流id
		workFLow.setWorkFlowId(wkid);
		//工作流名
		workFLow.setWorkFLowName(wkFlowName);
		//状态
		workFLow.setStatus(Constant.UNAPPROVED);
		//当前登录用户
		workFLow.setUserId(ContextUtil.getCreateOrModifyUserId());
		//用户名
		workFLow.setUserName(ContextUtil.getCreateOrModifyUserName());
		//部门
		Department departMent = ContextUtil.getCurrentUserDept();
		workFLow.setDeptId(departMent.getId());
		//部门名字
		workFLow.setDeptName(departMent.getDeptName());
		//appid
		workFLow.setAppId(appId);
		//保存工作流
		workFLowManager.saveWorkFlow(workFLow);
	}

}
