/*package com.deppon.crm.test.client.sync;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sync.dao.IWaitCustomerLogDao;
import com.deppon.crm.module.client.sync.domain.TransactionNoInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfo;
import com.deppon.crm.module.client.sync.domain.WaitCustomerLogInfoDetail;

public class WaitCustomerLogDaoTest extends TestCase {

	ClassPathXmlApplicationContext factory;
	IWaitCustomerLogDao waitCustomerLogDao;

	
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 
	@BeforeClass
	protected void setUp() throws Exception {
		String resource = "com/deppon/crm/module/client/server/META-INF/spring.sync.api.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		waitCustomerLogDao = (IWaitCustomerLogDao) factory.getBean("WaitCustomerLogDao");
	}

	
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 
	@After
	protected void tearDown() throws Exception {
		;
	}

	@Test
	public void testGetTransactionNo() {
		TransactionNoInfo info = waitCustomerLogDao.getTransactionNo();
		assertEquals(info.getTRANSACTION_NO().length(), 22);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.format(new Date(System.currentTimeMillis()));
		assertEquals(info.getTRANSACTION_NO().substring(0, 8),
				format.format(new Date(System.currentTimeMillis())));
	}

	@Test
	public void testInsertWaitCustomerLog() {
		WaitCustomerLogInfo loginfo = new WaitCustomerLogInfo();
		*//** 事务序号 *//*
		loginfo.setTRANSACTION_NO("12345");
		*//** 客户编号 *//*
		loginfo.setCUSTOMER_NO("12345");
		*//** 数据进表时间 *//*
		loginfo.setCRATE_TIME(new Date());
		*//** 同步完成时间 *//*
		loginfo.setFINISH_TIME(null);
		*//** 错误处理方式 *//*
		loginfo.setHANDLE_TYPE("N");
		*//** 记录状态 *//*
		loginfo.setSTATUS("U");
		*//** 目标系统 *//*
		loginfo.setTARGET("ALL");
		*//** 最后一次的出错代码 *//*
		loginfo.setERROR_CODE(null);
		*//** 最后一次发送的出错描述 *//*
		loginfo.setERR_DESC(null);
		*//** 最后一次的出错时间 *//*
		loginfo.setERR_TIME(null);
		try {
			waitCustomerLogDao.insertWriteCustomerLog(loginfo);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}

	@Test
	public void testInsertWaitCustomerLogDetail() {
		WaitCustomerLogInfoDetail logInfo = new WaitCustomerLogInfoDetail();
		logInfo.setTRANSACTION_NO("12345");
		logInfo.setTABLE_NAME("TABLE_A");
		*//** 待同步数据的记录的关键字 *//*
		logInfo.setTABLE_KEYWORD("54321");
		*//**
		 * 操作标识 I：待插入 U：待更新 D：待删除
		 *//*
		logInfo.setOPERATION_FLG("I");
		*//**
		 * 待发送数据 从数据库取得记录实体后，序列化成JSON格式
		 *//*
		try {
			waitCustomerLogDao.insertWriteCustomerLogDetail(logInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test
	public void testGetWaitingSendDatas() {
		List<WaitCustomerLogInfo>result = waitCustomerLogDao.getWaitingSendDatas();
		assertNotNull(result);
		assertTrue(result.size() >= 1);
	}
	
	@Test
	public void testUpdateSuccessFlg() {
		String jsonString = "";
		try {
			jsonString = readTestFile();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		waitCustomerLogDao.updateSuccessFlg("9060", jsonString);
		assertTrue(true);
	}
	
	private String readTestFile() throws IOException {
		URL resourceURL = ClassLoader.getSystemResource("com/deppon/crm/test/client/sync/JsonData.txt");
		System.out.println(resourceURL);
		FileReader reader = new FileReader(resourceURL.getFile());
		
		int bufferSize = 1024;
		char[] cbuf = new char[bufferSize];
		int offset = 0;
		StringBuilder s = new StringBuilder();
		while (reader.read(cbuf, offset, bufferSize) > 0) {
			s.append(cbuf);
			cbuf = new char[bufferSize];
		} 
		System.out.println(s);
		return s.toString();
	}
	
	@Test
	public void testUpdateFailFlg() {
		WaitCustomerLogInfo info = new WaitCustomerLogInfo();
		info.setFID("9979");
		CrmBusinessException e = new CrmBusinessException("no data");
		info.setERROR_CODE("5001");  // 不存在的操作方式或表
		info.setERR_DESC(e.getMessage() != null && e.getMessage().length() >= 100 ? e.getMessage().substring(0, 100) : e.getMessage());
		info.setHANDLE_TYPE("A");
		waitCustomerLogDao.updateFailFlg(info);
		assertTrue(true);
	}
	
	@Test
	public void testUpdateSending() {
		waitCustomerLogDao.updateSendingFlg("8697");
		assertTrue(true);
	}
}
*/