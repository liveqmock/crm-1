package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Message;

/**
 * 
 * 作 者：赵斌 修改时间：2012年3月15日 描 述： 待办事宜
 */
public interface IMessageDao {
	/**
	 * 查询当前用户待办事宜
	 * 
	 * @return
	 */
	public List<Message> getMeaasge(String userid);

	public List<Message> getMeaasgeByUserAndDept(String userid, String deptId);

	public void addMessage(Message message);

	public void addSuperMessage(String superMsg, String msgType);
	
	public void addMessageList(List<Message> messageList);

	public void deleteMessage(String id);

	public void deleteMessageByType(String type);
	
	public void deleteMessageByInvalid(String taskType);

	// -------------------------------------------------
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
	/**
	 * 
	 * 作 者：张振 
	 * 修改时间：2013-6-27 添加getLastModifyTime()方法，获取最近修改时间
	 *@return Date
	 * */
	public Date getLastModifyTime();
	/**
	 * 
	 * 作 者：张振 
	 * 修改时间：2013-6-28添加getMessageOfUserAndDpet()方法，获取用户代办
	 * 以及部门下的代办
	 *@return Map
	 *
	 * */
	public Map<String,Map<String,Integer>> getMessageOfUserAndDpet(); 
	/**
	 * 
	 * author ZhangZW
	 * UPDATE TIME：2013-11-07
	 * get message(messageType,the conut of the type) from t_crm_todo by the userid and the deptid of the user
	 * para:userid deptid
	 * 
	 *@return Map
	 *
	 * */
	public Map<String,Integer> getMessageByUserOrDept(String userid,String PERFIX);

}
