/*package com.deppon.crm.test.client.sync;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.sync.ICustomerInfoSend;
import com.deppon.crm.module.client.sync.dao.ISearchMemberInfosDao;

public class CustomerInfoSendTest extends TestCase  {
	ClassPathXmlApplicationContext factory;
	ICustomerInfoSend customerInfoSend; 
	private Log log = LogFactory.getLog(CustomerInfoSendTest.class);
	ISearchMemberInfosDao searchMemberInfosDao;

	
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 
	@BeforeClass
	protected void setUp() throws Exception {
		String resource = "com/deppon/crm/module/client/server/META-INF/spring.sync.api.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		customerInfoSend = (ICustomerInfoSend) factory.getBean("CustomerInfoSend");
	}

	
	private String readTestFile() throws IOException {
		URL resourceURL = ClassLoader.getSystemResource("com/deppon/crm/test/client/sync/JsonData.txt");
		System.out.println(resourceURL);
		FileReader reader = new FileReader(resourceURL.getFile());
		
		int bufferSize = 256;
		char[] cbuf = new char[bufferSize];
		int offset = 0;
		StringBuilder s = new StringBuilder();
		while (reader.read(cbuf, offset, bufferSize) > 0) {
			s.append(cbuf);
			cbuf = new char[bufferSize];
		} 
		return s.toString();
	}
	
	@Test
	public void testSend(){
		try {
			
			String jsonString = readTestFile();
			if (customerInfoSend.send("",jsonString)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} catch (CrmBusinessException e) {
			e.printStackTrace();
			if (log.isWarnEnabled()) {
				log.warn(e.getStackTrace());
			}
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
*/