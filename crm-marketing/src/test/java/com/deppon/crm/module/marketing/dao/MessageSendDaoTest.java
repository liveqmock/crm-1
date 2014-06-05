package com.deppon.crm.module.marketing.dao;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.deppon.crm.module.marketing.server.dao.IMessageSendDao;
import com.deppon.crm.module.marketing.server.dao.impl.MessageSendDao;
import com.deppon.crm.module.marketing.shared.domain.MessageSendPhone;
import com.deppon.crm.module.marketing.shared.domain.MessageSendTask;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

public class MessageSendDaoTest {
	IMessageSendDao messageDao;
	public static ApplicationContext factory;
//	static String[] resource = new String[] { "com/deppon/crm/module/marketing/server/META-INF/CommonTest.xml" };
	@Before
	public void setUp(){	
//		factory = new ClassPathXmlApplicationContext(resource);
//		messageDao = (IMessageSendDao) factory.getBean("messageSendDao");
		messageDao = (IMessageSendDao) SpringTestHelper.get().getBean(MessageSendDao.class);
	}
	@Test
	public void testSaveMessageSendTask(){
		MessageSendTask mst = new MessageSendTask();
		mst.setFileName("aa.xlsx");
		mst.setFilePath("E:"+File.pathSeparator+"aa.xlsx");
		mst.setStatus("0");
		mst.setStartRow(1);
		mst.setMsgContent("123213123123");
		mst.setBeginDate(new Date());
		mst.setEndDate(new Date());
		mst.setSendDept("DP10000");
		mst.setSendUser("111111");
		mst.setCreateDate(new Date());
		mst.setCreateUser("11111");
		mst.setModifyDate(new Date());
		mst.setModifyUser("11111");
		try{
			messageDao.saveMessageSendTask(mst);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateMessageSendTask(){
		MessageSendTask mst = new MessageSendTask();
		mst.setId("21");
		mst.setStartRow(2);
		mst.setBeginDate(new Date());
		messageDao.updateMessageSendTask(mst);
	}
	@Test
	public void testSearchMessageSendTaskForSend(){
		MessageSendTask mst = messageDao.searchMessageSendTaskForSend();
	}
	@Test
	public void testSaveMessageSendDetail(){
		MessageSendPhone msp = new MessageSendPhone();
		msp.setPhone("12322222");
		msp.setTaskId("1");
		msp.setStatus("1");
		msp.setSendDate(new Date());
		messageDao.saveMessageSendDetail(msp);
	}
	@Test
	public void testSearchMessageSendTaskAll(){
		messageDao.searchMessageSendTaskAll(0, 10);
	}
	@Test
	public void testSearchMessageSendDetailByTaskId(){
		List<MessageSendPhone> p = messageDao.searchMessageSendDetailByTaskId("1",0, 10);
		System.out.println(p);
	}
	@Test
	public void testCountMessageSendTaskAll(){
		try{
			int a = messageDao.countMessageSendTaskAll();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testCountMessageSendDetailByTaskId(){
		messageDao.countMessageSendDetailByTaskId("41");
	}
}
