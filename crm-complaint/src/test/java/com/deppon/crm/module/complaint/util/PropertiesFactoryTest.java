package com.deppon.crm.module.complaint.util;

import org.junit.Test;

import com.deppon.crm.module.complaint.server.util.PropertiesFactory;

public class PropertiesFactoryTest {
	@Test
	public void testGetPropertiesHelper(){
		PropertiesFactory.getPropertiesHelper("pFile");
	}
}
