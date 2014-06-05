package com.deppon.crm.module.client.common.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 方法调用设置方法
 * @author davidcun @2012-4-11
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="method")
public class MethodConfig {

	private int id;
	
	//方法名字
	@XmlElement
	private String name;
	
	//访问超时，默认是一分钟
//	@XmlElement
//	private int timeOut = 60000;
	//此方法是否记录日志
	
	@XmlElement
	private boolean recordPerformanceLog = false;
	
	@XmlElement
	private boolean recordExceptionLog = false;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRecordPerformanceLog() {
		return recordPerformanceLog;
	}

	public void setRecordPerformanceLog(boolean recordPerformanceLog) {
		this.recordPerformanceLog = recordPerformanceLog;
	}

	public boolean isRecordExceptionLog() {
		return recordExceptionLog;
	}

	public void setRecordExceptionLog(boolean recordExceptionLog) {
		this.recordExceptionLog = recordExceptionLog;
	}
}
