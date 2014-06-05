/**
 * <p>
 * Description:<br />
 * </p>
 * @title MessageSendAction.java
 * @package com.deppon.crm.module.marketing.server.action
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-9-28
 */
package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.IMessageSendManager;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * <p>
 * Description:营销短信action<br />
 * </p>
 * @title MessageSendAction.java
 * @package com.deppon.crm.module.marketing.server.action
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-9-28
 */
public class MessageSendAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	//营销短信manager
	private IMessageSendManager messageSendManager;
	//营销短信发送任务实体
	private MessageSendTask messageSendTask;
	//营销短信发送任务实体ID
	private String taskId;
	//营销短信任务列表
	private List<MessageSendTask> taskList;
	//发送详情手机号列表
	private List<MessageSendPhone> phoneList;
	// 起始页
	private int start;
	// 每页显示条数
	private int limit;
	//分页总条数
	private Long totalCount;

	/**
	 * <p>
	 * Description:保存上传的文件名、文件路径、短信内容<br />
	 * @author xiaohongye
	 * @version V0.1 
	 * @Date 2013-9-28
	 */
	@JSON
	public String saveMessageFile(){
		//当前用户
		User user=(User)UserContext.getCurrentUser();
		messageSendManager.saveMessageFile(messageSendTask,user);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询营销短信发送文件列表<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-9-29
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchTaskList(){
		 Map<String,Object> map = messageSendManager.searchMessageSendTaskAll(start, limit);
		 taskList = (List<MessageSendTask>) map.get("taskList");
		 totalCount = Long.valueOf(map.get("totalCount").toString());
		 return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询单个营销短信任务详情信息<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-9-29
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchPhoneList(){
		Map<String,Object> map = messageSendManager.searchMessageSendDetailByTaskId(taskId, start, limit);
		phoneList = (List<MessageSendPhone>) map.get("phoneList");
		totalCount = Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}
	
	/**
	 * @param totalCount the totalCount to get
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @param taskList the taskList to get
	 */
	public List<MessageSendTask> getTaskList() {
		return taskList;
	}

	/**
	 * @param phoneList the phoneList to get
	 */
	public List<MessageSendPhone> getPhoneList() {
		return phoneList;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @param messageSendTask the messageSendTask to get
	 */
	public MessageSendTask getMessageSendTask() {
		return messageSendTask;
	}

	/**
	 * @param messageSendTask the messageSendTask to set
	 */
	public void setMessageSendTask(MessageSendTask messageSendTask) {
		this.messageSendTask = messageSendTask;
	}

	/**
	 * @param messageSendManager the messageSendManager to set
	 */
	public void setMessageSendManager(IMessageSendManager messageSendManager) {
		this.messageSendManager = messageSendManager;
	}
}
