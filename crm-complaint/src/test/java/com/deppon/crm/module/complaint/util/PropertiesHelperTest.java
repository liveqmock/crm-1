package com.deppon.crm.module.complaint.util;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.complaint.server.util.PropertiesFactory;
import com.deppon.crm.module.complaint.server.util.PropertiesHelper;

public class PropertiesHelperTest {
	private PropertiesHelper ph;
	@Before
	public void initUtil(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		InputStream is = classLoader.getResourceAsStream("complaintInfo.properties");
		try {
			ph = new PropertiesHelper(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	@Test
	public void testStorefile(){
		ph.storefile("fileName");
		ph.setProperty("0", "ok");
		ph.getValue("0");
		ph.getValue("0", "ok");
		ph.printAllVlue();
		ph.removeProperty("0");
	}
}
