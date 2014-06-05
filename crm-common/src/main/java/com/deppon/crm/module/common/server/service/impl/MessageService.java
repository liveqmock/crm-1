package com.deppon.crm.module.common.server.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.deppon.crm.module.common.server.dao.IMessageDao;
import com.deppon.crm.module.common.server.service.IMessageService;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.frameworkimpl.server.cache.MessageCacheC;
import com.deppon.foss.framework.cache.CacheManager;

public class MessageService implements IMessageService {
	private static String[] STR13=new String[]{"RECOMPENSE_TODO_SUBMITED","COMPLAINT_RT_REPORT_RETURN","RECOMPENSE_MESSAGE","DUTY_MESSAGE","QUALITY_INSPECTOR_RETURNBACK","STATISTICIANS_RETURNBACK","CUSTMER_MESSAGE","NEW_PLAN","OVERDUE_SCHEDULE","NEW_SCHEDULE","ORDER_MESSAGE","SUPER_MESSAGE","ORDER_UNASSIGN","ORDER_UNACCEPT","ORDER_ACCEPTED","ORDER_GOBACK"};
	private static String[] STR9= new String[]{"RECOMPENSE_TODO_SUBMITED","COMPLAINT_RT_REPORT_RETURN","RECOMPENSE_MESSAGE","DUTY_MESSAGE","QUALITY_INSPECTOR_RETURNBACK","STATISTICIANS_RETURNBACK","CUSTMER_MESSAGE","NEW_PLAN","OVERDUE_SCHEDULE","NEW_SCHEDULE","ORDER_MESSAGE","SUPER_MESSAGE"};
	private static String[] STR4= new String[]{"ORDER_UNASSIGN","ORDER_UNACCEPT","ORDER_ACCEPTED","ORDER_GOBACK"};
	private static final String USERPERFIX="U_";
	private static final String DEPTPERFIX="D_";
	private static final int OBTAIL=1;
	private static final int DISLODGE=2;
	private IMessageDao messageDao;
	public IMessageDao getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public List<Message> getMeaasge(String userid) {
		return messageDao.getMeaasge(userid);
	}

	@Override
	public void addMessage(Message message) {
		messageDao.addMessage(message);
	}

	public void addSuperMessage(String superMsg, String msgType){
		messageDao.addSuperMessage(superMsg, msgType);
	}

	@Override
	public List<Message> getMeaasgeByUserAndDept(String userid, String deptId) {
		return messageDao.getMeaasgeByUserAndDept(userid, deptId);
	}

	@Override
	public void deleteMessage(String id) {
		messageDao.deleteMessage(id);
	}

	@Override
	public void deleteMessageByType(String type) {
		messageDao.deleteMessageByType(type);
	}

	@Override
	public void deleteMessageByInvalid(String type) {
		messageDao.deleteMessageByInvalid(type);
	}

	@Override
	public void addMessageList(List<Message> messageList) {
		messageDao.addMessageList(messageList);
	}

	// ------------------新-----------------------------

	@Override
	public List<Message> getMeaasgeByUserAndDeptExceptMessage(String userid,
			String deptId) {
		return messageDao.getMeaasgeByUserAndDeptExceptMessage(userid, deptId);
	}

	@Override
	public List<Message> getMeaasgeByUserAndDeptOnlyOrder(String userid,
			String deptId) {
		return messageDao.getMeaasgeByUserAndDeptOnlyOrder(userid, deptId);
	}

	@Override
	public List<Message> getMeaasgeByUserAndDeptOnlyMessage(String userid,
			String deptId, int start, int limit) {
		return messageDao.getMeaasgeByUserAndDeptOnlyMessage(userid, deptId,
				start, limit);
	}

	@Override
	public Integer getCountMessage(String userid, String deptId) {
		return messageDao.getCountMessage(userid, deptId);
	}

	@Override
	public void deleteMessages(List<String> messageIds) {
		messageDao.deleteMessages(messageIds);
	}

	@Override
	public List<Message> getMessageForErp() {
		return messageDao.getMessageForErp();
	}
	/*private Long orderCount;//ding dan  getMeaasgeByUserAndDeptOnlyOrder
	private Long exceptOrderCount;//getMeaasgeByUserAndDeptExceptMessage
	// 总页数
	private Long msgInfoCount;//getMeaasgeByUserAndDeptOnlyMessage
*/	@Override
	public Long seacherExceptOrderCount(String userid, String deptId) {
		 deptId=DEPTPERFIX+deptId;
		 userid=USERPERFIX+userid;
		 long count=to_od(deptId,STR13,DISLODGE);
		 count+=to_od(userid,STR13,DISLODGE);
		return new Long(count);
	}

	@Override
	public Long seacherMsgInfoCount(String userid, String deptId) {
		 deptId=DEPTPERFIX+deptId;
		 userid=USERPERFIX+userid;
		 long count=to_od(deptId,STR9,OBTAIL);
		 long count1=to_od(userid,STR9,OBTAIL);
		return new Long(count+count1);
	}

	@Override
	public Long seacherOrderCount(String userid, String deptId) {
		 deptId=DEPTPERFIX+deptId;
		 userid=USERPERFIX+userid;
		 long count=to_od(deptId,STR4,OBTAIL);
		 count+=to_od(userid,STR4,OBTAIL);
		return new Long(count);
	}
	//去除
		private int dislodge(Map<String,Integer> map2,String[] strs){
			int sum=0;	
			if(map2!=null){
				for(String temp:strs){	
					if(map2.containsKey(temp)){
						map2.remove(temp);
					}	
				}
				Collection<Integer> ints=map2.values();
					for(Integer test:ints){			
						sum+=test;				
				}	
			}
			return sum;	
	}
		//获取
		private int obtain(Map<String,Integer> map2,String[] strs ){
			int sum=0;	
			if(map2!=null){
				for(String temp:strs){
					if(map2.containsKey(temp)){
					int test=map2.get(temp);
						sum+=test;
					}
				}
			}
			return sum;	
		}
		private int to_od(String key,String[] strs,int MonthFlag){
			Map<String,Integer> tmp=null;
			tmp=( Map<String,Integer>) CacheManager.getInstance().getCache(MessageCacheC.UUID).get(key);
			int count=0;
			switch(MonthFlag){
				case 1:
					count=obtain(tmp,strs);
					break;
				case 2:
					count=dislodge(tmp,strs);
					break;
			}
			return count;
		}
}
