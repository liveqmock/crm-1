package com.deppon.crm.module.frameworkimpl.server.log.bean;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @description 请求日志对象.
 * @author 安小虎
 * @version 0.1
 * @date 2012-7-16
 */

public class ReqLogInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// requestID
	private String requestID;
	// 客户端ip
	private String clientIP;
	// 客户端浏览器
	private String clientUserAgent;
	// 服务端ip
	private String serverIP;
	// 服务端端口
	private Integer serverPort;
	// URI
	private String requestURI;
	// 请求用户
	private String user;
	// 请求应用
	private String appName;
	// 请求模块
	private String moduleName;
	// 请求action
	private String action;
	// 请求参数
	private String reqParameters;
	// 请求开始时间
	private Long reqStartTime;
	// 请求结束时间
	private Long reqEndTime;
	// 请求时长
	private Long reqDuration;

	public ReqLogInfo() {

	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getClientUserAgent() {
		return clientUserAgent;
	}

	public void setClientUserAgent(String clientUserAgent) {
		this.clientUserAgent = clientUserAgent;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReqParameters() {
		return reqParameters;
	}

	public void setReqParameters(String reqParameters) {
		this.reqParameters = reqParameters;
	}

	public Long getReqStartTime() {
		return reqStartTime;
	}

	public void setReqStartTime(Long reqStartTime) {
		this.reqStartTime = reqStartTime;
	}

	public Long getReqEndTime() {
		return reqEndTime;
	}

	public void setReqEndTime(Long reqEndTime) {
		this.reqEndTime = reqEndTime;
	}

	public Long getReqDuration() {
		return reqDuration;
	}

	public void setReqDuration(Long reqDuration) {
		this.reqDuration = reqDuration;
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
