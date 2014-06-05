package com.deppon.crm.module.common.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.frameworkimpl.server.cache.MessageCacheC;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

public class MessageAction extends AbstractAction {

	// -----------------只需要get的数据（JSON
	// 通过get方法将ACTION中的数据转换为JSON传给前台）---------------------------------------------
	// 代办事宜LIST
	private List<Message> exceptOrderList;
	private List<Message> orderList;
	private List<Message> msgInfoList;
	private Long orderCount;
	private Long exceptOrderCount;
	// 总页数
	private Long msgInfoCount;

	public List<Message> getExceptOrderList() {
		return exceptOrderList;
	}

	public void setExceptOrderList(List<Message> exceptOrderList) {
		this.exceptOrderList = exceptOrderList;
	}

	public Long getMsgInfoCount() {
		return msgInfoCount;
	}

	public void setMsgInfoCount(Long msgInfoCount) {
		this.msgInfoCount = msgInfoCount;
	}

	public List<Message> getMsgInfoList() {
		return msgInfoList;
	}

	public void setMsgInfoList(List<Message> msgInfoList) {
		this.msgInfoList = msgInfoList;
	}

	public Long getExceptOrderCount() {
		return exceptOrderCount;
	}

	public void setExceptOrderCount(Long exceptOrderCount) {
		this.exceptOrderCount = exceptOrderCount;
	}

