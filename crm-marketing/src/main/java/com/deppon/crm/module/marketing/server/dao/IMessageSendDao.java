package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;

/**   
 * <p>
 * Description:营销短信发送的Dao<br />
 * </p>
 * @title IMessageSendDao.java
 * @package com.deppon.crm.module.marketing.server.dao
 * @author ZhouYuan
 * @version 0.1 2013-09-27
 */
public interface IMessageSendDao {
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
	int saveMessageSendTask(MessageSendTask mst);
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
	 int updateMessageSendTask(MessageSendTask mst);
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
	 MessageSendTask searchMessageSendTaskForSend();
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
	 int saveMessageSendDetail(MessageSendPhone msp);
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
	 List<MessageSendTask> searchMessageSendTaskAll(int start,int limit);
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
	 int countMessageSendTaskAll();
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
	 List<MessageSendPhone> searchMessageSendDetailByTaskId(String id,int start,int limit);
	 /**
	 * 
	 * <p>
	 * 根据任务ID查询任务详情数<br/>
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-09-27
	 * @return List<MessageSendTask>
	 */
	 int countMessageSendDetailByTaskId(String id);
}
