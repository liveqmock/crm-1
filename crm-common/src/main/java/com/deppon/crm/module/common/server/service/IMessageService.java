package com.deppon.crm.module.common.server.service;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.foss.framework.service.IService;

public interface IMessageService extends IService {

	public List<Message> getMeaasge(String userid);

	public void addMessage(Message message);

	public void addSuperMessage(String superMsg, String msgType);

	public void addMessageList(List<Message> messageList);

	public List<Message> getMeaasgeByUserAndDept(String userid, String deptId);

	public void deleteMessage(String id);

	public void deleteMessageByType(String type);

	public void deleteMessageByInvalid(String taskType);
	
	public Long seacherExceptOrderCount(String userid, String deptId);

	public Long seacherMsgInfoCount(String userid, String deptId);

	public Long seacherOrderCount(String userid, String deptId);
	// -------------------新---------------------------
	/**
	 * .
	 * <p>
	 * 获取当前用户和部门的待办事宜（除了提醒消息）<br/>
	 * 方法名：getMeaasgeByUserAndDeptExceptMessage
	 * </p>
	 * 
	 * @param userid
	 *            当前登录用户的userid
	 * @param deptId
	 *            当前登录用户所在部门id
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public List<Message> getMeaasgeByUserAndDeptExceptMessage(String userid,
			String deptId);

	public List<Message> getMeaasgeByUserAndDeptOnlyOrder(String userid,
			String deptId);
	
	/**
	 * .
	 * <p>
	 * 获取当前用户和部门的待办事宜（只有提醒消息）<br/>
	 * 方法名：getMeaasgeByUserAndDeptOnlyMessage
	 * </p>
	 * 
	 * @param userid
	 *            当前登录用户的userid
	 * @param deptId
	 *            当前登录用户所在部门id
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public List<Message> getMeaasgeByUserAndDeptOnlyMessage(String userid,
			String deptId, int start, int limit);

	/**
	 * .
	 * <p>
	 * 获取消息提醒待办事宜的个数<br/>
	 * 方法名：getCountMessage
	 * </p>
	 * 
	 * @param userid
	 *            当前登录用户的userid
	 * @param deptId
	 *            当前登录用户所在部门id
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public Integer getCountMessage(String userid, String deptId);
	/**
	 * .
	 * <p>
	 * 根据代办事宜ID的LIST批量删除待办<br/>
	 * 方法名：deleteMessages
	 * </p>
	 * 
	 * @param messageIds
	 *            代办事宜的ID
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public void deleteMessages(List<String> messageIds);

	public List<Message> getMessageForErp();

}