	public Long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}

	public List<Message> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Message> orderList) {
		this.orderList = orderList;
	}

	// -----------------只需要set的数据(JSON
	// 通过set方法将前台传来的值设置到ACTION中)---------------------------------------------
	// 代办事宜MANAGER层
	private IMessageManager iMessageManager;

	public void setiMessageManager(IMessageManager iMessageManager) {
		this.iMessageManager = iMessageManager;
	}

	// 代办事宜的ID
	private String messageId;

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	// 登录用户ID
	private String userID;

	public void setUserID(String userID) {
		this.userID = userID;
	}

	// 登录用户所在部门ID
	private String deptID;

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	private int limit;
	private int start;

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	private List<String> ids;

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	// 发送超级信息的html字符串变量
	private String superMsg;

	// -----------------需要get和set的数据---------------------------------------------
	public void setSuperMsg(String superMsg) {
		this.superMsg = superMsg;
	}

	/**
	 * .
	 * <p>
	 * 获取代办事宜信息<br/>
	 * 方法名：searchAgencyMatters
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-3
	 * @since JDK1.6
	 */
	public String searchAgencyMatters() {
		User user = (User) UserContext.getCurrentUser();
		String userID = user.getId();
		String deptID = user.getEmpCode().getDeptId().getId();
		// 总个数
		Integer totalCountMessage = 0;
		Message message = new Message();
		exceptOrderList = new ArrayList<Message>();
		exceptOrderCount = (long) 0;
		if (user != null) {
			exceptOrderList = iMessageManager
					.getMeaasgeByUserAndDeptExceptMessage(userID, deptID);
			if (exceptOrderList == null) {
				exceptOrderList = new ArrayList<Message>();
			}
			totalCountMessage = iMessageManager.getCountMessage(userID, deptID);
			if (totalCountMessage != 0) {
				message.setTaskcount(totalCountMessage);
				message.setTasktype("MESSAGE");
				message.setUserid(Integer.parseInt(userID));
				exceptOrderList.add(message);
			}
			// exceptOrderCount = (long) totalCountMessage;
			for (Message msg : exceptOrderList) {
				exceptOrderCount = exceptOrderCount + msg.getTaskcount();
			}
		}
		return SUCCESS;
	}

	public String searchExceptOrderMessage() {
		User user = (User) UserContext.getCurrentUser();
		String userID = user.getId();
		String deptID = user.getEmpCode().getDeptId().getId();
		// 其它待办
		Integer totalCountMessage = 0;
		Message message = new Message();
		exceptOrderList = new ArrayList<Message>();
//		exceptOrderCount = (long) 0;
		if (user != null) {
			exceptOrderList = iMessageManager
					.getMeaasgeByUserAndDeptExceptMessage(userID, deptID);
			if (exceptOrderList == null) {
				exceptOrderList = new ArrayList<Message>();
			}
			totalCountMessage = iMessageManager.getCountMessage(userID, deptID);
			if (totalCountMessage != 0) {
				message.setTaskcount(totalCountMessage);
				message.setTasktype("MESSAGE");
				message.setUserid(Integer.parseInt(userID));
				exceptOrderList.add(message);
			}
//			for (Message msg : exceptOrderList) {
//				exceptOrderCount = exceptOrderCount + msg.getTaskcount();
//			}
		}
		return SUCCESS;
	}

	/**
	 * 查询首页待办订单和其它待办
	 * 
	 * @return
	 */
	public String searchOrderMessage() {
		User user = (User) UserContext.getCurrentUser();
		String userID = user.getId();
		String deptID = user.getEmpCode().getDeptId().getId();
		// 待办订单
		orderList = new ArrayList<Message>();
//		orderCount = (long) 0;
		if (user != null) {
			orderList = iMessageManager.getMeaasgeByUserAndDeptOnlyOrder(
					userID, deptID);
			if (orderList == null) {
				orderList = new ArrayList<Message>();
			}
//			for (Message msg : orderList) {
//				orderCount = orderCount + msg.getTaskcount();
//			}
		}
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 获取代办事宜信息(仅仅只有消息提醒)<br/>
	 * 方法名：searchOnlyMessage
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public String searchOnlyMessage() {
		User user = (User) UserContext.getCurrentUser();
		String userID = user.getId();
		String deptID = user.getEmpCode().getDeptId().getId();
		msgInfoList = new ArrayList<Message>();
		msgInfoCount = (long) 0;
		if (user != null) {
			msgInfoList = iMessageManager.getMeaasgeByUserAndDeptOnlyMessage(
					userID, deptID, start, limit);
			if (msgInfoList == null) {
				msgInfoList = new ArrayList<Message>();
			}
			msgInfoCount = (long) iMessageManager.getCountMessage(userID,
					deptID);
		}
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 查看代办之后删除该条代办<br/>
	 * 方法名：searchDeleteMessage
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-4
	 * @since JDK1.6
	 */
	public String deleteMessage() {
		iMessageManager.deleteMessage(messageId);
		return SUCCESS;
	}

	/**
	 * .
	 * <p>
	 * 查看代办之后删除该条代办(批量)<br/>
	 * 方法名：deleteMessages
	 * </p>
	 * 
	 * @author 张斌
	 * @时间 2012-5-4
	 * @since JDK1.6
	 */
	public String deleteMessages() {
		iMessageManager.deleteMessages(ids);
		return SUCCESS;
	}		   
	/**			
	    * 修改人：张斌
		*修改时间：2013-11-12 16:30
		*原有内容：无（新增）
		*修改原因：刷新缓存
	 */ 
	public String refresh(){
		User user = (User) UserContext.getCurrentUser();
		String userID = user.getId();
		String deptId = user.getEmpCode().getDeptId().getId();
		MessageCacheC mc=(MessageCacheC) CacheManager.getInstance().getCache(MessageCacheC.UUID);
		mc.refresh(userID, "U_");
		mc.refresh(deptId, "D_");
		return SUCCESS;
	}
	/**
	 * .
	 * <p>
	 * 发送超级信息给后端<br/>
	 * 方法名：addSuperMessage
	 * </p>
	 * 
	 * @author Rock
	 * @时间 2012-9-17
	 */
	public String addSuperMessage() {
		iMessageManager.addSuperMessage(superMsg);
		return SUCCESS;
	}
	public String seacherMessageCount(){
		User user = (User) UserContext.getCurrentUser();
		String userid = user.getId();
		String deptId = user.getEmpCode().getDeptId().getId();
		orderCount=iMessageManager.seacherOrderCount(userid, deptId);
		exceptOrderCount=iMessageManager.seacherExceptOrderCount(userid, deptId);
		msgInfoCount=iMessageManager.seacherMsgInfoCount(userid, deptId);
		return SUCCESS;
	}

}
