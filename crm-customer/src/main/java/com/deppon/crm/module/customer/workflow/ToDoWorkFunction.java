package com.deppon.crm.module.customer.workflow;

import java.util.List;

import java.util.Map;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.manager.ITodoWorkflowManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.TodoWorkflow;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.spi.WorkflowEntry;
/**
 * 
 * <p>
 * Description:代办function<br />
 * </p>
 * @title ToDoWorkFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class ToDoWorkFunction implements FunctionProvider{
	//待办工作流manager
	private	ITodoWorkflowManager todoWorkflowManager;
	//消息manager
	private IMessageManager messageManager;
	//用户service
	private IUserService userService;
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param userService
	 * void
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param messageManager
	 * void
	 */
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param todoWorkflowManager
	 * void
	 */
	public void setTodoWorkflowManager(ITodoWorkflowManager todoWorkflowManager) {
		this.todoWorkflowManager = todoWorkflowManager;
	}

	/**
	 * 
	 * <p>
	 * Description:代办生成<br />
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
		String isCleanToDoWorkStr = (String) args.get("isCleanToDoWork");
		String isCreateToDoWorkStr = (String) args.get("isCreateToDoWork");
		
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
	    long wkid = entry.getId();//当前工作流ID
		String wkFlowName = entry.getWorkflowName();
		
		boolean isCleanToDoWork =  Boolean.valueOf(isCleanToDoWorkStr);
		boolean isCreateToDoWork = Boolean.valueOf(isCreateToDoWorkStr);
		
		if(isCleanToDoWork){
			todoWorkflowManager.updateTodoflagByWorkflowId(wkid,"DOWN");
		}
		
		
		if(isCreateToDoWork){
			//保存一条toDo
			TodoWorkflow todo = new TodoWorkflow();
			
			//设置业务id
			todo.setApplicationId(ps.getString("appId"));
			
			//设置业务状态
			String owner = 	(String) transientVars.get("owner");
			boolean hasNextStep = (Boolean) transientVars.get("hasNextStep");
			
			if(!hasNextStep){
				return;
			}
			
			String[] os = owner.split("_");
			if (os != null && os.length == 2){
				String qulifiedDept = os[0];
				String qulifiedRole = os[1];
				//设置审批人角色
				todo.setDeptId(qulifiedDept);
				//设置审批人部门
				todo.setRoleId(qulifiedRole);
				
				todo.setTodoFlag("TODO");
				//设置workflow信息
				todo.setWorkflowId(wkid);
				todo.setWorkflowName(wkFlowName);
				//设置工作流状态
				todoWorkflowManager.addTodoWorkflow(todo);
				
				List<User> users = userService.queryUsersByDeptAndRole(qulifiedDept, qulifiedRole);
				
				for (User user : users) {
					//插入代办
					Message todoMessage = new Message();
					todoMessage.setTasktype(com.deppon.crm.module.common.server.util.Constant.TASK_TYPE_CUSTMER_MESSAGE);
					todoMessage.setTaskcount(1);
					todoMessage.setUserid(Integer.parseInt(user.getId()));
					todoMessage.setDeptId(Integer.parseInt(qulifiedDept));
					todoMessage.setIshaveinfo("你有一个待审批的工作流");
					messageManager.addMessage(todoMessage);
				}
				
			}else{
				throw new RuntimeException(owner+"异常代码");
			}
			
		}
		
		
		
	}
	
}
