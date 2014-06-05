package com.deppon.crm.module.frameworkimpl.server.cache;

import java.util.Date;
import java.util.Map;
import com.deppon.crm.module.common.server.dao.IMessageDao;
import com.deppon.foss.framework.cache.provider.IBatchCacheProvider;

public class MessageCacheProviderC  implements IBatchCacheProvider<String, Map<String,Integer>>{
	private IMessageDao messageDao;
	@Override
	public Date getLastModifyTime() {
		return messageDao.getLastModifyTime();
	}
	@Override
	public Map<String, Map<String, Integer>> get() {
		Map<String, Map<String, Integer>> messageMap=messageDao.getMessageOfUserAndDpet();
		return messageMap;
	}
	public  Map<String, Integer> getByUserOrDept(String key,String prefix) {
		 Map<String, Integer> messageMap=messageDao.getMessageByUserOrDept(key,prefix);
		return messageMap;
	}
	public IMessageDao getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
}
