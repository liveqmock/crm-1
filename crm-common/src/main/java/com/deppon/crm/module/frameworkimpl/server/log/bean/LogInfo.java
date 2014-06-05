package com.deppon.crm.module.frameworkimpl.server.log.bean;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class LogInfo implements Serializable{


	private static final long serialVersionUID = 4878151283778037352L;

	//日志类型
	private String logType;
	
	//请求ID
	private String requestId;

	//日志信息
	private String logInfo;
	//LOG LEVEL
	private String logLevel;
	//LOG DATE
	private Long createDate;
	private String errorCode;

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString(){
		return new StringBuffer().append("[")
		.append(this.requestId).append(";")
		.append(this.logType).append(";")
		.append(this.logInfo).append(";")
		.append(this.logLevel).append(",")
		.append(this.createDate.toString()).append("]").toString();
	}
	
	public DBObject getDBObject() {
	    DBObject obj = new BasicDBObject();
	    Field[] fields = this.getClass().getDeclaredFields();
	    PropertyDescriptor pd = null;
	    for(int i=0;i<fields.length;i++) {
	        try {
	            pd = new PropertyDescriptor(fields[i].getName(),this.getClass());
	            Method m = pd.getReadMethod();
	            obj.put(fields[i].getName(), m.invoke(this));
	        } catch(Exception e) {
	            continue;
	        }
	    }
	    return obj;
	}

}
