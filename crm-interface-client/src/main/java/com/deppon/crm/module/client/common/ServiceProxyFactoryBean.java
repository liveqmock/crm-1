package com.deppon.crm.module.client.common;

@SuppressWarnings("rawtypes")
public class ServiceProxyFactoryBean {

	private String className;
	private ServiceAccessProxyFactory serviceAccessProxyFactory;
	
	public ServiceProxyFactoryBean() {
	}
	public ServiceProxyFactoryBean(String className) {
		this.className = className;
	}
	
	@SuppressWarnings("unchecked")
	public Object create(){

		if (className==null || "".equals(className)) {
			throw new NullPointerException("when you want to invoke ServiceProxyFactoryBean.create()" +
					"method to create a class but the class name is null or empty String");
		}
		
		Class cls = null;
		try {
			cls = Class.forName(className);
			if (serviceAccessProxyFactory==null) {
				
				return ServiceAccessProxyFactory.createInstance().createProxyService(cls);
			}else {
				return serviceAccessProxyFactory.createProxyService(cls);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(String.format("can not find class %s when you invoke ServiceProxyFactoryBean.create() method", className),e);
		}
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
	public ServiceAccessProxyFactory getServiceAccessProxyFactory() {
		return serviceAccessProxyFactory;
	}
	public void setServiceAccessProxyFactory(
			ServiceAccessProxyFactory serviceAccessProxyFactory) {
		this.serviceAccessProxyFactory = serviceAccessProxyFactory;
	}
	
}
