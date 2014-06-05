package com.deppon.crm.module.sysmail.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.sysmail.server.dao.ISysMailConfigDao;
import com.deppon.crm.module.sysmail.server.dao.ISysMailSendDao;
import com.deppon.crm.module.sysmail.shared.domain.AccGroResultVO;
import com.deppon.crm.module.sysmail.shared.domain.AccountGroup;
import com.deppon.crm.module.sysmail.shared.domain.MailAccount;
import com.deppon.crm.module.sysmail.shared.domain.MailGroup;
import com.deppon.crm.module.sysmail.util.SysMailConfigUtil;

public class SysMailConfigDaoTest {
	private ISysMailConfigDao sysMailConfigDao = null;
	private ISysMailSendDao sysMailSendDao = null;
	private static ApplicationContext ctx = null;
	private String[] xmls = {"SysMailConfigTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			sysMailConfigDao = ctx.getBean(ISysMailConfigDao.class);
			sysMailSendDao = ctx.getBean(ISysMailSendDao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAddMailAccount() {
		MailAccount ma = SysMailConfigUtil.createMailAccount();
		sysMailConfigDao.addMailAccount(ma);
	}
	
	
	@Test
	public void testModifyMailAccount(){
		MailAccount ma = SysMailConfigUtil.createMailAccount();
		sysMailConfigDao.addMailAccount(ma);
		MailAccount ma1 = sysMailConfigDao.getMailAccountById(ma.getId());
		ma1.setModifyUser("guozhenghong");
		ma1.setEmailAddress("dpsyj111@deppon.com");
		sysMailConfigDao.modifyMailAccount(ma1);
	}
	
	@After
	public void clearEn(){
		sysMailConfigDao.deleteMailAccountById(SysMailConfigUtil.mailAccountId);
		sysMailConfigDao.deleteMailGroupById(SysMailConfigUtil.mailGrouopId);
	}
	
	@Test
	public void testInsertMailGroup(){
		sysMailConfigDao.addMailGroup(SysMailConfigUtil.createMailGroup());
	}
	
	@Test
	public void testUpdateMailGroup(){
		MailGroup mg = SysMailConfigUtil.createMailGroup();
		sysMailConfigDao.addMailGroup(mg);
		mg = sysMailConfigDao.getMailGroupById(mg.getId());
		mg.setModifyUser("guozhenghong");
		sysMailConfigDao.modifyMailGroup(mg);
	}
	
	@Test
	public void testQueryAllMailGroup(){
		List<MailGroup> list = sysMailConfigDao.queryAllMailGroup();
	}
	
	@Test
	public void testAddAccGroRelation(){
		AccountGroup ag = new AccountGroup();
		ag.setMailAccountId("2000000");
		ag.setMailGroupId("2000001");
		sysMailConfigDao.addAccGroRelation(ag);
		//sysMailConfigDao.deleteAccGroRelation(ag);
	}
	
	@Test
	public void testQueryMailAccountByGroupId(){
		String groupId = "2000001";
		List<AccGroResultVO> accs = sysMailConfigDao.queryMailAccountByGroupId(groupId,0,10);
		Assert.assertEquals(1, accs.size());
	}
	
	@Test
	public void testDeleteRelationBatch(){
		AccountGroup ag = new AccountGroup();
		ag.setMailAccountId("2000000");
		ag.setMailGroupId("2000001");
		
		sysMailConfigDao.deleteAccGroRelation(ag);
	}
	
	
	@Test
	public void testQueryMailAccountByCondition(){
		String condition = "苏";
		List<MailAccount> list = sysMailConfigDao.queryMailAccountByCondition(condition,0,10);
		System.out.println(list.size());
		
		condition = null;
		list = sysMailConfigDao.queryMailAccountByCondition(condition,0,10);
		System.out.println(list.size());
	}
	
	@Test
	public void testCountQueryMailAccountByGroupId(){
		String groupId = "2000001";
		long i = sysMailConfigDao.countMailAccountByGroupId(groupId);
		System.out.println(i);
	}
	
	@Test
	public void testCountMailAccountByCondition(){
		String condition = "苏";
		long i = sysMailConfigDao.countMailAccountByCondition(condition);
		System.out.println(i);
		
		condition = null;
		i = sysMailConfigDao.countMailAccountByCondition(condition);
		System.out.println(i);
	}
	
	@Test
	public void testQueryAllMailAccount(){
		List<MailAccount> list = sysMailConfigDao.queryAllMailAccount();
		System.out.println(list.size());
	}
	
	@Test
	public void testRemoveRelationBatch(){
		List<String> ids = new ArrayList<String>();
		ids.add("111");
		ids.add("222");
		ids.add("223");
		ids.add("444");
		ids.add("555");
		ids.add("116661");
		sysMailConfigDao.removeRelationBatch(ids);
	}
	
	@Test
	public void testQueryMailAccountByGroupCode(){
		String groupCode = "100000";
		List<MailAccount> list = sysMailSendDao.queryMailAccountByGroupCode(groupCode);
		System.out.println(list.size());
	}
	
	@Test
	public void testSaveSendLog(){
		List<String> toSend = new ArrayList<String>();
		toSend.add("dpsyj@deppon.com");
		toSend.add("suyujun2008@gmail.com");
		sysMailSendDao.saveSendLog(toSend);
	}
}
