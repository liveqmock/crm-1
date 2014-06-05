package com.deppon.crm.module.common.server.dao;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.deppon.crm.module.common.server.dao.impl.MessageDao;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.common.shared.domain.TaskTypeEnum;

public class MessageDaoTest extends TestCase {

	private MessageDao messageDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[] { "DaoBeanTest.xml" };

	@Override
	protected void setUp() throws Exception {
		try {
			if (ctx == null) {
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			messageDao = ctx.getBean(MessageDao.class);
			//messageCache=ctx.getBean(MessageCache.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void testGetMessage() {
		System.out.println((String)null);
		// 传入用户1查询待办事宜
		String userid = "286943";
		List l=messageDao.getMeaasge(userid);
		System.out.println(l.size());
		System.out.println(((Message)l.get(0)).getUserid());
		Assert.assertTrue((messageDao.getMeaasge(userid)).size() > 0);
		for (Message m : messageDao.getMeaasge(userid)) {
			System.out.println(m.getTasktype() + m.getTaskcount());
		}
		messageDao.getMeaasge(null);
		String str = "";
		messageDao.getMeaasge(str);
	}
	/**
	 * .
	 * 
	 * <p>
	 * 测试getMeaasgeByUserAndDeptExceptMessage<br/>
	 * 方法名：testGetMeaasgeByUserAndDeptExceptMessage
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public void testGetMeaasgeByUserAndDeptExceptMessage() {
		// 传入用户1查询待办事宜
		String userid = "522071";
		String deptId = "49082";
       Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,deptId)).size() >= 0);
		Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(null,deptId))==null);
	    Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,null))==null);
		Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(null,null))==null);
		String str = "";
		System.out.println(messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,deptId).size());
		messageDao.getMeaasgeByUserAndDeptExceptMessage(str,deptId);
		messageDao.getMeaasgeByUserAndDeptExceptMessage(null,deptId);
		messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,null);
		messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,str);
		messageDao.getMeaasgeByUserAndDeptExceptMessage(null,null);
		for (Message m : messageDao.getMeaasge(userid)) {
			System.out.println(m.getTasktype() + m.getTaskcount());
		}
	}
	/**
	 * .
	 * <p>
	 * 测试getMeaasgeByUserAndDeptOnlyMessage<br/>
	 * 方法名：testGetMeaasgeByUserAndDeptOnlyMessage
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public void testGetMeaasgeByUserAndDeptOnlyMessage(){
		// 传入用户1查询待办事宜
		String userid = "28508";
		String deptId = "24232";
		System.out.println(messageDao.getMeaasgeByUserAndDeptOnlyMessage(userid,deptId,0,12).size());
		System.out.println(messageDao.getMeaasge(userid).size());
		String str = "";
		for (Message m : messageDao.getMeaasge(userid)) {
			System.out.println(m.getTasktype() + m.getTaskcount());
		}
		messageDao.getMeaasgeByUserAndDeptOnlyMessage(str,deptId,0,5);
		messageDao.getMeaasgeByUserAndDeptOnlyMessage(null,deptId,0,5);
		messageDao.getMeaasgeByUserAndDeptOnlyMessage(userid,null,0,5);
		messageDao.getMeaasgeByUserAndDeptOnlyMessage(userid,str,0,5);
		messageDao.getMeaasgeByUserAndDeptOnlyMessage(null,null,0,5);
		Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,deptId)).size() >= 0);
		Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(null,deptId))==null);
		Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(userid,null))==null);
		Assert.assertTrue((messageDao.getMeaasgeByUserAndDeptExceptMessage(null,null))==null);
		
	}
	/**
	 * .
	 * <p>
	 * 测试getCountMessage<br/>
	 * 方法名：testGetCountMessage
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-22
	 * @since JDK1.6
	 */
	public void testGetCountMessage(){
		// 传入用户1查询待办事宜
		String userid = "28508";
		String deptId = "11207";
		String str = "";
		int d=messageDao.getCountMessage(userid,deptId);
		System.out.println(d);
		Assert.assertTrue((messageDao.getCountMessage(userid,deptId))>= 0);
		Assert.assertTrue((messageDao.getCountMessage(null,deptId)) >= 0);
		Assert.assertTrue((messageDao.getCountMessage(userid,null)) >= 0);
		Assert.assertTrue((messageDao.getCountMessage(null,null))==0);
		for (Message m : messageDao.getMeaasge(userid)) {
			System.out.println(m.getTasktype() + m.getTaskcount());
		}
		messageDao.getCountMessage(str,deptId);
		messageDao.getCountMessage(userid,str);
	}

	public void testGetMessageuser() {
		// 传入用户1查询待办事宜
		String userid = "386376";
		String deptId = "49082";
		System.out.println(messageDao.getMeaasge(userid).size());
		Assert.assertTrue((messageDao.getMeaasge(userid)).size() > 0);
		for (Message m : messageDao.getMeaasgeByUserAndDept(userid, deptId)) {
			System.out.println(m.getTasktype() + m.getTaskcount());
		}
		messageDao.getMeaasgeByUserAndDept(null, deptId);
		messageDao.getMeaasgeByUserAndDept(userid, null);
		String str = "";
		messageDao.getMeaasgeByUserAndDept(str, deptId);
		messageDao.getMeaasgeByUserAndDept(userid, str);
	}

	public void testAddMessage() {
		Message m = new Message();
		m.setCreateUser("1213124");
		m.setModifyUser("4165464");
		//m.setUserid(4358);
		m.setTasktype(TaskTypeEnum.EMAIL.value);
		m.setTaskcount(34124);
		m.setIshaveinfo("cccc");
		m.setUrl("www.baidu.com");
		m.setExpe("dasf");
		messageDao.addMessage(m);
		//messageDao.addMessage(null);
	}

	public void testDeleteMessage() {
		messageDao.deleteMessage("272022119");
	}

	public void testDeleteMessageByInvalid() {
		messageDao.deleteMessageByInvalid("CUSTMER_MESSAGE");
	}

	public void testDeleteMessageByType() {
		messageDao.deleteMessageByType("SUPER_MESSAGE");
	}

	public void testAddSuperMessage() {
		messageDao.addSuperMessage("123444", Constant.TASK_TYPE_SUPER_MESSAGE);
		messageDao.addSuperMessage(null, Constant.TASK_TYPE_SUPER_MESSAGE);
	}

	public void testGetMessageToErp() {
		List<Message> list = messageDao.getMessageForErp();
		System.out.println(list.size());
	}
	public void testaddMessageList(){
		List<Message> messageList = new ArrayList<Message>();
		Message m = new Message();
		m.setCreateUser("1213124");
		m.setModifyUser("4165464");
		m.setUserid(1241125);
		m.setTasktype(TaskTypeEnum.EMAIL.value);
		m.setTaskcount(34124);
		m.setIshaveinfo("哈哈");
		m.setUrl("www.baidu.com");
		m.setExpe("dasf");
		messageList.add(m);
		messageDao.addMessageList(messageList);
		List<Message> messageList1 = new ArrayList<Message>();
		messageDao.addMessageList(messageList1);
		messageDao.addMessageList(null);
	}
	public void testgetMeaasgeByUserAndDeptOnlyOrder(){
		String userid = "28508";
		String deptId = "23265";
		String str = "";
		messageDao.getMeaasgeByUserAndDeptOnlyOrder(userid, deptId);
		/*messageDao.getMeaasgeByUserAndDeptOnlyOrder(null, null);
		messageDao.getMeaasgeByUserAndDeptOnlyOrder(str, deptId);
		messageDao.getMeaasgeByUserAndDeptOnlyOrder(userid, str);
		messageDao.getMeaasgeByUserAndDeptOnlyOrder(null, deptId);
		messageDao.getMeaasgeByUserAndDeptOnlyOrder(userid, null);*/
	}
	public void testdeleteMessages(){
		List<String> messageIds = new ArrayList<String>();
		messageIds.add("272022546");
		messageDao.deleteMessages(messageIds);
	}
	/**
	 * autor：Zhangzw
	 * dis:   通过缓存获取某个用户待办信息统计
	 * */
	public void testGetMessageOfUserAndDpet() throws Exception{
			messageDao.getMessageOfUserAndDpet();
	}
}
