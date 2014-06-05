package com.deppon.crm.module.client.esb.domain;

public class EsbExceptionInfo {
	// ID
	private String id;
	// 异常编码
	private String exceptionCode;
	// 异常类型
	private String exceptionType;
	// 请求消息
	private String requestMessage;
	// 异常发生时间
	private String createdTime;
	// 异常消息
	private String message;
	// 异常详细信息
	private String exceptinInfo;
	// 请求标识
	private String requestId;
	// 业务标识
	private String businessId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getExceptinInfo() {
		return exceptinInfo;
	}

	public void setExceptinInfo(String exceptinInfo) {
		this.exceptinInfo = exceptinInfo;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
}
