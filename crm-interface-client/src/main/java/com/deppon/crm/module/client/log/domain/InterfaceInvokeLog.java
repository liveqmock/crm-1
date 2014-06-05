package com.deppon.crm.module.client.log.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @修改人: 罗典
 * @时间：2012-12-28下午5:08:10
 * @描述：增加接口请求参数，响应参数，日志输出参数信息
 */
public class InterfaceInvokeLog implements Serializable {

	private static final long serialVersionUID = -7865045226101905817L;

	private int id;
	// 调用类
	private String className;
	// 调用方法
	private String method;
	// 调用时间
	private Date invokeTime;
	// 毫秒单位
	private long useTime;
	// 调用者，扩展字段
	private String user = "";
	// 是否是异常
	private boolean exception;
	// 异常信息
	private String exceptionMsg;
	// 请求参数
	private String requestMsg;
	// 响应参数
	private String responseMsg;
	// log输出信息
	private String loggerMsg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(Date invokeTime) {
		this.invokeTime = invokeTime;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isException() {
		return exception;
	}

	public void setException(boolean exception) {
		this.exception = exception;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getLoggerMsg() {
		return loggerMsg;
	}

	public void setLoggerMsg(String loggerMsg) {
		this.loggerMsg = loggerMsg;
	}

	@Override
	public String toString() {
		return "InterfaceInvokeLog [id=" + id + ", className=" + className
				+ ", method=" + method + ", invokeTime=" + invokeTime
				+ ", useTime=" + useTime + ", user=" + user + ", exception="
				+ exception + ", exceptionMsg=" + exceptionMsg + "]";
	}
}
