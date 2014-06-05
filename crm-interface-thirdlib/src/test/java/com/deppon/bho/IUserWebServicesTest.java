package com.deppon.bho;

import org.junit.Before;
import org.junit.Test;

import com.deppon.bho.customer.IUserWebServices;
import com.deppon.bho.customer.IUserWebServicesService;
import com.deppon.bho.customer.User;

public class IUserWebServicesTest {

	IUserWebServices userWebServices;
	
	@Before
	public void init(){
		userWebServices = new IUserWebServicesService().getIUserWebServicesPort();
	}
	
	@Test
	public void bandingCustCodeTest(){
		String result = userWebServices.bandingCustCode("", "");
		System.out.println(result);
	}
	
	@Test
	public void getUserTest(){
		User user = userWebServices.getUser("xhb_paytest");
		System.out.println(user.getPassword());
	}
}
