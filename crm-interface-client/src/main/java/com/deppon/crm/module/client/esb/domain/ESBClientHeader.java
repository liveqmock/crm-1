package com.deppon.crm.module.client.esb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.deppon.crm.module.client.common.util.DateUtils;

/**
 * @作者：罗典
 * @描述：异步客户端out Header
 * @时间：2012-11-21
 * */
public class ESBClientHeader {
	// 版本号
	protected String version = "1.0";
	// 业务唯一ID
	protected String businessId;
	// 业务保留字段1，用于描述业务的辅助信息
	protected String businessDesc1;
	// 业务保留字段2，用于描述业务的辅助信息
	protected String businessDesc2;
	// 业务保留字段3，用于描述业务的辅助信息
	protected String businessDesc3 = DateUtils.getCurrentDate();
	// 用于请求消息的唯一性标识
	protected String requestId = UUID.randomUUID().toString();
	// 用于响应消息的唯一性标识
	protected String responseId;
	// 记录客户端的前端接入系统标识，CRM默认为CRM
	protected String sourceSystem = "CRM";
	// 记录客户端的后端接入系统标识
	protected String targetSystem ;
	// ESB提供给服务消费端的服务编码
	protected String esbServiceCode;
	// 服务提供端提供给ESB的服务编码
	protected String backServiceCode;
	// 消息格式  如XML,JSON，二进制对象等
	protected String messageFormat = "XML";
	// 交互模式   1- 请求/响应，2-请求/回调，3-单向
	protected Integer exchangePattern = 3;
	// 用来标识是否是重发的消息，第一次发送为1，后续每次重发加1
	protected Integer sentSequence = 1;
	// 处理结果，0-成功：SUCCESS， 1-失败：FAILURE
	protected Integer resultCode ;
	// 用户名
	protected String userName;
	// 密码
	protected String password;
	// 服务状态列表
	protected List<StatusInfo> statusList = new ArrayList<StatusInfo>();

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessDesc1() {
		return businessDesc1;
	}

	public void setBusinessDesc1(String businessDesc1) {
		this.businessDesc1 = businessDesc1;
	}

	public String getBusinessDesc2() {
		return businessDesc2;
	}

	public void setBusinessDesc2(String businessDesc2) {
		this.businessDesc2 = businessDesc2;
	}

	public String getBusinessDesc3() {
		return businessDesc3;
	}

	public void setBusinessDesc3(String businessDesc3) {
		this.businessDesc3 = businessDesc3;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}
	
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public String getEsbServiceCode() {
		return esbServiceCode;
	}

	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	public String getBackServiceCode() {
		return backServiceCode;
	}

	public void setBackServiceCode(String backServiceCode) {
		this.backServiceCode = backServiceCode;
	}

	public String getMessageFormat() {
		return messageFormat;
	}

	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	public Integer getExchangePattern() {
		return exchangePattern;
	}

	public void setExchangePattern(Integer exchangePattern) {
		this.exchangePattern = exchangePattern;
	}

	public Integer getSentSequence() {
		return sentSequence;
	}

	public void setSentSequence(Integer sentSequence) {
		this.sentSequence = sentSequence;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<StatusInfo> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<StatusInfo> statusList) {
		this.statusList = statusList;
	}

}
