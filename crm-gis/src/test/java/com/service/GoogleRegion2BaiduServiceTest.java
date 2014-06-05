package com.service;

import org.junit.Test;

import com.deppon.crm.module.gis.server.service.IGoogleRegion2BaiduService;
import com.deppon.crm.module.gis.server.service.impl.GoogleRegion2BaiduService;
import com.util.SpringTestHelper;

public class GoogleRegion2BaiduServiceTest {

	IGoogleRegion2BaiduService googleRegion2BaiduService=null;
	@Test
	public void test(){
		googleRegion2BaiduService= SpringTestHelper.get().getBean(GoogleRegion2BaiduService.class);
		
		googleRegion2BaiduService.executeGoogleRegion2Baidu();
		
	}
}
