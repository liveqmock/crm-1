package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IMessageSendDao;
import com.deppon.crm.module.marketing.server.service.IMessageSendService;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
/**   
 * <p>
 * Description:营销短信发送的SERVICE<br />
 * </p>
 * @title IMessageSendService.java
 * @package com.deppon.crm.module.marketing.server.service 
 * @author ZhouYuan
 * @version 0.1 2013-09-27
 */
public class MessageSendService implements IMessageSendService{
	IMessageSendDao messageSendDao;
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
		return messageSendDao.saveMessageSendTask(mst);
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
		return messageSendDao.updateMessageSendTask(mst);
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
	public MessageSendTask searchMessageSendTaskForSend() {
		return messageSendDao.searchMessageSendTaskForSend();
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
		return messageSendDao.saveMessageSendDetail(msp);
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
	@Override
	public List<MessageSendTask> searchMessageSendTaskAll(int start, int limit) {		
		return messageSendDao.searchMessageSendTaskAll(start, limit);
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
	public List<MessageSendPhone> searchMessageSendDetailByTaskId(String id,
			int start, int limit) {
		return messageSendDao.searchMessageSendDetailByTaskId(id, start, limit);
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
		 return messageSendDao.countMessageSendTaskAll();
	 }
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
	 @Override
	 public int countMessageSendDetailByTaskId(String id){
		 return messageSendDao.countMessageSendDetailByTaskId(id);
	 }
	
	public void setMessageSendDao(IMessageSendDao messageSendDao) {
		this.messageSendDao = messageSendDao;
	}
	public IMessageSendDao getMessageSendDao() {
		return messageSendDao;
	}

}
