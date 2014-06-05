/*package com.deppon.crm.test.client.wsdl;

import java.io.File;
import java.io.IOException;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

import com.deppon.crm.module.client.common.util.WSDLDownLoadUtil;

public class WSDLDownLoadUtilTest {

//	@Test
	public void downloadWsdlTest() throws IOException{
		WSDLDownLoadUtil.downloadWsdl("http://192.168.17.250:8080/eos-default/CallNCWSService?wsdl", "recompense.wsdl");
		
	}
	
//	@Test
	public void test2() throws IOException{
//		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
//		
//		factoryBean.setAddress("http://localhost:8080/helloworld");
//		factoryBean.setServiceClass(HelloWorld.class);
//		HelloWorld helloWorld = (HelloWorld) factoryBean.create();
//		
//		System.out.println(helloWorld.say("你好"));
	}
	
//	@Test
	public void test3() throws IOException{
		
		//WSDLDownLoadUtil.downloadWsdl("http://localhost:8080/ws?wsdl", "customer1.wsdl");
		
		File dir = new File(".");
		String path = dir.getCanonicalFile().getAbsolutePath();
		
		WSDLToJava.main(new String[]{"-d",path+"\\src\\test\\java",
				path+"\\src\\main\\resources\\wsdl\\customer1.wsdl"});
	}
	
//	@Test
	public void test4(){
		WSDLDownLoadUtil.downloadWsdl("http://192.168.22.122:8888/ws?wsdl", "huanghua.wsdl");
		
	}
	
}
*/