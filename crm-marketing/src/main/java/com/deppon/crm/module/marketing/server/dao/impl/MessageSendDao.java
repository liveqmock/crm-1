package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IMessageSendDao;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**   
 * <p>
 * Description:营销短信发送的Dao<br />
 * </p>
 * @title IMessageSendDao.java
 * @package com.deppon.crm.module.marketing.server.dao
 * @author ZhouYuan
 * @version 0.1 2013-09-27
 */
public class MessageSendDao extends iBatis3DaoImpl implements IMessageSendDao{
	private static final String NAMESPACE_MESSAGESEND="com.deppon.crm.module.marketing.shared.domain.MessageSend.";
	//保存营销短信发送任务
	private static final String SAVEMESSAGESENDTASK = "saveMessageSendTask";
	//更新营销短信发送任务
	private static final String UPDATEMESSAGESENDTASK = "updateMessageSendTask";
	//查询一条未完成的短信发送计划
	private static final String SEARCHMESSAGESENDTASKFORSEND = "searchMessageSendTaskForSend";
	//保存营销短信详情
	private static final String SAVEMESSAGESENDDETAIL = "saveMessageSendDetail";
	//查询全部的短信任务
	private static final String SEARCHMESSAGESENDTASKALL="searchMessageSendTaskAll";
	//查询全部营销短信任务数
	private static final String COUNTMESSAGESENDTASKALL="countMessageSendTaskAll";
	//根据任务ID查询任务详情
	private static final String SEARCHMESSAGESENDDETAILBYTASKID="searchMessageSendDetailByTaskId";
	//根据任务ID查询任务详情数
	private static final String COUNTMESSAGESENDDETAILBYTASKID="countMessageSendDetailByTaskId";
	/**
	 * 
	 * <p>
	 * 保存营销短信发送任务<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param MessageSendTask mst
	 * @return int
	 */
	@Override
	public int saveMessageSendTask(MessageSendTask mst) {
		return getSqlSession().insert(NAMESPACE_MESSAGESEND+SAVEMESSAGESENDTASK,mst);
	}
	/**
	 * 
	 * <p>
	 * 更新短信发送任务<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @param MessageSendTask mst
	 * @return int
	 */
	@Override
	public int updateMessageSendTask(MessageSendTask mst) {
		return getSqlSession().update(NAMESPACE_MESSAGESEND+UPDATEMESSAGESENDTASK,mst);
	}
	/**
	 * 
	 * <p>
	 * 查询一条短信发送任务<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return MessageSendTask mst
	 */
	@Override
	public MessageSendTask searchMessageSendTaskForSend(){
		return (MessageSendTask)getSqlSession().selectOne(NAMESPACE_MESSAGESEND+SEARCHMESSAGESENDTASKFORSEND);
	}
    /**
	 * 
	 * <p>
	 * 保存短信详情<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return MessageSendPhone msp
	 */
	@Override
	public int saveMessageSendDetail(MessageSendPhone msp) {
		return getSqlSession().insert(NAMESPACE_MESSAGESEND+SAVEMESSAGESENDDETAIL,msp);
	}
	 /**
	 * 
	 * <p>
	 * 查询全部短信发送任务<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageSendTask> searchMessageSendTaskAll(int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		return (List<MessageSendTask>)getSqlSession().selectList(NAMESPACE_MESSAGESEND+SEARCHMESSAGESENDTASKALL,null,rb);
	}
	 /**
	 * 
	 * <p>
	 * 查询全部短信发送任务数<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	 @Override
	 public int countMessageSendTaskAll(){
		 return (Integer)getSqlSession().selectOne(NAMESPACE_MESSAGESEND+COUNTMESSAGESENDTASKALL);
	 }
	/**
	 * 
	 * <p>
	 * 根据任务ID查询任务详情<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageSendPhone> searchMessageSendDetailByTaskId(String id,
			int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		return (List<MessageSendPhone>)getSqlSession().selectList(NAMESPACE_MESSAGESEND+SEARCHMESSAGESENDDETAILBYTASKID, id, rb);
	}
	/**
	 * 
	 * <p>
	 * 根据任务ID查询任务详情<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	@Override
	public int countMessageSendDetailByTaskId(String id) {
		return (Integer)getSqlSession().selectOne(NAMESPACE_MESSAGESEND+COUNTMESSAGESENDDETAILBYTASKID, id);
	}

}
