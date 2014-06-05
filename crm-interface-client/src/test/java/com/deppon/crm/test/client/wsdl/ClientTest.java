/*package com.deppon.crm.test.client.wsdl;


public class ClientTest {
	
	public void testCustomerService(){
		
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://localhost:8080/customer");
		factoryBean.setServiceClass(CustomerService.class);
		
		CustomerService service = (CustomerService) factoryBean.create();
		Customer customer = new Customer();
		customer.setAddress("customer1-add");
		List<Customer> cus=null;
		try {
			service.save(null);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
//		service.save(customer)
	}
	
	public void test1(){
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://localhost:8080/customer");
		factoryBean.setServiceClass(CustomerService.class);
		
		CustomerService service = (CustomerService) factoryBean.create();
		Customer customer = new Customer();
		customer.setAddress("customer1-add");
		List<Customer> cus=null;
		try {
			cus = service.save(null);
			System.out.println(cus.size());
		} catch (BusinessException e) {
			System.out.println(e.getFaultInfo().getErrorCode());
		}
		
	}
	
	*//**
	 * 许宏波
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-3-28
	 *
	 *//*
	public void test2(){
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://192.168.13.83:8092/alibaba/services/esbupdateService");
		factoryBean.setServiceClass(EsbupdateServicePortType.class);
		
		EsbupdateServicePortType service = (EsbupdateServicePortType) factoryBean.create();
		
		ObjectFactory factory = new ObjectFactory();
		JAXBElement<String> on = factory.createOrderOrderNumber("adfasdfafdsaf");
		Order order = new Order();
		order.setOrderNumber(on);
		service.updateOrderState(order);
	}
	
	public void test3(){
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress("http://localhost:9090/ws/helloworld");
		factoryBean.setServiceClass(HelloWorld.class);
		
		HelloWorld helloworld = (HelloWorld) factoryBean.create();
		
		Hello hello= new Hello();
		HashMap<String, Hello> maps = new HashMap<String, Hello>();
		maps.put("hello", hello);
//		helloworld.findHellos(maps);
		Hello hl = helloworld.getHello(new Hello());
		System.out.println(hl);
		
	}
}
*/