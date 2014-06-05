package com.deppon.esb;

import org.junit.Before;
import org.junit.Test;

import com.deppon.esb.ws.ESBService;
import com.deppon.esb.ws.ESBServiceService;
import com.deppon.esb.ws.EsbJsonRequest;
import com.deppon.esb.ws.EsbJsonResponse;
import com.deppon.esb.ws.Exception_Exception;

public class ESBServiceTest {

	ESBService esbService;
	
	@Before
	public void init(){
		ESBServiceService service = new ESBServiceService();
		esbService = service.getESBServicePort();
	}
	
	/**
	 * ERP-2,接货单新增测试
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-26
	 *
	 */
	@Test
	public void appointCarTest(){
		
		EsbJsonRequest request = new EsbJsonRequest();
		request.setSystemName("CRM");
		request.setBody("asdfsadf");
//		System.out.println(esbService);
		try {
			EsbJsonResponse esbJsonResponse = esbService.process(request);
			System.out.println(esbJsonResponse.getStatus());
			System.out.println(esbJsonResponse.getStacktrace());
			System.out.println(esbJsonResponse.getMessage());
			System.out.println(esbJsonResponse.getSystemName());
		} catch (Exception_Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 常规理赔申请
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 */
	@Test
	public void createNormalRecompenseTest(){
		EsbJsonRequest request = new EsbJsonRequest();
		
		try {
			
			esbService.process(request);
			
		} catch (Exception_Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多赔申请测试
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 */
	@Test
	public void createMuchRecompenseTest(){
		
	}
	
	/**
	 * 合同新增及合同修改测试
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 */
	@Test
	public void createUpdateContractTest(){
		
	}
	
	/**
	 * 合同部门绑定，或者转续
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 */
	@Test
	public void bindOrConverterContractTest(){
		
	}
	
	/**
	 * 取消合同
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-27
	 *
	 */
	public void cancelContractTest(){
		
	}
	
}
