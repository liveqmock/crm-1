package com.deppon.crm.module.common.server.manager;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.common.server.manager.impl.MessageManager;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.TaskTypeEnum;

public class MessageManagerTest extends TestCase{

//extends TestCase 
private MessageManager messageManager;
	
	private static ApplicationContext ctx = null;
	private static Message message;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Override
	protected void setUp() throws Exception {
		try{
			if(ctx == null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			messageManager = ctx.getBean(MessageManager.class);
			message = new Message();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void testgetmessage(){
		//传入用户1查询待办事宜
		String userid = "1241125";
		Assert.assertTrue((messageManager.getMeaasge(userid)).size()>0);
		for(Message m:messageManager.getMeaasge(userid)){
			System.out.println(TaskTypeEnum.getEnum(m.getTasktype()));
			System.out.println(m.getTaskcount());
		}
		messageManager.getMessageService();
		messageManager.addMessage(message);
		messageManager.addSuperMessage("message");
		messageManager.getMeaasgeByUserAndDept("106139","0050720");
		messageManager.getMeaasgeByUserAndDeptExceptMessage("106139","0050720");
		messageManager.getMeaasgeByUserAndDeptOnlyOrder("106139","0050720");
		messageManager.getMeaasgeByUserAndDeptOnlyMessage("106139","0050720",0,1);
		messageManager.getCountMessage("106139","0050720");
		messageManager.deleteMessage("106139");
		String type = "JONE";
		messageManager.deleteMessageByType(type);
		messageManager.deleteMessageByInvalid(type);
		List<Message> messageList = new ArrayList<Message>();
		messageList.add(message);
		messageManager.addMessageList(messageList);
		List<String> messageIds = new ArrayList<String>();
		messageIds.add("106139");
		messageManager.deleteMessages(messageIds);
		messageManager.getMessageForErp();
	}
	
	public void testaddmessage()
	{
		 Message m = new Message();
		   m.setCreateUser("1213124");
		   m.setModifyUser("4165464");
		   m.setUserid(1241125);
		   m.setTasktype(TaskTypeEnum.NOACCEPT_ORDER.value);
		   m.setTaskcount(34124);
		   m.setIshaveinfo("无");
		   m.setUrl("www.baidu.com");
		   m.setExpe("dasf");
		   
		   messageManager.addMessage(m);
	}
	
}
