package com.deppon.crm.module.interfaces.util;

import org.junit.Test;

import com.deppon.crm.module.client.common.exception.BusinessExceptionMessageProvider;

public class BusinessExceptionMessageProviderTest {

	@Test
	public void test1(){
		System.out.println(BusinessExceptionMessageProvider.getMessage("100000"));
	}
}
