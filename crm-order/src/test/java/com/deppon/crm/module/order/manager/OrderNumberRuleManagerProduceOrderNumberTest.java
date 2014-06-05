package com.deppon.crm.module.order.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.deppon.crm.module.order.server.manager.IOrderNumberRuleManager;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.AppContext;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml")
public class OrderNumberRuleManagerProduceOrderNumberTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	static {
		AppContext.initAppContext("application", "", "");
	}
	
	@Autowired
	private IOrderNumberRuleManager manager;
	private String date;
	
	@Before
	public void setUp(){
		executeSqlScript("fixtures/init_produceOrderNumber.sql", false);
		SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
		date = sf.format(new Date());
	}
	
	// 正常情况
	@Test
	public void produceOrderNumber(){
		String orderNumber = manager.produceOrderNumber("123456", "SUNING", "JZKY");
		assertEquals("SN"+date+"123456", orderNumber);
		
		orderNumber = manager.produceOrderNumber("123456", "SUNING", "PACKAGE");
		assertEquals("SNK"+date+"123456", orderNumber);
	}
	
	// 被禁用的规则
	@Test(expected = GeneralException.class)
	public void disabledRule(){
		manager.produceOrderNumber("123456", "SUNING", "JZKH");
		fail();
	}
	
	// 不存在的规则
	@Test(expected = GeneralException.class)
	public void notExistsRule(){
		manager.produceOrderNumber("123456", "AMAZON", "JZKY");
		fail();
	}
	
	//idSeq长度小于6位
	@Test
	public void idSeqLengthLessThanSix(){
		String orderNumber = manager.produceOrderNumber("1234", "SUNING", "JZKY");
		assertEquals("SN"+date+"001234", orderNumber);
	}
	
	//idSeq长度大于6位
	@Test
	public void idSeqLengthMoreThanSix(){
		String orderNumber = manager.produceOrderNumber("12345678", "SUNING", "JZKY");
		assertEquals("SN"+date+"345678", orderNumber);
	}
}
