package com.deppon.crm.module.client.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@XmlRootElement(name="interface")
@XmlAccessorType(XmlAccessType.FIELD)
public class InterfaceConfig {
	
	private static Log log = LogFactory.getLog(InterfaceConfig.class);
	
	public static final String DOUCUMENT_ROOT_ELEMENT_NAME = "interfaces";
	public static final String INTERFACE_ROOT_ELEMENT_NAME="interface";
	
	private int id;
	
	//接口类名
	@XmlElement
	private String className;
	
	//true表示CRM发布的服务实现，
	//false表示此类是提供给CRM调用外部系统的接口
	@XmlElement
	private boolean outwardService;
	
	@XmlElement(name="method")
	List<MethodConfig> methodConfigs;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InterfaceConfig() {
		methodConfigs = new ArrayList<MethodConfig>();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className.trim();
	}
	
	public boolean isOutwardService() {
		return outwardService;
	}

	public void setOutwardService(boolean outwardService) {
		this.outwardService = outwardService;
	}
	
	public List<MethodConfig> getMethodConfigs() {
		return methodConfigs;
	}

	public void setMethodConfigs(List<MethodConfig> methodConfigs) {
		this.methodConfigs = methodConfigs;
	}
	
	public void addMethodConfig(MethodConfig methodConfig){
		methodConfigs.add(methodConfig);
	}
	
	public MethodConfig getMethodConfig(String methodName){
		if (null == methodName || "".equals(methodName)) {
			log.info("argument methodName is null or empty");
			return null;
		}
		for (int i = 0; i < methodConfigs.size(); i++) {
			MethodConfig methodDetail = methodConfigs.get(i);
			if (methodDetail.getName().equals(methodName)) {
				return methodDetail;
			}
		}
		if (log.isErrorEnabled()) {
			log.info(String.format("can not find method {%s} in interface {%s} ", methodName,getClassName()));
		}
		return null;
	}
}
